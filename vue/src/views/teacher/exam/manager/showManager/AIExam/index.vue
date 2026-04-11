<template>
  <div class="auto-paper-page">
    <div class="auto-paper-summary">
      <div class="auto-paper-summary__item">
        <span class="auto-paper-summary__label">考试科目</span>
        <span class="auto-paper-summary__value">{{ form.course?.name }}</span>
      </div>
      <div class="auto-paper-summary__item auto-paper-summary__item--wide">
        <span class="auto-paper-summary__label">考试开始时间</span>
        <span class="auto-paper-summary__value">{{ form.examDate }}</span>
      </div>
      <div class="auto-paper-summary__item">
        <span class="auto-paper-summary__label">考试时长</span>
        <span class="auto-paper-summary__value">{{ form.totalTime }} 分钟</span>
      </div>
      <div class="auto-paper-summary__item">
        <span class="auto-paper-summary__label">总分</span>
        <span class="auto-paper-summary__value">{{ form.totalScore }} 分</span>
      </div>
      <div class="auto-paper-summary__item">
        <span class="auto-paper-summary__label">课程编号</span>
        <span class="auto-paper-summary__value">{{ form.course?.number }}</span>
      </div>
      <div class="auto-paper-summary__item">
        <span class="auto-paper-summary__label">考试类型</span>
        <span class="auto-paper-summary__value">{{ form.type }}</span>
      </div>
      <div class="auto-paper-summary__item">
        <span class="auto-paper-summary__label">出题教师</span>
        <span class="auto-paper-summary__value">{{ form.teacher?.name }}</span>
      </div>
    </div>

    <div class="auto-paper-main">
      <div class="auto-paper-form">
        <div class="auto-paper-panel__head">
          <div>
            <h3 class="auto-paper-panel__title">组卷参数</h3>
            <p class="auto-paper-panel__desc">设置题型数量与难度分布，提交后按当前参数生成试卷。</p>
          </div>
        </div>

        <el-form
          :model="AParams"
          status-icon
          :rules="rules"
          ref="AParams"
          label-width="96px"
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
              :disabled="generating"
              style="width: 100%;"
            />
          </el-form-item>

          <div v-if="generating || progress > 0" class="auto-paper-progress">
            <div class="auto-paper-progress__head">
              <span class="auto-paper-progress__label">组卷进度</span>
              <span class="auto-paper-progress__value">{{ progress }}%</span>
            </div>
            <el-progress
              :percentage="progress"
              :stroke-width="10"
              :show-text="false"
              :status="progress === 100 ? 'success' : undefined"
            />
            <div class="auto-paper-progress__hint">{{ progressText }}</div>
          </div>

          <div class="auto-paper-form__actions">
            <el-button type="primary" :loading="generating" @click="submit">提交</el-button>
            <el-button :disabled="generating" @click="resetForm">重置</el-button>
          </div>
        </el-form>
      </div>

      <div class="auto-paper-chart">
        <div class="auto-paper-panel__head">
          <div>
            <h3 class="auto-paper-panel__title">题型数量分布</h3>
            <p class="auto-paper-panel__desc">右侧图表会随着题型数量和难度选择实时更新。</p>
          </div>
        </div>
        <div ref="chartWrap" class="auto-paper-chart__wrap">
          <div ref="chart" class="auto-paper-chart__canvas"></div>
        </div>
      </div>
    </div>
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
        { label: '代码题', prop: 'code' }
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
      chart: null,
      resizeObserver: null,
      generating: false,
      progress: 0,
      progressText: '等待开始组卷',
      progressTimer: null
    };
  },
  mounted() {
    this.initChart();
    window.addEventListener('resize', this.handleResize);
    if (window.ResizeObserver && this.$refs.chartWrap) {
      this.resizeObserver = new ResizeObserver(() => {
        this.handleResize();
      });
      this.resizeObserver.observe(this.$refs.chartWrap);
    }
    this.$nextTick(() => {
      setTimeout(() => {
        this.handleResize();
      }, 50);
    });
  },
  beforeDestroy() {
    window.removeEventListener('resize', this.handleResize);
    if (this.resizeObserver) {
      this.resizeObserver.disconnect();
    }
    if (this.progressTimer) {
      clearInterval(this.progressTimer);
    }
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
          text: '题型数量分布',
          left: 'center',
          top: 0,
          textStyle: {
            fontSize: 16,
            fontWeight: 600,
            color: '#0f172a'
          }
        },
        tooltip: {
          trigger: 'axis',
          formatter: '{b}: {c} 题'
        },
        xAxis: {
          type: 'category',
          data: this.questionTypes.map(item => item.label),
          axisLine: {
            lineStyle: {
              color: '#94a3b8'
            }
          },
          axisTick: {
            alignWithLabel: true,
            lineStyle: {
              color: '#cbd5e1'
            }
          },
          axisLabel: {
            color: '#475569',
            fontSize: 13,
            rotate: 0,
            fontWeight: 500
          }
        },
        yAxis: {
          type: 'value',
          name: '题目数量',
          nameTextStyle: {
            color: '#64748b',
            fontSize: 13,
            padding: [0, 0, 10, 0]
          },
          minInterval: 1,
          axisLine: {
            show: true,
            lineStyle: {
              color: '#cbd5e1'
            }
          },
          axisTick: {
            show: true,
            lineStyle: {
              color: '#cbd5e1'
            }
          },
          axisLabel: {
            color: '#475569',
            fontSize: 12
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: '#e2e8f0',
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
          barMaxWidth: 56,
          label: {
            show: true,
            position: 'top'
          }
        }],
        grid: {
          containLabel: true,
          left: '5%',
          right: '4%',
          bottom: '10%',
          top: 52
        }
      };
      this.chart.setOption(option);
      this.$nextTick(() => {
        this.handleResize();
      });
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
      if (this.chart) {
        this.chart.resize();
      }
    },
    startFakeProgress() {
      this.generating = true;
      this.progress = 0;
      this.progressText = '正在分析组卷参数...';
      if (this.progressTimer) {
        clearInterval(this.progressTimer);
      }
      this.progressTimer = setInterval(() => {
        if (this.progress < 32) {
          this.progress += 8;
          this.progressText = '正在分析组卷参数...';
        } else if (this.progress < 68) {
          this.progress += 6;
          this.progressText = '正在匹配题型与难度分布...';
        } else if (this.progress < 92) {
          this.progress += 3;
          this.progressText = '正在整理试卷结构...';
        }
      }, 180);
    },
    finishFakeProgress(success) {
      return new Promise(resolve => {
        if (this.progressTimer) {
          clearInterval(this.progressTimer);
          this.progressTimer = null;
        }
        this.progress = 100;
        this.progressText = success ? '组卷完成，试卷已生成。' : '组卷中断，请稍后重试。';
        setTimeout(() => {
          this.generating = false;
          if (!success) {
            this.progress = 0;
            this.progressText = '等待开始组卷';
          }
          resolve();
        }, success ? 420 : 320);
      });
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
      if (this.generating) {
        return;
      }
      this.startFakeProgress();
      try {
        const res = await request.get("/exam/examPaper/addAll", {
          params: this.AParams
        });
        if (res.code === "200") {
          await this.finishFakeProgress(true);
          this.$message.success("试卷生成成功");
          this.resetForm();
        } else {
          await this.finishFakeProgress(false);
          this.$message.error(res.msg);
        }
      } catch (error) {
        await this.finishFakeProgress(false);
        this.$message.error("提交失败，请检查网络");
      }
    },
    resetForm() {
      if (this.generating) {
        return;
      }
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
.auto-paper-page {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.auto-paper-summary {
  display: grid;
  grid-template-columns: repeat(4, minmax(0, 1fr));
  gap: 14px;
}

.auto-paper-summary__item {
  display: flex;
  flex-direction: column;
  justify-content: center;
  min-height: 84px;
  padding: 14px 18px;
  border: 1px solid #e5e7eb;
  border-radius: 6px;
  background: #fff;
}

.auto-paper-summary__item--wide {
  grid-column: span 2;
}

.auto-paper-summary__label {
  margin-bottom: 8px;
  font-size: 13px;
  font-weight: 600;
  color: #64748b;
}

.auto-paper-summary__value {
  font-size: 16px;
  line-height: 1.6;
  font-weight: 700;
  color: #0f172a;
}

.auto-paper-main {
  display: grid;
  grid-template-columns: minmax(320px, 360px) minmax(0, 1fr);
  gap: 20px;
}

.auto-paper-form,
.auto-paper-chart {
  padding: 20px;
  border: 1px solid #e5e7eb;
  border-radius: 6px;
  background: #fff;
}

.auto-paper-panel__head {
  padding-bottom: 14px;
  margin-bottom: 18px;
  border-bottom: 1px solid #eef2f7;
}

.auto-paper-panel__title {
  margin: 0;
  font-size: 16px;
  font-weight: 700;
  color: #0f172a;
}

.auto-paper-panel__desc {
  margin: 6px 0 0;
  font-size: 13px;
  line-height: 1.5;
  color: #6b7280;
}

.auto-paper-form .el-form-item {
  margin-bottom: 18px;
}

.auto-paper-form__actions {
  display: flex;
  justify-content: center;
  gap: 10px;
  padding-top: 8px;
}

.auto-paper-progress {
  margin-top: 10px;
  padding: 12px 14px;
  border: 1px solid #dbeafe;
  border-radius: 6px;
  background: #f8fbff;
}

.auto-paper-progress__head {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 10px;
  margin-bottom: 8px;
}

.auto-paper-progress__label {
  font-size: 13px;
  font-weight: 600;
  color: #0f172a;
}

.auto-paper-progress__value {
  font-size: 12px;
  color: #0369a1;
}

.auto-paper-progress__hint {
  margin-top: 8px;
  font-size: 12px;
  color: #64748b;
}

.auto-paper-chart__wrap {
  width: 100%;
  min-height: 430px;
}

.auto-paper-chart__canvas {
  width: 100%;
  height: 430px;
}

@media (max-width: 1280px) {
  .auto-paper-summary {
    grid-template-columns: repeat(2, minmax(0, 1fr));
  }

  .auto-paper-summary__item--wide {
    grid-column: span 1;
  }
}

@media (max-width: 1200px) {
  .auto-paper-main {
    grid-template-columns: 1fr;
  }
}
</style>
