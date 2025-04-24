<template>
  <div class="detail-container">
    <!-- 头部导航 -->
    <div class="detail-header">
      <div class="header-left">
        <el-button
            icon="el-icon-arrow-left"
            class="back-btn"
            @click="handleBack"
        >
          返回
        </el-button>
        <h2 class="nav-title" @click="scrollToTop">{{ detailData.name }}</h2>
      </div>

      <div class="publisher-info">
        <div class="avatar-wrapper">
          <img :src="detailData.admin.photo" class="avatar">
        </div>
        <div class="meta">
          <div class="name">{{ detailData.admin.name }}</div>
          <div class="time">{{ formattedTime }}</div>
        </div>
      </div>
    </div>

    <!-- 内容区域 -->
    <div class="content-wrapper">
      <h1 class="title">{{ detailData.name }}</h1>
      <div
          class="content ql-editor"
          v-html="detailData.content"
      ></div>
    </div>
  </div>
</template>

<script>
import request from '@/utils/request'

export default {
  data() {
    return {
      detailData: {
        admin: {
          photo: '',
          name: '管理员',
          time: ''
        },
        name: '',
        content: '',
        time: ''
      }
    }
  },
  computed: {
    formattedTime() {
      return new Date(this.detailData.time).toLocaleString()
    }
  },
  created() {
    this.fetchDetail()
  },
  methods: {
    // 获取详情数据
    async fetchDetail() {
      try {
        const { type, id } = this.$route.params
        const apiUrl = `/info/${type}/selectById/${id}`
        const res = await request.get(apiUrl)

        if (res.code === '200') {
          this.detailData = res.data
          this.$nextTick(this.processImages)
        }
      } catch (error) {
        this.$message.error('获取详情失败')
      }
    },

    // 处理图片样式
    processImages() {
      const images = document.querySelectorAll('.content img')
      images.forEach(img => {
        img.style.maxWidth = '100%'
        img.style.height = 'auto'
      })
    },

    // 返回上一页
    handleBack() {
      this.$router.go(-1)
    },

    // 滚动到顶部
    scrollToTop() {
      window.scrollTo({ top: 0, behavior: 'smooth' })
    }
  }
}
</script>

<style scoped lang="scss">
.detail-container {
  background: #f8f9fa;
  min-height: 100vh;
  padding: 20px 12%;
  @media (max-width: 992px) {
    padding: 15px 5%;
  }
}

.detail-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  background: white;
  padding: 12px 25px;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0,0,0,0.06);
  transition: box-shadow 0.3s ease;
  &:hover {
    box-shadow: 0 6px 16px rgba(0,0,0,0.1);
  }
}

.header-left {
  display: flex;
  align-items: center;
  gap: 24px;
  flex: 1;
  min-width: 0;
}

.back-btn {
  padding: 9px 20px;
  border-radius: 8px;
  border-color: #e0e0e0;
  color: #666;
  transition: all 0.3s ease;
  &:hover {
    background: #f0f9ff;
    border-color: #1890ff;
    color: #1890ff;
    transform: translateX(-3px);
  }
}

.nav-title {
  margin: 0;
  font-size: 20px;
  color: #2c3e50;
  font-weight: 600;
  max-width: 800px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  transition: all 0.3s ease;
  cursor: pointer;

  &:hover {
    color: #1890ff;
    transform: translateY(-1px);
  }

  @media (max-width: 768px) {
    font-size: 18px;
    max-width: 200px;
  }
}

.publisher-info {
  display: flex;
  align-items: center;
  gap: 12px;
}

.avatar-wrapper {
  width: 44px;
  height: 44px;
  border-radius: 50%;
  background: white;
  padding: 2px;
  box-shadow: 0 2px 6px rgba(0,0,0,0.1);
  .avatar {
    width: 100%;
    height: 100%;
    border-radius: 50%;
    object-fit: cover;
  }
}

.meta {
  .name {
    font-weight: 600;
    color: #2c3e50;
    font-size: 14px;
  }
  .time {
    font-size: 12px;
    color: #95a5a6;
  }
}

.content-wrapper {
  background: white;
  margin-top: 24px;
  padding: 40px 50px;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0,0,0,0.06);
  @media (max-width: 768px) {
    padding: 25px;
  }

  .title {
    color: #2c3e50;
    margin: 0 0 30px 0;
    padding-bottom: 20px;
    border-bottom: 1px solid #eee;
    font-size: 28px;
    font-weight: 700;
    line-height: 1.3;
    @media (max-width: 768px) {
      display: none;
    }
  }
  height: 83vh;
  overflow-y: scroll;
}
.content-wrapper::-webkit-scrollbar {
  width: 0;
}

.content {
  font-size: 16px;
  line-height: 1.8;
  color: #34495e;
}

/* 富文本深度样式 */
::v-deep .ql-editor {
  h1, h2, h3 {
    margin: 1.2em 0 0.8em;
    color: #2c3e50;
  }

  p {
    margin: 0 0 1.2em;
    text-align: justify;
  }

  img {
    border-radius: 8px;
    box-shadow: 0 4px 12px rgba(0,0,0,0.1);
    margin: 2em auto;
    display: block;
    max-width: 80%;
    @media (max-width: 768px) {
      max-width: 100%;
    }
  }

  blockquote {
    border-left: 4px solid #1890ff;
    background: #f8f9fa;
    color: #7f8c8d;
    margin: 1.5em 0;
    padding: 1em 1.5em;
    border-radius: 4px;
  }

  ul, ol {
    padding-left: 1.5em;
    margin: 1em 0;
  }

  a {
    color: #1890ff;
    text-decoration: none;
    &:hover {
      text-decoration: underline;
    }
  }

  pre {
    background: #f8f9fa;
    padding: 1em;
    border-radius: 6px;
    code {
      font-family: 'JetBrains Mono', monospace;
      font-size: 14px;
    }
  }
}
</style>