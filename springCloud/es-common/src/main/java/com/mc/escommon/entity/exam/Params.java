package com.mc.escommon.entity.exam;

import com.mc.escommon.entity.user.Student;
import com.mc.escommon.entity.user.Teacher;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Params {
    @Schema(description = "token")
    private String token;
    @Schema(description = "分页页码")
    private Integer pageNum;
    @Schema(description = "分页大小")
    private Integer pageSize;

    //实体类部分
    @Schema(description = "课程id")
    private Integer id;
    @Schema(description = "教师id")
    private Integer teacherId;
    @Schema(description = "课程名称")
    private String name;
    @Schema(description = "开始时间")
    private String beginTime;
    @Schema(description = "结束时间")
    private String endTime;
    @Schema(description = "教师ids")
    private List<Integer> ids;
    @Schema(description = "照片")
    private String photo;
    @Schema(description = "课程编码")
    private String number;
    @Schema(description = "课程信息")
    private String message;
    @Schema(description = "教师")
    private Teacher teacher;


    @Schema(description = "课程Id")
    private Integer courseId;
    @Schema(description = "学生Id")
    private Integer studentId;
    @Schema(description = "是否加入课程")
    private String isAdd;
    @Schema(description = "课程")
    private Course course;
    @Schema(description = "学生")
    private Student student;

    @Schema(description = "选择题类型")
    private String questionType;
    @Schema(description = "试题内容")
    private String question ;
    @Schema(description = "选项A")
    private String answerA;
    @Schema(description = "选项B")
    private String answerB;
    @Schema(description = "选项C")
    private String answerC;
    @Schema(description = "选项D")
    private String answerD;
    @Schema(description = "正确答案")
    private String rightAnswer;
    @Schema(description = "题目解析")
    private String analysis;
    @Schema(description = "分值")
    private Integer score;
    @Schema(description = "难度等级")
    private String level;
    @Schema(description = "所属章节")
    private String section;

    @Schema(description = "创建时间")
    private String time;
    @Schema(description = "问题")
    private String title;
    @Schema(description = "c/c++")
    private String type;
    @Schema(description = "代码")
    private String codeMessage;
    @Schema(description = "运行信息")
    private String runMessage;


    @Schema(description = "考试试卷id")
    private Integer examId;
    @Schema(description = "试题Id")
    private Integer paperId;
    @Schema(description = "学生答案")
    private String answer;
    @Schema(description = "得分")
    private String markExams;
    @Schema(description = "环签名")
    private String ringSign;
    @Schema(description = "考试试卷")
    private ExamManage examManage;
    @Schema(description = "试题")
    private ExamPaper examPaper;
    @Schema(description = "编程题")
    private QuestionCode questionCode;
    @Schema(description = "填空题")
    private QuestionFill questionFill;
    @Schema(description = "判断题")
    private QuestionJudge questionJudge;
    @Schema(description = "选择题")
    private QuestionMulti questionMulti;

    @Schema(description = "该次考试介绍")
    private String description ;
    @Schema(description = "考试日期")
    private String examDate;
    @Schema(description = "持续时长")
    private Integer totalTime;
    @Schema(description = "总分")
    private Integer totalScore;
    @Schema(description = "考生须知")
    private String tips;
    @Schema(description = "环公钥")
    private String publicKeys;

    @Schema(description = "试卷id")
    private Integer managerId;
    @Schema(description = "问题id")
    private Integer questionId;

    @Schema(description = "考试提交")
    private String isSubmit;
    @Schema(description = "人脸认证照片")
    private String facePath;
    @Schema(description = "人脸认证确认")
    private String faceSubmit;

}
