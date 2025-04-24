<template>
  <div class="main">
    <!--    搜索框-->
    <div class="search">
      <el-row style="margin-top: 2vh;margin-left: 2%">
        <el-form :inline="true" :model="params" class="demo-form-inline">
          <el-form-item label="账号:" style="padding-right: 4%">
            <el-input v-model="params.account" placeholder="账号"></el-input>
          </el-form-item>
          <el-form-item label="姓名:" style="padding-right: 4%">
            <el-input v-model="params.name" placeholder="姓名"></el-input>
          </el-form-item>
          <el-form-item label="性别:" style="padding-right: 4%">
            <el-input v-model="params.sex" placeholder="性别"></el-input>
          </el-form-item>
          <el-form-item label="电话:" style="padding-right: 4%">
            <el-input  clearable v-model="params.phone" placeholder="电话"></el-input>
          </el-form-item>
          <el-form-item label="电子邮箱:" style="padding-right: 4%">
            <el-input  clearable v-model="params.email" placeholder="电子邮箱"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button  clearable type="primary" @click="findBySearch">查询</el-button>
          </el-form-item>
        </el-form>
      </el-row>
      <el-row style="margin-top: 2vh;margin-left: 2%">
        <el-button type="primary" @click="selectAll" plain style="margin-right: 4%">全选</el-button>
        <el-button type="primary" @click="DeselectAll" plain style="margin-right: 4%">清空选择</el-button>
        <el-button type="primary" @click="submitAllSuccess" plain style="margin-right: 4%">批量认证同意</el-button>
        <el-button type="primary" @click="submitAllError" plain style="margin-right: 4%">批量认证拒绝</el-button>
        <el-button type="primary" @click="selectSuccess" plain style="margin-right: 4%">认证成功</el-button>
        <el-button type="primary" @click="selectError" plain style="margin-right: 4%">认证失败</el-button>
        <el-button type="primary" @click="selectNo" plain style="margin-right: 4%">未认证</el-button>
      </el-row>
    </div>
    <!--    表单-->
    <div class="table">
      <el-table
          ref="table"
          :data="tableData"
          tooltip-effect="dark"
          border
          show-overflow-tooltip
          stripe
          style="width: 100%"
          height="54vh"
          @selection-change="handleSelectionChange"
          :row-key="getRowKeys"
      >
        <el-table-column ref="table" type="selection" width="55" align="center" :reserve-selection="true"  fixed>
        </el-table-column>
        <el-table-column prop="id" label="学生id" width="100" align="center"  fixed></el-table-column>
        <el-table-column  prop="student.name" label="姓名" width="100" align="center"  fixed></el-table-column>
        <el-table-column  prop="isSubmit" label="认证" width="100" align="center"  fixed></el-table-column>
        <el-table-column prop="photo" label="头像" width="100" align="center">
          <template v-slot="scope">
            <el-image
                style="width: 40px; height: 40px;border-radius:50%"
                :src="scope.row.student.photo"
                :preview-src-list="[scope.row.student.photo,scope.row.facePath,scope.row.idPath]">
            </el-image>
          </template>
        </el-table-column>
        <el-table-column prop="photo" label="照片" width="100" align="center">
          <template v-slot="scope">
            <el-image
                style="width: 40px; height: 40px;border-radius:10%"
                :src="scope.row.facePath"
                :preview-src-list="[scope.row.student.photo,scope.row.facePath,scope.row.idPath]">
            </el-image>
          </template>
        </el-table-column>
        <el-table-column prop="photo" label="身份证" width="100" align="center">
          <template v-slot="scope">
            <el-image
                style="width: 40px; height: 40px;border-radius:10%"
                :src="scope.row.idPath"
                :preview-src-list="[scope.row.student.photo,scope.row.facePath,scope.row.idPath]">
            </el-image>
          </template>
        </el-table-column>
        <el-table-column label="学校信息" align="center">
          <el-table-column prop="schoolsUniversity.universityName" label="省" width="100" align="center"></el-table-column>
          <el-table-column prop="schoolsColleges.collegesName" label="市" width="100" align="center"></el-table-column>
          <el-table-column prop="schoolsMajor.majorName" label="县" width="100" align="center"></el-table-column>
        </el-table-column>
        <el-table-column prop="addressAreas.areaId" label="地区编号" width="100" align="center"></el-table-column>
        <el-table-column label="地址信息" align="center">
          <el-table-column prop="addressProvinces.province" label="省" width="100" align="center"></el-table-column>
          <el-table-column prop="addressCities.city" label="市" width="100" align="center"></el-table-column>
          <el-table-column prop="addressAreas.area" label="县" width="100" align="center"></el-table-column>
        </el-table-column>
        <el-table-column prop="student.role" label="角色" width="100" align="center"></el-table-column>
        <el-table-column prop="student.sex" label="性别" width="100" align="center"></el-table-column>
        <el-table-column prop="student.email" label="电子邮箱" width="200" align="center"></el-table-column>
        <el-table-column prop="student.phone" label="电话" width="200" align="center"></el-table-column>
        <el-table-column prop="student.account" label="账号" width="200" align="center"></el-table-column>
        <el-table-column
            fixed="right"
            label="操作"
            width="150"
            align="center"
        >
          <template slot-scope="scope">
            <el-button type="text" @click="submit(scope.row.id,'同意')" size="small" style="color: red;">认证同意</el-button>
            <el-button type="text" @click="submit(scope.row.id,'拒绝')" size="small" style="color: red;">认证拒绝</el-button>
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
      ids:[],
      multipleSelection:"",
      params:{},
      dialogFormVisible:false//表单显示
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
        pageSize:this.pageSize
      }
      request.get('trustSeal/studentFace/selectPage', {
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
      request.get('trustSeal/studentFace/selectPage', {
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
    selectSuccess(){
      this.params.isSubmit="同意"
      request.get('trustSeal/studentFace/selectPage', {
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
    selectError(){
      this.params.isSubmit="拒绝"
      request.get('trustSeal/studentFace/selectPage', {
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
    selectNo(){
      this.params.isSubmit="否"
      request.get('trustSeal/studentFace/selectPage', {
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
    submit(id,agree) {
      let params;
      params={
        id:id,
        isSubmit: agree
      }
      request.put("/trustSeal/studentFace/update",params).then(res=>{
        if(res.code==="200"){
          this.$message.success("成功")
          this.findBySearch()
        }
        else {
          this.$message.error("失败")
        }
      })
    },
    submitAllSuccess(){
      if (!this.ids.length) {
        this.$message.warning('请选择数据')
        return
      }
      this.$confirm('您确定进行选中数据的批量认证吗？', '确认认证', {type: "warning"}).then(response => {
        request.put('/trustSeal/studentFace/update/batchSuccess',this.ids).then(res => {
          if (res.code === '200') {   // 表示操作成功
            this.$message.success('批量认证同意成功')
            this.findBySearch()
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      }).catch(() => {
      })
    },
    submitAllError(){
      if (!this.ids.length) {
        this.$message.warning('请选择数据')
        return
      }
      this.$confirm('您确定进行选中数据的批量认证吗？', '确认认证', {type: "warning"}).then(response => {
        request.put('/trustSeal/studentFace/update/batchError', this.ids).then(res => {
          if (res.code === '200') {   // 表示操作成功
            this.$message.success('批量认证拒绝成功')
            this.findBySearch()
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      }).catch(() => {
      })
    },
    insert() {
      this.form = {};
      this.dialogFormVisible = true;
    },
    update(row) {
      this.form = row;
      this.dialogFormVisible = true;
    },
  }
}
</script>


<style scoped>
.main{
  padding-left: 3px;
  padding-right: 3px;
}
.search{
  height: 22vh;
}
.table{
  height: 60vh;
}


</style>