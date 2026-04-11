<template>
  <div class="admin-shell">
    <el-container class="admin-shell__layout">
      <el-aside class="admin-shell__aside admin-shell__aside--cyber">
        <div class="admin-shell__brand admin-shell__brand--cyber">
          <span class="admin-shell__brand-mark"><i class="el-icon-s-platform"></i></span>
          <div class="admin-shell__brand-text">
            <p class="admin-shell__brand-title">考试系统</p>
            <p class="admin-shell__brand-subtitle">管理员后台</p>
          </div>
        </div>
        <el-menu
          class="admin-shell__menu admin-shell__menu--cyber"
          :default-active="$route.path"
          router
          background-color="transparent"
          text-color="#e2e8f0"
          active-text-color="#ffffff"
        >
          <el-menu-item index="/admin/home">
            <span class="admin-shell__menu-icon"><i class="el-icon-s-home"></i></span>
            <span class="admin-shell__menu-label">系统首页</span>
          </el-menu-item>
          <el-submenu index="1">
            <template slot="title">
              <span class="admin-shell__menu-icon"><i class="el-icon-s-operation"></i></span>
              <span class="admin-shell__menu-label">系统管理</span>
            </template>
            <el-menu-item index="/admin/log">
              <span class="admin-shell__menu-icon"><i class="el-icon-document"></i></span>
              <span class="admin-shell__menu-label">日志列表</span>
            </el-menu-item>
            <el-menu-item index="/admin/notice">
              <span class="admin-shell__menu-icon"><i class="el-icon-message-solid"></i></span>
              <span class="admin-shell__menu-label">公告列表</span>
            </el-menu-item>
            <el-menu-item index="/admin/news">
              <span class="admin-shell__menu-icon"><i class="el-icon-reading"></i></span>
              <span class="admin-shell__menu-label">新闻列表</span>
            </el-menu-item>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title">
              <span class="admin-shell__menu-icon"><i class="el-icon-user-solid"></i></span>
              <span class="admin-shell__menu-label">个人管理</span>
            </template>
            <el-menu-item index="/admin/person">
              <span class="admin-shell__menu-icon"><i class="el-icon-user"></i></span>
              <span class="admin-shell__menu-label">个人中心</span>
            </el-menu-item>
            <el-menu-item index="/admin/key">
              <span class="admin-shell__menu-icon"><i class="el-icon-key"></i></span>
              <span class="admin-shell__menu-label">密钥查看</span>
            </el-menu-item>
            <el-menu-item index="/admin/password">
              <span class="admin-shell__menu-icon"><i class="el-icon-lock"></i></span>
              <span class="admin-shell__menu-label">密码修改</span>
            </el-menu-item>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">
              <span class="admin-shell__menu-icon"><i class="el-icon-s-custom"></i></span>
              <span class="admin-shell__menu-label">用户管理</span>
            </template>
            <el-menu-item index="/admin/teacher">
              <span class="admin-shell__menu-icon"><i class="el-icon-s-check"></i></span>
              <span class="admin-shell__menu-label">教师列表</span>
            </el-menu-item>
            <el-menu-item index="/admin/student">
              <span class="admin-shell__menu-icon"><i class="el-icon-s-custom"></i></span>
              <span class="admin-shell__menu-label">学生列表</span>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container class="admin-shell__body">
        <el-header class="admin-shell__header">
          <div class="admin-shell__header-main">
            <h1 class="admin-shell__title">管理员后台</h1>
            <p class="admin-shell__subtitle">查看平台用户、公告新闻和系统运行数据。</p>
          </div>
          <div class="admin-shell__toolbar">
            <div class="admin-shell__toolbar-time">
              <span class="admin-shell__toolbar-label">系统时间</span>
              <span class="admin-shell__time">{{ nowTime }}</span>
            </div>
            <el-dropdown @command="handleCommand">
              <div class="admin-shell__user admin-shell__user--compact">
                <span class="admin-shell__user-avatar"><i class="el-icon-user"></i></span>
                <div class="admin-shell__user-meta">
                  <span class="admin-shell__user-name">{{ user.name || "管理员" }}</span>
                  <span class="admin-shell__user-role">当前登录用户</span>
                </div>
                <i class="el-icon-arrow-down el-icon--right"></i>
              </div>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item command="a" icon="el-icon-house">返回首页</el-dropdown-item>
                <el-dropdown-item command="b" icon="el-icon-search">打开百度</el-dropdown-item>
                <el-dropdown-item command="c" icon="el-icon-switch-button">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </el-header>

        <div class="admin-shell__content">
          <div class="admin-shell__breadcrumb-card">
            <div class="admin-shell__breadcrumb-main">
              <span class="admin-shell__breadcrumb-label">当前位置</span>
              <el-breadcrumb separator="/">
                <el-breadcrumb-item v-if="$route.meta.name !== '首页'" :to="{ path: '/admin/home' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item>{{ currentPageName }}</el-breadcrumb-item>
              </el-breadcrumb>
            </div>
            <span class="admin-shell__breadcrumb-note">当前模块：{{ currentPageName }}</span>
          </div>

          <el-main class="admin-shell__main">
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
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      nowTime: ""
    };
  },
  computed: {
    currentPageName() {
      return this.$route.meta.name || "工作台";
    }
  },
  mounted() {
    this.getTime();
  },
  methods: {
    handleCommand(command) {
      if (command === "a") {
        this.$router.push("/");
      }
      if (command === "b") {
        window.open("https://www.baidu.com", "_blank");
      }
      if (command === "c") {
        localStorage.removeItem("user");
        this.$router.push("/adminLogin");
      }
    },
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
.admin-shell__aside--cyber { --menu-bg-color: #0d1117; --menu-text-color: #8892a4; --menu-active-color: #00e5ff; --menu-hover-bg-color: rgba(0, 229, 255, 0.08); position: relative; overflow: hidden; background: linear-gradient(180deg, rgba(13, 17, 23, 0.98), rgba(9, 13, 19, 0.99)), radial-gradient(circle at top left, rgba(0, 229, 255, 0.12), transparent 26%), radial-gradient(circle at bottom right, rgba(125, 92, 255, 0.12), transparent 24%); border-right: 1px solid rgba(0, 229, 255, 0.12); box-shadow: 20px 0 48px rgba(3, 8, 15, 0.5); }
.admin-shell__aside--cyber::before, .admin-shell__aside--cyber::after { content: ""; position: absolute; inset: 0; pointer-events: none; }
.admin-shell__aside--cyber::before { background: linear-gradient(rgba(0, 229, 255, 0.06) 1px, transparent 1px), linear-gradient(90deg, rgba(0, 229, 255, 0.05) 1px, transparent 1px); background-size: 28px 28px; opacity: 0.2; mask-image: linear-gradient(180deg, rgba(0, 0, 0, 0.72), transparent 96%); }
.admin-shell__aside--cyber::after { background: radial-gradient(circle at 22% 10%, rgba(0, 229, 255, 0.16), transparent 0 16%), linear-gradient(120deg, transparent 0 36%, rgba(0, 229, 255, 0.08) 42%, transparent 48% 100%); animation: admin-cyber-scan 16s linear infinite; opacity: 0.9; }
.admin-shell__brand--cyber { position: relative; z-index: 1; margin-bottom: 12px; padding: 10px 12px 24px; }
.admin-shell__brand--cyber::after { content: ""; position: absolute; left: 6px; right: 6px; bottom: 4px; height: 1px; background: linear-gradient(90deg, transparent, rgba(0, 229, 255, 0.28), transparent); }
.admin-shell__brand--cyber .admin-shell__brand-mark { position: relative; border-radius: 14px; background: linear-gradient(135deg, rgba(0, 229, 255, 0.24), rgba(0, 229, 255, 0.08)), rgba(13, 17, 23, 0.9); border: 1px solid rgba(0, 229, 255, 0.22); color: var(--menu-active-color); box-shadow: 0 0 0 1px rgba(0, 229, 255, 0.08), 0 0 18px rgba(0, 229, 255, 0.28), inset 0 0 16px rgba(0, 229, 255, 0.08); animation: admin-brand-breathe 2.8s ease-in-out infinite; }
.admin-shell__brand--cyber .admin-shell__brand-mark::after { content: ""; position: absolute; inset: 5px; border: 1px solid rgba(0, 229, 255, 0.16); border-radius: 10px; }
.admin-shell__brand--cyber .admin-shell__brand-title { font-family: "Consolas", "SFMono-Regular", "Liberation Mono", monospace; letter-spacing: 0.12em; text-transform: uppercase; color: #e6fbff; }
.admin-shell__brand--cyber .admin-shell__brand-subtitle { letter-spacing: 0.14em; text-transform: uppercase; color: rgba(136, 146, 164, 0.88); }
.admin-shell__menu--cyber { position: relative; z-index: 1; padding-right: 4px; }
.admin-shell__menu--cyber.el-menu { background: var(--menu-bg-color) !important; border-right: none !important; }
.admin-shell__menu--cyber ::v-deep .el-menu { background: transparent !important; }
.admin-shell__menu--cyber ::v-deep .el-menu-item, .admin-shell__menu--cyber ::v-deep .el-submenu__title { position: relative; display: flex; align-items: center; height: 48px; margin-bottom: 8px; padding-left: 16px !important; padding-right: 14px !important; border-radius: 12px; border: 1px solid transparent; background: transparent !important; color: var(--menu-text-color) !important; overflow: hidden; transition: background-color 0.2s ease, color 0.2s ease, border-color 0.2s ease, box-shadow 0.2s ease, transform 0.2s ease; }
.admin-shell__menu--cyber ::v-deep .el-menu-item::before, .admin-shell__menu--cyber ::v-deep .el-submenu__title::before { content: ""; position: absolute; inset: 0; background: linear-gradient(90deg, rgba(0, 229, 255, 0.06), transparent 58%); opacity: 0; transition: opacity 0.2s ease; }
.admin-shell__menu--cyber ::v-deep .el-menu-item:hover, .admin-shell__menu--cyber ::v-deep .el-submenu__title:hover { background: var(--menu-hover-bg-color) !important; border-color: rgba(0, 229, 255, 0.16); color: #c8f8ff !important; box-shadow: inset 0 0 0 1px rgba(0, 229, 255, 0.08); transform: translateX(2px); }
.admin-shell__menu--cyber ::v-deep .el-menu-item:hover::before, .admin-shell__menu--cyber ::v-deep .el-submenu__title:hover::before { opacity: 1; }
.admin-shell__menu--cyber ::v-deep .el-menu-item.is-active { color: var(--menu-active-color) !important; background: rgba(0, 229, 255, 0.1) !important; border-color: rgba(0, 229, 255, 0.2); box-shadow: inset 3px 0 0 0 var(--menu-active-color), inset 0 0 0 1px rgba(0, 229, 255, 0.06), 0 0 16px rgba(0, 229, 255, 0.08); text-shadow: 0 0 8px #00e5ff; }
.admin-shell__menu--cyber ::v-deep .el-menu-item.is-active .admin-shell__menu-icon, .admin-shell__menu--cyber ::v-deep .el-menu-item.is-active .admin-shell__menu-label { color: var(--menu-active-color) !important; }
.admin-shell__menu--cyber ::v-deep .el-submenu.is-active > .el-submenu__title { color: #cceef5 !important; border-color: rgba(0, 229, 255, 0.12); }
.admin-shell__menu--cyber ::v-deep .el-submenu + .el-submenu { margin-top: 18px; }
.admin-shell__menu--cyber ::v-deep .el-submenu + .el-submenu::before { content: ""; display: block; height: 1px; margin: 0 10px 18px; background: linear-gradient(90deg, transparent, #00e5ff40, transparent); }
.admin-shell__menu--cyber ::v-deep .el-submenu__title { height: 42px; margin-bottom: 10px; background: rgba(255, 255, 255, 0.01) !important; color: #556070 !important; font-size: 11px; font-weight: 700; letter-spacing: 0.18em; text-transform: uppercase; }
.admin-shell__menu--cyber ::v-deep .el-submenu__title i { color: inherit !important; }
.admin-shell__menu--cyber ::v-deep .el-submenu .el-menu-item { margin-bottom: 6px; padding-left: 24px !important; }
.admin-shell__menu--cyber .admin-shell__menu-icon { position: relative; width: 30px; margin-right: 10px; color: inherit; }
.admin-shell__menu--cyber .admin-shell__menu-icon i { position: relative; z-index: 1; }
.admin-shell__menu--cyber .admin-shell__menu-label { position: relative; z-index: 1; font-weight: 600; letter-spacing: 0.02em; }
.admin-shell__menu--cyber ::v-deep .el-submenu .el-submenu__icon-arrow { margin-top: -5px; color: #556070 !important; transition: transform 0.2s ease, color 0.2s ease; }
.admin-shell__menu--cyber ::v-deep .el-submenu.is-opened > .el-submenu__title .el-submenu__icon-arrow, .admin-shell__menu--cyber ::v-deep .el-submenu__title:hover .el-submenu__icon-arrow { color: var(--menu-active-color) !important; }
.admin-shell__aside--cyber::-webkit-scrollbar, .admin-shell__menu--cyber ::v-deep .el-menu::-webkit-scrollbar { width: 6px; }
.admin-shell__aside--cyber::-webkit-scrollbar-track, .admin-shell__menu--cyber ::v-deep .el-menu::-webkit-scrollbar-track { background: rgba(255, 255, 255, 0.03); }
.admin-shell__aside--cyber::-webkit-scrollbar-thumb, .admin-shell__menu--cyber ::v-deep .el-menu::-webkit-scrollbar-thumb { border-radius: 999px; background: linear-gradient(180deg, rgba(0, 229, 255, 0.78), rgba(0, 229, 255, 0.3)); box-shadow: 0 0 10px rgba(0, 229, 255, 0.24); }
@keyframes admin-brand-breathe { 0%, 100% { box-shadow: 0 0 0 1px rgba(0, 229, 255, 0.08), 0 0 16px rgba(0, 229, 255, 0.18), inset 0 0 14px rgba(0, 229, 255, 0.06);} 50% { box-shadow: 0 0 0 1px rgba(0, 229, 255, 0.14), 0 0 28px rgba(0, 229, 255, 0.3), inset 0 0 20px rgba(0, 229, 255, 0.14);} }
@keyframes admin-cyber-scan { 0% { transform: translate3d(-4%, -2%, 0);} 50% { transform: translate3d(3%, 2%, 0);} 100% { transform: translate3d(-4%, -2%, 0);} }
</style>
