<template>
  <div>
    <div style="background: transparent">
      <div ref="chart" style="width: 100%;height: 26vh;"></div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
import * as echarts from 'echarts';

export default {
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      data: {}
    };
  },
  props: {
    loginData: {
      type: Object,
      default: () => ({})
    }
  },
  watch: {
    loginData: {
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
  created() {
    setTimeout(() => {
      this.load();
    }, 3);
    // this.load();
  },
  methods: {
    load() {
      this.data= this.loginData
      this.initChart();
    },
    initChart() {
      const roleMap = {
        teacher: '教师',
        caInstitution: '机构',
        student: '学生',
        admin: '管理员'
      };

      // 处理日期数据
      const dates = new Set();
      Object.values(this.data).forEach(role => {
        Object.keys(role).forEach(date => dates.add(date));
      });
      const sortedDates = Array.from(dates).sort();

      // 生成非堆叠系列数据
      const series = Object.keys(this.data).map(role => {
        const roleData = this.data[role];
        return {
          name: roleMap[role],
          type: 'line',
          smooth: true,  // 移除stack配置
          symbol: 'circle',  // 添加数据点标记
          symbolSize: 6,
          itemStyle: {
            color: this.getLineColor(role)  // 自定义颜色
          },
          data: sortedDates.map(date => roleData[date] || 0)
        };
      });

      // 配置选项
      const option = {
        backgroundColor: 'transparent',
        title: {
          text: '用户登录统计',
          left: '1%',
          textStyle: {
            fontSize: 16,  // 缩小标题字号
            color: '#f8f3f3'
          },
        },
        tooltip: {
          trigger: 'axis',

        },
        legend: {
          data: Object.keys(this.data).map(role => roleMap[role]),
          orient: 'horizontal',
          right: '2%',
          top: '10%',
          itemGap: 15,    // 缩小间距
          textStyle: {
            fontSize: 10, // 缩小图例字号
            color: '#f8f3f3'
          },
          selectedMode: true  // 确保可点击
        },
        grid: {
          left: '3%',
          right: '3%',
          bottom: '3%',  // 为x轴标签腾出空间
          containLabel: true
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          axisLine: {
            lineStyle: {
              color: '#f8f3f3'  // x轴颜色
            }
          },
          axisLabel: {
            color: '#f8f3f3',  // 标签颜色
            formatter: value => echarts.format.formatTime('MM/dd', value)
          },
          data: sortedDates
        },
        yAxis: {
          type: 'value',
          minInterval: 1,
          axisLine: {
            lineStyle: {
              color: '#f8f3f3'  // y轴颜色
            }
          },
          axisLabel: {
            color: '#f8f3f3'  // 标签颜色
          },
          splitLine: {
            lineStyle: {
              color: 'rgba(248,243,243,0.99)'  // 网格线颜色
            }
          }
        },
        series: series
      };

      // 初始化图表
      if (!this.chart) {
        this.chart = echarts.init(this.$refs.chart);
        // 添加图例点击事件监听
        this.chart.on('legendselectchanged', (params) => {
          console.log('图例点击变化:', params);
        });
      }
      this.chart.setOption(option);
    },
    // 自定义颜色分配
    getLineColor(role) {
      const colorMap = {
        teacher: '#FFA500',    // 橙色
        student: '#00BFFF',    // 深蓝色
        admin: '#32CD32',      // 绿色
        caInstitution: '#FF6347' // 红色
      };
      return colorMap[role] || '#f3ecec';
    }
  }
};
</script>

<style scoped>
div {
  background-color: transparent !important;
}
</style>
