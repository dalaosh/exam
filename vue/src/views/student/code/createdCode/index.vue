<template>
  <div class="student-page student-code-practice-page">
    <section class="student-page-head">
      <div>
        <h2 class="student-page-head__title">代码实操</h2>
      </div>
      <div class="student-page-head__meta">
        <el-tag class="student-tag" type="info">在线运行</el-tag>
      </div>
    </section>

    <section class="student-code-grid">
      <div class="student-code-stack">
        <article class="student-panel student-code-panel">
          <h3 class="student-code-panel__title">题目内容</h3>
          <el-input
            v-model="table.title"
            type="textarea"
            :autosize="{ minRows: 4, maxRows: 4 }"
            placeholder="请输入题目内容"
          />
        </article>

        <article class="student-panel student-code-panel">
          <div class="student-code-toolbar">
            <div class="student-code-toolbar__group">
              <span class="student-code-toolbar__label">语言选择</span>
              <el-select
                v-model="language"
                size="medium"
                class="student-code-toolbar__select"
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

            <div class="student-code-toolbar__group">
              <div class="student-code-slider">
                <span class="student-code-toolbar__label">字体大小</span>
                <input id="fontSize" v-model="fontSize" type="range" min="10" max="80">
                <span class="student-code-slider__value">{{ fontSize }}px</span>
              </div>
              <el-button type="primary" @click="runCode">运行代码</el-button>
            </div>
          </div>

          <div class="student-code-editor">
            <div class="student-code-editor__chrome">
              <div class="student-code-editor__chrome-left">
                <div class="student-code-editor__dots">
                  <span></span>
                  <span></span>
                  <span></span>
                </div>
                <div class="student-code-editor__tab">
                  <i class="el-icon-document"></i>
                  {{ fileName }}
                </div>
                <span class="student-code-editor__hint">Monaco Editor</span>
              </div>
              <div class="student-code-editor__chrome-right">
                <span class="student-code-badge student-code-badge--accent">
                  <i class="el-icon-cpu"></i>
                  {{ selectedLanguageLabel || "请选择语言" }}
                </span>
                <span class="student-code-badge">
                  <i class="el-icon-edit-outline"></i>
                  {{ codeLineCount }} 行
                </span>
              </div>
            </div>
            <div class="student-code-editor__body">
              <monaco-editor
                v-model="code"
                :language="editorLanguage"
                :options="editorOptions"
                class="student-code-practice-editor"
              />
            </div>
          </div>
        </article>
      </div>

      <div class="student-code-stack">
        <article class="student-panel student-code-panel">
          <div class="student-code-result-head">
            <div>
              <h3 class="student-code-panel__title" style="margin-bottom: 6px;">运行结果</h3>
            </div>
            <span class="student-code-result-status" :class="resultStatusClass">{{ resultStatusText }}</span>
          </div>
          <div class="student-code-output">
            <el-input
              v-model="result"
              type="textarea"
              :disabled="true"
              :autosize="{ minRows: 10, maxRows: 10 }"
              placeholder="运行结果"
            />
          </div>
        </article>

        <article class="student-panel student-code-panel">
          <div class="student-code-meta">
            <div class="student-code-meta__item">
              <span>当前语言</span>
              <strong>{{ selectedLanguageLabel || "未选择" }}</strong>
            </div>
            <div class="student-code-meta__item">
              <span>编辑状态</span>
              <strong>{{ code ? "已输入代码" : "等待输入" }}</strong>
            </div>
          </div>
          <div class="student-code-action">
            <el-button type="primary" @click="submit">保存</el-button>
          </div>
        </article>
      </div>
    </section>
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
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : "",
      code: "",
      language: null,
      form: {},
      result: "",
      fontSize: "18",
      table: {},
      languageOptions: [
        { value: "c", label: "C (gcc)", editorLanguage: "c", ext: "c" },
        { value: "c++", label: "C++ (g++)", editorLanguage: "cpp", ext: "cpp" },
        { value: "java", label: "Java (javac)", editorLanguage: "java", ext: "java" },
        { value: "python", label: "Python (python3)", editorLanguage: "python", ext: "py" },
        { value: "r", label: "R (R)", editorLanguage: "r", ext: "r" },
        { value: "asm", label: "ASM (nasm.sh)", editorLanguage: "plaintext", ext: "asm" },
        { value: "rust", label: "Rust (rustc)", editorLanguage: "rust", ext: "rs" },
        { value: "scala", label: "Scala (scalac)", editorLanguage: "scala", ext: "scala" },
        { value: "pypy3", label: "Python (pypy3)", editorLanguage: "python", ext: "py" },
        { value: "cangjie", label: "仓颉 (cjc)", editorLanguage: "plaintext", ext: "cj" },
        { value: "bash", label: "Bash (bash)", editorLanguage: "shell", ext: "sh" },
        { value: "plaintext", label: "Plaintext (cat)", editorLanguage: "plaintext", ext: "txt" },
        { value: "common-lisp", label: "Common Lisp (sbcl)", editorLanguage: "plaintext", ext: "lisp" },
        { value: "pascal", label: "Pascal (fpc)", editorLanguage: "pascal", ext: "pas" },
        { value: "go", label: "Go (gccgo)", editorLanguage: "go", ext: "go" },
        { value: "haskell", label: "Haskell (ghc)", editorLanguage: "haskell", ext: "hs" },
        { value: "lua", label: "Lua (lua)", editorLanguage: "lua", ext: "lua" },
        { value: "luajit", label: "Lua (luajit)", editorLanguage: "lua", ext: "lua" },
        { value: "c#", label: "C# (dotnet)", editorLanguage: "csharp", ext: "cs" },
        { value: "javascript", label: "JavaScript (node)", editorLanguage: "javascript", ext: "js" },
        { value: "ocaml", label: "OCaml (ocamlc)", editorLanguage: "plaintext", ext: "ml" },
        { value: "php", label: "PHP (php)", editorLanguage: "php", ext: "php" },
        { value: "perl", label: "Perl (perl)", editorLanguage: "perl", ext: "pl" },
      ],
    };
  },
  methods: {
    runCode() {
      if (this.language === null) {
        this.$message.error("请选择语言");
        return;
      }
      this.form = {
        m: this.code,
        t: this.language,
      };
      request.post("/exam/code/cpp", this.form).then((res) => {
        this.result = res.data;
      });
    },
    submit() {
      this.table.studentId = this.user.id;
      this.table.type = this.language;
      this.table.codeMessage = this.code;
      this.table.runMessage = this.result;
      request.post("/exam/code/add", this.table).then((res) => {
        if (res.code === "200") {
          this.$message.success("添加成功");
          this.form = {};
          this.language = null;
          this.code = "";
          this.result = "";
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
      if (!this.code) {
        return 1;
      }
      return this.code.split("\n").length;
    },
    resultStatusText() {
      if (!this.result) {
        return "等待运行";
      }
      return "已生成结果";
    },
    resultStatusClass() {
      return this.result ? "student-code-badge--success" : "student-code-badge--warning";
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
      };
    },
  },
};
</script>

<style scoped>
.student-code-practice-page {
  height: calc(100vh - 128px);
  min-height: 0;
  gap: 10px;
  overflow: hidden;
}

.student-code-practice-page .student-page-head {
  flex: 0 0 auto;
  align-items: center;
  gap: 10px;
}

.student-code-practice-page .student-page-head__title {
  font-size: 20px;
}

.student-code-practice-page .student-page-head__desc {
  margin-top: 2px;
  line-height: 1.35;
}

.student-code-practice-page .student-code-grid {
  flex: 1 1 auto;
  min-height: 0;
  display: grid;
  grid-template-columns: minmax(0, 1.45fr) minmax(300px, 0.85fr);
  gap: 12px;
  overflow: hidden;
}

.student-code-practice-page .student-code-stack {
  min-height: 0;
  display: flex;
  flex-direction: column;
  gap: 12px;
  overflow: hidden;
}

.student-code-practice-page .student-code-panel {
  padding: 14px 16px;
}

.student-code-practice-page .student-code-panel__title {
  margin-bottom: 8px;
}

.student-code-practice-page .student-code-panel__desc {
  margin: -2px 0 10px;
  line-height: 1.45;
}

.student-code-practice-page .student-code-stack:first-child .student-code-panel:first-child {
  flex: 0 0 auto;
}

.student-code-practice-page .student-code-stack:first-child .student-code-panel:last-child,
.student-code-practice-page .student-code-stack:last-child .student-code-panel:first-child {
  flex: 1 1 auto;
  min-height: 0;
  display: flex;
  flex-direction: column;
}

.student-code-practice-page .student-code-stack:last-child .student-code-panel:last-child {
  flex: 0 0 auto;
}

.student-code-practice-page .student-code-toolbar {
  flex: 0 0 auto;
  gap: 10px;
  margin-bottom: 10px;
}

.student-code-practice-page .student-code-toolbar__group {
  gap: 10px;
}

.student-code-practice-page .student-code-toolbar__select {
  width: 190px;
}

.student-code-practice-page .student-code-slider {
  min-width: 190px;
}

.student-code-practice-page .student-code-editor {
  flex: 1 1 auto;
  min-height: 0;
  display: flex;
  flex-direction: column;
}

.student-code-practice-page .student-code-editor__chrome {
  flex: 0 0 auto;
  padding: 8px 12px;
}

.student-code-practice-page .student-code-editor__tab {
  padding: 5px 10px;
}

.student-code-practice-page .student-code-editor__body {
  flex: 1 1 auto;
  min-height: 0;
}

.student-code-practice-editor {
  display: block;
  width: 100%;
  height: 100%;
  min-height: 0;
}

.student-code-practice-page .student-code-result-head {
  flex: 0 0 auto;
  gap: 8px;
  margin-bottom: 10px;
}

.student-code-practice-page .student-code-output {
  flex: 1 1 auto;
  min-height: 0;
  display: flex;
}

.student-code-practice-page .student-code-meta {
  grid-template-columns: repeat(2, minmax(0, 1fr));
  gap: 10px;
  margin-bottom: 10px;
}

.student-code-practice-page .student-code-meta__item {
  padding: 10px 12px;
}

.student-code-practice-page .student-code-action .el-button {
  min-width: 160px;
}

.student-code-practice-page :deep(.student-code-output .el-textarea),
.student-code-practice-page :deep(.student-code-output .el-textarea__inner) {
  height: 100% !important;
  min-height: 0 !important;
}

.student-code-practice-page :deep(.student-code-output .el-textarea__inner) {
  resize: none;
  border-radius: 14px;
  line-height: 1.55;
}

.student-code-practice-page :deep(.el-textarea__inner) {
  border-radius: 14px;
}

@media (max-width: 1280px) {
  .student-code-practice-page {
    height: auto;
    overflow: visible;
  }

  .student-code-practice-page .student-code-grid {
    grid-template-columns: 1fr;
    overflow: visible;
  }
}
</style>
