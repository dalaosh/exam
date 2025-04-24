<template>
  <div>
    <div class="chart-container">
      <div ref="barLineChart" style="width: 100%; height: 26vh"></div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
import * as echarts from 'echarts';

export default {
  props: {
    chartData: {
      type: Object,
      default: () => ({})
    }
  },
  watch: {
    chartData: {
      handler(newVal) {
        // 深度合并数据（防止丢失嵌套结构）
        this.data = {
          ...this.data,
          ...newVal,
        };
      },
      deep: true,   // 深度监听对象变化
      immediate: true // 立即触发一次确保初始数据合并
    }
  },
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      questionType: {},
      examAnswerLevel: {},
      basicNum: {},
      barLineChart: null
    };
  },
  created() {
    this.loadData();
  },
  mounted() {
    window.addEventListener('resize', this.handleResize);
  },
  beforeDestroy() {
    window.removeEventListener('resize', this.handleResize);
    if (this.barLineChart) this.barLineChart.dispose();
  },
  methods: {
    async loadData() {
      try {
        await Promise.all([this.loadExamAnswerLevel()]);
        this.$nextTick(() => {
          this.initBarLineChart();
        });
      } catch (error) {
        this.$message.error('数据加载失败');
      }
    },
    loadExamAnswerLevel() {
       this.examAnswerLevel = this.chartData;
    },

    initBarLineChart() {
      this.barLineChart = echarts.init(this.$refs.barLineChart);
      const categories = ['多选题', '简答题', '填空题', '编程题', '判断题'];
      const difficultyData = this.processDifficultyData();

      const option = {
        backgroundColor: 'transparent', // 透明背景
        title: {
          text: '题型难度分布分析',
          left: '1%', // 标题左对齐
          textStyle: {
            color: '#f5f1f1',
            fontSize: 16 // 缩小标题字号
          }
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        legend: {
          data: ['难度1', '难度2', '难度3', '难度4', '难度5'],
          right: '1%', // 图例右对齐
          top: '10%',   // 图例顶部定位
          textStyle: {
            color: '#f5f1f1',
            fontSize: 12 // 缩小图例字号
          },
          itemGap: 8 // 缩小图例间距
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '2%',
          containLabel: true
        },
        xAxis: {
          type: 'category',
          data: categories,
          axisLabel: {
            fontSize: 12, // 缩小X轴标签字号
            color: '#f6f2f2'
          },
          axisLine: {
            lineStyle: {
              color: '#ccc'
            }
          }
        },
        yAxis: {
          type: 'value',
          name: '题目数量',
          axisLabel: {
            fontSize: 12 // 缩小Y轴标签字号
          },
          axisLine: {
            lineStyle: {
              color: '#ccc'
            }
          },
          splitLine: {
            lineStyle: {
              color: '#f0f0f0'
            }
          }
        },
        series: [
          ...['One', 'Two', 'Three', 'Four', 'Five'].map((level, index) => ({
            name: `难度${index + 1}`,
            type: 'bar',
            stack: 'difficulty',
            barWidth: '60%',
            itemStyle: {
              color: this.getDifficultyColor(index + 1)
            },
            data: difficultyData[level]
          }))
        ]
      };

      this.barLineChart.setOption(option);
    },

    processDifficultyData() {
      const typeMap = [
        {key: 'questionMultiType', prefix: 'questionMulti'},
        {key: 'questionShortAnsType', prefix: 'questionShortAns'},
        {key: 'questionFillType', prefix: 'questionFill'},
        {key: 'questionCodeType', prefix: 'questionCode'},
        {key: 'questionJudgeType', prefix: 'questionJudge'}
      ];

      return typeMap.reduce((result, type) => {
        const typeData = this.examAnswerLevel[type.key] || {};
        ['One', 'Two', 'Three', 'Four', 'Five'].forEach(level => {
          const key = level;
          result[key] = result[key] || [];
          const value = typeData[`${type.prefix}${level}`] || 0;
          result[key].push(value);
        });
        return result;
      }, {});
    },

    getDifficultyColor(level) {
      const colors = ['#5470c6', '#91cc75', '#fac858', '#ee6666', '#73c0de'];
      return colors[level - 1] || '#999';
    },

    handleResize() {
      if (this.barLineChart) this.barLineChart.resize();
    }
  }
};
</script>

<style scoped>
.chart-container {
  background: transparent; /* 容器背景透明 */
}
</style>
