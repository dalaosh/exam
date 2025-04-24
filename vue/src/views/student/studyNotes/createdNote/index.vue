<template>
  <div class="mains">
    <div style="height:10vh;padding-top: 3vh;padding-left: 2%;background: #f4f5f6">
      <el-row>
        <el-col :span="8">
          <div style="font-size: 16px;text-align: center">
            <strong>创作者：</strong>{{user.name}}
          </div>
        </el-col>
        <el-col :span="8">
          <div style="text-align: center">
            <el-input v-model="name" placeholder="标题"></el-input>
          </div>
        </el-col>
        <el-col :span="8">
          <div style="text-align: center">
            <el-button type="primary" @click="upload" plain style="text-align: center">更改上传</el-button>
          </div>
        </el-col>
      </el-row>
    </div>
    <div class="mavon">
      <mavon-editor
          toolbarsBackground="#b9c7d7"
          previewBackground="#e9e9ea"
          :ishljs="true"
          ref=md
          :toolbars="toolbars"
          v-model="content"
          :fontSize="fontSize"
          @save="save"
          style="height: 77vh"
      >
        <template v-slot:left-toolbar-after>
          <!--点击按钮触发的事件是打开表单对话框-->
          <el-button type="text" title="插入图片资源" @click="dialogFormVisible=true">图片</el-button>
        </template>
      </mavon-editor>
    </div>
    <div>
      <el-dialog title="请填写信息" :visible.sync="dialogFormVisible" width="60%">
        <div>
          <el-upload
              action='http://localhost:9998/files/md/upload'
              :on-success="successUpload"
          >
            <el-button size="small" type="primary">点击上传</el-button>
          </el-upload>
        </div>
        <div>
          {{pos}}
        </div>
      </el-dialog>

    </div>
  </div>
</template>
<script>
import { mavonEditor } from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import request from "@/utils/request";
export default {
  name: 'MavonEditor',
  data() {
    return {
      fontSize: "24px",
      pos: "",
      content: "",
      receivedData:"",
      name:"",
      dialogFormVisible: false,
      user: localStorage.getItem("user") ? (JSON).parse(localStorage.getItem("user")) : {},
      toolbars: {
        bold: true, // 粗体
        italic: true, // 斜体
        header: true, // 标题
        underline: true, // 下划线
        strikethrough: true, // 中划线
        mark: true, // 标记
        superscript: true, // 上角标
        subscript: true, // 下角标
        quote: true, // 引用
        ol: true, // 有序列表
        ul: true, // 无序列表
        link: true, // 链接
        imagelink: false, // 图片链接
        code: true, // code
        table: true, // 表格
        fullscreen: true, // 全屏编辑
        readmodel: true, // 沉浸式阅读
        htmlcode: true, // 展示html源码
        help: true, // 帮助
        /* 1.3.5 */
        undo: true, // 上一步
        redo: true, // 下一步
        trash: true, // 清空
        save: false, // 保存（触发events中的save事件）
        /* 1.4.2 */
        navigation: true, // 导航目录
        /* 2.1.8 */
        alignleft: true, // 左对齐
        aligncenter: true, // 居中
        alignright: true, // 右对齐
        /* 2.2.1 */
        subfield: true, // 单双栏模式
        preview: true, // 预览
      }
    }
  },
  created() {
    this.receivedData = this.$route.params;
  },
  methods: {

    save() {
      this.$message.success("成功保存")
    },
    successUpload(response, file, fileList) {
      // 把头像属性换成上传的图片的链接
      this.pos = '![md图片](' + response.data + ' "md图片")'
    },
    upload() {
      let form;
      form = {
        userId: this.user.id,
        role: this.user.role,
        name: this.name,
        content: this.content
      }
      request.post("/md/add", form).then(res => {
        if (res.code === "200") {
          this.$message.success("成功")
        }
      })
    },
  },
  components: {
    mavonEditor
  }
}
</script>

<style scoped>
.mavon{
  height: 77vh;
  overflow-y: scroll;
}

</style>