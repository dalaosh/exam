<!--<template>-->
<!--  <div>-->
<!--    <el-row style="margin-top: 2vh;margin-bottom: 2vh;text-align: center">-->
<!--      <el-col :span="6">-->
<!--        考试科目:{{this.form.course.name}}-->
<!--      </el-col>-->
<!--      <el-col :span="10">-->
<!--        考试开始时间:{{this.form.examDate}}-->
<!--      </el-col>-->
<!--      <el-col :span="4">-->
<!--        考试时长:{{this.form.totalTime}}分钟-->
<!--      </el-col>-->
<!--      <el-col :span="4">-->
<!--        总分:{{this.form.totalScore}}分-->
<!--      </el-col>-->
<!--    </el-row>-->
<!--    <el-row style="text-align: center">-->
<!--      <el-col :span="6">-->
<!--        课程编码:{{this.form.course.number}}-->
<!--      </el-col>-->
<!--      <el-col :span="8">-->
<!--        考试类型:{{this.form.type}}-->
<!--      </el-col>-->
<!--      <el-col :span="8">-->
<!--        出题人:{{this.form.teacher.name}}-->
<!--      </el-col>-->
<!--    </el-row>-->
<!--    <el-row class="body">-->
<!--      <div style="margin: 0 30% 0 30%;width: 40%;">-->
<!--        <el-form :model="AParams" status-icon :rules="rules" ref="AParams" label-width="100px">-->
<!--          <el-form-item label="选择题" prop="multi">-->
<!--            <el-input v-model.number="AParams.multi"></el-input>-->
<!--          </el-form-item>-->
<!--          <el-form-item label="判断题" prop="judge">-->
<!--            <el-input v-model.number="AParams.judge"></el-input>-->
<!--          </el-form-item>-->
<!--          <el-form-item label="填空题" prop="fill">-->
<!--            <el-input v-model.number="AParams.fill"></el-input>-->
<!--          </el-form-item>-->
<!--          <el-form-item label="简答题" prop="shortAns">-->
<!--            <el-input v-model.number="AParams.shortAns"></el-input>-->
<!--          </el-form-item>-->
<!--          <el-form-item label="编程题" prop="code">-->
<!--            <el-input v-model.number="AParams.code"></el-input>-->
<!--          </el-form-item>-->
<!--          <el-form-item>-->
<!--            <el-button type="primary" @click="submit()">提交</el-button>-->
<!--            <el-button @click="resetForm()">重置</el-button>-->
<!--          </el-form-item>-->
<!--        </el-form>-->
<!--      </div>-->
<!--    </el-row>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--import request from "@/utils/request";-->
<!--export default {-->
<!--  props: {-->
<!--    // 定义接收的参数，这里以 param 为例-->
<!--    param: {-->
<!--      type: String, // 参数类型，这里是字符串类型-->
<!--      required: false, // 是否为必需参数，这里设为非必需-->
<!--      default: '',// 参数的默认值-->
<!--      form:[]-->
<!--    },-->
<!--    courseId: {-->
<!--      type: String, // 参数类型，这里是字符串类型-->
<!--      required: false, // 是否为必需参数，这里设为非必需-->
<!--      default: '',// 参数的默认值-->
<!--      form:[]-->
<!--    }-->
<!--  },-->
<!--  data(){-->
<!--    return{-->
<!--      user:localStorage.getItem("user") ? (JSON).parse(localStorage.getItem("user")) : {},-->
<!--      receivedData:"",-->
<!--      form:{-->
<!--      },-->
<!--      AParams:{-->
<!--        courseId: this.courseId,-->
<!--        examId:this.param-->
<!--      }-->
<!--    }-->
<!--  },-->
<!--  created() {-->
<!--    this.receivedData = this.param-->
<!--    this.load()-->
<!--  },-->
<!--  mounted() {-->

<!--  },-->
<!--  methods: {-->
<!--    load(){-->
<!--      this.$message.success(this.receivedData)-->
<!--      request.get("exam/examManage/selectById/"+this.receivedData).then(res=>{-->
<!--        if (res.code === '200') {-->
<!--          this.form=res.data-->
<!--        } else {-->
<!--          this.$message.error(res.msg)-->
<!--        }-->
<!--      })-->
<!--    },-->
<!--    submit(){-->
<!--      console.log(this.AParams)-->
<!--      request.get("/exam/examPaper/addAll", {-->
<!--        params:this.AParams-->
<!--      }).then(res => {-->
<!--        if (res.code === "200") {-->
<!--          this.$message.success("添加成功")-->
<!--        } else {-->
<!--          this.$message.error(res.msg)-->
<!--        }-->
<!--      })-->
<!--    },-->
<!--    resetForm(){-->
<!--      this.AParams={-->
<!--        courseId: this.courseId,-->
<!--        examId:this.param-->
<!--      }-->
<!--    }-->
<!--  }-->
<!--}-->
<!--</script>-->


<!--<style scoped>-->
<!--.title{-->
<!--  margin: 4vh 4% 0 4%;-->
<!--  width: 92%;-->
<!--  height: 8vh;-->
<!--  background: #f3f5f8;-->
<!--  border-radius: 10px;-->
<!--  font-size: 26px;-->
<!--  text-align: center;-->
<!--  padding-top: 2vh;-->
<!--}-->
<!--.body{-->
<!--  margin: 2vh 4% 0 4%;-->
<!--  padding: 2vh 2% 2vh 2%;-->
<!--  width: 92%;-->
<!--  height: 64vh;-->
<!--  background: #bcf6dd;-->
<!--  overflow-y: scroll;-->
<!--  border-radius: 10px-->
<!--}-->
<!--.body::-webkit-scrollbar{-->
<!--  width:0;-->
<!--}-->
<!--</style>-->


<template>
  <div style="height: 100vh;">
    <!-- 头部信息 -->
    <el-row style="margin: 2vh 0; text-align: center">
      <el-col :span="6">
        考试科目: {{ form.course?.name }}
      </el-col>
      <el-col :span="10">
        考试开始时间: {{ form.examDate }}
      </el-col>
      <el-col :span="4">
        考试时长: {{ form.totalTime }}分钟
      </el-col>
      <el-col :span="4">
        总分: {{ form.totalScore }}分
      </el-col>
    </el-row>
    <el-row style="text-align: center">
      <el-col :span="6">
        课程编码: {{ form.course?.number }}
      </el-col>
      <el-col :span="8">
        考试类型: {{ form.type }}
      </el-col>
      <el-col :span="8">
        出题人: {{ form.teacher?.name }}
      </el-col>
    </el-row>

    <!-- 主体内容 -->
    <el-row class="main-container">
      <!-- 左侧表单 -->
      <el-col :span="8" class="form-container">
        <el-form
            :model="AParams"
            status-icon
            :rules="rules"
            ref="AParams"
            label-width="100px"
            style="padding: 20px;"
        >
          <el-form-item label="难度等级" prop="difficulty">
            <el-select
                v-model="AParams.difficulty"
                placeholder="请选择难度"
                @change="updateChart"
                style="width: 100%;"
            >
              <el-option label="简单" value="easy"></el-option>
              <el-option label="中等" value="medium"></el-option>
              <el-option label="困难" value="hard"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item
              v-for="(item, index) in questionTypes"
              :key="index"
              :label="item.label"
              :prop="item.prop"
          >
            <el-input-number
                v-model.number="AParams[item.prop]"
                :min="0"
                controls-position="right"
                @change="updateChart"
                style="width: 100%;"
            />
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="submit">提交</el-button>
            <el-button @click="resetForm">重置</el-button>
          </el-form-item>
        </el-form>
      </el-col>

      <!-- 右侧图表 -->
      <el-col :span="16">
        <div class="chart-container">
          <div ref="chart" style="width: 600px; height: 60vh;"></div>
        </div>

      </el-col>
    </el-row>
  </div>
</template>

<script>
import request from "@/utils/request";
import * as echarts from 'echarts';

export default {
  props: {
    param: { type: String, default: '' },
    courseId: { type: String, default: '' }
  },
  data() {
    return {
      questionTypes: [
        { label: '选择题', prop: 'multi' },
        { label: '判断题', prop: 'judge' },
        { label: '填空题', prop: 'fill' },
        { label: '简答题', prop: 'shortAns' },
        { label: '编程题', prop: 'code' }
      ],
      form: {},
      AParams: {
        courseId: this.courseId,
        examId: this.param,
        difficulty: 'medium',
        multi: 0,
        judge: 0,
        fill: 0,
        shortAns: 0,
        code: 0
      },
      rules: {
        multi: [{ type: 'number', message: '必须为数字值' }],
        judge: [{ type: 'number', message: '必须为数字值' }],
        fill: [{ type: 'number', message: '必须为数字值' }],
        shortAns: [{ type: 'number', message: '必须为数字值' }],
        code: [{ type: 'number', message: '必须为数字值' }]
      },
      chart: null
    };
  },
  mounted() {
    this.initChart();
    window.addEventListener('resize', this.handleResize);
  },
  beforeDestroy() {
    window.removeEventListener('resize', this.handleResize);
    if (this.chart) {
      this.chart.dispose();
    }
  },
  methods: {
    initChart() {
      this.chart = echarts.init(this.$refs.chart);
      this.updateChart();
    },
    updateChart() {
      const option = {
        title: {
          text: '题目数量分布',
          left: 'center',
          textStyle: {
            fontSize: 18,
            color: '#130505'
          }
        },
        tooltip: {
          trigger: 'axis',
          formatter: '{b}: {c}题'
        },
        xAxis: {
          type: 'category',
          data: this.questionTypes.map(item => item.label),
          axisLine: { // X轴轴线样式
            lineStyle: {
              color: '#606266' // 轴颜色
            }
          },
          axisTick: { // X轴刻度线
            alignWithLabel: true,
            lineStyle: {
              color: '#909399' // 刻度线颜色
            }
          },
          axisLabel: { // X轴文字样式
            color: '#303133',
            fontSize: 14,
            rotate: 45,
            fontWeight: 500
          }
        },
        yAxis: {
          type: 'value',
          name: '题目数量',
          nameTextStyle: { // Y轴名称样式
            color: '#0756f3',
            fontSize: 14,
            padding: [0, 0, 10, 0]
          },
          minInterval: 1,
          axisLine: { // Y轴轴线
            show: true,
            lineStyle: {
              color: '#070c15'
            }
          },
          axisTick: { // Y轴刻度线
            show: true,
            lineStyle: {
              color: '#0a52e1'
            }
          },
          axisLabel: { // Y轴文字
            color: '#020917',
            fontSize: 12
          },
          splitLine: { // 网格线
            show: true,
            lineStyle: {
              color: '#ebeef5',
              type: 'dashed'
            }
          }
        },
        series: [{
          name: '数量',
          type: 'bar',
          data: this.questionTypes.map(item => this.AParams[item.prop]),
          itemStyle: {
            color: this.getDifficultyColor(),
            borderRadius: [5, 5, 0, 0]
          },
          barWidth: '60%',
          label: {
            show: true,
            position: 'top'
          }
        }],
        grid: {
          containLabel: true,
          left: '3%',
          right: '3%',
          bottom: '12%'
        }
      };
      this.chart.setOption(option);
    },
    getDifficultyColor() {
      const colors = {
        easy: '#91cc75',
        medium: '#fac858',
        hard: '#ee6666'
      };
      return colors[this.AParams.difficulty] || '#5470c6';
    },
    handleResize() {
      this.chart?.resize();
    },
    async load() {
      try {
        const res = await request.get(`exam/examManage/selectById/${this.param}`);
        if (res.code === '200') {
          this.form = res.data;
        } else {
          this.$message.error(res.msg);
        }
      } catch (error) {
        this.$message.error('数据加载失败');
      }
    },
    async submit() {
      try {
        const res = await request.get("/exam/examPaper/addAll", {
          params: this.AParams
        });
        if (res.code === "200") {
          this.$message.success("试卷生成成功");
          this.resetForm();
        } else {
          this.$message.error(res.msg);
        }
      } catch (error) {
        this.$message.error("提交失败，请检查网络");
      }
    },
    resetForm() {
      this.AParams = {
        ...this.AParams,
        difficulty: 'medium',
        multi: 0,
        judge: 0,
        fill: 0,
        shortAns: 0,
        code: 0
      };
      this.$nextTick(this.updateChart);
    }
  },
  created() {
    this.load();
  }
};
</script>

<style scoped>
.main-container {
  height: calc(100vh - 160px);
  margin: 20px;
  background: #f5f7fa;
  border-radius: 8px;
  box-shadow: 0 2px 12px rgba(0,0,0,.1);
}

.form-container {
  height: 60vh;
  background: white;
  border-right: 1px solid #ebeef5;
}

.chart-container {
  width: 100%;
  height: 60vh;
  padding: 20px;
  background: #f0ecf1;
}

.el-form-item {
  margin-bottom: 22px;
}

.el-button {
  margin-top: 10px;
  width: 48%;
}
</style>
