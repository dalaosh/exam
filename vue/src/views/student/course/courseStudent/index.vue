<template>
  <div>
    <el-row>
      <el-col :span="8">
        <el-row>
          <div class="left-top">
            <div class="title">
              教师信息
            </div>
            <el-row :gutter="20">
              <el-col :span="10">
                <el-image
                    style="width: 100%; height: 24vh"
                    :src=this.receivedData.teacher.photo
                    fit="fit"></el-image>
              </el-col>
              <el-col :span="14">
                <div class="message">姓名: {{this.receivedData.teacher.name}}</div>
                <div class="message">性别: {{this.receivedData.teacher.sex}}</div>
                <div class="message">角色: {{this.receivedData.teacher.role}}</div>
                <div class="message">电话: {{this.receivedData.teacher.phone}}</div>
              </el-col>
            </el-row>
            <div class="message">邮箱: {{this.receivedData.teacher.email}}</div>
          </div>
        </el-row>
        <el-row>
          <div class="left-bottom">
            <div class="title">
              学生信息
              <a v-if="this.receivedData.isAdd==='待审核'" style="background: #2758f6">待审核</a>
              <a v-if="this.receivedData.isAdd==='同意'" style="background: #0a980c">申请同意</a>
              <a v-if="this.receivedData.isAdd==='拒绝'" style="background: #da0727">申请拒绝</a>
            </div>
            <el-row :gutter="20">
              <el-col :span="10">
                <el-image
                    style="width: 100%; height: 24vh"
                    :src=this.receivedData.student.photo
                    fit="fit"></el-image>
              </el-col>
              <el-col :span="14">
                <div class="message">姓名: {{this.receivedData.student.name}}</div>
                <div class="message">性别: {{this.receivedData.student.sex}}</div>
                <div class="message">角色: {{this.receivedData.student.role}}</div>
                <div class="message">电话: {{this.receivedData.student.phone}}</div>
              </el-col>
            </el-row>
            <div class="message">邮箱: {{this.receivedData.student.email}}</div>
          </div>
        </el-row>
      </el-col>
      <el-col :span="16">
        <el-row>
          <div class="right-top">
            <el-row>
              <el-col :span="10">
                <div style="margin: 15px; text-align: center;">
                  <img style="width: 100%;height: 24vh;" :src="this.receivedData.course.photo" class="course">
                </div>
              </el-col>
              <el-col :span="14">
                <div style="margin-top: 2vh;font-size: 26px">
                  课程名称：{{this.receivedData.course.name}}
                </div>
                <div style="margin-top: 2vh;font-size: 26px">
                  开始时间：{{this.receivedData.course.beginTime}}
                </div>
                <div style="margin-top: 2vh;font-size: 26px">
                  结束时间：{{this.receivedData.course.endTime}}
                </div>
                <div style="margin-top: 2vh;font-size: 26px">
                  课程编码：{{this.receivedData.course.number}}
                </div>
              </el-col>
            </el-row>
            <div v-html="this.receivedData.course.mseeage" class="w-e-text"></div>
          </div>
        </el-row>
        <el-row>
          <div class="right-bottom">
            <div style="font-size: 26px">课程简介</div>
            <div v-html="this.receivedData.course.message" class="w-e-text text"></div>
          </div>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import request from "@/utils/request"

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
    this.receivedData = this.$route.params;
    this.load()
  },
  mounted() {

  },
  methods: {
    load(){
      request.get("exam/courseStudent/selectById/"+this.receivedData.id).then(res=>{
        if(res.code==="200"){
          this.receivedData=res.data
        }
      })
    }
  }
}
</script>


<style scoped>
.left-top{
  margin: 2vh 3% 1vh 3%;
  padding-left: 2%;
  padding-top: 1vh;
  height: 41vh;
  width: 94%;
  background: #dddee0;
  border-radius: 10px
}
.left-bottom{
  margin: 1vh 3% 0 3%;
  padding-left: 2%;
  padding-top: 1vh;
  height: 41vh;
  width: 94%;
  background: #dce1ec;
  border-radius: 10px
}
.right-top{
  margin: 2vh 3% 1vh 3%;
  padding-left: 2%;
  padding-top: 1vh;
  height: 30vh;
  width: 94%;
  background: #dddee0;
  border-radius: 10px
}
.right-bottom {
  margin: 1vh 3% 0 3%;
  padding-left: 2%;
  padding-top: 3vh;
  height: 52vh;
  width: 94%;
  background: #dce1ec;
  border-radius: 10px;
}
.text{
  margin-top: 2vh;
  width: 98%;
  height: 40vh;
  overflow-y: scroll;
  background: #cddef3;
}
.text::-webkit-scrollbar{
  width:0;
}
.message{
  font-size: 20px;
  margin-bottom: 2vh;
  margin-top: 2vh;
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

