<template>
  <div class="teacher-mark-paper-list-page">
    <section class="teacher-mark-paper-list-page__head">
      <div>
        <div class="teacher-mark-paper-list-page__eyebrow">Paper Grading</div>
        <h2 class="teacher-mark-paper-list-page__title">批阅试卷列表</h2>
      </div>
      <div class="teacher-mark-paper-list-page__meta">
        <span class="teacher-mark-paper-list-page__badge">共 {{ tableData.length }} 场待处理考试</span>
      </div>
    </section>

    <section class="teacher-mark-paper-list-page__board">
      <el-row v-if="visit!==-1&&visit!==0" :gutter="22">
        <el-col :span="8" v-for="(item, index) in tableData" :key="index">
          <el-card class="teacher-mark-paper-card" :body-style="{ padding: '0px' }">
            <div class="teacher-mark-paper-card__body">
              <div class="teacher-mark-paper-card__top">
                <div>
                  <span class="teacher-mark-paper-card__label">考试科目</span>
                  <h3 class="teacher-mark-paper-card__course">{{ item.course.name }}</h3>
                </div>
                <span class="teacher-mark-paper-card__code">{{ item.course.number }}</span>
              </div>

              <div class="teacher-mark-paper-card__grid">
                <div class="teacher-mark-paper-card__item teacher-mark-paper-card__item--wide">
                  <span>考试开始时间</span>
                  <strong>{{ item.examDate }}</strong>
                </div>
                <div class="teacher-mark-paper-card__item">
                  <span>考试类型</span>
                  <strong>{{ item.type }}</strong>
                </div>
                <div class="teacher-mark-paper-card__item">
                  <span>考试时长</span>
                  <strong>{{ item.totalTime }} 分钟</strong>
                </div>
                <div class="teacher-mark-paper-card__item">
                  <span>总分</span>
                  <strong>{{ item.totalScore }} 分</strong>
                </div>
                <div class="teacher-mark-paper-card__item">
                  <span>出题人</span>
                  <strong>{{ item.teacher.name }}</strong>
                </div>
                <div class="teacher-mark-paper-card__item teacher-mark-paper-card__item--wide">
                  <span>阅卷开始时间</span>
                  <strong>{{ item.beginTime }}</strong>
                </div>
                <div class="teacher-mark-paper-card__item teacher-mark-paper-card__item--wide">
                  <span>阅卷结束时间</span>
                  <strong>{{ item.endTime }}</strong>
                </div>
              </div>
            </div>

            <div class="teacher-mark-paper-card__actions">
              <el-button type="text" @click="showPublicKeys(item.id)" size="small">查看环公钥</el-button>
              <el-button type="text" @click="showManager(item)" size="small">查看详情</el-button>
              <el-button type="text" @click="beginExam(item.id)" size="small">进入阅卷</el-button>
            </div>
          </el-card>
        </el-col>
      </el-row>

      <div v-if="visit===-1" class="teacher-mark-paper-list-page__empty teacher-mark-paper-list-page__empty--primary">
        请先选择课程进行考试查询
      </div>
      <div v-if="visit===0" class="teacher-mark-paper-list-page__empty teacher-mark-paper-list-page__empty--success">
        该课程还未发布试卷
      </div>
    </section>
  </div>
</template>

<script>
import request from "@/utils/request"
export default {
  data(){
    return{
      user:localStorage.getItem("user") ? (JSON).parse(localStorage.getItem("user")) : {},
      form:{},
      tableData: [],
      pageNum: 1,   // 当前的页码
      pageSize: 6,  // 每页显示的个数
      total: 0,
      course:[],
      visit:-1,
    }
  },
  created() {
    this.findBySearch()
  },
  mounted() {

  },
  methods: {
    findBySearch(){
      let params;
      params={
        teacherId:this.user.id
      }
      request.get('/exam/examTeacherGrading/selectExam', {
        params:params
      }).then(res => {
        if (res.code === '200') {
          this.tableData=res.data
          this.visit=this.tableData.length
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    showManager(row){
      this.$router.push({
        name: "teacherShowExamMsg",
        params: {
          id:row.id
        }
      });
    },
    showPublicKeys(id){
      this.$router.push({
        name: "teacherShowPublicKeys",
        params: {
          id:id
        }
      });
    },
    beginExam(id){
      this.$router.push({
        name: "teacherShowPaper",
        params: {
          id:id
        }
      });
    },
    beginShowExam(id){
      this.$router.push({
        name: "studentShowTips",
        params: {
          id:id
        }
      });
    }
  }
}
</script>


<style scoped>
.teacher-mark-paper-list-page {
  display: flex;
  flex-direction: column;
  gap: 18px;
  padding-bottom: 10px;
}

.teacher-mark-paper-list-page__head {
  display: flex;
  align-items: flex-end;
  justify-content: space-between;
  gap: 16px;
}

.teacher-mark-paper-list-page__eyebrow {
  margin-bottom: 6px;
  font-size: 12px;
  font-weight: 700;
  letter-spacing: 0.12em;
  text-transform: uppercase;
  color: #0f8b84;
}

.teacher-mark-paper-list-page__title {
  margin: 0;
  font-size: 28px;
  font-weight: 800;
  color: #0f172a;
}

.teacher-mark-paper-list-page__desc {
  margin: 6px 0 0;
  font-size: 14px;
  color: #64748b;
}

.teacher-mark-paper-list-page__meta {
  display: flex;
  justify-content: flex-end;
}

.teacher-mark-paper-list-page__badge {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  min-height: 36px;
  padding: 0 16px;
  border-radius: 999px;
  background: linear-gradient(135deg, #eff6ff 0%, #dbeafe 100%);
  color: #1d4ed8;
  font-size: 13px;
  font-weight: 700;
}

.teacher-mark-paper-list-page__board {
  min-height: 640px;
  padding: 24px;
  border: 1px solid rgba(219, 231, 243, 0.92);
  border-radius: 26px;
  background:
    linear-gradient(180deg, rgba(255, 255, 255, 0.98), rgba(248, 250, 252, 0.96)),
    linear-gradient(135deg, rgba(219, 234, 254, 0.14), transparent 38%);
  box-shadow: 0 22px 52px rgba(15, 23, 42, 0.08);
}

.teacher-mark-paper-card {
  margin-bottom: 22px;
  border-radius: 24px;
  overflow: hidden;
  border: 1px solid #dbe4ee;
  box-shadow: 0 18px 40px rgba(15, 23, 42, 0.08);
}

.teacher-mark-paper-card__body {
  padding: 22px 22px 18px;
}

.teacher-mark-paper-card__top {
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
  gap: 16px;
  margin-bottom: 16px;
}

.teacher-mark-paper-card__label,
.teacher-mark-paper-card__item span {
  display: block;
  font-size: 12px;
  color: #64748b;
}

.teacher-mark-paper-card__course {
  margin: 6px 0 0;
  font-size: 22px;
  line-height: 1.35;
  color: #0f172a;
}

.teacher-mark-paper-card__code {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  min-height: 34px;
  padding: 0 14px;
  border-radius: 999px;
  background: #f8fafc;
  border: 1px solid #e2e8f0;
  color: #334155;
  font-size: 13px;
  font-weight: 700;
}

.teacher-mark-paper-card__grid {
  display: grid;
  grid-template-columns: repeat(2, minmax(0, 1fr));
  gap: 14px;
}

.teacher-mark-paper-card__item {
  display: flex;
  flex-direction: column;
  gap: 8px;
  padding: 14px 16px;
  border-radius: 18px;
  background: linear-gradient(180deg, #f8fbff 0%, #f1f5f9 100%);
  border: 1px solid #e2e8f0;
}

.teacher-mark-paper-card__item strong {
  font-size: 15px;
  line-height: 1.6;
  color: #0f172a;
  word-break: break-word;
}

.teacher-mark-paper-card__item--wide {
  grid-column: span 2;
}

.teacher-mark-paper-card__actions {
  display: flex;
  justify-content: center;
  gap: 12px;
  padding: 16px 18px 20px;
  border-top: 1px solid #e2e8f0;
  background: linear-gradient(180deg, rgba(248, 250, 252, 0.8), #ffffff);
}

.teacher-mark-paper-card__actions :deep(.el-button) {
  font-size: 14px;
  font-weight: 700;
  color: #0f8b84;
}

.teacher-mark-paper-list-page__empty {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 460px;
  border-radius: 22px;
  border: 1px dashed #cbd5e1;
  background: linear-gradient(180deg, #ffffff 0%, #f8fafc 100%);
  text-align: center;
  padding: 28px;
  font-size: 28px;
  font-weight: 800;
}

.teacher-mark-paper-list-page__empty--primary {
  color: #1d4ed8;
}

.teacher-mark-paper-list-page__empty--success {
  color: #0f766e;
}

@media (max-width: 1280px) {
  .teacher-mark-paper-list-page__head {
    align-items: flex-start;
    flex-direction: column;
  }

  .teacher-mark-paper-card__grid {
    grid-template-columns: 1fr;
  }

  .teacher-mark-paper-card__item--wide {
    grid-column: span 1;
  }
}
</style>

