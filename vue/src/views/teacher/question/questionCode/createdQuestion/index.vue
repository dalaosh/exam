<template>
  <div class="teacher-page teacher-question-editor teacher-code-editor-page">
    <section class="teacher-page-head">
      <div>
        <h2 class="teacher-page-head__title">创建编程题</h2>
      </div>
      <div class="teacher-page-head__meta">
        <el-tag class="teacher-tag" type="info">在线编译</el-tag>
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
              :value="item.id"
            />
          </el-select>
        </div>
        <div class="teacher-question-editor__field">
          <span class="teacher-question-editor__label">分值</span>
          <el-input v-model="table.score" clearable placeholder="请输入分值" />
        </div>
        <div class="teacher-question-editor__field">
          <span class="teacher-question-editor__label">难度等级</span>
          <el-rate
            v-model="table.level"
            :colors="['#fdea5a', '#32ef17', '#8a2be2']"
            :void-color="'#070000'"
          />
        </div>
        <div class="teacher-question-editor__field">
          <span class="teacher-question-editor__label">所属章节</span>
          <el-input v-model="table.section" clearable placeholder="请输入章节" />
        </div>
      </div>
    </section>

    <div class="teacher-question-editor__two-col">
      <section class="teacher-panel teacher-panel--padded">
        <div class="teacher-panel__head">
          <div>
            <h3 class="teacher-panel__title">问题</h3>
          </div>
        </div>
        <div class="w-e-text-container" id="editorA" style="width: 100%; height: 320px; z-index: 1"></div>
      </section>

      <section class="teacher-panel teacher-panel--padded">
        <div class="teacher-panel__head">
          <div>
            <h3 class="teacher-panel__title">解析</h3>
          </div>
        </div>
        <div class="w-e-text-container" id="editorB" style="width: 100%; height: 320px"></div>
      </section>
    </div>

    <section class="teacher-panel teacher-panel--padded teacher-code-editor-card">
      <div class="teacher-panel__head teacher-code-editor-card__head">
        <div>
          <h3 class="teacher-panel__title">参考代码</h3>
        </div>
      </div>

      <section class="teacher-code-grid">
        <div class="teacher-code-stack">
          <article class="teacher-code-panel teacher-code-panel--editor">
            <div class="teacher-code-toolbar">
              <div class="teacher-code-toolbar__group">
                <span class="teacher-code-toolbar__label">语言选择</span>
                <el-select
                  v-model="language"
                  size="medium"
                  class="teacher-code-toolbar__select"
                  placeholder="请选择语言"
                >
                  <el-option
                    v-for="item in languageOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </div>

              <div class="teacher-code-toolbar__group">
                <div class="teacher-code-slider">
                  <span class="teacher-code-toolbar__label">字体大小</span>
                  <input id="fontSize" v-model="fontSize" type="range" min="10" max="80">
                  <span class="teacher-code-slider__value">{{ fontSize }}px</span>
                </div>
                <el-button type="primary" @click="runCpp">运行代码</el-button>
              </div>
            </div>

            <div class="teacher-code-editor">
              <div class="teacher-code-editor__chrome">
                <div class="teacher-code-editor__chrome-left">
                  <div class="teacher-code-editor__dots">
                    <span></span>
                    <span></span>
                    <span></span>
                  </div>
                  <div class="teacher-code-editor__tab">
                    <i class="el-icon-document"></i>
                    {{ fileName }}
                  </div>
                  <span class="teacher-code-editor__hint">Monaco Editor</span>
                </div>
                <div class="teacher-code-editor__chrome-right">
                  <span class="teacher-code-badge teacher-code-badge--accent">
                    <i class="el-icon-cpu"></i>
                    {{ selectedLanguageLabel || "请选择语言" }}
                  </span>
                  <span class="teacher-code-badge">
                    <i class="el-icon-edit-outline"></i>
                    {{ codeLineCount }} 行
                  </span>
                </div>
              </div>

              <div class="teacher-code-editor__body">
                <monaco-editor
                  v-model="table.answer"
                  :language="editorLanguage"
                  :options="editorOptions"
                  class="teacher-code-practice-editor"
                />
              </div>
            </div>
          </article>
        </div>

        <div class="teacher-code-stack">
          <article class="teacher-code-panel teacher-code-panel--result">
            <div class="teacher-code-result-head">
              <div>
                <h3 class="teacher-code-panel__title">运行结果</h3>
              </div>
              <span class="teacher-code-result-status" :class="resultStatusClass">{{ resultStatusText }}</span>
            </div>
            <div class="teacher-code-output">
              <el-input
                v-model="result"
                type="textarea"
                :disabled="true"
                :autosize="{ minRows: 12, maxRows: 12 }"
                placeholder="运行结果"
              />
            </div>
          </article>

          <article class="teacher-code-panel teacher-code-panel--meta">
            <div class="teacher-code-meta">
              <div class="teacher-code-meta__item">
                <span>当前语言</span>
                <strong>{{ selectedLanguageLabel || "未选择" }}</strong>
              </div>
              <div class="teacher-code-meta__item">
                <span>编辑状态</span>
                <strong>{{ table.answer ? "已填写参考代码" : "等待输入" }}</strong>
              </div>
            </div>
            <div class="teacher-question-editor__actions teacher-code-editor-page__actions">
              <el-button type="primary" @click="submit">保存</el-button>
            </div>
          </article>
        </div>
      </section>
    </section>
  </div>
</template>

<script>
import MonacoEditor from "monaco-editor-vue";
import request from "@/utils/request";
import E from "wangeditor";

let editorA;
function initWangEditorA(content) {
  setTimeout(() => {
    if (!editorA) {
      editorA = new E("#editorA");
      editorA.config.placeholder = "请输入内容";
      editorA.config.uploadFileName = "file";
      editorA.config.uploadImgServer = "http://localhost:9998/files/wang/upload";
      editorA.create();
    }
    editorA.txt.html(content);
  }, 0);
}

let editorB;
function initWangEditorB(content) {
  setTimeout(() => {
    if (!editorB) {
      editorB = new E("#editorB");
      editorB.config.placeholder = "请输入内容";
      editorB.config.uploadFileName = "file";
      editorB.config.uploadImgServer = "http://localhost:9998/files/wang/upload";
      editorB.create();
    }
    editorB.txt.html(content);
  }, 0);
}

function destroyEditors() {
  if (editorA && typeof editorA.destroy === "function") {
    editorA.destroy();
  }
  if (editorB && typeof editorB.destroy === "function") {
    editorB.destroy();
  }
  editorA = null;
  editorB = null;
}

export default {
  components: {
    MonacoEditor,
  },
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : "",
      language: "c",
      form: {},
      result: "",
      fontSize: "18",
      table: {
        answer: "",
      },
      course: {},
      languageOptions: [
        { value: "c", label: "C (gcc)", editorLanguage: "c", ext: "c" },
      ],
    };
  },
  created() {
    this.findCourse();
    this.load();
  },
  beforeDestroy() {
    destroyEditors();
  },
  methods: {
    load() {
      destroyEditors();
      initWangEditorA("");
      initWangEditorB("");
    },
    findCourse() {
      const params = {
        teacherId: this.user.id,
      };
      request.get("/exam/course/selectAll", {
        params,
      }).then((res) => {
        if (res.code === "200") {
          this.course = res.data;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    runCpp() {
      if (!this.language) {
        this.$message.error("请选择语言");
        return;
      }
      this.form = {
        m: this.table.answer,
        t: this.language,
      };
      request.post("/exam/code/cpp", this.form).then((res) => {
        this.result = res.data;
      });
    },
    submit() {
      this.table.question = editorA.txt.html();
      this.table.analysis = editorB.txt.html();
      this.table.studentId = this.user.id;
      this.table.type = this.language;
      request.post("/exam/questionCode/add", this.table).then((res) => {
        if (res.code === "200") {
          this.$message.success("添加成功");
          this.form = {};
        } else {
          this.$message.error(res.msg);
        }
      });
    },
  },
  computed: {
    selectedLanguageOption() {
      return this.languageOptions.find((item) => item.value === this.language) || null;
    },
    selectedLanguageLabel() {
      return this.selectedLanguageOption ? this.selectedLanguageOption.label : "";
    },
    editorLanguage() {
      return this.selectedLanguageOption ? this.selectedLanguageOption.editorLanguage : "plaintext";
    },
    fileName() {
      const ext = this.selectedLanguageOption ? this.selectedLanguageOption.ext : "txt";
      return `practice.${ext}`;
    },
    codeLineCount() {
      if (!this.table.answer) {
        return 1;
      }
      return this.table.answer.split("\n").length;
    },
    resultStatusText() {
      return this.result ? "已生成结果" : "等待运行";
    },
    resultStatusClass() {
      return this.result ? "teacher-code-badge--success" : "teacher-code-badge--warning";
    },
    editorOptions() {
      return {
        fontSize: Number(this.fontSize),
        automaticLayout: true,
        minimap: {
          enabled: false,
        },
        roundedSelection: true,
        scrollBeyondLastLine: false,
        renderLineHighlight: "all",
        padding: {
          top: 16,
        },
      };
    },
  },
};
</script>

<style scoped>
.teacher-code-editor-page {
  min-height: calc(100vh - 148px);
}

.teacher-code-editor-card {
  overflow: hidden;
}

.teacher-code-editor-card__head {
  margin-bottom: 4px;
}

.teacher-code-grid {
  display: grid;
  grid-template-columns: minmax(0, 1.45fr) minmax(320px, 0.85fr);
  gap: 12px;
  min-height: 640px;
}

.teacher-code-stack {
  min-height: 0;
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.teacher-code-panel {
  padding: 14px 16px;
  border: 1px solid rgba(219, 231, 243, 0.96);
  border-radius: 20px;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0.98), rgba(248, 250, 252, 0.98));
  min-height: 0;
}

.teacher-code-panel--editor,
.teacher-code-panel--result {
  flex: 1 1 auto;
  display: flex;
  flex-direction: column;
}

.teacher-code-panel--meta {
  flex: 0 0 auto;
}

.teacher-code-panel__title {
  margin: 0;
  font-size: 16px;
  font-weight: 700;
  color: #0f172a;
}

.teacher-code-toolbar {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 12px;
  margin-bottom: 10px;
  flex-wrap: wrap;
}

.teacher-code-toolbar__group {
  display: flex;
  align-items: center;
  gap: 10px;
  flex-wrap: wrap;
}

.teacher-code-toolbar__label {
  font-size: 13px;
  font-weight: 700;
  color: #334155;
}

.teacher-code-toolbar__select {
  width: 210px;
}

.teacher-code-slider {
  display: flex;
  align-items: center;
  gap: 10px;
  min-width: 220px;
}

.teacher-code-slider__value {
  min-width: 48px;
  font-weight: 700;
  color: #0f172a;
}

.teacher-code-editor {
  flex: 1 1 auto;
  min-height: 0;
  display: flex;
  flex-direction: column;
  border: 1px solid #dbe4ee;
  border-radius: 24px;
  overflow: hidden;
  background: #ffffff;
}

.teacher-code-editor__chrome {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 12px;
  padding: 10px 14px;
  border-bottom: 1px solid #e2e8f0;
  background: linear-gradient(180deg, #f8fbff 0%, #f1f5f9 100%);
}

.teacher-code-editor__chrome-left,
.teacher-code-editor__chrome-right {
  display: flex;
  align-items: center;
  gap: 10px;
  flex-wrap: wrap;
}

.teacher-code-editor__dots {
  display: inline-flex;
  align-items: center;
  gap: 8px;
}

.teacher-code-editor__dots span {
  width: 12px;
  height: 12px;
  border-radius: 50%;
}

.teacher-code-editor__dots span:nth-child(1) {
  background: #fb7185;
}

.teacher-code-editor__dots span:nth-child(2) {
  background: #fbbf24;
}

.teacher-code-editor__dots span:nth-child(3) {
  background: #34d399;
}

.teacher-code-editor__tab {
  display: inline-flex;
  align-items: center;
  gap: 8px;
  padding: 6px 12px;
  border-radius: 999px;
  border: 1px solid #cbd5e1;
  background: rgba(255, 255, 255, 0.92);
  color: #1e3a8a;
  font-size: 14px;
  font-weight: 700;
}

.teacher-code-editor__hint {
  font-size: 14px;
  color: #64748b;
}

.teacher-code-editor__body {
  flex: 1 1 auto;
  min-height: 0;
  background: #ffffff;
}

.teacher-code-practice-editor {
  display: block;
  width: 100%;
  height: 100%;
  min-height: 480px;
}

.teacher-code-badge {
  display: inline-flex;
  align-items: center;
  gap: 8px;
  padding: 6px 12px;
  border-radius: 999px;
  border: 1px solid #dbe4ee;
  background: rgba(255, 255, 255, 0.92);
  color: #334155;
  font-size: 13px;
  font-weight: 700;
}

.teacher-code-badge i {
  font-size: 13px;
}

.teacher-code-badge--accent {
  color: #0284c7;
  border-color: rgba(125, 211, 252, 0.75);
  background: rgba(240, 249, 255, 0.96);
}

.teacher-code-badge--success {
  color: #047857;
  border-color: rgba(167, 243, 208, 0.9);
  background: rgba(236, 253, 245, 0.98);
}

.teacher-code-badge--warning {
  color: #b45309;
  border-color: rgba(252, 211, 77, 0.9);
  background: rgba(255, 251, 235, 0.98);
}

.teacher-code-result-head {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 10px;
  margin-bottom: 10px;
}

.teacher-code-result-status {
  display: inline-flex;
  align-items: center;
  justify-content: center;
}

.teacher-code-output {
  flex: 1 1 auto;
  min-height: 0;
  display: flex;
}

.teacher-code-meta {
  display: grid;
  grid-template-columns: repeat(2, minmax(0, 1fr));
  gap: 10px;
}

.teacher-code-meta__item {
  display: grid;
  gap: 6px;
  padding: 12px;
  border: 1px solid #dbe4ee;
  border-radius: 16px;
  background: linear-gradient(180deg, #f8fbff 0%, #f1f5f9 100%);
}

.teacher-code-meta__item span {
  font-size: 12px;
  color: #64748b;
}

.teacher-code-meta__item strong {
  color: #0f172a;
  font-size: 14px;
}

.teacher-code-editor-page__actions {
  margin-top: 12px;
  padding-top: 12px;
}

.teacher-code-editor-page :deep(.teacher-code-output .el-textarea),
.teacher-code-editor-page :deep(.teacher-code-output .el-textarea__inner) {
  height: 100% !important;
  min-height: 0 !important;
}

.teacher-code-editor-page :deep(.teacher-code-output .el-textarea__inner) {
  resize: none;
  border-radius: 16px;
  line-height: 1.55;
}

.teacher-code-editor-page :deep(.monaco-editor),
.teacher-code-editor-page :deep(.monaco-editor .margin),
.teacher-code-editor-page :deep(.monaco-editor .monaco-editor-background) {
  background-color: #ffffff !important;
}

.teacher-code-editor-page :deep(.monaco-editor .view-lines) {
  color: #0f172a !important;
}

.teacher-code-editor-page :deep(.monaco-editor .line-numbers) {
  color: rgba(100, 116, 139, 0.75) !important;
}

.teacher-code-editor-page :deep(.monaco-editor .current-line) {
  border-color: rgba(14, 165, 233, 0.18) !important;
}

.teacher-code-editor-page :deep(.monaco-editor .selected-text) {
  background-color: rgba(59, 130, 246, 0.14) !important;
}

.teacher-code-editor-page :deep(.monaco-editor .cursor) {
  background-color: #0f172a !important;
  border-color: #0f172a !important;
}

@media (max-width: 1280px) {
  .teacher-code-grid {
    grid-template-columns: 1fr;
    min-height: auto;
  }

  .teacher-code-practice-editor {
    min-height: 400px;
  }
}
</style>
