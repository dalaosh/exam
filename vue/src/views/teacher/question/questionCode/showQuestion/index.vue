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
              答案详解：
            </div>
            <div class="analysis">
              <div v-html="this.table.analysis" class="w-e-text"></div>
            </div>
          </div>
        </el-row>
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
                    :autosize="{ minRows: 14, maxRows: 14}"
                    placeholder="运行结果"
                    v-model="result">
                </el-input>
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
    this.load()
  },
  methods:{
    load(){
      request.get("/exam/questionCode/selectById/"+this.receivedData.id).then(res=>{
        if (res.code === "200") {
          this.table = res.data
          this.course=this.table.course
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
  background: #f2fdfd;
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
  height: 25vh;
  background: #eef3fa;
  overflow-y: scroll;
  border-radius: 10px
}
.question::-webkit-scrollbar{
  width:0;
}
.analysis{
  padding: 2vh 2% 2vh 2%;
  height: 40vh;
  background: #f3e7f8;
  overflow-y: scroll;
  border-radius: 10px
}
.analysis::-webkit-scrollbar{
  width:0;
}

.bottom{
  margin: 2vh 1% 1vh 1%;
  padding-left: 2%;
  padding-top: 1vh;
  height: 70vh;
  width: 98%;
  background: #dddee0;
  border-radius: 10px
}
.bottom-left{
  margin: 1vh 0 1vh 0;
  padding: 1vh 2% 1vh 2%;
  height: 60vh;
  width: 98%;
  background: #81a4f5;
  border-radius: 10px
}
.bottom-right{
  margin: 1vh 0 1vh 0;
  padding: 1vh 2% 1vh 2%;
  height: 60vh;
  width: 98%;
  background: #81a4f5;
  border-radius: 10px
}
:deep(.el-rate__icon) {
  font-size: 24px;  /* 调整图标尺寸 */
}
</style>

