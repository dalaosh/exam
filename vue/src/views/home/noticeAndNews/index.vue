<template>
  <div class="backgrounds">
    <!-- 标题 -->
    <div class="header">
      <strong>新闻公告</strong>
    </div>

    <!-- 内容区域 -->
    <div class="content-container">
      <el-row :gutter="20">
        <!-- 新闻列 -->
        <el-col :span="17">
          <div class="news-section">
            <h2 class="section-title">最新新闻</h2>
            <div class="news-body">
              <div
                  v-for="item in news"
                  :key="item.id"
                  class="info-card">
                <div class="card-content">
                  <h3 class="title">{{ item.name }}</h3>
                  <div class="meta">
                    <span class="time">{{ formattedTime(item.time) }}</span>
                    <el-button
                        type="primary"
                        size="mini"
                        @click="viewDetail('news', item.id)"
                    >
                      查看详情
                    </el-button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </el-col>
        <!-- 公告列 -->
        <el-col :span="7">
          <div class="notice-section">
            <h2 class="section-title">重要公告</h2>
            <div class="notice-body">
              <div
                  v-for="item in notice"
                  :key="item.id"
                  class="info-card">
                <div class="card-content">
                  <h3 class="title">{{ item.name }}</h3>
                  <div class="meta">
                    <span class="time">{{ formattedTime(item.time) }}</span>
                    <el-button
                        type="success"
                        size="mini"
                        @click="viewDetail('notice', item.id)"
                    >
                      查看详情
                    </el-button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  data() {
    return {
      notice: [],
      news: []
    };
  },
  computed: {
    // 格式化时间显示
    formattedTime() {
      return (timeStr) => {
        return new Date(timeStr).toLocaleDateString()
      }
    }
  },
  created() {
    this.findNews();
    this.findNotice();
  },
  methods: {
    // 获取新闻数据
    async findNews() {
      try {
        const res = await request.get('/info/news/selectAll');
        if (res.code === '200') {
          this.news = res.data.slice(0, 5); // 仅显示最新5条
        }
      } catch (error) {
        this.$message.error('新闻加载失败');
      }
    },

    // 获取公告数据
    async findNotice() {
      try {
        const res = await request.get('/info/notice/selectAll');
        if (res.code === '200') {
          this.notice = res.data.slice(0, 5); // 仅显示最新5条
        }
      } catch (error) {
        this.$message.error('公告加载失败');
      }
    },

    // 查看详情跳转
    viewDetail(type, id) {
      this.$router.push({
        name: 'noticeAndNew',
        params: {
          type: type,
          id: id
        }
      });
    }
  }
};
</script>

<style scoped>
.backgrounds{
  height: 92vh;
  overflow: hidden;
  background-image: url("@/assets/home/function/a.jpg");
  background-size: 100%;
  align-items: center;
  justify-content: center;
  /* 修正 filter 语法 */
  filter:'blur(10px)';
}

.header {
  text-align: center;
  padding: 20px 0;
  background: rgba(238, 248, 240, 0.9);
}

.header strong {
  font-size: 28px;
  color: #2c3e50;
  letter-spacing: 2px;
}

.content-container {
  background: rgba(255, 255, 255, 0.45);
  padding: 25px;
}

.section-title {
  color: #34495e;
  border-left: 4px solid #3498db;
  padding-left: 12px;
  margin: 20px 0;
}

.info-card {
  background: white;
  border-radius: 8px;
  margin-bottom: 15px;
  transition: all 0.3s ease;
  box-shadow: 0 2px 6px rgba(0,0,0,0.1);
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 12px rgba(0,0,0,0.15);
}

.card-content {
  padding: 15px 20px;
}

.title {
  font-size: 16px;
  color: #2c3e50;
  margin: 0 0 10px 0;
  line-height: 1.4;
}

.meta {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.time {
  color: #7f8c8d;
  font-size: 12px;
}
.news-body{
  width: 100%;
  height: 68vh;
  overflow-y: scroll;
}
.news-body::-webkit-scrollbar {
  width: 0;
}
.notice-body{
  width: 100%;
  height: 68vh;
  overflow-y: scroll;
}
.notice-body::-webkit-scrollbar {
  width: 0;
}
.el-button {
  padding: 5px 12px;
  border-radius: 15px;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .el-col {
    width: 100%;
    margin-bottom: 20px;
  }

  .content-container {
    padding: 15px;
  }

  .card-content {
    padding: 12px;
  }

  .title {
    font-size: 14px;
  }
}
</style>