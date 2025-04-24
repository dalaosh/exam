<template>
  <div class="app">
    <el-container>
      <el-header height="8vh" class="headers">
        <el-row>
          <el-col :span="14">
            <div style="margin-top: 2vh; text-align: left; font-size: 30px; color: #0f1117;font-family: 'STXingkai', '华文行楷', cursive;">
              考试系统-CA机构端
            </div>
          </el-col>
          <el-col :span="6">
            <div style="margin-top: 2.5vh; text-align: right; font-size: 24px; color: #0f1117;font-family: 'STXingkai', '华文行楷', cursive;">
              {{ nowTime }}
            </div>
          </el-col>
          <el-col :span="4">
            <el-dropdown split-button @command="handleCommand" style="float: right;margin-top: 2vh">
              <i class="el-icon-user-solid">{{user.name}}</i>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item command="a" icon="el-icon-user">首页</el-dropdown-item>
                <el-dropdown-item command="b" icon="el-icon-search">百度</el-dropdown-item>
                <el-dropdown-item command="c" icon="el-icon-switch-button">退出</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </el-col>
        </el-row>
      </el-header>
    </el-container>

    <el-container>
      <el-aside style="overflow: hidden; min-height: 92vh;width: 18%">
        <el-menu class="menu" :default-active="$route.path" router background-color="#f1f2f5" text-color="#000" active-text-color="#f6f">
          <el-menu-item index="/caInstitution/home">
            <img style="height: 30px" data-isrc="inav5" data-src="nav5" src="https://jwgl.lzjtu.edu.cn/jsxsd/css/images/lzjtdx/nav5.png" alt><span style="font-size: 20px">系统首页</span>
          </el-menu-item>
          <el-submenu index="1">
            <template slot="title">
              <img style="height: 30px" data-isrc="inav10" data-src="nav10" src="https://jwgl.lzjtu.edu.cn/jsxsd/css/images/lzjtdx/nav10.png" alt><span style="font-size: 20px">用户管理</span>
            </template>
            <el-menu-item-group >
              <el-menu-item index="/caInstitution/admin">
                <img style="height: 30px" data-isrc="inav5" data-src="nav5" src="https://jwgl.lzjtu.edu.cn/jsxsd/css/images/lzjtdx/nav5.png" alt><span style="font-size: 16px">管理员列表</span>
              </el-menu-item>
              <el-menu-item index="/caInstitution/caInstitution">
                <img style="height: 30px" data-isrc="inav5" data-src="nav5" src="https://jwgl.lzjtu.edu.cn/jsxsd/css/images/lzjtdx/nav5.png" alt><span style="font-size: 16px">机构列表</span>
              </el-menu-item>
              <el-menu-item index="/caInstitution/teacher">
                <img style="height: 30px" data-isrc="inav5" data-src="nav5" src="https://jwgl.lzjtu.edu.cn/jsxsd/css/images/lzjtdx/nav5.png" alt><span style="font-size: 16px">教师列表</span>
              </el-menu-item>
              <el-menu-item index="/caInstitution/student">
                <img style="height: 30px" data-isrc="inav5" data-src="nav5" src="https://jwgl.lzjtu.edu.cn/jsxsd/css/images/lzjtdx/nav5.png" alt><span style="font-size: 16px">学生列表</span>
              </el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title">
              <img style="height: 30px" data-isrc="inav10" data-src="nav10" src="https://jwgl.lzjtu.edu.cn/jsxsd/css/images/lzjtdx/nav10.png" alt><span style="font-size: 20px">个人管理</span>
            </template>
            <el-menu-item-group >
              <el-menu-item index="/caInstitution/person">
                <img style="height: 30px" data-isrc="inav5" data-src="nav5" src="https://jwgl.lzjtu.edu.cn/jsxsd/css/images/lzjtdx/nav5.png" alt><span style="font-size: 16px">个人中心</span>
              </el-menu-item>
              <el-menu-item index="/caInstitution/key">
                <img style="height: 30px" data-isrc="inav5" data-src="nav5" src="https://jwgl.lzjtu.edu.cn/jsxsd/css/images/lzjtdx/nav5.png" alt><span style="font-size: 16px">密钥查看</span>
              </el-menu-item>
              <el-menu-item index="/caInstitution/password">
                <img style="height: 30px" data-isrc="inav5" data-src="nav5" src="https://jwgl.lzjtu.edu.cn/jsxsd/css/images/lzjtdx/nav5.png" alt><span style="font-size: 16px">密码修改</span>
              </el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">
              <img style="height: 30px" data-isrc="inav10" data-src="nav10" src="https://jwgl.lzjtu.edu.cn/jsxsd/css/images/lzjtdx/nav10.png" alt><span style="font-size: 20px">学校管理</span>
            </template>
            <el-menu-item-group >
              <el-menu-item index="/caInstitution/caInstitutionUniversity">
                <img style="height: 30px" data-isrc="inav5" data-src="nav5" src="https://jwgl.lzjtu.edu.cn/jsxsd/css/images/lzjtdx/nav5.png" alt><span style="font-size: 16px">学校列表</span>
              </el-menu-item>
              <el-menu-item index="/caInstitution/caInstitutionColleges">
                <img style="height: 30px" data-isrc="inav5" data-src="nav5" src="https://jwgl.lzjtu.edu.cn/jsxsd/css/images/lzjtdx/nav5.png" alt><span style="font-size: 16px">学院列表</span>
              </el-menu-item>
              <el-menu-item index="/caInstitution/caInstitutionMajor">
                <img style="height: 30px" data-isrc="inav5" data-src="nav5" src="https://jwgl.lzjtu.edu.cn/jsxsd/css/images/lzjtdx/nav5.png" alt><span style="font-size: 16px">专业列表</span>
              </el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="4">
            <template slot="title">
              <img style="height: 30px" data-isrc="inav10" data-src="nav10" src="https://jwgl.lzjtu.edu.cn/jsxsd/css/images/lzjtdx/nav10.png" alt><span style="font-size: 20px">实名认证</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/caInstitution/caInstitutionRealFace">
                <img style="height: 30px" data-isrc="inav5" data-src="nav5" src="https://jwgl.lzjtu.edu.cn/jsxsd/css/images/lzjtdx/nav5.png" alt><span style="font-size: 16px">学生认证</span>
              </el-menu-item>
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>
        <el-row style="height: 6vh">
          <div style="font-size: 20px;padding-top: 10px;height: 6vh">
            <el-breadcrumb>
              <el-breadcrumb-item v-if="$route.meta.name !=='首页'" :to="{ path: '/student/home' }" style="font-size: 20px">首页</el-breadcrumb-item>
              <el-breadcrumb-item style="font-size: 20px">{{ $route.meta.name }}</el-breadcrumb-item>
            </el-breadcrumb>
            <hr>
          </div>
        </el-row>
        <el-row class="mains">
          <router-view/>
        </el-row>
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  data(){
    return{
      user:localStorage.getItem("user") ? (JSON).parse(localStorage.getItem("user")) : {},
      nowTime:""
    }
  },
  created() {
  },
  mounted() {
    this.getTime()
  },
  methods: {
    handleCommand(command) {
      if(command==='a'){
        this.$router.push("/");
      }
      if(command==='b'){
        window.open("https://www.baidu.com","_blank")
      }
      if(command==='c'){
        localStorage.removeItem("user");
        this.$router.push("/caInstitutionLogin");
      }
    },
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
  height: 92vh;
  overflow-y: scroll;
}
.menu::-webkit-scrollbar{
  width:0;
}
.el-main{
  height: 92vh;
  overflow-y: scroll;
}
.el-main::-webkit-scrollbar{
  width:0;
}
.headers{
  background: linear-gradient(to bottom right, #c1e1f6, #7db5f6);
}
.mains{
  height: 85vh;
  overflow-y: scroll;
}
.mains::-webkit-scrollbar{
  width:0;
}
</style>

