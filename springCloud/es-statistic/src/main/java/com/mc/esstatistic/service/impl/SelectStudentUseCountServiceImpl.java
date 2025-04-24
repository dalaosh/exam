package com.mc.esstatistic.service.impl;

import com.mc.escommon.entity.exam.ExamScore;
import com.mc.escommon.result.QuestionTypeEnum;
import com.mc.esstatistic.mapper.SelectStudentUseCountMapper;
import com.mc.esstatistic.service.SelectStudentUseCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author machao
 * * @datetime 2025/3/17 13:54
 * * inform
 */
@Service
public class SelectStudentUseCountServiceImpl implements SelectStudentUseCountService {
    @Autowired
    private SelectStudentUseCountMapper selectStudentUseCountMapper;
    /**
     * @param studentId
     * @return
     */
    @Override
    public Map<String, Map<String, Integer>> selectQuestionType(Integer studentId) {
        //每个学生类型的答题数
        Map<String, Integer> questionType = new HashMap<>();
        Integer questionCode=selectStudentUseCountMapper.selectQuestionByType(QuestionTypeEnum.QUESTION_CODE.value,studentId);
        Integer questionFill=selectStudentUseCountMapper.selectQuestionByType(QuestionTypeEnum.QUESTION_FILL.value,studentId);
        Integer questionJudge=selectStudentUseCountMapper.selectQuestionByType(QuestionTypeEnum.QUESTION_JUDGE.value,studentId);
        Integer questionMulti=selectStudentUseCountMapper.selectQuestionByType(QuestionTypeEnum.QUESTION_MULTI.value,studentId);
        Integer questionShortAns=selectStudentUseCountMapper.selectQuestionByType(QuestionTypeEnum.QUESTION_SHORT_ANS.value,studentId);
        questionType.put("questionCode",questionCode);
        questionType.put("questionFill",questionFill);
        questionType.put("questionJudge",questionJudge);
        questionType.put("questionMulti",questionMulti);
        questionType.put("questionShortAns",questionShortAns);
        //每个学生类型的正确答题数
        Map<String, Integer> questionTypeRight = new HashMap<>();
        Integer questionCodeRight=selectStudentUseCountMapper.selectQuestionByTypeRight(QuestionTypeEnum.QUESTION_CODE.value,studentId);
        Integer questionFillRight=selectStudentUseCountMapper.selectQuestionByTypeRight(QuestionTypeEnum.QUESTION_FILL.value,studentId);
        Integer questionJudgeRight=selectStudentUseCountMapper.selectQuestionByTypeRight(QuestionTypeEnum.QUESTION_JUDGE.value,studentId);
        Integer questionMultiRight=selectStudentUseCountMapper.selectQuestionByTypeRight(QuestionTypeEnum.QUESTION_MULTI.value,studentId);
        Integer questionShortAnsRight=selectStudentUseCountMapper.selectQuestionByTypeRight(QuestionTypeEnum.QUESTION_SHORT_ANS.value,studentId);
        questionTypeRight.put("questionCodeRight",questionCodeRight);
        questionTypeRight.put("questionFillRight",questionFillRight);
        questionTypeRight.put("questionJudgeRight",questionJudgeRight);
        questionTypeRight.put("questionMultiRight",questionMultiRight);
        questionTypeRight.put("questionShortAnsRight",questionShortAnsRight);

        Map<String, Map<String, Integer>> resultMap = new HashMap<>();
        resultMap.put("questionType", questionType);
        resultMap.put("questionTypeRight", questionTypeRight);
        return resultMap;
    }

    /**
     * @param studentId
     * @return
     *
     */
    @Override
    public Map<String, Map<String, Integer>> selectStudentStrong(Integer studentId) {
        //每个学生类型的答题数
        Map<String, Integer> questionType = new HashMap<>();
        Integer questionCode=selectStudentUseCountMapper.selectQuestionByType(QuestionTypeEnum.QUESTION_CODE.value,studentId);
        Integer questionFill=selectStudentUseCountMapper.selectQuestionByType(QuestionTypeEnum.QUESTION_FILL.value,studentId);
        Integer questionJudge=selectStudentUseCountMapper.selectQuestionByType(QuestionTypeEnum.QUESTION_JUDGE.value,studentId);
        Integer questionMulti=selectStudentUseCountMapper.selectQuestionByType(QuestionTypeEnum.QUESTION_MULTI.value,studentId);
        Integer questionShortAns=selectStudentUseCountMapper.selectQuestionByType(QuestionTypeEnum.QUESTION_SHORT_ANS.value,studentId);
        questionType.put("questionCode",questionCode);
        questionType.put("questionFill",questionFill);
        questionType.put("questionJudge",questionJudge);
        questionType.put("questionMulti",questionMulti);
        questionType.put("questionShortAns",questionShortAns);
        //每个学生类型的正确答题数
        Map<String, Integer> questionTypeRight = new HashMap<>();
        Integer questionCodeRight=selectStudentUseCountMapper.selectQuestionByTypeRight(QuestionTypeEnum.QUESTION_CODE.value,studentId);
        Integer questionFillRight=selectStudentUseCountMapper.selectQuestionByTypeRight(QuestionTypeEnum.QUESTION_FILL.value,studentId);
        Integer questionJudgeRight=selectStudentUseCountMapper.selectQuestionByTypeRight(QuestionTypeEnum.QUESTION_JUDGE.value,studentId);
        Integer questionMultiRight=selectStudentUseCountMapper.selectQuestionByTypeRight(QuestionTypeEnum.QUESTION_MULTI.value,studentId);
        Integer questionShortAnsRight=selectStudentUseCountMapper.selectQuestionByTypeRight(QuestionTypeEnum.QUESTION_SHORT_ANS.value,studentId);
        questionTypeRight.put("questionCodeRight",questionCodeRight);
        questionTypeRight.put("questionFillRight",questionFillRight);
        questionTypeRight.put("questionJudgeRight",questionJudgeRight);
        questionTypeRight.put("questionMultiRight",questionMultiRight);
        questionTypeRight.put("questionShortAnsRight",questionShortAnsRight);
        //全部题目
        Map<String, Integer> questionTypeAll = new HashMap<>();
        Integer questionCodeRightAll=selectStudentUseCountMapper.selectQuestionByTypeAll(QuestionTypeEnum.QUESTION_CODE.value,studentId);
        Integer questionFillRightAll=selectStudentUseCountMapper.selectQuestionByTypeAll(QuestionTypeEnum.QUESTION_FILL.value,studentId);
        Integer questionJudgeRightAll=selectStudentUseCountMapper.selectQuestionByTypeAll(QuestionTypeEnum.QUESTION_JUDGE.value,studentId);
        Integer questionMultiRightAll=selectStudentUseCountMapper.selectQuestionByTypeAll(QuestionTypeEnum.QUESTION_MULTI.value,studentId);
        Integer questionShortAnsRightAll=selectStudentUseCountMapper.selectQuestionByTypeAll(QuestionTypeEnum.QUESTION_SHORT_ANS.value,studentId);
        questionTypeAll.put("questionCodeAll",questionCodeRightAll);
        questionTypeAll.put("questionFillAll",questionFillRightAll);
        questionTypeAll.put("questionJudgeAll",questionJudgeRightAll);
        questionTypeAll.put("questionMultiAll",questionMultiRightAll);
        questionTypeAll.put("questionShortAnsAll",questionShortAnsRightAll);

        Map<String, Map<String, Integer>> resultMap = new HashMap<>();
        resultMap.put("questionType", questionType);
        resultMap.put("questionTypeRight", questionTypeRight);
        resultMap.put("questionTypeAll", questionTypeAll);
        return resultMap;
    }

    /**
     * @param studentId
     * @return
     */
    @Override
    public Map<String, Integer> selectBasicNum(Integer studentId) {
        //参与的考试数
        Integer selectExamNum = selectStudentUseCountMapper.selectExamNum(studentId);
        //课程数
        Integer selectCourseNum = selectStudentUseCountMapper.selectCourseNum(studentId);
        //答题总数
        Integer selectAnswerNum = selectStudentUseCountMapper.selectAnswerNum(studentId);
        List<ExamScore> selectScoreList=selectStudentUseCountMapper.selectScore(studentId);
        int selectS=0;
        Integer selectSS=selectScoreList.size();
        for (ExamScore examScore:selectScoreList){
            if(examScore.getExamManage().getTotalScore()*0.6<=examScore.getScore()){
                selectS+=1;
            }
        }
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("selectExamNum",selectExamNum);
        resultMap.put("selectCourseNum",selectCourseNum);
        resultMap.put("selectAnswerNum",selectAnswerNum);
        resultMap.put("selectS",selectS);
        resultMap.put("selectSS",selectSS);
        return resultMap;
    }
}
