package com.mc.escommon.entity.statistic;

import com.mc.escommon.entity.user.Admin;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    //实体类部分
    @Schema(description = "id")
    private Integer id;
    @Schema(description = "学院名称")
    private String collegesName;
    @Schema(description = "学校id")
    private Integer universityId;
    @Schema(description = "学校")
    private SchoolsUniversity schoolsUniversity;
    @Schema(description = "专业名称")
    private String majorName;
    @Schema(description = "学院id")
    private Integer collegesId;
    @Schema(description = "学校")
    private SchoolsColleges schoolsColleges;
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
