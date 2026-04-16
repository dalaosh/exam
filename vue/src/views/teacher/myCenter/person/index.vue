<template>
  <div class="teacher-page teacher-profile-page">
    <section class="teacher-page-head">
      <div>
        <h2 class="teacher-page-head__title">个人中心</h2>
      </div>
      <div class="teacher-page-head__meta">
        <el-tag class="teacher-tag" :type="setting ? 'info' : 'warning'">{{ setting ? '只读模式' : '编辑模式' }}</el-tag>
      </div>
    </section>

    <div class="teacher-profile-shell">
      <aside class="teacher-profile-sidebar">
        <section class="teacher-panel teacher-profile-card teacher-profile-identity">
          <div class="teacher-profile-identity__photo">
            <img :src="imageUrl || myInform.photo" alt="教师头像" />
          </div>
          <div class="teacher-profile-identity__meta">
            <h3>{{ myInform.name || '未设置姓名' }}</h3>
            <p>{{ myInform.role || 'teacher' }}</p>
            <span>{{ myInform.account || '暂无账号' }}</span>
          </div>
        </section>

        <section class="teacher-panel teacher-profile-card teacher-profile-upload">
          <div class="teacher-profile-card__head">
            <h3>头像更新</h3>
            <span>JPG / PNG</span>
          </div>
          <p class="teacher-profile-note">上传新头像后会直接提交到当前教师资料，不影响其他字段内容。</p>
          <el-upload
            class="teacher-profile-uploader"
            action="http://localhost:9998/files/avatar/upload"
            list-type="picture-card"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
          >
            <img v-if="imageUrl" :src="imageUrl" class="teacher-profile-uploader__image" alt="预览头像" />
            <div v-else class="teacher-profile-uploader__placeholder">
              <i class="el-icon-plus"></i>
              <span>上传头像</span>
            </div>
          </el-upload>
        </section>

        <section class="teacher-panel teacher-profile-card teacher-profile-help">
          <div class="teacher-profile-card__head">
            <h3>操作提示</h3>
            <span>工作区说明</span>
          </div>
          <ul class="teacher-profile-help__list">
            <li>点击“编辑”后，可修改姓名、性别、邮箱和电话。</li>
            <li>点击“取消”会恢复当前已保存的教师信息。</li>
            <li>密码密文与摘要仅展示，不在当前页面内直接修改。</li>
          </ul>
        </section>
      </aside>

      <main class="teacher-profile-main">
        <section class="teacher-profile-grid">
          <article class="teacher-panel teacher-profile-card">
            <div class="teacher-profile-card__head">
              <h3>基本信息</h3>
              <span>当前账号资料</span>
            </div>
            <el-descriptions :column="2" border class="teacher-profile-descriptions">
              <el-descriptions-item label="账号 ID">
                <el-tag class="teacher-tag">{{ myInform.id || '-' }}</el-tag>
              </el-descriptions-item>
              <el-descriptions-item label="角色">
                <el-tag class="teacher-tag" type="danger">{{ myInform.role || '-' }}</el-tag>
              </el-descriptions-item>
              <el-descriptions-item label="账号">
                <el-tag class="teacher-tag" type="warning">{{ myInform.account || '-' }}</el-tag>
              </el-descriptions-item>
              <el-descriptions-item label="姓名">
                <el-tag class="teacher-tag" type="success">{{ myInform.name || '-' }}</el-tag>
              </el-descriptions-item>
              <el-descriptions-item label="性别">
                <el-tag class="teacher-tag" type="success">{{ myInform.sex || '-' }}</el-tag>
              </el-descriptions-item>
              <el-descriptions-item label="电话">
                <el-tag class="teacher-tag" type="success">{{ myInform.phone || '-' }}</el-tag>
              </el-descriptions-item>
              <el-descriptions-item label="邮箱" :span="2">
                <el-tag class="teacher-tag" type="success">{{ myInform.email || '-' }}</el-tag>
              </el-descriptions-item>
            </el-descriptions>
          </article>

          <article class="teacher-panel teacher-profile-card teacher-profile-security">
            <div class="teacher-profile-card__head">
              <h3>安全信息</h3>
              <span>只读摘要</span>
            </div>
            <div class="teacher-profile-security__group">
              <label>加密后的密码</label>
              <el-input
                type="textarea"
                :readonly="true"
                :autosize="{ minRows: 5, maxRows: 5 }"
                v-model="user.password"
              ></el-input>
            </div>
            <div class="teacher-profile-security__group">
              <label>密码摘要</label>
              <el-input
                type="textarea"
                :readonly="true"
                :autosize="{ minRows: 3, maxRows: 3 }"
                v-model="user.keySm3"
              ></el-input>
            </div>
          </article>
        </section>

        <section class="teacher-panel teacher-profile-card teacher-profile-editor">
          <div class="teacher-profile-card__head">
            <h3>资料修改</h3>
            <span>{{ setting ? '点击编辑进入修改模式' : '修改后点击确定保存' }}</span>
          </div>
          <el-form :model="form" ref="formRef" label-position="top" class="teacher-profile-form">
            <div class="teacher-profile-form__grid">
              <el-form-item label="姓名" prop="name">
                <el-input :readonly="setting" v-model="form.name"></el-input>
              </el-form-item>
              <el-form-item label="性别" prop="sex">
                <el-input :readonly="setting" v-model="form.sex" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="电子邮箱" prop="email">
                <el-input :readonly="setting" v-model="form.email" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="电话" prop="phone">
                <el-input :readonly="setting" v-model="form.phone" autocomplete="off"></el-input>
              </el-form-item>
            </div>
          </el-form>

          <div class="teacher-profile-actions">
            <el-button @click="cancel()">取消</el-button>
            <el-button type="warning" @click="set()">编辑</el-button>
            <el-button type="primary" @click="update()">确定</el-button>
          </div>
        </section>
      </main>
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
      request.get("user/teacher/selectById/" + this.user.id).then((res) => {
        if (res.code === "200") {
          this.myInform = res.data || {};
          this.form = { ...this.myInform };
          this.imageUrl = this.myInform.photo || "";
        }
      });
    },
    handleAvatarSuccess(res) {
      this.imageUrl = res.data;
      const table = {
        id: this.myInform.id,
        photo: this.imageUrl
      };
      request.put("/user/teacher/update/inform", table).then((result) => {
        if (result.code === "200") {
          this.$message.success("头像修改成功");
          this.load();
        }
      });
    },
    update() {
      this.form.id = this.myInform.id;
      request.put("/user/teacher/update/inform", this.form).then((res) => {
        if (res.code === "200") {
          this.$message.success("信息修改成功");
          this.setting = true;
          this.load();
        }
      });
    },
    set() {
      this.form = { ...this.myInform };
      this.setting = false;
    },
    cancel() {
      this.form = { ...this.myInform };
      this.setting = true;
    }
  }
};
</script>

<style scoped>
.teacher-profile-shell {
  display: grid;
  grid-template-columns: 320px minmax(0, 1fr);
  gap: 18px;
  align-items: stretch;
}

.teacher-profile-sidebar,
.teacher-profile-main {
  display: grid;
  gap: 18px;
}

.teacher-profile-card {
  padding: 20px;
}

.teacher-profile-card__head {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 12px;
  margin-bottom: 16px;
}

.teacher-profile-card__head h3 {
  margin: 0;
  font-size: 17px;
  font-weight: 700;
  color: #0f172a;
}

.teacher-profile-card__head span {
  font-size: 12px;
  color: #6b7280;
}

.teacher-profile-identity {
  display: grid;
  gap: 16px;
}

.teacher-profile-identity__photo {
  border-radius: 18px;
  overflow: hidden;
  background: #f8fafc;
  border: 1px solid #e5e7eb;
  aspect-ratio: 4 / 5;
}

.teacher-profile-identity__photo img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.teacher-profile-identity__meta {
  display: grid;
  gap: 6px;
}

.teacher-profile-identity__meta h3 {
  margin: 0;
  font-size: 22px;
  font-weight: 700;
  color: #0f172a;
}

.teacher-profile-identity__meta p,
.teacher-profile-identity__meta span {
  margin: 0;
  color: #6b7280;
  line-height: 1.6;
}

.teacher-profile-note {
  margin: 0 0 16px;
  color: #6b7280;
  line-height: 1.7;
}

.teacher-profile-uploader {
  display: block;
}

.teacher-profile-uploader :deep(.el-upload) {
  width: 100%;
  height: 210px;
  border-radius: 18px;
  border: 1px dashed #9fd7c2;
  background: linear-gradient(180deg, #f8fffc 0%, #eefbf6 100%);
}

.teacher-profile-uploader__image {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 18px;
}

.teacher-profile-uploader__placeholder {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100%;
  gap: 10px;
  color: #0f766e;
  font-weight: 600;
}

.teacher-profile-uploader__placeholder i {
  font-size: 28px;
}

.teacher-profile-help {
  align-content: start;
}

.teacher-profile-help__list {
  margin: 0;
  padding-left: 18px;
  color: #475569;
  line-height: 1.8;
}

.teacher-profile-grid {
  display: grid;
  grid-template-columns: repeat(2, minmax(0, 1fr));
  gap: 18px;
}

.teacher-profile-descriptions :deep(.el-descriptions__table) {
  border-radius: 14px;
  overflow: hidden;
}

.teacher-profile-security {
  display: grid;
  gap: 16px;
}

.teacher-profile-security__group {
  display: grid;
  gap: 8px;
}

.teacher-profile-security__group label {
  font-size: 13px;
  font-weight: 700;
  color: #475569;
}

.teacher-profile-security :deep(.el-textarea__inner) {
  border-radius: 14px;
  background: #f8fafc;
  border-color: #e2e8f0;
  color: #334155;
  line-height: 1.7;
}

.teacher-profile-editor {
  display: grid;
  gap: 18px;
}

.teacher-profile-form__grid {
  display: grid;
  grid-template-columns: repeat(2, minmax(0, 1fr));
  gap: 0 18px;
}

.teacher-profile-form :deep(.el-form-item__label) {
  padding-bottom: 6px;
  font-weight: 700;
  color: #475569;
}

.teacher-profile-form :deep(.el-input__inner) {
  height: 40px;
  border-radius: 12px;
}

.teacher-profile-actions {
  display: flex;
  justify-content: flex-end;
  flex-wrap: wrap;
  gap: 12px;
}

.teacher-profile-actions :deep(.el-button) {
  min-width: 108px;
  height: 40px;
  border-radius: 12px;
  font-weight: 700;
}

@media (max-width: 1320px) {
  .teacher-profile-shell,
  .teacher-profile-grid,
  .teacher-profile-form__grid {
    grid-template-columns: 1fr;
  }
}
</style>
