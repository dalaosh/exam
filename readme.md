# 基于国密算法与人脸认证的考试管理系统

这是一个面向课程考试、在线答题、题库管理、阅卷评分和身份认证的前后端分离项目。系统采用 Spring Cloud 多模块后端与 Vue 2 前端实现，按管理员、教师、学生、CA 机构四类角色划分功能入口，并通过网关统一转发各业务服务。

## 项目功能

- 多角色登录与权限路由：管理员、教师、学生、CA 机构分别进入独立工作台。
- 用户与组织管理：学生、教师、管理员、CA 机构、学校、学院、专业等基础信息维护。
- 课程与考试管理：课程创建、学生选课/课程学生管理、考试创建、试卷配置、考生分配。
- 题库管理：选择题、判断题、填空题、简答题、编程题的增删改查、导入导出和组卷使用。
- 在线考试与答题：学生查看考试、进入考试、提交答卷、查看参考答案与成绩。
- 阅卷与成绩统计：教师分配试卷、流水阅卷、题目校验、成绩列表和统计分析。
- 实名与人脸认证：学生/教师实名认证、人脸图片上传、ArcSoft 人脸识别相关能力。
- 国密/密钥相关能力：前端集成 `sm-crypto`，后端保留密钥查看、环公钥展示、签名校验等业务入口。
- 文件与内容管理：头像、课程文件、Markdown、富文本图片、真实人脸/测试人脸文件上传。
- 数据可视化与 WebSocket：考试、用户、使用量等统计大屏与 WebSocket 数据通道。

## 技术栈

### 后端

- JDK 17
- Spring Boot 3.0.0
- Spring Cloud 2022.0.0
- Spring Cloud Alibaba 2022.0.0.0-RC1
- Spring Cloud Gateway
- MyBatis / tk.mybatis / PageHelper
- MySQL 5.7
- Redis
- Knife4j / springdoc-openapi
- Hutool、JWT、Apache POI、Easy Captcha、ZXing
- ArcSoft Face SDK

### 前端

- Vue 2.6
- Vue Router 3
- Vue CLI 5
- Element UI 2
- Axios
- ECharts
- CodeMirror / Monaco Editor
- Markdown 编辑器与富文本编辑器
- MediaPipe Tasks Vision
- sm-crypto
- Video.js、Three.js

## 目录结构

```text
D:\examination_management
├── examsystem.sql              # 数据库初始化脚本
├── springCloud                 # Spring Cloud 后端多模块工程
│   ├── es-common               # 公共类、异常处理、通用配置
│   ├── es-gateway              # 网关服务，默认端口 9998
│   ├── es-user                 # 用户、登录、验证码、个人中心
│   ├── es-mail                 # 邮箱验证码服务
│   ├── es-info                 # 公告、新闻、日志
│   ├── es-files                # 文件上传与访问
│   ├── es-exam                 # 课程、题库、考试、阅卷、成绩
│   ├── es-statistic            # 统计与大屏数据
│   ├── es-trustSeal            # 学校信息、实名/人脸认证
│   └── es-websocket            # WebSocket 数据推送
└── vue                         # Vue 2 前端工程
    ├── src/router              # 前端路由与角色守卫
    ├── src/views/admin         # 管理员端
    ├── src/views/teacher       # 教师端
    ├── src/views/student       # 学生端
    ├── src/views/caInstitution # CA 机构端
    └── src/utils/request.js    # Axios 请求封装
```

## 服务端口

| 服务 | 模块 | 默认端口 | 网关路径 |
| --- | --- | ---: | --- |
| 前端开发服务 | `vue` | `9999` | - |
| 网关服务 | `es-gateway` | `9998` | - |
| 用户服务 | `es-user` | `9080` | `/user/**`, `/md/**` |
| 邮箱服务 | `es-mail` | `9081` | `/email/**` |
| 信息服务 | `es-info` | `9082` | `/info/**` |
| 文件服务 | `es-files` | `9083` | `/files/**` |
| 考试服务 | `es-exam` | `9084` | `/exam/**` |
| 统计服务 | `es-statistic` | `9085` | `/statistic/**` |
| 认证服务 | `es-trustSeal` | `9086` | `/trustSeal/**` |
| WebSocket 服务 | `es-websocket` | `9087` | `/websocket/**` |

当前 `springCloud/es-gateway/src/main/resources/application.yml` 使用静态路由转发，并关闭了 Nacos discovery/config。如果需要恢复 Nacos 注册发现，需要同步调整各服务的 `spring.cloud.nacos` 配置和网关路由策略。

## 环境准备

建议使用以下版本，与项目原始配置保持一致：

- JDK：17
- Maven：3.9.x
- MySQL：5.7
- Redis：本地 `6379`
- Node.js：16.20.x
- npm：8.19.x

初始化数据库：

```sql
CREATE DATABASE examsystem DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
```

然后将根目录下的 `examsystem.sql` 导入 `examsystem` 数据库。

后端数据库连接配置位于各业务模块的：

```text
springCloud/es-*/src/main/resources/application.properties
```

Redis、服务端口、网关路由等配置位于：

```text
springCloud/es-*/src/main/resources/application.yml
```

请根据本机 MySQL、Redis 的账号密码修改配置。生产或公开演示环境不要直接使用仓库中已有的示例密码、邮箱授权码或前端 API Key。

## 后端启动

进入后端工程：

```powershell
cd D:\examination_management\springCloud
```

编译全部模块：

```powershell
mvn clean package -DskipTests
```

启动顺序建议：

1. 启动 MySQL，并确认已导入 `examsystem.sql`。
2. 启动 Redis，并确保密码配置与 `application.yml` 一致。
3. 启动各业务服务。
4. 最后启动 `es-gateway` 网关服务。

可在 IDE 中分别运行各模块的 Application 类，也可使用 Maven 启动单个模块，例如：

```powershell
mvn -pl es-user -am spring-boot:run
mvn -pl es-exam -am spring-boot:run
mvn -pl es-gateway -am spring-boot:run
```

常用启动类：

```text
springCloud/es-user/src/main/java/com/mc/esuser/EsUserApplication.java
springCloud/es-exam/src/main/java/com/mc/esexam/EsExamApplication.java
springCloud/es-files/src/main/java/com/mc/esfiles/EsFilesApplication.java
springCloud/es-statistic/src/main/java/com/mc/esstatistic/EsStatisticApplication.java
springCloud/es-trustSeal/src/main/java/com/mc/estrustSeal/EsTrustSealApplication.java
springCloud/es-websocket/src/main/java/com/mc/eswebsocket/EsWebSocketApplication.java
springCloud/es-gateway/src/main/java/com/mc/esgateway/EsGatewayApplication.java
```

网关启动后，接口统一通过：

```text
http://localhost:9998
```

Knife4j 聚合文档入口：

```text
http://localhost:9998/doc.html
```

## 前端启动

进入前端工程：

```powershell
cd D:\examination_management\vue
```

安装依赖：

```powershell
npm install
```

启动开发环境：

```powershell
npm run serve
```

默认访问地址：

```text
http://localhost:9999
```

前端接口地址配置：

```text
vue/.env.development
vue/.env.production
```

开发环境默认请求后端网关：

```text
VUE_APP_BASEURL='http://localhost:9998'
```

生产构建：

```powershell
npm run build
```

构建产物输出到：

```text
vue/dist
```

## 主要页面入口

| 角色/页面 | 路由 |
| --- | --- |
| 首页 | `/` |
| 管理员登录 | `/adminLogin` |
| 教师登录 | `/teacherLogin` |
| 学生登录 | `/studentLogin` |
| CA 机构登录 | `/caInstitutionLogin` |
| 管理员工作台 | `/admin/home` |
| 教师工作台 | `/teacher/home` |
| 学生工作台 | `/student/home` |
| CA 机构工作台 | `/caInstitution/home` |
| 统计大屏 | `/bigEcharts`, `/bigEchartsWebSocket` |

项目使用 Vue Router history 模式。若部署到 Nginx，需要配置前端路由回退到 `index.html`。

## 本地验证清单

完成启动后，建议按以下顺序验证：

1. `http://localhost:9998/doc.html` 可以打开接口文档。
2. `http://localhost:9999` 可以打开前端首页。
3. 登录页验证码可以正常加载。
4. 管理员、教师、学生、CA 机构至少各验证一个可用账号。
5. 登录后首页数据、菜单和个人信息可以正常加载。
6. 教师端验证课程、题库、考试管理的列表接口。
7. 学生端验证考试列表、答题入口和成绩页面。
8. 如需人脸认证，确认 ArcSoft SDK 文件、授权文件和图片存储路径可用。

## 常见注意事项

- 后端多个模块都依赖 MySQL、Redis；只启动网关不能代表系统可用。
- 当前配置中部分服务仍保留 Nacos 依赖，但发现与配置中心已关闭；不要同时混用静态路由和未配置完整的 Nacos 路由。
- `springCloud/files`、`springCloud/es-common/src/main/resources/application.yml` 中配置了人脸图片相关存储路径，迁移机器后需要检查绝对路径。
- 编程题运行能力依赖本机 C/C++ 编译器路径，部署前需要核对后端代码中的编译器配置。
- 前端 `.env` 文件中如包含真实 API Key，应迁移到本地私有配置或部署平台环境变量，不建议提交到公开仓库。
- 如果 Vue 构建遇到 `thread-loader` 的 `spawn EPERM`，优先检查 Windows 权限、杀毒软件或受限运行环境。

## 维护建议

- 新增后端接口后，同步检查网关路由、Knife4j 文档和前端请求路径。
- 新增前端页面时，同步维护 `vue/src/router/index.js` 的角色路由与权限跳转。
- 修改数据库结构后，及时更新 `examsystem.sql` 或新增迁移说明。
- 涉及登录、密钥、人脸认证、阅卷评分的改动，应同时验证接口、页面和数据库闭环。
