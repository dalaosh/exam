package com.mc.esexam.service;

import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.CourseStudent;
import com.mc.escommon.entity.exam.Params;

import java.util.List;

/**
 * @author machao
 * * @datetime 2025/2/15 15:47
 * * inform 课程学生
 */
public interface CourseStudentService {
    /**
     * 新增
     */
    void add(CourseStudent courseStudent);

    /**
     * 删除
     */
    void deleteById(Integer id);

    /**
     * 批量删除
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 修改
     */
    void updateById(CourseStudent courseStudent);

    /**
     * 根据ID查询
     */
    CourseStudent selectById(Integer id);

    /**
     * 查询所有
     */
    List<CourseStudent> selectAll(CourseStudent courseStudent);

    /**
     * 分页查询
     */
    PageInfo<CourseStudent> selectPage(Params params);
}
