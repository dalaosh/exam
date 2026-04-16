<template>
  <div class="teacher-page">
    <section class="teacher-page-head">
      <div>
        <h2 class="teacher-page-head__title">课程修改</h2>
      </div>
    </section>

    <div class="teacher-editor-layout">
      <aside class="teacher-panel teacher-editor-sidebar">
        <h3 class="teacher-panel__title">操作员信息</h3>
        <div class="teacher-editor-meta">
          <el-image class="teacher-editor-profile" :src="user.photo" fit="cover"></el-image>
          <div class="teacher-editor-profile__list">
            <div><strong>姓名：</strong>{{ user.name }}</div>
            <div><strong>性别：</strong>{{ user.sex }}</div>
            <div><strong>角色：</strong>{{ user.role }}</div>
            <div><strong>电话：</strong>{{ user.phone }}</div>
            <div><strong>账号：</strong>{{ user.account }}</div>
            <div><strong>邮箱：</strong>{{ user.email }}</div>
          </div>
        </div>
      </aside>

      <section class="teacher-panel teacher-editor-main">
        <div class="teacher-editor-main__stack">
          <div class="teacher-editor-upload">
            <div class="teacher-editor-form-item">
              <span class="teacher-editor-form-item__label">课程封面</span>
              <el-upload
                class="avatar-uploader"
                action="http://localhost:9998/files/course/upload"
                list-type="picture-card"
                :show-file-list="false"
                :on-success="handleAvatarSuccess">
                <img class="teacher-editor-upload__preview" v-if="imageUrl" :src="imageUrl" alt="">
                <div v-else class="teacher-empty" style="min-height: 220px;">
                  <i class="el-icon-plus"></i>
                </div>
              </el-upload>
            </div>

            <div class="teacher-editor-form-grid">
              <div class="teacher-editor-form-item">
                <span class="teacher-editor-form-item__label">课程名称</span>
                <div>{{ form.name }}</div>
              </div>
              <div class="teacher-editor-form-item">
                <span class="teacher-editor-form-item__label">课程编号</span>
                <div>{{ form.number }}</div>
              </div>
              <div class="teacher-editor-form-item">
                <span class="teacher-editor-form-item__label">开始时间</span>
                <div>{{ form.beginTime }}</div>
              </div>
              <div class="teacher-editor-form-item">
                <span class="teacher-editor-form-item__label">结束时间</span>
                <div>{{ form.endTime }}</div>
              </div>
            </div>
          </div>

          <div class="teacher-panel teacher-panel--padded">
            <div class="teacher-panel__head">
              <div>
                <h3 class="teacher-panel__title">课程简介</h3>
              </div>
            </div>
            <div class="w-e-text-container" id="editor" style="width: 100%; height: 380px"></div>
          </div>

          <div class="teacher-editor-actions">
            <el-button type="primary" @click="submit">提交</el-button>
          </div>
        </div>
      </section>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request"
import E from "wangeditor";
let editor
function initWangEditor(content){ setTimeout(() => {
  if(!editor){
    editor=new E('#editor')
    editor.config.placeholder='请输入内容'
    editor.config.uploadFileName='file'
    editor.config.uploadImgServer='http://localhost:9998/files/wang/upload'
    editor.create()
  }
  editor.txt.html(content)
},0)
}
export default {
  data(){
    return{
      user:localStorage.getItem("user") ? (JSON).parse(localStorage.getItem("user")) : {},
      form:{
        number:""
      },
      tableData: [],
      value1:[],
      imageUrl: '',
      receivedData:{}
    }
  },
  created() {
    this.receivedData = this.$route.params
    this.load()
  },
  mounted() {

  },
  methods: {
    load(){
      request.get("exam/course/selectById/"+this.receivedData.id).then(res=>{
        if (res.code === '200') {
          this.form=res.data
          editor=""
          initWangEditor(this.form.message)
          this.imageUrl=this.form.photo
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    times(){
      this.form.beginTime=this.time(this.value1[0])
      this.form.endTime=this.time(this.value1[1])
    },
    time(dataTime){
      const date = new Date(dataTime);
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, '0');
      const day = String(date.getDate()).padStart(2, '0');
      const hours = String(date.getHours()).padStart(2, '0');
      const minutes = String(date.getMinutes()).padStart(2, '0');
      const seconds = String(date.getSeconds()).padStart(2, '0');
      return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
    },
    handleAvatarSuccess(res){
      this.imageUrl=res.data
      this.form.photo=res.data
    },
    random(){
      request.get("exam/course/random").then(res => {
        if (res.code === '200') {
          this.form.number=res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    submit(){
      this.form.teacherId=this.user.id
      this.form.message=editor.txt.html()
      request.put("exam/course/update",this.form).then(res=>{
        if (res.code === '200') {
          this.$message.success("修改成功")
        } else {
          this.$message.error(res.msg)
        }
      })
    }
  }
}
</script>

<style scoped>
:deep(.w-e-text-container) {
  border: 1px solid #d1d5db;
  border-radius: 6px;
  overflow: hidden;
  background: #fff;
}

:deep(.w-e-toolbar) {
  border: none !important;
  border-bottom: 1px solid #e5e7eb !important;
  background: #f9fafb;
}

:deep(.w-e-text) {
  padding: 16px;
  line-height: 1.7;
}

:deep(.avatar-uploader .el-upload) {
  width: 100%;
  border: none;
}
</style>
