import Vue from 'vue'
import VueRouter from 'vue-router'

//报错
import error from "@/views/404"
//大屏可视化
import bigEcharts from "@/views/BigEcharts/index.vue";
import bigEchartsWebSocket from "@/views/BigEchartsWebSocket/index.vue";
import websocketTest from "@/views/BigEcharts/websocketTest/index.vue";
//日志公告
import noticeAndNew from "@/views/home/noticeAndNews/NoticeAndNew"

// 登录
import adminLoginView from "@/views/admin/login/index";
import teacherLoginView from "@/views/teacher/login/index";
import studentLoginView from "@/views/student/login/index";
import caInstitutionLoginView from "@/views/caInstitution/login/index";
//忘记密码
import adminForgetView from "@/views/admin/forget/index";
import teacherForgetView from "@/views/teacher/forget/index";
import studentForgetView from "@/views/student/forget/index";
import caInstitutionForgetView from "@/views/caInstitution/forget/index";

//考生答题
import studentAnswer from "@/views/student/exam/paper/answer/index.vue";
import studentTips from "@/views/student/exam/paper/tips/index.vue";
import studentShowAnswer from "@/views/student/exam/paper/showAnswer/index.vue";
import studentShowTips from "@/views/student/exam/paper/showTips/index.vue";
//首页
import homeView from "@/views/home/index";
//管理员首页
import adminHomeView from "@/views/admin/home/index"
import adminMenu from "@/views/admin/menu/index";
//管理员端系统管理
import adminLog from "@/views/admin/systemManager/log/index"
import adminNotice from "@/views/admin/systemManager/notice/index"
import adminNews from "@/views/admin/systemManager/news/index"
//管理员个人中心
import adminKey from "@/views/admin/myCenter/key"
import adminPassword from "@/views/admin/myCenter/password"
import adminPerson from "@/views/admin/myCenter/person"
//管理员用户管理
import AdStudent from "@/views/admin/usermanager/student/index"
import AdTeacher from "@/views/admin/usermanager/teacher/index"
//教师首页
import teacherHomeView from "@/views/teacher/home/index"
import teacherMenu from "@/views/teacher/menu/index"
//教师个人中心
import teacherKey from "@/views/teacher/myCenter/key"
import teacherPassword from "@/views/teacher/myCenter/password"
import teacherPerson from "@/views/teacher/myCenter/person"
//教师端用户管理
import TStudent from "@/views/teacher/usermanager/student"
//教师课程操作
import teacherCourse from "@/views/teacher/course/course";
import teacherCourseStudent from "@/views/teacher/course/courseStudent";
import creatCourse from "@/views/teacher/course/creatCourse/index.vue";
import teacherCourseUpdate from "@/views/teacher/course/courseUpdate/index.vue";

//教师实名认证
import teacherRealFace from "@/views/teacher/exam/manager/realFace/index.vue";

//教师题库管理
//编程
import teacherQuestionCodeList from "@/views/teacher/question/questionCode/questionList"
import teacherQuestionCodeShow from "@/views/teacher/question/questionCode/showQuestion"
import teacherQuestionCodeCreated from "@/views/teacher/question/questionCode/createdQuestion"
import teacherQuestionCodeUpdate from "@/views/teacher/question/questionCode/updateQuestion"
//填空
import teacherQuestionFillList from "@/views/teacher/question/questionFill/questionList"
import teacherQuestionFillShow from "@/views/teacher/question/questionFill/showQuestion"
import teacherQuestionFillCreated from "@/views/teacher/question/questionFill/createdQuestion"
import teacherQuestionFillUpdate from "@/views/teacher/question/questionFill/updateQuestion"
//判断
import teacherQuestionJudgeList from "@/views/teacher/question/questionJudge/questionList"
import teacherQuestionJudgeShow from "@/views/teacher/question/questionJudge/showQuestion"
import teacherQuestionJudgeCreated from "@/views/teacher/question/questionJudge/createdQuestion"
import teacherQuestionJudgeUpdate from "@/views/teacher/question/questionJudge/updateQuestion"
//选择
import teacherQuestionMultiList from "@/views/teacher/question/questionMulti/questionList"
import teacherQuestionMultiShow from "@/views/teacher/question/questionMulti/showQuestion"
import teacherQuestionMultiCreated from "@/views/teacher/question/questionMulti/createdQuestion"
import teacherQuestionMultiUpdate from "@/views/teacher/question/questionMulti/updateQuestion"
//简答
import teacherQuestionShortAnsList from "@/views/teacher/question/questionShortAns/questionList"
import teacherQuestionShortAnsShow from "@/views/teacher/question/questionShortAns/showQuestion"
import teacherQuestionShortAnsCreated from "@/views/teacher/question/questionShortAns/createdQuestion"
import teacherQuestionShortAnsUpdate from "@/views/teacher/question/questionShortAns/updateQuestion"
//教师考试系统管理
import teacherCreatedManager from "@/views/teacher/exam/manager/createdManager";
import teacherManagerList from "@/views/teacher/exam/manager/managerList/index.vue";
import teacherShowManager from "@/views/teacher/exam/manager/showManager/index.vue";
import teacherUpdateManager from "@/views/teacher/exam/manager/updateManager/index.vue";
import teacherShowPublicKeys from "@/views/teacher/exam/manager/showPublicKeys/index.vue";
import teacherShowPublicKeysChild from "@/views/teacher/exam/manager/showPublicKeysChild/index.vue";
//教师分配试卷进行流水阅卷
import teacherDistributePaper from "@/views/teacher/exam/paper/distributePaper/index.vue";
import teacherMarkPaperList from "@/views/teacher/exam/paper/markPaperList/index.vue";
import teacherShowExamMsg from "@/views/teacher/exam/paper/showExamMsg/index.vue";
import teacherShowPaper from "@/views/teacher/exam/paper/showPaper/index.vue";
//教师统计分数
import teacherMarkCheck from "@/views/teacher/exam/mark/markCheck/index.vue";
import teacherMarkStatistics from "@/views/teacher/exam/mark/markStatistics/index.vue";
import teacherMarkList from "@/views/teacher/exam/mark/markList/index.vue";

//学生首页
import studentHomeView from "@/views/student/home/index"
import studentMenu from "@/views/student/menu/index"
//学生个人中心
import studentKey from "@/views/student/myCenter/key"
import studentPassword from "@/views/student/myCenter/password"
import studentPerson from "@/views/student/myCenter/person"
import realNameAuthentication from "@/views/student/myCenter/realNameAuthentication/index.vue";

//学生学习笔记
import studentCreatedNote from "@/views/student/studyNotes/createdNote"
import studentListNote from "@/views/student/studyNotes/listNote"
import studentShowNote from "@/views/student/studyNotes/showNote"
import studentUpdateNote from "@/views/student/studyNotes/updateNote"
//学生信息管理
import studentCourse from "@/views/student/course/course"
import studentCourseStudent from "@/views/student/course/courseStudent"
import studentCourseReply from "@/views/student/course/courseReply/index.vue";
//学生代码实操
import studentCreatedCode from "@/views/student/code/createdCode/index.vue"
import studentListCode from "@/views/student/code/listCode/index.vue"
import studentShowCode from "@/views/student/code/showCode/index.vue"
import studentUpdateCode from "@/views/student/code/updateCode/index.vue"
//学生考试系统管理
import studentExamList from "@/views/student/exam/manager/examList/index.vue";
import studentShowExam from "@/views/student/exam/manager/showExam/index.vue";
import studentShowPublicKeys from "@/views/student/exam/manager/showPublicKeys/index.vue";
import studentShowPublicKeysChild from "@/views/student/exam/manager/showPublicKeysChild/index.vue";
import studentScore from "@/views/student/exam/score/index.vue";

//CA机构首页
import caInstitutionHomeView from "@/views/caInstitution/home/index"
import caInstitutionMenu from "@/views/caInstitution/menu/index"
//CA机构端用户管理
import caAdmin from "@/views/caInstitution/usermanager/admin/index"
import caCaInstitution from "@/views/caInstitution/usermanager/caInstitution/index"
import caStudent from "@/views/caInstitution/usermanager/student/index"
import caTeacher from "@/views/caInstitution/usermanager/teacher/index"
//CA机构端个人中心
import caInstitutionKey from "@/views/caInstitution/myCenter/key"
import caInstitutionPassword from "@/views/caInstitution/myCenter/password"
import caInstitutionPerson from "@/views/caInstitution/myCenter/person"
//CA机构学校管理
import caInstitutionUniversity from "@/views/caInstitution/schoolsManager/university/index.vue";
import caInstitutionColleges from "@/views/caInstitution/schoolsManager/colleges/index.vue";
import caInstitutionMajor from "@/views/caInstitution/schoolsManager/major/index.vue";
//CA机构实名认证
import caInstitutionRealFace from "@/views/caInstitution/realFace/index.vue";

Vue.use(VueRouter)

const routes = [
  //   首页
  {path: "/", component: homeView,
    meta: {name:"首页",requiresAuth: true},
  },
  //大屏可视化
  {path: "/bigEcharts", name: "bigEcharts", component: bigEcharts,
    meta: {name:"大屏可视化",requiresAuth: false}
  },
  {path: "/bigEchartsWebSocket", name: "bigEchartsWebSocket", component: bigEchartsWebSocket,
    meta: {name:"大屏可视化WS",requiresAuth: false}
  },
  {path: "/websocketTest", name: "websocketTest", component: websocketTest,
    meta: {name:"websocket测试",requiresAuth: false}
  },
  {path: "/noticeAndNew/:type/:id", name: "noticeAndNew", component: noticeAndNew,
    meta: {name:"公告测试",requiresAuth: false}
  },
  //   登录
  { path: '/adminLogin', name: 'adminLogin', component: adminLoginView,
    meta: {name:"管理员登录",requiresAuth: false}
  },
  {path: "/teacherLogin", name: "teacherLogin", component: teacherLoginView,
    meta: {name:"教师登录",requiresAuth: false}
  },
  {path: "/studentLogin", name: "studentLogin", component: studentLoginView,
    meta: {name:"学生登录",requiresAuth: false}
  },
  {path: "/caInstitutionLogin", name: "caInstitutionLogin", component: caInstitutionLoginView,
    meta: {name:"机构登录",requiresAuth: false}
  },
  //    忘记密码
  { path: '/adminForget', name: 'adminForget', component: adminForgetView,
    meta: {name:"管理员忘记密码",requiresAuth: false}
  },
  {path: "/teacherForget", name: "teacherForget", component: teacherForgetView,
    meta: {name:"教师忘记密码",requiresAuth: false}
  },
  {path: "/studentForget", name: "studentForget", component: studentForgetView,
    meta: {name:"学生忘记密码",requiresAuth: false}
  },
  {path: "/caInstitutionForget", name: "caInstitutionForget", component: caInstitutionForgetView,
    meta: {name:"机构忘记密码",requiresAuth: false}
  },
  //   考生答题
  {path: "/studentTips/:id/:examStudent", name: "studentTips", component: studentTips,
    meta: {name:"考生须知",requiresAuth: true,role: 'student'}
  },
  {path: "/studentAnswer/:id", name: "studentAnswer", component: studentAnswer,
    meta: {name:"考生考试界面",requiresAuth: true,role: 'student'}
  },
  {path: "/studentShowTips/:id", name: "studentShowTips", component: studentShowTips,
    meta: {name:"参考答案须知",requiresAuth: true,role: 'student'}
  },
  {path: "/studentShowAnswer/:id", name: "studentShowAnswer", component: studentShowAnswer,
    meta: {name:"考生参考答案界面",requiresAuth: true,role: 'student'}
  },
  {path: '/admin', component: adminMenu,
    children:[
      {path: 'home', name: 'adminHome', component: adminHomeView,
        meta: {name:"首页",requiresAuth: true,role: 'admin'}
      },
      {path: 'log', name: 'adminLog', component: adminLog,
        meta: {name:"日志列表",requiresAuth: true,role: 'admin'}
      },
      {path: 'notice', name: 'adminNotice', component: adminNotice,
        meta: {name:"公告列表",requiresAuth: true,role: 'admin'}
      },
      {path: 'news', name: 'adminNews', component: adminNews,
        meta: {name:"新闻列表",requiresAuth: true,role: 'admin'}
      },
      {path: 'person', name: 'adminPerson', component: adminPerson,
        meta: {name:"个人中心",requiresAuth: true,role: 'admin'}
      },
      {path: 'key', name: 'adminKey', component: adminKey,
        meta: {name:"密钥查看",requiresAuth: true,role: 'admin'}
      },
      {path: 'password', name: 'adminPassword', component: adminPassword,
        meta: {name:"密码修改",requiresAuth: true,role: 'admin'}
      },
      {path: 'teacher', name: 'AdTeacher', component: AdTeacher,
        meta: {name:"教师列表",requiresAuth: true,role: 'admin'}
      },
      {path: 'student', name: 'AdStudent', component: AdStudent,
        meta: {name:"学生列表",requiresAuth: true,role: 'admin'}
      },
    ]
  },
  {path: '/caInstitution',component: caInstitutionMenu,
    children:[
      {path: 'home', name: 'caInstitutionHome', component: caInstitutionHomeView,
        meta: {name:"首页",requiresAuth: true,role: 'caInstitution'}
      },
      {path: 'admin', name: 'caAdmin', component: caAdmin,
        meta: {name:"管理员列表",requiresAuth: true,role: 'caInstitution'}
      },
      {path: 'caInstitution', name: 'caCaInstitution', component: caCaInstitution,
        meta: {name:"机构列表",requiresAuth: true,role: 'caInstitution'}
      },
      {path: 'teacher', name: 'caTeacher', component: caTeacher,
        meta: {name:"教师列表",requiresAuth: true,role: 'caInstitution'}
      },
      {path: 'student', name: 'caStudent', component: caStudent,
        meta: {name:"学生列表",requiresAuth: true,role: 'caInstitution'}
      },
      {path: 'person', name: 'caInstitutionPerson', component: caInstitutionPerson,
        meta: {name:"个人中心",requiresAuth: true,role: 'caInstitution'}
      },
      {path: 'key', name: 'caInstitutionKey', component:  caInstitutionKey,
        meta: {name:"密钥查看",requiresAuth: true,role: 'caInstitution'}
      },
      {path: 'password', name: 'caInstitutionPassword', component:  caInstitutionPassword,
        meta: {name:"密码修改",requiresAuth: true,role: 'caInstitution'}
      },
      {path: 'caInstitutionUniversity', name: 'caInstitutionUniversity', component: caInstitutionUniversity,
        meta: {name:"学校列表",requiresAuth: true,role: 'caInstitution'}
      },
      {path: 'caInstitutionColleges', name: 'caInstitutionColleges', component: caInstitutionColleges,
        meta: {name:"学院列表",requiresAuth: true,role: 'caInstitution'}
      },
      {path: 'caInstitutionMajor', name: 'caInstitutionMajor', component:  caInstitutionMajor,
        meta: {name:"专业列表",requiresAuth: true,role: 'caInstitution'}
      },
      {path: 'caInstitutionRealFace', name: 'caInstitutionRealFace', component:  caInstitutionRealFace,
        meta: {name:"实名认证",requiresAuth: true,role: 'caInstitution'}
      },
    ]
  },
  {path: '/teacher', component: teacherMenu,
    children:[
      {path: 'home', name: 'teacherHome', component: teacherHomeView,
        meta: {name:"首页",requiresAuth: true,role: 'teacher'}
      },
      {path: 'person', name: 'teacherPerson', component: teacherPerson,
        meta: {name:"个人中心",requiresAuth: true,role: 'teacher'}
      },
      {path: 'key', name: 'teacherKey', component:  teacherKey,
        meta: {name:"密钥查看",requiresAuth: true,role: 'teacher'}
      },
      {path: 'password', name: 'teacherPassword', component:  teacherPassword,
        meta: {name:"密码修改",requiresAuth: true,role: 'teacher'}
      },
      {path: 'student', name: 'TStudent', component: TStudent,
        meta: {name:"学生列表",requiresAuth: true,role: 'teacher'}
      },
      {path: 'teacherCourse', name: 'teacherCourse', component:  teacherCourse,
        meta: {name:"课程列表",requiresAuth: true,role: 'teacher'}
      },
      {path: 'teacherCourseStudent/:id', name: 'teacherCourseStudent', component: teacherCourseStudent,
        meta: {name:"课程学生管理",requiresAuth: true,role: 'teacher'}
      },
      {path: 'teacherCourseUpdate/:id', name: 'teacherCourseUpdate', component: teacherCourseUpdate,
        meta: {name:"课程修改",requiresAuth: true,role: 'teacher'}
      },
      {path: 'creatCourse', name: 'creatCourse', component: creatCourse,
        meta: {name:"创建课程",requiresAuth: true,role: 'teacher'}
      },
      {path: 'teacherQuestionCodeList', name: 'teacherQuestionCodeList', component: teacherQuestionCodeList,
        meta: {name:"编程题库列表",requiresAuth: true,role: 'teacher'}
      },
      {path: 'teacherQuestionCodeShow/:id', name: 'teacherQuestionCodeShow', component: teacherQuestionCodeShow,
        meta: {name:"编程题详情",requiresAuth: true,role: 'teacher'}
      },
      {path: 'teacherQuestionCodeCreated', name: 'teacherQuestionCodeCreated', component: teacherQuestionCodeCreated,
        meta: {name:"编程题创建",requiresAuth: true,role: 'teacher'}
      },
      {path: 'teacherQuestionCodeUpdate/:id', name: 'teacherQuestionCodeUpdate', component: teacherQuestionCodeUpdate,
        meta: {name:"编程题修改",requiresAuth: true,role: 'teacher'}
      },
      {path: 'teacherQuestionFillList', name: 'teacherQuestionFillList', component: teacherQuestionFillList,
        meta: {name:"填空题库列表",requiresAuth: true,role: 'teacher'}
      },
      {path: 'teacherQuestionFillShow/:id', name: 'teacherQuestionFillShow', component: teacherQuestionFillShow,
        meta: {name:"填空题详情",requiresAuth: true,role: 'teacher'}
      },
      {path: 'teacherQuestionFillCreated', name: 'teacherQuestionFillCreated', component: teacherQuestionFillCreated,
        meta: {name:"填空题创建",requiresAuth: true,role: 'teacher'}
      },
      {path: 'teacherQuestionFillUpdate/:id', name: 'teacherQuestionFillUpdate', component: teacherQuestionFillUpdate,
        meta: {name:"填空题修改",requiresAuth: true,role: 'teacher'}
      },
      {path: 'teacherQuestionJudgeList', name: 'teacherQuestionJudgeList', component: teacherQuestionJudgeList,
        meta: {name:"判断题库列表",requiresAuth: true,role: 'teacher'}
      },
      {path: 'teacherQuestionJudgeShow/:id', name: 'teacherQuestionJudgeShow', component: teacherQuestionJudgeShow,
        meta: {name:"判断题详情",requiresAuth: true,role: 'teacher'}
      },
      {path: 'teacherQuestionJudgeCreated', name: 'teacherQuestionJudgeCreated', component: teacherQuestionJudgeCreated,
        meta: {name:"判断题创建",requiresAuth: true,role: 'teacher'}
      },
      {path: 'teacherQuestionJudgeUpdate/:id', name: 'teacherQuestionJudgeUpdate', component: teacherQuestionJudgeUpdate,
        meta: {name:"判断题修改",requiresAuth: true,role: 'teacher'}
      },
      {path: 'teacherQuestionMultiList', name: 'teacherQuestionMultiList', component: teacherQuestionMultiList,
        meta: {name:"选择题库列表",requiresAuth: true,role: 'teacher'}
      },
      {path: 'teacherQuestionMultiShow/:id', name: 'teacherQuestionMultiShow', component: teacherQuestionMultiShow,
        meta: {name:"选择题详情",requiresAuth: true,role: 'teacher'}
      },
      {path: 'teacherQuestionMultiCreated', name: 'teacherQuestionMultiCreated', component: teacherQuestionMultiCreated,
        meta: {name:"选择题创建",requiresAuth: true,role: 'teacher'}
      },
      {path: 'teacherQuestionMultiUpdate/:id', name: 'teacherQuestionMultiUpdate', component: teacherQuestionMultiUpdate,
        meta: {name:"选择题修改",requiresAuth: true,role: 'teacher'}
      },
      {path: 'teacherQuestionShortAnsList', name: 'teacherQuestionShortAnsList', component: teacherQuestionShortAnsList,
        meta: {name:"简答题库列表",requiresAuth: true,role: 'teacher'}
      },
      {path: 'teacherQuestionShortAnsShow/:id', name: 'teacherQuestionShortAnsShow', component: teacherQuestionShortAnsShow,
        meta: {name:"简答题详情",requiresAuth: true,role: 'teacher'}
      },
      {path: 'teacherQuestionShortAnsCreated', name: 'teacherQuestionShortAnsCreated', component: teacherQuestionShortAnsCreated,
        meta: {name:"简答题创建",requiresAuth: true,role: 'teacher'}
      },
      {path: 'teacherQuestionShortAnsUpdate/:id', name: 'teacherQuestionShortAnsUpdate', component: teacherQuestionShortAnsUpdate,
        meta: {name:"简答题修改",requiresAuth: true,role: 'teacher'}
      },
      {path: 'teacherManagerList', name: 'teacherManagerList', component: teacherManagerList,
        meta: {name:"试卷信息管理",requiresAuth: true,role: 'teacher'}
      },
      {path: 'teacherShowManager/:id/:courseId', name: 'teacherShowManager', component: teacherShowManager,
        meta: {name:"试卷信息详情与添加试题",requiresAuth: true,role: 'teacher'}
      },
      {path: 'teacherCreatedManager', name: 'teacherCreatedManager', component: teacherCreatedManager,
        meta: {name:"试卷信息创建",requiresAuth: true,role: 'teacher'}
      },
      {path: 'teacherUpdateManager/:id', name: 'teacherUpdateManager', component: teacherUpdateManager,
        meta: {name:"试卷信息修改",requiresAuth: true,role: 'teacher'}
      },
      {path: 'teacherShowPublicKeys/:id', name: 'teacherShowPublicKeys', component: teacherShowPublicKeys,
        meta: {name:"查看环公钥",requiresAuth: true, role: 'teacher'},
        children:[
          {path: 'teacherShowPublicKeysChild/:name/:key', name: 'teacherShowPublicKeysChild', component: teacherShowPublicKeysChild,
            meta: {name:"查看环公钥详情",requiresAuth: true, role: 'teacher'},
          }
        ]
      },
      {path: 'teacherDistributePaper/:id', name: 'teacherDistributePaper', component: teacherDistributePaper,
        meta: {name:"试题分发",requiresAuth: true,role: 'teacher'}
      },
      {path: 'teacherMarkPaperList', name: 'teacherMarkPaperList', component: teacherMarkPaperList,
        meta: {name:"批阅试卷列表",requiresAuth: true,role: 'teacher'}
      },
      {path: 'teacherShowExamMsg/:id', name: 'teacherShowExamMsg', component: teacherShowExamMsg,
        meta: {name:"试卷详情",requiresAuth: true,role: 'teacher'}
      },
      {path: 'teacherShowPaper/:id', name: 'teacherShowPaper', component: teacherShowPaper,
        meta: {name:"试卷阅卷详情",requiresAuth: true,role: 'teacher'}
      },
      {path: 'teacherMarkList', name: 'teacherMarkList', component: teacherMarkList,
        meta: {name:"分数列表",requiresAuth: true,role: 'teacher'}
      },
      {path: 'teacherMarkCheck/:id', name: 'teacherMarkCheck', component: teacherMarkCheck,
        meta: {name:"题目校验",requiresAuth: true,role: 'teacher'}
      },
      {path: 'teacherMarkStatistics/:id', name: 'teacherMarkStatistics', component: teacherMarkStatistics,
        meta: {name:"分数统计",requiresAuth: true,role: 'teacher'}
      },
      {path: 'teacherRealFace/:id', name: 'teacherRealFace', component: teacherRealFace,
        meta: {name:"手动认证",requiresAuth: true,role: 'teacher'}
      },
    ]
  },
  {path: '/student', component: studentMenu,
    children:[
      {path: 'home', name: 'studentHome', component: studentHomeView,
        meta: {name:"首页",requiresAuth: true,role: 'student'}
      },
      {path: 'person', name: 'studentPerson', component: studentPerson,
        meta: {name:"个人中心",requiresAuth: true,role: 'student'}
      },
      {path: 'key', name: 'studentKey', component:  studentKey,
        meta: {name:"密钥查看",requiresAuth: true,role: 'student'}
      },
      {path: 'password', name: 'studentPassword', component:  studentPassword,
        meta: {name:"密码修改",requiresAuth: true,role: 'student'}
      },
      {path: 'realNameAuthentication', name: 'realNameAuthentication', component:  realNameAuthentication,
        meta: {name:"实名认证",requiresAuth: true,role: 'student'}
      },
      {path: 'createdNote', name: 'studentCreatedNote', component:  studentCreatedNote,
        meta: {name:"新建笔记",requiresAuth: true,role: 'student'}
      },
      {path: 'listNote', name: 'studentListNote', component:  studentListNote,
        meta: {name:"笔记列表",requiresAuth: true,role: 'student'}
      },
      {path: 'showNote', name: 'studentShowNote', component:  studentShowNote,
        meta: {name:"笔记查看",requiresAuth: true,role: 'student'}
      },
      {path: 'updateNote', name: 'studentUpdateNote', component:  studentUpdateNote,
        meta: {name:"笔记修改",requiresAuth: true,role: 'student'}
      },
      {path: 'studentCourse', name: 'studentCourse', component:  studentCourse,
        meta: {name:"课程信息",requiresAuth: true,role: 'student'}
      },
      {path: 'studentCourseStudent/:id', name: 'studentCourseStudent', component:  studentCourseStudent,
        meta: {name:"课程详情",requiresAuth: true,role: 'student'}
      },
      {path: 'studentCourseReply/:id', name: 'studentCourseReply', component:  studentCourseReply,
        meta: {name:"课程申请",requiresAuth: true,role: 'student'}
      },
      {path: 'createdCode', name: 'studentCreatedCode', component:  studentCreatedCode,
        meta: {name:"代码实操",requiresAuth: true,role: 'student'}
      },
      {path: 'listCode', name: 'studentListCode', component:  studentListCode,
        meta: {name:"代码列表",requiresAuth: true,role: 'student'}
      },
      {path: 'showCode/:id', name: 'studentShowCode', component:  studentShowCode,
        meta: {name:"代码详情",requiresAuth: true,role: 'student'}
      },
      {path: 'updateCode/:id', name: 'studentUpdateCode', component:  studentUpdateCode,
        meta: {name:"代码修改",requiresAuth: true,role: 'student'}
      },
      {path: 'studentExamList', name: 'studentExamList', component:  studentExamList,
        meta: {name:"考试列表",requiresAuth: true,role: 'student'}
      },
      {path: 'studentShowExam/:id', name: 'studentShowExam', component:  studentShowExam,
        meta: {name:"考试详情",requiresAuth: true,role: 'student'}
      },
      {path: 'studentShowPublicKeys/:id', name: 'studentShowPublicKeys', component: studentShowPublicKeys,
        meta: {name:"查看环公钥",requiresAuth: true, role: 'student'},
        children:[
          {path: 'studentShowPublicKeysChild/:name/:key', name: 'studentShowPublicKeysChild', component: studentShowPublicKeysChild,
            meta: {name:"查看环公钥详情",requiresAuth: true, role: 'student'},
          }
        ]
      },
      {path: 'studentScore/:id', name: 'studentScore', component:  studentScore,
        meta: {name:"成绩公布",requiresAuth: true,role: 'student'}
      },
    ]
  },
  { path: '*', name: 'NotFound', component: error,
    meta: {name:"报错",requiresAuth: true}
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})


// 注：不需要前台的项目，可以注释掉该路由守卫
// 路由守卫
router.beforeEach((to ,from, next) => {
  const loggedInUser =JSON.parse(localStorage.getItem("user") || '{}');
  if (to.path === '/'){return next()}
  if (!loggedInUser) {next('/login')}
  if (to.meta.requiresAuth) {
    if (!loggedInUser) {
      return next('/login');
    } else {
      if (to.meta.role === 'admin' && loggedInUser.role === 'caInstitution') {
        return next('/caInstitution/home');
      } else if (to.meta.role === 'admin' && loggedInUser.role === 'teacher') {
        return next('/teacher/home');
      }
      else if (to.meta.role === 'admin' && loggedInUser.role === 'student') {
        return next('/student/home');
      }

      else if (to.meta.role === 'caInstitution' && loggedInUser.role === 'admin') {
        return next('/admin/home');
      } else if (to.meta.role === 'caInstitution' && loggedInUser.role === 'teacher') {
        return next('/teacher/home');
      }
      else if (to.meta.role === 'caInstitution' && loggedInUser.role === 'student') {
        return next('/student/home');
      }

      else if (to.meta.role === 'teacher' && loggedInUser.role === 'admin') {
        return next('/admin/home');
      } else if (to.meta.role === 'teacher' && loggedInUser.role === 'caInstitution') {
        return next('/caInstitution/home');
      }
      else if (to.meta.role === 'teacher' && loggedInUser.role === 'student') {
        return next('/student/home');
      }

      else if (to.meta.role === 'student' && loggedInUser.role === 'admin') {
        return next('/admin/home');
      }else if (to.meta.role === 'student' && loggedInUser.role === 'caInstitution') {
        return next('/caInstitution/home');
      } else if (to.meta.role === 'student' && loggedInUser.role === 'teacher') {
        return next('/teacher/home');
      }
      return next()
    }
  }
  next();
})

export default router
