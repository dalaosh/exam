package com.mc.esexam.service;

import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.Params;
import com.mc.escommon.entity.exam.QuestionFill;

import java.util.List;

/**
 * @author machao
 * *@datetime 2025/2/20 15:41
 * *@inform 填空题服务接口
 */
public interface QuestionFillService {
    /**
     * 新增填空题
     * @param questionFill 要新增的填空题实体对象
     */
    void add(QuestionFill questionFill);

    /**
     * 根据 ID 删除填空题
     * @param id 要删除的填空题的 ID
     */
    void deleteById(Integer id);

    /**
     * 批量删除填空题
     * @param ids 包含要删除的填空题 ID 的列表
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 修改填空题信息
     * @param questionFill 包含要修改信息的填空题实体对象
     */
    void updateById(QuestionFill questionFill);

    /**
     * 根据 ID 查询填空题
     * @param id 要查询的填空题的 ID
     * @return 返回查询到的填空题实体对象，如果未找到则返回 null
     */
    QuestionFill selectById(Integer id);

    /**
     * 查询所有填空题
     * @param questionFill 作为查询条件的填空题实体对象
     * @return 返回符合条件的填空题列表
     */
    List<QuestionFill> selectAll(QuestionFill questionFill);

    /**
     * 分页查询填空题
     * @param params 包含分页信息和查询条件的参数对象
     * @return 返回分页后的填空题信息
     */
    PageInfo<QuestionFill> selectPage(Params params);
}