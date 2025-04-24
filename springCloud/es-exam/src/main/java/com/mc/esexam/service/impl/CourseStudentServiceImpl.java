package com.mc.esexam.service.impl;


import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.CourseStudent;
import com.mc.escommon.entity.exam.Params;
import com.mc.esexam.mapper.CourseStudentMapper;
import com.mc.esexam.service.CourseStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
/**
 * @author machao
 * * @datetime 2025/2/15 15:48
 * * inform 课程学生
 */
@Service
public class CourseStudentServiceImpl implements CourseStudentService {
    @Autowired
    private CourseStudentMapper courseStudentMapper;

    /**
     * 新增
     */
    @Override
    public void add(CourseStudent courseStudent) {
        courseStudent.setIsAdd("待审核");
        courseStudentMapper.insert(courseStudent);
    }

    /**
     * 删除
     */
    @Override
    public void deleteById(Integer id) {
        courseStudentMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids){
            courseStudentMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    @Override
    public void updateById(CourseStudent courseStudent) {
        courseStudentMapper.updateById(courseStudent);
    }

    /**
     * 根据ID查询
     */
    @Override
    public CourseStudent selectById(Integer id) {
        return courseStudentMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    @Override
    public List<CourseStudent> selectAll(CourseStudent courseStudent) {
        return courseStudentMapper.selectAll(courseStudent);
    }

    /**
     * 分页查询
     */
    @Override
    public PageInfo<CourseStudent> selectPage(Params params) {
        //进行数据转移
        CourseStudent courseStudent=new CourseStudent();
        if(params.getStudentId()!=null){
            courseStudent.setStudentId(params.getStudentId());//学生id
        }
        if(params.getCourseId()!=null){
            courseStudent.setCourseId(params.getCourseId());//科目id
        }
        if(!Objects.equals(params.getIsAdd(), "") && params.getIsAdd()!=null){
            courseStudent.setIsAdd(params.getIsAdd());//加入成功
        }
        //开启分页查询
        if(params.getPageNum()==null) {
            params.setPageNum(1);
            params.setPageSize(5);
        }
        PageHelper.startPage(params.getPageNum(), params.getPageSize());

        // 接下来的查询会自动按照当前开启的分页设置来查询
        List<CourseStudent> list= courseStudentMapper.selectAll(courseStudent);
        return PageInfo.of(list);
    }
}
