package com.mc.esstatistic.service;


import java.util.Map;

/**
 * 该服务接口主要用于处理与考试统计相关的业务逻辑，
 * 提供了根据不同条件查询考试答题统计信息的方法。
 *
 * @author machao
 * *@datetime 2025/3/15 13:21
 */
public interface SelectExamCountService {

    /**
     * 根据考试 ID 查询考试答题情况，返回一个嵌套的 Map 结构。
     * 外层 Map 的键表示某个统计维度（例如题目类型等），
     * 内层 Map 的键表示具体统计项（例如题目 ID），值表示对应的统计数量。
     *
     * @param examId 考试的唯一标识符，用于指定要查询的考试。
     * @return 一个嵌套的 Map，包含考试答题的统计信息。
     */
    Map<String, Map<String, Integer>> selectExamAnswer(Integer examId);

    /**
     * 根据考试 ID 和学生 ID 查询特定学生在该考试中的答题情况，返回一个嵌套的 Map 结构。
     * 外层 Map 的键表示某个统计维度（例如题目类型等），
     * 内层 Map 的键表示具体统计项（例如题目 ID），值表示对应的统计数量。
     *
     * @param examId    考试的唯一标识符，用于指定要查询的考试。
     * @param studentId 学生的唯一标识符，用于指定要查询的学生。
     * @return 一个嵌套的 Map，包含特定学生在该考试中的答题统计信息。
     */
    Map<String, Map<String, Integer>> selectExamAnswerStudent(Integer examId, Integer studentId);

    /**
     * 根据考试 ID 和课程 ID 查询考试答题的统计信息，返回一个简单的 Map 结构。
     * Map 的键表示统计项（例如正确答题数量、错误答题数量等），值表示对应的统计数量。
     *
     * @param examId   考试的唯一标识符，用于指定要查询的考试。
     * @param courseId 课程的唯一标识符，用于进一步筛选统计信息。
     * @return 一个简单的 Map，包含考试答题的统计信息。
     */
    Map<String, Integer> selectExamAnswerStatistic(Integer examId, Integer courseId);
}