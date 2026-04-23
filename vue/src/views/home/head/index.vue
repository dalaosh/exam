<template>
  <header class="home-topbar">
    <div class="home-topbar__brand" @click="$router.push('/')">
      <div class="home-topbar__title">SM-ExamGuard：可信在线考试仿真实验平台</div>
      <div class="home-topbar__subtitle">融合国密算法与多智能体 AI 协同</div>
    </div>

    <nav class="home-topbar__nav" aria-label="大屏入口">
      <button class="home-topbar__nav-btn" type="button" @click="$router.push('/bigEcharts')">
        大屏可视化
      </button>
      <button class="home-topbar__nav-btn" type="button" @click="$router.push('/bigEchartsWebSocket')">
        大屏
      </button>
    </nav>

    <div class="home-topbar__status">
      <span class="home-topbar__user">{{ lgu }}</span>
      <span class="home-topbar__divider"></span>
      <span class="home-topbar__time">{{ nowTime }}</span>
    </div>
  </header>
</template>

<script>
export default {
  name: "homeHeader",
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      timer: "",
      nowTime: "",
      login: "",
      lgu: ""
    }
  },
  created() {
    this.item()
    this.updateTime()
  },
  mounted() {
    this.getTime()
  },
  methods: {
    getTime() {
      this.timer = setInterval(() => {
        this.updateTime()
      }, 1000)
    },
    updateTime() {
      const timeDate = new Date()
      const year = timeDate.getFullYear()
      const month = timeDate.getMonth() + 1
      const day = timeDate.getDate()
      const hours = String(timeDate.getHours()).padStart(2, "0")
      const minutes = String(timeDate.getMinutes()).padStart(2, "0")
      const seconds = String(timeDate.getSeconds()).padStart(2, "0")
      const weekArr = ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"]

      this.nowTime = `${year}-${month}-${day} ${hours}:${minutes}:${seconds} ${weekArr[timeDate.getDay()]}`
    },
    item() {
      const isEmpty = Object.keys(this.user).length === 0
      if (isEmpty) {
        this.login = true
        this.lgu = "请登录"
      } else {
        this.lgu = `${this.user.role}:${this.user.name}`
        this.login = false
      }
    }
  },
  beforeDestroy() {
    if (this.timer) {
      clearInterval(this.timer)
    }
  }
}
</script>

<style scoped>
.home-topbar {
  height: 86px;
  box-sizing: border-box;
  position: relative;
  padding: 0 54px 0 52px;
  display: grid;
  grid-template-columns: minmax(680px, 1fr) auto minmax(520px, 1fr);
  align-items: center;
  column-gap: 36px;
  color: #fff;
  background: linear-gradient(100deg, rgba(32, 145, 216, 0.94) 0%, rgba(64, 112, 211, 0.94) 54%, rgba(139, 78, 202, 0.94) 100%);
  box-shadow: 0 8px 22px rgba(26, 57, 130, 0.16);
}

.home-topbar__brand {
  min-width: 0;
  cursor: pointer;
}

.home-topbar__title {
  font-size: 29px;
  line-height: 1.1;
  font-weight: 900;
  letter-spacing: 0;
  color: rgba(255, 255, 255, 0.95);
  text-shadow: 0 2px 8px rgba(18, 48, 110, 0.24);
  white-space: nowrap;
}

.home-topbar__subtitle {
  margin-top: 13px;
  font-size: 14px;
  line-height: 1;
  font-weight: 700;
  color: rgba(226, 246, 255, 0.64);
  white-space: nowrap;
}

.home-topbar__nav {
  justify-self: center;
  height: 44px;
  min-width: 188px;
  padding: 0 16px;
  display: inline-flex;
  align-items: center;
  gap: 10px;
  border-radius: 8px;
  background: rgba(60, 121, 219, 0.28);
  box-shadow: inset 0 0 0 1px rgba(255, 255, 255, 0.11), 0 8px 18px rgba(25, 65, 145, 0.08);
  backdrop-filter: blur(8px);
}

.home-topbar__nav-btn {
  height: 34px;
  min-width: 56px;
  padding: 0 16px;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  border: 0;
  border-radius: 6px;
  color: rgba(255, 255, 255, 0.84);
  background: transparent;
  font-size: 18px;
  font-weight: 800;
  line-height: 1;
  letter-spacing: 0;
  white-space: nowrap;
  cursor: pointer;
  transition: background-color 0.18s ease, color 0.18s ease, transform 0.18s ease;
}

.home-topbar__nav-btn:first-child {
  min-width: 108px;
}

.home-topbar__nav-btn:hover {
  color: #fff;
  background: rgba(255, 255, 255, 0.13);
  transform: translateY(-1px);
}

.home-topbar__status {
  min-width: 0;
  display: flex;
  align-items: center;
  justify-content: flex-end;
  gap: 22px;
  color: rgba(255, 255, 255, 0.76);
  font-size: 22px;
  font-weight: 800;
  white-space: nowrap;
}

.home-topbar__user,
.home-topbar__time {
  line-height: 1;
}

.home-topbar__user {
  font-size: 18px;
}

.home-topbar__divider {
  width: 1px;
  height: 28px;
  background: rgba(255, 255, 255, 0.24);
}

@media (max-width: 1280px) {
  .home-topbar {
    padding: 0 28px;
    grid-template-columns: minmax(420px, 1fr) auto minmax(320px, 1fr);
    column-gap: 22px;
  }

  .home-topbar__title {
    font-size: 23px;
  }

  .home-topbar__status {
    font-size: 18px;
    gap: 14px;
  }

  .home-topbar__user {
    font-size: 16px;
  }
}

@media (max-width: 960px) {
  .home-topbar {
    height: auto;
    min-height: 86px;
    padding: 14px 18px;
    grid-template-columns: 1fr;
    gap: 12px;
  }

  .home-topbar__brand {
    width: 100%;
    min-width: 0;
  }

  .home-topbar__nav {
    justify-self: start;
  }

  .home-topbar__title {
    font-size: 21px;
    white-space: normal;
  }

  .home-topbar__subtitle {
    margin-top: 8px;
  }

  .home-topbar__status {
    width: 100%;
    font-size: 15px;
  }

  .home-topbar__time {
    min-width: 0;
  }
}
</style>
