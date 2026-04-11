<template>
  <div class="exam-entry-page">
    <section class="exam-entry-hero">
      <div class="exam-entry-hero__main">
        <el-button type="text" class="exam-entry-hero__back" @click="end">返回考试列表</el-button>
        <div class="exam-entry-hero__eyebrow">考试认证</div>
        <h1 class="exam-entry-hero__title">{{ form.course && form.course.name ? form.course.name : "考试" }}</h1>
        <p class="exam-entry-hero__desc">完成拍照认证后即可进入答题页面，右侧保留考试须知和开考状态。</p>
      </div>

      <div class="exam-entry-hero__meta">
        <div class="exam-entry-stat">
          <span class="exam-entry-stat__label">当前时间</span>
          <strong>{{ nowTime || "--" }}</strong>
        </div>
        <div class="exam-entry-stat">
          <span class="exam-entry-stat__label">总分</span>
          <strong>{{ form.totalScore || 0 }} 分</strong>
        </div>
        <div class="exam-entry-stat">
          <span class="exam-entry-stat__label">时长</span>
          <strong>{{ form.totalTime || 0 }} 分钟</strong>
        </div>
        <div class="exam-entry-stat exam-entry-stat--status">
          <span class="exam-entry-stat__label">考试状态</span>
          <el-tag :type="examStatusTagType" effect="dark">{{ examStatusLabel }}</el-tag>
        </div>
      </div>
    </section>

    <section class="exam-entry-summary">
      <div class="exam-entry-summary__item">
        <span>开考时间</span>
        <strong>{{ form.examDate || "--" }}</strong>
      </div>
      <div class="exam-entry-summary__item">
        <span>认证状态</span>
        <strong>{{ verificationStatusLabel }}</strong>
      </div>
      <div class="exam-entry-summary__item exam-entry-summary__item--wide">
        <span>状态说明</span>
        <strong>{{ countdownText }}</strong>
      </div>
    </section>

    <section class="exam-entry-content">
      <div class="exam-entry-main">
        <article class="exam-panel exam-panel--auth">
          <div class="exam-panel__head">
            <div>
              <h2 class="exam-panel__title">考前认证</h2>
              <p class="exam-panel__desc">先开启摄像头拍照，再执行人脸认证。</p>
            </div>
          </div>

          <div class="exam-capture-grid">
            <section class="exam-capture-card">
              <div class="exam-capture-card__head">
                <h3>摄像头画面</h3>
                <div class="exam-capture-card__actions">
                  <el-button type="primary" plain @click="openCamera" :disabled="cameraEnabled">开启</el-button>
                  <el-button type="danger" plain @click="closeCamera" :disabled="!cameraEnabled">关闭</el-button>
                  <el-button type="success" plain @click="takePhoto" :disabled="!cameraEnabled">拍照</el-button>
                </div>
              </div>

              <div class="exam-media-frame">
                <video ref="video" v-show="cameraEnabled" autoplay class="exam-media-frame__video"></video>
                <div v-show="!cameraEnabled" class="exam-media-frame__placeholder">
                  摄像头未开启
                </div>
              </div>
            </section>

            <section class="exam-capture-card">
              <div class="exam-capture-card__head">
                <h3>拍照预览</h3>
                <el-button type="primary" @click="insertMessage">人脸认证</el-button>
              </div>

              <div class="exam-media-frame">
                <el-image
                  v-if="capturedPhoto"
                  class="exam-media-frame__image"
                  :src="capturedPhoto"
                  fit="contain"
                />
                <div v-else class="exam-media-frame__placeholder">等待拍照</div>
              </div>
            </section>
          </div>
        </article>

        <article class="exam-panel exam-panel--profile">
          <div class="exam-panel__head">
            <div>
              <h2 class="exam-panel__title">考生资料</h2>
              <p class="exam-panel__desc">认证结果和考生基本信息会显示在这里。</p>
            </div>
          </div>

          <div class="exam-profile">
            <div class="exam-profile__media">
              <el-image
                v-if="faceData.student && faceData.student.photo"
                class="exam-profile__avatar"
                :src="faceData.student.photo"
                fit="cover"
              />
              <div v-else class="exam-profile__avatar exam-profile__placeholder">暂无头像</div>
            </div>

            <div class="exam-profile__info">
              <div class="exam-profile__grid">
                <div class="exam-profile__item">
                  <span>匹配度</span>
                  <strong>{{ faceData.similarValue || "--" }}</strong>
                </div>
                <div class="exam-profile__item">
                  <span>认证结果</span>
                  <el-tag :type="verificationTagType">{{ verificationStatusLabel }}</el-tag>
                </div>
                <div class="exam-profile__item">
                  <span>账号</span>
                  <strong>{{ faceData.student && faceData.student.account ? faceData.student.account : "--" }}</strong>
                </div>
                <div class="exam-profile__item">
                  <span>手机号</span>
                  <strong>{{ faceData.student && faceData.student.phone ? faceData.student.phone : "--" }}</strong>
                </div>
                <div class="exam-profile__item">
                  <span>姓名</span>
                  <strong>{{ faceData.student && faceData.student.name ? faceData.student.name : user.name || "--" }}</strong>
                </div>
                <div class="exam-profile__item">
                  <span>邮箱</span>
                  <strong>{{ faceData.student && faceData.student.email ? faceData.student.email : "--" }}</strong>
                </div>
              </div>
            </div>
          </div>
        </article>
      </div>

      <aside class="exam-entry-side">
        <article class="exam-panel exam-panel--tips">
          <div class="exam-panel__head">
            <div>
              <h2 class="exam-panel__title">考试须知</h2>
              <p class="exam-panel__desc">进入答题前，请确认已阅读以下内容。</p>
            </div>
          </div>

          <div class="exam-tips-body">
            <div v-html="form.tips" class="w-e-text"></div>
          </div>
        </article>

        <article class="exam-panel exam-panel--action">
          <div class="exam-panel__head exam-panel__head--compact">
            <div>
              <h2 class="exam-panel__title">进入考试</h2>
              <p class="exam-panel__desc">只有在开考时间内并完成认证后才能进入。</p>
            </div>
          </div>

          <div class="exam-action-box">
            <div class="exam-action-box__status">
              <span>当前状态</span>
              <strong>{{ countdownText }}</strong>
            </div>

            <el-button
              v-if="examStatus === 'before'"
              disabled
              type="primary"
              round
              class="exam-action-box__button"
            >
              考试尚未开始
            </el-button>
            <el-button
              v-else-if="examStatus === 'ongoing'"
              type="primary"
              round
              class="exam-action-box__button"
              @click="submit"
            >
              开始考试
            </el-button>
            <el-button
              v-else
              disabled
              type="primary"
              round
              class="exam-action-box__button"
            >
              考试已结束
            </el-button>
          </div>
        </article>
      </aside>
    </section>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  data() {
    return {
      nowTime: "",
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      receivedData: "",
      form: {},
      dataTime: {},
      clockTimer: null,
      countdownTimer: null,
      examStatus: "before",
      student: {},
      cameraEnabled: false,
      capturedPhoto: "",
      mediaStream: null,
      studentFile: "",
      sessionVerificationState: "idle",
      faceData: {
        student: {},
      },
    };
  },
  computed: {
    examStatusLabel() {
      if (this.examStatus === "before") {
        return "待开考";
      }
      if (this.examStatus === "ongoing") {
        return "进行中";
      }
      return "已结束";
    },
    examStatusTagType() {
      if (this.examStatus === "before") {
        return "warning";
      }
      if (this.examStatus === "ongoing") {
        return "success";
      }
      return "info";
    },
    verificationStatusLabel() {
      if (this.student.faceSubmit === "教师代理") {
        return "教师代理";
      }
      if (this.hasVerifiedFaceInSession) {
        return "验证成功";
      }
      if (this.sessionVerificationState === "failed") {
        return "验证失败";
      }
      return "待认证";
    },
    verificationTagType() {
      if (this.student.faceSubmit === "教师代理" || this.hasVerifiedFaceInSession) {
        return "success";
      }
      if (this.sessionVerificationState === "failed") {
        return "danger";
      }
      return "warning";
    },
    hasVerifiedFaceInSession() {
      return this.sessionVerificationState === "success";
    },
    countdownText() {
      if (this.examStatus === "before") {
        return `距开考还有 ${this.dataTime.day || 0}天 ${this.dataTime.hour || 0}时 ${this.dataTime.minute || 0}分 ${this.dataTime.second || 0}秒`;
      }
      if (this.examStatus === "ongoing") {
        return "考试正在进行，可进入答题。";
      }
      return "考试时间已结束。";
    },
  },
  created() {
    this.receivedData = this.$route.params;
    this.loadExam();
    this.loadStudent();
  },
  mounted() {
    this.startClock();
  },
  beforeDestroy() {
    if (this.clockTimer) {
      clearInterval(this.clockTimer);
    }
    if (this.countdownTimer) {
      clearInterval(this.countdownTimer);
    }
    this.closeCamera();
  },
  methods: {
    startClock() {
      if (this.clockTimer) {
        clearInterval(this.clockTimer);
      }
      this.clockTimer = setInterval(() => {
        const timeDate = new Date();
        const year = timeDate.getFullYear();
        const month = timeDate.getMonth() + 1;
        const day = timeDate.getDate();
        let hours = timeDate.getHours();
        hours = hours >= 10 ? hours : "0" + hours;
        let minutes = timeDate.getMinutes();
        minutes = minutes >= 10 ? minutes : "0" + minutes;
        let seconds = timeDate.getSeconds();
        seconds = seconds >= 10 ? seconds : "0" + seconds;
        const week = timeDate.getDay();
        const weekArr = ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];
        this.nowTime = `${year}-${month}-${day} ${hours}:${minutes}:${seconds} ${weekArr[week]}`;
      }, 1000);
    },
    loadExam() {
      request.get("exam/examManage/selectById/" + this.receivedData.id).then((res) => {
        if (res.code === "200") {
          this.form = res.data;
          this.calculateRemainingTime();
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    loadStudent() {
      request.get("exam/examStudent/selectById/" + this.receivedData.examStudent).then((res) => {
        if (res.code === "200") {
          this.student = res.data;
          this.calculateRemainingTime();
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    calculateRemainingTime() {
      if (!this.form.examDate) {
        return;
      }

      if (this.countdownTimer) {
        clearInterval(this.countdownTimer);
      }

      const updateCountdown = () => {
        const examStartDate = new Date(this.form.examDate);
        const examEndDate = new Date(examStartDate.getTime() + this.form.totalTime * 60 * 1000);
        const now = new Date();
        const diffStart = examStartDate.getTime() - now.getTime();
        const diffEnd = examEndDate.getTime() - now.getTime();

        if (diffStart > 0) {
          this.examStatus = "before";
          let seconds = Math.floor(diffStart / 1000);
          let minutes = Math.floor(seconds / 60);
          let hours = Math.floor(minutes / 60);
          const days = Math.floor(hours / 24);
          hours %= 24;
          minutes %= 60;
          seconds %= 60;

          this.dataTime = {
            day: days,
            hour: hours,
            minute: minutes,
            second: seconds,
          };
        } else if (diffEnd > 0) {
          this.examStatus = "ongoing";
          this.dataTime = {
            day: 0,
            hour: 0,
            minute: 0,
            second: 0,
          };
        } else {
          this.examStatus = "ended";
          this.dataTime = {
            day: 0,
            hour: 0,
            minute: 0,
            second: 0,
          };
        }
      };

      updateCountdown();
      this.countdownTimer = setInterval(updateCountdown, 1000);
    },
    submit() {
      if (!this.hasVerifiedFaceInSession && this.student.faceSubmit !== "教师代理") {
        this.$message.warning("请先完成人脸认证，如认证失败请联系任课教师");
        return;
      }

      this.$router.push({
        name: "studentAnswer",
        params: {
          id: this.receivedData.id,
        },
      });
    },
    end() {
      this.$router.push({
        name: "studentExamList",
      });
    },
    async openCamera() {
      try {
        const stream = await navigator.mediaDevices.getUserMedia({
          video: { width: 640, height: 480 },
        });
        this.mediaStream = stream;
        this.$refs.video.srcObject = stream;
        this.cameraEnabled = true;
      } catch (error) {
        this.$message.error("无法访问摄像头: " + error.message);
      }
    },
    closeCamera() {
      if (this.mediaStream) {
        this.mediaStream.getTracks().forEach((track) => track.stop());
        this.mediaStream = null;
      }
      this.cameraEnabled = false;
      if (this.$refs.video) {
        this.$refs.video.srcObject = null;
      }
    },
    takePhoto() {
      const video = this.$refs.video;
      const canvas = document.createElement("canvas");
      canvas.width = video.videoWidth;
      canvas.height = video.videoHeight;
      const ctx = canvas.getContext("2d");
      ctx.drawImage(video, 0, 0, canvas.width, canvas.height);
      const base64Data = canvas.toDataURL("image/jpeg", 0.8);
      this.studentFile = this.processBase64(base64Data);
      this.capturedPhoto = base64Data;
    },
    processBase64(base64Str) {
      if (!base64Str) return "";
      const parts = base64Str.split(",");
      return parts.length > 1 ? parts[1] : base64Str;
    },
    insertMessage() {
      if (!this.studentFile) {
        this.$message.warning("请先拍照后再进行认证");
        return;
      }

      request
        .post("/trustSeal/studentFace/faceSearch", {
          file: this.studentFile,
        })
        .then((res) => {
          if (res.code === "200") {
            this.faceData = res.data;
            if (res.data.studentId === this.user.id) {
              this.sessionVerificationState = "success";
              this.examStudentUpdate("验证成功");
            } else {
              this.sessionVerificationState = "failed";
              this.$message.error("认证失败");
              this.examStudentUpdate("验证失败");
            }
          } else {
            this.sessionVerificationState = "failed";
            this.$message.error(res.msg);
          }
        });
    },
    examStudentUpdate(submit) {
      request
        .put("/exam/examStudent/updateSubmit", {
          id: this.receivedData.examStudent,
          facePath: this.studentFile,
          faceSubmit: submit,
        })
        .then((res) => {
          if (res.code === "200") {
            this.$message.success("认证状态已更新");
            this.loadStudent();
          } else {
            this.$message.error(res.msg);
          }
        });
    },
  },
};
</script>

<style scoped lang="scss">
.exam-entry-page {
  min-height: 100vh;
  padding: 24px;
  background:
    radial-gradient(circle at top right, rgba(37, 99, 235, 0.08), transparent 24%),
    linear-gradient(180deg, #eff5ff 0%, #f8fbff 100%);
}

.exam-entry-hero,
.exam-entry-summary,
.exam-panel {
  border: 1px solid rgba(226, 232, 240, 0.95);
  border-radius: 24px;
  background: rgba(255, 255, 255, 0.96);
  box-shadow: 0 18px 44px rgba(15, 23, 42, 0.08);
}

.exam-entry-hero {
  display: grid;
  grid-template-columns: minmax(0, 1.25fr) minmax(360px, 0.95fr);
  gap: 20px;
  padding: 24px 26px;
}

.exam-entry-hero__main {
  display: grid;
  gap: 10px;
}

.exam-entry-hero__back {
  justify-self: start;
  padding: 0;
  font-weight: 700;
}

.exam-entry-hero__eyebrow {
  font-size: 12px;
  font-weight: 700;
  letter-spacing: 0.14em;
  text-transform: uppercase;
  color: #2563eb;
}

.exam-entry-hero__title {
  margin: 0;
  font-size: 30px;
  line-height: 1.2;
  color: #0f172a;
}

.exam-entry-hero__desc {
  margin: 0;
  max-width: 760px;
  font-size: 14px;
  line-height: 1.7;
  color: #64748b;
}

.exam-entry-hero__meta {
  display: grid;
  grid-template-columns: repeat(2, minmax(0, 1fr));
  gap: 14px;
}

.exam-entry-stat {
  display: grid;
  gap: 8px;
  padding: 16px 18px;
  border: 1px solid #e2e8f0;
  border-radius: 18px;
  background: linear-gradient(180deg, #ffffff 0%, #f8fafc 100%);
}

.exam-entry-stat strong {
  font-size: 18px;
  color: #0f172a;
}

.exam-entry-stat__label {
  font-size: 12px;
  font-weight: 600;
  color: #64748b;
}

.exam-entry-stat--status {
  align-content: start;
}

.exam-entry-summary {
  display: grid;
  grid-template-columns: repeat(3, minmax(0, 1fr));
  gap: 16px;
  margin-top: 18px;
  padding: 18px 22px;
}

.exam-entry-summary__item {
  display: grid;
  gap: 8px;
}

.exam-entry-summary__item span {
  font-size: 12px;
  font-weight: 600;
  color: #64748b;
}

.exam-entry-summary__item strong {
  font-size: 15px;
  line-height: 1.6;
  color: #0f172a;
}

.exam-entry-summary__item--wide {
  grid-column: span 1;
}

.exam-entry-content {
  display: grid;
  grid-template-columns: minmax(0, 1.18fr) minmax(360px, 0.82fr);
  gap: 20px;
  margin-top: 18px;
}

.exam-entry-main,
.exam-entry-side {
  display: grid;
  gap: 20px;
  align-content: start;
}

.exam-panel {
  padding: 22px;
}

.exam-panel__head {
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
  gap: 14px;
  margin-bottom: 18px;
}

.exam-panel__head--compact {
  margin-bottom: 16px;
}

.exam-panel__title {
  margin: 0;
  font-size: 20px;
  font-weight: 700;
  color: #0f172a;
}

.exam-panel__desc {
  margin: 6px 0 0;
  font-size: 13px;
  line-height: 1.7;
  color: #64748b;
}

.exam-capture-grid {
  display: grid;
  grid-template-columns: repeat(2, minmax(0, 1fr));
  gap: 18px;
}

.exam-capture-card {
  display: grid;
  gap: 14px;
  padding: 18px;
  border: 1px solid #e2e8f0;
  border-radius: 20px;
  background: linear-gradient(180deg, #ffffff 0%, #f8fafc 100%);
}

.exam-capture-card__head {
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
  gap: 14px;
  flex-wrap: wrap;
}

.exam-capture-card__head h3 {
  margin: 0;
  font-size: 16px;
  font-weight: 700;
  color: #0f172a;
}

.exam-capture-card__actions {
  display: flex;
  align-items: center;
  gap: 8px;
  flex-wrap: wrap;
}

.exam-media-frame {
  height: 300px;
  border: 1px dashed #60a5fa;
  border-radius: 18px;
  overflow: hidden;
  background: rgba(239, 246, 255, 0.82);
}

.exam-media-frame__video,
.exam-media-frame__image {
  width: 100%;
  height: 100%;
}

.exam-media-frame__image :deep(.el-image__inner),
.exam-media-frame__image :deep(.el-image__error),
.exam-profile__avatar :deep(.el-image__inner),
.exam-profile__avatar :deep(.el-image__error) {
  width: 100%;
  height: 100%;
}

.exam-media-frame__image :deep(.el-image__error),
.exam-profile__avatar :deep(.el-image__error) {
  display: flex;
  align-items: center;
  justify-content: center;
  background: rgba(239, 246, 255, 0.82);
}

.exam-media-frame__placeholder {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 100%;
  color: #64748b;
  font-size: 18px;
  font-weight: 600;
  text-align: center;
}

.exam-profile {
  display: grid;
  grid-template-columns: 220px minmax(0, 1fr);
  gap: 20px;
  align-items: start;
}

.exam-profile__avatar {
  width: 100%;
  height: 240px;
  border-radius: 18px;
  overflow: hidden;
  background: #f8fafc;
}

.exam-profile__placeholder {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 100%;
  color: #64748b;
  font-size: 18px;
  font-weight: 600;
  text-align: center;
}

.exam-profile__grid {
  display: grid;
  grid-template-columns: repeat(2, minmax(0, 1fr));
  gap: 14px;
}

.exam-profile__item {
  display: grid;
  gap: 8px;
  padding: 14px 16px;
  border-radius: 16px;
  background: #f8fafc;
  border: 1px solid #e2e8f0;
}

.exam-profile__item span {
  font-size: 12px;
  font-weight: 600;
  color: #64748b;
}

.exam-profile__item strong {
  color: #0f172a;
  line-height: 1.6;
  word-break: break-word;
}

.exam-tips-body {
  min-height: 520px;
  max-height: 720px;
  padding: 18px 20px;
  border: 1px solid #e2e8f0;
  border-radius: 18px;
  background: linear-gradient(180deg, #ffffff 0%, #f8fafc 100%);
  overflow: auto;
  font-size: 15px;
  line-height: 1.9;
  color: #334155;
}

.exam-action-box {
  display: grid;
  gap: 16px;
}

.exam-action-box__status {
  display: grid;
  gap: 8px;
  padding: 16px 18px;
  border-radius: 18px;
  background: #f8fafc;
  border: 1px solid #e2e8f0;
}

.exam-action-box__status span {
  font-size: 12px;
  font-weight: 600;
  color: #64748b;
}

.exam-action-box__status strong {
  color: #0f172a;
  line-height: 1.7;
}

.exam-action-box__button {
  width: 100%;
  height: 48px;
  font-size: 16px;
  font-weight: 700;
}

:deep(.el-button) {
  border-radius: 12px;
  font-weight: 600;
}

:deep(.el-tag) {
  border-radius: 999px;
  font-weight: 700;
}

@media (max-width: 1440px) {
  .exam-entry-hero,
  .exam-entry-content {
    grid-template-columns: 1fr;
  }
}

@media (max-width: 1100px) {
  .exam-entry-summary,
  .exam-capture-grid,
  .exam-profile,
  .exam-profile__grid {
    grid-template-columns: 1fr;
  }

  .exam-entry-hero__meta {
    grid-template-columns: 1fr 1fr;
  }
}

@media (max-width: 900px) {
  .exam-entry-page {
    padding: 18px;
  }

  .exam-entry-hero__meta {
    grid-template-columns: 1fr;
  }

  .exam-entry-hero__title {
    font-size: 24px;
  }

  .exam-media-frame {
    height: 240px;
  }

  .exam-tips-body {
    min-height: 360px;
  }
}
</style>
