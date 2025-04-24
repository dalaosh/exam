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
        <el-button :disabled="user.id!==1" type="danger" @click="deleteBatch" plain style="margin-right: 4%">批量删除</el-button>
        <el-button :disabled="user.id!==1" type="warning" @click="updatePdBatch" plain style="margin-right: 4%">批量重置密码</el-button>
        <el-button :disabled="user.id!==1"  type="warning" @click="updateKeyBatch" plain style="margin-right: 4%">批量重置密钥</el-button>
        <el-button :disabled="user.id!==1"  type="success" @click="insert" plain style="margin-right: 4%">新增</el-button>
        <el-button type="success" @click="exp()" plain style="margin-right: 4%">批量导出</el-button>
        <el-upload action="http://localhost:9998/user/caInstitution/upload" style="display: inline-block; margin-left: 10px" :show-file-list="false" :on-success="successUpload">
          <el-button :disabled="user.id!==1"  type="success" plain>批量导入</el-button>
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
        <el-table-column prop="id" label="机构成员id" width="100" align="center"  fixed></el-table-column>
        <el-table-column  prop="name" label="姓名" width="100" align="center"  fixed></el-table-column>
        <el-table-column prop="photo" label="照片" width="100" align="center">
          <template v-slot="scope">
            <el-image
                style="width: 40px; height: 40px;border-radius:50%"
                :src="scope.row.photo"
                :preview-src-list="[scope.row.photo]">
            </el-image>
          </template>
        </el-table-column>
        <el-table-column prop="role" label="角色" width="100" align="center"></el-table-column>
        <el-table-column prop="sex" label="性别" width="100" align="center"></el-table-column>
        <el-table-column prop="email" label="电子邮箱" width="200" align="center"></el-table-column>
        <el-table-column prop="phone" label="电话" width="200" align="center"></el-table-column>
        <el-table-column prop="account" label="账号" width="200" align="center"></el-table-column>
        <el-table-column label="密码" width="250" align="center">
          <template slot-scope="scope">
            <el-input
                style="font-size: 16px"
                type="textarea"
                :readonly="true"
                :autosize="{ minRows: 2, maxRows: 2}"
                placeholder=""
                v-model="scope.row.password">
            </el-input>
          </template>
        </el-table-column>
        <el-table-column label="密码摘要" width="250" align="center">
          <template slot-scope="scope">
            <el-input
                style="font-size: 16px"
                type="textarea"
                :readonly="true"
                :autosize="{ minRows: 2, maxRows: 2}"
                placeholder=""
                v-model="scope.row.keySm3">
            </el-input>
          </template>
        </el-table-column>
        <el-table-column
            fixed="right"
            label="操作"
            width="200"
            align="center"
        >
          <template slot-scope="scope">
            <el-button :disabled="user.id!==1" type="text" @click="updatePd(scope.row.id)" size="small" style="color: #074ab0;">重置密码</el-button>
            <el-button :disabled="user.id!==1" type="text" @click="updateKey(scope.row.id)" size="small" style="color: #074ab0;">重置密钥</el-button>
            <el-button :disabled="user.id!==1" type="text" @click="deleteById(scope.row.id)" size="small" style="color: red;">删除</el-button>
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

    <!--    添加弹窗面板-->
    <div>
      <el-dialog title="请填写信息" :visible.sync="dialogFormVisible" width="40%">
        <el-form :model="form" :rules="rules" ref="formRef">
          <el-form-item label="姓名" label-width="20%" prop="name">
            <el-input v-model="form.name" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="性别" label-width="20%" prop="sex">
            <el-input v-model="form.sex" autocomplete="off" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="电子邮件" label-width="20%" prop="email">
            <el-input v-model="form.email" autocomplete="off" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="电话" label-width="20%" prop="phone">
            <el-input v-model="form.phone" autocomplete="off" style="width: 80%"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="submit()">确 定</el-button>
        </div>
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
      rules: {
        name: [
          {required: true, message: '请输入姓名', trigger: 'blur'},
        ],
        sex: [
          {required: true, message: '请输入性别', trigger: 'blur'},
        ],
        email: [
          {required: true, message: '请输入电子邮箱', trigger: 'blur'},
        ],
        phone: [
          {required: true, message: '请输入电话', trigger: 'blur'},
        ]
      },
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
      request.get('/user/caInstitution/selectPage', {
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
      request.get('/user/caInstitution/selectPage', {
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
        request.delete('/user/caInstitution/delete/' + id).then(res => {
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
    updatePd(id){
      this.$confirm('您确定重置该密码吗？', '确认重置', {type: "warning"}).then(response => {
        request.put('/user/caInstitution/update/password/' + id).then(res => {
          if (res.code === '200') {   // 表示操作成功
            this.$message.success('重置成功')
            this.findBySearch()
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      }).catch(() => {
      })
    },
    updateKey(id){
      this.$confirm('您确定重置该密钥吗？', '确认重置', {type: "warning"}).then(response => {
        request.put('/user/caInstitution/update/key/' + id).then(res => {
          if (res.code === '200') {   // 表示操作成功
            this.$message.success('重置成功')
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
        request.delete('/user/caInstitution/delete/batch', {data: this.ids}).then(res => {
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
    updatePdBatch(){
      if (!this.ids.length) {
        this.$message.warning('请选择数据')
        return
      }
      this.$confirm('您确定批量重置密码吗？', '确认重置', {type: "warning"}).then(response => {
        request.put('/user/caInstitution/update/password/batch', this.ids).then(res => {
          if (res.code === '200') {   // 表示操作成功
            this.$message.success('批量修改成功')
            this.findBySearch()
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      }).catch(() => {
      })
    },
    updateKeyBatch(){
      if (!this.ids.length) {
        this.$message.warning('请选择数据')
        return
      }
      this.$confirm('您确定批量重置密钥吗？', '确认重置', {type: "warning"}).then(response => {
        request.put('/user/caInstitution/update/key/batch', this.ids).then(res => {
          if (res.code === '200') {   // 表示操作成功
            this.$message.success('批量修改成功')
            this.findBySearch()
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      }).catch(() => {
      })
    },
    submit() {
      this.form.username=this.form.id? '' : this.user.name
      this.form.photo="https://tse4-mm.cn.bing.net/th/id/OIP-C.QoRCEN8clpncbfUjX7WJaQAAAA?w=178&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7"

      request({
        url: this.form.id ? '/user/caInstitution/update' : '/user/caInstitution/add',
        method: this.form.id ? 'PUT' : 'POST',
        data: this.form
      }).then(res => {
        if (res.code === '200') {  // 表示成功保存
          this.$message.success('保存成功')
          this.findBySearch()
          this.dialogFormVisible = false
        } else {
          this.$message.error(res.msg)  // 弹出错误的信息
        }
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
    exp() {
      location.href = 'http://localhost:9998/user/caInstitution/export'
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


</style>