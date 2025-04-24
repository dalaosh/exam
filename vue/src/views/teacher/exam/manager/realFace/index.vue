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
          <el-form-item label="验证状态:" style="padding-right: 4%">
            <el-select v-model="params.faceSubmit" clearable filterable placeholder="请选择">
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
        <el-table-column prop="photo" label="人脸照片" width="100" align="center">
          <template v-slot="scope">
            <el-image
                style="width: 50px; height: 50px;border-radius:5%"
                :src="scope.row.facePath"
                :preview-src-list="[scope.row.facePath]">
            </el-image>
          </template>
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
        <el-table-column prop="id" label="id" width="100" align="center" fixed></el-table-column>
        <el-table-column fixed="right" prop="faceSubmit" label="审核状况" width="100" align="center"></el-table-column>
        <el-table-column
            fixed="right"
            label="操作"
            width="100"
            align="center">
          <template slot-scope="scope">
            <el-button type="text" @click="agree(scope.row)" size="small" style="color: #5cea0a;">认证</el-button>
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
    <el-drawer
        title="认证信息"
        :visible.sync="drawer"
        direction="rtl"
        size="50%"
        :before-close="handleClose">
      <el-row class="tops">
        <el-col :span="12">
          <div class="title">考生信息</div>
          <div class="left-drawer">
            <el-row :gutter="20">
              <el-col :span="10">
                <el-image
                    style="width: 100%; height: 26vh"
                    :src="examStudent.student.photo"
                    fit="fit"></el-image>
              </el-col>
              <el-col :span="14">
                <div class="message">姓名: {{examStudent.student.name}}</div>
                <div class="message">性别: {{examStudent.student.sex}}</div>
                <div class="message">角色: {{examStudent.student.role}}</div>
                <div style="font-size: 20px;margin-top: 2vh;margin-left: 4%;">电话: {{examStudent.student.phone}}</div>
              </el-col>
            </el-row>
            <div class="message">邮箱: {{examStudent.student.email}}</div>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="title">身份证</div>
          <div>
            <el-image
                style="width: 90%; height: 26vh;margin-left: 5%;margin-top: 3vh"
                :src="studentFace.idPath"
                fit="fit"></el-image>
          </div>
        </el-col>
      </el-row>
      <el-row class="middles">
        <el-col :span="12">
          <div class="title">照片</div>
          <div>
            <el-image
                style="width: 90%; height: 26vh;margin-left: 5%;"
                :src="studentFace.facePath"
                fit="fit"></el-image>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="title">考生认证</div>
          <div>
            <el-image
                style="width: 90%; height: 26vh;margin-left: 5%;"
                :src="examStudent.facePath"
                fit="fit"></el-image>
          </div>
        </el-col>
      </el-row>
      <el-row class="bottoms">
        <div style="text-align: center;padding-top: 1vh">
          <el-button type="primary" @click="submit" plain style="font-size: 24px;width: 40%">认证</el-button>
        </div>
        <div>
        </div>
      </el-row>
    </el-drawer>
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
      drawer:false,
      student:{},
      isAdd:[
        {data:"验证成功"},
        {data:"验证失败"},
      ],
      receivedData:"",
      examStudent:{
        student:{}
      },
      studentFace:{
      }
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
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            this.drawer=false
            this.studentFace={}
            done();
          })
          .catch(_ => {
            this.drawer=false
            this.studentFace={}
          });
    },
    load(){
      this.params= {
        pageNum:this.pageNum,
        pageSize:this.pageSize,
        managerId:this.receivedData.id
      }
      request.get('/exam/examStudent/selectPage', {
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
      request.get('/exam/examStudent/selectPage', {
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
    agree(row) {
      if(row.faceSubmit==="验证成功"){
        this.$message.warning("人脸认证已经成功")
      }
      else if(row.faceSubmit==="教师代理"){
        this.$message.warning("人脸认证已经代理完成")
      }
      else{
        this.examStudent=row
        this.findStudentFace()
        this.drawer=true
      }
    },
    findStudentFace(){
      request.get("trustSeal/studentFace/selectByUserId/"+this.examStudent.student.id).then(res=>{
        if (res.code === '200') {  // 表示成功保存
          this.studentFace=res.data
        } else {
          this.$message.error(res.msg)  // 弹出错误的信息
        }
      })
    },
    submit(){
      let params;
      params={
        id:this.examStudent.id,
        facePath:this.studentFile,
        faceSubmit:"教师代理"
      }
      request.put("/exam/examStudent/updateSubmitByTeacher",params).then(res=>{
        if(res.code==="200"){
          this.$message.success("认证成功")
          this.drawer=false
          this.load()
        }
        else {
          this.$message.error(res.msg)
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
.tops{
  margin: 0 3% 0 3%;
  padding: 1vh 2% 1vh 2%;
  height: 40vh;
  width: 94%;
  background: #dce1ec;
  border-radius: 10px;
}
.middles{
  margin: 2vh 3% 0 3%;
  padding: 1vh 2% 1vh 2%;
  height: 34vh;
  width: 94%;
  background: #dce1ec;
  border-radius: 10px;
}
.bottoms{
  margin: 2vh 3% 0 3%;
  padding: 1vh 2% 1vh 2%;
  height: 10vh;
  width: 94%;
  background: #dce1ec;
  border-radius: 10px;
}
.title{
  font-size: 24px;
  text-align: center;
  font-family: 'STXingkai', '华文行楷', cursive;
  margin-bottom: 2vh;
}
.message{
  font-size: 20px;
  margin-bottom: 3vh;
  margin-top: 2vh;
  margin-left: 4%;
}

</style>

