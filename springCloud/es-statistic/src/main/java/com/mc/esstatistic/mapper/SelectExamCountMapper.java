package com.mc.esstatistic.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 考试数据统计Mapper接口，用于与数据库交互，执行各类考试数据统计相关的查询操作。
 *
 * @author machao
 * *@datetime 2025/3/14 22:39
 */
@Mapper
public interface SelectExamCountMapper {

    /**
     * 统计指定考试中所有学生的答题记录总数（包含已批阅和未批阅）。
     *
     * @param examId 考试ID（必须为有效考试ID），用于指定要统计的考试。
     * @return 答题记录总数，若不存在相关记录则返回0。
     * @throws RuntimeException 当数据库查询出现异常时抛出该异常。
     */
    int selectExamAnswerAll(@Param("examId") Integer examId);

    /**
     * 统计指定考试中已批阅的答题记录数量。
     *
     * @param examId 考试ID，用于指定要统计的考试。
     * @return 已批阅的答题记录数，若不存在相关记录则返回0。
     * @throws RuntimeException 当数据库查询出现异常时抛出该异常。
     */
    int selectExamAnswerIsMarkAll(@Param("examId") Integer examId);

    /**
     * 统计指定考试中未批阅的答题记录数量。
     *
     * @param examId 考试ID，用于指定要统计的考试。
     * @return 未批阅的答题记录数，若不存在相关记录则返回0。
     * @throws RuntimeException 当数据库查询出现异常时抛出该异常。
     */
    int selectExamAnswerNoMarkAll(@Param("examId") Integer examId);

    /**
     * 统计指定考试中验签成功答题记录数量。
     *
     * @param examId 考试ID，用于指定要统计的考试。
     * @return 验签成功记录数，若不存在相关记录则返回0。
     * @throws RuntimeException 当数据库查询出现异常时抛出该异常。
     */
    int selectExamAnswerIsRightSignAll(@Param("examId") Integer examId);

    /**
     * 统计指定考试中验签失败或未验签的答题记录数量。
     *
     * @param examId 考试ID，用于指定要统计的考试。
     * @return 验签失败或未验签的记录数，若不存在相关记录则返回0。
     * @throws RuntimeException 当数据库查询出现异常时抛出该异常。
     */
    int selectExamAnswerNoRightSignAll(@Param("examId") Integer examId);

    /**
     * 统计指定考试中某题型已批阅的答题记录数量。
     *
     * @param examId       考试ID，用于指定要统计的考试。
     * @param questionType 题型分类（如1=选择题，2=填空题等），用于指定要统计的题型。
     * @return 该题型已批阅的记录数，若不存在相关记录则返回0。
     * @throws RuntimeException 当数据库查询出现异常时抛出该异常。
     */
    int selectExamAnswerIsMarkQuestion(@Param("examId") Integer examId, @Param("questionType") String questionType);

    /**
     * 统计指定考试中某题型未批阅的答题记录数量。
     *
     * @param examId       考试ID，用于指定要统计的考试。
     * @param questionType 题型分类，用于指定要统计的题型。
     * @return 该题型未批阅的记录数，若不存在相关记录则返回0。
     * @throws RuntimeException 当数据库查询出现异常时抛出该异常。
     */
    int selectExamAnswerNoMarkQuestion(@Param("examId") Integer examId, @Param("questionType") String questionType);

    /**
     * 统计指定考试中某题型验签成功答题记录数量。
     *
     * @param examId       考试ID，用于指定要统计的考试。
     * @param questionType 题型分类，用于指定要统计的题型。
     * @return 该题型验签成功记录数，若不存在相关记录则返回0。
     * @throws RuntimeException 当数据库查询出现异常时抛出该异常。
     */
    int selectExamAnswerIsRightSignQuestion(@Param("examId") Integer examId, @Param("questionType") String questionType);

    /**
     * 统计指定考试中某题型验签失败或未验签的答题记录数量。
     *
     * @param examId       考试ID，用于指定要统计的考试。
     * @param questionType 题型分类，用于指定要统计的题型。
     * @return 该题型验签失败或未验签的记录数，若不存在相关记录则返回0。
     * @throws RuntimeException 当数据库查询出现异常时抛出该异常。
     */
    int selectExamAnswerNoRightSignQuestion(@Param("examId") Integer examId, @Param("questionType") String questionType);

    /**
     * 统计指定学生在某考试中的总答题数量（包含已批阅和未批阅）。
     *
     * @param examId    考试ID，用于指定要统计的考试。
     * @param studentId 学生ID（必须为有效用户ID），用于指定要统计的学生。
     * @return 该学生的总答题数，若该学生未答题则返回0。
     * @throws RuntimeException 当数据库查询出现异常时抛出该异常。
     */
    int selectExamAnswerIsAnswer(@Param("examId") Integer examId, @Param("studentId") Integer studentId);

    /**
     * 统计指定学生在某考试中某题型的答题数量。
     *
     * @param examId       考试ID，用于指定要统计的考试。
     * @param studentId    学生ID，用于指定要统计的学生。
     * @param questionType 题型分类，用于指定要统计的题型。
     * @return 该学生在此题型下的答题数，若该学生未答题则返回0。
     * @throws RuntimeException 当数据库查询出现异常时抛出该异常。
     */
    int selectExamAnswerIsAnswerQuestion(@Param("examId") Integer examId, @Param("studentId") Integer studentId, @Param("questionType") String questionType);

    /**
     * 统计指定考试试卷的总题目数量。
     *
     * @param examId 考试ID，用于指定要统计的考试。
     * @return 试卷总题数，若试卷不存在则返回0。
     * @throws RuntimeException 当数据库查询出现异常时抛出该异常。
     */
    int selectExamPaperAll(@Param("examId") Integer examId);

    /**
     * 统计指定考试试卷中某题型的题目数量。
     *
     * @param examId       考试ID，用于指定要统计的考试。
     * @param questionType 题型分类，用于指定要统计的题型。
     * @return 该题型的题目数量，若不存在相关题型题目则返回0。
     * @throws RuntimeException 当数据库查询出现异常时抛出该异常。
     */
    int selectExamPaperQuestion(@Param("examId") Integer examId, @Param("questionType") String questionType);

    /**
     * 统计指定课程中同意参加考试的考生人数。
     *
     * @param courseId 课程ID，用于指定要统计的课程。
     * @param isAdd    是否同意参加考试的标识（通常为“同意”）。
     * @return 同意参加考试的考生人数，若不存在相关记录则返回0。
     * @throws RuntimeException 当数据库查询出现异常时抛出该异常。
     */
    int selectExamCourseNum(@Param("courseId") Integer courseId, @Param("isAdd") String isAdd);

    /**
     * 统计指定考试的题目总数。
     *
     * @param examId 考试ID，用于指定要统计的考试。
     * @return 考试的题目总数，若考试不存在则返回0。
     * @throws RuntimeException 当数据库查询出现异常时抛出该异常。
     */
    int selectExamPaperNum(@Param("examId") Integer examId);

    /**
     * 统计指定考试的有效答题数。
     *
     * @param examId 考试ID，用于指定要统计的考试。
     * @return 有效答题数，若不存在有效答题记录则返回0。
     * @throws RuntimeException 当数据库查询出现异常时抛出该异常。
     */
    int selectExamAnswerNum(@Param("examId") Integer examId);

}