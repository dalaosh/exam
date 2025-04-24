<template>
  <div>
    <el-row>
      <el-card class="box-card" style="height: 37vh;background-color: rgb(244,248,247);color: #030000;border-radius:10px;margin: 2vh 3% 2vh 2%">
        <div slot="header" class="clearfix" style="font-size: 26px">
          教师{{$route.params.name}}的公钥
        </div>
        <div>
          <el-input
              type="textarea"
              style="font-size: 20px"
              :autosize="{ minRows: 6, maxRows: 6}"
              placeholder="公钥数据"
              v-model="$route.params.dataKey[$route.params.name]">
          </el-input>
        </div>
      </el-card>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "BookView",
  data() {
    return {
      user:localStorage.getItem("user") ? (JSON).parse(localStorage.getItem("user")) : {},
    }
  },
  //创建前加载前做
  created() {

  },
  mounted() {
  },

  //调用函数
  methods: {

  }
}
</script>
