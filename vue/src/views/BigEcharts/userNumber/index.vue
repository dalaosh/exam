<template>
  <div style="width: 100%;height: 8vh;color: #fff;text-align: center">
    <el-row>
      <el-col :span="6">
        <div style="font-size: 20px">{{ dataCount.adminCount }}</div>
        <div style="font-size: 22px;color: #08eedb;margin-top: 1vh">管理员</div>
      </el-col>
      <el-col :span="6">
        <div style="font-size: 20px">{{ dataCount.caCount }}</div>
        <div style="font-size: 22px;color: #08eedb;margin-top: 1vh">注册中心</div>
      </el-col>
      <el-col :span="6">
        <div style="font-size: 20px">{{ dataCount.teacherCount }}</div>
        <div style="font-size: 22px;color: #08eedb;margin-top: 1vh">教师</div>
      </el-col>
      <el-col :span="6">
        <div style="font-size: 20px">{{ dataCount.studentCount }}</div>
        <div style="font-size: 22px;color: #08eedb;margin-top: 1vh">学生</div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  name: "index",
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      dataCount: {},
    };
  },
  created() {
    this.load();
  },
  methods: {
    load() {
      request.get("statistic/selectUserCount/selectUserCount").then(res => {
        if (res.code === '200') {
          this.dataCount = res.data;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
  }
}
</script>

<style scoped>

</style>