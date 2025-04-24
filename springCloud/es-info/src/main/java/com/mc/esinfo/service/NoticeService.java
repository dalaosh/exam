package com.mc.esinfo.service;

import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.info.Notice;
import com.mc.escommon.entity.info.Params;

import java.util.List;

public interface NoticeService {
    /**
     * 新增
     */
    void add(Notice notice);

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
    void updateById(Notice notice);

    /**
     * 根据ID查询
     */
    Notice selectById(Integer id);

    /**
     * 查询所有
     */
    List<Notice> selectAll(Notice notice);

    /**
     * 分页查询
     */
    PageInfo<Notice> selectPage(Params params);
}
