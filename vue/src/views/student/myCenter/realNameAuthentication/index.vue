<template>
  <div>
    <el-row>
      <el-col :span="8">
        <el-row>
          <!-- 教师信息模块（原样保留） -->
          <div class="left-top">
            <div class="title">基本信息</div>
            <el-row :gutter="20">
              <el-col :span="10">
                <el-image
                    style="width: 100%; height: 24vh"
                    :src="user.photo"
                    fit="fit"></el-image>
              </el-col>
              <el-col :span="14">
                <div class="message">姓名: {{user.name}}</div>
                <div class="message">性别: {{user.sex}}</div>
                <div class="message">角色: {{user.role}}</div>
                <div class="message">电话: {{user.phone}}</div>
              </el-col>
            </el-row>
            <div class="message">邮箱: {{user.email}}</div>
          </div>
          <!-- 操作说明模块（新增内容） -->
          <div class="left-bottom">
            <div class="title">操作说明</div>
            <el-steps
                direction="vertical"
                :active="4"
                class="operation-guide">
              <el-step title="初始化引擎">
                <div slot="description">
                  <p class="step-desc">1. 激活SDK（首次需要网络连接）</p>
                  <p class="step-desc">2. 初始化视频检测模式</p>
                </div>
              </el-step>
              <el-step title="人脸注册">
                <div slot="description">
                  <p class="step-desc">1. 获取摄像头权限</p>
                  <p class="step-desc">2. 执行活体检测</p>
                  <p class="step-desc">3. 保存特征数据</p>
                </div>
              </el-step>
              <el-step title="实时识别">
                <div slot="description">
                  <p class="step-desc">1. 启动识别线程</p>
                  <p class="step-desc">2. 特征比对</p>
                  <p class="step-desc">3. 结果处理（阈值0.82）</p>
                </div>
              </el-step>
              <el-step title="异常处理">
                <div slot="description">
                  <p class="error-title">常见错误码:</p>
                  <ul class="error-list">
                    <li v-for="(desc, code) in errorMapping" :key="code">
                      <span class="error-code">{{ code }}</span>: {{ desc }}
                    </li>
                  </ul>
                </div>
              </el-step>
            </el-steps>
          </div>
        </el-row>
      </el-col>
      <el-col :span="16"  v-if="setOrShow==='set'">
        <!-- 右侧内容保持不变 -->
        <el-row>
          <el-col :span="12">
            <div class="right-left-top">
              <div class="title">家庭住址</div>
              <div style="margin-top: 2vh">
                请选择省：
                <!-- 使用内联函数调用 provinceChange 方法并传递 ma -->
                <el-select style="width: 70%" v-model="adds.provinceId" placeholder="请输入省" @change="findCity()">
                  <!-- 遍历 provinceArr 数组生成选项 -->
                  <el-option v-for="ma in provinceArr" :label="ma.province" :value="ma.id" :key="ma.id">
                  </el-option>
                </el-select>
              </div>
              <div style="margin-top: 2vh">
                请选择市：
                <el-select style="width: 70%" v-model="adds.cityId" placeholder="请输入市" @change="findArea()">
                  <el-option v-for="ma in cityArr" :label="ma.city" :value="ma.id" :key="ma.id"></el-option>
                </el-select>
              </div>
              <div style="margin-top: 2vh">
                请选择县：
                <el-select style="width: 70%" v-model="adds.areaId" placeholder="请输入县">
                  <el-option v-for="ma in areaArr" :label="ma.area" :value="ma.id" :key="ma.id"></el-option>
                </el-select>
              </div>
            </div>
          </el-col>
          <el-col :span="12">
            <div class="right-right-top">
              <div class="title">学校信息</div>
              <div style="margin-top: 2vh">
                请选择学校：
                <!-- 使用内联函数调用 provinceChange 方法并传递 ma -->
                <el-select style="width: 60%" v-model="schools.universityId" placeholder="请输入省" @change="findColleges()">
                  <!-- 遍历 provinceArr 数组生成选项 -->
                  <el-option v-for="ma in universityArr" :label="ma.universityName" :value="ma.id" :key="ma.id">
                  </el-option>
                </el-select>
              </div>
              <div style="margin-top: 2vh">
                请选择学院：
                <el-select style="width: 60%" v-model="schools.collegesId" placeholder="请输入市" @change="findMajor()">
                  <el-option v-for="ma in collegesArr" :label="ma.collegesName" :value="ma.id" :key="ma.id"></el-option>
                </el-select>
              </div>
              <div style="margin-top: 2vh">
                请选择专业：
                <el-select style="width: 60%" v-model="schools.majorId" placeholder="请输入县">
                  <el-option v-for="ma in majorArr" :label="ma.majorName" :value="ma.id" :key="ma.id"></el-option>
                </el-select>
              </div>
            </div>
          </el-col>
        </el-row>
        <el-row>
          <div class="right-middle">
            <el-row>
              <el-row :gutter="15">
                <!-- 左侧拍照区域 -->
                <el-col :span="8">
                  <div class="title">拍照功能</div>
                  <div style="margin-bottom: 2vh; text-align: center">
                    <el-button
                        type="primary"
                        @click="openCamera"
                        :disabled="cameraEnabled"
                        style="margin-right: 3px">
                      开启摄像
                    </el-button>
                    <el-button
                        type="danger"
                        @click="closeCamera"
                        :disabled="!cameraEnabled">
                      关闭摄像
                    </el-button>
                    <el-button
                        type="success"
                        @click="takePhoto"
                        :disabled="!cameraEnabled"
                        style="margin-left: 3px">
                      拍 照
                    </el-button>
                  </div>
                  <div style="width: 98%; height: 22vh; border: 1px dashed #0c5bf3;text-align: center">
                    <video
                        ref="video"
                        v-show="cameraEnabled"
                        autoplay
                        style="width: 100%; height: 100%"></video>
                    <div v-show="!cameraEnabled" style="padding-top: 8vh;font-size: 24px">
                      摄像头未开启
                    </div>
                  </div>
                </el-col>

                <!-- 中间照片显示 -->
                <el-col :span="8">
                  <div class="title">照片显示</div>
                  <div style="width: 98%">
                    <el-image
                        style="width: 100%; height: 28vh; border: 1px dashed #0c5bf3;text-align: center"
                        :src="capturedPhoto"
                        fit="contain">
                      <div slot="error" style="padding-top: 8vh;font-size: 24px">
                        等待拍摄照片...
                      </div>
                    </el-image>
                  </div>
                </el-col>

                <!-- 右侧身份证上传 -->
                <el-col :span="8">
                  <div class="title">身份证上传</div>
                  <div style="width: 98%; height: 29vh;;text-align: center">
                    <el-upload
                        class="upload-demo"
                        action="http://localhost:9998/files/idPhoto/upload"
                        :limit="1"
                        :on-success="handleUpload"
                        :show-file-list="false">
                      <el-button type="primary" style="margin-bottom: 20px">
                        点击上传
                      </el-button>
                    </el-upload>
                    <el-image
                        style="width: 100%; height: 22vh; border: 1px dashed #3a44fa"
                        :src="idCardImage"
                        fit="contain">
                      <div slot="error" style="padding-top: 8vh;font-size: 24px">
                        等待上传身份证...
                      </div>
                    </el-image>
                  </div>
                </el-col>
              </el-row>
            </el-row>
          </div>
        </el-row>
        <el-row>
          <div class="right-bottom">
            <el-row>
              <el-col :span="12">
                <div style="color: #3437f8;font-size: 24px;text-align: center">
                  请进行实名认证
                </div>
              </el-col>
              <el-col :span="12">
                <el-button
                    type="primary"
                    @click="insertMessage"
                    style="width: 60%;font-size: 24px">
                  提交资料
                </el-button>
              </el-col>
            </el-row>
          </div>
        </el-row>
      </el-col>
      <el-col :span="16"  v-if="setOrShow==='update'">
        <!-- 右侧内容保持不变 -->
        <el-row>
          <el-col :span="12">
            <div class="right-left-top">
              <div class="title">家庭住址</div>
              <div style="margin-top: 2vh">
                请选择省：
                <!-- 使用内联函数调用 provinceChange 方法并传递 ma -->
                <el-select style="width: 70%" v-model="adds.provinceId" placeholder="请输入省" @change="findCity()">
                  <!-- 遍历 provinceArr 数组生成选项 -->
                  <el-option v-for="ma in provinceArr" :label="ma.province" :value="ma.id" :key="ma.id">
                  </el-option>
                </el-select>
              </div>
              <div style="margin-top: 2vh">
                请选择市：
                <el-select style="width: 70%" v-model="adds.cityId" placeholder="请输入市" @change="findArea()">
                  <el-option v-for="ma in cityArr" :label="ma.city" :value="ma.id" :key="ma.id"></el-option>
                </el-select>
              </div>
              <div style="margin-top: 2vh">
                请选择县：
                <el-select style="width: 70%" v-model="adds.areaId" placeholder="请输入县">
                  <el-option v-for="ma in areaArr" :label="ma.area" :value="ma.id" :key="ma.id"></el-option>
                </el-select>
              </div>
            </div>
          </el-col>
          <el-col :span="12">
            <div class="right-right-top">
              <div class="title">学校信息</div>
              <div style="margin-top: 2vh">
                请选择学校：
                <!-- 使用内联函数调用 provinceChange 方法并传递 ma -->
                <el-select style="width: 60%" v-model="schools.universityId" placeholder="请输入省" @change="findColleges()">
                  <!-- 遍历 provinceArr 数组生成选项 -->
                  <el-option v-for="ma in universityArr" :label="ma.universityName" :value="ma.id" :key="ma.id">
                  </el-option>
                </el-select>
              </div>
              <div style="margin-top: 2vh">
                请选择学院：
                <el-select style="width: 60%" v-model="schools.collegesId" placeholder="请输入市" @change="findMajor()">
                  <el-option v-for="ma in collegesArr" :label="ma.collegesName" :value="ma.id" :key="ma.id"></el-option>
                </el-select>
              </div>
              <div style="margin-top: 2vh">
                请选择专业：
                <el-select style="width: 60%" v-model="schools.majorId" placeholder="请输入县">
                  <el-option v-for="ma in majorArr" :label="ma.majorName" :value="ma.id" :key="ma.id"></el-option>
                </el-select>
              </div>
            </div>
          </el-col>
        </el-row>
        <el-row>
          <div class="right-middle">
            <el-row>
              <el-row :gutter="15">
                <!-- 左侧拍照区域 -->
                <el-col :span="8">
                  <div class="title">拍照功能</div>
                  <div style="margin-bottom: 2vh; text-align: center">
                    <el-button
                        type="primary"
                        @click="openCamera"
                        :disabled="cameraEnabled"
                        style="margin-right: 3px">
                      开启摄像
                    </el-button>
                    <el-button
                        type="danger"
                        @click="closeCamera"
                        :disabled="!cameraEnabled">
                      关闭摄像
                    </el-button>
                    <el-button
                        type="success"
                        @click="takePhoto"
                        :disabled="!cameraEnabled"
                        style="margin-left: 3px">
                      拍 照
                    </el-button>
                  </div>
                  <div style="width: 98%; height: 22vh; border: 1px dashed #0c5bf3;text-align: center">
                    <video
                        ref="video"
                        v-show="cameraEnabled"
                        autoplay
                        style="width: 100%; height: 100%"></video>
                    <div v-show="!cameraEnabled" style="padding-top: 8vh;font-size: 24px">
                      摄像头未开启
                    </div>
                  </div>
                </el-col>

                <!-- 中间照片显示 -->
                <el-col :span="8">
                  <div class="title">照片显示</div>
                  <div style="width: 98%">
                    <el-image
                        style="width: 100%; height: 28vh; border: 1px dashed #0c5bf3;text-align: center"
                        :src="capturedPhoto"
                        fit="contain">
                      <div slot="error" style="padding-top: 8vh;font-size: 24px">
                        等待拍摄照片...
                      </div>
                    </el-image>
                  </div>
                </el-col>

                <!-- 右侧身份证上传 -->
                <el-col :span="8">
                  <div class="title">身份证上传</div>
                  <div style="width: 98%; height: 29vh;;text-align: center">
                    <el-upload
                        class="upload-demo"
                        action="http://localhost:9998/files/idPhoto/upload"
                        :limit="1"
                        :on-success="handleUpload"
                        :show-file-list="false">
                      <el-button type="primary" style="margin-bottom: 20px">
                        点击上传
                      </el-button>
                    </el-upload>
                    <el-image
                        style="width: 100%; height: 22vh; border: 1px dashed #3a44fa"
                        :src="idCardImage"
                        fit="contain">
                      <div slot="error" style="padding-top: 8vh;font-size: 24px">
                        等待上传身份证...
                      </div>
                    </el-image>
                  </div>
                </el-col>
              </el-row>
            </el-row>
          </div>
        </el-row>
        <el-row>
          <div class="right-bottom">
            <el-col :span="12">
              <div style="color: #3437f8;font-size: 24px;text-align: center">
                实名认证信息重新填写
              </div>
            </el-col>
            <el-col :span="12">
              <el-button
                  type="primary"
                  @click="updateMessage"
                  style="width: 60%;font-size: 24px">
                提交资料
              </el-button>
            </el-col>
          </div>
        </el-row>
      </el-col>
      <el-col :span="16" v-if="setOrShow==='show'">
        <!-- 右侧内容保持不变 -->
        <el-row>
          <el-col :span="12">
            <div class="right-left-top">
              <div class="title">家庭住址</div>
              <div style="margin-top: 2vh">
                请选择省：{{studentFace.addressProvinces.province}}
              </div>
              <div style="margin-top: 2vh">
                请选择市：{{studentFace.addressCities.city}}
              </div>
              <div style="margin-top: 2vh">
                请选择县：{{studentFace.addressAreas.area}}
              </div>
            </div>
          </el-col>
          <el-col :span="12">
            <div class="right-right-top">
              <div class="title">学校信息</div>
              <div style="margin-top: 2vh">
                请选择学校：{{studentFace.schoolsUniversity.universityName}}
              </div>
              <div style="margin-top: 2vh">
                请选择学院：{{studentFace.schoolsColleges.collegesName}}
              </div>
              <div style="margin-top: 2vh">
                请选择专业：{{studentFace.schoolsMajor.majorName}}
              </div>
            </div>
          </el-col>
        </el-row>
        <el-row>
          <div class="right-middle">
            <el-row>
              <el-row :gutter="15">
                <!-- 左侧拍照区域 -->
                <el-col :span="8">
                  <div class="title">验证说明</div>
                  <div style="width: 100%; height: 28vh; border: 1px dashed #3a44fa">
                    <div v-if="studentFace.isSubmit==='否'">
                      正在等待审核
                    </div>
                    <div v-if="studentFace.isSubmit==='同意'">
                      审核成功
                    </div>
                    <div v-if="studentFace.isSubmit==='拒绝'">
                      审核失败，重新上传
                    </div>
                  </div>
                </el-col>
                <!-- 中间照片显示 -->
                <el-col :span="8">
                  <div class="title">照片显示</div>
                  <div style="width: 98%">
                    <el-image
                        style="width: 100%; height: 28vh; border: 1px dashed #3a44fa"
                        :src="studentFace.facePath"
                        fit="contain">
                      <div slot="error" style="padding-top: 8vh;font-size: 24px">
                        等待上传身份证...
                      </div>
                    </el-image>
                  </div>
                </el-col>

                <!-- 右侧身份证上传 -->
                <el-col :span="8">
                  <div class="title">身份证上传</div>
                  <div style="width: 98%; height: 29vh;;text-align: center">
                    <el-image
                        style="width: 100%; height: 28vh; border: 1px dashed #3a44fa"
                        :src="studentFace.idPath"
                        fit="contain">
                      <div slot="error" style="padding-top: 8vh;font-size: 24px">
                        等待上传身份证...
                      </div>
                    </el-image>
                  </div>
                </el-col>
              </el-row>
            </el-row>
          </div>
        </el-row>
        <el-row>
          <div class="right-bottom">

          </div>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  data() {
    return {
      // 原有数据保持不变
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},

      // 新增错误处理数据
      errorMapping: {
        '0x0001': 'SDK未激活',
        '0x0003': '图像格式错误（需要NV21）',
        '0x0011': '超出设备授权数量',
        '0x0020': '内存分配失败'
      },
      errorLevelMapping: {
        'critical': 'error',
        'warning': 'warning',
        'info': 'info'
      },
      // 地区
      provinceArr:"",
      province:"",
      cityArr:"",
      city:"",
      areaArr:"",
      area:"",
      adds:{},
      universityArr:"",
      university:"",
      collegesArr:"",
      colleges:"",
      majorArr:"",
      major:"",
      schools:{},
      cameraEnabled: false,
      capturedPhoto: '',
      idCardImage: '',
      mediaStream: null,
      studentFile:"",
      idPath:"",
      setOrShow:"show",
      studentFace:[]
    }
  },
  created(){
    this.load()
    this.findProvince()
    this.findUniversity()
  },
  methods: {
    load(){
      request.get("/trustSeal/studentFace/selectByUserId/"+this.user.id).then(res=>{
        if (res.code === '200') {
          this.studentFace=res.data
          if(this.studentFace!==null){
            if(this.studentFace.length===0){
              this.setOrShow="set"
            }
            else if(this.studentFace.isSubmit==="同意"){
              this.setOrShow="show"
            }
            else{
              this.setOrShow="update"
            }
          }
          else{
            this.setOrShow="set"
          }
          console.log(this.setOrShow)
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
    findUniversity(){
      request.get("/trustSeal/universityCollegeMajor/university/selectAll").then(res=>{
        if (res.code === '200') {
          this.universityArr=res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    findColleges(){
      let city;
      city={
        universityId:this.schools.universityId
      }
      request.get("/trustSeal/universityCollegeMajor/college/selectAll",{
        params:city
      }).then(res=>{
        if (res.code === '200') {
          this.collegesArr=res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    findMajor(){
      let area;
      area={
        collegesId:this.schools.collegesId
      }
      request.get("/trustSeal/universityCollegeMajor/major/selectAll",{
        params:area
      }).then(res=>{
        if (res.code === '200') {
          this.majorArr=res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    // 开启摄像头
    async openCamera() {
      try {
        const stream = await navigator.mediaDevices.getUserMedia({
          video: { width: 640, height: 480 }
        });
        this.mediaStream = stream;
        this.$refs.video.srcObject = stream;
        this.cameraEnabled = true;
      } catch (error) {
        this.$message.error('无法访问摄像头: ' + error.message);
      }
    },

    // 关闭摄像头
    closeCamera() {
      if (this.mediaStream) {
        this.mediaStream.getTracks().forEach(track => track.stop());
        this.mediaStream = null;
      }
      this.cameraEnabled = false;
      this.$refs.video.srcObject = null;
    },

    // 拍照
    // takePhoto() {
    //   const video = this.$refs.video;
    //   const canvas = document.createElement('canvas');
    //   canvas.width = video.videoWidth;
    //   canvas.height = video.videoHeight;
    //   canvas.getContext('2d').drawImage(video, 0, 0);
    //   this.capturedPhoto = canvas.toDataURL('image/png');
    // },
    takePhoto() {
      const video = this.$refs.video;
      const canvas = document.createElement('canvas');
      canvas.width = video.videoWidth;
      canvas.height = video.videoHeight;
      const ctx = canvas.getContext('2d');

      // 绘制视频帧到canvas
      ctx.drawImage(video, 0, 0, canvas.width, canvas.height);

      // 转换为JPEG格式（更适合照片）
      const base64Data = canvas.toDataURL('image/jpeg', 0.8) // 0.8为质量参数

      // 处理后端需要的格式（去掉data:image/jpeg;base64,前缀）
      const processedBase64 = this.processBase64(base64Data);

      // 存储到变量（根据接口需求）
      this.studentFile=base64Data

      // 同时保留预览图
      this.capturedPhoto = base64Data;
    },

// Base64处理方法（与Java后端base64Process方法对应）
    processBase64(base64Str) {
      if (!base64Str) return '';
      // 分割逗号后的部分
      const parts = base64Str.split(',');
      return parts.length > 1 ? parts[1] : base64Str;
    },

    // 处理身份证上传
    handleUpload(res) {
      this.imageUrl=res.data
      this.idCardImage = this.imageUrl;
      this.$message.success('身份证上传成功');
    },
    insertMessage(){
      let params;
      params={
        file:this.studentFile,
        studentId:this.user.id,
        provinceId:this.adds.provinceId,
        cityId:this.adds.cityId,
        areaId:this.adds.areaId,
        universityId: this.schools.universityId,
        collegesId:this.schools.collegesId,
        majorId:this.schools.majorId,
        isSubmit:"否",
        idPath:this.idCardImage,
        id:0
      }
      request.post("/trustSeal/studentFace/faceAdd",params).then(res=>{
        if(res.code==="200"){
          this.$message.success("成功")
          this.load()
        }
        else {
          this.$message.error(res.msg)
        }
      })
    },
    updateMessage(){
      let params;
      params={
        file:this.studentFile,
        studentId:this.user.id,
        provinceId:this.adds.provinceId,
        cityId:this.adds.cityId,
        areaId:this.adds.areaId,
        universityId: this.schools.universityId,
        collegesId:this.schools.collegesId,
        majorId:this.schools.majorId,
        isSubmit:"否",
        idPath:this.idCardImage,
        id:this.studentFace.id
      }
      request.post("/trustSeal/studentFace/faceAdd",params).then(res=>{
        if(res.code==="200"){
          this.$message.success("成功")
          this.load()
        }
        else {
          this.$message.error(res.msg)
        }
      })
    }
  },

  // 组件销毁时关闭摄像头
  beforeDestroy() {
    this.closeCamera();
  }
}
</script>

<style scoped>
/* 原有样式保持不变 */
.left-top{
  margin: 2vh 3% 1vh 3%;
  padding-left: 2%;
  padding-top: 1vh;
  height: 41vh;
  width: 94%;
  background: #dddee0;
  border-radius: 10px;
}

.left-bottom{
  margin: 2vh 3% 1vh 3%;
  padding-left: 2%;
  padding-top: 1vh;
  height: 41vh;
  width: 94%;
  background: #dddee0;
  border-radius: 10px;
}
.right-left-top{
  margin: 2vh 3% 1vh 3%;
  padding-left: 2%;
  padding-top: 1vh;
  height: 30vh;
  width: 94%;
  background: #dddee0;
  border-radius: 10px;
}
.right-right-top{
  margin: 2vh 3% 1vh 3%;
  padding-left: 2%;
  padding-top: 1vh;
  height: 30vh;
  width: 94%;
  background: #dddee0;
  border-radius: 10px;
}
.right-middle{
  margin: 1vh 1.5% 1vh 1.5%;
  padding-left: 2%;
  padding-top: 1vh;
  height: 40vh;
  width: 97%;
  background: #dddee0;
  border-radius: 10px;
}
.right-bottom{
  margin: 1vh 1.5% 1vh 1.5%;
  padding-left: 2%;
  padding-top: 2vh;
  height: 10vh;
  width: 97%;
  text-align: center;
  background: #dddee0;
  border-radius: 10px;
}
/* 新增操作说明样式 */
.operation-guide {
  padding: 0 15px;
  max-height: 30vh;
  overflow-y: scroll;
}
.operation-guide::-webkit-scrollbar{
  width:0;
}

.step-desc {
  font-size: 14px;
  color: #606266;
  margin: 8px 0;
}

.code-block {
  background: #f5f7fa;
  padding: 12px;
  border-radius: 4px;
  font-family: 'Consolas', monospace;
  font-size: 13px;
  line-height: 1.5;
  margin: 10px 0;
}

.error-title {
  font-weight: bold;
  margin: 15px 0 10px;
}

.error-list {
  padding-left: 20px;
}

.error-code {
  display: inline-block;
  width: 60px;
  color: #f56c6c;
  font-weight: bold;
}

.message{
  font-size: 20px;
  margin-bottom: 2vh;
  margin-top: 2vh;
  margin-left: 4%;
}
.title{
  font-family: 'STXingkai', '华文行楷', cursive;
  font-size: 32px;
  padding-bottom: 3vh;
}
</style>
