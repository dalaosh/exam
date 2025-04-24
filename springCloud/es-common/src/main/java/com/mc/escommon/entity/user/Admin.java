package com.mc.escommon.entity.user;

import cn.hutool.core.annotation.Alias;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "管理员")
public class Admin {

    @Schema(description = "管理员id")
    private Integer id;
    @Alias("角色")
    @Schema(description = "角色")
    private String role;
    @Schema(description = "头像")
    private String photo;
    @Alias("账号")
    @Schema(description = "账号")
    private String account;
    @Alias("密码")
    @Schema(description = "密码")
    private String password;
    @Alias("密码摘要")
    @Schema(description = "密码摘要")
    private String keySm3;
    @Alias("电子邮箱")
    @Schema(description = "电子邮箱")
    private String email;
    @Alias("姓名")
    @Schema(description = "姓名")
    private String name;
    @Alias("性别")
    @Schema(description = "性别")
    private String sex;
    @Alias("电话")
    @Schema(description = "电话")
    private String phone;
    @Schema(description = "token参数")
    private String token;
}
