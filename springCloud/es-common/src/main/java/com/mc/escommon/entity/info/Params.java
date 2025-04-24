package com.mc.escommon.entity.info;

import com.mc.escommon.entity.user.Admin;
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

    //实体类部分
    @Schema(description = "id")
    private Integer id;
    @Schema(description = "用户id")
    private Integer userId;
    @Schema(description = "用户角色")
    private String role;
    @Schema(description = "名称")
    private String name;
    @Schema(description = "时间")
    private String time;
    @Schema(description = "操作人")
    private String username;
    @Schema(description = "操作ip")
    private String ip;
    @Schema(description = "内容")
    private String content;
    @Schema(description = "发布者")
    private Admin admin;
}
