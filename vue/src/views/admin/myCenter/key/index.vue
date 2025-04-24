<template>
  <div>
    <!-- 个人密钥卡片 -->
    <el-card shadow="never" class="main-card">
      <div class="card-title">个人密钥</div>
      <el-row :gutter="24">
        <el-col :span="12">
          <el-card class="key-card">
            <div class="key-label">公钥</div>
            <el-input
                type="textarea"
                readonly
                v-model="key.selfPub"
                :autosize="{ minRows: 5, maxRows: 5 }"
                class="key-input"
                resize="none"
            />
          </el-card>
        </el-col>
        <el-col :span="12">
          <el-card class="key-card">
            <div class="key-label">私钥</div>
            <el-input
                type="textarea"
                readonly
                v-model="key.selfPri"
                :autosize="{ minRows: 5, maxRows: 5 }"
                class="key-input"
                resize="none"
            />
          </el-card>
        </el-col>
      </el-row>
    </el-card>

    <!-- 考试密钥卡片 -->
    <el-card shadow="never" class="main-card">
      <div class="card-title">考试密钥</div>
      <el-row :gutter="24">
        <el-col :span="12">
          <el-card class="key-card">
            <div class="key-label">公钥</div>
            <el-input
                type="textarea"
                readonly
                v-model="key.examPub"
                :autosize="{ minRows: 5, maxRows: 5 }"
                class="key-input"
                resize="none"
            />
          </el-card>
        </el-col>
        <el-col :span="12">
          <el-card class="key-card">
            <div class="key-label">私钥</div>
            <el-input
                type="textarea"
                readonly
                v-model="key.examPri"
                :autosize="{ minRows: 5, maxRows: 5 }"
                class="key-input"
                resize="none"
            />
          </el-card>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
import request from "@/utils/request"

export default {
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      key: {
        selfPub: '',
        selfPri: '',
        examPub: '',
        examPri: ''
      }
    }
  },
  created() {
    this.load()
  },
  methods: {
    async load() {
      try {
        const res = await request.post("/user/admin/getKey", {
          account: this.user.account,
          id: this.user.id
        })
        if (res.code === "200") {
          this.key = {
            selfPub: res.data.selfPub || '暂无公钥',
            selfPri: res.data.selfPri || '暂无私钥',
            examPub: res.data.examPub || '暂无公钥',
            examPri: res.data.examPri || '暂无私钥'
          }
        }
      } catch (error) {
        console.error("密钥获取失败:", error)
        this.$message.error("密钥加载失败")
      }
    }
  }
}
</script>

<style scoped>
.main-card {
  height: 38vh;
  background: #f8faf9;
  border-radius: 12px;
  margin: 16px 20px;
  border: 1px solid #e8eceb;
}

.card-title {
  color: #2c3e50;
  font-size: 18px;
  font-weight: 600;
  margin-bottom: 16px;
  letter-spacing: 0.5px;
}

.key-card {
  border-radius: 8px;
  margin-bottom: 16px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
  border: 1px solid #ebf0ef;
}

.key-label {
  color: #5a5e66;
  font-size: 14px;
  margin-bottom: 8px;
  font-weight: 500;
}

.key-input {
  font-family: 'Menlo', monospace;
  font-size: 14px;
  line-height: 1.6;
  background: #f5fffd;
  border-radius: 6px;
}

.key-input ::v-deep .el-textarea__inner {
  transition: all 0.3s ease;
  border-color: #dcdfe6;
  padding: 12px;
  background: #f5fffd;
}

.key-input ::v-deep .el-textarea__inner:hover {
  border-color: #42b983;
  box-shadow: 0 0 8px rgba(66, 185, 131, 0.1);
}
</style>