package com.mc.esexam.service;

import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.ExamAnswer;
import com.mc.escommon.entity.exam.ExamTeacherGrading;
import com.mc.escommon.entity.exam.Params;

import java.util.List;

/**
 * @author machao
 * @datetime 2025/3/1 0:30
 * @inform 考试答案服务接口
 */
public interface ExamAnswerService {
    /**
     * 新增考试答案
     */
    void add(ExamAnswer examAnswer);

    /**
     * 根据 ID 删除考试答案
     */
    void deleteById(Integer id);

    /**
     * 批量删除考试答案
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 修改考试答案信息
     */
    void updateById(ExamAnswer examAnswer);

    /**
     * 根据 ID 查询考试答案
     */
    ExamAnswer selectById(Integer id);

    /**
     * 查询所有考试答案
     */
    List<ExamAnswer> selectAll(ExamAnswer examAnswer);

    /**
     * 分页查询考试答案
     */
    PageInfo<ExamAnswer> selectPage(Params params);

    /**
     * 阅卷判分
     */
    void updateByScore(Params params);

    /**
     * 自动判分
     */

    void updateByMultiAll(List<ExamTeacherGrading> examTeacherGradinglist);

    void updateByJudgeAll(List<ExamTeacherGrading> examTeacherGradinglist);
    /**
     * 自动判分审核时
     */

    void updateByMultiAllAnswer(List<ExamAnswer> examAnswerList);

    void updateByJudgeAllAnswer(List<ExamAnswer> examAnswerList);

    /**
     * 查询未批阅题数
     */
    List<ExamAnswer> selectNoMark(ExamAnswer examAnswer);
    /**
     * 查询已批阅题数
     */
    List<ExamAnswer> selectIsMark(ExamAnswer examAnswer);
    /**
     * 查询未验签题数
     */
    List<ExamAnswer> selectNoRightSign(ExamAnswer examAnswer);
    /**
     * 查询已验签题数
     */
    List<ExamAnswer> selectIsRightSign(ExamAnswer examAnswer);

    /**
     * 批量验签
     */
    void selectSetRightSignAll(List<ExamAnswer> examAnswerList);
    /**
     * 单个验签
     */
    void selectSetRightSign(ExamAnswer examAnswer);
}