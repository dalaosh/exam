<template>
  <div class="admin-page">
    <section class="admin-page-head">
      <div>
        <h2 class="admin-page-head__title">个人中心</h2>
        <p class="admin-page-head__desc">查看账户信息、更新头像与维护基础资料。</p>
      </div>
      <div class="admin-page-head__meta">
        <el-tag class="admin-tag" type="success">管理员账户</el-tag>
      </div>
    </section>

    <div class="admin-profile-grid">
      <section class="admin-panel admin-profile-card admin-upload-card">
        <div>
          <h3 class="admin-section-title">头像设置</h3>
          <p class="admin-section-note">上传新的头像图片后会立即保存。</p>
        </div>
        <img class="admin-upload-preview" :src="myInform.photo" />
        <el-upload
          class="admin-avatar-uploader"
          action="http://localhost:9998/files/avatar/upload"
          list-type="picture-card"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
        >
          <img v-if="imageUrl" :src="imageUrl" class="admin-avatar-uploader__image">
          <div v-else class="admin-avatar-uploader__placeholder">
            <i class="el-icon-plus"></i>
            <span>上传头像</span>
          </div>
        </el-upload>
      </section>

      <section class="admin-panel admin-profile-card">
        <div>
          <h3 class="admin-section-title">基础信息</h3>
          <p class="admin-section-note">展示管理员当前账户的主要身份字段。</p>
        </div>
        <el-descriptions :column="1" border>
          <el-descriptions-item label="账户ID">
            <el-tag class="admin-tag">{{ myInform.id }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="角色">
            <el-tag class="admin-tag" type="danger">{{ myInform.role }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="账号">
            <el-tag class="admin-tag" type="warning">{{ myInform.account }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="姓名">
            <el-tag class="admin-tag" type="success">{{ myInform.name }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="性别">
            <el-tag class="admin-tag" type="info">{{ myInform.sex }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="电话">
            <el-tag class="admin-tag" type="success">{{ myInform.phone }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="邮箱">
            <el-tag class="admin-tag" type="success">{{ myInform.email }}</el-tag>
          </el-descriptions-item>
        </el-descriptions>

        <div class="admin-profile-form">
          <h3 class="admin-section-title">信息修改</h3>
          <p class="admin-section-note">点击编辑后可修改姓名、性别、邮箱和电话。</p>
          <el-form :model="form" ref="formRef" label-width="74px">
            <el-form-item label="姓名" prop="name">
              <el-input :readonly="setting" v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="sex">
              <el-input :readonly="setting" v-model="form.sex" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" prop="email">
              <el-input :readonly="setting" v-model="form.email" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="电话" prop="phone">
              <el-input :readonly="setting" v-model="form.phone" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <div class="admin-profile-actions">
            <el-button @click="cancel()">取消</el-button>
            <el-button type="primary" plain @click="set()">编辑</el-button>
            <el-button type="primary" @click="update()">保存</el-button>
          </div>
        </div>
      </section>

      <section class="admin-panel admin-profile-card">
        <div>
          <h3 class="admin-section-title">账户安全说明</h3>
          <p class="admin-section-note">密码与摘要信息仅做展示，帮助管理员核验当前账户状态。</p>
        </div>

        <div class="admin-help-card">
          <div class="admin-help-card__title">
            <i class="el-icon-lock"></i>
            <span>加密密码</span>
          </div>
          <el-input
            class="admin-table__textarea"
            type="textarea"
            :readonly="true"
            :autosize="{ minRows: 4, maxRows: 4 }"
            v-model="user.password"
          />
        </div>

        <div class="admin-help-card">
          <div class="admin-help-card__title">
            <i class="el-icon-tickets"></i>
            <span>密码摘要</span>
          </div>
          <el-input
            class="admin-table__textarea"
            type="textarea"
            :readonly="true"
            :autosize="{ minRows: 3, maxRows: 3 }"
            v-model="user.keySm3"
          />
        </div>

        <div class="admin-help-card">
          <div class="admin-help-card__title">
            <i class="el-icon-guide"></i>
            <span>操作指引</span>
          </div>
          <div class="admin-help-card__list">
            <div><span class="admin-help-step">1</span> 上传头像后系统会自动提交保存。</div>
            <div><span class="admin-help-step">2</span> 点击“编辑”进入可修改状态。</div>
            <div><span class="admin-help-step">3</span> 点击“保存”提交姓名、性别、邮箱和电话变更。</div>
          </div>
        </div>
      </section>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      myInform: {},
      imageUrl: "",
      form: {},
      setting: true
    };
  },
  created() {
    this.load();
  },
  methods: {
    load() {
      request.get("user/admin/selectById/" + this.user.id).then((res) => {
        if (res.code === "200") {
          this.myInform = res.data;
          this.form = Object.assign({}, this.myInform);
        }
      });
    },
    handleAvatarSuccess(res) {
      this.imageUrl = res.data;
      const table = {
        id: this.myInform.id,
        photo: this.imageUrl
      };
      request.put("/user/admin/update/inform", table).then((response) => {
        if (response.code === "200") {
          this.$message.success("头像修改成功");
          this.load();
        }
      });
    },
    update() {
      this.form.id = this.myInform.id;
      request.put("/user/admin/update/inform", this.form).then((res) => {
        if (res.code === "200") {
          this.$message.success("信息修改成功");
          this.load();
          this.setting = true;
        }
      });
    },
    set() {
      this.form = Object.assign({}, this.myInform);
      this.setting = false;
    },
    cancel() {
      this.form = Object.assign({}, this.myInform);
      this.setting = true;
    }
  }
};
</script>

<style scoped>
.admin-profile-form {
  margin-top: 22px;
}

.admin-avatar-uploader ::v-deep .el-upload--picture-card {
  width: 100%;
  height: 160px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 18px;
  border-color: #dbe4ee;
  background: #f8fafc;
}

.admin-avatar-uploader__image {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 18px;
}

.admin-avatar-uploader__placeholder {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 10px;
  color: #64748b;
}
</style>
