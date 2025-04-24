<template>
  <div>
    <el-row>
      <div class="top">
        <el-descriptions labelStyle="font-size:16px" contentStyle="font-size:16px" column="3" title="考试信息">
          <el-descriptions-item label="课程名称">
            <el-tag style="font-size: 16px">{{examManager.course.name}}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item  label="课程编码">
            <el-tag style="font-size: 16px" type="info">{{ examManager.course.number }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item  label="任课教师">
            <el-tag style="font-size: 16px" type="info">{{ examManager.teacher.name }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item  label="考试类别">
            <el-tag style="font-size: 16px" type="info">{{ examManager.type }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="考生人数">
            <el-tag style="font-size: 16px" type="warning">{{ examAnswerNum.examCourseNum }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="考试时长">
            <el-tag style="font-size: 16px" type="warning">{{ examManager.totalTime }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="总分">
            <el-tag style="font-size: 16px" type="danger">{{examManager.totalScore}}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="题目总数">
            <el-tag style="font-size: 16px" type="success">{{ examAnswerNum.examPaperNum }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="有效答题数">
            <el-tag style="font-size: 16px" type="danger">{{examAnswerNum.examAnswerNum}}</el-tag>
          </el-descriptions-item>
        </el-descriptions>
      </div>
    </el-row>
    <el-row>
      <el-col :span="8">
        <div class="left">
          <div style="margin-bottom: 2vh;text-align: center">
            <el-button style="margin-bottom: 1vh;margin-top: 1vh;font-size: 18px"  class="buttonSize" type="primary"  @click="submit()">进行成绩统计</el-button>
          </div>
          <el-steps style="height: 46vh;padding-left: 10%" direction="vertical" :active="active">
            <el-step title="第一步" description="开始进行成绩统计"></el-step>
            <el-step title="第二步" description="提取课程学生信息"></el-step>
            <el-step title="第三步" description="将学生信息写入成绩榜"></el-step>
            <el-step title="第四步" description="计算每个学生的成绩"></el-step>
          </el-steps>
        </div>
      </el-col>
      <el-col :span="16">
        <div class="right">
          <div style="font-size: 26px;text-align: center">
            金榜题名
          </div>
          <div class="body">
            <div class="regional_table">
              <el-table
                  :data="rankList"
                  :border="true"
                  height="calc(100vh - 380px)"
              >
                <el-table-column align="center"  label="排名" width="120">
                  <template slot-scope="scope">
                    <img v-if="scope.$index === 0" src="@/assets/icon/img.png" class="rank-icon" />
                    <img v-if="scope.$index === 1" src="@/assets/icon/img_2.png" class="rank-icon" />
                    <img v-if="scope.$index === 2" src="@/assets/icon/img_1.png" class="rank-icon" />
                    {{ scope.$index + 1 }}
                  </template>
                </el-table-column>
                <el-table-column prop="student.name" label="姓名" align="center" />
                <el-table-column prop="score" label="分数" align="center" />
              </el-table>
            </div>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import request from "@/utils/request"

export default {
  data() {
    return {
      user: localStorage.getItem("user") ? (JSON).parse(localStorage.getItem("user")) : {},
      receivedData: "",
      examManager:{},
      examAnswerNum:{},
      active:0,
      courseStudent:{},
      rankList: []
    }
  },
  created() {
    this.receivedData = this.$route.params
    this.findExamManager()
    this.select()
  },
  mounted() {

  },
  methods: {
    findExamManager(){
      request.get("exam/examManage/selectById/"+this.receivedData.id).then(res=>{
        if (res.code === '200') {
          this.examManager=res.data
          this.findCountNum()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    findCountNum(){
      let params;
      let examId;
      let courseId;
      examId=this.receivedData.id
      courseId=this.examManager.course.id
      params={
        examId,
        courseId
      }
      request.get("statistic/selectExamCount/selectExamAnswerStatistic",{
        params:params
      }).then(res=>{
        if (res.code === '200') {
          this.examAnswerNum=res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    submit(){
      //查询学生
      this.active=1;
      this.selectStudent()
    },
    selectStudent(){
      let params;
      params= {
        courseId:this.examManager.course.id,
        isAdd:"同意"
      }
      request.get('/exam/courseStudent/selectAll', {
        params:params
      }).then(res => {
        if (res.code === '200') {
          this.courseStudent=res.data
          //添加学生
          this.active=2;
          this.insertStudent()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    insertStudent(){
      //向成绩榜中批量添加学生
      let courseStudent;
      courseStudent=this.courseStudent
      request.post('/exam/examScore/batchInsert/'+this.receivedData.id,courseStudent).then(res => {
        if (res.code === '200') {
//添加学生
          this.active=3;
          this.addCourse()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    addCourse(){
      request.post('/exam/examScore/setScore/'+this.receivedData.id).then(res => {
        if (res.code === '200') {
          this.select()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    select(){
      let params;
      params= {
        managerId:this.receivedData.id,
      }
      request.get('/exam/examScore/selectAll', {
        params:params
      }).then(res => {
        if (res.code === '200') {
          this.rankList=res.data
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
  margin: 1vh 2% 1vh 2%;
  padding: 1vh 2% 1vh 2%;
  height: 20vh;
  width: 96%;
  background: rgba(241, 253, 253, 0.77);
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  /* 过渡效果，使阴影变化更平滑 */
  transition: box-shadow 0.3s ease;
}

.left{
  margin: 1vh 2% 1vh 6%;
  padding: 3vh 8% 1vh 0;
  height: 62vh;
  width: 92%;
  background: rgba(241, 253, 253, 0.77);
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  /* 过渡效果，使阴影变化更平滑 */
  transition: box-shadow 0.3s ease;
}

.right{
  margin: 1vh 3% 1vh 1%;
  padding: 1vh 2% 1vh 2%;
  height: 62vh;
  width: 96%;
  background: rgba(248, 20, 96, 0.77);
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  /* 过渡效果，使阴影变化更平滑 */
  transition: box-shadow 0.3s ease;
}
.body{
  margin: 1vh 10% 1vh 10%;
  text-align: center;
  height: 54vh;
  width: 80%;
  background: rgba(248, 20, 96, 0.77);
  border-radius: 10px;
  overflow-y: scroll;
}
.body::-webkit-scrollbar {
  width: 0;
}
.rank-icon {
  width: 20px;
  height: 20px;
  vertical-align: middle;
  margin-right: 5px;
}
/*最外层透明*/
.regional_table /deep/ .el-table,
.regional_table /deep/ .el-table__expanded-cell {
  background-color: transparent;
  color: #dce2ec;
}
/* 表格内背景颜色 */
.regional_table /deep/ .el-table th,
.regional_table /deep/ .el-table tr,
.regional_table /deep/ .el-table td {
  background-color: transparent !important;
  color: #041750;
  font-size: 20px; /* 可根据需要调整字体大小 */
}

</style>

