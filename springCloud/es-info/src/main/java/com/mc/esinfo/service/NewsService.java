package com.mc.esinfo.service;

import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.info.News;
import com.mc.escommon.entity.info.Params;

import java.util.List;

public interface NewsService {
    /**
     * 新增
     */
    void add(News news);

    /**
     * 删除
     */
    void deleteById(Integer id);

    /**
     * 批量删除
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 修改
     */
    void updateById(News news);

    /**
     * 根据ID查询
     */
    News selectById(Integer id);

    /**
     * 查询所有
     */
    List<News> selectAll(News news);

    /**
     * 分页查询
     */
    PageInfo<News> selectPage(Params params);
}
