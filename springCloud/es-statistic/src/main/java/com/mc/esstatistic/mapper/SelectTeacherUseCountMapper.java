package com.mc.esstatistic.mapper;

import com.mc.escommon.entity.exam.ExamScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author machao
 * * @datetime 2025/3/17 13:56
 * * inform
 */
@Mapper
public interface SelectTeacherUseCountMapper {
    /**
     * 每个教师每个题型数量 5个sql
     */
    int selectQuestionCodeNum(@Param("teacherId") Integer teacherId);
    int selectQuestionFillNum(@Param("teacherId") Integer teacherId);
    int selectQuestionJudgeNum(@Param("teacherId") Integer teacherId);
    int selectQuestionMultiNum(@Param("teacherId") Integer teacherId);
    int selectQuestionShortAnsNum(@Param("teacherId") Integer teacherId);

    /**
     * 每个教师每个题型的难度等级数量  5个sql
     */
    int selectQuestionCodeNumLevel(@Param("teacherId") Integer teacherId, @Param("level") String level);
    int selectQuestionFillNumLevel(@Param("teacherId") Integer teacherId, @Param("level") String level);
    int SelectQuestionJudgeNumLevel(@Param("teacherId") Integer teacherId,@Param("level") String level);
    int SelectQuestionMultiNumLevel(@Param("teacherId") Integer teacherId,@Param("level") String level);
    int SelectQuestionShortAnsNumLevel(@Param("teacherId") Integer teacherId,@Param("level") String level);

    /**
     * 考试次数
     */
    int selectExamNum(@Param("teacherId") Integer teacherId);

    /**
     * 课程数量
     */
    int selectCourseNum(@Param("teacherId") Integer teacherId);

    /**
     * 学生人数
     */
    int selectStudentNum(@Param("teacherId") Integer teacherId);

    /**
     * 平均及格率初步审查
     */
    List<ExamScore> selectScore(@Param("teacherId") Integer teacherId);
}
