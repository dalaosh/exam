package com.mc.escommon.entity.info;

import com.mc.escommon.entity.user.Admin;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "公告")
public class Notice {
    @Schema(description = "token参数")
    private String token;
    @Schema(description = "公告id")
    private Integer id;
    @Schema(description = "发布者Id")
    private Integer userId;
    @Schema(description = "公告名称")
    private String name;
    @Schema(description = "公告内容")
    private String content;
    @Schema(description = "公告时间")
    private String time;
    @Schema(description = "发布者")
    private Admin admin;
}
