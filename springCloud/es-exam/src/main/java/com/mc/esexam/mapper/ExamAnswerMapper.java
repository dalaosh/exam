package com.mc.esexam.mapper;

import com.mc.escommon.entity.exam.ExamAnswer;
import com.mc.escommon.entity.user.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author machao
 * * @datetime 2025/2/28 23:23
 * * @inform 考试答题信息
 */
@Mapper
public interface ExamAnswerMapper {
    /**
     * 插入操作
     * @param examAnswer 考试答案
     * @return 返回是否插入成功
     */
    int insert(ExamAnswer examAnswer);

    /**
     * 根据id单个删除
     * @param id 考试答案id
     * @return 返回是否删除成功
     */
    int deleteById(Integer id);

    /**
     * 根据id多个删除
     * @param ids 考试答案id数组
     * @return 返回是否删除成功
     */
    int deleteByIds(Integer[] ids);

    /**
     * 根据条件查询
     * @param examAnswer 查询条件
     * @return 返回多个考试答案
     */
    List<ExamAnswer> selectAll(ExamAnswer examAnswer);

    /**
     * 根据id单个查询
     * @param id 考试答案id
     * @return 返回考试答案全部信息
     */
    ExamAnswer selectById(Integer id);

    /**
     * 修改考试答案数据
     * @param examAnswer 要修改的数据
     * @return 返回是否修改成功
     */
    int updateById(ExamAnswer examAnswer);

    Teacher selectTeacherById(Integer id);

    List<ExamAnswer> selectNoMark(ExamAnswer examAnswer);

    List<ExamAnswer> selectIsMark(ExamAnswer examAnswer);

    List<ExamAnswer> selectNoRightSign(ExamAnswer examAnswer);

    List<ExamAnswer> selectIsRightSign(ExamAnswer examAnswer);
}