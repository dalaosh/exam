<template>
  <div class="teacher-shell">
    <el-container class="teacher-shell__layout">
      <el-aside class="teacher-shell__aside teacher-shell__aside--cyber">
        <div class="teacher-shell__brand teacher-shell__brand--cyber">
          <span class="teacher-shell__brand-mark">
            <i class="el-icon-s-cooperation"></i>
          </span>
          <div>
            <p class="teacher-shell__brand-title">考试系统</p>
            <p class="teacher-shell__brand-subtitle">教师工作台</p>
          </div>
        </div>

        <el-menu
          class="teacher-shell__menu teacher-shell__menu--cyber"
          :default-active="$route.path"
          router
          background-color="transparent"
          text-color="#e2e8f0"
          active-text-color="#ffffff"
        >
          <el-menu-item index="/teacher/home">
            <span class="teacher-shell__menu-icon"><i class="el-icon-s-home"></i></span>
            <span class="teacher-shell__menu-label">系统首页</span>
          </el-menu-item>

          <el-submenu index="1">
            <template slot="title">
              <span class="teacher-shell__menu-icon"><i class="el-icon-user-solid"></i></span>
              <span class="teacher-shell__menu-label">个人管理</span>
            </template>
            <el-menu-item index="/teacher/person">
              <span class="teacher-shell__menu-icon"><i class="el-icon-user"></i></span>
              <span class="teacher-shell__menu-label">个人中心</span>
            </el-menu-item>
            <el-menu-item index="/teacher/key">
              <span class="teacher-shell__menu-icon"><i class="el-icon-key"></i></span>
              <span class="teacher-shell__menu-label">密钥查看</span>
            </el-menu-item>
            <el-menu-item index="/teacher/password">
              <span class="teacher-shell__menu-icon"><i class="el-icon-lock"></i></span>
              <span class="teacher-shell__menu-label">密码修改</span>
            </el-menu-item>
          </el-submenu>

          <el-submenu index="2">
            <template slot="title">
              <span class="teacher-shell__menu-icon"><i class="el-icon-s-custom"></i></span>
              <span class="teacher-shell__menu-label">用户管理</span>
            </template>
            <el-menu-item index="/teacher/student">
              <span class="teacher-shell__menu-icon"><i class="el-icon-s-check"></i></span>
              <span class="teacher-shell__menu-label">学生列表</span>
            </el-menu-item>
          </el-submenu>

          <el-submenu index="3">
            <template slot="title">
              <span class="teacher-shell__menu-icon"><i class="el-icon-reading"></i></span>
              <span class="teacher-shell__menu-label">课程管理</span>
            </template>
            <el-menu-item index="/teacher/creatCourse">
              <span class="teacher-shell__menu-icon"><i class="el-icon-circle-plus-outline"></i></span>
              <span class="teacher-shell__menu-label">创建课程</span>
            </el-menu-item>
            <el-menu-item index="/teacher/teacherCourse">
              <span class="teacher-shell__menu-icon"><i class="el-icon-notebook-1"></i></span>
              <span class="teacher-shell__menu-label">课程信息</span>
            </el-menu-item>
          </el-submenu>

          <el-submenu index="4">
            <template slot="title">
              <span class="teacher-shell__menu-icon"><i class="el-icon-collection"></i></span>
              <span class="teacher-shell__menu-label">题库管理</span>
            </template>
            <el-menu-item index="/teacher/teacherQuestionCodeCreated">
              <span class="teacher-shell__menu-icon"><i class="el-icon-edit-outline"></i></span>
              <span class="teacher-shell__menu-label">创建编程题</span>
            </el-menu-item>
            <el-menu-item index="/teacher/teacherQuestionCodeList">
              <span class="teacher-shell__menu-icon"><i class="el-icon-document"></i></span>
              <span class="teacher-shell__menu-label">编程题列表</span>
            </el-menu-item>
            <el-menu-item index="/teacher/teacherQuestionFillCreated">
              <span class="teacher-shell__menu-icon"><i class="el-icon-circle-plus-outline"></i></span>
              <span class="teacher-shell__menu-label">创建填空题</span>
            </el-menu-item>
            <el-menu-item index="/teacher/teacherQuestionFillList">
              <span class="teacher-shell__menu-icon"><i class="el-icon-tickets"></i></span>
              <span class="teacher-shell__menu-label">填空题列表</span>
            </el-menu-item>
            <el-menu-item index="/teacher/teacherQuestionJudgeCreated">
              <span class="teacher-shell__menu-icon"><i class="el-icon-circle-plus-outline"></i></span>
              <span class="teacher-shell__menu-label">创建判断题</span>
            </el-menu-item>
            <el-menu-item index="/teacher/teacherQuestionJudgeList">
              <span class="teacher-shell__menu-icon"><i class="el-icon-document"></i></span>
              <span class="teacher-shell__menu-label">判断题列表</span>
            </el-menu-item>
            <el-menu-item index="/teacher/teacherQuestionMultiCreated">
              <span class="teacher-shell__menu-icon"><i class="el-icon-circle-plus-outline"></i></span>
              <span class="teacher-shell__menu-label">创建选择题</span>
            </el-menu-item>
            <el-menu-item index="/teacher/teacherQuestionMultiList">
              <span class="teacher-shell__menu-icon"><i class="el-icon-document"></i></span>
              <span class="teacher-shell__menu-label">选择题列表</span>
            </el-menu-item>
            <el-menu-item index="/teacher/teacherQuestionShortAnsCreated">
              <span class="teacher-shell__menu-icon"><i class="el-icon-circle-plus-outline"></i></span>
              <span class="teacher-shell__menu-label">创建简答题</span>
            </el-menu-item>
            <el-menu-item index="/teacher/teacherQuestionShortAnsList">
              <span class="teacher-shell__menu-icon"><i class="el-icon-document"></i></span>
              <span class="teacher-shell__menu-label">简答题列表</span>
            </el-menu-item>
          </el-submenu>

          <el-submenu index="5">
            <template slot="title">
              <span class="teacher-shell__menu-icon"><i class="el-icon-s-order"></i></span>
              <span class="teacher-shell__menu-label">考试管理</span>
            </template>
            <el-menu-item index="/teacher/teacherCreatedManager">
              <span class="teacher-shell__menu-icon"><i class="el-icon-circle-plus-outline"></i></span>
              <span class="teacher-shell__menu-label">创建试卷</span>
            </el-menu-item>
            <el-menu-item index="/teacher/teacherManagerList">
              <span class="teacher-shell__menu-icon"><i class="el-icon-document-copy"></i></span>
              <span class="teacher-shell__menu-label">试卷列表</span>
            </el-menu-item>
            <el-menu-item index="/teacher/teacherMarkPaperList">
              <span class="teacher-shell__menu-icon"><i class="el-icon-edit"></i></span>
              <span class="teacher-shell__menu-label">试卷批阅</span>
            </el-menu-item>
            <el-menu-item index="/teacher/teacherMarkList">
              <span class="teacher-shell__menu-icon"><i class="el-icon-data-analysis"></i></span>
              <span class="teacher-shell__menu-label">成绩发布</span>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container class="teacher-shell__body">
        <el-header class="teacher-shell__header">
          <div class="teacher-shell__header-main">
            <h1 class="teacher-shell__title">教师工作台</h1>
            <p class="teacher-shell__subtitle">查看课程、考试、学生和阅卷相关数据。</p>
          </div>

          <div class="teacher-shell__toolbar">
            <div class="teacher-shell__toolbar-time">
              <span class="teacher-shell__toolbar-label">系统时间</span>
              <span class="teacher-shell__time">{{ nowTime }}</span>
            </div>
            <div class="teacher-shell__action-group">
              <el-button plain @click="home">返回首页</el-button>
              <el-button type="primary" plain @click="back">退出登录</el-button>
            </div>
            <div class="teacher-shell__user teacher-shell__user--compact">
              <img class="teacher-shell__user-avatar" :src="user.photo" alt="">
              <div class="teacher-shell__user-meta">
                <span class="teacher-shell__user-name">{{ user.name || "教师" }}</span>
                <span class="teacher-shell__user-role">当前登录用户</span>
              </div>
            </div>
          </div>
        </el-header>

        <div class="teacher-shell__content">
          <div class="teacher-shell__breadcrumb-card">
            <div class="teacher-shell__breadcrumb-main">
              <span class="teacher-shell__breadcrumb-label">当前位置</span>
              <el-breadcrumb separator="/">
                <el-breadcrumb-item v-if="$route.meta.name !== '首页'" :to="{ path: '/teacher/home' }">
                  首页
                </el-breadcrumb-item>
                <el-breadcrumb-item>{{ currentPageName }}</el-breadcrumb-item>
              </el-breadcrumb>
            </div>
            <span class="teacher-shell__breadcrumb-note">当前模块：{{ currentPageName }}</span>
          </div>

          <el-main class="teacher-shell__main">
            <router-view />
          </el-main>
        </div>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      nowTime: "",
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
    };
  },
  computed: {
    currentPageName() {
      return this.$route.meta.name || "教师工作台";
    }
  },
  mounted() {
    this.getTime();
  },
  methods: {
    getTime() {
      this.timer = setInterval(() => {
        const timeDate = new Date();
        const year = timeDate.getFullYear();
        const mounth = timeDate.getMonth() + 1;
        const day = timeDate.getDate();
        let hours = timeDate.getHours();
        hours = hours >= 10 ? hours : "0" + hours;
        let minutes = timeDate.getMinutes();
        minutes = minutes >= 10 ? minutes : "0" + minutes;
        let seconds = timeDate.getSeconds();
        seconds = seconds >= 10 ? seconds : "0" + seconds;
        const week = timeDate.getDay();
        const weekArr = [
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
    back() {
      localStorage.removeItem("user");
      this.$router.push("/teacherLogin");
    },
    home() {
      this.$router.push("/");
    }
  },
  beforeDestroy() {
    if (this.timer) {
      clearInterval(this.timer);
    }
  },
};
</script>

<style lang="scss" src="../styles/theme.scss"></style>
<style scoped lang="scss">
.teacher-shell__aside--cyber {
  --menu-bg-color: #0d1117;
  --menu-text-color: #8a96aa;
  --menu-active-color: #5cf2ff;
  --menu-hover-bg-color: rgba(92, 242, 255, 0.08);
  position: relative;
  overflow: hidden;
  background:
    linear-gradient(180deg, rgba(13, 17, 23, 0.98), rgba(11, 16, 24, 0.99)),
    radial-gradient(circle at top left, rgba(92, 242, 255, 0.12), transparent 28%),
    radial-gradient(circle at bottom right, rgba(71, 117, 255, 0.12), transparent 24%);
  border-right: 1px solid rgba(92, 242, 255, 0.12);
  box-shadow: 22px 0 52px rgba(2, 7, 15, 0.48);
}

.teacher-shell__aside--cyber::before,
.teacher-shell__aside--cyber::after {
  content: "";
  position: absolute;
  inset: 0;
  pointer-events: none;
}

.teacher-shell__aside--cyber::before {
  background:
    linear-gradient(rgba(92, 242, 255, 0.05) 1px, transparent 1px),
    linear-gradient(90deg, rgba(92, 242, 255, 0.04) 1px, transparent 1px);
  background-size: 26px 26px;
  opacity: 0.22;
  mask-image: linear-gradient(180deg, rgba(0, 0, 0, 0.76), transparent 96%);
}

.teacher-shell__aside--cyber::after {
  background:
    linear-gradient(140deg, transparent 0 34%, rgba(92, 242, 255, 0.1) 42%, transparent 50% 100%),
    radial-gradient(circle at 18% 12%, rgba(92, 242, 255, 0.18), transparent 0 14%);
  animation: teacher-cyber-scan 18s linear infinite;
}

.teacher-shell__brand--cyber {
  position: relative;
  z-index: 1;
  margin-bottom: 14px;
  padding: 10px 12px 24px;
}

.teacher-shell__brand--cyber::after {
  content: "";
  position: absolute;
  left: 6px;
  right: 6px;
  bottom: 4px;
  height: 1px;
  background: linear-gradient(90deg, transparent, rgba(92, 242, 255, 0.3), transparent);
}

.teacher-shell__brand--cyber .teacher-shell__brand-mark {
  position: relative;
  border-radius: 14px;
  background:
    linear-gradient(135deg, rgba(92, 242, 255, 0.22), rgba(92, 242, 255, 0.08)),
    rgba(13, 17, 23, 0.92);
  border: 1px solid rgba(92, 242, 255, 0.2);
  color: var(--menu-active-color);
  box-shadow:
    0 0 0 1px rgba(92, 242, 255, 0.08),
    0 0 18px rgba(92, 242, 255, 0.24),
    inset 0 0 14px rgba(92, 242, 255, 0.08);
  animation: teacher-brand-breathe 3s ease-in-out infinite;
}

.teacher-shell__brand--cyber .teacher-shell__brand-mark::after {
  content: "";
  position: absolute;
  inset: 5px;
  border: 1px solid rgba(92, 242, 255, 0.16);
  border-radius: 10px;
}

.teacher-shell__brand--cyber .teacher-shell__brand-title {
  font-family: "Consolas", "SFMono-Regular", "Liberation Mono", monospace;
  letter-spacing: 0.12em;
  text-transform: uppercase;
  color: #effcff;
}

.teacher-shell__brand--cyber .teacher-shell__brand-subtitle {
  letter-spacing: 0.14em;
  text-transform: uppercase;
  color: rgba(138, 150, 170, 0.88);
}

.teacher-shell__menu--cyber {
  position: relative;
  z-index: 1;
  padding-right: 4px;
}

.teacher-shell__menu--cyber.el-menu {
  background: var(--menu-bg-color) !important;
  border-right: none !important;
}

.teacher-shell__menu--cyber ::v-deep .el-menu {
  background: transparent !important;
}

.teacher-shell__menu--cyber ::v-deep .el-menu-item,
.teacher-shell__menu--cyber ::v-deep .el-submenu__title {
  position: relative;
  display: flex;
  align-items: center;
  height: 48px;
  margin-bottom: 8px;
  padding-left: 16px !important;
  padding-right: 14px !important;
  border-radius: 12px;
  border: 1px solid transparent;
  background: transparent !important;
  color: var(--menu-text-color) !important;
  overflow: hidden;
  transition:
    background-color 0.2s ease,
    color 0.2s ease,
    border-color 0.2s ease,
    box-shadow 0.2s ease,
    transform 0.2s ease;
}

.teacher-shell__menu--cyber ::v-deep .el-menu-item::before,
.teacher-shell__menu--cyber ::v-deep .el-submenu__title::before {
  content: "";
  position: absolute;
  inset: 0;
  background: linear-gradient(90deg, rgba(92, 242, 255, 0.06), transparent 60%);
  opacity: 0;
  transition: opacity 0.2s ease;
}

.teacher-shell__menu--cyber ::v-deep .el-menu-item:hover,
.teacher-shell__menu--cyber ::v-deep .el-submenu__title:hover {
  background: var(--menu-hover-bg-color) !important;
  border-color: rgba(92, 242, 255, 0.14);
  color: #d5fbff !important;
  box-shadow: inset 0 0 0 1px rgba(92, 242, 255, 0.08);
  transform: translateX(2px);
}

.teacher-shell__menu--cyber ::v-deep .el-menu-item:hover::before,
.teacher-shell__menu--cyber ::v-deep .el-submenu__title:hover::before {
  opacity: 1;
}

.teacher-shell__menu--cyber ::v-deep .el-menu-item.is-active {
  color: var(--menu-active-color) !important;
  background: rgba(92, 242, 255, 0.1) !important;
  border-color: rgba(92, 242, 255, 0.18);
  box-shadow:
    inset 3px 0 0 0 var(--menu-active-color),
    inset 0 0 0 1px rgba(92, 242, 255, 0.06),
    0 0 16px rgba(92, 242, 255, 0.08);
  text-shadow: 0 0 8px rgba(92, 242, 255, 0.9);
}

.teacher-shell__menu--cyber ::v-deep .el-menu-item.is-active .teacher-shell__menu-icon,
.teacher-shell__menu--cyber ::v-deep .el-menu-item.is-active .teacher-shell__menu-label {
  color: var(--menu-active-color) !important;
}

.teacher-shell__menu--cyber ::v-deep .el-submenu.is-active > .el-submenu__title {
  color: #d9f4fb !important;
  border-color: rgba(92, 242, 255, 0.12);
}

.teacher-shell__menu--cyber ::v-deep .el-submenu + .el-submenu {
  margin-top: 18px;
}

.teacher-shell__menu--cyber ::v-deep .el-submenu + .el-submenu::before {
  content: "";
  display: block;
  height: 1px;
  margin: 0 10px 18px;
  background: linear-gradient(90deg, transparent, rgba(92, 242, 255, 0.25), transparent);
}

.teacher-shell__menu--cyber ::v-deep .el-submenu__title {
  height: 42px;
  margin-bottom: 10px;
  background: rgba(255, 255, 255, 0.01) !important;
  color: #5a6679 !important;
  font-size: 11px;
  font-weight: 700;
  letter-spacing: 0.18em;
  text-transform: uppercase;
}

.teacher-shell__menu--cyber ::v-deep .el-submenu__title i {
  color: inherit !important;
}

.teacher-shell__menu--cyber ::v-deep .el-submenu .el-menu-item {
  width: calc(100% - 16px);
  height: 42px;
  margin: 0 8px 6px;
  padding-left: 18px !important;
  border-radius: 10px;
}

.teacher-shell__menu--cyber .teacher-shell__menu-icon {
  width: 30px;
  margin-right: 10px;
  color: inherit;
}

.teacher-shell__menu--cyber .teacher-shell__menu-label {
  font-weight: 600;
  letter-spacing: 0.02em;
}

.teacher-shell__menu--cyber ::v-deep .el-submenu .el-submenu__icon-arrow {
  margin-top: -5px;
  color: #5a6679 !important;
  transition: transform 0.2s ease, color 0.2s ease;
}

.teacher-shell__menu--cyber ::v-deep .el-submenu.is-opened > .el-submenu__title .el-submenu__icon-arrow,
.teacher-shell__menu--cyber ::v-deep .el-submenu__title:hover .el-submenu__icon-arrow {
  color: var(--menu-active-color) !important;
}

.teacher-shell__aside--cyber::-webkit-scrollbar,
.teacher-shell__menu--cyber ::v-deep .el-menu::-webkit-scrollbar {
  width: 6px;
}

.teacher-shell__aside--cyber::-webkit-scrollbar-track,
.teacher-shell__menu--cyber ::v-deep .el-menu::-webkit-scrollbar-track {
  background: rgba(255, 255, 255, 0.03);
}

.teacher-shell__aside--cyber::-webkit-scrollbar-thumb,
.teacher-shell__menu--cyber ::v-deep .el-menu::-webkit-scrollbar-thumb {
  border-radius: 999px;
  background: linear-gradient(180deg, rgba(92, 242, 255, 0.78), rgba(92, 242, 255, 0.26));
  box-shadow: 0 0 10px rgba(92, 242, 255, 0.22);
}

@keyframes teacher-brand-breathe {
  0%,
  100% {
    box-shadow:
      0 0 0 1px rgba(92, 242, 255, 0.08),
      0 0 16px rgba(92, 242, 255, 0.18),
      inset 0 0 14px rgba(92, 242, 255, 0.06);
  }

  50% {
    box-shadow:
      0 0 0 1px rgba(92, 242, 255, 0.14),
      0 0 28px rgba(92, 242, 255, 0.28),
      inset 0 0 20px rgba(92, 242, 255, 0.12);
  }
}

@keyframes teacher-cyber-scan {
  0% {
    transform: translate3d(-4%, -2%, 0);
  }

  50% {
    transform: translate3d(3%, 2%, 0);
  }

  100% {
    transform: translate3d(-4%, -2%, 0);
  }
}
</style>
