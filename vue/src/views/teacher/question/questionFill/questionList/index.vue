<template>
  <div class="teacher-page teacher-question-list-page">
    <section class="teacher-page-head">
      <div>
        <h2 class="teacher-page-head__title">填空题列表</h2>
      </div>
    </section>

    <section class="teacher-panel teacher-question-list-page__toolbar">
      <div class="teacher-question-list-page__toolbar-row">
        <el-form :model="params" class="demo-form-inline teacher-question-list-page__filters">
          <el-form-item label="课程:">
            <el-select v-model="params.courseId" clearable filterable placeholder="请选择">
              <el-option
                v-for="item in course"
                :key="item.id"
                :label="item.name"
                :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="章节:">
            <el-input clearable v-model="params.section" placeholder="章节"></el-input>
          </el-form-item>
          <el-form-item label="难度等级:">
            <el-rate
              v-model="params.level"
              :colors="['#fdea5a', '#32ef17', '#8a2be2']"
              :void-color="'#070000'">
            </el-rate>
          </el-form-item>
          <el-form-item class="teacher-question-list-page__submit">
            <el-button type="primary" @click="findBySearch">查询</el-button>
          </el-form-item>
        </el-form>

        <div class="teacher-question-list-page__actions">
          <el-button type="primary" plain @click="selectAll">全选</el-button>
          <el-button type="primary" plain @click="DeselectAll">清空选择</el-button>
          <el-button type="danger" plain @click="deleteBatch">批量删除</el-button>
          <el-button type="success" plain @click="insert">新增</el-button>
          <el-button type="success" plain @click="exp()">批量导出</el-button>
          <el-upload action="http://localhost:9998/exam/questionFill/upload" :show-file-list="false" :on-success="successUpload">
            <el-button type="success" plain>批量导入</el-button>
          </el-upload>
        </div>
      </div>
    </section>

    <section class="teacher-panel teacher-question-list-page__table-card">
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
        :row-key="getRowKeys">
        <el-table-column ref="table" type="selection" width="55" align="center" :reserve-selection="true" fixed></el-table-column>
        <el-table-column prop="id" label="题目ID" width="100" align="center"></el-table-column>
        <el-table-column prop="section" label="章节" width="100" align="center"></el-table-column>
        <el-table-column label="题目" width="400" align="center">
          <template slot-scope="scope">
            <div class="question teacher-question-list-page__question">
              <div v-html="scope.row.question" class="w-e-text"></div>
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="level" label="难度等级" width="200" align="center">
          <template slot-scope="scope">
            <el-rate
              v-model="scope.row.level"
              disabled
              :colors="['#fdea5a', '#32ef17', '#8a2be2']"
              :void-color="'#e0e0e0'">
            </el-rate>
          </template>
        </el-table-column>
        <el-table-column prop="score" label="分值" width="100" align="center"></el-table-column>

        <el-table-column prop="course.id" label="课程ID" width="100" align="center"></el-table-column>
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

        <el-table-column prop="teacher.id" label="出题人ID" width="100" align="center"></el-table-column>
        <el-table-column label="出题人信息" align="center">
          <el-table-column prop="teacher.name" label="姓名" width="100" align="center"></el-table-column>
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

        <el-table-column fixed="right" label="操作" width="150" align="center">
          <template slot-scope="scope">
            <div class="teacher-question-list-page__op">
              <el-button type="text" @click="showMessage(scope.row.id)" size="small" style="color: #074ab0;">详情</el-button>
              <el-button type="text" @click="updateMessage(scope.row.id)" size="small" style="color: #074ab0;">修改</el-button>
              <el-button type="text" @click="deleteById(scope.row.id)" size="small" style="color: red;">删除</el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>

      <div class="teacher-question-list-page__pagination">
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
    </section>
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
      pageNum: 1,
      pageSize: 10,
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
      this.findBySearch()
    },
    handleCurrentChange(pageNum){
      this.params.pageNum=pageNum;
      this.findBySearch()
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
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
      request.get('/exam/questionFill/selectPage', {
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

      request.get('/exam/questionFill/selectPage', {
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
      this.$confirm('您确定删除吗？', '确认删除', { type: 'warning' }).then(response => {
        request.delete('/exam/questionFill/delete/' + id).then(res => {
          if (res.code === '200') {
            this.$message.success('删除成功')
            this.findBySearch()
          } else {
            this.$message.error(res.msg)
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
      this.$confirm('您确定批量删除这些数据吗？', '确认删除', { type: 'warning' }).then(response => {
        request.delete('/exam/questionFill/delete/batch', {data: this.ids}).then(res => {
          if (res.code === '200') {
            this.$message.success('批量删除成功')
            this.findBySearch()
          } else {
            this.$message.error(res.msg)
          }
        })
      }).catch(() => {
      })
    },
    insert() {
      this.$router.push("/teacher/teacherQuestionFillCreated")
    },
    updateMessage(id){
      this.$router.push({
        name: "teacherQuestionFillUpdate",
        params: {
          id:id
        }
      });
    },
    showMessage(id){
      this.$router.push({
        name: "teacherQuestionFillShow",
        params: {
          id:id
        }
      });
    },
    exp() {
      location.href = 'http://localhost:9998/exam/questionFill/export'
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
.teacher-question-list-page__toolbar-row {
  display: flex;
  flex-direction: column;
  gap: 18px;
}

.teacher-question-list-page__filters {
  display: flex;
  flex-wrap: wrap;
  align-items: flex-end;
  gap: 16px 20px;
  margin: 0;
}

.teacher-question-list-page__filters :deep(.el-form-item) {
  display: flex;
  align-items: center;
  margin: 0;
}

.teacher-question-list-page__filters :deep(.el-form-item__content) {
  line-height: normal;
}

.teacher-question-list-page__filters :deep(.el-form-item__label) {
  padding-right: 12px;
  color: #173b6c;
  font-weight: 700;
  line-height: 40px;
}

.teacher-question-list-page__filters :deep(.el-input),
.teacher-question-list-page__filters :deep(.el-select) {
  width: 260px;
}

.teacher-question-list-page__filters :deep(.el-rate) {
  display: inline-flex;
  white-space: nowrap;
}

.teacher-question-list-page__submit {
  margin-left: 4px;
}

.teacher-question-list-page__actions {
  display: flex;
  flex-wrap: wrap;
  gap: 12px;
  padding-top: 18px;
  border-top: 1px solid rgba(199, 214, 236, 0.7);
}

.teacher-question-list-page__actions .el-button {
  margin-left: 0;
}

.teacher-question-list-page__actions :deep(.el-upload) {
  display: inline-flex;
}

@media (max-width: 768px) {
  .teacher-question-list-page__filters {
    flex-direction: column;
    align-items: stretch;
  }

  .teacher-question-list-page__filters :deep(.el-form-item) {
    display: block;
  }

  .teacher-question-list-page__filters :deep(.el-input),
  .teacher-question-list-page__filters :deep(.el-select) {
    width: 100%;
  }

  .teacher-question-list-page__submit {
    margin-left: 0;
  }
}
</style>





