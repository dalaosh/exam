<template>
  <div style="background: #fdf9f9; padding: 20px">
    <!-- 统计看板模块 -->
    <el-row>
      <el-col :span="6">
        <div class="box">
          <div style="color: #666">课程</div>
          <div style="margin-top: 10px; font-size: 20px">{{ basicNum.selectCourseNum }}</div>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="box">
          <div style="color: #666">考试数量</div>
          <div style="margin-top: 10px; font-size: 20px">{{ basicNum.selectExamNum }}</div>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="box">
          <div style="color: #666">学生数量</div>
          <div style="margin-top: 10px; font-size: 20px">{{ basicNum.selectStudentNum }}</div>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="box">
          <div style="color: #666">及格率(及格人数/总人数)</div>
          <div style="margin-top: 10px; font-size: 20px">{{ basicNum.selectS }}/{{ basicNum.selectSS }}</div>
        </div>
      </el-col>
    </el-row>

    <!-- 图表展示模块 -->
    <el-row :gutter="20" style="margin-top: 2vh">
      <el-col :span="12">
        <div class="chart-container">
          <div ref="pieChart" style="width: 100%; height: 60vh"></div>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="chart-container">
          <div ref="barLineChart" style="width: 100%; height: 60vh"></div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import request from "@/utils/request";
import * as echarts from 'echarts';

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
    window.addEventListener('resize', this.handleResize);
  },
  beforeDestroy() {
    window.removeEventListener('resize', this.handleResize);
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
        this.$message.error('数据加载失败');
      }
    },

    // 加载基础数据
    loadBasicNum() {
      return request.get(`statistic/selectTeacherUseCount/selectBasicNum/${this.user.id}`).then(res => {
        if (res.code === '200') this.basicNum = res.data;
      });
    },

    // 加载题型数据
    loadQuestionType() {
      return request.get(`statistic/selectTeacherUseCount/selectQuestionType/${this.user.id}`).then(res => {
        if (res.code === '200') this.questionType = res.data;
      });
    },

    // 加载难度数据
    loadExamAnswerLevel() {
      return request.get(`statistic/selectTeacherUseCount/selectExamAnswerLevel/${this.user.id}`).then(res => {
        if (res.code === '200') this.examAnswerLevel = res.data;
      });
    },

    // 初始化饼图
    initPieChart() {
      this.pieChart = echarts.init(this.$refs.pieChart);
      const data = Object.entries(this.questionType).map(([key, value]) => ({
        name: this.formatTypeName(key),
        value
      }));

      const option = {
        title: {
          text: '题型分布统计',
          left: 'center',
          textStyle: {
            color: '#666',
            fontSize: 16
          }
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b}: {c} ({d}%)'
        },
        legend: {
          orient: 'vertical',
          left: 'left',
          top: 'bottom'
        },
        series: [
          {
            name: '题型分布',
            type: 'pie',
            radius: ['40%', '60%'],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 10,
              borderColor: '#fff',
              borderWidth: 2
            },
            label: {
              show: true,
              formatter: '{b}\n{d}%',
              lineHeight: 20
            },
            emphasis: {
              label: {
                show: true,
                fontSize: 16,
                fontWeight: 'bold'
              }
            },
            data
          }
        ]
      };

      this.pieChart.setOption(option);
    },

    // 初始化组合图表
    initBarLineChart() {
      this.barLineChart = echarts.init(this.$refs.barLineChart);
      const categories = ['多选题', '简答题', '填空题', '编程题', '判断题'];
      const difficultyData = this.processDifficultyData();
      const totalData = categories.map(name =>
          this.questionType[this.getTypeKey(name)] || 0
      );

      const option = {
        title: {
          text: '题型难度分布分析',
          left: 'center',
          textStyle: {
            color: '#666',
            fontSize: 16
          }
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        legend: {
          data: ['难度1', '难度2', '难度3', '难度4', '难度5', '总题数'],
          bottom: 20
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '15%',
          containLabel: true
        },
        xAxis: {
          type: 'category',
          data: categories,
          axisLabel: {
            rotate: 45
          }
        },
        yAxis: {
          type: 'value',
          name: '题目数量',
          axisLine: {
            show: true
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
          })),
          {
            name: '总题数',
            type: 'line',
            symbolSize: 8,
            itemStyle: {
              color: '#ff6b6b'
            },
            lineStyle: {
              width: 3,
              type: 'dashed'
            },
            data: totalData
          }
        ]
      };

      this.barLineChart.setOption(option);
    },

    // 处理难度数据
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

    // 辅助方法
    formatTypeName(key) {
      const typeMap = {
        questionShortAns: '简答题',
        questionCode: '编程题',
        questionMulti: '多选题',
        questionFill: '填空题',
        questionJudge: '判断题'
      };
      return typeMap[key] || key;
    },

    getTypeKey(name) {
      const reverseMap = {
        '多选题': 'questionMulti',
        '简答题': 'questionShortAns',
        '填空题': 'questionFill',
        '编程题': 'questionCode',
        '判断题': 'questionJudge'
      };
      return reverseMap[name];
    },

    getDifficultyColor(level) {
      const colors = ['#5470c6', '#91cc75', '#fac858', '#ee6666', '#73c0de'];
      return colors[level - 1] || '#999';
    },

    handleResize() {
      if (this.pieChart) this.pieChart.resize();
      if (this.barLineChart) this.barLineChart.resize();
    }
  }
};
</script>

<style scoped>
.box {
  background: white;
  border-radius: 8px;
  padding: 20px;
  margin: 10px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  transition: transform 0.3s;
  text-align: center;
}

.box:hover {
  transform: translateY(-5px);
}

.chart-container {
  background: white;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
</style>