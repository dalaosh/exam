<template>
  <div style="background: #fdf9f9">
    <!-- 统计看板模块 -->
    <el-row>
      <el-col :span="24">
        <h2 class="main-title" style="text-align: center">系统成员统计</h2>
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
    <div class="tech-container">
      <el-row :gutter="20">
        <el-col :span="24">
          <h2 class="main-title">数字身份认证与管理技术</h2>
        </el-col>
        <el-col
            v-for="(tech, index) in technologies"
            :key="index"
            :xs="24" :sm="12" :md="12" :lg="6" :xl="6"
        >
          <el-card class="tech-card" style="height: 40vh">
            <div class="icon-wrapper">
              <i :class="tech.icon" class="tech-icon"></i>
            </div>
            <div class="content">
              <h3 class="tech-title">{{ tech.title }}</h3>
              <div class="description">
                <p
                    v-for="(paragraph, pIndex) in tech.description"
                    :key="pIndex"
                    class="description-paragraph"
                >
                  {{ paragraph }}
                </p>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
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

      technologies: [
        {
          title: '人脸识别技术',
          icon: 'el-icon-user-solid',
          description: [
            '基于深度学习算法实现高精度面部特征提取与验证',
            '支持活体检测功能，有效防止照片/视频等伪造攻击',
            '毫秒级响应速度，提供无感身份认证体验'
          ]
        },
        {
          title: '国密算法体系',
          icon: 'el-icon-key',
          description: [
            '全面采用SM2/SM3/SM4国密算法标准',
            '支持国产密码硬件设备集成与调用',
            '实现数据全生命周期加密保护'
          ]
        },
        {
          title: '环签名技术',
          icon: 'el-icon-connection',
          description: [
            '基于群签名的匿名身份验证方案',
            '满足隐私保护场景下的可验证需求',
            '支持可撤销匿名性的审计机制'
          ]
        },
        {
          title: '微服务架构',
          icon: 'el-icon-s-operation',
          description: [
            '基于Spring Cloud的分布式系统架构',
            '模块化设计支持弹性伸缩与快速迭代',
            '容器化部署保障高可用性与故障隔离'
          ]
        }
      ]
    };
  },
  created() {
    this.load1();
    this.load2();
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
      // const series = Object.keys(this.data).map(role => {
      //   const roleData = this.data[role];
      //   return {
      //     name: roleMap[role],
      //     type: 'line',
      //     stack: '总量',
      //     smooth: true,
      //     data: sortedDates.map(date => roleData[date] || 0)
      //   };
      // });
      const series = Object.keys(this.data).map(role => {
        const roleData = this.data[role];
        return {
          name: roleMap[role],
          type: 'line',
          smooth: true,  // 保留平滑曲线
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
          right: '1%',        // 距离右侧 5%
          top: '1%',         // 距离顶部 10%
          itemGap: 15,        // 图例项间隔
          textStyle: {
            fontSize: 18
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

/* 技术展示模块样式 */
.tech-container {
  padding: 20px;
  margin-top: 20px;
  background: #f8f9fa;

  .main-title {
    color: #2c3e50;
    text-align: center;
    margin-bottom: 30px;
  }

  .tech-card {
    margin-bottom: 20px;
    border-radius: 8px;
    transition: transform 0.3s;

    &:hover {
      transform: translateY(-5px);
      box-shadow: 0 4px 12px rgba(0,0,0,0.1);
    }

    .icon-wrapper {
      text-align: center;
      padding: 15px 0;
      background: #fff;

      .tech-icon {
        font-size: 40px;
        color: #409EFF;
      }
    }

    .content {
      padding: 15px;

      .tech-title {
        color: #303133;
        font-size: 16px;
        margin-bottom: 12px;
      }

      .description-paragraph {
        color: #606266;
        font-size: 14px;
        line-height: 1.6;
        margin: 8px 0;
      }
    }
  }
}

@media (max-width: 768px) {
  .tech-container {
    padding: 10px;

    .tech-card {
      .tech-title {
        font-size: 15px;
      }

      .description-paragraph {
        font-size: 13px;
      }
    }
  }
}
</style>