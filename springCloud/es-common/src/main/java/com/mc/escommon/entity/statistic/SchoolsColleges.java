package com.mc.escommon.entity.statistic;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author machao
 * * @datetime 2025/2/16 20:07
 * * inform
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "学院信息")
public class SchoolsColleges{
    @Schema(description = "token参数")
    private String token;
    @Schema(description = "id")
    private Integer id;
    @Schema(description = "学院名称")
    private String collegesName;
    @Schema(description = "学校id")
    private Integer universityId;
    @Schema(description = "省")
    private Integer provinceId;
    @Schema(description = "市")
    private Integer cityId;
    @Schema(description = "县")
    private Integer areaId;
    @Schema(description = "学校")
    private SchoolsUniversity schoolsUniversity;
    private AddressProvinces addressProvinces;
    private AddressCities addressCities;
    private AddressAreas addressAreas;
}
