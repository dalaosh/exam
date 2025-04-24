package com.mc.esstatistic.service.impl;

import com.mc.escommon.entity.exam.ExamManage;
import com.mc.escommon.entity.exam.ExamScore;
import com.mc.esstatistic.mapper.SelectBigEChartMapper;
import com.mc.esstatistic.service.SelectBigEChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author machao
 * * @datetime 2025/3/19 19:53
 * * inform
 */
@Service
public class SelectBigEChartServiceImpl implements SelectBigEChartService {
    @Autowired
    private SelectBigEChartMapper selectBigEChartMapper;

    /**
     * 基本信息
     */
    @Override
    public Map<String, Integer> selectBasicNum() {
        Integer courseNum=selectBigEChartMapper.selectCourseNum();
        Integer examNum=selectBigEChartMapper.selectExamNum();
        List<ExamScore> selectScoreList=selectBigEChartMapper.selectScore();
        int selectS=0;
        Integer selectSS=selectScoreList.size();
        for (ExamScore examScore:selectScoreList){
            if(examScore.getExamManage().getTotalScore()*0.6<=examScore.getScore()){
                selectS+=1;
            }
        }
        int s=selectS*100/selectSS;
        Integer questionCodeNum=selectBigEChartMapper.selectQuestionCodeNum();
        Integer questionMultiNum=selectBigEChartMapper.selectQuestionMultiNum();
        Integer questionFillNum=selectBigEChartMapper.selectQuestionFillNum();
        Integer questionJudgeNum=selectBigEChartMapper.selectQuestionJudgeNum();
        Integer questionAnswerNum=selectBigEChartMapper.selectQuestionShortAnsNum();
        Integer questionAll=questionCodeNum+questionMultiNum+questionFillNum+questionAnswerNum+questionJudgeNum;
        Map<String, Integer> resultMap=new HashMap<>();
        resultMap.put("courseNum",courseNum);
        resultMap.put("examNum",examNum);
        resultMap.put("questionAll",questionAll);
        resultMap.put("s",s);
        return resultMap;
    }

    /**
     * @return
     */
    @Override
    public Map<String, Map<String, Integer>> selectQuestionNum() {
        //查询代码题
        Map<String, Integer> questionCodeType = new HashMap<>();
        Integer questionCodeOne=selectBigEChartMapper.selectQuestionCodeNumLevel("1");
        Integer questionCodeTwo=selectBigEChartMapper.selectQuestionCodeNumLevel("2");
        Integer questionCodeThree=selectBigEChartMapper.selectQuestionCodeNumLevel("3");
        Integer questionCodeFour=selectBigEChartMapper.selectQuestionCodeNumLevel("4");
        Integer questionCodeFive=selectBigEChartMapper.selectQuestionCodeNumLevel("5");
        questionCodeType.put("questionCodeOne",questionCodeOne);
        questionCodeType.put("questionCodeTwo",questionCodeTwo);
        questionCodeType.put("questionCodeThree",questionCodeThree);
        questionCodeType.put("questionCodeFour",questionCodeFour);
        questionCodeType.put("questionCodeFive",questionCodeFive);

        Map<String, Integer> questionFillType = new HashMap<>();
        Integer questionFillOne=selectBigEChartMapper.selectQuestionFillNumLevel("1");
        Integer questionFillTwo=selectBigEChartMapper.selectQuestionFillNumLevel("2");
        Integer questionFillThree=selectBigEChartMapper.selectQuestionFillNumLevel("3");
        Integer questionFillFour=selectBigEChartMapper.selectQuestionFillNumLevel("4");
        Integer questionFillFive=selectBigEChartMapper.selectQuestionFillNumLevel("5");
        questionFillType.put("questionFillOne",questionFillOne);
        questionFillType.put("questionFillTwo",questionFillTwo);
        questionFillType.put("questionFillThree",questionFillThree);
        questionFillType.put("questionFillFour",questionFillFour);
        questionFillType.put("questionFillFive",questionFillFive);

        Map<String, Integer> questionJudgeType = new HashMap<>();
        Integer questionJudgeOne=selectBigEChartMapper.SelectQuestionJudgeNumLevel("1");
        Integer questionJudgeTwo=selectBigEChartMapper.SelectQuestionJudgeNumLevel("2");
        Integer questionJudgeThree=selectBigEChartMapper.SelectQuestionJudgeNumLevel("3");
        Integer questionJudgeFour=selectBigEChartMapper.SelectQuestionJudgeNumLevel("4");
        Integer questionJudgeFive=selectBigEChartMapper.SelectQuestionJudgeNumLevel("5");
        questionJudgeType.put("questionJudgeOne",questionJudgeOne);
        questionJudgeType.put("questionJudgeTwo",questionJudgeTwo);
        questionJudgeType.put("questionJudgeThree",questionJudgeThree);
        questionJudgeType.put("questionJudgeFour",questionJudgeFour);
        questionJudgeType.put("questionJudgeFive",questionJudgeFive);

        Map<String, Integer> questionMultiType = new HashMap<>();
        Integer questionMultiOne=selectBigEChartMapper.SelectQuestionMultiNumLevel("1");
        Integer questionMultiTwo=selectBigEChartMapper.SelectQuestionMultiNumLevel("2");
        Integer questionMultiThree=selectBigEChartMapper.SelectQuestionMultiNumLevel("3");
        Integer questionMultiFour=selectBigEChartMapper.SelectQuestionMultiNumLevel("4");
        Integer questionMultiFive=selectBigEChartMapper.SelectQuestionMultiNumLevel("5");
        questionMultiType.put("questionMultiOne",questionMultiOne);
        questionMultiType.put("questionMultiTwo",questionMultiTwo);
        questionMultiType.put("questionMultiThree",questionMultiThree);
        questionMultiType.put("questionMultiFour",questionMultiFour);
        questionMultiType.put("questionMultiFive",questionMultiFive);

        Map<String, Integer> questionShortAnsType = new HashMap<>();
        Integer questionShortAnsOne=selectBigEChartMapper.SelectQuestionShortAnsNumLevel("1");
        Integer questionShortAnsTwo=selectBigEChartMapper.SelectQuestionShortAnsNumLevel("2");
        Integer questionShortAnsThree=selectBigEChartMapper.SelectQuestionShortAnsNumLevel("3");
        Integer questionShortAnsFour=selectBigEChartMapper.SelectQuestionShortAnsNumLevel("4");
        Integer questionShortAnsFive=selectBigEChartMapper.SelectQuestionShortAnsNumLevel("5");
        questionShortAnsType.put("questionShortAnsOne",questionShortAnsOne);
        questionShortAnsType.put("questionShortAnsTwo",questionShortAnsTwo);
        questionShortAnsType.put("questionShortAnsThree",questionShortAnsThree);
        questionShortAnsType.put("questionShortAnsFour",questionShortAnsFour);
        questionShortAnsType.put("questionShortAnsFive",questionShortAnsFive);

        Map<String, Map<String, Integer>> resultMap = new HashMap<>();
        resultMap.put("questionCodeType", questionCodeType);
        resultMap.put("questionFillType", questionFillType);
        resultMap.put("questionJudgeType", questionJudgeType);
        resultMap.put("questionMultiType", questionMultiType);
        resultMap.put("questionShortAnsType", questionShortAnsType);
        return resultMap;
    }

    /**
     * @return
     */
    @Override
    public List<ExamManage> selectExamAll() {
        List<ExamManage> examManageList = selectBigEChartMapper.selectExamAll();
        return examManageList;
    }

    /**
     * @return
     */
    @Override
    public Map<String, Integer> selectStrongAll() {
        // 初始化有序结果容器（使用LinkedHashMap保持顺序）
        Map<String, Integer> resultMap = new LinkedHashMap<String, Integer>() {{
            put("优秀", 0);    // 90-100
            put("良好", 0);    // 80-89
            put("中等", 0);    // 70-79
            put("及格", 0);    // 60-69
            put("不及格", 0);  // <60
            put("有效数据", 0);  // 有效数据计数
            put("无效数据", 0);  // 异常数据计数
            put("总人数", 0);   // 总人数统计
        }};

        try {
            // 1. 查询数据库获取原始数据
            List<ExamScore> scoreList = selectBigEChartMapper.selectScore();

            // 2. 遍历处理每条记录
            for (ExamScore examScore : scoreList) {
                // 总人数递增
                resultMap.compute("总人数", (k, v) -> v + 1);

                Integer scoreOld = examScore.getScore();
                Integer All=examScore.getExamManage().getTotalScore();
                Integer score=scoreOld*100/All;

                // 3. 数据有效性校验
                if (score == null || score < 0 || score > 100) {
                    resultMap.compute("不及格", (k, v) -> v + 1);
                    resultMap.compute("无效数据", (k, v) -> v + 1);
                    continue;
                }

                // 4. 分数段分类
                if (score >= 90) {
                    resultMap.compute("优秀", (k, v) -> v + 1);
                } else if (score >= 80) {
                    resultMap.compute("良好", (k, v) -> v + 1);
                } else if (score >= 70) {
                    resultMap.compute("中等", (k, v) -> v + 1);
                } else if (score >= 60) {
                    resultMap.compute("及格", (k, v) -> v + 1);
                } else {
                    resultMap.compute("不及格", (k, v) -> v + 1);
                }

                // 有效数据递增
                resultMap.compute("有效数据", (k, v) -> v + 1);
            }

            // 5. 数据一致性验证
            int sum = resultMap.get("优秀")
                    + resultMap.get("良好")
                    + resultMap.get("中等")
                    + resultMap.get("及格")
                    + resultMap.get("不及格");

            if (sum != resultMap.get("有效数据")) {
                throw new RuntimeException("数据统计不一致，有效数据：" + resultMap.get("有效数据")
                        + " 实际统计：" + sum);
            }

        } catch (Exception e) {
            // 6. 异常处理
            e.printStackTrace();
            resultMap.put("系统异常", -1);  // 添加异常标识
        }

        return resultMap;
    }
}
