<template>
  <div class="main">
    <!--    搜索框-->
    <div class="search">
      <el-row style="margin-top: 2vh;margin-left: 2%">
        <el-form :inline="true" :model="params" class="demo-form-inline">
          <el-form-item label="详细地址:" style="padding-right: 4%">
            <el-row :gutter="20">
              <el-col :span="8">
                <el-select clearable v-model="params.provinceId" placeholder="请输入省" @change="findSelectCity()">
                  <el-option v-for="ma in selectProvinceArr" :label="ma.province" :value="ma.id" :key="ma.id">
                  </el-option>
                </el-select>
              </el-col>
              <el-col :span="8">
                <el-select clearable style="width: 85%" v-model="params.cityId" placeholder="请输入市" @change="findSelectArea()">
                  <el-option v-for="ma in selectCityArr" :label="ma.city" :value="ma.id" :key="ma.id"></el-option>
                </el-select>
              </el-col>
              <el-col :span="8">
                <el-select clearable style="width: 85%" v-model="params.areaId" placeholder="请输入区" @change="setSelectAddress()">
                  <el-option v-for="ma in selectAreaArr" :label="ma.area" :value="ma.id" :key="ma.id"></el-option>
                </el-select>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item label="学校名称:" style="padding-right: 4%">
            <el-input v-model="params.universityName" placeholder="学校名称"></el-input>
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
        <el-table-column ref="table" type="selection" width="55" align="center" :reserve-selection="true">
        </el-table-column>
        <el-table-column prop="id" label="id" width="50" align="center"></el-table-column>
        <el-table-column prop="universityName" label="学校名称" width="200" align="center"></el-table-column>
        <el-table-column label="地址信息" align="center">
          <el-table-column prop="addressProvinces.provinceId" label="省编号" width="100" align="center"></el-table-column>
          <el-table-column prop="addressProvinces.province" label="省" width="100" align="center"></el-table-column>
          <el-table-column prop="addressCities.cityId" label="市编号" width="100" align="center"></el-table-column>
          <el-table-column prop="addressCities.city" label="市" width="100" align="center"></el-table-column>
          <el-table-column prop="addressAreas.areaId" label="县编号" width="100" align="center"></el-table-column>
          <el-table-column prop="addressAreas.area" label="县" width="100" align="center"></el-table-column>
          <el-table-column prop="address" label="详细地址" width="200" align="center"></el-table-column>
        </el-table-column>
        <el-table-column
            fixed="right"
            label="操作"
            width="100"
            align="center">
          <template slot-scope="scope">
            <el-button type="text" @click="update(scope.row)" size="small" style="color: #074ab0;">修改</el-button>
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

    <!--    添加弹窗面板-->
    <div>
      <el-dialog title="请填写信息" :visible.sync="dialogFormVisible" width="40%">
        <el-form :model="form">
          <el-form-item label="学校名称" label-width="15%">
            <el-input v-model="form.universityName" autocomplete="off" style="width: 85%"></el-input>
          </el-form-item>
          <el-form-item label="请选择省" label-width="15%">
            <!-- 使用内联函数调用 provinceChange 方法并传递 ma -->
            <el-select style="width: 85%" v-model="adds.provinceId" placeholder="请输入省" @change="findCity()">
              <!-- 遍历 provinceArr 数组生成选项 -->
              <el-option v-for="ma in provinceArr" :label="ma.province" :value="ma.id" :key="ma.id">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="请选择市" label-width="15%">
            <el-select style="width: 85%" v-model="adds.cityId" placeholder="请输入市" @change="findArea()">
              <el-option v-for="ma in cityArr" :label="ma.city" :value="ma.id" :key="ma.id"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="请选择县" label-width="15%">
            <el-select style="width: 85%" v-model="adds.areaId" placeholder="请输入区" @change="setAddress()">
              <el-option v-for="ma in areaArr" :label="ma.area" :value="ma.id" :key="ma.id"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="详细地址" label-width="15%">
            <el-input
            style="font-size: 16px;width: 85%"
            type="textarea"
            :autosize="{ minRows: 2, maxRows: 2}"
            placeholder=""
            v-model="form.address">
            </el-input>
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
      selectProvinceArr:"",
      selectProvince:"",
      selectCityArr:"",
      selectCity:"",
      selectAreaArr:"",
      selectArea:"",
      provinceArr:"",
      province:"",
      cityArr:"",
      city:"",
      areaArr:"",
      area:"",
      adds:{}
    }
  },
  created() {
    this.load()
    this.findSelectProvince()
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
      request.get('/trustSeal/universityCollegeMajor/university/selectPage', {
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
      request.get('/trustSeal/universityCollegeMajor/university/selectPage', {
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
    findProvince(){
      request.get("/trustSeal/address/provinces").then(res=>{
        if (res.code === '200') {
          this.provinceArr=res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    findSelectProvince(){
      request.get("/trustSeal/address/provinces").then(res=>{
        if (res.code === '200') {
          this.selectProvinceArr=res.data
        } else {
          this.$message.error(res.msg)
        }

      })
    },
    findCity(){
      this.province = this.provinceArr.find(item => item.id === this.adds.provinceId);
      let city;
      city={
        provinceId:this.province.provinceId
      }
      request.get("/trustSeal/address/cities",{
        params:city
      }).then(res=>{
        if (res.code === '200') {
          this.cityArr=res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    findSelectCity(){
      this.selectProvince = this.selectProvinceArr.find(item => item.id === this.params.provinceId);
      let city;
      city={
        provinceId:this.selectProvince.provinceId
      }
      request.get("/trustSeal/address/cities",{
        params:city
      }).then(res=>{
        if (res.code === '200') {
          this.selectCityArr=res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    findArea(){
      this.city= this.cityArr.find(item => item.id === this.adds.cityId);
      let area;
      area={
        cityId:this.city.cityId
      }
      request.get("/trustSeal/address/areas",{
        params:area
      }).then(res=>{
        if (res.code === '200') {
          this.areaArr=res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    findSelectArea(){
      this.selectCity= this.selectCityArr.find(item => item.id === this.params.cityId);
      let area;
      area={
        cityId:this.selectCity.cityId
      }
      request.get("/trustSeal/address/areas",{
        params:area
      }).then(res=>{
        if (res.code === '200') {
          this.selectAreaArr=res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    setAddress(){
      this.area= this.areaArr.find(item => item.id === this.adds.areaId);
      this.form.provinceId=this.adds.provinceId
      this.form.cityId=this.adds.cityId
      this.form.areaId=this.adds.areaId
    },
    setSelectAddress(){

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
        request.delete('/trustSeal/universityCollegeMajor/university/delete/' + id).then(res => {
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
        request.delete('/trustSeal/universityCollegeMajor/university/delete/batch', {data: this.ids}).then(res => {
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
    submit() {
      request({
        url: this.form.id ? '/trustSeal/universityCollegeMajor/university/update' : '/trustSeal/universityCollegeMajor/university/add',
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
      this.findProvince()
      this.form = {};
      this.adds={}
      this.dialogFormVisible = true;
    },
    update(row) {
      this.findProvince()

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
  height: 16vh;
}
.table{
  height: 66vh;
}


</style>

