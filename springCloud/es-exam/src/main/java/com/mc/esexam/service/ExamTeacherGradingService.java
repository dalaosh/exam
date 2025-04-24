package com.mc.esexam.service;

import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.ExamManage;
import com.mc.escommon.entity.exam.ExamTeacherGrading;
import com.mc.escommon.entity.exam.Params;

import java.util.List;

/**
 * @author machao
 * @datetime 2025/3/1 0:34
 * @inform 教师评卷服务接口
 */
public interface ExamTeacherGradingService {

    /**
     * 新增教师评卷信息
     * @param examTeacherGrading 教师评卷实体对象
     */
    void add(ExamTeacherGrading examTeacherGrading);

    /**
     * 根据 ID 删除教师评卷信息
     * @param id 教师评卷记录的 ID
     */
    void deleteById(Integer id);

    /**
     * 批量删除教师评卷信息
     * @param ids 教师评卷记录的 ID 列表
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 根据 ID 更新教师评卷信息
     * @param examTeacherGrading 包含要更新信息的教师评卷实体对象
     */
    void updateById(ExamTeacherGrading examTeacherGrading);

    /**
     * 根据 ID 查询教师评卷信息
     * @param id 教师评卷记录的 ID
     * @return 对应的教师评卷实体对象，若未找到则返回 null
     */
    ExamTeacherGrading selectById(Integer id);

    /**
     * 根据条件查询所有教师评卷信息
     * @param examTeacherGrading 包含查询条件的教师评卷实体对象
     * @return 符合条件的教师评卷信息列表
     */
    List<ExamTeacherGrading> selectAll(ExamTeacherGrading examTeacherGrading);

    /**
     * 分页查询教师评卷信息
     * @param params 包含分页和查询条件的参数对象
     * @return 分页后的教师评卷信息
     */
    PageInfo<ExamTeacherGrading> selectPage(Params params);

    /**
     * 自动分配题目
     *
     * @param examId 试卷管理id
     * @param ids
     */
    void addPlay(Integer examId, List<Integer> ids,String beginTime,String endTime);
    /**
     * 根据 ID 删除教师评卷信息
     * @param id 教师评卷试卷ID
     */
    void deleteByExamId(Integer id);

    List<ExamManage> selectExam(ExamTeacherGrading examTeacherGrading);
}