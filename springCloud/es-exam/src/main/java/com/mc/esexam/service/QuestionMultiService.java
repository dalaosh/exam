package com.mc.esexam.service;

import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.Params;
import com.mc.escommon.entity.exam.QuestionMulti;

import java.util.List;

/**
 * @author machao
 * @datetime 2025/2/20 15:43
 * @inform 选择题服务接口
 */
public interface QuestionMultiService {
    /**
     * 新增选择题
     * @param questionMulti 待新增的选择题实体
     */
    void add(QuestionMulti questionMulti);

    /**
     * 根据 ID 删除选择题
     * @param id 待删除选择题的 ID
     */
    void deleteById(Integer id);

    /**
     * 批量删除选择题
     * @param ids 待删除选择题 ID 的列表
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 根据 ID 更新选择题信息
     * @param questionMulti 包含更新信息的选择题实体，ID 用于定位记录
     */
    void updateById(QuestionMulti questionMulti);

    /**
     * 根据 ID 查询选择题
     * @param id 待查询选择题的 ID
     * @return 查询到的选择题实体，若未找到则返回 null
     */
    QuestionMulti selectById(Integer id);

    /**
     * 根据条件查询选择题列表
     * @param questionMulti 包含查询条件的选择题实体
     * @return 符合条件的选择题列表
     */
    List<QuestionMulti> selectAll(QuestionMulti questionMulti);

    /**
     * 分页查询选择题
     * @param params 包含分页信息和查询条件的参数对象
     * @return 分页后的选择题信息
     */
    PageInfo<QuestionMulti> selectPage(Params params);
}