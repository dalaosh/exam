<template>
  <div class="main">
    <!--    表单-->
    <div  class="cards">
      <!-- 使用 el-row 组件定义行布局，设置列之间的间距为 20 -->
      <el-row v-if="this.visit!==-1&&this.visit!==0">
        <!-- 使用 v-for 指令遍历 jsonData 数组，为每个元素渲染一个 el-col 组件 -->
        <el-col :span="8" v-for="(item, index) in tableData" :key="index">
          <el-card class="card cardMsg" :body-style="{ padding: '0px' }">
            <div style="padding: 14px;">
              <el-row style="margin-top: 2vh;margin-bottom: 2vh">
                <el-col :span="14">
                  考试科目:{{item.course.name}}
                </el-col>
                <el-col :span="10">
                  课程编码:{{item.course.number}}
                </el-col>
              </el-row>
              <el-row style="margin-top: 2vh;margin-bottom: 2vh;">
                考试开始时间:{{item.examDate}}
              </el-row>
              <el-row style="margin-top: 2vh;margin-bottom: 2vh;">
                <el-col :span="14">
                  考试类型:{{item.type}}
                </el-col>
                <el-col :span="10">
                  考试时长:{{item.totalTime}}分钟
                </el-col>
              </el-row>
              <el-row style="">
                <el-col :span="14">
                  总分:{{item.totalScore}}分
                </el-col>
                <el-col :span="10">
                  出题人:{{item.teacher.name}}
                </el-col>
              </el-row>
              <el-row style="margin-top: 2vh;margin-bottom: 2vh;">
                阅卷开始时间:{{item.beginTime}}
              </el-row>
              <el-row style="margin-top: 2vh;">
                阅卷结束时间:{{item.endTime}}
              </el-row>
            </div>
            <div style="padding: 0 2% 0 2%;text-align: center;">
              <el-button type="text" @click="showManager(item)" size="small" style="color: #009dff;font-size: 20px">查看详情</el-button>
              <el-button type="text" @click="markCheck(item.id)" size="small" style="color: #009dff;font-size: 20px;margin-right: 3%">批卷验签</el-button>
              <el-button type="text" @click="teacherMarkStatistics(item.id)" size="small" style="color: #009dff;font-size: 20px">公布成绩</el-button>
            </div>
          </el-card>
        </el-col>
      </el-row>
      <el-row style="font-family: '华文行楷', cursive;font-weight: bold;font-size: 6vh;text-align: center;margin-top: 30vh;color: #0b42e8" v-if="visit===-1">
        请先选择课程进行考试的查询
      </el-row>
      <el-row style="font-family: '华文行楷', cursive;font-weight: bold;font-size: 6vh;text-align: center;margin-top: 30vh;color: #05fd09" v-if="visit===0">
        该课程还未发布试卷
      </el-row>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request"
import studentShowExam from "@/views/student/exam/manager/showExam/index.vue";
export default {
  data(){
    return{
      user:localStorage.getItem("user") ? (JSON).parse(localStorage.getItem("user")) : {},
      form:{},
      tableData: [],
      pageNum: 1,   // 当前的页码
      pageSize: 6,  // 每页显示的个数
      total: 0,
      course:[],
      visit:-1,
    }
  },
  created() {
    this.findBySearch()
  },
  mounted() {

  },
  methods: {
    findBySearch(){
      let params;
      params={
        teacherId:this.user.id
      }
      request.get('/exam/examTeacherGrading/selectExam', {
        params:params
      }).then(res => {
        if (res.code === '200') {
          this.tableData=res.data
          this.visit=this.tableData.length
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    showManager(row){
      this.$router.push({
        name: "teacherShowExamMsg",
        params: {
          id:row.id
        }
      });
    },
    markCheck(id){
      this.$router.push({
        name: "teacherMarkCheck",
        params: {
          id:id
        }
      });
    },
    teacherMarkStatistics(id){
      this.$router.push({
        name: "teacherMarkStatistics",
        params: {
          id:id
        }
      });
    },
  }
}
</script>


<style scoped>
.main{
  padding-left: 3px;
  padding-right: 3px;
}
.search{
  height: 7vh;
}
.cards{
  margin: 2vh 1% 0 1%;
  padding: 2vh 1% 2vh 1%;
  border-radius: 10px;
  height: 83vh;
  width: 98%;
  background: #ecf1f6;
  overflow-y: scroll;
}
.cards::-webkit-scrollbar{
  width:0;
}
.cardMsg{
  margin: 2vh 2% 0 2%;
  width: 96%;
  height: 40vh;
}
</style>

