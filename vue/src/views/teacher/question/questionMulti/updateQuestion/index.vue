<template>
  <div class="teacher-page teacher-question-editor">
    <section class="teacher-page-head">
      <div>
        <h2 class="teacher-page-head__title">修改选择题</h2>
      </div>
    </section>

    <div class="teacher-panel teacher-panel--padded">
      <div class="teacher-question-editor__meta-grid">
        <div class="teacher-question-editor__field">
          <span class="teacher-question-editor__label">所属课程</span>
          <el-select v-model="table.courseId" clearable filterable placeholder="请选择">
            <el-option
              v-for="item in course"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
        </div>
        <div class="teacher-question-editor__field">
          <span class="teacher-question-editor__label">分值</span>
          <el-input clearable v-model="table.score" placeholder="请输入分值"></el-input>
        </div>
        <div class="teacher-question-editor__field">
          <span class="teacher-question-editor__label">难度等级</span>
          <el-rate
            v-model="table.level"
            :colors="['#fdea5a', '#32ef17', '#8a2be2']"
            :void-color="'#070000'">
          </el-rate>
        </div>
        <div class="teacher-question-editor__field">
          <span class="teacher-question-editor__label">所属章节</span>
          <el-input clearable v-model="table.section" placeholder="请输入章节"></el-input>
        </div>
      </div>

      <div class="teacher-question-editor__two-col">
        <div class="teacher-panel teacher-panel--padded">
          <div class="teacher-panel__head">
            <div>
              <h3 class="teacher-panel__title">问题</h3>
            </div>
          </div>
          <div class="w-e-text-container" id="editorA" style="width: 100%; height: 320px"></div>
        </div>

        <div class="teacher-panel teacher-panel--padded">
          <div class="teacher-panel__head">
            <div>
              <h3 class="teacher-panel__title">解析</h3>
            </div>
          </div>
          <div class="w-e-text-container" id="editorB" style="width: 100%; height: 320px"></div>
        </div>
      </div>

      <div class="teacher-question-editor__two-col">
        <div class="teacher-panel teacher-panel--padded">
          <div class="teacher-panel__head">
            <div>
              <h3 class="teacher-panel__title">选项配置</h3>
            </div>
          </div>
          <div class="teacher-question-editor__stack">
            <div class="teacher-question-editor__field">
              <span class="teacher-question-editor__label">选项 A</span>
              <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 3}" placeholder="请输入选项内容" v-model="table.answerA"></el-input>
            </div>
            <div class="teacher-question-editor__field">
              <span class="teacher-question-editor__label">选项 B</span>
              <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 3}" placeholder="请输入选项内容" v-model="table.answerB"></el-input>
            </div>
            <div class="teacher-question-editor__field">
              <span class="teacher-question-editor__label">选项 C</span>
              <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 3}" placeholder="请输入选项内容" v-model="table.answerC"></el-input>
            </div>
            <div class="teacher-question-editor__field">
              <span class="teacher-question-editor__label">选项 D</span>
              <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 3}" placeholder="请输入选项内容" v-model="table.answerD"></el-input>
            </div>
          </div>
        </div>

        <div class="teacher-panel teacher-panel--padded">
          <div class="teacher-panel__head">
            <div>
              <h3 class="teacher-panel__title">题型与答案</h3>
            </div>
          </div>
          <div class="teacher-question-editor__stack">
            <div class="teacher-question-editor__field">
              <span class="teacher-question-editor__label">题型</span>
              <el-select v-model="table.questionType" clearable filterable placeholder="请选择">
                <el-option
                  v-for="item in type"
                  :key="item.data"
                  :label="item.data"
                  :value="item.data">
                </el-option>
              </el-select>
            </div>
            <div class="teacher-question-editor__answer">
              <span class="teacher-question-editor__label">正确答案</span>
              <div class="teacher-question-editor__answer-value">{{ table.rightAnswer ? table.rightAnswer : "请选择答案" }}</div>
              <el-checkbox-group class="teacher-question-editor__choice-group" @change="multiChange" v-model="multiAnswer">
                <el-checkbox v-for="m in multi" :label="m" :key="m">{{ m }}</el-checkbox>
              </el-checkbox-group>
            </div>
          </div>
        </div>
      </div>

      <div class="teacher-question-editor__actions">
        <el-button type="primary" @click="submit">保存</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request"
import E from "wangeditor";
let editorA
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
let editorB
function initWangEditorB(content){ setTimeout(() => {
  if(!editorB){
    editorB=new E('#editorB')
    editorB.config.placeholder='请输入内容'
    editorB.config.uploadFileName='file'
    editorB.config.uploadImgServer='http://localhost:9998/files/wang/upload'
    editorB.create()
  }
  editorB.txt.html(content)
},0)
}
export default {
  data(){
    return{
      user:localStorage.getItem("user") ? (JSON).parse(localStorage.getItem("user")) : {},
      form:{
        number:""
      },
      table:{},
      course:{},
      type:[
        {data:"单选"},
        {data:"多选"}
      ],
      receivedData:"",
      multi:['A','B','C','D'],
      multiAnswer:[]
    }
  },
  created() {
    this.receivedData = this.$route.params;
    this.findCourse()
    this.load()
  },
  mounted() {

  },
  methods: {
    load(){
      editorA=""
      editorB=""
      request.get("/exam/questionMulti/selectById/"+this.receivedData.id).then(res=>{
        if (res.code === "200") {
          this.table = res.data
          initWangEditorA(this.table.question)
          initWangEditorB(this.table.analysis)
          this.selectQuestion()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    findCourse(){
      let params;
      params= {
        teacherId:this.user.id
      }
      request.get('/exam/course/selectAll', {
        params:params
      }).then(res => {
        if (res.code === '200') {
          this.course=res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    multiChange(){
      this.table.rightAnswer=""
      const multiAnswer = this.multiAnswer.sort();
      this.table.rightAnswer = multiAnswer.join('');
    },
    selectQuestion(){
      this.multiAnswer = this.table.rightAnswer.split('')
    },
    submit(){
      this.table.question=editorA.txt.html()
      this.table.analysis=editorB.txt.html()
      request.put("/exam/questionMulti/update", this.table).then(res => {
        if (res.code === "200") {
          this.$message.success("保存成功")
          this.form = {}
        } else {
          this.$message.error(res.msg)
        }
      })
    }
  }
}
</script>

<style scoped></style>
