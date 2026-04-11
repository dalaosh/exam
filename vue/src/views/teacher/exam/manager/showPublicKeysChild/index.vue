<template>
  <div class="key-detail-card">
    <div class="detail-header">
      <div class="detail-title">教师 {{ $route.params.name }} 的公钥</div>
      <div class="detail-copy">以下内容仅供查看与校验使用。</div>
    </div>
    <el-input
      type="textarea"
      class="key-output"
      :autosize="{ minRows: 8, maxRows: 8 }"
      placeholder="公钥数据"
      v-model="$route.params.dataKey[$route.params.name]"
    ></el-input>
  </div>
</template>

<script>
export default {
  name: "BookView",
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  }
}
</script>

<style scoped>
.key-detail-card {
  margin-top: 8px;
  padding: 20px 22px;
  border: 1px solid rgba(226, 232, 240, 0.95);
  border-radius: 22px;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0.98), rgba(248, 250, 252, 0.96));
  box-shadow: 0 16px 36px rgba(15, 23, 42, 0.06);
}

.detail-header {
  margin-bottom: 14px;
}

.detail-title {
  margin-bottom: 6px;
  font-size: 18px;
  font-weight: 700;
  color: #0f172a;
}

.detail-copy {
  color: #64748b;
  font-size: 13px;
}

.key-output :deep(.el-textarea__inner) {
  border-radius: 16px;
  border-color: #dbe4ee;
  background: linear-gradient(180deg, #ffffff 0%, #f8fafc 100%);
  font-size: 14px;
  line-height: 1.8;
}
</style>
