package com.mc.esinfo.mapper;

import com.mc.escommon.entity.info.Log;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface LogMapper {
    /**
     * 插入操作
     * @param log 日志数据
     * @return 返回是否插入成功
     */
    int insert(Log log);

    /**
     * 根据id单个删除
     * @param id 日志id
     * @return 返回是否删除成功
     */
    int deleteById(Integer id);

    /**
     * 根据id多个删除
     * @param ids 日志id数组
     * @return 返回是否删除成功
     */
    int deleteByIds(Integer[] ids);

    /**
     * 根据条件查询
     * @param log 查询条件
     * @return 返回多个日志
     */
    List<Log> selectAll(Log log);

    /**
     * 根据id单个查询
     * @param id 日志id
     * @return 返回日志全部信息
     */
    Log selectById(Integer id);

    /**
     * 修改日志数据
     * @param log 要修改的数据
     * @return 返回是否修改成功
     */
    int updateById(Log log);
}
