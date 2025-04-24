<template>
  <div>
    <div>
      <el-row class="heads">
        <el-col :span="6">
          <div style="text-align: center">
            <el-button type="text" @click="addQuestion()" size="small" style="color: #31b903;font-size: 20px">新增</el-button>
          </div>
        </el-col>
        <el-col :span="12">
          <div style="text-align: center;font-size: 24px">
            填空题
          </div>
        </el-col>
        <el-col :span="6">

        </el-col>
      </el-row>
    </div>
    <div class="mains">
      <div style="margin: 2vh 8% 2vh 8%" v-for="(data, index) in questions" :key="index" >
        <el-card shadow="hover" class="box-card cards">
          <div style="text-align: right;color: #bd0a4f;font-size: 20px">
            {{data.questionMulti.score}}分
          </div>
          <div style="font-size: 18px;margin-bottom: 2vh">
            <el-row style="margin-bottom: 2vh">
              <el-col :span="2" >
                <div style="text-align: center" v-html="'<p>'+(index+1)+'、'+'</p>'" class="w-e-text"></div>
              </el-col>
              <el-col :span="22">
                <div v-html="data.questionMulti.question" class="w-e-text"></div>
              </el-col>
            </el-row>
            <div style="margin-bottom: 1vh">
              <strong>A</strong> 、{{data.questionMulti.answerA}}
            </div>
            <div style="margin-bottom: 1vh">
              <strong>B</strong> 、{{data.questionMulti.answerB}}
            </div>
            <div style="margin-bottom: 1vh">
              <strong>C</strong> 、{{data.questionMulti.answerC}}
            </div>
            <div style="margin-bottom: 1vh">
              <strong>D</strong> 、{{data.questionMulti.answerD}}
            </div>
          </div>
          <div style="font-size: 18px;margin-bottom: 2vh">
            答: {{data.questionMulti.rightAnswer}}
          </div>
          <div style="text-align: right">
            <el-button type="text" @click="deleteById(data.id)" size="small" style="color: red;font-size: 20px">删除</el-button>
          </div>
        </el-card>
      </div>
    </div>

    <!--    侧面抽屉-->
    <el-drawer
        title="填空题题库"
        :visible.sync="drawer"
        custom-class="demo-drawer"
        direction="rtl"
        size="60%"
        :before-close="handleClose">
      <div class="main">
        <!--    搜索框-->
        <div class="search">
          <el-row style="margin-top: 2vh;margin-left: 2%">
            <el-form :inline="true" :model="findQuestionMultiParams" class="demo-form-inline">
              <el-form-item label="题目编号:" style="padding-right: 2%">
                <el-input clearable v-model="findQuestionMultiParams.id" placeholder="题目编号"></el-input>
              </el-form-item>
              <el-form-item label="章节:" style="padding-right: 2%">
                <el-input  clearable v-model="findQuestionMultiParams.section" placeholder="章节"></el-input>
              </el-form-item>
              <el-form-item label="分值:" style="padding-right: 6%">
                <el-input clearable  clearable v-model="findQuestionMultiParams.score" placeholder="分值"></el-input>
              </el-form-item>
              <el-form-item label="难度等级:" style="padding-right: 2%">
                <el-input clearable v-model="findQuestionMultiParams.level" placeholder="难度等级"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="findQuestionMulti">查询</el-button>
              </el-form-item>
            </el-form>
          </el-row>
        </div>
        <!--    表单-->
        <div class="table">
          <el-table
              ref="table"
              :data="questionMulti"
              tooltip-effect="dark"
              border
              show-overflow-tooltip
              stripe
              style="width: 100%"
              height="66vh"
          >
            <el-table-column prop="id" label="题目id" width="100" align="center"></el-table-column>
            <el-table-column prop="section" label="章节" width="100" align="center"></el-table-column>
            <el-table-column label="题目" width="400" align="center">
              <template slot-scope="scope">
                <div class="question">
                  <div v-html="scope.row.question" class="w-e-text"></div>
                </div>
              </template>
            </el-table-column>
            <el-table-column prop="level" label="难度级别" width="100" align="center"></el-table-column>
            <el-table-column prop="score" label="分值" width="100" align="center"></el-table-column>
            <el-table-column
                fixed="right"
                label="操作"
                width="100"
                align="center"
            >
              <template slot-scope="scope">
                <el-button type="text" @click="submit(scope.row.id)" size="small" style="color: #074ab0;">添加</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>

      </div>
    </el-drawer>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  props: {
    // 定义接收的参数，这里以 param 为例
    examId: {
      type: String, // 参数类型，这里是字符串类型
      required: false, // 是否为必需参数，这里设为非必需
      default: '',// 参数的默认值
      form:[]
    },
    courseId: {
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
      form:{},
      questions:  [],
      params:{},
      drawer: false,
      questionMulti:[],
      findQuestionMultiParams:{}
    }
  },
  created() {
    this.receivedData = {
      examId:this.examId,
      courseId:this.courseId,
    }
    this.findQuestionMultiParams={
      courseId:this.receivedData.courseId
    }
    this.load()
  },
  mounted() {

  },
  methods: {
    load(){
      this.params= {
        examId:this.receivedData.examId,
        questionType:"multi"
      }
      request.get('/exam/examPaper/selectAll', {
        params:this.params
      }).then(res => {
        if (res.code === '200') {
          this.questions=res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    findBySearch(){
      request.get('/exam/examPaper/selectAll', {
        params:this.params
      }).then(res => {
        if (res.code === '200') {
          this.questions=res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    findQuestionMulti(){
      request.get('/exam/questionMulti/selectAll', {
        params:this.findQuestionMultiParams
      }).then(res => {
        if (res.code === '200') {
          this.questionMulti=res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    addQuestion(){
      this.findQuestionMulti()
      this.drawer=true
    },
    submit(id){
      let params;
      params={
        examId:this.receivedData.examId,
        questionType: "multi",
        questionId:id
      }
      request.post("/exam/examPaper/add", params).then(res => {
        if (res.code === "200") {
          this.$message.success("添加成功")
          this.findBySearch()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    deleteById(id){
      this.$confirm('您确定删除吗？', '确认删除', {type: "warning"}).then(response => {
        request.delete('/exam/examPaper/delete/' + id).then(res => {
          if (res.code === '200') {   // 表示操作成功
            this.$message.success('删除成功')
            this.findBySearch()
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      }).catch(() => {
      })
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            this.$message.success("成功1")
            done();
          })
          .catch(_ => {
            this.$message.success("成功2")
          });
    }
  }
}
</script>


<style scoped>
.cards{
  border-radius: 10px;
  background: rgba(237, 243, 252, 0.5);
}
.heads{
  height: 4vh;
  width: 100%;
}
.mains{
  height: 70vh;
  width: 100%;
  background: #ffffff;
  overflow-y: scroll;
  border-radius: 10px
}
.mains::-webkit-scrollbar{
  width:0;
}
.questionTable{
  background: #e7c6f5;
}
</style>

