package com.mc.esexam.mapper;

import com.mc.escommon.entity.exam.ExamScore;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author machao
 * * @datetime 2025/2/28 23:44
 * * @inform 考试成绩相关操作接口
 */
@Mapper
public interface ExamScoreMapper {

    /**
     * 插入考试成绩信息
     * @param examScore 考试成绩实体对象
     * @return 插入成功返回插入记录的数量（通常为 1），失败返回 0
     */
    int insert(ExamScore examScore);

    /**
     * 根据成绩 ID 删除考试成绩信息
     * @param id 成绩 ID
     * @return 删除成功返回删除记录的数量（通常为 1），失败返回 0
     */
    int deleteById(Integer id);

    /**
     * 根据多个成绩 ID 批量删除考试成绩信息
     * @param ids 成绩 ID 数组
     * @return 删除成功的记录数量
     */
    int deleteByIds(Integer[] ids);

    /**
     * 根据条件查询考试成绩信息列表
     * @param examScore 包含查询条件的考试成绩实体对象
     * @return 符合条件的考试成绩信息列表
     */
    List<ExamScore> selectAll(ExamScore examScore);

    /**
     * 根据成绩 ID 查询单个考试成绩信息
     * @param id 成绩 ID
     * @return 对应的考试成绩实体对象，若未找到则返回 null
     */
    ExamScore selectById(Integer id);

    /**
     * 根据成绩 ID 更新考试成绩信息
     * @param examScore 包含要更新信息的考试成绩实体对象
     * @return 更新成功返回更新记录的数量（通常为 1），失败返回 0
     */
    int updateById(ExamScore examScore);
}