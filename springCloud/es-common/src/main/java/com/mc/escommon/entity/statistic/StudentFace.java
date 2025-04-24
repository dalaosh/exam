package com.mc.escommon.entity.statistic;

import com.mc.escommon.entity.user.Student;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author machao
 * @datetime 2025/3/23 11:02
 * inform
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "人脸认证信息")
public class StudentFace {

    @Schema(description = "唯一标识")
    private Integer id;

    @Schema(description = "学生id")
    private Integer studentId;

    @Schema(description = "人脸唯一标识")
    private String faceId;

    @Schema(description = "人脸特征")
    private byte[] faceFeature;

    @Schema(description = "人脸照片路径")
    private String facePath;

    @Schema(description = "身份证照片路径")
    private String idPath;

    @Schema(description = "省id")
    private Integer provinceId;

    @Schema(description = "市id")
    private Integer cityId;

    @Schema(description = "县id")
    private Integer areaId;

    @Schema(description = "学校id")
    private Integer universityId;

    @Schema(description = "学院id")
    private Integer collegesId;

    @Schema(description = "专业id")
    private Integer majorId;

    @Schema(description = "身份认证审核")
    private String isSubmit;

    private String file;
    private String account;
    private String name;
    private String sex;
    private String phone;
    private String email;
    private Integer similarValue;

    private Student student;
    private AddressProvinces addressProvinces;
    private AddressCities addressCities;
    private AddressAreas addressAreas;
    private SchoolsUniversity schoolsUniversity;
    private SchoolsColleges schoolsColleges;
    private SchoolsMajor schoolsMajor;
}