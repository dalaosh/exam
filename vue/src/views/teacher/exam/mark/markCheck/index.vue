<template>
  <div class="main">
    <section class="mark-hero">
      <div class="hero-main">
        <div>
          <div class="hero-kicker">批卷验签</div>
          <h2>先完成批阅与验签，再进入成绩统计</h2>
        </div>
        <span class="approval-pill" :class="{ done: isExamConfirmed }">
          {{ approvalStatusText }}
        </span>
      </div>

      <div class="hero-metrics">
        <div class="metric-item">
          <span>已批阅</span>
          <strong>{{ totalCount.isMarkCount }}</strong>
        </div>
        <div class="metric-item warning">
          <span>未批阅</span>
          <strong>{{ totalCount.noMarkCount }}</strong>
        </div>
        <div class="metric-item">
          <span>验签成功</span>
          <strong>{{ totalCount.isRightSignCount }}</strong>
        </div>
        <div class="metric-item warning">
          <span>待验签</span>
          <strong>{{ totalCount.noRightSignCount }}</strong>
        </div>
      </div>

      <div class="command-bar">
        <el-button type="primary" @click="batchSetRightSign">全部验签</el-button>
        <el-button plain @click="newLoad">刷新</el-button>
        <el-button plain @click="submitIsMarks">完成确认</el-button>
        <el-button plain @click="markStatistic">成绩统计</el-button>
      </div>
    </section>

    <div class="overview-grid">
      <button
          v-for="item in questionTypeStats"
          :key="item.value"
          class="type-card"
          :class="{ active: activeType === item.value }"
          type="button"
          @click="activeType = item.value"
      >
        <span class="type-name">{{ item.label }}</span>
        <span class="type-row">
          <span>批阅</span>
          <strong>{{ item.isMarkCount }}</strong>
          <em>/</em>
          <strong class="warn">{{ item.noMarkCount }}</strong>
        </span>
        <span class="type-row">
          <span>验签</span>
          <strong>{{ item.isRightSignCount }}</strong>
          <em>/</em>
          <strong class="warn">{{ item.noRightSignCount }}</strong>
        </span>
      </button>
    </div>

    <el-tabs v-model="activeType" class="mark-check-tabs" type="border-card">
      <el-tab-pane label="选择题" name="multi">
        <div class="questions">
          <el-row>
            <el-col :span="6">
              <div class="filter-panel">
                <div class="panel-head">
                  <span>选择题处理</span>
                  <small>{{ questionMulti.length }} 条</small>
                </div>
                <el-button class="auto-action" type="primary" @click="changeMultiAll()">自动批阅选择题</el-button>
                <div class="filter-title">筛选范围</div>
                <el-radio-group v-model="filters.multi" class="filter-group" @change="handleFilterChange('multi', $event)">
                  <el-radio-button
                      v-for="item in filterOptions"
                      :key="item.value"
                      :label="item.value"
                  >{{ item.label }}</el-radio-button>
                </el-radio-group>
              </div>
            </el-col>
            <el-col :span="18">
              <div class="rightMain">
                <el-card v-for="(data, index) in questionMulti" :key="index" shadow="hover" class="cardMain">
                  <div class="card-score">{{ data.questionMulti.score }}分</div>
                  <el-row>
                    <el-col :span="2">{{ index + 1 }}、</el-col>
                    <el-col :span="22">
                      <div v-html="data.questionMulti.question" class="w-e-text divs"></div>
                    </el-col>
                    <el-col :span="3" class="option-key">A、</el-col>
                    <el-col :span="21"><div v-html="data.questionMulti.answerA" class="w-e-text divs"></div></el-col>
                    <el-col :span="3" class="option-key">B、</el-col>
                    <el-col :span="21"><div v-html="data.questionMulti.answerB" class="w-e-text divs"></div></el-col>
                    <el-col :span="3" class="option-key">C、</el-col>
                    <el-col :span="21"><div v-html="data.questionMulti.answerC" class="w-e-text divs"></div></el-col>
                    <el-col :span="3" class="option-key">D、</el-col>
                    <el-col :span="21"><div v-html="data.questionMulti.answerD" class="w-e-text divs"></div></el-col>
                  </el-row>
                  <el-row class="card-actions">
                    <div class="card-actions__inner">
                      <el-button v-if="marks(data)" disabled type="text" size="small">批阅完成</el-button>
                      <el-button v-else type="text" size="small" @click="showAnswer(data)">进行批阅</el-button>
                      <el-button v-if="sign(data)" disabled type="text" size="small">验签完成</el-button>
                      <el-button v-else type="text" size="small" @click="setRightSign(data)">进行验签</el-button>
                    </div>
                  </el-row>
                </el-card>
              </div>
            </el-col>
          </el-row>
        </div>
      </el-tab-pane>

      <el-tab-pane label="填空题" name="fill">
        <div class="questions">
          <el-row>
            <el-col :span="6">
              <div class="filter-panel">
                <div class="panel-head">
                  <span>填空题处理</span>
                  <small>{{ questionFill.length }} 条</small>
                </div>
                <div class="filter-title">筛选范围</div>
                <el-radio-group v-model="filters.fill" class="filter-group" @change="handleFilterChange('fill', $event)">
                  <el-radio-button
                      v-for="item in filterOptions"
                      :key="item.value"
                      :label="item.value"
                  >{{ item.label }}</el-radio-button>
                </el-radio-group>
              </div>
            </el-col>
            <el-col :span="18">
              <div class="rightMain">
                <el-card v-for="(data, index) in questionFill" :key="index" shadow="hover" class="cardMain">
                  <div class="card-score">{{ data.questionFill.score }}分</div>
                  <el-row>
                    <el-col :span="2">{{ index + 1 }}、</el-col>
                    <el-col :span="22">
                      <div v-html="data.questionFill.question" class="w-e-text divs"></div>
                    </el-col>
                  </el-row>
                  <el-row class="card-actions">
                    <div class="card-actions__inner">
                      <el-button v-if="sign(data) && marks(data)" disabled type="text" size="small">批阅完成</el-button>
                      <el-button v-else type="text" size="small" @click="showAnswer(data)">进行批阅</el-button>
                      <el-button v-if="sign(data)" disabled type="text" size="small">验签完成</el-button>
                      <el-button v-else type="text" size="small" @click="setRightSign(data)">进行验签</el-button>
                    </div>
                  </el-row>
                </el-card>
              </div>
            </el-col>
          </el-row>
        </div>
      </el-tab-pane>

      <el-tab-pane label="判断题" name="judge">
        <div class="questions">
          <el-row>
            <el-col :span="6">
              <div class="filter-panel">
                <div class="panel-head">
                  <span>判断题处理</span>
                  <small>{{ questionJudge.length }} 条</small>
                </div>
                <el-button class="auto-action" type="primary" @click="changeJudgeAll()">自动批阅判断题</el-button>
                <div class="filter-title">筛选范围</div>
                <el-radio-group v-model="filters.judge" class="filter-group" @change="handleFilterChange('judge', $event)">
                  <el-radio-button
                      v-for="item in filterOptions"
                      :key="item.value"
                      :label="item.value"
                  >{{ item.label }}</el-radio-button>
                </el-radio-group>
              </div>
            </el-col>
            <el-col :span="18">
              <div class="rightMain">
                <el-card v-for="(data, index) in questionJudge" :key="index" shadow="hover" class="cardMain">
                  <div class="card-score">{{ data.questionJudge.score }}分</div>
                  <el-row>
                    <el-col :span="2">{{ index + 1 }}、</el-col>
                    <el-col :span="22">
                      <div v-html="data.questionJudge.question" class="w-e-text divs"></div>
                    </el-col>
                  </el-row>
                  <el-row class="card-actions">
                    <div class="card-actions__inner">
                      <el-button v-if="marks(data)" disabled type="text" size="small">批阅完成</el-button>
                      <el-button v-else type="text" size="small" @click="showAnswer(data)">进行批阅</el-button>
                      <el-button v-if="sign(data)" disabled type="text" size="small">验签完成</el-button>
                      <el-button v-else type="text" size="small" @click="setRightSign(data)">进行验签</el-button>
                    </div>
                  </el-row>
                </el-card>
              </div>
            </el-col>
          </el-row>
        </div>
      </el-tab-pane>

      <el-tab-pane label="简答题" name="shortAns">
        <div class="questions">
          <el-row>
            <el-col :span="6">
              <div class="filter-panel">
                <div class="panel-head">
                  <span>简答题处理</span>
                  <small>{{ questionShortAns.length }} 条</small>
                </div>
                <div class="filter-title">筛选范围</div>
                <el-radio-group v-model="filters.shortAns" class="filter-group" @change="handleFilterChange('shortAns', $event)">
                  <el-radio-button
                      v-for="item in filterOptions"
                      :key="item.value"
                      :label="item.value"
                  >{{ item.label }}</el-radio-button>
                </el-radio-group>
              </div>
            </el-col>
            <el-col :span="18">
              <div class="rightMain">
                <el-card v-for="(data, index) in questionShortAns" :key="index" shadow="hover" class="cardMain">
                  <div class="card-score">{{ data.questionShortAns.score }}分</div>
                  <el-row>
                    <el-col :span="2">{{ index + 1 }}、</el-col>
                    <el-col :span="22">
                      <div v-html="data.questionShortAns.question" class="w-e-text divs"></div>
                    </el-col>
                  </el-row>
                  <el-row class="card-actions">
                    <div class="card-actions__inner">
                      <el-button v-if="marks(data)" disabled type="text" size="small">批阅完成</el-button>
                      <el-button v-else type="text" size="small" @click="showAnswer(data)">进行批阅</el-button>
                      <el-button v-if="sign(data)" disabled type="text" size="small">验签完成</el-button>
                      <el-button v-else type="text" size="small" @click="setRightSign(data)">进行验签</el-button>
                    </div>
                  </el-row>
                </el-card>
              </div>
            </el-col>
          </el-row>
        </div>
      </el-tab-pane>

      <el-tab-pane label="代码题" name="code">
        <div class="questions">
          <el-row>
            <el-col :span="6">
              <div class="filter-panel">
                <div class="panel-head">
                  <span>代码题处理</span>
                  <small>{{ questionCode.length }} 条</small>
                </div>
                <div class="filter-title">筛选范围</div>
                <el-radio-group v-model="filters.code" class="filter-group" @change="handleFilterChange('code', $event)">
                  <el-radio-button
                      v-for="item in filterOptions"
                      :key="item.value"
                      :label="item.value"
                  >{{ item.label }}</el-radio-button>
                </el-radio-group>
              </div>
            </el-col>
            <el-col :span="18">
              <div class="rightMain">
                <el-card v-for="(data, index) in questionCode" :key="index" shadow="hover" class="cardMain">
                  <div class="card-score">{{ data.questionCode.score }}分</div>
                  <el-row>
                    <el-col :span="2">{{ index + 1 }}、</el-col>
                    <el-col :span="22">
                      <div v-html="data.questionCode.question" class="w-e-text divs"></div>
                    </el-col>
                  </el-row>
                  <el-row class="card-actions">
                    <div class="card-actions__inner">
                      <el-button v-if="marks(data)" disabled type="text" size="small">批阅完成</el-button>
                      <el-button v-else type="text" size="small" @click="showAnswer(data)">进行批阅</el-button>
                      <el-button v-if="sign(data)" disabled type="text" size="small">验签完成</el-button>
                      <el-button v-else type="text" size="small" @click="setRightSign(data)">进行验签</el-button>
                    </div>
                  </el-row>
                </el-card>
              </div>
            </el-col>
          </el-row>
        </div>
      </el-tab-pane>
    </el-tabs>

    <el-drawer
        title="问题详情"
        :visible.sync="drawer"
        custom-class="demo-drawer"
        direction="rtl"
        size="60%"
        :before-close="handleClose"
    >
      <div class="drawer">
        <div class="mark-detail">
          <div class="detail-hero">
            <div>
              <div class="detail-kicker">问题详情</div>
              <div class="detail-title">{{ questionTypeLabel }}</div>
            </div>
            <div class="score-badge">满分 {{ questionScore }} 分</div>
          </div>

          <section class="detail-section">
            <div class="section-label">题目</div>
            <div v-html="questionTitle" class="w-e-text question-content"></div>
          </section>

          <section v-if="!isCodeQuestion" class="answer-compare">
            <div class="answer-box">
              <div class="answer-label">标准答案</div>
              <div v-html="standardAnswer" class="w-e-text answer-content"></div>
            </div>
            <div class="answer-box student">
              <div class="answer-label">学生答案</div>
              <div v-html="studentAnswer" class="w-e-text answer-content"></div>
            </div>
          </section>

          <section v-if="isCodeQuestion" class="code-compare">
            <div class="code-box">
              <div class="answer-label">标准答案</div>
              <monaco-editor
                  v-if="question.questionCode"
                  v-model="question.questionCode.answer"
                  language="c"
                  :options="{ readOnly: true, fontSize: '15px', minimap: { enabled: false } }"
                  class="code-editor"
              />
            </div>
            <div class="code-box">
              <div class="answer-label">学生答案</div>
              <monaco-editor
                  v-model="question.answer"
                  language="c"
                  :options="{ readOnly: true, fontSize: '15px', minimap: { enabled: false } }"
                  class="code-editor"
              />
            </div>
          </section>

          <section class="detail-section">
            <div class="section-label">答案详解</div>
            <div v-html="questionAnalysis" class="w-e-text analysis-content"></div>
          </section>

          <section class="score-panel">
            <div class="score-panel-head">
              <div>
                <div class="section-label">评分</div>
                <div class="score-hint">请选择本题得分后提交</div>
              </div>
              <div class="current-score">当前 {{ question.markExams || 0 }} 分</div>
            </div>
            <el-radio-group v-model="question.markExams" class="score-options">
              <el-radio-button
                  v-for="score in scoreOptions"
                  :key="score"
                  :label="score.toString()"
              >{{ score }} 分</el-radio-button>
            </el-radio-group>
            <div class="score-actions">
              <el-button type="primary" @click="submitCurrentQuestion">提交评分</el-button>
            </div>
          </section>
        </div>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import request from "@/utils/request";
import MonacoEditor from "monaco-editor-vue";

export default {
  components: {
    MonacoEditor,
  },
  data() {
    const emptyCount = () => ({
      isMarkCount: 0,
      noMarkCount: 0,
      isRightSignCount: 0,
      noRightSignCount: 0,
    });

    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      form: {},
      receivedData: "",
      allCount: {
        questionMulti: emptyCount(),
        questionFill: emptyCount(),
        questionJudge: emptyCount(),
        questionShortAns: emptyCount(),
        questionCode: emptyCount(),
        questionAll: emptyCount(),
      },
      questionCode: [],
      questionFill: [],
      questionMulti: [],
      questionShortAns: [],
      questionJudge: [],
      drawer: false,
      question: {
        examPaper: [],
      },
      examManagers: {},
      activeType: "multi",
      filters: {
        multi: "all",
        fill: "all",
        judge: "all",
        shortAns: "all",
        code: "all",
      },
      filterOptions: [
        { label: "全部", value: "all" },
        { label: "已批阅", value: "isMark" },
        { label: "未批阅", value: "noMark" },
        { label: "验签成功", value: "isRightSign" },
        { label: "待验签", value: "noRightSign" },
      ],
    };
  },
  created() {
    this.receivedData = this.$route.params;
    this.findExamManager();
    this.findQuestion();
    this.findAllCount();
  },
  computed: {
    totalCount() {
      return this.safeCount("questionAll");
    },
    questionTypeStats() {
      return [
        { label: "选择题", value: "multi", countKey: "questionMulti" },
        { label: "填空题", value: "fill", countKey: "questionFill" },
        { label: "判断题", value: "judge", countKey: "questionJudge" },
        { label: "简答题", value: "shortAns", countKey: "questionShortAns" },
        { label: "代码题", value: "code", countKey: "questionCode" },
      ].map(item => ({
        ...item,
        ...this.safeCount(item.countKey),
      }));
    },
    questionTypeValue() {
      return this.question && this.question.examPaper ? this.question.examPaper.questionType : "";
    },
    questionConfig() {
      const configMap = {
        multi: { label: "选择题", key: "questionMulti", answerKey: "rightAnswer" },
        fill: { label: "填空题", key: "questionFill", answerKey: "answer" },
        judge: { label: "判断题", key: "questionJudge", answerKey: "answer" },
        shortAns: { label: "简答题", key: "questionShortAns", answerKey: "answer" },
        code: { label: "代码题", key: "questionCode", answerKey: "answer" },
      };
      return configMap[this.questionTypeValue] || {};
    },
    questionDetail() {
      return this.questionConfig.key && this.question[this.questionConfig.key] ? this.question[this.questionConfig.key] : {};
    },
    questionTypeLabel() {
      return this.questionConfig.label || "题目";
    },
    questionTitle() {
      return this.questionDetail.question || "暂无题目内容";
    },
    standardAnswer() {
      return this.questionDetail[this.questionConfig.answerKey] || "暂无标准答案";
    },
    studentAnswer() {
      return this.question.answer || "未作答";
    },
    questionAnalysis() {
      return this.questionDetail.analysis || "暂无解析";
    },
    questionScore() {
      const score = Number(this.questionDetail.score);
      return Number.isFinite(score) ? score : 0;
    },
    scoreOptions() {
      const maxScore = Math.max(0, Math.floor(this.questionScore));
      return Array.from({ length: maxScore + 1 }, (_, index) => index);
    },
    isCodeQuestion() {
      return this.questionTypeValue === "code";
    },
    hasPendingReviewOrSign() {
      return this.totalCount.noMarkCount !== 0 || this.totalCount.noRightSignCount !== 0;
    },
    isExamConfirmed() {
      return this.examManagers.isMarked === "是" && !this.hasPendingReviewOrSign;
    },
    approvalStatusText() {
      if (this.totalCount.noMarkCount !== 0) return "待批阅确认";
      if (this.totalCount.noRightSignCount !== 0) return "待验签确认";
      return this.examManagers.isMarked === "是" ? "已完成确认" : "待完成确认";
    },
  },
  methods: {
    safeCount(key) {
      const count = this.allCount && this.allCount[key] ? this.allCount[key] : {};
      return {
        isMarkCount: Number(count.isMarkCount) || 0,
        noMarkCount: Number(count.noMarkCount) || 0,
        isRightSignCount: Number(count.isRightSignCount) || 0,
        noRightSignCount: Number(count.noRightSignCount) || 0,
      };
    },
    handleFilterChange(questionType, filterValue) {
      const actionMap = {
        all: "findAll",
        isMark: "findIsMark",
        noMark: "findNoMark",
        isRightSign: "findIsRightSign",
        noRightSign: "findNoRightSign",
      };
      const action = actionMap[filterValue] || "findAll";
      this[action](questionType);
    },
    resetFilters() {
      Object.keys(this.filters).forEach(type => {
        this.filters[type] = "all";
      });
    },
    findExamManager() {
      request.get("exam/examManage/selectById/" + this.receivedData.id).then(res => {
        if (res.code === "200") {
          this.examManagers = res.data;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findAllCount() {
      const examId = this.receivedData.id;
      request.get("/statistic/selectExamCount/selectExamAnswer", {
        params: {
          examId,
        },
      }).then(res => {
        if (res.code === "200") {
          this.allCount = {
            ...this.allCount,
            ...res.data,
          };
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    newLoad() {
      this.resetFilters();
      this.findQuestion();
      this.findAllCount();
      this.$message.success("成功");
    },
    findQuestion() {
      this.findQuestionFill();
      this.findQuestionCode();
      this.findQuestionJudge();
      this.findQuestionMulti();
      this.findQuestionShortAns();
    },
    findQuestionCode() {
      request.get("/exam/examAnswer/selectAll", {
        params: { examId: this.receivedData.id, questionType: "code" },
      }).then(res => {
        if (res.code === "200") {
          this.questionCode = res.data;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findQuestionFill() {
      request.get("/exam/examAnswer/selectAll", {
        params: { examId: this.receivedData.id, questionType: "fill" },
      }).then(res => {
        if (res.code === "200") {
          this.questionFill = res.data;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findQuestionJudge() {
      request.get("/exam/examAnswer/selectAll", {
        params: { examId: this.receivedData.id, questionType: "judge" },
      }).then(res => {
        if (res.code === "200") {
          this.questionJudge = res.data;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findQuestionMulti() {
      request.get("/exam/examAnswer/selectAll", {
        params: { examId: this.receivedData.id, questionType: "multi" },
      }).then(res => {
        if (res.code === "200") {
          this.questionMulti = res.data;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findQuestionShortAns() {
      request.get("/exam/examAnswer/selectAll", {
        params: { examId: this.receivedData.id, questionType: "shortAns" },
      }).then(res => {
        if (res.code === "200") {
          this.questionShortAns = res.data;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    marks(data) {
      return !!(data.markExams && data.markExams !== "");
    },
    sign(data) {
      return !!(data.rightSign && data.rightSign === "验签成功");
    },
    findIsMark(questionType) {
      this.filters[questionType] = "isMark";
      request.get("/exam/examAnswer/selectIsMark", {
        params: { examId: this.receivedData.id, questionType },
      }).then(res => {
        if (res.code === "200") {
          this.setQuestionList(questionType, res.data);
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findNoMark(questionType) {
      this.filters[questionType] = "noMark";
      request.get("/exam/examAnswer/selectNoMark", {
        params: { examId: this.receivedData.id, questionType },
      }).then(res => {
        if (res.code === "200") {
          this.setQuestionList(questionType, res.data);
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findIsRightSign(questionType) {
      this.filters[questionType] = "isRightSign";
      request.get("/exam/examAnswer/selectIsRightSign", {
        params: { examId: this.receivedData.id, questionType },
      }).then(res => {
        if (res.code === "200") {
          this.setQuestionList(questionType, res.data);
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findNoRightSign(questionType) {
      this.filters[questionType] = "noRightSign";
      request.get("/exam/examAnswer/selectNoRightSign", {
        params: { examId: this.receivedData.id, questionType },
      }).then(res => {
        if (res.code === "200") {
          this.setQuestionList(questionType, res.data);
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findAll(questionType) {
      this.filters[questionType] = "all";
      request.get("/exam/examAnswer/selectAll", {
        params: { examId: this.receivedData.id, questionType },
      }).then(res => {
        if (res.code === "200") {
          this.setQuestionList(questionType, res.data);
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    setQuestionList(questionType, data) {
      if (questionType === "multi") this.questionMulti = data;
      if (questionType === "fill") this.questionFill = data;
      if (questionType === "judge") this.questionJudge = data;
      if (questionType === "shortAns") this.questionShortAns = data;
      if (questionType === "code") this.questionCode = data;
    },
    handleClose(done) {
      this.$confirm("确认关闭？")
          .then(() => {
            done();
          })
          .catch(() => {});
    },
    showAnswer(data) {
      this.question = data;
      this.drawer = true;
    },
    submitCurrentQuestion() {
      if (this.questionTypeValue === "multi") this.submitMulti(this.question);
      else if (this.questionTypeValue === "fill") this.submitFill(this.question);
      else if (this.questionTypeValue === "judge") this.submitJudge(this.question);
      else if (this.questionTypeValue === "shortAns") this.submitShortAns(this.question);
      else if (this.questionTypeValue === "code") this.submitCode(this.question);
    },
    changeMultiAll() {
      request.get("/exam/examAnswer/selectNoMark", {
        params: {
          examId: this.receivedData.id,
          questionType: "multi",
        },
      }).then(res => {
        if (res.code === "200") {
          const question = res.data;
          if (question.length === 0) {
            this.$message.warning("题目全部批阅完成，无需批阅");
          } else {
            this.changeMultiAllCh(question);
          }
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    changeMultiAllCh(data) {
      request.put("exam/examAnswer/updateByMultiAllAnswer", data).then(res => {
        if (res.code === "200") {
          this.$message.success("阅卷成功");
          this.filters.multi = "all";
          this.findAllCount();
          this.findAll("multi");
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    changeJudgeAll() {
      request.get("/exam/examAnswer/selectNoMark", {
        params: {
          examId: this.receivedData.id,
          questionType: "judge",
        },
      }).then(res => {
        if (res.code === "200") {
          const question = res.data;
          if (question.length === 0) {
            this.$message.warning("题目全部批阅完成，无需批阅");
          } else {
            this.changeJudgeAllCh(question);
          }
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    changeJudgeAllCh(data) {
      request.put("exam/examAnswer/updateByJudgeAllAnswer", data).then(res => {
        if (res.code === "200") {
          this.$message.success("阅卷成功");
          this.filters.judge = "all";
          this.findAllCount();
          this.findAll("judge");
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    submitMulti(data) {
      request.put("exam/examAnswer/updateByScore", {
        id: data.id,
        markExams: data.markExams,
        teacherId: this.user.id,
        examId: this.receivedData.id,
      }).then(res => {
        if (res.code === "200") {
          this.$message.success("成功阅卷");
          this.findAll("multi");
          this.findAllCount();
          this.drawer = false;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    submitJudge(data) {
      request.put("exam/examAnswer/updateByScore", {
        id: data.id,
        markExams: data.markExams,
        teacherId: this.user.id,
        examId: this.receivedData.id,
      }).then(res => {
        if (res.code === "200") {
          this.$message.success("成功阅卷");
          this.findAll("judge");
          this.findAllCount();
          this.drawer = false;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    submitFill(data) {
      request.put("exam/examAnswer/updateByScore", {
        id: data.id,
        markExams: data.markExams,
        teacherId: this.user.id,
        examId: this.receivedData.id,
      }).then(res => {
        if (res.code === "200") {
          this.$message.success("成功阅卷");
          this.findAll("fill");
          this.findAllCount();
          this.drawer = false;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    submitShortAns(data) {
      request.put("exam/examAnswer/updateByScore", {
        id: data.id,
        markExams: data.markExams,
        teacherId: this.user.id,
        examId: this.receivedData.id,
      }).then(res => {
        if (res.code === "200") {
          this.$message.success("成功阅卷");
          this.findAll("shortAns");
          this.findAllCount();
          this.drawer = false;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    submitCode(data) {
      request.put("exam/examAnswer/updateByScore", {
        id: data.id,
        markExams: data.markExams,
        teacherId: this.user.id,
        examId: this.receivedData.id,
      }).then(res => {
        if (res.code === "200") {
          this.$message.success("成功阅卷");
          this.findAll("code");
          this.findAllCount();
          this.drawer = false;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    batchSetRightSign() {
      request.get("/exam/examAnswer/selectNoRightSign", {
        params: {
          examId: this.receivedData.id,
        },
      }).then(res => {
        if (res.code === "200") {
          const question = res.data;
          if (question.length === 0) {
            this.$message.warning("验签全部完成，无需验签");
          } else {
            this.batchSetRightSignAn(question);
          }
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    batchSetRightSignAn(question) {
      request.put("exam/examAnswer/batchSetRightSign", question).then(res => {
        if (res.code === "200") {
          this.$message.success("验签完成");
          this.resetFilters();
          this.findAllCount();
          this.findQuestion();
        } else {
          this.$message.error("验签失败");
        }
      }).catch(() => {
        this.$message.error("验签失败");
      });
    },
    setRightSign(question) {
      request.put("exam/examAnswer/setRightSign", question).then(res => {
        if (res.code === "200") {
          this.$message.success("验签完成");
          this.filters[question.examPaper.questionType] = "all";
          this.findAllCount();
          this.findAll(question.examPaper.questionType);
        } else {
          this.$message.error("验签失败");
        }
      }).catch(() => {
        this.$message.error("验签失败");
      });
    },
    submitIsMarks() {
      const questionAll = this.totalCount;
      if (questionAll.noMarkCount !== 0) {
        this.$message.warning("还有题目未批阅");
      } else if (questionAll.noRightSignCount !== 0) {
        this.$message.warning("还有签名未验签");
      } else if (this.examManagers.isMarked === "是") {
        this.$message.warning("完成标记，无需再次标记");
      } else {
        this.form.id = this.receivedData.id;
        this.form.isMarked = "是";
        request.put("exam/examManage/update", this.form).then(res => {
          if (res.code === "200") {
            this.$message.success("成功");
            this.findExamManager();
          } else {
            this.$message.error(res.msg);
          }
        });
      }
    },
    markStatistic() {
      if (this.isExamConfirmed) {
        this.$router.push({
          name: "teacherMarkStatistics",
          params: {
            id: this.receivedData.id,
          },
        });
      } else if (this.totalCount.noMarkCount !== 0) {
        this.$message.warning("还有题目未批阅");
      } else if (this.totalCount.noRightSignCount !== 0) {
        this.$message.warning("还有签名未验签");
      } else {
        this.$message.warning("请先进行标记");
      }
    },
  },
};
</script>

<style scoped>
.main {
  padding: 6px 8px 0;
  color: #1f2937;
}

.mark-hero {
  display: grid;
  grid-template-columns: minmax(260px, 1.15fr) minmax(360px, 1.35fr) auto;
  align-items: center;
  gap: 18px;
  margin: 12px 1% 14px;
  padding: 18px 20px;
  border: 1px solid #dfe7ef;
  border-radius: 8px;
  background: #ffffff;
  box-shadow: 0 12px 28px rgba(15, 23, 42, 0.06);
}

.hero-main {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 16px;
}

.hero-kicker {
  margin-bottom: 5px;
  color: #64748b;
  font-size: 13px;
  font-weight: 700;
}

.hero-main h2 {
  margin: 0;
  color: #0f172a;
  font-size: 22px;
  font-weight: 800;
  line-height: 1.25;
}

.approval-pill {
  flex: none;
  display: inline-flex;
  align-items: center;
  min-height: 34px;
  padding: 0 14px;
  border-radius: 999px;
  background: #fff7ed;
  color: #c2410c;
  font-size: 13px;
  font-weight: 800;
}

.approval-pill.done {
  background: #ecfdf5;
  color: #047857;
}

.hero-metrics {
  display: grid;
  grid-template-columns: repeat(4, minmax(74px, 1fr));
  gap: 10px;
}

.metric-item {
  min-width: 0;
  padding: 10px 12px;
  border-radius: 8px;
  border: 1px solid #e2e8f0;
  background: #f8fafc;
}

.metric-item span {
  display: block;
  color: #64748b;
  font-size: 12px;
  font-weight: 700;
}

.metric-item strong {
  display: block;
  margin-top: 4px;
  color: #0f172a;
  font-size: 24px;
  font-weight: 900;
  line-height: 1;
}

.metric-item.warning strong {
  color: #b45309;
}

.command-bar {
  display: grid;
  grid-template-columns: repeat(2, minmax(108px, 1fr));
  gap: 10px;
}

.command-bar .el-button {
  width: 100%;
  height: 38px;
  margin: 0;
  border-radius: 8px;
  font-size: 14px;
  font-weight: 800;
}

.overview-grid {
  display: grid;
  grid-template-columns: repeat(5, minmax(150px, 1fr));
  gap: 12px;
  margin: 0 1% 14px;
}

.type-card {
  display: grid;
  gap: 9px;
  padding: 14px 16px;
  border: 1px solid #dfe7ef;
  border-radius: 8px;
  background: #ffffff;
  color: #334155;
  text-align: left;
  cursor: pointer;
  box-shadow: 0 10px 22px rgba(15, 23, 42, 0.05);
  transition: border-color 0.18s ease, box-shadow 0.18s ease, transform 0.18s ease;
}

.type-card:hover,
.type-card.active {
  border-color: #0f766e;
  box-shadow: 0 14px 30px rgba(15, 118, 110, 0.12);
  transform: translateY(-1px);
}

.type-name {
  color: #0f172a;
  font-size: 17px;
  font-weight: 900;
}

.type-row {
  display: grid;
  grid-template-columns: 42px auto 8px auto;
  align-items: baseline;
  column-gap: 6px;
  color: #64748b;
  font-size: 13px;
  font-weight: 700;
}

.type-row strong {
  color: #0f172a;
  font-size: 19px;
  font-weight: 900;
}

.type-row strong.warn {
  color: #b45309;
}

.type-row em {
  color: #cbd5e1;
  font-style: normal;
}

.mark-check-tabs {
  margin: 0 1% 18px;
  width: 98%;
  border: 1px solid #dfe7ef;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 14px 30px rgba(15, 23, 42, 0.06);
}

.mark-check-tabs:deep(.el-tabs__header) {
  margin: 0;
  border-bottom: 1px solid #e6ecf3;
  background: linear-gradient(180deg, #fbfdff 0%, #f6f8fb 100%);
}

.mark-check-tabs:deep(.el-tabs__nav-wrap) {
  padding: 0 16px;
}

.mark-check-tabs:deep(.el-tabs__item) {
  height: 52px;
  line-height: 52px;
  font-size: 15px;
  font-weight: 700;
  color: #64748b;
}

.mark-check-tabs:deep(.el-tabs__item.is-active) {
  color: #0f766e;
}

.mark-check-tabs:deep(.el-tabs__content) {
  padding: 14px 16px 18px;
  background: #f8fafc;
}

.questions {
  height: 54vh;
}

.filter-panel {
  display: flex;
  flex-direction: column;
  gap: 14px;
  height: 52vh;
  padding: 16px;
  border: 1px solid #e2e8f0;
  border-radius: 8px;
  background: #ffffff;
  box-sizing: border-box;
}

.panel-head {
  display: flex;
  align-items: baseline;
  justify-content: space-between;
  gap: 12px;
  padding-bottom: 12px;
  border-bottom: 1px solid #e2e8f0;
}

.panel-head span {
  color: #0f172a;
  font-size: 17px;
  font-weight: 900;
}

.panel-head small {
  color: #64748b;
  font-size: 12px;
  font-weight: 800;
}

.filter-title {
  color: #475569;
  font-size: 13px;
  font-weight: 800;
}

.auto-action {
  width: 100%;
  min-height: 42px;
  margin: 0 !important;
  border-radius: 8px;
  font-size: 15px;
  font-weight: 800;
}

.filter-group {
  display: grid;
  gap: 8px;
  width: 100%;
}

.filter-group:deep(.el-radio-button) {
  display: block;
  width: 100%;
}

.filter-group:deep(.el-radio-button__inner) {
  display: flex;
  align-items: center;
  justify-content: flex-start;
  width: 100%;
  min-height: 40px;
  border: 1px solid #d8e1eb !important;
  border-radius: 8px !important;
  background: #f8fafc;
  color: #334155;
  font-size: 14px;
  font-weight: 800;
  box-shadow: none !important;
}

.filter-group:deep(.el-radio-button__orig-radio:checked + .el-radio-button__inner) {
  border-color: #0f766e !important;
  background: #ecfdf5;
  color: #0f766e;
}

.rightMain {
  margin-left: 16px;
  padding: 18px;
  height: 52vh;
  width: calc(100% - 16px);
  border: 1px solid #dfe7ef;
  border-radius: 8px;
  background: #ffffff;
  overflow-y: auto;
  box-sizing: border-box;
}

.rightMain::-webkit-scrollbar,
.drawer::-webkit-scrollbar {
  width: 7px;
}

.rightMain::-webkit-scrollbar-thumb,
.drawer::-webkit-scrollbar-thumb {
  background: rgba(148, 163, 184, 0.58);
  border-radius: 999px;
}

.cardMain {
  margin: 0 0 16px;
  padding: 16px 18px;
  width: 100%;
  border: 1px solid #e2e8f0;
  border-radius: 8px;
  background: #ffffff;
  box-shadow: 0 10px 22px rgba(15, 23, 42, 0.05);
  box-sizing: border-box;
}

.cardMain:last-child {
  margin-bottom: 0;
}

.card-score {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  min-width: 58px;
  margin-left: auto;
  padding: 6px 10px;
  border-radius: 999px;
  background: rgba(225, 29, 72, 0.08);
  color: #e11d48;
  font-size: 15px;
  font-weight: 800;
}

.option-key {
  text-align: right;
}

.card-actions {
  margin-top: 14px !important;
  padding-top: 14px;
  border-top: 1px solid rgba(226, 232, 240, 0.92);
}

.card-actions__inner {
  padding: 0 2%;
  text-align: center;
}

.main :deep(.cardMain .el-button--text) {
  padding: 8px 14px !important;
  border-radius: 999px;
  background: rgba(15, 118, 110, 0.08);
  color: #0f766e !important;
  font-size: 15px !important;
  font-weight: 700;
}

.main :deep(.cardMain .el-button--text.is-disabled) {
  background: rgba(34, 197, 94, 0.12);
  color: #15803d !important;
}

.main :deep(.cardMain .el-button--text + .el-button--text) {
  margin-left: 12px !important;
}

.drawer {
  margin: 0;
  padding: 18px 20px 24px;
  width: auto;
  height: calc(100vh - 76px);
  background: #f6f8fb;
  overflow-y: auto;
  box-sizing: border-box;
}

.mark-detail {
  display: flex;
  flex-direction: column;
  gap: 14px;
}

.detail-hero {
  position: sticky;
  top: 0;
  z-index: 2;
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 16px;
  padding: 16px 0 14px;
  border-bottom: 1px solid #e2e8f0;
  background: #f6f8fb;
}

.detail-kicker {
  color: #64748b;
  font-size: 13px;
  font-weight: 700;
}

.detail-title {
  margin-top: 4px;
  color: #0f172a;
  font-size: 24px;
  font-weight: 800;
}

.score-badge,
.current-score {
  flex: none;
  min-width: 92px;
  padding: 8px 12px;
  border-radius: 999px;
  background: #ecfdf5;
  color: #047857;
  font-size: 14px;
  font-weight: 800;
  text-align: center;
}

.detail-section,
.answer-box,
.code-box,
.score-panel {
  border: 1px solid #e2e8f0;
  border-radius: 8px;
  background: #ffffff;
}

.detail-section {
  padding: 16px 18px;
}

.section-label,
.answer-label {
  margin-bottom: 10px;
  color: #475569;
  font-size: 14px;
  font-weight: 800;
}

.question-content {
  min-height: 58px;
  padding: 14px 16px;
  border-radius: 8px;
  background: #f8fafc;
  font-size: 18px;
}

.answer-compare,
.code-compare {
  display: grid;
  grid-template-columns: minmax(0, 1fr) minmax(0, 1fr);
  gap: 14px;
}

.answer-box,
.code-box {
  padding: 14px;
}

.answer-content {
  min-height: 64px;
  padding: 14px 16px;
  border-radius: 8px;
  background: #f8fafc;
  color: #0f172a;
  font-size: 17px;
  text-align: center;
}

.answer-box.student .answer-content {
  background: #f0f9ff;
}

.code-editor {
  margin-top: 8px;
  height: 34vh;
  min-height: 240px;
  overflow: hidden;
  border: 1px solid #e2e8f0;
  border-radius: 8px;
  text-align: left;
}

.analysis-content {
  padding: 12px 14px;
  border-left: 3px solid #0f766e;
  border-radius: 8px;
  background: #f8fafc;
  font-size: 16px;
}

.score-panel {
  position: sticky;
  bottom: 0;
  z-index: 2;
  padding: 14px 16px 16px;
  box-shadow: 0 -12px 24px rgba(15, 23, 42, 0.06);
}

.score-panel-head {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 14px;
  margin-bottom: 12px;
}

.score-hint {
  color: #64748b;
  font-size: 13px;
}

.score-options {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
}

.score-options:deep(.el-radio-button) {
  margin: 0;
}

.score-options:deep(.el-radio-button__inner) {
  min-width: 72px;
  border: 1px solid #cbd5e1;
  border-radius: 8px !important;
  color: #334155;
  font-size: 16px;
  font-weight: 800;
}

.score-options:deep(.el-radio-button__orig-radio:checked + .el-radio-button__inner) {
  border-color: #0f766e;
  background: #0f766e;
  color: #ffffff;
  box-shadow: none;
}

.score-actions {
  display: flex;
  justify-content: flex-end;
  margin-top: 14px;
}

.score-actions .el-button {
  min-width: 128px;
  height: 40px;
  border-radius: 8px;
  font-size: 15px;
  font-weight: 800;
}

.main :deep(.el-button--primary) {
  background: linear-gradient(135deg, #0f766e, #0d9488);
  border-color: #0f766e;
}

.main :deep(.el-button--primary:hover),
.main :deep(.el-button--primary:focus) {
  background: linear-gradient(135deg, #0b5d57, #0f766e);
  border-color: #0b5d57;
}

.main :deep(.demo-drawer) {
  background: #f4f7fb;
}

.main :deep(.demo-drawer .el-drawer__header) {
  margin-bottom: 0;
  padding: 18px 22px 0;
  color: #0f172a;
  font-size: 20px;
  font-weight: 700;
}

.main :deep(.demo-drawer .el-drawer__body) {
  padding: 0;
}

.main :deep(.w-e-text) {
  line-height: 1.75;
  color: #334155;
}

@media (max-width: 1200px) {
  .mark-hero {
    grid-template-columns: 1fr;
  }

  .overview-grid {
    grid-template-columns: repeat(2, minmax(150px, 1fr));
  }

  .rightMain {
    margin-left: 12px;
    width: calc(100% - 12px);
  }
}
</style>
