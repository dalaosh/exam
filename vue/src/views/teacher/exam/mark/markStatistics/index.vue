<template>
  <div class="statistics-page">
    <div class="summary-card">
      <el-descriptions
        labelStyle="font-size:14px;color:#64748b;font-weight:600"
        contentStyle="font-size:15px;color:#0f172a;font-weight:700"
        :column="3"
        title="考试信息"
      >
        <el-descriptions-item label="课程名称">
          <el-tag>{{ examManager.course && examManager.course.name }}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="课程编码">
          <el-tag type="info">{{ examManager.course && examManager.course.number }}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="任课教师">
          <el-tag type="info">{{ examManager.teacher && examManager.teacher.name }}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="考试类别">
          <el-tag type="info">{{ examManager.type }}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="考生人数">
          <el-tag type="warning">{{ examAnswerNum.examCourseNum }}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="考试时长">
          <el-tag type="warning">{{ examManager.totalTime }}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="总分">
          <el-tag type="danger">{{ examManager.totalScore }}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="题目总数">
          <el-tag type="success">{{ examAnswerNum.examPaperNum }}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="有效答题数">
          <el-tag type="danger">{{ examAnswerNum.examAnswerNum }}</el-tag>
        </el-descriptions-item>
      </el-descriptions>
    </div>

    <el-row :gutter="20">
      <el-col :span="8">
        <div class="workflow-card">
          <div class="panel-title">成绩统计流程</div>
          <div class="workflow-action">
            <el-button type="primary" class="primary-action" @click="submit">进行成绩统计</el-button>
          </div>
          <el-steps class="workflow-steps" direction="vertical" :active="active">
            <el-step title="第一步" description="开始进行成绩统计"></el-step>
            <el-step title="第二步" description="提取课程学生信息"></el-step>
            <el-step title="第三步" description="将学生信息写入成绩档"></el-step>
            <el-step title="第四步" description="计算每位学生的成绩"></el-step>
          </el-steps>
        </div>
      </el-col>

      <el-col :span="16">
        <div class="ranking-card">
          <div class="panel-title">成绩排名</div>
          <div class="ranking-body">
            <div class="regional_table">
              <el-table :data="rankList" border height="calc(100vh - 380px)">
                <el-table-column align="center" label="排名" width="120">
                  <template slot-scope="scope">
                    <img v-if="scope.$index === 0" src="@/assets/icon/img.png" class="rank-icon" />
                    <img v-if="scope.$index === 1" src="@/assets/icon/img_2.png" class="rank-icon" />
                    <img v-if="scope.$index === 2" src="@/assets/icon/img_1.png" class="rank-icon" />
                    {{ scope.$index + 1 }}
                  </template>
                </el-table-column>
                <el-table-column prop="student.name" label="姓名" align="center" />
                <el-table-column prop="score" label="分数" align="center" />
              </el-table>
            </div>
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
      examManager: {},
      examAnswerNum: {},
      active: 0,
      courseStudent: {},
      rankList: []
    }
  },
  created() {
    this.receivedData = this.$route.params
    this.findExamManager()
    this.select()
  },
  methods: {
    findExamManager() {
      request.get("exam/examManage/selectById/" + this.receivedData.id).then(res => {
        if (res.code === "200") {
          this.examManager = res.data
          this.findCountNum()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    findCountNum() {
      request.get("statistic/selectExamCount/selectExamAnswerStatistic", {
        params: {
          examId: this.receivedData.id,
          courseId: this.examManager.course.id
        }
      }).then(res => {
        if (res.code === "200") {
          this.examAnswerNum = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    submit() {
      this.active = 1
      this.selectStudent()
    },
    selectStudent() {
      request.get("/exam/courseStudent/selectAll", {
        params: {
          courseId: this.examManager.course.id,
          isAdd: "同意"
        }
      }).then(res => {
        if (res.code === "200") {
          this.courseStudent = res.data
          this.active = 2
          this.insertStudent()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    insertStudent() {
      request.post("/exam/examScore/batchInsert/" + this.receivedData.id, this.courseStudent).then(res => {
        if (res.code === "200") {
          this.active = 3
          this.addCourse()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    addCourse() {
      request.post("/exam/examScore/setScore/" + this.receivedData.id).then(res => {
        if (res.code === "200") {
          this.select()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    select() {
      request.get("/exam/examScore/selectAll", {
        params: {
          managerId: this.receivedData.id
        }
      }).then(res => {
        if (res.code === "200") {
          this.rankList = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    }
  }
}
</script>

<style scoped>
.statistics-page {
  padding-bottom: 8px;
}

.summary-card,
.workflow-card,
.ranking-card {
  margin-bottom: 20px;
  padding: 24px;
  border: 1px solid rgba(226, 232, 240, 0.95);
  border-radius: 24px;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0.98), rgba(248, 250, 252, 0.96));
  box-shadow: 0 20px 48px rgba(15, 23, 42, 0.08);
}

.summary-card :deep(.el-descriptions__title) {
  font-size: 20px;
  color: #0f172a;
}

.summary-card :deep(.el-tag) {
  border-radius: 999px;
  font-weight: 600;
}

.panel-title {
  margin-bottom: 18px;
  padding-left: 14px;
  border-left: 4px solid #2563eb;
  font-size: 18px;
  font-weight: 700;
  color: #0f172a;
}

.workflow-action {
  margin-bottom: 20px;
  text-align: center;
}

.primary-action {
  min-width: 180px;
  height: 46px;
  border-radius: 14px;
  font-size: 15px;
  font-weight: 700;
}

.workflow-steps {
  min-height: 440px;
  padding: 8px 0 0 8px;
}

.ranking-body {
  padding: 6px 0 0;
}

.rank-icon {
  width: 20px;
  height: 20px;
  vertical-align: middle;
  margin-right: 5px;
}

.regional_table :deep(.el-table) {
  border-radius: 18px;
  overflow: hidden;
  border: 1px solid #e2e8f0;
}

.regional_table :deep(.el-table th) {
  background: #f8fafc !important;
  color: #334155;
  font-weight: 700;
}

.regional_table :deep(.el-table td) {
  color: #475569;
}
</style>
