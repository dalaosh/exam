<template>
  <div>
    <el-row>
      <el-col :span="8">
        <el-row>
          <div class="left">
            <div class="title">
              操作者信息
            </div>
            <div style="text-align: center">
              <el-image
                style="width: 50%; height: 30vh"
                :src=this.user.photo
                fit="fit">
              </el-image>
            </div>
            <div class="message">姓名: {{this.user.name}}</div>
            <div class="message">性别: {{this.user.sex}}</div>
            <div class="message">角色: {{this.user.role}}</div>
            <div class="message">电话: {{this.user.phone}}</div>
            <div class="message">账号: {{this.user.account}}</div>
            <div class="message">邮箱: {{this.user.email}}</div>
          </div>
        </el-row>
      </el-col>
      <el-col :span="16">
        <el-row>
          <div class="right-top">
            <el-row>
              <el-col :span="6">
                <div style="margin: 15px; text-align: center;">
                  <el-upload
                      class="avatar-uploader"
                      action="http://localhost:9998/files/course/upload"
                      list-type="picture-card"
                      :show-file-list="false"
                      :on-success="handleAvatarSuccess">
                    <img style="width: 100%;height: 24vh;" v-if="imageUrl" :src="imageUrl" class="course">
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                  </el-upload>
                </div>
              </el-col>
              <el-col :span="18">
                <div style="margin-top: 2vh">
                  课程名称：{{form.name}}
<!--                  <el-input-->
<!--                      style="font-size: 16px;width: 400px"-->
<!--                      placeholder="请输入课程名称"-->
<!--                      v-model="form.name">-->
<!--                  </el-input>-->
                </div>
<!--                <div class="w-e-menu" style="margin-top: 2vh">-->
<!--                  日期范围:-->
<!--                  <el-date-picker-->
<!--                      style="margin-left: 2%"-->
<!--                      @change="times"-->
<!--                      v-model="value1"-->
<!--                      type="datetimerange"-->
<!--                      range-separator="至"-->
<!--                      start-placeholder="开始日期"-->
<!--                      end-placeholder="结束日期">-->
<!--                  </el-date-picker>-->
<!--                </div>-->
                <div class="w-e-menu" style="margin-top: 2vh">
                  开始时间:{{form.beginTime}}

                </div>
                <div class="w-e-menu" style="margin-top: 2vh">
                  结束时间:{{form.endTime}}

                </div>
                <div style="margin-top: 2vh">
                  课程编码：{{form.number}}
<!--                  <el-input-->
<!--                      style="font-size: 16px;width: 250px;margin-right: 50px"-->
<!--                      placeholder="请点击右边按钮"-->
<!--                      disabled-->
<!--                      v-model="form.number">-->
<!--                  </el-input>-->
<!--                  <el-button type="primary" style="width: 100px;font-size: 18px" @click="random">点击生成</el-button>-->
                </div>
              </el-col>
            </el-row>
            <div style="margin: 6vh 0 2vh 0">
              请输入课程简介
            </div>
            <div class="w-e-text-container" id='editor' style="width: 98%;height: 35vh"></div>
          </div>
        </el-row>
        <div class="right-bottom">
          <el-button type="primary" round style="width: 40%;font-size: 18px" @click="submit">提 交</el-button>
        </div>
      </el-col>
    </el-row>
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
      // 假设 this.form.violationTime 是日期字符串或者时间戳
      const date = new Date(dataTime);
// 提取年、月、日、时、分、秒信息
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, '0'); // 月份从 0 开始，所以要加 1
      const day = String(date.getDate()).padStart(2, '0');
      const hours = String(date.getHours()).padStart(2, '0');
      const minutes = String(date.getMinutes()).padStart(2, '0');
      const seconds = String(date.getSeconds()).padStart(2, '0');
// 按照 YYYY-MM-DD HH:mm:ss 格式拼接字符串
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
.left{
  margin: 2vh 3% 1vh 3%;
  padding-left: 2%;
  padding-top: 1vh;
  height: 84vh;
  width: 94%;
  background: #dddee0;
  border-radius: 10px
}
.right-top{
  margin: 2vh 3% 1vh 3%;
  padding-left: 2%;
  padding-top: 1vh;
  height: 72vh;
  width: 94%;
  background: #dddee0;
  border-radius: 10px
}
.right-bottom{
  margin: 1vh 3% 0 3%;
  padding-left: 2%;
  padding-top: 3vh;
  height: 10vh;
  width: 94%;
  background: #dce1ec;
  border-radius: 10px;
  text-align: center;
}
.message{
  font-size: 24px;
  margin-bottom: 3vh;
  margin-top: 3vh;
  margin-left: 4%;
}
.title{
  font-family: 'STXingkai', '华文行楷', cursive;
  font-size: 32px;
  padding-bottom: 3vh;
}
.w-e-menu {
  z-index: 2 !important;
}
.w-e-text-container {
  z-index: 1 !important;
}
</style>

