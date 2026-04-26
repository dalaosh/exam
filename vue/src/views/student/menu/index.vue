<template>
  <div class="student-shell">
    <el-container class="student-shell__layout">
      <el-aside class="student-shell__aside student-shell__aside--cyber">
        <div class="student-shell__brand student-shell__brand--cyber">
          <span class="student-shell__brand-mark"><i class="el-icon-reading"></i></span>
          <div>
            <p class="student-shell__brand-title">考试系统</p>
            <p class="student-shell__brand-subtitle">学生学习中心</p>
          </div>
        </div>

        <el-menu
          class="student-shell__menu student-shell__menu--cyber"
          :default-active="$route.path"
          router
          background-color="transparent"
          text-color="#e2e8f0"
          active-text-color="#ffffff"
        >
          <el-menu-item index="/student/home">
            <span class="student-shell__menu-icon"><i class="el-icon-s-home"></i></span>
            <span class="student-shell__menu-label">系统首页</span>
          </el-menu-item>
          <el-submenu index="1">
            <template slot="title">
              <span class="student-shell__menu-icon"><i class="el-icon-user-solid"></i></span>
              <span class="student-shell__menu-label">个人管理</span>
            </template>
            <el-menu-item index="/student/person">
              <span class="student-shell__menu-icon"><i class="el-icon-user"></i></span>
              <span class="student-shell__menu-label">个人中心</span>
            </el-menu-item>
            <el-menu-item index="/student/realNameAuthentication">
              <span class="student-shell__menu-icon"><i class="el-icon-s-check"></i></span>
              <span class="student-shell__menu-label">实名认证</span>
            </el-menu-item>
            <el-menu-item index="/student/key">
              <span class="student-shell__menu-icon"><i class="el-icon-key"></i></span>
              <span class="student-shell__menu-label">密钥查看</span>
            </el-menu-item>
            <el-menu-item index="/student/password">
              <span class="student-shell__menu-icon"><i class="el-icon-lock"></i></span>
              <span class="student-shell__menu-label">密码修改</span>
            </el-menu-item>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title">
              <span class="student-shell__menu-icon"><i class="el-icon-document"></i></span>
              <span class="student-shell__menu-label">学习笔记</span>
            </template>
            <el-menu-item index="/student/createdNote">
              <span class="student-shell__menu-icon"><i class="el-icon-edit-outline"></i></span>
              <span class="student-shell__menu-label">创建笔记</span>
            </el-menu-item>
            <el-menu-item index="/student/listNote">
              <span class="student-shell__menu-icon"><i class="el-icon-document"></i></span>
              <span class="student-shell__menu-label">笔记信息</span>
            </el-menu-item>
            <el-menu-item index="/student/createdCode">
              <span class="student-shell__menu-icon"><i class="el-icon-s-order"></i></span>
              <span class="student-shell__menu-label">代码实操</span>
            </el-menu-item>
            <el-menu-item index="/student/listCode">
              <span class="student-shell__menu-icon"><i class="el-icon-document"></i></span>
              <span class="student-shell__menu-label">代码信息</span>
            </el-menu-item>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">
              <span class="student-shell__menu-icon"><i class="el-icon-reading"></i></span>
              <span class="student-shell__menu-label">课程管理</span>
            </template>
            <el-menu-item index="/student/studentCourse">
              <span class="student-shell__menu-icon"><i class="el-icon-s-management"></i></span>
              <span class="student-shell__menu-label">课程信息</span>
            </el-menu-item>
          </el-submenu>
          <el-submenu index="4">
            <template slot="title">
              <span class="student-shell__menu-icon"><i class="el-icon-tickets"></i></span>
              <span class="student-shell__menu-label">考试管理</span>
            </template>
            <el-menu-item index="/student/studentExamList">
              <span class="student-shell__menu-icon"><i class="el-icon-document"></i></span>
              <span class="student-shell__menu-label">试卷列表</span>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container class="student-shell__body">
        <el-header class="student-shell__header">
          <div class="student-shell__header-main">
            <h1 class="student-shell__title">学生学习中心</h1>
            <p class="student-shell__subtitle">查看课程、考试、答题和个人资料。</p>
          </div>

          <div class="student-shell__toolbar">
            <div class="student-shell__toolbar-time">
              <span class="student-shell__toolbar-label">系统时间</span>
              <span class="student-shell__time">{{ nowTime }}</span>
            </div>
            <div class="student-shell__action-group">
              <el-button plain @click="home">返回首页</el-button>
              <el-button type="primary" plain @click="back">退出登录</el-button>
            </div>
            <div class="student-shell__user student-shell__user--compact">
              <img class="student-shell__user-avatar" :src="user.photo" alt="">
              <div class="student-shell__user-meta">
                <span class="student-shell__user-name">{{ user.name || "学生" }}</span>
                <span class="student-shell__user-role">当前登录用户</span>
              </div>
            </div>
          </div>
        </el-header>

        <div class="student-shell__content">
          <el-main class="student-shell__main">
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
        const weekArr = ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];
        this.nowTime = `${year}-${mounth}-${day} ${hours}:${minutes}:${seconds} ${weekArr[week]}`;
      }, 1000);
    },
    back() {
      localStorage.removeItem("user");
      this.$router.push("/studentLogin");
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
.student-shell__aside--cyber { --menu-bg-color: #0d1117; --menu-text-color: #8d9aaf; --menu-active-color: #5fffb0; --menu-hover-bg-color: rgba(95, 255, 176, 0.08); position: relative; overflow: hidden; background: linear-gradient(180deg, rgba(13, 17, 23, 0.98), rgba(10, 15, 22, 0.99)), radial-gradient(circle at top left, rgba(95, 255, 176, 0.12), transparent 28%), radial-gradient(circle at bottom right, rgba(0, 229, 255, 0.12), transparent 24%); border-right: 1px solid rgba(95, 255, 176, 0.12); box-shadow: 22px 0 52px rgba(2, 8, 14, 0.46); }
.student-shell__aside--cyber::before, .student-shell__aside--cyber::after { content: ""; position: absolute; inset: 0; pointer-events: none; }
.student-shell__aside--cyber::before { background: linear-gradient(rgba(95, 255, 176, 0.05) 1px, transparent 1px), linear-gradient(90deg, rgba(95, 255, 176, 0.04) 1px, transparent 1px); background-size: 24px 24px; opacity: 0.24; mask-image: linear-gradient(180deg, rgba(0, 0, 0, 0.75), transparent 96%); }
.student-shell__aside--cyber::after { background: linear-gradient(140deg, transparent 0 34%, rgba(95, 255, 176, 0.09) 42%, transparent 50% 100%), radial-gradient(circle at 18% 10%, rgba(95, 255, 176, 0.18), transparent 0 14%); animation: student-cyber-scan 18s linear infinite; }
.student-shell__brand--cyber { position: relative; z-index: 1; margin-bottom: 14px; padding: 10px 12px 24px; }
.student-shell__brand--cyber::after { content: ""; position: absolute; left: 6px; right: 6px; bottom: 4px; height: 1px; background: linear-gradient(90deg, transparent, rgba(95, 255, 176, 0.32), transparent); }
.student-shell__brand--cyber .student-shell__brand-mark { position: relative; border-radius: 14px; background: linear-gradient(135deg, rgba(95, 255, 176, 0.22), rgba(95, 255, 176, 0.08)), rgba(13, 17, 23, 0.92); border: 1px solid rgba(95, 255, 176, 0.22); color: var(--menu-active-color); box-shadow: 0 0 0 1px rgba(95, 255, 176, 0.08), 0 0 18px rgba(95, 255, 176, 0.22), inset 0 0 14px rgba(95, 255, 176, 0.08); animation: student-brand-breathe 3s ease-in-out infinite; }
.student-shell__brand--cyber .student-shell__brand-mark::after { content: ""; position: absolute; inset: 5px; border: 1px solid rgba(95, 255, 176, 0.15); border-radius: 10px; }
.student-shell__brand--cyber .student-shell__brand-title { font-family: "Consolas", "SFMono-Regular", "Liberation Mono", monospace; letter-spacing: 0.12em; text-transform: uppercase; color: #f2fff9; }
.student-shell__brand--cyber .student-shell__brand-subtitle { letter-spacing: 0.14em; text-transform: uppercase; color: rgba(141, 154, 175, 0.88); }
.student-shell__menu--cyber { position: relative; z-index: 1; padding-right: 4px; }
.student-shell__menu--cyber.el-menu { background: var(--menu-bg-color) !important; border-right: none !important; }
.student-shell__menu--cyber ::v-deep .el-menu { background: transparent !important; }
.student-shell__menu--cyber ::v-deep .el-menu-item, .student-shell__menu--cyber ::v-deep .el-submenu__title { position: relative; display: flex; align-items: center; height: 48px; margin-bottom: 8px; padding-left: 16px !important; padding-right: 14px !important; border-radius: 12px; border: 1px solid transparent; background: transparent !important; color: var(--menu-text-color) !important; overflow: hidden; transition: background-color 0.2s ease, color 0.2s ease, border-color 0.2s ease, box-shadow 0.2s ease, transform 0.2s ease; }
.student-shell__menu--cyber ::v-deep .el-menu-item::before, .student-shell__menu--cyber ::v-deep .el-submenu__title::before { content: ""; position: absolute; inset: 0; background: linear-gradient(90deg, rgba(95, 255, 176, 0.06), transparent 60%); opacity: 0; transition: opacity 0.2s ease; }
.student-shell__menu--cyber ::v-deep .el-menu-item:hover, .student-shell__menu--cyber ::v-deep .el-submenu__title:hover { background: var(--menu-hover-bg-color) !important; border-color: rgba(95, 255, 176, 0.14); color: #ddffe9 !important; box-shadow: inset 0 0 0 1px rgba(95, 255, 176, 0.08); transform: translateX(2px); }
.student-shell__menu--cyber ::v-deep .el-menu-item:hover::before, .student-shell__menu--cyber ::v-deep .el-submenu__title:hover::before { opacity: 1; }
.student-shell__menu--cyber ::v-deep .el-menu-item.is-active { color: var(--menu-active-color) !important; background: rgba(95, 255, 176, 0.1) !important; border-color: rgba(95, 255, 176, 0.18); box-shadow: inset 3px 0 0 0 var(--menu-active-color), inset 0 0 0 1px rgba(95, 255, 176, 0.06), 0 0 16px rgba(95, 255, 176, 0.08); text-shadow: 0 0 8px rgba(95, 255, 176, 0.9); }
.student-shell__menu--cyber ::v-deep .el-menu-item.is-active .student-shell__menu-icon, .student-shell__menu--cyber ::v-deep .el-menu-item.is-active .student-shell__menu-label { color: var(--menu-active-color) !important; }
.student-shell__menu--cyber ::v-deep .el-submenu.is-active > .el-submenu__title { color: #e2fff1 !important; border-color: rgba(95, 255, 176, 0.12); }
.student-shell__menu--cyber ::v-deep .el-submenu + .el-submenu { margin-top: 18px; }
.student-shell__menu--cyber ::v-deep .el-submenu + .el-submenu::before { content: ""; display: block; height: 1px; margin: 0 10px 18px; background: linear-gradient(90deg, transparent, rgba(95, 255, 176, 0.25), transparent); }
.student-shell__menu--cyber ::v-deep .el-submenu__title { height: 42px; margin-bottom: 10px; background: rgba(255, 255, 255, 0.01) !important; color: #5d697d !important; font-size: 11px; font-weight: 700; letter-spacing: 0.18em; text-transform: uppercase; }
.student-shell__menu--cyber ::v-deep .el-submenu__title i { color: inherit !important; }
.student-shell__menu--cyber ::v-deep .el-submenu .el-menu-item { width: calc(100% - 16px); height: 42px; margin: 0 8px 6px; padding-left: 18px !important; border-radius: 10px; }
.student-shell__menu--cyber .student-shell__menu-icon { width: 30px; margin-right: 10px; color: inherit; }
.student-shell__menu--cyber .student-shell__menu-label { font-weight: 600; letter-spacing: 0.02em; }
.student-shell__menu--cyber ::v-deep .el-submenu .el-submenu__icon-arrow { margin-top: -5px; color: #5d697d !important; transition: transform 0.2s ease, color 0.2s ease; }
.student-shell__menu--cyber ::v-deep .el-submenu.is-opened > .el-submenu__title .el-submenu__icon-arrow, .student-shell__menu--cyber ::v-deep .el-submenu__title:hover .el-submenu__icon-arrow { color: var(--menu-active-color) !important; }
.student-shell__aside--cyber::-webkit-scrollbar, .student-shell__menu--cyber ::v-deep .el-menu::-webkit-scrollbar { width: 6px; }
.student-shell__aside--cyber::-webkit-scrollbar-track, .student-shell__menu--cyber ::v-deep .el-menu::-webkit-scrollbar-track { background: rgba(255, 255, 255, 0.03); }
.student-shell__aside--cyber::-webkit-scrollbar-thumb, .student-shell__menu--cyber ::v-deep .el-menu::-webkit-scrollbar-thumb { border-radius: 999px; background: linear-gradient(180deg, rgba(95, 255, 176, 0.78), rgba(95, 255, 176, 0.26)); box-shadow: 0 0 10px rgba(95, 255, 176, 0.2); }
@keyframes student-brand-breathe { 0%, 100% { box-shadow: 0 0 0 1px rgba(95, 255, 176, 0.08), 0 0 16px rgba(95, 255, 176, 0.16), inset 0 0 14px rgba(95, 255, 176, 0.06);} 50% { box-shadow: 0 0 0 1px rgba(95, 255, 176, 0.14), 0 0 28px rgba(95, 255, 176, 0.24), inset 0 0 20px rgba(95, 255, 176, 0.1);} }
@keyframes student-cyber-scan { 0% { transform: translate3d(-4%, -2%, 0);} 50% { transform: translate3d(3%, 2%, 0);} 100% { transform: translate3d(-4%, -2%, 0);} }

@media (max-width: 900px) {
  .student-shell__aside {
    display: none;
  }

  .student-shell__header {
    align-items: flex-start;
    padding: 12px 14px 8px;
  }

  .student-shell__title {
    font-size: 20px;
  }

  .student-shell__subtitle {
    font-size: 12px;
  }

  .student-shell__toolbar {
    justify-content: flex-start;
    width: 100%;
    gap: 8px;
  }

  .student-shell__toolbar-time,
  .student-shell__action-group,
  .student-shell__user--compact {
    min-height: 32px;
  }

  .student-shell__content {
    padding: 10px 14px 14px;
  }

  .student-shell__main.el-main {
    margin-top: 10px;
  }
}
</style>
