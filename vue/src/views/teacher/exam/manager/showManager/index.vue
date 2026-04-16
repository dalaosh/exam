<template>
  <div class="exam-workspace">
    <div class="workspace-header">
      <div>
        <div class="workspace-title">考试管理工作台</div>
      </div>
    </div>

    <el-tabs v-model="activeTab" class="workspace-tabs" type="border-card">
      <el-tab-pane label="考试详情" name="overview">
        <div class="tab-panel">
          <show :param="receivedData.id"></show>
        </div>
      </el-tab-pane>
      <el-tab-pane label="自动组卷" name="auto">
        <div class="tab-panel">
          <AIExam :param="receivedData.id" :courseId="receivedData.courseId"></AIExam>
        </div>
      </el-tab-pane>
      <el-tab-pane label="手动组卷" name="manual">
        <div class="tab-panel">
          <manual-paper :exam-id="receivedData.id" :course-id="receivedData.courseId"></manual-paper>
        </div>
      </el-tab-pane>
      <el-tab-pane label="多Agent智能组卷" name="multi-agent">
        <div class="tab-panel">
          <multi-agent-paper :exam-id="receivedData.id" :course-id="receivedData.courseId"></multi-agent-paper>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import AIExam from "@/views/teacher/exam/manager/showManager/AIExam/index.vue";
import ManualPaper from "@/views/teacher/exam/manager/showManager/manualPaper.vue";
import MultiAgentPaper from "@/views/teacher/exam/manager/showManager/multiAgentPaper.vue";
import show from "@/views/teacher/exam/manager/showManager/show";

export default {
  components: {
    AIExam,
    ManualPaper,
    MultiAgentPaper,
    show,
  },
  data() {
    return {
      activeTab: "overview",
      receivedData: "",
    };
  },
  created() {
    this.receivedData = this.$route.params;
  },
};
</script>

<style scoped>
.exam-workspace {
  display: flex;
  flex-direction: column;
  gap: 14px;
  padding-bottom: 8px;
}

.workspace-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 16px;
  margin: 0;
  padding-bottom: 2px;
}

.workspace-title {
  font-size: 18px;
  font-weight: 600;
  color: #0f172a;
  line-height: 1.3;
}

.workspace-copy {
  color: #64748b;
  font-size: 13px;
}

.workspace-tabs {
  border-radius: 6px;
  overflow: hidden;
  border: 1px solid #e5e7eb;
  box-shadow: none;
}

.workspace-tabs :deep(.el-tabs__header) {
  padding: 8px 12px 0;
  margin: 0;
  background: #fff;
  border-bottom: 1px solid #e5e7eb;
}

.workspace-tabs :deep(.el-tabs__item) {
  height: 38px;
  line-height: 38px;
  border-radius: 6px 6px 0 0;
  font-weight: 600;
  color: #475569;
}

.workspace-tabs :deep(.el-tabs__item.is-active) {
  color: #1d4ed8;
}

.workspace-tabs :deep(.el-tabs__content) {
  padding: 16px;
  background: #fff;
}

.tab-panel {
  min-height: 620px;
}

.workspace-tabs :deep(.heads) {
  display: flex;
  align-items: center;
  min-height: 52px;
  margin-bottom: 14px;
  padding: 0 4px;
  border-bottom: 1px solid #eef2f7;
}

.workspace-tabs :deep(.heads::before),
.workspace-tabs :deep(.heads::after) {
  display: none;
}

.workspace-tabs :deep(.heads .el-col) {
  float: none;
  display: flex;
  align-items: center;
  min-height: 52px;
}

.workspace-tabs :deep(.heads .el-col:first-child) {
  justify-content: flex-start;
}

.workspace-tabs :deep(.heads .el-col:nth-child(2)) {
  justify-content: center;
  flex: 1;
}

.workspace-tabs :deep(.heads .el-col:last-child) {
  justify-content: flex-end;
}

.workspace-tabs :deep(.heads .el-button) {
  margin: 0;
  padding: 0;
  font-size: 14px !important;
  font-weight: 600;
}

.workspace-tabs :deep(.mains) {
  height: auto !important;
  min-height: 500px;
  padding: 4px 0 10px;
  border-radius: 6px;
  background: #fff;
  overflow: visible;
}

.workspace-tabs :deep(.mains::-webkit-scrollbar) {
  width: 6px;
}

.workspace-tabs :deep(.mains::-webkit-scrollbar-thumb) {
  background: rgba(148, 163, 184, 0.5);
  border-radius: 999px;
}

.workspace-tabs :deep(.cards) {
  border-radius: 6px;
  border: 1px solid #e5e7eb;
  background: #fff;
  box-shadow: none;
}

.workspace-tabs :deep(.cards .el-card__body) {
  padding: 18px 20px;
}

.workspace-tabs :deep(.main .search) {
  height: auto !important;
  padding: 16px 18px;
  border-bottom: 1px solid #eef2f7;
}

.workspace-tabs :deep(.main .search .el-row) {
  margin-top: 0 !important;
  margin-left: 0 !important;
}

.workspace-tabs :deep(.main .search .el-form) {
  display: flex;
  flex-wrap: wrap;
  gap: 12px 10px;
  align-items: flex-end;
}

.workspace-tabs :deep(.main .search .el-form-item) {
  margin-right: 0 !important;
  margin-bottom: 0 !important;
}

.workspace-tabs :deep(.main .search .el-input),
.workspace-tabs :deep(.main .search .el-select) {
  width: 190px;
  max-width: 100%;
}

.workspace-tabs :deep(.main .table) {
  height: auto !important;
  padding: 18px;
}

.workspace-tabs :deep(.el-drawer__header) {
  margin-bottom: 0;
  padding: 18px 20px 14px;
  border-bottom: 1px solid #e5e7eb;
}

.workspace-tabs :deep(.el-drawer__body) {
  overflow: auto;
  background: #fff;
}

.workspace-tabs :deep(.el-drawer__body .main) {
  min-height: 100%;
}

.workspace-tabs :deep(.question),
.workspace-tabs :deep(.teacher-rich-content) {
  line-height: 1.75;
}

@media (max-width: 1100px) {
  .workspace-header {
    flex-wrap: wrap;
    align-items: flex-start;
  }

  .workspace-copy {
    width: 100%;
  }

  .workspace-tabs :deep(.heads) {
    flex-wrap: wrap;
    gap: 8px;
    align-items: flex-start;
  }
}
</style>
