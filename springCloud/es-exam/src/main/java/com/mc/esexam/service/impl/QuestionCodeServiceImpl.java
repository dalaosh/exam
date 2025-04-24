package com.mc.esexam.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.Course;
import com.mc.escommon.entity.exam.Params;
import com.mc.escommon.entity.exam.QuestionCode;
import com.mc.esexam.mapper.QuestionCodeMapper;
import com.mc.esexam.service.QuestionCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author machao
 * *@datetime 2025/2/20 15:41
 * *@inform 代码题服务实现类
 */
@Service
public class QuestionCodeServiceImpl implements QuestionCodeService {

    @Autowired
    private QuestionCodeMapper questionCodeMapper;

    /**
     * 新增
     */
    @Override
    public void add(QuestionCode questionCode) {
        questionCodeMapper.insert(questionCode);
    }

    /**
     * 删除
     */
    @Override
    public void deleteById(Integer id) {
        questionCodeMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids){
            questionCodeMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    @Override
    public void updateById(QuestionCode questionCode) {
        questionCodeMapper.updateById(questionCode);
    }

    /**
     * 根据ID查询
     */
    @Override
    public QuestionCode selectById(Integer id) {
        return questionCodeMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    @Override
    public List<QuestionCode> selectAll(QuestionCode questionCode) {
        return questionCodeMapper.selectAll(questionCode);
    }

    /**
     * 分页查询
     */
    @Override
    public PageInfo<QuestionCode> selectPage(Params params) {
        // 进行数据转移
        QuestionCode questionCode = new QuestionCode();
        if (params.getId() != null) {
            questionCode.setId(params.getId());// id
        }
        if (params.getCourseId() != null) {
            questionCode.setCourseId(params.getCourseId());// 课程id
        }
        if (params.getLevel() != null) {
            questionCode.setLevel(params.getLevel());// 题目难度级别
        }
        if (!Objects.equals(params.getSection(), "") && params.getSection() != null) {
            questionCode.setSection(params.getSection());// 章节
        }
        if (params.getScore() != null) {
            questionCode.setScore(params.getScore());// 分值
        }
        if(params.getTeacherId()!=null){
            Course course=new Course();
            course.setTeacherId(params.getTeacherId());
            questionCode.setCourse(course);
        }
        // 开启分页查询
        if (params.getPageNum() == null) {
            params.setPageNum(1);
            params.setPageSize(5);
        }
        PageHelper.startPage(params.getPageNum(), params.getPageSize());

        // 接下来的查询会自动按照当前开启的分页设置来查询
        List<QuestionCode> list = questionCodeMapper.selectAll(questionCode);
        return PageInfo.of(list);
    }
}