package com.mc.esexam.mapper;

import com.mc.escommon.entity.exam.ExamStudent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author machao
 * @datetime 2025/2/28 23:44
 * @inform 考试学生相关操作接口
 */
@Mapper
public interface ExamStudentMapper {

    /**
     * 插入考试学生信息
     * @param examStudent 考试学生实体对象
     * @return 插入成功返回插入记录的数量（通常为 1），失败返回 0
     */
    int insert(ExamStudent examStudent);

    /**
     * 根据学生考试记录 ID 删除考试学生信息
     * @param id 学生考试记录 ID
     * @return 删除成功返回删除记录的数量（通常为 1），失败返回 0
     */
    int deleteById(Integer id);

    /**
     * 根据多个学生考试记录 ID 批量删除考试学生信息
     * @param ids 学生考试记录 ID 数组
     * @return 删除成功的记录数量
     */
    int deleteByIds(Integer[] ids);

    /**
     * 根据条件查询考试学生信息列表
     * @param examStudent 包含查询条件的考试学生实体对象
     * @return 符合条件的考试学生信息列表
     */
    List<ExamStudent> selectAll(ExamStudent examStudent);

    /**
     * 根据学生考试记录 ID 查询单个考试学生信息
     * @param id 学生考试记录 ID
     * @return 对应的考试学生实体对象，若未找到则返回 null
     */
    ExamStudent selectById(Integer id);

    /**
     * 根据学生考试记录 ID 更新考试学生信息
     * @param examStudent 包含要更新信息的考试学生实体对象
     * @return 更新成功返回更新记录的数量（通常为 1），失败返回 0
     */
    int updateById(ExamStudent examStudent);
}