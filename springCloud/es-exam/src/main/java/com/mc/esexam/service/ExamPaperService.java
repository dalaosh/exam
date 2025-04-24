package com.mc.esexam.service;

import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.ExamPaper;
import com.mc.escommon.entity.exam.Params;

import java.util.List;

/**
 * @author machao
 * @datetime 2025/3/1 0:32
 * @inform 考试试卷服务接口
 */
public interface ExamPaperService {
    /**
     * 新增考试试卷
     * @param examPaper 要新增的考试试卷实体
     */
    void add(ExamPaper examPaper);

    /**
     * 根据 ID 删除考试试卷
     * @param id 要删除的考试试卷的 ID
     */
    void deleteById(Integer id);

    /**
     * 批量删除考试试卷
     * @param ids 要删除的考试试卷 ID 列表
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 根据 ID 更新考试试卷信息
     * @param examPaper 包含更新信息的考试试卷实体
     */
    void updateById(ExamPaper examPaper);

    /**
     * 根据 ID 查询考试试卷
     * @param id 要查询的考试试卷的 ID
     * @return 对应的考试试卷实体，如果未找到则返回 null
     */
    ExamPaper selectById(Integer id);

    /**
     * 根据条件查询所有考试试卷
     * @param examPaper 包含查询条件的考试试卷实体
     * @return 符合条件的考试试卷列表
     */
    List<ExamPaper> selectAll(ExamPaper examPaper);

    /**
     * 分页查询考试试卷
     * @param params 包含分页和查询条件的参数对象
     * @return 分页后的考试试卷信息
     */
    PageInfo<ExamPaper> selectPage(Params params);

    void addAll(Integer multi, Integer judge, Integer fill, Integer shortAns, Integer code, Integer courseId, Integer examId);
}
