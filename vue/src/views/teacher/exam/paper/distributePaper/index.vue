<template>
  <div class="distribute-page">
    <section class="dispatch-hero">
      <div class="hero-copy">
        <div class="hero-eyebrow">MARKING DISPATCH</div>
        <h2>阅卷分配</h2>
      </div>

      <div class="hero-panel">
        <div class="metric-row">
          <div class="metric-card">
            <span>已经分配</span>
            <strong>{{ examTeacherGradingNum }}</strong>
          </div>
          <div class="metric-card">
            <span>题目总数</span>
            <strong>{{ questionNum }}</strong>
          </div>
          <div class="metric-card">
            <span>分配进度</span>
            <strong>{{ assignPercent }}%</strong>
          </div>
        </div>
        <div class="progress-track">
          <div class="progress-fill" :style="{ width: assignPercent + '%' }"></div>
        </div>
        <div class="hero-actions">
          <el-button type="primary" @click="examTeacherGradingAdd">分配题目</el-button>
          <el-button type="danger" plain @click="examTeacherGradingDelete">全部删除</el-button>
        </div>
      </div>
    </section>

    <section class="question-board">
      <div
          v-for="group in questionGroups"
          :key="group.key"
          class="question-section"
      >
        <div class="question-section__head">
          <div>
            <h3>{{ group.title }}</h3>
            <p>当前共 {{ group.list.length }} 题</p>
          </div>
          <span class="section-count">{{ group.list.length }}</span>
        </div>

        <div v-if="group.list.length" class="question-grid">
          <article
              v-for="(data, index) in group.list"
              :key="group.key + '-' + index"
              class="question-card"
          >
            <div class="question-card__top">
              <span>第 {{ index + 1 }} 题</span>
              <strong v-if="data[group.field]">{{ data[group.field].score }} 分</strong>
            </div>
            <div
                v-if="data[group.field]"
                v-html="data[group.field].question"
                class="w-e-text question-card__content"
            ></div>
          </article>
        </div>

        <div v-else class="question-empty">
          暂无{{ group.title }}
        </div>
      </div>
    </section>

    <el-drawer
        title="教师列表"
        :visible.sync="drawer"
        custom-class="dispatch-drawer"
        direction="rtl"
        size="68%"
        :before-close="handleClose">
      <div class="drawer-panel">
        <div class="drawer-toolbar">
          <div>
            <div class="drawer-kicker">选择阅卷教师</div>
            <h3>本次已选 {{ selectedTeacherCount }} 位教师</h3>
          </div>
          <el-button type="success" @click="addPlay()">确认分配</el-button>
        </div>

        <div class="drawer-search">
          <el-form :inline="true" :model="params" class="teacher-search-form">
            <el-form-item label="账号">
              <el-input v-model="params.account" clearable placeholder="账号"></el-input>
            </el-form-item>
            <el-form-item label="姓名">
              <el-input v-model="params.name" clearable placeholder="姓名"></el-input>
            </el-form-item>
            <el-form-item label="性别">
              <el-input v-model="params.sex" clearable placeholder="性别"></el-input>
            </el-form-item>
            <el-form-item label="电话">
              <el-input v-model="params.phone" clearable placeholder="电话"></el-input>
            </el-form-item>
            <el-form-item label="邮箱">
              <el-input v-model="params.email" clearable placeholder="电子邮箱"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" plain @click="findTeacherAll">查询</el-button>
            </el-form-item>
          </el-form>

          <div class="date-row">
            <span>考试期限</span>
            <el-date-picker
                v-model="dataTime"
                @change="times"
                type="datetimerange"
                :picker-options="pickerOptions"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                align="right">
            </el-date-picker>
          </div>
        </div>

        <div class="teacher-table-wrap">
          <el-table
              ref="table"
              class="teacher-table"
              :data="teacherList"
              tooltip-effect="dark"
              border
              show-overflow-tooltip
              stripe
              style="width: 100%"
              height="48vh"
              @selection-change="handleSelectionChange"
              :row-key="getRowKeys"
          >
            <el-table-column ref="table" type="selection" width="55" align="center" :reserve-selection="true" fixed>
            </el-table-column>
            <el-table-column prop="id" label="教师id" width="100" align="center" fixed></el-table-column>
            <el-table-column prop="name" label="姓名" width="100" align="center" fixed></el-table-column>
            <el-table-column prop="photo" label="照片" width="100" align="center">
              <template v-slot="scope">
                <el-image
                    class="teacher-avatar"
                    :src="scope.row.photo"
                    :preview-src-list="[scope.row.photo]">
                </el-image>
              </template>
            </el-table-column>
            <el-table-column prop="role" label="角色" width="100" align="center"></el-table-column>
            <el-table-column prop="sex" label="性别" width="100" align="center"></el-table-column>
            <el-table-column prop="email" label="电子邮箱" width="200" align="center"></el-table-column>
            <el-table-column prop="phone" label="电话" width="200" align="center"></el-table-column>
            <el-table-column prop="account" label="账号" width="200" align="center"></el-table-column>
            <el-table-column label="密码" width="250" align="center">
              <template slot-scope="scope">
                <el-input
                    class="table-textarea"
                    type="textarea"
                    :readonly="true"
                    :autosize="{ minRows: 2, maxRows: 2}"
                    placeholder=""
                    v-model="scope.row.password">
                </el-input>
              </template>
            </el-table-column>
            <el-table-column label="密码摘要" width="250" align="center">
              <template slot-scope="scope">
                <el-input
                    class="table-textarea"
                    type="textarea"
                    :readonly="true"
                    :autosize="{ minRows: 2, maxRows: 2}"
                    placeholder=""
                    v-model="scope.row.keySm3">
                </el-input>
              </template>
            </el-table-column>
            <el-table-column label="考试公钥" width="250" align="center">
              <template slot-scope="scope">
                <el-input
                    class="table-textarea"
                    type="textarea"
                    :readonly="true"
                    :autosize="{ minRows: 2, maxRows: 2}"
                    placeholder=""
                    v-model="scope.row.publicKey">
                </el-input>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  data() {
    return {
      user: localStorage.getItem("user") ? (JSON).parse(localStorage.getItem("user")) : {},
      form: {},
      receivedData: "",
      questionCode: [],
      questionFill: [],
      questionMulti: [],
      questionShortAns: [],
      questionJudge: [],
      examTeacherGrading: [],
      examTeacherGradingNum: 0,
      questionNum: 0,
      drawer: false,
      teacherList: [],
      ids: [],
      params: {},
      dataTime:[],
      pickerOptions: {
        shortcuts: [
          {
            text: '未来十二小时',
            onClick(picker) {
              const start = new Date();
              const end = new Date();
              end.setTime(end.getTime() + 3600 * 1000 * 12);
              picker.$emit('pick', [start, end]);
            }
          },
          {
            text: '未来一天',
            onClick(picker) {
              const start = new Date();
              const end = new Date();
              end.setTime(end.getTime() + 3600 * 1000 * 24);
              picker.$emit('pick', [start, end]);
            }
          },
          {
            text: '未来三天',
            onClick(picker) {
              const start = new Date();
              const end = new Date();
              end.setTime(end.getTime() + 3600 * 1000 * 24 * 3);
              picker.$emit('pick', [start, end]);
            }
          },
          {
            text: '未来一周',
            onClick(picker) {
              const start = new Date();
              const end = new Date();
              end.setTime(end.getTime() + 3600 * 1000 * 24 * 7);
              picker.$emit('pick', [start, end]);
            }
          },
          {
            text: '未来一个月',
            onClick(picker) {
              const start = new Date();
              const end = new Date();
              // 简单假设一个月 30 天
              end.setTime(end.getTime() + 3600 * 1000 * 24 * 30);
              picker.$emit('pick', [start, end]);
            }
          },
          {
            text: '未来三个月',
            onClick(picker) {
              const start = new Date();
              const end = new Date();
              // 简单假设三个月 90 天
              end.setTime(end.getTime() + 3600 * 1000 * 24 * 90);
              picker.$emit('pick', [start, end]);
            }
          }
        ]
      },
      dataTimes:{}
    };
  },
  created() {
    this.receivedData = this.$route.params;
    this.findQuestion();
  },
  mounted() {},
  computed: {
    assignPercent() {
      if (!this.questionNum) {
        return 0;
      }
      return Math.min(100, Math.round((this.examTeacherGradingNum / this.questionNum) * 100));
    },
    questionGroups() {
      return [
        { key: 'multi', title: '选择题', list: this.questionMulti, field: 'questionMulti' },
        { key: 'judge', title: '判断题', list: this.questionJudge, field: 'questionJudge' },
        { key: 'fill', title: '填空题', list: this.questionFill, field: 'questionFill' },
        { key: 'shortAns', title: '简答题', list: this.questionShortAns, field: 'questionShortAns' },
        { key: 'code', title: '代码题', list: this.questionCode, field: 'questionCode' }
      ];
    },
    selectedTeacherCount() {
      return this.ids.length;
    }
  },
  methods: {
    times(){
      this.dataTimes.beginTime=this.time(this.dataTime[0])
      this.dataTimes.endTime=this.time(this.dataTime[1])
    },
    time(dataTime){
      // 假设 this.form.violationTime 是日期字符串或者时间戳
      const date = new Date(dataTime);
// 提取年、月、日、时、分、秒信息
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, '0'); // 月份从 0 开始，所以要加 1
      const day = String(date.getDate()).padStart(2, '0');
      const hours = String(date.getHours()).padStart(2, '0');
      const minutes = String(date.getMinutes()).padStart(2, '0');
      const seconds = String(date.getSeconds()).padStart(2, '0');
// 按照 YYYY-MM-DD HH:mm:ss 格式拼接字符串
      return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
    },
    //id为examManager的id
    findQuestion() {
      this.findQuestionFill();
      this.findQuestionCode();
      this.findQuestionJudge();
      this.findQuestionMulti();
      this.findQuestionShortAns();
      this.findExamTeacherGrading();
    },
    findQuestionCode() {
      let params;
      params = {
        examId: this.receivedData.id,
        questionType: "code"
      };
      request.get('/exam/examPaper/selectAll', {
        params: params
      }).then(res => {
        if (res.code === '200') {
          this.questionCode = res.data;
          this.questionNum = this.questionCode.length + this.questionNum;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findQuestionFill() {
      let params;
      params = {
        examId: this.receivedData.id,
        questionType: "fill"
      };
      request.get('/exam/examPaper/selectAll', {
        params: params
      }).then(res => {
        if (res.code === '200') {
          this.questionFill = res.data;
          this.questionNum = this.questionFill.length + this.questionNum;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findQuestionJudge() {
      let params;
      params = {
        examId: this.receivedData.id,
        questionType: "judge"
      };
      request.get('/exam/examPaper/selectAll', {
        params: params
      }).then(res => {
        if (res.code === '200') {
          this.questionJudge = res.data;
          this.questionNum = this.questionJudge.length + this.questionNum;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findQuestionMulti() {
      let params;
      params = {
        examId: this.receivedData.id,
        questionType: "multi"
      };
      request.get('/exam/examPaper/selectAll', {
        params: params
      }).then(res => {
        if (res.code === '200') {
          this.questionMulti = res.data;
          this.questionNum = this.questionMulti.length + this.questionNum;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findQuestionShortAns() {
      let params;
      params = {
        examId: this.receivedData.id,
        questionType: "shortAns"
      };
      request.get('/exam/examPaper/selectAll', {
        params: params
      }).then(res => {
        if (res.code === '200') {
          this.questionShortAns = res.data;
          this.questionNum = this.questionShortAns.length + this.questionNum;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findExamTeacherGrading() {
      let params;
      params = {
        examId: this.receivedData.id,
        studentId: this.user.id
      };
      request.get('/exam/examTeacherGrading/selectAll', {
        params: params
      }).then(res => {
        if (res.code === '200') {
          this.examTeacherGrading = res.data;
          this.examTeacherGradingNum = this.examTeacherGrading.length;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    //先查询试卷 5个题库
    //查询分配表，通过examId进行查询
    //如果分配表里有数据，则无法全部分配//首先进行删除操作，再次进行分配，如果没数据。则可以直接分配

    handleSelectionChange(val) {
      this.multipleSelection = val;
      // 获取选中数据的id
      this.ids = val.map(item => item.id);
    },
    getRowKeys(row) {
      return row.id;
    },
    findTeacher() {
      request.get('/user/teacher/selectAll').then(res => {
        if (res.code === '200') {
          this.teacherList = res.data;
          // 自动选择指定行
          this.selectInitialRows();
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findTeacherAll() {
      request.get('/user/teacher/selectAll', {
        params: this.params
      }).then(res => {
        if (res.code === '200') {
          this.teacherList = res.data;
          // 自动选择指定行
          this.selectInitialRows();
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    selectAll() {
      this.$refs.table.toggleAllSelection();
    },
    DeselectAll() {
      this.$refs.table.clearSelection();
      this.ids = [];
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
    },
    examTeacherGradingAdd() {
      if (this.examTeacherGradingNum!== 0 && this.examTeacherGradingNum!== this.questionNum) {
        this.$message.error("部分题目已经分配，请删除后重新分配");
      } else if (this.examTeacherGradingNum!== 0 && this.examTeacherGradingNum === this.questionNum) {
        this.$message.warning("题目已经全部分配，无需再次分配");
      } else {
        this.ids = [this.user.id];
        this.findTeacher();
        this.drawer = true;
      }
    },
    addPlay() {
      let params;
      params={
        beginTime:this.dataTimes.beginTime,
        endTime:this.dataTimes.endTime,
        ids:this.ids
      }
      request.post("exam/examTeacherGrading/addPlay/" + this.receivedData.id, params).then(res => {
        if (res.code === '200') {
          this.findExamTeacherGrading();
          this.drawer = false;
          this.$message.success("分配成功")
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    examTeacherGradingDelete() {
      if (this.examTeacherGradingNum === 0) {
        this.$message.error("暂无分配，无需删除");
      } else if (this.examTeacherGradingNum === this.questionNum) {
        this.$message.warning("题目已经全部分配，无需删除");
      } else {
        this.$confirm('您确定删除吗？', '确认删除', { type: "warning" }).then(response => {
          request.delete('/exam/examTeacherGrading/deleteByExamId/' + this.receivedData.id).then(res => {
            if (res.code === '200') {   // 表示操作成功
              this.$message.success('删除成功');
              this.findExamTeacherGrading();
            } else {
              this.$message.error(res.msg);  // 弹出错误的信息
            }
          });
        }).catch(() => {});
      }
    },
    selectInitialRows() {
      // 遍历 teacherList 中的每一行
      this.teacherList.forEach(row => {
        // 检查当前行的 id 是否在 ids 数组中
        if (this.ids.includes(row.id)) {
          // 如果存在，则选中该行
          this.$refs.table.toggleRowSelection(row, true);
        }
      });
    }
  }
};
</script>

<style scoped>
.distribute-page {
  min-height: 0;
  padding: 8px 14px;
  color: #111827;
  background: #f4f7fa;
}

.dispatch-hero {
  display: grid;
  grid-template-columns: minmax(260px, 0.72fr) minmax(460px, 1fr);
  gap: 14px;
  padding: 12px;
  border: 1px solid #dbe4ee;
  border-radius: 8px;
  background: #ffffff;
  box-shadow: 0 14px 34px rgba(15, 23, 42, 0.08);
}

.hero-copy {
  display: flex;
  min-width: 0;
  flex-direction: column;
  justify-content: center;
}

.hero-eyebrow {
  margin-bottom: 6px;
  color: #0f766e;
  font-size: 12px;
  font-weight: 800;
  letter-spacing: 0.08em;
}

.hero-copy h2 {
  margin: 0;
  color: #111827;
  font-size: 26px;
  line-height: 1.2;
}

.hero-copy p {
  max-width: 560px;
  margin: 8px 0 0;
  color: #64748b;
  font-size: 15px;
  line-height: 1.7;
}

.hero-panel {
  min-width: 0;
  padding: 12px;
  border: 1px solid #dbe4ee;
  border-radius: 8px;
  background: #f8fafc;
}

.metric-row {
  display: grid;
  grid-template-columns: repeat(3, minmax(0, 1fr));
  gap: 10px;
}

.metric-card {
  min-width: 0;
  padding: 10px 12px;
  border: 1px solid #dbe4ee;
  border-radius: 8px;
  background: #ffffff;
}

.metric-card span {
  display: block;
  color: #64748b;
  font-size: 13px;
}

.metric-card strong {
  display: block;
  margin-top: 5px;
  color: #0f766e;
  font-size: 26px;
  line-height: 1;
}

.progress-track {
  height: 10px;
  margin-top: 10px;
  overflow: hidden;
  border-radius: 8px;
  background: #e2e8f0;
}

.progress-fill {
  height: 100%;
  border-radius: 8px;
  background: #059669;
  transition: width 0.2s ease;
}

.hero-actions {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
  margin-top: 10px;
}

.question-board {
  display: grid;
  grid-template-columns: repeat(3, minmax(0, 1fr));
  gap: 10px;
  margin-top: 10px;
}

.question-section {
  min-width: 0;
  overflow: hidden;
  border: 1px solid #dbe4ee;
  border-radius: 8px;
  background: #ffffff;
}

.question-section__head {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 12px;
  padding: 10px 12px;
  border-bottom: 1px solid #e5e7eb;
  background: #ffffff;
}

.question-section__head h3 {
  margin: 0;
  color: #111827;
  font-size: 17px;
}

.question-section__head p {
  margin: 3px 0 0;
  color: #64748b;
  font-size: 13px;
}

.section-count {
  display: inline-flex;
  min-width: 34px;
  height: 28px;
  align-items: center;
  justify-content: center;
  border-radius: 8px;
  color: #075985;
  font-weight: 800;
  background: #e0f2fe;
}

.question-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(260px, 1fr));
  gap: 10px;
  max-height: 190px;
  padding: 10px;
  overflow-y: auto;
}

.question-card {
  min-width: 0;
  min-height: 104px;
  max-height: 156px;
  padding: 10px;
  overflow-y: auto;
  border: 1px solid #dbe4ee;
  border-radius: 8px;
  background: #f8fafc;
  transition: border-color 0.2s ease, box-shadow 0.2s ease, transform 0.2s ease;
}

.question-card:hover {
  border-color: #34d399;
  box-shadow: 0 10px 20px rgba(15, 23, 42, 0.08);
  transform: translateY(-1px);
}

.question-card__top {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 10px;
  margin-bottom: 8px;
}

.question-card__top span {
  color: #475569;
  font-size: 13px;
  font-weight: 700;
}

.question-card__top strong {
  flex: 0 0 auto;
  color: #be123c;
  font-size: 14px;
}

.question-card__content {
  color: #1f2937;
  font-size: 16px;
  line-height: 1.6;
  word-break: break-word;
}

.question-empty {
  padding: 20px 12px;
  color: #94a3b8;
  font-size: 15px;
  text-align: center;
  background: #f8fafc;
}

.drawer-panel {
  display: flex;
  height: 100%;
  min-height: 0;
  flex-direction: column;
  gap: 14px;
  padding: 0 20px 20px;
  background: #f8fafc;
}

.drawer-toolbar {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 14px;
  padding: 14px;
  border: 1px solid #dbe4ee;
  border-radius: 8px;
  background: #ffffff;
}

.drawer-kicker {
  margin-bottom: 4px;
  color: #0f766e;
  font-size: 12px;
  font-weight: 800;
}

.drawer-toolbar h3 {
  margin: 0;
  color: #111827;
  font-size: 20px;
}

.drawer-search {
  padding: 14px 14px 12px;
  border: 1px solid #dbe4ee;
  border-radius: 8px;
  background: #ffffff;
}

.teacher-search-form {
  display: flex;
  flex-wrap: wrap;
  gap: 0 10px;
}

.teacher-search-form :deep(.el-form-item) {
  margin-right: 0;
  margin-bottom: 10px;
}

.date-row {
  display: flex;
  align-items: center;
  gap: 12px;
  padding-top: 10px;
  border-top: 1px solid #e5e7eb;
}

.date-row span {
  color: #475569;
  font-size: 15px;
  font-weight: 700;
}

.teacher-table-wrap {
  min-height: 0;
  overflow: hidden;
  border: 1px solid #dbe4ee;
  border-radius: 8px;
  background: #ffffff;
}

.teacher-avatar {
  width: 40px;
  height: 40px;
  border-radius: 50%;
}

.table-textarea {
  font-size: 16px;
}

:deep(.dispatch-drawer .el-drawer__body) {
  overflow: hidden;
  background: #f8fafc;
}

:deep(.dispatch-drawer .el-drawer__header) {
  margin-bottom: 14px;
  padding: 20px 20px 0;
  color: #111827;
  font-size: 18px;
  font-weight: 800;
}

:deep(.el-button) {
  border-radius: 8px;
}

:deep(.el-input__inner),
:deep(.el-textarea__inner) {
  border-radius: 8px;
}

@media (max-width: 980px) {
  .question-board {
    grid-template-columns: repeat(2, minmax(0, 1fr));
  }
}

@media (max-width: 1180px) {
  .dispatch-hero {
    grid-template-columns: 1fr;
  }

  .hero-copy p {
    max-width: none;
  }
}

@media (max-width: 760px) {
  .distribute-page {
    padding: 12px;
  }

  .metric-row {
    grid-template-columns: 1fr;
  }

  .hero-actions,
  .drawer-toolbar,
  .date-row {
    align-items: stretch;
    flex-direction: column;
  }

  .question-grid {
    grid-template-columns: 1fr;
  }
}
</style>
