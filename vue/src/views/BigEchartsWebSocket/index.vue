<template>
  <div class="mains">
    <!-- 连接状态指示 -->
    <el-row style="text-align: center">
      <div class="dashboard-frame" style="margin-top: 2vh;width: 98%;margin-left: 1%;">
        <div class="frame-container">
          <div style="z-index: 4">
            <div class="corner-glow"></div>
            <headEChard :connect="connectionStatus "></headEChard>
          </div>
        </div>
      </div>
    </el-row>
    <el-row>
      <el-col :span="8">
        <el-row style="text-align: right">
          <div class="dashboard-frame" style="width: 94%">
            <div class="frame-container">
              <div style="z-index: 4">
                <div class="corner-glow"></div>
                <userNumber  :countData="data.userCount.data"></userNumber>
              </div>
            </div>
          </div>
        </el-row>
        <el-row style="text-align: right">
          <div class="dashboard-frame" style="width: 94%">
            <div class="frame-container">
              <div style="z-index: 4">
                <div class="corner-glow"></div>
                <userLogin :loginData="data.userLogin.data"></userLogin>
              </div>
            </div>
          </div>
        </el-row>
      </el-col>
      <el-col :span="8">
        <el-row style="text-align: center">
          <div class="dashboard-frame" style="width: 96%">
            <div class="frame-container">
              <div style="z-index: 4">
                <div class="corner-glow"></div>
                <videoView></videoView>
              </div>
            </div>
          </div>
        </el-row>
      </el-col>
      <el-col :span="8">
        <el-row>
          <div class="dashboard-frame" style="width: 96%">
            <div class="frame-container">
              <div style="z-index: 4">
                <div class="corner-glow"></div>
                <examNumber  :examData="data.basicNum.data"></examNumber>
              </div>
            </div>
          </div>
        </el-row>
        <el-row>
          <div class="dashboard-frame" style="width: 96%">
            <div class="frame-container">
              <div style="z-index: 4">
                <div class="corner-glow"></div>
                <examEChart :chartData="data.questionNum.data"></examEChart>
              </div>
            </div>
          </div>
        </el-row>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="10" style="text-align: right">
        <el-row>
          <div class="dashboard-frame" style="width: 96%">
            <div class="frame-container">
              <div style="z-index: 4">
                <div class="corner-glow"></div>
                <systemInfo  :systemData="data.systemInfo.data"></systemInfo>
              </div>
            </div>
          </div>
        </el-row>
        <el-row>
          <div class="dashboard-frame" style="width: 96%;">
            <div class="frame-container">
              <div style="z-index: 4">
                <div class="corner-glow"></div>
                <keyInfo></keyInfo>
              </div>
            </div>
          </div>
        </el-row>
      </el-col>
      <el-col :span="8" style="text-align: center">
        <div class="dashboard-frame" style="width: 96%;">
          <div class="frame-container">
            <div style="z-index: 4">
              <div class="corner-glow"></div>
              <dateExam :scheduleData="data.examAll.data"></dateExam>
            </div>
          </div>
        </div>
      </el-col>
      <el-col :span="6" style="text-align: left">
        <div class="dashboard-frame" style="width: 94%">
          <div class="frame-container">
            <div style="z-index: 4">
              <div class="corner-glow"></div>
              <examStrong  :analysisData="data.strongAll.data"></examStrong>
            </div>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import ReconnectingWebSocket from 'reconnecting-websocket'
import headEChard from "@/views/BigEchartsWebSocket/head/index.vue";
import userNumber from "@/views/BigEchartsWebSocket/userNumber/index.vue";
import userLogin from "@/views/BigEchartsWebSocket/userLogin/index.vue";
import examNumber from "@/views/BigEchartsWebSocket/examNumber/index.vue";
import examEChart from "@/views/BigEchartsWebSocket/examEChart/index.vue";
import videoView from "@/views/BigEchartsWebSocket/videoView/index.vue";
import systemInfo from "@/views/BigEchartsWebSocket/systemInfo/index.vue";
import keyInfo from "@/views/BigEchartsWebSocket/keyInfo/index.vue";
import dateExam from "@/views/BigEchartsWebSocket/dateExam/index.vue";
import examStrong from "@/views/BigEchartsWebSocket/examStrong/index.vue";
export default {
  components: {
    headEChard,//头部
    userNumber,//用户数量
    userLogin,//用户登录次数
    examNumber,//考试相关次数
    examEChart,//题库题型数据
    videoView,//视频
    systemInfo,//系统磁盘信息
    keyInfo,//公钥等信息
    dateExam,//考试时间安排
    examStrong//学生能力分析
  },
  data() {
    return {
      isConnected: false,
      connectionStatus: '连接中...',
      data:{},
      chartData: {
        header: null,
        userCount: null,
        userLogin: null,
        videoStats: null,
        examInfo: null,
        examAnalysis: null,
        systemInfo: null,
        publicKeys: null,
        examSchedule: null,
        scoreAnalysis: null
      },
      ws: null
    }
  },
  mounted() {
    this.initWebSocket()
  },
  beforeDestroy() {
    this.ws?.close()
  },
  methods: {
    initWebSocket() {
      this.ws = new ReconnectingWebSocket('ws://localhost:9998/websocket/statistics/dashboard', [], {
        maxRetries: 10,
        connectionTimeout: 5000
      })

      this.ws.onopen = () => {
        this.isConnected = true
        this.connectionStatus = '实时连接已建立'
      }

      this.ws.onmessage = (event) => {
        try {
          const message = JSON.parse(event.data)
          this.updateChartData(message)
        } catch (error) {
          console.error('数据解析失败:', error)
        }
      }

      this.ws.onerror = (error) => {
        this.connectionStatus = '连接异常'
        console.error('WebSocket错误:', error)
      }

      this.ws.onclose = () => {
        this.isConnected = false
        this.connectionStatus = '连接已断开'
      }
    },

    updateChartData(message) {
      this.data=message

    },

    formatTime(timestamp) {
      return new Date(timestamp).toLocaleString()
    }
  }
}
</script>

<style scoped>
.mains{
  overflow-x: scroll;
  overflow-y: scroll;
  background: #101129;
  height: 100vh;
  width: 100vw;
}

.mains::-webkit-scrollbar {
  width: 0;
}

/* 变量定义 */
:root {
  --glow-color: 76, 195, 255;   /* RGB格式方便透明度计算 */
  --glow-intensity: 0.6;        /* 增强发光强度 */
}

.dashboard-frame {
  //background: #101129;
  min-height: 1vh;
  padding: 1vh 1%;
  position: relative;
  display: inline-block;
}

.frame-container {
  --border-glow-size: 14px;
  padding: 14px;
  min-width: 80%;
  min-height: 1vh;
  margin: 0 auto;
  position: relative;
  display: flex;
  flex-direction: column;
  filter:
      drop-shadow(0 0 8px rgba(var(--glow-color), 0.4))
      drop-shadow(0 0 15px rgba(var(--glow-color), 0.2));
}

/* 流动光带效果 */
.frame-container::before {
  content: '';
  position: absolute;
  top: -6px;
  left: -6px;
  right: -6px;
  bottom: -6px;
  background: linear-gradient(
      45deg,
      transparent 40%,
      rgba(var(--glow-color), 0.6) 60%,
      transparent 80%
  );
  opacity: 0;
  z-index: 1;
  animation: lightFlow 6s linear infinite;
  mix-blend-mode: screen;
}

/* 主边框效果 */
.frame-container::after {
  border: 4px solid transparent;
  border-image: url(@/assets/echarts/border.png) 51 38 30 150; /* 放大左下切片 */
  border-width: 40px 30px 30px 120px;  /* 加大左下边框尺寸 */
  content: '';
  position: absolute;
  top: -4px;
  left: -8px;    /* 左偏移调整 */
  right: -2px;
  bottom: -4px;  /* 下偏移调整 */
  pointer-events: none;
  z-index: 2;
  background:
      linear-gradient(
          135deg,
          rgba(var(--glow-color), 0.25) 0%,
          rgba(var(--glow-color), 0.15) 30%,
          transparent 70%
      ),
      linear-gradient(
          to top right,
          transparent 60%,
          rgba(var(--glow-color), 0.2) 80%
      );
  box-shadow:
      inset 0 0 25px rgba(var(--glow-color), calc(0.4 * var(--glow-intensity))),
      0 0 35px rgba(var(--glow-color), calc(0.3 * var(--glow-intensity))),
      15px 10px 30px rgba(var(--glow-color), 0.2);
}

/* 左下角光效 */
.corner-glow {
  position: absolute;
  left: -18px;
  bottom: -12px;
  width: 170px;
  height: 100px;
  background: radial-gradient(
      circle at left bottom,
      rgba(var(--glow-color), 0.5) 0%,
      transparent 70%
  );
  filter: blur(15px);
  z-index: 1;
  pointer-events: none;
  animation:
      corner-pulse 2.5s ease-in-out infinite,
      border-glow 3s ease-in-out infinite;
}

</style>
