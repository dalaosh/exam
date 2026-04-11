<template>
  <div class="main">
    <div class="search">
      <el-row class="filter-row">
        <el-form :inline="true" :model="params" class="demo-form-inline">
          <el-form-item label="学生">
            <el-select v-model="params.studentId" clearable filterable placeholder="请选择">
              <el-option
                v-for="item in student"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="认证状态">
            <el-select v-model="params.faceSubmit" clearable filterable placeholder="请选择">
              <el-option
                v-for="item in isAdd"
                :key="item.data"
                :label="item.data"
                :value="item.data"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="findBySearch">查询</el-button>
          </el-form-item>
        </el-form>
      </el-row>
      <el-row class="toolbar-row">
        <el-button type="primary" plain @click="selectAll">全选</el-button>
        <el-button type="warning" plain @click="DeselectAll">清空选择</el-button>
      </el-row>
    </div>

    <div class="table">
      <el-table
        :header-cell-style="tableHeaderColor"
        ref="table"
        :data="tableData"
        tooltip-effect="dark"
        border
        show-overflow-tooltip
        stripe
        style="width: 100%"
        height="60vh"
        @selection-change="handleSelectionChange"
        :row-key="getRowKeys"
      >
        <el-table-column ref="receivedData" type="selection" width="55" align="center" :reserve-selection="true" fixed></el-table-column>
        <el-table-column prop="photo" label="人脸照片" width="100" align="center">
          <template v-slot="scope">
            <el-image
              style="width: 50px; height: 50px; border-radius: 8px"
              :src="scope.row.facePath"
              :preview-src-list="[scope.row.facePath]"
            ></el-image>
          </template>
        </el-table-column>
        <el-table-column prop="studentId" label="学生id" width="100" align="center"></el-table-column>
        <el-table-column label="学生信息" align="center">
          <el-table-column prop="student.name" label="姓名" width="100" align="center"></el-table-column>
          <el-table-column prop="student.photo" label="照片" width="100" align="center">
            <template v-slot="scope">
              <el-image
                style="width: 40px; height: 40px; border-radius: 50%"
                :src="scope.row.student.photo"
                :preview-src-list="[scope.row.student.photo]"
              ></el-image>
            </template>
          </el-table-column>
          <el-table-column prop="student.role" label="角色" width="100" align="center"></el-table-column>
          <el-table-column prop="student.sex" label="性别" width="100" align="center"></el-table-column>
          <el-table-column prop="student.email" label="电子邮箱" width="200" align="center"></el-table-column>
          <el-table-column prop="student.phone" label="电话" width="200" align="center"></el-table-column>
          <el-table-column prop="student.account" label="账号" width="200" align="center"></el-table-column>
        </el-table-column>
        <el-table-column prop="id" label="id" width="100" align="center" fixed></el-table-column>
        <el-table-column fixed="right" prop="faceSubmit" label="审核状态" width="120" align="center"></el-table-column>
        <el-table-column fixed="right" label="操作" width="100" align="center">
          <template slot-scope="scope">
            <el-button type="text" @click="agree(scope.row)" size="small" class="agree-link">认证</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="pagination-wrap">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[5, 10, 15, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
        ></el-pagination>
      </div>
    </div>

    <el-drawer
      title="认证信息"
      :visible.sync="drawer"
      direction="rtl"
      size="50%"
      :before-close="handleClose"
    >
      <el-row class="tops" :gutter="20">
        <el-col :span="12">
          <div class="title">考生信息</div>
          <div class="drawer-card">
            <el-row :gutter="20">
              <el-col :span="10">
                <el-image
                  style="width: 100%; height: 26vh"
                  :src="examStudent.student.photo"
                  fit="cover"
                ></el-image>
              </el-col>
              <el-col :span="14">
                <div class="message">姓名: {{ examStudent.student.name }}</div>
                <div class="message">性别: {{ examStudent.student.sex }}</div>
                <div class="message">角色: {{ examStudent.student.role }}</div>
                <div class="message">电话: {{ examStudent.student.phone }}</div>
              </el-col>
            </el-row>
            <div class="message">邮箱: {{ examStudent.student.email }}</div>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="title">身份证</div>
          <div class="drawer-card">
            <el-image
              style="width: 100%; height: 26vh"
              :src="studentFace.idPath"
              fit="cover"
            ></el-image>
          </div>
        </el-col>
      </el-row>

      <el-row class="middles" :gutter="20">
        <el-col :span="12">
          <div class="title">照片</div>
          <div class="drawer-card">
            <el-image
              style="width: 100%; height: 26vh"
              :src="studentFace.facePath"
              fit="cover"
            ></el-image>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="title">考生认证</div>
          <div class="drawer-card">
            <el-image
              style="width: 100%; height: 26vh"
              :src="examStudent.facePath"
              fit="cover"
            ></el-image>
          </div>
        </el-col>
      </el-row>

      <div class="bottoms">
        <el-button type="primary" class="drawer-submit" @click="submit">认证</el-button>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import request from "@/utils/request"

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
      drawer: false,
      student: {},
      isAdd: [
        { data: "验证成功" },
        { data: "验证失败" }
      ],
      receivedData: "",
      examStudent: {
        student: {}
      },
      studentFace: {}
    }
  },
  created() {
    this.receivedData = this.$route.params
    this.load()
    this.findStudent()
  },
  methods: {
    tableHeaderColor({ rowIndex, columnIndex }) {
      if (rowIndex === 0 && columnIndex === 0) {
        return { background: "#fffaee" }
      } else if (rowIndex === 0 && columnIndex !== 0) {
        return { background: "#fcfaf9", border: "2" }
      } else {
        return { background: "#fffaee" }
      }
    },
    handleSizeChange(pageSize) {
      this.params.pageSize = pageSize
      this.findBySearch()
    },
    handleCurrentChange(pageNum) {
      this.params.pageNum = pageNum
      this.findBySearch()
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
      this.ids = val.map(item => item.id)
    },
    getRowKeys(row) {
      return row.id
    },
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then(_ => {
          this.drawer = false
          this.studentFace = {}
          done()
        })
        .catch(_ => {
          this.drawer = false
          this.studentFace = {}
        })
    },
    load() {
      this.params = {
        pageNum: this.pageNum,
        pageSize: this.pageSize,
        managerId: this.receivedData.id
      }
      request.get("/exam/examStudent/selectPage", {
        params: this.params
      }).then(res => {
        if (res.code === "200") {
          this.total = res.data.total
          this.tableData = res.data.list
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    findBySearch() {
      request.get("/exam/examStudent/selectPage", {
        params: this.params
      }).then(res => {
        if (res.code === "200") {
          this.total = res.data.total
          this.tableData = res.data.list
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    selectAll() {
      this.$refs.table.toggleAllSelection()
    },
    DeselectAll() {
      this.$refs.table.clearSelection()
      this.ids = []
    },
    agree(row) {
      if (row.faceSubmit === "验证成功") {
        this.$message.warning("人脸认证已经成功")
      } else if (row.faceSubmit === "教师代理") {
        this.$message.warning("人脸认证已经代理完成")
      } else {
        this.examStudent = row
        this.findStudentFace()
        this.drawer = true
      }
    },
    findStudentFace() {
      request.get("trustSeal/studentFace/selectByUserId/" + this.examStudent.student.id).then(res => {
        if (res.code === "200") {
          this.studentFace = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    submit() {
      request.put("/exam/examStudent/updateSubmitByTeacher", {
        id: this.examStudent.id,
        facePath: this.studentFile,
        faceSubmit: "教师代理"
      }).then(res => {
        if (res.code === "200") {
          this.$message.success("认证成功")
          this.drawer = false
          this.load()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    findStudent() {
      request.get("user/student/selectAll").then(res => {
        if (res.code === "200") {
          this.student = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    }
  }
}
</script>

<style scoped>
.main {
  display: flex;
  flex-direction: column;
  gap: 18px;
  padding-bottom: 8px;
}

.search,
.table {
  padding: 22px 24px;
  border: 1px solid rgba(226, 232, 240, 0.95);
  border-radius: 24px;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0.98), rgba(248, 250, 252, 0.96));
  box-shadow: 0 20px 48px rgba(15, 23, 42, 0.08);
}

.filter-row {
  margin-bottom: 16px;
}

.toolbar-row {
  display: flex;
  gap: 12px;
}

.pagination-wrap {
  margin-top: 18px;
  text-align: center;
}

.tops,
.middles {
  margin-bottom: 18px;
}

.title {
  margin-bottom: 14px;
  font-size: 18px;
  font-weight: 700;
  text-align: center;
  color: #0f172a;
}

.drawer-card,
.bottoms {
  padding: 20px 22px;
  border: 1px solid #e2e8f0;
  border-radius: 22px;
  background: linear-gradient(180deg, #ffffff 0%, #f8fafc 100%);
}

.bottoms {
  display: flex;
  justify-content: center;
}

.drawer-submit {
  min-width: 180px;
  height: 46px;
  border-radius: 14px;
  font-size: 15px;
  font-weight: 700;
}

.message {
  margin: 0 0 12px;
  font-size: 15px;
  line-height: 1.8;
  color: #475569;
}

.agree-link {
  color: #1d4ed8;
  font-weight: 600;
}

:deep(.demo-form-inline) {
  display: flex;
  flex-wrap: wrap;
  gap: 12px 16px;
  align-items: flex-end;
}

:deep(.demo-form-inline .el-form-item) {
  margin-bottom: 0;
}

:deep(.el-form-item__label) {
  color: #475569;
  font-weight: 600;
}

:deep(.el-input__inner),
:deep(.el-select .el-input__inner) {
  border-radius: 12px;
  border-color: #dbe4ee;
}

:deep(.el-button) {
  border-radius: 12px;
  font-weight: 600;
}

:deep(.el-table) {
  border-radius: 18px;
  overflow: hidden;
  border: 1px solid #e2e8f0;
}

:deep(.el-table th) {
  background: #f8fafc !important;
  color: #334155;
  font-weight: 700;
}

:deep(.el-table td) {
  color: #475569;
}

:deep(.el-drawer__header) {
  margin-bottom: 0;
  padding: 22px 24px 0;
  font-size: 20px;
  font-weight: 700;
  color: #0f172a;
}

:deep(.el-drawer__body) {
  padding: 16px 24px 24px;
  background: linear-gradient(180deg, #f8fafc 0%, #ffffff 100%);
}

:deep(.el-image) {
  border-radius: 18px;
  overflow: hidden;
}
</style>
