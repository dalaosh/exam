<template>
  <div class="teacher-page teacher-paper-list">
    <section class="teacher-page-head teacher-paper-list__head">
      <div>
        <h2 class="teacher-page-head__title">试卷信息管理</h2>
      </div>
      <div class="teacher-page-head__meta teacher-paper-list__meta">
        <el-tag class="teacher-tag" type="info">共 {{ total }} 份试卷</el-tag>
      </div>
    </section>

    <section class="teacher-panel teacher-panel--toolbar teacher-paper-list__toolbar-panel">
      <div class="teacher-toolbar">
        <el-form :inline="true" :model="params" class="teacher-toolbar__form teacher-paper-list__form">
          <el-form-item label="课程名称">
            <el-select v-model="params.courseId" clearable filterable placeholder="请选择课程">
              <el-option
                v-for="item in course"
                :key="item.id"
                :label="item.name + ' === ' + item.number"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="考试类型">
            <el-input v-model="params.type" clearable placeholder="请输入考试类型"></el-input>
          </el-form-item>
          <el-form-item label="总分">
            <el-input v-model="params.totalScore" clearable placeholder="请输入总分"></el-input>
          </el-form-item>
          <el-form-item label="总时长">
            <el-input v-model="params.totalTime" clearable placeholder="请输入总时长"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="findBySearch">查询</el-button>
          </el-form-item>
        </el-form>

        <div class="teacher-toolbar__actions teacher-paper-list__actions">
          <el-button type="primary" plain class="teacher-paper-list__action-btn teacher-paper-list__action-btn--primary" @click="selectAll">全选</el-button>
          <el-button type="warning" plain class="teacher-paper-list__action-btn teacher-paper-list__action-btn--warning" @click="DeselectAll">清空选择</el-button>
          <el-button type="danger" plain class="teacher-paper-list__action-btn teacher-paper-list__action-btn--danger" @click="deleteBatch">批量删除</el-button>
        </div>
      </div>
    </section>

    <section class="teacher-panel teacher-panel--table teacher-paper-list__table-panel">
      <div class="teacher-panel__head teacher-paper-list__panel-head">
        <div>
          <h3 class="teacher-panel__title">试卷列表</h3>
        </div>
      </div>

      <template v-if="visit !== -1 && visit !== 0">
        <el-table
          ref="table"
          class="teacher-table"
          :data="tableData"
          tooltip-effect="dark"
          border
          show-overflow-tooltip
          stripe
          style="width: 100%"
          height="560"
          @selection-change="handleSelectionChange"
          :row-key="getRowKeys"
        >
          <el-table-column ref="table" type="selection" width="55" align="center" :reserve-selection="true" fixed />
          <el-table-column prop="id" label="ID" width="90" align="center" fixed />
          <el-table-column prop="course.name" label="课程名称" min-width="160" align="center" />
          <el-table-column prop="examDate" label="考试日期" min-width="180" align="center" />
          <el-table-column prop="totalTime" label="持续时长" width="120" align="center" />
          <el-table-column prop="totalScore" label="总分" width="100" align="center" />
          <el-table-column prop="type" label="考试类型" min-width="150" align="center">
            <template v-slot="scope">
              <el-tag class="teacher-tag" type="info">{{ scope.row.type }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="description" label="考试介绍" width="140" align="center">
            <template slot-scope="scope">
              <div class="teacher-table__actions">
                <el-button type="text" @click="viewEditor(scope.row.description)">查看详情</el-button>
              </div>
            </template>
          </el-table-column>
          <el-table-column prop="tips" label="考生须知" width="140" align="center">
            <template slot-scope="scope">
              <div class="teacher-table__actions">
                <el-button type="text" @click="viewEditor(scope.row.tips)">查看详情</el-button>
              </div>
            </template>
          </el-table-column>
          <el-table-column prop="teacher.name" label="创建者" min-width="140" align="center" />
          <el-table-column fixed="right" label="操作" width="420" align="center">
            <template slot-scope="scope">
              <div class="teacher-table__actions">
                <el-button type="text" @click="showPublicKeys(scope.row.id)">查看环公钥</el-button>
                <el-button type="text" @click="showManager(scope.row)">题目创建</el-button>
                <el-button type="text" @click="updateById(scope.row.id)">修改</el-button>
                <el-button type="text" :loading="publishingRowId === scope.row.id" @click="submit(scope.row)">发布</el-button>
                <el-button type="text" @click="realFace(scope.row)">手动认证</el-button>
                <el-button type="text" @click="distributeById(scope.row)">教师分配</el-button>
                <el-button type="text" style="color: #dc2626;" @click="deleteById(scope.row.id)">删除</el-button>
              </div>
            </template>
          </el-table-column>
        </el-table>

        <div class="teacher-pagination">
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pageNum"
            :page-sizes="[5, 10, 15, 20]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total"
          />
        </div>
      </template>

      <div v-else-if="visit === -1" class="teacher-empty">
        请先选择课程进行考试查询。
      </div>

      <div v-else class="teacher-empty">
        该课程还未创建试卷。
      </div>
    </section>

    <el-dialog title="信息详情" :visible.sync="editorVisible" width="760px" custom-class="teacher-dialog">
      <div v-html="viewData" class="teacher-rich-content"></div>
    </el-dialog>

    <el-dialog
      title="试卷安全发布"
      :visible.sync="publishDialogVisible"
      width="680px"
      custom-class="teacher-dialog publish-dialog"
      :close-on-click-modal="false"
      :close-on-press-escape="!publishLoading"
      :show-close="!publishLoading"
    >
      <div class="publish-demo">
        <div class="publish-demo__summary">
          <div>
            <div class="publish-demo__eyebrow">SM2 Secure Publish</div>
            <h3 class="publish-demo__title">{{ publishCurrentStep.title }}</h3>
            <p class="publish-demo__desc">{{ publishCurrentStep.desc }}</p>
          </div>
          <div class="publish-demo__percent">{{ publishProgress }}%</div>
        </div>

        <el-progress
          :percentage="publishProgress"
          :stroke-width="12"
          :show-text="false"
          :status="publishProgress >= 100 ? 'success' : undefined"
        />

        <div class="publish-demo__steps">
          <div
            v-for="(step, index) in publishSteps"
            :key="step.title"
            class="publish-demo__step"
            :class="{ 'is-active': index === publishStepIndex, 'is-done': index < publishStepIndex || publishProgress >= 100 }"
          >
            <span class="publish-demo__dot">{{ index + 1 }}</span>
            <span class="publish-demo__step-title">{{ step.title }}</span>
          </div>
        </div>

        <div class="publish-demo__terminal">
          <div v-for="(log, index) in publishLogs" :key="index" class="publish-demo__log">
            <span class="publish-demo__time">{{ log.time }}</span>
            <span>{{ log.text }}</span>
          </div>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      form: {},
      tableData: [],
      pageNum: 1,
      pageSize: 10,
      total: 0,
      course: [],
      ids: "",
      multipleSelection: "",
      params: {
        pageNum: 1,
        pageSize: 10,
      },
      editorVisible: false,
      viewData: "",
      dialogFormVisible: false,
      receivedData: "",
      visit: -1,
      publishDialogVisible: false,
      publishLoading: false,
      publishingRowId: null,
      publishProgress: 0,
      publishStepIndex: 0,
      publishTimer: null,
      publishLogs: [],
      publishSteps: [
        {
          title: "试题内容封装",
          desc: "正在汇总选择、判断、填空、简答、编程题及分值结构。",
          target: 16,
        },
        {
          title: "考试配置锁定",
          desc: "正在写入考试时间、考试时长、考生须知与访问策略快照。",
          target: 34,
        },
        {
          title: "SM2 国密加密",
          desc: "正在生成试卷密文包，模拟执行 SM2 公钥加密与签名绑定。",
          target: 56,
        },
        {
          title: "防篡改校验",
          desc: "正在计算试卷结构摘要，模拟校验题目顺序与配置完整性。",
          target: 76,
        },
        {
          title: "发布前安全检查",
          desc: "正在确认密文包、访问控制和防泄露策略状态。",
          target: 92,
        },
        {
          title: "发布就绪",
          desc: "安全封装流程完成，正在提交原有发布操作。",
          target: 100,
        },
      ]
    };
  },
  computed: {
    publishCurrentStep() {
      return this.publishSteps[this.publishStepIndex] || this.publishSteps[0];
    }
  },
  created() {
    this.receivedData = this.$route.params;
    this.load();
    this.findCourse();
  },
  beforeDestroy() {
    this.clearPublishTimer();
  },
  methods: {
    nowLabel() {
      const date = new Date();
      const hh = String(date.getHours()).padStart(2, "0");
      const mm = String(date.getMinutes()).padStart(2, "0");
      const ss = String(date.getSeconds()).padStart(2, "0");
      return `${hh}:${mm}:${ss}`;
    },
    clearPublishTimer() {
      if (this.publishTimer) {
        clearInterval(this.publishTimer);
        this.publishTimer = null;
      }
    },
    appendPublishLog(text) {
      this.publishLogs.unshift({
        time: this.nowLabel(),
        text,
      });
      this.publishLogs = this.publishLogs.slice(0, 8);
    },
    runPublishDemo(row) {
      this.clearPublishTimer();
      this.publishDialogVisible = true;
      this.publishLoading = true;
      this.publishingRowId = row.id;
      this.publishProgress = 0;
      this.publishStepIndex = 0;
      this.publishLogs = [];
      this.appendPublishLog(`试卷 #${row.id} 已进入发布安全封装流程。`);
      this.appendPublishLog("开始读取试题内容、试卷结构与考试配置。");

      return new Promise((resolve) => {
        this.publishTimer = setInterval(() => {
          if (this.publishProgress >= 100) {
            this.clearPublishTimer();
            resolve();
            return;
          }

          const nextProgress = Math.min(this.publishProgress + 4, 100);
          const nextStepIndex = this.publishSteps.findIndex((step) => nextProgress <= step.target);

          if (nextStepIndex !== -1 && nextStepIndex !== this.publishStepIndex) {
            this.publishStepIndex = nextStepIndex;
            this.appendPublishLog(this.publishSteps[nextStepIndex].title + "中...");
          }

          this.publishProgress = nextProgress;

          if (this.publishProgress === 56) {
            this.appendPublishLog("SM2 密文包生成完成，密钥索引已绑定。");
          }
          if (this.publishProgress === 76) {
            this.appendPublishLog("试卷结构摘要校验通过，未发现篡改风险。");
          }
          if (this.publishProgress === 100) {
            this.publishStepIndex = this.publishSteps.length - 1;
            this.appendPublishLog("安全发布模拟流程完成。");
          }
        }, 120);
      });
    },
    async publishExam(row) {
      try {
        const res = await request.get(`exam/examStudent/addStudentBatch/${row.id}`);
        if (res.code === "200") {
          this.appendPublishLog("原有发布接口调用成功，试卷已发布。");
          this.$message.success("试卷发布成功");
          this.load();
        } else {
          this.appendPublishLog("原有发布接口返回异常：" + (res.data || res.msg || "未知错误"));
          this.$message.warning(res.data || res.msg || "发布失败");
        }
      } catch (error) {
        this.appendPublishLog("原有发布接口请求失败，请检查后端服务状态。");
        this.$message.error("发布失败，请检查后端服务状态");
      } finally {
        this.publishLoading = false;
        this.publishingRowId = null;
      }
    },
    load() {
      this.params.teacherId = this.user.id;
      request.get("/exam/examManage/selectPage", {
        params: this.params
      }).then((res) => {
        if (res.code === "200") {
          this.total = res.data.total;
          this.tableData = res.data.list;
          this.visit = this.total;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    handleSizeChange(pageSize) {
      this.params.pageSize = pageSize;
      this.findBySearch();
    },
    handleCurrentChange(pageNum) {
      this.params.pageNum = pageNum;
      this.findBySearch();
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
      this.ids = val.map((item) => item.id);
    },
    getRowKeys(row) {
      return row.id;
    },
    findCourse() {
      const params = {
        teacherId: this.user.id
      };
      request.get("exam/course/selectAll", {
        params
      }).then((res) => {
        if (res.code === "200") {
          this.course = res.data;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findBySearch() {
      request.get("/exam/examManage/selectPage", {
        params: this.params
      }).then((res) => {
        if (res.code === "200") {
          this.total = res.data.total;
          this.tableData = res.data.list;
          this.visit = this.total;
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
    deleteById(id) {
      this.$confirm("您确定删除吗？", "确认删除", { type: "warning" }).then(() => {
        request.delete(`/exam/examManage/delete/${id}`).then((res) => {
          if (res.code === "200") {
            this.$message.success("删除成功");
            this.findBySearch();
          } else {
            this.$message.error(res.msg);
          }
        });
      }).catch(() => {});
    },
    deleteBatch() {
      if (!this.ids.length) {
        this.$message.warning("请选择数据");
        return;
      }
      this.$confirm("您确定批量删除这些数据吗？", "确认删除", { type: "warning" }).then(() => {
        request.delete("/exam/examManage/delete/batch", { data: this.ids }).then((res) => {
          if (res.code === "200") {
            this.$message.success("批量删除成功");
            this.findBySearch();
          } else {
            this.$message.error(res.msg);
          }
        });
      }).catch(() => {});
    },
    viewEditor(data) {
      this.editorVisible = true;
      this.viewData = data;
    },
    showManager(row) {
      if (this.isExamEnded(row)) {
        this.$message.warning("考试已经结束");
      } else if (this.isExamStarted(row)) {
        this.$message.warning("考试正在进行");
      } else {
        this.$router.push({
          name: "teacherShowManager",
          params: {
            id: row.id,
            courseId: row.course.id
          }
        });
      }
    },
    showPublicKeys(id) {
      this.$router.push({
        name: "teacherShowPublicKeys",
        params: {
          id
        }
      });
    },
    updateById(id) {
      this.$router.push({
        name: "teacherUpdateManager",
        params: {
          id
        }
      });
    },
    submit(row) {
      if (row.isSubmit === "是") {
        this.$message.warning("试卷已经发布");
      } else {
        this.$confirm("您确定公布试卷吗？", "确认公布", { type: "warning" }).then(() => {
          this.runPublishDemo(row).then(() => {
            this.publishExam(row);
          });
        }).catch(() => {});
      }
    },
    distributeById(data) {
      if (this.isExamEnded(data)) {
        if (data.beginTime !== "" && data.beginTime !== null) {
          if (this.beginBefore(data)) {
            this.$router.push({
              name: "teacherDistributePaper",
              params: {
                id: data.id
              }
            });
          } else if (this.endAfter(data)) {
            this.$message.warning("批卷已经结束");
          } else {
            this.$message.warning("批卷正在进行中");
          }
        } else {
          this.$router.push({
            name: "teacherDistributePaper",
            params: {
              id: data.id
            }
          });
        }
      } else if (this.isExamStarted(data)) {
        this.$message.warning("考试正在进行");
      } else {
        this.$message.warning("考试还未开始");
      }
    },
    realFace(row) {
      this.$router.push({
        name: "teacherRealFace",
        params: {
          id: row.id
        }
      });
    },
    beginBefore(row) {
      const beginTime = new Date(row.beginTime);
      const now = new Date();
      return now <= beginTime;
    },
    endAfter(row) {
      const endTime = new Date(row.endTime);
      const now = new Date();
      return now >= endTime;
    },
    isExamStarted(row) {
      const examDate = new Date(row.examDate);
      const now = new Date();
      return now >= examDate;
    },
    isExamEnded(row) {
      const examDate = new Date(row.examDate);
      const totalTime = row.totalTime * 60 * 1000;
      const endDate = new Date(examDate.getTime() + totalTime);
      const now = new Date();
      return now >= endDate;
    }
  }
};
</script>

<style scoped lang="scss">
.teacher-paper-list {
  gap: 16px;
}

.teacher-paper-list__head {
  padding: 4px 2px 0;
}

.teacher-paper-list__meta :deep(.teacher-tag) {
  height: 34px;
  padding: 0 14px;
  border-radius: 12px;
  border-color: #dbeafe;
  background: linear-gradient(135deg, #f8fbff 0%, #eff6ff 100%);
  color: #2563eb;
  font-weight: 700;
  line-height: 32px;
  box-shadow: inset 0 0 0 1px rgba(191, 219, 254, 0.42);
}

.teacher-paper-list__toolbar-panel {
  padding: 24px 30px 28px;
  border-radius: 24px;
  border-color: #dbe7f3;
  background:
      linear-gradient(180deg, rgba(255, 255, 255, 0.98), rgba(248, 250, 252, 0.96)),
      linear-gradient(135deg, rgba(219, 234, 254, 0.22), transparent 40%);
  box-shadow: 0 20px 48px rgba(15, 23, 42, 0.08);
}

.teacher-paper-list :deep(.teacher-toolbar) {
  width: 100%;
}

.teacher-paper-list__form {
  display: grid;
  grid-template-columns: repeat(4, minmax(190px, 1fr)) 110px;
  gap: 18px 20px;
  align-items: center;
  padding: 22px 24px;
  border-radius: 20px;
  background: linear-gradient(180deg, #f8fbff 0%, #f1f5f9 100%);
  border: 1px solid #e2e8f0;
}

.teacher-paper-list__form :deep(.el-form-item) {
  display: flex;
  align-items: center;
  min-width: 0;
  margin-bottom: 0;
}

.teacher-paper-list__form :deep(.el-form-item__label) {
  flex: none;
  padding-right: 12px;
  font-size: 13px;
  font-weight: 700;
  color: #334155;
}

.teacher-paper-list__form :deep(.el-form-item__content) {
  flex: 1;
  min-width: 0;
  line-height: normal;
}

.teacher-paper-list__form :deep(.el-input__inner),
.teacher-paper-list__form :deep(.el-select .el-input__inner) {
  width: 100%;
  height: 42px;
  border-radius: 14px;
  border-color: #dbe4ee;
  background: #ffffff;
  box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.7);
}

.teacher-paper-list__form :deep(.el-input),
.teacher-paper-list__form :deep(.el-select) {
  width: 100%;
}

.teacher-paper-list__form :deep(.el-button--primary) {
  width: 110px;
  min-width: 110px;
  height: 42px;
  border-radius: 14px;
  background: linear-gradient(135deg, #0f8b84 0%, #0f766e 100%);
  border-color: #0f766e;
  box-shadow: 0 12px 24px rgba(15, 118, 110, 0.22);
}

.teacher-paper-list__actions {
  display: grid;
  grid-template-columns: repeat(3, 126px);
  gap: 24px;
  justify-content: start;
  padding-top: 2px;
}

.teacher-paper-list__action-btn {
  width: 126px;
  min-width: 126px;
  height: 42px;
  border-radius: 14px;
  font-weight: 700;
  border-width: 1px;
}

.teacher-paper-list__action-btn--primary {
  color: #0f8b84;
  border-color: rgba(45, 212, 191, 0.42);
  background: linear-gradient(135deg, rgba(240, 253, 250, 0.9), rgba(204, 251, 241, 0.72));
}

.teacher-paper-list__action-btn--warning {
  color: #d97706;
  border-color: rgba(251, 191, 36, 0.42);
  background: linear-gradient(135deg, rgba(255, 251, 235, 0.96), rgba(254, 240, 138, 0.5));
}

.teacher-paper-list__action-btn--danger {
  color: #dc2626;
  border-color: rgba(252, 165, 165, 0.48);
  background: linear-gradient(135deg, rgba(254, 242, 242, 0.96), rgba(254, 202, 202, 0.52));
}

.teacher-paper-list__table-panel {
  padding: 20px 20px 22px;
  border-radius: 24px;
  border-color: #dbe7f3;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0.98), rgba(248, 250, 252, 0.96));
  box-shadow: 0 20px 48px rgba(15, 23, 42, 0.08);
}

.teacher-paper-list__panel-head {
  padding-bottom: 14px;
  margin-bottom: 2px;
  border-bottom-color: #e5edf6;
}

.teacher-paper-list :deep(.teacher-table) {
  margin-top: 14px;
  border-radius: 20px;
  border: 1px solid #e2e8f0;
  box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.65);
}

.teacher-paper-list :deep(.teacher-table th.el-table__cell) {
  height: 52px;
  background: linear-gradient(180deg, #f8fbff 0%, #eff6ff 100%) !important;
  color: #334155;
  border-bottom-color: #dbe7f3 !important;
}

.teacher-paper-list :deep(.teacher-table td.el-table__cell) {
  height: 58px;
  background: rgba(255, 255, 255, 0.88);
}

.teacher-paper-list :deep(.teacher-table .el-table__row:hover > td.el-table__cell) {
  background: linear-gradient(180deg, #f8fffd 0%, #effcf6 100%) !important;
}

.teacher-paper-list :deep(.teacher-table__actions .el-button) {
  padding: 6px 10px;
  border-radius: 10px;
  background: rgba(248, 250, 252, 0.88);
  border: 1px solid #e2e8f0;
  transition: all 0.2s ease;
}

.teacher-paper-list :deep(.teacher-table__actions .el-button:hover) {
  background: #eff6ff;
  border-color: #bfdbfe;
  color: #2563eb;
}

.teacher-paper-list :deep(.teacher-pagination) {
  padding-top: 20px;
}

.teacher-paper-list :deep(.teacher-empty) {
  min-height: 180px;
  border-radius: 18px;
  background: linear-gradient(180deg, #f8fbff 0%, #f1f5f9 100%);
  border: 1px dashed #cbd5e1;
  color: #64748b;
}

.teacher-paper-list :deep(.teacher-dialog) {
  border-radius: 24px;
}

.publish-demo {
  padding: 2px 4px 4px;
}

.publish-demo__summary {
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
  gap: 20px;
  margin-bottom: 18px;
}

.publish-demo__eyebrow {
  display: inline-flex;
  align-items: center;
  height: 24px;
  padding: 0 9px;
  border-radius: 999px;
  background: #ecfeff;
  color: #0891b2;
  font-size: 12px;
  font-weight: 700;
}

.publish-demo__title {
  margin: 10px 0 6px;
  color: #0f172a;
  font-size: 20px;
  line-height: 1.3;
}

.publish-demo__desc {
  margin: 0;
  color: #64748b;
  line-height: 1.7;
}

.publish-demo__percent {
  min-width: 82px;
  color: #0f766e;
  font-size: 30px;
  font-weight: 800;
  text-align: right;
}

.publish-demo__steps {
  display: grid;
  grid-template-columns: repeat(3, minmax(0, 1fr));
  gap: 10px;
  margin-top: 18px;
}

.publish-demo__step {
  display: flex;
  align-items: center;
  gap: 8px;
  min-height: 38px;
  padding: 8px 10px;
  border: 1px solid #e2e8f0;
  border-radius: 12px;
  background: #f8fafc;
  color: #64748b;
}

.publish-demo__step.is-active {
  border-color: #67e8f9;
  background: #ecfeff;
  color: #0e7490;
}

.publish-demo__step.is-done {
  border-color: #86efac;
  background: #f0fdf4;
  color: #15803d;
}

.publish-demo__dot {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  width: 22px;
  height: 22px;
  border-radius: 999px;
  background: #ffffff;
  font-size: 12px;
  font-weight: 800;
  flex: none;
}

.publish-demo__step-title {
  overflow: hidden;
  font-size: 13px;
  font-weight: 700;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.publish-demo__terminal {
  display: flex;
  flex-direction: column;
  gap: 8px;
  min-height: 164px;
  margin-top: 18px;
  padding: 14px;
  border-radius: 14px;
  background: #0f172a;
}

.publish-demo__log {
  display: grid;
  grid-template-columns: 72px 1fr;
  gap: 10px;
  color: #dbeafe;
  font-size: 13px;
  line-height: 1.6;
}

.publish-demo__time {
  color: #67e8f9;
  font-family: Consolas, monospace;
}

@media (max-width: 760px) {
  .publish-demo__summary {
    flex-direction: column;
  }

  .publish-demo__percent {
    text-align: left;
  }

  .publish-demo__steps {
    grid-template-columns: 1fr;
  }
}

@media (max-width: 1280px) {
  .teacher-paper-list__form {
    grid-template-columns: repeat(2, minmax(220px, 1fr));
  }

  .teacher-paper-list__form :deep(.el-button--primary) {
    width: 100%;
  }
}

@media (max-width: 760px) {
  .teacher-paper-list__toolbar-panel {
    padding: 18px;
  }

  .teacher-paper-list__form,
  .teacher-paper-list__actions {
    grid-template-columns: 1fr;
  }

  .teacher-paper-list__actions {
    gap: 12px;
  }

  .teacher-paper-list__action-btn {
    width: 100%;
  }
}
</style>
