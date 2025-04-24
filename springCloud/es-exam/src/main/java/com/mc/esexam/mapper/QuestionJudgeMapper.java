package com.mc.esexam.mapper;

import com.mc.escommon.entity.exam.QuestionJudge;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author machao
 * *@datetime 2025/2/20 11:59
 * *@inform 判断题题库
 */
@Mapper
public interface QuestionJudgeMapper {
    /**
     * 插入判断题记录
     * @param questionJudge 判断题实体对象
     * @return 插入成功返回大于 0 的值，失败返回 0
     */
    int insert(QuestionJudge questionJudge);

    /**
     * 根据 ID 删除单个判断题记录
     * @param id 判断题的 ID
     * @return 删除成功返回大于 0 的值，失败返回 0
     */
    int deleteById(Integer id);

    /**
     * 根据多个 ID 批量删除判断题记录
     * @param ids 判断题 ID 数组
     * @return 删除成功的记录数量
     */
    int deleteByIds(Integer[] ids);

    /**
     * 根据条件查询判断题记录列表
     * @param questionJudge 包含查询条件的判断题实体对象
     * @return 符合条件的判断题记录列表
     */
    List<QuestionJudge> selectAll(QuestionJudge questionJudge);

    /**
     * 根据 ID 查询单个判断题记录
     * @param id 判断题的 ID
     * @return 对应的判断题实体对象，如果未找到则返回 null
     */
    QuestionJudge selectById(Integer id);

    /**
     * 根据 ID 更新判断题记录
     * @param questionJudge 包含要更新信息的判断题实体对象
     * @return 更新成功返回大于 0 的值，失败返回 0
     */
    int updateById(QuestionJudge questionJudge);
}