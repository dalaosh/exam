package com.mc.esexam.service.impl;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.ExamManage;
import com.mc.escommon.entity.exam.ExamPaper;
import com.mc.escommon.entity.exam.ExamTeacherGrading;
import com.mc.escommon.entity.exam.Params;
import com.mc.esexam.mapper.ExamManageMapper;
import com.mc.esexam.mapper.ExamPaperMapper;
import com.mc.esexam.mapper.ExamTeacherGradingMapper;
import com.mc.esexam.service.ExamTeacherGradingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author machao
 * @datetime 2025/3/1 0:34
 * @inform 教师评卷服务实现类
 */
@Service
public class ExamTeacherGradingServiceImpl implements ExamTeacherGradingService {

    @Autowired
    private ExamTeacherGradingMapper examTeacherGradingMapper;
    @Autowired
    private ExamPaperMapper examPaperMapper;
    @Autowired
    private ExamManageMapper examManageMapper;

    /**
     * 新增教师评卷信息
     * @param examTeacherGrading 教师评卷实体对象
     */
    @Override
    public void add(ExamTeacherGrading examTeacherGrading) {
        examTeacherGradingMapper.insert(examTeacherGrading);
    }

    /**
     * 根据 ID 删除教师评卷信息
     * @param id 教师评卷记录的 ID
     */
    @Override
    public void deleteById(Integer id) {
        examTeacherGradingMapper.deleteById(id);
    }

    /**
     * 批量删除教师评卷信息
     * @param ids 教师评卷记录的 ID 列表
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            examTeacherGradingMapper.deleteById(id);
        }
    }

    /**
     * 根据 ID 更新教师评卷信息
     * @param examTeacherGrading 包含要更新信息的教师评卷实体对象
     */
    @Override
    public void updateById(ExamTeacherGrading examTeacherGrading) {
        examTeacherGradingMapper.updateById(examTeacherGrading);
    }

    /**
     * 根据 ID 查询教师评卷信息
     * @param id 教师评卷记录的 ID
     * @return 对应的教师评卷实体对象，若未找到则返回 null
     */
    @Override
    public ExamTeacherGrading selectById(Integer id) {
        return examTeacherGradingMapper.selectById(id);
    }

    /**
     * 根据条件查询所有教师评卷信息
     * @param examTeacherGrading 包含查询条件的教师评卷实体对象
     * @return 符合条件的教师评卷信息列表
     */
    @Override
    public List<ExamTeacherGrading> selectAll(ExamTeacherGrading examTeacherGrading) {
        return examTeacherGradingMapper.selectAll(examTeacherGrading);
    }

    /**
     * 分页查询教师评卷信息
     * @param params 包含分页和查询条件的参数对象
     * @return 分页后的教师评卷信息
     */
    @Override
    public PageInfo<ExamTeacherGrading> selectPage(Params params) {
        // 数据转移
        ExamTeacherGrading gradingQuery = new ExamTeacherGrading();
        if (params.getPaperId() != null) {
            gradingQuery.setPaperId(params.getPaperId());
        }

        // 分页设置
        if (params.getPageNum() == null) {
            params.setPageNum(1);
            params.setPageSize(5);
        }
        PageHelper.startPage(params.getPageNum(), params.getPageSize());

        // 执行查询
        List<ExamTeacherGrading> gradingList = examTeacherGradingMapper.selectAll(gradingQuery);
        return new PageInfo<>(gradingList);
    }

    /**
     * 自动分配题目
     *
     * @param examId 试卷管理id
     * @param ids
     */
    @Override
    public void addPlay(Integer examId, List<Integer> ids,String beginTime,String endTime) {
        Set<Integer> set = new HashSet<>(ids);
        // 将 HashSet 中的元素转移到一个新的 ArrayList 中
        ids=new ArrayList<>(set);
        // 查询试题信息
        ExamPaper examPaper = new ExamPaper();
        examPaper.setExamId(examId);
        List<ExamPaper> examPaperList = examPaperMapper.selectAll(examPaper);
        //设置时间
        ExamManage examManage=new ExamManage();
        //时间期限
        examManage.setBeginTime(beginTime);
        examManage.setEndTime(endTime);
        examManage.setId(examId);
        examManageMapper.updateById(examManage);
        // 教师人数
        int teacherNum = ids.size();

        List<ExamTeacherGrading> examTeacherGradingList = new ArrayList<>();

        if (teacherNum > 0) {
            int index = 0;
            for (ExamPaper paper : examPaperList) {
                ExamTeacherGrading examTeacherGrading = new ExamTeacherGrading();
                examTeacherGrading.setPaperId(paper.getId());

                // 按顺序分配教师 ID
                Integer teacherId = ids.get(index % teacherNum);
                examTeacherGrading.setTeacherId(teacherId);

                // 当前时间
                String time = DateUtil.now();
                examTeacherGrading.setTime(time);

                // 试卷ID
                examTeacherGrading.setExamId(examId);

                // 将 examTeacherGrading 添加到列表中
                examTeacherGradingList.add(examTeacherGrading);

                index++;
            }
        }
        for(ExamTeacherGrading examTeacherGrading:examTeacherGradingList){
            examTeacherGradingMapper.insert(examTeacherGrading);
        }
    }

    /**
     * 根据 ID 删除教师评卷信息
     *
     * @param id 教师评卷试卷ID
     */
    @Override
    public void deleteByExamId(Integer id) {
        examTeacherGradingMapper.deleteByExamId(id);
    }

    /**
     * @param examTeacherGrading
     * @return
     */
    @Override
    public List<ExamManage> selectExam(ExamTeacherGrading examTeacherGrading) {
        // 查询该教师的题目
        List<ExamTeacherGrading> examTeacherGradingList = selectAll(examTeacherGrading);
        // 提取 examId，将重复的去除
        Set<Integer> idSet = new HashSet<>();
        List<Integer> ids = new ArrayList<>();
        for (ExamTeacherGrading grading : examTeacherGradingList) {
            Integer examId = grading.getExamId();
            if (idSet.add(examId)) {
                ids.add(examId);
            }
        }
        // 查询课程
        List<ExamManage> examManageList = new ArrayList<>();
        for (Integer id : ids) {
            ExamManage examManage = examManageMapper.selectById(id);
            if (examManage != null) {
                examManageList.add(examManage);
            }
        }
        return examManageList;
    }
}