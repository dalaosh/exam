<template>
  <div>
    <el-row>
      <div class="top">
        <div class="title">
          题目信息
        </div>
        <rl-row style="text-align: center">
          <el-col :span="6">
            请选择课程:
            <el-select style="width: 50%;z-index: 2" v-model="table.courseId" clearable filterable placeholder="请选择">
              <el-option
                  v-for="item in course"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
              </el-option>
            </el-select>
          </el-col>
          <el-col :span="6">
            请输入分值:
            <el-input style="width: 50%" clearable v-model="table.score" placeholder="请输入分值"></el-input>
          </el-col>
          <el-col :span="6">
            <el-row>
              <el-col :span="8" style="text-align: right">
                等级:
              </el-col>
              <el-col :span="16">
                <el-rate
                    v-model="table.level"
                    :colors="['#fdea5a', '#32ef17', '#8a2be2']"
                    :void-color="'#070000'"
                ></el-rate>
              </el-col>
            </el-row>
          </el-col>
          <el-col :span="6">
            请输入章节:
            <el-input style="width: 50%" clearable v-model="table.section" placeholder="请输入章节"></el-input>
          </el-col>
        </rl-row>
      </div>
    </el-row>
    <el-row>
      <el-col :span="12">
        <div class="middle-left">
          <div class="title">
            问题
          </div>
          <div class="w-e-text-container" id='editorA' style="width: 98%;height: 40vh;z-index: 2"></div>
        </div>
        <div class="bottom-left">
          <div style="font-size: 26px;;margin-bottom: 1vh">
            答案：
          </div>
          <el-input
              style="font-size: 18px"
              type="textarea"
              :autosize="{ minRows: 2, maxRows: 2}"
              placeholder="答案"
              v-model="table.answer">
          </el-input>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="middle-right">
          <div class="title">
            解析
          </div>
          <div class="w-e-text-container" id='editorB' style="width: 98%;height: 40vh"></div>
        </div>
        <div class="bottom-right">
          <el-button type="primary" round style="width: 40%;font-size: 18px;margin-top: 2vh" @click="submit">保 存</el-button>
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
      form:{
        number:""
      },
      table:{},
      course:{},
      receivedData:"",
    }
  },
  created() {
    this.receivedData = this.$route.params;
    this.findCourse()
    this.load()
  },
  mounted() {

  },
  methods: {
    load(){
      editorA=""
      editorB=""
      request.get("/exam/questionFill/selectById/"+this.receivedData.id).then(res=>{
        if (res.code === "200") {
          this.table = res.data
          initWangEditorA(this.table.question)
          initWangEditorB(this.table.analysis)
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    findCourse(){
      let params;
      params= {
        teacherId:this.user.id
      }
      request.get('/exam/course/selectAll', {
        params:params
      }).then(res => {
        if (res.code === '200') {
          this.course=res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    submit() {
      this.table.question = editorA.txt.html()
      this.table.analysis = editorB.txt.html()
      request.put("/exam/questionFill/update", this.table).then(res => {
        if (res.code === "200") {
          this.$message.success("修改成功")
          this.form = {}
        } else {
          this.$message.error(res.msg)
        }
      })
    }
  }
}
</script>


<style scoped>
.top {
  margin: 2vh 1% 1vh 1%;
  padding-left: 2%;
  padding-top: 1vh;
  height: 15vh;
  width: 98%;
  background: #dddee0;
  border-radius: 10px
}

.middle-left {
  margin: 1vh 1% 1vh 2%;
  padding-left: 2%;
  padding-top: 1vh;
  height: 50vh;
  width: 96%;
  background: #dddee0;
  border-radius: 10px
}

.middle-right {
  margin: 1vh 1% 1vh 2%;
  padding-left: 2%;
  padding-top: 1vh;
  height: 56vh;
  width: 96%;
  background: #dddee0;
  border-radius: 10px
}

.bottom-left {
  margin: 1vh 1% 0 2%;
  padding: 1vh 1% 0 2%;
  height: 16vh;
  width: 96%;
  background: #dddee0;
  border-radius: 10px
}

.bottom-right {
  margin: 1vh 1% 0 2%;
  padding: 1vh 1% 0 2%;
  height: 10vh;
  width: 96%;
  text-align: center;
  background: #dddee0;
  border-radius: 10px
}

.title {
  font-family: 'STXingkai', '华文行楷', cursive;
  font-size: 32px;
  padding-bottom: 3vh;
}
</style>

