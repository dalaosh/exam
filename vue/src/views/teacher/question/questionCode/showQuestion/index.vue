<template>
  <div class="review-page">
    <el-row :gutter="20">
      <el-col :span="8">
        <div class="review-card review-side-card">
          <div class="review-card-title">出题人信息</div>
          <el-row :gutter="16">
            <el-col :span="10">
              <el-image
                class="review-avatar"
                :src="user.photo"
                fit="cover"
              ></el-image>
            </el-col>
            <el-col :span="14">
              <div class="message">姓名：{{ user.name }}</div>
              <div class="message">性别：{{ user.sex }}</div>
              <div class="message">角色：{{ user.role }}</div>
              <div class="message">电话：{{ user.phone }}</div>
            </el-col>
          </el-row>
          <div class="message review-email">邮箱：{{ user.email }}</div>
        </div>

        <div class="review-card review-side-card">
          <div class="review-card-title">题目信息</div>
          <el-row class="info-row">
            <el-col class="info-label" :span="8">课程名称</el-col>
            <el-col class="info-value" :span="16">{{ course.name }}</el-col>
          </el-row>
          <el-row class="info-row">
            <el-col class="info-label" :span="8">题目分值</el-col>
            <el-col class="info-value" :span="16">{{ table.score }} 分</el-col>
          </el-row>
          <el-row class="info-row">
            <el-col class="info-label" :span="8">题目等级</el-col>
            <el-col class="info-value" :span="16">
              <el-rate
                v-model="table.level"
                disabled
                :colors="['#fdea5a', '#32ef17', '#8a2be2']"
                :void-color="'#e0e0e0'"
              ></el-rate>
            </el-col>
          </el-row>
          <el-row class="info-row">
            <el-col class="info-label" :span="8">题目章节</el-col>
            <el-col class="info-value" :span="16">{{ table.section }}</el-col>
          </el-row>
        </div>
      </el-col>

      <el-col :span="16">
        <div class="review-card review-main-card">
          <div class="section-title">题面</div>
          <div class="review-panel question">
            <div v-html="table.question" class="w-e-text"></div>
          </div>

          <div class="section-title">解析说明</div>
          <div class="review-panel analysis">
            <div v-html="table.analysis" class="w-e-text"></div>
          </div>
        </div>
      </el-col>
    </el-row>

    <div class="review-card workspace-card">
      <div class="workspace-header">
        <div class="review-card-title workspace-title">参考答案代码</div>
        <span class="status-badge" :class="{ 'is-active': !!result }">
          {{ result ? '已生成结果' : '等待运行' }}
        </span>
      </div>

      <el-row :gutter="20">
        <el-col :span="12">
          <div class="editor-card">
            <div class="code-toolbar">
              <div class="toolbar-meta">
                <label for="fontSize">字体大小</label>
                <input id="fontSize" type="range" min="10" max="80" v-model="fontSize" />
                <span>{{ fontSize }}px</span>
              </div>
              <el-button class="run-button" @click="runCpp">运行</el-button>
            </div>
            <div class="editor-body">
              <monaco-editor
                v-model="table.answer"
                :language="language"
                :options="editorOptions"
                style="height: 50vh;"
              ></monaco-editor>
            </div>
          </div>
        </el-col>

        <el-col :span="12">
          <div class="result-card">
            <div class="section-title result-title">运行结果</div>
            <el-input
              class="result-output"
              disabled
              type="textarea"
              :autosize="{ minRows: 14, maxRows: 14 }"
              placeholder="运行结果"
              v-model="result"
            ></el-input>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import MonacoEditor from "monaco-editor-vue"
import request from "@/utils/request"

export default {
  components: {
    MonacoEditor
  },
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      language: "c",
      form: {},
      result: "",
      fontSize: "18",
      table: {},
      course: {},
      receivedData: ""
    }
  },
  created() {
    this.receivedData = this.$route.params
    this.load()
  },
  methods: {
    load() {
      request.get("/exam/questionCode/selectById/" + this.receivedData.id).then(res => {
        if (res.code === "200") {
          this.table = res.data
          this.course = this.table.course || {}
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    runCpp() {
      if (this.language === null) {
        this.$message.error("请选择语言")
      }
      this.form = {
        m: this.table.answer,
        t: "c"
      }
      request.post("/exam/code/cpp", this.form).then(res => {
        this.result = res.data
      })
    }
  },
  computed: {
    editorOptions() {
      return {
        fontSize: this.fontSize
      }
    }
  }
}
</script>

<style scoped>
.review-page {
  padding-bottom: 8px;
}

.review-card {
  margin-bottom: 20px;
  padding: 24px;
  border: 1px solid rgba(226, 232, 240, 0.95);
  border-radius: 24px;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0.98), rgba(248, 250, 252, 0.96));
  box-shadow: 0 20px 48px rgba(15, 23, 42, 0.08);
}

.review-side-card {
  min-height: 280px;
}

.review-main-card {
  min-height: 100%;
}

.workspace-card {
  margin-top: 4px;
}

.review-card-title {
  margin-bottom: 18px;
  font-size: 20px;
  font-weight: 700;
  line-height: 1.4;
  color: #0f172a;
}

.workspace-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 12px;
  margin-bottom: 18px;
}

.workspace-title {
  margin-bottom: 0;
}

.status-badge {
  padding: 8px 14px;
  border-radius: 999px;
  background: rgba(148, 163, 184, 0.14);
  color: #475569;
  font-size: 13px;
  font-weight: 700;
}

.status-badge.is-active {
  background: rgba(37, 99, 235, 0.12);
  color: #1d4ed8;
}

.review-avatar {
  width: 100%;
  height: 220px;
  border-radius: 18px;
  overflow: hidden;
}

.message {
  margin-bottom: 12px;
  font-size: 15px;
  line-height: 1.8;
  color: #475569;
  word-break: break-all;
}

.review-email {
  margin-top: 12px;
}

.info-row {
  margin-bottom: 18px;
}

.info-label {
  color: #64748b;
  font-weight: 600;
}

.info-value {
  color: #0f172a;
  word-break: break-word;
}

.section-title {
  margin: 20px 0 12px;
  padding-left: 14px;
  border-left: 4px solid #2563eb;
  font-size: 18px;
  font-weight: 700;
  color: #0f172a;
  line-height: 1.4;
}

.section-title:first-of-type {
  margin-top: 0;
}

.review-panel {
  padding: 18px 20px;
  border: 1px solid #e2e8f0;
  border-radius: 18px;
  background: linear-gradient(180deg, #ffffff 0%, #f8fafc 100%);
  overflow: auto;
}

.question {
  min-height: 240px;
}

.analysis {
  min-height: 300px;
}

.editor-card,
.result-card {
  min-height: 100%;
  padding: 20px;
  border: 1px solid #dbe4ee;
  border-radius: 20px;
  background: linear-gradient(180deg, #ffffff 0%, #f8fafc 100%);
}

.code-toolbar {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 16px;
  margin-bottom: 16px;
  padding: 14px 16px;
  border: 1px solid #dbeafe;
  border-radius: 16px;
  background: linear-gradient(135deg, #eff6ff 0%, #f8fafc 100%);
}

.toolbar-meta {
  display: flex;
  align-items: center;
  gap: 12px;
  font-size: 15px;
  font-weight: 600;
  color: #334155;
}

.run-button {
  min-width: 96px;
  background: linear-gradient(135deg, #2563eb 0%, #1d4ed8 100%);
  border-color: transparent;
  color: #fff;
}

.editor-body {
  border: 1px solid #dbe4ee;
  border-radius: 18px;
  overflow: hidden;
}

.result-title {
  margin-top: 0;
}

.result-output :deep(.el-textarea__inner) {
  min-height: 420px !important;
  border: 1px solid #dbe4ee;
  border-radius: 18px;
  background: linear-gradient(180deg, #0f172a 0%, #111827 100%);
  color: #e2e8f0;
  font-size: 14px;
  line-height: 1.8;
}

.review-panel::-webkit-scrollbar {
  width: 6px;
}

.review-panel::-webkit-scrollbar-thumb {
  background: rgba(148, 163, 184, 0.5);
  border-radius: 999px;
}

:deep(.el-rate__icon) {
  font-size: 22px;
}

:deep(.monaco-editor),
:deep(.monaco-editor .margin),
:deep(.monaco-editor-background) {
  background: #0f172a !important;
}

:deep(.monaco-editor .current-line) {
  border-color: rgba(59, 130, 246, 0.35) !important;
}

:deep(.monaco-editor .line-numbers) {
  color: rgba(148, 163, 184, 0.72) !important;
}
</style>
