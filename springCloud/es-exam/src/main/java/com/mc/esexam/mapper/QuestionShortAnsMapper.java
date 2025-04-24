package com.mc.esexam.mapper;

import com.mc.escommon.entity.exam.QuestionShortAns;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author machao
 * @datetime 2025/3/3 17:51
 * @inform 简答题题库
 */
@Mapper
public interface QuestionShortAnsMapper {
    /**
     * 插入操作
     * @param questionShortAns 简答题问题
     * @return 返回是否插入成功
     */
    int insert(QuestionShortAns questionShortAns);

    /**
     * 根据id单个删除
     * @param id 简答题问题id
     * @return 返回是否删除成功
     */
    int deleteById(Integer id);

    /**
     * 根据id多个删除
     * @param ids 简答题问题id数组
     * @return 返回是否删除成功
     */
    int deleteByIds(Integer[] ids);

    /**
     * 根据条件查询
     * @param questionShortAns 查询条件
     * @return 返回多个简答题问题
     */
    List<QuestionShortAns> selectAll(QuestionShortAns questionShortAns);

    /**
     * 根据id单个查询
     * @param id 简答题问题id
     * @return 返回简答题问题全部信息
     */
    QuestionShortAns selectById(Integer id);

    /**
     * 修改简答题问题数据
     * @param questionShortAns 要修改的数据
     * @return 返回是否修改成功
     */
    int updateById(QuestionShortAns questionShortAns);
}