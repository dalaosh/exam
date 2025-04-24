package com.mc.escommon.entity.markdown;

import com.mc.escommon.entity.user.Admin;
import com.mc.escommon.entity.user.Student;
import com.mc.escommon.entity.user.Teacher;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "markdown文档")
public class Markdown {
    @Schema(description = "token参数")
    private String token;
    @Schema(description = "mdId")
    private Integer id;
    @Schema(description = "创建者角色")
    private String role;
    @Schema(description = "创建者id")
    private Integer userId;
    @Schema(description = "题目")
    private String name;
    @Schema(description = "文档地址")
    private String address;
    @Schema(description = "时间")
    private String time;
    @Schema(description = "管理员")
    private Admin admin;
    @Schema(description = "教师")
    private Teacher teacher;
    @Schema(description = "学生")
    private Student student;

}
