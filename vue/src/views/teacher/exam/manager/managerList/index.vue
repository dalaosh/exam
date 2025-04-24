<template>
  <div class="main">
    <!--    搜索框-->
    <div class="search">
      <el-row style="margin-top: 2vh;margin-left: 2%">
        <el-form :inline="true" :model="params" class="demo-form-inline">
          <el-form-item label="课程名称:" style="padding-right: 4%">
            <el-select v-model="params.courseId" style="font-size: 20px;width: 300px" clearable filterable placeholder="请选择">
              <el-option
                  v-for="item in course"
                  :key="item.id"
                  style="font-size: 20px; width: 100%;"
                  :label="item.name+'==='+item.number"
                  :value="item.id"
              >
                <span style="float: left;margin-left: 6px; color: #8492a6; font-size: 18px">课程:{{ item.name }}</span>
                <span style="float: left;margin-left: 6px; color: #8492a6; font-size: 18px">编号:{{ item.number }}</span>
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="考试类型:" style="padding-right: 4%">
            <el-input v-model="params.type" placeholder="考试类型"></el-input>
          </el-form-item>
          <el-form-item label="总分:" style="padding-right: 4%">
            <el-input v-model="params.totalScore" placeholder="总分"></el-input>
          </el-form-item>
          <el-form-item label="总时长:" style="padding-right: 4%">
            <el-input v-model="params.totalTime" placeholder="总时长"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button  clearable type="primary" @click="findBySearch">查询</el-button>
          </el-form-item>
        </el-form>
      </el-row>
      <el-row style="margin-top: 1vh;margin-left: 2%">
        <el-button type="primary" @click="selectAll" plain style="margin-right: 4%">全选</el-button>
        <el-button type="warning" @click="DeselectAll" plain style="margin-right: 4%">清空选择</el-button>
        <el-button type="danger" @click="deleteBatch" plain style="margin-right: 4%">批量删除</el-button>
      </el-row>
    </div>
    <!--    表单-->
    <div v-if="this.visit!==-1&&this.visit!==0" class="table">
      <el-table
          ref="table"
          :data="tableData"
          tooltip-effect="dark"
          border
          show-overflow-tooltip
          stripe
          style="width: 100%"
          height="58vh"
          @selection-change="handleSelectionChange"
          :row-key="getRowKeys"
      >
        <el-table-column ref="table" type="selection" width="55" align="center" :reserve-selection="true" fixed>
        </el-table-column>
        <el-table-column prop="id" label="id" width="100" align="center" fixed></el-table-column>
        <el-table-column prop="course.name" label="课程名称" width="200" align="center"></el-table-column>
        <el-table-column prop="examDate" label="考试日期" width="200" align="center"></el-table-column>
        <el-table-column prop="totalTime" label="持续时长" width="100" align="center"></el-table-column>
        <el-table-column prop="totalScore" label="总分" width="100" align="center"></el-table-column>
        <el-table-column prop="type" label="考试类型" width="200" align="center"></el-table-column>
        <el-table-column prop="description" label="考试介绍" width="200" align="center">
          <template slot-scope="scope">
            <el-button type="text" @click="viewEditor(scope.row.description)" style="color: #0b42e8;">点击查看</el-button>
          </template>
        </el-table-column>
        <el-table-column prop="tips" label="考生须知" width="200" align="center">
          <template slot-scope="scope">
            <el-button type="text" @click="viewEditor(scope.row.tips)" style="color: #0b42e8;">点击查看</el-button>
          </template>
        </el-table-column>
        <el-table-column prop="teacher.name" label="创建者" width="200" align="center"></el-table-column>
        <el-table-column
            fixed="right"
            label="操作"
            width="370"
            align="center">
          <template slot-scope="scope">
            <el-button type="text" @click="showPublicKeys(scope.row.id)" size="small" style="color: #009dff;">查看环公钥</el-button>
            <el-button type="text" @click="showManager(scope.row)" size="small" style="color: #44b70c;">题目创建</el-button>
            <el-button type="text" @click="updateById(scope.row.id)" size="small" style="color: #009dff;">修改</el-button>
            <el-button type="text" @click="submit(scope.row)" size="small" style="color: #09b5f3;">发布</el-button>
            <el-button type="text" @click="realFace(scope.row)" size="small" style="color: #09b5f3;">手动认证</el-button>
            <el-button type="text" @click="distributeById(scope.row)" size="small" style="color: #09b5f3;">教师分配</el-button>
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
    <div style="background: #f4f4fd;font-family: '华文行楷', cursive;font-weight: bold;font-size: 6vh;text-align: center;padding-top: 30vh;height:62vh;color: #0b42e8" v-if="visit===-1">
      请先选择课程进行考试的查询
    </div>
    <div style="background: #f4f4fd;font-family: '华文行楷', cursive;font-weight: bold;font-size: 6vh;text-align: center;padding-top: 30vh;height:62vh;color: #05fd09" v-if="visit===0">
      该课程还未创建试卷
    </div>
    <!--    富文本显示-->
    <div>
      <el-dialog title="信息详情" :visible.sync="editorVisible" width="50%">
        <div v-html="this.viewData" class="w-e-text"></div>
      </el-dialog>
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
      course:[],
      ids:"",
      multipleSelection:"",
      params:{
        pageNum:1,
        pageSize:10,
      },
      editorVisible:false,//富文本显示
      viewData:"",//富文本数据
      dialogFormVisible:false,//表单显示
      receivedData:"",
      visit:-1
    }
  },
  created() {
    this.receivedData = this.$route.params;
    this.load()
    this.findCourse()
  },
  mounted() {

  },
  methods: {
    load(){
      this.params.teacherId=this.user.id
      request.get('/exam/examManage/selectPage', {
        params:this.params
      }).then(res => {
        if (res.code === '200') {
          this.total=res.data.total
          this.tableData=res.data.list
          this.visit=this.total
        } else {
          this.$message.error(res.msg)
        }
      })
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
    findCourse(){
      let params;
      params={
        teacherId:this.user.id
      }
      request.get("exam/course/selectAll",{
        params:params
      }).then(res=>{
        if (res.code === '200') {
          this.course=res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    findBySearch(){
      request.get('/exam/examManage/selectPage', {
        params:this.params
      }).then(res => {
        if (res.code === '200') {
          this.total=res.data.total
          this.tableData=res.data.list
          this.visit=this.total
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
        request.delete('/exam/examManage/delete/' + id).then(res => {
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
        request.delete('/exam/examManage/delete/batch', {data: this.ids}).then(res => {
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
    viewEditor(data){
      this.editorVisible=true;
      this.viewData=data
    },
    showManager(row){
      if(this.isExamEnded(row)){
        this.$message.warning("考试已经结束")
      }
      else if(this.isExamStarted(row)){
        this.$message.warning("考试正在进行")
      }
      else{
        this.$router.push({
          name: "teacherShowManager",
          params: {
            id:row.id,
            courseId:row.course.id
          }
        });
      }
    },
    showPublicKeys(id){
      this.$router.push({
        name: "teacherShowPublicKeys",
        params: {
          id:id
        }
      });
    },
    updateById(id){
      this.$router.push({
        name: "teacherUpdateManager",
        params: {
          id:id
        }
      });
    },
    submit(row){
      if(row.isSubmit==="是"){
        this.$message.warning("试卷已经发布")
      }
      else{
        this.$confirm('您确定公布试卷吗？', '确认公布', {type: "warning"}).then(response => {
          request.get("exam/examStudent/addStudentBatch/"+row.id).then(res=>{
            if(res.code==="200"){
              this.$message.success("成功")
              this.load()
            }
            else{
              this.$message.warning(res.data)
            }
          })
        }).catch(() => {
        })
      }
    },
    distributeById(data){
      if(this.isExamEnded(data)){
        if(data.beginTime!==''&& data.beginTime!==null){
          if(this.beginBefore(data)){
            this.$router.push({
              name: "teacherDistributePaper",
              params: {
                id: data.id
              }
            });
          }
          else if(this.endAfter(data)){
            this.$message.warning("批卷已经结束")
          }else{
            this.$message.warning("批卷正在进行中")
          }
        }
        else{
          this.$router.push({
            name: "teacherDistributePaper",
            params: {
              id: data.id
            }
          });
        }
      }
      else if(this.isExamStarted(data)){
        this.$message.warning("考试正在进行")
      }
      else {
        this.$message.warning("考试还未开始")
      }
    },
    realFace(row){
      this.$router.push({
        name: "teacherRealFace",
        params: {
          id: row.id
        }
      });
    },
    // 判断在开始时间之前
    beginBefore(row) {
      const beginTime = new Date(row.beginTime);
      const now = new Date();
      return now <= beginTime;
    },
    // 判断在结束时间之后
    endAfter(row){
      const endTime = new Date(row.endTime);
      const now = new Date();
      return now >= endTime;
    },

    // 判断考试是否开始
    isExamStarted(row) {
      const examDate = new Date(row.examDate);
      const now = new Date();
      return now >= examDate;
    },
    // 判断考试是否结束
    isExamEnded(row) {
      const examDate = new Date(row.examDate);
      const totalTime = row.totalTime * 60 * 1000; // 将总时长转换为毫秒
      const endDate = new Date(examDate.getTime() + totalTime);
      const now = new Date();
      return now >= endDate;
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
  height: 20vh;
}
.table{
  height: 60vh;
}


</style>

