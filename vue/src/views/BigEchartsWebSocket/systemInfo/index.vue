<template>
  <div style="width: 100%; height: 20vh; color: #fff; text-align: center; padding: 10px">
    <el-row :gutter="20">
      <!-- CPU信息 -->
      <el-col :span="6">
        <div class="metric-box">
          <div class="metric-value">{{ (cpu.usage * 100).toFixed(1) }}%</div>
          <div class="metric-label" style="color: #08eedb">CPU使用率</div>
          <div class="metric-tip">当前负载</div>
        </div>
      </el-col>

      <!-- 内存信息 -->
      <el-col :span="6">
        <div class="metric-box">
          <div class="metric-value">{{ (memory.usageRate * 100).toFixed(1) }}%</div>
          <div class="metric-label" style="color: #08eedb">内存使用率</div>
          <div class="metric-tip">已用 {{ memory.used.toFixed(1) }}GB / 总量 {{ memory.total.toFixed(1) }}GB</div>
        </div>
      </el-col>

      <!-- 磁盘信息 -->
      <el-col :span="12">
        <div class="disk-container">
          <div v-for="(disk, index) in disk.disks" :key="index" class="disk-item">
            <div class="disk-header">
              <span>{{ disk.name }}</span>
              <span>{{ (disk.usageRate * 100).toFixed(1) }}%</span>
            </div>
            <el-progress
                :percentage="disk.usageRate * 100"
                :color="getDiskColor(disk.usageRate)"
                :show-text="false"
                stroke-width="12"
            />
            <div class="disk-footer">
              <span>剩余 {{ disk.free.toFixed(1) }}GB</span>
              <span>已用 {{ disk.used.toFixed(1) }}GB</span>
            </div>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "SystemMonitor",
  props: {
    systemData: {
      type: Object,
      default: () => ({})
    }
  },
  watch: {
    systemData: {
      handler(newVal) {
        // 深度合并数据（防止丢失嵌套结构）
        this.data = {
          ...this.data,
          ...newVal,
        };
      },
      deep: true,   // 深度监听对象变化
      immediate: true // 立即触发一次确保初始数据合并
    }
  },
  mounted() {
    this.data = { ...this.data, ...this.systemData };
  },
  beforeDestroy() {
    clearInterval(this.refreshTimer);
  },
  data() {
    return {
      data: {
        disk: {disks: []},
        memory: {},
        cpu: {}
      },
      refreshTimer: null
    };
  },
  computed: {
    cpu() {
      return this.data.cpu || {};
    },
    memory() {
      return this.data.memory || {};
    },
    disk() {
      return this.data.disk || {};
    }
  },
  methods: {
    getDiskColor(rate) {
      if (rate > 0.8) return '#ff4d4f';  // 红色警报
      if (rate > 0.6) return '#faad14';  // 黄色警告
      return '#08eedb';                  // 正常颜色
    }
  }
}
</script>

<style scoped>
.metric-box {
  background-image: url("@/assets/echarts/img.png");
  background-size: 100% 18vh;
  padding: 15px;
  border-radius: 4px;
  height: 18vh;
}

.metric-value {
  font-size: 28px;
  margin: 10px 0;
  font-weight: bold;
}

.metric-label {
  font-size: 16px;
  margin-bottom: 8px;
}

.metric-tip {
  font-size: 12px;
  color: #888;
}

.disk-container {
  height: 18vh;
  overflow-y: scroll;
}

.disk-container::-webkit-scrollbar {
  width: 0;
}

.disk-item {
  margin-bottom: 12px;
  padding: 8px;
  background: rgba(255, 255, 255, 0.05);
  border-radius: 4px;
}

.disk-header {
  display: flex;
  justify-content: space-between;
  font-size: 12px;
  margin-bottom: 6px;
}

.disk-footer {
  display: flex;
  justify-content: space-between;
  font-size: 12px;
  color: #888;
  margin-top: 4px;
}

::v-deep .el-progress-bar {
  padding-right: 0;
}
</style>
