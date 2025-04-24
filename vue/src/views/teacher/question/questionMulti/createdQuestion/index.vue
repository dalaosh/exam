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
      <div class="question">
        <el-row>
          <el-col :span="12">
            <div class="title">
              问题
            </div>
            <div class="w-e-text-container" id='editorA' style="width: 98%;height: 35vh;z-index: 1"></div>
          </el-col>
          <el-col :span="12">
            <div style="margin: 8vh 2% 2vh 0;padding: 2vh 2% 2vh 2%;width: 92%;height: 34vh;">
              <el-row>
                <el-col :span="6">
                  <div style="font-size: 24px;text-align: center;margin-top: 1vh">
                    选项A
                  </div>
                </el-col>
                <el-col :span="18">
                  <el-input
                      style="font-size: 18px;width: 100%"
                      type="textarea"
                      :autosize="{ minRows: 1, maxRows: 1}"
                      placeholder="答案"
                      v-model="table.answerA">
                  </el-input>
                </el-col>
              </el-row>
              <el-row style="margin-top: 3vh">
                <el-col :span="6">
                  <div style="font-size: 24px;text-align: center;margin-top: 1vh">
                    选项B
                  </div>
                </el-col>
                <el-col :span="18">
                  <el-input
                      style="font-size: 18px;width: 100%"
                      type="textarea"
                      :autosize="{ minRows: 1, maxRows: 1}"
                      placeholder="答案"
                      v-model="table.answerB">
                  </el-input>
                </el-col>
              </el-row>
              <el-row style="margin-top: 3vh">
                <el-col :span="6">
                  <div style="font-size: 24px;text-align: center;margin-top: 1vh">
                    选项C
                  </div>
                </el-col>
                <el-col :span="18">
                  <el-input
                      style="font-size: 18px;width: 100%"
                      type="textarea"
                      :autosize="{ minRows: 1, maxRows: 1}"
                      placeholder="答案"
                      v-model="table.answerC">
                  </el-input>
                </el-col>
              </el-row>
              <el-row style="margin-top: 3vh">
                <el-col :span="6">
                  <div style="font-size: 24px;text-align: center;margin-top: 1vh">
                    选项D
                  </div>
                </el-col>
                <el-col :span="18">
                  <el-input
                      style="font-size: 18px;width: 100%"
                      type="textarea"
                      :autosize="{ minRows: 1, maxRows: 1}"
                      placeholder="答案"
                      v-model="table.answerD">
                  </el-input>
                </el-col>
              </el-row>
            </div>
          </el-col>
        </el-row>
      </div>
    </el-row>
    <el-row>
      <el-col :span="12">
        <div class="answer">
          <div style="font-size: 26px;;margin-bottom: 1vh">
            题型：
            <el-select style="width: 60%" v-model="table.questionType" clearable filterable placeholder="请选择">
              <el-option
                  v-for="item in type"
                  :key="item.data"
                  :label="item.data"
                  :value="item.data">
              </el-option>
            </el-select>
          </div>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="answer">
          <div style="font-size: 26px;">
            <el-row>
              <el-col :span="4" style="margin-top: 1vh">
                答案：
              </el-col>
              <el-col :span="4" style="margin-top: 1vh">
                {{this.table.rightAnswer?this.table.rightAnswer:"请作答"}}
              </el-col>
              <el-col :span="16">
                <el-checkbox-group
                    @change="multiChange"
                    style="margin-top: 2vh"
                    v-model="multiAnswer">
                  <el-checkbox v-for="m in multi" :label="m" :key="m"><a style="font-size: 28px">{{m}}</a></el-checkbox>
                </el-checkbox-group>
              </el-col>
            </el-row>
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row>
      <div class="analysis">
        <div class="title">
          解析
        </div>
        <div class="w-e-text-container" id='editorB' style="width: 98%;height: 42vh"></div>
      </div>
    </el-row>
    <el-row>
      <div class="bottom">
        <el-button type="primary" round style="width: 60%;font-size: 24px;margin-top: 1vh" @click="submit">保 存</el-button>
      </div>
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
      type:[
        {data:"单选"},
        {data:"多选"}
      ],
      multi:['A','B','C','D'],
      multiAnswer:[]
    }
  },
  created() {
    this.findCourse()
    this.load()
  },
  mounted() {

  },
  methods: {
    load(){
      editorA=""
      initWangEditorA("")
      editorB=""
      initWangEditorB("")
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
    multiChange(){
      this.table.rightAnswer=""
      const multiAnswer = this.multiAnswer.sort();
      // 将排序后的结果拼接成字符串
      this.table.rightAnswer = multiAnswer.join('');
    },

    submit(){
      this.table.question=editorA.txt.html()
      this.table.analysis=editorB.txt.html()
      request.post("/exam/questionMulti/add", this.table).then(res => {
        if (res.code === "200") {
          this.$message.success("添加成功")
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
.top{
  margin: 2vh 1% 1vh 1%;
  padding-left: 2%;
  padding-top: 1vh;
  height: 15vh;
  width: 98%;
  background: #dddee0;
  border-radius: 10px
}
.question{
  margin: 1vh 1% 1vh 1%;
  padding-left: 2%;
  padding-top: 1vh;
  height: 47vh;
  width: 98%;
  background: #dddee0;
  border-radius: 10px
}
.analysis{
  margin: 1vh 1% 1vh 1%;
  padding-left: 2%;
  padding-top: 1vh;
  height: 54vh;
  width: 98%;
  background: #dddee0;
  border-radius: 10px
}
.answer{
  margin: 1vh 1% 0 2%;
  padding: 1vh 1% 0 2%;
  height: 8vh;
  width: 96%;
  text-align: center;
  background: #dddee0;
  border-radius: 10px
}
.bottom{
  margin: 1vh 1% 0 1%;
  padding: 1vh 1% 0 2%;
  height: 10vh;
  width: 98%;
  text-align: center;
  background: #dddee0;
  border-radius: 10px;
}
.title{
  font-family: 'STXingkai', '华文行楷', cursive;
  font-size: 32px;
  padding-bottom: 3vh;
}
</style>

