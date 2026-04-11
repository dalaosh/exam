<template>
  <div class="teacher-page teacher-question-editor">
    <section class="teacher-page-head">
      <div>
        <h2 class="teacher-page-head__title">编辑简答题</h2>
        <p class="teacher-page-head__desc">统一问题、答案、解析三块编辑器和底部保存区间距，避免容器互相挤压。</p>
      </div>
    </section>

    <section class="teacher-panel teacher-panel--padded">
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
    </section>

    <div class="teacher-question-editor__two-col">
      <section class="teacher-panel teacher-panel--padded">
        <div class="teacher-panel__head">
          <div>
            <h3 class="teacher-panel__title">问题</h3>
            <p class="teacher-panel__desc">编辑题面内容。</p>
          </div>
        </div>
        <div class="w-e-text-container" id="editorA" style="width: 100%; height: 320px; z-index: 1"></div>
      </section>

      <section class="teacher-panel teacher-panel--padded">
        <div class="teacher-panel__head">
          <div>
            <h3 class="teacher-panel__title">答案</h3>
            <p class="teacher-panel__desc">编写标准答案内容。</p>
          </div>
        </div>
        <div class="w-e-text-container" id="editorC" style="width: 100%; height: 320px"></div>
      </section>
    </div>

    <section class="teacher-panel teacher-panel--padded">
      <div class="teacher-panel__head">
        <div>
          <h3 class="teacher-panel__title">解析</h3>
          <p class="teacher-panel__desc">说明评分依据和关键知识点。</p>
        </div>
      </div>
      <div class="w-e-text-container" id="editorB" style="width: 100%; height: 320px"></div>
    </section>

    <div class="teacher-question-editor__actions">
      <el-button type="primary" @click="submit">保存</el-button>
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
let editorC
function initWangEditorC(content){ setTimeout(() => {
  if(!editorC){
    editorC=new E('#editorC')
    editorC.config.placeholder='请输入内容'
    editorC.config.uploadFileName='file'
    editorC.config.uploadImgServer='http://localhost:9998/files/wang/upload'
    editorC.create()
  }
  editorC.txt.html(content)
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
      receivedData:"",
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
      editorC=""
      request.get("/exam/questionShortAns/selectById/"+this.receivedData.id).then(res=>{
        if (res.code === "200") {
          this.table = res.data
          initWangEditorA(this.table.question)
          initWangEditorB(this.table.analysis)
          initWangEditorC(this.table.answer)
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
    submit(){
      this.table.question=editorA.txt.html()
      this.table.analysis=editorB.txt.html()
      this.table.answer=editorC.txt.html()
      request.put("/exam/questionShortAns/update", this.table).then(res => {
        if (res.code === "200") {
          this.$message.success("添加成功")
          this.form = {}
        } else {
          this.$message.error(res.msg)
        }
      })
    }
  }
}
</script>

<style scoped>
</style>
