<template>
  <div class="teacher-page teacher-home-page">
    <section class="teacher-page-head teacher-page-head--compact">
      <div>
        <h2 class="teacher-page-head__title">教学概览</h2>
        <p class="teacher-page-head__desc">汇总课程、考试、学生和题库数据，便于快速查看当前教学情况。</p>
      </div>
      <div class="teacher-page-head__meta teacher-page-head__meta--plain">
        <span class="teacher-page-head__meta-text">数据统计</span>
      </div>
    </section>

    <section class="teacher-stat-grid">
      <article class="teacher-stat-card">
        <div class="teacher-stat-card__label">课程数量</div>
        <div class="teacher-stat-card__value">{{ basicNum.selectCourseNum || 0 }}</div>
        <div class="teacher-stat-card__hint">当前创建与管理课程</div>
      </article>
      <article class="teacher-stat-card">
        <div class="teacher-stat-card__label">考试数量</div>
        <div class="teacher-stat-card__value">{{ basicNum.selectExamNum || 0 }}</div>
        <div class="teacher-stat-card__hint">已创建考试总数</div>
      </article>
      <article class="teacher-stat-card">
        <div class="teacher-stat-card__label">学生数量</div>
        <div class="teacher-stat-card__value">{{ basicNum.selectStudentNum || 0 }}</div>
        <div class="teacher-stat-card__hint">课程关联学生规模</div>
      </article>
      <article class="teacher-stat-card">
        <div class="teacher-stat-card__label">及格情况</div>
        <div class="teacher-stat-card__value">{{ basicNum.selectS || 0 }}/{{ basicNum.selectSS || 0 }}</div>
        <div class="teacher-stat-card__hint">及格人数 / 总人数</div>
      </article>
    </section>

    <section class="teacher-dashboard-grid">
      <article class="teacher-panel teacher-panel--padded">
        <div class="teacher-panel__head">
          <div>
            <h3 class="teacher-panel__title">题型分布统计</h3>
            <p class="teacher-panel__desc">查看当前题库中各题型占比。</p>
          </div>
        </div>
        <div ref="pieChart" class="teacher-chart"></div>
      </article>

      <article class="teacher-panel teacher-panel--padded">
        <div class="teacher-panel__head">
          <div>
            <h3 class="teacher-panel__title">题型难度分析</h3>
            <p class="teacher-panel__desc">查看不同题型的难度分布与总题量。</p>
          </div>
        </div>
        <div ref="barLineChart" class="teacher-chart"></div>
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
      questionType: {},
      examAnswerLevel: {},
      basicNum: {},
      pieChart: null,
      barLineChart: null
    };
  },
  created() {
    this.loadData();
  },
  mounted() {
    window.addEventListener("resize", this.handleResize);
  },
  beforeDestroy() {
    window.removeEventListener("resize", this.handleResize);
    if (this.pieChart) this.pieChart.dispose();
    if (this.barLineChart) this.barLineChart.dispose();
  },
  methods: {
    async loadData() {
      try {
        await Promise.all([this.loadQuestionType(), this.loadExamAnswerLevel(), this.loadBasicNum()]);
        this.$nextTick(() => {
          this.initPieChart();
          this.initBarLineChart();
        });
      } catch (error) {
        this.$message.error("数据加载失败");
      }
    },
    loadBasicNum() {
      return request.get(`statistic/selectTeacherUseCount/selectBasicNum/${this.user.id}`).then((res) => {
        if (res.code === "200") this.basicNum = res.data;
      });
    },
    loadQuestionType() {
      return request.get(`statistic/selectTeacherUseCount/selectQuestionType/${this.user.id}`).then((res) => {
        if (res.code === "200") this.questionType = res.data;
      });
    },
    loadExamAnswerLevel() {
      return request.get(`statistic/selectTeacherUseCount/selectExamAnswerLevel/${this.user.id}`).then((res) => {
        if (res.code === "200") this.examAnswerLevel = res.data;
      });
    },
    initPieChart() {
      this.pieChart = echarts.init(this.$refs.pieChart);
      const data = Object.entries(this.questionType).map(([key, value]) => ({
        name: this.formatTypeName(key),
        value
      }));

      this.pieChart.setOption({
        color: ["#10b981", "#34d399", "#f59e0b", "#3b82f6", "#8b5cf6"],
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
          orient: "vertical",
          left: "left",
          top: "bottom",
          textStyle: {
            color: "#64748b"
          }
        },
        series: [
          {
            name: "题型分布",
            type: "pie",
            radius: ["40%", "62%"],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 10,
              borderColor: "#fff",
              borderWidth: 2
            },
            label: {
              show: true,
              formatter: "{b}\n{d}%",
              lineHeight: 20
            },
            emphasis: {
              label: {
                show: true,
                fontSize: 16,
                fontWeight: "bold"
              }
            },
            data
          }
        ]
      });
    },
    initBarLineChart() {
      this.barLineChart = echarts.init(this.$refs.barLineChart);
      const categories = ["多选题", "简答题", "填空题", "编程题", "判断题"];
      const difficultyData = this.processDifficultyData();
      const totalData = categories.map((name) =>
        this.questionType[this.getTypeKey(name)] || 0
      );

      this.barLineChart.setOption({
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "shadow"
          }
        },
        legend: {
          data: ["难度1", "难度2", "难度3", "难度4", "难度5", "总题数"],
          bottom: 16,
          textStyle: {
            color: "#64748b"
          }
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "18%",
          top: 24,
          containLabel: true
        },
        xAxis: {
          type: "category",
          data: categories,
          axisLabel: {
            color: "#475569"
          }
        },
        yAxis: {
          type: "value",
          name: "题目数量",
          nameTextStyle: {
            color: "#64748b"
          },
          axisLabel: {
            color: "#64748b"
          },
          splitLine: {
            lineStyle: {
              color: "#e2e8f0"
            }
          }
        },
        series: [
          ...["One", "Two", "Three", "Four", "Five"].map((level, index) => ({
            name: `难度${index + 1}`,
            type: "bar",
            stack: "difficulty",
            barWidth: "60%",
            itemStyle: {
              color: this.getDifficultyColor(index + 1),
              borderRadius: index === 4 ? [6, 6, 0, 0] : 0
            },
            data: difficultyData[level]
          })),
          {
            name: "总题数",
            type: "line",
            symbolSize: 8,
            itemStyle: {
              color: "#ef4444"
            },
            lineStyle: {
              width: 3,
              type: "dashed"
            },
            data: totalData
          }
        ]
      });
    },
    processDifficultyData() {
      const typeMap = [
        { key: "questionMultiType", prefix: "questionMulti" },
        { key: "questionShortAnsType", prefix: "questionShortAns" },
        { key: "questionFillType", prefix: "questionFill" },
        { key: "questionCodeType", prefix: "questionCode" },
        { key: "questionJudgeType", prefix: "questionJudge" }
      ];

      return typeMap.reduce((result, type) => {
        const typeData = this.examAnswerLevel[type.key] || {};
        ["One", "Two", "Three", "Four", "Five"].forEach((level) => {
          const key = level;
          result[key] = result[key] || [];
          const value = typeData[`${type.prefix}${level}`] || 0;
          result[key].push(value);
        });
        return result;
      }, {});
    },
    formatTypeName(key) {
      const typeMap = {
        questionShortAns: "简答题",
        questionCode: "编程题",
        questionMulti: "多选题",
        questionFill: "填空题",
        questionJudge: "判断题"
      };
      return typeMap[key] || key;
    },
    getTypeKey(name) {
      const reverseMap = {
        多选题: "questionMulti",
        简答题: "questionShortAns",
        填空题: "questionFill",
        编程题: "questionCode",
        判断题: "questionJudge"
      };
      return reverseMap[name];
    },
    getDifficultyColor(level) {
      const colors = ["#10b981", "#34d399", "#fbbf24", "#fb7185", "#60a5fa"];
      return colors[level - 1] || "#999";
    },
    handleResize() {
      if (this.pieChart) this.pieChart.resize();
      if (this.barLineChart) this.barLineChart.resize();
    }
  }
};
</script>
