package com.mc.esexam.service;

import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.Course;
import com.mc.escommon.entity.exam.Params;

import java.util.List;

/**
 * @author machao
 * * @datetime 2025/2/15 15:47
 * * inform 课程
 */


public interface CourseService {
    /**
     * 新增
     */
    void add(Course course);

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
    void updateById(Course course);

    /**
     * 根据ID查询
     */
    Course selectById(Integer id);


    /**
     * 根据编号查询
     */
    Course selectByNumber(String number);

    /**
     * 查询所有
     */
    List<Course> selectAll(Course course);

    /**
     * 分页查询
     */
    PageInfo<Course> selectPage(Params params);

    String Random();

    List<Course> selectAllById(Params params);
}

