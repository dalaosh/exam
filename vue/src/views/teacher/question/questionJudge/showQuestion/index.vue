<template>
  <div>
    <el-row>
      <el-col :span="8">
        <el-row>
          <div class="left-top">
            <div class="title">
              出题人信息
            </div>
            <el-row :gutter="20">
              <el-col :span="10">
                <el-image
                    style="width: 100%; height: 24vh"
                    :src=this.user.photo
                    fit="fit"></el-image>
              </el-col>
              <el-col :span="14">
                <div class="message">姓名: {{this.user.name}}</div>
                <div class="message">性别: {{this.user.sex}}</div>
                <div class="message">角色: {{this.user.role}}</div>
                <div class="message">电话: {{this.user.phone}}</div>
              </el-col>
            </el-row>
            <div class="message">邮箱: {{this.user.email}}</div>
          </div>
        </el-row>
        <el-row>
          <div class="left-bottom">
            <div class="title">
              题目信息
            </div>
            <el-row style="margin-bottom: 4vh;margin-top: 3vh">
              <el-col :span="8">
                课程名称:
              </el-col>
              <el-col :span="16">
                {{this.course.name}}
              </el-col>
            </el-row>
            <el-row style="margin-bottom: 4vh">
              <el-col :span="8">
                题目分值:
              </el-col>
              <el-col :span="16">
                {{this.table.score}}分
              </el-col>
            </el-row>
            <el-row style="margin-bottom: 4vh">
              <el-col :span="8">
                题目等级:
              </el-col>
              <el-col :span="16">
                <el-rate
                    v-model="this.table.level"
                    disabled
                    :colors="['#fdea5a', '#32ef17', '#8a2be2']"
                    :void-color="'#e0e0e0'"
                ></el-rate>
              </el-col>
            </el-row>
            <el-row style="margin-bottom: 4vh">
              <el-col :span="8">
                题目章节:
              </el-col>
              <el-col :span="16">
                {{this.table.section}}
              </el-col>
            </el-row>
          </div>
        </el-row>
      </el-col>
      <el-col :span="16">
        <el-row>
          <div class="right">
            <div style="font-size: 26px;;margin-top: 2vh;margin-bottom: 1vh">
              题目：
            </div>
            <div class="question">
              <div v-html="this.table.question" class="w-e-text"></div>
            </div>
            <div style="font-size: 26px;;margin-top: 2vh;margin-bottom: 1vh">
              答案：
            </div>
            <div class="answer">
              <div v-html="this.table.answer" class="w-e-text"></div>
            </div>
            <div style="font-size: 26px;;margin-top: 2vh;margin-bottom: 1vh">
              答案详解：
            </div>
            <div class="analysis">
              <div v-html="this.table.analysis" class="w-e-text"></div>
            </div>
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
      receivedData:"",
      form:{
        number:""
      },
      table:{},
      course:{}
    }
  },
  created() {
    this.receivedData = this.$route.params;
    this.load()
    this.findCourse()
  },
  mounted() {

  },
  methods: {
    load(){
      request.get("/exam/questionJudge/selectById/"+this.receivedData.id).then(res=>{
        if (res.code === "200") {
          this.table = res.data
          this.course=this.table.course
        } else {
          this.$message.error(res.msg)
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
  padding: 1vh 3% 1vh 3%;
  height: 41vh;
  width: 94%;
  font-size: 20px;
  background: #dce1ec;
  border-radius: 10px
}
.right{
  margin: 2vh 3% 1vh 3%;
  padding: 1vh 3% 1vh 3%;
  height: 84vh;
  width: 94%;
  font-size: 20px;
  background: #e8fcfc;
  border-radius: 10px
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
.question{
  padding: 2vh 2% 2vh 2%;
  height: 20vh;
  background: #eef3fa;
  overflow-y: scroll;
  border-radius: 10px
}
.question::-webkit-scrollbar{
  width:0;
}
.answer{
  padding: 2vh 2% 2vh 2%;
  height: 8vh;
  background: #eff2fc;
  overflow-y: scroll;
  border-radius: 10px
}
.answer::-webkit-scrollbar{
  width:0;
}
.analysis{
  padding: 2vh 2% 2vh 2%;
  height: 31vh;
  background: #f3e7f8;
  overflow-y: scroll;
  border-radius: 10px
}
.analysis::-webkit-scrollbar{
  width:0;
}
</style>

