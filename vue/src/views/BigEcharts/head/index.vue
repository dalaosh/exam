<template>
  <div style="width: 100%;height: 5vh;color: #fff;">
    <el-row>
      <el-col :span="3" style="text-align: left;padding-left: 5%">
        <el-button type="text" @click="$router.push('/')" size="small" style="color: red;">
          <div style="text-align: right; font-size: 32px; color: #a5efde;font-family: 'STXingkai', '华文行楷', cursive;">
            首页
          </div>
        </el-button>
      </el-col>
      <el-col :span="14" style="text-align: center">
        <div style="font-size: 32px">基于国密算法与人脸认证的考试系统</div>
      </el-col>
      <el-col :span="7" style="text-align: right">
        <div style="font-size: 32px">{{nowTime}}</div>
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
    }
  },
  created() {

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
  },
  beforeDestroy() {
    if (this.timer) {
      clearInterval(this.timer);
    }
  },
}
</script>