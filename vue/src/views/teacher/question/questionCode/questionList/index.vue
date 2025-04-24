<template>
  <div class="main">
    <!--    搜索框-->
    <div class="search">
      <el-row style="margin-top: 2vh;margin-left: 2%">
        <el-form :inline="true" :model="params" class="demo-form-inline">
          <el-form-item label="题目编号:" style="padding-right: 4%">
            <el-input clearable v-model="params.id" placeholder="题目编号"></el-input>
          </el-form-item>
          <el-form-item label="课程:" style="padding-right: 4%">
            <el-select v-model="params.courseId" clearable filterable placeholder="请选择">
              <el-option
                  v-for="item in course"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="章节:" style="padding-right: 4%">
            <el-input  clearable v-model="params.section" placeholder="章节"></el-input>
          </el-form-item>
          <el-form-item label="难度等级:" style="padding-right: 4%;">
            <el-row>
              <el-rate
                  style="margin-top: 1vh"
                  v-model="params.level"
                  :colors="['#fdea5a', '#32ef17', '#8a2be2']"
                  :void-color="'#070000'"
              ></el-rate>
            </el-row>
          </el-form-item>
          <el-form-item label="分值:" style="padding-right: 4%">
            <el-input clearable  clearable v-model="params.score" placeholder="分值"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="findBySearch">查询</el-button>
          </el-form-item>
        </el-form>
      </el-row>
      <el-row style="margin-top: 2vh;margin-left: 2%">
        <el-button type="primary" @click="selectAll" plain style="margin-right: 4%">全选</el-button>
        <el-button type="primary" @click="DeselectAll" plain style="margin-right: 4%">清空选择</el-button>
        <el-button type="danger" @click="deleteBatch" plain style="margin-right: 4%">批量删除</el-button>
        <el-button type="success" @click="insert" plain style="margin-right: 4%">新增</el-button>
        <el-button type="success" @click="exp()" plain style="margin-right: 4%">批量导出</el-button>
        <el-upload action="http://localhost:9998/exam/questionCode/upload" style="display: inline-block; margin-left: 10px" :show-file-list="false" :on-success="successUpload">
          <el-button type="success" plain>批量导入</el-button>
        </el-upload>
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
        <el-table-column prop="id" label="题目id" width="100" align="center"></el-table-column>
        <el-table-column prop="section" label="章节" width="100" align="center"></el-table-column>
        <el-table-column label="题目" width="400" align="center">
          <template slot-scope="scope">
            <div class="question">
              <div v-html="scope.row.question" class="w-e-text"></div>
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="level" label="难度级别" width="200" align="center">
          <template slot-scope="scope">
            <el-rate
                v-model="scope.row.level"
                disabled
                :colors="['#fdea5a', '#32ef17', '#8a2be2']"
                :void-color="'#e0e0e0'"
            ></el-rate>
          </template>
        </el-table-column>
        <el-table-column prop="score" label="分值" width="100" align="center"></el-table-column>

        <el-table-column prop="course.id" label="课程id" width="100" align="center"></el-table-column>
        <el-table-column label="课程信息" align="center">
          <el-table-column prop="course.photo" label="照片" width="100" align="center">
            <template v-slot="scope">
              <el-image
                  style="width: 40px; height: 40px;border-radius:50%"
                  :src="scope.row.course.photo"
                  :preview-src-list="[scope.row.course.photo]">
              </el-image>
            </template>
          </el-table-column>
          <el-table-column prop="course.name" label="课程名称" width="200" align="center" fixed></el-table-column>
          <el-table-column prop="course.number" label="课程编号" width="150" align="center"></el-table-column>
        </el-table-column>

        <el-table-column prop="teacher.id" label="出题人id" width="100" align="center"></el-table-column>
        <el-table-column label="出题人信息" align="center">
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
        </el-table-column>

        <el-table-column
            fixed="right"
            label="操作"
            width="150"
            align="center"
        >
          <template slot-scope="scope">
            <el-button type="text" @click="showMessage(scope.row.id)" size="small" style="color: #074ab0;">详情</el-button>
            <el-button type="text" @click="updateMessage(scope.row.id)" size="small" style="color: #074ab0;">修改</el-button>
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
      user:localStorage.getItem("user") ? (JSON).parse(localStorage.getItem("user")) : {},
      form:{},
      tableData: [],
      pageNum: 1,   // 当前的页码
      pageSize: 10,  // 每页显示的个数
      total: 0,
      ids:[],
      multipleSelection:"",
      params:{},
      course:{}
    }
  },
  created() {
    this.load()
    this.findCourse()
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
      request.get('/exam/questionCode/selectPage', {
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
    findCourse(){
      let params;
      params= {
        teacherId:this.user.id
      }
      request.get('/exam/course/selectAll', {
        params:params
      }).then(res => {
        if (res.code === '200') {
          this.course=res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    findBySearch(){

      request.get('/exam/questionCode/selectPage', {
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
        request.delete('/exam/questionCode/delete/' + id).then(res => {
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
        request.delete('/exam/questionCode/delete/batch', {data: this.ids}).then(res => {
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
      this.$router.push("/teacher/teacherQuestionCodeCreated")
    },
    updateMessage(id){
      this.$router.push({
        name: "teacherQuestionCodeUpdate",
        params: {
          id:id
        }
      });
    },
    showMessage(id){
      this.$router.push({
        name: "teacherQuestionCodeShow",
        params: {
          id:id
        }
      });
    },
    exp() {
      location.href = 'http://localhost:9998/exam/questionCode/export'
    },
    successUpload(res) {
      if (res.code === '200') {
        this.$message.success("批量导入成功")
        this.findBySearch()
      } else {
        this.$message.error(res.msg)
      }
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
  height: 22vh;
}
.table{
  height: 60vh;
}

.question{
  width: 100%;
  height: 10vh;
  overflow-y: scroll;
}
.question::-webkit-scrollbar{
  width:0;
}
</style>