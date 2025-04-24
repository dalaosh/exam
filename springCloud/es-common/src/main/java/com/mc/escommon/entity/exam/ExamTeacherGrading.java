package com.mc.escommon.entity.exam;

import com.mc.escommon.entity.user.Teacher;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author machao
 * * @datetime 2025/2/28 17:50
 * * inform
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "教师阅卷")
public class ExamTeacherGrading {
    @Schema(description = "token参数")
    private String token;

    @Schema(description = "考试批阅id")
    private Integer id;
    @Schema(description = "批阅题号")
    private Integer paperId;
    @Schema(description = "分配时间")
    private String time;
    @Schema(description = "教师id")
    private Integer teacherId;
    @Schema(description = "试卷id")
    private Integer examId;





    @Schema(description = "考试试卷")
    private ExamManage examManage;
    @Schema(description = "课程")
    private Course course;
    @Schema(description = "试题")
    private ExamPaper ExamPaper;
    @Schema(description = "教师")
    private Teacher teacher;
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
