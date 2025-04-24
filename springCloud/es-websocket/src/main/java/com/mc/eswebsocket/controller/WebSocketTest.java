package com.mc.eswebsocket.controller;

import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint("/websocket/test/{clientId}")
@Component
public class WebSocketTest {
    private Session session;
    private String clientId;
    private ScheduledFuture<?> heartbeatTask;

    // 全局静态资源
    private static final ConcurrentMap<String, Session> sessionManager = new ConcurrentHashMap<>();
    private static final AtomicInteger onlineCount = new AtomicInteger(0);
    private static final ScheduledExecutorService heartbeatExecutor =
            Executors.newScheduledThreadPool(2);
    private static final ExecutorService messageExecutor =
            new ThreadPoolExecutor(4, 8, 30L, TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>(1000));

    // 日志格式工具
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    private static final ZoneId ZONE_ID = ZoneId.of("Asia/Shanghai");

    private String getProcessId() {
        return ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
    }

    private String buildLogHeader(String level) {
        ZonedDateTime now = ZonedDateTime.now(ZONE_ID);
        return String.format("%s %5s %s --- [%s] %s: ",
                DATE_FORMATTER.format(now),
                level,
                getProcessId(),
                Thread.currentThread().getName(),
                this.getClass().getSimpleName());
    }

    // 日志方法
    private void log(String action, String detail) {
        String logMsg = buildLogHeader("INFO") +
                String.format("%s - %s", action, detail);
        System.out.println(logMsg);
    }

    private void logError(String errorType, String message) {
        String logMsg = buildLogHeader("WARN") +
                String.format("%s - %s", errorType, message);
        System.err.println(logMsg);
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("clientId") String clientId) {
        try {
            log("连接尝试", String.format("客户端: %s | 当前会话数: %d",
                    clientId, sessionManager.size()));

            Session oldSession = sessionManager.get(clientId);
            if (oldSession != null && oldSession.isOpen()) {
                log("连接冲突", String.format("客户端: %s | 旧会话ID: %s",
                        clientId, oldSession.getId()));
                oldSession.close(new CloseReason(
                        CloseReason.CloseCodes.NORMAL_CLOSURE, "重复连接"));
            }

            this.session = session;
            this.clientId = clientId;
            session.setMaxIdleTimeout(60_000);

            sessionManager.put(clientId, session);
            int currentCount = onlineCount.incrementAndGet();
            log("连接建立", String.format("客户端: %s | 新会话ID: %s | 当前在线: %d",
                    clientId, session.getId(), currentCount));

            startHeartbeat();
        } catch (IOException e) {
            logError("连接异常", String.format("客户端: %s | 异常信息: %s",
                    clientId, e.getMessage()));
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log("消息接收", String.format("客户端: %s | 消息长度: %d字节",
                clientId, message.length()));
        handleMessage(message);
    }

    @OnClose
    public void onClose() {
        cleanupResources();
        int currentCount = onlineCount.get();
        log("连接关闭", String.format("客户端: %s | 当前在线: %d", clientId, currentCount));
    }

    @OnError
    public void onError(Throwable error) {
        logError("连接异常", String.format("客户端: %s | 原因: %s",
                clientId, error.getMessage()));
        cleanupResources();
    }

    private void startHeartbeat() {
        log("心跳启动", String.format("客户端: %s | 初始延迟: 30秒 | 间隔: 30秒", clientId));
        heartbeatTask = heartbeatExecutor.scheduleAtFixedRate(() -> {
            if (session != null && session.isOpen()) {
                try {
                    session.getBasicRemote().sendPing(null);
                    log("心跳检测", String.format("客户端: %s | 最后活动时间: %s",
                            clientId, ZonedDateTime.now(ZONE_ID).format(DATE_FORMATTER)));
                } catch (IOException e) {
                    logError("心跳异常", String.format("客户端: %s | 错误详情: %s",
                            clientId, e.getMessage()));
                    cleanupResources();
                }
            }
        }, 30, 30, TimeUnit.SECONDS);
    }

    private void handleMessage(String message) {
        if ("broadcast".equalsIgnoreCase(message)) {
            log("广播消息", String.format("客户端: %s | 触发广播", clientId));
            broadcastMessage("服务器广播消息");
        }
    }

    public void sendToClient(String clientId, String message) {
        messageExecutor.execute(() -> {
            Session target = sessionManager.get(clientId);
            if (target != null && target.isOpen()) {
                try {
                    target.getBasicRemote().sendText(message);
                    log("消息发送", String.format("目标: %s | 内容长度: %d",
                            clientId, message.length()));
                } catch (IOException e) {
                    logError("发送失败", String.format("目标: %s | 原因: %s",
                            clientId, e.getMessage()));
                }
            }
        });
    }

    public void broadcastMessage(String message) {
        sessionManager.forEach((cid, session) -> {
            if (session.isOpen()) {
                try {
                    session.getBasicRemote().sendText(message);
                    log("广播成功", String.format("目标: %s | 内容长度: %d",
                            cid, message.length()));
                } catch (IOException e) {
                    logError("广播失败", String.format("目标: %s | 原因: %s",
                            cid, e.getMessage()));
                }
            }
        });
    }

    private void cleanupResources() {
        try {
            if (session != null && session.isOpen()) {
                log("关闭会话", String.format("客户端: %s | 关闭原因: 资源清理", clientId));
                session.close();
            }

            if (sessionManager.remove(clientId) != null) {
                int current = onlineCount.decrementAndGet();
                log("会话清理", String.format("客户端: %s | 剩余在线数: %d", clientId, current));
            }

            if (heartbeatTask != null && !heartbeatTask.isCancelled()) {
                heartbeatTask.cancel(true);
                log("心跳停止", String.format("客户端: %s | 任务状态: %s",
                        clientId, heartbeatTask.isDone() ? "已完成" : "已取消"));
            }
        } catch (IOException e) {
            logError("清理异常", String.format("客户端: %s | 异常信息: %s",
                    clientId, e.getMessage()));
        }
    }

    public static int getOnlineCount() {
        return onlineCount.get();
    }
}