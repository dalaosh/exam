package com.mc.estrustSeal.controller;

import com.mc.escommon.entity.statistic.AddressAreas;
import com.mc.escommon.entity.statistic.AddressCities;
import com.mc.escommon.entity.statistic.AddressProvinces;
import com.mc.escommon.result.Result;
import com.mc.estrustSeal.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("trustSeal/address")
@Tag(name = "地区接口", description = "关于地区信息数据的相关操作，如查询省、市、县信息等")
public class AddressController {

    @Autowired
    private AddressService addressService;

    /**
     * 查询省信息
     *
     * @param addressProvinces 包含查询条件的省信息实体，可用于筛选符合条件的省份
     * @return 返回包含符合条件的省信息列表的统一结果对象
     */
    @Operation(summary = "查询省信息", description = "根据传入的省信息实体查询符合条件的省份列表")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = Result.class)))
    })
    @Parameters({
            @Parameter(name = "addressProvinces", description = "包含查询条件的省信息实体，可根据实体中的属性筛选省份", required = true)
    })
    @GetMapping("/provinces")
    public Result selectProvinces(AddressProvinces addressProvinces) {
        List<AddressProvinces> list = addressService.selectProvinces(addressProvinces);
        return Result.success(list);
    }

    /**
     * 查询市信息
     *
     * @param addressCities 包含查询条件的市信息实体，可用于筛选符合条件的市
     * @return 返回包含符合条件的市信息列表的统一结果对象
     */
    @Operation(summary = "查询市信息", description = "根据传入的市信息实体查询符合条件的市列表")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = Result.class)))
    })
    @Parameters({
            @Parameter(name = "addressCities", description = "包含查询条件的市信息实体，可根据实体中的属性筛选市", required = true)
    })
    @GetMapping("/cities")
    public Result selectCities(AddressCities addressCities) {
        List<AddressCities> list = addressService.selectCities(addressCities);
        return Result.success(list);
    }

    /**
     * 查询县信息
     *
     * @param addressAreas 包含查询条件的县信息实体，可用于筛选符合条件的县
     * @return 返回包含符合条件的县信息列表的统一结果对象
     */
    @Operation(summary = "查询县信息", description = "根据传入的县信息实体查询符合条件的县列表")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = Result.class)))
    })
    @Parameters({
            @Parameter(name = "addressAreas", description = "包含查询条件的县信息实体，可根据实体中的属性筛选县", required = true)
    })
    @GetMapping("/areas")
    public Result selectAreas(AddressAreas addressAreas) {
        List<AddressAreas> list = addressService.selectAreas(addressAreas);
        return Result.success(list);
    }

}