package com.mc.escommon.entity.exam;

import com.mc.escommon.entity.user.Student;
import com.mc.escommon.entity.user.Teacher;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author machao
 * * @datetime 2025/3/23 10:37
 * * inform
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "学生考试安排表")
public class ExamStudent {
    @Schema(description = "token参数")
    private String token;

    @Schema(description = "学生考试id")
    private Integer id;
    @Schema(description = "试卷id")
    private Integer examId;
    @Schema(description = "学生id")
    private Integer studentId;
    @Schema(description = "考试提交")
    private String isSubmit;
    @Schema(description = "人脸认证照片")
    private String facePath;
    @Schema(description = "人脸认证确认")
    private String faceSubmit;

    private Integer courseId;
    private String type;

    @Schema(description = "试卷")
    private ExamManage examManage;
    @Schema(description = "学生")
    private Student student;

    @Schema(description = "课程")
    private Course course;
    @Schema(description = "教师")
    private Teacher teacher;
}
