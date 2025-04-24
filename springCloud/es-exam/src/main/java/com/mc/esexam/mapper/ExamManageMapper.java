package com.mc.esexam.mapper;

import com.mc.escommon.entity.exam.ExamManage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author machao
 * * @datetime 2025/2/28 23:02
 * * @inform 考试试卷信息
 */
@Mapper
public interface ExamManageMapper {

    /**
     * 插入操作
     * @param examManage 考试管理信息实体
     * @return 返回插入操作影响的行数，大于 0 表示插入成功
     */
    int insert(ExamManage examManage);

    /**
     * 根据 id 单个删除
     * @param id 考试管理信息的 id
     * @return 返回删除操作影响的行数，大于 0 表示删除成功
     */
    int deleteById(Integer id);

    /**
     * 根据 id 多个删除
     * @param ids 考试管理信息的 id 数组
     * @return 返回删除操作影响的行数，大于 0 表示删除成功
     */
    int deleteByIds(Integer[] ids);

    /**
     * 根据条件查询
     * @param examManage 包含查询条件的考试管理信息实体
     * @return 返回符合条件的考试管理信息列表
     */
    List<ExamManage> selectAll(ExamManage examManage);

    /**
     * 根据 id 单个查询
     * @param id 考试管理信息的 id
     * @return 返回查询到的考试管理信息实体，如果未找到则返回 null
     */
    ExamManage selectById(Integer id);

    /**
     * 修改考试管理信息
     * @param examManage 包含要修改数据的考试管理信息实体
     * @return 返回更新操作影响的行数，大于 0 表示更新成功
     */
    int updateById(ExamManage examManage);
}