<template>
  <div class="teacher-page teacher-question-editor">
    <section class="teacher-page-head">
      <div>
        <h2 class="teacher-page-head__title">创建判断题</h2>
        <p class="teacher-page-head__desc">统一课程、分值、难度、题面、答案和解析布局，避免题库编辑页内部挤压。</p>
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
      <div class="teacher-question-editor__stack">
        <section class="teacher-panel teacher-panel--padded">
          <div class="teacher-panel__head">
            <div>
              <h3 class="teacher-panel__title">问题</h3>
              <p class="teacher-panel__desc">使用富文本编辑判断题题面。</p>
            </div>
          </div>
          <div class="w-e-text-container" id="editorA" style="width: 100%; height: 320px; z-index: 1"></div>
        </section>

        <section class="teacher-panel teacher-panel--padded">
          <div class="teacher-panel__head">
            <div>
              <h3 class="teacher-panel__title">答案</h3>
              <p class="teacher-panel__desc">当前答案：{{ table.answer ? table.answer : "请选择" }}</p>
            </div>
          </div>
          <div class="teacher-question-editor__choice-group">
            <el-radio v-model="table.answer" label="T">T</el-radio>
            <el-radio v-model="table.answer" label="F">F</el-radio>
          </div>
        </section>
      </div>

      <div class="teacher-question-editor__stack">
        <section class="teacher-panel teacher-panel--padded">
          <div class="teacher-panel__head">
            <div>
              <h3 class="teacher-panel__title">解析</h3>
              <p class="teacher-panel__desc">说明判断依据和知识点。</p>
            </div>
          </div>
          <div class="w-e-text-container" id="editorB" style="width: 100%; height: 320px"></div>
        </section>

        <div class="teacher-question-editor__actions">
          <el-button type="primary" @click="submit">保存</el-button>
        </div>
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
      course:{}
    }
  },
  created() {
    this.findCourse()
    this.load()
  },
  mounted() {

  },
  methods: {
    load(){
      editorA=""
      initWangEditorA("")
      editorB=""
      initWangEditorB("")
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
      request.post("/exam/questionJudge/add", this.table).then(res => {
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
