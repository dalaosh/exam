package com.mc.esstatistic.service.impl;

import com.mc.escommon.entity.exam.ExamScore;
import com.mc.esstatistic.mapper.SelectTeacherUseCountMapper;
import com.mc.esstatistic.service.SelectTeacherUseCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author machao
 * * @datetime 2025/3/17 13:55
 * * inform
 */
@Service
public class SelectTeacherUseCountServiceImpl implements SelectTeacherUseCountService {
    @Autowired
    private SelectTeacherUseCountMapper selectTeacherUseCountMapper;
    /**
     * @param teacherId
     * @return
     */
    @Override
    public Map<String, Integer> selectQuestionType(Integer teacherId) {
        //查询问题个数
        Map<String, Integer> questionType = new HashMap<>();
        Integer questionCode=selectTeacherUseCountMapper.selectQuestionCodeNum(teacherId);
        Integer questionFill=selectTeacherUseCountMapper.selectQuestionFillNum(teacherId);
        Integer questionJudge=selectTeacherUseCountMapper.selectQuestionJudgeNum(teacherId);
        Integer questionMulti=selectTeacherUseCountMapper.selectQuestionMultiNum(teacherId);
        Integer questionShortAns=selectTeacherUseCountMapper.selectQuestionShortAnsNum(teacherId);
        questionType.put("questionCode", questionCode);
        questionType.put("questionFill", questionFill);
        questionType.put("questionJudge", questionJudge);
        questionType.put("questionMulti", questionMulti);
        questionType.put("questionShortAns", questionShortAns);
        return questionType;
    }

    /**
     * @param teacherId
     * @return
     */
    @Override
    public Map<String, Map<String, Integer>> selectExamAnswerLevel(Integer teacherId) {
        //查询代码题
        Map<String, Integer> questionCodeType = new HashMap<>();
        Integer questionCodeOne=selectTeacherUseCountMapper.selectQuestionCodeNumLevel(teacherId,"1");
        Integer questionCodeTwo=selectTeacherUseCountMapper.selectQuestionCodeNumLevel(teacherId,"2");
        Integer questionCodeThree=selectTeacherUseCountMapper.selectQuestionCodeNumLevel(teacherId,"3");
        Integer questionCodeFour=selectTeacherUseCountMapper.selectQuestionCodeNumLevel(teacherId,"4");
        Integer questionCodeFive=selectTeacherUseCountMapper.selectQuestionCodeNumLevel(teacherId,"5");
        questionCodeType.put("questionCodeOne",questionCodeOne);
        questionCodeType.put("questionCodeTwo",questionCodeTwo);
        questionCodeType.put("questionCodeThree",questionCodeThree);
        questionCodeType.put("questionCodeFour",questionCodeFour);
        questionCodeType.put("questionCodeFive",questionCodeFive);

        Map<String, Integer> questionFillType = new HashMap<>();
        Integer questionFillOne=selectTeacherUseCountMapper.selectQuestionFillNumLevel(teacherId,"1");
        Integer questionFillTwo=selectTeacherUseCountMapper.selectQuestionFillNumLevel(teacherId,"2");
        Integer questionFillThree=selectTeacherUseCountMapper.selectQuestionFillNumLevel(teacherId,"3");
        Integer questionFillFour=selectTeacherUseCountMapper.selectQuestionFillNumLevel(teacherId,"4");
        Integer questionFillFive=selectTeacherUseCountMapper.selectQuestionFillNumLevel(teacherId,"5");
        questionFillType.put("questionFillOne",questionFillOne);
        questionFillType.put("questionFillTwo",questionFillTwo);
        questionFillType.put("questionFillThree",questionFillThree);
        questionFillType.put("questionFillFour",questionFillFour);
        questionFillType.put("questionFillFive",questionFillFive);

        Map<String, Integer> questionJudgeType = new HashMap<>();
        Integer questionJudgeOne=selectTeacherUseCountMapper.SelectQuestionJudgeNumLevel(teacherId,"1");
        Integer questionJudgeTwo=selectTeacherUseCountMapper.SelectQuestionJudgeNumLevel(teacherId,"2");
        Integer questionJudgeThree=selectTeacherUseCountMapper.SelectQuestionJudgeNumLevel(teacherId,"3");
        Integer questionJudgeFour=selectTeacherUseCountMapper.SelectQuestionJudgeNumLevel(teacherId,"4");
        Integer questionJudgeFive=selectTeacherUseCountMapper.SelectQuestionJudgeNumLevel(teacherId,"5");
        questionJudgeType.put("questionJudgeOne",questionJudgeOne);
        questionJudgeType.put("questionJudgeTwo",questionJudgeTwo);
        questionJudgeType.put("questionJudgeThree",questionJudgeThree);
        questionJudgeType.put("questionJudgeFour",questionJudgeFour);
        questionJudgeType.put("questionJudgeFive",questionJudgeFive);

        Map<String, Integer> questionMultiType = new HashMap<>();
        Integer questionMultiOne=selectTeacherUseCountMapper.SelectQuestionMultiNumLevel(teacherId,"1");
        Integer questionMultiTwo=selectTeacherUseCountMapper.SelectQuestionMultiNumLevel(teacherId,"2");
        Integer questionMultiThree=selectTeacherUseCountMapper.SelectQuestionMultiNumLevel(teacherId,"3");
        Integer questionMultiFour=selectTeacherUseCountMapper.SelectQuestionMultiNumLevel(teacherId,"4");
        Integer questionMultiFive=selectTeacherUseCountMapper.SelectQuestionMultiNumLevel(teacherId,"5");
        questionMultiType.put("questionMultiOne",questionMultiOne);
        questionMultiType.put("questionMultiTwo",questionMultiTwo);
        questionMultiType.put("questionMultiThree",questionMultiThree);
        questionMultiType.put("questionMultiFour",questionMultiFour);
        questionMultiType.put("questionMultiFive",questionMultiFive);

        Map<String, Integer> questionShortAnsType = new HashMap<>();
        Integer questionShortAnsOne=selectTeacherUseCountMapper.SelectQuestionShortAnsNumLevel(teacherId,"1");
        Integer questionShortAnsTwo=selectTeacherUseCountMapper.SelectQuestionShortAnsNumLevel(teacherId,"2");
        Integer questionShortAnsThree=selectTeacherUseCountMapper.SelectQuestionShortAnsNumLevel(teacherId,"3");
        Integer questionShortAnsFour=selectTeacherUseCountMapper.SelectQuestionShortAnsNumLevel(teacherId,"4");
        Integer questionShortAnsFive=selectTeacherUseCountMapper.SelectQuestionShortAnsNumLevel(teacherId,"5");
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
     * @param teacherId
     * @return
     */
    @Override
    public Map<String, Integer> selectBasicNum(Integer teacherId) {
        //考试次数
        Integer selectExamNum=selectTeacherUseCountMapper.selectExamNum(teacherId);
        Integer selectCourseNum=selectTeacherUseCountMapper.selectCourseNum(teacherId);
        Integer selectStudentNum=selectTeacherUseCountMapper.selectStudentNum(teacherId);
        List<ExamScore> selectScoreList=selectTeacherUseCountMapper.selectScore(teacherId);
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
        resultMap.put("selectStudentNum",selectStudentNum);
        resultMap.put("selectS",selectS);
        resultMap.put("selectSS",selectSS);
        return resultMap;
    }
}
