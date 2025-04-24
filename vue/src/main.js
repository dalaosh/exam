import 'element-ui/lib/theme-chalk/index.css';
import '@/assets/global.css'
import "./config/utils"


import Vue from 'vue'
import router from './router'
Vue.config.productionTip = false

import ElementUI from 'element-ui';
Vue.use(ElementUI, { size: "small" })

//引入粒子插件
import VueParticles from 'vue-particles'
//注册粒子插件
Vue.use(VueParticles)

import VueRouter from 'vue-router';
Vue.use(VueRouter);

import * as echarts from "echarts"
// 绑定在vue的原型对象上
Vue.prototype.$echarts = echarts

import 'element-ui/lib/theme-chalk/index.css';

import scroll from 'vue-seamless-scroll'
Vue.use(scroll)

import App from './App.vue'
new Vue({
  router,
  render: h => h(App),
}).$mount('#app')

