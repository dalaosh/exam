package com.mc.esexam.service;

import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.Params;
import com.mc.escommon.entity.exam.QuestionJudge;

import java.util.List;

/**
 * @author machao
 * *@datetime 2025/2/20 15:42
 * *@inform 判断题服务接口
 */
public interface QuestionJudgeService {

    /**
     * 新增判断题
     * @param questionJudge 要新增的判断题对象
     */
    void add(QuestionJudge questionJudge);

    /**
     * 根据 ID 删除判断题
     * @param id 要删除的判断题的 ID
     */
    void deleteById(Integer id);

    /**
     * 批量删除判断题
     * @param ids 包含要删除的判断题 ID 的列表
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 根据 ID 更新判断题信息
     * @param questionJudge 包含要更新信息的判断题对象，其中 ID 用于定位记录
     */
    void updateById(QuestionJudge questionJudge);

    /**
     * 根据 ID 查询判断题
     * @param id 要查询的判断题的 ID
     * @return 对应的判断题对象，如果未找到则返回 null
     */
    QuestionJudge selectById(Integer id);

    /**
     * 根据条件查询判断题列表
     * @param questionJudge 包含查询条件的判断题对象
     * @return 符合条件的判断题列表
     */
    List<QuestionJudge> selectAll(QuestionJudge questionJudge);

    /**
     * 分页查询判断题
     * @param params 包含分页和查询条件的参数对象
     * @return 分页后的判断题信息
     */
    PageInfo<QuestionJudge> selectPage(Params params);
}