<!--<template>-->
<!--  <div>-->
<!--    <el-row>-->
<!--      <el-col :span="16">-->
<!--        <div>-->
<!--          <el-carousel :interval="4000" arrow="always" height="50vh">-->
<!--            <div v-for="item in photo">-->
<!--              <el-carousel-item>-->
<!--                <el-image-->
<!--                    style="width: 100%;height: 100%;object-fit: cover;"-->
<!--                    :src="item"-->
<!--                ></el-image>-->
<!--              </el-carousel-item>-->
<!--            </div>-->
<!--          </el-carousel>-->
<!--        </div>-->
<!--      </el-col>-->
<!--      <el-col :span="8" style="padding: 2vh">-->
<!--        <el-row :gutter="20">-->
<!--          <el-col :span="12">-->
<!--            <div class="login-photo a" @click="login? adminLogin() : adminHome()">-->
<!--              <div class="fonts">-->
<!--                <div v-if="login">管理员登录</div>-->
<!--                <div v-if="!login && user.role==='admin'">管理员进入</div>-->
<!--              </div>-->
<!--            </div>-->
<!--          </el-col>-->
<!--          <el-col :span="12">-->
<!--            <div class="login-photo b" @click="login? caInstitutionLogin() : caInstitutionHome()">-->
<!--              <div class="fonts">-->
<!--                <div v-if="login">注册中心登录</div>-->
<!--                <div v-if="!login && user.role==='caInstitution'">注册人员进入</div>-->
<!--              </div>-->
<!--            </div>-->
<!--          </el-col>-->
<!--        </el-row>-->
<!--        <div style="height: 2vh">-->

<!--        </div>-->
<!--        <el-row :gutter="20">-->
<!--          <el-col :span="12">-->
<!--            <div class="login-photo c" @click="login? teacherLogin() : teacherHome()">-->
<!--              <div class="fonts">-->
<!--                <div v-if="login">教师登录</div>-->
<!--                <div v-if="!login && user.role==='teacher'">教师进入</div>-->
<!--              </div>-->
<!--            </div>-->
<!--          </el-col>-->
<!--          <el-col :span="12">-->
<!--            <div class="login-photo d" @click="login? studentLogin() : studentHome()">-->
<!--              <div class="fonts">-->
<!--                <div v-if="login">学生登录</div>-->
<!--                <div v-if="!login && user.role==='student'">学生进入</div>-->
<!--              </div>-->
<!--            </div>-->
<!--          </el-col>-->
<!--        </el-row>-->
<!--      </el-col>-->
<!--    </el-row>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--export default {-->
<!--  name: "home",-->
<!--  data() {-->
<!--    return {-->
<!--      user: localStorage.getItem("user") ? (JSON).parse(localStorage.getItem("user")) : {},-->
<!--      timer: "", //定义一个定时器-->
<!--      nowTime: "", // 当前日期时间-->
<!--      photo: [-->
<!--        'https://lib.tsinghua.edu.cn/images/duzaidongji.jpg',-->
<!--        'https://www.nlc.cn/upload/img/2023-11-21/f080014a.jpg',-->
<!--        'https://www.lib.sjtu.edu.cn/upload/images/1692425282019_pic6.jpg',-->
<!--        'https://www.lib.sjtu.edu.cn/upload/images/1692425148797_pic2.jpg',-->
<!--      ],-->
<!--      login: "",//是否登录，没登陆赋值”登录“-->
<!--      lgu: "" //是否登录，在头部进行显示-->
<!--    }-->
<!--  },-->
<!--  created() {-->
<!--    this.item()-->
<!--  },-->
<!--  mounted() {-->

<!--  },-->
<!--  methods: {-->
<!--    //登录入口-->
<!--    adminLogin() {-->
<!--      this.$router.push("/adminLogin")-->
<!--    },-->
<!--    adminHome() {-->
<!--      if (this.user.role === "admin") {-->
<!--        this.$router.push("/admin/home")-->
<!--      } else {-->
<!--        this.$message.error("没权限访问")-->
<!--      }-->
<!--    },-->

<!--    teacherLogin() {-->
<!--      this.$router.push("/teacherLogin")-->
<!--    },-->
<!--    teacherHome() {-->
<!--      if (this.user.role === "teacher") {-->
<!--        this.$router.push("/teacher/home")-->
<!--      } else {-->
<!--        this.$message.error("没权限访问")-->
<!--      }-->
<!--    },-->

<!--    studentLogin() {-->
<!--      this.$router.push("/studentLogin")-->
<!--    },-->
<!--    studentHome() {-->
<!--      if (this.user.role === "student") {-->
<!--        this.$router.push("/student/home")-->
<!--      } else {-->
<!--        this.$message.error("没权限访问")-->
<!--      }-->
<!--    },-->

<!--    caInstitutionLogin() {-->
<!--      this.$router.push("/caInstitutionLogin")-->
<!--    },-->
<!--    caInstitutionHome() {-->
<!--      if (this.user.role === "caInstitution") {-->
<!--        this.$router.push("/caInstitution/home")-->
<!--      } else {-->
<!--        this.$message.error("没权限访问")-->
<!--      }-->
<!--    },-->
<!--    //用户登录检查-->
<!--    item() {-->
<!--      const isEmpty = Object.keys(this.user).length === 0;-->
<!--      if (isEmpty) {-->
<!--        this.login = true-->
<!--        this.lgu = "请登录"-->
<!--      } else {-->
<!--        this.lgu = this.user.role + ":" + this.user.name-->
<!--        this.login = false-->
<!--      }-->
<!--    },-->
<!--  },-->
<!--  beforeDestroy() {-->
<!--    if (this.timer) {-->
<!--      clearInterval(this.timer);-->
<!--    }-->
<!--  },-->
<!--}-->
<!--</script>-->

<!--<style scoped>-->
<!--.login-photo {-->
<!--  height: 23vh;-->
<!--  width: 100%;-->
<!--  border-radius: 30px;-->
<!--  overflow: hidden;-->
<!--  background-size: 100%;-->
<!--}-->

<!--.a {-->
<!--  background-image: url('@/assets/home/login_button/admin.jpg');-->
<!--}-->

<!--.b {-->
<!--  background-image: url('@/assets/home/login_button/caInstitution.jpg');-->
<!--}-->

<!--.c {-->
<!--  background-image: url('@/assets/home/login_button/teacher.jpg');-->
<!--}-->

<!--.d {-->
<!--  background-image: url('@/assets/home/login_button/student.jpg');-->
<!--}-->

<!--.fonts {-->
<!--  color: rgba(245, 245, 98, 0.75);-->
<!--  background-color: rgba(5, 6, 7, 0.68);-->
<!--  font-size: 30px;-->
<!--  text-align: center;-->
<!--  margin-top: 10vh;-->
<!--}-->
<!--</style>-->





<template>
  <div class="home-container">
    <el-row :gutter="20">
      <!-- 轮播图区域 -->
      <el-col :span="16" class="carousel-col">
        <el-carousel
            :interval="4000"
            arrow="always"
            height="60vh"
            indicator-position="outside"
        >
          <el-carousel-item v-for="(item, index) in photos" :key="index">
            <el-image
                :src="item"
                style="width: 100%;height: 100%;object-fit: cover;"
                class="carousel-image"
            />
          </el-carousel-item>
        </el-carousel>
      </el-col>

      <!-- 登录入口区域 -->
      <el-col :span="8" class="login-col">
        <div class="login-grid">
          <div
              v-for="(item, index) in loginItems"
              :key="index"
              class="login-card"
              :class="[item.className, { 'active-entry': isActiveEntry(item) }]"
              @click="handleAction(item)"
          >
            <div class="card-content">
              <div class="icon-wrapper">
                <i :class="item.icon" class="role-icon"></i>
                <i
                    v-if="isActiveEntry(item)"
                    class="el-icon-circle-check entry-badge"
                ></i>
              </div>
              <div class="text-group">
                <div class="main-text">{{ getButtonText(item) }}</div>
                <div v-if="isActiveEntry(item)" class="sub-text">
                  点击进入工作台
                </div>
              </div>
            </div>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "HomePage",
  data() {
    return {
      photos: [
        'https://lib.tsinghua.edu.cn/images/duzaidongji.jpg',
        'https://www.nlc.cn/upload/img/2023-11-21/f080014a.jpg',
        'https://www.lib.sjtu.edu.cn/upload/images/1692425282019_pic6.jpg',
        'https://www.lib.sjtu.edu.cn/upload/images/1692425148797_pic2.jpg',
      ],
      loginItems: [
        {
          className: 'admin',
          role: 'admin',
          loginText: '管理员登录',
          enterText: '管理员入口',
          route: 'admin',
          icon: 'el-icon-s-custom'
        },
        {
          className: 'ca-institution',
          role: 'caInstitution',
          loginText: '注册中心登录',
          enterText: '注册中心入口',
          route: 'caInstitution',
          icon: 'el-icon-office-building'
        },
        {
          className: 'teacher',
          role: 'teacher',
          loginText: '教师登录',
          enterText: '教师入口',
          route: 'teacher',
          icon: 'el-icon-user-solid'
        },
        {
          className: 'student',
          role: 'student',
          loginText: '学生登录',
          enterText: '学生入口',
          route: 'student',
          icon: 'el-icon-s-data'
        }
      ]
    }
  },
  computed: {
    user() {
      return JSON.parse(localStorage.getItem("user")) || {}
    },
    isLoggedIn() {
      return Object.keys(this.user).length > 0
    }
  },
  methods: {
    getButtonText(item) {
      return this.isLoggedIn ? item.enterText : item.loginText
    },
    isActiveEntry(item) {
      return this.isLoggedIn && this.user.role === item.role
    },
    handleAction(item) {
      const path = this.isLoggedIn
          ? `/${item.route}/home`
          : `/${item.route}Login`

      if (this.isLoggedIn && !this.isActiveEntry(item)) {
        this.$message.error('无权限访问该页面')
        return
      }

      this.$router.push(path)
    }
  }
}
</script>

<style scoped>
.home-container {
  padding: 20px;
  background: #f8f9fa;
}

.carousel-col {
  padding-right: 10px;
}

.login-col {
  padding-left: 10px;
}

.login-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
  height: 60vh;
}

.login-card {
  height: calc((60vh - 20px) / 2);
  border-radius: 15px;
  background-size: cover;
  background-position: center;
  cursor: pointer;
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
  position: relative;
  overflow: hidden;
  border: 2px solid transparent;
}

.login-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 10px 20px rgba(0,0,0,0.6);
}

.login-card::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.6);
  transition: all 0.3s ease;
}

/* 激活状态样式 */
.active-entry {
  border-color: #67c23a;
  box-shadow: 0 4px 15px rgba(3, 3, 61, 0.4);
}

.active-entry::before {
  background: rgba(3, 3, 61, 0.4);
}

.card-content {
  position: relative;
  height: 100%;
  padding: 25px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  color: #fff;
}

.icon-wrapper {
  position: relative;
  margin-bottom: 20px;
}

.role-icon {
  font-size: 2.5em;
  filter: drop-shadow(0 2px 4px rgba(0,0,0,0.3));
  transition: transform 0.3s ease;
}

.entry-badge {
  position: absolute;
  right: -10px;
  top: -5px;
  font-size: 1.8em;
  color: #67c23a;
  background: rgba(255,255,255,0.9);
  border-radius: 50%;
  padding: 3px;
}

.text-group {
  text-align: center;
}

.main-text {
  font-size: 1.4em;
  font-weight: 500;
  letter-spacing: 1px;
  text-shadow: 0 2px 4px rgba(0,0,0,0.6);
}

.sub-text {
  font-size: 0.9em;
  margin-top: 8px;
  opacity: 0.9;
  color: #e8ffe3;
}

/* 背景图配置 */
.admin { background-image: url('@/assets/home/login_button/admin.jpg'); }
.ca-institution { background-image: url('@/assets/home/login_button/caInstitution.jpg'); }
.teacher { background-image: url('@/assets/home/login_button/teacher.jpg'); }
.student { background-image: url('@/assets/home/login_button/student.jpg'); }

@media (max-width: 768px) {
  .home-container {
    padding: 10px;
  }

  .el-col {
    width: 100%;
    padding: 10px !important;
  }

  .login-grid {
    grid-template-columns: 1fr;
    height: auto;
  }

  .login-card {
    height: 120px;
  }

  .role-icon {
    font-size: 2em;
  }

  .main-text {
    font-size: 1.2em;
  }
}
</style>