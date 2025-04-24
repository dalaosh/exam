package com.mc.escommon.entity.exam;

import com.mc.escommon.entity.user.Student;
import com.mc.escommon.entity.user.Teacher;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author machao
 * * @datetime 2025/2/28 19:51
 * * inform
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "学生分数信息")
public class ExamScore {
    @Schema(description = "token参数")
    private String token;

    @Schema(description = "分数id")
    private Integer id;
    @Schema(description = "试卷id")
    private Integer managerId;
    @Schema(description = "学生id")
    private Integer studentId;
    @Schema(description = "分数")
    private Integer score;

    @Schema(description = "试卷")
    private ExamManage examManage;
    @Schema(description = "学生")
    private Student student;

    @Schema(description = "课程")
    private Course course;
    @Schema(description = "教师")
    private Teacher teacher;

}
