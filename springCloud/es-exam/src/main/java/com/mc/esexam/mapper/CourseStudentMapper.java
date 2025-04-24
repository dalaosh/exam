package com.mc.esexam.mapper;

import com.mc.escommon.entity.exam.CourseStudent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author machao
 * * @datetime 2025/2/15 13:53
 * * inform 课程学生
 */
@Mapper
public interface CourseStudentMapper {
    /**
     * 插入操作
     * @param courseStudent 课程
     * @return 返回是否插入成功
     */
    int insert(CourseStudent courseStudent);

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
     * @param courseStudent 查询条件
     * @return 返回多个课程
     */
    List<CourseStudent> selectAll(CourseStudent courseStudent);

    /**
     * 根据id单个查询
     * @param id 课程id
     * @return 返回课程全部信息
     */
    CourseStudent selectById(Integer id);

    /**
     * 修改机构数据
     * @param courseStudent 要修改的数据
     * @return 返回是否修改成功
     */
    int updateById(CourseStudent courseStudent);
}
