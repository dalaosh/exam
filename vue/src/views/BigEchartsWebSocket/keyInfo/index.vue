<template>
  <div class="inline-tech-status">
    <div v-for="(tech, index) in technologies"
         :key="index"
         class="tech-tag"
         :class="`status-${tech.status}`"
         @click="toggleStatus(index)">
      <i :class="tech.icon"></i>
      <span class="tech-name">{{ tech.shortName }}</span>
      <span class="status-dot" :style="{background: statusColor(tech.status)}"></span>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      technologies: [
        {shortName: '非对称加密', status: 'supported', icon: 'el-icon-key'},
        {shortName: '数字摘要', status: 'supported', icon: 'el-icon-cpu'},
        {shortName: '可链接环签名', status: 'supported', icon: 'el-icon-document'},
        {shortName: '人脸认证', status: 'beta', icon: 'el-icon-user'},
      ]
    }
  },
  methods: {
    statusColor(status) {
      const map = {
        supported: '#67c23a',
        beta: '#e6a23c',
        deprecated: '#909399',
        disabled: '#f56c6c'
      }
      return map[status] || '#909399'
    },
    toggleStatus(index) {
      const statusOrder = ['supported', 'beta', 'deprecated', 'disabled']
      const currentIndex = statusOrder.indexOf(this.technologies[index].status)
      const nextIndex = (currentIndex + 1) % statusOrder.length
      this.technologies[index].status = statusOrder[nextIndex]
    }
  }
}
</script>

<style scoped>
.inline-tech-status {
  display: flex;
  gap: 8px;
  padding: 6px;
  overflow-x: auto;
  border-radius: 4px;
  text-align: center;
}

.tech-tag {
  flex: 0 0 auto;
  display: inline-flex;
  align-items: center;
  padding: 4px 10px;
  border-radius: 12px;
  border: 1px solid;
  font-size: 16px;
  cursor: pointer;
  transition: all 0.2s;
}

.tech-tag:hover {
  transform: translateY(-1px);
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
}

.tech-tag i {
  font-size: 14px;
  margin-right: 4px;
}

.status-dot {
  width: 6px;
  height: 6px;
  border-radius: 50%;
  margin-left: 6px;
  box-shadow: 0 0 2px rgba(0, 0, 0, 0.1);
}

/* 状态颜色 */
.status-supported {
  border-color: #67c23a;
  color: #67c23a;
}

.status-beta {
  border-color: #e6a23c;
  color: #e6a23c;
}

.status-deprecated {
  border-color: #909399;
  color: #909399;
}

.status-disabled {
  border-color: #f56c6c;
  color: #f56c6c;
}
</style>