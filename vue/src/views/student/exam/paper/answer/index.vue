<template>
  <div class="exam-answer-page">
    <div class="head">
      <el-row :gutter="20" type="flex" align="middle" class="head-row">
        <el-col :span="4">
          <el-button type="text" class="head-back" @click="end()">返回</el-button>
        </el-col>
        <el-col :span="8">
          <div class="head-meta">
            <span class="head-meta__label">当前试卷</span>
            <h1 class="head-meta__title">{{ course.name }}考试试卷</h1>
          </div>
        </el-col>
        <el-col :span="5">
          <div class="head-stat">
            <span class="head-stat__label">考试时长</span>
            <strong class="head-stat__value">{{ form.totalTime || 0 }} 分钟</strong>
          </div>
        </el-col>
        <el-col :span="7">
          <div class="head-stat head-stat--highlight">
            <span class="head-stat__label">{{ examStatus === "ongoing" ? "剩余时间" : "当前状态" }}</span>
            <strong class="head-stat__value">{{ countdownText }}</strong>
          </div>
        </el-col>
      </el-row>
    </div>
    <div class="main">
      <el-row :gutter="24" type="flex" align="stretch" class="main-grid">
        <el-col :span="8" class="sidebar-col">
          <el-card class="box-card message">
            <div class="section-card__header">
              <h3>考试信息</h3>
              <span>当前答题信息与基础状态</span>
            </div>
            <div class="info-grid">
              <div class="info-item">
                <span class="info-item__label">科目</span>
                <strong class="info-item__value">{{ course.name || "--" }}</strong>
              </div>
              <div class="info-item">
                <span class="info-item__label">课程编码</span>
                <strong class="info-item__value">{{ course.number || "--" }}</strong>
              </div>
              <div class="info-item">
                <span class="info-item__label">考试类型</span>
                <strong class="info-item__value">{{ form.type || "--" }}</strong>
              </div>
              <div class="info-item">
                <span class="info-item__label">出题人</span>
                <strong class="info-item__value">{{ teacher.name || "--" }}</strong>
              </div>
            </div>
          </el-card>
          <el-card class="box-card shows">
            <div class="section-card__header section-card__header--compact">
              <h3>状态说明</h3>
            </div>
            <div class="status-legend">
              <div class="status-item">
                <div class="nowQ">1</div>
                <span>当前</span>
              </div>
              <div class="status-item">
                <div class="beforeQ">2</div>
                <span>未答</span>
              </div>
              <div class="status-item">
                <div class="afterQ">3</div>
                <span>已答</span>
              </div>
              <div class="status-item">
                <div class="setQ">4</div>
                <span>标记</span>
              </div>
            </div>
          </el-card>
          <el-card class="box-card number">
            <div class="section-card__header section-card__header--compact">
              <h3>题目导航</h3>
              <span>点击题号直接跳转</span>
            </div>
            <div v-if="questionMulti.length > 0" class="number-section">
              <div class="titleQuestion">
                一、不定项选择题
              </div>
              <div class="numberQuestion">
                <el-row>
                  <el-col :span="4"  v-for="(data, index) in questionMulti" :key="index">
                    <div :class="getQuestionStatusClass('multi', index)" @click="goToQuestion(data, index)">{{index+1}}
                    </div>
                  </el-col>
                </el-row>
              </div>
            </div>
            <div v-if="questionFill.length > 0" class="number-section">
              <div class="titleQuestion">
                二、填空题
              </div>
              <div class="numberQuestion">
                <el-row>
                  <el-col :span="4"  v-for="(data, index) in questionFill" :key="index">
                    <div :class="getQuestionStatusClass('fill', index)" @click="goToQuestion(data, index)">{{index+1}}
                    </div>
                  </el-col>
                </el-row>
              </div>
            </div>
            <div v-if="questionJudge.length > 0" class="number-section">
              <div class="titleQuestion">
                三、判断题
              </div>
              <div class="numberQuestion">
                <el-row>
                  <el-col :span="4"  v-for="(data, index) in questionJudge" :key="index">
                    <div :class="getQuestionStatusClass('judge', index)" @click="goToQuestion(data, index)">{{index+1}}
                    </div>
                  </el-col>
                </el-row>
              </div>
            </div>
            <div v-if="questionShortAns.length > 0" class="number-section">
              <div class="titleQuestion">
                四、简答题
              </div>
              <div class="numberQuestion">
                <el-row>
                  <el-col :span="4"  v-for="(data, index) in questionShortAns" :key="index">
                    <div :class="getQuestionStatusClass('shortAns', index)" @click="goToQuestion(data, index)">{{index+1}}
                    </div>
                  </el-col>
                </el-row>
              </div>
            </div>
            <div v-if="questionCode.length > 0" class="number-section">
              <div class="titleQuestion">
                五、代码题
              </div>
              <div class="numberQuestion">
                <el-row>
                  <el-col :span="4"  v-for="(data, index) in questionCode" :key="index">
                    <div :class="getQuestionStatusClass('code', index)" @click="goToQuestion(data, index)">{{index+1}}
                    </div>
                  </el-col>
                </el-row>
              </div>
            </div>
          </el-card>
        </el-col>
        <el-col :span="16" class="content-col">
          <div class="questions">
            <el-card class="box-card questionBody">
              <div class="question-topbar">
                <div class="question-index">{{ displayQuestionNumber }}</div>
                <div class="question-meta">
                  <span class="question-badge">{{ questionTypeLabel }}</span>
                  <span class="question-badge question-badge--ghost">{{ isCurrentQuestionAnswered ? "已作答" : "待作答" }}</span>
                  <span v-if="question.isMarked === '是'" class="question-badge question-badge--mark">已标记</span>
                </div>
              </div>
              <div class="question-stem">
                <div v-html="question.question || ''" class="w-e-text"></div>
              </div>
              <div v-if="this.currentQuestionType==='multi'" class="answer-panel">
                <div class="option-list">
                  <div class="option-item"><strong class="option-key">A</strong><span>{{ question.answerA }}</span></div>
                  <div class="option-item"><strong class="option-key">B</strong><span>{{ question.answerB }}</span></div>
                  <div class="option-item"><strong class="option-key">C</strong><span>{{ question.answerC }}</span></div>
                  <div class="option-item"><strong class="option-key">D</strong><span>{{ question.answerD }}</span></div>
                </div>
                <div class="answer-divider"></div>
                <div class="answer-toolbar">
                  <div class="answer-summary">
                    <span>你的答案</span>
                    <strong>{{ answer || "未作答" }}</strong>
                  </div>
                  <el-checkbox-group @change="multiChange" v-model="multiAnswer" class="choice-group">
                    <el-checkbox v-for="m in multi" :label="m" :key="m">{{m}}</el-checkbox>
                  </el-checkbox-group>
                  <el-button type="primary" class="submit-answer" @click="submit">保存答案</el-button>
                </div>
              </div>
              <div v-if="this.currentQuestionType==='fill'" class="answer-panel">
                <div class="answer-divider"></div>
                <div class="fill-answer-row">
                  <div class="answer-summary">
                    <span>请作答</span>
                    <strong>{{ isCurrentQuestionAnswered ? "已填写" : "未填写" }}</strong>
                  </div>
                  <el-input
                      class="answer-input"
                      type="textarea"
                      :autosize="{ minRows: 1, maxRows: 2}"
                      placeholder="请输入你的答案"
                      v-model="answer">
                  </el-input>
                  <el-button type="primary" class="submit-answer" @click="submit">保存答案</el-button>
                </div>
              </div>
              <div v-if="this.currentQuestionType==='judge'" class="answer-panel">
                <div class="answer-divider"></div>
                <div class="judge-answer-row">
                  <div class="answer-summary">
                    <span>你的判断</span>
                    <strong>{{ answer || "未选择" }}</strong>
                  </div>
                  <div class="judge-group">
                    <el-radio v-model="answer" label="T">T</el-radio>
                    <el-radio v-model="answer" label="F">F</el-radio>
                  </div>
                  <el-button type="primary" class="submit-answer" @click="submit">保存答案</el-button>
                </div>
              </div>
              <div v-if="this.currentQuestionType==='shortAns'" class="answer-panel">
                <div class="answer-divider"></div>
                <div class="editor-answer-row">
                  <div class="answer-summary">
                    <span>简答作答区</span>
                    <strong>支持富文本编辑</strong>
                  </div>
                  <div class="editor-shell">
                    <div class="w-e-text-container" id='editorA'></div>
                  </div>
                  <el-button type="primary" class="submit-answer" @click="submit">保存答案</el-button>
                </div>
              </div>
              <div v-if="this.currentQuestionType==='code'" class="answer-panel">
                <div class="answer-divider"></div>
                <div class="code-workspace">
                  <div class="code-editor-shell">
                    <div class="answer-summary">
                      <span>代码编辑区</span>
                      <strong>C 语言运行环境</strong>
                    </div>
                    <div class="code-editor-frame">
                      <monaco-editor
                          v-model="answer"
                          language="c"
                          :options="codeEditorOptions"
                          class="code-editor"
                      ></monaco-editor>
                    </div>
                  </div>
                  <div class="code-result-card">
                    <div class="code-result-card__head">
                      <div>
                        <span class="code-result-card__label">运行面板</span>
                        <strong class="code-result-card__title">代码执行与输出</strong>
                      </div>
                      <span class="code-result-card__status">{{ result ? "已更新" : "等待运行" }}</span>
                    </div>
                    <el-button class="code-run" @click="runCpp">运行代码</el-button>
                    <el-input
                        class="code-result"
                        disabled
                        type="textarea"
                        :autosize="{ minRows: 12, maxRows: 12}"
                        placeholder="运行结果"
                        v-model="result">
                    </el-input>
                    <el-button type="primary" class="submit-answer submit-answer--full" @click="submit">保存答案</el-button>
                  </div>
                </div>
              </div>
            </el-card>
            <el-card class="box-card questionBottom">
              <el-row class="question-actions" type="flex" align="middle">
                <el-col :span="6" class="question-actions__left">
                  <el-button type="primary" round class="question-nav-btn" @click="prevQuestion">上一题</el-button>
                </el-col>
                <el-col :span="12" class="question-actions__center">
                  <el-button v-if="question.isMarked==='否'" type="primary" round class="question-mark-btn" @click="markQuestion('是')">标记本题</el-button>
                  <el-button v-if="question.isMarked==='是'" type="primary" round class="question-mark-btn question-mark-btn--active" @click="markQuestion('否')">取消标记</el-button>
                </el-col>
                <el-col :span="6" class="question-actions__right">
                  <el-button type="primary" round class="question-nav-btn" @click="nextQuestion">下一题</el-button>
                </el-col>
              </el-row>
            </el-card>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import MonacoEditor from "monaco-editor-vue";
import request from "@/utils/request";
import E from "wangeditor";
let editorA = null
function initWangEditorA(content){ setTimeout(() => {
  if(!editorA){
    editorA=new E('#editorA')
    editorA.config.placeholder='请输入内容'
    editorA.config.uploadFileName='file'
    editorA.config.uploadImgServer='http://localhost:9998/files/wang/upload'
    editorA.create()
  }
  editorA.txt.html(content)
},0)
}
function destroyWangEditorA() {
  if (editorA && typeof editorA.destroy === "function") {
    editorA.destroy();
  }
  editorA = null;
}
export default {
  components: {
    MonacoEditor,
  },
  data() {
    return {
      nowTime: "",
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      receivedData: "",
      // 初始

      form: {},
      course:{},
      teacher:{},
      dataTime: {},
      clockTimer: null,
      countdownTimer: null,
      // 初始数据

      examStatus: 'ongoing', // 考试状态，初始为考试前
      questionCode: [],
      questionFill: [],
      questionMulti: [],
      questionShortAns: [],
      questionJudge: [],
      questionAnswer: [],

      //当前题目
      currentQuestionType: null,
      currentQuestionIndex: null,
      question:{},
      answer:"",

      multi:['A','B','C','D'],
      multiAnswer:[],
      result:"",
      codeEditorOptions: {
        fontSize: 18,
        theme: "vs-dark",
        minimap: {
          enabled: false
        },
        automaticLayout: true,
        lineNumbersMinChars: 3,
        scrollBeyondLastLine: false,
        renderLineHighlight: "all",
        padding: {
          top: 16
        }
      }
    };
  },
  computed: {
    countdownText() {
      if (this.examStatus !== 'ongoing') {
        return '考试已结束，停止答题';
      }
      return `${this.dataTime.hour || 0}时${this.dataTime.minute || 0}分${this.dataTime.second || 0}秒`;
    },
    questionTypeLabel() {
      const labelMap = {
        multi: '不定项选择题',
        fill: '填空题',
        judge: '判断题',
        shortAns: '简答题',
        code: '代码题'
      };
      return labelMap[this.currentQuestionType] || '试题';
    },
    displayQuestionNumber() {
      return typeof this.question.index === 'number' ? this.question.index + 1 : '--';
    },
    isCurrentQuestionAnswered() {
      return this.isAnswerCompleted(this.answer);
    }
  },
  created() {
    this.receivedData = this.$route.params;
    this.load();
    this.findQuestion();
  },
  mounted() {
    this.getTime();
  },
  beforeDestroy() {
    if (this.clockTimer) {
      clearInterval(this.clockTimer);
    }
    if (this.countdownTimer) {
      clearInterval(this.countdownTimer);
    }
    destroyWangEditorA();
  },
  methods: {
    // 获取当前时间
    getTime() {
      if (this.clockTimer) {
        clearInterval(this.clockTimer);
      }
      this.clockTimer = setInterval(() => {
        let timeDate = new Date();
        let year = timeDate.getFullYear();
        let month = timeDate.getMonth() + 1;
        let day = timeDate.getDate();
        let hours = timeDate.getHours();
        hours = hours >= 10 ? hours : "0" + hours;
        let minutes = timeDate.getMinutes();
        minutes = minutes >= 10 ? minutes : "0" + minutes;
        let seconds = timeDate.getSeconds();
        seconds = seconds >= 10 ? seconds : "0" + seconds;
        let week = timeDate.getDay();
        let weekArr = [
          "星期日",
          "星期一",
          "星期二",
          "星期三",
          "星期四",
          "星期五",
          "星期六"
        ];

        this.nowTime = `${year}-${month}-${day} ${hours}:${minutes}:${seconds} ${weekArr[week]}`;
      }, 1000);
    },
    // 计算距离考试结束的剩余时间
    calculateRemainingTime() {
      if (this.form.examDate) {
        let examStartDate = new Date(this.form.examDate);
        let examEndDate = new Date(examStartDate.getTime() + this.form.totalTime * 60 * 1000); // 计算考试结束时间

        if (this.countdownTimer) {
          clearInterval(this.countdownTimer);
        }
        this.countdownTimer = setInterval(() => {
          let now = new Date();
          let diff = examEndDate.getTime() - now.getTime();

          if (diff > 0) {
            // 考试正在进行中
            this.examStatus = 'ongoing';
            let seconds = Math.floor(diff / 1000);
            let minutes = Math.floor(seconds / 60);
            let hours = Math.floor(minutes / 60);
            let days = Math.floor(hours / 24);
            let months = Math.floor(days / 30);
            let years = Math.floor(months / 12);

            days %= 30;
            hours %= 24;
            minutes %= 60;
            seconds %= 60;

            this.dataTime = {
              year: years,
              month: months,
              day: days,
              hour: hours,
              minute: minutes,
              second: seconds
            };
          } else {
            // 考试已结束
            this.end();
            this.examStatus = 'ended';
            this.dataTime = {
              year: 0,
              month: 0,
              day: 0,
              hour: 0,
              minute: 0,
              second: 0
            };
            clearInterval(this.countdownTimer);
          }
        }, 1000);
      }
    },
    // 返回上一页
    end() {
      this.$router.push({
        name: "studentExamList",
      });
    },
    // 加载考试信息
    load() {
      request.get("exam/examManage/selectById/" + this.receivedData.id).then(res => {
        if (res.code === '200') {
          this.form = res.data;
          this.course=this.form.course
          this.teacher=this.form.teacher
          this.calculateRemainingTime();
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    runCpp() {
      const params = {
        m: this.answer,
        t: "c"
      }
      request.post('/exam/code/cpp', params).then(res => {
        this.result = res.data
      })
    },
    multiChange(){
      this.answer=""
      const multiAnswer = this.multiAnswer.sort();
      // 将排序后的结果拼接成字符串
      this.answer = multiAnswer.join('');
    },
    selectQuestion(){
      this.multiAnswer = this.answer.split('')
    },
    isAnswerCompleted(value) {
      if (value === null || value === undefined) {
        return false;
      }
      if (typeof value !== 'string') {
        return Boolean(value);
      }
      const plainText = value
          .replace(/<p><br><\/p>/gi, '')
          .replace(/&nbsp;/gi, '')
          .replace(/<[^>]+>/g, '')
          .trim();
      return plainText !== '';
    },
    findQuestion() {
      this.findQuestionFill();
      this.findQuestionCode();
      this.findQuestionJudge();
      this.findQuestionMulti();
      this.findQuestionShortAns();
      this.findAnswer();
    },
    findQuestionCode() {
      let params;
      params = {
        examId: this.receivedData.id,
        questionType: "code"
      };
      request.get('/exam/examPaper/selectAll', {
        params: params
      }).then(res => {
        if (res.code === '200') {
          this.questionCode = res.data;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findQuestionFill() {
      let params;
      params = {
        examId: this.receivedData.id,
        questionType: "fill"
      };
      request.get('/exam/examPaper/selectAll', {
        params: params
      }).then(res => {
        if (res.code === '200') {
          this.questionFill = res.data;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findQuestionJudge() {
      let params;
      params = {
        examId: this.receivedData.id,
        questionType: "judge"
      };
      request.get('/exam/examPaper/selectAll', {
        params: params
      }).then(res => {
        if (res.code === '200') {
          this.questionJudge = res.data;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findQuestionMulti() {
      let params;
      params = {
        examId: this.receivedData.id,
        questionType: "multi"
      };
      request.get('/exam/examPaper/selectAll', {
        params: params
      }).then(res => {
        if (res.code === '200') {
          this.questionMulti = res.data;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findQuestionShortAns() {
      let params;
      params = {
        examId: this.receivedData.id,
        questionType: "shortAns"
      };
      request.get('/exam/examPaper/selectAll', {
        params: params
      }).then(res => {
        if (res.code === '200') {
          this.questionShortAns = res.data;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findAnswer() {
      let params;
      params = {
        examId: this.receivedData.id,
        studentId: this.user.id
      };
      request.get('/exam/examAnswer/selectAll', {
        params: params
      }).then(res => {
        if (res.code === '200') {
          this.questionAnswer = res.data;
          this.goTo();
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findAnswers() {
      let params;
      params = {
        examId: this.receivedData.id,
        studentId: this.user.id
      };
      request.get('/exam/examAnswer/selectAll', {
        params: params
      }).then(res => {
        if (res.code === '200') {
          this.questionAnswer = res.data;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    goTo(){
      if (this.questionMulti.length > 0) {
        this.goToQuestion(this.questionMulti[0], 0);
      }
      else if (this.questionFill.length > 0) {
        this.goToQuestion(this.questionFill[0], 0);
      }
      else if (this.questionJudge.length > 0) {
        this.goToQuestion(this.questionJudge[0], 0);
      }
      else if (this.questionShortAns.length > 0) {
        this.goToQuestion(this.questionShortAns[0], 0);
      }
      else if (this.questionCode.length > 0) {
        this.goToQuestion(this.questionCode[0], 0);
      }
    },
    // 获取题目的状态类名
    getQuestionStatusClass(questionType, index) {
      let questionId;
      switch (questionType) {
        case 'multi':
          questionId = this.questionMulti[index].id;
          break;
        case 'fill':
          questionId = this.questionFill[index].id;
          break;
        case 'judge':
          questionId = this.questionJudge[index].id;
          break;
        case 'shortAns':
          questionId = this.questionShortAns[index].id;
          break;
        case 'code':
          questionId = this.questionCode[index].id;
          break;
      }
      const answer = this.questionAnswer.find(ans => ans.paperId === questionId);
      if (this.currentQuestionType === questionType && this.currentQuestionIndex === index) {
        return 'nowQQ';
      } else if (answer) {
        if (answer.isMarked==='是') {
          return 'setQQ';
        } else if(this.isAnswerCompleted(answer.answer)) {
          return 'afterQQ';
        }
        else {
          return 'beforeQQ';
        }
      } else {
        return 'beforeQQ';
      }
    },
    // 跳转到指定题目
    goToQuestion(question, index) {
      if (this.currentQuestionType === 'shortAns' && question.questionType !== 'shortAns') {
        destroyWangEditorA();
      }
      this.answer=""
      this.multiAnswer=[]
      this.currentQuestionType = question.questionType;
      this.currentQuestionIndex = index;
      const answer = this.questionAnswer.find(ans => ans.paperId === question.id);
      this.answer=answer ? answer.answer:""
      // 这里可以添加显示具体题目的逻辑
      switch (question.questionType) {
        case 'multi':
          this.question = question.questionMulti;
          this.selectQuestion()
          break;
        case 'fill':
          this.question = question.questionFill;
          break;
        case 'judge':
          this.question = question.questionJudge;
          break;
        case 'shortAns':
          this.question = question.questionShortAns;
          initWangEditorA(this.answer)
          break;
        case 'code':
          this.question = question.questionCode;
          break;
      }
      this.question.index=index
      this.question.paperId=question.id
      this.question.isMarked=answer?answer.isMarked:"否"
    },
    // 上一题
    prevQuestion() {
      if (this.currentQuestionType && this.currentQuestionIndex !== null) {
        let prevIndex = this.currentQuestionIndex - 1;
        let prevType = this.currentQuestionType;

        // 处理当前类型题目索引越界的情况，切换到上一种题型
        if (prevIndex < 0) {
          switch (this.currentQuestionType) {
            case 'fill':
              if (this.questionMulti.length > 0) {
                prevType = 'multi';
                prevIndex = this.questionMulti.length - 1;
              }
              break;
            case 'judge':
              if (this.questionFill.length > 0) {
                prevType = 'fill';
                prevIndex = this.questionFill.length - 1;
              } else if (this.questionMulti.length > 0) {
                prevType = 'multi';
                prevIndex = this.questionMulti.length - 1;
              } else if (this.questionShortAns.length > 0) {
                prevType = 'shortAns';
                prevIndex = this.questionShortAns.length - 1;
              }
              break;
            case 'shortAns':
              if (this.questionJudge.length > 0) {
                prevType = 'judge';
                prevIndex = this.questionJudge.length - 1;
              } else if (this.questionFill.length > 0) {
                prevType = 'fill';
                prevIndex = this.questionFill.length - 1;
              } else if (this.questionMulti.length > 0) {
                prevType = 'multi';
                prevIndex = this.questionMulti.length - 1;
              }
              break;
            case 'code':
              if (this.questionShortAns.length > 0) {
                prevType = 'shortAns';
                prevIndex = this.questionShortAns.length - 1;
              } else if (this.questionJudge.length > 0) {
                prevType = 'judge';
                prevIndex = this.questionJudge.length - 1;
              } else if (this.questionFill.length > 0) {
                prevType = 'fill';
                prevIndex = this.questionFill.length - 1;
              } else if (this.questionMulti.length > 0) {
                prevType = 'multi';
                prevIndex = this.questionMulti.length - 1;
              }
              break;
          }
        }

        // 如果找到了上一题，切换到该题目
        if (prevIndex >= 0) {
          let question;
          switch (prevType) {
            case 'multi':
              question = this.questionMulti[prevIndex];
              break;
            case 'fill':
              question = this.questionFill[prevIndex];
              break;
            case 'judge':
              question = this.questionJudge[prevIndex];
              break;
            case 'shortAns':
              question = this.questionShortAns[prevIndex];
              break;
            case 'code':
              question = this.questionCode[prevIndex];
              break;
          }
          this.goToQuestion(question, prevIndex);
        }
      }
    },
    // 下一题
    nextQuestion() {
      if (this.currentQuestionType && this.currentQuestionIndex !== null) {
        let nextIndex = this.currentQuestionIndex + 1;
        let nextType = this.currentQuestionType;

        // 处理当前类型题目索引越界的情况，切换到下一种题型
        switch (this.currentQuestionType) {
          case 'multi':
            if (nextIndex >= this.questionMulti.length) {
              if (this.questionFill.length > 0) {
                nextType = 'fill';
                nextIndex = 0;
              } else if (this.questionJudge.length > 0) {
                nextType = 'judge';
                nextIndex = 0;
              } else if (this.questionShortAns.length > 0) {
                nextType = 'shortAns';
                nextIndex = 0;
              } else if (this.questionCode.length > 0) {
                nextType = 'code';
                nextIndex = 0;
              }
            }
            break;
          case 'fill':
            if (nextIndex >= this.questionFill.length) {
              if (this.questionJudge.length > 0) {
                nextType = 'judge';
                nextIndex = 0;
              } else if (this.questionShortAns.length > 0) {
                nextType = 'shortAns';
                nextIndex = 0;
              } else if (this.questionCode.length > 0) {
                nextType = 'code';
                nextIndex = 0;
              }
            }
            break;
          case 'judge':
            if (nextIndex >= this.questionJudge.length) {
              if (this.questionShortAns.length > 0) {
                nextType = 'shortAns';
                nextIndex = 0;
              } else if (this.questionCode.length > 0) {
                nextType = 'code';
                nextIndex = 0;
              }
            }
            break;
          case 'shortAns':
            if (nextIndex >= this.questionShortAns.length) {
              if (this.questionCode.length > 0) {
                nextType = 'code';
                nextIndex = 0;
              }
            }
            break;
        }

        // 如果找到了下一题，切换到该题目
        if (nextType) {
          let question;
          switch (nextType) {
            case 'multi':
              if (nextIndex < this.questionMulti.length) {
                question = this.questionMulti[nextIndex];
              }
              break;
            case 'fill':
              if (nextIndex < this.questionFill.length) {
                question = this.questionFill[nextIndex];
              }
              break;
            case 'judge':
              if (nextIndex < this.questionJudge.length) {
                question = this.questionJudge[nextIndex];
              }
              break;
            case 'shortAns':
              if (nextIndex < this.questionShortAns.length) {
                question = this.questionShortAns[nextIndex];
              }
              break;
            case 'code':
              if (nextIndex < this.questionCode.length) {
                question = this.questionCode[nextIndex];
              }
              break;
          }
          if (question) {
            this.goToQuestion(question, nextIndex);
          }
        }
      }
    },
    // 标记题目
    markQuestion(isMarked) {
      const answer = this.questionAnswer.find(ans => ans.paperId === this.question.paperId);
      console.log(answer?answer:"")
      if(answer){
        //已经有，进行修改
        let params;
        params={
          id:answer.id,
          studentId:this.user.id,
          isMarked:isMarked
        }
        request.put("exam/examAnswer/update",params).then(res=>{
          if(res.code==='200'){
            this.question.isMarked=isMarked
            this.findAnswers()
          }else{
            this.$message.error(res.msg)
          }
        })
      }else{
        //没有。进行添加
        let params;
        params={
          examId:this.receivedData.id,
          paperId:this.question.paperId,
          studentId:this.user.id,
          isMarked:isMarked
        }
        request.post("exam/examAnswer/add",params).then(res=>{
          if(res.code==='200'){
            this.question.isMarked=isMarked
            this.findAnswers()
          }else{
            this.$message.error(res.msg)
          }
        })
      }
    },
    submit(){
      const answer = this.questionAnswer.find(ans => ans.paperId === this.question.paperId);
      if(this.currentQuestionType==='shortAns' && editorA){
        this.answer=editorA.txt.html()
      }
      if(answer){
        //已经有，进行修改
        let params;
        params={
          id:answer.id,
          studentId:this.user.id,
          answer:this.answer
        }
        request.put("exam/examAnswer/update",params).then(res=>{
          if(res.code==='200'){
            this.findAnswers()
          }else{
            this.$message.error(res.msg)
          }
        })
      }else{
        //没有。进行添加
        let params;
        params={
          examId:this.receivedData.id,
          paperId:this.question.paperId,
          studentId:this.user.id,
          answer:this.answer,
          isMarked:"否"
        }
        request.post("exam/examAnswer/add",params).then(res=>{
          if(res.code==='200'){
            this.findAnswers()
          }else{
            this.$message.error(res.msg)
          }
        })
      }
    },
  }
};
</script>

<style scoped>
.exam-answer-page {
  min-height: 100vh;
  background:
      radial-gradient(circle at top left, rgba(96, 165, 250, 0.14), transparent 30%),
      linear-gradient(180deg, #eef4ff 0%, #f8fbff 100%);
}

.head {
  height: auto;
  min-height: 88px;
  padding: 18px 28px;
  font-family: inherit;
  text-align: left;
  color: #1e293b;
  background: linear-gradient(135deg, #ffffff 0%, #edf4ff 100%);
  border-bottom: 1px solid rgba(226, 232, 240, 0.9);
  box-shadow: 0 16px 40px rgba(15, 23, 42, 0.06);
}

.head-row {
  min-height: 52px;
}

.head-back {
  padding: 0;
  font-size: 16px;
  font-weight: 700;
  color: #1d4ed8;
}

.head-meta {
  display: flex;
  flex-direction: column;
  gap: 6px;
}

.head-meta__label,
.head-stat__label {
  font-size: 12px;
  letter-spacing: 0.08em;
  color: #64748b;
}

.head-meta__title {
  margin: 0;
  font-size: 24px;
  font-weight: 800;
  color: #0f172a;
}

.head-stat {
  display: flex;
  flex-direction: column;
  gap: 8px;
  padding: 12px 16px;
  border-radius: 18px;
  background: rgba(255, 255, 255, 0.78);
  border: 1px solid rgba(219, 234, 254, 0.9);
}

.head-stat--highlight {
  background: linear-gradient(135deg, rgba(219, 234, 254, 0.72), rgba(255, 255, 255, 0.92));
}

.head-stat__value {
  font-size: 18px;
  color: #0f172a;
}

.main {
  min-height: calc(100vh - 80px);
  height: auto;
  padding: 22px 24px 28px;
  background: transparent;
}

.main-grid {
  align-items: stretch;
}

.sidebar-col,
.content-col {
  display: flex;
}

.sidebar-col {
  flex-direction: column;
}

.message,
.shows,
.number,
.questionBody,
.questionBottom {
  border: 1px solid rgba(226, 232, 240, 0.9);
  border-radius: 26px;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0.98), rgba(248, 250, 252, 0.96));
  box-shadow: 0 24px 52px rgba(15, 23, 42, 0.08);
}

.message {
  margin: 0 0 18px;
  padding: 22px;
}

.shows {
  margin: 0 0 18px;
  padding: 18px 22px;
}

.number {
  margin: 0;
  padding: 18px 22px;
  flex: 1;
  min-height: calc(100vh - 360px);
  overflow-y: auto;
  background: linear-gradient(180deg, #ffffff 0%, #f8fafc 100%);
}

.number::-webkit-scrollbar {
  width: 10px;
}

.number::-webkit-scrollbar-thumb {
  background: rgba(148, 163, 184, 0.45);
  border-radius: 999px;
}

.number::-webkit-scrollbar-track {
  background: transparent;
}

.section-card__header {
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
  gap: 12px;
  margin-bottom: 18px;
}

.section-card__header--compact {
  margin-bottom: 16px;
}

.section-card__header h3 {
  margin: 0;
  font-size: 22px;
  font-weight: 800;
  color: #0f172a;
}

.section-card__header span {
  font-size: 13px;
  line-height: 1.6;
  color: #64748b;
}

.info-grid {
  display: grid;
  grid-template-columns: repeat(2, minmax(0, 1fr));
  gap: 14px;
}

.info-item {
  display: flex;
  flex-direction: column;
  gap: 8px;
  padding: 16px 18px;
  border-radius: 18px;
  background: linear-gradient(180deg, #f8fbff 0%, #f1f5f9 100%);
  border: 1px solid #e2e8f0;
}

.info-item__label {
  font-size: 12px;
  color: #64748b;
}

.info-item__value {
  font-size: 16px;
  color: #0f172a;
}

.status-legend {
  display: grid;
  grid-template-columns: repeat(4, minmax(0, 1fr));
  gap: 12px;
}

.status-item {
  text-align: center;
  font-size: 13px;
  font-weight: 600;
  color: #475569;
}

.titleQuestion {
  margin-bottom: 12px;
  font-size: 15px;
  font-weight: 700;
  color: #0f172a;
}

.number-section + .number-section {
  margin-top: 24px;
}

.questions {
  display: flex;
  flex-direction: column;
  width: 100%;
  height: auto;
  min-height: calc(100vh - 124px);
  overflow: visible;
  background: transparent;
}

.numberQuestion {
  text-align: center;
}

.questionBody {
  margin: 0 0 18px;
  padding: 22px;
  flex: 1;
  min-height: calc(100vh - 280px);
  background: linear-gradient(180deg, #ffffff 0%, #f8fafc 100%);
}

.questionBottom {
  margin: 0;
  padding: 14px 22px;
  height: auto;
}

.nowQ,
.beforeQ,
.afterQ,
.setQ,
.nowQQ,
.beforeQQ,
.afterQQ,
.setQQ {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 44px;
  height: 44px;
  margin: 0 auto 10px;
  border-radius: 14px;
  font-size: 15px;
  font-weight: 700;
  color: #fff;
  padding-top: 0;
}

.nowQ,
.nowQQ {
  background: linear-gradient(135deg, #2563eb 0%, #1d4ed8 100%);
}

.beforeQ,
.beforeQQ {
  background: linear-gradient(135deg, #94a3b8 0%, #64748b 100%);
}

.afterQ,
.afterQQ {
  background: linear-gradient(135deg, #0ea5e9 0%, #0284c7 100%);
}

.setQ,
.setQQ {
  background: linear-gradient(135deg, #f97316 0%, #ea580c 100%);
}

.nowQQ,
.beforeQQ,
.afterQQ,
.setQQ {
  cursor: pointer;
  margin-bottom: 12px;
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.nowQQ:hover,
.beforeQQ:hover,
.afterQQ:hover,
.setQQ:hover {
  transform: translateY(-2px);
  box-shadow: 0 12px 28px rgba(37, 99, 235, 0.2);
}

.question-topbar {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 18px;
  margin-bottom: 18px;
}

.question-index {
  min-width: 56px;
  height: 56px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 18px;
  background: linear-gradient(135deg, #eff6ff 0%, #dbeafe 100%);
  color: #1d4ed8;
  font-size: 24px;
  font-weight: 800;
}

.question-meta {
  display: flex;
  align-items: center;
  justify-content: flex-end;
  flex-wrap: wrap;
  gap: 10px;
}

.question-badge {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  padding: 8px 14px;
  border-radius: 999px;
  background: #e0f2fe;
  color: #0369a1;
  font-size: 13px;
  font-weight: 700;
}

.question-badge--ghost {
  background: #f1f5f9;
  color: #475569;
}

.question-badge--mark {
  background: #fff7ed;
  color: #c2410c;
}

.question-stem {
  padding: 0 0 20px;
  border-bottom: 1px solid #e2e8f0;
}

.answer-panel {
  padding-top: 22px;
}

.option-list {
  display: grid;
  gap: 12px;
}

.option-item {
  display: flex;
  align-items: center;
  gap: 14px;
  padding: 16px 18px;
  border-radius: 18px;
  background: linear-gradient(180deg, #f8fbff 0%, #f1f5f9 100%);
  border: 1px solid #e2e8f0;
  color: #334155;
  line-height: 1.7;
}

.option-key {
  width: 32px;
  height: 32px;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  border-radius: 10px;
  background: #dbeafe;
  color: #1d4ed8;
}

.answer-divider {
  height: 1px;
  margin: 22px 0;
  background: linear-gradient(90deg, transparent 0%, #dbe4ee 12%, #dbe4ee 88%, transparent 100%);
}

.answer-toolbar,
.fill-answer-row,
.judge-answer-row,
.editor-answer-row {
  display: flex;
  flex-direction: column;
  gap: 18px;
}

.answer-summary {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 12px;
  padding: 14px 16px;
  border-radius: 16px;
  background: linear-gradient(180deg, #eff6ff 0%, #f8fafc 100%);
  border: 1px solid #dbeafe;
}

.answer-summary span {
  font-size: 13px;
  color: #64748b;
}

.answer-summary strong {
  font-size: 14px;
  color: #0f172a;
}

.choice-group {
  display: flex;
  flex-wrap: wrap;
  gap: 12px 18px;
}

.judge-group {
  display: flex;
  align-items: center;
  gap: 28px;
  padding: 6px 2px;
}

.editor-shell {
  min-height: 400px;
}

.code-workspace {
  display: grid;
  align-items: stretch;
  grid-template-columns: minmax(0, 1fr) 320px;
  gap: 18px;
}

.code-editor-shell,
.code-result-card {
  display: flex;
  flex-direction: column;
  gap: 14px;
}

.code-editor-frame {
  flex: 1;
  min-height: 50vh;
  padding: 14px;
  border-radius: 22px;
  background: linear-gradient(180deg, #111827 0%, #0f172a 100%);
  box-shadow: inset 0 0 0 1px rgba(148, 163, 184, 0.14);
}

.code-editor {
  width: 100%;
  height: 100%;
  border-radius: 16px;
  overflow: hidden;
}

.code-result-card {
  min-height: 50vh;
  padding: 18px;
  border-radius: 22px;
  background: linear-gradient(180deg, #f8fbff 0%, #eef4ff 100%);
  border: 1px solid #dbeafe;
}

.code-result-card__head {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.code-result-card__label {
  display: block;
  margin-bottom: 6px;
  font-size: 12px;
  color: #64748b;
}

.code-result-card__title {
  display: block;
  font-size: 18px;
  color: #0f172a;
}

.code-result-card__status {
  display: inline-flex;
  align-self: flex-start;
  padding: 6px 12px;
  border-radius: 999px;
  background: #eff6ff;
  color: #1d4ed8;
  font-size: 13px;
  font-weight: 700;
}

.code-run {
  width: 100%;
  height: 44px;
  border-radius: 14px;
  border: 1px solid #cbd5e1;
  background: linear-gradient(180deg, #f8fafc 0%, #e2e8f0 100%);
  color: #0f172a;
  font-weight: 700;
}

.submit-answer {
  align-self: flex-end;
  min-width: 136px;
  height: 44px;
  border-radius: 14px;
  font-size: 15px;
  font-weight: 700;
}

.submit-answer--full {
  align-self: stretch;
  margin-top: auto;
}

.question-actions {
  min-height: 72px;
}

.question-actions__left {
  text-align: left;
}

.question-actions__center {
  text-align: center;
}

.question-actions__right {
  text-align: right;
}

.question-nav-btn,
.question-mark-btn {
  min-width: 146px;
  height: 46px;
  font-size: 16px;
  font-weight: 700;
}

.question-mark-btn--active {
  background: linear-gradient(135deg, #f97316 0%, #ea580c 100%);
  border-color: #ea580c;
}

:deep(.el-card__body) {
  padding: 0;
}

:deep(.el-button) {
  border-radius: 12px;
  font-weight: 600;
}

:deep(.el-button--primary) {
  background: linear-gradient(135deg, #2563eb 0%, #1d4ed8 100%);
  border-color: #1d4ed8;
  box-shadow: 0 10px 22px rgba(37, 99, 235, 0.2);
}

:deep(.el-button--primary.is-plain) {
  color: #1d4ed8;
  background: #eff6ff;
  border-color: #bfdbfe;
  box-shadow: none;
}

:deep(.el-checkbox__label),
:deep(.el-radio__label) {
  color: #334155;
  font-size: 15px;
  font-weight: 600;
}

:deep(.el-input__inner),
:deep(.el-textarea__inner) {
  border-radius: 14px;
  border-color: #dbe4ee;
  font-size: 15px;
}

:deep(.w-e-text-container) {
  border: 1px solid #dbe4ee;
  border-radius: 18px;
  overflow: hidden;
  background: #fff;
  width: 100%;
  height: 50vh;
}

:deep(.w-e-toolbar) {
  padding: 8px 10px;
  border-bottom: 1px solid #e2e8f0 !important;
  background: linear-gradient(135deg, #eff6ff 0%, #f8fafc 100%);
}

:deep(.w-e-text) {
  padding: 10px 12px;
}

:deep(.monaco-editor),
:deep(.monaco-editor .margin),
:deep(.monaco-editor-background) {
  background: #0f172a !important;
}

:deep(.monaco-editor .current-line) {
  border-color: rgba(59, 130, 246, 0.35) !important;
}

:deep(.monaco-editor .view-lines) {
  color: #e2e8f0 !important;
}

:deep(.monaco-editor .selected-text) {
  background-color: rgba(59, 130, 246, 0.22) !important;
}

:deep(.monaco-editor .line-numbers) {
  color: rgba(148, 163, 184, 0.72) !important;
}

@media (max-width: 1440px) {
  .head-meta__title {
    font-size: 22px;
  }

  .code-workspace {
    grid-template-columns: minmax(0, 1fr);
  }

  .code-editor-frame {
    height: 44vh;
  }
}

</style>
