package com.mc.esexam.service;

import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.Params;
import com.mc.escommon.entity.exam.QuestionCode;

import java.util.List;

/**
 * @author machao
 * *@datetime 2025/2/20 15:41
 * *@inform 代码题服务接口
 */
public interface QuestionCodeService {
    /**
     * 新增代码题
     */
    void add(QuestionCode questionCode);

    /**
     * 根据 ID 删除代码题
     */
    void deleteById(Integer id);

    /**
     * 批量删除代码题
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 修改代码题信息
     */
    void updateById(QuestionCode questionCode);

    /**
     * 根据 ID 查询代码题
     */
    QuestionCode selectById(Integer id);

    /**
     * 查询所有代码题
     */
    List<QuestionCode> selectAll(QuestionCode questionCode);

    /**
     * 分页查询代码题
     */
    PageInfo<QuestionCode> selectPage(Params params);
}