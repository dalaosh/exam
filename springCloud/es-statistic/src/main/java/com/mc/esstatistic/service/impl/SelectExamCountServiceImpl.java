package com.mc.esstatistic.service.impl;

import com.mc.escommon.result.QuestionTypeEnum;
import com.mc.esstatistic.mapper.SelectExamCountMapper;
import com.mc.esstatistic.service.SelectExamCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author machao
 * * @datetime 2025/3/15 13:21
 * * inform
 */
@Service
public class SelectExamCountServiceImpl implements SelectExamCountService {
    @Autowired
    private SelectExamCountMapper selectExamCountMapper;

    /**
     * 根据考试 ID 查询考试答题情况，返回一个嵌套的 Map 结构。
     * 外层 Map 的键表示某个统计维度（例如题目类型等），
     * 内层 Map 的键表示具体统计项（例如题目 ID），值表示对应的统计数量。
     *
     * @param examId 考试的唯一标识符，用于指定要查询的考试。
     * @return 一个嵌套的 Map，包含考试答题的统计信息。
     */
    @Override
    public Map<String, Map<String, Integer>> selectExamAnswer(Integer examId) {
        // 整体数据
        Map<String, Integer> questionAll = new HashMap<>();
        questionAll.put("allCount", selectExamCountMapper.selectExamAnswerAll(examId));
        questionAll.put("isMarkCount", selectExamCountMapper.selectExamAnswerIsMarkAll(examId));
        questionAll.put("noMarkCount", selectExamCountMapper.selectExamAnswerNoMarkAll(examId));
        questionAll.put("isRightSignCount", selectExamCountMapper.selectExamAnswerIsRightSignAll(examId));
        questionAll.put("noRightSignCount", selectExamCountMapper.selectExamAnswerNoRightSignAll(examId));

        // 代码题
        Map<String, Integer> questionCode = new HashMap<>();
        questionCode.put("isMarkCount", selectExamCountMapper.selectExamAnswerIsMarkQuestion(examId, QuestionTypeEnum.QUESTION_CODE.value));
        questionCode.put("noMarkCount", selectExamCountMapper.selectExamAnswerNoMarkQuestion(examId, QuestionTypeEnum.QUESTION_CODE.value));
        questionCode.put("isRightSignCount", selectExamCountMapper.selectExamAnswerIsRightSignQuestion(examId, QuestionTypeEnum.QUESTION_CODE.value));
        questionCode.put("noRightSignCount", selectExamCountMapper.selectExamAnswerNoRightSignQuestion(examId, QuestionTypeEnum.QUESTION_CODE.value));

        // 填空题
        Map<String, Integer> questionFill = new HashMap<>();
        questionFill.put("isMarkCount", selectExamCountMapper.selectExamAnswerIsMarkQuestion(examId, QuestionTypeEnum.QUESTION_FILL.value));
        questionFill.put("noMarkCount", selectExamCountMapper.selectExamAnswerNoMarkQuestion(examId, QuestionTypeEnum.QUESTION_FILL.value));
        questionFill.put("isRightSignCount", selectExamCountMapper.selectExamAnswerIsRightSignQuestion(examId, QuestionTypeEnum.QUESTION_FILL.value));
        questionFill.put("noRightSignCount", selectExamCountMapper.selectExamAnswerNoRightSignQuestion(examId, QuestionTypeEnum.QUESTION_FILL.value));

        // 判断题
        Map<String, Integer> questionJudge = new HashMap<>();
        questionJudge.put("isMarkCount", selectExamCountMapper.selectExamAnswerIsMarkQuestion(examId, QuestionTypeEnum.QUESTION_JUDGE.value));
        questionJudge.put("noMarkCount", selectExamCountMapper.selectExamAnswerNoMarkQuestion(examId, QuestionTypeEnum.QUESTION_JUDGE.value));
        questionJudge.put("isRightSignCount", selectExamCountMapper.selectExamAnswerIsRightSignQuestion(examId, QuestionTypeEnum.QUESTION_JUDGE.value));
        questionJudge.put("noRightSignCount", selectExamCountMapper.selectExamAnswerNoRightSignQuestion(examId, QuestionTypeEnum.QUESTION_JUDGE.value));

        // 选择题
        Map<String, Integer> questionMulti = new HashMap<>();
        questionMulti.put("isMarkCount", selectExamCountMapper.selectExamAnswerIsMarkQuestion(examId, QuestionTypeEnum.QUESTION_MULTI.value));
        questionMulti.put("noMarkCount", selectExamCountMapper.selectExamAnswerNoMarkQuestion(examId, QuestionTypeEnum.QUESTION_MULTI.value));
        questionMulti.put("isRightSignCount", selectExamCountMapper.selectExamAnswerIsRightSignQuestion(examId, QuestionTypeEnum.QUESTION_MULTI.value));
        questionMulti.put("noRightSignCount", selectExamCountMapper.selectExamAnswerNoRightSignQuestion(examId, QuestionTypeEnum.QUESTION_MULTI.value));

        // 简答题
        Map<String, Integer> questionShortAns = new HashMap<>();
        questionShortAns.put("isMarkCount", selectExamCountMapper.selectExamAnswerIsMarkQuestion(examId, QuestionTypeEnum.QUESTION_SHORT_ANS.value));
        questionShortAns.put("noMarkCount", selectExamCountMapper.selectExamAnswerNoMarkQuestion(examId, QuestionTypeEnum.QUESTION_SHORT_ANS.value));
        questionShortAns.put("isRightSignCount", selectExamCountMapper.selectExamAnswerIsRightSignQuestion(examId, QuestionTypeEnum.QUESTION_SHORT_ANS.value));
        questionShortAns.put("noRightSignCount", selectExamCountMapper.selectExamAnswerNoRightSignQuestion(examId, QuestionTypeEnum.QUESTION_SHORT_ANS.value));

        // 创建一个新的 Map，用于存储上面的集合
        Map<String, Map<String, Integer>> questionMap = new HashMap<>();
        // 将各个集合添加到新的 Map 中
        questionMap.put("questionAll", questionAll);
        questionMap.put("questionCode", questionCode);
        questionMap.put("questionFill", questionFill);
        questionMap.put("questionJudge", questionJudge);
        questionMap.put("questionMulti", questionMulti);
        questionMap.put("questionShortAns", questionShortAns);

        return questionMap;
    }

    /**
     * 根据考试 ID 和学生 ID 查询特定学生在该考试中的答题情况，返回一个嵌套的 Map 结构。
     * 外层 Map 的键表示某个统计维度（例如题目类型等），
     * 内层 Map 的键表示具体统计项（例如题目 ID），值表示对应的统计数量。
     *
     * @param examId    考试的唯一标识符，用于指定要查询的考试。
     * @param studentId 学生的唯一标识符，用于指定要查询的学生。
     * @return 一个嵌套的 Map，包含特定学生在该考试中的答题统计信息。
     */
    @Override
    public Map<String, Map<String, Integer>> selectExamAnswerStudent(Integer examId, Integer studentId) {
        // 整体数据
        Map<String, Integer> questionAll = new HashMap<>();
        questionAll.put("totalQuestions", selectExamCountMapper.selectExamPaperAll(examId));
        questionAll.put("answeredQuestions", selectExamCountMapper.selectExamAnswerIsAnswer(examId, studentId));

        // 代码题
        Map<String, Integer> questionCode = new HashMap<>();
        questionCode.put("totalQuestions", selectExamCountMapper.selectExamPaperQuestion(examId, QuestionTypeEnum.QUESTION_CODE.value));
        questionCode.put("answeredQuestions", selectExamCountMapper.selectExamAnswerIsAnswerQuestion(examId, studentId, QuestionTypeEnum.QUESTION_CODE.value));

        // 填空题
        Map<String, Integer> questionFill = new HashMap<>();
        questionFill.put("totalQuestions", selectExamCountMapper.selectExamPaperQuestion(examId, QuestionTypeEnum.QUESTION_FILL.value));
        questionFill.put("answeredQuestions", selectExamCountMapper.selectExamAnswerIsAnswerQuestion(examId, studentId, QuestionTypeEnum.QUESTION_FILL.value));

        // 判断题
        Map<String, Integer> questionJudge = new HashMap<>();
        questionJudge.put("totalQuestions", selectExamCountMapper.selectExamPaperQuestion(examId, QuestionTypeEnum.QUESTION_JUDGE.value));
        questionJudge.put("answeredQuestions", selectExamCountMapper.selectExamAnswerIsAnswerQuestion(examId, studentId, QuestionTypeEnum.QUESTION_JUDGE.value));

        // 选择题
        Map<String, Integer> questionMulti = new HashMap<>();
        questionMulti.put("totalQuestions", selectExamCountMapper.selectExamPaperQuestion(examId, QuestionTypeEnum.QUESTION_MULTI.value));
        questionMulti.put("answeredQuestions", selectExamCountMapper.selectExamAnswerIsAnswerQuestion(examId, studentId, QuestionTypeEnum.QUESTION_MULTI.value));

        // 简答题
        Map<String, Integer> questionShortAns = new HashMap<>();
        questionShortAns.put("totalQuestions", selectExamCountMapper.selectExamPaperQuestion(examId, QuestionTypeEnum.QUESTION_SHORT_ANS.value));
        questionShortAns.put("answeredQuestions", selectExamCountMapper.selectExamAnswerIsAnswerQuestion(examId, studentId, QuestionTypeEnum.QUESTION_SHORT_ANS.value));

        Map<String, Map<String, Integer>> resultMap = new HashMap<>();
        resultMap.put("questionAll", questionAll);
        resultMap.put("questionCode", questionCode);
        resultMap.put("questionFill", questionFill);
        resultMap.put("questionJudge", questionJudge);
        resultMap.put("questionMulti", questionMulti);
        resultMap.put("questionShortAns", questionShortAns);
        return resultMap;
    }

    /**
     * 根据考试 ID 和课程 ID 查询考试答题的统计信息，返回一个简单的 Map 结构。
     * Map 的键表示统计项（例如正确答题数量、错误答题数量等），值表示对应的统计数量。
     *
     * @param examId   考试的唯一标识符，用于指定要查询的考试。
     * @param courseId 课程的唯一标识符，用于进一步筛选统计信息。
     * @return 一个简单的 Map，包含考试答题的统计信息。
     */
    @Override
    public Map<String, Integer> selectExamAnswerStatistic(Integer examId, Integer courseId) {
        int examCourseNum = selectExamCountMapper.selectExamCourseNum(courseId, "同意");
        int examPaperNum = selectExamCountMapper.selectExamPaperNum(examId);
        int examAnswerNum = selectExamCountMapper.selectExamAnswerNum(examId);
        Map<String, Integer> examNum = new HashMap<>();
        examNum.put("examCourseNum",examCourseNum);//学生
        examNum.put("examPaperNum",examPaperNum);//试卷问题数
        examNum.put("examAnswerNum",examAnswerNum);//有效回答问题数
        return examNum;
    }
}
