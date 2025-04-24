package com.mc.esexam.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.CourseStudent;
import com.mc.escommon.entity.exam.ExamManage;
import com.mc.escommon.entity.exam.ExamStudent;
import com.mc.escommon.entity.exam.Params;
import com.mc.escommon.realFile.RealFaceImageUtils;
import com.mc.escommon.realFile.TestFaceImageUtils;
import com.mc.escommon.result.Result;
import com.mc.escommon.result.ResultCodeEnum;
import com.mc.esexam.mapper.CourseStudentMapper;
import com.mc.esexam.mapper.ExamManageMapper;
import com.mc.esexam.mapper.ExamStudentMapper;
import com.mc.esexam.service.ExamStudentService;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author machao
 * @datetime 2025/3/1 0:33
 * @inform 考试学生服务实现类
 */
@Service
public class ExamStudentServiceImpl implements ExamStudentService {

    @Autowired
    private ExamStudentMapper examStudentMapper;
    @Autowired
    private CourseStudentMapper courseStudentMapper;
    @Autowired
    private ExamManageMapper examManageMapper;

    /**
     * 新增考试学生信息
     * @param examStudent 要新增的考试学生实体
     */
    @Override
    public void add(ExamStudent examStudent) {
        examStudentMapper.insert(examStudent);
    }

    /**
     * 根据 ID 删除考试学生信息
     * @param id 要删除的考试学生的 ID
     */
    @Override
    public void deleteById(Integer id) {
        examStudentMapper.deleteById(id);
    }

    /**
     * 批量删除考试学生信息
     * @param ids 要删除的考试学生 ID 列表
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            examStudentMapper.deleteById(id);
        }
    }

    /**
     * 根据 ID 更新考试学生信息
     * @param examStudent 包含更新信息的考试学生实体
     */
    @Override
    public void updateById(ExamStudent examStudent) {
        examStudentMapper.updateById(examStudent);
    }

    /**
     * 根据 ID 查询考试学生信息
     * @param id 要查询的考试学生的 ID
     * @return 对应的考试学生实体，如果未找到则返回 null
     */
    @Override
    public ExamStudent selectById(Integer id) {
        return examStudentMapper.selectById(id);
    }

    /**
     * 根据条件查询所有考试学生信息
     * @param examStudent 包含查询条件的考试学生实体
     * @return 符合条件的考试学生列表
     */
    @Override
    public List<ExamStudent> selectAll(ExamStudent examStudent) {
        return examStudentMapper.selectAll(examStudent);
    }

    /**
     * 分页查询考试学生信息
     * @param params 包含分页和查询条件的参数对象
     * @return 分页后的考试学生信息
     */
    @Override
    public PageInfo<ExamStudent> selectPage(Params params) {
        // 数据转移，将 Params 中的条件设置到 ExamStudent 对象中
        ExamStudent studentQuery = new ExamStudent();
        if (params.getStudentId() != null) {
            studentQuery.setStudentId(params.getStudentId());
        }
        if (params.getExamId() != null) {
            studentQuery.setExamId(params.getExamId());
        }
        if (params.getIsSubmit() != null) {
            studentQuery.setIsSubmit(params.getIsSubmit());
        }
        if (params.getFaceSubmit() != null && !params.getFaceSubmit().isEmpty()) {
            studentQuery.setFaceSubmit(params.getFaceSubmit());
        }
        if (params.getCourseId() != null) {
            studentQuery.setCourseId(params.getCourseId());
        }
        if (params.getType() != null && !params.getType().isEmpty()) {
            studentQuery.setType(params.getType());
        }

        // 分页设置
        if (params.getPageNum() == null) {
            params.setPageNum(1);
            params.setPageSize(5);
        }
        PageHelper.startPage(params.getPageNum(), params.getPageSize());

        // 执行查询
        List<ExamStudent> studentList = examStudentMapper.selectAll(studentQuery);
        return new PageInfo<>(studentList);
    }

    /**
     * 批量添加学生
     * @param courseStudentList 课程中的学生
     * @param examId 试卷id
     */
    @Override
    public void batchInsert(List<CourseStudent> courseStudentList, Integer examId) {
        for (CourseStudent courseStudent : courseStudentList) {
            ExamStudent examStudent = new ExamStudent();
            examStudent.setExamId(examId);
            examStudent.setStudentId(courseStudent.getStudentId());
            examStudentMapper.insert(examStudent);
        }
    }

    /**
     * 检查学生是否已提交考试
     * @param studentId 学生ID
     * @param examId 考试ID
     * @return true表示已提交，false表示未提交
     */
    @Override
    public boolean checkIsSubmitted(Integer studentId, Integer examId) {
        ExamStudent query = new ExamStudent();
        query.setStudentId(studentId);
        query.setExamId(examId);
        query.setIsSubmit("是");
        List<ExamStudent> students = examStudentMapper.selectAll(query);
        if (!students.isEmpty()) {
            ExamStudent student = students.get(0);
            return true;
        }
        return false;
    }

    /**
     */
    @Override
    public void addStudentBatch(Integer id) {
        ExamManage examManage=examManageMapper.selectById(id);
        CourseStudent courseStudent=new CourseStudent();
        courseStudent.setCourse(examManage.getCourse());
        List<CourseStudent> courseStudentList=courseStudentMapper.selectAll(courseStudent);
        for (CourseStudent courseStudent1:courseStudentList){
            ExamStudent examStudent=new ExamStudent();
            examStudent.setStudentId(courseStudent1.getStudentId());
            examStudent.setExamId(id);
            examStudent.setIsSubmit("未提交");
            List<ExamStudent> examStudentList=examStudentMapper.selectAll(examStudent);
            if(examStudentList.isEmpty()){
                examStudentMapper.insert(examStudent);
            }
        }
        ExamManage manage=new ExamManage();
        manage.setId(id);
        manage.setIsSubmit("是");
        examManageMapper.updateById(manage);
    }

    /**
     * @param examStudent
     */
    @Override
    public void updateSubmit(ExamStudent examStudent) {
        String face=examStudent.getFacePath();
        String imageUrl = null;
        TestFaceImageUtils imageUtils=new TestFaceImageUtils();
        try {
            imageUrl = imageUtils.saveBase64Image(face);
        } catch (IOException e) {
            Result.error(ResultCodeEnum.EMAIL_CODE_ERROR);
        }
        examStudent.setFacePath(imageUrl);
        examStudentMapper.updateById(examStudent);
    }

    /**
     * @param examStudent
     */
    @Override
    public void updateSubmitByTeacher(ExamStudent examStudent) {
        examStudentMapper.updateById(examStudent);
    }
}