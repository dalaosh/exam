<template>
  <div class="student-page">
    <section class="student-page-head">
      <div>
        <h2 class="student-page-head__title">个人中心</h2>
        <p class="student-page-head__desc">统一查看基础资料、头像上传、安全摘要和个人信息编辑，保留原有交互方式。</p>
      </div>
    </section>

    <section class="student-profile-grid">
      <article class="student-panel student-profile-card">
        <h3 class="student-section-title">头像资料</h3>
        <p class="student-section-note">上传新头像后立即提交，头像更新逻辑保持不变。</p>
        <div class="student-upload-card">
          <img class="student-profile-preview" :src="myInform.photo || user.photo" alt="">
          <el-upload
            class="avatar-uploader"
            action="http://localhost:9998/files/avatar/upload"
            list-type="picture-card"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
          >
            <img v-if="imageUrl" :src="imageUrl" class="student-profile-preview" alt="">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </div>
      </article>

      <article class="student-panel student-profile-card">
        <h3 class="student-section-title">基本信息</h3>
        <p class="student-section-note">左侧展示只读信息，右侧保留编辑模式切换。</p>
        <el-descriptions :column="1" border>
          <el-descriptions-item label="账号 ID">
            <el-tag class="student-tag">{{ myInform.id }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="角色">
            <el-tag class="student-tag" type="danger">{{ myInform.role }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="账号">
            <el-tag class="student-tag" type="warning">{{ myInform.account }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="姓名">
            <el-tag class="student-tag" type="success">{{ myInform.name }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="性别">
            <el-tag class="student-tag" type="success">{{ myInform.sex }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="电话">
            <el-tag class="student-tag" type="success">{{ myInform.phone }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="邮箱地址">
            <el-tag class="student-tag" type="success">{{ myInform.email }}</el-tag>
          </el-descriptions-item>
        </el-descriptions>

        <div style="margin-top: 18px;">
          <h3 class="student-section-title">信息编辑</h3>
          <el-form :model="form" ref="formRef" label-width="88px">
            <el-form-item label="姓名" prop="name">
              <el-input :readonly="setting" v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="sex">
              <el-input :readonly="setting" v-model="form.sex" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="电子邮件" prop="email">
              <el-input :readonly="setting" v-model="form.email" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="电话" prop="phone">
              <el-input :readonly="setting" v-model="form.phone" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <div class="student-profile-actions">
            <el-button @click="cancel">取消</el-button>
            <el-button type="primary" plain @click="set">编辑</el-button>
            <el-button type="primary" @click="update">确定</el-button>
          </div>
        </div>
      </article>

      <article class="student-panel student-profile-card">
        <h3 class="student-section-title">安全摘要</h3>
        <p class="student-section-note">密码密文和摘要只读展示，方便核验但不改变原有后端数据结构。</p>

        <div style="margin-bottom: 18px;">
          <div class="student-section-title" style="font-size: 14px; margin-bottom: 10px;">加密的密码</div>
          <el-input
            class="student-text-block"
            type="textarea"
            :readonly="true"
            :autosize="{ minRows: 4, maxRows: 4 }"
            v-model="user.password"
          />
        </div>

        <div style="margin-bottom: 18px;">
          <div class="student-section-title" style="font-size: 14px; margin-bottom: 10px;">密码摘要</div>
          <el-input
            class="student-text-block"
            type="textarea"
            :readonly="true"
            :autosize="{ minRows: 2, maxRows: 2 }"
            v-model="user.keySm3"
          />
        </div>

        <div class="student-help-card">
          <div class="student-help-card__title">
            <span class="student-help-step">1</span>
            头像更改说明
          </div>
          <div class="student-help-card__list">
            <span>点击上传区域选择图片，支持 JPG 和 PNG 格式。</span>
            <span>上传成功后会立即触发头像更新并刷新个人资料。</span>
          </div>
        </div>

        <div class="student-help-card">
          <div class="student-help-card__title">
            <span class="student-help-step">2</span>
            信息编辑说明
          </div>
          <div class="student-help-card__list">
            <span>点击“编辑”进入修改模式，保持原有字段和提交逻辑不变。</span>
            <span>点击“确定”后提交最新资料，点击“取消”恢复当前信息。</span>
          </div>
        </div>
      </article>
    </section>
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
      setting: true,
      labelStyle: {}
    };
  },
  created() {
    this.load();
  },
  methods: {
    load() {
      request.get(`user/student/selectById/${this.user.id}`).then((res) => {
        if (res.code === "200") {
          this.myInform = res.data;
          this.form = this.myInform;
        }
      });
    },
    handleAvatarSuccess(res) {
      this.imageUrl = res.data;
      const table = {
        id: this.myInform.id,
        photo: this.imageUrl
      };
      request.put("/user/student/update/inform", table).then((result) => {
        if (result.code === "200") {
          this.$message.success("头像修改成功");
          this.load();
        }
      });
    },
    update() {
      this.form.id = this.myInform.id;
      request.put("/user/student/update/inform", this.form).then((res) => {
        if (res.code === "200") {
          this.$message.success("信息修改成功");
          this.load();
        }
      });
    },
    set() {
      this.form = this.myInform;
      this.setting = false;
    },
    cancel() {
      this.form = this.myInform;
      this.setting = true;
    }
  }
};
</script>
