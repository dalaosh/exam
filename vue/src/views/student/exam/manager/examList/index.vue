<template>
  <div class="student-page">
    <section class="student-page-head">
      <div>
        <h2 class="student-page-head__title">考试列表</h2>
        <p class="student-page-head__desc">按课程和考试类型筛选试卷，统一查看详情、环公钥、答案和成绩榜单。</p>
      </div>
      <div class="student-page-head__meta">
        <el-tag class="student-tag" type="info">共 {{ total }} 场考试</el-tag>
      </div>
    </section>

    <section class="student-panel student-panel--toolbar">
      <div class="student-toolbar">
        <el-form :inline="true" :model="params" class="student-toolbar__form">
          <el-form-item label="课程名称">
            <el-select v-model="params.courseId" clearable filterable placeholder="请选择课程">
              <el-option
                v-for="item in course"
                :key="item.courseId"
                :label="item.course.name + ' === ' + item.course.number"
                :value="item.courseId"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="考试类型">
            <el-input v-model="params.type" clearable placeholder="请输入考试类型"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="findBySearch">查询</el-button>
          </el-form-item>
        </el-form>
      </div>
    </section>

    <section class="student-panel student-panel--padded">
      <div class="student-panel__head">
        <div>
          <h3 class="student-panel__title">考试资源</h3>
          <p class="student-panel__desc">保持原有操作顺序，优化卡片层级、留白和动作区统一性。</p>
        </div>
      </div>

      <div v-if="visit !== -1 && visit !== 0" class="student-resource-grid">
        <article v-for="(item, index) in tableData" :key="index" class="student-resource-card">
          <div class="student-resource-card__meta">
            <div>
              <h4 class="student-resource-card__title">{{ item.course.name }}</h4>
              <p class="student-resource-card__subtitle">课程编码：{{ item.course.number }}</p>
            </div>
            <el-tag class="student-tag" effect="plain">{{ item.examManage.type }}</el-tag>
          </div>

          <div class="student-resource-card__list">
            <div class="student-resource-card__item">
              <span>考试开始</span>
              <span>{{ item.examManage.examDate }}</span>
            </div>
            <div class="student-resource-card__item">
              <span>考试时长</span>
              <span>{{ item.examManage.totalTime }} 分钟</span>
            </div>
            <div class="student-resource-card__item">
              <span>考试总分</span>
              <span>{{ item.examManage.totalScore }} 分</span>
            </div>
            <div class="student-resource-card__item">
              <span>出题教师</span>
              <span>{{ item.teacher.name }}</span>
            </div>
          </div>

          <div class="student-resource-card__actions">
            <el-button type="primary" plain @click="showPublicKeys(item)">查看环公钥</el-button>
            <el-button type="primary" plain @click="showManager(item)">查看详情</el-button>
            <el-button type="primary" @click="beginExam(item)">开始考试</el-button>
            <el-button type="success" plain @click="beginShowExam(item)">查看答案</el-button>
            <el-button type="warning" plain @click="showScore(item)">查看榜单</el-button>
          </div>
        </article>
      </div>

      <div v-else-if="visit === -1" class="student-empty">
        请先选择课程进行考试查询。
      </div>

      <div v-else class="student-empty">
        该课程暂未发布试卷。
      </div>

      <div class="student-pagination">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[5, 10, 15, 20]"
          :page-size="pageSize"
          layout="total, prev, pager, next, jumper"
          :total="total"
        />
      </div>
    </section>
  </div>
</template>

<script>
import request from "@/utils/request";

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
      params: {
        pageNum: 1,
        pageSize: 6,
      },
      visit: -1,
    };
  },
  created() {
    this.initPage();
  },
  methods: {
    initPage() {
      this.params = {
        pageNum: this.pageNum,
        pageSize: this.pageSize,
        studentId: this.user.id,
        type: this.params.type || ""
      };
      this.findCourse().then(() => {
        if (!this.params.courseId && this.course.length) {
          this.params.courseId = this.course[0].courseId;
        }
        if (this.params.courseId) {
          this.load();
        } else {
          this.total = 0;
          this.tableData = [];
          this.visit = 0;
        }
      });
    },
    handleSizeChange(pageSize) {
      this.params.pageSize = pageSize;
      this.findBySearch();
    },
    handleCurrentChange(pageNum) {
      this.params.pageNum = pageNum;
      this.findBySearch();
    },
    load() {
      this.params.studentId = this.user.id;
      request.get("/exam/examStudent/selectPage", {
        params: this.params
      }).then((res) => {
        if (res.code === "200") {
          this.total = res.data.total;
          this.tableData = res.data.list;
          this.visit = this.tableData.length;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findCourse() {
      const params = {
        studentId: this.user.id,
        isAdd: "同意"
      };
      return request.get("/exam/courseStudent/selectAll", {
        params
      }).then((res) => {
        if (res.code === "200") {
          this.course = res.data;
        } else {
          this.$message.error(res.msg);
        }
        return this.course;
      });
    },
    findBySearch() {
      this.params.studentId = this.user.id;
      request.get("/exam/examStudent/selectPage", {
        params: this.params
      }).then((res) => {
        if (res.code === "200") {
          this.total = res.data.total;
          this.tableData = res.data.list;
          this.visit = this.tableData.length;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    showManager(row) {
      this.$router.push({
        name: "studentShowExam",
        params: {
          id: row.examManage.id
        }
      });
    },
    showPublicKeys(row) {
      this.$router.push({
        name: "studentShowPublicKeys",
        params: {
          id: row.examManage.id
        }
      });
    },
    beginExam(row) {
      this.$router.push({
        name: "studentTips",
        params: {
          id: row.examManage.id,
          examStudent: row.id
        }
      });
    },
    beginShowExam(row) {
      this.$router.push({
        name: "studentShowTips",
        params: {
          id: row.examManage.id
        }
      });
    },
    showScore(row) {
      this.$router.push({
        name: "studentScore",
        params: {
          id: row.examManage.id
        }
      });
    }
  }
};
</script>
