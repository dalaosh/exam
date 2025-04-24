package com.mc.escommon.entity.exam;

import com.mc.escommon.entity.user.Student;
import com.mc.escommon.entity.user.Teacher;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author machao
 * * @datetime 2025/2/15 13:37
 * * inform
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "课程学生关联信息")
public class CourseStudent {
    @Schema(description = "token参数")
    private String token;
    @Schema(description = "课程关联id")
    private Integer id;
    @Schema(description = "课程Id")
    private Integer courseId;
    @Schema(description = "学生Id")
    private Integer studentId;
    @Schema(description = "是否加入课程")
    private String isAdd;
    @Schema(description = "课程")
    private Course course;
    @Schema(description = "教师")
    private Teacher teacher;
    @Schema(description = "学生")
    private Student student;
}
