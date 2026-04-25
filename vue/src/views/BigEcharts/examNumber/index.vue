<template>
  <div style="width: 100%;height: 8vh;color: #fff;text-align: center">
    <el-row>
      <el-col :span="6">
        <div style="font-size: 20px">{{ dataCount.courseNum }}</div>
        <div style="font-size: 22px;color: #08eedb;margin-top: 1vh">课程数</div>
      </el-col>
      <el-col :span="6">
        <div style="font-size: 20px">{{ dataCount.examNum }}</div>
        <div style="font-size: 22px;color: #08eedb;margin-top: 1vh">试卷数</div>
      </el-col>
      <el-col :span="6">
        <div style="font-size: 20px">{{ dataCount.s }}%</div>
        <div style="font-size: 22px;color: #08eedb;margin-top: 1vh">及格率</div>
      </el-col>
      <el-col :span="6">
        <div style="font-size: 20px">{{ dataCount.questionAll }}</div>
        <div style="font-size: 22px;color: #08eedb;margin-top: 1vh">题目总数</div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "index",
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      dataCount: {
        courseNum: 12,
        examNum: 36,
        s: 92,
        questionAll: 428,
      },
    };
  },
  created() {
  },
  methods: {
  }
}
</script>

<style scoped>

</style>
