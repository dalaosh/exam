package com.mc.escommon.entity.markdown;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Params {
    @Schema(description = "token")
    private String token;
    @Schema(description = "分页页码")
    private Integer pageNum;
    @Schema(description = "分页大小")
    private Integer pageSize;
    @Schema(description = "正文")
    private String content;


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

}
