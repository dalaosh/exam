<template>
  <div style="background: #fcf8f3">
    <el-row>
      <el-col :span="14">
        <el-row>
          <div class="left-top">
            <div style="font-size: 26px;margin-bottom: 1vh">题目：</div>
            <el-input
                style="font-size: 26px"
                disabled="true"
                type="textarea"
                :autosize="{ minRows: 3, maxRows: 3}"
                placeholder=""
                v-model="table.title">
            </el-input>
          </div>
        </el-row>
        <el-row>
          <div class="left-bottom">
            <el-row>
              <el-col style="font-size: 24px" :span="16">
                语言：{{table.type}}
              </el-col>
              <el-col :span="8">
                <el-button disabled="true" style="width: 50%; background-color: #dbdbec; border-color: #030303;  color: #0a0101;font-size: 18px" @click="runCpp">运 行</el-button>
              </el-col>
            </el-row>
            <el-row>
              <div style="font-size: 24px">
                <label for="fontSize">字体大小：</label>
                <input type="range" min="10" max="80" v-model="fontSize" id="fontSize" />
                <span>{{ fontSize }}px</span>
              </div>
            </el-row>
            <el-row>
              <monaco-editor
                  disabled="true"
                  v-model="code"
                  :language="language"
                  :options="editorOptions"
                  style="margin-top:2vh;height: 44vh"
              ></monaco-editor>
            </el-row>
          </div>
        </el-row>
      </el-col>
      <el-col :span="10">
        <el-row>
          <div class="right-top">
            <div style="font-size: 26px;margin-bottom: 2vh">
              存档时间：{{table.time}}
            </div>
            <div style="font-size: 26px;margin-bottom: 1vh">
              运行结果：
            </div>
            <el-input
                style="font-size: 18px"
                disabled="true"
                type="textarea"
                :autosize="{ minRows: 17, maxRows: 17}"
                placeholder="运行结果"
                v-model="result">
            </el-input>
          </div>
        </el-row>
        <el-row>
          <div class="right-bottom">
            更上一层楼
          </div>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import MonacoEditor from "monaco-editor-vue";
import request from "@/utils/request";

export default {
  components: {
    MonacoEditor,
  },
  data() {
    return {
      receivedData:"",
      user:localStorage.getItem("user") ? (JSON).parse(localStorage.getItem("user")) : "",
      code: "",
      language:null,
      form:{},
      result:"",
      fontSize:"18",
      table:{}
    };
  },
  created() {
    this.receivedData = this.$route.params;
    this.load()
  },
  methods:{
    load(){
      request.get("exam/code/selectById/"+this.receivedData.id).then(res=>{
        if(res.code==="200"){
          this.table=res.data
          this.code=this.table.codeMessage
          this.result=this.table.runMessage
          this.language=this.table.type
        }
        else {
          this.$message.error(res.msg)
        }
      })
    },
    runCpp(){
      if(this.language===null){
        this.$message.error("请选择语言")
      }
      this.form={
        m:this.code,
        t:this.language
      }
      request.post('/exam/code/cpp', this.form).then(res => {
        this.result=res.data
      })
    },
    submit() {
      this.table.studentId = this.user.id
      this.table.type = this.language
      this.table.codeMessage = this.code
      this.table.runMessage = this.result
      request.post("/exam/code/add", this.table).then(res => {
        if (res.code === "200") {
          this.$message.success("添加成功")
          this.form = {}
          this.language = null
          this.code = ""
          this.result = ""
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
el-input {
  color: blue;
}

.left-top {
  margin: 2vh 1% 1vh 3%;
  padding: 1vh 2% 1vh 2%;
  height: 25vh;
  width: 96%;
  background: #dddee0;
  border-radius: 10px
}

.left-bottom {
  margin: 1vh 1% 0 3%;
  padding: 1vh 2% 1vh 2%;
  height: 58vh;
  width: 96%;
  background: #dddee0;
  border-radius: 10px
}

.right-top {
  margin: 2vh 3% 1vh 1%;
  padding: 1vh 2% 1vh 2%;
  height: 73vh;
  width: 96%;
  background: #dddee0;
  border-radius: 10px
}

.right-bottom {
  margin: 1vh 3% 0 1%;
  padding: 2vh 2% 1vh 2%;
  height: 10vh;
  width: 96%;
  background: #dddee0;
  border-radius: 10px;
  text-align: center;
  font-size: 4vh;
}
</style>
