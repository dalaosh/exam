<template>
  <div>
    <el-container>
      <el-header style="height: 10vh;background: #e8ecfa">
        <el-row>
          <el-col :span="6" style="font-size: 16px;text-align: center;margin-top: 4vh">
            <strong>创作者：</strong>{{receivedData.student.name}}
          </el-col>
          <el-col :span="12" style="font-size: 16px;text-align: center;margin-top: 4vh">
            <strong>标题：</strong>{{receivedData.name}}
          </el-col>
          <el-col :span="6" style="font-size: 16px;text-align: center;margin-top: 4vh">
            <strong>发布时间：</strong> {{receivedData.time}}
          </el-col>
        </el-row>
      </el-header>
      <el-container>
        <el-aside width="30%" style="height: 77vh;background: #e6e7e8">
          <div style="text-align: center;font-size: 36px"><strong>
            目录
          </strong></div>
          <div style="margin-left: 4%">
            <ul>
              <!-- 遍历目录数据渲染目录项 -->
              <li v-for="(item, index) in tocData" :key="index">
                <!-- 根据标题级别设置缩进 -->
                <span :style="{ marginLeft: (item.level - 1) * 20 + 'px' }">{{ item.title }}</span>
              </li>
            </ul>
          </div>
        </el-aside>
        <el-main width="70%" style="height: 77vh;">
          <mavon-editor
              v-model="content"
              :boxShadow="false"
              :subfield="false"
              :defaultOpen="'preview'"
              :editable="false"
              :toolbarsFlag="false"
              :navigation="false"
              ref="mavonEditor"
              @change="handleEditorChange"
          />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import { mavonEditor } from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'

import request from "@/utils/request";
export default {
  components: {
    mavonEditor
  },
  data() {
    return {
      receivedData:"",
      content: "",
      tocData: [],
    }
  },
  created() {
    this.receivedData = this.$route.params;
    this.load();
  },
  mounted() {
    // 组件挂载时生成目录
    this.generateToc();
  },
  methods:{
    load(){
      if (Object.keys(this.receivedData).length === 0) {
        // 若为空，跳转到指定路由，这里假设跳转到 '/home'
        this.$router.push("/student/listNote")
      }
      let form;
      form={
        address:this.receivedData.address
      }
      request.post("/md/content",form).then(res => {
        if(res.code==="200"){
          this.content=res.data
        }
      })
    },
    handleEditorChange() {
      // 编辑器内容改变时重新生成目录
      this.generateToc();
    },
    generateToc() {
      const lines = this.content.split('\n');
      const toc = [];
      // 遍历 Markdown 内容的每一行
      lines.forEach(line => {
        const match = line.match(/^(#+) (.*)$/);
        if (match) {
          const level = match[1].length;
          const title = match[2];
          // 将匹配到的标题信息添加到目录数据中
          toc.push({ level, title });
        }
      });
      this.tocData = toc;
    },
  }
}
</script>

<style scoped>

</style>

