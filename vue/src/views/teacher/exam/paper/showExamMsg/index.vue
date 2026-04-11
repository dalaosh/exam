<template>
  <div class="teacher-page">
    <section class="teacher-page-head">
      <div>
        <h2 class="teacher-page-head__title">试卷信息详情</h2>
        <p class="teacher-page-head__desc">统一展示考试摘要、考试介绍和考生须知，避免旧式大色块和固定高度布局。</p>
      </div>
    </section>

    <section class="teacher-panel teacher-panel--padded">
      <div class="teacher-detail-summary">
        <div class="teacher-detail-summary__item">
          <span class="teacher-detail-summary__label">考试科目</span>
          <span class="teacher-detail-summary__value">{{this.form.course.name}}</span>
        </div>
        <div class="teacher-detail-summary__item">
          <span class="teacher-detail-summary__label">考试开始时间</span>
          <span class="teacher-detail-summary__value">{{this.form.examDate}}</span>
        </div>
        <div class="teacher-detail-summary__item">
          <span class="teacher-detail-summary__label">考试时长</span>
          <span class="teacher-detail-summary__value">{{this.form.totalTime}} 分钟</span>
        </div>
        <div class="teacher-detail-summary__item">
          <span class="teacher-detail-summary__label">总分</span>
          <span class="teacher-detail-summary__value">{{this.form.totalScore}} 分</span>
        </div>
        <div class="teacher-detail-summary__item">
          <span class="teacher-detail-summary__label">课程编码</span>
          <span class="teacher-detail-summary__value">{{this.form.course.number}}</span>
        </div>
        <div class="teacher-detail-summary__item">
          <span class="teacher-detail-summary__label">考试类型</span>
          <span class="teacher-detail-summary__value">{{this.form.type}}</span>
        </div>
        <div class="teacher-detail-summary__item">
          <span class="teacher-detail-summary__label">出题教师</span>
          <span class="teacher-detail-summary__value">{{this.form.teacher.name}}</span>
        </div>
      </div>
    </section>

    <div class="teacher-dashboard-grid">
      <section class="teacher-panel teacher-panel--padded">
        <div class="teacher-panel__head">
          <div>
            <h3 class="teacher-panel__title">考试介绍</h3>
            <p class="teacher-panel__desc">考试内容说明和组织方式。</p>
          </div>
        </div>
        <div class="teacher-detail-rich">
          <div v-html="this.form.description" class="w-e-text"></div>
        </div>
      </section>

      <section class="teacher-panel teacher-panel--padded">
        <div class="teacher-panel__head">
          <div>
            <h3 class="teacher-panel__title">考生须知</h3>
            <p class="teacher-panel__desc">考试纪律、答题要求和注意事项。</p>
          </div>
        </div>
        <div class="teacher-detail-rich">
          <div v-html="this.form.tips" class="w-e-text"></div>
        </div>
      </section>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  data(){
    return{
      user:localStorage.getItem("user") ? (JSON).parse(localStorage.getItem("user")) : {},
      receivedData:"",
      form:{}
    }
  },
  created() {
    this.receivedData = this.$route.params
    this.load()
  },
  mounted() {

  },
  methods: {
    load(){
      request.get("exam/examManage/selectById/"+this.receivedData.id).then(res=>{
        if (res.code === '200') {
          this.form=res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
  }
}
</script>

<style scoped>
.teacher-page .teacher-detail-summary {
  grid-template-columns: repeat(3, minmax(0, 1fr));
}

.teacher-page .teacher-detail-summary__item:nth-child(2) {
  grid-column: span 2;
}

.teacher-page .teacher-detail-summary__item {
  min-height: 96px;
}

@media (max-width: 1280px) {
  .teacher-page .teacher-detail-summary {
    grid-template-columns: 1fr;
  }

  .teacher-page .teacher-detail-summary__item:nth-child(2) {
    grid-column: auto;
  }
}
</style>
