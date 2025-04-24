package com.mc.escommon.entity.exam;

import cn.hutool.core.annotation.Alias;
import com.mc.escommon.entity.user.Teacher;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * @author machao
 * * @datetime 2025/2/28 15:48
 * * inform
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "考试试卷管理")
public class ExamManage {
    @Schema(description = "token参数")
    private String token;

    @Schema(description = "试题编号")
    private Integer id;
    @Schema(description = "该次考试介绍")
    private String description ;
    @Schema(description = "考试课程Id")
    private Integer courseId;
    @Schema(description = "考试日期")
    private String examDate;
    @Schema(description = "持续时长")
    private Integer totalTime;
    @Schema(description = "总分")
    private Integer totalScore;
    @Schema(description = "考试类型")
    private String type;
    @Schema(description = "考生须知")
    private String tips;
    @Schema(description = "环公钥")
    private String publicKeys;
    @Schema(description = "开始时间")
    private String beginTime;
    @Schema(description = "结束时间")
    private String endTime;
    @Schema(description = "是否批阅完成")
    private String isMarked;
    @Schema(description = "是否发布")
    private String isSubmit;

    @Schema(description = "课程")
    private Course course;
    @Schema(description = "教师id")
    private Integer teacherId;
    @Schema(description = "教师")
    private Teacher teacher;
}
