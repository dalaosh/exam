<template>
  <div>
    <el-row>
      <el-col :span="8">
        <el-row>
          <div class="left">
            <div class="title" style="text-align: center">
              考试信息
            </div>
            <el-form label-position="top" :model="form">
              <el-form-item label="请选择课程:">
                <el-select disabled v-model="form.courseId" style="font-size: 20px; width: 100%;" clearable filterable placeholder="请选择">
                  <el-option
                      v-for="item in course"
                      :key="item.id"
                      style="font-size: 20px; width: 100%;"
                      :label="item.name"
                      :value="item.id"
                  ></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="考试开始时间">
                <el-date-picker
                    style="width: 100%"
                    v-model="value1"
                    type="datetime"
                    placeholder="选择日期时间">
                </el-date-picker>
              </el-form-item>
              <el-form-item label="持续时长">
                <el-input style="font-size: 18px" placeholder="请输入持续时长(分钟)" v-model="form.totalTime"></el-input>
              </el-form-item>
              <el-form-item label="总分">
                <el-input style="font-size: 18px" placeholder="请输入持续时长(分钟)" v-model="form.totalScore"></el-input>
              </el-form-item>
              <el-form-item label="考试类型">
                <el-input style="font-size: 18px" placeholder="请输入持续时长(分钟)" v-model="form.type"></el-input>
              </el-form-item>
              <el-form-item>
              </el-form-item>
            </el-form>
          </div>
        </el-row>
      </el-col>
      <el-col :span="16">
        <el-row>
          <el-col :span="12">
            <div class="right-top">
              <div class="title">
                考试介绍
              </div>
              <div class="w-e-text-container" id='editorA' style="width: 98%;height: 60vh"></div>
            </div>
          </el-col>
          <el-col :span="12">
            <div class="right-top">
              <div class="title">
                考生须知
              </div>
              <div class="w-e-text-container" id='editorB' style="width: 98%;height: 60vh"></div>
            </div>
          </el-col>
        </el-row>
        <div class="right-bottom">
          <el-button type="primary" round style="width: 40%;font-size: 18px" @click="submit">修 改</el-button>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import request from "@/utils/request"
import E from "wangeditor";
let editorA
function initWangEditorA(content){ setTimeout(() => {
  if(!editorA){
    editorA=new E('#editorA')
    editorA.config.placeholder='请输入内容'
    editorA.config.uploadFileName='file'
    editorA.config.uploadImgServer='http://localhost:9998/files/wang/upload'
    editorA.create()
  }
  editorA.txt.html(content)
},0)
}
let editorB
function initWangEditorB(content){ setTimeout(() => {
  if(!editorB){
    editorB=new E('#editorB')
    editorB.config.placeholder='请输入内容'
    editorB.config.uploadFileName='file'
    editorB.config.uploadImgServer='http://localhost:9998/files/wang/upload'
    editorB.create()
  }
  editorB.txt.html(content)
},0)
}
export default {
  data(){
    return{
      user:localStorage.getItem("user") ? (JSON).parse(localStorage.getItem("user")) : {},
      form:{},
      course:{},
      value1:"",
      receivedData:"",
    }
  },
  created() {
    this.receivedData = this.$route.params
    this.load()
    this.findCourse()
  },
  mounted() {

  },
  methods: {
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
    load(){
      request.get("exam/examManage/selectById/"+this.receivedData.id).then(res=>{
        if (res.code === '200') {
          this.form=res.data
          editorA=""
          initWangEditorA(this.form.description)
          editorB=""
          initWangEditorB(this.form.tips)
          this.value1=this.form.examDate
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    findCourse(){
      request.get("exam/course/selectAll").then(res=>{
        if (res.code === '200') {
          this.course=res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    submit(){
      this.form.description=editorA.txt.html()
      this.form.tips=editorB.txt.html()
      this.form.examDate=this.time(this.value1)
      request.put("exam/examManage/update",this.form).then(res=>{
        if (res.code === '200') {
          this.$message.success("修改成功")
          this.form={}
          this.load()
          this.value1=""
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
  padding: 2vh 3% 1vh 3% ;
  height: 84vh;
  width: 94%;
  background: #dddee0;
  border-radius: 10px
}
.right-top{
  margin: 2vh 3% 1vh 3%;
  padding: 2vh 3% 1vh 3% ;
  height: 72vh;
  width: 94%;
  background: #dddee0;
  border-radius: 10px
}
.right-bottom{
  margin: 1vh 1% 0 1%;
  padding-left: 2%;
  padding-top: 3vh;
  height: 10vh;
  width: 98%;
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
</style>

