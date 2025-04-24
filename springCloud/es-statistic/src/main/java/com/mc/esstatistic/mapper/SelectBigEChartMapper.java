package com.mc.esstatistic.mapper;

import com.mc.escommon.entity.exam.ExamManage;
import com.mc.escommon.entity.exam.ExamScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author machao
 * * @datetime 2025/3/19 19:34
 * * inform
 */

@Mapper
public interface SelectBigEChartMapper {
    /**
     * 考试次数
     */
    int selectExamNum();

    /**
     * 课程数量
     */
    int selectCourseNum();

    /**
     * 平均及格率初步审查
     */
    List<ExamScore> selectScore();

    /**
     * 题型的数量  5个sql
     */
    int selectQuestionCodeNum();
    int selectQuestionFillNum();
    int selectQuestionJudgeNum();
    int selectQuestionMultiNum();
    int selectQuestionShortAnsNum();

    /**
     * 每个题型难度等级
     */
    int selectQuestionCodeNumLevel(@Param("level") String level);
    int selectQuestionFillNumLevel(@Param("level") String level);
    int SelectQuestionJudgeNumLevel(@Param("level") String level);
    int SelectQuestionMultiNumLevel(@Param("level") String level);
    int SelectQuestionShortAnsNumLevel(@Param("level") String level);

    List<ExamManage> selectExamAll();
}
