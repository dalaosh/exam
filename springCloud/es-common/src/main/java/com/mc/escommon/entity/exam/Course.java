package com.mc.escommon.entity.exam;

import com.mc.escommon.entity.user.Teacher;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author machao
 * * @datetime 2025/2/15 12:48
 * * inform
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "课程信息")
public class Course {
    @Schema(description = "token参数")
    private String token;

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
    @Schema(description = "照片")
    private String photo;
    @Schema(description = "课程编码")
    private String number;
    @Schema(description = "课程信息")
    private String message;
    @Schema(description = "教师")
    private Teacher teacher;

}
