package com.mc.esexam.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.*;
import com.mc.escommon.result.QuestionTypeEnum;
import com.mc.escommon.result.ResultCodeEnum;
import com.mc.esexam.exception.CustomException;
import com.mc.esexam.mapper.*;
import com.mc.esexam.service.ExamPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author machao
 * @datetime 2025/3/1 0:32
 * @inform 考试试卷服务实现类
 */
@Service
public class ExamPaperServiceImpl implements ExamPaperService {

    @Autowired
    private ExamPaperMapper examPaperMapper;
    @Autowired
    private QuestionCodeMapper questionCodeMapper;
    @Autowired
    private QuestionFillMapper questionFillMapper;
    @Autowired
    private QuestionJudgeMapper questionJudgeMapper;
    @Autowired
    private QuestionShortAnsMapper questionShortAnsMapper;
    @Autowired
    private QuestionMultiMapper questionMultiMapper;
    @Autowired
    private ExamManageMapper examManageMapper;

    /**
     * 新增考试试卷
     * @param examPaper 要新增的考试试卷实体
     */
    @Override
    public void add(ExamPaper examPaper) {
        List<ExamPaper> examPaperList=examPaperMapper.selectAll(examPaper);
        // 判断查询结果是否为空
        if (examPaperList == null || examPaperList.isEmpty()) {
            //查询题目
            //获取分数
            int num=0;
            if(Objects.equals(examPaper.getQuestionType(), "code")){
                QuestionCode questionCode=questionCodeMapper.selectById(examPaper.getQuestionId());
                num=questionCode.getScore();
            }
            else if(Objects.equals(examPaper.getQuestionType(), "fill")){
                QuestionFill questionFill=questionFillMapper.selectById(examPaper.getQuestionId());
                num=questionFill.getScore();
            }
            else if(Objects.equals(examPaper.getQuestionType(), "judge")){
                QuestionJudge questionJudge=questionJudgeMapper.selectById(examPaper.getQuestionId());
                num=questionJudge.getScore();
            }
            else if(Objects.equals(examPaper.getQuestionType(), "shortAns")){
                QuestionShortAns questionShortAns=questionShortAnsMapper.selectById(examPaper.getQuestionId());
                num=questionShortAns.getScore();
            }
            else if(Objects.equals(examPaper.getQuestionType(), "multi")){
                QuestionMulti questionMulti=questionMultiMapper.selectById(examPaper.getQuestionId());
                num=questionMulti.getScore();
            }
            //查询试卷
            ExamManage examManage=examManageMapper.selectById(examPaper.getExamId());
            //查询总分
            Integer totalScore = examManage.getTotalScore();;
            //累加总分
            totalScore=totalScore+num;
            examManage.setTotalScore(totalScore);
            //添加题目
            examPaperMapper.insert(examPaper);
            //修改总分
            examManageMapper.updateById(examManage);
        }
        else {
            throw new CustomException(ResultCodeEnum.PARAM_ADD_ERROR);
        }
    }

    /**
     * 根据 ID 删除考试试卷
     * @param id 要删除的考试试卷的 ID
     */
    @Override
    public void deleteById(Integer id) {
        ExamPaper examPaper=examPaperMapper.selectById(id);
        //查询题目
        //获取分数
        int num=0;
        if(Objects.equals(examPaper.getQuestionType(), "code")){
            QuestionCode questionCode=questionCodeMapper.selectById(examPaper.getQuestionId());
            num=questionCode.getScore();
        }
        else if(Objects.equals(examPaper.getQuestionType(), "fill")){
            QuestionFill questionFill=questionFillMapper.selectById(examPaper.getQuestionId());
            num=questionFill.getScore();
        }
        else if(Objects.equals(examPaper.getQuestionType(), "judge")){
            QuestionJudge questionJudge=questionJudgeMapper.selectById(examPaper.getQuestionId());
            num=questionJudge.getScore();
        }
        else if(Objects.equals(examPaper.getQuestionType(), "shortAns")){
            QuestionShortAns questionShortAns=questionShortAnsMapper.selectById(examPaper.getQuestionId());
            num=questionShortAns.getScore();
        }
        else if(Objects.equals(examPaper.getQuestionType(), "multi")){
            QuestionMulti questionMulti=questionMultiMapper.selectById(examPaper.getQuestionId());
            num=questionMulti.getScore();
        }
        //查询试卷
        ExamManage examManage=examManageMapper.selectById(examPaper.getExamId());
        //查询总分
        Integer totalScore = examManage.getTotalScore();;
        //累加总分
        totalScore=totalScore-num;
        examManage.setTotalScore(totalScore);
        //添加题目
        examPaperMapper.insert(examPaper);
        //修改总分
        examManageMapper.updateById(examManage);
        examPaperMapper.deleteById(id);
    }

    /**
     * 批量删除考试试卷
     * @param ids 要删除的考试试卷 ID 列表
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            examPaperMapper.deleteById(id);
        }
    }

    /**
     * 根据 ID 更新考试试卷信息
     * @param examPaper 包含更新信息的考试试卷实体
     */
    @Override
    public void updateById(ExamPaper examPaper) {
        examPaperMapper.updateById(examPaper);
    }

    /**
     * 根据 ID 查询考试试卷
     * @param id 要查询的考试试卷的 ID
     * @return 对应的考试试卷实体，如果未找到则返回 null
     */
    @Override
    public ExamPaper selectById(Integer id) {
        return examPaperMapper.selectById(id);
    }

    /**
     * 根据条件查询所有考试试卷
     * @param examPaper 包含查询条件的考试试卷实体
     * @return 符合条件的考试试卷列表
     */
    @Override
    public List<ExamPaper> selectAll(ExamPaper examPaper) {
        return examPaperMapper.selectAll(examPaper);
    }

    /**
     * 分页查询考试试卷
     * @param params 包含分页和查询条件的参数对象
     * @return 分页后的考试试卷信息
     */
    @Override
    public PageInfo<ExamPaper> selectPage(Params params) {
        // 数据转移
        ExamPaper paper = new ExamPaper();
        if (params.getQuestionType() != null && !Objects.equals(params.getQuestionType(), "")) {
            paper.setQuestionType(params.getQuestionType());
        }
        if (params.getQuestionId() != null) {
            paper.setQuestionId(params.getQuestionId());
        }
        if (params.getExamId() != null) {
            paper.setExamId(params.getExamId());
        }

        // 分页设置
        if (params.getPageNum() == null) {
            params.setPageNum(1);
            params.setPageSize(5);
        }
        PageHelper.startPage(params.getPageNum(), params.getPageSize());

        // 执行查询
        List<ExamPaper> paperList = examPaperMapper.selectAll(paper);
        return PageInfo.of(paperList);
    }

    @Override
    public void addAll(Integer multi, Integer judge, Integer fill, Integer shortAns, Integer code, Integer courseId, Integer examId) {
        // 查询所有题库
        QuestionMulti questionMulti = new QuestionMulti();
        questionMulti.setCourseId(courseId);
        QuestionJudge questionJudge = new QuestionJudge();
        questionJudge.setCourseId(courseId);
        QuestionFill questionFill = new QuestionFill();
        questionFill.setCourseId(courseId);
        QuestionShortAns questionShortAns = new QuestionShortAns();
        questionShortAns.setCourseId(courseId);
        QuestionCode questionCode = new QuestionCode();
        questionCode.setCourseId(courseId);

        List<QuestionMulti> questionMultiList = questionMultiMapper.selectAll(questionMulti);
        List<QuestionJudge> questionJudgeList = questionJudgeMapper.selectAll(questionJudge);
        List<QuestionFill> questionFillList = questionFillMapper.selectAll(questionFill);
        List<QuestionShortAns> questionShortAnsList = questionShortAnsMapper.selectAll(questionShortAns);
        List<QuestionCode> questionCodeList = questionCodeMapper.selectAll(questionCode);

        // 随机打乱顺序并选取指定数量题目
        Collections.shuffle(questionMultiList);
        List<QuestionMulti> selectedMulti = questionMultiList.subList(0, Math.min(multi, questionMultiList.size()));
        for (QuestionMulti questionMulti1:selectedMulti){
            ExamPaper examPaper=new ExamPaper();
            examPaper.setExamId(examId);
            examPaper.setQuestionType(questionMulti1.getQuestionType());
            examPaper.setQuestionId(questionMulti1.getId());
            add(examPaper);
        }

        Collections.shuffle(questionJudgeList);
        List<QuestionJudge> selectedJudge = questionJudgeList.subList(0, Math.min(judge, questionJudgeList.size()));
        for (QuestionJudge questionJudge1:selectedJudge){
            ExamPaper examPaper=new ExamPaper();
            examPaper.setExamId(examId);
            examPaper.setQuestionType(QuestionTypeEnum.QUESTION_JUDGE.value);
            examPaper.setQuestionId(questionJudge1.getId());
            add(examPaper);
        }

        Collections.shuffle(questionFillList);
        List<QuestionFill> selectedFill = questionFillList.subList(0, Math.min(fill, questionFillList.size()));
        for (QuestionFill questionFill1:selectedFill){
            ExamPaper examPaper=new ExamPaper();
            examPaper.setExamId(examId);
            examPaper.setQuestionType(QuestionTypeEnum.QUESTION_FILL.value);
            examPaper.setQuestionId(questionFill1.getId());
            add(examPaper);
        }

        Collections.shuffle(questionShortAnsList);
        List<QuestionShortAns> selectedShortAns = questionShortAnsList.subList(0, Math.min(shortAns, questionShortAnsList.size()));
        for (QuestionShortAns questionShortAns1:selectedShortAns){
            ExamPaper examPaper=new ExamPaper();
            examPaper.setExamId(examId);
            examPaper.setQuestionType(QuestionTypeEnum.QUESTION_SHORT_ANS.value);
            examPaper.setQuestionId(questionShortAns1.getId());
           add(examPaper);
        }
        Collections.shuffle(questionCodeList);
        List<QuestionCode> selectedCode = questionCodeList.subList(0, Math.min(code, questionCodeList.size()));
        for (QuestionCode questionCode1:selectedCode){
            ExamPaper examPaper=new ExamPaper();
            examPaper.setExamId(examId);
            examPaper.setQuestionType(QuestionTypeEnum.QUESTION_CODE.value);
            examPaper.setQuestionId(questionCode1.getId());
            add(examPaper);
        }
    }
}
