# 创建总项目
>使用spring-cloud-alibaba构建微服务平台 \
> 加载必要的配置文件

# 创建用户服务
>创建springboot项目 \
> 引入相关配置 \
> 创建接口 

# 构建 nacos
> 首先从官网下载包  https://nacos.io/download/release-history/?spm=5238cd80.2ef5001f.0.0.3f613b7c8cuIxE \
> 解压后配置密钥  在配置文件中VGhpc0lzTXlDdXN0b21TZWNyZXRLZXkwMTIzNDU2Nzg=  \
> 最后修改启动方式   在启动文件中  set MODE="standalone"
> 在每个文件中配置
```yml
spring:
  application:
    name: es-user
  config:
    import: nacos:es-user-dev.yml
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848
      config:
        server-addr: localhost:8848
        file-extension: yml

```
# 创建网关服务
1.创建一个springboot项目 



# 扫描二维码解密
pir   /String
# 签字

# 

API
sk-44d77924842e4c388ff268c3c5f0af34

sk-44d77924842e4c388ff268c3c5f0af34

# 参考文献/毕业相关  现场翻译
# 中文文献查阅 /论文大纲  
论文结构  背景
# SCI论文




