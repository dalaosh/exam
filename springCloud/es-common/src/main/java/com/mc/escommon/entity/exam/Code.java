package com.mc.escommon.entity.exam;

import com.mc.escommon.entity.user.Student;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author machao
 * * @datetime 2025/2/23 20:07
 * * inform
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "课程信息")
public class Code {
    @Schema(description = "token参数")
    private String token;
    @Schema(description = "c/c++")
    private String t;
    @Schema(description = "代码")
    private String m;

    @Schema(description = "id")
    private Integer id;
    @Schema(description = "学生id")
    private Integer studentId;
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
    @Schema(description = "学生")
    private Student student;


}
