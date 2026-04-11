<template>
  <div class="ca-page">
    <div class="ca-page-head">
      <div>
        <h2 class="ca-page-head__title">CA 中心概览</h2>
        <p class="ca-page-head__desc">查看机构用户规模、登录趋势以及核心认证能力说明。</p>
      </div>
      <div class="ca-page-head__meta">
        <span class="ca-page-head__meta-text">数据范围：平台实时汇总</span>
      </div>
    </div>

    <div class="ca-stat-grid">
      <div class="ca-stat-card">
        <div class="ca-stat-card__label">CA 机构数量</div>
        <div class="ca-stat-card__value">{{ dataCount.caCount }}</div>
        <div class="ca-stat-card__hint">平台接入机构总量</div>
      </div>
      <div class="ca-stat-card">
        <div class="ca-stat-card__label">管理员数量</div>
        <div class="ca-stat-card__value">{{ dataCount.adminCount }}</div>
        <div class="ca-stat-card__hint">后台维护账户总量</div>
      </div>
      <div class="ca-stat-card">
        <div class="ca-stat-card__label">教师数量</div>
        <div class="ca-stat-card__value">{{ dataCount.teacherCount }}</div>
        <div class="ca-stat-card__hint">教师端入驻账户总量</div>
      </div>
      <div class="ca-stat-card">
        <div class="ca-stat-card__label">学生数量</div>
        <div class="ca-stat-card__value">{{ dataCount.studentCount }}</div>
        <div class="ca-stat-card__hint">学生端活跃用户基础</div>
      </div>
    </div>

    <div class="ca-dashboard-grid">
      <div class="ca-panel ca-panel--padded">
        <div class="ca-panel__head">
          <div>
            <h3 class="ca-panel__title">用户登录趋势</h3>
            <p class="ca-panel__desc">按角色展示近期开启登录的变化情况。</p>
          </div>
        </div>
        <div ref="chart" class="ca-chart"></div>
      </div>

      <div class="ca-panel ca-panel--padded">
        <div class="ca-panel__head">
          <div>
            <h3 class="ca-panel__title">能力说明</h3>
            <p class="ca-panel__desc">围绕身份认证、密钥管理与平台治理的核心能力。</p>
          </div>
        </div>
        <div class="ca-feature-grid">
          <div
            v-for="(tech, index) in technologies"
            :key="index"
            class="ca-feature-card"
          >
            <span class="ca-feature-card__icon">
              <i :class="tech.icon"></i>
            </span>
            <div>
              <h4 class="ca-feature-card__title">{{ tech.title }}</h4>
            </div>
            <ul class="ca-feature-card__list">
              <li
                v-for="(paragraph, pIndex) in tech.description"
                :key="pIndex"
              >
                {{ paragraph }}
              </li>
            </ul>
          </div>
        </div>
      </div>
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
            '支持活体检测功能，有效防止照片与视频伪造攻击',
            '秒级响应识别结果，保障实名校验效率'
          ]
        },
        {
          title: '国密算法体系',
          icon: 'el-icon-key',
          description: [
            '全面采用 SM2 / SM3 / SM4 国密算法标准',
            '支持与国产密码设备集成调用',
            '实现数据全生命周期加密保护'
          ]
        },
        {
          title: '环签名认证',
          icon: 'el-icon-connection',
          description: [
            '基于群签名能力实现匿名身份验证',
            '满足隐私保护场景下的可验签需求',
            '支持可追溯匿名性的审计能力'
          ]
        },
        {
          title: '微服务架构',
          icon: 'el-icon-s-operation',
          description: [
            '基于 Spring Cloud 的分布式系统架构',
            '模块化设计支持扩展与快速迭代',
            '容器化部署提升可用性与故障隔离能力'
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
      const dates = new Set();
      Object.values(this.data).forEach(role => {
        Object.keys(role).forEach(date => dates.add(date));
      });
      const sortedDates = Array.from(dates).sort();

      const roleMap = {
        teacher: '教师',
        caInstitution: '机构',
        student: '学生',
        admin: '管理员'
      };

      const series = Object.keys(this.data).map(role => {
        const roleData = this.data[role];
        return {
          name: roleMap[role],
          type: 'line',
          smooth: true,
          data: sortedDates.map(date => roleData[date] || 0)
        };
      });

      const option = {
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross'
          }
        },
        legend: {
          data: Object.keys(this.data).map(role => roleMap[role]),
          top: 8,
          left: 'center',
          textStyle: {
            fontSize: 12,
            color: '#64748b'
          },
          itemWidth: 14,
          itemHeight: 10
        },
        grid: {
          left: '3%',
          right: '3%',
          bottom: '8%',
          top: '18%',
          containLabel: true
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: sortedDates,
          axisLine: {
            lineStyle: {
              color: '#cbd5e1'
            }
          },
          axisLabel: {
            color: '#64748b',
            formatter: value => echarts.format.formatTime('MM-dd', value)
          }
        },
        yAxis: {
          type: 'value',
          minInterval: 1,
          splitLine: {
            lineStyle: {
              color: '#e2e8f0'
            }
          },
          axisLabel: {
            color: '#64748b'
          }
        },
        series: series
      };

      if (!this.chart) {
        this.chart = echarts.init(this.$refs.chart);
      }
      this.chart.setOption(option);
    }
  }
};
</script>

<style scoped>
</style>
