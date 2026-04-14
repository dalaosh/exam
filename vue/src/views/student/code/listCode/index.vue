<template>
  <div class="student-page student-list-page">
    <section class="student-page-head">
      <div>
        <h2 class="student-page-head__title">代码信息</h2>
        <p class="student-page-head__desc">统一查看代码实操题目、语言类型与时间信息，保持原有查看、编辑与删除流程。</p>
      </div>
      <div class="student-page-head__meta">
        <el-tag class="student-tag" type="info">共 {{ total }} 条记录</el-tag>
      </div>
    </section>

    <section class="student-panel student-panel--toolbar student-list-page__toolbar">
      <div class="student-toolbar student-list-page__toolbar-inner">
        <el-form :inline="true" :model="params" class="student-toolbar__form student-list-page__filters">
          <el-form-item label="问题">
            <el-input v-model="params.title" clearable placeholder="请输入问题"></el-input>
          </el-form-item>
          <el-form-item label="课程">
            <el-select v-model="params.courseId" clearable filterable placeholder="请选择课程">
              <el-option
                v-for="item in ty"
                :key="item.data"
                :label="item.data"
                :value="item.data"
              />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="findBySearch">查询</el-button>
          </el-form-item>
        </el-form>

        <div class="student-toolbar__actions student-list-page__actions">
          <el-button type="primary" plain @click="selectAll">全选</el-button>
          <el-button type="warning" plain @click="DeselectAll">清空选择</el-button>
          <el-button type="danger" plain @click="deleteBatch">批量删除</el-button>
          <span class="student-toolbar__spacer"></span>
          <el-button type="primary" @click="insert">新增实操</el-button>
        </div>
      </div>
    </section>

    <section class="student-panel student-panel--table student-list-page__table-panel">
      <div class="student-panel__head student-list-page__table-head">
        <div>
          <h3 class="student-panel__title">代码列表</h3>
          <p class="student-panel__desc">表格视觉与按钮层级统一，长文本题目采用只读文本域保持可读性。</p>
        </div>
      </div>

      <el-table
        ref="table"
        class="student-table student-list-page__table"
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
        <el-table-column ref="table" type="selection" width="55" align="center" :reserve-selection="true" />
        <el-table-column prop="id" label="ID" width="90" align="center" />
        <el-table-column prop="student.name" label="姓名" width="110" align="center" />
        <el-table-column prop="title" label="题目" min-width="320" align="center">
          <template slot-scope="scope">
            <el-input
              class="student-table__textarea"
              type="textarea"
              :readonly="true"
              :autosize="{ minRows: 2, maxRows: 2 }"
              v-model="scope.row.title"
            />
          </template>
        </el-table-column>
        <el-table-column prop="time" label="时间" min-width="180" align="center" />
        <el-table-column prop="type" label="类型" width="120" align="center" />
        <el-table-column fixed="right" label="操作" width="220" align="center">
          <template slot-scope="scope">
            <div class="student-table__actions">
              <el-button type="text" @click="show(scope.row)">查看</el-button>
              <el-button type="text" @click="update(scope.row)">编辑</el-button>
              <el-button type="text" style="color: #dc2626;" @click="deleteById(scope.row.id)">删除</el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>

      <div class="student-pagination">
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
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : "",
      tableData: [],
      pageNum: 1,
      pageSize: 10,
      total: 0,
      ids: "",
      multipleSelection: "",
      params: {},
      ty: [
        { data: "c" },
        { data: "c++" }
      ]
    };
  },
  created() {
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
        studentId: this.user.id,
        role: this.user.role
      };
      request.get("/exam/code/selectPage", {
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
      request.get("/exam/code/selectPage", {
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
        request.delete(`/exam/code/delete/${id}`, { data: this.user.role }).then((res) => {
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
        request.delete("/exam/code/delete/batch", { data: this.ids }).then((res) => {
          if (res.code === "200") {
            this.$message.success("批量删除成功");
            this.findBySearch();
          } else {
            this.$message.error(res.msg);
          }
        });
      }).catch(() => {});
    },
    insert() {
      this.$router.push("/student/createdCode");
    },
    update(row) {
      this.$router.push({
        name: "studentUpdateCode",
        params: {
          id: row.id
        }
      });
    },
    show(row) {
      this.$router.push({
        name: "studentShowCode",
        params: {
          id: row.id
        }
      });
    }
  }
};
</script>
