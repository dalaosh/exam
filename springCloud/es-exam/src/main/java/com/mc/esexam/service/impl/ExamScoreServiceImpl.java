package com.mc.esexam.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.CourseStudent;
import com.mc.escommon.entity.exam.ExamAnswer;
import com.mc.escommon.entity.exam.ExamScore;
import com.mc.escommon.entity.exam.Params;
import com.mc.esexam.mapper.ExamAnswerMapper;
import com.mc.esexam.mapper.ExamScoreMapper;
import com.mc.esexam.service.ExamScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author machao
 * @datetime 2025/3/1 0:33
 * @inform 考试成绩服务实现类
 */
@Service
public class ExamScoreServiceImpl implements ExamScoreService {

    @Autowired
    private ExamScoreMapper examScoreMapper;
    @Autowired
    private ExamAnswerMapper examAnswerMapper;

    /**
     * 新增考试成绩
     * @param examScore 要新增的考试成绩实体
     */
    @Override
    public void add(ExamScore examScore) {
        examScoreMapper.insert(examScore);
    }

    /**
     * 根据 ID 删除考试成绩
     * @param id 要删除的考试成绩的 ID
     */
    @Override
    public void deleteById(Integer id) {
        examScoreMapper.deleteById(id);
    }

    /**
     * 批量删除考试成绩
     * @param ids 要删除的考试成绩 ID 列表
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            examScoreMapper.deleteById(id);
        }
    }

    /**
     * 根据 ID 更新考试成绩信息
     * @param examScore 包含更新信息的考试成绩实体
     */
    @Override
    public void updateById(ExamScore examScore) {
        examScoreMapper.updateById(examScore);
    }

    /**
     * 根据 ID 查询考试成绩
     * @param id 要查询的考试成绩的 ID
     * @return 对应的考试成绩实体，如果未找到则返回 null
     */
    @Override
    public ExamScore selectById(Integer id) {
        return examScoreMapper.selectById(id);
    }

    /**
     * 根据条件查询所有考试成绩
     * @param examScore 包含查询条件的考试成绩实体
     * @return 符合条件的考试成绩列表
     */
    @Override
    public List<ExamScore> selectAll(ExamScore examScore) {
        return examScoreMapper.selectAll(examScore);
    }

    /**
     * 分页查询考试成绩
     * @param params 包含分页和查询条件的参数对象
     * @return 分页后的考试成绩信息
     */
    @Override
    public PageInfo<ExamScore> selectPage(Params params) {
        // 数据转移，将 Params 中的条件设置到 ExamScore 对象中
        ExamScore scoreQuery = new ExamScore();
        if (params.getStudentId() != null) {
            scoreQuery.setStudentId(params.getStudentId());
        }
        if (params.getManagerId() != null) {
            scoreQuery.setManagerId(params.getManagerId());
        }
        if (params.getScore() != null) {
            scoreQuery.setScore(params.getScore());
        }

        // 分页设置
        if (params.getPageNum() == null) {
            params.setPageNum(1);
            params.setPageSize(5);
        }
        PageHelper.startPage(params.getPageNum(), params.getPageSize());

        // 执行查询
        List<ExamScore> scoreList = examScoreMapper.selectAll(scoreQuery);
        return new PageInfo<>(scoreList);
    }

    /**
     * 批量添加学生
     *
     * @param courseStudentList 课程中的学生
     * @param examId        试卷id
     * @return
     */
    @Override
    public void batchInsert(List<CourseStudent> courseStudentList, Integer examId) {
        for (CourseStudent courseStudent:courseStudentList){
            ExamScore examScore=new ExamScore();
            examScore.setManagerId(examId);
            examScore.setStudentId(courseStudent.getStudentId());
            examScoreMapper.insert(examScore);
        }
    }

    /**
     * @param examId
     */
    @Override
    public void setScore(Integer examId) {
        //查询所有的学生
        ExamScore score=new ExamScore();
        score.setManagerId(examId);
        List<ExamScore> examScoreList=examScoreMapper.selectAll(score);
        //查询学生的答题
        ExamAnswer answer=new ExamAnswer();
        answer.setExamId(examId);
        answer.setRightSign("验签成功");
        List<ExamAnswer> examAnswerList=examAnswerMapper.selectAll(answer);

        for (ExamScore examScore:examScoreList){
            int scores=0;
            for (ExamAnswer examAnswer:examAnswerList){
                if(Objects.equals(examAnswer.getStudentId(), examScore.getStudentId())){
                    scores= scores+Integer.parseInt(examAnswer.getMarkExams());
                }
            }
            examScore.setScore(scores);
            examScoreMapper.updateById(examScore);
        }
    }
}