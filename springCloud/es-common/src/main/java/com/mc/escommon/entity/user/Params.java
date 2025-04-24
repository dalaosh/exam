package com.mc.escommon.entity.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

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


//    角色部分
    @Schema(description = "id")
    private Integer id;
    @Schema(description = "角色")
    private String role;
    @Schema(description = "头像")
    private String photo;
    @Schema(description = "账号")
    private String account;
    @Schema(description = "密码")
    private String password;
    @Schema(description = "密码摘要")
    private String keySm3;
    @Schema(description = "电子邮箱")
    private String email;
    @Schema(description = "姓名")
    private String name;
    @Schema(description = "性别")
    private String sex;
    @Schema(description = "电话")
    private String phone;
    @Schema(description = "公钥")
    private String publicKey;
    @Schema(description = "图形验证码")
    private String verCode;
    @Schema(description = "邮箱验证码")
    private String mailCode;
    @Schema(description = "图形随机数")
    private String key1;
    @Schema(description = "邮箱随机数")
    private String key2;
}
