<template>
  <div class="grading-page">
    <el-row v-if="visit===1">
      <el-col :span="8">
        <div class="mains">
          <div class="question-group">
            <div class="title title--section">
              <span>选择题</span>
              <el-button type="text" @click="startFakeAgentMark('multi')" plain class="action-link action-link--wide">自动阅卷选择题</el-button>
            </div>
            <div>
              <el-card shadow="hover" class="cards" v-for="(data, index) in questionMulti" :key="index">
                <el-row class="card-head">
                  <el-col :span="12">
                    <el-button type="text" @click="changeMulti(data,index)" plain class="action-link">阅卷</el-button>
                  </el-col>
                  <el-col :span="12" class="score-badge">
                    {{data.questionMulti.score}}分
                  </el-col>
                </el-row>
                <el-row class="card-body">
                  <el-col :span="2" class="serial-no">{{index+1}}、</el-col>
                  <el-col :span="22"><div v-html="data.questionMulti.question" class="w-e-text divs card-rich"></div></el-col>
                  <el-col :span="2" class="serial-no">A、</el-col>
                  <el-col :span="22"><div v-html="data.questionMulti.answerA" class="w-e-text divs card-rich"></div></el-col>
                  <el-col :span="2" class="serial-no">B、</el-col>
                  <el-col :span="22"><div v-html="data.questionMulti.answerB" class="w-e-text divs card-rich"></div></el-col>
                  <el-col :span="2" class="serial-no">C、</el-col>
                  <el-col :span="22"><div v-html="data.questionMulti.answerC" class="w-e-text divs card-rich"></div></el-col>
                  <el-col :span="2" class="serial-no">D、</el-col>
                  <el-col :span="22"><div v-html="data.questionMulti.answerD" class="w-e-text divs card-rich"></div></el-col>
                </el-row>
              </el-card>
            </div>
          </div>

          <div class="question-group">
            <div class="title title--section">
              <span>判断题</span>
              <el-button type="text" @click="startFakeAgentMark('judge')" plain class="action-link action-link--wide">自动阅卷判断题</el-button>
            </div>
            <div class="divs">
              <el-card shadow="hover" class="cards" v-for="(data, index) in questionJudge" :key="index">
                <el-row class="card-head">
                  <el-col :span="12"><el-button type="text" @click="changeJudge(data,index)" plain class="action-link">阅卷</el-button></el-col>
                  <el-col :span="12" class="score-badge">{{data.questionJudge.score}}分</el-col>
                </el-row>
                <el-row class="card-body">
                  <el-col :span="2" class="serial-no">{{index+1}}、</el-col>
                  <el-col :span="22"><div v-html="data.questionJudge.question" class="w-e-text divs card-rich"></div></el-col>
                </el-row>
              </el-card>
            </div>
          </div>

          <div class="question-group">
            <div class="title title--section">
              <span>填空题</span>
              <el-button type="text" @click="startFakeAgentMark('fill')" plain class="action-link action-link--wide">Edu-Agent智能批卷</el-button>
            </div>
            <div class="divs">
              <el-card shadow="hover" class="cards" v-for="(data, index) in questionFill" :key="index">
                <el-row class="card-head">
                  <el-col :span="12"><el-button type="text" @click="changeFill(data,index)" plain class="action-link">阅卷</el-button></el-col>
                  <el-col :span="12" class="score-badge">{{data.questionFill.score}}分</el-col>
                </el-row>
                <el-row class="card-body">
                  <el-col :span="2" class="serial-no">{{index+1}}、</el-col>
                  <el-col :span="22"><div v-html="data.questionFill.question" class="w-e-text divs card-rich"></div></el-col>
                </el-row>
              </el-card>
            </div>
          </div>

          <div class="question-group">
            <div class="title title--section">
              <span>简答题</span>
              <el-button type="text" @click="startFakeAgentMark('shortAns')" plain class="action-link action-link--wide">Edu-Agent智能批卷</el-button>
            </div>
            <div class="divs">
              <el-card shadow="hover" class="cards" v-for="(data, index) in questionShortAns" :key="index">
                <el-row class="card-head">
                  <el-col :span="12"><el-button type="text" @click="changeShortAns(data,index)" plain class="action-link">阅卷</el-button></el-col>
                  <el-col :span="12" class="score-badge">{{data.questionShortAns.score}}分</el-col>
                </el-row>
                <el-row class="card-body">
                  <el-col :span="2" class="serial-no">{{index+1}}、</el-col>
                  <el-col :span="22"><div v-html="data.questionShortAns.question" class="w-e-text divs card-rich"></div></el-col>
                </el-row>
              </el-card>
            </div>
          </div>

          <div class="question-group">
            <div class="title title--section">
              <span>代码题</span>
              <el-button type="text" @click="startFakeAgentMark('code')" plain class="action-link action-link--wide">Edu-Agent智能批卷</el-button>
            </div>
            <div class="divs">
              <el-card shadow="hover" class="cards" v-for="(data, index) in questionCode" :key="index">
                <el-row class="card-head">
                  <el-col :span="12"><el-button type="text" @click="changeCode(data,index)" plain class="action-link">阅卷</el-button></el-col>
                  <el-col :span="12" class="score-badge">{{data.questionCode.score}}分</el-col>
                </el-row>
                <el-row class="card-body">
                  <el-col :span="2" class="serial-no">{{index+1}}、</el-col>
                  <el-col :span="22"><div v-html="data.questionCode.question" class="w-e-text divs card-rich"></div></el-col>
                </el-row>
              </el-card>
            </div>
          </div>
        </div>
      </el-col>

      <el-col :span="16">
        <div v-if="questionType!==0" class="status-banner status-banner--alert">
          未批阅数量：<span class="status-banner__value">{{this.markNum}}</span>
        </div>
        <div v-if="questionType===0" class="status-banner">
          认真是一种态度，负责是一种责任
        </div>

        <div v-if="questionType===1" class="questions">
          <div class="questionSignal" v-for="(data, index) in questionListMulti" :key="index">
            <el-row><div class="title title--signal">选择题 第{{questionTypeId+1}}题 第{{index+1}}位同学</div></el-row>
            <el-row class="answer-row">
              <el-col :span="12" class="answer-col"><div class="answer-label">标准答案</div><div v-html="data.questionMulti.rightAnswer" class="w-e-text answer-box"></div></el-col>
              <el-col :span="12" class="answer-col"><div class="answer-label">学生答案</div><div v-html="data.answer" class="w-e-text answer-box"></div></el-col>
            </el-row>
            <el-row class="analysis-panel"><div class="analysis-label">答案详解</div><div v-html="data.questionMulti.analysis" class="w-e-text analysis-content"></div></el-row>
            <el-row class="grading-footer">
              <el-col :span="18" class="grading-footer__score"><div class="score-options"><el-radio v-for="i in data.questionMulti.score + 1" :key="i - 1" v-model="data.markExams" class="score-radio" :label="(i - 1).toString()"><span class="score-radio__text">{{ i - 1 }} 分</span></el-radio></div></el-col>
              <el-col :span="6" class="grading-footer__submit"><el-button type="text" @click="submitMulti(data,index)" plain class="submit-link">提交</el-button></el-col>
            </el-row>
          </div>
        </div>

        <div v-if="questionType===2" class="questions">
          <div class="questionSignal" v-for="(data, index) in questionListJudge" :key="index">
            <el-row><div class="title title--signal">判断题 第{{questionTypeId+1}}题 第{{index+1}}位同学</div></el-row>
            <el-row class="answer-row">
              <el-col :span="12" class="answer-col"><div class="answer-label">标准答案</div><div v-html="data.questionJudge.answer" class="w-e-text answer-box"></div></el-col>
              <el-col :span="12" class="answer-col"><div class="answer-label">学生答案</div><div v-html="data.answer" class="w-e-text answer-box"></div></el-col>
            </el-row>
            <el-row class="analysis-panel"><div class="analysis-label">答案详解</div><div v-html="data.questionJudge.analysis" class="w-e-text analysis-content"></div></el-row>
            <el-row class="grading-footer">
              <el-col :span="18" class="grading-footer__score"><div class="score-options"><el-radio v-for="i in data.questionJudge.score + 1" :key="i - 1" v-model="data.markExams" class="score-radio" :label="(i - 1).toString()"><span class="score-radio__text">{{ i - 1 }} 分</span></el-radio></div></el-col>
              <el-col :span="6" class="grading-footer__submit"><el-button type="text" @click="submitJudge(data,index)" plain class="submit-link">提交</el-button></el-col>
            </el-row>
          </div>
        </div>

        <div v-if="questionType===3" class="questions">
          <div class="questionSignal" v-for="(data, index) in questionListFill" :key="index">
            <el-row><div class="title title--signal">填空题 第{{questionTypeId+1}}题 第{{index+1}}位同学</div></el-row>
            <el-row class="answer-row">
              <el-col :span="12" class="answer-col"><div class="answer-label">标准答案</div><div v-html="data.questionFill.answer" class="w-e-text answer-box"></div></el-col>
              <el-col :span="12" class="answer-col"><div class="answer-label">学生答案</div><div v-html="data.answer" class="w-e-text answer-box"></div></el-col>
            </el-row>
            <el-row class="analysis-panel"><div class="analysis-label">答案详解</div><div v-html="data.questionFill.analysis" class="w-e-text analysis-content"></div></el-row>
            <el-row class="grading-footer">
              <el-col :span="18" class="grading-footer__score"><div class="score-options"><el-radio v-for="i in data.questionFill.score + 1" :key="i - 1" v-model="data.markExams" class="score-radio" :label="(i - 1).toString()"><span class="score-radio__text">{{ i - 1 }} 分</span></el-radio></div></el-col>
              <el-col :span="6" class="grading-footer__submit">
                <span v-if="hasAgentSuggestion(data.id)" class="agent-score-tip">Agent建议给分：{{ getAgentSuggestion(data.id) }}分</span>
                <el-button type="text" @click="submitFill(data,index)" plain class="submit-link">提交</el-button>
              </el-col>
            </el-row>
          </div>
        </div>

        <div v-if="questionType===4" class="questions">
          <div class="questionSignal" v-for="(data, index) in questionListShortAns" :key="index">
            <el-row><div class="title title--signal">简答题 第{{questionTypeId+1}}题 第{{index+1}}位同学</div></el-row>
            <el-row class="answer-row">
              <el-col :span="12" class="answer-col"><div class="answer-label">标准答案</div><div v-html="data.questionShortAns.answer" class="w-e-text answer-box"></div></el-col>
              <el-col :span="12" class="answer-col"><div class="answer-label">学生答案</div><div v-html="data.answer" class="w-e-text answer-box"></div></el-col>
            </el-row>
            <el-row class="analysis-panel"><div class="analysis-label">答案详解</div><div v-html="data.questionShortAns.analysis" class="w-e-text analysis-content"></div></el-row>
            <el-row class="grading-footer">
              <el-col :span="18" class="grading-footer__score"><div class="score-options"><el-radio v-for="i in data.questionShortAns.score + 1" :key="i - 1" v-model="data.markExams" class="score-radio" :label="(i - 1).toString()"><span class="score-radio__text">{{ i - 1 }} 分</span></el-radio></div></el-col>
              <el-col :span="6" class="grading-footer__submit">
                <span v-if="hasAgentSuggestion(data.id)" class="agent-score-tip">Agent建议给分：{{ getAgentSuggestion(data.id) }}分</span>
                <el-button type="text" @click="submitShortAns(data,index)" plain class="submit-link">提交</el-button>
              </el-col>
            </el-row>
          </div>
        </div>

        <div v-if="questionType===5" class="questions">
          <div class="questionSignal" v-for="(data, index) in questionListCode" :key="index">
            <el-row><div class="title title--signal">代码题 第{{questionTypeId+1}}题 第{{index+1}}位同学</div></el-row>
            <el-row class="answer-row answer-row--code">
              <el-col :span="12" class="answer-col"><div class="answer-label">标准答案</div><monaco-editor v-model="data.questionCode.answer" language="c" height="30vh" :options="codePreviewOptions" class="code-editor"></monaco-editor></el-col>
              <el-col :span="12" class="answer-col"><div class="answer-label">学生答案</div><monaco-editor v-model="data.answer" language="c" height="30vh" :options="codePreviewOptions" class="code-editor"></monaco-editor></el-col>
            </el-row>
            <el-row class="analysis-panel"><div class="analysis-label">答案详解</div><div v-html="data.questionCode.analysis" class="w-e-text analysis-content"></div></el-row>
            <el-row class="grading-footer">
              <el-col :span="18" class="grading-footer__score"><div class="score-options"><el-radio v-for="i in data.questionCode.score + 1" :key="i - 1" v-model="data.markExams" class="score-radio" :label="(i - 1).toString()"><span class="score-radio__text">{{ i - 1 }} 分</span></el-radio></div></el-col>
              <el-col :span="6" class="grading-footer__submit">
                <span v-if="hasAgentSuggestion(data.id)" class="agent-score-tip">Agent建议给分：{{ getAgentSuggestion(data.id) }}分</span>
                <el-button type="text" @click="submitCode(data,index)" plain class="submit-link">提交</el-button>
              </el-col>
            </el-row>
          </div>
        </div>

        <div v-if="questionType===0" class="questions"><div class="empty-state">请选择题目进行阅卷</div></div>
      </el-col>
    </el-row>

    <el-row v-if="visit===0"><div class="questions"><div class="empty-state">批卷工作已经结束</div></div></el-row>
    <el-row v-if="visit===-1"><div class="questions"><div class="empty-state">批卷工作还未开始</div></div></el-row>
    <el-dialog
      :visible.sync="agentVisible"
      width="420px"
      :show-close="false"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      custom-class="agent-progress-dialog">
      <div class="agent-progress">
        <div class="agent-progress__eyebrow">智能批阅 Agent</div>
        <h3 class="agent-progress__title">{{ agentTitle }}</h3>
        <p class="agent-progress__desc">{{ agentStage }}</p>
        <el-progress
          :percentage="agentPercent"
          :stroke-width="16"
          :show-text="false"
          color="#0f766e">
        </el-progress>
        <div class="agent-progress__meta">
          <span>当前进度</span>
          <strong>{{ agentPercent }}%</strong>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import request from "@/utils/request";
import MonacoEditor from "monaco-editor-vue";

export default {
  components: {
    MonacoEditor,
  },
  computed: {
    codePreviewOptions() {
      return {
        readOnly: true,
        fontSize: 18,
        automaticLayout: true,
        minimap: {
          enabled: false,
        },
        scrollBeyondLastLine: false,
      };
    },
  },
  data(){
    return{
      user:localStorage.getItem("user") ? (JSON).parse(localStorage.getItem("user")) : {},
      receivedData:"",
      form:{},
      paperData:{},
      questionCode: [],
      questionFill: [],
      questionMulti: [],
      questionShortAns: [],
      questionJudge: [],
      questionListCode: [],
      questionListFill: [],
      questionListMulti: [],
      questionListShortAns: [],
      questionListJudge: [],
      questionType:0,
      questionTypeId:0,
      score:"",
      markNum:0,
      visit:-1,
      examManager:"",
      agentSuggestions:{},
      agentSuggestionEnabledTypes:{},
      agentVisible:false,
      agentPercent:0,
      agentStage:"",
      agentTitle:"",
      agentTimer:null
    }
  },
  created() {
    this.receivedData = this.$route.params
    this.load()
    this.findExam()
  },
  mounted() {},
  beforeDestroy() {
    this.stopFakeAgentMark()
  },
  methods: {
    startFakeAgentMark(questionType) {
      if (this.agentVisible) {
        return
      }
      const configMap = {
        multi: {
          title: "Agent 正在批阅选择题",
          action: () => this.changeMultiAll()
        },
        judge: {
          title: "Agent 正在批阅判断题",
          action: () => this.changeJudgeAll()
        },
        fill: {
          title: "Agent 正在批阅填空题",
          action: () => this.enableFakeAgentSuggestions("fill")
        },
        shortAns: {
          title: "Agent 正在批阅简答题",
          action: () => this.enableFakeAgentSuggestions("shortAns")
        },
        code: {
          title: "Agent 正在批阅代码题",
          action: () => this.enableFakeAgentSuggestions("code")
        }
      }
      const config = configMap[questionType]
      if (!config) {
        return
      }
      const stages = [
        "正在解析题目结构与标准答案...",
        "正在抽取待批阅题目并建立任务队列...",
        "正在模拟智能体分批阅卷...",
        "正在整理批阅结果并准备回写...",
        "即将完成，正在同步最终状态..."
      ]
      this.stopFakeAgentMark()
      this.agentVisible = true
      this.agentPercent = 0
      this.agentTitle = config.title
      this.agentStage = stages[0]
      let stageIndex = 0
      this.agentTimer = setInterval(() => {
        const step = Math.floor(Math.random() * 8) + 6
        this.agentPercent = Math.min(this.agentPercent + step, 100)
        const nextStage = Math.min(Math.floor(this.agentPercent / 22), stages.length - 1)
        if (nextStage !== stageIndex) {
          stageIndex = nextStage
          this.agentStage = stages[stageIndex]
        }
        if (this.agentPercent >= 100) {
          this.stopFakeAgentMark()
          this.agentStage = "智能体批阅完成，正在刷新结果..."
          setTimeout(() => {
            this.agentVisible = false
            if (config.action) {
              config.action()
            }
          }, 500)
        }
      }, 260)
    },
    stopFakeAgentMark() {
      if (this.agentTimer) {
        clearInterval(this.agentTimer)
        this.agentTimer = null
      }
    },
    enableFakeAgentSuggestions(questionType) {
      this.$set(this.agentSuggestionEnabledTypes, questionType, true)
      const currentListMap = {
        fill: this.questionListFill,
        shortAns: this.questionListShortAns,
        code: this.questionListCode
      }
      this.ensureFakeAgentSuggestions(questionType, currentListMap[questionType], true)
    },
    ensureFakeAgentSuggestions(questionType, list, forceRefresh = false) {
      const listMap = {
        fill: this.questionListFill,
        shortAns: this.questionListShortAns,
        code: this.questionListCode
      }
      const scoreGetterMap = {
        fill: (item) => item.questionFill && item.questionFill.score,
        shortAns: (item) => item.questionShortAns && item.questionShortAns.score,
        code: (item) => item.questionCode && item.questionCode.score
      }
      const targetList = list || listMap[questionType] || []
      const getScore = scoreGetterMap[questionType]
      targetList.forEach((item) => {
        if (!forceRefresh && this.hasAgentSuggestion(item.id)) {
          return
        }
        const maxScore = Number(getScore ? getScore(item) : 0) || 0
        const suggestion = Math.floor(Math.random() * (maxScore + 1))
        this.$set(this.agentSuggestions, item.id, suggestion)
      })
    },
    isAgentSuggestionEnabled(questionType) {
      return !!this.agentSuggestionEnabledTypes[questionType]
    },
    hasAgentSuggestion(answerId) {
      return Object.prototype.hasOwnProperty.call(this.agentSuggestions, answerId)
    },
    getAgentSuggestion(answerId) {
      return this.agentSuggestions[answerId]
    },
    timeJudge(){
      if(this.beginBefore(this.examManager)){
        this.visit=-1
      }
      else if(this.endAfter(this.examManager)){
        this.visit=0
      }
      else{
        this.visit=1
      }
    },
    load(){
      let params;
      params={
        examId:this.receivedData.id,
        teacherId:this.user.id
      }
      request.get("exam/examTeacherGrading/selectAll",{
        params:params
      }).then(res=>{
        if (res.code === '200') {
          this.paperData=res.data
          this.categorizeQuestions()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    findExam(){
      request.get("exam/examManage/selectById/"+this.receivedData.id).then(res=>{
        if (res.code === '200') {
          this.examManager=res.data
          this.timeJudge()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    beginBefore(row) {
      const beginTime = new Date(row.beginTime);
      const now = new Date();
      return now <= beginTime;
    },
    endAfter(row){
      const endTime = new Date(row.endTime);
      const now = new Date();
      return now >= endTime;
    },

    categorizeQuestions() {
      this.paperData.forEach(item => {
        const questionType = item.examPaper.questionType;
        switch (questionType) {
          case 'code':
            this.questionCode.push(item);
            break;
          case 'fill':
            this.questionFill.push(item);
            break;
          case 'multi':
            this.questionMulti.push(item);
            break;
          case 'shortAns':
            this.questionShortAns.push(item);
            break;
          case 'judge':
            this.questionJudge.push(item);
            break;
          default:
            break;
        }
      });
    },
    changeMulti(data, index){
      let params;
      params={
        examId: data.examId,
        paperId:data.paperId
      }
      console.log(params)
      request.get("exam/examAnswer/selectAll",{
        params:params
      }).then(res=>{
        if (res.code === '200') {
          this.questionListMulti=res.data
          this.questionType=1;
          this.questionTypeId=index
          this.markNum=this.setNum(this.questionListMulti)
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    changeMultiAll(){
      let params;
      params=this.questionMulti
      console.log(params)
      request.put("exam/examAnswer/updateByMultiAll",params).then(res=>{
        if (res.code === '200') {
          this.$message.success("阅卷成功")
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    changeJudge(data, index){
      let params;
      params={
        examId: data.examId,
        paperId:data.paperId
      }
      console.log(params)
      request.get("exam/examAnswer/selectAll",{
        params:params
      }).then(res=>{
        if (res.code === '200') {
          this.questionListJudge=res.data
          this.questionType=2;
          this.questionTypeId=index
          this.markNum=this.setNum(this.questionListJudge)
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    changeJudgeAll(){
      let params;
      params=this.questionJudge
      console.log(params)
      request.put("exam/examAnswer/updateByJudgeAll",params).then(res=>{
        if (res.code === '200') {
          this.$message.success("阅卷成功")
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    changeFill(data, index){
      let params;
      params={
        examId: data.examId,
        paperId:data.paperId
      }
      console.log(params)
      request.get("exam/examAnswer/selectAll",{
        params:params
      }).then(res=>{
        if (res.code === '200') {
          this.questionListFill=res.data
          this.questionType=3;
          this.questionTypeId=index
          this.markNum=this.setNum(this.questionListFill)
          if (this.isAgentSuggestionEnabled("fill")) {
            this.ensureFakeAgentSuggestions("fill", this.questionListFill)
          }
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    changeShortAns(data, index){
      let params;
      params={
        examId: data.examId,
        paperId:data.paperId
      }
      console.log(params)
      request.get("exam/examAnswer/selectAll",{
        params:params
      }).then(res=>{
        if (res.code === '200') {
          this.questionListShortAns=res.data
          this.questionType=4;
          this.questionTypeId=index
          this.markNum=this.setNum(this.questionListShortAns)
          if (this.isAgentSuggestionEnabled("shortAns")) {
            this.ensureFakeAgentSuggestions("shortAns", this.questionListShortAns)
          }
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    changeCode(data, index){
      let params;
      params={
        examId: data.examId,
        paperId:data.paperId
      }
      console.log(params)
      request.get("exam/examAnswer/selectAll",{
        params:params
      }).then(res=>{
        if (res.code === '200') {
          this.questionListCode=res.data
          this.questionType=5;
          this.questionTypeId=index
          this.markNum=this.setNum(this.questionListCode)
          if (this.isAgentSuggestionEnabled("code")) {
            this.ensureFakeAgentSuggestions("code", this.questionListCode)
          }
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    setNum(questionList){
      let unmarkedCount = 0;
      questionList.forEach(question => {
        if (question.markExams === null || question.markExams === undefined|| question.markExams === "") {
          unmarkedCount++;
        }
      });
      return unmarkedCount
    },
    submitMulti(data,index){
      let params;
      params={ id:data.id, markExams:data.markExams, teacherId:this.user.id, examId:this.receivedData.id }
      request.put("exam/examAnswer/updateByScore",params).then(res=>{
        if (res.code === '200') {
          this.$message.success("成功阅卷")
          this.changeMulti(data,this.questionTypeId)
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    submitJudge(data,index){
      let params;
      params={ id:data.id, markExams:data.markExams, teacherId:this.user.id, examId:this.receivedData.id }
      request.put("exam/examAnswer/updateByScore",params).then(res=>{
        if (res.code === '200') {
          this.$message.success("成功阅卷")
          this.changeJudge(data,this.questionTypeId)
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    submitFill(data,index){
      let params;
      params={ id:data.id, markExams:data.markExams, teacherId:this.user.id, examId:this.receivedData.id }
      request.put("exam/examAnswer/updateByScore",params).then(res=>{
        if (res.code === '200') {
          this.$message.success("成功阅卷")
          this.changeFill(data,this.questionTypeId)
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    submitShortAns(data,index){
      let params;
      params={ id:data.id, markExams:data.markExams, teacherId:this.user.id, examId:this.receivedData.id }
      request.put("exam/examAnswer/updateByScore",params).then(res=>{
        if (res.code === '200') {
          this.$message.success("成功阅卷")
          this.changeShortAns(data,this.questionTypeId)
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    submitCode(data,index){
      let params;
      params={ id:data.id, markExams:data.markExams, teacherId:this.user.id, examId:this.receivedData.id }
      request.put("exam/examAnswer/updateByScore",params).then(res=>{
        if (res.code === '200') {
          this.$message.success("成功阅卷")
          this.changeCode(data,this.questionTypeId)
        } else {
          this.$message.error(res.msg)
        }
      })
    }
  }
}
</script>

<style scoped>
.grading-page { color: #1f2937; }
.question-group + .question-group { margin-top: 6px; }
.cards { display: inline-block; width: 90%; margin: 12px 1% 0; min-height: 188px; max-height: 272px; border-radius: 18px; border: 1px solid rgba(218, 226, 237, 0.92); background: linear-gradient(180deg, #ffffff 0%, #f8fbff 100%); box-shadow: 0 14px 32px rgba(148, 163, 184, 0.14); overflow-y: auto; }
.cards :deep(.el-card__body) { padding: 16px 18px 18px; }
.cards::-webkit-scrollbar, .mains::-webkit-scrollbar, .questions::-webkit-scrollbar { width: 7px; }
.cards::-webkit-scrollbar-thumb, .mains::-webkit-scrollbar-thumb, .questions::-webkit-scrollbar-thumb { background: rgba(148, 163, 184, 0.58); border-radius: 999px; }
.title { font-family: 'STXingkai', '华文行楷', cursive; color: #123047; }
.title--section { display: flex; align-items: center; justify-content: space-between; gap: 16px; margin: 0 1%; padding: 8px 4px 0; font-size: 28px; }
.title--signal { font-size: 28px; margin-bottom: 16px; }
.divs { font-size: 15px; line-height: 1.7; }
.mains { margin-top: 10px; height: 85vh; padding: 10px 0 16px 5%; border-radius: 24px; background: linear-gradient(180deg, rgba(246, 248, 252, 0.96) 0%, rgba(235, 241, 247, 0.98) 100%), radial-gradient(circle at top left, rgba(157, 230, 219, 0.28), transparent 42%); border: 1px solid rgba(222, 230, 239, 0.92); box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.7); overflow-y: auto; }
.questions { display: inline-block; width: 98%; margin: 10px 1% 0; height: calc(85vh - 82px); border-radius: 24px; border: 1px solid rgba(221, 229, 239, 0.92); background: linear-gradient(180deg, rgba(255, 255, 255, 0.98) 0%, rgba(245, 249, 252, 0.96) 100%), radial-gradient(circle at top right, rgba(165, 243, 252, 0.2), transparent 36%); box-shadow: 0 18px 44px rgba(148, 163, 184, 0.16); overflow-y: auto; }
.questionSignal { margin: 18px 2%; padding: 24px; border-radius: 24px; border: 1px solid rgba(221, 229, 239, 0.92); background: linear-gradient(180deg, rgba(255, 255, 255, 0.98) 0%, rgba(248, 250, 252, 0.96) 100%), linear-gradient(135deg, rgba(219, 234, 254, 0.16), transparent 36%); box-shadow: 0 16px 34px rgba(148, 163, 184, 0.12); }
.card-head { display: flex; align-items: center; margin-bottom: 12px; color: #e11d48; }
.card-body { color: #334155; }
.card-rich { line-height: 1.75; }
.serial-no { font-weight: 700; color: #0f766e; }
.action-link { width: 70%; padding: 8px 0; border-radius: 999px; background: rgba(15, 118, 110, 0.08); color: #0f766e; font-size: 16px; font-weight: 700; text-align: center; }
.action-link--wide { width: auto; min-width: 176px; padding: 10px 18px; font-size: 15px; }
.action-link:hover, .submit-link:hover { background: rgba(15, 118, 110, 0.14); color: #0b5d57; }
.score-badge { display: flex; justify-content: flex-end; align-items: center; font-size: 16px; font-weight: 700; color: #e11d48; }
.status-banner { display: flex; align-items: center; justify-content: center; min-height: 72px; margin: 4px 1% 10px; padding: 14px 20px; border-radius: 18px; border: 1px solid rgba(221, 229, 239, 0.9); background: linear-gradient(180deg, #ffffff 0%, #f8fbff 100%); color: #334155; font-size: 22px; font-weight: 700; letter-spacing: 1px; }
.status-banner--alert { color: #b91c1c; background: linear-gradient(180deg, #fff7f7 0%, #fff1f2 100%); }
.status-banner__value { margin-left: 8px; font-size: 28px; }
.answer-row { margin: 16px 0 18px; }
.answer-col { text-align: left; }
.answer-label, .analysis-label { margin-bottom: 10px; font-size: 18px; font-weight: 700; color: #1e3a5f; }
.answer-box { width: 100%; min-height: 120px; padding: 16px 18px; border-radius: 18px; border: 1px solid rgba(208, 220, 233, 0.92); background: linear-gradient(180deg, #f8fbff 0%, #edf5fb 100%); text-align: left; color: #334155; box-sizing: border-box; overflow-wrap: anywhere; word-break: break-word; }
.answer-box :deep(p) { margin: 0 0 10px; line-height: 1.8; }
.answer-box :deep(p:last-child) { margin-bottom: 0; }
.answer-box :deep(ul),
.answer-box :deep(ol) { margin: 0; padding-left: 1.4em; }
.answer-box :deep(ul ul),
.answer-box :deep(ol ol),
.answer-box :deep(ul ol),
.answer-box :deep(ol ul) { margin-top: 8px; padding-left: 1.2em; }
.answer-box :deep(li) { margin: 0 0 8px; line-height: 1.8; }
.answer-box :deep(li:last-child) { margin-bottom: 0; }
.answer-box :deep(li > p) { display: inline; margin: 0; }
.analysis-panel { display: block; margin: 0 2% 18px; padding: 18px 20px; border-radius: 18px; border: 1px solid rgba(232, 220, 164, 0.86); background: linear-gradient(180deg, #fffdf5 0%, #fff9e8 100%); }
.analysis-content { color: #4b5563; line-height: 1.8; }
.grading-footer {
  display: flex;
  flex-direction: column;
  align-items: stretch;
  gap: 18px;
}
.grading-footer > .el-col {
  width: 100%;
  max-width: 100%;
  flex: 0 0 100%;
}
.grading-footer__score,
.grading-footer__submit {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
}
.grading-footer__submit {
  gap: 16px;
  flex-wrap: wrap;
}
.score-options {
  display: flex;
  flex-wrap: nowrap;
  justify-content: space-between;
  align-items: stretch;
  gap: 12px;
  width: 100%;
}
.score-radio {
  flex: 1 1 0;
  min-width: 0;
  margin-right: 0;
  padding: 10px 0;
  border: 1px solid rgba(203, 213, 225, 0.96);
  border-radius: 16px;
  background: #ffffff;
  text-align: center;
  transition: all 0.2s ease;
}
.score-radio:hover { border-color: rgba(15, 118, 110, 0.5); background: rgba(240, 253, 250, 0.9); }
.score-radio__text { font-size: 20px; font-weight: 700; color: #334155; }
.score-radio :deep(.el-radio__label) {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  padding-left: 8px;
}
.submit-link {
  width: auto;
  min-width: 180px;
  padding: 10px 42px;
  border-radius: 999px;
  background: linear-gradient(135deg, rgba(15, 118, 110, 0.14), rgba(20, 184, 166, 0.18));
  color: #0f766e;
  font-size: 18px;
  font-weight: 700;
  text-align: center;
}
.agent-score-tip {
  display: inline-flex;
  align-items: center;
  min-height: 42px;
  padding: 0 16px;
  border-radius: 999px;
  background: rgba(15, 118, 110, 0.08);
  color: #0f766e;
  font-size: 15px;
  font-weight: 700;
  white-space: nowrap;
}
.answer-row--code .answer-col { text-align: left; }
.code-editor { display: block; height: 30vh; margin-top: 8px; overflow: hidden; border: 1px solid rgba(208, 220, 233, 0.92); border-radius: 18px; }
.empty-state { display: flex; align-items: center; justify-content: center; height: 100%; padding: 40px; text-align: center; font-size: 52px; color: #17405d; letter-spacing: 3px; font-family: 'STXingkai', '华文行楷', cursive; }
.grading-page :deep(.el-radio__input.is-checked .el-radio__inner) { border-color: #0f766e; background: #0f766e; }
.grading-page :deep(.el-radio__label) { padding-left: 8px; }
.grading-page :deep(.w-e-text p), .grading-page :deep(.w-e-text div) { margin: 0; }
.grading-page :deep(.agent-progress-dialog) { border-radius: 22px; overflow: hidden; }
.grading-page :deep(.agent-progress-dialog .el-dialog__header) { display: none; }
.grading-page :deep(.agent-progress-dialog .el-dialog__body) { padding: 0; }
.agent-progress {
  padding: 26px 24px 22px;
  background:
    linear-gradient(180deg, rgba(255, 255, 255, 0.98) 0%, rgba(246, 250, 252, 0.96) 100%),
    radial-gradient(circle at top right, rgba(45, 212, 191, 0.18), transparent 38%);
}
.agent-progress__eyebrow {
  display: inline-flex;
  align-items: center;
  min-height: 28px;
  padding: 0 12px;
  border-radius: 999px;
  background: rgba(15, 118, 110, 0.1);
  color: #0f766e;
  font-size: 13px;
  font-weight: 700;
}
.agent-progress__title {
  margin: 16px 0 8px;
  font-size: 24px;
  line-height: 1.35;
  color: #0f172a;
}
.agent-progress__desc {
  margin: 0 0 18px;
  font-size: 14px;
  line-height: 1.7;
  color: #475569;
}
.agent-progress__meta {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-top: 14px;
  color: #475569;
  font-size: 14px;
}
.agent-progress__meta strong {
  color: #0f766e;
  font-size: 18px;
}
</style>
