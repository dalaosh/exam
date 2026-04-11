<template>
  <div class="admin-page admin-dashboard">
    <section class="admin-page-head admin-page-head--compact">
      <div>
        <h2 class="admin-page-head__title">系统概览</h2>
        <p class="admin-page-head__desc">查看平台用户规模、登录情况和近期内容更新。</p>
      </div>
      <div class="admin-page-head__meta admin-page-head__meta--plain">
        <span class="admin-page-head__meta-text">平台统计</span>
      </div>
    </section>

    <section class="admin-stat-grid">
      <article class="admin-stat-card">
        <div class="admin-stat-card__label">CA 机构数量</div>
        <div class="admin-stat-card__value">{{ dataCount.caCount || 0 }}</div>
        <div class="admin-stat-card__hint">平台信任中心规模</div>
      </article>
      <article class="admin-stat-card">
        <div class="admin-stat-card__label">管理员数量</div>
        <div class="admin-stat-card__value">{{ dataCount.adminCount || 0 }}</div>
        <div class="admin-stat-card__hint">系统维护账户总量</div>
      </article>
      <article class="admin-stat-card">
        <div class="admin-stat-card__label">教师数量</div>
        <div class="admin-stat-card__value">{{ dataCount.teacherCount || 0 }}</div>
        <div class="admin-stat-card__hint">教学端活跃用户</div>
      </article>
      <article class="admin-stat-card">
        <div class="admin-stat-card__label">学生数量</div>
        <div class="admin-stat-card__value">{{ dataCount.studentCount || 0 }}</div>
        <div class="admin-stat-card__hint">学习端用户基础</div>
      </article>
    </section>

    <section class="admin-panel">
      <div class="admin-panel__head">
        <div>
          <h3 class="admin-panel__title">登录趋势</h3>
          <p class="admin-panel__desc">按角色查看近期系统登录情况。</p>
        </div>
      </div>
      <div ref="chart" class="admin-chart"></div>
    </section>

    <section class="admin-announcement-grid">
      <article class="admin-panel">
        <div class="admin-panel__head">
          <div>
            <h3 class="admin-panel__title">公告动态</h3>
            <p class="admin-panel__desc">查看最新公告内容和发布时间。</p>
          </div>
        </div>
        <div class="admin-feed">
          <el-collapse accordion>
            <el-collapse-item v-for="item in notice" :key="item.id" :name="item.id">
              <template #title>
                <div class="admin-feed__title">
                  <span class="admin-feed__title-text">{{ item.name }}</span>
                  <span class="admin-feed__time">{{ item.time }}</span>
                </div>
              </template>
              <div class="admin-feed__body">
                <div class="admin-feed__author">
                  <el-avatar :size="40" :src="item.admin.photo" />
                  <div>
                    <div class="admin-feed__author-name">{{ item.admin.name }}</div>
                    <div class="admin-feed__author-time">{{ item.time }}</div>
                  </div>
                </div>
                <div class="admin-rich-content" v-html="item.content"></div>
              </div>
            </el-collapse-item>
          </el-collapse>
        </div>
      </article>

      <article class="admin-panel">
        <div class="admin-panel__head">
          <div>
            <h3 class="admin-panel__title">新闻动态</h3>
            <p class="admin-panel__desc">查看系统新闻和内容更新。</p>
          </div>
        </div>
        <div class="admin-feed">
          <el-collapse accordion>
            <el-collapse-item v-for="item in news" :key="item.id" :name="item.id">
              <template #title>
                <div class="admin-feed__title">
                  <span class="admin-feed__title-text">{{ item.name }}</span>
                  <span class="admin-feed__time">{{ item.time }}</span>
                </div>
              </template>
              <div class="admin-feed__body">
                <div class="admin-feed__author">
                  <el-avatar :size="40" :src="item.admin.photo" />
                  <div>
                    <div class="admin-feed__author-name">{{ item.admin.name }}</div>
                    <div class="admin-feed__author-time">{{ item.time }}</div>
                  </div>
                </div>
                <div class="admin-rich-content" v-html="item.content"></div>
              </div>
            </el-collapse-item>
          </el-collapse>
        </div>
      </article>
    </section>
  </div>
</template>

<script>
import request from "@/utils/request";
import * as echarts from "echarts";

export default {
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      dataCount: {},
      data: {},
      notice: [],
      news: [],
      chart: null,
    };
  },
  created() {
    this.load1();
    this.load2();
    this.findNews();
    this.findNotice();
  },
  mounted() {
    window.addEventListener("resize", this.handleResize);
  },
  beforeDestroy() {
    window.removeEventListener("resize", this.handleResize);
    if (this.chart) {
      this.chart.dispose();
    }
  },
  methods: {
    handleResize() {
      if (this.chart) {
        this.chart.resize();
      }
    },
    load1() {
      request.get("statistic/selectUserCount/selectUserCount").then((res) => {
        if (res.code === "200") {
          this.dataCount = res.data;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    load2() {
      request.get("statistic/selectUserCount/selectUserLogin").then((res) => {
        if (res.code === "200") {
          this.data = res.data;
          this.$nextTick(() => {
            this.initChart();
          });
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findNews() {
      this.params = { pageNum: 1, pageSize: 6, userId: this.user.id };
      request.get("/info/news/selectPage", { params: this.params }).then((res) => {
        if (res.code === "200") {
          this.news = res.data.list;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findNotice() {
      this.params = { pageNum: 1, pageSize: 6, userId: this.user.id };
      request.get("/info/notice/selectPage", { params: this.params }).then((res) => {
        if (res.code === "200") {
          this.notice = res.data.list;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    initChart() {
      const dates = new Set();
      Object.values(this.data).forEach((role) => {
        Object.keys(role).forEach((date) => dates.add(date));
      });
      const sortedDates = Array.from(dates).sort();
      const roleMap = { teacher: "教师", caInstitution: "机构", student: "学生", admin: "管理员" };
      const series = Object.keys(this.data).map((role) => {
        const roleData = this.data[role];
        return {
          name: roleMap[role],
          type: "line",
          smooth: true,
          symbol: "circle",
          symbolSize: 7,
          data: sortedDates.map((date) => roleData[date] || 0)
        };
      });
      const option = {
        color: ["#2563eb", "#14b8a6", "#f59e0b", "#8b5cf6"],
        title: {
          text: "用户登录趋势",
          left: "center",
          top: 12,
          textStyle: { fontSize: 16, fontWeight: 700, color: "#0f172a" }
        },
        tooltip: { trigger: "axis", axisPointer: { type: "cross" } },
        legend: {
          data: Object.keys(this.data).map((role) => roleMap[role]),
          top: 44,
          textStyle: { color: "#64748b" }
        },
        grid: { left: "4%", right: "4%", bottom: "10%", top: "24%", containLabel: true },
        xAxis: {
          type: "category",
          boundaryGap: false,
          data: sortedDates,
          axisLabel: { color: "#64748b", formatter: (value) => echarts.format.formatTime("MM-dd", value) },
          axisLine: { lineStyle: { color: "#cbd5e1" } }
        },
        yAxis: {
          type: "value",
          minInterval: 1,
          axisLabel: { color: "#64748b" },
          splitLine: { lineStyle: { color: "#e2e8f0" } }
        },
        series
      };
      if (!this.chart) {
        this.chart = echarts.init(this.$refs.chart);
      }
      this.chart.setOption(option);
    }
  }
};
</script>

<style scoped>
.admin-chart {
  width: 100%;
}
</style>
