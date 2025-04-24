package com.mc.escommon.entity.exam;

import cn.hutool.core.annotation.Alias;
import com.mc.escommon.entity.user.Teacher;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * @author machao
 * * @datetime 2025/2/19 21:08
 * * inform
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "选择题题库")
public class QuestionMulti {
    @Schema(description = "token参数")
    private String token;

    @Schema(description = "试题编号")
    private Integer id;
    @Alias("考试科目Id")
    @Schema(description = "考试科目Id")
    private Integer courseId;
    @Alias("选择题类型")
    @Schema(description = "选择题类型")
    private String questionType;
    @Alias("试题内容")
    @Schema(description = "试题内容")
    private String question ;
    @Alias("选项A")
    @Schema(description = "选项A")
    private String answerA;
    @Alias("选项B")
    @Schema(description = "选项B")
    private String answerB;
    @Alias("选项C")
    @Schema(description = "选项C")
    private String answerC;
    @Alias("选项D")
    @Schema(description = "选项D")
    private String answerD;
    @Alias("正确答案")
    @Schema(description = "正确答案")
    private String rightAnswer;
    @Alias("题目解析")
    @Schema(description = "题目解析")
    private String analysis;
    @Alias("分值")
    @Schema(description = "分值")
    private Integer score;
    @Alias("难度等级")
    @Schema(description = "难度等级")
    private String level;
    @Alias("所属章节")
    @Schema(description = "所属章节")
    private String section;
    @Schema(description = "课程")
    private Course course;
    @Schema(description = "教师")
    private Teacher teacher;
}
