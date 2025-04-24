<template>
  <div style="width: 100%;height: 40vh;color: #fff;text-align: center">
    <div class="boxnav" style="width: 100%; height: 57vh;">
      <!--            controls  属性的值可以为  true  或  false ，默认值为  true 。设置为  false  时，视频的控制栏将被隐藏。-->
      <video :poster="require('@/assets/echarts/border.png')" src="../../../assets/echarts/schools.mp4" loop muted autoplay style="width: 100%;height: 40vh;object-fit: cover;"></video>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  name: "index",
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
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