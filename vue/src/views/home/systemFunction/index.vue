<template>
  <div class="backgrounds">
    <!-- 系统优势标题 -->
    <div style="text-align: center;line-height: 10vh; background-color:rgba(232,232,229,0.5)">
      <strong  style="font-size: 30px; margin-left: 5px; color: #193624">系统优势</strong>
    </div>
    <!-- 系统优势及子系统介绍区域 -->
    <div style=" background-color:rgba(87,103,101,0.3);height: 82vh">
      <!-- 系统整体介绍 -->
      <el-row>
        <el-col :span="24">
          <div style="margin-left:2%;
                    margin-top:2%;
                    height: 15vh;
                    width: 96%;
                    border-radius: 10px;
                    background-color:rgba(164,189,186,0.6);
                    padding:5px;
">
            <h2>基于国密算法与人脸认证的考试系统是一款融合了先进加密技术与生物识别技术的综合性考试管理系统，
              旨在提升考试安全性、优化考试流程、改进考试信息管理。我们的系统具有以下特点：
            </h2>
          </div>
        </el-col>
      </el-row>
      <!-- 各子系统介绍 -->
      <el-row>
        <!-- 国密算法加密子系统 -->
        <el-col :span="5">
          <div style="margin-left:8%;
                    margin-top:2%;
                    height: 41vh;
                    width: 88%;
                    border-radius: 10px;
                    background-color:rgba(164,189,186,0.6); padding:10px;line-height: 35px">
            <h1 style="text-align: center">
              国密算法加密
            </h1>
            <hr>
            <h3>
              该子系统运用国密算法对考试相关数据进行加密处理，
              确保数据在传输和存储过程中的安全性和完整性。同时，采用环签名技术进一步增强数据的隐私性和不可抵赖性。
            </h3>
          </div>
        </el-col>
        <!-- 虹软人脸识别子系统 -->
        <el-col :span="5">
          <div style="margin-left:4%;
                    margin-top:2%;
                    height: 41vh;
                    width: 92%;
                    border-radius: 10px;
                    background: rgba(164,189,186,0.6); padding:10px;line-height: 35px">
            <h1 style="text-align: center">
              虹软人脸识别
            </h1>
            <hr>
            <h3>
              借助虹软人脸识别技术，在考试前进行考生身份验证，确保考生身份的真实性。在考试过程中，实时监控考生面部表情和动作，
              及时发现异常行为，保障考试的公平公正。
            </h3>
          </div>
        </el-col>
        <!-- C 语言代码编程运行子系统 -->
        <el-col :span="4">
          <div style="margin-left:4%;
                    margin-top:2%;
                    height: 41vh;
                    width: 92%;
                    border-radius: 10px;
                    background: rgba(164,189,186,0.6); padding:10px;line-height: 35px">
            <h1 style="text-align: center">
              C程序编码器
            </h1>
            <hr>
            <h3>
              该子系统提供 C 语言代码编辑和运行环境，支持考生在考试中进行编程答题。系统会对考生提交的代码进行编译、运行。
            </h3>
          </div>
        </el-col>
        <!-- 大屏可视化子系统 -->
        <el-col :span="5">
          <div style="margin-left:4%;
                    margin-top:2%;
                    height: 41vh;
                    width: 92%;
                    border-radius: 10px;
                    background: rgba(164,189,186,0.6); padding:10px;line-height: 35px">
            <h1 style="text-align: center">
              大屏可视化
            </h1>
            <hr>
            <h3>
              通过可视化界面展示考试实时数据，如考生答题进度、考试成绩统计、异常行为监控等，为考试管理人员提供直观的管理决策参考。
            </h3>
          </div>
        </el-col>
        <!-- Markdown 文档管理子系统 -->
        <el-col :span="5">
          <div style="margin-right:8%;
                    margin-left:4%;
                    margin-top:2%;
                    height: 41vh;
                    width: 88%;
                    border-radius: 10px;
                    background: rgba(164,189,186,0.6); padding:10px;line-height: 35px">
            <h1 style="text-align: center">
              Markdown 文档管理
            </h1>
            <hr>
            <h3>
              该子系统支持对考试相关的 Markdown 文档（如考试说明、试题解析等）进行管理，方便管理人员进行文档的创建、编辑、存储和共享。
            </h3>
          </div>
        </el-col>
      </el-row>
      <!-- 系统总结 -->
      <el-row>
        <el-col :span="24">
          <div style="margin-left:2%;
                    margin-top:8px;
                    height: 15vh;
                    width: 96%;
                    border-radius: 10px;
                    background: rgba(164,189,186,0.6); padding:5px">
            <h2>基于国密算法与人脸认证的考试系统将通过综合整合多种先进技术手段，为考试管理者和考生提供更加安全、高效、便捷的服务体验，
              推动考试管理的数字化转型升级，助力教育考试事业的发展。
            </h2>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
  methods: {}
};
</script>

<style scoped>
.backgrounds{
  height: 92vh;
  overflow: hidden;
  background-image: url("@/assets/home/function/a.jpg");
  background-size: 100%;
  align-items: center;
  justify-content: center;
  /* 修正 filter 语法 */
  filter:'blur(10px)';
}
</style>