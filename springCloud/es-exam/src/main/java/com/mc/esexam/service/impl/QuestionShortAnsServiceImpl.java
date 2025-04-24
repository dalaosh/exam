package com.mc.esexam.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.Course;
import com.mc.escommon.entity.exam.Params;
import com.mc.escommon.entity.exam.QuestionShortAns;
import com.mc.esexam.mapper.QuestionShortAnsMapper;
import com.mc.esexam.service.QuestionShortAnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author machao
 * @datetime 2025/3/3 17:53
 * @inform 简答题服务实现类
 */
@Service
public class QuestionShortAnsServiceImpl implements QuestionShortAnsService {

    @Autowired
    private QuestionShortAnsMapper questionShortAnsMapper;

    /**
     * 新增
     */
    @Override
    public void add(QuestionShortAns questionShortAns) {
        questionShortAnsMapper.insert(questionShortAns);
    }

    /**
     * 删除
     */
    @Override
    public void deleteById(Integer id) {
        questionShortAnsMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            questionShortAnsMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    @Override
    public void updateById(QuestionShortAns questionShortAns) {
        questionShortAnsMapper.updateById(questionShortAns);
    }

    /**
     * 根据ID查询
     */
    @Override
    public QuestionShortAns selectById(Integer id) {
        return questionShortAnsMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    @Override
    public List<QuestionShortAns> selectAll(QuestionShortAns questionShortAns) {
        return questionShortAnsMapper.selectAll(questionShortAns);
    }

    /**
     * 分页查询
     */
    @Override
    public PageInfo<QuestionShortAns> selectPage(Params params) {
        // 进行数据转移
        QuestionShortAns questionShortAns = new QuestionShortAns();
        if (params.getId() != null) {
            questionShortAns.setId(params.getId());// id
        }
        if (params.getCourseId() != null) {
            questionShortAns.setCourseId(params.getCourseId());// 课程id
        }
        if (params.getLevel() != null) {
            questionShortAns.setLevel(params.getLevel());// 题目难度级别
        }
        if (!Objects.equals(params.getSection(), "") && params.getSection() != null) {
            questionShortAns.setSection(params.getSection());// 章节
        }
        if (params.getScore() != null) {
            questionShortAns.setScore(params.getScore());// 分值
        }
        if (params.getTeacherId() != null) {
            Course course = new Course();
            course.setTeacherId(params.getTeacherId());
            questionShortAns.setCourse(course);
        }
        // 开启分页查询
        if (params.getPageNum() == null) {
            params.setPageNum(1);
            params.setPageSize(5);
        }
        PageHelper.startPage(params.getPageNum(), params.getPageSize());

        // 接下来的查询会自动按照当前开启的分页设置来查询
        List<QuestionShortAns> list = questionShortAnsMapper.selectAll(questionShortAns);
        return PageInfo.of(list);
    }
}