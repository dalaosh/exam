package com.mc.esexam.mapper;

import com.mc.escommon.entity.exam.ExamPaper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author machao
 * * @datetime 2025/2/28 23:10
 * * @inform 考试试卷相关操作接口
 */
@Mapper
public interface ExamPaperMapper {

    /**
     * 插入考试试卷信息
     * @param examPaper 考试试卷实体对象
     * @return 插入成功返回 1，失败返回 0
     */
    int insert(ExamPaper examPaper);

    /**
     * 根据试卷 ID 删除考试试卷信息
     * @param id 试卷 ID
     * @return 删除成功返回 1，失败返回 0
     */
    int deleteById(Integer id);

    /**
     * 根据多个试卷 ID 批量删除考试试卷信息
     * @param ids 试卷 ID 数组
     * @return 删除成功的记录数
     */
    int deleteByIds(Integer[] ids);

    /**
     * 根据条件查询考试试卷信息列表
     * @param examPaper 包含查询条件的考试试卷实体对象
     * @return 符合条件的考试试卷信息列表
     */
    List<ExamPaper> selectAll(ExamPaper examPaper);

    /**
     * 根据试卷 ID 查询单个考试试卷信息
     * @param id 试卷 ID
     * @return 对应的考试试卷实体对象，若未找到则返回 null
     */
    ExamPaper selectById(Integer id);

    /**
     * 根据试卷编号查询单个考试试卷信息
     * @param number 试卷编号
     * @return 对应的考试试卷实体对象，若未找到则返回 null
     */
    ExamPaper selectByNumber(String number);

    /**
     * 根据试卷 ID 更新考试试卷信息
     * @param examPaper 包含要更新信息的考试试卷实体对象
     * @return 更新成功返回 1，失败返回 0
     */
    int updateById(ExamPaper examPaper);
}