<template>
  <div class="teacher-page teacher-paper-list">
    <section class="teacher-page-head teacher-paper-list__head">
      <div>
        <h2 class="teacher-page-head__title">试卷信息管理</h2>
        <p class="teacher-page-head__desc">集中管理试卷查询、发布、分配、手动认证与题目维护，保持原有业务链路和按钮行为不变。</p>
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
          <p class="teacher-panel__desc">统一展示课程、考试时间、总分和操作列，降低复杂操作的视觉噪音。</p>
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
                <el-button type="text" @click="submit(scope.row)">发布</el-button>
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
      visit: -1
    };
  },
  created() {
    this.receivedData = this.$route.params;
    this.load();
    this.findCourse();
  },
  methods: {
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
          request.get(`exam/examStudent/addStudentBatch/${row.id}`).then((res) => {
            if (res.code === "200") {
              this.$message.success("成功");
              this.load();
            } else {
              this.$message.warning(res.data);
            }
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
  padding: 22px 24px 24px;
  border-radius: 24px;
  border-color: #dbe7f3;
  background:
      linear-gradient(180deg, rgba(255, 255, 255, 0.98), rgba(248, 250, 252, 0.96)),
      linear-gradient(135deg, rgba(219, 234, 254, 0.22), transparent 40%);
  box-shadow: 0 20px 48px rgba(15, 23, 42, 0.08);
}

.teacher-paper-list__form {
  padding: 18px;
  border-radius: 20px;
  background: linear-gradient(180deg, #f8fbff 0%, #f1f5f9 100%);
  border: 1px solid #e2e8f0;
}

.teacher-paper-list__form :deep(.el-form-item) {
  margin-bottom: 0;
}

.teacher-paper-list__form :deep(.el-form-item__label) {
  padding-right: 12px;
  font-size: 13px;
  font-weight: 700;
  color: #334155;
}

.teacher-paper-list__form :deep(.el-input__inner),
.teacher-paper-list__form :deep(.el-select .el-input__inner) {
  height: 42px;
  border-radius: 14px;
  border-color: #dbe4ee;
  background: #ffffff;
  box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.7);
}

.teacher-paper-list__form :deep(.el-button--primary) {
  min-width: 88px;
  height: 42px;
  border-radius: 14px;
  background: linear-gradient(135deg, #0f8b84 0%, #0f766e 100%);
  border-color: #0f766e;
  box-shadow: 0 12px 24px rgba(15, 118, 110, 0.22);
}

.teacher-paper-list__actions {
  padding-top: 4px;
}

.teacher-paper-list__action-btn {
  min-width: 100px;
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
</style>
