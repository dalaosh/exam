<template>
  <div class="student-course-detail-page" v-loading="loading">
    <section class="course-detail-head">
      <div>
        <h2 class="course-detail-head__title">课程详情</h2>
      </div>
      <el-tag class="course-status" :class="statusClass">{{ statusText }}</el-tag>
    </section>

    <section class="course-detail-layout">
      <aside class="people-column">
        <article class="person-panel">
          <div class="person-panel__head">
            <div>
              <h3 class="person-panel__title">教师信息</h3>
              <p class="person-panel__subtitle">授课教师</p>
            </div>
          </div>

          <div class="person-profile">
            <el-image class="person-profile__photo" :src="teacher.photo" fit="cover">
              <div slot="error" class="image-fallback">暂无照片</div>
            </el-image>
            <div class="person-profile__main">
              <div class="person-profile__name">{{ teacher.name || "未填写" }}</div>
              <div class="person-profile__role">{{ teacher.role || "teacher" }}</div>
            </div>
          </div>

          <dl class="info-list">
            <div class="info-list__item">
              <dt>性别</dt>
              <dd>{{ teacher.sex || "-" }}</dd>
            </div>
            <div class="info-list__item">
              <dt>电话</dt>
              <dd>{{ teacher.phone || "-" }}</dd>
            </div>
            <div class="info-list__item">
              <dt>邮箱</dt>
              <dd>{{ teacher.email || "-" }}</dd>
            </div>
          </dl>
        </article>

        <article class="person-panel">
          <div class="person-panel__head">
            <div>
              <h3 class="person-panel__title">学生信息</h3>
              <p class="person-panel__subtitle">当前申请人</p>
            </div>
            <el-tag class="course-status course-status--small" :class="statusClass">{{ statusText }}</el-tag>
          </div>

          <div class="person-profile">
            <el-image class="person-profile__photo" :src="student.photo" fit="cover">
              <div slot="error" class="image-fallback">暂无照片</div>
            </el-image>
            <div class="person-profile__main">
              <div class="person-profile__name">{{ student.name || "未填写" }}</div>
              <div class="person-profile__role">{{ student.role || "student" }}</div>
            </div>
          </div>

          <dl class="info-list">
            <div class="info-list__item">
              <dt>性别</dt>
              <dd>{{ student.sex || "-" }}</dd>
            </div>
            <div class="info-list__item">
              <dt>电话</dt>
              <dd>{{ student.phone || "-" }}</dd>
            </div>
            <div class="info-list__item">
              <dt>邮箱</dt>
              <dd>{{ student.email || "-" }}</dd>
            </div>
          </dl>
        </article>
      </aside>

      <main class="course-column">
        <section class="course-hero">
          <div class="course-hero__media">
            <el-image class="course-cover" :src="course.photo" fit="contain">
              <div slot="error" class="image-fallback">课程封面</div>
            </el-image>
          </div>

          <div class="course-hero__content">
            <span class="course-hero__eyebrow">COURSE</span>
            <h1 class="course-hero__title">{{ course.name || "课程名称" }}</h1>
            <div class="course-meta-grid">
              <div class="course-meta">
                <span>开始时间</span>
                <strong>{{ course.beginTime || "-" }}</strong>
              </div>
              <div class="course-meta">
                <span>结束时间</span>
                <strong>{{ course.endTime || "-" }}</strong>
              </div>
              <div class="course-meta">
                <span>课程编号</span>
                <strong>{{ course.number || "-" }}</strong>
              </div>
              <div class="course-meta">
                <span>授课教师</span>
                <strong>{{ teacher.name || "-" }}</strong>
              </div>
            </div>
          </div>
        </section>

        <section class="course-intro">
          <div class="course-intro__head">
            <div>
              <h3 class="course-intro__title">课程简介</h3>
            </div>
          </div>
          <div v-if="course.message" v-html="course.message" class="course-intro__body w-e-text"></div>
          <div v-else class="course-intro__empty">暂无课程简介。</div>
        </section>
      </main>
    </section>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      receivedData: {},
      loading: false
    };
  },
  computed: {
    course() {
      return this.receivedData.course || {};
    },
    teacher() {
      return this.receivedData.teacher || {};
    },
    student() {
      return this.receivedData.student || {};
    },
    statusValue() {
      return this.receivedData.isAdd || "待审核";
    },
    statusText() {
      if (this.statusValue === "同意") {
        return "申请同意";
      }
      if (this.statusValue === "拒绝") {
        return "申请拒绝";
      }
      return "待审核";
    },
    statusClass() {
      if (this.statusValue === "同意") {
        return "course-status--success";
      }
      if (this.statusValue === "拒绝") {
        return "course-status--danger";
      }
      return "course-status--pending";
    }
  },
  created() {
    this.receivedData = this.$route.params || {};
    this.load();
  },
  methods: {
    load() {
      this.loading = true;
      request.get("exam/courseStudent/selectById/" + this.receivedData.id).then((res) => {
        if (res.code === "200") {
          this.receivedData = res.data || {};
        } else {
          this.$message.error(res.msg);
        }
      }).finally(() => {
        this.loading = false;
      });
    }
  }
};
</script>

<style scoped>
.student-course-detail-page {
  min-height: calc(100vh - 190px);
  color: #162033;
}

.course-detail-head {
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
  gap: 16px;
  margin-bottom: 18px;
}

.course-detail-head__title {
  margin: 0;
  font-size: 28px;
  line-height: 1.25;
  font-weight: 700;
  color: #0f172a;
}

.course-detail-head__desc {
  margin: 8px 0 0;
  font-size: 14px;
  line-height: 1.6;
  color: #64748b;
}

.course-detail-layout {
  display: grid;
  grid-template-columns: 360px minmax(0, 1fr);
  gap: 18px;
  align-items: start;
}

.people-column,
.course-column {
  display: grid;
  gap: 18px;
}

.person-panel,
.course-hero,
.course-intro {
  border: 1px solid #dfe7ef;
  border-radius: 8px;
  background: #ffffff;
  box-shadow: 0 14px 34px rgba(15, 23, 42, 0.06);
}

.person-panel {
  padding: 20px;
}

.person-panel__head,
.course-intro__head {
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
  gap: 12px;
  margin-bottom: 18px;
}

.person-panel__title,
.course-intro__title {
  margin: 0;
  font-size: 18px;
  line-height: 1.35;
  font-weight: 700;
  color: #0f172a;
}

.person-panel__subtitle,
.course-intro__desc {
  margin: 4px 0 0;
  font-size: 13px;
  line-height: 1.5;
  color: #64748b;
}

.person-profile {
  display: grid;
  grid-template-columns: 116px minmax(0, 1fr);
  gap: 16px;
  align-items: center;
  padding-bottom: 18px;
  border-bottom: 1px solid #eef2f6;
}

.person-profile__photo {
  width: 116px;
  height: 136px;
  border-radius: 8px;
  background: #f1f5f9;
  overflow: hidden;
}

.person-profile__name {
  font-size: 22px;
  line-height: 1.35;
  font-weight: 700;
  color: #0f172a;
  word-break: break-word;
}

.person-profile__role {
  margin-top: 8px;
  display: inline-flex;
  padding: 5px 10px;
  border-radius: 4px;
  background: #f0fdfa;
  color: #0f766e;
  font-size: 12px;
  font-weight: 700;
}

.info-list {
  display: grid;
  gap: 12px;
  margin: 18px 0 0;
}

.info-list__item {
  display: grid;
  grid-template-columns: 58px minmax(0, 1fr);
  gap: 12px;
  align-items: start;
}

.info-list dt {
  color: #64748b;
  font-size: 13px;
  line-height: 1.7;
}

.info-list dd {
  margin: 0;
  color: #172033;
  font-size: 15px;
  line-height: 1.7;
  word-break: break-word;
}

.course-status {
  flex: 0 0 auto;
  height: 32px;
  padding: 0 14px;
  border: none;
  border-radius: 4px;
  line-height: 32px;
  font-weight: 700;
}

.course-status--small {
  height: 28px;
  padding: 0 10px;
  line-height: 28px;
}

.course-status--success {
  background: #ecfdf5;
  color: #047857;
}

.course-status--pending {
  background: #eff6ff;
  color: #1d4ed8;
}

.course-status--danger {
  background: #fef2f2;
  color: #b91c1c;
}

.course-hero {
  display: grid;
  grid-template-columns: minmax(280px, 0.95fr) minmax(0, 1.35fr);
  min-height: 260px;
  overflow: hidden;
}

.course-hero__media {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 260px;
  padding: 18px;
  background: #f1f5f9;
}

.course-cover {
  display: block;
  width: 100%;
  height: 100%;
  min-height: 224px;
  border-radius: 8px;
  background: #eef3f8;
}

.course-hero__content {
  display: flex;
  flex-direction: column;
  justify-content: center;
  min-width: 0;
  padding: 30px 34px;
}

.course-hero__eyebrow {
  font-size: 12px;
  font-weight: 800;
  color: #0f766e;
}

.course-hero__title {
  margin: 10px 0 22px;
  font-size: 34px;
  line-height: 1.25;
  font-weight: 800;
  color: #0f172a;
  word-break: break-word;
}

.course-meta-grid {
  display: grid;
  grid-template-columns: repeat(2, minmax(0, 1fr));
  gap: 12px;
}

.course-meta {
  min-width: 0;
  padding: 12px 14px;
  border-radius: 8px;
  background: #f8fafc;
  border: 1px solid #e5edf4;
}

.course-meta span {
  display: block;
  margin-bottom: 6px;
  color: #64748b;
  font-size: 12px;
  line-height: 1.4;
}

.course-meta strong {
  display: block;
  color: #172033;
  font-size: 15px;
  line-height: 1.45;
  word-break: break-word;
}

.course-intro {
  min-height: 420px;
  padding: 22px;
}

.course-intro__head {
  margin-bottom: 14px;
  padding-bottom: 14px;
  border-bottom: 1px solid #eef2f6;
}

.course-intro__body {
  max-height: 420px;
  overflow-y: auto;
  padding: 4px 4px 6px 0;
  color: #334155;
  font-size: 15px;
  line-height: 1.8;
}

.course-intro__body::-webkit-scrollbar {
  width: 8px;
}

.course-intro__body::-webkit-scrollbar-thumb {
  border-radius: 8px;
  background: #cbd5e1;
}

.course-intro__empty,
.image-fallback {
  display: flex;
  align-items: center;
  justify-content: center;
  color: #94a3b8;
  background: #f8fafc;
}

.course-intro__empty {
  min-height: 260px;
  border-radius: 8px;
  border: 1px dashed #cbd5e1;
}

.image-fallback {
  width: 100%;
  height: 100%;
  font-size: 13px;
}

::v-deep(.course-intro__body img) {
  max-width: 100%;
  height: auto;
  border-radius: 8px;
}

@media (max-width: 1280px) {
  .course-detail-layout {
    grid-template-columns: 320px minmax(0, 1fr);
  }

  .course-hero {
    grid-template-columns: 1fr;
  }

  .course-hero__media {
    aspect-ratio: 16 / 7;
    min-height: 0;
  }
}

@media (max-width: 980px) {
  .course-detail-layout {
    grid-template-columns: 1fr;
  }

  .people-column {
    grid-template-columns: repeat(2, minmax(0, 1fr));
  }
}

@media (max-width: 720px) {
  .course-detail-head {
    flex-direction: column;
  }

  .people-column,
  .course-meta-grid {
    grid-template-columns: 1fr;
  }

  .person-profile {
    grid-template-columns: 96px minmax(0, 1fr);
  }

  .person-profile__photo {
    width: 96px;
    height: 112px;
  }

  .course-hero__content {
    padding: 24px 20px;
  }

  .course-hero__title {
    font-size: 28px;
  }
}
</style>
