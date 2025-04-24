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
          <div style="margin-top: 10px; font-size: 20px">{{ basicNum.selectAnswerNum }}</div>
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
          <div ref="radarChart" style="width: 100%; height: 60vh"></div>
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
        .catch(error => {
          console.error("数据加载失败:", error);
        });
  },
  mounted() {
    window.addEventListener('resize', this.handleResize);
  },
  beforeDestroy() {
    window.removeEventListener('resize', this.handleResize);
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

      // 指标定义（使用questionTypeAll作为最大值）
      const indicators = [
        { name: '多选题', max: this.studentStrong.questionTypeAll.questionMultiAll || 1 },
        { name: '填空题', max: this.studentStrong.questionTypeAll.questionFillAll || 1 },
        { name: '判断题', max: this.studentStrong.questionTypeAll.questionJudgeAll || 1 },
        { name: '简答题', max: this.studentStrong.questionTypeAll.questionShortAnsAll || 1 },
        { name: '编程题', max: this.studentStrong.questionTypeAll.questionCodeAll || 1 }
      ];

      // 三个数据集
      const totalData = [  // 总题数（questionTypeAll）
        this.studentStrong.questionTypeAll.questionMultiAll || 0,
        this.studentStrong.questionTypeAll.questionFillAll || 0,
        this.studentStrong.questionTypeAll.questionJudgeAll || 0,
        this.studentStrong.questionTypeAll.questionShortAnsAll || 0,
        this.studentStrong.questionTypeAll.questionCodeAll || 0
      ];

      const answerData = [  // 答题数（questionType）
        this.studentStrong.questionType.questionMulti || 0,
        this.studentStrong.questionType.questionFill || 0,
        this.studentStrong.questionType.questionJudge || 0,
        this.studentStrong.questionType.questionShortAns || 0,
        this.studentStrong.questionType.questionCode || 0
      ];

      const correctData = [  // 正确数（questionTypeRight）
        this.studentStrong.questionTypeRight.questionMultiRight || 0,
        this.studentStrong.questionTypeRight.questionFillRight || 0,
        this.studentStrong.questionTypeRight.questionJudgeRight || 0,
        this.studentStrong.questionTypeRight.questionShortAnsRight || 0,
        this.studentStrong.questionTypeRight.questionCodeRight || 0
      ];

      const option = {
        title: {
          text: '题目分析雷达图',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          data: ['题库总量', '已答数量', '正确数量'],
          top: 30
        },
        radar: {
          indicator: indicators,
          shape: 'polygon',
          center: ['50%', '55%'],
          radius: '70%',
          axisName: {
            color: '#666',
            position: 'end',
            offset: [0, 15]
          },
          splitArea: {
            areaStyle: {
              color: ['rgba(250,250,250,0.8)', 'rgba(200,200,200,0.8)']
            }
          }
        },
        series: [{
          type: 'radar',
          data: [
            {
              value: totalData,
              name: '题库总量',
              symbol: 'rect',
              lineStyle: { type: 'dashed', color: '#999' },
              areaStyle: { color: 'rgba(200, 200, 200, 0.1)' }
            },
            {
              value: answerData,
              name: '已答数量',
              symbol: 'circle',
              lineStyle: { color: '#5470C6', width: 2 },
              areaStyle: { color: 'rgba(84, 112, 198, 0.3)' }
            },
            {
              value: correctData,
              name: '正确数量',
              symbol: 'triangle',
              lineStyle: { color: '#EE6666', width: 2 },
              areaStyle: { color: 'rgba(238, 102, 102, 0.3)' }
            }
          ]
        }]
      };
      this.radarChart.setOption(option);
    },
    initBarLineChart() {
      const chartDom = this.$refs.barLineChart;
      this.barLineChart = echarts.init(chartDom);

      const categories = ['多选题', '填空题', '判断题', '简答题', '编程题'];
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

      const option = {
        title: {
          text: '题型分析',
          left: 'center'
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        legend: {
          data: ['总题数', '正确数'],
          top: '7%'
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
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
          name: '题目数量'
        },
        series: [
          {
            name: '总题数',
            type: 'bar',
            data: totalData,
            barWidth: '40%',
            itemStyle: {
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                {offset: 0, color: '#83bff6'},
                {offset: 0.5, color: '#188df0'},
                {offset: 1, color: '#188df0'}
              ])
            }
          },
          {
            name: '正确数',
            type: 'line',
            data: correctData,
            symbol: 'circle',
            symbolSize: 10,
            lineStyle: {
              color: '#FF6B6B',
              width: 3
            },
            itemStyle: {
              color: '#FF6B6B',
              borderWidth: 2
            }
          }
        ]
      };
      this.barLineChart.setOption(option);
    },

    load1() {
      return request.get(`statistic/selectStudentUseCount/selectQuestionType/${this.user.id}`)
          .then(res => {
            if (res.code === '200') this.questionType = res.data;
          });
    },
    load2() {
      return request.get(`statistic/selectStudentUseCount/selectStudentStrong/${this.user.id}`)
          .then(res => {
            if (res.code === '200') this.studentStrong = res.data;
          });
    },
    load3() {
      return request.get(`statistic/selectStudentUseCount/selectBasicNum/${this.user.id}`)
          .then(res => {
            if (res.code === '200') this.basicNum = res.data;
          });
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