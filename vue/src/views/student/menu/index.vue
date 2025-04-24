<template>
  <div class="app">
    <el-container>
      <el-header style="background: linear-gradient(to bottom right,  #8ef8f8, #dacff6);height: 12vh">
        <el-row>
          <el-col :span="7">
            <div style="margin-top: 2vh; text-align: left; font-size: 30px; color: #0f1117;font-family: 'STXingkai', '华文行楷', cursive;">
              考试系统-学生端
            </div>
          </el-col>
          <el-col :span="11">
            <div style="margin-top: 2vh; text-align: left; font-size: 30px; color: #0f1117;font-family: 'STXingkai', '华文行楷', cursive;">
              学习如逆水行舟,不进则退
            </div>
          </el-col>
          <el-col :span="6">
            <div style="margin-top: 2.5vh; text-align: right; font-size: 24px; color: #0f1117;font-family: 'STXingkai', '华文行楷', cursive;">
              {{ nowTime }}
            </div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="5">
            <div style="font-size: 20px;padding-top: 10px;height: 6vh">
              <el-breadcrumb>
                <el-breadcrumb-item v-if="$route.meta.name !=='首页'" :to="{ path: '/student/home' }" style="font-size: 20px">首页</el-breadcrumb-item>
                <el-breadcrumb-item style="font-size: 20px">{{ $route.meta.name }}</el-breadcrumb-item>
              </el-breadcrumb>
              <hr>
            </div>
          </el-col>
          <el-col :span="12">
            <div style="margin-top: 0.5vh; text-align: left; font-size: 30px; color: #0f1117;font-family: 'STXingkai', '华文行楷', cursive;">
              考试是成长路上的挑战,全力以赴,方可破浪前行
            </div>
          </el-col>
          <el-col :span="3">
            <div style="margin-top: 1.5vh; text-align: right; ">
              <a style="font-size: 24px; color: #0f1117;font-family: 'STXingkai', '华文行楷', cursive;margin-right: 20px" @click="home()">首页</a>
              <a style="font-size: 24px; color: #0f1117;font-family: 'STXingkai', '华文行楷', cursive;margin-right: 20px" @click="back()">退出</a>

            </div>
          </el-col>
          <el-col :span="4">
            <el-row>
              <el-col :span="12">
                <img style="width: 40px;height: 40px; border-radius: 50%;float: right;margin-right: 5px" :src="user.photo" />
              </el-col>
              <el-col :span="12">
                <div style="margin-top: 1vh;font-size: 24px; color: #0f1117;font-family: 'STXingkai', '华文行楷', cursive;">
                  {{ user.name }}
                </div>
              </el-col>
            </el-row>
          </el-col>
        </el-row>
      </el-header>
    </el-container>
    <el-container>
      <el-aside style="overflow: hidden; min-height: 88vh; background-color: #f1f2f5; width: 18%">
        <el-menu class="menu" :default-active="$route.path" router background-color="#f1f2f5" text-color="#000" active-text-color="#f6f">
          <el-menu-item index="/student/home">
            <img style="height: 30px" data-isrc="inav5" data-src="nav5" src="https://jwgl.lzjtu.edu.cn/jsxsd/css/images/lzjtdx/nav5.png" alt><span style="font-size: 20px">系统首页</span>
          </el-menu-item>
          <el-submenu index="1">
            <template slot="title">
              <img style="height: 30px" data-isrc="inav10" data-src="nav10" src="https://jwgl.lzjtu.edu.cn/jsxsd/css/images/lzjtdx/nav10.png" alt><span style="font-size: 20px">个人管理</span>
            </template>
            <el-menu-item-group >
              <el-menu-item index="/student/person">
                <img style="height: 30px" data-isrc="inav5" data-src="nav5" src="https://jwgl.lzjtu.edu.cn/jsxsd/css/images/lzjtdx/nav5.png" alt><span style="font-size: 16px">个人中心</span>
              </el-menu-item>
              <el-menu-item index="/student/realNameAuthentication">
                <img style="height: 30px" data-isrc="inav5" data-src="nav5" src="https://jwgl.lzjtu.edu.cn/jsxsd/css/images/lzjtdx/nav5.png" alt><span style="font-size: 16px">实名认证</span>
              </el-menu-item>
              <el-menu-item index="/student/key">
                <img style="height: 30px" data-isrc="inav5" data-src="nav5" src="https://jwgl.lzjtu.edu.cn/jsxsd/css/images/lzjtdx/nav5.png" alt><span style="font-size: 16px">密钥查看</span>
              </el-menu-item>
              <el-menu-item index="/student/password">
                <img style="height: 30px" data-isrc="inav5" data-src="nav5" src="https://jwgl.lzjtu.edu.cn/jsxsd/css/images/lzjtdx/nav5.png" alt><span style="font-size: 16px">密码修改</span>
              </el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title">
              <img style="height: 30px" data-isrc="inav10" data-src="nav10" src="https://jwgl.lzjtu.edu.cn/jsxsd/css/images/lzjtdx/nav10.png" alt><span style="font-size: 20px">学习笔记</span>
            </template>
            <el-menu-item-group >
              <el-menu-item index="/student/createdNote">
                <img style="height: 30px" data-isrc="inav5" data-src="nav5" src="https://jwgl.lzjtu.edu.cn/jsxsd/css/images/lzjtdx/nav5.png" alt><span style="font-size: 16px">创建笔记</span>
              </el-menu-item>
              <el-menu-item index="/student/listNote">
                <img style="height: 30px" data-isrc="inav5" data-src="nav5" src="https://jwgl.lzjtu.edu.cn/jsxsd/css/images/lzjtdx/nav5.png" alt><span style="font-size: 16px">笔记信息</span>
              </el-menu-item>
              <el-menu-item index="/student/createdCode">
                <img style="height: 30px" data-isrc="inav5" data-src="nav5" src="https://jwgl.lzjtu.edu.cn/jsxsd/css/images/lzjtdx/nav5.png" alt><span style="font-size: 16px">代码实操</span>
              </el-menu-item>
              <el-menu-item index="/student/listCode">
                <img style="height: 30px" data-isrc="inav5" data-src="nav5" src="https://jwgl.lzjtu.edu.cn/jsxsd/css/images/lzjtdx/nav5.png" alt><span style="font-size: 16px">代码信息</span>
              </el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">
              <img style="height: 30px" data-isrc="inav10" data-src="nav10" src="https://jwgl.lzjtu.edu.cn/jsxsd/css/images/lzjtdx/nav10.png" alt><span style="font-size: 20px">课程管理</span>
            </template>
            <el-menu-item-group >
              <el-menu-item index="/student/studentCourse">
                <img style="height: 30px" data-isrc="inav5" data-src="nav5" src="https://jwgl.lzjtu.edu.cn/jsxsd/css/images/lzjtdx/nav5.png" alt><span style="font-size: 16px">课程信息</span>
              </el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="4">
          <template slot="title">
            <img style="height: 30px" data-isrc="inav10" data-src="nav10" src="https://jwgl.lzjtu.edu.cn/jsxsd/css/images/lzjtdx/nav10.png" alt><span style="font-size: 20px">考试管理</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="/student/studentExamList">
              <img style="height: 30px" data-isrc="inav5" data-src="nav5" src="https://jwgl.lzjtu.edu.cn/jsxsd/css/images/lzjtdx/nav5.png" alt><span style="font-size: 16px">试卷列表</span>
            </el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view/>
      </el-main>
    </el-container>

  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  data(){
    return{
      nowTime:"",
      user:localStorage.getItem("user") ? (JSON).parse(localStorage.getItem("user")) : "",
    }
  },
  created() {

  },
  mounted() {
    this.getTime()
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
    back(){
      localStorage.removeItem("user");
      this.$router.push("/studentLogin");
    },
    home(){
      this.$router.push("/");
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
.el-menu{
  border-right: none !important;
}
.el-card__body, >>>.el-main {
  padding: 1px;
}
.app{
  height: 100%;
  width: 100%;
  overflow-y: hidden;
  overflow-x: hidden;
  z-index:-1;
}
.menu{
  height: 88vh;
  overflow-y: scroll;
}
.menu::-webkit-scrollbar{
  width:0;
}
.el-main{
  height: 88vh;
  overflow-y: scroll;
}
.el-main::-webkit-scrollbar{
  width:0;
}
</style>

