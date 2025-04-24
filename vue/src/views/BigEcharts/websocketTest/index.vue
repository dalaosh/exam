<template>
  <div>
    <div class="container" ref="vantaRef" style="width:100%;height:100vh">
      <div style="width: 800px; padding: 30px; background-color: rgba(135,150,252,0.4); border-radius: 5px;">
        <!-- WebSocket控制区域 -->
        <div style="margin-bottom: 30px; color: white">
          <h1 style="text-align: center; font-size: 28px">WebSocket 测试平台</h1>

          <!-- 服务器地址 -->
          <div style="margin: 20px 0">
            <el-input
                v-model="webSocketServer"
                placeholder="输入WebSocket地址"
                :disabled="isConnecting || isConnected"
                clearable
            ></el-input>
          </div>

          <!-- 连接控制按钮组 -->
          <div style="display: flex; gap: 10px; margin-bottom: 20px">
            <el-button
                type="success"
                :loading="isConnecting"
                :disabled="isConnected || isConnecting"
                @click="connect"
            >
              <span v-if="!isConnecting">建立连接</span>
              <span v-else>连接中...</span>
            </el-button>

            <el-button
                type="danger"
                :disabled="!isConnected"
                @click="disconnect"
            >
              断开连接
            </el-button>
          </div>

          <!-- 状态指示 -->
          <div style="display: flex; align-items: center; margin-bottom: 20px">
            <div :style="statusStyle" class="status-indicator"></div>
            <span style="margin-left: 10px">{{ connectionStatus }}</span>
          </div>

          <!-- 消息交互区域 -->
          <div style="display: flex; gap: 10px; margin-bottom: 20px">
            <el-input
                v-model="messageInput"
                placeholder="输入消息内容"
                :disabled="!isConnected"
                @keyup.enter.native="sendMessage"
                clearable
            >
              <template #append>
                <el-button
                    type="primary"
                    :disabled="!isConnected || sending"
                    :loading="sending"
                    @click="sendMessage"
                >
                  {{ sending ? '发送中...' : '发送消息' }}
                </el-button>
              </template>
            </el-input>
          </div>

          <!-- 消息历史面板 -->
          <div class="message-panel">
            <div v-for="(msg, index) in messageList" :key="index" class="message-item">
              <div :class="msg.type">
                <div class="content">{{ msg.content }}</div>
                <div class="meta">
                  <span class="timestamp">{{ msg.timestamp }}</span>
                  <span class="direction">{{ directionMap[msg.direction] }}</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import * as THREE from 'three';
import BIRDS from 'vanta/dist/vanta.birds.min';
import WebSocketClient from '../../../utils/websocket'; // 导入封装好的库

export default {
  data() {
    return {
      webSocketServer: 'ws://localhost:9998/websocket/test/mc',
      messageInput: '',
      isConnected: false,
      isConnecting: false,
      sending: false,
      connectionStatus: '未连接',
      messageList: [],
      directionMap: {
        send: '发 ↑',
        receive: '收 ↓',
        system: '系 ■'
      },
      client: null, // WebSocket 客户端实例
      vantaEffect: null
    };
  },
  computed: {
    statusStyle() {
      return {
        width: '12px',
        height: '12px',
        borderRadius: '50%',
        backgroundColor: this.isConnected ? '#52c41a' : '#ff4d4d',
        transition: 'background-color 0.3s'
      };
    }
  },
  mounted() {
    this.initBackground();
  },
  beforeDestroy() {
    this.disconnect();
    if (this.vantaEffect) {
      this.vantaEffect.destroy();
    }
  },
  methods: {
    // 连接方法
    connect() {
      if (!this.webSocketServer) {
        return this.$message.warning('请先输入WebSocket地址');
      }

      if (this.client) return;

      this.isConnecting = true;
      this.connectionStatus = '连接中...';

      // 创建客户端实例
      this.client = new WebSocketClient({
        url: this.webSocketServer,
        debug: true,
        autoReconnect: true,
        reconnectLimit: 5
      });

      // 注册事件监听
      this.client.on('open', () => {
        this.isConnected = true;
        this.isConnecting = false;
        this.connectionStatus = `已连接 (${this.webSocketServer})`;
        this.addSystemMessage('WebSocket连接成功');
        this.$message.success('连接成功');
      });

      this.client.on('message', (data) => {
        this.messageList.push({
          content: JSON.stringify(data),
          direction: 'receive',
          type: 'receive-bubble',
          timestamp: this.formatTime(new Date())
        });
      });

      this.client.on('error', (error) => {
        this.handleError(`连接错误: ${error}`);
      });

      this.client.on('close', () => {
        this.addSystemMessage('连接已关闭');
        this.resetConnection();
      });

      // 开始连接
      this.client.connect();
    },

    // 断开连接
    disconnect() {
      if (this.client) {
        this.client.disconnect();
        this.resetConnection();
        this.$message.success('已断开连接');
      }
    },

    // 发送消息
    async sendMessage() {
      if (!this.messageInput.trim()) {
        return this.$message.warning('消息内容不能为空');
      }

      try {
        this.sending = true;
        this.client.send(this.messageInput);

        this.messageList.push({
          content: this.messageInput,
          direction: 'send',
          type: 'send-bubble',
          timestamp: this.formatTime(new Date())
        });

        this.messageInput = '';
        this.$message.success('消息发送成功');
      } catch (error) {
        this.handleError('发送失败: ' + error);
      } finally {
        this.sending = false;
      }
    },

    // 错误处理
    handleError(message) {
      this.$message.error(message);
      this.addSystemMessage(message);
      this.resetConnection();
    },

    // 重置连接状态
    resetConnection() {
      this.isConnected = false;
      this.isConnecting = false;
      this.connectionStatus = '未连接';
      this.client = null;
    },

    // 时间格式化
    formatTime(date) {
      return new Intl.DateTimeFormat('zh-CN', {
        hour: '2-digit',
        minute: '2-digit',
        second: '2-digit',
        hour12: false
      }).format(date);
    },

    // 添加系统消息
    addSystemMessage(content) {
      this.messageList.push({
        content: content,
        direction: 'system',
        type: 'system-bubble',
        timestamp: this.formatTime(new Date())
      });
    },

    // 初始化背景动画
    initBackground() {
      this.$nextTick(() => {
        this.vantaEffect = BIRDS({
          el: this.$refs.vantaRef,
          THREE: THREE,
          mouseControls: true,
          touchControls: true,
          gyroControls: false,
          minHeight: 200.00,
          minWidth: 200.00,
          scale: 1.00,
          scaleMobile: 1.00
        });
      });
    }
  }
};
</script>

<style scoped>
/* 保持原有样式不变 */
.message-panel {
  height: 400px;
  border: 1px solid rgba(255,255,255,0.3);
  border-radius: 8px;
  padding: 15px;
  background-color: rgba(0,0,0,0.2);
  overflow-y: auto;
}

.message-item {
  margin: 10px 0;
  animation: fadeIn 0.3s ease-in;
}

/* 其他样式保持不变... */
</style>