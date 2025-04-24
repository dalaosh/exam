<template>
  <div class="background">
    <el-row>
      <el-col :span="14">
        <div style="height: 100vh;width: 100%;">
          <div class="littleView_1">

          </div>
        </div>
      </el-col>
      <el-col :span="10">
        <div style="height: 100vh;width: 100%;">
          <div class="littleView_2">
            <div style="text-align: center; font-size: 32px; margin-bottom: 20px; color: white">欢迎使用</div>
            <el-form :model="form" :rules="rules" ref="formRef">
              <el-form-item prop="account">
                <el-input style="font-size: 20px" prefix-icon="el-icon-user" placeholder="请输入账号" v-model="form.account"></el-input>
              </el-form-item>
              <el-form-item prop="password">
                <el-input style="font-size: 20px" prefix-icon="el-icon-lock" placeholder="请输入密码" show-password  v-model="form.password"></el-input>
              </el-form-item>
              <el-form-item prop="verCode">
                <div style="display: flex;justify-content: center">
                  <el-input style="width: 52%;margin-right: 10px" prefix-icon="el-icon-user" placeholder="请输入验证码" v-model="form.verCode" ></el-input>
                  <img :src="captchaUrl" @click="clickImg()" width="140px" height="33px" />
                </div>
              </el-form-item>
              <el-form-item>
                <el-button style="width: 40%; background-color: #0000ff; border-color: #030303; color: white;margin-right: 16%;font-size: 20px" @click.native="$router.push('/')">首页</el-button>
                <el-button style="width: 40%; background-color: #0000ff; border-color: #030303;  color: white;font-size: 20px" @click="login">登 录</el-button>
              </el-form-item>
              <div style="display: flex; align-items: center;color: #f9fafd">
                <div style="flex: 1"></div>
                <div style="flex: 1; text-align: right">
                  忘记密码？请 <a style="color: #faca07;" href="/studentForget">找回</a>
                </div>
              </div>
            </el-form>
          </div>
        </div>
      </el-col>
    </el-row>
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
      //验证码
      captchaUrl:'',
      key:'',

    }
  },
  created() {

  },
  mounted() {
    this.key=Math.random();//随机数
    this.captchaUrl=process.env.VUE_APP_BASEURL+"/user/login/captcha?key="+this.key
  },
  methods: {
    login() {
      // 验证通过
      request.post('/user/login?key='+this.key, this.form).then(res => {
        if (res.code === '200') {
          localStorage.setItem("user", JSON.stringify(res.data))  // 存储用户数据
          this.$router.push('/student/home')  // 跳转主页
          this.$message.success('登录成功')
        } else {
          this.$message.error(res.msg)
          this.clickImg()
        }
      })
    },
    clickImg(){
      this.form.verCode='';
      this.key=Math.random();//随机数
      this.captchaUrl=process.env.VUE_APP_BASEURL+"/user/login/captcha?key="+this.key;
    },
  }
}
</script>

<style scoped>
.background {
  height: 100vh;
  overflow: hidden;
  background-image: url("@/assets/login/studet4.jpg");
  background-size: 100%;
}

.littleView_1{
  width: 60%;
  margin-left: 20%;
  height: 50vh;
  margin-top: 25vh;
  padding: 50px 20px 50px 20px;
  border-radius: 5px;
  background-image: url("@/assets/login/student3.png");
  background-size: cover;
}
.littleView_2{
  width: 60%;
  margin-left: 20%;
  height: 44vh;
  margin-top: 28vh;
  padding: 20px 30px 50px 30px;
  background-image: url("@/assets/login/student5.jpg");
  background-size: cover;
  border-radius: 5px;
}
a {
  color: #2a60c9;
}
</style>