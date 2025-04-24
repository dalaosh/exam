<template>
  <footer class="footer">
    <!-- 版权信息 -->
    <div class="copyright">
      <p>&copy; 2024 - 2025 考试系统版权所有</p>
    </div>
    <!-- 联系方式 -->
    <div class="contact">
      <p>官方邮箱：2563622159@qq.com</p>
      <p>客服电话：19958500349</p>
    </div>
    <!-- 友情链接 -->
    <div class="links">
      <p>友情链接：</p>
      <ul>
        <li><a href="https://www.lzjtu.edu.cn/" target="_blank">兰州交通大学</a></li>
        <li><a href="https://www.lut.edu.cn/" target="_blank">兰州理工大学</a></li>
      </ul>
    </div>
  </footer>
</template>

<script>
export default {
  name: 'FooterMenu',
  data() {
    return {};
  },
  methods: {}
};
</script>

<style scoped>
.footer {
  background-color: #333;
  color: white;
  padding: 20px;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
}

.copyright,
.contact,
.links {
  margin: 10px;
}

.links ul {
  list-style-type: none;
  padding: 0;
}

.links ul li {
  margin: 5px 0;
}

.links ul li a {
  color: white;
  text-decoration: none;
}

.links ul li a:hover {
  text-decoration: underline;
}
</style>