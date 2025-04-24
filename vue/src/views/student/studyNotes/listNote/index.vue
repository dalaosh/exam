<template>
  <div class="main">
    <!--    -->
    <div class="search">
      <el-row style="margin-top: 2vh;margin-left: 2%">
        <!--        ip 操作人 操作名称-->
        <el-form :inline="true" :model="params" class="demo-form-inline">
          <el-form-item label="标题:" style="padding-right: 4%">
            <el-input v-model="params.name" placeholder="标题"></el-input>
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
        <el-button type="primary" @click="insert" plain >新增</el-button>
      </el-row>
    </div>
    <div class="table">
      <el-table
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
        <el-table-column ref="table" type="selection" width="55" align="center" :reserve-selection="true">
        </el-table-column>
        <el-table-column prop="id" label="id" width="100" align="center"></el-table-column>
        <el-table-column prop="student.name" label="姓名" width="100" align="center"></el-table-column>
        <el-table-column prop="name" label="题目" width="200" align="center"></el-table-column>
        <el-table-column prop="time" label="时间" width="150" align="center"></el-table-column>
        <el-table-column prop="address" label="路径" width="400" align="center"></el-table-column>
        <el-table-column
            fixed="right"
            label="操作"
            width="200"
            align="center">
          <template slot-scope="scope">
            <el-button type="text" @click="show(scope.row)" size="small" style="color: #7fa8e7;">查看</el-button>
            <el-button type="text" @click="update(scope.row)" size="small" style="color: #0767f6;">编辑</el-button>
            <el-button type="text" @click="download(scope.row)" size="small" style="color: #6f52f8;">下载</el-button>
            <el-button type="text" @click="deleteById(scope.row.id)" size="small" style="color: red;">删除</el-button>
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
      user:localStorage.getItem("user") ? (JSON).parse(localStorage.getItem("user")) : "",
      tableData: [],
      pageNum: 1,   // 当前的页码
      pageSize: 10,  // 每页显示的个数
      total: 0,
      ids:"",
      multipleSelection:"",
      params:{
      }
    }
  },
  created() {
    this.load()
  },
  mounted() {

  },
  methods: {
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
      this.params= {
        pageNum:this.pageNum,
        pageSize:this.pageSize,
        userId:this.user.id,
        role:this.user.role
      }
      request.get('/md/selectPage', {
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
      request.get('/md/selectPage', {
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
        request.delete('/md/delete/' + id,{data:this.user.role}).then(res => {
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
        request.delete('/md/delete/batch', {data: this.ids}).then(res => {
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
    insert() {
      this.$router.push("/student/creatNote")
    },
    update(row){
      this.$router.push({
        name: "studentUpdateNote",
        params: row
      });
    },
    download(row){
      location.href = 'http://localhost:9998'+row.address
    },
    show(row){
      this.$router.push({
        name: "studentShowNote",
        params: row
      });
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

