package com.mc.esexam.mapper;

import com.mc.escommon.entity.exam.QuestionMulti;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author machao
 * *@datetime 2025/2/20 12:06
 * *@inform 选择题题库
 */
@Mapper
public interface QuestionMultiMapper {
    /**
     * 插入多选题记录
     * @param questionMulti 多选题实体对象
     * @return 插入成功返回大于 0 的值，失败返回 0
     */
    int insert(QuestionMulti questionMulti);

    /**
     * 根据 ID 删除单个多选题记录
     * @param id 多选题的 ID
     * @return 删除成功返回大于 0 的值，失败返回 0
     */
    int deleteById(Integer id);

    /**
     * 根据多个 ID 批量删除多选题记录
     * @param ids 多选题 ID 数组
     * @return 删除成功的记录数量
     */
    int deleteByIds(Integer[] ids);

    /**
     * 根据条件查询多选题记录列表
     * @param questionMulti 包含查询条件的多选题实体对象
     * @return 符合条件的多选题记录列表
     */
    List<QuestionMulti> selectAll(QuestionMulti questionMulti);

    /**
     * 根据 ID 查询单个多选题记录
     * @param id 多选题的 ID
     * @return 对应的多选题实体对象，如果未找到则返回 null
     */
    QuestionMulti selectById(Integer id);

    /**
     * 根据 ID 更新多选题记录
     * @param questionMulti 包含要更新信息的多选题实体对象
     * @return 更新成功返回大于 0 的值，失败返回 0
     */
    int updateById(QuestionMulti questionMulti);
}