<template xmlns="">
  <div class="main">
    <div class="overview-grid">
      <el-row>
        <el-col :span="4">
          <div class="cards">
            <div class="title">选择题</div>
            <el-row>
              <el-col :span="8"><strong>批阅数</strong></el-col>
              <el-col :span="8" style="text-align: center">
                <div>已批</div><div>{{allCount.questionMulti.isMarkCount}}</div>
              </el-col>
              <el-col :span="8" style="text-align: center">
                <div>未批</div><div>{{allCount.questionMulti.noMarkCount}}</div>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="8"><strong>验签</strong></el-col>
              <el-col :span="8" style="text-align: center">
                <div>成功</div><div>{{allCount.questionMulti.isRightSignCount}}</div>
              </el-col>
              <el-col :span="8"  style="text-align: center">
                <div>失败</div><div>{{allCount.questionMulti.noRightSignCount}}</div>
              </el-col>
            </el-row>
          </div>
        </el-col>
        <el-col :span="4">
          <div class="cards">
            <div class="title">填空题</div>
            <el-row>
              <el-col :span="8"><strong>批阅数</strong></el-col>
              <el-col :span="8" style="text-align: center">
                <div>已批</div><div>{{allCount.questionFill.isMarkCount}}</div>
              </el-col>
              <el-col :span="8" style="text-align: center">
                <div>未批</div><div>{{allCount.questionFill.noMarkCount}}</div>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="8"><strong>验签</strong></el-col>
              <el-col :span="8" style="text-align: center">
                <div>成功</div><div>{{allCount.questionFill.isRightSignCount}}</div>
              </el-col>
              <el-col :span="8"  style="text-align: center">
                <div>失败</div><div>{{allCount.questionFill.noRightSignCount}}</div>
              </el-col>
            </el-row>
          </div>
        </el-col>
        <el-col :span="4">
          <div class="cards">
            <div class="title">判断题</div>
            <el-row>
              <el-col :span="8"><strong>批阅数</strong></el-col>
              <el-col :span="8" style="text-align: center">
                <div>已批</div><div>{{allCount.questionJudge.isMarkCount}}</div>
              </el-col>
              <el-col :span="8" style="text-align: center">
                <div>未批</div><div>{{allCount.questionJudge.noMarkCount}}</div>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="8"><strong>验签</strong></el-col>
              <el-col :span="8" style="text-align: center">
                <div>成功</div><div>{{allCount.questionJudge.isRightSignCount}}</div>
              </el-col>
              <el-col :span="8"  style="text-align: center">
                <div>失败</div><div>{{allCount.questionJudge.noRightSignCount}}</div>
              </el-col>
            </el-row>
          </div>
        </el-col>
        <el-col :span="4">
          <div class="cards">
            <div class="title">简答题</div>
            <el-row>
              <el-col :span="8"><strong>批阅数</strong></el-col>
              <el-col :span="8" style="text-align: center">
                <div>已批</div><div>{{allCount.questionShortAns.isMarkCount}}</div>
              </el-col>
              <el-col :span="8" style="text-align: center">
                <div>未批</div><div>{{allCount.questionShortAns.noMarkCount}}</div>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="8"><strong>验签</strong></el-col>
              <el-col :span="8" style="text-align: center">
                <div>成功</div><div>{{allCount.questionShortAns.isRightSignCount}}</div>
              </el-col>
              <el-col :span="8"  style="text-align: center">
                <div>失败</div><div>{{allCount.questionShortAns.noRightSignCount}}</div>
              </el-col>
            </el-row>
          </div>
        </el-col>
        <el-col :span="4">
          <div class="cards">
            <div class="title">代码题</div>
            <el-row>
              <el-col :span="8"><strong>批阅数</strong></el-col>
              <el-col :span="8" style="text-align: center">
                <div>已批</div><div>{{allCount.questionCode.isMarkCount}}</div>
              </el-col>
              <el-col :span="8" style="text-align: center">
                <div>未批</div><div>{{allCount.questionCode.noMarkCount}}</div>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="8"><strong>验签</strong></el-col>
              <el-col :span="8" style="text-align: center">
                <div>成功</div><div>{{allCount.questionCode.isRightSignCount}}</div>
              </el-col>
              <el-col :span="8"  style="text-align: center">
                <div>失败</div><div>{{allCount.questionCode.noRightSignCount}}</div>
              </el-col>
            </el-row>
          </div>
        </el-col>
        <el-col :span="4">
          <div class="cards">
            <div class="title">总</div>
            <el-row>
              <el-col :span="8"><strong>批阅数</strong></el-col>
              <el-col :span="8" style="text-align: center">
                <div>已批</div><div>{{allCount.questionAll.isMarkCount}}</div>
              </el-col>
              <el-col :span="8" style="text-align: center">
                <div>未批</div><div>{{allCount.questionAll.noMarkCount}}</div>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="8"><strong>验签</strong></el-col>
              <el-col :span="8" style="text-align: center">
                <div>成功</div><div>{{allCount.questionAll.isRightSignCount}}</div>
              </el-col>
              <el-col :span="8"  style="text-align: center">
                <div>失败</div><div>{{allCount.questionAll.noRightSignCount}}</div>
              </el-col>
            </el-row>
          </div>
        </el-col>
      </el-row>
    </div>
    <div class="command-bar" style="height: 6vh;margin-top: 1vh;margin-left: 1%">
      <el-button style="width: 12%;font-size: 18px" type="primary" @click="batchSetRightSign">全部验签</el-button>
      <el-button style="width: 12%;font-size: 18px" type="primary" @click="newLoad">刷新</el-button>
      <el-button style="width: 12%;font-size: 18px" type="primary" @click="submitIsMarks">完成确认</el-button>
      <el-button style="width: 12%;font-size: 18px" type="primary" @click="markStatistic">进入成绩统计</el-button>
      <a style="margin: 2vh 0 0 2%;font-size: 20px;padding-left: 4%">{{examManagers.isMarked==='是'?'完成审批':'未完成审批'}}</a>
    </div>
    <el-tabs class="mark-check-tabs" type="border-card" style="margin-left: 1%;width: 98%;padding: 0">
      <el-tab-pane label="选择题">
        <div class="questions">
          <el-row>
            <el-col :span="6">
              <div class="filter-panel">
                <el-button style="margin-bottom: 1vh;margin-top: 1vh"  class="buttonSize" type="primary"  @click="startFakeAgentMark('multi')">自动批阅选择题</el-button>
                <el-button style="margin-bottom: 1vh" class="buttonSize" type="primary" @click="findAll('multi')">全部</el-button>
                <el-button style="margin-bottom: 1vh" class="buttonSize" type="primary" @click="findIsMark('multi')">已批</el-button>
                <el-button style="margin-bottom: 1vh" class="buttonSize" type="primary" @click="findNoMark('multi')">未批</el-button>
                <el-button style="margin-bottom: 1vh" class="buttonSize" type="primary" @click="findIsRightSign('multi')">验签成功</el-button>
                <el-button class="buttonSize" type="primary" @click="findNoRightSign('multi')">验签失败或未验签</el-button>
              </div>
            </el-col>
            <el-col :span="18">
              <div class="rightMain">
                <el-card shadow="hover" class="cardMain" v-for="(data, index) in questionMulti" :key="index" >
                  <div style="text-align: right;color: #bd0a4f;font-size: 20px">
                    {{data.questionMulti.score}}分
                  </div>
                  <el-row>
                    <el-col :span="2">
                      {{index+1}}、
                    </el-col>
                    <el-col :span="22">
                      <div v-html="data.questionMulti.question" class="w-e-text divs"></div>
                    </el-col>
                    <el-col :span="3" style="text-align: right">
                      A、
                    </el-col>
                    <el-col :span="21">
                      <div v-html="data.questionMulti.answerA" class="w-e-text divs"></div>
                    </el-col>
                    <el-col :span="3"  style="text-align: right">
                      B、
                    </el-col>
                    <el-col :span="21">
                      <div v-html="data.questionMulti.answerB" class="w-e-text divs"></div>
                    </el-col>
                    <el-col :span="3"  style="text-align: right">
                      C、
                    </el-col>
                    <el-col :span="21">
                      <div v-html="data.questionMulti.answerC" class="w-e-text divs"></div>
                    </el-col>
                    <el-col :span="3"  style="text-align: right">
                      D、
                    </el-col>
                    <el-col :span="21">
                      <div v-html="data.questionMulti.answerD" class="w-e-text divs"></div>
                    </el-col>
                  </el-row>
                  <el-row style="margin-top: 1vh">
                    <div style="padding: 0 2% 0 2%;text-align: center;">
                      <el-button disabled v-if="marks(data)" type="text" size="small" style="color: #33ef68;font-size: 20px">批阅完成</el-button>
                      <el-button v-if="!marks(data)" type="text" @click="showAnswer(data)" size="small" style="color: #009dff;font-size: 20px;margin-right: 3%">进行批阅</el-button>
                      <el-button disabled v-if="sign(data)" type="text" size="small" style="color: #33ef68;font-size: 20px">验签完成</el-button>
                      <el-button v-if="!sign(data)" type="text" @click="setRightSign(data)" size="small" style="color: #009dff;font-size: 20px">进行验签</el-button>
                    </div>
                  </el-row>
                </el-card>
              </div>
            </el-col>
          </el-row>
        </div>
      </el-tab-pane>
      <el-tab-pane label="填空题">
        <div class="questions">
          <el-row>
            <el-col :span="6">
              <div class="filter-panel">
                <el-button class="buttonSize" type="primary" @click="findAll('fill')">全部</el-button>
                <el-button class="buttonSize" type="primary" @click="findIsMark('fill')">已批</el-button>
                <el-button class="buttonSize" type="primary" @click="findNoMark('fill')">未批</el-button>
                <el-button class="buttonSize" type="primary" @click="findIsRightSign('fill')">验签成功</el-button>
                <el-button class="buttonSize" type="primary" @click="findNoRightSign('fill')">验签失败或未验签</el-button>
              </div>
            </el-col>
            <el-col :span="18">
              <div class="rightMain">
                <el-card shadow="hover" class="cardMain" v-for="(data, index) in questionFill" :key="index" >
                  <div style="text-align: right;color: #bd0a4f;font-size: 20px">
                    {{data.questionFill.score}}分
                  </div>
                  <el-row>
                    <el-col :span="2" >
                      {{index+1}}、
                    </el-col>
                    <el-col :span="22">
                      <div v-html="data.questionFill.question" class="w-e-text divs"></div>
                    </el-col>
                  </el-row>
                  <el-row style="margin-top: 1vh">
                    <div style="padding: 0 2% 0 2%;text-align: center;">
                      <el-button disabled v-if="sign(data) && marks(data)" type="text" size="small" style="color: #33ef68;font-size: 20px">批阅完成</el-button>
                      <el-button v-if="!sign(data) || !marks(data)" type="text" @click="showAnswer(data)" size="small" style="color: #009dff;font-size: 20px;margin-right: 3%">进行批阅</el-button>
                      <el-button disabled v-if="sign(data)" type="text" size="small" style="color: #33ef68;font-size: 20px">验签完成</el-button>
                      <el-button v-if="!sign(data)" type="text" @click="setRightSign(data)" size="small" style="color: #009dff;font-size: 20px">进行验签</el-button>
                    </div>
                  </el-row>
                </el-card>
              </div>
            </el-col>
          </el-row>
        </div>
      </el-tab-pane>
      <el-tab-pane label="判断题">
        <div class="questions">
          <el-row>
            <el-col :span="6">
              <div class="filter-panel">
                <el-button style="margin-bottom: 1vh;margin-top: 1vh"  class="buttonSize" type="primary"  @click="startFakeAgentMark('judge')">自动批阅判断题</el-button>
                <el-button style="margin-bottom: 1vh" class="buttonSize" type="primary" @click="findAll('judge')">全部</el-button>
                <el-button style="margin-bottom: 1vh" class="buttonSize" type="primary" @click="findIsMark('judge')">已批</el-button>
                <el-button style="margin-bottom: 1vh" class="buttonSize" type="primary" @click="findNoMark('judge')">未批</el-button>
                <el-button style="margin-bottom: 1vh" class="buttonSize" type="primary" @click="findIsRightSign('judge')">验签成功</el-button>
                <el-button class="buttonSize" type="primary" @click="findNoRightSign('judge')">验签失败或未验签</el-button>
              </div>
            </el-col>
            <el-col :span="18">
              <div class="rightMain">
                <el-card shadow="hover" class="cardMain" v-for="(data, index) in questionJudge" :key="index" >
                  <div style="text-align: right;color: #bd0a4f;font-size: 20px">
                    {{data.questionJudge.score}}分
                  </div>
                  <el-row>
                    <el-col :span="2" >
                      {{index+1}}、
                    </el-col>
                    <el-col :span="22">
                      <div v-html="data.questionJudge.question" class="w-e-text divs"></div>
                    </el-col>
                  </el-row>
                  <el-row style="margin-top: 1vh">
                    <div style="padding: 0 2% 0 2%;text-align: center;">
                      <el-button disabled v-if="marks(data)" type="text" size="small" style="color: #33ef68;font-size: 20px">批阅完成</el-button>
                      <el-button v-if="!marks(data)" type="text" @click="showAnswer(data)" size="small" style="color: #009dff;font-size: 20px;margin-right: 3%">进行批阅</el-button>
                      <el-button disabled v-if="sign(data)" type="text" size="small" style="color: #33ef68;font-size: 20px">验签完成</el-button>
                      <el-button v-if="!sign(data)" type="text" @click="setRightSign(data)" size="small" style="color: #009dff;font-size: 20px">进行验签</el-button>
                    </div>
                  </el-row>
                </el-card>
              </div>
            </el-col>
          </el-row>
        </div>
      </el-tab-pane>
      <el-tab-pane label="简答题">
        <div class="questions">
          <el-row>
            <el-col :span="6">
              <div class="filter-panel">
                <el-button class="buttonSize" type="primary" @click="findAll('shortAns')">全部</el-button>
                <el-button class="buttonSize" type="primary" @click="findIsMark('shortAns')">已批</el-button>
                <el-button class="buttonSize" type="primary" @click="findNoMark('shortAns')">未批</el-button>
                <el-button class="buttonSize" type="primary" @click="findIsRightSign('shortAns')">验签成功</el-button>
                <el-button class="buttonSize" type="primary" @click="findNoRightSign('shortAns')">验签失败或未验签</el-button>
              </div>
            </el-col>
            <el-col :span="18">
              <div class="rightMain">
                <el-card shadow="hover" class="cardMain" v-for="(data, index) in questionShortAns" :key="index" >
                  <div style="text-align: right;color: #bd0a4f;font-size: 20px">
                    {{data.questionShortAns.score}}分
                  </div>
                  <el-row>
                    <el-col :span="2" >
                      {{index+1}}、
                    </el-col>
                    <el-col :span="22">
                      <div v-html="data.questionShortAns.question" class="w-e-text divs"></div>
                    </el-col>
                  </el-row>
                  <el-row style="margin-top: 1vh">
                    <div style="padding: 0 2% 0 2%;text-align: center;">
                      <div style="padding: 0 2% 0 2%;text-align: center;">
                        <el-button disabled v-if="marks(data)" type="text" size="small" style="color: #33ef68;font-size: 20px">批阅完成</el-button>
                        <el-button v-if="!marks(data)" type="text" @click="showAnswer(data)" size="small" style="color: #009dff;font-size: 20px;margin-right: 3%">进行批阅</el-button>
                        <el-button disabled v-if="sign(data)" type="text" size="small" style="color: #33ef68;font-size: 20px">验签完成</el-button>
                        <el-button v-if="!sign(data)" type="text" @click="setRightSign(data)" size="small" style="color: #009dff;font-size: 20px">进行验签</el-button>
                      </div>
                    </div>
                  </el-row>
                </el-card>
              </div>
            </el-col>
          </el-row>
        </div>
      </el-tab-pane>
      <el-tab-pane label="代码题">
        <div class="questions">
          <el-row>
            <el-col :span="6">
              <div class="filter-panel">
                <el-button class="buttonSize" type="primary" @click="findAll('code')">全部</el-button>
                <el-button class="buttonSize" type="primary" @click="findIsMark('code')">已批</el-button>
                <el-button class="buttonSize" type="primary" @click="findNoMark('code')">未批</el-button>
                <el-button class="buttonSize" type="primary" @click="findIsRightSign('code')">验签成功</el-button>
                <el-button class="buttonSize" type="primary" @click="findNoRightSign('code')">验签失败或未验签</el-button>
              </div>
            </el-col>
            <el-col :span="18">
              <div class="rightMain">
                <el-card shadow="hover" class="cardMain" v-for="(data, index) in questionCode" :key="index" >
                  <div style="text-align: right;color: #bd0a4f;font-size: 20px">
                    {{data.questionCode.score}}分
                  </div>
                  <el-row>
                    <el-col :span="2" >
                      {{index+1}}、
                    </el-col>
                    <el-col :span="22">
                      <div v-html="data.questionCode.question" class="w-e-text divs"></div>
                    </el-col>
                  </el-row>
                  <el-row style="margin-top: 1vh">
                    <div style="padding: 0 2% 0 2%;text-align: center;">
                      <el-button disabled v-if="marks(data)" type="text" size="small" style="color: #33ef68;font-size: 20px">批阅完成</el-button>
                      <el-button v-if="!marks(data)" type="text" @click="showAnswer(data)" size="small" style="color: #009dff;font-size: 20px;margin-right: 3%">进行批阅</el-button>
                      <el-button disabled v-if="sign(data)" type="text" size="small" style="color: #33ef68;font-size: 20px">验签完成</el-button>
                      <el-button v-if="!sign(data)" type="text" @click="setRightSign(data)" size="small" style="color: #009dff;font-size: 20px">进行验签</el-button>
                    </div>
                  </el-row>
                </el-card>
              </div>
            </el-col>
          </el-row>
        </div>
      </el-tab-pane>
    </el-tabs>
    <el-dialog
        :visible.sync="agentVisible"
        width="420px"
        :show-close="false"
        :close-on-click-modal="false"
        :close-on-press-escape="false"
        custom-class="agent-progress-dialog">
      <div class="agent-progress">
        <div class="agent-progress__eyebrow">智能批阅 Agent</div>
        <h3 class="agent-progress__title">{{ agentTitle }}</h3>
        <p class="agent-progress__desc">{{ agentStage }}</p>
        <el-progress
            :percentage="agentPercent"
            :stroke-width="16"
            :show-text="false"
            color="#0f766e">
        </el-progress>
        <div class="agent-progress__meta">
          <span>当前进度</span>
          <strong>{{ agentPercent }}%</strong>
        </div>
      </div>
    </el-dialog>
    <!--    侧面抽屉-->
    <el-drawer
        title="问题详情"
        :visible.sync="drawer"
        custom-class="demo-drawer"
        direction="rtl"
        size="60%"
        :before-close="handleClose">
      <div class="drawer">
        <div v-if="question.examPaper.questionType==='multi'">
          <div class="title">选择题</div>
          <div class="questionSignal">
            <el-row style="margin: 2vh 2% 2vh 2%;background: #fcfbef;font-size: 26px">
              题目：
              <div style="width: 90%;margin-left: 5%" v-html="question.questionMulti.question" class="w-e-text"></div>
            </el-row>
            <el-row style="margin: 2vh 2% 2vh 2%;background: #fcfbef;font-size: 26px;text-align: center">
              <el-col :span="12">
                标准答案：
                <div style="width: 90%;background: #dde3f3;margin-left: 5%" v-html="question.questionMulti.rightAnswer" class="w-e-text"></div>
              </el-col>
              <el-col :span="12">
                学生答案：
                <div style="width: 90%;background: #dde3f3;margin-left: 5%" v-html="question.answer" class="w-e-text"></div>
              </el-col>
            </el-row>
            <el-row style="margin: 2vh 2% 2vh 2%;background: #fcfbef;font-size: 26px">
              答案详解：
              <div style="width: 90%;margin-left: 5%" v-html="question.questionMulti.analysis" class="w-e-text"></div>
            </el-row>
            <el-row style="margin: 2vh 2% 2vh 2%;background: #fcfbef;font-size: 26px">
              <el-col :span="18">
                <template>
                  <div style="text-align: center">
                    <!-- 动态生成单选框 -->
                    <el-radio
                        v-for="i in question.questionMulti.score + 1"
                        :key="i - 1"
                        v-model="question.markExams"
                        style="text-align: center; margin-top: 2vh; width: 20%; "
                        :label="(i - 1).toString()"
                    >
                      <a style="font-size: 32px;">{{ i - 1 }} 分</a>
                    </el-radio>
                  </div>
                </template>
              </el-col>
              <el-col :span="6">
                <el-button type="text" @click="submitMulti(question)" plain style="width: 70%;font-size: 20px">提交</el-button>
              </el-col>
            </el-row>
          </div>
        </div>
        <div v-if="question.examPaper.questionType==='fill'">
          <div class="title">填空题</div>
          <div class="questionSignal">
            <el-row style="margin: 2vh 2% 2vh 2%;background: #fcfbef;font-size: 26px">
              题目：
              <div style="width: 90%;margin-left: 5%" v-html="question.questionFill.question" class="w-e-text"></div>
            </el-row>
            <el-row style="margin: 2vh 2% 2vh 2%;background: #fcfbef;font-size: 26px;text-align: center">
              <el-col :span="12">
                标准答案：
                <div style="width: 90%;background: #dde3f3;margin-left: 5%" v-html="question.questionFill.answer" class="w-e-text"></div>
              </el-col>
              <el-col :span="12">
                学生答案：
                <div style="width: 90%;background: #dde3f3;margin-left: 5%" v-html="question.answer" class="w-e-text"></div>
              </el-col>
            </el-row>
            <el-row style="margin: 2vh 2% 2vh 2%;background: #fcfbef;font-size: 26px">
              答案详解：
              <div style="width: 90%;margin-left: 5%" v-html="question.questionFill.analysis" class="w-e-text"></div>
            </el-row>
            <el-row style="margin: 2vh 2% 2vh 2%;background: #fcfbef;font-size: 26px">
              <el-col :span="18">
                <template>
                  <div style="text-align: center">
                    <!-- 动态生成单选框 -->
                    <el-radio
                        v-for="i in question.questionFill.score + 1"
                        :key="i - 1"
                        v-model="question.markExams"
                        style="text-align: center; margin-top: 2vh; width: 20%; "
                        :label="(i - 1).toString()"
                    >
                      <a style="font-size: 32px;">{{ i - 1 }} 分</a>
                    </el-radio>
                  </div>
                </template>
              </el-col>
              <el-col :span="6">
                <el-button type="text" @click="submitFill(question)" plain style="width: 70%;font-size: 20px">提交</el-button>
              </el-col>
            </el-row>
          </div>
        </div>
        <div v-if="question.examPaper.questionType==='judge'">
          <div class="title">判断题</div>
          <div class="questionSignal">
            <el-row style="margin: 2vh 2% 2vh 2%;background: #fcfbef;font-size: 26px">
              题目：
              <div style="width: 90%;margin-left: 5%" v-html="question.questionJudge.question" class="w-e-text"></div>
            </el-row>
            <el-row style="margin: 2vh 2% 2vh 2%;background: #fcfbef;font-size: 26px;text-align: center">
              <el-col :span="12">
                标准答案：
                <div style="width: 90%;background: #dde3f3;margin-left: 5%" v-html="question.questionJudge.answer" class="w-e-text"></div>
              </el-col>
              <el-col :span="12">
                学生答案：
                <div style="width: 90%;background: #dde3f3;margin-left: 5%" v-html="question.answer" class="w-e-text"></div>
              </el-col>
            </el-row>
            <el-row style="margin: 2vh 2% 2vh 2%;background: #fcfbef;font-size: 26px">
              答案详解：
              <div style="width: 90%;margin-left: 5%" v-html="question.questionJudge.analysis" class="w-e-text"></div>
            </el-row>
            <el-row style="margin: 2vh 2% 2vh 2%;background: #fcfbef;font-size: 26px">
              <el-col :span="18">
                <template>
                  <div style="text-align: center">
                    <!-- 动态生成单选框 -->
                    <el-radio
                        v-for="i in question.questionJudge.score + 1"
                        :key="i - 1"
                        v-model="question.markExams"
                        style="text-align: center; margin-top: 2vh; width: 20%; "
                        :label="(i - 1).toString()"
                    >
                      <a style="font-size: 32px;">{{ i - 1 }} 分</a>
                    </el-radio>
                  </div>
                </template>
              </el-col>
              <el-col :span="6">
                <el-button type="text" @click="submitJudge(question)" plain style="width: 70%;font-size: 20px">提交</el-button>
              </el-col>
            </el-row>
          </div>
        </div>
        <div v-if="question.examPaper.questionType==='shortAns'">
          <div class="title">简答题</div>
          <div class="questionSignal">
            <el-row style="margin: 2vh 2% 2vh 2%;background: #fcfbef;font-size: 26px">
              题目：
              <div style="width: 90%;margin-left: 5%" v-html="question.questionShortAns.question" class="w-e-text"></div>
            </el-row>
            <el-row style="margin: 2vh 2% 2vh 2%;background: #fcfbef;font-size: 26px;text-align: center">
              <el-col :span="12">
                标准答案：
                <div style="width: 90%;background: #dde3f3;margin-left: 5%" v-html="question.questionShortAns.answer" class="w-e-text"></div>
              </el-col>
              <el-col :span="12">
                学生答案：
                <div style="width: 90%;background: #dde3f3;margin-left: 5%" v-html="question.answer" class="w-e-text"></div>
              </el-col>
            </el-row>
            <el-row style="margin: 2vh 2% 2vh 2%;background: #fcfbef;font-size: 26px">
              答案详解：
              <div style="width: 90%;margin-left: 5%" v-html="question.questionShortAns.analysis" class="w-e-text"></div>
            </el-row>
            <el-row style="margin: 2vh 2% 2vh 2%;background: #fcfbef;font-size: 26px">
              <el-col :span="18">
                <template>
                  <div style="text-align: center">
                    <!-- 动态生成单选框 -->
                    <el-radio
                        v-for="i in question.questionShortAns.score + 1"
                        :key="i - 1"
                        v-model="question.markExams"
                        style="text-align: center; margin-top: 2vh; width: 20%; "
                        :label="(i - 1).toString()"
                    >
                      <a style="font-size: 32px;">{{ i - 1 }} 分</a>
                    </el-radio>
                  </div>
                </template>
              </el-col>
              <el-col :span="6">
                <el-button type="text" @click="submitShortAns(question)" plain style="width: 70%;font-size: 20px">提交</el-button>
              </el-col>
            </el-row>
          </div>
        </div>
        <div v-if="question.examPaper.questionType==='code'">
          <div class="title">代码题</div>
          <div class="questionSignal">
            <el-row style="margin: 2vh 2% 2vh 2%;background: #fcfbef;font-size: 26px">
              题目：
              <div style="width: 90%;margin-left: 5%" v-html="question.questionCode.question" class="w-e-text"></div>
            </el-row>
            <el-row style="margin: 2vh 2% 2vh 2%;background: #fcfbef;font-size: 26px;text-align: center">
              <el-col :span="12">
                标准答案：
                <monaco-editor
                    v-model="question.questionCode.answer"
                    language="c"
                    :options="{ readOnly: true, fontSize: '18px' }"
                    style="margin-top:2vh;height: 30vh;text-align: left"
                ></monaco-editor>
              </el-col>
              <el-col :span="12">
                学生答案：
                <monaco-editor
                    v-model="question.answer"
                    language="c"
                    :options="{ readOnly: true, fontSize: '18px' }"
                    style="margin-top:2vh;height: 30vh;text-align: left"
                ></monaco-editor>
              </el-col>
            </el-row>
            <el-row style="margin: 2vh 2% 2vh 2%;background: #fcfbef;font-size: 26px">
              答案详解：
              <div style="width: 90%;margin-left: 5%" v-html="question.questionCode.analysis" class="w-e-text"></div>
            </el-row>
            <el-row style="margin: 2vh 2% 2vh 2%;background: #fcfbef;font-size: 26px">
              <el-col :span="18">
                <template>
                  <div style="text-align: center">
                    <!-- 动态生成单选框 -->
                    <el-radio
                        v-for="i in question.questionCode.score + 1"
                        :key="i - 1"
                        v-model="question.markExams"
                        style="text-align: center; margin-top: 2vh; width: 20%; "
                        :label="(i - 1).toString()"
                    >
                      <a style="font-size: 32px;">{{ i - 1 }} 分</a>
                    </el-radio>
                  </div>
                </template>
              </el-col>
              <el-col :span="6">
                <el-button type="text" @click="submitCode(question)" plain style="width: 70%;font-size: 20px">提交</el-button>
              </el-col>
            </el-row>
          </div>
        </div>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import request from "@/utils/request"
import MonacoEditor from "monaco-editor-vue";
export default {
  components: {
    MonacoEditor,
  },
  data(){
    return{
      user:localStorage.getItem("user") ? (JSON).parse(localStorage.getItem("user")) : {},
      form:{},
      tableData: [],
      pageNum: 1,   // 当前的页码
      pageSize: 10,  // 每页显示的个数
      total: 0,
      course:[],
      ids:"",
      multipleSelection:"",
      params:{
        pageNum:1,
        pageSize:10,
      },
      editorVisible:false,//富文本显示
      viewData:"",//富文本数据
      dialogFormVisible:false,//表单显示
      receivedData:"",
      questionType:[
        {name:"选择题",value:"multi"},
        {name:"判断题",value:"judge"},
        {name:"填空题",value:"fill"},
        {name:"简批阅题",value:"shortAns"},
        {name:"代码题",value:"code"},
      ],
      allCount:{},
      questionCode: [],
      questionFill: [],
      questionMulti: [],
      questionShortAns: [],
      questionJudge: [],
      drawer:false,
      agentVisible:false,
      agentPercent:0,
      agentStage:"",
      agentTitle:"",
      agentTimer:null,
      question:{
        examPaper:[]
      },
      examManagers:{}
    }
  },
  created() {
    this.receivedData = this.$route.params;
    this.findExamManager()
    this.findQuestion()
    this.findAllCount()
  },
  mounted() {

  },
  beforeDestroy() {
    this.stopFakeAgentMark()
  },
  methods: {
    startFakeAgentMark(questionType) {
      if (this.agentVisible) {
        return
      }
      const configMap = {
        multi: {
          title: "Agent 正在批阅选择题",
          action: () => this.changeMultiAll()
        },
        judge: {
          title: "Agent 正在批阅判断题",
          action: () => this.changeJudgeAll()
        }
      }
      const config = configMap[questionType]
      if (!config) {
        return
      }
      const stages = [
        "正在解析题目结构与标准答案...",
        "正在抽取待批阅题目并建立任务队列...",
        "正在模拟智能体分批阅卷...",
        "正在整理批阅结果并准备回写...",
        "即将完成，正在同步最终状态..."
      ]
      this.stopFakeAgentMark()
      this.agentVisible = true
      this.agentPercent = 0
      this.agentTitle = config.title
      this.agentStage = stages[0]
      let stageIndex = 0
      this.agentTimer = setInterval(() => {
        const step = Math.floor(Math.random() * 8) + 6
        this.agentPercent = Math.min(this.agentPercent + step, 100)
        const nextStage = Math.min(Math.floor(this.agentPercent / 22), stages.length - 1)
        if (nextStage !== stageIndex) {
          stageIndex = nextStage
          this.agentStage = stages[stageIndex]
        }
        if (this.agentPercent >= 100) {
          this.stopFakeAgentMark()
          this.agentStage = "智能体批阅完成，正在刷新结果..."
          setTimeout(() => {
            this.agentVisible = false
            config.action()
          }, 500)
        }
      }, 260)
    },
    stopFakeAgentMark() {
      if (this.agentTimer) {
        clearInterval(this.agentTimer)
        this.agentTimer = null
      }
    },
    findExamManager(){
      request.get("exam/examManage/selectById/"+this.receivedData.id).then(res=>{
        if (res.code === '200') {
          this.examManagers=res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    findAllCount(){
      let examId;
      examId=this.receivedData.id
      request.get('/statistic/selectExamCount/selectExamAnswer', {
        params: {
          examId,
        }
      }).then(res => {
        if (res.code === '200') {
          // 处理成功响应
          // 假设你想将结果存储在 this.allCount 中
          this.allCount = res.data;
        } else {
          // 处理失败响应
          this.$message.error(res.msg);
        }
      })
    },
    newLoad(){
      this.findQuestion()
      this.findAllCount()
      this.$message.success("成功")
    },
    findQuestion() {
      this.findQuestionFill();
      this.findQuestionCode();
      this.findQuestionJudge();
      this.findQuestionMulti();
      this.findQuestionShortAns();
    },
    findQuestionCode() {
      let params;
      params = {
        examId: this.receivedData.id,
        questionType: "code"
      };
      request.get('/exam/examAnswer/selectAll', {
        params: params
      }).then(res => {
        if (res.code === '200') {
          this.questionCode = res.data;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findQuestionFill() {
      let params;
      params = {
        examId: this.receivedData.id,
        questionType: "fill"
      };
      request.get('/exam/examAnswer/selectAll', {
        params: params
      }).then(res => {
        if (res.code === '200') {
          this.questionFill = res.data;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findQuestionJudge() {
      let params;
      params = {
        examId: this.receivedData.id,
        questionType: "judge"
      };
      request.get('/exam/examAnswer/selectAll', {
        params: params
      }).then(res => {
        if (res.code === '200') {
          this.questionJudge = res.data;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findQuestionMulti() {
      let params;
      params = {
        examId: this.receivedData.id,
        questionType: "multi"
      };
      request.get('/exam/examAnswer/selectAll', {
        params: params
      }).then(res => {
        if (res.code === '200') {
          this.questionMulti = res.data;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findQuestionShortAns() {
      let params;
      params = {
        examId: this.receivedData.id,
        questionType: "shortAns"
      };
      request.get('/exam/examAnswer/selectAll', {
        params: params
      }).then(res => {
        if (res.code === '200') {
          this.questionShortAns = res.data;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    marks(data) {
      // 检查 data 对象中 mark_exams 属性是否为空
      if (!data.markExams || data.markExams==='') {
        return false;
      }
      return true;
    },
    sign(data) {
      // 检查 data 对象中 right_sign 属性是否为空
      if (!data.rightSign ||data.rightSign!=='验签成功') {
        return false;
      }
      return true;
    },
    findIsMark(questionType){
      let params;
      params = {
        examId: this.receivedData.id,
        questionType: questionType
      };
      request.get('/exam/examAnswer/selectIsMark', {
        params: params
      }).then(res => {
        if (res.code === '200') {
          if(questionType==='multi'){
            this.questionMulti = res.data;
          }
          if(questionType==='fill'){
            this.questionFill = res.data;
          }
          if(questionType==='judge'){
            this.questionJudge = res.data;
          }
          if(questionType==='shortAns'){
            this.questionShortAns = res.data;
          }
          if(questionType==='code'){
            this.questionCode = res.data;
          }
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findNoMark(questionType){
      let params;
      params = {
        examId: this.receivedData.id,
        questionType: questionType
      };
      request.get('/exam/examAnswer/selectNoMark', {
        params: params
      }).then(res => {
        if (res.code === '200') {
          if(questionType==='multi'){
            this.questionMulti = res.data;
          }
          if(questionType==='fill'){
            this.questionFill = res.data;
          }
          if(questionType==='judge'){
            this.questionJudge = res.data;
          }
          if(questionType==='shortAns'){
            this.questionShortAns = res.data;
          }
          if(questionType==='code'){
            this.questionCode = res.data;
          }
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findIsRightSign(questionType){
      let params;
      params = {
        examId: this.receivedData.id,
        questionType: questionType
      };
      request.get('/exam/examAnswer/selectIsRightSign', {
        params: params
      }).then(res => {
        if (res.code === '200') {
          if(questionType==='multi'){
            this.questionMulti = res.data;
          }
          if(questionType==='fill'){
            this.questionFill = res.data;
          }
          if(questionType==='judge'){
            this.questionJudge = res.data;
          }
          if(questionType==='shortAns'){
            this.questionShortAns = res.data;
          }
          if(questionType==='code'){
            this.questionCode = res.data;
          }
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findNoRightSign(questionType){
      let params;
      params = {
        examId: this.receivedData.id,
        questionType: questionType
      };
      request.get('/exam/examAnswer/selectNoRightSign', {
        params: params
      }).then(res => {
        if (res.code === '200') {
          if(questionType==='multi'){
            this.questionMulti = res.data;
          }
          if(questionType==='fill'){
            this.questionFill = res.data;
          }
          if(questionType==='judge'){
            this.questionJudge = res.data;
          }
          if(questionType==='shortAns'){
            this.questionShortAns = res.data;
          }
          if(questionType==='code'){
            this.questionCode = res.data;
          }
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    findAll(questionType){
      let params;
      params = {
        examId: this.receivedData.id,
        questionType: questionType
      };
      request.get('/exam/examAnswer/selectAll', {
        params: params
      }).then(res => {
        if (res.code === '200') {
          if(questionType==='multi'){
            this.questionMulti = res.data;
          }
          if(questionType==='fill'){
            this.questionFill = res.data;
          }
          if(questionType==='judge'){
            this.questionJudge = res.data;
          }
          if(questionType==='shortAns'){
            this.questionShortAns = res.data;
          }
          if(questionType==='code'){
            this.questionCode = res.data;
          }
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
    },
    showAnswer(data){
      this.question=data
      this.drawer=true
    },
    changeMultiAll(){
      let params;
      params = {
        examId: this.receivedData.id,
        questionType: 'multi'
      };
      request.get('/exam/examAnswer/selectNoMark', {
        params: params
      }).then(res => {
        if (res.code === '200') {
          let question;
          question=res.data
          if(question.length===0){
            this.$message.warning("题目全部批阅完成，无需批阅")
          }
          else {
            this.changeMultiAllCh(question)
          }
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    changeMultiAllCh(data){
      let params;
      params=data
      request.put("exam/examAnswer/updateByMultiAllAnswer",params).then(res=>{
        if (res.code === '200') {
          this.$message.success("阅卷成功")
          this.findAllCount()
          this.findAll("multi")
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    changeJudgeAll(){
      let params;
      params = {
        examId: this.receivedData.id,
        questionType: 'judge'
      };
      request.get('/exam/examAnswer/selectNoMark', {
        params: params
      }).then(res => {
        if (res.code === '200') {
          let question;
          question=res.data
          if(question.length===0){
            this.$message.warning("题目全部批阅完成，无需批阅")
          }
          else {
            this.changeJudgeAllCh(question)
          }
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    changeJudgeAllCh(data){
      let params;
      params=data
      request.put("exam/examAnswer/updateByJudgeAllAnswer",params).then(res=>{
        if (res.code === '200') {
          this.$message.success("阅卷成功")
          this.findAllCount()
          this.findAll("judge")
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    submitMulti(data){
      let params;
      params={
        id:data.id,
        markExams:data.markExams,
        teacherId:this.user.id,
        examId:this.receivedData.id
      }
      request.put("exam/examAnswer/updateByScore",params).then(res=>{
        if (res.code === '200') {
          this.$message.success("成功阅卷")
          this.findAll("multi")
          this.findAllCount()
          this.drawer = false;
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    submitJudge(data){
      let params;
      params={
        id:data.id,
        markExams:data.markExams,
        teacherId:this.user.id,
        examId:this.receivedData.id
      }
      request.put("exam/examAnswer/updateByScore",params).then(res=>{
        if (res.code === '200') {
          this.$message.success("成功阅卷")
          this.findAll("judge")
          this.findAllCount()
          this.drawer = false;
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    submitFill(data){
      let params;
      params={
        id:data.id,
        markExams:data.markExams,
        teacherId:this.user.id,
        examId:this.receivedData.id
      }
      request.put("exam/examAnswer/updateByScore",params).then(res=>{
        if (res.code === '200') {
          this.$message.success("成功阅卷")
          this.findAll("fill")
          this.findAllCount()
          this.drawer = false;
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    submitShortAns(data){
      let params;
      params={
        id:data.id,
        markExams:data.markExams,
        teacherId:this.user.id,
        examId:this.receivedData.id
      }
      request.put("exam/examAnswer/updateByScore",params).then(res=>{
        if (res.code === '200') {
          this.$message.success("成功阅卷")
          this.findAll("shortAns")
          this.findAllCount()
          this.drawer = false;
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    submitCode(data){
      let params;
      params={
        id:data.id,
        markExams:data.markExams,
        teacherId:this.user.id,
        examId:this.receivedData.id
      }
      request.put("exam/examAnswer/updateByScore",params).then(res=>{
        if (res.code === '200') {
          this.$message.success("成功阅卷")
          this.findAll("code")
          this.findAllCount()
          this.drawer = false;
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    batchSetRightSign(){
      let params;
      params = {
        examId: this.receivedData.id,
      };
      request.get('/exam/examAnswer/selectNoRightSign', {
        params: params
      }).then(res => {
        if (res.code === '200') {
          let question;
          question=res.data
          if(question.length===0){
            this.$message.warning("验签全部完成，无需验签")
          }
          else {
            this.batchSetRightSignAn(question)
          }
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    batchSetRightSignAn(question){
      let params;
      params=question
      request.put("exam/examAnswer/batchSetRightSign",params).then(res=>{
        if (res.code === '200') {
          this.$message.success("签名成功")
          this.findAllCount()
          this.findQuestion()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    setRightSign(question){
      request.put("exam/examAnswer/setRightSign",question).then(res=>{
        if (res.code === '200') {
          this.$message.success("签名成功")
          this.findAllCount()
          this.findAll(question.examPaper.questionType)

        } else {
          this.$message.error(res.msg)
        }
      })
    },
    submitIsMarks(){
      let questionAll;
      questionAll=this.allCount.questionAll
      if(questionAll.noMarkCount!==0){
        this.$message.warning("还有题目未批阅")
      }
      else if(questionAll.noRightSignCount!==0){
        this.$message.warning("还有签名未验签")
      }
      else if(this.examManagers.isMarked==='是'){
        this.$message.warning("完成标记，无需再次标记")
      }
      else{
        this.form.id=this.receivedData.id
        this.form.isMarked="是"
        request.put("exam/examManage/update",this.form).then(res=>{
          if (res.code === '200') {
            this.$message.success("成功")
          } else {
            this.$message.error(res.msg)
          }
        })
      }
    },
    markStatistic(){
      if(this.examManagers.isMarked==='是'){
        this.$router.push({
          name: "teacherMarkStatistics",
          params: {
            id:this.receivedData.id
          }
        });
      }
      else{
        this.$message.warning("请先进行标记")
      }
    }
  }
}
</script>


<style scoped>
.main{
  padding: 4px 6px 0;
  color: #1f2937;
}

.overview-grid {
  margin-bottom: 8px;
}

.cards{
  margin: 10px 5% 12px;
  padding: 16px 16px 14px;
  min-height: 156px;
  width: 90%;
  border-radius: 18px;
  border: 1px solid rgba(218, 226, 237, 0.92);
  background:
    linear-gradient(180deg, rgba(255, 255, 255, 0.98) 0%, rgba(243, 250, 253, 0.95) 100%),
    radial-gradient(circle at top left, rgba(125, 211, 252, 0.12), transparent 42%);
  box-shadow: 0 14px 30px rgba(148, 163, 184, 0.16);
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.cards:hover{
  transform: translateY(-2px);
  box-shadow: 0 18px 38px rgba(148, 163, 184, 0.2);
}

.title{
  font-size: 22px;
  font-weight: 700;
  color: #123047;
  text-align: center;
}

.cards .title {
  padding-bottom: 12px;
}

.questions{
  height: 54vh;
}

.buttonSize{
  margin: 0 !important;
  width: 100% !important;
  min-height: 44px;
  font-size: 16px !important;
  font-weight: 700;
  border-radius: 14px;
}

.command-bar {
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  gap: 12px;
  min-height: auto !important;
  margin: 12px 1% 14px !important;
  padding: 0;
}

.command-bar .el-button {
  width: auto !important;
  min-width: 132px;
  height: 42px;
  margin: 0 !important;
  padding: 0 18px;
  border-radius: 12px;
  font-size: 15px !important;
  font-weight: 700;
  box-shadow: 0 10px 20px rgba(15, 118, 110, 0.12);
}

.command-bar a {
  display: inline-flex;
  align-items: center;
  min-height: 42px;
  margin: 0 0 0 8px !important;
  padding: 0 16px !important;
  border-radius: 999px;
  background: rgba(15, 23, 42, 0.04);
  color: #334155;
  font-size: 17px !important;
  font-weight: 700;
}

.mark-check-tabs {
  margin-left: 1% !important;
  width: 98% !important;
}

.mark-check-tabs:deep(.el-tabs__header) {
  margin: 0;
  border-bottom: 1px solid #e6ecf3;
  background: linear-gradient(180deg, #fbfdff 0%, #f6f8fb 100%);
}

.mark-check-tabs:deep(.el-tabs__nav-wrap) {
  padding: 0 16px;
}

.mark-check-tabs:deep(.el-tabs__item) {
  height: 52px;
  line-height: 52px;
  font-size: 15px;
  font-weight: 700;
  color: #64748b;
}

.mark-check-tabs:deep(.el-tabs__item.is-active) {
  color: #0f766e;
}

.mark-check-tabs:deep(.el-tabs__content) {
  padding: 14px 16px 18px;
  background: #f8fafc;
}

.mark-check-tabs:deep(.el-tabs--border-card) {
  border: 1px solid rgba(223, 229, 237, 0.96);
  border-radius: 22px;
  overflow: hidden;
  box-shadow: 0 14px 34px rgba(148, 163, 184, 0.14);
}

.mark-check-tabs:deep(.el-tab-pane > .questions > .el-row > .el-col:first-child > div) {
  display: grid;
  gap: 12px;
  padding: 16px;
  border-radius: 20px;
  border: 1px solid rgba(223, 229, 237, 0.96);
  background: linear-gradient(180deg, #ffffff 0%, #f8fbff 100%);
  box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.8);
}

.filter-panel {
  display: grid;
  gap: 12px;
  padding: 16px;
  border-radius: 20px;
  border: 1px solid rgba(223, 229, 237, 0.96);
  background:
    linear-gradient(180deg, rgba(255, 255, 255, 0.98) 0%, rgba(247, 250, 252, 0.96) 100%),
    radial-gradient(circle at top left, rgba(191, 219, 254, 0.16), transparent 38%);
  box-shadow: 0 12px 28px rgba(148, 163, 184, 0.12);
}

.mark-check-tabs:deep(.el-tab-pane > .questions > .el-row > .el-col:first-child .el-button) {
  margin: 0 !important;
}

.rightMain{
  margin: 0 0 0 16px;
  padding: 18px;
  height: 52vh;
  width: calc(100% - 16px);
  border-radius: 22px;
  border: 1px solid rgba(223, 229, 237, 0.96);
  background:
    linear-gradient(180deg, rgba(255, 255, 255, 0.98) 0%, rgba(243, 250, 253, 0.96) 100%),
    radial-gradient(circle at top right, rgba(165, 243, 252, 0.14), transparent 34%);
  overflow-y: auto;
  box-sizing: border-box;
}

.rightMain::-webkit-scrollbar,
.drawer::-webkit-scrollbar {
  width: 7px;
}

.rightMain::-webkit-scrollbar-thumb,
.drawer::-webkit-scrollbar-thumb {
  background: rgba(148, 163, 184, 0.58);
  border-radius: 999px;
}

.cardMain{
  margin: 0 0 16px;
  padding: 16px 18px;
  width: 100%;
  border-radius: 18px;
  border: 1px solid rgba(218, 226, 237, 0.92);
  background: linear-gradient(180deg, #ffffff 0%, #f8fbff 100%);
  box-shadow: 0 12px 28px rgba(148, 163, 184, 0.12);
  box-sizing: border-box;
}

.cardMain:last-child {
  margin-bottom: 0;
}

.drawer{
  margin: 12px 16px 16px;
  padding: 16px;
  width: auto;
  height: calc(100vh - 110px);
  border-radius: 22px;
  border: 1px solid rgba(223, 229, 237, 0.96);
  background:
    linear-gradient(180deg, rgba(255, 255, 255, 0.98) 0%, rgba(248, 250, 252, 0.96) 100%),
    linear-gradient(135deg, rgba(219, 234, 254, 0.16), transparent 36%);
  overflow-y: auto;
  box-sizing: border-box;
}

.main :deep(.el-button--primary) {
  background: linear-gradient(135deg, #0f766e, #0d9488);
  border-color: #0f766e;
}

.main :deep(.el-button--primary:hover),
.main :deep(.el-button--primary:focus) {
  background: linear-gradient(135deg, #0b5d57, #0f766e);
  border-color: #0b5d57;
}

.main :deep(.cards .el-row) {
  margin-top: 12px;
  color: #475569;
}

.main :deep(.cards strong) {
  color: #0f172a;
  font-size: 15px;
}

.main :deep(.cards .el-col[style*='text-align: center']) {
  color: #334155;
  font-weight: 600;
}

.main :deep(.cards .el-col[style*='text-align: center'] div:first-child) {
  font-size: 13px;
  color: #64748b;
}

.main :deep(.cards .el-col[style*='text-align: center'] div:last-child) {
  margin-top: 4px;
  font-size: 26px;
  font-weight: 800;
  color: #0f172a;
}

.main :deep(.cards .el-row + .el-row) {
  margin-top: 14px;
  padding-top: 12px;
  border-top: 1px solid rgba(226, 232, 240, 0.92);
}

.main :deep(.cardMain .el-button--text) {
  padding: 8px 14px !important;
  border-radius: 999px;
  background: rgba(15, 118, 110, 0.08);
  color: #0f766e !important;
  font-size: 15px !important;
  font-weight: 700;
}

.main :deep(.cardMain .el-button--text.is-disabled) {
  background: rgba(34, 197, 94, 0.12);
  color: #15803d !important;
}

.main :deep(.cardMain .el-button--text + .el-button--text) {
  margin-left: 12px !important;
}

.main :deep(.cardMain > div[style*='text-align: right']) {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  min-width: 58px;
  padding: 6px 10px;
  border-radius: 999px;
  background: rgba(225, 29, 72, 0.08);
  color: #e11d48 !important;
  font-size: 15px !important;
  font-weight: 800;
  margin-left: auto;
}

.main :deep(.cardMain .el-row:last-child) {
  margin-top: 14px !important;
  padding-top: 14px;
  border-top: 1px solid rgba(226, 232, 240, 0.92);
}

.main :deep(.demo-drawer) {
  background: #f4f7fb;
}

.main :deep(.demo-drawer .el-drawer__header) {
  margin-bottom: 0;
  padding: 18px 22px 0;
  color: #0f172a;
  font-size: 20px;
  font-weight: 700;
}

.main :deep(.demo-drawer .el-drawer__body) {
  padding: 0;
}

.main :deep(.agent-progress-dialog) {
  border-radius: 22px;
  overflow: hidden;
}

.main :deep(.agent-progress-dialog .el-dialog__header) {
  display: none;
}

.main :deep(.agent-progress-dialog .el-dialog__body) {
  padding: 0;
}

.agent-progress {
  padding: 26px 24px 22px;
  background:
    linear-gradient(180deg, rgba(255, 255, 255, 0.98) 0%, rgba(246, 250, 252, 0.96) 100%),
    radial-gradient(circle at top right, rgba(45, 212, 191, 0.18), transparent 38%);
}

.agent-progress__eyebrow {
  display: inline-flex;
  align-items: center;
  min-height: 28px;
  padding: 0 12px;
  border-radius: 999px;
  background: rgba(15, 118, 110, 0.1);
  color: #0f766e;
  font-size: 13px;
  font-weight: 700;
}

.agent-progress__title {
  margin: 16px 0 8px;
  font-size: 24px;
  line-height: 1.35;
  color: #0f172a;
}

.agent-progress__desc {
  margin: 0 0 18px;
  font-size: 14px;
  line-height: 1.7;
  color: #475569;
}

.agent-progress__meta {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-top: 14px;
  color: #475569;
  font-size: 14px;
}

.agent-progress__meta strong {
  color: #0f766e;
  font-size: 18px;
}

.main :deep(.w-e-text) {
  line-height: 1.75;
  color: #334155;
}

@media (max-width: 1200px) {
  .command-bar .el-button {
    min-width: 116px;
  }

  .rightMain {
    margin-left: 12px;
    width: calc(100% - 12px);
  }
}
</style>

