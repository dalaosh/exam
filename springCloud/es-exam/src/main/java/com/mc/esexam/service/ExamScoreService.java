package com.mc.esexam.service;

import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.CourseStudent;
import com.mc.escommon.entity.exam.ExamScore;
import com.mc.escommon.entity.exam.Params;

import java.util.List;

/**
 * @author machao
 * @datetime 2025/3/1 0:33
 * @inform 考试成绩服务接口
 */
public interface ExamScoreService {

    /**
     * 新增考试成绩
     * @param examScore 要新增的考试成绩实体
     */
    void add(ExamScore examScore);

    /**
     * 根据 ID 删除考试成绩
     * @param id 要删除的考试成绩的 ID
     */
    void deleteById(Integer id);

    /**
     * 批量删除考试成绩
     * @param ids 要删除的考试成绩 ID 列表
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 根据 ID 更新考试成绩信息
     * @param examScore 包含更新信息的考试成绩实体
     */
    void updateById(ExamScore examScore);

    /**
     * 根据 ID 查询考试成绩
     * @param id 要查询的考试成绩的 ID
     * @return 对应的考试成绩实体，如果未找到则返回 null
     */
    ExamScore selectById(Integer id);

    /**
     * 根据条件查询所有考试成绩
     * @param examScore 包含查询条件的考试成绩实体
     * @return 符合条件的考试成绩列表
     */
    List<ExamScore> selectAll(ExamScore examScore);

    /**
     * 分页查询考试成绩
     * @param params 包含分页和查询条件的参数对象
     * @return 分页后的考试成绩信息
     */
    PageInfo<ExamScore> selectPage(Params params);

    /**
     * 批量添加学生
     * @param courseStudentList 课程中的学生
     * @param examId 试卷id
     * @return
     */
    void batchInsert(List<CourseStudent> courseStudentList, Integer examId);

    void setScore(Integer examId);
}