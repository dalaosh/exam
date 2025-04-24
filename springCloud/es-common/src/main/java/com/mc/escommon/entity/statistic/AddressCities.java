package com.mc.escommon.entity.statistic;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author machao
 * * @datetime 2025/2/10 13:07
 * * inform
 * 市表
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "市表")
public class AddressCities{
    @Schema(description = "token参数")
    private String token;
    /** id */
    @Schema(description = "id")
    private Integer id;
    /** 城市id */
    @Schema(description = "城市id")
    private String cityId;
    /** 城市名称 */
    @Schema(description = "城市名称")
    private String city;
    /** 省份id */
    @Schema(description = "省份id")
    private String provinceId;
}
