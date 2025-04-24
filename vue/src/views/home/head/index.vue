<template>
  <div>
    <el-row>
      <el-col :span="10">
        <div style="margin-top: 1.5vh; text-align: center; font-size: 36px; color: #0f1117;font-family: 'STXingkai', '华文行楷', cursive;">
          基于国密算法与人脸认证的考试系统
        </div>
      </el-col>
      <el-col :span="4" style="text-align: center">
        <el-button type="text" @click="$router.push('/bigEcharts')" size="small" style="color: red;">
          <div style="margin-top: 2vh; text-align: center; font-size: 30px; color: #1341cc;font-family: 'STXingkai', '华文行楷', cursive;">
            大屏可视化
          </div>
        </el-button>
        <el-button type="text" @click="$router.push('/bigEchartsWebSocket')" size="small" style="color: red;">
        <div style="margin-top: 2vh; text-align: center; font-size: 30px; color: #1341cc;font-family: 'STXingkai', '华文行楷', cursive;">
          大屏
        </div>
        </el-button>

      </el-col>
      <el-col :span="4" style="text-align: center">
        <div style="margin-top: 3vh; text-align: right; font-size: 30px; color: #1341cc;font-family: 'STXingkai', '华文行楷', cursive;">
          {{lgu}}
        </div>
      </el-col>
      <el-col :span="6">
        <div class="showtime">{{ nowTime }}</div>
      </el-col>
        </el-row>
  </div>
</template>

<script>
export default {
  name: "home",
  data() {
    return {
      user: localStorage.getItem("user") ? (JSON).parse(localStorage.getItem("user")) : {},
      timer: "", //定义一个定时器
      nowTime: "", // 当前日期时间
      login: "",//是否登录，没登陆赋值”登录“
      lgu: "" //是否登录，在头部进行显示
    }
  },
  created() {
    this.item()
  },
  mounted() {
    this.getTime();
  },
  methods: {
    // 时间
    getTime() {
      this.timer = setInterval(() => {
        let timeDate = new Date();
        let year = timeDate.getFullYear();
        let mounth = timeDate.getMonth() + 1;
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
          "星期六",
        ];

        this.nowTime = `${year}-${mounth}-${day} ${hours}:${minutes}:${seconds} ${weekArr[week]}`;
      }, 1000);
    },

    //用户登录检查
    item() {
      const isEmpty = Object.keys(this.user).length === 0;
      if (isEmpty) {
        this.login = true
        this.lgu = "请登录"
      } else {
        this.lgu = this.user.role + ":" + this.user.name
        this.login = false
      }
    },
  },
  beforeDestroy() {
    if (this.timer) {
      clearInterval(this.timer);
    }
  },
}
</script>

<style scoped>
.showtime {
  overflow: hidden;
  width: 20rem;
  height: 8rem;
  position: absolute;
  top: 0;
  right: 0.375rem;
  line-height: 4rem;
  font-size: 1.5rem;
  margin-top: 1vh;
  color: rgba(255, 255, 255, 0.7);
}
</style>
