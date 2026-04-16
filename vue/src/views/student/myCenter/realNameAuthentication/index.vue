<template>
  <div class="student-page student-realname-page">
    <section class="student-page-head">
      <div>
        <h2 class="student-page-head__title">实名认证</h2>
      </div>
      <div class="student-page-head__meta">
        <el-tag class="student-tag" type="info">{{ setOrShow==='show' ? '已提交信息' : '待补全信息' }}</el-tag>
      </div>
    </section>

    <div class="student-realname-shell">
      <aside class="student-realname-aside">
        <section class="student-panel student-realname-card">
          <h3 class="student-section-title">基本信息</h3>
          <div class="student-realname-profile">
            <el-image class="student-realname-avatar" :src="user.photo" fit="cover"></el-image>
            <div class="student-realname-profile__meta">
              <div class="student-realname-item">姓名：{{ user.name }}</div>
              <div class="student-realname-item">性别：{{ user.sex }}</div>
              <div class="student-realname-item">角色：{{ user.role }}</div>
              <div class="student-realname-item">电话：{{ user.phone }}</div>
              <div class="student-realname-item">邮箱：{{ user.email }}</div>
            </div>
          </div>
        </section>

        <section class="student-panel student-realname-card">
          <h3 class="student-section-title">操作说明</h3>
          <el-steps direction="vertical" :active="4" class="student-realname-guide">
            <el-step title="初始化引擎">
              <div slot="description">
                <p class="student-realname-step">1. 激活 SDK（首次需要网络连接）</p>
                <p class="student-realname-step">2. 初始化视频检测模式</p>
              </div>
            </el-step>
            <el-step title="人脸注册">
              <div slot="description">
                <p class="student-realname-step">1. 获取摄像头权限</p>
                <p class="student-realname-step">2. 执行活体检测</p>
                <p class="student-realname-step">3. 保存特征数据</p>
              </div>
            </el-step>
            <el-step title="实时识别">
              <div slot="description">
                <p class="student-realname-step">1. 启动识别线程</p>
                <p class="student-realname-step">2. 特征比对</p>
                <p class="student-realname-step">3. 结果处理（阈值 0.82）</p>
              </div>
            </el-step>
            <el-step title="异常处理">
              <div slot="description">
                <p class="student-realname-error-title">常见错误码</p>
                <ul class="student-realname-error-list">
                  <li v-for="(desc, code) in errorMapping" :key="code">
                    <span class="student-realname-error-code">{{ code }}</span>
                    <span>{{ desc }}</span>
                  </li>
                </ul>
              </div>
            </el-step>
          </el-steps>
        </section>
      </aside>

      <main class="student-realname-main">
        <section class="student-realname-info-grid">
          <article class="student-panel student-realname-card">
            <h3 class="student-section-title">家庭住址</h3>
            <div class="student-realname-form-grid" v-if="setOrShow !== 'show'">
              <div class="student-realname-field">
                <span class="student-realname-field__label">省份</span>
                <el-select v-model="adds.provinceId" placeholder="请选择省份" @change="findCity()">
                  <el-option v-for="ma in provinceArr" :label="ma.province" :value="ma.id" :key="ma.id"></el-option>
                </el-select>
              </div>
              <div class="student-realname-field">
                <span class="student-realname-field__label">城市</span>
                <el-select v-model="adds.cityId" placeholder="请选择城市" @change="findArea()">
                  <el-option v-for="ma in cityArr" :label="ma.city" :value="ma.id" :key="ma.id"></el-option>
                </el-select>
              </div>
              <div class="student-realname-field">
                <span class="student-realname-field__label">区县</span>
                <el-select v-model="adds.areaId" placeholder="请选择区县">
                  <el-option v-for="ma in areaArr" :label="ma.area" :value="ma.id" :key="ma.id"></el-option>
                </el-select>
              </div>
            </div>
            <div class="student-realname-display" v-else>
              <div class="student-realname-item">省份：{{ studentFace.addressProvinces.province }}</div>
              <div class="student-realname-item">城市：{{ studentFace.addressCities.city }}</div>
              <div class="student-realname-item">区县：{{ studentFace.addressAreas.area }}</div>
            </div>
          </article>

          <article class="student-panel student-realname-card">
            <h3 class="student-section-title">学校信息</h3>
            <div class="student-realname-form-grid" v-if="setOrShow !== 'show'">
              <div class="student-realname-field">
                <span class="student-realname-field__label">学校</span>
                <el-select v-model="schools.universityId" placeholder="请选择学校" @change="findColleges()">
                  <el-option v-for="ma in universityArr" :label="ma.universityName" :value="ma.id" :key="ma.id"></el-option>
                </el-select>
              </div>
              <div class="student-realname-field">
                <span class="student-realname-field__label">学院</span>
                <el-select v-model="schools.collegesId" placeholder="请选择学院" @change="findMajor()">
                  <el-option v-for="ma in collegesArr" :label="ma.collegesName" :value="ma.id" :key="ma.id"></el-option>
                </el-select>
              </div>
              <div class="student-realname-field">
                <span class="student-realname-field__label">专业</span>
                <el-select v-model="schools.majorId" placeholder="请选择专业">
                  <el-option v-for="ma in majorArr" :label="ma.majorName" :value="ma.id" :key="ma.id"></el-option>
                </el-select>
              </div>
            </div>
            <div class="student-realname-display" v-else>
              <div class="student-realname-item">学校：{{ studentFace.schoolsUniversity.universityName }}</div>
              <div class="student-realname-item">学院：{{ studentFace.schoolsColleges.collegesName }}</div>
              <div class="student-realname-item">专业：{{ studentFace.schoolsMajor.majorName }}</div>
            </div>
          </article>
        </section>

        <section class="student-panel student-realname-card">
          <h3 class="student-section-title">{{ setOrShow === 'show' ? '认证资料' : '认证采集' }}</h3>

          <div class="student-realname-media-grid" v-if="setOrShow !== 'show'">
            <article class="student-realname-media-card">
              <div class="student-realname-media-card__head">
                <h4>拍照功能</h4>
                <div class="student-realname-camera-actions">
                  <el-button type="primary" @click="openCamera" :disabled="cameraEnabled">开启摄像</el-button>
                  <el-button type="danger" @click="closeCamera" :disabled="!cameraEnabled">关闭摄像</el-button>
                  <el-button type="success" @click="takePhoto" :disabled="!cameraEnabled">拍照</el-button>
                </div>
              </div>
              <div class="student-realname-media-box">
                <video ref="video" v-show="cameraEnabled" autoplay></video>
                <div v-show="!cameraEnabled" class="student-realname-placeholder">摄像头未开启</div>
              </div>
            </article>

            <article class="student-realname-media-card">
              <div class="student-realname-media-card__head">
                <h4>照片显示</h4>
              </div>
              <div class="student-realname-media-box">
                <el-image :src="capturedPhoto" fit="contain">
                  <div slot="error" class="student-realname-placeholder">等待拍摄照片</div>
                </el-image>
              </div>
            </article>

            <article class="student-realname-media-card">
              <div class="student-realname-media-card__head">
                <h4>身份证上传</h4>
                <el-upload
                  class="upload-demo"
                  action="http://localhost:9998/files/idPhoto/upload"
                  :limit="1"
                  :on-success="handleUpload"
                  :show-file-list="false">
                  <el-button type="primary">点击上传</el-button>
                </el-upload>
              </div>
              <div class="student-realname-media-box">
                <el-image :src="idCardImage" fit="contain">
                  <div slot="error" class="student-realname-placeholder">等待上传身份证</div>
                </el-image>
              </div>
            </article>
          </div>

          <div class="student-realname-media-grid student-realname-media-grid--readonly" v-else>
            <article class="student-realname-media-card">
              <div class="student-realname-media-card__head">
                <h4>验证说明</h4>
              </div>
              <div class="student-realname-status-box">
                <span v-if="studentFace.isSubmit==='否'">正在等待审核</span>
                <span v-if="studentFace.isSubmit==='同意'">审核成功</span>
                <span v-if="studentFace.isSubmit==='拒绝'">审核失败，请重新提交</span>
              </div>
            </article>

            <article class="student-realname-media-card">
              <div class="student-realname-media-card__head">
                <h4>照片显示</h4>
              </div>
              <div class="student-realname-media-box">
                <el-image :src="studentFace.facePath" fit="contain">
                  <div slot="error" class="student-realname-placeholder">暂无人脸照片</div>
                </el-image>
              </div>
            </article>

            <article class="student-realname-media-card">
              <div class="student-realname-media-card__head">
                <h4>身份证照片</h4>
              </div>
              <div class="student-realname-media-box">
                <el-image :src="studentFace.idPath" fit="contain">
                  <div slot="error" class="student-realname-placeholder">暂无身份证照片</div>
                </el-image>
              </div>
            </article>
          </div>

          <div class="student-realname-submit" v-if="setOrShow !== 'show'">
            <div class="student-realname-submit__note">
              {{ setOrShow === 'update' ? '实名认证信息重新填写' : '请进行实名认证' }}
            </div>
            <el-button type="primary" @click="setOrShow === 'update' ? updateMessage() : insertMessage()">提交资料</el-button>
          </div>
        </section>
      </main>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      errorMapping: {
        '0x0001': 'SDK未激活',
        '0x0003': '图像格式错误（需要 NV21）',
        '0x0011': '超出设备授权数量',
        '0x0020': '内存分配失败'
      },
      errorLevelMapping: {
        'critical': 'error',
        'warning': 'warning',
        'info': 'info'
      },
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
    closeCamera() {
      if (this.mediaStream) {
        this.mediaStream.getTracks().forEach(track => track.stop());
        this.mediaStream = null;
      }
      this.cameraEnabled = false;
      if (this.$refs.video) {
        this.$refs.video.srcObject = null;
      }
    },
    takePhoto() {
      const video = this.$refs.video;
      const canvas = document.createElement('canvas');
      canvas.width = video.videoWidth;
      canvas.height = video.videoHeight;
      const ctx = canvas.getContext('2d');
      ctx.drawImage(video, 0, 0, canvas.width, canvas.height);
      const base64Data = canvas.toDataURL('image/jpeg', 0.8)
      const processedBase64 = this.processBase64(base64Data);
      this.studentFile=base64Data
      this.capturedPhoto = base64Data;
      return processedBase64
    },
    processBase64(base64Str) {
      if (!base64Str) return '';
      const parts = base64Str.split(',');
      return parts.length > 1 ? parts[1] : base64Str;
    },
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
  beforeDestroy() {
    this.closeCamera();
  }
}
</script>

<style scoped>
.student-realname-shell {
  display: grid;
  grid-template-columns: 300px minmax(0, 1fr);
  gap: 18px;
  align-items: stretch;
}

.student-realname-aside,
.student-realname-main {
  display: grid;
  gap: 18px;
}

.student-realname-aside {
  grid-template-rows: auto minmax(0, 1fr);
}

.student-realname-main {
  grid-template-rows: auto minmax(0, 1fr);
}

.student-realname-card {
  padding: 20px;
}

.student-realname-aside > .student-realname-card:last-child,
.student-realname-main > .student-realname-card:last-child {
  display: flex;
  flex-direction: column;
  min-height: 100%;
}

.student-realname-profile {
  display: grid;
  grid-template-columns: 180px minmax(0, 1fr);
  gap: 18px;
  align-items: start;
}

.student-realname-avatar {
  width: 100%;
  height: 220px;
  border-radius: 16px;
  overflow: hidden;
  background: #f8fafc;
}

.student-realname-profile__meta,
.student-realname-display {
  display: grid;
  gap: 12px;
}

.student-realname-item {
  color: #475569;
  line-height: 1.7;
  word-break: break-word;
}

.student-realname-guide {
  flex: 1;
  min-height: 0;
  padding-right: 4px;
  max-height: none;
  overflow: auto;
}

.student-realname-step {
  margin: 6px 0;
  color: #64748b;
  line-height: 1.65;
}

.student-realname-error-title {
  margin: 10px 0 8px;
  font-weight: 700;
  color: #0f172a;
}

.student-realname-error-list {
  padding-left: 18px;
  margin: 0;
  color: #64748b;
}

.student-realname-error-list li {
  margin-bottom: 8px;
}

.student-realname-error-code {
  display: inline-block;
  min-width: 62px;
  color: #dc2626;
  font-weight: 700;
}

.student-realname-info-grid,
.student-realname-media-grid {
  display: grid;
  gap: 18px;
}

.student-realname-info-grid {
  grid-template-columns: repeat(2, minmax(0, 1fr));
}

.student-realname-media-grid {
  grid-template-columns: repeat(3, minmax(0, 1fr));
  align-items: stretch;
}

.student-realname-form-grid {
  display: grid;
  gap: 16px;
}

.student-realname-field {
  display: grid;
  gap: 8px;
}

.student-realname-field__label {
  font-size: 13px;
  font-weight: 600;
  color: #475569;
}

.student-realname-media-card {
  display: grid;
  grid-template-rows: auto minmax(0, 1fr);
  gap: 12px;
  height: 100%;
  padding: 16px;
  border: 1px solid rgba(226, 232, 240, 0.9);
  border-radius: 14px;
  background: #f8fafc;
}

.student-realname-media-card__head {
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
  gap: 12px;
}

.student-realname-media-card__head h4 {
  margin: 0;
  font-size: 16px;
  font-weight: 700;
  color: #0f172a;
}

.student-realname-camera-actions {
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-end;
  gap: 8px;
}

.student-realname-media-box {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 240px;
  border: 1px dashed #93c5fd;
  border-radius: 14px;
  background: #fff;
  overflow: hidden;
}

.student-realname-media-box :deep(.el-image),
.student-realname-media-box video {
  width: 100%;
  height: 240px;
}

.student-realname-media-box video {
  object-fit: cover;
  background: #0f172a;
}

.student-realname-placeholder,
.student-realname-status-box {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  min-height: 240px;
  padding: 20px;
  color: #64748b;
  text-align: center;
  line-height: 1.7;
}

.student-realname-submit {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 16px;
  margin-top: auto;
  padding-top: 18px;
  border-top: 1px solid rgba(226, 232, 240, 0.9);
}

.student-realname-submit__note {
  color: #475569;
  font-size: 14px;
}

.student-realname-submit :deep(.el-button) {
  min-width: 180px;
  height: 42px;
}

.student-realname-page :deep(.el-input__inner),
.student-realname-page :deep(.el-select .el-input__inner) {
  height: 38px;
  border-radius: 10px;
}

.student-realname-page :deep(.el-button) {
  border-radius: 10px;
  font-weight: 600;
}

@media (max-width: 1320px) {
  .student-realname-shell,
  .student-realname-info-grid,
  .student-realname-media-grid {
    grid-template-columns: 1fr;
  }

  .student-realname-submit {
    flex-direction: column;
    align-items: stretch;
  }

  .student-realname-submit :deep(.el-button) {
    width: 100%;
  }
}
</style>
