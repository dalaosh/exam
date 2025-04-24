package com.mc.esexam.mapper;

import com.mc.escommon.entity.exam.ExamTeacherGrading;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author machao
 * * @datetime 2025/2/28 23:49
 * * @inform 教师评卷相关操作的 Mapper 接口
 */
@Mapper
public interface ExamTeacherGradingMapper {

    /**
     * 插入教师评卷信息
     * @param examTeacherGrading 教师评卷实体对象
     * @return 插入成功返回 1，失败返回 0
     */
    int insert(ExamTeacherGrading examTeacherGrading);

    /**
     * 根据 ID 删除教师评卷信息
     * @param id 教师评卷记录的 ID
     * @return 删除成功返回 1，失败返回 0
     */
    int deleteById(Integer id);

    /**
     * 根据多个 ID 批量删除教师评卷信息
     * @param ids 教师评卷记录的 ID 数组
     * @return 删除成功的记录数
     */
    int deleteByIds(Integer[] ids);

    /**
     * 根据条件查询教师评卷信息列表
     * @param examTeacherGrading 包含查询条件的教师评卷实体对象
     * @return 符合条件的教师评卷信息列表
     */
    List<ExamTeacherGrading> selectAll(ExamTeacherGrading examTeacherGrading);

    /**
     * 根据 ID 查询单个教师评卷信息
     * @param id 教师评卷记录的 ID
     * @return 对应的教师评卷实体对象，若未找到则返回 null
     */
    ExamTeacherGrading selectById(Integer id);

    /**
     * 根据 ID 更新教师评卷信息
     * @param examTeacherGrading 包含要更新信息的教师评卷实体对象
     * @return 更新成功返回 1，失败返回 0
     */
    int updateById(ExamTeacherGrading examTeacherGrading);
    /**
     * 根据 ID 删除教师评卷信息
     * @param id 教师评卷记录的 ID
     * @return 删除成功返回 1，失败返回 0
     */
    void deleteByExamId(Integer id);
}