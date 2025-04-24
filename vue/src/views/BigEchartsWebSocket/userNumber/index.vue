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
  props: {
    countData: {
      type: Object,
      default: () => ({})
    }
  },
  watch: {
    countData: {
      handler(newVal) {
        // 深度合并数据（防止丢失嵌套结构）
        this.data = {
          ...this.data,
          ...newVal,
        };
      },
      deep: true,   // 深度监听对象变化
      immediate: true // 立即触发一次确保初始数据合并
    }
  },
  created() {
    this.load();
  },
  methods: {
    load() {
          this.dataCount = this.countData;
    },
  }
}
</script>

<style scoped>

</style>
