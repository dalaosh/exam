<template>
  <div class="main">
    <!--    搜索框-->
    <div class="search">
      <el-row style="margin-top: 2vh;margin-left: 2%">
        <el-form :inline="true" :model="params" class="demo-form-inline">
          <el-form-item label="课程名称:" style="padding-right: 4%">
            <el-select v-model="params.courseId" style="font-size: 20px;width: 300px" clearable filterable placeholder="请选择">
              <el-option
                  v-for="item in course"
                  :key="item.courseId"
                  style="font-size: 20px; width: 100%;"
                  :label="item.course.name+'==='+item.course.number"
                  :value="item.courseId"
              >
                <span style="float: left;margin-left: 6px; color: #8492a6; font-size: 18px">课程:{{ item.course.name }}</span>
                <span style="float: left;margin-left: 6px; color: #8492a6; font-size: 18px">编号:{{ item.course.number }}</span>
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="考试类型:" style="padding-right: 4%">
            <el-input v-model="params.type" placeholder="考试类型"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button style="width: 150px"  clearable type="primary" @click="findBySearch">查询</el-button>
          </el-form-item>
        </el-form>
      </el-row>
    </div>
    <!--    表单-->
    <div class="cards">
      <!-- 使用 el-row 组件定义行布局，设置列之间的间距为 20 -->
      <el-row v-if="this.visit!==-1&&this.visit!==0" class="cards">
        <!-- 使用 v-for 指令遍历 jsonData 数组，为每个元素渲染一个 el-col 组件 -->
        <el-col :span="8" v-for="(item, index) in tableData" :key="index">
          <el-card class="card cardMsg" :body-style="{ padding: '0px' }">
            <div style="padding: 14px;">
              <el-row style="margin-top: 2vh;margin-bottom: 2vh">
                <el-col :span="14">
                  科目:{{item.course.name}}
                </el-col>
                <el-col :span="10">
                  编码:{{item.course.number}}
                </el-col>
              </el-row>
              <el-row style="margin-top: 2vh;margin-bottom: 2vh;">
                考试开始时间:{{item.examManage.examDate}}
              </el-row>
              <el-row style="margin-top: 2vh;margin-bottom: 2vh;">
                <el-col :span="14">
                  考试类型:{{item.examManage.type}}
                </el-col>
                <el-col :span="10">
                  考试时长:{{item.examManage.totalTime}}分钟
                </el-col>
              </el-row>
              <el-row style="">
                <el-col :span="14">
                  总分:{{item.examManage.totalScore}}分
                </el-col>
                <el-col :span="10">
                  出题人:{{item.teacher.name}}
                </el-col>
              </el-row>
            </div>
            <div style="padding: 0 2% 0 2%;text-align: center">
              <el-button type="text" @click="showPublicKeys(item)" size="small" style="color: #009dff;font-size: 20px;margin-right: 3%">查看环公钥</el-button>
              <el-button type="text" @click="showManager(item)" size="small" style="color: #009dff;font-size: 20px">查看详情</el-button>
              <el-button type="text" @click="beginExam(item)" size="small" style="color: #009dff;font-size: 20px">开始考试</el-button>
              <el-button type="text" @click="beginShowExam(item)" size="small" style="color: #009dff;font-size: 20px;margin-right: 3%">查看答案</el-button>
              <el-button type="text" @click="showScore(item)" size="small" style="color: #009dff;font-size: 20px">查看榜单</el-button>
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
    <div style="margin-top: 2vh;text-align: center">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[5,10,15,20]"
          :page-size="pageSize"
          layout="total, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
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
      params: {
        pageNum:1,
        pageSize:6,
      },
      visit:-1,
    }
  },
  created() {
    this.findCourse()
    this.load()
  },
  mounted() {

  },
  methods: {
    handleSizeChange(pageSize){
      this.params.pageSize=pageSize;
      //查询
      this.findBySearch()
    },
    handleCurrentChange(pageNum){
      this.params.pageNum=pageNum;
      //查询
      this.findBySearch()
    },
    load(){
      this.params.studentId=this.user.id
      request.get('/exam/examStudent/selectPage', {
        params:this.params
      }).then(res => {
        if (res.code === '200') {
          this.total=res.data.total
          this.tableData=res.data.list
          this.visit=this.tableData.length

        } else {
          this.$message.error(res.msg)
        }
      })
    },
    findCourse(){
      let params;
      params= {
        studentId: this.user.id,
        isAdd:"同意"
      }
      request.get('/exam/courseStudent/selectAll', {
        params:params
      }).then(res => {
        if (res.code === '200') {
          this.course=res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    findBySearch(){
      request.get('/exam/examStudent/selectPage', {
        params:this.params
      }).then(res => {
        if (res.code === "200") {
          this.total=res.data.total
          this.tableData=res.data.list
          this.visit=this.tableData.length
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    showManager(row){
      this.$router.push({
        name: "studentShowExam",
        params: {
          id:row.examManage.id
        }
      });
    },
    showPublicKeys(row){
      this.$router.push({
        name: "studentShowPublicKeys",
        params: {
          id:row.examManage.id
        }
      });
    },
    beginExam(row){
      this.$router.push({
        name: "studentTips",
        params: {
          id:row.examManage.id,
          examStudent:row.id
        }
      });
    },
    beginShowExam(row){
      this.$router.push({
        name: "studentShowTips",
        params: {
          id:row.examManage.id
        }
      });
    },
    showScore(row){
      this.$router.push({
        name: "studentScore",
        params: {
          id:row.examManage.id
        }
      });
    }
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
  margin: 0 1% 0 1%;
  border-radius: 10px;
  height: 70vh;
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
  height: 32vh;
}
</style>

