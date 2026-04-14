<template>
  <div class="teacher-page teacher-course-create-page">
    <section class="teacher-page-head">
      <div>
        <h2 class="teacher-page-head__title">创建课程</h2>
        <p class="teacher-page-head__desc">
          统一课程封面、时间信息、课程编号和课程简介的编辑布局，减少遮挡并保持教师后台风格一致。
        </p>
      </div>
    </section>

    <div class="teacher-editor-layout teacher-course-create-page__layout">
      <aside class="teacher-panel teacher-editor-sidebar teacher-course-create-page__sidebar">
        <div>
          <h3 class="teacher-panel__title">操作员信息</h3>
          <p class="teacher-course-create-page__sidebar-note">
            课程创建后会自动绑定当前教师账号，右侧只需填写课程资料本身。
          </p>
        </div>

        <div class="teacher-editor-meta">
          <el-image class="teacher-editor-profile" :src="user.photo" fit="cover"></el-image>
          <div class="teacher-editor-profile__list">
            <div><strong>姓名：</strong>{{ user.name }}</div>
            <div><strong>性别：</strong>{{ user.sex }}</div>
            <div><strong>角色：</strong>{{ user.role }}</div>
            <div><strong>电话：</strong>{{ user.phone }}</div>
            <div><strong>账号：</strong>{{ user.account }}</div>
            <div><strong>邮箱：</strong>{{ user.email }}</div>
          </div>
        </div>

        <div class="teacher-course-create-page__helper">
          <div class="teacher-course-create-page__helper-head">填写提示</div>
          <ul class="teacher-course-create-page__helper-list">
            <li>先上传课程封面，再补齐课程名称和起止时间。</li>
            <li>课程编号可通过按钮自动生成，避免手动录入错误。</li>
            <li>课程简介建议写清学习目标、进度安排和课堂要求。</li>
          </ul>
        </div>
      </aside>

      <section class="teacher-panel teacher-editor-main teacher-course-create-page__main">
        <div class="teacher-editor-main__stack teacher-course-create-page__stack">
          <div class="teacher-editor-upload teacher-course-create-page__meta">
            <div class="teacher-editor-form-item">
              <span class="teacher-editor-form-item__label">课程封面</span>
              <el-upload
                class="avatar-uploader"
                action="http://localhost:9998/files/course/upload"
                list-type="picture-card"
                :show-file-list="false"
                :on-success="handleAvatarSuccess"
              >
                <img v-if="imageUrl" class="teacher-editor-upload__preview" :src="imageUrl" alt="" />
                <div v-else class="teacher-empty teacher-course-create-page__empty">
                  <i class="el-icon-plus"></i>
                </div>
              </el-upload>
            </div>

            <div class="teacher-editor-form-grid teacher-course-create-page__form-grid">
              <div class="teacher-editor-form-item">
                <span class="teacher-editor-form-item__label">课程名称</span>
                <el-input v-model="form.name" placeholder="请输入课程名称"></el-input>
              </div>

              <div class="teacher-editor-form-item">
                <span class="teacher-editor-form-item__label">课程编号</span>
                <div class="teacher-toolbar__actions">
                  <el-input v-model="form.number" disabled placeholder="请点击按钮生成课程编号"></el-input>
                  <el-button type="primary" plain @click="random">点击生成</el-button>
                </div>
              </div>

              <div class="teacher-editor-form-item" style="grid-column: 1 / -1;">
                <span class="teacher-editor-form-item__label">日期范围</span>
                <div class="teacher-course-create-page__date-grid">
                  <div class="teacher-course-create-page__date-item">
                    <span class="teacher-course-create-page__date-caption">开始时间</span>
                    <el-date-picker
                      v-model="form.beginTime"
                      type="datetime"
                      format="yyyy-MM-dd HH:mm:ss"
                      value-format="yyyy-MM-dd HH:mm:ss"
                      placeholder="请选择开始时间"
                    >
                    </el-date-picker>
                  </div>
                  <div class="teacher-course-create-page__date-item">
                    <span class="teacher-course-create-page__date-caption">结束时间</span>
                    <el-date-picker
                      v-model="form.endTime"
                      type="datetime"
                      format="yyyy-MM-dd HH:mm:ss"
                      value-format="yyyy-MM-dd HH:mm:ss"
                      placeholder="请选择结束时间"
                    >
                    </el-date-picker>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="teacher-panel teacher-panel--padded teacher-course-create-page__editor-card">
            <div class="teacher-panel__head">
              <div>
                <h3 class="teacher-panel__title">课程简介</h3>
                <p class="teacher-panel__desc">使用富文本介绍课程安排、目标、考核方式和学习要求。</p>
              </div>
              <div class="teacher-course-create-page__editor-meta">建议控制在 3 至 5 段，便于学生快速浏览。</div>
            </div>
            <div id="editor" class="w-e-text-container teacher-course-create-page__editor" style="width: 100%; height: 380px"></div>
          </div>

          <div class="teacher-editor-actions teacher-editor-actions--end">
            <el-button type="primary" @click="submit">提交</el-button>
          </div>
        </div>
      </section>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
import E from "wangeditor";

let editor;

function initWangEditor(content) {
  setTimeout(() => {
    if (!editor) {
      editor = new E("#editor");
      editor.config.placeholder = "请输入内容";
      editor.config.uploadFileName = "file";
      editor.config.uploadImgServer = "http://localhost:9998/files/wang/upload";
      editor.create();
    }
    editor.txt.html(content);
  }, 0);
}

export default {
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      form: {
        number: "",
        beginTime: "",
        endTime: ""
      },
      tableData: [],
      imageUrl: ""
    };
  },
  created() {
    this.load();
  },
  methods: {
    load() {
      editor = "";
      initWangEditor("");
    },
    handleAvatarSuccess(res) {
      this.imageUrl = res.data;
      this.form.photo = res.data;
    },
    random() {
      request.get("exam/course/random").then((res) => {
        if (res.code === "200") {
          this.form.number = res.data;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    submit() {
      this.form.teacherId = this.user.id;
      this.form.message = editor.txt.html();
      request.post("exam/course/add", this.form).then((res) => {
        if (res.code === "200") {
          this.$message.success("添加成功");
          this.form = {
            number: "",
            beginTime: "",
            endTime: ""
          };
          this.imageUrl = "";
          editor = "";
          initWangEditor("");
        } else {
          this.$message.error(res.msg);
        }
      });
    }
  }
};
</script>

<style scoped>
:deep(.w-e-text-container) {
  border: 1px solid #d1d5db;
  border-radius: 6px;
  overflow: hidden;
  background: #fff;
}

:deep(.w-e-toolbar) {
  border: none !important;
  border-bottom: 1px solid #e5e7eb !important;
  background: #f9fafb;
}

:deep(.w-e-text) {
  padding: 16px;
  line-height: 1.7;
}

:deep(.avatar-uploader .el-upload) {
  width: 100%;
  border: none;
}

.teacher-course-create-page__layout {
  align-items: stretch;
}

.teacher-course-create-page__sidebar,
.teacher-course-create-page__main {
  min-height: 100%;
}

.teacher-course-create-page__sidebar {
  display: flex;
  flex-direction: column;
  gap: 18px;
}

.teacher-course-create-page__sidebar-note {
  margin: 8px 0 0;
  color: #6b7280;
  line-height: 1.7;
}

.teacher-course-create-page__helper {
  margin-top: auto;
  padding: 16px 18px;
  border-radius: 16px;
  background: linear-gradient(180deg, #f8fffc 0%, #eefbf6 100%);
  border: 1px solid #c7eadb;
}

.teacher-course-create-page__helper-head {
  margin-bottom: 10px;
  font-size: 15px;
  font-weight: 700;
  color: #0f766e;
}

.teacher-course-create-page__helper-list {
  margin: 0;
  padding-left: 18px;
  color: #475569;
  line-height: 1.8;
}

.teacher-course-create-page__main {
  display: flex;
  flex-direction: column;
}

.teacher-course-create-page__stack {
  flex: 1;
}

.teacher-course-create-page__meta {
  padding: 4px 0 2px;
}

.teacher-course-create-page__form-grid {
  align-content: start;
}

.teacher-course-create-page__date-grid {
  display: grid;
  grid-template-columns: repeat(2, minmax(0, 1fr));
  gap: 16px;
}

.teacher-course-create-page__date-item {
  display: grid;
  gap: 8px;
  min-width: 0;
}

.teacher-course-create-page__date-caption {
  font-size: 12px;
  font-weight: 600;
  color: #64748b;
}

.teacher-course-create-page__editor-card {
  display: flex;
  flex: 1;
  flex-direction: column;
}

.teacher-course-create-page__editor-meta {
  font-size: 12px;
  color: #6b7280;
}

.teacher-course-create-page__editor {
  flex: 1;
  min-height: 420px;
}

.teacher-course-create-page__empty {
  min-height: 240px;
}

.teacher-course-create-page :deep(.el-date-editor) {
  width: 100%;
}

.teacher-course-create-page :deep(.el-input__inner) {
  height: 42px;
  border-radius: 12px;
}

.teacher-course-create-page :deep(.avatar-uploader .el-upload) {
  min-height: 240px;
  border-radius: 18px;
  border: 1px dashed #b7d8cb;
  background: linear-gradient(180deg, #fbfffd 0%, #f3faf7 100%);
}

.teacher-course-create-page :deep(.w-e-toolbar) {
  background: #f8fafc;
}

.teacher-course-create-page :deep(.w-e-text) {
  min-height: 320px;
}

@media (max-width: 1280px) {
  .teacher-course-create-page__helper {
    margin-top: 0;
  }

  .teacher-course-create-page__date-grid {
    grid-template-columns: 1fr;
  }
}

@media (max-width: 768px) {
  .teacher-course-create-page__date-grid {
    grid-template-columns: 1fr;
  }
}
</style>
