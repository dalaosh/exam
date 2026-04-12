<template>
  <div class="teacher-page teacher-question-list-page">
    <section class="teacher-page-head">
      <div>
        <h2 class="teacher-page-head__title">学生管理</h2>
        <p class="teacher-page-head__desc">
          统一查看学生账号、联系方式和密钥摘要，保持查询、选择和导出行为不变。
        </p>
      </div>
      <div class="teacher-page-head__meta">
        <el-tag class="teacher-tag" type="info">共 {{ total }} 条记录</el-tag>
      </div>
    </section>

    <section class="teacher-panel teacher-question-list-page__toolbar">
      <el-form :inline="true" :model="params" class="teacher-toolbar__form teacher-question-list-page__filters">
        <el-form-item label="账号">
          <el-input v-model="params.account" clearable placeholder="请输入账号"></el-input>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="params.name" clearable placeholder="请输入姓名"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-input v-model="params.sex" clearable placeholder="请输入性别"></el-input>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="params.phone" clearable placeholder="请输入电话"></el-input>
        </el-form-item>
        <el-form-item label="电子邮箱">
          <el-input v-model="params.email" clearable placeholder="请输入电子邮箱"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="findBySearch">查询</el-button>
        </el-form-item>
      </el-form>

      <div class="teacher-question-list-page__actions">
        <el-button type="primary" plain @click="selectAll">全选</el-button>
        <el-button type="warning" plain @click="DeselectAll">清空选择</el-button>
        <el-button type="success" plain @click="exp()">批量导出</el-button>
      </div>
    </section>

    <section class="teacher-panel teacher-panel--table">
      <div class="teacher-panel__head">
        <div>
          <h3 class="teacher-panel__title">学生列表</h3>
          <p class="teacher-panel__desc">统一表格层级和文本展示，便于教师快速浏览学生信息。</p>
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
        :row-key="getRowKeys"
        @selection-change="handleSelectionChange"
      >
        <el-table-column ref="table" type="selection" width="55" align="center" :reserve-selection="true" fixed />
        <el-table-column prop="id" label="学生 ID" width="110" align="center" fixed />
        <el-table-column prop="name" label="姓名" width="120" align="center" fixed />
        <el-table-column prop="photo" label="照片" width="110" align="center">
          <template v-slot="scope">
            <el-image class="teacher-table__avatar" :src="scope.row.photo" :preview-src-list="[scope.row.photo]" />
          </template>
        </el-table-column>
        <el-table-column prop="role" label="角色" width="110" align="center">
          <template v-slot="scope">
            <el-tag class="teacher-tag" type="info">{{ scope.row.role }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="sex" label="性别" width="100" align="center" />
        <el-table-column prop="email" label="电子邮箱" min-width="220" align="center" />
        <el-table-column prop="phone" label="电话" min-width="180" align="center" />
        <el-table-column prop="account" label="账号" min-width="180" align="center" />
        <el-table-column label="密码" width="250" align="center">
          <template slot-scope="scope">
            <el-input
              v-model="scope.row.password"
              class="teacher-table__textarea"
              type="textarea"
              :readonly="true"
              :autosize="{ minRows: 2, maxRows: 2 }"
            />
          </template>
        </el-table-column>
        <el-table-column label="密码摘要" width="250" align="center">
          <template slot-scope="scope">
            <el-input
              v-model="scope.row.keySm3"
              class="teacher-table__textarea"
              type="textarea"
              :readonly="true"
              :autosize="{ minRows: 2, maxRows: 2 }"
            />
          </template>
        </el-table-column>
        <el-table-column label="考试公钥" width="250" align="center">
          <template slot-scope="scope">
            <el-input
              v-model="scope.row.publicKey"
              class="teacher-table__textarea"
              type="textarea"
              :readonly="true"
              :autosize="{ minRows: 2, maxRows: 2 }"
            />
          </template>
        </el-table-column>
      </el-table>

      <div class="teacher-pagination">
        <el-pagination
          :current-page="pageNum"
          :page-sizes="[5, 10, 15, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        />
      </div>
    </section>

    <el-dialog title="请填写信息" :visible.sync="dialogFormVisible" width="560px" custom-class="teacher-dialog">
      <el-form ref="formRef" :model="form" :rules="rules" class="teacher-dialog__form" label-width="88px">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-input v-model="form.sex" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="电子邮箱" prop="email">
          <el-input v-model="form.email" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input v-model="form.phone" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="teacher-dialog__footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="submit()">确定</el-button>
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
      rules: {
        name: [{ required: true, message: "请输入姓名", trigger: "blur" }],
        sex: [{ required: true, message: "请输入性别", trigger: "blur" }],
        email: [{ required: true, message: "请输入电子邮箱", trigger: "blur" }],
        phone: [{ required: true, message: "请输入电话", trigger: "blur" }]
      },
      form: {},
      tableData: [],
      pageNum: 1,
      pageSize: 10,
      total: 0,
      ids: [],
      multipleSelection: "",
      params: {},
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
        pageSize: this.pageSize
      };
      request
        .get("/user/student/selectPage", {
          params: this.params
        })
        .then((res) => {
          if (res.code === "200") {
            this.total = res.data.total;
            this.tableData = res.data.list;
          } else {
            this.$message.error(res.msg);
          }
        });
    },
    findBySearch() {
      request
        .get("/user/student/selectPage", {
          params: this.params
        })
        .then((res) => {
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
    submit() {
      this.form.username = this.form.id ? "" : this.user.name;
      this.form.photo =
        "https://tse2-mm.cn.bing.net/th/id/OIP-C.-UuSAtM_GHACvgnxzzvj7AHaHa?w=193&h=193&c=7&r=0&o=5&dpr=1.5&pid=1.7";
      request({
        url: this.form.id ? "/user/student/update" : "/user/student/add",
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
    insert() {
      this.form = {};
      this.dialogFormVisible = true;
    },
    update(row) {
      this.form = row;
      this.dialogFormVisible = true;
    },
    exp() {
      location.href = "http://localhost:9998/user/student/export";
    }
  }
};
</script>
