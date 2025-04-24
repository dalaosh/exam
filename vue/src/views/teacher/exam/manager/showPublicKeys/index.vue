<template>
  <div style="background-color: #fefffe;padding-top: 0;height: 79vh">
    <el-row>
      <el-col :span="12">
        <div>
          <el-card class="box-card" style="height: 80vh;background-color: rgb(241,231,248);color: #030000;border-radius:10px;margin: 3vh 1% 3vh 3%">
            <div slot="header" class="clearfix" style="font-size: 26px">
              教师环公钥
            </div>
            <div style="font-size: 26px">
              <div id="lookKeys" style="height:66vh;border-radius:20px">
              </div>
            </div>
          </el-card>
        </div>
      </el-col>
      <el-col :span="12">
        <el-row>
          <el-card class="box-card" style="height: 40vh;background-color: rgb(239,244,252);color: #030000;border-radius:10px;margin: 3vh 3% 1vh 2%">
            <div style="font-size: 26px;margin-bottom: 3vh">
              用户使用说明
            </div>
            <div style="font-size: 24px">
              &emsp;&emsp;本次考试为保障您的数据安全与隐私，提供了便捷查看教师公钥的功能。您只需点击图表中教师姓名，系统便会迅速展示其公钥信息。
              <tr></tr>
              &emsp;&emsp;所有教师公钥构成环公钥体系，运用先进密码学技术，采用国密算法为数据安全筑牢屏障。每位教师公钥都是体系关键，他们用专业和技术保驾护航，让您安心使用系统。
            </div>
          </el-card>
        </el-row>
        <el-row>
          <router-view/>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>
<script>

import request from "@/utils/request";
import * as echarts from 'echarts';
export default {

  name: "BookView",
  data() {
    return {
      user:localStorage.getItem("user") ? (JSON).parse(localStorage.getItem("user")) : {},
      params:{
        name:"",
        pageNum:1,
        pageSize:5
      },
      dataKey:{},
      SearchAll:"",
      tableData:[],
      receivedData:"",
    }
  },
  //创建前加载前做
  created() {
    this.receivedData = this.$route.params
    this.findBySearch()
  },
  mounted() {
    this.echartsView()
  },
  //调用函数
  methods: {
    //   获取数据
    echartsView(dataKey){
      // 随机生成不重复的颜色
      // 随机生成不重复且颜色差异较大的颜色
      function getRandomColor() {
        const letters = '0123456789ABCDEF';
        let color = '#';
        for (let i = 0; i < 6; i++) {
          color += letters[Math.floor(Math.random() * 16)];
        }
        return color;
      }

// 生成一组不重复且颜色差异较大的随机颜色
      function generateUniqueRandomColors(count) {
        const colors = new Set();
        while (colors.size < count) {
          const color = getRandomColor();
          // 计算颜色的亮度，确保颜色差异较大
          const brightness = Math.sqrt(
              0.299 * parseInt(color.substring(1, 3), 16) ** 2 +
              0.587 * parseInt(color.substring(3, 5), 16) ** 2 +
              0.114 * parseInt(color.substring(5, 7), 16) ** 2
          );
          if (brightness > 120) {
            colors.add(color);
          }
        }
        return Array.from(colors);
      }
      var option;

      option = {
        tooltip: {
          trigger: 'item',
          formatter: function (params) {
            var res = params.name;
            return res;
          }
        },
        series: [
          {
            name: 'Access From',
            type: 'pie',
            radius: ['70%', '90%'],
            avoidLabelOverlap: {
              show: true,
              position: 'center'
            },
            label: {
              show: false,
              position: 'center'
            },
            emphasis: {
              label: {
                show: true,
                fontSize: 40,
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: true
            },

            data: this.tableData.map((item, index) => ({
              value: item.value,
              name: item.name,
              itemStyle: {
                color: generateUniqueRandomColors(this.tableData.length)[index] // 使用生成的随机颜色
              }
            }))
          }
        ]

      };
      var chartDom = document.getElementById('lookKeys');
      var myChart = echarts.init(chartDom);
      myChart.on('click', (params) => {
        // 在这里编写点击事件的逻辑
        // console.log(params.name); // 可以打印出被点击的数据项信息
        var name=params.name
        var key=Math.random();//随机数
        // console.log(dataKey)
        this.$router.push({
          name: 'teacherShowPublicKeysChild',
          params: {
            name:params.name,
            key:key,
            dataKey:dataKey
          }
        });
      });
      option && myChart.setOption(option);

    },
    findBySearch(){
      request.post("/exam/examManage/getPublicKeys/"+this.receivedData.id).then(res=>{
        if(res.code==='200'){
          this.SearchAll=res.data
          this.SearchAll.forEach(item=>{
            this.tableData.push({name:item.name,value:1})
            this.dataKey[item.name]=item.publicKey
          })
          this.echartsView(this.dataKey)
        }else {
          this.$message({
            message: res.msg,
            type: 'error'
          })
        }
      })
    },
  }

}
</script>

<style scoped>

</style>