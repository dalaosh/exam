<template>
  <div class="student-page student-password-page">
    <section class="student-page-head">
      <div>
        <h2 class="student-page-head__title">密码修改</h2>
      </div>
      <div class="student-page-head__meta">
        <el-tag class="student-tag" type="warning">分步验证</el-tag>
      </div>
    </section>

    <div class="student-password-layout">
      <section class="student-panel student-profile-card student-password-sidebar">
        <h3 class="student-section-title">修改步骤</h3>
        <div class="student-password-sidebar__steps">
          <el-steps direction="vertical" :active="active - 1">
            <el-step
              v-for="(item, index) in stepItems"
              :key="index"
              :title="item.title"
              :description="item.description"
            ></el-step>
          </el-steps>
        </div>
        <div class="student-password-sidebar__tip">
          当前只对已登录学生账号做密码重置，请确保邮箱仍可正常接收验证码。
        </div>
      </section>

      <section class="student-panel student-password-main">
        <div class="student-password-top">
          <div class="student-help-card student-password-account-card">
            <div class="student-help-card__title">
              <i class="el-icon-user-solid"></i>
              <span>账户信息</span>
            </div>
            <el-descriptions :column="2" border>
              <el-descriptions-item label="账号">
                <el-tag class="student-tag">{{ user.account }}</el-tag>
              </el-descriptions-item>
              <el-descriptions-item label="手机号">
                <el-tag class="student-tag" type="success">{{ user.phone }}</el-tag>
              </el-descriptions-item>
              <el-descriptions-item label="姓名">
                <el-tag class="student-tag" type="warning">{{ user.name }}</el-tag>
              </el-descriptions-item>
              <el-descriptions-item label="邮箱">
                <el-tag class="student-tag" type="danger">{{ user.email }}</el-tag>
              </el-descriptions-item>
            </el-descriptions>
          </div>

          <div class="student-help-card student-password-tips-card">
            <div class="student-help-card__title">
              <i class="el-icon-picture"></i>
              <span>安全提示</span>
            </div>
            <el-image class="student-password-tips-card__image" :src="src[0]" fit="cover"></el-image>
            <p class="student-password-tips-card__desc">
              建议在可信设备上完成验证和密码修改，结束后及时使用新密码重新登录。
            </p>
          </div>
        </div>

        <div class="student-password-stage">
          <div class="student-password-stage__meta">
            <div class="student-password-stage__meta-main">
              <div class="student-password-stage__eyebrow">当前步骤</div>
              <div class="student-password-stage__step">{{ currentStep.title }}</div>
              <p class="student-password-stage__step-note">{{ currentStep.description }}</p>
            </div>
            <div class="student-password-stage__counter">{{ active }}/{{ stepItems.length }}</div>
          </div>
          <div class="student-password-stage__progress">
            <span :style="stageProgressStyle"></span>
          </div>

          <div v-if="active === 1" class="student-password-stage__pane student-password-stage__pane--intro">
            <div class="student-password-stage__inner">
              <h3 class="student-password-stage__title">准备开始</h3>
              <p class="student-password-stage__desc">
                请按页面提示依次完成邮箱验证和密码重置，避免在公共环境下操作。
              </p>
            </div>
          </div>

          <div v-if="active === 2" class="student-password-stage__pane">
            <div class="student-password-stage__inner">
              <h3 class="student-password-stage__form-title">邮箱验证</h3>
              <el-form :model="form" :rules="ruleB" ref="formRef">
                <el-form-item prop="verCode">
                  <div class="student-password-inline">
                    <el-input
                      v-model="form.verCode"
                      prefix-icon="el-icon-picture-outline"
                      placeholder="请输入图形验证码"
                    ></el-input>
                    <img :src="captchaUrl" @click="clickImg()" width="140" height="40" />
                  </div>
                </el-form-item>
                <el-form-item prop="mailCode">
                  <div class="student-password-inline">
                    <el-input
                      v-model="form.mailCode"
                      prefix-icon="el-icon-message"
                      placeholder="请输入邮箱验证码"
                    ></el-input>
                    <el-button type="primary" plain @click="getMail()">获取验证码</el-button>
                  </div>
                </el-form-item>
              </el-form>
            </div>
          </div>

          <div v-if="active === 3" class="student-password-stage__pane">
            <div class="student-password-stage__inner">
              <h3 class="student-password-stage__form-title">重置密码</h3>
              <el-form :model="form" :rules="ruleC" ref="formRef">
                <el-form-item prop="password">
                  <el-input
                    v-model="form.password"
                    prefix-icon="el-icon-lock"
                    placeholder="请输入新密码"
                    show-password
                  ></el-input>
                </el-form-item>
                <el-form-item prop="confirmPassword">
                  <el-input
                    v-model="password"
                    prefix-icon="el-icon-lock"
                    placeholder="请再次输入新密码"
                    show-password
                  ></el-input>
                </el-form-item>
              </el-form>
            </div>
          </div>

          <div v-if="active === 4" class="student-password-stage__pane student-password-stage__pane--intro">
            <div class="student-password-stage__inner">
              <h3 class="student-password-stage__title">修改成功</h3>
              <p class="student-password-stage__desc">新密码已生效，请妥善保管并在后续登录中使用。</p>
            </div>
          </div>
        </div>

        <div class="student-password-actions">
          <div class="student-password-actions__note">只有邮箱验证通过后，系统才会提交新密码。</div>
          <div class="student-password-actions__group">
            <el-button round :disabled="active === 1" @click="over">上一步</el-button>
            <el-button round type="primary" :disabled="active === stepItems.length" @click="next">
              {{ button }}
            </el-button>
          </div>
        </div>
      </section>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
import back1 from "@/assets/forget/1.jpg";
import back2 from "@/assets/forget/2.jpg";
import back3 from "@/assets/forget/3.jpg";
import back4 from "@/assets/forget/4.jpg";

export default {
  name: "forget",
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      src: [back1, back2, back3, back4],
      active: 1,
      form: {
        role: "student"
      },
      button: "开始",
      buttons: ["开始", "验证", "修改", "完成"],
      key1: "",
      key2: "",
      captchaUrl: "",
      password: "",
      ruleB: {},
      ruleC: {}
    };
  },
  computed: {
    stepItems() {
      return [
        { title: "开始", description: "进入密码修改流程" },
        { title: "邮箱验证", description: "验证图形验证码并获取邮箱验证码" },
        { title: "重置密码", description: "输入并确认新的登录密码" },
        { title: "完成", description: "密码修改成功" }
      ];
    },
    currentStep() {
      return this.stepItems[this.active - 1] || this.stepItems[0];
    },
    stageProgressStyle() {
      const total = this.stepItems.length - 1;
      const percent = total > 0 ? ((this.active - 1) / total) * 100 : 0;
      return {
        width: `${percent}%`
      };
    }
  },
  created() {
    this.form = {
      ...this.user,
      role: "student"
    };
  },
  mounted() {
    this.clickImg();
    this.key2 = Math.random();
  },
  methods: {
    over() {
      if (this.active !== 1) {
        this.active -= 1;
        this.button = this.buttons[this.active - 1];
      }
    },
    ne() {
      if (this.active < this.stepItems.length) {
        this.active += 1;
        this.button = this.buttons[this.active - 1];
      }
    },
    next() {
      if (this.active === 1) {
        this.ne();
      } else if (this.active === 2) {
        request.post(`/user/forget/verify?key=${this.key2}`, this.form).then((res) => {
          if (res.code === "200") {
            this.$message.success("验证成功");
            this.ne();
          } else {
            this.$message.error(res.msg);
          }
        });
      } else if (this.active === 3) {
        if (this.form.password !== this.password) {
          this.$message.error("请输入相同的新密码");
          return;
        }
        this.form.id = this.user.id;
        request.post("/user/forget", this.form).then((res) => {
          if (res.code === "200") {
            this.$message.success("修改成功");
            this.ne();
          } else {
            this.$message.error(res.msg);
          }
        });
      }
    },
    clickImg() {
      this.key1 = Math.random();
      this.captchaUrl = `${process.env.VUE_APP_BASEURL}/user/login/captcha?key=${this.key1}`;
      this.form.verCode = "";
    },
    getMail() {
      if (!this.form.verCode) {
        this.$message.error("请输入验证码");
        return;
      }
      this.form.key1 = this.key1;
      request.post(`/user/forget/email?key=${this.key2}`, this.form).then((res) => {
        if (res.code === "200") {
          this.$message.success("发送成功");
        } else {
          this.$message.error(res.msg);
        }
      });
    }
  }
};
</script>

<style scoped>
.student-password-page.student-page {
  gap: 10px;
}

.student-password-page .student-page-head {
  align-items: center;
}

.student-password-page .student-page-head__title {
  font-size: 18px;
  line-height: 1.25;
}

.student-password-page .student-page-head__meta .student-tag {
  height: 24px;
  line-height: 24px;
}

.student-password-page .student-password-layout {
  align-items: stretch;
  grid-template-columns: 260px minmax(0, 1fr);
  gap: 14px;
}

.student-password-page .student-password-sidebar {
  display: flex;
  flex-direction: column;
  min-height: 100%;
  align-self: stretch;
  position: static;
  padding: 16px;
}

.student-password-page .student-password-sidebar__steps {
  flex: 1;
  min-height: 0;
  margin-top: 2px;
  overflow: hidden;
}

.student-password-page .student-password-sidebar__tip {
  margin-top: 10px;
  padding: 10px 12px;
  border-radius: 8px;
  background: linear-gradient(135deg, rgba(16, 185, 129, 0.1), rgba(59, 130, 246, 0.08));
  color: #475569;
  font-size: 12px;
  line-height: 1.45;
}

.student-password-page .student-password-top {
  align-items: stretch;
  grid-template-columns: minmax(0, 1.35fr) minmax(220px, 0.65fr);
  gap: 12px;
  margin-bottom: 12px;
}

.student-password-page .student-password-top > .student-help-card + .student-help-card {
  margin-top: 0;
}

.student-password-page .student-password-main {
  display: flex;
  flex-direction: column;
  min-height: 100%;
  padding: 16px;
}

.student-password-page .student-password-account-card,
.student-password-page .student-password-tips-card {
  height: 100%;
  padding: 12px;
}

.student-password-page .student-password-tips-card {
  display: flex;
  flex-direction: column;
}

.student-password-page .student-password-tips-card__image {
  width: 100%;
  height: 96px;
  border-radius: 6px;
}

.student-password-page .student-password-tips-card__desc {
  margin: 8px 0 0;
  color: #64748b;
  font-size: 12px;
  line-height: 1.45;
}

.student-password-page .student-password-stage {
  display: flex;
  flex: 1;
  flex-direction: column;
  min-height: 218px;
  padding: 14px 16px;
}

.student-password-page .student-password-stage__meta {
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
  gap: 16px;
}

.student-password-page .student-password-stage__eyebrow {
  font-size: 12px;
  font-weight: 700;
  letter-spacing: 0.08em;
  color: #0f766e;
}

.student-password-page .student-password-stage__step {
  margin-top: 4px;
  font-size: 18px;
  font-weight: 700;
  color: #0f172a;
}

.student-password-page .student-password-stage__step-note {
  margin: 4px 0 0;
  color: #64748b;
  font-size: 12px;
  line-height: 1.45;
}

.student-password-page .student-password-stage__counter {
  flex: 0 0 auto;
  min-width: 52px;
  padding: 7px 10px;
  border-radius: 8px;
  background: #eff6ff;
  color: #1d4ed8;
  text-align: center;
  font-size: 16px;
  font-weight: 700;
}

.student-password-page .student-password-stage__progress {
  margin-top: 10px;
  height: 6px;
  border-radius: 999px;
  background: #e2e8f0;
  overflow: hidden;
}

.student-password-page .student-password-stage__progress span {
  display: block;
  height: 100%;
  border-radius: inherit;
  background: linear-gradient(90deg, #10b981, #0ea5e9);
  transition: width 0.25s ease;
}

.student-password-page .student-password-stage__pane {
  display: flex;
  flex: 1;
  align-items: flex-start;
  justify-content: center;
  min-height: 132px;
  padding: 14px 0 0;
}

.student-password-page .student-password-stage__pane--intro {
  align-items: center;
  min-height: 132px;
}

.student-password-page .student-password-stage__inner {
  width: 100%;
  max-width: 520px;
}

.student-password-page .student-password-stage__title,
.student-password-page .student-password-stage__form-title {
  margin-bottom: 12px;
  font-size: 20px;
}

.student-password-page .student-password-stage__desc {
  font-size: 13px;
  line-height: 1.55;
}

.student-password-page .student-password-inline {
  display: grid;
  grid-template-columns: minmax(0, 1fr) auto;
  gap: 12px;
  align-items: center;
}

.student-password-page .student-password-inline img,
.student-password-page .student-password-inline .el-button {
  height: 36px;
  border-radius: 6px;
}

.student-password-page .student-password-inline img {
  width: 140px;
  object-fit: cover;
  border: 1px solid #dbe4ee;
  background: #f8fafc;
  cursor: pointer;
}

.student-password-page :deep(.el-form-item) {
  margin-bottom: 12px;
}

.student-password-page :deep(.el-descriptions) {
  height: 100%;
}

.student-password-page :deep(.el-descriptions__body .el-descriptions__table) {
  height: 100%;
}

.student-password-page :deep(.el-descriptions-item__cell) {
  padding: 8px 10px;
}

.student-password-page :deep(.el-steps--vertical) {
  gap: 4px;
}

.student-password-page :deep(.el-step__title) {
  font-size: 14px;
  font-weight: 700;
  line-height: 1.35;
}

.student-password-page :deep(.el-step__description) {
  padding-bottom: 6px;
  font-size: 12px;
  line-height: 1.35;
}

.student-password-page :deep(.el-step__head) {
  width: 28px;
}

.student-password-page :deep(.el-step__icon) {
  width: 22px;
  height: 22px;
  font-size: 12px;
}

.student-password-page :deep(.el-step.is-process .el-step__head) {
  color: #0f766e;
  border-color: #10b981;
}

.student-password-page :deep(.el-step.is-finish .el-step__head) {
  color: #0ea5e9;
  border-color: #0ea5e9;
}

.student-password-page .student-password-actions {
  margin-top: auto;
  padding-top: 10px;
  justify-content: space-between;
}

.student-password-page .student-password-actions__note {
  color: #64748b;
  font-size: 12px;
  line-height: 1.45;
}

.student-password-page .student-password-actions__group {
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  gap: 10px;
}

.student-password-page .student-password-actions .el-button {
  min-width: 96px;
  height: 36px;
}

@media (max-width: 1280px) {
  .student-password-page .student-password-layout {
    grid-template-columns: 230px minmax(0, 1fr);
  }

  .student-password-page .student-password-sidebar {
    position: static;
  }

  .student-password-page .student-password-stage__meta,
  .student-password-page .student-password-actions {
    flex-direction: column;
    align-items: stretch;
  }

  .student-password-page .student-password-actions__group {
    justify-content: flex-end;
  }
}

@media (max-width: 1100px) {
  .student-password-page .student-password-layout,
  .student-password-page .student-password-top {
    grid-template-columns: 1fr;
  }

  .student-password-page .student-password-sidebar,
  .student-password-page .student-password-top {
    display: none;
  }

  .student-password-page .student-password-main {
    padding: 12px;
  }

  .student-password-page .student-password-stage {
    min-height: 180px;
  }

  .student-password-page .student-password-stage__pane,
  .student-password-page .student-password-stage__pane--intro {
    min-height: 96px;
  }

  .student-password-page .student-password-sidebar__steps,
  .student-password-page .student-password-sidebar__tip {
    display: none;
  }
}

@media (max-width: 768px) {
  .student-password-page .student-password-top,
  .student-password-page .student-password-inline {
    grid-template-columns: 1fr;
  }

  .student-password-page .student-password-inline img,
  .student-password-page .student-password-inline .el-button {
    width: 100%;
  }
}
</style>
