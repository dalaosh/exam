<template>
  <div class="admin-page admin-password-page">
    <section class="admin-page-head">
      <div>
        <h2 class="admin-page-head__title">密码修改</h2>
        <p class="admin-page-head__desc">通过邮箱验证码与图形验证码完成管理员密码更新。</p>
      </div>
      <div class="admin-page-head__meta">
        <el-tag class="admin-tag" type="warning">分步验证</el-tag>
      </div>
    </section>

    <div class="admin-password-layout">
      <section class="admin-panel admin-profile-card">
        <h3 class="admin-section-title">修改步骤</h3>
        <p class="admin-section-note">按照引导完成校验后即可重置密码。</p>
        <el-steps direction="vertical" :active="active - 1">
          <el-step title="开始" description="进入密码修改流程"></el-step>
          <el-step title="邮箱验证" description="验证图形验证码并获取邮箱验证码"></el-step>
          <el-step title="重置密码" description="输入并确认新密码"></el-step>
          <el-step title="完成" description="密码修改成功"></el-step>
        </el-steps>
      </section>

      <section class="admin-panel admin-password-main">
        <div class="admin-password-top">
          <div class="admin-help-card">
            <div class="admin-help-card__title">
              <i class="el-icon-user-solid"></i>
              <span>账户信息</span>
            </div>
            <el-descriptions :column="2" border>
              <el-descriptions-item label="账号">
                <el-tag class="admin-tag">{{ user.account }}</el-tag>
              </el-descriptions-item>
              <el-descriptions-item label="手机号">
                <el-tag class="admin-tag" type="success">{{ user.phone }}</el-tag>
              </el-descriptions-item>
              <el-descriptions-item label="姓名">
                <el-tag class="admin-tag" type="warning">{{ user.name }}</el-tag>
              </el-descriptions-item>
              <el-descriptions-item label="邮箱">
                <el-tag class="admin-tag" type="danger">{{ user.email }}</el-tag>
              </el-descriptions-item>
            </el-descriptions>
          </div>

          <div class="admin-help-card">
            <div class="admin-help-card__title">
              <i class="el-icon-picture"></i>
              <span>安全提示</span>
            </div>
            <el-image style="width: 100%; height: 180px; border-radius: 14px;" :src="src[0]" fit="cover"></el-image>
          </div>
        </div>

        <div class="admin-password-stage">
          <div v-if="active === 1" class="admin-password-stage__pane">
            <div class="admin-password-stage__inner">
              <h3 class="admin-password-stage__title">准备开始</h3>
              <p class="admin-password-stage__desc">请按照提示依次完成邮箱验证和新密码设置。</p>
            </div>
          </div>

          <div v-if="active === 2" class="admin-password-stage__pane">
            <div class="admin-password-stage__inner">
              <h3 class="admin-password-stage__form-title">邮箱验证</h3>
              <el-form :model="form" :rules="ruleB" ref="formRef">
                <el-form-item prop="verCode">
                  <div class="admin-password-inline">
                    <el-input
                      v-model="form.verCode"
                      prefix-icon="el-icon-user"
                      placeholder="请输入图形验证码"
                    ></el-input>
                    <img :src="captchaUrl" @click="clickImg()" width="140" height="40" />
                  </div>
                </el-form-item>
                <el-form-item prop="verCode">
                  <div class="admin-password-inline">
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

          <div v-if="active === 3" class="admin-password-stage__pane">
            <div class="admin-password-stage__inner">
              <h3 class="admin-password-stage__form-title">重置密码</h3>
              <el-form :model="form" :rules="ruleC" ref="formRef">
                <el-form-item prop="password">
                  <el-input
                    prefix-icon="el-icon-lock"
                    placeholder="请输入新密码"
                    show-password
                    v-model="form.password"
                  ></el-input>
                </el-form-item>
                <el-form-item prop="password">
                  <el-input
                    prefix-icon="el-icon-lock"
                    placeholder="请再次输入新密码"
                    show-password
                    v-model="password"
                  ></el-input>
                </el-form-item>
              </el-form>
            </div>
          </div>

          <div v-if="active === 4" class="admin-password-stage__pane">
            <div class="admin-password-stage__inner">
              <h3 class="admin-password-stage__title">修改成功</h3>
              <p class="admin-password-stage__desc">新密码已生效，请妥善保管并在后续登录中使用。</p>
            </div>
          </div>
        </div>

        <div class="admin-password-actions">
          <el-button round @click="over">上一步</el-button>
          <el-button round type="primary" @click="next">{{ button }}</el-button>
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
      src: [
        back1,
        back2,
        back3,
        back4,
      ],
      active: 1,
      form: {
        role: "admin"
      },
      button: "开始",
      buttons: ["开始", "验证", "修改", "完成"],
      ruleA: {
        account: [
          { required: true, message: "请输入账号", trigger: "blur" },
        ],
        phone: [
          { required: true, message: "请输入手机号", trigger: "blur" },
        ],
      },
      key1: "",
      key2: "",
      captchaUrl: "",
      emailUrl: "",
      password: "",
      ruleB: {},
      ruleC: {}
    };
  },
  created() {
    this.form = this.user;
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
          this.$message.error("请输入相同的密码");
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
.admin-password-inline {
  display: grid;
  grid-template-columns: minmax(0, 1fr) auto;
  gap: 12px;
  align-items: center;
}
</style>
