<template>
  <!-- 原有的模板代码保持不变 -->
  <div>
    <div style="height: 10vh; width: 100%;">
      <el-row style="text-align: center">
        <el-col :span="8" style="font-size: 32px;margin: 3vh 0 3vh 0">
          已经分配数：{{examTeacherGradingNum}}
        </el-col>
        <el-col :span="8" style="font-size: 32px;margin: 3vh 0 3vh 0">
          题目总数：{{questionNum}}
        </el-col>
        <el-col :span="4" style="font-size: 32px;margin: 3vh 0 3vh 0">
          <el-button type="primary" @click="examTeacherGradingAdd" plain style="width: 70%;font-size: 28px">分配题目</el-button>
        </el-col>
        <el-col :span="4" style="font-size: 32px;margin: 3vh 0 3vh 0">
          <el-button type="primary" @click="examTeacherGradingDelete" plain style="width: 70%;font-size: 28px">全部删除</el-button>
        </el-col>
      </el-row>
    </div>
    <div>
      <div class="title">
        选择题
      </div>
      <div>
        <el-card shadow="hover" class="cards"  v-for="(data, index) in questionMulti" :key="index" >
          <div style="text-align: right;color: #bd0a4f;font-size: 20px">
            {{data.questionMulti.score}}分
          </div>
          <el-row>
            <el-col :span="2" >
              {{index+1}}、
            </el-col>
            <el-col :span="22">
              <div v-html="data.questionMulti.question" class="w-e-text divs"></div>
            </el-col>
          </el-row>
        </el-card>
      </div>
    </div>
    <div>
      <div class="title">
        判断题
      </div>
      <div class="divs">
        <el-card shadow="hover" class="cards"  v-for="(data, index) in questionJudge" :key="index" >
          <div style="text-align: right;color: #bd0a4f;font-size: 20px">
            {{data.questionJudge.score}}分
          </div>
          <el-row>
            <el-col :span="2" >
              {{index+1}}、
            </el-col>
            <el-col :span="22">
              <div v-html="data.questionJudge.question" class="w-e-text divs"></div>
            </el-col>
          </el-row>
        </el-card>
      </div>
    </div>
    <div>
      <div class="title">
        填空题
      </div>
      <div class="divs">
        <el-card shadow="hover" class="cards"  v-for="(data, index) in questionFill" :key="index" >
          <div style="text-align: right;color: #bd0a4f;font-size: 20px">
            {{data.questionFill.score}}分
          </div>
          <el-row>
            <el-col :span="2" >
              {{index+1}}、
            </el-col>
            <el-col :span="22">
              <div v-html="data.questionFill.question" class="w-e-text divs"></div>
            </el-col>
          </el-row>
        </el-card>
      </div>
    </div>
    <div>
      <div class="title">
        简答题
      </div>
      <div class="divs">
        <el-card shadow="hover" class="cards"  v-for="(data, index) in questionShortAns" :key="index" >
          <div style="text-align: right;color: #bd0a4f;font-size: 20px">
            {{data.questionShortAns.score}}分
          </div>
          <el-row>
            <el-col :span="2" >
              {{index+1}}、
            </el-col>
            <el-col :span="22">
              <div v-html="data.questionShortAns.question" class="w-e-text divs"></div>
            </el-col>
          </el-row>
        </el-card>
      </div>
    </div>
    <div>
      <div class="title">
        代码题
      </div>
      <div class="divs">
        <el-card shadow="hover" class="cards"  v-for="(data, index) in questionCode" :key="index" >
          <div style="text-align: right;color: #bd0a4f;font-size: 20px">
            {{data.questionCode.score}}分
          </div>
          <el-row>
            <el-col :span="2" >
              {{index+1}}、
            </el-col>
            <el-col :span="22">
              <div v-html="data.questionCode.question" class="w-e-text divs"></div>
            </el-col>
          </el-row>
        </el-card>
      </div>
    </div>
    <!--    侧面抽屉-->
    <el-drawer
        title="教师列表"
        :visible.sync="drawer"
        custom-class="demo-drawer"
        direction="rtl"
        size="60%"
        :before-close="handleClose">
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
                <el-button  clearable type="primary" @click="findTeacherAll">查询</el-button>
              </el-form-item>
            </el-form>
          </el-row>
          <el-row style="margin-top: 2vh;margin-left: 2%;margin-bottom: 2vh">
<!--            <el-button type="primary" @click="selectAll" plain style="margin-right: 4%">全选</el-button>-->
<!--            <el-button type="primary" @click="DeselectAll" plain style="margin-right: 4%">清空选择</el-button>-->
            <el-row>
              <el-col :span="4">
                <div style="text-align: center;font-size: 24px">
                  考试期限:
                </div>
              </el-col>
              <el-col :span="12">
                <div class="block">
                  <el-date-picker
                      v-model="dataTime"
                      @change="times"
                      type="datetimerange"
                      :picker-options="pickerOptions"
                      range-separator="至"
                      start-placeholder="开始日期"
                      end-placeholder="结束日期"
                      align="right">
                  </el-date-picker>
                </div>
              </el-col>
              <el-col :span="8">
                <el-button type="success" @click="addPlay()" plain style="margin-right: 4%;width: 60%;">分配题目</el-button>
              </el-col>
            </el-row>
          </el-row>
        </div>
        <!--    表单-->
        <div class="table">
          <el-table
              ref="table"
              :data="teacherList"
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
            <el-table-column label="考试公钥" width="250" align="center">
              <template slot-scope="scope">
                <el-input
                    style="font-size: 16px"
                    type="textarea"
                    :readonly="true"
                    :autosize="{ minRows: 2, maxRows: 2}"
                    placeholder=""
                    v-model="scope.row.publicKey">
                </el-input>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  data() {
    return {
      user: localStorage.getItem("user") ? (JSON).parse(localStorage.getItem("user")) : {},
      form: {},
      receivedData: "",
      questionCode: [],
      questionFill: [],
      questionMulti: [],
      questionShortAns: [],
      questionJudge: [],
      examTeacherGrading: [],
      examTeacherGradingNum: 0,
      questionNum: 0,
      drawer: false,
      teacherList: [],
      ids: [],
      params: {},
      dataTime:[],
      pickerOptions: {
        shortcuts: [
          {
            text: '未来十二小时',
            onClick(picker) {
              const start = new Date();
              const end = new Date();
              end.setTime(end.getTime() + 3600 * 1000 * 12);
              picker.$emit('pick', [start, end]);
            }
          },
          {
            text: '未来一天',
            onClick(picker) {
              const start = new Date();
              const end = new Date();
              end.setTime(end.getTime() + 3600 * 1000 * 24);
              picker.$emit('pick', [start, end]);
            }
          },
          {
            text: '未来三天',
            onClick(picker) {
              const start = new Date();
              const end = new Date();
              end.setTime(end.getTime() + 3600 * 1000 * 24 * 3);
              picker.$emit('pick', [start, end]);
            }
          },
          {
            text: '未来一周',
            onClick(picker) {
              const start = new Date();
              const end = new Date();
              end.setTime(end.getTime() + 3600 * 1000 * 24 * 7);
              picker.$emit('pick', [start, end]);
            }
          },
          {
            text: '未来一个月',
            onClick(picker) {
              const start = new Date();
              const end = new Date();
              // 简单假设一个月 30 天
              end.setTime(end.getTime() + 3600 * 1000 * 24 * 30);
              picker.$emit('pick', [start, end]);
            }
          },
          {
            text: '未来三个月',
            onClick(picker) {
              const start = new Date();
              const end = new Date();
              // 简单假设三个月 90 天
              end.setTime(end.getTime() + 3600 * 1000 * 24 * 90);
              picker.$emit('pick', [start, end]);
            }
          }
        ]
      },
      dataTimes:{}
    };
  },
  created() {
    this.receivedData = this.$route.params;
    this.findQuestion();
  },
  mounted() {},
  methods: {
    times(){
      this.dataTimes.beginTime=this.time(this.dataTime[0])
      this.dataTimes.endTime=this.time(this.dataTime[1])
    },
    time(dataTime){
      // 假设 this.form.violationTime 是日期字符串或者时间戳
      const date = new Date(dataTime);
// 提取年、月、日、时、分、秒信息
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, '0'); // 月份从 0 开始，所以要加 1
      const day = String(date.getDate()).padStart(2, '0');
      const hours = String(date.getHours()).padStart(2, '0');
      const minutes = String(date.getMinutes()).padStart(2, '0');
      const seconds = String(date.getSeconds()).padStart(2, '0');
// 按照 YYYY-MM-DD HH:mm:ss 格式拼接字符串
      return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
    },
    //id为examManager的id
    findQuestion() {
      this.findQuestionFill();
      this.findQuestionCode();
      this.findQuestionJudge();
      this.findQuestionMulti();
      this.findQuestionShortAns();
      this.findExamTeacherGrading();
    },
    findQuestionCode() {
      let params;
      params = {
        examId: this.receivedData.id,
        questionType: "code"
      };
      request.get('/exam/examPaper/selectAll', {
        params: params
      }).then(res => {
        if (res.code === '200') {
          this.questionCode = res.data;
          this.questionNum = this.questionCode.length + this.questionNum;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findQuestionFill() {
      let params;
      params = {
        examId: this.receivedData.id,
        questionType: "fill"
      };
      request.get('/exam/examPaper/selectAll', {
        params: params
      }).then(res => {
        if (res.code === '200') {
          this.questionFill = res.data;
          this.questionNum = this.questionFill.length + this.questionNum;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findQuestionJudge() {
      let params;
      params = {
        examId: this.receivedData.id,
        questionType: "judge"
      };
      request.get('/exam/examPaper/selectAll', {
        params: params
      }).then(res => {
        if (res.code === '200') {
          this.questionJudge = res.data;
          this.questionNum = this.questionJudge.length + this.questionNum;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findQuestionMulti() {
      let params;
      params = {
        examId: this.receivedData.id,
        questionType: "multi"
      };
      request.get('/exam/examPaper/selectAll', {
        params: params
      }).then(res => {
        if (res.code === '200') {
          this.questionMulti = res.data;
          this.questionNum = this.questionMulti.length + this.questionNum;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findQuestionShortAns() {
      let params;
      params = {
        examId: this.receivedData.id,
        questionType: "shortAns"
      };
      request.get('/exam/examPaper/selectAll', {
        params: params
      }).then(res => {
        if (res.code === '200') {
          this.questionShortAns = res.data;
          this.questionNum = this.questionShortAns.length + this.questionNum;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findExamTeacherGrading() {
      let params;
      params = {
        examId: this.receivedData.id,
        studentId: this.user.id
      };
      request.get('/exam/examTeacherGrading/selectAll', {
        params: params
      }).then(res => {
        if (res.code === '200') {
          this.examTeacherGrading = res.data;
          this.examTeacherGradingNum = this.examTeacherGrading.length;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    //先查询试卷 5个题库
    //查询分配表，通过examId进行查询
    //如果分配表里有数据，则无法全部分配//首先进行删除操作，再次进行分配，如果没数据。则可以直接分配

    handleSelectionChange(val) {
      this.multipleSelection = val;
      // 获取选中数据的id
      this.ids = val.map(item => item.id);
    },
    getRowKeys(row) {
      return row.id;
    },
    findTeacher() {
      request.get('/user/teacher/selectAll').then(res => {
        if (res.code === '200') {
          this.teacherList = res.data;
          // 自动选择指定行
          this.selectInitialRows();
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findTeacherAll() {
      request.get('/user/teacher/selectAll', {
        params: this.params
      }).then(res => {
        if (res.code === '200') {
          this.teacherList = res.data;
          // 自动选择指定行
          this.selectInitialRows();
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    selectAll() {
      this.$refs.table.toggleAllSelection();
    },
    DeselectAll() {
      this.$refs.table.clearSelection();
      this.ids = [];
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
    },
    examTeacherGradingAdd() {
      if (this.examTeacherGradingNum!== 0 && this.examTeacherGradingNum!== this.questionNum) {
        this.$message.error("部分题目已经分配，请删除后重新分配");
      } else if (this.examTeacherGradingNum!== 0 && this.examTeacherGradingNum === this.questionNum) {
        this.$message.warning("题目已经全部分配，无需再次分配");
      } else {
        this.ids = [this.user.id];
        this.findTeacher();
        this.drawer = true;
      }
    },
    addPlay() {
      let params;
      params={
        beginTime:this.dataTimes.beginTime,
        endTime:this.dataTimes.endTime,
        ids:this.ids
      }
      request.post("exam/examTeacherGrading/addPlay/" + this.receivedData.id, params).then(res => {
        if (res.code === '200') {
          this.findExamTeacherGrading();
          this.drawer = false;
          this.$message.success("分配成功")
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    examTeacherGradingDelete() {
      if (this.examTeacherGradingNum === 0) {
        this.$message.error("暂无分配，无需删除");
      } else if (this.examTeacherGradingNum === this.questionNum) {
        this.$message.warning("题目已经全部分配，无需删除");
      } else {
        this.$confirm('您确定删除吗？', '确认删除', { type: "warning" }).then(response => {
          request.delete('/exam/examTeacherGrading/deleteByExamId/' + this.receivedData.id).then(res => {
            if (res.code === '200') {   // 表示操作成功
              this.$message.success('删除成功');
              this.findExamTeacherGrading();
            } else {
              this.$message.error(res.msg);  // 弹出错误的信息
            }
          });
        }).catch(() => {});
      }
    },
    selectInitialRows() {
      // 遍历 teacherList 中的每一行
      this.teacherList.forEach(row => {
        // 检查当前行的 id 是否在 ids 数组中
        if (this.ids.includes(row.id)) {
          // 如果存在，则选中该行
          this.$refs.table.toggleRowSelection(row, true);
        }
      });
    }
  }
};
</script>

<style scoped>
.cards {
  white-space: unset;
  display: inline-block;
  width: 48%;
  margin: 3vh 1% 0 1%;
  height: 30vh;
  border-radius: 10px;
  background-color: #f1fdfd;
  overflow-y: scroll;
}

.cards::-webkit-scrollbar {
  width: 0;
}

.title {
  font-family: 'STXingkai', '华文行楷', cursive;
  font-size: 32px;
  margin: 1vh 1% 0 1%;
}

.divs {
  font-size: 24px;
}
</style>