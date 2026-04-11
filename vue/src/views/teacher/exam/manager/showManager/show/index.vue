<template>
  <div class="exam-detail-page">
    <div class="summary-card">
      <div class="summary-grid">
        <div class="summary-item">
          <span class="summary-label">考试科目</span>
          <span class="summary-value">{{ form.course && form.course.name }}</span>
        </div>
        <div class="summary-item wide">
          <span class="summary-label">考试开始时间</span>
          <span class="summary-value">{{ form.examDate }}</span>
        </div>
        <div class="summary-item">
          <span class="summary-label">考试时长</span>
          <span class="summary-value">{{ form.totalTime }} 分钟</span>
        </div>
        <div class="summary-item">
          <span class="summary-label">总分</span>
          <span class="summary-value">{{ form.totalScore }} 分</span>
        </div>
        <div class="summary-item">
          <span class="summary-label">课程编号</span>
          <span class="summary-value">{{ form.course && form.course.number }}</span>
        </div>
        <div class="summary-item">
          <span class="summary-label">考试类型</span>
          <span class="summary-value">{{ form.type }}</span>
        </div>
        <div class="summary-item">
          <span class="summary-label">出题教师</span>
          <span class="summary-value">{{ form.teacher && form.teacher.name }}</span>
        </div>
      </div>
    </div>

    <div class="content-grid">
      <div class="content-card">
        <div class="content-title">考试介绍</div>
        <div class="content-body">
          <div v-html="form.description" class="w-e-text"></div>
        </div>
      </div>
      <div class="content-card">
        <div class="content-title">考生须知</div>
        <div class="content-body">
          <div v-html="form.tips" class="w-e-text"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request"

export default {
  props: {
    param: {
      type: String,
      required: false,
      default: "",
      form: []
    }
  },
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      receivedData: "",
      form: {}
    }
  },
  created() {
    this.receivedData = this.param
    this.load()
  },
  methods: {
    load() {
      request.get("exam/examManage/selectById/" + this.receivedData).then(res => {
        if (res.code === "200") {
          this.form = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    }
  }
}
</script>

<style scoped>
.exam-detail-page {
  display: flex;
  flex-direction: column;
  gap: 20px;
  padding-bottom: 8px;
}

.summary-card,
.content-card {
  padding: 20px;
  border: 1px solid #e5e7eb;
  border-radius: 6px;
  background: #fff;
  box-shadow: none;
}

.summary-grid {
  display: grid;
  grid-template-columns: repeat(3, minmax(0, 1fr));
  gap: 14px;
}

.summary-item {
  display: flex;
  flex-direction: column;
  justify-content: center;
  min-height: 84px;
  padding: 14px 18px;
  border: 1px solid #e5e7eb;
  border-radius: 6px;
  background: #fff;
}

.summary-item.wide {
  grid-column: span 2;
}

.summary-label {
  display: block;
  margin-bottom: 8px;
  color: #64748b;
  font-size: 13px;
  font-weight: 600;
}

.summary-value {
  display: block;
  color: #0f172a;
  font-size: 16px;
  font-weight: 700;
  line-height: 1.6;
  word-break: break-word;
}

.content-grid {
  display: grid;
  grid-template-columns: repeat(2, minmax(0, 1fr));
  gap: 20px;
}

.content-title {
  margin-bottom: 16px;
  padding-left: 12px;
  border-left: 4px solid #2563eb;
  font-size: 18px;
  font-weight: 700;
  color: #0f172a;
}

.content-body {
  min-height: 360px;
  padding: 18px 20px;
  border: 1px solid #e5e7eb;
  border-radius: 6px;
  background: #fff;
  overflow: auto;
}

.content-body::-webkit-scrollbar {
  width: 6px;
}

.content-body::-webkit-scrollbar-thumb {
  background: rgba(148, 163, 184, 0.5);
  border-radius: 999px;
}

@media (max-width: 1200px) {
  .summary-grid {
    grid-template-columns: repeat(2, minmax(0, 1fr));
  }

  .summary-item.wide {
    grid-column: span 1;
  }

  .content-grid {
    grid-template-columns: 1fr;
  }
}
</style>
