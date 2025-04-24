<template>
  <div class="main">
    <!--    搜索框-->
    <div class="search">
      <el-row style="margin-top: 2vh;margin-left: 2%">
        <el-form :inline="true" :model="params" class="demo-form-inline">
          <el-form-item label="学生:" style="padding-right: 4%">
            <el-select v-model="params.studentId" clearable filterable placeholder="请选择">
              <el-option
                  v-for="item in student"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="是否加入:" style="padding-right: 4%">
            <el-select v-model="params.isAdd" clearable filterable placeholder="请选择">
              <el-option
                  v-for="item in isAdd"
                  :key="item.data"
                  :label="item.data"
                  :value="item.data">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button  clearable type="primary" @click="findBySearch">查询</el-button>
          </el-form-item>
        </el-form>
      </el-row>
      <el-row style="margin-top: 2vh;margin-left: 2%">
        <el-button type="primary" @click="selectAll" plain style="margin-right: 4%">全选</el-button>
        <el-button type="warning" @click="DeselectAll" plain style="margin-right: 4%">清空选择</el-button>
        <el-button type="danger" @click="deleteBatch" plain style="margin-right: 4%">批量删除</el-button>
      </el-row>
    </div>
<!--    表单-->
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
        <el-table-column ref="receivedData" type="selection" width="55" align="center" :reserve-selection="true" fixed>
        </el-table-column>
        <el-table-column prop="id" label="id" width="100" align="center" fixed></el-table-column>
        <el-table-column prop="courseId" label="课程id" width="100" align="center"></el-table-column>
        <el-table-column label="课程信息" align="center">
          <el-table-column prop="course.name" label="课程名称" width="200" align="center"></el-table-column>
          <el-table-column prop="course.photo" label="照片" width="100" align="center">
            <template v-slot="scope">
              <el-image
                  style="width: 40px; height: 40px;border-radius:50%"
                  :src="scope.row.course.photo"
                  :preview-src-list="[scope.row.course.photo]">
              </el-image>
            </template>
          </el-table-column>
          <el-table-column prop="course.number" label="课程编号" width="200" align="center"></el-table-column>
          <el-table-column prop="course.beginTime" label="开始时间" width="200" align="center"></el-table-column>
          <el-table-column prop="course.endTime" label="结束时间" width="200" align="center"></el-table-column>
        </el-table-column>
        <el-table-column prop="teacher.id" label="教师id" width="100" align="center" ></el-table-column>
        <el-table-column label="授课教师信息" align="center">
          <el-table-column  prop="teacher.name" label="姓名" width="100" align="center"></el-table-column>
          <el-table-column prop="teacher.photo" label="照片" width="100" align="center">
            <template v-slot="scope">
              <el-image
                  style="width: 40px; height: 40px;border-radius:50%"
                  :src="scope.row.teacher.photo"
                  :preview-src-list="[scope.row.teacher.photo]">
              </el-image>
            </template>
          </el-table-column>
          <el-table-column prop="teacher.role" label="角色" width="100" align="center"></el-table-column>
          <el-table-column prop="teacher.sex" label="性别" width="100" align="center"></el-table-column>
          <el-table-column prop="teacher.email" label="电子邮箱" width="200" align="center"></el-table-column>
          <el-table-column prop="teacher.phone" label="电话" width="200" align="center"></el-table-column>
          <el-table-column prop="teacher.account" label="账号" width="200" align="center"></el-table-column>
        </el-table-column>
        <el-table-column prop="studentId" label="学生id" width="100" align="center"></el-table-column>
        <el-table-column label="学生信息" align="center">
          <el-table-column  prop="student.name" label="姓名" width="100" align="center"></el-table-column>
          <el-table-column prop="student.photo" label="照片" width="100" align="center">
            <template v-slot="scope">
              <el-image
                  style="width: 40px; height: 40px;border-radius:50%"
                  :src="scope.row.student.photo"
                  :preview-src-list="[scope.row.student.photo]">
              </el-image>
            </template>
          </el-table-column>
          <el-table-column prop="student.role" label="角色" width="100" align="center"></el-table-column>
          <el-table-column prop="student.sex" label="性别" width="100" align="center"></el-table-column>
          <el-table-column prop="student.email" label="电子邮箱" width="200" align="center"></el-table-column>
          <el-table-column prop="student.phone" label="电话" width="200" align="center"></el-table-column>
          <el-table-column prop="student.account" label="账号" width="200" align="center"></el-table-column>
        </el-table-column>
        <el-table-column fixed="right" prop="isAdd" label="是否批准" width="100" align="center"></el-table-column>
        <el-table-column
            fixed="right"
            label="操作"
            width="100"
            align="center">
          <template slot-scope="scope">
            <el-button v-if='scope.row.isAdd==="待审核"' type="text" @click="agree(scope.row)" size="small" style="color: #5cea0a;">同意</el-button>
            <el-button v-if='scope.row.isAdd==="待审核"' type="text" @click="disagree(scope.row)" size="small" style="color: red;">拒绝</el-button>
            <el-button disabled v-if='scope.row.isAdd!=="待审核"' type="text" size="small" style="color: red;">
              {{ scope.row.isAdd }}</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div style="margin-top: 2vh;text-align: center">
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pageNum"
            :page-sizes="[5,10,15,20]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
        </el-pagination>
      </div>
    </div>

  </div>
</template>

<script>
import request from "@/utils/request"

export default {
  data(){
    return{
      user:localStorage.getItem("user") ? (JSON).parse(localStorage.getItem("user")) : {},
      form:{},
      tableData: [],
      pageNum: 1,   // 当前的页码
      pageSize: 10,  // 每页显示的个数
      total: 0,
      ids:"",
      multipleSelection:"",
      params:{},
      editorVisible:false,//富文本显示
      viewData:"",//富文本数据
      dialogFormVisible:false,//表单显示

      student:{},
      isAdd:[
        {data:"是"},
        {data:"否"},
      ],
      receivedData:"",
    }
  },
  created() {
    this.receivedData = this.$route.params

    this.load()
    this.findStudent()
  },
  mounted() {

  },
  methods: {
    tableHeaderColor ({ rowIndex, columnIndex }) {
      if (rowIndex === 0 && columnIndex === 0) {
        return { background: '#fffaee' }
      } else if (rowIndex === 0 && columnIndex !== 0) {
        return { background: '#fcfaf9', border: '2' }
      } else {
        return { background: '#fffaee' }
      }
    },
    handleSizeChange(pageSize){
      this.params.pageSize=pageSize;
      //查询
      this.findBySearch()
    },
    handleCurrentChange(pageNum){
      this.params.pageNum=pageNum;
      //查询
      this.findBySearch()
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
      // 获取选中数据的id
      this.ids = val.map(item => item.id);
    },
    getRowKeys(row){
      return row.id
    },
    load(){
      if (Object.keys(this.receivedData).length === 0) {
        // 若为空，跳转到指定路由，这里假设跳转到 '/home'
        this.$router.push("/teacher/teacherCourse")
      }
      this.params= {
        pageNum:this.pageNum,
        pageSize:this.pageSize,
        courseId:this.receivedData.id
      }
      request.get('/exam/courseStudent/selectPage', {
        params:this.params
      }).then(res => {
        if (res.code === '200') {
          this.total=res.data.total
          this.tableData=res.data.list
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    findBySearch(){
      request.get('/exam/courseStudent/selectPage', {
        params:this.params
      }).then(res => {
        if (res.code === '200') {
          this.total=res.data.total
          this.tableData=res.data.list
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    selectAll(){
      this.$refs.table.toggleAllSelection();
    },
    DeselectAll(){
      this.$refs.table.clearSelection();
      this.ids = [];
    },
    deleteById(id){
      this.$confirm('您确定删除吗？', '确认删除', {type: "warning"}).then(response => {
        request.delete('/exam/courseStudent/delete/' + id).then(res => {
          if (res.code === '200') {   // 表示操作成功
            this.$message.success('删除成功')
            this.findBySearch()
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      }).catch(() => {
      })
    },
    deleteBatch(){
      if (!this.ids.length) {
        this.$message.warning('请选择数据')
        return
      }
      this.$confirm('您确定批量删除这些数据吗？', '确认删除', {type: "warning"}).then(response => {
        request.delete('/exam/courseStudent/delete/batch', {data: this.ids}).then(res => {
          if (res.code === '200') {   // 表示操作成功
            this.$message.success('批量删除成功')
            this.findBySearch()
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      }).catch(() => {
      })
    },
    agree(row) {
      row.isAdd="同意"
      request.put("/exam/courseStudent/update",row).then(res=>{
        if(res.code==="200"){
          this.$message.success("成功")
          this.findBySearch()
        }
      })
    },
    disagree(row) {
      row.isAdd="拒绝"
      request.put("/exam/courseStudent/update",row).then(res=>{
        if(res.code==="200"){
          this.$message.success("成功")
          this.findBySearch()
        }
      })
    },
    findStudent(){
      request.get("user/student/selectAll").then(res=>{
        if (res.code === '200') {  // 表示成功保存
          this.student=res.data
        } else {
          this.$message.error(res.msg)  // 弹出错误的信息
        }
      })
    }
  }
}
</script>


<style scoped>
.main{
  padding-left: 3px;
  padding-right: 3px;
}
.search{
  height: 16vh;
}
.table{
  height: 66vh;
}


</style>

