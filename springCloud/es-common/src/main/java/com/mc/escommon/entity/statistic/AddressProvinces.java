package com.mc.escommon.entity.statistic;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author machao
 * * @datetime 2025/2/10 13:02
 * * inform
 * 省表
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "省表")
public class AddressProvinces{
    @Schema(description = "token参数")
    private String token;
    /** id */
    @Schema(description = "id")
    private Integer id;
    /** 省份id */
    @Schema(description = "省份id")
    private String provinceId;
    /** 省份名称 */
    @Schema(description = "省份名称")
    private String province;
    /** 省份简称 */
    @Schema(description = "省份简称")
    private String provinceEasy;
}
