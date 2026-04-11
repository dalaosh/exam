<template>
  <div class="admin-page admin-page--table">
    <section class="admin-page-head">
      <div>
        <h2 class="admin-page-head__title">新闻管理</h2>
        <p class="admin-page-head__desc">维护新闻内容，支持搜索、编辑、查看和批量处理。</p>
      </div>
      <div class="admin-page-head__meta">
        <el-tag class="admin-tag" type="info">共 {{ total }} 条新闻</el-tag>
      </div>
    </section>

    <section class="admin-panel admin-panel--toolbar">
      <div class="admin-toolbar">
        <el-form :inline="true" :model="params" class="admin-toolbar__form">
          <el-form-item label="标题">
            <el-input v-model="params.name" clearable placeholder="请输入标题"></el-input>
          </el-form-item>
          <el-form-item label="内容">
            <el-input v-model="params.content" clearable placeholder="请输入内容关键词"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="findBySearch">查询</el-button>
          </el-form-item>
        </el-form>

        <div class="admin-toolbar__actions">
          <el-button type="primary" plain @click="selectAll">全选</el-button>
          <el-button type="warning" plain @click="DeselectAll">清空选择</el-button>
          <el-button type="danger" plain @click="deleteBatch">批量删除</el-button>
          <span class="admin-toolbar__spacer"></span>
          <el-button type="primary" @click="insert">新增新闻</el-button>
        </div>
      </div>
    </section>

    <section class="admin-panel admin-panel--table">
      <div class="admin-panel__head">
        <div>
          <h3 class="admin-panel__title">新闻列表</h3>
          <p class="admin-panel__desc">支持查看详情、编辑新闻以及批量管理。</p>
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
        <el-table-column prop="id" label="新闻ID" width="120" align="center" />
        <el-table-column prop="admin.name" label="发布人" width="140" align="center" />
        <el-table-column prop="name" label="新闻标题" min-width="260" align="center" />
        <el-table-column prop="content" label="新闻内容" width="140" align="center">
          <template slot-scope="scope">
            <div class="admin-table__actions">
              <el-button type="text" class="admin-table__action" style="color: #2563eb;" @click="viewEditor(scope.row.content)">
                查看详情
              </el-button>
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="time" label="发布时间" min-width="180" align="center" />
        <el-table-column fixed="right" label="操作" width="130" align="center">
          <template slot-scope="scope">
            <div class="admin-table__actions">
              <el-button type="text" class="admin-table__action" style="color: #2563eb;" @click="update(scope.row)">
                编辑
              </el-button>
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

    <el-dialog
      title="新闻详情"
      :visible.sync="editorVisible"
      width="760px"
      custom-class="admin-dialog"
    >
      <div class="admin-rich-content" v-html="viewData"></div>
    </el-dialog>

    <el-dialog
      title="新闻信息"
      :visible.sync="dialogFormVisible"
      width="820px"
      custom-class="admin-dialog"
    >
      <el-form :model="form" label-width="88px" class="admin-dialog__form">
        <el-form-item label="新闻标题">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="新闻内容">
          <div id="editor" class="admin-dialog__editor"></div>
        </el-form-item>
      </el-form>
      <div slot="footer" class="admin-dialog__footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="submit()">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import request from "@/utils/request";
import E from "wangeditor";

let editor;
function initWangEditor(content) {
  setTimeout(() => {
    if (!editor) {
      editor = new E("#editor");
      editor.config.placeholder = "请输入内容";
      editor.config.uploadFileName = "file";
      editor.config.uploadImgServer = "http://localhost:9998/files/wang/upload";
      editor.create();
    }
    editor.txt.html(content);
  }, 0);
}

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
      dialogFormVisible: false
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
        userId: this.user.id
      };
      request.get("/info/news/selectPage", {
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
      request.get("/info/news/selectPage", {
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
      this.$confirm("您确定删除这条新闻吗？", "确认删除", { type: "warning" }).then(() => {
        request.delete("/info/news/delete/" + id).then((res) => {
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
      this.$confirm("您确定批量删除这些新闻吗？", "确认删除", { type: "warning" }).then(() => {
        request.delete("/info/news/delete/batch", { data: this.ids }).then((res) => {
          if (res.code === "200") {
            this.$message.success("批量删除成功");
            this.findBySearch();
          } else {
            this.$message.error(res.msg);
          }
        });
      }).catch(() => {});
    },
    submit() {
      this.form.content = editor.txt.html();
      this.form.userId = this.form.id ? "" : this.user.id;
      request({
        url: this.form.id ? "/info/news/update" : "/info/news/add",
        method: this.form.id ? "PUT" : "POST",
        data: this.form
      }).then((res) => {
        if (res.code === "200") {
          this.$message.success("保存成功");
          this.findBySearch();
          this.dialogFormVisible = false;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    viewEditor(data) {
      this.editorVisible = true;
      this.viewData = data;
    },
    insert() {
      this.form = {};
      initWangEditor("");
      this.dialogFormVisible = true;
    },
    update(row) {
      this.form = row;
      initWangEditor(row.content);
      this.dialogFormVisible = true;
    },
  }
};
</script>
