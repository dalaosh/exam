package com.mc.esexam.service;

import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.Code;
import com.mc.escommon.entity.exam.Params;

import java.util.List;

/**
 * @author machao
 * @datetime 2025/2/24 15:43
 * @inform 代码服务接口
 */
public interface CodeService {
    /**
     * 新增代码记录
     * @param code 代码实体对象
     */
    void add(Code code);

    /**
     * 根据 ID 删除代码记录
     * @param id 代码记录的 ID
     */
    void deleteById(Integer id);

    /**
     * 批量删除代码记录
     * @param ids 代码记录 ID 的列表
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 根据 ID 修改代码记录
     * @param code 包含修改信息的代码实体对象
     */
    void updateById(Code code);

    /**
     * 根据 ID 查询代码记录
     * @param id 代码记录的 ID
     * @return 代码实体对象
     */
    Code selectById(Integer id);

    /**
     * 查询所有代码记录
     * @param code 包含查询条件的代码实体对象
     * @return 代码记录列表
     */
    List<Code> selectAll(Code code);

    /**
     * 分页查询代码记录
     * @param params 分页查询参数
     * @return 分页信息对象，包含代码记录列表
     */
    PageInfo<Code> selectPage(Params params);

}