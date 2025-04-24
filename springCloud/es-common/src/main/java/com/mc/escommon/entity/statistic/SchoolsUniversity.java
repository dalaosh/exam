package com.mc.escommon.entity.statistic;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author machao
 * * @datetime 2025/2/16 20:04
 * * inform
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "学校信息")
public class SchoolsUniversity{
    @Schema(description = "token参数")
    private String token;
    @Schema(description = "id")
    private Integer id;
    @Schema(description = "学校名称")
    private String universityName;
    @Schema(description = "省")
    private Integer provinceId;
    @Schema(description = "市")
    private Integer cityId;
    @Schema(description = "县")
    private Integer areaId;
    @Schema(description = "地址")
    private String address;
    private AddressProvinces addressProvinces;
    private AddressCities addressCities;
    private AddressAreas addressAreas;

}
