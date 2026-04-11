<template>
  <div class="admin-page">
    <section class="admin-page-head">
      <div>
        <h2 class="admin-page-head__title">密钥查看</h2>
        <p class="admin-page-head__desc">集中展示个人密钥与考试密钥信息，便于管理员核验。</p>
      </div>
      <div class="admin-page-head__meta">
        <el-tag class="admin-tag" type="info">只读展示</el-tag>
      </div>
    </section>

    <section class="admin-panel admin-profile-card">
      <h3 class="admin-section-title">个人密钥</h3>
      <p class="admin-section-note">用于当前管理员账户的身份识别与签名处理。</p>
      <div class="admin-key-grid">
        <el-card class="admin-help-card" shadow="never">
          <div class="admin-help-card__title">
            <i class="el-icon-key"></i>
            <span>公钥</span>
          </div>
          <el-input
            type="textarea"
            readonly
            v-model="key.selfPub"
            :autosize="{ minRows: 7, maxRows: 7 }"
            class="admin-table__textarea"
            resize="none"
          />
        </el-card>
        <el-card class="admin-help-card" shadow="never">
          <div class="admin-help-card__title">
            <i class="el-icon-lock"></i>
            <span>私钥</span>
          </div>
          <el-input
            type="textarea"
            readonly
            v-model="key.selfPri"
            :autosize="{ minRows: 7, maxRows: 7 }"
            class="admin-table__textarea"
            resize="none"
          />
        </el-card>
      </div>
    </section>

    <section class="admin-panel admin-profile-card">
      <h3 class="admin-section-title">考试密钥</h3>
      <p class="admin-section-note">用于考试过程中的加解密处理与数据安全校验。</p>
      <div class="admin-key-grid">
        <el-card class="admin-help-card" shadow="never">
          <div class="admin-help-card__title">
            <i class="el-icon-key"></i>
            <span>公钥</span>
          </div>
          <el-input
            type="textarea"
            readonly
            v-model="key.examPub"
            :autosize="{ minRows: 7, maxRows: 7 }"
            class="admin-table__textarea"
            resize="none"
          />
        </el-card>
        <el-card class="admin-help-card" shadow="never">
          <div class="admin-help-card__title">
            <i class="el-icon-lock"></i>
            <span>私钥</span>
          </div>
          <el-input
            type="textarea"
            readonly
            v-model="key.examPri"
            :autosize="{ minRows: 7, maxRows: 7 }"
            class="admin-table__textarea"
            resize="none"
          />
        </el-card>
      </div>
    </section>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      key: {
        selfPub: "",
        selfPri: "",
        examPub: "",
        examPri: ""
      }
    };
  },
  created() {
    this.load();
  },
  methods: {
    async load() {
      try {
        const res = await request.post("/user/admin/getKey", {
          account: this.user.account,
          id: this.user.id
        });
        if (res.code === "200") {
          this.key = {
            selfPub: res.data.selfPub || "暂无公钥",
            selfPri: res.data.selfPri || "暂无私钥",
            examPub: res.data.examPub || "暂无公钥",
            examPri: res.data.examPri || "暂无私钥"
          };
        }
      } catch (error) {
        this.$message.error("密钥加载失败");
      }
    }
  }
};
</script>
