<template>
  <div class="ca-shell">
    <el-container class="ca-shell__layout">
      <el-aside class="ca-shell__aside ca-shell__aside--cyber">
        <div class="ca-shell__brand ca-shell__brand--cyber">
          <span class="ca-shell__brand-mark">
            <i class="el-icon-s-check"></i>
          </span>
          <div class="ca-shell__brand-text">
            <p class="ca-shell__brand-title">考试系统</p>
            <p class="ca-shell__brand-subtitle">CA 注册中心</p>
          </div>
        </div>

        <el-menu
          class="ca-shell__menu ca-shell__menu--cyber"
          :default-active="$route.path"
          router
          background-color="transparent"
          text-color="#e2e8f0"
          active-text-color="#ffffff"
        >
          <el-menu-item index="/caInstitution/home">
            <span class="ca-shell__menu-icon"><i class="el-icon-s-home"></i></span>
            <span class="ca-shell__menu-label">系统首页</span>
          </el-menu-item>

          <el-submenu index="1">
            <template slot="title">
              <span class="ca-shell__menu-icon"><i class="el-icon-user-solid"></i></span>
              <span class="ca-shell__menu-label">用户管理</span>
            </template>
            <el-menu-item index="/caInstitution/admin">
              <span class="ca-shell__menu-icon"><i class="el-icon-s-custom"></i></span>
              <span class="ca-shell__menu-label">管理员列表</span>
            </el-menu-item>
            <el-menu-item index="/caInstitution/caInstitution">
              <span class="ca-shell__menu-icon"><i class="el-icon-menu"></i></span>
              <span class="ca-shell__menu-label">机构列表</span>
            </el-menu-item>
            <el-menu-item index="/caInstitution/teacher">
              <span class="ca-shell__menu-icon"><i class="el-icon-user"></i></span>
              <span class="ca-shell__menu-label">教师列表</span>
            </el-menu-item>
            <el-menu-item index="/caInstitution/student">
              <span class="ca-shell__menu-icon"><i class="el-icon-user"></i></span>
              <span class="ca-shell__menu-label">学生列表</span>
            </el-menu-item>
          </el-submenu>

          <el-submenu index="2">
            <template slot="title">
              <span class="ca-shell__menu-icon"><i class="el-icon-user"></i></span>
              <span class="ca-shell__menu-label">个人中心</span>
            </template>
            <el-menu-item index="/caInstitution/person">
              <span class="ca-shell__menu-icon"><i class="el-icon-user-solid"></i></span>
              <span class="ca-shell__menu-label">个人信息</span>
            </el-menu-item>
            <el-menu-item index="/caInstitution/key">
              <span class="ca-shell__menu-icon"><i class="el-icon-key"></i></span>
              <span class="ca-shell__menu-label">密钥查看</span>
            </el-menu-item>
            <el-menu-item index="/caInstitution/password">
              <span class="ca-shell__menu-icon"><i class="el-icon-lock"></i></span>
              <span class="ca-shell__menu-label">密码修改</span>
            </el-menu-item>
          </el-submenu>

          <el-submenu index="3">
            <template slot="title">
              <span class="ca-shell__menu-icon"><i class="el-icon-s-management"></i></span>
              <span class="ca-shell__menu-label">学校管理</span>
            </template>
            <el-menu-item index="/caInstitution/caInstitutionUniversity">
              <span class="ca-shell__menu-icon"><i class="el-icon-s-order"></i></span>
              <span class="ca-shell__menu-label">学校列表</span>
            </el-menu-item>
            <el-menu-item index="/caInstitution/caInstitutionColleges">
              <span class="ca-shell__menu-icon"><i class="el-icon-collection"></i></span>
              <span class="ca-shell__menu-label">学院列表</span>
            </el-menu-item>
            <el-menu-item index="/caInstitution/caInstitutionMajor">
              <span class="ca-shell__menu-icon"><i class="el-icon-document"></i></span>
              <span class="ca-shell__menu-label">专业列表</span>
            </el-menu-item>
          </el-submenu>

          <el-submenu index="4">
            <template slot="title">
              <span class="ca-shell__menu-icon"><i class="el-icon-s-check"></i></span>
              <span class="ca-shell__menu-label">实名认证</span>
            </template>
            <el-menu-item index="/caInstitution/caInstitutionRealFace">
              <span class="ca-shell__menu-icon"><i class="el-icon-picture-outline"></i></span>
              <span class="ca-shell__menu-label">学生认证</span>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container class="ca-shell__body">
        <el-header class="ca-shell__header">
          <div class="ca-shell__header-main">
            <h1 class="ca-shell__title">CA 注册中心</h1>
            <p class="ca-shell__subtitle">统一维护机构、学校与实名审核数据。</p>
          </div>

          <div class="ca-shell__toolbar">
            <div class="ca-shell__toolbar-time">
              <span class="ca-shell__toolbar-label">系统时间</span>
              <span class="ca-shell__time">{{ nowTime }}</span>
            </div>

            <el-dropdown class="ca-shell__user-dropdown" @command="handleCommand">
              <div class="ca-shell__user ca-shell__user--compact">
                <span class="ca-shell__user-avatar">
                  <i class="el-icon-user"></i>
                </span>
                <div class="ca-shell__user-meta">
                  <span class="ca-shell__user-name">{{ user.name || "CA 用户" }}</span>
                  <span class="ca-shell__user-role">当前登录用户</span>
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

        <div class="ca-shell__content">
          <div class="ca-shell__breadcrumb-card">
            <div class="ca-shell__breadcrumb-main">
              <span class="ca-shell__breadcrumb-label">当前位置</span>
              <el-breadcrumb separator="/">
                <el-breadcrumb-item
                  v-if="$route.meta.name !== '首页'"
                  :to="{ path: '/caInstitution/home' }"
                >
                  首页
                </el-breadcrumb-item>
                <el-breadcrumb-item>{{ currentPageName }}</el-breadcrumb-item>
              </el-breadcrumb>
            </div>
            <span class="ca-shell__breadcrumb-note">当前模块：{{ currentPageName }}</span>
          </div>

          <el-main class="ca-shell__main">
            <router-view />
          </el-main>
        </div>
      </el-container>
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
  computed: {
    currentPageName() {
      return this.$route.meta.name || "CA 注册中心";
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

<style lang="scss" src="../styles/theme.scss"></style>
<style scoped lang="scss">
.ca-shell__aside--cyber {
  --menu-bg-color: #0d1117;
  --menu-text-color: #8996a9;
  --menu-active-color: #52d3ff;
  --menu-hover-bg-color: rgba(82, 211, 255, 0.08);
  position: relative;
  overflow: hidden;
  background:
    linear-gradient(180deg, rgba(13, 17, 23, 0.98), rgba(10, 15, 22, 0.99)),
    radial-gradient(circle at top left, rgba(82, 211, 255, 0.11), transparent 28%),
    radial-gradient(circle at bottom right, rgba(34, 197, 94, 0.1), transparent 24%);
  border-right: 1px solid rgba(82, 211, 255, 0.14);
  box-shadow: 22px 0 52px rgba(2, 8, 14, 0.46);
}

.ca-shell__aside--cyber::before,
.ca-shell__aside--cyber::after {
  content: "";
  position: absolute;
  inset: 0;
  pointer-events: none;
}

.ca-shell__aside--cyber::before {
  background:
    linear-gradient(rgba(82, 211, 255, 0.05) 1px, transparent 1px),
    linear-gradient(90deg, rgba(82, 211, 255, 0.04) 1px, transparent 1px);
  background-size: 24px 24px;
  opacity: 0.24;
  mask-image: linear-gradient(180deg, rgba(0, 0, 0, 0.75), transparent 96%);
}

.ca-shell__aside--cyber::after {
  background:
    linear-gradient(140deg, transparent 0 34%, rgba(82, 211, 255, 0.08) 42%, transparent 50% 100%),
    radial-gradient(circle at 18% 10%, rgba(82, 211, 255, 0.16), transparent 0 14%);
  animation: ca-cyber-scan 18s linear infinite;
}

.ca-shell__brand--cyber {
  position: relative;
  z-index: 1;
  margin-bottom: 14px;
  padding: 10px 12px 24px;
}

.ca-shell__brand--cyber::after {
  content: "";
  position: absolute;
  left: 6px;
  right: 6px;
  bottom: 4px;
  height: 1px;
  background: linear-gradient(90deg, transparent, rgba(82, 211, 255, 0.32), transparent);
}

.ca-shell__brand--cyber .ca-shell__brand-mark {
  position: relative;
  border-radius: 14px;
  background:
    linear-gradient(135deg, rgba(82, 211, 255, 0.22), rgba(82, 211, 255, 0.08)),
    rgba(13, 17, 23, 0.92);
  border: 1px solid rgba(82, 211, 255, 0.22);
  color: var(--menu-active-color);
  box-shadow:
    0 0 0 1px rgba(82, 211, 255, 0.08),
    0 0 18px rgba(82, 211, 255, 0.22),
    inset 0 0 14px rgba(82, 211, 255, 0.08);
  animation: ca-brand-breathe 3s ease-in-out infinite;
}

.ca-shell__brand--cyber .ca-shell__brand-mark::after {
  content: "";
  position: absolute;
  inset: 5px;
  border: 1px solid rgba(82, 211, 255, 0.15);
  border-radius: 10px;
}

.ca-shell__brand--cyber .ca-shell__brand-title {
  font-family: "Consolas", "SFMono-Regular", "Liberation Mono", monospace;
  letter-spacing: 0.12em;
  text-transform: uppercase;
  color: #f2fbff;
}

.ca-shell__brand--cyber .ca-shell__brand-subtitle {
  letter-spacing: 0.14em;
  text-transform: uppercase;
  color: rgba(137, 150, 169, 0.88);
}

.ca-shell__menu--cyber {
  position: relative;
  z-index: 1;
  padding-right: 4px;
}

.ca-shell__menu--cyber.el-menu {
  background: var(--menu-bg-color) !important;
  border-right: none !important;
}

.ca-shell__menu--cyber ::v-deep .el-menu {
  background: transparent !important;
}

.ca-shell__menu--cyber ::v-deep .el-menu-item,
.ca-shell__menu--cyber ::v-deep .el-submenu__title {
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

.ca-shell__menu--cyber ::v-deep .el-menu-item::before,
.ca-shell__menu--cyber ::v-deep .el-submenu__title::before {
  content: "";
  position: absolute;
  inset: 0;
  background: linear-gradient(90deg, rgba(82, 211, 255, 0.06), transparent 60%);
  opacity: 0;
  transition: opacity 0.2s ease;
}

.ca-shell__menu--cyber ::v-deep .el-menu-item:hover,
.ca-shell__menu--cyber ::v-deep .el-submenu__title:hover {
  background: var(--menu-hover-bg-color) !important;
  border-color: rgba(82, 211, 255, 0.14);
  color: #dcf8ff !important;
  box-shadow: inset 0 0 0 1px rgba(82, 211, 255, 0.08);
  transform: translateX(2px);
}

.ca-shell__menu--cyber ::v-deep .el-menu-item:hover::before,
.ca-shell__menu--cyber ::v-deep .el-submenu__title:hover::before {
  opacity: 1;
}

.ca-shell__menu--cyber ::v-deep .el-menu-item.is-active {
  color: var(--menu-active-color) !important;
  background: rgba(82, 211, 255, 0.1) !important;
  border-color: rgba(82, 211, 255, 0.18);
  box-shadow:
    inset 3px 0 0 0 var(--menu-active-color),
    inset 0 0 0 1px rgba(82, 211, 255, 0.06),
    0 0 16px rgba(82, 211, 255, 0.08);
  text-shadow: 0 0 8px rgba(82, 211, 255, 0.9);
}

.ca-shell__menu--cyber ::v-deep .el-menu-item.is-active .ca-shell__menu-icon,
.ca-shell__menu--cyber ::v-deep .el-menu-item.is-active .ca-shell__menu-label {
  color: var(--menu-active-color) !important;
}

.ca-shell__menu--cyber ::v-deep .el-submenu.is-active > .el-submenu__title {
  color: #def7ff !important;
  border-color: rgba(82, 211, 255, 0.12);
}

.ca-shell__menu--cyber ::v-deep .el-submenu + .el-submenu {
  margin-top: 18px;
}

.ca-shell__menu--cyber ::v-deep .el-submenu + .el-submenu::before {
  content: "";
  display: block;
  height: 1px;
  margin: 0 10px 18px;
  background: linear-gradient(90deg, transparent, rgba(82, 211, 255, 0.25), transparent);
}

.ca-shell__menu--cyber ::v-deep .el-submenu__title {
  height: 42px;
  margin-bottom: 10px;
  background: rgba(255, 255, 255, 0.01) !important;
  color: #5a6678 !important;
  font-size: 11px;
  font-weight: 700;
  letter-spacing: 0.18em;
  text-transform: uppercase;
}

.ca-shell__menu--cyber ::v-deep .el-submenu__title i {
  color: inherit !important;
}

.ca-shell__menu--cyber ::v-deep .el-submenu .el-menu-item {
  margin-bottom: 6px;
  padding-left: 24px !important;
}

.ca-shell__menu-icon {
  width: 30px;
  margin-right: 10px;
  color: inherit;
}

.ca-shell__menu-label {
  font-weight: 600;
  letter-spacing: 0.02em;
}

.ca-shell__menu--cyber ::v-deep .el-submenu .el-submenu__icon-arrow {
  margin-top: -5px;
  color: #5a6678 !important;
  transition: transform 0.2s ease, color 0.2s ease;
}

.ca-shell__menu--cyber ::v-deep .el-submenu.is-opened > .el-submenu__title .el-submenu__icon-arrow,
.ca-shell__menu--cyber ::v-deep .el-submenu__title:hover .el-submenu__icon-arrow {
  color: var(--menu-active-color) !important;
}

.ca-shell__aside--cyber::-webkit-scrollbar,
.ca-shell__menu--cyber ::v-deep .el-menu::-webkit-scrollbar {
  width: 6px;
}

.ca-shell__aside--cyber::-webkit-scrollbar-track,
.ca-shell__menu--cyber ::v-deep .el-menu::-webkit-scrollbar-track {
  background: rgba(255, 255, 255, 0.03);
}

.ca-shell__aside--cyber::-webkit-scrollbar-thumb,
.ca-shell__menu--cyber ::v-deep .el-menu::-webkit-scrollbar-thumb {
  border-radius: 999px;
  background: linear-gradient(180deg, rgba(82, 211, 255, 0.78), rgba(82, 211, 255, 0.26));
  box-shadow: 0 0 10px rgba(82, 211, 255, 0.2);
}

@keyframes ca-brand-breathe {
  0%,
  100% {
    box-shadow:
      0 0 0 1px rgba(82, 211, 255, 0.08),
      0 0 16px rgba(82, 211, 255, 0.16),
      inset 0 0 14px rgba(82, 211, 255, 0.06);
  }

  50% {
    box-shadow:
      0 0 0 1px rgba(82, 211, 255, 0.14),
      0 0 28px rgba(82, 211, 255, 0.24),
      inset 0 0 20px rgba(82, 211, 255, 0.1);
  }
}

@keyframes ca-cyber-scan {
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
