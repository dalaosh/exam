<template>
  <div class="teacher-page">
    <section class="teacher-page-head">
      <div>
        <h2 class="teacher-page-head__title">课程信息</h2>
        <p class="teacher-page-head__desc">统一查看课程基础信息、课程介绍与学生管理入口，保持原有查询、删除和跳转逻辑不变。</p>
      </div>
      <div class="teacher-page-head__meta">
        <el-tag class="teacher-tag" type="info">共 {{ total }} 门课程</el-tag>
      </div>
    </section>

    <section class="teacher-panel teacher-panel--toolbar">
      <div class="teacher-toolbar">
        <el-form :inline="true" :model="params" class="teacher-toolbar__form">
          <el-form-item label="课程名称">
            <el-input v-model="params.name" clearable placeholder="请输入课程名称"></el-input>
          </el-form-item>
          <el-form-item label="课程编号">
            <el-input v-model="params.number" clearable placeholder="请输入课程编号"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="findBySearch">查询</el-button>
          </el-form-item>
        </el-form>

        <div class="teacher-toolbar__actions">
          <el-button type="primary" plain @click="selectAll">全选</el-button>
          <el-button type="warning" plain @click="DeselectAll">清空选择</el-button>
          <el-button type="danger" plain @click="deleteBatch">批量删除</el-button>
        </div>
      </div>
    </section>

    <section class="teacher-panel teacher-panel--table">
      <div class="teacher-panel__head">
        <div>
          <h3 class="teacher-panel__title">课程列表</h3>
          <p class="teacher-panel__desc">突出课程名称、编号、时间范围与关键操作，提升课程管理页可读性。</p>
        </div>
      </div>

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
        <el-table-column prop="name" label="课程名称" min-width="180" align="center" fixed />
        <el-table-column prop="photo" label="照片" width="110" align="center">
          <template v-slot="scope">
            <el-image class="teacher-table__avatar" :src="scope.row.photo" :preview-src-list="[scope.row.photo]" />
          </template>
        </el-table-column>
        <el-table-column prop="number" label="课程编号" min-width="160" align="center" />
        <el-table-column prop="beginTime" label="开始时间" min-width="180" align="center" />
        <el-table-column prop="endTime" label="结束时间" min-width="180" align="center" />
        <el-table-column prop="message" label="课程信息" width="140" align="center">
          <template slot-scope="scope">
            <div class="teacher-table__actions">
              <el-button type="text" @click="viewEditor(scope.row.message)">查看详情</el-button>
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="teacher.name" label="创建者" min-width="140" align="center" />
        <el-table-column fixed="right" label="操作" width="220" align="center">
          <template slot-scope="scope">
            <div class="teacher-table__actions">
              <el-button type="text" @click="courseUpdate(scope.row)">修改</el-button>
              <el-button type="text" @click="courseStudents(scope.row)">查看学生</el-button>
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
      ids: "",
      multipleSelection: "",
      params: {},
      editorVisible: false,
      viewData: "",
      dialogFormVisible: false,
      receivedData: ""
    };
  },
  created() {
    this.receivedData = this.$route.params;
    this.load();
  },
  methods: {
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
    load() {
      this.params = {
        pageNum: this.pageNum,
        pageSize: this.pageSize,
        teacherId: this.user.id
      };
      request.get("/exam/course/selectPage", {
        params: this.params
      }).then((res) => {
        if (res.code === "200") {
          this.total = res.data.total;
          this.tableData = res.data.list;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findBySearch() {
      request.get("/exam/course/selectPage", {
        params: this.params
      }).then((res) => {
        if (res.code === "200") {
          this.total = res.data.total;
          this.tableData = res.data.list;
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
        request.delete(`/exam/course/delete/${id}`).then((res) => {
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
        request.delete("/exam/course/delete/batch", { data: this.ids }).then((res) => {
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
    courseStudents(row) {
      this.$router.push({
        name: "teacherCourseStudent",
        params: {
          id: row.id
        }
      });
    },
    courseUpdate(row) {
      this.$router.push({
        name: "teacherCourseUpdate",
        params: {
          id: row.id
        }
      });
    }
  }
};
</script>
