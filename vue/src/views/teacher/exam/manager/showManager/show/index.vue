<template>
  <div>
    <el-row style="margin-top: 2vh;margin-bottom: 2vh;text-align: center">
      <el-col :span="6">
        考试科目:{{this.form.course.name}}
      </el-col>
      <el-col :span="10">
        考试开始时间:{{this.form.examDate}}
      </el-col>
      <el-col :span="4">
        考试时长:{{this.form.totalTime}}分钟
      </el-col>
      <el-col :span="4">
        总分:{{this.form.totalScore}}分
      </el-col>
    </el-row>
    <el-row style="text-align: center">
      <el-col :span="6">
        课程编码:{{this.form.course.number}}
      </el-col>
      <el-col :span="8">
        考试类型:{{this.form.type}}
      </el-col>
      <el-col :span="8">
        出题人:{{this.form.teacher.name}}
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <div class="title">
          考试介绍
        </div>
        <div class="body">
          <div v-html="this.form.description" class="w-e-text"></div>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="title">
          考生须知
        </div>
        <div class="body">
          <div v-html="this.form.tips" class="w-e-text"></div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  props: {
    // 定义接收的参数，这里以 param 为例
    param: {
      type: String, // 参数类型，这里是字符串类型
      required: false, // 是否为必需参数，这里设为非必需
      default: '',// 参数的默认值
      form:[]
    }
  },
  data(){
    return{
      user:localStorage.getItem("user") ? (JSON).parse(localStorage.getItem("user")) : {},
      receivedData:"",
      form:{}
    }
  },
  created() {
    this.receivedData = this.param
    this.load()
  },
  mounted() {

  },
  methods: {
    load(){
      request.get("exam/examManage/selectById/"+this.receivedData).then(res=>{
        if (res.code === '200') {
          this.form=res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
  }
}
</script>


<style scoped>
.title{
  margin: 4vh 4% 0 4%;
  width: 92%;
  height: 8vh;
  background: #f3f5f8;
  border-radius: 10px;
  font-size: 26px;
  text-align: center;
  padding-top: 2vh;
}
.body{
  margin: 2vh 4% 0 4%;
  padding: 2vh 2% 2vh 2%;
  width: 92%;
  height: 44vh;
  background: #f1f4f8;
  overflow-y: scroll;
  border-radius: 10px
}
.body::-webkit-scrollbar{
  width:0;
}
</style>

