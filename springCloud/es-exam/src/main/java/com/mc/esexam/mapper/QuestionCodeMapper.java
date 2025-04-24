package com.mc.esexam.mapper;

import com.mc.escommon.entity.exam.QuestionCode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author machao
 * *@datetime 2025/2/20 11:57
 * *@inform 代码题题库
 */
@Mapper
public interface QuestionCodeMapper {
    /**
     * 插入操作
     * @param questionCode 代码题问题
     * @return 返回是否插入成功
     */
    int insert(QuestionCode questionCode);

    /**
     * 根据id单个删除
     * @param id 代码题问题id
     * @return 返回是否删除成功
     */
    int deleteById(Integer id);

    /**
     * 根据id多个删除
     * @param ids 代码题问题id数组
     * @return 返回是否删除成功
     */
    int deleteByIds(Integer[] ids);

    /**
     * 根据条件查询
     * @param questionCode 查询条件
     * @return 返回多个代码题问题
     */
    List<QuestionCode> selectAll(QuestionCode questionCode);

    /**
     * 根据id单个查询
     * @param id 代码题问题id
     * @return 返回代码题问题全部信息
     */
    QuestionCode selectById(Integer id);

    /**
     * 修改代码题问题数据
     * @param questionCode 要修改的数据
     * @return 返回是否修改成功
     */
    int updateById(QuestionCode questionCode);


}