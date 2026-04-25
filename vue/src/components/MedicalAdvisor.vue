<!-- 智慧考试 AI 助手组件 -->
<template>
  <div class="medical-advisor markdown-body">
    <el-drawer
      :visible.sync="drawerVisible"
      direction="rtl"
      size="560px"
      :with-header="false"
      :before-close="handleClose"
    >
      <div class="advisor-container">
        <!-- 标题区域 -->
        <div class="advisor-header">
          <div class="title">
            <img src="@/assets/imgs/examAI.png" alt="Edu-Agent" class="advisor-avatar">
            <div class="title-copy">
              <h3>Edu-Agent</h3>
              <span>智慧考试助手</span>
            </div>
          </div>
          <div class="header-actions">
            <el-switch
              v-model="showReasoning"
              active-text="显示思考过程"
              inactive-text=""
              class="reasoning-switch"
            ></el-switch>
            <el-button
              type="text"
              icon="el-icon-close"
              @click="drawerVisible = false"
              class="close-btn"
            ></el-button>
          </div>
        </div>

        <!-- 聊天内容区域 -->
        <div class="chat-container" ref="chatContainer">
          <div
            v-for="(message, index) in chatMessages"
            :key="index"
            class="message"
            :class="{'bot-message': message.sender === 'bot', 'user-message': message.sender === 'user'}"
          >
            <div v-if="message.sender === 'bot'" class="avatar">
              <img src="@/assets/imgs/examAI.png" alt="AI">
            </div>
            <div v-else class="avatar">
              <img src="@/assets/imgs/doctor.png" alt="用户">
            </div>
            <div class="message-content">
              <div
                v-if="message.sender === 'bot' && loading && index === chatMessages.length - 1 && !message.content && !message.reasoningContent"
                class="typing-indicator"
              >
                <div class="expert-loader">
                  <span class="expert-loader__node"></span>
                  <span class="expert-loader__node"></span>
                  <span class="expert-loader__node"></span>
                </div>
                <span class="typing-text">多学科专家正在协同分析</span>
              </div>
              <div v-else>
                <!-- 思考内容区域 - 确保在有reasoningContent且不等于content时显示 -->
                <div v-if="message.sender === 'bot' && message.reasoningContent && message.reasoningContent !== message.content && showReasoning"
                    class="message-text reasoning markdown-body"
                    v-html="formatMessage(message.reasoningContent)">
                </div>
                <!-- 主要内容区域 -->
                <div class="message-text markdown-body" v-html="formatMessage(message.content || '')"></div>
              </div>
            </div>
          </div>
        </div>

        <!-- 输入区域 -->
        <div class="input-container">
          <el-input
            type="textarea"
            :autosize="{ minRows: 2, maxRows: 4 }"
            v-model="inputMessage"
            placeholder="请输入您的考试问题..."
            @keydown.enter.native="handleEnterKey"
          ></el-input>
          <div class="input-actions">
            <el-button
              type="info"
              icon="el-icon-delete"
              @click="clearChat"
            >清空对话</el-button>
            <el-button
              type="primary"
              icon="el-icon-s-promotion"
              :disabled="!inputMessage.trim() || loading"
              @click="sendMessage"
            >发送</el-button>

          </div>
        </div>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import { medicalAdvisorAPI } from '@/utils/AIService';
import MarkdownIt from 'markdown-it';
import DOMPurify from 'dompurify';
import hljs from 'highlight.js';
import 'highlight.js/styles/github.css';

// 配置 markdown-it
const md = new MarkdownIt({
  html: true,
  linkify: true,
  typographer: true,
  breaks: true,
  highlight: function(str, lang) {
    if (lang && hljs.getLanguage(lang)) {
      try {
        return '<pre class="hljs"><code>' +
               hljs.highlight(str, { language: lang, ignoreIllegals: true }).value +
               '</code></pre>';
      } catch (__) {}
    }
    // 对未指定语言的代码块使用普通高亮
    return '<pre class="hljs"><code>' + md.utils.escapeHtml(str) + '</code></pre>';
  }
});

// 调整 markdown-it 配置以获得更好的输出
md.renderer.rules.table_open = () => '<div class="table-container"><table class="md-table">';
md.renderer.rules.table_close = () => '</table></div>';

export default {
  name: "MedicalAdvisor",
  props: {
    visible: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      drawerVisible: false,
      inputMessage: "",
      chatMessages: [],
      loading: false,
      messages: [], // 用于API请求的聊天历史
      currentReasoningContent: "", // 存储当前思考内容
      showReasoning: true // 控制是否显示思考过程
    };
  },
  watch: {
    visible(newVal) {
      this.drawerVisible = newVal;
      if (newVal && this.chatMessages.length === 0) {
        // 第一次打开时发送欢迎消息
        this.addBotMessage("您好，我是 Edu-Agent，可以为您解答考试、课程和平台使用问题。");
      }
    },
    drawerVisible(newVal) {
      if (!newVal) {
        this.$emit('update:visible', false);
      }
    },
    chatMessages() {
      this.$nextTick(() => {
        this.scrollToBottom();
      });
    }
  },
  methods: {
    handleClose(done) {
      this.$emit('update:visible', false);
      done();
    },
    formatMessage(message) {
      if (!message) return '';

      try {
        // 使用 markdown-it 解析 markdown
        const renderedHtml = md.render(message);

        // 使用 DOMPurify 净化 HTML 防止 XSS 攻击
        const sanitizedHtml = DOMPurify.sanitize(renderedHtml, {
          ADD_ATTR: ['target'], // 允许链接使用 target="_blank"
          FORBID_TAGS: ['style', 'script', 'iframe'], // 禁止可能导致问题的标签
          FORBID_ATTR: ['style', 'onerror', 'onload'], // 禁止可能导致问题的属性
        });

        return sanitizedHtml;
      } catch (error) {
        console.error('Markdown parsing error:', error);
        // 出错时返回转义后的原始内容
        return message
          .replace(/</g, '&lt;')
          .replace(/>/g, '&gt;')
          .replace(/\n/g, '<br>');
      }
    },
    sendMessage() {
      const message = this.inputMessage.trim();
      if (!message || this.loading) return;

      // 添加用户消息到聊天记录
      this.addUserMessage(message);
      this.inputMessage = "";

      // 更新聊天历史
      this.messages.push({
        role: 'user',
        content: message
      });

      this.loading = true;

      // 重置思考内容
      this.currentReasoningContent = "";

      // 添加一个空的机器人消息作为流式响应的容器
      const botMessageIndex = this.chatMessages.length;
      this.addBotMessage("");

      // 流式响应处理
      medicalAdvisorAPI.chatStream(this.messages, {
        onMessage: (chunk) => {
          try {
            if (chunk === "[DONE]") return;

            const data = JSON.parse(chunk);
            if (!data.choices || data.choices.length === 0) return;

            const choice = data.choices[0];
            const delta = choice.delta || {};

            // 当前消息对象
            const currentMessage = this.chatMessages[botMessageIndex];

            // 检查delta中是否包含reasoning_content字段（无论值是什么）
            if ('reasoning_content' in delta) {
              const reasoningContent = delta.reasoning_content || '';

              // 累积思考内容
              this.currentReasoningContent += reasoningContent;

              // 更新消息中的思考内容字段，但不立即渲染
              this.$set(currentMessage, 'reasoningContent', this.currentReasoningContent);

              // 如果尚未开始接收正式内容，在内容区域也显示思考内容
              if (!currentMessage.hasContent) {
                this.$set(currentMessage, 'content', this.currentReasoningContent);
              }
            }

            // 检查delta中是否包含content字段（无论值是什么）
            if ('content' in delta) {
              const content = delta.content || '';

              // 如果这是第一个content块
              if (!currentMessage.hasContent) {
                // 将思考内容保存下来，并清空内容准备接收正式内容
                this.$set(currentMessage, 'hasContent', true);

                // 如果之前显示的是思考内容，现在清空
                if (currentMessage.content === this.currentReasoningContent) {
                  this.$set(currentMessage, 'content', '');
                }
              }

              // 追加内容
              const updatedContent = (currentMessage.content || '') + content;
              this.$set(currentMessage, 'content', updatedContent);
              currentMessage.rawContent = updatedContent;
            }

            // 如果收到完成信号
            if (choice.finish_reason === "stop") {
              console.log("收到停止信号，当前状态:", {
                hasContent: currentMessage.hasContent,
                content: currentMessage.content,
                reasoningContent: currentMessage.reasoningContent,
                currentReasoning: this.currentReasoningContent
              });

              // 如果有思考内容但没有正式内容
              if (this.currentReasoningContent && (!currentMessage.content || currentMessage.content === '')) {
                this.$set(currentMessage, 'content', this.currentReasoningContent);
                currentMessage.rawContent = this.currentReasoningContent;
              }

              // 记录完整回复到历史
              const fullContent = currentMessage.rawContent || '';

              this.messages.push({
                role: 'assistant',
                content: fullContent
              });

              // 限制历史长度，防止请求过大
              if (this.messages.length > 20) {
                // 保留最近的对话，但始终保留第一条系统引导消息
                const firstMessage = this.messages[0];
                this.messages = [firstMessage, ...this.messages.slice(-19)];
              }
            }

            // 滚动到底部
            this.scrollToBottom();
          } catch (e) {
            console.error("解析流式响应失败:", e, chunk);
          }
        },
        onComplete: () => {
          this.loading = false;
        },
        onError: (error) => {
          console.error("流式请求出错:", error);
          const errorMessage = "\n\n(请求出错，请稍后再试)";
          const currentMessage = this.chatMessages[botMessageIndex];
          this.$set(currentMessage, 'content', (currentMessage.content || '') + errorMessage);
          this.loading = false;
          this.currentReasoningContent = "";
        }
      });
    },

    addUserMessage(content) {
      this.chatMessages.push({
        sender: "user",
        content: content,
        time: this.getCurrentTime()
      });
    },

    addBotMessage(content) {
      this.chatMessages.push({
        sender: "bot",
        content: content,
        reasoningContent: "",
        hasContent: false,
        time: this.getCurrentTime()
      });
    },
    getCurrentTime() {
      const now = new Date();
      const hours = now.getHours().toString().padStart(2, '0');
      const minutes = now.getMinutes().toString().padStart(2, '0');
      return `${hours}:${minutes}`;
    },
    scrollToBottom() {
      const container = this.$refs.chatContainer;
      if (container) {
        container.scrollTop = container.scrollHeight;
      }
    },
    clearChat() {
      this.$confirm('确定要清空所有对话记录吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.chatMessages = [];
        this.messages = [];
        this.currentReasoningContent = "";

        // 重新添加欢迎消息
        this.addBotMessage("您好，我是 Edu-Agent，可以为您解答考试、课程和平台使用问题。");

        // 添加到历史
        this.messages.push({
          role: 'assistant',
          content: "您好，我是 Edu-Agent，可以为您解答考试、课程和平台使用问题。"
        });
      }).catch(() => {});
    },
    handleEnterKey(event) {
      if (event.shiftKey) {
        return;
      }

      event.preventDefault();
      this.sendMessage();
    }
  }
};
</script>

<style scoped>
.medical-advisor {
  font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", Arial, sans-serif;
}

.medical-advisor :deep(.el-drawer) {
  background: #f4f8fc;
  box-shadow: -18px 0 40px rgba(5, 18, 45, 0.28);
}

.medical-advisor :deep(.el-drawer__body) {
  overflow: hidden;
}

.advisor-container {
  display: flex;
  flex-direction: column;
  height: 100%;
  background:
    radial-gradient(circle at 18% 8%, rgba(30, 210, 255, 0.16), transparent 30%),
    linear-gradient(180deg, #eef6ff 0%, #f7fbff 38%, #ffffff 100%);
}

.advisor-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 18px 24px;
  background:
    linear-gradient(135deg, rgba(8, 30, 76, 0.98) 0%, rgba(18, 92, 181, 0.98) 58%, rgba(20, 191, 220, 0.95) 100%);
  color: white;
  border-bottom: 1px solid rgba(138, 212, 255, 0.35);
  box-shadow: 0 12px 28px rgba(14, 73, 151, 0.18);
  position: relative;
  overflow: hidden;
}

.advisor-header::before {
  content: "";
  position: absolute;
  inset: 0;
  background:
    linear-gradient(90deg, rgba(255, 255, 255, 0.08) 1px, transparent 1px),
    linear-gradient(0deg, rgba(255, 255, 255, 0.06) 1px, transparent 1px);
  background-size: 34px 34px;
  opacity: 0.3;
  pointer-events: none;
}

.advisor-header::after {
  content: "";
  position: absolute;
  right: 72px;
  top: -42px;
  width: 170px;
  height: 170px;
  border-radius: 50%;
  border: 1px solid rgba(145, 224, 255, 0.22);
  box-shadow: 0 0 44px rgba(52, 195, 255, 0.22);
  pointer-events: none;
}

.advisor-header .title {
  display: flex;
  align-items: center;
  position: relative;
  z-index: 1;
}

.title-copy {
  display: flex;
  flex-direction: column;
  gap: 2px;
}

.title-copy h3 {
  margin: 0;
  font-size: 24px;
  line-height: 1.1;
  font-weight: 700;
  letter-spacing: 0;
}

.title-copy span {
  font-size: 12px;
  line-height: 1.2;
  color: rgba(220, 245, 255, 0.82);
}

.header-actions {
  display: flex;
  align-items: center;
  position: relative;
  z-index: 1;
}

.reasoning-switch {
  margin-right: 15px;
}

.reasoning-switch :deep(.el-switch__label) {
  color: white;
  font-weight: 500;
}

.reasoning-switch :deep(.el-switch__core) {
  border-color: rgba(218, 241, 255, 0.8);
  background: rgba(255, 255, 255, 0.16);
}

.reasoning-switch :deep(.el-switch.is-checked .el-switch__core) {
  border-color: rgba(79, 232, 255, 0.95);
  background-color: #19c4de;
}

.advisor-avatar {
  width: 46px;
  height: 46px;
  border-radius: 50%;
  margin-right: 12px;
  padding: 4px;
  background: rgba(255, 255, 255, 0.92);
  box-shadow: 0 0 0 1px rgba(184, 235, 255, 0.55), 0 8px 22px rgba(0, 15, 46, 0.22);
}

.close-btn {
  color: white;
  font-size: 20px;
  border-radius: 50%;
  transition: background-color 0.2s ease, transform 0.2s ease;
}

.close-btn:hover {
  color: #ffffff;
  background-color: rgba(255, 255, 255, 0.14);
  transform: rotate(90deg);
}

.chat-container {
  flex: 1;
  padding: 24px 22px;
  overflow-y: auto;
  background:
    radial-gradient(circle at 96% 16%, rgba(40, 180, 255, 0.12), transparent 22%),
    linear-gradient(180deg, rgba(245, 250, 255, 0.94), rgba(255, 255, 255, 0.96));
}

.chat-container::-webkit-scrollbar {
  width: 6px;
}

.chat-container::-webkit-scrollbar-thumb {
  background: rgba(31, 118, 196, 0.24);
  border-radius: 999px;
}

.message {
  display: flex;
  margin-bottom: 20px;
  max-width: 88%;
}

.bot-message {
  margin-right: auto;
}

.user-message {
  margin-left: auto;
  flex-direction: row-reverse;
}

.avatar {
  width: 38px;
  height: 38px;
  border-radius: 50%;
  overflow: hidden;
  margin: 0 10px;
  flex-shrink: 0;
  background: #ffffff;
  border: 1px solid rgba(135, 202, 242, 0.45);
  box-shadow: 0 8px 20px rgba(20, 70, 120, 0.12);
}

.avatar img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.message-content {
  padding: 13px 16px;
  position: relative;
  border-radius: 14px;
}

.bot-message .message-content {
  width: 100%;
  color: #1c2a3a;
  background: rgba(255, 255, 255, 0.92);
  border: 1px solid rgba(137, 194, 233, 0.28);
  box-shadow: 0 12px 28px rgba(23, 77, 132, 0.08);
}

.user-message .message-content {
  background: linear-gradient(135deg, #1476d4 0%, #18b7d4 100%);
  box-shadow: 0 10px 24px rgba(19, 123, 202, 0.22);
  color: #ffffff;
}

.user-message .message-text h1,
.user-message .message-text h2,
.user-message .message-text h3,
.user-message .message-text h4,
.user-message .message-text h5,
.user-message .message-text h6,
.user-message .message-text a {
  color: #ffffff;
}

.message-text {
  line-height: 1.5;
  word-break: break-word;
  overflow-wrap: break-word;
}

.message-text p {
  margin: 0 0 0.8em;
}

.message-text p:last-child {
  margin-bottom: 0;
}

.message-text h1,
.message-text h2,
.message-text h3,
.message-text h4,
.message-text h5,
.message-text h6 {
  margin: 1em 0 0.5em;
  line-height: 1.25;
  font-weight: 600;
  color: #24292e;
}

.message-text h1:first-child,
.message-text h2:first-child,
.message-text h3:first-child {
  margin-top: 0;
}

.message-text h1 {
  font-size: 1.5em;
  padding-bottom: 0.2em;
  border-bottom: 1px solid #eaecef;
}

.message-text h2 {
  font-size: 1.3em;
  padding-bottom: 0.2em;
  border-bottom: 1px solid #eaecef;
}

.message-text h3 {
  font-size: 1.1em;
}

.message-text code {
  font-family: 'SFMono-Regular', Consolas, 'Liberation Mono', Menlo, monospace;
  background-color: rgba(27,31,35,0.05);
  border-radius: 3px;
  padding: 0.2em 0.4em;
  font-size: 85%;
}

.message-text pre {
  margin: 0.8em 0;
  padding: 0;
  background-color: transparent;
  overflow: visible;
}

.message-text pre.hljs {
  background-color: #f6f8fa;
  border-radius: 6px;
  padding: 1em;
  overflow: auto;
  font-size: 85%;
  line-height: 1.45;
}

.message-text pre.hljs code {
  background-color: transparent;
  padding: 0;
  margin: 0;
  border-radius: 0;
  white-space: pre;
  font-family: 'SFMono-Regular', Consolas, 'Liberation Mono', Menlo, monospace;
}

.message-text ul,
.message-text ol {
  padding-left: 1.5em;
  margin: 0.5em 0;
}

.message-text li {
  margin: 0.2em 0;
}

.message-text li+li {
  margin-top: 0.1em;
}

.message-text li > p {
  margin: 0;
}

.message-text blockquote {
  margin: 0.8em 0;
  padding: 0 1em;
  color: #6a737d;
  border-left: 0.25em solid #dfe2e5;
}

.message-text blockquote > :first-child {
  margin-top: 0;
}

.message-text blockquote > :last-child {
  margin-bottom: 0;
}

.message-text .table-container {
  overflow-x: auto;
  margin: 0.8em 0;
}

.message-text .md-table {
  border-collapse: collapse;
  width: 100%;
  margin: 0;
  display: table;
}

.message-text .md-table th,
.message-text .md-table td {
  border: 1px solid #dfe2e5;
  padding: 0.4em 0.8em;
  text-align: left;
}

.message-text .md-table th {
  font-weight: 600;
  background-color: #f6f8fa;
}

.message-text .md-table tr:nth-child(2n) {
  background-color: #f6f8fa;
}

.message-text a {
  color: #0366d6;
  text-decoration: none;
}

.message-text a:hover {
  text-decoration: underline;
}

.message-text hr {
  height: 0.25em;
  padding: 0;
  margin: 1em 0;
  background-color: #e1e4e8;
  border: 0;
}

.message-text strong {
  font-weight: 600;
}

.message-text img {
  max-width: 100%;
  box-sizing: content-box;
  background-color: #fff;
  margin: 0.5em 0;
}

/* 思考内容样式 */
.message-text.reasoning {
  color: #476072;
  font-style: italic;
  background: linear-gradient(135deg, rgba(232, 248, 255, 0.96), rgba(244, 250, 255, 0.96));
  padding: 10px;
  border-radius: 6px;
  border-left: 3px solid #19c4de;
  margin-bottom: 10px;
  position: relative;
  font-size: 0.95em;
}

.message-text.reasoning::before {
  content: "思考过程";
  display: block;
  font-weight: bold;
  margin-bottom: 5px;
  color: #1278bd;
  font-size: 0.9em;
}

.message-text.reasoning::after {
  content: "";
  position: absolute;
  bottom: -5px;
  left: 20px;
  width: 0;
  height: 0;
  border-left: 5px solid transparent;
  border-right: 5px solid transparent;
  border-top: 5px solid #e8f8ff;
}

.message-time {
  font-size: 12px;
  color: #909399;
  margin-top: 5px;
  text-align: right;
}

.input-container {
  padding: 16px 20px 18px;
  background: rgba(255, 255, 255, 0.96);
  border-top: 1px solid rgba(135, 180, 220, 0.28);
  box-shadow: 0 -12px 28px rgba(8, 44, 88, 0.06);
}

.input-container :deep(.el-textarea__inner) {
  border-radius: 10px;
  border-color: rgba(88, 146, 198, 0.28);
  background: #fbfdff;
  color: #1c2a3a;
  resize: none;
  transition: border-color 0.2s ease, box-shadow 0.2s ease;
}

.input-container :deep(.el-textarea__inner:focus) {
  border-color: #18b7d4;
  box-shadow: 0 0 0 3px rgba(24, 183, 212, 0.12);
}

.input-actions {
  display: flex;
  justify-content: space-between;
  margin-top: 12px;
}

.input-actions .el-button {
  margin-left: 10px;
  border-radius: 8px;
  font-weight: 600;
}

.input-actions :deep(.el-button--info) {
  color: #536273;
  background: #eef3f8;
  border-color: #dbe6ef;
}

.input-actions :deep(.el-button--primary) {
  background: linear-gradient(135deg, #1378d6 0%, #18b7d4 100%);
  border-color: transparent;
  box-shadow: 0 8px 18px rgba(19, 120, 214, 0.22);
}

.input-actions :deep(.el-button--primary.is-disabled) {
  background: #b9d8f4;
  box-shadow: none;
}

@media (max-width: 640px) {
  .medical-advisor :deep(.el-drawer) {
    width: 100% !important;
  }

  .advisor-header {
    padding: 16px;
  }

  .title-copy h3 {
    font-size: 22px;
  }

  .reasoning-switch {
    margin-right: 8px;
  }

  .message {
    max-width: 94%;
  }
}

/* 打字指示器样式 */
.typing-indicator {
  display: flex;
  align-items: center;
  gap: 10px;
  padding: 2px 0;
  min-height: 24px;
}

.expert-loader {
  display: inline-flex;
  align-items: center;
  gap: 5px;
  padding: 5px 7px;
  border-radius: 999px;
  background: rgba(22, 147, 213, 0.08);
  border: 1px solid rgba(24, 183, 212, 0.16);
}

.expert-loader__node {
  width: 7px;
  height: 7px;
  background: linear-gradient(135deg, #1378d6, #18b7d4);
  border-radius: 50%;
  display: inline-block;
  box-shadow: 0 0 10px rgba(24, 183, 212, 0.35);
  animation: expertPulse 1.4s infinite ease-in-out;
}

.expert-loader__node:nth-child(1) {
  animation-delay: 0s;
}

.expert-loader__node:nth-child(2) {
  animation-delay: 0.2s;
}

.expert-loader__node:nth-child(3) {
  animation-delay: 0.4s;
}

.typing-text {
  color: #31516a;
  font-size: 14px;
  line-height: 1.4;
}

.typing-text::after {
  content: "...";
  display: inline-block;
  width: 18px;
  text-align: left;
  animation: typingEllipsis 1.2s steps(4, end) infinite;
}

@keyframes expertPulse {
  0%, 60%, 100% {
    opacity: 0.45;
    transform: translateY(0) scale(0.92);
  }
  30% {
    opacity: 1;
    transform: translateY(-3px) scale(1);
  }
}

@keyframes typingEllipsis {
  0% {
    content: "";
  }
  25% {
    content: ".";
  }
  50% {
    content: "..";
  }
  75%, 100% {
    content: "...";
  }
}

/* 代码块相关样式 */
.code-block {
  background-color: #f6f8fa;
  border-radius: 6px;
  padding: 16px;
  overflow: auto;
  margin: 12px 0;
  font-family: 'SFMono-Regular', Consolas, 'Liberation Mono', Menlo, monospace;
  font-size: 85%;
}

.code-block code {
  background-color: transparent !important;
  padding: 0 !important;
  border-radius: 0 !important;
  font-family: inherit !important;
}

/* 确保正确展示 */
.markdown-body pre {
  margin: 12px 0;
  padding: 16px;
  overflow: auto;
  background-color: #f6f8fa;
  border-radius: 6px;
  line-height: 1.45;
}

.markdown-body code {
  font-family: SFMono-Regular, Consolas, Liberation Mono, Menlo, monospace;
  padding: 0.2em 0.4em;
  margin: 0;
  font-size: 85%;
  background-color: rgba(27,31,35,0.05);
  border-radius: 3px;
}

.markdown-body pre > code {
  padding: 0;
  margin: 0;
  font-size: 100%;
  word-break: normal;
  white-space: pre;
  background: transparent;
  border: 0;
}

/* 修改代码块样式 */
.markdown-code-block {
  margin: 16px 0;
  background-color: #f6f8fa;
  border-radius: 6px;
  overflow: hidden;
}

.markdown-code-block pre {
  margin: 0 !important;
  padding: 16px;
  overflow: auto;
  background-color: #f6f8fa;
  border-radius: 0 !important;
}

.markdown-code-block code {
  background-color: transparent !important;
  padding: 0 !important;
  font-family: 'SFMono-Regular', Consolas, 'Liberation Mono', Menlo, monospace;
  font-size: 85%;
  white-space: pre;
  display: block;
}

/* 代码块样式 */
.code-block-wrapper {
  margin: 16px 0;
  border-radius: 6px;
  overflow: hidden;
  background-color: #f6f8fa;
}

.code-block-wrapper pre {
  margin: 0 !important;
  padding: 16px;
  overflow: auto;
  font-family: SFMono-Regular, Consolas, Liberation Mono, Menlo, monospace;
  font-size: 85%;
  line-height: 1.45;
}

.code-block-wrapper code {
  background-color: transparent !important;
  padding: 0 !important;
  white-space: pre;
}

/* Markdown样式 */
.message-text h1 {
  font-size: 1.5em;
  margin: 16px 0 10px;
  padding-bottom: 0.3em;
  border-bottom: 1px solid #eaecef;
}

.message-text h2 {
  font-size: 1.3em;
  margin: 16px 0 8px;
  padding-bottom: 0.3em;
  border-bottom: 1px solid #eaecef;
}

.message-text h3 {
  font-size: 1.1em;
  margin: 16px 0 8px;
}

.message-text code {
  font-family: SFMono-Regular, Consolas, Liberation Mono, Menlo, monospace;
  background-color: rgba(27,31,35,0.05);
  border-radius: 3px;
  padding: 0.2em 0.4em;
  font-size: 85%;
}

.message-text blockquote {
  margin: 16px 0;
  padding: 0 1em;
  color: #6a737d;
  border-left: 0.25em solid #dfe2e5;
}

.message-text ul, .message-text ol {
  padding-left: 2em;
  margin: 16px 0;
}

.message-text li {
  margin: 4px 0;
}

.message-text table {
  border-collapse: collapse;
  margin: 16px 0;
  width: 100%;
}

.message-text table td {
  border: 1px solid #dfe2e5;
  padding: 6px 13px;
}

.message-text strong {
  font-weight: 600;
}

.message-text em {
  font-style: italic;
}

.message-text a {
  color: #0366d6;
  text-decoration: none;
}

.message-text a:hover {
  text-decoration: underline;
}

.message-text hr {
  height: 0.25em;
  padding: 0;
  margin: 24px 0;
  background-color: #e1e4e8;
  border: 0;
}

.message-text p {
  margin: 0 0 12px;
  line-height: 1.5;
}

.message-text p:last-child {
  margin-bottom: 0;
}

.message-text ul, .message-text ol {
  padding-left: 2em;
  margin: 12px 0;
}

.message-text li + li {
  margin-top: 2px;
}

.message-text li > p {
  margin: 0;
}

/* 减少标题上下间距 */
.message-text h1, .message-text h2, .message-text h3 {
  margin-top: 12px;
  margin-bottom: 8px;
}

.message-text h1:first-child,
.message-text h2:first-child,
.message-text h3:first-child {
  margin-top: 0;
}

/* 优化引用块间距 */
.message-text blockquote {
  margin: 12px 0;
  padding: 0 1em;
  color: #6a737d;
  border-left: 0.25em solid #dfe2e5;
}

.message-text blockquote > p {
  margin: 6px 0;
}
</style>
