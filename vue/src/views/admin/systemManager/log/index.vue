<template>
  <div class="admin-page admin-page--table">
    <section class="admin-page-head">
      <div>
        <h2 class="admin-page-head__title">日志管理</h2>
        <p class="admin-page-head__desc">查看系统操作日志，支持条件筛选与批量删除。</p>
      </div>
      <div class="admin-page-head__meta">
        <el-tag class="admin-tag" type="info">共 {{ total }} 条记录</el-tag>
      </div>
    </section>

    <section class="admin-panel admin-panel--toolbar">
      <div class="admin-toolbar">
        <el-form :inline="true" :model="params" class="admin-toolbar__form">
          <el-form-item label="IP">
            <el-input v-model="params.ip" clearable placeholder="请输入 IP"></el-input>
          </el-form-item>
          <el-form-item label="操作人">
            <el-input v-model="params.username" clearable placeholder="请输入操作人"></el-input>
          </el-form-item>
          <el-form-item label="操作名称">
            <el-input v-model="params.name" clearable placeholder="请输入操作名称"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="findBySearch">查询</el-button>
          </el-form-item>
        </el-form>

        <div class="admin-toolbar__actions">
          <el-button type="primary" plain @click="selectAll">全选</el-button>
          <el-button type="warning" plain @click="DeselectAll">清空选择</el-button>
          <span class="admin-toolbar__spacer"></span>
          <el-button type="danger" plain @click="deleteBatch">批量删除</el-button>
        </div>
      </div>
    </section>

    <section class="admin-panel admin-panel--table">
      <div class="admin-panel__head">
        <div>
          <h3 class="admin-panel__title">日志列表</h3>
          <p class="admin-panel__desc">用于追踪用户操作、角色身份和访问来源。</p>
        </div>
      </div>

      <el-table
        ref="table"
        class="admin-table"
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
        <el-table-column prop="id" label="日志ID" width="180" align="center" />
        <el-table-column prop="userId" label="用户ID" width="120" align="center" />
        <el-table-column prop="username" label="操作人" width="160" align="center" />
        <el-table-column prop="role" label="角色" width="140" align="center">
          <template v-slot="scope">
            <el-tag class="admin-tag" type="info">{{ scope.row.role }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="name" label="操作内容" min-width="200" align="center" />
        <el-table-column prop="ip" label="IP 地址" width="180" align="center" />
        <el-table-column prop="time" label="时间" min-width="180" align="center" />
        <el-table-column fixed="right" label="操作" width="100" align="center">
          <template slot-scope="scope">
            <div class="admin-table__actions">
              <el-button type="text" class="admin-table__action" style="color: #dc2626;" @click="deleteById(scope.row.id)">
                删除
              </el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>

      <div class="admin-pagination">
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
      tableData: [],
      pageNum: 1,
      pageSize: 10,
      total: 0,
      ids: "",
      multipleSelection: "",
      params: {}
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
        pageSize: this.pageSize
      };
      request.get("/info/log/selectPage", {
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
      request.get("/info/log/selectPage", {
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
      this.$confirm("您确定删除这条日志吗？", "确认删除", { type: "warning" }).then(() => {
        request.delete("/info/log/delete/" + id).then((res) => {
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
      this.$confirm("您确定批量删除这些日志吗？", "确认删除", { type: "warning" }).then(() => {
        request.delete("/info/log/delete/batch", { data: this.ids }).then((res) => {
          if (res.code === "200") {
            this.$message.success("批量删除成功");
            this.findBySearch();
          } else {
            this.$message.error(res.msg);
          }
        });
      }).catch(() => {});
    },
  }
};
</script>
