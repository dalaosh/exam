package com.mc.escommon.entity.info;

import com.mc.escommon.entity.user.Admin;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "新闻")
public class News {
    @Schema(description = "token参数")
    private String token;
    @Schema(description = "新闻id")
    private Integer id;
    @Schema(description = "发布者ID")
    private Integer userId;
    @Schema(description = "新闻名称")
    private String name;
    @Schema(description = "新闻内容")
    private String content;
    @Schema(description = "新闻时间")
    private String time;
    @Schema(description = "发布者")
    private Admin admin;
}
