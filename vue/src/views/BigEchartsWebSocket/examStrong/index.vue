<template>
  <div style="width: 100%; height: 31vh; position: relative;">
    <div ref="radarChart" style="width: 100%; height: 31vh;"></div>
  </div>
</template>

<script>
import request from "@/utils/request";
import * as echarts from 'echarts';

export default {
  name: "index",
  props: {
    analysisData: {
      type: Object,
      default: () => ({})
    }
  },
  watch: {
    analysisData: {
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
      dataCount: {},
      chartInstance: null
    };
  },
  mounted() {
    this.initChart();
    window.addEventListener('resize', this.handleResize);
  },
  beforeDestroy() {
    window.removeEventListener('resize', this.handleResize);
    if (this.chartInstance) {
      this.chartInstance.dispose();
    }
  },
  methods: {
    load() {
      this.dataCount = this.analysisData;
      this.$nextTick(() => {
        this.updateChart();
      });
    },

    initChart() {
      this.chartInstance = echarts.init(this.$refs.radarChart);
      const option = this.getChartOption();
      this.chartInstance.setOption(option);
    },

    updateChart() {
      if (this.chartInstance) {
        const option = this.getChartOption();
        this.chartInstance.setOption(option);
      }
    },

    getChartOption() {
      return {
        backgroundColor: 'transparent',
        title: {
          text: '成绩分布',
          left: '1%',
          textStyle: {
            color: '#fff',
            fontSize: 16
          }
        },
        radar: {
          indicator: [
            {name: '优秀', max: this.dataCount.总人数 || 1},
            {name: '良好', max: this.dataCount.总人数 || 1},
            {name: '中等', max: this.dataCount.总人数 || 1},
            {name: '及格', max: this.dataCount.总人数 || 1},
            {name: '不及格', max: this.dataCount.总人数 || 1}
          ],
          axisLine: {
            lineStyle: {
              color: 'rgba(255,255,255,0.5)'
            }
          },
          splitLine: {
            lineStyle: {
              color: 'rgba(255,255,255,0.2)'
            }
          },
          splitArea: {
            areaStyle: {
              color: ['rgba(8,238,219,0.1)', 'rgba(8,238,219,0.05)']
            }
          }
        },
        series: [{
          type: 'radar',
          data: [{
            value: [
              this.dataCount.优秀 || 0,
              this.dataCount.良好 || 0,
              this.dataCount.中等 || 0,
              this.dataCount.及格 || 0,
              this.dataCount.不及格 || 0
            ],
            name: '人数分布',
            areaStyle: {
              color: 'rgba(8,238,219,0.4)'
            },
            lineStyle: {
              color: '#08eedb'
            },
            itemStyle: {
              color: '#08eedb'
            }
          }]
        }],
        textStyle: {
          color: '#fff'
        }
      };
    },

    handleResize() {
      if (this.chartInstance) {
        this.chartInstance.resize();
      }
    }
  },
  created() {
    this.load();
  }
}
</script>

<style scoped>
/* 调整雷达图标签颜色 */
:deep(.radar-chart text) {
  fill: #fff !important;
}
</style>
