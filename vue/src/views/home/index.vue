<template>
  <div>
    <el-container>
      <el-header class="header">
        <homeHeader></homeHeader>
      </el-header>
      <el-main>
        <el-row>
          <!-- 使用 SloganWithVideo 组件并监听自定义事件 -->
          <videos @scroll-to-section="scrollToSection"></videos>
          <login ref="logins"></login>
          <noticeAndNews ref="noticeAndNews"></noticeAndNews>
          <systemFunction ref="systemFunction"></systemFunction>
          <notesForExam ref="notesForExam"></notesForExam>
          <below ref="below"></below>
        </el-row>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import videos from "@/views/home/video/index.vue";
import systemFunction from "./systemFunction/index.vue"
import notesForExam from "@/views/home/notesForExam/index.vue";
import below from "@/views/home/below/index.vue";
import homeHeader from "@/views/home/head/index.vue";
import login from "@/views/home/login/index.vue";
import noticeAndNews from "@/views/home/noticeAndNews/index.vue";

export default {
  components: {
    systemFunction,
    notesForExam,
    below,
    videos,
    homeHeader,
    login,
    noticeAndNews
  },
  name: "home",
  data() {
    return {
      user: localStorage.getItem("user") ? (JSON).parse(localStorage.getItem("user")) : {},
      timer: "", //定义一个定时器
      nowTime: "", // 当前日期时间
      photo: [
        'https://lib.tsinghua.edu.cn/images/duzaidongji.jpg',
        'https://www.nlc.cn/upload/img/2023-11-21/f080014a.jpg',
        'https://www.lib.sjtu.edu.cn/upload/images/1692425282019_pic6.jpg',
        'https://www.lib.sjtu.edu.cn/upload/images/1692425148797_pic2.jpg',
      ],
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

    //登录入口
    adminLogin() {
      this.$router.push("/adminLogin")
    },
    adminHome() {
      if (this.user.role === "admin") {
        this.$router.push("/admin/home")
      } else {
        this.$message.error("没权限访问")
      }
    },

    teacherLogin() {
      this.$router.push("/teacherLogin")
    },
    teacherHome() {
      if (this.user.role === "teacher") {
        this.$router.push("/teacher/home")
      } else {
        this.$message.error("没权限访问")
      }
    },

    studentLogin() {
      this.$router.push("/studentLogin")
    },
    studentHome() {
      if (this.user.role === "student") {
        this.$router.push("/student/home")
      } else {
        this.$message.error("没权限访问")
      }
    },

    caInstitutionLogin() {
      this.$router.push("/caInstitutionLogin")
    },
    caInstitutionHome() {
      if (this.user.role === "caInstitution") {
        this.$router.push("/caInstitution/home")
      } else {
        this.$message.error("没权限访问")
      }
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

    // 滚动到指定区域
    scrollToSection(sectionId) {
      const section = this.$refs[sectionId];
      if (section) {
        // 将 behavior 设置为 'auto' 实现快速定位
        section.$el.scrollIntoView({ behavior: 'auto' });
      }
    }
  },
  beforeDestroy() {
    if (this.timer) {
      clearInterval(this.timer);
    }
  },
}
</script>

<style scoped>
.el-main {
  height: 92vh;
  overflow-y: scroll;
}

.el-main::-webkit-scrollbar {
  width: 0;
}

.el-card__body,
.el-main {
  padding: 0;
}

.header {
  height: 10vh;
  background: linear-gradient(to bottom right, #3498db, #8e44ad)
}

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

.login-photo {
  height: 23vh;
  width: 100%;
  border-radius: 30px;
  overflow: hidden;
  background-size: 100%;
}

.a {
  background-image: url('@/assets/home/login_button/admin.jpg');
}

.b {
  background-image: url('@/assets/home/login_button/caInstitution.jpg');
}

.c {
  background-image: url('@/assets/home/login_button/teacher.jpg');
}

.d {
  background-image: url('@/assets/home/login_button/student.jpg');
}

.fonts {
  color: rgba(245, 245, 98, 0.75);
  background-color: rgba(5, 6, 7, 0.68);
  font-size: 30px;
  text-align: center;
  margin-top: 10vh;
}
</style>