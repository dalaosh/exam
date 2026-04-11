<template>
  <div class="student-page">
    <section class="student-page-head">
      <div>
        <h2 class="student-page-head__title">修改代码</h2>
        <p class="student-page-head__desc">统一展示题目、语言、编辑器和运行结果，保持原有修改提交流程不变。</p>
      </div>
      <div class="student-page-head__meta">
        <el-tag class="student-tag" type="warning">编辑模式</el-tag>
      </div>
    </section>

    <section class="student-code-grid">
      <div class="student-code-stack">
        <article class="student-panel student-code-panel">
          <h3 class="student-code-panel__title">题目内容</h3>
          <p class="student-code-panel__desc">支持直接修改题目描述，便于同步维护代码实操存档。</p>
          <el-input
            v-model="table.title"
            type="textarea"
            :autosize="{ minRows: 4, maxRows: 4 }"
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
                <span class="student-code-editor__hint">Editable Workspace</span>
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
                style="height: 520px"
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
              <p class="student-code-panel__desc" style="margin: 0;">修改代码后可先运行，再决定是否提交更新。</p>
            </div>
            <span class="student-code-result-status" :class="resultStatusClass">{{ resultStatusText }}</span>
          </div>
          <div class="student-code-output">
            <el-input
              v-model="result"
              type="textarea"
              :disabled="true"
              :autosize="{ minRows: 22, maxRows: 22 }"
              placeholder="运行结果"
            />
          </div>
        </article>

        <article class="student-panel student-code-panel">
          <div class="student-code-meta">
            <div class="student-code-meta__item">
              <span>存档时间</span>
              <strong>{{ table.time || "暂无" }}</strong>
            </div>
            <div class="student-code-meta__item">
              <span>当前语言</span>
              <strong>{{ selectedLanguageLabel || "未选择" }}</strong>
            </div>
          </div>
          <div class="student-code-action">
            <el-button type="primary" @click="submit">修改</el-button>
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
      receivedData: "",
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
      ],
    };
  },
  created() {
    this.receivedData = this.$route.params;
    this.load();
  },
  methods: {
    load() {
      request.get(`exam/code/selectById/${this.receivedData.id}`).then((res) => {
        if (res.code === "200") {
          this.table = res.data;
          this.code = this.table.codeMessage;
          this.result = this.table.runMessage;
          this.language = this.table.type;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
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
      request.put("/exam/code/update", this.table).then((res) => {
        if (res.code === "200") {
          this.$message.success("修改成功");
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
      return `revision.${ext}`;
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
      return "已更新结果";
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
