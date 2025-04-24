<template>
  <div class="dark-scroll-table">
    <!-- 表头 -->
    <div class="table-header">
      <div class="header-item">课程</div>
      <div class="header-item">考试类型</div>
      <div class="header-item">任课教师</div>
      <div class="header-item">开考时间</div>
    </div>

    <!-- 滚动内容 -->
    <div class="scroll-wrapper">
      <div
          class="scroll-content"
          :style="{ animationDuration: scrollSpeed }"
      >
        <!-- 双倍数据实现无缝滚动 -->
        <div
            v-for="(item, index) in duplicatedData"
            :key="index"
            class="table-row"
        >
          <div class="row-item course">{{ item.course }}</div>
          <div class="row-item type">{{ item.examType }}</div>
          <div class="row-item teacher">{{ item.teacher }}</div>
          <div class="row-item time">{{ formatTime(item.examDate) }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  data() {
    return {
      scrollSpeed: '20s',
      examData: [] // 初始数据示例
    }
  },
  props: {
    scheduleData: {
      type: Object,
      default: () => ({})
    }
  },
  watch: {
    scheduleData: {
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
  computed: {
    duplicatedData() {
      return [...this.examData, ...this.examData]
    }
  },
  methods: {
    formatTime(dateStr) {
      return dateStr
    },
    async loadData() {
        this.examData = this.scheduleData.map(item => ({
          course: item.course.name,
          examType: item.type,
          teacher: item.teacher.name,
          examDate: item.examDate
        }))
      }
  },
  mounted() {
    this.loadData()
  }
}
</script>

<style scoped>
/* 保持原图样式 */
.dark-scroll-table {
  color: #fff;
  border-radius: 4px;
  overflow: hidden;
  height: 31vh;
  font-size: 12px;
}

/* 四列布局调整 */
.table-header {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 2fr; /* 课程列加宽 */
  padding: 12px 15px;
  border-bottom: 1px solid #3a3a3a;
}

.scroll-wrapper {
  height: calc(31vh - 46px);
  overflow: hidden;
  position: relative;
}

.scroll-content {
  animation: scroll linear infinite;
}

.table-row {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 2fr;
  padding: 10px 15px;
  border-bottom: 1px solid rgba(255,255,255,0.1);
}

.row-item {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  padding: 0 8px;
}

/* 列对齐优化 */
.course { text-align: center }
.type { text-align: center }
.teacher { text-align: center }
.time { text-align: center }

@keyframes scroll {
  0% { transform: translateY(0); }
  100% { transform: translateY(-50%); }
}

/* 移动端适配 */
@media (max-width: 768px) {
  .table-header, .table-row {
    grid-template-columns: 1.5fr 1fr 1fr 1fr;
    padding: 8px 10px;
  }
  .row-item {
    font-size: 11px;
  }
}
</style>
