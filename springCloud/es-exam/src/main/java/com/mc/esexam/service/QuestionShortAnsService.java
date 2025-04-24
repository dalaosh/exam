package com.mc.esexam.service;

import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.Params;
import com.mc.escommon.entity.exam.QuestionShortAns;

import java.util.List;

/**
 * @author machao
 * @datetime 2025/3/3 17:52
 * @inform 简答题服务接口
 */
public interface QuestionShortAnsService {
    /**
     * 新增简答题
     * @param questionShortAns 要新增的简答题实体对象
     */
    void add(QuestionShortAns questionShortAns);

    /**
     * 根据 ID 删除简答题
     * @param id 要删除的简答题的 ID
     */
    void deleteById(Integer id);

    /**
     * 批量删除简答题
     * @param ids 包含要删除的简答题 ID 的列表
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 修改简答题信息
     * @param questionShortAns 包含要修改信息的简答题实体对象
     */
    void updateById(QuestionShortAns questionShortAns);

    /**
     * 根据 ID 查询简答题
     * @param id 要查询的简答题的 ID
     * @return 返回查询到的简答题实体对象，如果未找到则返回 null
     */
    QuestionShortAns selectById(Integer id);

    /**
     * 查询所有简答题
     * @param questionShortAns 作为查询条件的简答题实体对象
     * @return 返回符合条件的简答题列表
     */
    List<QuestionShortAns> selectAll(QuestionShortAns questionShortAns);

    /**
     * 分页查询简答题
     * @param params 包含分页信息和查询条件的参数对象
     * @return 返回分页后的简答题信息
     */
    PageInfo<QuestionShortAns> selectPage(Params params);
}