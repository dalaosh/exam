package com.mc.esexam.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.Course;
import com.mc.escommon.entity.exam.Params;
import com.mc.esexam.mapper.CourseMapper;
import com.mc.esexam.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author machao
 * * @datetime 2025/2/15 15:48
 * * inform 课程
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    /**
     * 新增
     */
    @Override
    public void add(Course course) {
        courseMapper.insert(course);
    }

    /**
     * 删除
     */
    @Override
    public void deleteById(Integer id) {
        courseMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids){
            courseMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    @Override
    public void updateById(Course course) {
        courseMapper.updateById(course);
    }

    /**
     * 根据ID查询
     */
    @Override
    public Course selectById(Integer id) {
        return courseMapper.selectById(id);
    }

    /**
     * 根据编号查询
     */
    @Override
    public Course selectByNumber(String number){
        return courseMapper.selectByNumber(number);
    }

    /**
     * 查询所有
     */
    @Override
    public List<Course> selectAll(Course course) {
        return courseMapper.selectAll(course);
    }

    /**
     * 分页查询
     */
    @Override
    public PageInfo<Course> selectPage(Params params) {
        //进行数据转移
        Course course=new Course();
        if(params.getTeacherId()!=null){
            course.setTeacherId(params.getTeacherId());//代课教师
        }
        if(!Objects.equals(params.getName(), "") && params.getName()!=null){
            course.setName(params.getName());//课程名称
        }
        if(!Objects.equals(params.getNumber(), "") && params.getNumber()!=null){
            course.setNumber(params.getNumber());//课程编号
        }
        //开启分页查询
        if(params.getPageNum()==null) {
            params.setPageNum(1);
            params.setPageSize(5);
        }
        PageHelper.startPage(params.getPageNum(), params.getPageSize());

        // 接下来的查询会自动按照当前开启的分页设置来查询
        List<Course> list= courseMapper.selectAll(course);
        return PageInfo.of(list);
    }

    @Override
    public String Random() {
        // 定义大写字母、小写字母和数字的字符集合
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";

        Random random = new Random();
        List<Character> codeList = new ArrayList<>();

        // 从每种字符集合中随机选取一个字符
        codeList.add(upperCaseLetters.charAt(random.nextInt(upperCaseLetters.length())));
        codeList.add(lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length())));
        codeList.add(digits.charAt(random.nextInt(digits.length())));

        // 定义包含所有字符的集合
        String allCharacters = upperCaseLetters + lowerCaseLetters + digits;

        // 生成剩余的 5 个字符
        for (int i = 0; i < 5; i++) {
            codeList.add(allCharacters.charAt(random.nextInt(allCharacters.length())));
        }

        // 打乱字符顺序
        Collections.shuffle(codeList);

        // 将字符列表转换为字符串
        StringBuilder codeBuilder = new StringBuilder();
        for (char c : codeList) {
            codeBuilder.append(c);
        }

        return codeBuilder.toString();
    }

    @Override
    public List<Course> selectAllById(Params params) {
        // 查询所有课程
        List<Course> courseList = courseMapper.selectAll(new Course());
        Integer teacherId = params.getTeacherId();

        // 1. 分离教师创建的课程和其他课程
        List<Course> teacherCourses = new ArrayList<>();
        List<Course> otherCourses = new ArrayList<>();
        Set<String> teacherCourseNames = new HashSet<>();

        // 第一次循环：分离课程并收集课程名称
        for (Course course : courseList) {
            if (teacherId.equals(course.getTeacherId())) {
                teacherCourses.add(course);
                teacherCourseNames.add(course.getName());
            } else {
                otherCourses.add(course);
            }
        }

        // 2. 筛选匹配课程名称的其他课程
        List<Course> result = new ArrayList<>();
        for (Course course : otherCourses) {
            if (teacherCourseNames.contains(course.getName())) {
                result.add(course);
            }
        }

        return result;
    }
}
