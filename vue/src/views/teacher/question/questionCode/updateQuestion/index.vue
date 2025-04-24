<template>
  <div style="background: #fcf8f3">
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
          <div class="w-e-text-container" id='editorA' style="width: 98%;height: 46vh;z-index: 1"></div>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="middle-right">
          <div class="title">
            解析
          </div>
          <div class="w-e-text-container" id='editorB' style="width: 98%;height: 46vh"></div>
        </div>
      </el-col>
    </el-row>
    <el-row>
      <div class="bottom">
        <div style="font-size: 26px;margin-bottom: 1vh">
          答案编码(参考答案)：
        </div>
        <div>
          <el-row>
            <el-col :span="12">
              <div  class="bottom-left">
                <el-row>
                  <el-col style="font-size: 24px" :span="16">
                    <label for="fontSize">字体大小：</label>
                    <input type="range" min="10" max="80" v-model="fontSize" id="fontSize" />
                    <span>{{ fontSize }}px</span>
                  </el-col>
                  <el-col :span="8">
                    <el-button style="width: 50%; background-color: #dbdbec; border-color: #030303;  color: #0a0101;font-size: 18px" @click="runCpp">运 行</el-button>
                  </el-col>
                </el-row>
                <el-row>
                  <monaco-editor
                      v-model="table.answer"
                      :language="language"
                      :options="editorOptions"
                      style="margin-top:2vh;height: 50vh;"
                  ></monaco-editor>
                </el-row>
              </div>
            </el-col>
            <el-col :span="12">
              <div class="bottom-right">
                <div style="font-size: 26px;margin-bottom: 1vh">
                  运行结果：
                </div>
                <el-input
                    style="font-size: 18px"
                    disabled
                    type="textarea"
                    :autosize="{ minRows: 12, maxRows: 12}"
                    placeholder="运行结果"
                    v-model="result">
                </el-input>
                <div style="margin-top: 3vh;text-align: center">
                  <el-button type="primary" round style="width: 40%;font-size: 18px" @click="submit">修 改</el-button>
                </div>
              </div>
            </el-col>
          </el-row>
        </div>
      </div>
    </el-row>
  </div>
</template>

<script>
import MonacoEditor from "monaco-editor-vue";
import request from "@/utils/request";
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
  components: {
    MonacoEditor,
  },
  data() {
    return {
      user:localStorage.getItem("user") ? (JSON).parse(localStorage.getItem("user")) : "",
      code: "",
      language:"c",
      form:{},
      result:"",
      fontSize:"18",
      table:{},
      course:{},
      receivedData:""
    };
  },
  created() {
    this.receivedData = this.$route.params;
    this.findCourse()
    this.load()
  },
  methods:{
    load(){
      editorA=""
      editorB=""
      request.get("/exam/questionCode/selectById/"+this.receivedData.id).then(res=>{
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
          this.course = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    runCpp() {
      if (this.language === null) {
        this.$message.error("请选择语言")
      }
      this.form = {
        m: this.table.answer,
        t: "c"
      }
      request.post('/exam/code/cpp', this.form).then(res => {
        this.result = res.data
      })
    },
    submit() {
      this.table.question = editorA.txt.html()
      this.table.analysis = editorB.txt.html()
      this.table.studentId = this.user.id
      this.table.type = "c"
      request.put("/exam/questionCode/update", this.table).then(res => {
        if (res.code === "200") {
          this.$message.success("修改成功")
          this.form = {}
        } else {
          this.$message.error(res.msg)
        }
      })
    }
  },
  computed: {
    editorOptions() {
      return {
        fontSize: this.fontSize,
        // 你可以在这里添加更多的配置选项，如主题、minimap等
      };
    },
  }
};
</script>

<style scoped>
.title {
  font-family: 'STXingkai', '华文行楷', cursive;
  font-size: 32px;
  padding-bottom: 3vh;
}

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
  height: 56vh;
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

.bottom {
  margin: 2vh 1% 1vh 1%;
  padding-left: 2%;
  padding-top: 1vh;
  height: 70vh;
  width: 98%;
  background: #dddee0;
  border-radius: 10px
}

.bottom-left {
  margin: 1vh 0 1vh 0;
  padding: 1vh 2% 1vh 2%;
  height: 60vh;
  width: 98%;
  background: #81a4f5;
  border-radius: 10px
}

.bottom-right {
  margin: 1vh 0 1vh 0;
  padding: 1vh 2% 1vh 2%;
  height: 60vh;
  width: 98%;
  background: #81a4f5;
  border-radius: 10px
}
</style>
