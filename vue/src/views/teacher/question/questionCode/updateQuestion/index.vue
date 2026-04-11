<template>
  <div class="teacher-page teacher-question-editor teacher-code-editor-page">
    <section class="teacher-page-head">
      <div>
        <h2 class="teacher-page-head__title">编辑编程题</h2>
        <p class="teacher-page-head__desc">统一题面、解析、参考代码和运行结果布局，避免代码题编辑页容器失衡。</p>
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
            <p class="teacher-panel__desc">使用富文本编辑编程题题面。</p>
          </div>
        </div>
        <div class="w-e-text-container" id="editorA" style="width: 100%; height: 320px; z-index: 1"></div>
      </section>

      <section class="teacher-panel teacher-panel--padded">
        <div class="teacher-panel__head">
          <div>
            <h3 class="teacher-panel__title">解析</h3>
            <p class="teacher-panel__desc">说明题目意图、评分点和解题思路。</p>
          </div>
        </div>
        <div class="w-e-text-container" id="editorB" style="width: 100%; height: 320px"></div>
      </section>
    </div>

    <section class="teacher-panel teacher-panel--padded">
      <div class="teacher-panel__head">
        <div>
          <h3 class="teacher-panel__title">参考代码</h3>
          <p class="teacher-panel__desc">左侧编辑代码，右侧查看运行结果。</p>
        </div>
      </div>

      <div class="teacher-question-editor__two-col">
        <div class="teacher-question-editor__stack">
          <div class="teacher-code-editor-page__toolbar">
            <div class="teacher-code-editor-page__font">
              <label for="fontSize">字体大小</label>
              <input type="range" min="10" max="80" v-model="fontSize" id="fontSize" />
              <span>{{ fontSize }}px</span>
            </div>
            <el-button type="primary" plain @click="runCpp">运行</el-button>
          </div>
          <div class="teacher-code-editor-page__monaco">
            <monaco-editor
              v-model="table.answer"
              :language="language"
              :options="editorOptions"
              style="height: 420px;"
            ></monaco-editor>
          </div>
        </div>

        <div class="teacher-question-editor__stack">
          <div class="teacher-code-editor-page__result">
            <div class="teacher-code-editor-page__result-title">运行结果</div>
            <el-input
              disabled
              type="textarea"
              :autosize="{ minRows: 14, maxRows: 14}"
              placeholder="运行结果"
              v-model="result">
            </el-input>
          </div>
          <div class="teacher-question-editor__actions">
            <el-button type="primary" @click="submit">保存</el-button>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import MonacoEditor from "monaco-editor-vue";
import request from "@/utils/request";
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
function destroyEditors() {
  if (editorA && typeof editorA.destroy === "function") {
    editorA.destroy()
  }
  if (editorB && typeof editorB.destroy === "function") {
    editorB.destroy()
  }
  editorA = null
  editorB = null
}

export default {
  components: {
    MonacoEditor,
  },
  data() {
    return {
      user:localStorage.getItem("user") ? (JSON).parse(localStorage.getItem("user")) : "",
      code: "",
      language:"c",
      form:{},
      result:"",
      fontSize:"18",
      table:{},
      course:{},
      receivedData:""
    };
  },
  created() {
    this.receivedData = this.$route.params;
    this.findCourse()
    this.load()
  },
  beforeDestroy() {
    destroyEditors()
  },
  methods:{
    load(){
      destroyEditors()
      request.get("/exam/questionCode/selectById/"+this.receivedData.id).then(res=>{
        if (res.code === "200") {
          this.table = res.data
          initWangEditorA(this.table.question)
          initWangEditorB(this.table.analysis)
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
          this.course = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    runCpp() {
      if (this.language === null) {
        this.$message.error("请选择语言")
      }
      this.form = {
        m: this.table.answer,
        t: "c"
      }
      request.post('/exam/code/cpp', this.form).then(res => {
        this.result = res.data
      })
    },
    submit() {
      this.table.question = editorA.txt.html()
      this.table.analysis = editorB.txt.html()
      this.table.studentId = this.user.id
      this.table.type = "c"
      request.put("/exam/questionCode/update", this.table).then(res => {
        if (res.code === "200") {
          this.$message.success("修改成功")
          this.form = {}
        } else {
          this.$message.error(res.msg)
        }
      })
    }
  },
  computed: {
    editorOptions() {
      return {
        theme: "vs-dark",
        fontSize: Number(this.fontSize),
        automaticLayout: true,
        minimap: {
          enabled: false
        },
        lineNumbersMinChars: 3,
        scrollBeyondLastLine: false,
        renderLineHighlight: "all",
        padding: {
          top: 16
        }
      };
    },
  }
};
</script>

<style scoped>
.teacher-code-editor-page__toolbar {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 12px;
}

.teacher-code-editor-page__font {
  display: flex;
  align-items: center;
  gap: 10px;
  color: #475569;
  font-size: 14px;
}

.teacher-code-editor-page__monaco,
.teacher-code-editor-page__result {
  padding: 16px;
  border: 1px solid #dbe4ee;
  border-radius: 18px;
  background: linear-gradient(180deg, #f8fbff 0%, #f1f5f9 100%);
}

.teacher-code-editor-page__result-title {
  margin-bottom: 12px;
  font-size: 15px;
  font-weight: 700;
  color: #0f172a;
}

.teacher-code-editor-page__monaco {
  overflow: hidden;
  background: linear-gradient(180deg, #111827 0%, #0f172a 100%);
}

.teacher-code-editor-page__result {
  display: flex;
  flex-direction: column;
  min-height: 100%;
}

:deep(.monaco-editor),
:deep(.monaco-editor .margin),
:deep(.monaco-editor .monaco-editor-background) {
  background-color: #0f172a !important;
}

:deep(.monaco-editor .view-lines) {
  color: #e2e8f0 !important;
}

:deep(.monaco-editor .selected-text) {
  background-color: rgba(59, 130, 246, 0.22) !important;
}

:deep(.monaco-editor .current-line) {
  border-color: rgba(34, 197, 94, 0.24) !important;
}

:deep(.monaco-editor .line-numbers) {
  color: rgba(148, 163, 184, 0.75) !important;
}

:deep(.monaco-editor .cursor) {
  background-color: #f8fafc !important;
  border-color: #f8fafc !important;
}
</style>
