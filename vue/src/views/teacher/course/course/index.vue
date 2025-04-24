<template>
  <div class="main">
    <!--    搜索框-->
    <div class="search">
      <el-row style="margin-top: 2vh;margin-left: 2%">
        <el-form :inline="true" :model="params" class="demo-form-inline">
          <el-form-item label="课程名称:" style="padding-right: 4%">
            <el-input v-model="params.name" placeholder="课程名称"></el-input>
          </el-form-item>
          <el-form-item label="课程编号:" style="padding-right: 4%">
            <el-input v-model="params.number" placeholder="课程编号"></el-input>
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
        <el-table-column ref="table" type="selection" width="55" align="center" :reserve-selection="true" fixed>
        </el-table-column>
        <el-table-column prop="id" label="id" width="100" align="center" fixed></el-table-column>
        <el-table-column prop="name" label="课程名称" width="200" align="center" fixed></el-table-column>
        <el-table-column prop="photo" label="照片" width="100" align="center">
          <template v-slot="scope">
            <el-image
                style="width: 40px; height: 40px;border-radius:50%"
                :src="scope.row.photo"
                :preview-src-list="[scope.row.photo]">
            </el-image>
          </template>
        </el-table-column>
        <el-table-column prop="number" label="课程编号" width="200" align="center"></el-table-column>
        <el-table-column prop="beginTime" label="开始时间" width="200" align="center"></el-table-column>
        <el-table-column prop="endTime" label="结束时间" width="200" align="center"></el-table-column>
        <el-table-column prop="message" label="课程信息" width="200" align="center">
          <template slot-scope="scope">
            <el-button type="text" @click="viewEditor(scope.row.message)" style="color: #0b42e8;">点击查看</el-button>
          </template>
        </el-table-column>
        <el-table-column prop="teacher.name" label="创建者" width="200" align="center"></el-table-column>
        <el-table-column
            fixed="right"
            label="操作"
            width="200"
            align="center">
          <template slot-scope="scope">
            <el-button type="text" @click="courseUpdate(scope.row)" size="small" style="color: #009dff;">修改</el-button>
            <el-button type="text" @click="courseStudents(scope.row)" size="small" style="color: #009dff;">查看学生</el-button>
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
import E from 'wangeditor'
import courseStudent from "@/views/teacher/course/courseStudent/index.vue";

let editor
function initWangEditor(content){ setTimeout(() => {
  if(!editor){
    editor=new E('#editor')
    editor.config.placeholder='请输入内容'
    editor.config.uploadFileName='file'
    editor.config.uploadImgServer='http://localhost:9998/files/wang/upload'
    editor.create()
  }
  editor.txt.html(content)
},0)
}
export default {
  computed: {
    courseStudent() {
      return courseStudent
    }
  },
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
      receivedData:""
    }
  },
  created() {
    this.receivedData = this.$route.params;
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
        teacherId:this.user.id
      }
      request.get('/exam/course/selectPage', {
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
      request.get('/exam/course/selectPage', {
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
        request.delete('/exam/course/delete/' + id).then(res => {
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
        request.delete('/exam/course/delete/batch', {data: this.ids}).then(res => {
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
    courseStudents(row){
      this.$router.push({
        name: "teacherCourseStudent",
        params: {
          id:row.id
        }
      });
    },
    courseUpdate(row){
      this.$router.push({
        name: "teacherCourseUpdate",
        params: {
          id:row.id
        }
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

