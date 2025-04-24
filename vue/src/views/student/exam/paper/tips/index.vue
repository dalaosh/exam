<template>
  <div>
    <div class="head">
      <el-row>
        <el-col :span="2">
          <el-button type="text" @click="end()" size="small" style="color: #009dff;font-size: 30px;margin-right: 3%">返回</el-button>
        </el-col>
        <el-col :span="6">
          {{ form.course.name }}考试试卷
        </el-col>
        <el-col :span="6">
          总分：{{ form.totalScore }}分
        </el-col>
        <el-col :span="10">
          当前时间：{{ nowTime }}
        </el-col>
      </el-row>
    </div>
    <div class="head" style="background: linear-gradient(to right top, #8ef8f8, #d6c7fa);">
      <el-row>
        <el-col :span="8">
          开考时间：{{ form.examDate }}
        </el-col>
        <el-col :span="5">
          考试时长：{{ form.totalTime }}分钟
        </el-col>
        <el-col :span="11">
          <span v-if="examStatus === 'before'">
            距离考试还有：{{ dataTime.year }}年{{ dataTime.month }}月{{ dataTime.day }}日{{ dataTime.hour }}时{{ dataTime.minute }}分{{ dataTime.second }}秒
          </span>
          <span v-else-if="examStatus === 'ongoing'">
            考试正在进行中
          </span>
          <span v-else>
            考试已结束
          </span>
        </el-col>
      </el-row>
    </div>
    <div class="main">
      <el-row>
        <el-col :span="12">
          <div>
            <el-row class="right-top">
              <el-col :span="12">
                <div style="margin-bottom: 2vh; text-align: center">
                  <el-button
                      type="primary"
                      @click="openCamera"
                      :disabled="cameraEnabled"
                      style="margin-right: 3px">
                    开启摄像
                  </el-button>
                  <el-button
                      type="danger"
                      @click="closeCamera"
                      :disabled="!cameraEnabled">
                    关闭摄像
                  </el-button>
                  <el-button
                      type="success"
                      @click="takePhoto"
                      :disabled="!cameraEnabled"
                      style="margin-left: 3px">
                    拍 照
                  </el-button>
                </div>
                <div style="width: 98%; height: 28vh; border: 1px dashed #0c5bf3;text-align: center">
                  <video
                      ref="video"
                      v-show="cameraEnabled"
                      autoplay
                      style="width: 100%; height: 100%"></video>
                  <div v-show="!cameraEnabled" style="padding-top: 8vh;font-size: 24px">
                    摄像头未开启
                  </div>
                </div>
              </el-col>
              <el-col :span="12">
                <div style="margin-bottom: 2vh; text-align: center">
                  <el-button
                      type="primary"
                      @click="insertMessage"
                      style="margin-right: 3px">
                    人脸认证
                  </el-button>
                </div>
                <div style="width: 98%">
                  <el-image
                      style="width: 100%; height: 28vh; border: 1px dashed #0c5bf3;text-align: center"
                      :src="capturedPhoto"
                      fit="contain">
                    <div slot="error" style="padding-top: 8vh;font-size: 24px">
                      等待拍摄照片...
                    </div>
                  </el-image>
                </div>
              </el-col>
            </el-row>
          </div>
          <div class="right-bottom">
            <div class="title" style="height: 5vh">考生资料</div>
            <el-row>
              <el-col :span="6">
                <el-image
                    style="width: 100%; height: 26vh"
                    :src="faceData.student.photo"
                    fit="fit"></el-image>
              </el-col>
              <el-col :span="18">
                <div style="padding: 0 2% 0 2%;width: 100%">
                  <el-descriptions direction="vertical" :labelStyle="{'font-size':'18px'}" :contentStyle="{'font-size':'18px'}" :column=2>
                    <el-descriptions-item label="匹配度">
                      <el-tag style="font-size: 18px" type="warning">{{ faceData.similarValue }}</el-tag>
                    </el-descriptions-item>
                    <el-descriptions-item label="状态">
                      <el-tag style="font-size: 18px" type="danger">{{this.student.faceSubmit}}</el-tag>
                    </el-descriptions-item>
                    <el-descriptions-item label="账号">
                      <el-tag style="font-size: 18px">{{faceData.student.account}}</el-tag>
                    </el-descriptions-item>
                    <el-descriptions-item  label="手机号">
                      <el-tag style="font-size: 18px" type="success">{{ faceData.student.phone }}</el-tag>
                    </el-descriptions-item>
                    <el-descriptions-item label="姓名">
                      <el-tag style="font-size: 18px" type="warning">{{ faceData.student.name }}</el-tag>
                    </el-descriptions-item>
                    <el-descriptions-item label="邮箱地址">
                      <el-tag style="font-size: 18px" type="danger">{{faceData.student.email}}</el-tag>
                    </el-descriptions-item>
                  </el-descriptions>
                </div>
              </el-col>
            </el-row>
          </div>
        </el-col>
        <el-col :span="12">
          <el-card class="box-card cards">
            <div class="title">
              考生须知
            </div>
            <div class="body">
              <div v-html="this.form.tips" class="w-e-text"></div>
            </div>
          </el-card>
          <div class="bottom">
            <el-button disabled v-if="examStatus === 'before'" type="primary" round style="width: 60%;margin-top:2vh;font-size: 24px;">考试还未开始</el-button>
            <el-button v-else-if="examStatus === 'ongoing'" type="primary" round style="width: 60%;margin-top:2vh;font-size: 24px;" @click="submit">开 始 考 试</el-button>
            <el-button disabled v-else type="primary" round style="width: 60%;margin-top:2vh;font-size: 24px;">考试已经结束</el-button>
          </div>
        </el-col>
      </el-row>
    </div>
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
      timer: null,
      examStatus: 'before', // 考试状态，初始为考试前
      student:{},
      cameraEnabled: false,
      capturedPhoto: '',
      idCardImage: '',
      mediaStream: null,
      studentFile:"",
      idPath:"",
      setOrShow:"show",
      studentFace:"",
      faceData:{
        student:{}
      },
    };
  },
  created() {
    this.receivedData = this.$route.params;
    this.load1();
    this.load2();
  },
  mounted() {
    this.getTime();
    this.calculateRemainingTime();
  },
  beforeDestroy() {
    if (this.timer) {
      clearInterval(this.timer);
      this.closeCamera();
    }
  },
  methods: {
    getTime() {
      this.timer = setInterval(() => {
        let timeDate = new Date();
        let year = timeDate.getFullYear();
        let month = timeDate.getMonth() + 1;
        let day = timeDate.getDate();
        let hours = timeDate.getHours();
        hours = hours >= 10 ? hours : "0" + hours;
        let minutes = timeDate.getMinutes();
        minutes = minutes >= 10 ? minutes : "0" + minutes;
        let seconds = timeDate.getSeconds();
        seconds = seconds >= 10 ? seconds : "0" + seconds;
        let week = timeDate.getDay();
        let weekArr = [
          "星期日",
          "星期一",
          "星期二",
          "星期三",
          "星期四",
          "星期五",
          "星期六"
        ];

        this.nowTime = `${year}-${month}-${day} ${hours}:${minutes}:${seconds} ${weekArr[week]}`;
      }, 1000);
    },
    load1() {
      request.get("exam/examManage/selectById/" + this.receivedData.id).then(res => {
        if (res.code === '200') {
          this.form = res.data;
          this.calculateRemainingTime();
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    load2() {
      request.get("exam/examStudent/selectById/" + this.receivedData.examStudent).then(res => {
        if (res.code === '200') {
          this.student = res.data;
          this.calculateRemainingTime();
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    calculateRemainingTime() {
      if (this.form.examDate) {
        let examStartDate = new Date(this.form.examDate);
        let examEndDate = new Date(examStartDate.getTime() + this.form.totalTime * 60 * 1000); // 计算考试结束时间

        this.timer = setInterval(() => {
          let now = new Date();
          let diffStart = examStartDate.getTime() - now.getTime();
          let diffEnd = examEndDate.getTime() - now.getTime();

          if (diffStart > 0) {
            // 考试未开始，计算倒计时
            this.examStatus = 'before';
            let seconds = Math.floor(diffStart / 1000);
            let minutes = Math.floor(seconds / 60);
            let hours = Math.floor(minutes / 60);
            let days = Math.floor(hours / 24);
            let months = Math.floor(days / 30);
            let years = Math.floor(months / 12);

            days %= 30;
            hours %= 24;
            minutes %= 60;
            seconds %= 60;

            this.dataTime = {
              year: years,
              month: months,
              day: days,
              hour: hours,
              minute: minutes,
              second: seconds
            };
          } else if (diffEnd > 0) {
            // 考试正在进行中
            this.examStatus = 'ongoing';
            this.dataTime = {
              year: 0,
              month: 0,
              day: 0,
              hour: 0,
              minute: 0,
              second: 0
            };
          } else {
            // 考试已结束
            this.examStatus = 'ended';
            this.dataTime = {
              year: 0,
              month: 0,
              day: 0,
              hour: 0,
              minute: 0,
              second: 0
            };
            clearInterval(this.timer);
          }
        }, 1000);
      }
    },
    submit(){
      if(this.student.faceSubmit!=="验证成功"&&this.student.faceSubmit!=="教师代理"){
        this.$message.warning("请先完成验证，如果验证不通过请联系任课教师")
      }
      else{
        this.$router.push({
          name: "studentAnswer",
          params: {
            id:this.receivedData.id
          }
        });
      }
    },
    end(){
      this.$router.push({
        name: "studentExamList",
      });
    },
    // 开启摄像头
    async openCamera() {
      try {
        const stream = await navigator.mediaDevices.getUserMedia({
          video: { width: 640, height: 480 }
        });
        this.mediaStream = stream;
        this.$refs.video.srcObject = stream;
        this.cameraEnabled = true;
      } catch (error) {
        this.$message.error('无法访问摄像头: ' + error.message);
      }
    },

    // 关闭摄像头
    closeCamera() {
      if (this.mediaStream) {
        this.mediaStream.getTracks().forEach(track => track.stop());
        this.mediaStream = null;
      }
      this.cameraEnabled = false;
      this.$refs.video.srcObject = null;
    },

    // 拍照
    // takePhoto() {
    //   const video = this.$refs.video;
    //   const canvas = document.createElement('canvas');
    //   canvas.width = video.videoWidth;
    //   canvas.height = video.videoHeight;
    //   canvas.getContext('2d').drawImage(video, 0, 0);
    //   this.capturedPhoto = canvas.toDataURL('image/png');
    // },
    takePhoto() {
      const video = this.$refs.video;
      const canvas = document.createElement('canvas');
      canvas.width = video.videoWidth;
      canvas.height = video.videoHeight;
      const ctx = canvas.getContext('2d');

      // 绘制视频帧到canvas
      ctx.drawImage(video, 0, 0, canvas.width, canvas.height);

      // 转换为JPEG格式（更适合照片）
      const base64Data = canvas.toDataURL('image/jpeg', 0.8) // 0.8为质量参数

      // 处理后端需要的格式（去掉data:image/jpeg;base64,前缀）
      const processedBase64 = this.processBase64(base64Data);

      // 存储到变量（根据接口需求）
      this.studentFile=base64Data

      // 同时保留预览图
      this.capturedPhoto = base64Data;
    },

// Base64处理方法（与Java后端base64Process方法对应）
    processBase64(base64Str) {
      if (!base64Str) return '';
      // 分割逗号后的部分
      const parts = base64Str.split(',');
      return parts.length > 1 ? parts[1] : base64Str;
    },
    insertMessage(){
      let params;
      params={
        file:this.studentFile,
      }
      request.post("/trustSeal/studentFace/faceSearch",params).then(res=>{
        if(res.code==="200"){
          if(res.data.studentId===this.user.id){
            this.faceData=res.data
            this.examStudentUpdate("验证成功")
          }
          else{
            this.faceData=res.data
            this.$message.error("认证失败")
            this.examStudentUpdate("验证失败")
          }
        }
        else {
          this.$message.error(res.msg)
        }
      })
    },
    examStudentUpdate(submit){
      let params;
      params={
        id:this.receivedData.examStudent,
        facePath:this.studentFile,
        faceSubmit:submit
      }
      request.put("/exam/examStudent/updateSubmit",params).then(res=>{
        if(res.code==="200"){
          this.$message.success("修改成功")
          this.load2()
        }
        else {
          this.$message.error(res.msg)
        }
      })
    }
  },

};
</script>

<style scoped>
.head {
  width: 100%;
  height: 10vh;
  background: linear-gradient(to bottom right, #8ef8f8, #d6c7fa);
  font-family: 'STXingkai', '华文行楷', cursive;
  font-size: 32px;
  text-align: center;
  padding-top: 3vh;
}
.main{
  padding-top: 2vh;
  width: 100%;
  height: 80vh;
  background: #f0f3fd;
}

.cards {
  margin: 0 5% 2vh 5%;
  padding: 2vh 2% 0 2%;
  width: 90%;
  height: 66vh;
  overflow-y: scroll;
  border-radius: 10px;
}
.cards::-webkit-scrollbar {
  width: 0;
}
.title{
  width: 100%;
  height: 6vh;
  font-family: 'STXingkai', '华文行楷', cursive;
  font-size: 36px;
  text-align: center;
}
.body {
  margin: 0 2% 2vh 2%;
  padding: 2vh 2% 2vh 2%;
  width: 96%;
  height: 50vh;
  font-size: 24px;
  overflow-y: scroll;
  border-radius: 10px;
  background: #f4edf6;
}
.body::-webkit-scrollbar {
  width: 0;
}
.bottom{
  text-align: center;
}
.right-top{
  margin: 0 0 2vh 2%;
  padding: 2vh 2% 0 2%;
  width: 96%;
  height: 38vh;
  border-radius: 10px;
  background: #8ef8f8;
}
.right-bottom{
  margin: 0 0 2vh 2%;
  padding: 2vh 2% 0 2%;
  width: 96%;
  height: 36vh;
  border-radius: 10px;
  background: #8ef8f8;
}
</style>