<template>
  <!-- 原有的模板代码保持不变 -->
  <div>
    <el-row v-if="visit===1">
      <el-col :span="8">
        <div class="mains">
          <div>
            <div class="title">
              选择题
              <el-button type="text" @click="changeMultiAll()" plain style="width: 60%;font-size: 20px;text-align: center">自动阅卷选择题</el-button>
            </div>
            <div>
              <el-card shadow="hover" class="cards" v-for="(data, index) in questionMulti" :key="index">
                <el-row style="text-align: right;color: #bd0a4f">
                  <el-col :span="12">
                    <el-button type="text" @click="changeMulti(data,index)" plain style="width: 70%;font-size: 20px">阅卷</el-button>
                  </el-col>
                  <el-col style="font-size: 20px" :span="12">
                    {{data.questionMulti.score}}分
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="2">
                    {{index+1}}、
                  </el-col>
                  <el-col :span="22">
                    <div v-html="data.questionMulti.question" class="w-e-text divs"></div>
                  </el-col>
                  <el-col :span="2" >
                    A、
                  </el-col>
                  <el-col :span="22">
                    <div v-html="data.questionMulti.answerA" class="w-e-text divs"></div>
                  </el-col>
                  <el-col :span="2" >
                    B、
                  </el-col>
                  <el-col :span="22">
                    <div v-html="data.questionMulti.answerB" class="w-e-text divs"></div>
                  </el-col>
                  <el-col :span="2" >
                    C、
                  </el-col>
                  <el-col :span="22">
                    <div v-html="data.questionMulti.answerC" class="w-e-text divs"></div>
                  </el-col>
                  <el-col :span="2" >
                    D、
                  </el-col>
                  <el-col :span="22">
                    <div v-html="data.questionMulti.answerD" class="w-e-text divs"></div>
                  </el-col>
                </el-row>
              </el-card>
            </div>
          </div>
          <div>
            <div class="title">
              判断题
              <el-button type="text" @click="changeJudgeAll()" plain style="width: 60%;font-size: 20px;text-align: center">自动阅卷判断题</el-button>
            </div>
            <div class="divs">
              <el-card shadow="hover" class="cards"  v-for="(data, index) in questionJudge" :key="index" >
                <el-row style="text-align: right;color: #bd0a4f">
                  <el-col :span="12">
                    <el-button type="text" @click="changeJudge(data,index)" plain style="width: 70%;font-size: 20px">阅卷</el-button>
                  </el-col>
                  <el-col style="font-size: 20px" :span="12">
                    {{data.questionJudge.score}}分
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="2" >
                    {{index+1}}、
                  </el-col>
                  <el-col :span="22">
                    <div v-html="data.questionJudge.question" class="w-e-text divs"></div>
                  </el-col>
                </el-row>
              </el-card>
            </div>
          </div>
          <div>
            <div class="title">
              填空题
            </div>
            <div class="divs">
              <el-card shadow="hover" class="cards"  v-for="(data, index) in questionFill" :key="index" >
                <el-row style="text-align: right;color: #bd0a4f">
                  <el-col :span="12">
                    <el-button type="text" @click="changeFill(data,index)" plain style="width: 70%;font-size: 20px">阅卷</el-button>
                  </el-col>
                  <el-col style="font-size: 20px" :span="12">
                    {{data.questionFill.score}}分
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="2" >
                    {{index+1}}、
                  </el-col>
                  <el-col :span="22">
                    <div v-html="data.questionFill.question" class="w-e-text divs"></div>
                  </el-col>
                </el-row>
              </el-card>
            </div>
          </div>
          <div>
            <div class="title">
              简答题
            </div>
            <div class="divs">
              <el-card shadow="hover" class="cards"  v-for="(data, index) in questionShortAns" :key="index" >
                <el-row style="text-align: right;color: #bd0a4f">
                  <el-col :span="12">
                    <el-button type="text" @click="changeShortAns(data,index)" plain style="width: 70%;font-size: 20px">阅卷</el-button>
                  </el-col>
                  <el-col style="font-size: 20px" :span="12">
                    {{data.questionShortAns.score}}分
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="2" >
                    {{index+1}}、
                  </el-col>
                  <el-col :span="22">
                    <div v-html="data.questionShortAns.question" class="w-e-text divs"></div>
                  </el-col>
                </el-row>
              </el-card>
            </div>
          </div>
          <div>
            <div class="title">
              代码题
            </div>
            <div class="divs">
              <el-card shadow="hover" class="cards"  v-for="(data, index) in questionCode" :key="index" >
                <el-row style="text-align: right;color: #bd0a4f">
                  <el-col :span="12">
                    <el-button type="text" @click="changeCode(data,index)" plain style="width: 70%;font-size: 20px">阅卷</el-button>
                  </el-col>
                  <el-col style="font-size: 20px" :span="12">
                    {{data.questionCode.score}}分
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="2" >
                    {{index+1}}、
                  </el-col>
                  <el-col :span="22">
                    <div v-html="data.questionCode.question" class="w-e-text divs"></div>
                  </el-col>
                </el-row>
              </el-card>
            </div>
          </div>
        </div>
      </el-col>
      <el-col :span="16">
        <div v-if="questionType!==0" style="height: 6vh;padding-top: 2vh;text-align: center;font-size: 26px">
          未批阅数量：{{this.markNum}}
        </div>
        <div v-if="questionType===0" style="height: 6vh;padding-top: 2vh;text-align: center;font-size: 26px">
          认真是一种态度，负责是一种责任
        </div>
        <div v-if="questionType===1" class="questions">
          <div class="questionSignal" v-for="(data, index) in questionListMulti" :key="index">
            <el-row>
              <div class="title">选择题 第{{questionTypeId+1}}题 第{{index+1}}位同学</div>
            </el-row>
            <el-row style="margin-top: 2vh;margin-bottom: 2vh;font-size: 26px;text-align: center;">
              <el-col :span="12">
                标准答案：
                <div style="width: 90%;background: #dde3f3;margin-left: 5%" v-html="data.questionMulti.rightAnswer" class="w-e-text"></div>
              </el-col>
              <el-col :span="12">
                学生答案：
                <div style="width: 90%;background: #dde3f3;margin-left: 5%" v-html="data.answer" class="w-e-text"></div>
              </el-col>
            </el-row>
            <el-row style="margin: 2vh 2% 2vh 2%;background: #fcfbef;font-size: 26px">
              答案详解：
              <div style="width: 90%;margin-left: 5%" v-html="data.questionMulti.analysis" class="w-e-text"></div>
            </el-row>
            <el-row>
              <el-col :span="18">
                <template>
                  <div style="text-align: center">
                    <!-- 动态生成单选框 -->
                    <el-radio
                        v-for="i in data.questionMulti.score + 1"
                        :key="i - 1"
                        v-model="data.markExams"
                        style="text-align: center; margin-top: 2vh; width: 20%; "
                        :label="(i - 1).toString()"
                    >
                      <a style="font-size: 32px;">{{ i - 1 }} 分</a>
                    </el-radio>
                  </div>
                </template>
              </el-col>
              <el-col :span="6">
                <el-button type="text" @click="submitMulti(data,index)" plain style="width: 70%;font-size: 20px">提交</el-button>
              </el-col>
            </el-row>
          </div>
        </div>
        <div v-if="questionType===2" class="questions">
          <div class="questionSignal" v-for="(data, index) in questionListJudge" :key="index">
            <el-row>
              <div class="title">判断题 第{{questionTypeId+1}}题 第{{index+1}}位同学</div>
            </el-row>
            <el-row style="margin-top: 2vh;margin-bottom: 2vh;font-size: 26px;text-align: center;">
              <el-col :span="12">
                标准答案：
                <div style="width: 90%;background: #dde3f3;margin-left: 5%" v-html="data.questionJudge.answer" class="w-e-text"></div>
              </el-col>
              <el-col :span="12">
                学生答案：
                <div style="width: 90%;background: #dde3f3;margin-left: 5%" v-html="data.answer" class="w-e-text"></div>
              </el-col>
            </el-row>
            <el-row style="margin: 2vh 2% 2vh 2%;background: #fcfbef;font-size: 26px">
              答案详解：
              <div style="width: 90%;margin-left: 5%" v-html="data.questionJudge.analysis" class="w-e-text"></div>
            </el-row>
            <el-row>
              <el-col :span="18">
                <template>
                  <div style="text-align: center">
                    <!-- 动态生成单选框 -->
                    <el-radio
                        v-for="i in data.questionJudge.score + 1"
                        :key="i - 1"
                        v-model="data.markExams"
                        style="text-align: center; margin-top: 2vh; width: 20%; "
                        :label="(i - 1).toString()"
                    >
                      <a style="font-size: 32px;">{{ i - 1 }} 分</a>
                    </el-radio>
                  </div>
                </template>
              </el-col>
              <el-col :span="6">
                <el-button type="text" @click="submitJudge(data,index)" plain style="width: 70%;font-size: 20px">提交</el-button>
              </el-col>
            </el-row>
          </div>
        </div>
        <div v-if="questionType===3" class="questions">
          <div class="questionSignal" v-for="(data, index) in questionListFill" :key="index">
            <el-row>
              <div class="title">填空题 第{{questionTypeId+1}}题 第{{index+1}}位同学</div>
            </el-row>
            <el-row style="margin-top: 2vh;margin-bottom: 2vh;font-size: 26px;text-align: center;">
              <el-col :span="12">
                标准答案：
                <div style="width: 90%;background: #dde3f3;margin-left: 5%" v-html="data.questionFill.answer" class="w-e-text"></div>
              </el-col>
              <el-col :span="12">
                学生答案：
                <div style="width: 90%;background: #dde3f3;margin-left: 5%" v-html="data.answer" class="w-e-text"></div>
              </el-col>
            </el-row>
            <el-row style="margin: 2vh 2% 2vh 2%;background: #fcfbef;font-size: 26px">
              答案详解：
              <div style="width: 90%;margin-left: 5%" v-html="data.questionFill.analysis" class="w-e-text"></div>
            </el-row>
            <el-row>
              <el-col :span="18">
                <template>
                  <div style="text-align: center">
                    <!-- 动态生成单选框 -->
                    <el-radio
                        v-for="i in data.questionFill.score + 1"
                        :key="i - 1"
                        v-model="data.markExams"
                        style="text-align: center; margin-top: 2vh; width: 20%; "
                        :label="(i - 1).toString()"
                    >
                      <a style="font-size: 32px;">{{ i - 1 }} 分</a>
                    </el-radio>
                  </div>
                </template>
              </el-col>
              <el-col :span="6">
                <el-button type="text" @click="submitFill(data,index)" plain style="width: 70%;font-size: 20px">提交</el-button>
              </el-col>
            </el-row>
          </div>
        </div>
        <div v-if="questionType===4" class="questions">
          <div class="questionSignal" v-for="(data, index) in questionListShortAns" :key="index">
            <el-row>
              <div class="title">填空题 第{{questionTypeId+1}}题 第{{index+1}}位同学</div>
            </el-row>
            <el-row style="margin-top: 2vh;margin-bottom: 2vh;font-size: 26px;text-align: center;">
              <el-col :span="12">
                标准答案：
                <div style="width: 90%;background: #dde3f3;margin-left: 5%" v-html="data.questionShortAns.answer" class="w-e-text"></div>
              </el-col>
              <el-col :span="12">
                学生答案：
                <div style="width: 90%;background: #dde3f3;margin-left: 5%" v-html="data.answer" class="w-e-text"></div>
              </el-col>
            </el-row>
            <el-row style="margin: 2vh 2% 2vh 2%;background: #fcfbef;font-size: 26px">
              答案详解：
              <div style="width: 90%;margin-left: 5%" v-html="data.questionShortAns.analysis" class="w-e-text"></div>
            </el-row>
            <el-row>
              <el-col :span="18">
                <template>
                  <div style="text-align: center">
                    <!-- 动态生成单选框 -->
                    <el-radio
                        v-for="i in data.questionShortAns.score + 1"
                        :key="i - 1"
                        v-model="data.markExams"
                        style="text-align: center; margin-top: 2vh; width: 20%; "
                        :label="(i - 1).toString()"
                    >
                      <a style="font-size: 32px;">{{ i - 1 }} 分</a>
                    </el-radio>
                  </div>
                </template>
              </el-col>
              <el-col :span="6">
                <el-button type="text" @click="submitShortAns(data,index)" plain style="width: 70%;font-size: 20px">提交</el-button>
              </el-col>
            </el-row>
          </div>
        </div>
        <div v-if="questionType===5" class="questions">
          <div class="questionSignal" v-for="(data, index) in questionListCode" :key="index">
            <el-row>
              <div class="title">填空题 第{{questionTypeId+1}}题 第{{index+1}}位同学</div>
            </el-row>
            <el-row style="margin-top: 2vh;margin-bottom: 2vh;font-size: 26px;text-align: center;">
              <el-col :span="12">
                标准答案：
                <monaco-editor
                    v-model="data.questionCode.answer"
                    language="c"
                    :options="{ readOnly: true, fontSize: '18px' }"
                    style="margin-top:2vh;height: 30vh;text-align: left"
                ></monaco-editor>
              </el-col>
              <el-col :span="12">
                学生答案：
                <monaco-editor
                    v-model="data.answer"
                    language="c"
                    :options="{ readOnly: true, fontSize: '18px' }"
                    style="margin-top:2vh;height: 30vh;text-align: left"
                ></monaco-editor>
              </el-col>
            </el-row>
            <el-row style="margin: 2vh 2% 2vh 2%;background: #fcfbef;font-size: 26px">
              答案详解：
              <div style="width: 90%;margin-left: 5%" v-html="data.questionCode.analysis" class="w-e-text"></div>
            </el-row>
            <el-row>
              <el-col :span="18">
                <template>
                  <div style="text-align: center">
                    <!-- 动态生成单选框 -->
                    <el-radio
                        v-for="i in data.questionCode.score + 1"
                        :key="i - 1"
                        v-model="data.markExams"
                        style="text-align: center; margin-top: 2vh; width: 20%; "
                        :label="(i - 1).toString()"
                    >
                      <a style="font-size: 32px;">{{ i - 1 }} 分</a>
                    </el-radio>
                  </div>
                </template>
              </el-col>
              <el-col :span="6">
                <el-button type="text" @click="submitCode(data,index)" plain style="width: 70%;font-size: 20px">提交</el-button>
              </el-col>
            </el-row>
          </div>
        </div>
        <div v-if="questionType===0" class="questions">
          <div style="margin-top: 36vh;text-align: center;font-size: 8vh;font-family: 'STXingkai', '华文行楷', cursive;">
            请选择题目进行阅卷
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row v-if="visit===0">
      <div class="questions">
        <div style="margin-top: 36vh;text-align: center;font-size: 8vh;font-family: 'STXingkai', '华文行楷', cursive;">
          批卷工作已经结束
        </div>
      </div>
    </el-row>
    <el-row v-if="visit===-1">
      <div class="questions">
        <div style="margin-top: 36vh;text-align: center;font-size: 8vh;font-family: 'STXingkai', '华文行楷', cursive;">
          批卷工作还未开始
        </div>
      </div>
    </el-row>
  </div>
</template>

<script>
import request from "@/utils/request";
import MonacoEditor from "monaco-editor-vue";
export default {
  components: {
    MonacoEditor,
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
      examManager:""
    }
  },
  created() {
    this.receivedData = this.$route.params
    this.load()
    this.findExam()
  },
  mounted() {

  },
  methods: {
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
    // 判断在开始时间之前
    beginBefore(row) {
      const beginTime = new Date(row.beginTime);
      const now = new Date();
      return now <= beginTime;
    },
    // 判断在结束时间之后
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
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    setNum(questionList){
      // 初始化未打分总次数
      let unmarkedCount = 0;
      // 遍历问题列表
      questionList.forEach(question => {
        // 检查 markExams 是否为 null 或 undefined，如果是则表示未打分
        if (question.markExams === null || question.markExams === undefined|| question.markExams === "") {
          unmarkedCount++;
        }
      });
      return unmarkedCount
    },
    submitMulti(data,index){
      let params;
      params={
        id:data.id,
        markExams:data.markExams,
        teacherId:this.user.id,
        examId:this.receivedData.id
      }
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
      params={
        id:data.id,
        markExams:data.markExams,
        teacherId:this.user.id,
        examId:this.receivedData.id
      }
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
      params={
        id:data.id,
        markExams:data.markExams,
        teacherId:this.user.id,
        examId:this.receivedData.id
      }
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
      params={
        id:data.id,
        markExams:data.markExams,
        teacherId:this.user.id,
        examId:this.receivedData.id
      }
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
      params={
        id:data.id,
        markExams:data.markExams,
        teacherId:this.user.id,
        examId:this.receivedData.id
      }
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
.cards {
  white-space: unset;
  display: inline-block;
  width: 90%;
  margin: 1vh 1% 0 1%;
  height: 20vh;
  border-radius: 10px;
  background-color: #f1fdfd;
  overflow-y: scroll;
}

.cards::-webkit-scrollbar {
  width: 0;
}

.title {
  font-family: 'STXingkai', '华文行楷', cursive;
  font-size: 32px;
  margin: 0 1% 0 1%;
  padding-top: 1vh;
}

.divs {
  font-size: 18px;
}
.mains{
  margin-top: 1vh;
  height: 85vh;
  padding-left: 5%;
  background: #e2dee3;
  overflow-y: scroll;
}
.mains::-webkit-scrollbar {
  width: 0;
}
.questions{
  white-space: unset;
  display: inline-block;
  width: 98%;
  margin: 1vh 1% 0 1%;
  height: 79vh;
  border-radius: 10px;
  background-color: #adf5f5;
  overflow-y: scroll;
}
.questions::-webkit-scrollbar {
  width: 0;
}
.questionSignal{
  margin: 2vh 2% 2vh 2%;
  background: #e8d6ef;
}
</style>
