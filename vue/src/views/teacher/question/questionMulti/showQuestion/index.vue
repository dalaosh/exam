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
          <el-row class="info-row">
            <el-col class="info-label" :span="8">题目类型</el-col>
            <el-col class="info-value" :span="16">{{ table.questionType }}</el-col>
          </el-row>
        </div>
      </el-col>

      <el-col :span="16">
        <div class="review-card review-main-card">
          <div class="section-title">题面与选项</div>
          <div class="review-panel question">
            <div v-html="table.question" class="w-e-text question-body"></div>
            <div class="option-row"><strong>A</strong> {{ table.answerA }}</div>
            <div class="option-row"><strong>B</strong> {{ table.answerB }}</div>
            <div class="option-row"><strong>C</strong> {{ table.answerC }}</div>
            <div class="option-row"><strong>D</strong> {{ table.answerD }}</div>
          </div>

          <div class="section-title">标准答案</div>
          <div class="review-panel answer answer-highlight">
            {{ table.rightAnswer }}
          </div>

          <div class="section-title">解析说明</div>
          <div class="review-panel analysis">
            <div v-html="table.analysis" class="w-e-text"></div>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import request from "@/utils/request"

export default {
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      receivedData: "",
      table: {},
      course: {}
    }
  },
  created() {
    this.receivedData = this.$route.params
    this.load()
  },
  methods: {
    load() {
      request.get("/exam/questionMulti/selectById/" + this.receivedData.id).then(res => {
        if (res.code === "200") {
          this.table = res.data
          this.course = this.table.course || {}
        } else {
          this.$message.error(res.msg)
        }
      })
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

.review-card-title {
  margin-bottom: 18px;
  font-size: 20px;
  font-weight: 700;
  line-height: 1.4;
  color: #0f172a;
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
  margin-bottom: 16px;
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
  min-height: 300px;
}

.question-body {
  margin-bottom: 18px;
}

.option-row {
  margin-top: 10px;
  padding: 12px 14px;
  border-radius: 14px;
  background: rgba(241, 245, 249, 0.95);
  color: #334155;
}

.answer {
  min-height: 90px;
}

.answer-highlight {
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 18px;
  font-weight: 700;
  color: #1d4ed8;
}

.analysis {
  min-height: 200px;
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
</style>
