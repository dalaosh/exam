<template>
  <div>
    <el-card shadow="never" style="height: 40vh;background-color: rgb(244,248,247);color: #030000;border-radius:10px;margin: 1vh 20px 1vh 20px">
      <div style="margin-bottom: 2vh">个人密钥</div>
      <div>
        <el-row :gutter="20">
          <el-col :span="14">
            <el-card class="box-card" style="margin-bottom: 3vh">
              <div>
                公钥
              </div>
              <div>
                <el-input
                    style="font-size: 16px;width: 96%;margin: 2vh 2%"
                    type="textarea"
                    :readonly="true"
                    :autosize="{ minRows: 5, maxRows: 5}"
                    placeholder=""
                    v-model="key.selfPub">
                </el-input>
              </div>
            </el-card>
          </el-col>
          <el-col :span="10">
            <el-card class="box-card">
              <div>
                私钥
              </div>
              <div>
                <el-input
                    style="font-size: 16px;width: 90%;margin: 2vh 2%"
                    type="textarea"
                    :readonly="true"
                    :autosize="{ minRows: 5, maxRows: 5}"
                    placeholder=""
                    v-model="key.selfPri">
                </el-input>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>
    </el-card>
    <el-card shadow="never" style="height: 40vh;background-color: rgb(244,248,247);color: #030000;border-radius:10px;margin: 1vh 20px 1vh 20px">
      <div style="margin-bottom: 2vh">考试密钥</div>
      <div>
        <el-row :gutter="20">
          <el-col :span="14">
            <el-card class="box-card" style="margin-bottom: 3vh">
              <div>
                公钥
              </div>
              <div>
                <el-input
                    style="font-size: 16px;width: 96%;margin: 2vh 2%"
                    type="textarea"
                    :readonly="true"
                    :autosize="{ minRows: 5, maxRows: 5}"
                    placeholder=""
                    v-model="key.examPub">
                </el-input>
              </div>
            </el-card>
          </el-col>
          <el-col :span="10">
            <el-card class="box-card">
              <div>
                私钥
              </div>
              <div>
                <el-input
                    style="font-size: 16px;width: 90%;margin: 2vh 2%"
                    type="textarea"
                    :readonly="true"
                    :autosize="{ minRows: 5, maxRows: 5}"
                    placeholder=""
                    v-model="key.examPri">
                </el-input>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>
    </el-card>
  </div>
</template>

<script>
import request from "@/utils/request"
export default {
  data() {
    return{
      user:localStorage.getItem("user") ? (JSON).parse(localStorage.getItem("user")) : {},
      myInform:{
        photo:"https://tse4-mm.cn.bing.net/th/id/OIP-C.QoRCEN8clpncbfUjX7WJaQAAAA?w=178&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7"
      },
      form:{
      },
      key:{
      }
    }
  },
  created() {
    this.load()
  },
  mounted() {

  },
  methods: {
    load(){
      this.form.account=this.user.account
      this.form.id=this.user.id
      request.post("/user/teacher/getKey",this.form).then(res=>{
        if(res.code==="200"){
          this.key=res.data
        }
      })
    }
  }
}
</script>

<style scoped>

</style>