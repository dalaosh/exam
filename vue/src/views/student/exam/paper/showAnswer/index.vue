<template>
  <div>
    <div class="head">
      <el-row>
        <el-col :span="2">
          <el-button type="text" @click="end()" size="small" style="color: #009dff;font-size: 30px;margin-right: 3%">返回</el-button>
        </el-col>
        <el-col :span="8">
          {{ course.name }}考试试卷
        </el-col>
        <el-col :span="6">
          考试时长：{{ form.totalTime }}分钟
        </el-col>
        <el-col :span="8">
          <span v-if="examStatus === 'ongoing'">
            距离考试结束还有：{{ dataTime.hour }}时{{ dataTime.minute }}分{{ dataTime.second }}秒
          </span>
          <span v-else>
            考试已结束，停止答题
          </span>
        </el-col>
      </el-row>
    </div>
    <div class="main">
      <el-row>
        <el-col :span="8">
          <el-card class="box-card message">
            <el-row>
              <el-col :span="12">
                科目:{{course.name}}
              </el-col>
              <el-col :span="12">
                课程编码:{{course.number}}
              </el-col>
            </el-row>
            <el-row style="margin-top: 1vh">
              <el-col :span="12">
                考试类型:{{form.type}}
              </el-col>
              <el-col :span="12">
                出题人:{{teacher.name}}
              </el-col>
            </el-row>
          </el-card>
          <el-card class="box-card shows">
            <el-row>
              <el-col :span="6">
                <div class="nowQ">1
                </div>
                当前
              </el-col>
              <el-col :span="6">
                <div class="beforeQ">2
                </div>
                未答
              </el-col>
              <el-col :span="6">
                <div class="afterQ">3
                </div>
                已答
              </el-col>
              <el-col :span="6">
                <div class="setQ">4
                </div>
                标记
              </el-col>
            </el-row>
          </el-card>
          <el-card class="box-card number">
            <div>
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
            <div>
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
            <div>
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
            <div>
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
            <div>
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
        <el-col :span="16">
          <div class="questions">
            <el-card class="box-card questionBody">
              <div style="font-size: 24px;margin-bottom: 2vh">
                <el-row>
                  <el-col :span="2" >
                    {{question.index+1}}、
                  </el-col>
                  <el-col :span="22">
                    <div v-html="question.question" class="w-e-text"></div>
                  </el-col>
                </el-row>
              </div>
              <div v-if="this.currentQuestionType==='multi'">
                <div style="margin-bottom: 1vh">
                  <strong>A</strong> 、{{question.answerA}}
                </div>
                <div style="margin-bottom: 1vh">
                  <strong>B</strong> 、{{question.answerB}}
                </div>
                <div style="margin-bottom: 1vh">
                  <strong>C</strong> 、{{question.answerC}}
                </div>
                <div style="margin-bottom: 1vh">
                  <strong>D</strong> 、{{question.answerD}}
                </div>
                <div style="margin-top: 4vh;margin-bottom: 4vh">
                  <hr>
                </div>
                <div>
                  <el-row>
                    <strong style="color: #04fa51">你的答案：</strong>{{answer}}
                  </el-row>
                  <hr>
                </div>
                <div>
                  <el-row>
                    <div style="margin-top: 2vh">
                      <strong style="color: #044afa">正确答案：</strong>{{question.rightAnswer}}
                    </div>
                  </el-row>
                  <el-row>
                    <div style="margin-top: 2vh">
                      <strong style="color: #06b5f5">所属章节：</strong>{{question.section}}
                    </div>
                  </el-row>
                  <el-row style="margin-top: 2vh">
                    <strong style="color: #7606b6;">答案解析：</strong>
                    <div style="padding: 2vh 3% 2vh 3%">
                      <div v-html="question.analysis" class="w-e-text"></div>
                    </div>
                  </el-row>
                  <hr>
                </div>
              </div>
              <div v-if="this.currentQuestionType==='fill'">
                <div style="margin-top: 4vh;margin-bottom: 4vh">
                  <hr>
                </div>
                <div>
                  <el-row>
                    <strong style="color: #04fa51">你的答案：</strong>{{answer}}
                  </el-row>
                  <hr>
                </div>
                <div>
                  <el-row>
                    <div style="margin-top: 2vh">
                      <strong style="color: #044afa">正确答案：</strong>{{question.answer}}
                    </div>
                  </el-row>
                  <el-row>
                    <div style="margin-top: 2vh">
                      <strong style="color: #06b5f5">所属章节：</strong>{{question.section}}
                    </div>
                  </el-row>
                  <el-row style="margin-top: 2vh">
                    <strong style="color: #7606b6;">答案解析：</strong>
                    <div style="padding: 2vh 3% 2vh 3%">
                      <div v-html="question.analysis" class="w-e-text"></div>
                    </div>
                  </el-row>
                  <hr>
                </div>
              </div>
              <div v-if="this.currentQuestionType==='judge'">
                <div style="margin-top: 4vh;margin-bottom: 4vh">
                  <hr>
                </div>
                <div>
                  <el-row>
                    <strong style="color: #04fa51">你的答案：</strong>{{answer}}
                  </el-row>
                  <hr>
                </div>
                <div>
                  <el-row>
                    <div style="margin-top: 2vh">
                      <strong style="color: #044afa">正确答案：</strong>{{question.answer}}
                    </div>
                  </el-row>
                  <el-row>
                    <div style="margin-top: 2vh">
                      <strong style="color: #06b5f5">所属章节：</strong>{{question.section}}
                    </div>
                  </el-row>
                  <el-row style="margin-top: 2vh">
                    <strong style="color: #7606b6;">答案解析：</strong>
                    <div style="padding: 2vh 3% 2vh 3%">
                      <div v-html="question.analysis" class="w-e-text"></div>
                    </div>
                  </el-row>
                  <hr>
                </div>
              </div>
              <div v-if="this.currentQuestionType==='shortAns'">
                <div style="margin-top: 4vh;margin-bottom: 4vh">
                  <hr>
                </div>
                <div>
                  <el-row>
                    <strong style="color: #04fa51">你的答案：</strong>
                    <div v-html="answer" class="w-e-text"></div>
                  </el-row>
                  <hr>
                </div>
                <div>
                  <el-row>
                    <div style="margin-top: 2vh">
                      <div style="margin-top: 2vh">
                        <strong style="color: #044afa">正确答案：</strong>
                      </div>
                      <div style="padding: 2vh 3% 2vh 3%">
                        <div v-html="question.answer" class="w-e-text"></div>
                      </div>
                    </div>
                  </el-row>
                  <el-row>
                    <div style="margin-top: 2vh">
                      <strong style="color: #06b5f5">所属章节：</strong>{{question.section}}
                    </div>
                  </el-row>
                  <el-row style="margin-top: 2vh">
                    <strong style="color: #7606b6;">答案解析：</strong>
                    <div style="padding: 2vh 3% 2vh 3%">
                      <div v-html="question.analysis" class="w-e-text"></div>
                    </div>
                  </el-row>
                  <hr>
                </div>
              </div>
              <div v-if="this.currentQuestionType==='code'">
                <div style="margin-top: 4vh;margin-bottom: 4vh">
                  <hr>
                </div>
                <div>
                  <strong style="color: #04fa51">你的答案：</strong>
                  <el-row style="margin-top: 2vh">
                    <el-col :span="16">
                      <div style="background: rgba(236,229,250,0.63);width: 100%;height: 50vh;padding-left: 2%;padding-top: 2vh">
                        <monaco-editor
                            v-model="answer"
                            language="c"
                            options="32px"
                            style="height: 46vh;width: 96%"
                        ></monaco-editor>
                      </div>
                    </el-col>
                    <el-col :span="8">
                      <el-row>
                        <div style="text-align: center">
                          <el-button style="width: 50%; background-color: #dbdbec; border-color: #030303;  color: #0a0101;font-size: 18px" @click="runCpp1">运 行</el-button>
                        </div>
                      </el-row>
                      <el-row>
                        <div style="padding: 2vh 2%">
                          <el-input
                              style="font-size: 18px"
                              disabled
                              type="textarea"
                              :autosize="{ minRows: 10, maxRows: 10}"
                              placeholder="运行结果"
                              v-model="result1">
                          </el-input>
                        </div>
                      </el-row>
                    </el-col>
                  </el-row>
                  <hr>
                </div>
                <div>
                  <div style="margin-top: 2vh">
                    <strong style="color: #044afa">正确答案：</strong>
                  </div>
                  <el-row style="margin-top: 2vh">
                    <el-col :span="16">
                      <div style="background: rgba(236,229,250,0.63);width: 100%;height: 50vh;padding-left: 2%;padding-top: 2vh">
                        <monaco-editor
                            v-model="question.answer"
                            language="c"
                            options="32px"
                            style="height: 46vh;width: 96%"
                        ></monaco-editor>
                      </div>
                    </el-col>
                    <el-col :span="8">
                      <el-row>
                        <div style="text-align: center">
                          <el-button style="width: 50%; background-color: #dbdbec; border-color: #030303;  color: #0a0101;font-size: 18px" @click="runCpp2">运 行</el-button>
                        </div>
                      </el-row>
                      <el-row>
                        <div style="padding: 2vh 2%">
                          <el-input
                              style="font-size: 18px"
                              disabled
                              type="textarea"
                              :autosize="{ minRows: 10, maxRows: 10}"
                              placeholder="运行结果"
                              v-model="result2">
                          </el-input>
                        </div>
                      </el-row>
                    </el-col>
                  </el-row>
                  <hr>
                </div>
                <div>
                  <el-row>
                    <div style="margin-top: 2vh">
                      <strong style="color: #06b5f5">所属章节：</strong>{{question.section}}
                    </div>
                  </el-row>
                  <el-row style="margin-top: 2vh">
                    <strong style="color: #7606b6;">答案解析：</strong>
                    <div style="padding: 2vh 3% 2vh 3%">
                      <div v-html="question.analysis" class="w-e-text"></div>
                    </div>
                  </el-row>
                  <hr>
                </div>
              </div>
            </el-card>
            <el-card class="box-card questionBottom">
              <el-row>
                <el-col :span="12" style="text-align: center">
                  <el-button type="primary" round style="width: 50%;margin-top:3vh;font-size: 24px;" @click="prevQuestion">上 一 题</el-button>
                </el-col>
                <el-col :span="12" style="text-align: center">
                  <el-button type="primary" round style="width: 50%;margin-top:3vh;font-size: 24px;" @click="nextQuestion">下 一 题</el-button>
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
      timer: null,
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
      result1:"",
      result2:""
    };
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
    if (this.timer) {
      clearInterval(this.timer);
    }
  },
  methods: {
    // 获取当前时间
    getTime() {
      this.timer = setInterval(() => {
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

        this.timer = setInterval(() => {
          let now = new Date();
          let diff = examEndDate.getTime() - now.getTime();

          if (diff > 0) {
            // 考试正在进行中
            // 考试正在进行中
            this.end();
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
            this.examStatus = 'ended';
            this.dataTime = {
              year: 0,
              month: 0,
              day: 0,
              hour: 0,
              minute: 0,
              second: 0
            };
            clearInterval(this.timer);
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
    runCpp1() {
      this.form = {
        m: this.answer,
        t: "c"
      }
      request.post('/exam/code/cpp', this.form).then(res => {
        this.result1 = res.data
      })
    },
    runCpp2() {
      this.form = {
        m: this.question.answer,
        t: "c"
      }
      request.post('/exam/code/cpp', this.form).then(res => {
        this.result2 = res.data
      })
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
        } else if(answer.answer!=="" && answer.answer!==null ) {
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
          break;
        case 'fill':
          this.question = question.questionFill;
          break;
        case 'judge':
          this.question = question.questionJudge;
          break;
        case 'shortAns':
          this.question = question.questionShortAns;
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
  }
};
</script>

<style scoped>
.head {
  width: 100%;
  height: 10vh;
  background: linear-gradient(to bottom right, #8ef8f8, #d6c7fa);
  font-family: 'STXingkai', '华文行楷', cursive;
  font-size: 32px;
  text-align: center;
  padding-top: 3vh;
}
.main{
  width: 100%;
  height: 90vh;
  background: #f0f3fd;
}
.message{
  margin: 2vh 2% 2vh 2%;
  width: 96%;
  font-size: 20px;
}
.shows{
  margin: 2vh 2% 2vh 2%;
  width: 96%;
  font-size: 20px;
  text-align: center;
}
.nowQ{
  background: #036df5;
  border-radius: 50%;
  margin-left: 27%;
  height: 6vh;
  width: 46%;
  padding-top: 1vh;
}
.beforeQ{
  background: #0aef2f;
  border-radius: 50%;
  margin-left: 27%;
  height: 6vh;
  width: 46%;
  padding-top: 1vh;
}
.afterQ{
  background: #c0f3e3;
  border-radius: 50%;
  margin-left: 27%;
  height: 6vh;
  width: 46%;
  padding-top: 1vh;
}
.setQ{
  background: #f51e3f;
  border-radius: 50%;
  margin-left: 27%;
  height: 6vh;
  width: 46%;
  padding-top: 1vh;
}

.nowQQ{
  background: #036df5;
  border-radius: 50%;
  margin-left: 20%;
  height: 6vh;
  width: 60%;
  padding-top: 1vh;
  margin-bottom: 1vh;
}
.beforeQQ{
  background: #0aef2f;
  border-radius: 50%;
  margin-left: 20%;
  height: 6vh;
  width: 60%;
  padding-top: 1vh;
  margin-bottom: 1vh;
}
.afterQQ{
  background: #c0f3e3;
  border-radius: 50%;
  margin-left: 20%;
  height: 6vh;
  width: 60%;
  padding-top: 1vh;
  margin-bottom: 1vh;
}
.setQQ{
  background: #f51e3f;
  border-radius: 50%;
  margin-left: 20%;
  height: 6vh;
  width: 60%;
  padding-top: 1vh;
  margin-bottom: 1vh;
}
.number {
  margin: 2vh 2% 0 2%;
  width: 96%;
  font-size: 20px;
  height: 56vh;
  overflow-y: scroll;
}
.number::-webkit-scrollbar {
  width: 0;
}
.titleQuestion{
  font-size: 24px;
  margin-bottom: 2vh;
}
.numberQuestion{
  text-align: center;
}
.questions {
  width: 100%;
  height: 90vh;
  overflow-y: scroll;
  background: #c8deef;
}
.questions::-webkit-scrollbar {
  width: 0;
}
.questionBody{
  margin: 2vh 2% 2vh 2%;
  width: 96%;
  font-size: 20px;
  height: 70vh;
  overflow-y: scroll;
}
.questionBody::-webkit-scrollbar {
  width: 0;
}
.questionBottom{
  margin: 2vh 2% 0 2%;
  width: 96%;
  font-size: 20px;
  height: 15vh;
}

</style>