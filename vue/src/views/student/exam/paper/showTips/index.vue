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
      <el-card class="box-card cards">
        <div class="title">
          考生须知
        </div>
        <div class="body">
          <div v-html="this.form.tips" class="w-e-text"></div>
        </div>
      </el-card>
      <div class="bottom">
        <el-button disabled v-if="examStatus === 'before'" type="primary" round style="width: 30%;margin-top:2vh;font-size: 24px;margin-left: 35%">考试还未开始</el-button>
        <el-button disabled v-else-if="examStatus === 'ongoing'" type="primary" round style="width: 30%;margin-top:2vh;font-size: 24px;margin-left: 35%">正 在 考 试</el-button>
        <el-button v-else type="primary" round style="width: 30%;margin-top:2vh;font-size: 24px;margin-left: 35%" @click="submit">考试已经结束，点击查看答案</el-button>
      </div>
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
      examStatus: 'before' // 考试状态，初始为考试前
    };
  },
  created() {
    this.receivedData = this.$route.params;
    this.load();
  },
  mounted() {
    this.getTime();
    this.calculateRemainingTime();
  },
  beforeDestroy() {
    if (this.timer) {
      clearInterval(this.timer);
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
    load() {
      request.get("exam/examManage/selectById/" + this.receivedData.id).then(res => {
        if (res.code === '200') {
          this.form = res.data;
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
      this.$router.push({
        name: "studentShowAnswer",
        params: {
          id:this.receivedData.id
        }
      });
    },
    end(){
      this.$router.push({
        name: "studentExamList",
      });
    }
  }
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
  margin: 0 20% 2vh 20%;
  padding: 2vh 2% 0 2%;
  width: 60%;
  height: 66vh;
  overflow-y: scroll;
  border-radius: 10px;
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
</style>