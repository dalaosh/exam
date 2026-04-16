<template>
  <div class="public-key-page">
    <el-row :gutter="20">
      <el-col :span="12">
        <div class="key-card chart-card">
          <div class="card-header">
            <div>
              <div class="card-eyebrow">Public Key Ring</div>
              <div class="card-title">教师环公钥</div>
            </div>
          </div>
          <div id="lookKeys" class="chart-body"></div>
        </div>
      </el-col>

      <el-col :span="12">
        <div class="key-card intro-card">
          <div class="card-header">
            <div class="card-title">使用说明</div>
          </div>
          <div class="intro-body">
            本次考试为保障数据安全与隐私，提供了教师公钥环查看能力。点击左侧图表中的教师姓名，系统会展示该教师的公钥数据。
            <br /><br />
            所有教师公钥共同构成环公钥体系，用于支撑考试流程中的安全加密与验证环节。
          </div>
        </div>
        <router-view />
      </el-col>
    </el-row>
  </div>
</template>
<script>
import request from "@/utils/request"
import * as echarts from "echarts"

export default {
  name: "BookView",
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      params: {
        name: "",
        pageNum: 1,
        pageSize: 5
      },
      dataKey: {},
      SearchAll: "",
      tableData: [],
      receivedData: ""
    }
  },
  created() {
    this.receivedData = this.$route.params
    this.findBySearch()
  },
  mounted() {
    this.echartsView()
  },
  methods: {
    echartsView(dataKey) {
      function getRandomColor() {
        const letters = "0123456789ABCDEF"
        let color = "#"
        for (let i = 0; i < 6; i++) {
          color += letters[Math.floor(Math.random() * 16)]
        }
        return color
      }
      function generateUniqueRandomColors(count) {
        const colors = new Set()
        while (colors.size < count) {
          const color = getRandomColor()
          const brightness = Math.sqrt(
            0.299 * parseInt(color.substring(1, 3), 16) ** 2 +
            0.587 * parseInt(color.substring(3, 5), 16) ** 2 +
            0.114 * parseInt(color.substring(5, 7), 16) ** 2
          )
          if (brightness > 120) {
            colors.add(color)
          }
        }
        return Array.from(colors)
      }
      const option = {
        tooltip: {
          trigger: "item",
          formatter: function(params) {
            return params.name
          }
        },
        series: [
          {
            type: "pie",
            radius: ["66%", "86%"],
            avoidLabelOverlap: {
              show: true,
              position: "center"
            },
            label: {
              show: false,
              position: "center"
            },
            emphasis: {
              label: {
                show: true,
                fontSize: 32,
                fontWeight: "bold"
              }
            },
            labelLine: {
              show: true
            },
            data: this.tableData.map((item, index) => ({
              value: item.value,
              name: item.name,
              itemStyle: {
                color: generateUniqueRandomColors(this.tableData.length)[index]
              }
            }))
          }
        ]
      }
      const chartDom = document.getElementById("lookKeys")
      const myChart = echarts.init(chartDom)
      myChart.on("click", params => {
        this.$router.push({
          name: "teacherShowPublicKeysChild",
          params: {
            name: params.name,
            key: Math.random(),
            dataKey: dataKey
          }
        })
      })
      myChart.setOption(option)
    },
    findBySearch() {
      request.post("/exam/examManage/getPublicKeys/" + this.receivedData.id).then(res => {
        if (res.code === "200") {
          this.SearchAll = res.data
          this.SearchAll.forEach(item => {
            this.tableData.push({ name: item.name, value: 1 })
            this.dataKey[item.name] = item.publicKey
          })
          this.echartsView(this.dataKey)
        } else {
          this.$message({
            message: res.msg,
            type: "error"
          })
        }
      })
    }
  }
}
</script>

<style scoped>
.public-key-page {
  padding-bottom: 8px;
}

.key-card {
  margin-bottom: 20px;
  padding: 24px;
  border: 1px solid rgba(226, 232, 240, 0.95);
  border-radius: 24px;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0.98), rgba(248, 250, 252, 0.96));
  box-shadow: 0 20px 48px rgba(15, 23, 42, 0.08);
}

.card-header {
  margin-bottom: 18px;
}

.card-eyebrow {
  margin-bottom: 6px;
  font-size: 12px;
  font-weight: 700;
  letter-spacing: 0.12em;
  text-transform: uppercase;
  color: #2563eb;
}

.card-title {
  font-size: 22px;
  font-weight: 700;
  color: #0f172a;
}

.chart-body {
  height: 520px;
}

.intro-body {
  min-height: 220px;
  padding: 18px 20px;
  border: 1px solid #e2e8f0;
  border-radius: 18px;
  background: linear-gradient(180deg, #ffffff 0%, #f8fafc 100%);
  color: #475569;
  font-size: 15px;
  line-height: 1.9;
}
</style>
