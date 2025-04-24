package com.mc.esexam.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.Course;
import com.mc.escommon.entity.exam.Params;
import com.mc.escommon.entity.exam.QuestionMulti;
import com.mc.esexam.mapper.QuestionMultiMapper;
import com.mc.esexam.service.QuestionMultiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author machao
 * *@datetime 2025/2/20 15:43
 * *@inform 多选题服务实现类
 */
@Service
public class QuestionMultiServiceImpl implements QuestionMultiService {

    // 注入 QuestionMultiMapper，用于与数据库进行交互
    @Autowired
    private QuestionMultiMapper questionMultiMapper;

    /**
     * 新增多选题
     *
     * @param questionMulti 要新增的多选题对象
     */
    @Override
    public void add(QuestionMulti questionMulti) {
        // 调用 QuestionMultiMapper 的 insert 方法将多选题对象插入数据库
        questionMultiMapper.insert(questionMulti);
    }

    /**
     * 根据 ID 删除多选题
     *
     * @param id 要删除的多选题的 ID
     */
    @Override
    public void deleteById(Integer id) {
        // 调用 QuestionMultiMapper 的 deleteById 方法根据 ID 删除对应的多选题记录
        questionMultiMapper.deleteById(id);
    }

    /**
     * 批量删除多选题
     *
     * @param ids 包含要删除的多选题 ID 的列表
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        // 遍历 ID 列表，逐个调用 deleteById 方法删除对应的多选题记录
        for (Integer id : ids) {
            questionMultiMapper.deleteById(id);
        }
    }

    /**
     * 根据 ID 更新多选题信息
     *
     * @param questionMulti 包含要更新信息的多选题对象，ID 用于定位记录
     */
    @Override
    public void updateById(QuestionMulti questionMulti) {
        // 调用 QuestionMultiMapper 的 updateById 方法根据 ID 更新对应的多选题记录
        questionMultiMapper.updateById(questionMulti);
    }

    /**
     * 根据 ID 查询多选题
     *
     * @param id 要查询的多选题的 ID
     * @return 查询到的多选题对象，如果未找到则返回 null
     */
    @Override
    public QuestionMulti selectById(Integer id) {
        // 调用 QuestionMultiMapper 的 selectById 方法根据 ID 查询对应的多选题记录
        return questionMultiMapper.selectById(id);
    }

    /**
     * 根据条件查询多选题列表
     *
     * @param questionMulti 包含查询条件的多选题对象
     * @return 符合条件的多选题列表
     */
    @Override
    public List<QuestionMulti> selectAll(QuestionMulti questionMulti) {
        // 调用 QuestionMultiMapper 的 selectAll 方法根据条件查询多选题列表
        return questionMultiMapper.selectAll(questionMulti);
    }

    /**
     * 分页查询多选题
     *
     * @param params 包含分页和查询条件的参数对象
     * @return 分页后的多选题信息
     */
    @Override
    public PageInfo<QuestionMulti> selectPage(Params params) {
        // 创建一个新的 QuestionMulti 对象，用于存储查询条件
        QuestionMulti questionMulti = new QuestionMulti();

        // 如果 Params 中的 ID 不为空，将其设置到 QuestionMulti 对象中
        if (params.getId() != null) {
            questionMulti.setId(params.getId());
        }

        // 如果 Params 中的课程 ID 不为空，将其设置到 QuestionMulti 对象中
        if (params.getCourseId() != null) {
            questionMulti.setCourseId(params.getCourseId());
        }

        // 如果 Params 中的题目难度级别不为空，将其设置到 QuestionMulti 对象中
        if (params.getLevel() != null) {
            questionMulti.setLevel(params.getLevel());
        }

        // 如果 Params 中的章节不为空且不为空字符串，将其设置到 QuestionMulti 对象中
        if (!Objects.equals(params.getSection(), "") && params.getSection() != null) {
            questionMulti.setSection(params.getSection());
        }

        // 如果 Params 中的分值不为空，将其设置到 QuestionMulti 对象中
        if (params.getScore() != null) {
            questionMulti.setScore(params.getScore());
        }
        if(params.getTeacherId()!=null){
            Course course=new Course();
            course.setTeacherId(params.getTeacherId());
            questionMulti.setCourse(course);
        }

        // 如果 Params 中的页码为空，设置默认页码为 1，每页记录数为 5
        if (params.getPageNum() == null) {
            params.setPageNum(1);
            params.setPageSize(5);
        }

        // 使用 PageHelper 开启分页查询
        PageHelper.startPage(params.getPageNum(), params.getPageSize());

        // 调用 QuestionMultiMapper 的 selectAll 方法进行查询
        List<QuestionMulti> list = questionMultiMapper.selectAll(questionMulti);

        // 将查询结果封装到 PageInfo 对象中返回
        return PageInfo.of(list);
    }
}