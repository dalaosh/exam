<template>
  <div class="student-page">
    <section class="student-page-head">
      <div>
        <h2 class="student-page-head__title">成绩榜单</h2>
        <p class="student-page-head__desc">集中查看考试基础信息和成绩排名，保持原有榜单展示逻辑不变。</p>
      </div>
    </section>

    <section class="student-result-grid">
      <article class="student-panel student-panel--padded">
        <div class="student-panel__head">
          <div>
            <h3 class="student-panel__title">考试信息</h3>
            <p class="student-panel__desc">课程、考试类型、人数与题目数量统一汇总展示。</p>
          </div>
        </div>

        <el-descriptions :column="3" border>
          <el-descriptions-item label="课程名称">
            <el-tag class="student-tag">{{ examManager.course && examManager.course.name }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="课程编码">
            <el-tag class="student-tag" type="info">{{ examManager.course && examManager.course.number }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="任课教师">
            <el-tag class="student-tag" type="info">{{ examManager.teacher && examManager.teacher.name }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="考试类别">
            <el-tag class="student-tag" type="info">{{ examManager.type }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="考生人数">
            <el-tag class="student-tag" type="warning">{{ examAnswerNum.examCourseNum }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="考试时长">
            <el-tag class="student-tag" type="warning">{{ examManager.totalTime }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="总分">
            <el-tag class="student-tag" type="danger">{{ examManager.totalScore }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="题目总数">
            <el-tag class="student-tag" type="success">{{ examAnswerNum.examPaperNum }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="有效答题数">
            <el-tag class="student-tag" type="danger">{{ examAnswerNum.examAnswerNum }}</el-tag>
          </el-descriptions-item>
        </el-descriptions>
      </article>

      <article class="student-panel student-panel--table">
        <div class="student-panel__head">
          <div>
            <h3 class="student-panel__title">金榜题名</h3>
            <p class="student-panel__desc">按分数展示榜单，前三名保留图标标识。</p>
          </div>
        </div>

        <el-table
          class="student-table student-result-table"
          :data="rankList"
          border
          height="520"
        >
          <el-table-column align="center" label="排名" width="120">
            <template slot-scope="scope">
              <img v-if="scope.$index === 0" src="@/assets/icon/img.png" class="rank-icon" alt="">
              <img v-if="scope.$index === 1" src="@/assets/icon/img_2.png" class="rank-icon" alt="">
              <img v-if="scope.$index === 2" src="@/assets/icon/img_1.png" class="rank-icon" alt="">
              {{ scope.$index + 1 }}
            </template>
          </el-table-column>
          <el-table-column prop="student.name" label="姓名" align="center" />
          <el-table-column prop="score" label="分数" align="center" />
        </el-table>
      </article>
    </section>
  </div>
</template>

<script>
import request from "@/utils/request";

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
    };
  },
  created() {
    this.receivedData = this.$route.params;
    this.findExamManager();
    this.select();
  },
  methods: {
    findExamManager() {
      request.get(`exam/examManage/selectById/${this.receivedData.id}`).then((res) => {
        if (res.code === "200") {
          this.examManager = res.data;
          this.findCountNum();
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findCountNum() {
      const params = {
        examId: this.receivedData.id,
        courseId: this.examManager.course.id
      };
      request.get("statistic/selectExamCount/selectExamAnswerStatistic", {
        params
      }).then((res) => {
        if (res.code === "200") {
          this.examAnswerNum = res.data;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    submit() {
      this.active = 1;
      this.selectStudent();
    },
    selectStudent() {
      const params = {
        courseId: this.examManager.course.id,
        isAdd: "同意"
      };
      request.get("/exam/courseStudent/selectAll", {
        params
      }).then((res) => {
        if (res.code === "200") {
          this.courseStudent = res.data;
          this.active = 2;
          this.insertStudent();
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    insertStudent() {
      request.post(`/exam/examScore/batchInsert/${this.receivedData.id}`, this.courseStudent).then((res) => {
        if (res.code === "200") {
          this.active = 3;
          this.addCourse();
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    addCourse() {
      request.post(`/exam/examScore/setScore/${this.receivedData.id}`).then((res) => {
        if (res.code === "200") {
          this.select();
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    select() {
      const params = {
        managerId: this.receivedData.id,
      };
      request.get("/exam/examScore/selectAll", {
        params
      }).then((res) => {
        if (res.code === "200") {
          this.rankList = res.data;
        } else {
          this.$message.error(res.msg);
        }
      });
    }
  }
};
</script>
