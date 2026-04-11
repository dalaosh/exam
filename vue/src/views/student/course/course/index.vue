<template>
  <div class="student-page">
    <section class="student-page-head">
      <div>
        <h2 class="student-page-head__title">课程信息</h2>
        <p class="student-page-head__desc">通过课程编码快速检索课程，同时统一查看已申请课程的审核状态与详细信息。</p>
      </div>
      <div class="student-page-head__meta">
        <el-tag class="student-tag" type="info">已申请 {{ visit > 0 ? visit : 0 }} 门课程</el-tag>
      </div>
    </section>

    <section class="student-panel student-panel--padded">
      <div class="student-search-hero">
        <div class="student-search-hero__pane">
          <h3 class="student-search-hero__title">课程编码查询</h3>
          <p class="student-search-hero__desc">保持原有查询方式不变，输入课程编码后可查看课程信息并申请加入。</p>
          <div class="student-search-hero__form">
            <el-input
              v-model="number"
              class="student-search-hero__input"
              clearable
              placeholder="请输入课程编号"
            />
            <el-button type="primary" @click="searchNumber">查询课程</el-button>
          </div>
        </div>

        <div class="student-search-hero__preview">
          <template v-if="total === 1">
            <div class="student-resource-card__meta">
              <div>
                <h4 class="student-resource-card__title">{{ table.name }}</h4>
                <p class="student-resource-card__subtitle">课程详情预览</p>
              </div>
              <el-button type="primary" plain @click="visits">查看详情</el-button>
            </div>
            <div class="student-resource-card__list">
              <div class="student-resource-card__item">
                <span>开始时间</span>
                <span>{{ table.beginTime }}</span>
              </div>
              <div class="student-resource-card__item">
                <span>结束时间</span>
                <span>{{ table.endTime }}</span>
              </div>
            </div>
            <div class="student-resource-card__actions">
              <el-button v-if="but === 0" type="primary" @click="insert">申请加入</el-button>
              <el-tag v-else class="student-tag" type="success">已申请或已加入</el-tag>
            </div>
          </template>
          <div v-else-if="total === 0" class="student-search-hero__empty">
            未找到该课程，请检查输入的课程编号。
          </div>
          <div v-else class="student-search-hero__empty">
            请输入课程编码进行查询。
          </div>
        </div>
      </div>
    </section>

    <section class="student-panel student-panel--padded">
      <div class="student-panel__head">
        <div>
          <h3 class="student-panel__title">我的课程</h3>
          <p class="student-panel__desc">课程卡片统一展示教师、审核状态和时间区间，保留原有详情入口。</p>
        </div>
      </div>

      <div v-if="visit !== 0" class="student-resource-grid">
        <article v-for="(item, index) in tableData" :key="index" class="student-resource-card">
          <img :src="item.course.photo" class="student-resource-card__cover" alt="">

          <div class="student-resource-card__meta">
            <div>
              <h4 class="student-resource-card__title">{{ item.course.name }}</h4>
              <p class="student-resource-card__subtitle">任课教师：{{ item.teacher.name }}</p>
            </div>
            <el-tag
              class="student-tag"
              :type="item.isAdd === '同意' ? 'success' : item.isAdd === '拒绝' ? 'danger' : 'warning'"
            >
              {{ item.isAdd }}
            </el-tag>
          </div>

          <div class="student-resource-card__list">
            <div class="student-resource-card__item">
              <span>开始时间</span>
              <span>{{ item.course.beginTime }}</span>
            </div>
            <div class="student-resource-card__item">
              <span>结束时间</span>
              <span>{{ item.course.endTime }}</span>
            </div>
          </div>

          <div class="student-resource-card__actions">
            <el-button type="primary" plain @click="show(item.id)">查看详情</el-button>
          </div>
        </article>
      </div>

      <div v-else class="student-empty">
        你还没有申请课程，请尽快申请。
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
      tableData: [],
      visit: 0,
      total: -1,
      table: {},
      number: "",
      but: 0
    };
  },
  created() {
    this.load();
  },
  methods: {
    load() {
      const params = {
        studentId: this.user.id
      };
      request.get("/exam/courseStudent/selectAll", {
        params
      }).then((res) => {
        if (res.code === "200") {
          this.tableData = res.data;
          this.visit = this.tableData.length;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    show(id) {
      this.$router.push({
        name: "studentCourseStudent",
        params: {
          id
        }
      });
    },
    visits() {
      this.$router.push({
        name: "studentCourseReply",
        params: {
          id: this.table.id
        }
      });
    },
    searchNumber() {
      request.get(`exam/course/selectByNumber/${this.number}`).then((res) => {
        if (res.code === "200") {
          this.total = 0;
          if (Object.keys(res.data).length !== 0) {
            this.table = res.data;
            this.total = 1;
            this.searchS();
          }
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    insert() {
      const params = {
        studentId: this.user.id,
        courseId: this.table.id
      };
      request.post("/exam/courseStudent/add", params).then((res) => {
        if (res.code === "200") {
          this.$message.success("申请成功");
          this.total = -1;
          this.table = {};
          this.load();
          this.number = "";
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    searchS() {
      const params = {
        studentId: this.user.id,
        courseId: this.table.id
      };
      request.get("/exam/courseStudent/selectAll", {
        params
      }).then((res) => {
        if (res.code === "200") {
          this.but = res.data.length;
        } else {
          this.$message.error(res.msg);
        }
      });
    }
  }
};
</script>
