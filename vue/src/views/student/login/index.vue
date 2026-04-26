<template>
  <div class="login-page student-login">
    <div class="login-shell">
      <section class="login-intro">
        <div class="intro-kicker">考试管理平台</div>
        <h1>学生考试入口</h1>
        <p>用于参加在线考试、查看学习任务与成绩反馈，保障每一次考试过程清晰可信。</p>
        <div class="intro-signals">
          <span>在线考试</span>
          <span>身份核验</span>
          <span>成绩查询</span>
        </div>
      </section>

      <section class="login-panel">
        <div class="role-tag">学生端</div>
        <h2>欢迎登录</h2>
        <p class="subtitle">请输入学生账号进入考试系统</p>

        <el-form :model="form" :rules="rules" ref="formRef" class="login-form">
          <el-form-item prop="account">
            <el-input prefix-icon="el-icon-user" placeholder="请输入账号" v-model="form.account"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input prefix-icon="el-icon-lock" placeholder="请输入密码" show-password v-model="form.password"></el-input>
          </el-form-item>
          <el-form-item prop="verCode">
            <div class="captcha-row">
              <el-input prefix-icon="el-icon-key" placeholder="请输入验证码" v-model="form.verCode"></el-input>
              <img class="captcha-img" :src="captchaUrl" @click="clickImg()" alt="验证码" />
            </div>
          </el-form-item>
          <div class="actions">
            <el-button class="home-btn" @click.native="$router.push('/')">首页</el-button>
            <el-button class="login-btn" @click="login">登录</el-button>
          </div>
          <div class="forgot">忘记密码？请 <a href="/studentForget">找回</a></div>
        </el-form>
      </section>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  name: "Login",
  data() {
    return {
      form: { role: 'student',verCode:"" },
      rules: {
        account: [
          { required: true, message: '请输入账号', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
        ],
        verCode:[
          {required: true, message: '请输入验证码', trigger: 'blur' }
        ]
      },
      captchaUrl:'',
      key:'',

    }
  },
  mounted() {
    this.key=Math.random();
    this.captchaUrl=process.env.VUE_APP_BASEURL+"/user/login/captcha?key="+this.key
  },
  methods: {
    login() {
      request.post('/user/login?key='+this.key, this.form).then(res => {
        if (res.code === '200') {
          localStorage.setItem("user", JSON.stringify(res.data))
          this.$router.push('/student/home')
          this.$message.success('登录成功')
        } else {
          this.$message.error(res.msg)
          this.clickImg()
        }
      })
    },
    clickImg(){
      this.form.verCode='';
      this.key=Math.random();
      this.captchaUrl=process.env.VUE_APP_BASEURL+"/user/login/captcha?key="+this.key;
    },
  }
}
</script>

<style scoped>
@import "../../../assets/styles/role-login.css";

.student-login {
  --accent: #60dbff;
  --accent-strong: #5b7cff;
  --accent-soft: rgba(96, 219, 255, 0.46);
  --accent-faint: rgba(96, 219, 255, 0.12);
  --accent-border: rgba(96, 219, 255, 0.34);
  --panel-glow: rgba(96, 219, 255, 0.12);
  background-image: url("@/assets/login/tech/student-login-tech.jpg");
}
</style>
