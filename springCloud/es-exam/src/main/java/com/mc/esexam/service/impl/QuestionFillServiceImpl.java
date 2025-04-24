package com.mc.esexam.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.Course;
import com.mc.escommon.entity.exam.Params;
import com.mc.escommon.entity.exam.QuestionFill;
import com.mc.esexam.mapper.QuestionFillMapper;
import com.mc.esexam.service.QuestionFillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author machao
 * *@datetime 2025/2/20 15:41
 * *@inform 填空题服务实现类
 */
@Service
public class QuestionFillServiceImpl implements QuestionFillService {

    @Autowired
    private QuestionFillMapper questionFillMapper;

    /**
     * 新增
     */
    @Override
    public void add(QuestionFill questionFill) {
        questionFillMapper.insert(questionFill);
    }

    /**
     * 删除
     */
    @Override
    public void deleteById(Integer id) {
        questionFillMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            questionFillMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    @Override
    public void updateById(QuestionFill questionFill) {
        questionFillMapper.updateById(questionFill);
    }

    /**
     * 根据ID查询
     */
    @Override
    public QuestionFill selectById(Integer id) {
        return questionFillMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    @Override
    public List<QuestionFill> selectAll(QuestionFill questionFill) {
        return questionFillMapper.selectAll(questionFill);
    }

    /**
     * 分页查询
     */
    @Override
    public PageInfo<QuestionFill> selectPage(Params params) {
        // 进行数据转移
        QuestionFill questionFill = new QuestionFill();
        if (params.getId() != null) {
            questionFill.setId(params.getId());// id
        }
        if (params.getCourseId() != null) {
            questionFill.setCourseId(params.getCourseId());// 课程id
        }
        if (params.getLevel() != null) {
            questionFill.setLevel(params.getLevel());// 题目难度级别
        }
        if (!Objects.equals(params.getSection(), "") && params.getSection() != null) {
            questionFill.setSection(params.getSection());// 章节
        }
        if (params.getScore() != null) {
            questionFill.setScore(params.getScore());// 分值
        }
        if(params.getTeacherId()!=null){
            Course course=new Course();
            course.setTeacherId(params.getTeacherId());
            questionFill.setCourse(course);
        }
        // 开启分页查询
        if (params.getPageNum() == null) {
            params.setPageNum(1);
            params.setPageSize(5);
        }
        PageHelper.startPage(params.getPageNum(), params.getPageSize());

        // 接下来的查询会自动按照当前开启的分页设置来查询
        List<QuestionFill> list = questionFillMapper.selectAll(questionFill);
        return PageInfo.of(list);
    }
}