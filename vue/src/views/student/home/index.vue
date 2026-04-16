<template>
  <div class="student-page student-dashboard">
    <section class="student-page-head student-page-head--compact">
      <div>
        <h2 class="student-page-head__title">学习概览</h2>
      </div>
      <div class="student-page-head__meta student-page-head__meta--plain">
        <span class="student-page-head__meta-text">学习统计</span>
      </div>
    </section>

    <section class="student-stat-grid">
      <article class="student-stat-card">
        <div class="student-stat-card__label">课程数量</div>
        <div class="student-stat-card__value">{{ basicNum.selectCourseNum || 0 }}</div>
        <div class="student-stat-card__hint">当前参与课程</div>
      </article>
      <article class="student-stat-card">
        <div class="student-stat-card__label">考试数量</div>
        <div class="student-stat-card__value">{{ basicNum.selectExamNum || 0 }}</div>
        <div class="student-stat-card__hint">可参加考试</div>
      </article>
      <article class="student-stat-card">
        <div class="student-stat-card__label">答题数量</div>
        <div class="student-stat-card__value">{{ basicNum.selectAnswerNum || 0 }}</div>
        <div class="student-stat-card__hint">累计有效答题记录</div>
      </article>
      <article class="student-stat-card">
        <div class="student-stat-card__label">及格情况</div>
        <div class="student-stat-card__value">{{ basicNum.selectS || 0 }}/{{ basicNum.selectSS || 0 }}</div>
        <div class="student-stat-card__hint">及格人数 / 总人数</div>
      </article>
    </section>

    <section class="student-dashboard-grid">
      <article class="student-panel student-panel--padded student-panel--radar">
        <div class="student-panel__head">
          <div>
            <h3 class="student-panel__title">题目分析雷达图</h3>
          </div>
        </div>
        <div ref="radarChart" class="student-chart student-chart--radar"></div>
      </article>

      <article class="student-panel student-panel--padded">
        <div class="student-panel__head">
          <div>
            <h3 class="student-panel__title">题型分析</h3>
          </div>
        </div>
        <div ref="barLineChart" class="student-chart"></div>
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
      questionType: {
        questionTypeRight: {},
        questionType: {}
      },
      studentStrong: {
        questionTypeAll: {},
        questionTypeRight: {},
        questionType: {}
      },
      basicNum: {},
      radarChart: null,
      barLineChart: null
    };
  },
  created() {
    Promise.all([this.load1(), this.load2(), this.load3()])
      .then(() => {
        this.$nextTick(() => {
          this.initRadarChart();
          this.initBarLineChart();
        });
      })
      .catch((error) => {
        console.error("数据加载失败:", error);
      });
  },
  mounted() {
    window.addEventListener("resize", this.handleResize);
  },
  beforeDestroy() {
    window.removeEventListener("resize", this.handleResize);
    if (this.radarChart) {
      this.radarChart.dispose();
    }
    if (this.barLineChart) {
      this.barLineChart.dispose();
    }
  },
  methods: {
    handleResize() {
      if (this.radarChart) {
        this.radarChart.resize();
      }
      if (this.barLineChart) {
        this.barLineChart.resize();
      }
    },
    initRadarChart() {
      const chartDom = this.$refs.radarChart;
      this.radarChart = echarts.init(chartDom);

      const indicators = [
        { name: "多选题", max: this.studentStrong.questionTypeAll.questionMultiAll || 1 },
        { name: "填空题", max: this.studentStrong.questionTypeAll.questionFillAll || 1 },
        { name: "判断题", max: this.studentStrong.questionTypeAll.questionJudgeAll || 1 },
        { name: "简答题", max: this.studentStrong.questionTypeAll.questionShortAnsAll || 1 },
        { name: "编程题", max: this.studentStrong.questionTypeAll.questionCodeAll || 1 }
      ];

      const totalData = [
        this.studentStrong.questionTypeAll.questionMultiAll || 0,
        this.studentStrong.questionTypeAll.questionFillAll || 0,
        this.studentStrong.questionTypeAll.questionJudgeAll || 0,
        this.studentStrong.questionTypeAll.questionShortAnsAll || 0,
        this.studentStrong.questionTypeAll.questionCodeAll || 0
      ];

      const answerData = [
        this.studentStrong.questionType.questionMulti || 0,
        this.studentStrong.questionType.questionFill || 0,
        this.studentStrong.questionType.questionJudge || 0,
        this.studentStrong.questionType.questionShortAns || 0,
        this.studentStrong.questionType.questionCode || 0
      ];

      const correctData = [
        this.studentStrong.questionTypeRight.questionMultiRight || 0,
        this.studentStrong.questionTypeRight.questionFillRight || 0,
        this.studentStrong.questionTypeRight.questionJudgeRight || 0,
        this.studentStrong.questionTypeRight.questionShortAnsRight || 0,
        this.studentStrong.questionTypeRight.questionCodeRight || 0
      ];

      const radarIndicators = indicators.map((item, index) => {
        const currentMax = Math.max(totalData[index], answerData[index], correctData[index], 1);
        return {
          ...item,
          max: Math.ceil(currentMax * 1.15)
        };
      });

      this.radarChart.setOption({
        color: ["#94a3b8", "#0ea5e9", "#f97316"],
        tooltip: { trigger: "item" },
        legend: {
          data: ["题库总量", "已答数量", "正确数量"],
          top: 10,
          left: "center",
          itemWidth: 18,
          itemHeight: 10,
          itemGap: 18,
          textStyle: {
            color: "#64748b",
            fontSize: 13,
            padding: [0, 0, 0, 4]
          }
        },
        radar: {
          indicator: radarIndicators,
          shape: "polygon",
          center: ["50%", "60%"],
          radius: "58%",
          axisName: {
            color: "#475569",
            fontSize: 14,
            padding: [2, 8]
          },
          splitNumber: 5,
          splitArea: {
            areaStyle: {
              color: ["rgba(248, 250, 252, 0.9)", "rgba(226, 232, 240, 0.7)"]
            }
          },
          splitLine: {
            lineStyle: {
              color: "#dbe4ee"
            }
          },
          axisLine: {
            lineStyle: {
              color: "rgba(148, 163, 184, 0.45)"
            }
          }
        },
        series: [{
          type: "radar",
          symbol: "circle",
          symbolSize: 7,
          lineStyle: { width: 2.5 },
          data: [
            {
              value: totalData,
              name: "题库总量",
              areaStyle: { color: "rgba(148, 163, 184, 0.12)" },
              lineStyle: { type: "dashed", width: 2.5 },
              itemStyle: { color: "#94a3b8" }
            },
            {
              value: answerData,
              name: "已答数量",
              areaStyle: { color: "rgba(14, 165, 233, 0.18)" },
              itemStyle: { color: "#0ea5e9" }
            },
            {
              value: correctData,
              name: "正确数量",
              areaStyle: { color: "rgba(249, 115, 22, 0.14)" },
              itemStyle: { color: "#f97316" }
            }
          ]
        }]
      });
    },
    initBarLineChart() {
      const chartDom = this.$refs.barLineChart;
      this.barLineChart = echarts.init(chartDom);
      const categories = ["多选题", "填空题", "判断题", "简答题", "编程题"];
      const totalData = [
        this.questionType.questionType.questionMulti || 0,
        this.questionType.questionType.questionFill || 0,
        this.questionType.questionType.questionJudge || 0,
        this.questionType.questionType.questionShortAns || 0,
        this.questionType.questionType.questionCode || 0
      ];
      const correctData = [
        this.questionType.questionTypeRight.questionMultiRight || 0,
        this.questionType.questionTypeRight.questionFillRight || 0,
        this.questionType.questionTypeRight.questionJudgeRight || 0,
        this.questionType.questionTypeRight.questionShortAnsRight || 0,
        this.questionType.questionTypeRight.questionCodeRight || 0
      ];

      this.barLineChart.setOption({
        color: ["#38bdf8", "#fb7185"],
        tooltip: { trigger: "axis", axisPointer: { type: "shadow" } },
        legend: {
          data: ["总题数", "正确数"],
          top: 8,
          textStyle: { color: "#64748b" }
        },
        grid: {
          left: "4%",
          right: "4%",
          bottom: "6%",
          top: 56,
          containLabel: true
        },
        xAxis: {
          type: "category",
          data: categories,
          axisLine: { lineStyle: { color: "#cbd5e1" } },
          axisLabel: { color: "#475569" }
        },
        yAxis: {
          type: "value",
          name: "题目数量",
          nameTextStyle: { color: "#64748b" },
          axisLine: { show: false },
          splitLine: { lineStyle: { color: "#e2e8f0" } },
          axisLabel: { color: "#64748b" }
        },
        series: [
          {
            name: "总题数",
            type: "bar",
            data: totalData,
            barWidth: "40%",
            itemStyle: {
              borderRadius: [10, 10, 0, 0],
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                { offset: 0, color: "#67e8f9" },
                { offset: 1, color: "#0ea5e9" }
              ])
            }
          },
          {
            name: "正确数",
            type: "line",
            data: correctData,
            symbol: "circle",
            symbolSize: 8,
            smooth: true,
            lineStyle: { width: 3 }
          }
        ]
      });
    },
    load1() {
      return request.get(`statistic/selectStudentUseCount/selectQuestionType/${this.user.id}`).then((res) => {
        if (res.code === "200") {
          this.questionType = res.data;
        }
      });
    },
    load2() {
      return request.get(`statistic/selectStudentUseCount/selectStudentStrong/${this.user.id}`).then((res) => {
        if (res.code === "200") {
          this.studentStrong = res.data;
        }
      });
    },
    load3() {
      return request.get(`statistic/selectStudentUseCount/selectBasicNum/${this.user.id}`).then((res) => {
        if (res.code === "200") {
          this.basicNum = res.data;
        }
      });
    }
  }
};
</script>

<style scoped lang="scss">
.student-panel--radar {
  overflow: hidden;
}

.student-chart--radar {
  height: 470px;
  margin-top: 4px;
}

@media (max-width: 1200px) {
  .student-chart--radar {
    height: 430px;
  }
}
</style>
