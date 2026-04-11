<template>
  <div class="student-page">
    <section class="student-page-head">
      <div>
        <h2 class="student-page-head__title">密钥查看</h2>
        <p class="student-page-head__desc">集中查看个人密钥与考试密钥，保持只读展示和统一后台样式。</p>
      </div>
      <div class="student-page-head__meta">
        <el-tag class="student-tag" type="info">只读信息</el-tag>
      </div>
    </section>

    <section class="student-panel student-panel--padded">
      <h3 class="student-section-title">个人密钥</h3>
      <p class="student-section-note">用于当前学生账户的身份识别与签名校验。</p>
      <div class="student-key-grid">
        <div class="student-help-card student-key-card">
          <div class="student-help-card__title">
            <i class="el-icon-key"></i>
            <span>公钥</span>
          </div>
          <el-input
            type="textarea"
            readonly
            resize="none"
            v-model="key.selfPub"
            :rows="7"
            class="student-text-block student-key-textarea"
          />
        </div>

        <div class="student-help-card student-key-card">
          <div class="student-help-card__title">
            <i class="el-icon-lock"></i>
            <span>私钥</span>
          </div>
          <el-input
            type="textarea"
            readonly
            resize="none"
            v-model="key.selfPri"
            :rows="7"
            class="student-text-block student-key-textarea"
          />
        </div>
      </div>
    </section>

    <section class="student-panel student-panel--padded">
      <h3 class="student-section-title">考试密钥</h3>
      <p class="student-section-note">用于考试过程中的加解密与数据校验。</p>
      <div class="student-key-grid">
        <div class="student-help-card student-key-card">
          <div class="student-help-card__title">
            <i class="el-icon-key"></i>
            <span>公钥</span>
          </div>
          <el-input
            type="textarea"
            readonly
            resize="none"
            v-model="key.examPub"
            :rows="7"
            class="student-text-block student-key-textarea"
          />
        </div>

        <div class="student-help-card student-key-card">
          <div class="student-help-card__title">
            <i class="el-icon-lock"></i>
            <span>私钥</span>
          </div>
          <el-input
            type="textarea"
            readonly
            resize="none"
            v-model="key.examPri"
            :rows="7"
            class="student-text-block student-key-textarea"
          />
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import request from "@/utils/request"
export default {
  data() {
    return{
      user:localStorage.getItem("user") ? (JSON).parse(localStorage.getItem("user")) : {},
      myInform:{
        photo:"https://tse4-mm.cn.bing.net/th/id/OIP-C.QoRCEN8clpncbfUjX7WJaQAAAA?w=178&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7"
      },
      form:{
      },
      key:{
      }
    }
  },
  created() {
    this.load()
  },
  mounted() {

  },
  methods: {
    load(){
      this.form.account=this.user.account
      this.form.id=this.user.id
      request.post("/user/student/getKey",this.form).then(res=>{
        if(res.code==="200"){
          this.key=res.data
        }
      })
    }
  }
}
</script>

<style scoped></style>
