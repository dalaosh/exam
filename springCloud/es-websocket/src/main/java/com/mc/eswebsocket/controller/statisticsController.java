package com.mc.eswebsocket.controller;

import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson2.JSON;
import com.mc.eswebsocket.feignClient.StatisticFeignClient;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

@ServerEndpoint("/websocket/statistics/{clientId}")
@Component
public class  statisticsController{
    private static final Logger logger = LoggerFactory.getLogger(statisticsController.class);

    // 连接管理组件
    private static final int MAX_CONNECTIONS = 1000;
    private static final ConcurrentMap<String, Session> SESSION_MAP = new ConcurrentHashMap<>(128);
    private static final AtomicInteger ONLINE_COUNT = new AtomicInteger(0);
    private static final ReentrantLock CONNECTION_LOCK = new ReentrantLock();

    // 线程池配置（带命名和拒绝策略）
    private static final ScheduledThreadPoolExecutor SCHEDULER = new ScheduledThreadPoolExecutor(4,
            new NamedThreadFactory("WS-Scheduler"));
    private static final ThreadPoolExecutor TASK_EXECUTOR = new ThreadPoolExecutor(8, 16,
            60L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(10000),
            new NamedThreadFactory("WS-Task"),
            new ThreadPoolExecutor.CallerRunsPolicy());

    // Feign客户端代理（解决WebSocket组件无法直接注入的问题）
    private static class FeignHolder {
        private static final StatisticFeignClient CLIENT =
                SpringUtil.getBean(StatisticFeignClient.class);
    }

    // 实例相关状态
    private Session session;
    private String clientId;
    private volatile ScheduledFuture<?> pushFuture;

    @OnOpen
    public void onOpen(Session session, @PathParam("clientId") String clientId) throws IOException {
        try {
            CONNECTION_LOCK.lock();

            // 连接数校验
            if (ONLINE_COUNT.get() >= MAX_CONNECTIONS) {
                session.close(new CloseReason(
                        CloseReason.CloseCodes.TRY_AGAIN_LATER,
                        "Maximum connections reached"));
                return;
            }

            // 清理旧连接
            Session existingSession = SESSION_MAP.get(clientId);
            if (existingSession != null && existingSession.isOpen()) {
                existingSession.close(new CloseReason(
                        CloseReason.CloseCodes.VIOLATED_POLICY,
                        "New connection replacing existing"));
            }

            // 初始化新连接
            this.session = session;
            this.clientId = clientId;
            session.setMaxIdleTimeout(120_000); // 2分钟空闲超时

            SESSION_MAP.put(clientId, session);
            ONLINE_COUNT.incrementAndGet();

            // 启动数据推送
            startPushTask();
            logger.info("Client connected: {} | Total: {}", clientId, ONLINE_COUNT.get());
        } finally {
            CONNECTION_LOCK.unlock();
        }
    }

    private void startPushTask() {
        // 定时任务配置：立即启动，每5秒执行一次
        pushFuture = SCHEDULER.scheduleAtFixedRate(() -> {
            TASK_EXECUTOR.execute(this::fetchAndPushData);
        }, 0, 5, TimeUnit.SECONDS);
    }

    private void fetchAndPushData() {
        try {
            Map<String, Object> data = Map.of(
                    "systemInfo", safeGet(FeignHolder.CLIENT::getSystemInfo),
                    "basicNum", safeGet(FeignHolder.CLIENT::selectBasicNum),
                    "questionNum", safeGet(FeignHolder.CLIENT::selectQuestionNum),
                    "examAll", safeGet(FeignHolder.CLIENT::selectExamAll),
                    "strongAll", safeGet(FeignHolder.CLIENT::selectStrongAll),
                    "userCount", safeGet(FeignHolder.CLIENT::selectUserCount),
                    "userLogin", safeGet(FeignHolder.CLIENT::selectUserLogin)
            );

            String jsonPayload;
            jsonPayload = JSON.toJSONString(data);
            synchronized (session) { // 保证发送线程安全
                if (session.isOpen()) {
                    session.getBasicRemote().sendText(jsonPayload);
                }
            }
        } catch (Exception e) {
            logger.error("Data push failed for {}: {}", clientId, e.getMessage());
            cleanupResources();
        }
    }

    private Object safeGet(Supplier<?> supplier) {
        try {
            return supplier.get();
        } catch (Exception e) {
            logger.warn("Feign call failed: {}", e.getMessage());
            return Map.of("error", "Service unavailable");
        }
    }

    @OnClose
    public void onClose() {
        cleanupResources();
        logger.info("Client disconnected: {} | Remaining: {}", clientId, ONLINE_COUNT.get());
    }

    @OnError
    public void onError(Throwable error) {
        logger.error("WebSocket error: {}", error.getMessage());
        cleanupResources();
    }

    private void cleanupResources() {
        try {
            // 关闭会话
            if (session != null && session.isOpen()) {
                session.close();
            }

            // 清理记录
            if (SESSION_MAP.remove(clientId) != null) {
                ONLINE_COUNT.decrementAndGet();
            }

            // 取消定时任务
            if (pushFuture != null && !pushFuture.isDone()) {
                pushFuture.cancel(true);
            }
        } catch (IOException e) {
            logger.error("Resource cleanup failed: {}", e.getMessage());
        }
    }

    // 自定义线程工厂（带命名）
    private static class NamedThreadFactory implements ThreadFactory {
        private final String namePrefix;
        private final AtomicInteger threadNumber = new AtomicInteger(1);

        NamedThreadFactory(String namePrefix) {
            this.namePrefix = namePrefix + "-";
        }

        public Thread newThread(Runnable r) {
            return new Thread(r, namePrefix + threadNumber.getAndIncrement());
        }
    }
}
