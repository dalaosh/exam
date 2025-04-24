package com.mc.esexam.mapper;

import com.mc.escommon.entity.exam.Code;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author machao
 * @datetime 2025/2/24 15:11
 * @inform 代码信息
 */
@Mapper
public interface CodeMapper {
    /**
     * 插入操作
     * @param code 代码信息
     * @return 返回插入的记录数，1 表示成功
     */
    int insert(Code code);

    /**
     * 根据 id 单个删除
     * @param id 代码 id
     * @return 返回删除的记录数，1 表示成功
     */
    int deleteById(Integer id);

    /**
     * 根据 id 多个删除
     * @param ids 代码 id 数组
     * @return 返回删除的记录数
     */
    int deleteByIds(Integer[] ids);

    /**
     * 根据条件查询
     * @param code 查询条件
     * @return 返回多个代码信息
     */
    List<Code> selectAll(Code code);

    /**
     * 根据 id 单个查询
     * @param id 代码 id
     * @return 返回代码全部信息
     */
    Code selectById(Integer id);

    /**
     * 根据学生 id 单个查询
     * @param studentId 学生 id
     * @return 返回代码全部信息
     */
    Code selectByStudentId(Integer studentId);

    /**
     * 修改代码数据
     * @param code 要修改的数据
     * @return 返回修改的记录数，1 表示成功
     */
    int updateById(Code code);
}