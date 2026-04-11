<template>
  <div class="grading-page">
    <div class="page-header">
      <div>
        <div class="page-eyebrow">Grading Center</div>
        <div class="page-title">阅卷与成绩发布</div>
      </div>
      <div class="page-copy">查看待阅卷考试，并进入批改验签与成绩发布流程。</div>
    </div>

    <div class="content-card">
      <el-row v-if="visit !== -1 && visit !== 0" :gutter="20">
        <el-col :span="8" v-for="(item, index) in tableData" :key="index">
          <el-card class="grading-card" :body-style="{ padding: '0px' }">
            <div class="grading-card-body">
              <div class="info-grid">
                <div class="info-item">
                  <span class="label">考试科目</span>
                  <span class="value">{{ item.course.name }}</span>
                </div>
                <div class="info-item">
                  <span class="label">课程编码</span>
                  <span class="value">{{ item.course.number }}</span>
                </div>
                <div class="info-item wide">
                  <span class="label">考试开始时间</span>
                  <span class="value">{{ item.examDate }}</span>
                </div>
                <div class="info-item">
                  <span class="label">考试类型</span>
                  <span class="value">{{ item.type }}</span>
                </div>
                <div class="info-item">
                  <span class="label">考试时长</span>
                  <span class="value">{{ item.totalTime }} 分钟</span>
                </div>
                <div class="info-item">
                  <span class="label">总分</span>
                  <span class="value">{{ item.totalScore }} 分</span>
                </div>
                <div class="info-item">
                  <span class="label">出题人</span>
                  <span class="value">{{ item.teacher.name }}</span>
                </div>
                <div class="info-item wide">
                  <span class="label">阅卷开始时间</span>
                  <span class="value">{{ item.beginTime }}</span>
                </div>
                <div class="info-item wide">
                  <span class="label">阅卷结束时间</span>
                  <span class="value">{{ item.endTime }}</span>
                </div>
              </div>
            </div>
            <div class="card-actions">
              <el-button type="text" @click="showManager(item)" size="small">查看详情</el-button>
              <el-button type="text" @click="markCheck(item.id)" size="small">批卷验签</el-button>
              <el-button type="text" @click="teacherMarkStatistics(item.id)" size="small">公布成绩</el-button>
            </div>
          </el-card>
        </el-col>
      </el-row>

      <div v-if="visit === -1" class="empty-state empty-primary">
        请先选择课程并发布考试，之后这里会显示待阅卷考试。
      </div>
      <div v-if="visit === 0" class="empty-state empty-success">
        当前课程尚未发布考试，暂无可阅卷内容。
      </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request"

export default {
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      form: {},
      tableData: [],
      pageNum: 1,
      pageSize: 6,
      total: 0,
      course: [],
      visit: -1
    }
  },
  created() {
    this.findBySearch()
  },
  methods: {
    findBySearch() {
      request.get("/exam/examTeacherGrading/selectExam", {
        params: {
          teacherId: this.user.id
        }
      }).then(res => {
        if (res.code === "200") {
          this.tableData = res.data
          this.visit = this.tableData.length
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    showManager(row) {
      this.$router.push({
        name: "teacherShowExamMsg",
        params: {
          id: row.id
        }
      })
    },
    markCheck(id) {
      this.$router.push({
        name: "teacherMarkCheck",
        params: {
          id: id
        }
      })
    },
    teacherMarkStatistics(id) {
      this.$router.push({
        name: "teacherMarkStatistics",
        params: {
          id: id
        }
      })
    }
  }
}
</script>

<style scoped>
.grading-page {
  padding-bottom: 8px;
}

.page-header {
  display: flex;
  align-items: flex-end;
  justify-content: space-between;
  gap: 16px;
  margin-bottom: 18px;
}

.page-eyebrow {
  margin-bottom: 6px;
  font-size: 12px;
  font-weight: 700;
  letter-spacing: 0.12em;
  text-transform: uppercase;
  color: #2563eb;
}

.page-title {
  font-size: 26px;
  font-weight: 700;
  color: #0f172a;
}

.page-copy {
  color: #64748b;
  font-size: 14px;
}

.content-card {
  min-height: 640px;
  padding: 24px;
  border: 1px solid rgba(226, 232, 240, 0.95);
  border-radius: 24px;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0.98), rgba(248, 250, 252, 0.96));
  box-shadow: 0 20px 48px rgba(15, 23, 42, 0.08);
}

.grading-card {
  margin-bottom: 20px;
  border-radius: 22px;
  overflow: hidden;
  border: 1px solid #e2e8f0;
  box-shadow: 0 16px 36px rgba(15, 23, 42, 0.06);
}

.grading-card-body {
  padding: 20px;
}

.info-grid {
  display: grid;
  grid-template-columns: repeat(2, minmax(0, 1fr));
  gap: 12px;
}

.info-item {
  padding: 14px 16px;
  border-radius: 16px;
  background: #f8fafc;
  border: 1px solid #e2e8f0;
}

.info-item.wide {
  grid-column: span 2;
}

.label {
  display: block;
  margin-bottom: 8px;
  font-size: 13px;
  font-weight: 600;
  color: #64748b;
}

.value {
  display: block;
  font-size: 15px;
  font-weight: 700;
  color: #0f172a;
  line-height: 1.6;
  word-break: break-word;
}

.card-actions {
  display: flex;
  justify-content: center;
  gap: 12px;
  padding: 14px 16px 18px;
  border-top: 1px solid #e2e8f0;
  background: linear-gradient(180deg, rgba(248, 250, 252, 0.8), #fff);
}

.card-actions :deep(.el-button) {
  font-size: 14px;
  font-weight: 600;
  color: #2563eb;
}

.empty-state {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 420px;
  border-radius: 20px;
  border: 1px dashed #cbd5e1;
  background: linear-gradient(180deg, #ffffff 0%, #f8fafc 100%);
  font-size: 24px;
  font-weight: 700;
  text-align: center;
  padding: 24px;
}

.empty-primary {
  color: #1d4ed8;
}

.empty-success {
  color: #16a34a;
}
</style>
