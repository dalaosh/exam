package com.mc.escommon.entity.statistic;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author machao
 * * @datetime 2025/2/16 20:09
 * * inform
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "专业信息")
public class SchoolsMajor{
    @Schema(description = "token参数")
    private String token;
    @Schema(description = "id")
    private Integer id;
    @Schema(description = "专业名称")
    private String majorName;
    @Schema(description = "学院id")
    private Integer collegesId;
    @Schema(description = "学校")
    private SchoolsUniversity schoolsUniversity;
    @Schema(description = "学院")
    private SchoolsColleges schoolsColleges;
    @Schema(description = "学校id")
    private Integer universityId;
    @Schema(description = "省")
    private Integer provinceId;
    @Schema(description = "市")
    private Integer cityId;
    @Schema(description = "县")
    private Integer areaId;
    private AddressProvinces addressProvinces;
    private AddressCities addressCities;
    private AddressAreas addressAreas;
}
