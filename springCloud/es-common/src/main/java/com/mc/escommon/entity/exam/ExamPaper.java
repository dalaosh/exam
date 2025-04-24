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
@Schema(description = "考试试题管理")
public class ExamPaper {
    @Schema(description = "token参数")
    private String token;

    @Schema(description = "考试试题id")
    private Integer id;
    @Schema(description = "考试试卷id")
    private Integer examId;
    @Schema(description = "考试试卷")
    private ExamManage examManage;
    @Schema(description = "课程")
    private Course course;
    @Schema(description = "教师")
    private Teacher teacher;

    @Schema(description = "题目类型")
    private String questionType;
    @Schema(description = "题目id")
    private Integer questionId;
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
