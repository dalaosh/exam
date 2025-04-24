package com.mc.escommon.entity.exam;

import com.mc.escommon.entity.user.Student;
import com.mc.escommon.entity.user.Teacher;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author machao
 * * @datetime 2025/2/28 15:58
 * * inform
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "考试学生答题")
public class ExamAnswer {
    @Schema(description = "token参数")
    private String token;

    @Schema(description = "答题id")
    private Integer id;
    @Schema(description = "考试试卷id")
    private Integer examId;
    @Schema(description = "试题Id")
    private Integer paperId;
    @Schema(description = "学生Id")
    private Integer studentId;
    @Schema(description = "学生答案")
    private String answer;
    @Schema(description = "得分")
    private String markExams;
    @Schema(description = "环签名")
    private String ringSign;
    @Schema(description = "提交时间")
    private String time;
    @Schema(description = "标记")
    private String isMarked;
    @Schema(description = "签名类型")
    private String rightSign;

    @Schema(description = "考试题型")
    private String questionType;

    @Schema(description = "考试试卷")
    private ExamManage examManage;
    @Schema(description = "课程")
    private Course course;
    @Schema(description = "教师")
    private Teacher teacher;
    @Schema(description = "学生")
    private Student student;

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
    @Schema(description = "简答题")
    private QuestionShortAns questionShortAns;

}
