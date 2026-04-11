<template>
  <div class="student-page student-note-editor-page">
    <section class="student-page-head student-page-head--compact">
      <div>
        <h2 class="student-page-head__title">创建笔记</h2>
        <p class="student-page-head__desc">填写标题并直接开始写正文。</p>
      </div>
      <div class="student-page-head__meta student-page-head__meta--plain">
        <span class="student-page-head__meta-text">Markdown</span>
        <span class="student-page-head__meta-text">{{ user.name || "学生" }}</span>
      </div>
    </section>

    <section class="student-note-editor__workspace">
      <div class="student-panel student-panel--padded student-note-editor__main">
        <div class="student-note-editor__topbar">
          <div class="student-note-editor__field">
            <label class="student-note-editor__label" for="note-title">笔记标题</label>
            <el-input
              id="note-title"
              v-model.trim="name"
              maxlength="60"
              show-word-limit
              placeholder="例如：第三章重点公式整理"
            />
          </div>

          <div class="student-note-editor__actions">
            <el-button plain @click="goList">返回列表</el-button>
            <el-button plain @click="resetEditor">清空</el-button>
            <el-button type="primary" :loading="publishing" @click="upload">
              发布笔记
            </el-button>
          </div>
        </div>

        <div class="student-note-editor__editor-head">
          <div>
            <h3 class="student-note-editor__editor-title">正文编辑区</h3>
            <p class="student-note-editor__editor-desc">支持实时预览。</p>
          </div>
          <div class="student-note-editor__editor-status">
            <span class="student-code-badge student-code-badge--accent">双栏预览</span>
            <span class="student-code-badge">{{ contentLength }} 字符</span>
          </div>
        </div>

        <div class="student-note-editor__editor">
          <mavon-editor
            ref="md"
            v-model="content"
            :toolbars="toolbars"
            :ishljs="true"
            :fontSize="fontSize"
            toolbarsBackground="#f8fafc"
            previewBackground="#f8fafc"
            @save="save"
          >
            <template v-slot:left-toolbar-after>
              <el-button
                type="text"
                title="插入图片资源"
                class="student-note-editor__toolbar-link"
                @click="dialogFormVisible = true"
              >
                图片
              </el-button>
            </template>
          </mavon-editor>
        </div>
      </div>

      <aside class="student-panel student-panel--padded student-note-editor__side">
        <section class="student-note-editor__side-section">
          <h3 class="student-note-editor__side-title">发布检查</h3>
          <div class="student-note-editor__checklist">
            <div class="student-note-editor__checkitem" :class="{ 'is-ready': !!name }">
              <span class="student-note-editor__checkdot"></span>
              <span>标题已填写</span>
            </div>
            <div class="student-note-editor__checkitem" :class="{ 'is-ready': !!contentText }">
              <span class="student-note-editor__checkdot"></span>
              <span>正文已开始</span>
            </div>
            <div class="student-note-editor__checkitem" :class="{ 'is-ready': !!lastImageMarkdown }">
              <span class="student-note-editor__checkdot"></span>
              <span>已插入图片</span>
            </div>
          </div>
        </section>

        <section class="student-note-editor__side-section">
          <h3 class="student-note-editor__side-title">图片素材</h3>
          <el-button plain @click="dialogFormVisible = true">上传图片</el-button>
          <div class="student-note-editor__upload-preview">
            <span class="student-note-editor__upload-label">最近插入</span>
            <code>{{ lastImageMarkdown || "暂无上传记录" }}</code>
          </div>
        </section>

        <section class="student-note-editor__side-section student-note-editor__side-section--compact">
          <h3 class="student-note-editor__side-title">写作提示</h3>
          <ul class="student-note-editor__tips">
            <li>先列标题，再补内容。</li>
            <li>每段只表达一个重点。</li>
            <li>结尾补充复习结论。</li>
          </ul>
        </section>
      </aside>
    </section>

    <el-dialog
      title="上传笔记图片"
      :visible.sync="dialogFormVisible"
      width="520px"
      class="student-note-editor__dialog"
    >
      <div class="student-note-editor__dialog-body">
        <p class="student-note-editor__dialog-desc">上传后会自动插入正文末尾。</p>
        <el-upload
          action="http://localhost:9998/files/md/upload"
          :show-file-list="false"
          :on-success="successUpload"
        >
          <el-button type="primary" plain>选择图片并上传</el-button>
        </el-upload>

        <div class="student-note-editor__dialog-result">
          <span>插入语法</span>
          <code>{{ lastImageMarkdown || "上传后这里会显示图片语法" }}</code>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { mavonEditor } from "mavon-editor";
import "mavon-editor/dist/css/index.css";
import request from "@/utils/request";

export default {
  name: "MavonEditor",
  components: {
    mavonEditor,
  },
  data() {
    return {
      fontSize: "16px",
      content: "",
      name: "",
      dialogFormVisible: false,
      lastImageMarkdown: "",
      publishing: false,
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      toolbars: {
        bold: true,
        italic: true,
        header: true,
        underline: true,
        strikethrough: true,
        mark: true,
        superscript: true,
        subscript: true,
        quote: true,
        ol: true,
        ul: true,
        link: true,
        imagelink: false,
        code: true,
        table: true,
        fullscreen: true,
        readmodel: true,
        htmlcode: true,
        help: true,
        undo: true,
        redo: true,
        trash: true,
        save: false,
        navigation: true,
        alignleft: true,
        aligncenter: true,
        alignright: true,
        subfield: true,
        preview: true,
      },
    };
  },
  computed: {
    contentText() {
      return this.content
        .replace(/```[\s\S]*?```/g, " ")
        .replace(/`[^`]*`/g, " ")
        .replace(/!\[[^\]]*\]\([^)]+\)/g, " ")
        .replace(/\[[^\]]*\]\([^)]+\)/g, " ")
        .replace(/[#>*_\-\n\r]/g, " ")
        .replace(/\s+/g, " ")
        .trim();
    },
    contentLength() {
      return this.contentText.length;
    },
  },
  methods: {
    save() {
      this.$message.success("已保留当前编辑内容");
    },
    successUpload(response) {
      this.lastImageMarkdown = `![笔记图片](${response.data} "笔记图片")`;
      this.content = this.content
        ? `${this.content}\n\n${this.lastImageMarkdown}`
        : this.lastImageMarkdown;
      this.dialogFormVisible = false;
      this.$message.success("图片已插入正文");
    },
    upload() {
      if (!this.name) {
        this.$message.warning("请先填写笔记标题");
        return;
      }
      if (!this.contentText) {
        this.$message.warning("请先输入笔记正文");
        return;
      }

      const form = {
        userId: this.user.id,
        role: this.user.role,
        name: this.name,
        content: this.content,
      };

      this.publishing = true;
      request
        .post("/md/add", form)
        .then((res) => {
          if (res.code === "200") {
            this.$message.success("笔记发布成功");
            this.$router.push("/student/listNote");
          } else {
            this.$message.error(res.msg || "发布失败");
          }
        })
        .finally(() => {
          this.publishing = false;
        });
    },
    goList() {
      this.$router.push("/student/listNote");
    },
    resetEditor() {
      this.$confirm("确定清空当前标题和正文吗？", "清空内容", {
        type: "warning",
      })
        .then(() => {
          this.name = "";
          this.content = "";
          this.lastImageMarkdown = "";
        })
        .catch(() => {});
    },
  },
};
</script>

<style scoped lang="scss">
.student-note-editor-page {
  gap: 14px;
}

.student-note-editor__workspace {
  --workspace-height: calc(100vh - 252px);
  display: grid;
  grid-template-columns: minmax(0, 1fr) 340px;
  gap: 18px;
  align-items: stretch;
}

.student-note-editor__main,
.student-note-editor__side {
  min-height: 720px;
  height: var(--workspace-height);
}

.student-note-editor__main {
  display: grid;
  grid-template-rows: auto auto minmax(0, 1fr);
  gap: 16px;
}

.student-note-editor__topbar {
  display: grid;
  grid-template-columns: minmax(0, 1fr) auto;
  gap: 16px;
  align-items: end;
}

.student-note-editor__field {
  display: grid;
  gap: 8px;
}

.student-note-editor__label {
  font-size: 13px;
  font-weight: 600;
  color: #475569;
}

.student-note-editor__actions {
  display: flex;
  align-items: center;
  justify-content: flex-end;
  flex-wrap: wrap;
  gap: 10px;
}

.student-note-editor__editor-head {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 12px;
  flex-wrap: wrap;
}

.student-note-editor__editor-title {
  margin: 0;
  font-size: 16px;
  font-weight: 700;
  color: #0f172a;
}

.student-note-editor__editor-desc {
  margin: 4px 0 0;
  font-size: 12px;
  color: #64748b;
}

.student-note-editor__editor-status {
  display: flex;
  align-items: center;
  gap: 8px;
  flex-wrap: wrap;
}

.student-note-editor__editor {
  min-height: 0;
  height: 100%;
  border: 1px solid #dbe4ee;
  border-radius: 16px;
  overflow: hidden;
  background: #fff;
}

.student-note-editor__toolbar-link {
  margin-left: 6px;
  font-weight: 600;
}

.student-note-editor__side {
  display: grid;
  grid-template-rows: auto auto 1fr;
  gap: 16px;
  align-content: stretch;
}

.student-note-editor__side-section {
  display: grid;
  gap: 12px;
  padding: 4px 0 0;
}

.student-note-editor__side-section + .student-note-editor__side-section {
  padding-top: 16px;
  border-top: 1px solid #eef2f7;
}

.student-note-editor__side-section--compact {
  align-content: start;
}

.student-note-editor__side-title {
  margin: 0;
  font-size: 16px;
  font-weight: 700;
  color: #0f172a;
}

.student-note-editor__checklist {
  display: grid;
  gap: 10px;
}

.student-note-editor__checkitem {
  display: grid;
  grid-template-columns: 10px minmax(0, 1fr);
  gap: 12px;
  align-items: center;
  padding: 14px 14px;
  border: 1px solid #e2e8f0;
  border-radius: 14px;
  background: #f8fafc;
  color: #334155;
  font-weight: 600;
}

.student-note-editor__checkitem.is-ready {
  border-color: #a7f3d0;
  background: #f0fdf4;
}

.student-note-editor__checkdot {
  width: 10px;
  height: 10px;
  border-radius: 999px;
  background: #cbd5e1;
}

.student-note-editor__checkitem.is-ready .student-note-editor__checkdot {
  background: #22c55e;
}

.student-note-editor__upload-preview {
  display: grid;
  gap: 8px;
  padding: 14px 16px;
  border-radius: 14px;
  background: #f8fafc;
  border: 1px dashed #cbd5e1;
}

.student-note-editor__upload-label {
  font-size: 12px;
  font-weight: 600;
  color: #64748b;
}

.student-note-editor__upload-preview code {
  font-size: 12px;
  line-height: 1.7;
  word-break: break-all;
  color: #0f172a;
}

.student-note-editor__tips {
  margin: 0;
  padding-left: 18px;
  display: grid;
  gap: 10px;
  color: #475569;
  font-size: 13px;
  line-height: 1.7;
}

.student-note-editor__dialog-body {
  display: grid;
  gap: 16px;
}

.student-note-editor__dialog-desc {
  margin: 0;
  font-size: 13px;
  color: #64748b;
}

.student-note-editor__dialog-result {
  display: grid;
  gap: 8px;
  padding: 14px 16px;
  border-radius: 12px;
  background: #f8fafc;
  border: 1px solid #e2e8f0;
}

.student-note-editor__dialog-result span {
  font-size: 12px;
  font-weight: 600;
  color: #64748b;
}

.student-note-editor__dialog-result code {
  font-size: 12px;
  line-height: 1.7;
  word-break: break-all;
  color: #0f172a;
}

::v-deep .student-note-editor__editor .v-note-wrapper {
  height: 100%;
  min-height: 100%;
  z-index: 0;
}

::v-deep .student-note-editor__editor .v-note-op {
  border-bottom: 1px solid #e2e8f0;
  background: #f8fafc;
}

::v-deep .student-note-editor__editor .v-note-op.shadow {
  box-shadow: none;
}

::v-deep .student-note-editor__editor .v-note-panel {
  height: calc(100% - 50px);
  min-height: 0;
}

::v-deep .student-note-editor__editor .content-input-wrapper,
::v-deep .student-note-editor__editor .content-preview-wrapper {
  padding-top: 8px;
}

::v-deep .student-note-editor__editor .auto-textarea-input,
::v-deep .student-note-editor__editor .v-note-panel pre,
::v-deep .student-note-editor__editor .v-show-content {
  font-size: 16px !important;
  line-height: 1.8 !important;
}

@media (max-width: 1400px) {
  .student-note-editor__workspace {
    --workspace-height: auto;
    grid-template-columns: 1fr;
  }

  .student-note-editor__main,
  .student-note-editor__side {
    height: auto;
    min-height: 0;
  }
}

@media (max-width: 1100px) {
  .student-note-editor__topbar {
    grid-template-columns: 1fr;
  }

  .student-note-editor__actions {
    justify-content: flex-start;
  }
}

@media (max-width: 900px) {
  .student-note-editor__editor-head {
    align-items: flex-start;
  }

  ::v-deep .student-note-editor__editor .v-note-wrapper {
    min-height: 620px;
  }
}
</style>
