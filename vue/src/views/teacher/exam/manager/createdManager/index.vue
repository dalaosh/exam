<template>
  <div class="exam-editor-page">
    <el-row :gutter="20">
      <el-col :span="8">
        <div class="editor-card editor-side-card">
          <div class="section-header">
            <div>
              <div class="section-eyebrow">Exam Builder</div>
              <div class="section-title">考试信息</div>
            </div>
          </div>
          <el-form label-position="top" :model="form" class="editor-form">
            <el-form-item label="请选择课程">
              <el-select
                v-model="form.courseId"
                clearable
                filterable
                placeholder="请选择课程"
                popper-class="course-option-dropdown"
              >
                <el-option
                  v-for="item in course"
                  :key="item.id"
                  :label="`${item.name} / ${item.number}`"
                  :value="item.id"
                >
                  <div class="course-option">
                    <span class="course-option__name">课程：{{ item.name }}</span>
                    <span class="course-option__code">编号：{{ item.number }}</span>
                  </div>
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="考试开始时间">
              <el-date-picker
                v-model="value1"
                type="datetime"
                placeholder="选择日期时间"
                style="width: 100%"
              ></el-date-picker>
            </el-form-item>
            <el-form-item label="持续时长">
              <el-input v-model="form.totalTime" placeholder="请输入持续时长（分钟）"></el-input>
            </el-form-item>
            <el-form-item label="总分">
              <el-input disabled v-model="form.totalScore" placeholder="总分自动计算"></el-input>
            </el-form-item>
            <el-form-item label="考试类型">
              <el-input v-model="form.type" placeholder="请输入考试类型"></el-input>
            </el-form-item>
          </el-form>
        </div>
      </el-col>

      <el-col :span="16">
        <el-row :gutter="20">
          <el-col :span="12">
            <div class="editor-card">
              <div class="section-header compact">
                <div class="section-title">考试介绍</div>
              </div>
              <div class="editor-shell">
                <div class="w-e-text-container" id="editorA"></div>
              </div>
            </div>
          </el-col>
          <el-col :span="12">
            <div class="editor-card">
              <div class="section-header compact">
                <div class="section-title">考生须知</div>
              </div>
              <div class="editor-shell">
                <div class="w-e-text-container" id="editorB"></div>
              </div>
            </div>
          </el-col>
        </el-row>

        <div class="action-card">
          <div class="action-copy">确认考试信息与考试说明后再提交。</div>
          <el-button type="primary" class="primary-action" @click="submit">提交</el-button>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
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

export default {
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      form: {
        totalScore: 0
      },
      course: [],
      value1: ""
    };
  },
  created() {
    this.load();
    this.findCourse();
  },
  methods: {
    time(dataTime) {
      const date = new Date(dataTime);
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, "0");
      const day = String(date.getDate()).padStart(2, "0");
      const hours = String(date.getHours()).padStart(2, "0");
      const minutes = String(date.getMinutes()).padStart(2, "0");
      const seconds = String(date.getSeconds()).padStart(2, "0");
      return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
    },
    load() {
      editorA = "";
      initWangEditorA("");
      editorB = "";
      initWangEditorB("");
    },
    findCourse() {
      request
        .get("exam/course/selectAll", {
          params: {
            teacherId: this.user.id
          }
        })
        .then((res) => {
          if (res.code === "200") {
            this.course = res.data || [];
          } else {
            this.$message.error(res.msg);
          }
        });
    },
    submit() {
      this.form.description = editorA.txt.html();
      this.form.tips = editorB.txt.html();
      this.form.examDate = this.time(this.value1);
      request.post("exam/examManage/add", this.form).then((res) => {
        if (res.code === "200") {
          this.$message.success("添加成功");
          this.form = {
            totalScore: 0
          };
          this.load();
          this.value1 = "";
        } else {
          this.$message.error(res.msg);
        }
      });
    }
  }
};
</script>

<style scoped>
.exam-editor-page {
  padding-bottom: 8px;
}

.editor-card,
.action-card {
  margin-bottom: 20px;
  padding: 24px;
  border: 1px solid rgba(226, 232, 240, 0.95);
  border-radius: 24px;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0.98), rgba(248, 250, 252, 0.96));
  box-shadow: 0 20px 48px rgba(15, 23, 42, 0.08);
}

.editor-side-card {
  min-height: 100%;
}

.section-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 20px;
}

.section-header.compact {
  margin-bottom: 16px;
}

.section-eyebrow {
  margin-bottom: 6px;
  font-size: 12px;
  font-weight: 700;
  letter-spacing: 0.12em;
  text-transform: uppercase;
  color: #2563eb;
}

.section-title {
  font-size: 20px;
  font-weight: 700;
  color: #0f172a;
  line-height: 1.4;
}

.editor-form :deep(.el-form-item__label) {
  font-weight: 600;
  color: #475569;
}

.editor-form :deep(.el-input__inner),
.editor-form :deep(.el-textarea__inner),
.editor-form :deep(.el-select .el-input__inner) {
  height: 44px;
  border-radius: 14px;
  border-color: #dbe4ee;
}

.editor-form :deep(.el-date-editor.el-input) {
  width: 100%;
}

.course-option {
  display: flex;
  flex-direction: column;
  gap: 2px;
  padding: 4px 18px 4px 0;
}

.course-option__name,
.course-option__code {
  display: block;
  font-size: 13px;
  line-height: 1.5;
}

.course-option__name {
  color: #334155;
  font-weight: 600;
}

.course-option__code {
  color: #64748b;
}

:deep(.course-option-dropdown .el-select-dropdown__item) {
  height: auto;
  min-height: 60px;
  padding-top: 8px;
  padding-bottom: 8px;
  line-height: 1.4;
  white-space: normal;
  display: flex;
  align-items: center;
}

:deep(.course-option-dropdown .el-select-dropdown__wrap) {
  overflow-x: hidden;
}

.editor-shell {
  min-height: 420px;
}

.editor-shell :deep(.w-e-text-container) {
  height: 420px !important;
  border: 1px solid #dbe4ee;
  border-radius: 18px;
  overflow: hidden;
}

.editor-shell :deep(.w-e-toolbar) {
  padding: 8px 10px;
  border-bottom: 1px solid #e2e8f0 !important;
  background: linear-gradient(135deg, #eff6ff 0%, #f8fafc 100%);
}

.editor-shell :deep(.w-e-menu) {
  border-radius: 10px;
}

.editor-shell :deep(.w-e-text) {
  padding: 10px 12px;
}

.action-card {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 16px;
}

.action-copy {
  color: #64748b;
  font-size: 14px;
}

.primary-action {
  min-width: 160px;
  height: 46px;
  border-radius: 14px;
  font-size: 15px;
  font-weight: 700;
}
</style>
