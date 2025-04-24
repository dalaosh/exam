package com.mc.esuser.service;

import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.markdown.Params;
import com.mc.escommon.entity.markdown.Markdown;

import java.util.List;

/**
 * @author machao
 * * @datetime 2025/1/14 22:07
 * * inform
 */
public interface MarkdownService {
    /**
     * 新增
     */
    void add(Params params);

    /**
     * 删除
     */
    void deleteById(Integer id,String role);

    /**
     * 批量删除
     */
    void deleteBatch(List<Integer> ids,String role);

    /**
     * 修改
     */
    void updateById(Params params);

//    /**
//     * 根据ID查询
//     */
//    Markdown selectById(Integer id);

    /**
     * 查询所有
     */
    List<Markdown> selectAll(Markdown markdown);
    /**
     * 根据userId和名称查询文本/role进行区分
     */
    String selectContent(String address);
    /**
     * 分页查询
     */
    PageInfo<Markdown> selectPage(Params params);
}
