package com.mc.escommon.entity.statistic;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author machao
 * * @datetime 2025/2/10 13:09
 * * inform
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "县表")
public class AddressAreas{
    @Schema(description = "token参数")
    private String token;
    @Schema(description = "id")
    private String id;
    /** id */
    private String areaId;
    @Schema(description = "县名")
    /** 县名 */
    private String area;
    @Schema(description = "城市id")
    /** 城市id */
    private String cityId;
}
