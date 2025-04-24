package com.mc.escommon.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "信息查询")
public class ProcessInfo {
    private Integer age;
    private Integer gender;
}
