<template>
  <div>
    <!--    //账号和手机号查询信息-->
    <!--    //输入图形验证码发送qq邮箱-->
    <!--    //进行qq邮箱的验证  上右下左-->
    <el-row>
      <el-col :span="8" >
        <div class="background">
          <div style="margin-top: 1.5vh; text-align: center; font-size: 30px; color: #3b64e1;font-family: 'STXingkai', '华文行楷', cursive;">
            教师修改密码
          </div>
          <div style="height: 74vh;margin-left: 4%;">
            <el-steps direction="vertical" :active="active-1">
              <el-step title="开始" description="开始修改密码"></el-step>
              <el-step title="邮箱验证" description="进行邮箱验证，通过邮箱验证确保是本人"></el-step>
              <el-step title="密码修改" description="都完成后，信息确认无误，开始修改密码"></el-step>
              <el-step title="成功" description="密码修改成功"></el-step>
            </el-steps>
          </div>
        </div>
      </el-col>
      <el-col :span="16">
        <div class="background">
          <el-row>
            <el-col :span="12">
              <div style="height:24vh;margin: 1vh 3% 1vh 3%;background: #0b42e8">
                <el-card shadow="never" style="height: 24vh">
                  <div>
                    <el-descriptions direction="vertical" :labelStyle="{'font-size':'12px'}" :contentStyle="{'font-size':'12px'}" :column=2 title="用户信息">
                      <el-descriptions-item label="账号">
                        <el-tag style="font-size: 12px">{{user.account}}</el-tag>
                      </el-descriptions-item>
                      <el-descriptions-item :contentStyle="{'text-align': 'right'}" label="手机号">
                        <el-tag style="font-size: 12px" type="success">{{ user.phone }}</el-tag>
                      </el-descriptions-item>
                      <el-descriptions-item label="姓名">
                        <el-tag style="font-size: 12px" type="warning">{{ user.name }}</el-tag>
                      </el-descriptions-item>
                      <el-descriptions-item  :contentStyle="{'text-align': 'right'}" label="邮箱地址">
                        <el-tag style="font-size: 12px" type="danger">{{user.email}}</el-tag>
                      </el-descriptions-item>
                    </el-descriptions>
                  </div>
                </el-card>
              </div>
            </el-col>
            <el-col :span="12">
              <div style="height:24vh;margin: 1vh 3% 1vh 3%;background: #0b42e8">
                <el-image
                    style="width: 100%; height: 24vh"
                    :src="src[0]"
                    fit="fit"></el-image>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="5">
              <div style="margin-top:42vh;float: right">
                <el-button round @click="over">
                  <div style="font-size: 26px">
                    上一步
                  </div>
                </el-button>
              </div>
            </el-col>
            <el-col :span="14">
              <div style="height: 50vh;width: 80%;margin: 1vh 10% 1vh 10%;">
                <div class='a' v-if="active===1" style="height: 50vh">
                  <div style="margin-top: 1.5vh; text-align: center; font-size: 30px; color: #01050e;font-family: 'STXingkai', '华文行楷', cursive;">
                    <div style="padding-top: 20vh">
                      <strong>
                        修改密码即将开始
                      </strong>
                    </div>
                    请根据提示完成操作
                  </div>
                </div>
                <div class="c" v-if="active===2" style="height: 50vh">
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
                <div class="d" v-if="active===3" style="height: 50vh">
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
                <div class="e" v-if="active===4" style="height: 50vh">
                  <div style="margin-top: 1.5vh; text-align: center; font-size: 30px; color: #01040a;font-family: 'STXingkai', '华文行楷', cursive;">
                    <div style="padding-top: 20vh">
                      <strong>
                        密码成功修改
                      </strong>
                    </div>
                    请记住你的新密码
                  </div>
                </div>
              </div>
            </el-col>
            <el-col :span="5">
              <div style="margin-top:42vh; float: left">
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
      user:localStorage.getItem("user") ? (JSON).parse(localStorage.getItem("user")) : {},
      src:[
        back1,
        back2,
        back3,
        back4,
      ],//图片
      active:1,//进度
      form:{
        role:"teacher"
      },//发送
      button:"开始",//下一步按钮
      buttons:["开始","验证","修改","完成"],
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
    this.form=this.user
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
      else if(this.active===3){
        if(this.form.password!==this.password){
          this.$message.error("请正确输入")
          return
        }
        this.form.id=this.user.id
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
  height: 80vh;
  width: 92%;
  margin: 2vh 4% 2vh 4%;
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
