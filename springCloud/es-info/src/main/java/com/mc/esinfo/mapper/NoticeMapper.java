package com.mc.esinfo.mapper;

import com.mc.escommon.entity.info.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface NoticeMapper {
    /**
     * 插入操作
     * @param notice 日志数据
     * @return 返回是否插入成功
     */
    int insert(Notice notice);

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
     * @param notice 查询条件
     * @return 返回多个日志
     */
    List<Notice> selectAll(Notice notice);

    /**
     * 根据id单个查询
     * @param id 日志id
     * @return 返回日志全部信息
     */
    Notice selectById(Integer id);

    /**
     * 修改日志数据
     * @param notice 要修改的数据
     * @return 返回是否修改成功
     */
    int updateById(Notice notice);
}
