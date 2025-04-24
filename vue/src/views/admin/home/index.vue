<template>
  <div style="background: #fdf9f9">
    <!-- 统计看板模块 -->
    <el-row>
      <el-col :span="24">
        <h2 class="main-title" style="text-align: center">系统用户统计</h2>
      </el-col>
      <el-col :span="6">
        <div class="box">
          <div style="color: #666">CA机构中心</div>
          <div style="margin-top: 10px; font-size: 20px">{{ dataCount.caCount }}</div>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="box">
          <div style="color: #666">管理员</div>
          <div style="margin-top: 10px; font-size: 20px">{{ dataCount.adminCount }}</div>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="box">
          <div style="color: #666">教师</div>
          <div style="margin-top: 10px; font-size: 20px">{{ dataCount.teacherCount }}</div>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="box">
          <div style="color: #666">学生</div>
          <div style="margin-top: 10px; font-size: 20px">{{ dataCount.studentCount }}</div>
        </div>
      </el-col>
    </el-row>

    <div ref="chart" style="width: 90%;margin-left: 5%; height: 60vh;margin-top: 2vh"></div>
    <!-- 技术展示模块 -->
    <el-row>
      <el-col :span="24">
        <h2 class="main-title" style="text-align: center">通知公告模块</h2>
      </el-col>
      <el-col :span="12">
        <div class="left">
          <el-collapse accordion class="collapse">
            <el-collapse-item
                v-for="(item, index) in notice"
                :key="item.id"
                :name="item.id"
            >
              <!-- 自定义标题 -->
              <template #title>
                <div class="title-container">
                  <span class="item-title">{{ item.name }}</span>
                  <span class="item-time">{{ item.time }}</span>
                </div>
              </template>

              <!-- 内容区域 -->
              <div class="content-box">
                <div class="user-info">
                  <el-avatar :size="40" :src="item.admin.photo" />
                  <div class="user-meta">
                    <span>{{ item.admin.name }}</span>
                    <span>{{ item.time }}</span>
                  </div>
                </div>
                <div class="content" v-html="item.content"></div>
              </div>
            </el-collapse-item>
          </el-collapse>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="right">
          <el-collapse accordion class="collapse">
            <el-collapse-item
                v-for="(item, index) in news"
                :key="item.id"
                :name="item.id"
            >
              <!-- 自定义标题 -->
              <template #title>
                <div class="title-container">
                  <span class="item-title">{{ item.name }}</span>
                  <span class="item-time">{{ item.time }}</span>
                </div>
              </template>

              <!-- 内容区域 -->
              <div class="content-box">
                <div class="user-info">
                  <el-avatar :size="40" :src="item.admin.photo" />
                  <div class="user-meta">
                    <span>{{ item.admin.name }}</span>
                    <span>{{ item.time }}</span>
                  </div>
                </div>
                <div class="content" v-html="item.content"></div>
              </div>
            </el-collapse-item>
          </el-collapse>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import request from "@/utils/request";
import * as echarts from 'echarts';
export default {
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      dataCount: {},
      notice:{},
      news:{},
    };
  },
  created() {
    this.load1();
    this.load2();
    this.findNews()
    this.findNotice()
  },
  methods: {
    load1() {
      request.get("statistic/selectUserCount/selectUserCount").then(res => {
        if (res.code === '200') {
          this.dataCount = res.data;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    load2() {
      request.get("statistic/selectUserCount/selectUserLogin").then(res => {
        if (res.code === '200') {
          this.data = res.data;
          this.initChart();
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findNews(){
      this.params= {
        pageNum:1,
        pageSize:6,
        userId:this.user.id
      }
      request.get('/info/news/selectPage', {
        params:this.params
      }).then(res => {
        if (res.code === '200') {
          this.news=res.data.list
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    findNotice(){
      this.params= {
        pageNum:1,
        pageSize:6,
        userId:this.user.id
      }
      request.get('/info/notice/selectPage', {
        params:this.params
      }).then(res => {
        if (res.code === '200') {
          this.notice=res.data.list
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    initChart() {
      // 处理日期数据
      const dates = new Set();
      Object.values(this.data).forEach(role => {
        Object.keys(role).forEach(date => dates.add(date));
      });
      const sortedDates = Array.from(dates).sort();

      // 角色中文映射
      const roleMap = {
        teacher: '教师',
        caInstitution: '机构',
        student: '学生',
        admin: '管理员'
      };

      // 生成系列数据
      const series = Object.keys(this.data).map(role => {
        const roleData = this.data[role];
        return {
          name: roleMap[role],
          type: 'line',
          // stack: '总量',
          smooth: true,
          data: sortedDates.map(date => roleData[date] || 0)
        };
      });

      // 配置选项
      const option = {
        title: {
          text: '用户登录统计',
          left: 'center',
          textStyle: {          // 新增标题样式
            fontSize: 24,       // 字号调整
            color: '#0a0a0a'       // 颜色设置
          },
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross'
          }
        },
        legend: {
          data: Object.keys(this.data).map(role => roleMap[role]),
          // orient: 'vertical',  // 设置为竖向排列
          right: '2%',        // 距离右侧 5%
          top: '1%',         // 距离顶部 10%
          itemGap: 15,        // 图例项间隔
          textStyle: {
            fontSize: 20
          },
          itemWidth: 15,      // 图例标记宽度
          itemHeight: 12      // 图例标记高度
        },
        grid: {
          left: '3%',
          right: '1%',  // 增加右边距给图例腾出空间
          bottom: '12%',
          containLabel: true
        },
        // toolbox: {
        //   feature: {
        //     saveAsImage: {
        //       title: '保存为图片'
        //     },
        //     dataZoom: {
        //       yAxisIndex: 'none'
        //     },
        //     restore: {}
        //   }
        // },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: sortedDates,
          axisLabel: {
            formatter: value => echarts.format.formatTime('MM-dd', value)
          }
        },
        yAxis: {
          type: 'value',
          minInterval: 1
        },
        series: series
      };

      // 初始化图表
      if (!this.chart) {
        this.chart = echarts.init(this.$refs.chart);
      }
      this.chart.setOption(option);
    }
  }
};
</script>

<style scoped>
/* 统计看板样式 */
.box {
  width: 94%;
  box-shadow: 0 0 10px -2px rgba(0, 0, 0, .2);
  border-radius: 5px;
  padding: 20px;
  text-align: center;
  margin: 2vh 3% 2vh 3%;
}
.left{
  margin: 1vh 3% 1vh 1%;
  padding: 1vh 2% 1vh 2%;
  height: 74vh;
  width: 96%;
  background: rgba(241, 246, 244, 0.77);
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  /* 过渡效果，使阴影变化更平滑 */
  transition: box-shadow 0.3s ease;
}
.right{
  margin: 1vh 3% 1vh 1%;
  padding: 1vh 2% 1vh 2%;
  height: 74vh;
  width: 96%;
  background: rgba(241, 246, 244, 0.77);
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  /* 过渡效果，使阴影变化更平滑 */
  transition: box-shadow 0.3s ease;
}
.collapse{
  max-height: 70vh;
  overflow-y: scroll;
}
.collapse::-webkit-scrollbar {
  width: 0;
}
.title-container {
  display: flex;
  justify-content: space-between;
  width: 100%;
  padding-right: 20px;
}

.item-title {
  font-weight: bold;
}

.item-time {
  color: #999;
  font-size: 0.9em;
}

.content-box {
  padding: 15px;
  background: #f8f9fa;
  border-radius: 4px;
}

.user-info {
  display: flex;
  align-items: center;
  margin-bottom: 15px;
}

.user-meta {
  margin-left: 15px;
  display: flex;
  flex-direction: column;
}

.user-meta span:first-child {
  font-weight: 500;
}

.user-meta span:last-child {
  color: #666;
  font-size: 0.8em;
}

.content {
  line-height: 1.6;
  color: #333;
}
</style>