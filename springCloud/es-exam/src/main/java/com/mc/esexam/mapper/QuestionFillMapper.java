package com.mc.esexam.mapper;

import com.mc.escommon.entity.exam.QuestionFill;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author machao
 * *@datetime 2025/2/20 11:59
 * *@inform 填空题题库
 */
@Mapper
public interface QuestionFillMapper {
    /**
     * 插入操作
     * @param questionFill 填空题问题
     * @return 返回是否插入成功
     */
    int insert(QuestionFill questionFill);

    /**
     * 根据id单个删除
     * @param id 填空题问题id
     * @return 返回是否删除成功
     */
    int deleteById(Integer id);

    /**
     * 根据id多个删除
     * @param ids 填空题问题id数组
     * @return 返回是否删除成功
     */
    int deleteByIds(Integer[] ids);

    /**
     * 根据条件查询
     * @param questionFill 查询条件
     * @return 返回多个填空题问题
     */
    List<QuestionFill> selectAll(QuestionFill questionFill);

    /**
     * 根据id单个查询
     * @param id 填空题问题id
     * @return 返回填空题问题全部信息
     */
    QuestionFill selectById(Integer id);

    /**
     * 修改填空题问题数据
     * @param questionFill 要修改的数据
     * @return 返回是否修改成功
     */
    int updateById(QuestionFill questionFill);
}