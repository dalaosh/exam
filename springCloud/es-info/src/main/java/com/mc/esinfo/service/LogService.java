package com.mc.esinfo.service;

import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.info.Log;
import com.mc.escommon.entity.info.Params;

import java.util.List;

public interface LogService {
    /**
     * 新增
     */
    void add(Log log);

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
    void updateById(Log log);

    /**
     * 根据ID查询
     */
    Log selectById(Integer id);

    /**
     * 查询所有
     */
    List<Log> selectAll(Log log);

    /**
     * 分页查询
     */
    PageInfo<Log> selectPage(Params params);
}
