package com.mc.esexam.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.Course;
import com.mc.escommon.entity.exam.Params;
import com.mc.escommon.entity.exam.QuestionJudge;
import com.mc.esexam.mapper.QuestionJudgeMapper;
import com.mc.esexam.service.QuestionJudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author machao
 * *@datetime 2025/2/20 15:42
 * *@inform 判断题服务实现类，负责处理判断题相关的业务逻辑
 */
@Service
public class QuestionJudgeServiceImpl implements QuestionJudgeService {

    // 注入 QuestionJudgeMapper 实例，用于与数据库进行交互
    @Autowired
    private QuestionJudgeMapper questionJudgeMapper;

    /**
     * 新增判断题
     *
     * @param questionJudge 要新增的判断题对象，包含了判断题的各种信息，如题目内容、答案等
     */
    @Override
    public void add(QuestionJudge questionJudge) {
        // 调用 QuestionJudgeMapper 的 insert 方法将判断题对象插入到数据库中
        questionJudgeMapper.insert(questionJudge);
    }

    /**
     * 根据 ID 删除判断题
     *
     * @param id 要删除的判断题的 ID，通过该 ID 可以准确定位到要删除的记录
     */
    @Override
    public void deleteById(Integer id) {
        // 调用 QuestionJudgeMapper 的 deleteById 方法，根据传入的 ID 删除数据库中对应的判断题记录
        questionJudgeMapper.deleteById(id);
    }

    /**
     * 批量删除判断题
     *
     * @param ids 包含要删除的判断题 ID 的列表，用于一次性删除多个判断题记录
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        // 遍历 ID 列表
        for (Integer id : ids) {
            // 对列表中的每个 ID，调用 QuestionJudgeMapper 的 deleteById 方法删除对应的判断题记录
            questionJudgeMapper.deleteById(id);
        }
    }

    /**
     * 根据 ID 更新判断题信息
     *
     * @param questionJudge 包含要更新信息的判断题对象，其中 ID 用于定位记录，其他属性为要更新的内容
     */
    @Override
    public void updateById(QuestionJudge questionJudge) {
        // 调用 QuestionJudgeMapper 的 updateById 方法，根据传入的判断题对象更新数据库中对应的记录
        questionJudgeMapper.updateById(questionJudge);
    }

    /**
     * 根据 ID 查询判断题
     *
     * @param id 要查询的判断题的 ID，通过该 ID 可以从数据库中查找对应的判断题记录
     * @return 对应的判断题对象，如果未找到则返回 null
     */
    @Override
    public QuestionJudge selectById(Integer id) {
        // 调用 QuestionJudgeMapper 的 selectById 方法，根据传入的 ID 从数据库中查询对应的判断题记录
        return questionJudgeMapper.selectById(id);
    }

    /**
     * 根据条件查询判断题列表
     *
     * @param questionJudge 包含查询条件的判断题对象，根据该对象的属性值构建查询条件
     * @return 符合条件的判断题列表
     */
    @Override
    public List<QuestionJudge> selectAll(QuestionJudge questionJudge) {
        // 调用 QuestionJudgeMapper 的 selectAll 方法，根据传入的判断题对象作为查询条件，从数据库中查询符合条件的所有判断题记录
        return questionJudgeMapper.selectAll(questionJudge);
    }

    /**
     * 分页查询判断题
     *
     * @param params 包含分页和查询条件的参数对象，其中包含页码、每页记录数以及其他查询条件
     * @return 分页后的判断题信息，封装在 PageInfo 对象中
     */
    @Override
    public PageInfo<QuestionJudge> selectPage(Params params) {
        // 创建一个新的 QuestionJudge 对象，用于存储从 Params 对象中转移过来的查询条件
        QuestionJudge questionJudge = new QuestionJudge();

        // 如果 Params 对象中的 ID 不为空，则将其设置到 QuestionJudge 对象中
        if (params.getId() != null) {
            questionJudge.setId(params.getId());
        }

        // 如果 Params 对象中的课程 ID 不为空，则将其设置到 QuestionJudge 对象中
        if (params.getCourseId() != null) {
            questionJudge.setCourseId(params.getCourseId());
        }

        // 如果 Params 对象中的题目难度级别不为空，则将其设置到 QuestionJudge 对象中
        if (params.getLevel() != null) {
            questionJudge.setLevel(params.getLevel());
        }

        // 如果 Params 对象中的章节信息不为空且不为空字符串，则将其设置到 QuestionJudge 对象中
        if (!Objects.equals(params.getSection(), "") && params.getSection() != null) {
            questionJudge.setSection(params.getSection());
        }

        // 如果 Params 对象中的分值不为空，则将其设置到 QuestionJudge 对象中
        if (params.getScore() != null) {
            questionJudge.setScore(params.getScore());
        }
        if(params.getTeacherId()!=null){
            Course course=new Course();
            course.setTeacherId(params.getTeacherId());
            questionJudge.setCourse(course);
        }

        // 如果 Params 对象中的页码为空，将页码设置为 1，每页记录数设置为 5
        if (params.getPageNum() == null) {
            params.setPageNum(1);
            params.setPageSize(5);
        }

        // 使用 PageHelper 开启分页查询，指定页码和每页记录数
        PageHelper.startPage(params.getPageNum(), params.getPageSize());

        // 调用 QuestionJudgeMapper 的 selectAll 方法，根据转移后的查询条件进行查询，得到符合条件的判断题列表
        List<QuestionJudge> list = questionJudgeMapper.selectAll(questionJudge);

        // 将查询结果列表封装到 PageInfo 对象中，方便处理分页信息
        return PageInfo.of(list);
    }
}