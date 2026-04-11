<template>
  <div class="teacher-page teacher-password-page">
    <section class="teacher-page-head">
      <div>
        <h2 class="teacher-page-head__title">密码修改</h2>
        <p class="teacher-page-head__desc">通过图形验证码和邮箱验证码完成教师账号密码重置，保留原有校验和提交流程。</p>
      </div>
      <div class="teacher-page-head__meta">
        <el-tag class="teacher-tag" type="warning">分步验证</el-tag>
      </div>
    </section>

    <div class="teacher-password-layout">
      <aside class="teacher-panel teacher-password-sidebar">
        <div class="teacher-password-sidebar__head">
          <h3>修改步骤</h3>
          <span>{{ active }}/{{ stepItems.length }}</span>
        </div>
        <p class="teacher-password-sidebar__desc">请按步骤完成验证码校验和密码重置，流程中不会修改教师端其他资料。</p>
        <div class="teacher-password-sidebar__steps">
          <el-steps direction="vertical" :active="active - 1">
            <el-step
              v-for="(item, index) in stepItems"
              :key="index"
              :title="item.title"
              :description="item.description"
            ></el-step>
          </el-steps>
        </div>
        <div class="teacher-password-sidebar__tip">
          仅当前已登录教师账号可使用此页面重置密码，请确保邮箱和验证码接收能力正常。
        </div>
      </aside>

      <section class="teacher-panel teacher-password-main">
        <div class="teacher-password-top">
          <article class="teacher-password-card teacher-password-account">
            <div class="teacher-password-card__head">
              <i class="el-icon-user-solid"></i>
              <span>账号信息</span>
            </div>
            <el-descriptions :column="2" border>
              <el-descriptions-item label="账号">
                <el-tag class="teacher-tag">{{ user.account }}</el-tag>
              </el-descriptions-item>
              <el-descriptions-item label="手机号">
                <el-tag class="teacher-tag" type="success">{{ user.phone }}</el-tag>
              </el-descriptions-item>
              <el-descriptions-item label="姓名">
                <el-tag class="teacher-tag" type="warning">{{ user.name }}</el-tag>
              </el-descriptions-item>
              <el-descriptions-item label="邮箱">
                <el-tag class="teacher-tag" type="danger">{{ user.email }}</el-tag>
              </el-descriptions-item>
            </el-descriptions>
          </article>

          <article class="teacher-password-card teacher-password-banner">
            <div class="teacher-password-card__head">
              <i class="el-icon-picture-outline"></i>
              <span>安全提醒</span>
            </div>
            <el-image class="teacher-password-banner__image" :src="src[Math.max(active - 1, 0)]" fit="cover"></el-image>
            <p class="teacher-password-banner__desc">建议在可信设备上完成邮箱验证和密码更新，修改完成后使用新密码重新登录。</p>
          </article>
        </div>

        <div class="teacher-password-stage">
          <div class="teacher-password-stage__meta">
            <div>
              <div class="teacher-password-stage__eyebrow">当前步骤</div>
              <div class="teacher-password-stage__title">{{ currentStep.title }}</div>
              <p class="teacher-password-stage__desc">{{ currentStep.description }}</p>
            </div>
            <div class="teacher-password-stage__counter">{{ active }}/{{ stepItems.length }}</div>
          </div>

          <div class="teacher-password-stage__progress">
            <span :style="stageProgressStyle"></span>
          </div>

          <div v-if="active === 1" class="teacher-password-stage__pane teacher-password-stage__pane--intro">
            <div class="teacher-password-stage__inner">
              <h3>准备开始</h3>
              <p>请按页面提示依次完成验证码校验和密码重置，避免在公共环境下操作。</p>
            </div>
          </div>

          <div v-if="active === 2" class="teacher-password-stage__pane">
            <div class="teacher-password-stage__inner">
              <h3 class="teacher-password-stage__form-title">邮箱验证</h3>
              <el-form :model="form" :rules="ruleB" ref="formRef">
                <el-form-item prop="verCode">
                  <div class="teacher-password-inline">
                    <el-input
                      v-model="form.verCode"
                      prefix-icon="el-icon-picture-outline"
                      placeholder="请输入图形验证码"
                    ></el-input>
                    <img :src="captchaUrl" @click="clickImg()" alt="验证码" />
                  </div>
                </el-form-item>
                <el-form-item prop="mailCode">
                  <div class="teacher-password-inline">
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

          <div v-if="active === 3" class="teacher-password-stage__pane">
            <div class="teacher-password-stage__inner">
              <h3 class="teacher-password-stage__form-title">重置密码</h3>
              <el-form :model="form" :rules="ruleC" ref="formRef">
                <el-form-item prop="password">
                  <el-input
                    v-model="form.password"
                    prefix-icon="el-icon-lock"
                    placeholder="请输入新的登录密码"
                    show-password
                  ></el-input>
                </el-form-item>
                <el-form-item prop="confirmPassword">
                  <el-input
                    v-model="password"
                    prefix-icon="el-icon-lock"
                    placeholder="请再次输入新的登录密码"
                    show-password
                  ></el-input>
                </el-form-item>
              </el-form>
            </div>
          </div>

          <div v-if="active === 4" class="teacher-password-stage__pane teacher-password-stage__pane--intro">
            <div class="teacher-password-stage__inner">
              <h3>修改成功</h3>
              <p>教师账号新密码已经生效，请妥善保管并在后续登录中使用。</p>
            </div>
          </div>
        </div>

        <div class="teacher-password-actions">
          <div class="teacher-password-actions__note">只有邮箱验证通过后，系统才会提交新的登录密码。</div>
          <div class="teacher-password-actions__group">
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
        role: "teacher"
      },
      button: "开始",
      buttons: ["开始", "验证", "修改", "完成"],
      ruleA: {
        account: [{ required: true, message: "请输入账号", trigger: "blur" }],
        phone: [{ required: true, message: "请输入手机号", trigger: "blur" }]
      },
      ruleB: {},
      ruleC: {},
      key1: "",
      key2: "",
      captchaUrl: "",
      emailUrl: "",
      password: ""
    };
  },
  computed: {
    stepItems() {
      return [
        { title: "开始", description: "进入教师账号密码修改流程" },
        { title: "邮箱验证", description: "完成图形验证码和邮箱验证码校验" },
        { title: "重置密码", description: "输入并确认新的登录密码" },
        { title: "完成", description: "教师密码修改成功" }
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
      role: "teacher"
    };
  },
  mounted() {
    this.key1 = Math.random();
    this.key2 = Math.random();
    this.captchaUrl = process.env.VUE_APP_BASEURL + "/user/login/captcha?key=" + this.key1;
  },
  methods: {
    over() {
      if (this.active !== 1) {
        this.active -= 1;
        this.button = this.buttons[this.active - 1];
      }
    },
    ne() {
      this.active += 1;
      this.button = this.buttons[this.active - 1];
    },
    next() {
      if (this.active === 1) {
        this.ne();
      } else if (this.active === 2) {
        request.post("/user/forget/verify?key=" + this.key2, this.form).then((res) => {
          if (res.code === "200") {
            this.$message.success("验证成功");
            this.ne();
          } else {
            this.$message.error(res.msg);
          }
        });
      } else if (this.active === 3) {
        if (this.form.password !== this.password) {
          this.$message.error("请正确输入");
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
      this.captchaUrl = process.env.VUE_APP_BASEURL + "/user/login/captcha?key=" + this.key1;
      this.form.verCode = "";
    },
    getMail() {
      if (this.form.verCode === null || this.form.verCode === "") {
        this.$message.error("请输入验证码");
        return;
      }
      this.form.key1 = this.key1;
      request.post("/user/forget/email?key=" + this.key2, this.form).then((res) => {
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
.teacher-password-layout {
  display: grid;
  grid-template-columns: 300px minmax(0, 1fr);
  gap: 18px;
  align-items: stretch;
}

.teacher-password-sidebar,
.teacher-password-main {
  min-height: 100%;
}

.teacher-password-sidebar {
  display: flex;
  flex-direction: column;
  padding: 20px;
}

.teacher-password-sidebar__head {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 12px;
}

.teacher-password-sidebar__head h3 {
  margin: 0;
  font-size: 18px;
  font-weight: 700;
  color: #0f172a;
}

.teacher-password-sidebar__head span {
  min-width: 58px;
  padding: 8px 10px;
  border-radius: 12px;
  background: #ecfdf5;
  color: #0f766e;
  text-align: center;
  font-weight: 700;
}

.teacher-password-sidebar__desc {
  margin: 12px 0 0;
  color: #6b7280;
  line-height: 1.7;
}

.teacher-password-sidebar__steps {
  flex: 1;
  min-height: 0;
  margin-top: 16px;
  overflow: auto;
}

.teacher-password-sidebar__tip {
  margin-top: 18px;
  padding: 14px 16px;
  border-radius: 14px;
  background: linear-gradient(135deg, rgba(16, 185, 129, 0.1), rgba(13, 148, 136, 0.08));
  color: #475569;
  font-size: 13px;
  line-height: 1.7;
}

.teacher-password-main {
  display: flex;
  flex-direction: column;
  gap: 18px;
  padding: 20px;
}

.teacher-password-top {
  display: grid;
  grid-template-columns: minmax(0, 1.2fr) minmax(280px, 0.8fr);
  gap: 18px;
}

.teacher-password-card {
  height: 100%;
  padding: 18px;
  border-radius: 18px;
  background: #f8fafc;
  border: 1px solid #e5e7eb;
}

.teacher-password-card__head {
  display: flex;
  align-items: center;
  gap: 8px;
  margin-bottom: 14px;
  font-size: 16px;
  font-weight: 700;
  color: #0f172a;
}

.teacher-password-banner {
  display: flex;
  flex-direction: column;
}

.teacher-password-banner__image {
  width: 100%;
  height: 180px;
  border-radius: 14px;
}

.teacher-password-banner__desc {
  margin: 14px 0 0;
  color: #6b7280;
  line-height: 1.7;
}

.teacher-password-stage {
  display: flex;
  flex: 1;
  flex-direction: column;
  padding: 22px;
  border-radius: 20px;
  background: linear-gradient(180deg, rgba(248, 250, 252, 0.7), rgba(255, 255, 255, 0.95));
  border: 1px solid #e5e7eb;
}

.teacher-password-stage__meta {
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
  gap: 16px;
}

.teacher-password-stage__eyebrow {
  font-size: 12px;
  font-weight: 700;
  letter-spacing: 0.06em;
  color: #0f766e;
}

.teacher-password-stage__title {
  margin-top: 8px;
  font-size: 24px;
  font-weight: 700;
  color: #0f172a;
}

.teacher-password-stage__desc {
  margin: 8px 0 0;
  color: #6b7280;
  line-height: 1.7;
}

.teacher-password-stage__counter {
  min-width: 64px;
  padding: 10px 12px;
  border-radius: 14px;
  background: #ecfdf5;
  color: #0f766e;
  text-align: center;
  font-size: 18px;
  font-weight: 700;
}

.teacher-password-stage__progress {
  margin-top: 16px;
  height: 8px;
  border-radius: 999px;
  background: #e5e7eb;
  overflow: hidden;
}

.teacher-password-stage__progress span {
  display: block;
  height: 100%;
  border-radius: inherit;
  background: linear-gradient(90deg, #10b981, #0f766e);
  transition: width 0.25s ease;
}

.teacher-password-stage__pane {
  display: flex;
  flex: 1;
  align-items: center;
  justify-content: center;
  min-height: 320px;
  padding-top: 24px;
}

.teacher-password-stage__pane--intro .teacher-password-stage__inner {
  text-align: center;
}

.teacher-password-stage__inner {
  width: 100%;
  max-width: 560px;
}

.teacher-password-stage__inner h3 {
  margin: 0;
  font-size: 20px;
  font-weight: 700;
  color: #0f172a;
}

.teacher-password-stage__inner p {
  margin: 12px 0 0;
  color: #6b7280;
  line-height: 1.75;
}

.teacher-password-stage__form-title {
  margin-bottom: 18px !important;
}

.teacher-password-inline {
  display: grid;
  grid-template-columns: minmax(0, 1fr) auto;
  gap: 12px;
  align-items: center;
}

.teacher-password-inline img,
.teacher-password-inline .el-button {
  height: 40px;
  border-radius: 10px;
}

.teacher-password-inline img {
  width: 140px;
  object-fit: cover;
  border: 1px solid #dbe4ee;
  background: #f8fafc;
  cursor: pointer;
}

.teacher-password-actions {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 14px;
  flex-wrap: wrap;
  padding-top: 2px;
}

.teacher-password-actions__note {
  color: #6b7280;
  line-height: 1.7;
}

.teacher-password-actions__group {
  display: flex;
  align-items: center;
  gap: 12px;
  flex-wrap: wrap;
}

.teacher-password-main :deep(.el-form-item) {
  margin-bottom: 16px;
}

.teacher-password-main :deep(.el-input__inner) {
  height: 40px;
  border-radius: 12px;
}

.teacher-password-sidebar :deep(.el-step__title) {
  font-weight: 700;
}

.teacher-password-sidebar :deep(.el-step__description) {
  line-height: 1.6;
}

.teacher-password-sidebar :deep(.el-step.is-process .el-step__head) {
  color: #0f766e;
  border-color: #10b981;
}

.teacher-password-sidebar :deep(.el-step.is-finish .el-step__head) {
  color: #14b8a6;
  border-color: #14b8a6;
}

@media (max-width: 1320px) {
  .teacher-password-layout,
  .teacher-password-top {
    grid-template-columns: 1fr;
  }
}

@media (max-width: 768px) {
  .teacher-password-stage__meta,
  .teacher-password-actions {
    flex-direction: column;
    align-items: stretch;
  }

  .teacher-password-inline {
    grid-template-columns: 1fr;
  }

  .teacher-password-inline img,
  .teacher-password-inline .el-button {
    width: 100%;
  }
}
</style>
