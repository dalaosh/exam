<template>
  <div>
    <el-row>
      <div style="height: 20vh;width: 90%;margin: 2vh 5% 2vh 5%;background: rgba(221,233,246,0.64);border-radius: 10px;">
        <el-row>
          <el-col :span="10">
            <div style="font-size: 20px;text-align: center;margin-top: 3vh">
              输入课程编码进行查询
            </div>
            <div style="margin-top: 3vh">
              <el-input v-model="number" style="margin-left: 13%;width: 50%" placeholder="课程编号"></el-input>
              <el-button @click="searchNumber()" type="primary"  plain style="margin-left: 4%;width: 20%">查询</el-button>
            </div>
          </el-col>
          <el-col v-if="total===1" :span="14" style="padding-left: 10%">
            <el-row>
              <el-col :span="16">
                <div class="msg">{{table.name}}</div>
              </el-col>
              <el-col :span="8">
                <div>
                  <el-button type="text" @click="visits()" size="small" style="color: #0932d7;font-size: 20px">查看详情</el-button>
                </div>
              </el-col>
            </el-row>
            <el-row>
              <div class="msg">开始时间:{{table.beginTime}}</div>
            </el-row>
            <el-row>
              <el-col :span="16">
                <div class="msg">结束时间{{table.endTime}}</div>
              </el-col>
              <el-col :span="8">
                <el-button v-if="this.but===0" type="text" @click="insert()" size="small" style="color: #0932d7;font-size: 20px">申请加入</el-button>
              </el-col>
            </el-row>
          </el-col>
          <el-col v-if="total===-1" :span="14" style="padding-left: 10%">
            <div style="font-family: '华文行楷', cursive;font-weight: bold;font-size: 4vh;text-align: center;margin-top: 6vh;color: #0b42e8">
              请在左边输入课程编码进行查询
            </div>
          </el-col>
          <el-col v-if="total===0" :span="14" style="padding-left: 10%">
            <div style="font-family: '华文行楷', cursive;font-weight: bold;font-size: 4vh;text-align: center;margin-top: 8vh;color: #0b42e8">
              未找到该课程
            </div>
          </el-col>
        </el-row>
      </div>
    </el-row>
    <hr>
    <!-- 使用 el-row 组件定义行布局，设置列之间的间距为 20 -->
    <el-row v-if="visit!==0" class="cards">
      <!-- 使用 v-for 指令遍历 jsonData 数组，为每个元素渲染一个 el-col 组件 -->
      <el-col :span="8" style="margin-bottom: 20px" v-for="(item, index) in tableData" :key="index">
        <el-card class="card" :body-style="{ padding: '0px' }">
          <img :src="item.course.photo" class="image">
          <div style="padding: 14px;">
            <el-row>
              <el-col :span="6">
                课程信息
              </el-col>
              <el-col :span="9">
                <div style="text-align: right">任课教师：{{item.teacher.name}}</div>
              </el-col>
              <el-col :span="9">
                <div style="text-align: right">是否审核：{{item.isAdd}}</div>
              </el-col>
            </el-row>
            <div class="message">
              <el-row>
                <el-col :span="8">
                  <div class="msg">{{item.course.name}}</div>
                </el-col>
                <el-col :span="8">
                  <div>
                    <el-button type="text" @click="show(item.id)" size="small" style="color: #0932d7;font-size: 20px">查看详情</el-button>
                  </div>
                </el-col>
              </el-row>
              <el-row>
                <div class="msg">开始时间:{{item.course.beginTime}}</div>
              </el-row>
              <el-row>
                <div class="msg">结束时间{{item.course.endTime}}</div>
              </el-row>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-row style="font-family: '华文行楷', cursive;font-weight: bold;font-size: 6vh;text-align: center;margin-top: 20vh;color: #0b42e8" v-if="visit===0">
      你还没有申请课程，请尽快申请
    </el-row>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  data() {
    return {
      user:localStorage.getItem("user") ? (JSON).parse(localStorage.getItem("user")) : {},
      tableData:[],
      visit:0,
      total:-1,
      table:{},
      number:"",
      but:0
    };
  },
  created() {
    this.load()
  },
  methods: {
    load(){
      let params;
      params= {
        studentId: this.user.id
      }
      request.get('/exam/courseStudent/selectAll', {
        params:params
      }).then(res => {
        if (res.code === '200') {
          this.tableData=res.data
          this.visit=this.tableData.length
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    show(id){
      this.$router.push({
        name: "studentCourseStudent",
        params: {
          id:id
        }
      });
    },
    visits(){
      this.$router.push({
        name: "studentCourseReply",
        params: {
          id:this.table.id
        }
      });
    },
    searchNumber(){
      request.get("exam/course/selectByNumber/"+this.number).then(res=>{
        if(res.code==="200"){
          this.total=0;
          if(Object.keys(res.data).length !== 0){
            this.table=res.data;
            this.total=1
            this.searchS()
          }
        }else {
          this.$message.error(res.msg)
        }
      })
    },
    insert(){
      let params;
      params={
        studentId:this.user.id,
        courseId:this.table.id
      }
      request.post("/exam/courseStudent/add",params).then(res => {
        if (res.code === '200') {
          this.$message.success("申请成功")
          this.total=-1
          this.table={}
          this.load()
          this.number=""
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    searchS(){
      let params;
      params={
        studentId:this.user.id,
        courseId:this.table.id
      }
      request.get('/exam/courseStudent/selectAll', {
        params:params
      }).then(res => {
        if (res.code === '200') {
          this.but=res.data.length
        } else {
          this.$message.error(res.msg)
        }
      })
    },
  }
};
</script>

<style scoped>
.image {
  width: 100%;
  height: 30vh;
}
.card{
  margin: 2vh 2% 0 2%;
  border-radius: 10px;
}
.message{
  margin: 1vh 1% 0 1%;
  width: 98%;
  height: 15vh;
}
.msg{
  margin-top: 1vh;
  font-size: 20px;
  margin-left: 4%;
}
.cards{
  overflow-y: scroll;
  height: 61vh;
}
.cards::-webkit-scrollbar{
  width:0;
}
</style>
