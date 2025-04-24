<template>
  <div>
    <el-row>
      <el-col :span="14">
        <el-row style="height: 25vh">
          <div style="margin-top: 6vh; text-align: center; font-size: 46px; color: #0b42e8;font-family: 'STXingkai', '华文行楷', cursive;">
            教师端登录入口
          </div>
        </el-row>
        <el-row>
          <div class="background_1">
            <el-image
                style="width: 100%; height: 74.5vh"
                :src='require("@/assets/login/teacherLogin1.webp")'
                fit="cover"></el-image>
          </div>
        </el-row>
      </el-col>
      <el-col :span="10">
        <div class="background_2">
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
                  <el-input style="width: 48%;margin-right: 10px" prefix-icon="el-icon-user" placeholder="请输入验证码" v-model="form.verCode" ></el-input>
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
                  忘记密码？请 <a style="color: #faca07;" href="/teacherForget">找回</a>
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
import request from "@/utils/request"
export default {
  name: "Login",
  data() {
    return {
      form: { role: 'teacher',verCode:'' },
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
          this.$router.push('/teacher/home')  // 跳转主页
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
.background_1 {
  height: 75vh;
  background-size: 100%;
  width: 100%;
}
.background_2 {
  height: 100vh;
  overflow: hidden;
  //background-image: url("@/assets/login/login.png");
  background-color: #ccd8f8;
  background-size: 100%;
  width: 100%;
  padding-top: 24vh
}


.littleView_2{
  width: 60%;
  margin-left: 20%;
  height: 50vh;
  padding: 50px 30px 50px 30px;
  //background-image: url("@/assets/login/login.png");
  background-color: rgba(3, 3, 194, 0.24);
  border-radius: 5px;
}
a {
  color: #2a60c9;
}
</style>