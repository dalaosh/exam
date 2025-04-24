package com.mc.esexam.service.impl;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mc.escommon.SmUtils.api.FileWithKeyReadAndWriteAPI;
import com.mc.escommon.SmUtils.api.RingSignConnectAPI;
import com.mc.escommon.SmUtils.api.Sm2API;
import com.mc.escommon.entity.exam.ExamAnswer;
import com.mc.escommon.entity.exam.ExamManage;
import com.mc.escommon.entity.exam.ExamTeacherGrading;
import com.mc.escommon.entity.exam.Params;
import com.mc.escommon.entity.user.Teacher;
import com.mc.escommon.result.ResultCodeEnum;
import com.mc.escommon.result.RoleEnum;
import com.mc.esexam.exception.CustomException;
import com.mc.esexam.mapper.ExamAnswerMapper;
import com.mc.esexam.mapper.ExamManageMapper;
import com.mc.esexam.service.ExamAnswerService;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author machao
 * @datetime 2025/3/1 0:30
 * @inform 考试答案服务实现类
 */
@Service
public class ExamAnswerServiceImpl implements ExamAnswerService {

    @Autowired
    private ExamAnswerMapper examAnswerMapper;
    @Autowired
    private ExamManageMapper examManageMapper;

    /**
     * 新增
     */
    @Override
    public void add(ExamAnswer examAnswer) {
        String time = DateUtil.now();
        examAnswer.setTime(time);
        examAnswerMapper.insert(examAnswer);
    }

    /**
     * 删除
     */
    @Override
    public void deleteById(Integer id) {
        examAnswerMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            examAnswerMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    @Override
    public void updateById(ExamAnswer examAnswer) {
        String time = DateUtil.now();
        examAnswer.setTime(time);
        examAnswerMapper.updateById(examAnswer);
    }

    /**
     * 根据ID查询
     */
    @Override
    public ExamAnswer selectById(Integer id) {
        return examAnswerMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    @Override
    public List<ExamAnswer> selectAll(ExamAnswer examAnswer) {
        return examAnswerMapper.selectAll(examAnswer);
    }

    /**
     * 分页查询
     */
    @Override
    public PageInfo<ExamAnswer> selectPage(Params params) {
        // 进行数据转移
        ExamAnswer examAnswer = new ExamAnswer();
        if (params.getExamId() != null) {
            examAnswer.setExamId(params.getExamId()); // 考试ID
        }
        if (params.getPaperId() != null) {
            examAnswer.setPaperId(params.getPaperId()); // 试卷ID
        }
        if (params.getStudentId() != null) {
            examAnswer.setStudentId(params.getStudentId()); // 学生ID
        }
        if (params.getAnswer() != null && !Objects.equals(params.getAnswer(), "")) {
            examAnswer.setAnswer(params.getAnswer()); // 答案
        }
        if (params.getMarkExams() != null) {
            examAnswer.setMarkExams(params.getMarkExams()); // 评分
        }
        if (params.getRingSign() != null && !Objects.equals(params.getRingSign(), "")) {
            examAnswer.setRingSign(params.getRingSign()); // 环形签名
        }
        if (params.getTime() != null) {
            examAnswer.setTime(params.getTime()); // 时间
        }

        // 开启分页查询
        if (params.getPageNum() == null) {
            params.setPageNum(1);
            params.setPageSize(5);
        }
        PageHelper.startPage(params.getPageNum(), params.getPageSize());

        // 接下来的查询会自动按照当前开启的分页设置来查询
        List<ExamAnswer> list = examAnswerMapper.selectAll(examAnswer);
        return PageInfo.of(list);
    }

    /**
     * 阅卷判分
     */
    @Override
    public void updateByScore(Params params) {
        ExamAnswer examAnswerScore=new ExamAnswer();
        //id
        examAnswerScore.setId(params.getId());
        //考试分数
        examAnswerScore.setMarkExams(params.getMarkExams());
        String generate = ringSign(params.getTeacherId(), params.getExamId());
        //添加签名
        examAnswerScore.setRingSign(generate);
        //对公钥进行拆分
        examAnswerMapper.updateById(examAnswerScore);
    }


    /**
     * 自动判分
     */
    @Override
    public void updateByMultiAll(List<ExamTeacherGrading> examTeacherGradinglist) {

        for (ExamTeacherGrading teacherGrading:examTeacherGradinglist ){
            //查询问题
            ExamAnswer examAnswer=new ExamAnswer();
            examAnswer.setExamId(teacherGrading.getExamId());
            examAnswer.setPaperId(teacherGrading.getPaperId());
            List<ExamAnswer> examAnswerList=examAnswerMapper.selectAll(examAnswer);
            for (ExamAnswer answer:examAnswerList){
                if(answer.getMarkExams()==null|| answer.getMarkExams().equals("")){
                    ExamAnswer ans=new ExamAnswer();
                    ans.setId(answer.getId());
                    Integer score=answer.getQuestionMulti().getScore();
                    String answers=answer.getQuestionMulti().getRightAnswer();
                    if(Objects.equals(answers, answer.getAnswer())){
                        ans.setMarkExams(String.valueOf(score));
                    }
                    else {
                        ans.setMarkExams("0");
                    }
                    String generate = ringSign(teacherGrading.getTeacherId(),teacherGrading.getExamId());
                    //添加签名
                    ans.setRingSign(generate);
                    examAnswerMapper.updateById(ans);
                }
            }
        }
    }

    @Override
    public void updateByJudgeAll(List<ExamTeacherGrading> examTeacherGradinglist) {
        for (ExamTeacherGrading teacherGrading:examTeacherGradinglist ){
            //查询问题
            ExamAnswer examAnswer=new ExamAnswer();
            examAnswer.setExamId(teacherGrading.getExamId());
            examAnswer.setPaperId(teacherGrading.getPaperId());
            List<ExamAnswer> examAnswerList=examAnswerMapper.selectAll(examAnswer);
            for (ExamAnswer answer:examAnswerList){
                if(answer.getMarkExams()==null|| answer.getMarkExams().equals("")){
                    ExamAnswer ans=new ExamAnswer();
                    ans.setId(answer.getId());
                    Integer score=answer.getQuestionJudge().getScore();
                    String answers=answer.getQuestionJudge().getAnswer();
                    if(Objects.equals(answers, answer.getAnswer())){
                        ans.setMarkExams(String.valueOf(score));
                    }
                    else {
                        ans.setMarkExams("0");
                    }
                    String generate = ringSign(teacherGrading.getTeacherId(),teacherGrading.getExamId());
                    //添加签名
                    ans.setRingSign(generate);
                    examAnswerMapper.updateById(ans);
                }
            }
        }
    }
    /**
     * 自动判分审核时
     */
    @Override
    public void updateByMultiAllAnswer(List<ExamAnswer> examAnswerList) {
        for (ExamAnswer answer:examAnswerList){
            if(answer.getMarkExams()==null|| answer.getMarkExams().equals("")){
                ExamAnswer ans=new ExamAnswer();
                ans.setId(answer.getId());
                Integer score=answer.getQuestionMulti().getScore();
                String answers=answer.getQuestionMulti().getRightAnswer();
                if(Objects.equals(answers, answer.getAnswer())){
                    ans.setMarkExams(String.valueOf(score));
                }
                else {
                    ans.setMarkExams("0");
                }
                String generate = ringSign(answer.getCourse().getTeacherId(),answer.getExamId());
                //添加签名
                ans.setRingSign(generate);
                examAnswerMapper.updateById(ans);
            }
        }
    }

    @Override
    public void updateByJudgeAllAnswer(List<ExamAnswer> examAnswerList) {
        for (ExamAnswer answer:examAnswerList){
            if(answer.getMarkExams()==null|| answer.getMarkExams().equals("")){
                ExamAnswer ans=new ExamAnswer();
                ans.setId(answer.getId());
                Integer score=answer.getQuestionJudge().getScore();
                String answers=answer.getQuestionJudge().getAnswer();
                if(Objects.equals(answers, answer.getAnswer())){
                    ans.setMarkExams(String.valueOf(score));
                }
                else {
                    ans.setMarkExams("0");
                }
                String generate = ringSign(answer.getCourse().getTeacherId(),answer.getExamId());
                //添加签名
                ans.setRingSign(generate);
                examAnswerMapper.updateById(ans);
            }
        }
    }

    /**
     * 查询未答题数
     *
     * @param examAnswer
     */
    @Override
    public List<ExamAnswer> selectNoMark(ExamAnswer examAnswer) {
        return examAnswerMapper.selectNoMark(examAnswer);
    }

    /**
     * 查询已答题数
     *
     * @param examAnswer
     */
    @Override
    public List<ExamAnswer> selectIsMark(ExamAnswer examAnswer) {
        return examAnswerMapper.selectIsMark(examAnswer);
    }

    /**
     * 查询未验签题数
     *
     * @param examAnswer
     */
    @Override
    public List<ExamAnswer> selectNoRightSign(ExamAnswer examAnswer) {
        return examAnswerMapper.selectNoRightSign(examAnswer);
    }

    /**
     * 查询已验签题数
     *
     * @param examAnswer
     */
    @Override
    public List<ExamAnswer> selectIsRightSign(ExamAnswer examAnswer) {
        return examAnswerMapper.selectIsRightSign(examAnswer);
    }

    /**
     * 批量验签
     *
     * @param examAnswerList
     */
    @Override
    public void selectSetRightSignAll(List<ExamAnswer> examAnswerList) {
        for (ExamAnswer examAnswer:examAnswerList){
            if(examAnswer.getRightSign()==null|| examAnswer.getRightSign().isEmpty()){
                Integer examAnswerId=examAnswer.getId();
                Integer examId=examAnswer.getExamId();
                boolean verification = verification(examAnswerId, examId);
                ExamAnswer ans=new ExamAnswer();
                ans.setId(examAnswerId);
                if(verification){
                    ans.setRightSign("验签成功");
                }
                else{
                    ans.setRightSign("验签失败");
                }
                examAnswerMapper.updateById(ans);
            }
        }
    }

    /**
     * 单个验签
     *
     * @param examAnswer
     */
    @Override
    public void selectSetRightSign(ExamAnswer examAnswer) {
        if(examAnswer.getRightSign()==null|| !examAnswer.getRightSign().equals("验签成功")){
            Integer examAnswerId=examAnswer.getId();
            Integer examId=examAnswer.getExamId();
            boolean verification = verification(examAnswerId, examId);
            ExamAnswer ans=new ExamAnswer();
            ans.setId(examAnswerId);
            if(verification){
                ans.setRightSign("验签成功");
            }
            else{
                ans.setRightSign("验签失败");
            }
            examAnswerMapper.updateById(ans);
        }
    }

    //签名
    private String ringSign(Integer teacherId,Integer examId){
        //签名
        //查询教师信息
        Teacher teacher=examAnswerMapper.selectTeacherById(teacherId);
        //查询环公钥 publicKeysList字符串
        ExamManage examManage=examManageMapper.selectById(examId);
        String publicKeysList=examManage.getPublicKeys();
        //数据操作,拆分环公钥
        if(Objects.equals(publicKeysList, "") ||publicKeysList==null){
            throw new CustomException(ResultCodeEnum.EXCEL_DATA_NOFOUND);
        }

        // 拆分字符串并创建列表publicKeys
        List<String> publicKeys = new ArrayList<>();
        String[] pairs = publicKeysList.split(", ");
        for (String pair : pairs) {
            String[] parts = pair.split(":");
            if (parts.length == 2) {
                publicKeys.add(parts[1]);
            }
        }
        //查询自己的公钥和私钥
        String url= RoleEnum.teacher.name()+"/"+teacher.getAccount()+"/exam";
        try {
            FileWithKeyReadAndWriteAPI.readKey(url);
        } catch (CertificateException | NoSuchAlgorithmException | InvalidKeySpecException | IOException |
                 NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
        BCECPrivateKey privateKey=FileWithKeyReadAndWriteAPI.getPrivateKey();
        BCECPublicKey publicKey=FileWithKeyReadAndWriteAPI.getPublicKey();
        String pubKey= Sm2API.pub2str(publicKey);
        int pi=-1;
        for (int i=0;i<publicKeys.size();i++){
            if(Objects.equals(pubKey, publicKeys.get(i))){
                pi=i+1;
                System.out.println(Objects.equals(pubKey, publicKeys.get(pi)));
                break;
            }
        }
        if(pi==-1){
            throw new CustomException(ResultCodeEnum.EXCEL_DATA_NOFOUND);
        }

        //对格式进行转化
        List<BCECPublicKey> pkList=new ArrayList<>();
        for (String pk:publicKeys){
            try {
                pkList.add(Sm2API.str2pub(pk));
            }catch (Exception e){
                throw new CustomException(ResultCodeEnum.EXCEL_DATA_NOFOUND);
            }
        }
        //进行签名
        String ringSign = RingSignConnectAPI.generate("exam", pkList, privateKey, pi);
        return ringSign;
    }//

    //验签
    private boolean verification(Integer examAnswerId,Integer examId){
        //签名信息
        ExamAnswer examAnswer=examAnswerMapper.selectById(examAnswerId);
        String ringSign=examAnswer.getRingSign();
        //查询环公钥 publicKeysList字符串
        ExamManage examManage=examManageMapper.selectById(examId);
        String publicKeysList=examManage.getPublicKeys();
        //数据操作,拆分环公钥
        if(Objects.equals(publicKeysList, "") ||publicKeysList==null){
            throw new CustomException(ResultCodeEnum.EXCEL_DATA_NOFOUND);
        }
        //查询自己的公钥和私钥
        String url= RoleEnum.teacher.name()+"/"+examManage.getTeacher().getAccount()+"/exam";
        try {
            FileWithKeyReadAndWriteAPI.readKey(url);
        } catch (CertificateException | NoSuchAlgorithmException | InvalidKeySpecException | IOException |
                 NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
        // 拆分字符串并创建列表publicKeys
        List<String> publicKeys = new ArrayList<>();
        String[] pairs = publicKeysList.split(", ");
        for (String pair : pairs) {
            String[] parts = pair.split(":");
            if (parts.length == 2) {
                publicKeys.add(parts[1]);
            }
        }

        //对格式进行转化
        List<BCECPublicKey> pkList=new ArrayList<>();
        for (String pk:publicKeys){
            try {
                pkList.add(Sm2API.str2pub(pk));
            }catch (Exception e){
                throw new CustomException(ResultCodeEnum.EXCEL_DATA_NOFOUND);
            }
        }
        return RingSignConnectAPI.verifySign("exam",pkList,ringSign);
    }

}
