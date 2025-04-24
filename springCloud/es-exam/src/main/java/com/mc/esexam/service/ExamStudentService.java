package com.mc.esexam.service;

import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.CourseStudent;
import com.mc.escommon.entity.exam.ExamStudent;
import com.mc.escommon.entity.exam.Params;

import java.util.List;

/**
 * @author machao
 * @datetime 2025/3/1 0:33
 * @inform 考试学生服务接口
 */
public interface ExamStudentService {

    /**
     * 新增考试学生信息
     * @param examStudent 要新增的考试学生实体
     */
    void add(ExamStudent examStudent);

    /**
     * 根据 ID 删除考试学生信息
     * @param id 要删除的考试学生的 ID
     */
    void deleteById(Integer id);

    /**
     * 批量删除考试学生信息
     * @param ids 要删除的考试学生 ID 列表
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 根据 ID 更新考试学生信息
     * @param examStudent 包含更新信息的考试学生实体
     */
    void updateById(ExamStudent examStudent);

    /**
     * 根据 ID 查询考试学生信息
     * @param id 要查询的考试学生的 ID
     * @return 对应的考试学生实体，如果未找到则返回 null
     */
    ExamStudent selectById(Integer id);

    /**
     * 根据条件查询所有考试学生信息
     * @param examStudent 包含查询条件的考试学生实体
     * @return 符合条件的考试学生列表
     */
    List<ExamStudent> selectAll(ExamStudent examStudent);

    /**
     * 分页查询考试学生信息
     * @param params 包含分页和查询条件的参数对象
     * @return 分页后的考试学生信息
     */
    PageInfo<ExamStudent> selectPage(Params params);

    /**
     * 批量添加学生
     * @param courseStudentList 课程中的学生
     * @param examId 试卷id
     * @return
     */
    void batchInsert(List<CourseStudent> courseStudentList, Integer examId);

    // 这里可根据实际业务添加与考试学生相关的特定方法，比如下面示例方法
    /**
     * 检查学生是否已提交考试
     * @param studentId 学生ID
     * @param examId 考试ID
     * @return true表示已提交，false表示未提交
     */
    boolean checkIsSubmitted(Integer studentId, Integer examId);

    void addStudentBatch(Integer id);

    void updateSubmit(ExamStudent examStudent);

    void updateSubmitByTeacher(ExamStudent examStudent);
}