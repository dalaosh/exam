const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,

  devServer: {
    //host:'192.168.1.17',
    port: 9999,
    client: {
      overlay: false,//取消红色
      webSocketURL: 'ws://localhost:9998/websocket/test/mc',
    },
    headers: {
      'Access-Control-Allow-Origin': '*',
    },
  },
  chainWebpack: config =>{
    config.plugin('html')
        .tap(args => {
          args[0].title = "基于国密算法与人脸认证的考试系统设计与实现";
          return args;
        })
  },

})
