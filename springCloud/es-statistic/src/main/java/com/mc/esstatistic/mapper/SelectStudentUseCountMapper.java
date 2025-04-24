package com.mc.esstatistic.mapper;

import com.mc.escommon.entity.exam.ExamScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author machao
 * * @datetime 2025/3/17 13:55
 * * inform
 */
@Mapper
public interface SelectStudentUseCountMapper {
    /**
     * 每个学生每个题型题目数量
     */
    int selectQuestionByType(@Param("questionType") String questionType, @Param("studentId") Integer studentId);
    /**
     * 每个学生每个题型正确数量
     */
    int selectQuestionByTypeRight(@Param("questionType") String questionType, @Param("studentId") Integer studentId);

    /**
     * 整体每个题型正确数量
     */
    int selectQuestionByTypeAll(@Param("questionType") String questionType, @Param("studentId") Integer studentId);

    /**
     * 参与的考试数
     */
    int selectExamNum(@Param("studentId") Integer studentId);

    /**
     * 参与的课程
     */
    int selectCourseNum(@Param("studentId") Integer studentId);

    /**
     * 答题总数
     */
    int selectAnswerNum(@Param("studentId") Integer studentId);

    /**
     * 平均及格率
     */
    List<ExamScore> selectScore(@Param("studentId") Integer studentId);
}
