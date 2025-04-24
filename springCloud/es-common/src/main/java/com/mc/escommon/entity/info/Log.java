package com.mc.escommon.entity.info;

import com.mc.escommon.entity.user.CaInstitution;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "日志信息")
public class Log {
    @Schema(description = "token参数")
    private String token;
    @Schema(description = "日志id")
    private Integer id;
    @Schema(description = "用户id")
    private Integer userId;
    @Schema(description = "操作人")
    private String username;
    @Schema(description = "用户角色")
    private String role;
    @Schema(description = "日志名称")
    private String name;
    @Schema(description = "日志时间")
    private String time;
    @Schema(description = "操作ip")
    private String ip;
}
