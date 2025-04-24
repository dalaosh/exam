<template>
  <div>
    <el-card shadow="never" style="height: 82vh;background-color: rgb(244,248,247);color: #030000;border-radius:10px;margin: 1vh 20px 1vh 20px">
      <div>
        <el-row :gutter="20">
          <el-col :span="6">
            <div style="height: 44vh">
              <img
                  style="width: 80%; height: 40vh;margin-left: 10%;margin-top: 2vh;margin-bottom: 2vh"
                  :src="myInform.photo" />
            </div>
            <div style="height: 30vh;margin-top: 3vh">
              请上传照片进行更改
              <div style="margin: 15px; text-align: center;">
                <el-upload
                    class="avatar-uploader"
                    action="http://localhost:9998/files/avatar/upload"
                    list-type="picture-card"
                    :show-file-list="false"
                    :on-success="handleAvatarSuccess">
                  <img style="width: 100%;height: 24vh;" v-if="imageUrl" :src="imageUrl" class="avatar">
                  <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                </el-upload>
              </div>
            </div>
          </el-col>
          <el-col :span="8">
            <div style="height: 43vh;padding-top: 1vh;">
              <el-descriptions :labelStyle="{'font-size':'18px'}" :contentStyle="{'font-size':'18px'}" :column=1 title="基本信息">
                <el-descriptions-item label="账号id">
                  <el-tag style="font-size: 18px">{{myInform.id}}</el-tag>
                </el-descriptions-item>
                <el-descriptions-item label="角色">
                  <el-tag style="font-size: 18px" type="danger">{{ myInform.role }}</el-tag>
                </el-descriptions-item>
                <el-descriptions-item label="账号">
                  <el-tag style="font-size: 18px" type="warning">{{ myInform.account }}</el-tag>
                </el-descriptions-item>
                <el-descriptions-item label="姓名">
                  <el-tag style="font-size: 18px" type="success">{{myInform.name}}</el-tag>
                </el-descriptions-item>
                <el-descriptions-item label="性别">
                  <el-tag style="font-size: 18px" type="success">{{myInform.sex}}</el-tag>
                </el-descriptions-item>
                <el-descriptions-item label="电话">
                  <el-tag style="font-size: 18px" type="success">{{myInform.phone}}</el-tag>
                </el-descriptions-item>
                <el-descriptions-item label="邮箱地址">
                  <el-tag style="font-size: 18px" type="success">{{myInform.email}}</el-tag>
                </el-descriptions-item>
              </el-descriptions>
            </div>
            <div style="margin-bottom: 1vh">用户信息修改</div>
            <div style="height: 26vh;padding-top: 1vh;padding-bottom: 1vh; background: #fdfcfc">
              <el-form :model="form" ref="formRef">
                <el-form-item label="姓名" label-width="25%" prop="name">
                  <el-input :readonly="setting" v-model="form.name" style="width: 85%"></el-input>
                </el-form-item>
                <el-form-item label="性别" label-width="25%" prop="sex">
                  <el-input :readonly="setting" v-model="form.sex" autocomplete="off" style="width: 85%"></el-input>
                </el-form-item>
                <el-form-item label="电子邮件" label-width="25%" prop="email">
                  <el-input :readonly="setting" v-model="form.email" autocomplete="off" style="width: 85%"></el-input>
                </el-form-item>
                <el-form-item label="电话" label-width="25%" prop="phone">
                  <el-input :readonly="setting" v-model="form.phone" autocomplete="off" style="width: 85%"></el-input>
                </el-form-item>
              </el-form>
            </div>
            <div style="text-align: center;margin-top: 1vh">
              <el-button type="primary" @click="cancel()">取 消</el-button>
              <el-button type="primary" @click="set()">编 辑</el-button>
              <el-button type="primary" @click="update()">确 定</el-button>
            </div>
          </el-col>
          <el-col :span="10">
            <div style="height: 77vh;">
              <el-row>
                <div>
                  加密的密码
                </div>
                <el-input
                    style="font-size: 20px;width: 90%;margin: 2vh 2%"
                    type="textarea"
                    :readonly="true"
                    :autosize="{ minRows: 4, maxRows: 4}"
                    placeholder=""
                    v-model="user.password">
                </el-input>
              </el-row>
              <el-row>
                <div>
                  密码摘要
                </div>
                <el-input
                    style="font-size: 20px;width: 90%;margin: 2vh 2%"
                    type="textarea"
                    :readonly="true"
                    :autosize="{ minRows: 2, maxRows: 2}"
                    placeholder=""
                    v-model="user.keySm3">
                </el-input>
              </el-row>
              <el-row>
                <div style="padding: 24px;background: #f0f8ff;border-radius: 8px;box-shadow: 0 2px 8px rgba(28, 136, 229, 0.12);height: 40vh;display: flex;flex-direction: column;gap: 20px;">
                  <!-- 头像上传模块 -->
                  <div style="background: white;padding: 16px;border-radius: 6px;box-shadow: 0 1px 4px rgba(0, 0, 0, 0.05);">
                    <div style="display: flex;align-items: center;gap: 8px;margin-bottom: 12px; color: #1890ff;font-size: 18px;">
                      <i class="el-icon-upload" style="font-size: 20px"></i>
                      <strong>头像更改操作</strong>
                    </div>
                    <div style="display: flex;flex-direction: column;gap: 8px;color: #606266;padding-left: 28px; ">
                      <div style="display: flex; align-items: center; gap: 8px">
                        <span style="width: 20px;height: 20px;background: #1890ff;color: white;border-radius: 50%;text-align: center;line-height: 20px;">1</span>点击
                        <span style="display: inline-flex;width: 24px;height: 24px;background: #1890ff;color: white;border-radius: 4px;align-items: center;justify-content: center;">+</span>进行图片上传
                      </div>
                      <div style="display: flex; align-items: center; gap: 8px">
                        <span style="width: 20px;height: 20px;background: #1890ff;color: white;border-radius: 50%;text-align: center; line-height: 20px;">2</span>
                        <span>支持 JPG/PNG 格式（最大 2MB）</span>
                      </div>
                    </div>
                  </div>

                  <!-- 信息编辑模块 -->
                  <div style="background: white;padding: 16px; border-radius: 6px; box-shadow: 0 1px 4px rgba(0, 0, 0, 0.05);">
                    <div style=" display: flex; align-items: center; gap: 8px; margin-bottom: 12px;color: #52c41a; font-size: 18px;">
                      <i class="el-icon-edit" style="font-size: 20px"></i>
                      <strong>信息更改操作</strong>
                    </div>
                    <div style="display: flex;flex-direction: column; gap: 8px; color: #606266; padding-left: 28px; ">
                      <div style="display: flex; align-items: center; gap: 8px">
                        <span style="width: 20px;height: 20px;background: #52c41a;color: white;border-radius: 50%; text-align: center; line-height: 20px; ">1</span>点击
                        <span style="padding: 4px 12px; background: #f6ffed; border: 1px solid #b7eb8f; border-radius: 4px; color: #52c41a; ">编辑按钮</span>进入修改模式
                      </div>
                      <div style="display: flex; align-items: center; gap: 8px">
                        <span style=" width: 20px;height: 20px;background: #52c41a;color: white;border-radius: 50%;text-align: center;line-height: 20px;">2</span>点击
                        <span style="padding: 4px 12px; background: #f6ffed;border: 1px solid #b7eb8f; border-radius: 4px;color: #52c41a; ">确认提交</span>
                        保存修改内容
                      </div>
                    </div>
                  </div>
                </div>
              </el-row>
            </div>
          </el-col>
        </el-row>
      </div>
    </el-card>
  </div>
</template>

<script>
import request from "@/utils/request"
export default {
  data() {
    return{
      user:localStorage.getItem("user") ? (JSON).parse(localStorage.getItem("user")) : {},
      myInform:{},
      imageUrl: '',
      form:{},
      setting:true,
      labelStyle:{

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
      request.get("user/admin/selectById/"+this.user.id).then(res=>{
        if(res.code==="200"){
          this.myInform=res.data
          this.form=this.myInform
        }
      })
    },
    handleAvatarSuccess(res){
      this.imageUrl=res.data
      let table
      table = {
        id: this.myInform.id,
        photo: this.imageUrl
      };
      request.put("/user/admin/update/inform",table).then(res=>{
        if(res.code==="200"){
          this.$message.success("头像修改成功")
          this.load()
        }
      })
    },
    update(){
      this.form.id=this.myInform.id
      request.put("/user/admin/update/inform",this.form).then(res=>{
        if(res.code==="200"){
          this.$message.success("信息修改成功")
          this.load()
        }
      })
    },
    set(){
      this.form=this.myInform
      this.setting=false
    },
    cancel(){
      this.form=this.myInform
      this.setting=true
    }
  }
}
</script>

<style scoped>

</style>