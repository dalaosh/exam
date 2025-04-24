<template>
  <div>
    <el-tabs class="main" type="border-card">
      <el-tab-pane label="考试详情">
        <div class="title">
          考试详情
        </div>
        <div style="height: 70vh" class="body">
          <show :param="receivedData.id"></show>
        </div>
      </el-tab-pane>
      <el-tab-pane label="自动组卷">
        <div class="body">
          <AIExam :param="receivedData.id" :courseId="receivedData.courseId"></AIExam>
        </div>
      </el-tab-pane>
      <el-tab-pane label="选择题">
        <div class="body">
          <multi :examId="receivedData.id" :courseId="receivedData.courseId"></multi>
        </div>
      </el-tab-pane>
      <el-tab-pane label="判断题">
        <div class="body">
          <judge :examId="receivedData.id" :courseId="receivedData.courseId"></judge>
        </div>
      </el-tab-pane>
      <el-tab-pane label="填空题">
        <div class="body">
          <fill :examId="receivedData.id" :courseId="receivedData.courseId"></fill>
        </div>
      </el-tab-pane>
      <el-tab-pane label="简答题">
        <div class="body">
          <shortAns :examId="receivedData.id" :courseId="receivedData.courseId"></shortAns>
        </div>
      </el-tab-pane>
      <el-tab-pane label="代码题">
        <div class="body">
          <codes :examId="receivedData.id" :courseId="receivedData.courseId"></codes>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import codes from "@/views/teacher/exam/manager/showManager/code"
import fill from "@/views/teacher/exam/manager/showManager/fill";
import judge from "@/views/teacher/exam/manager/showManager/judge";
import multi from "@/views/teacher/exam/manager/showManager/multi";
import show from "@/views/teacher/exam/manager/showManager/show";
import shortAns from "@/views/teacher/exam/manager/showManager/shortAns";
import AIExam from "@/views/teacher/exam/manager/showManager/AIExam/index.vue";
export default {
  components: {
    codes,
    fill,
    judge,
    multi,
    show,
    shortAns,
    AIExam
  },
  data(){
    return{
      user:localStorage.getItem("user") ? (JSON).parse(localStorage.getItem("user")) : {},
      form:{},
      receivedData:"",
    }
  },
  created() {
    this.receivedData = this.$route.params

  },
  mounted() {

  },
  methods: {

  }
}
</script>


<style scoped>
.main{
  margin: 1vh 2% 1vh 2%;
  border-radius: 10px;
}
.title{
  font-family: 'STXingkai', '华文行楷', cursive;
  font-size: 26px;
  text-align: center;
}
.body {
  margin: 1vh 1% 0 1%;
  width: 98%;
  height: 75vh;
}
</style>

