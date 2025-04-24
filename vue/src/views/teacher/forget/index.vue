<template>
  <div>
    <!--    //账号和手机号查询信息-->
    <!--    //输入图形验证码发送qq邮箱-->
    <!--    //进行qq邮箱的验证  上右下左-->
    <el-row>
      <el-col :span="8" >
        <div class="background">
          <div style="margin-top: 1.5vh; text-align: center; font-size: 30px; color: #3b64e1;font-family: 'STXingkai', '华文行楷', cursive;">
            教师找回密码
          </div>
          <div style="height: 80vh;margin-left: 4%;">
            <el-steps direction="vertical" :active="active-1">
              <el-step title="开始" description="开始找回密码，需要知道账号和手机号，并且设置了邮箱，如果没有设置邮箱或忘记了手机号，账号请及时联系机构中心"></el-step>
              <el-step title="信息查询" description="首先通过账号和手机号进行信息的查询，查询成功后才能进入下一步"></el-step>
              <el-step title="邮箱验证" description="进行邮箱验证，通过邮箱验证确保是本人"></el-step>
              <el-step title="密码修改" description="都完成后，信息确认无误，开始修改密码"></el-step>
              <el-step title="成功" description="密码修改成功"></el-step>
            </el-steps>
          </div>
          <div style="text-align: center;">
            <el-button round >
              <el-link style="font-size: 30px; color: #3b64e1;font-family: 'STXingkai', '华文行楷', cursive;" href="/teacherLogin" type="primary">返回登录</el-link>
            </el-button>
          </div>
        </div>
      </el-col>
      <el-col :span="16">
        <div class="background">
          <el-row>
            <el-col :span="12">
              <div style="height:30vh;margin: 2vh 3% 2vh 3%;background: #0b42e8">
                <el-card style="height: 30vh">
                  <div>
                    <el-descriptions direction="vertical" labelStyle="font-size:16px" contentStyle="font-size:16px" column="3" title="用户信息">
                      <el-descriptions-item label="账号">
                        <el-tag style="font-size: 16px">{{user.account}}</el-tag>
                      </el-descriptions-item>
                      <el-descriptions-item  :contentStyle="{'text-align': 'right'}" label="账号是否存在">
                        <el-tag style="font-size: 16px" type="info">{{ user.inf }}</el-tag>
                      </el-descriptions-item>
                      <el-descriptions-item label="姓名">
                        <el-tag style="font-size: 16px" type="warning">{{ user.name }}</el-tag>
                      </el-descriptions-item>
                      <el-descriptions-item :contentStyle="{'text-align': 'right'}" label="手机号">
                        <el-tag style="font-size: 16px" type="success">{{ user.phone }}</el-tag>
                      </el-descriptions-item>
                      <el-descriptions-item  :contentStyle="{'text-align': 'right'}" label="邮箱地址">
                        <el-tag style="font-size: 16px" type="danger">{{user.email}}</el-tag>
                      </el-descriptions-item>
                    </el-descriptions>
                  </div>
                </el-card>
              </div>
            </el-col>
            <el-col :span="12">
              <div style="height:30vh;margin: 2vh 3% 2vh 3%;background: #0b42e8">
                <el-image
                    style="width: 100%; height: 30vh"
                    :src="src[0]"
                    fit="fit"></el-image>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="5">
              <div style="margin-top:45vh;float: right">
                <el-button round @click="over">
                  <div style="font-size: 26px">
                    上一步
                  </div>
                </el-button>
              </div>
            </el-col>
            <el-col :span="14">
              <div style="height: 50vh;width: 80%;margin: 3vh 10% 3vh 10%;">
                <div class='a' v-if="active===1" style="height: 50vh">
                  <div style="margin-top: 1.5vh; text-align: center; font-size: 30px; color: #01050e;font-family: 'STXingkai', '华文行楷', cursive;">
                    <div style="padding-top: 20vh">
                      <strong>
                        找回密码即将开始
                      </strong>
                    </div>
                    请根据提示完成操作
                  </div>
                </div>
                <div class="b" v-if="active===2" style="height: 50vh">
                  <div style="text-align: center; font-size: 32px; margin-bottom: 10vh;padding-top: 5vh; color: white">进行查询</div>
                  <el-form :model="form" :rules="ruleA" ref="formRef">
                    <el-form-item prop="account" style="margin-left: 10%;width: 80%">
                      <el-input style="font-size: 20px" prefix-icon="el-icon-user" placeholder="请输入账号" v-model="form.account"></el-input>
                    </el-form-item>
                    <el-form-item prop="phone"style="margin-left: 10%;width: 80%">
                      <el-input style="font-size: 20px" prefix-icon="el-icon-lock" placeholder="请输入手机号" v-model="form.phone"></el-input>
                    </el-form-item>
                  </el-form>
                </div>
                <div class="c" v-if="active===3" style="height: 50vh">
                  <div style="text-align: center; font-size: 32px; margin-bottom: 10vh;padding-top: 5vh; color: white">进行验证</div>
                  <el-form :model="form" :rules="ruleB" ref="formRef">
                    <el-form-item prop="verCode">
                      <div style="display: flex;justify-content: center">
                        <el-input style="width: 48%;margin-right: 10px" prefix-icon="el-icon-user" placeholder="请输入图形验证码" v-model="form.verCode" ></el-input>
                        <img :src="captchaUrl" @click="clickImg()" width="140px" height="33px" />
                      </div>
                    </el-form-item>
                    <el-form-item prop="verCode">
                      <div style="display: flex;justify-content: center">
                        <el-input style="width: 48%;margin-right: 10px" prefix-icon="el-icon-user" placeholder="请输入邮箱验证码" v-model="form.mailCode" ></el-input>
                        <el-button style='width:134px;height:33px' @click="getMail()">获取验证码</el-button>
                      </div>
                    </el-form-item>
                  </el-form>
                </div>
                <div class="d" v-if="active===4" style="height: 50vh">
                  <div style="text-align: center; font-size: 32px; margin-bottom: 10vh;padding-top: 5vh; color: white">进行修改</div>
                  <el-form :model="form" :rules="ruleC" ref="formRef">
                    <el-form-item prop="password"style="margin-left: 10%;width: 80%">
                      <el-input style="font-size: 20px" prefix-icon="el-icon-lock" placeholder="请输入密码" show-password  v-model="form.password"></el-input>
                    </el-form-item>
                    <el-form-item prop="password"style="margin-left: 10%;width: 80%">
                      <el-input style="font-size: 20px" prefix-icon="el-icon-lock" placeholder="请输入密码" show-password  v-model="password"></el-input>
                    </el-form-item>
                  </el-form>
                </div>
                <div class="e" v-if="active===5" style="height: 50vh">
                  <div style="margin-top: 1.5vh; text-align: center; font-size: 30px; color: #01040a;font-family: 'STXingkai', '华文行楷', cursive;">
                    <div style="padding-top: 20vh">
                      <strong>
                        密码成功找回
                      </strong>
                    </div>
                    请记住你的新密码
                  </div>
                </div>
              </div>
            </el-col>
            <el-col :span="5">
              <div style="margin-top:45vh; float: left">
                <el-button round @click="next">
                  <div style="font-size: 26px">
                    {{button}}
                  </div>
                </el-button>
              </div>
            </el-col>
          </el-row>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import request from "@/utils/request"
import back1 from "@/assets/forget/1.jpg"
import back2 from "@/assets/forget/2.jpg"
import back3 from "@/assets/forget/3.jpg"
import back4 from "@/assets/forget/4.jpg"
export default {
  name: "forget",
  data() {
    return {
      src:[
        back1,
        back2,
        back3,
        back4,
      ],//图片
      active:1,//进度
      user:{
        inf:"否"
      },//接受
      form:{
        role:"teacher"
      },//发送
      button:"开始",//下一步按钮
      buttons:["开始","查询","验证","修改","完成"],
      ruleA: {
        account: [
          { required: true, message: '请输入账号', trigger: 'blur' },
        ],
        phone: [
          { required: true, message: '请输入手机号', trigger: 'blur' },
        ],
      },
      key1:"",
      key2:"",
      captchaUrl:'',
      emailUrl:'',
      password:""
    }
  },
  created() {

  },
  mounted() {
    this.key1=Math.random();//随机数
    this.key2=Math.random();//随机数
    this.captchaUrl=process.env.VUE_APP_BASEURL+"/user/login/captcha?key="+this.key1
  },
  methods: {
    over(){
      if(this.active!==1){
        this.active-=1
        this.button=this.buttons[this.active-1]
      }
    },
    ne(){
      this.active+=1
      this.button=this.buttons[this.active-1]
    },
    next(){
      if(this.active===1){
        this.ne()
      }
      else if(this.active===2){
        // 查询信息
        request.post('/user/findMessage', this.form).then(res => {
          if (res.code === '200') {
            if(res.data===null){
              this.$message.error("未找到信息")
              return
            }
            this.user=res.data
            this.user.inf="是"
            this.form.account=this.user.account
            this.form.phone=this.user.phone
            this.ne()
          } else {
            this.user.inf="否"
            this.$message.error(res.msg)
          }
        })
      }
      else if(this.active===3){
        //进行校验
        request.post('/user/forget/verify?key='+this.key2, this.form).then(res => {
          if (res.code === '200') {
            this.$message.success("验证成功")
            this.ne()
          } else {
            this.$message.error(res.msg)
          }
        })
      }
      else if(this.active===4){
        if(this.form.password!==this.password){
          this.$message.error("请正确输入")
          return
        }
        this.form.id=this.user.id
        this.form.account=this.user.account
        request.post('/user/forget', this.form).then(res => {
          if (res.code === '200') {
            this.$message.success("修改成功")
            this.ne()
          } else {
            this.$message.error(res.msg)
          }
        })
      }
    },
    clickImg(){
      this.key1=Math.random();//随机数
      this.captchaUrl=process.env.VUE_APP_BASEURL+"/user/login/captcha?key="+this.key1;
      this.form.verCode='';
    },
    getMail(){
      if(this.form.verCode===null||this.form.verCode===''){
        this.$message.error("请输入验证码")
      }
      this.form.key1=this.key1
      request.post('/user/forget/email?key='+this.key2, this.form).then(res => {
        if (res.code === '200') {
          this.$message.success("发送成功")
        } else {
          this.$message.error(res.msg)
        }
      })
    }
  }
}
</script>

<style scoped>
.background{
  height: 92vh;
  width: 92%;
  margin: 4vh 4% 4vh 4%;
  background: rgba(212, 220, 245, 0.34);
}

.a{
  background-image: url("@/assets/forget/2.jpg");
  background-size: 100% 50vh;
}
.b{
  background-image: url("@/assets/forget/3.jpg");
  background-size: 100% 50vh;
}
.c{
  background-image: url("@/assets/forget/3.jpg");
  background-size: 100% 50vh;
}
.d{
  background-image: url("@/assets/forget/3.jpg");
  background-size: 100% 50vh;
}
.e{
  background-image: url("@/assets/forget/4.jpg");
  background-size: 100% 50vh;
}
</style>
