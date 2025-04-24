<template>
  <div class="slogan-container">
    <!-- 视频背景 -->
    <video autoplay muted loop class="background-video">
      <source src="@/assets/home/tob_bg/school.mp4" type="video/mp4">
      您的浏览器不支持视频播放。
    </video>
    <!-- 考试系统简介区域 -->
    <div class="system-intro">
      <h2>考试系统简介</h2>
      <p>本考试系统基于国密算法与人脸认证技术构建，具备高度的安全性与可靠性。</p>
      <p>国密算法确保了考试数据在传输与存储过程中的保密性、完整性和不可抵赖性，有效防止数据泄露与篡改。</p>
      <p>人脸认证技术则为考生身份验证提供了便捷且精准的方式，极大提升了考试的公平性与效率。</p>
      <p>系统功能丰富，涵盖考试安排、试卷管理、在线考试、成绩查询等全流程服务，为教育机构和考生打造一站式考试解决方案。</p>
    </div>
    <!-- 考试系统标语 -->
    <div class="slogan">
      <h1>精准测评，智慧护航，开启考试新征程！</h1>
    </div>
    <!-- 导航按钮 -->
    <div id="navigation-buttons">
      <el-tooltip content="登录" placement="left">
        <template>
          <div class="nav-icon" @click="scrollTo('logins')">
            <img src="@/assets/home/login_button/admin.jpg" alt="登录" />
          </div>
        </template>
      </el-tooltip>
      <el-tooltip content="新闻公告" placement="left">
        <template>
          <div class="nav-icon" @click="scrollTo('noticeAndNews')">
            <img src="@/assets/home/login_button/caInstitution.jpg" alt="系统功能" />
          </div>
        </template>
      </el-tooltip>
      <el-tooltip content="系统功能" placement="left">
        <template>
          <div class="nav-icon" @click="scrollTo('systemFunction')">
            <img src="@/assets/home/login_button/caInstitution.jpg" alt="系统功能" />
          </div>
        </template>
      </el-tooltip>
      <el-tooltip content="考试须知" placement="left">
        <template>
          <div class="nav-icon" @click="scrollTo('notesForExam')">
            <img src="@/assets/home/login_button/teacher.jpg" alt="考试须知" />
          </div>
        </template>
      </el-tooltip>
      <el-tooltip content="底部信息" placement="left">
        <template>
          <div class="nav-icon" @click="scrollTo('below')">
            <img src="@/assets/home/login_button/student.jpg" alt="底部信息" />
          </div>
        </template>
      </el-tooltip>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SloganWithVideo',
  data() {
    return {};
  },
  methods: {
    scrollTo(sectionId) {
      // 触发自定义事件，传递要滚动到的区域 ID
      this.$emit('scroll-to-section', sectionId);
    }
  }
};
</script>

<style scoped>
.slogan-container {
  position: relative;
  width: 100%;
  height: 92vh;
  overflow: hidden;
}

.background-video {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
  z-index: -1;
}

/* 考试系统简介区域样式 */
.system-intro {
  position: absolute;
  top: 60vh;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 80%;
  color: white;
  text-align: center;
  line-height: 1.6;
  z-index: 1;
  background-color: rgba(0, 0, 0, 0.6);
  padding: 20px;
  border-radius: 10px;
}

.system-intro h2 {
  font-size: 30px;
  margin-bottom: 15px;
}

.slogan {
  position: absolute;
  top: 20vh;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
  width: 100%;
  color: white;
}

.slogan h1 {
  font-size: 48px;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.8);
  animation: fadeInUp 1s ease-out;
}

#navigation-buttons {
  position: absolute;
  top: 50%;
  right: 20px;
  transform: translateY(-50%);
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.nav-icon {
  width: 60px;
  height: 60px;
  background-color: rgba(0, 0, 0, 0.5);
  border-radius: 4px;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  transition: background-color 0.3s;
  overflow: hidden;
}

.nav-icon img {
  max-width: 100%;
  max-height: 100%;
  object-fit: contain;
}

.nav-icon:hover {
  background-color: rgba(0, 0, 0, 0.8);
}

@keyframes fadeInUp {
  from {
    opacity: 0;
    transform: translateY(20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}
</style>