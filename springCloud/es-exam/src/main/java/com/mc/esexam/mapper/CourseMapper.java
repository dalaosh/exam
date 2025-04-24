package com.mc.esexam.mapper;

import com.mc.escommon.entity.exam.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author machao
 * * @datetime 2025/2/15 13:53
 * * inform 课程信息
 */
@Mapper
public interface CourseMapper {
    /**
     * 插入操作
     * @param course 课程
     * @return 返回是否插入成功
     */
    int insert(Course course);

    /**
     * 根据id单个删除
     * @param id 课程id
     * @return 返回是否删除成功
     */
    int deleteById(Integer id);

    /**
     * 根据id多个删除
     * @param ids 课程id数组
     * @return 返回是否删除成功
     */
    int deleteByIds(Integer[] ids);

    /**
     * 根据条件查询
     * @param course 查询条件
     * @return 返回多个课程
     */
    List<Course> selectAll(Course course);

    /**
     * 根据id单个查询
     * @param id 课程id
     * @return 返回课程全部信息
     */
    Course selectById(Integer id);

    /**
     * 根据编号单个查询
     * @param number 课程编号
     * @return 返回课程全部信息
     */
    Course selectByNumber(String number);

    /**
     * 修改机构数据
     * @param course 要修改的数据
     * @return 返回是否修改成功
     */
    int updateById(Course course);
}
