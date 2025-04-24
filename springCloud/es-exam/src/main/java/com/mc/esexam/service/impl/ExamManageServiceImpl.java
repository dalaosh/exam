package com.mc.esexam.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.ExamManage;
import com.mc.escommon.entity.exam.Params;
import com.mc.escommon.entity.user.Teacher;
import com.mc.escommon.result.ResultCodeEnum;
import com.mc.esexam.exception.CustomException;
import com.mc.esexam.mapper.ExamManageMapper;
import com.mc.esexam.mapper.TeacherMapper;
import com.mc.esexam.service.ExamManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author machao
 * @datetime 2025/3/1 0:31
 * @inform 考试管理服务实现类
 */
@Service
public class ExamManageServiceImpl implements ExamManageService {

    @Autowired
    private ExamManageMapper examManageMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    /**
     * 新增考试管理信息
     * @param examManage 考试管理实体对象
     */
    @Override
    public void add(ExamManage examManage) {
        //查询所有的公钥
        List<Teacher> teacherList=teacherMapper.selectAll(new Teacher());

        // 合并环公钥
        StringBuilder mergedString = new StringBuilder();
        for (Teacher teacher : teacherList) {
            mergedString.append(teacher.getName()).append(":").append(teacher.getPublicKey()).append(", ");
        }

        // 删除最后的逗号和空格
        if (!mergedString.isEmpty()) {
            mergedString.delete(mergedString.length() - 2, mergedString.length());
        }

        // 输出合并后的字符串
        System.out.println(mergedString.toString());
        //添加公钥信息
        examManage.setPublicKeys(mergedString.toString());
        examManage.setIsMarked("否");
        examManage.setIsSubmit("否");
        examManageMapper.insert(examManage);
    }

    /**
     * 根据 ID 删除考试管理信息
     * @param id 考试管理记录的 ID
     */
    @Override
    public void deleteById(Integer id) {
        examManageMapper.deleteById(id);
    }

    /**
     * 批量删除考试管理信息
     * @param ids 考试管理记录的 ID 列表
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            examManageMapper.deleteById(id);
        }
    }

    /**
     * 根据 ID 更新考试管理信息
     * @param examManage 包含要更新信息的考试管理实体对象
     */
    @Override
    public void updateById(ExamManage examManage) {
        examManageMapper.updateById(examManage);
    }

    /**
     * 根据 ID 查询考试管理信息
     * @param id 考试管理记录的 ID
     * @return 对应的考试管理实体对象，若未找到则返回 null
     */
    @Override
    public ExamManage selectById(Integer id) {
        return examManageMapper.selectById(id);
    }

    /**
     * 查询所有考试管理信息
     * @param examManage 包含查询条件的考试管理实体对象
     * @return 符合条件的考试管理信息列表
     */
    @Override
    public List<ExamManage> selectAll(ExamManage examManage) {
        return examManageMapper.selectAll(examManage);
    }

    /**
     * 分页查询考试管理信息
     * @param params 包含分页和查询条件的参数对象
     * @return 分页后的考试管理信息
     */
    @Override
    public PageInfo<ExamManage> selectPage(Params params) {
        // 进行数据转移
        ExamManage examManage = new ExamManage();
        if (params.getCourseId() != null) {
            examManage.setCourseId(params.getCourseId()); // 课程 ID
        }
        if (params.getExamDate() != null) {
            examManage.setExamDate(params.getExamDate()); // 考试日期
        }
        if (params.getTotalTime() != null) {
            examManage.setTotalTime(params.getTotalTime()); // 总时长
        }
        if (params.getTotalScore() != null) {
            examManage.setTotalScore(params.getTotalScore()); // 总分
        }
        if (params.getType() != null && !Objects.equals(params.getType(), "")) {
            examManage.setType(params.getType()); // 考试类型
        }
        if (params.getTips() != null && !Objects.equals(params.getTips(), "")) {
            examManage.setTips(params.getTips()); // 提示信息
        }
        if (params.getPublicKeys() != null && !Objects.equals(params.getPublicKeys(), "")) {
            examManage.setPublicKeys(params.getPublicKeys()); // 公钥
        }
        if (params.getTeacherId() != null) {
            examManage.setTeacherId(params.getTeacherId()); // 公钥
        }

        // 开启分页查询
        if (params.getPageNum() == null) {
            params.setPageNum(1);
            params.setPageSize(5);
        }
        PageHelper.startPage(params.getPageNum(), params.getPageSize());

        // 接下来的查询会自动按照当前开启的分页设置来查询
        List<ExamManage> list = examManageMapper.selectAll(examManage);
        return PageInfo.of(list);
    }

    /**
     * 根据 ID 查询考试管理信息环公钥
     *
     * @param id 考试管理记录的 ID
     */
    @Override
    public List<Teacher> getPublicKeys(Integer id) {
        //查询环公钥字符串
        ExamManage examManage=examManageMapper.selectById(id);
        String publicKeys= examManage.getPublicKeys();
        //数据操作
        if(Objects.equals(publicKeys, "") ||publicKeys==null){
            throw new CustomException(ResultCodeEnum.EXCEL_DATA_NOFOUND);
        }
        // 拆分字符串并创建EntryForm对象列表
        List<Teacher> teacherList = new ArrayList<>();
        String[] pairs = publicKeys.split(", ");
        for (String pair : pairs) {
            String[] parts = pair.split(":");
            if (parts.length == 2) {
                Teacher teacher = new Teacher();
                teacher.setName(parts[0]);
                teacher.setPublicKey(parts[1]);
                teacherList.add(teacher);
            }
        }

        return teacherList;
    }
}