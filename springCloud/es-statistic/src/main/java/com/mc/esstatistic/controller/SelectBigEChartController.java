package com.mc.esstatistic.controller;

import com.mc.escommon.entity.exam.ExamManage;
import com.mc.escommon.result.Result;
import com.mc.esstatistic.mapper.SelectBigEChartMapper;
import com.mc.esstatistic.service.SelectBigEChartService;
import com.mc.esstatistic.service.impl.SelectBigEChartServiceImpl;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author machao
 * * @datetime 2025/3/19 20:14
 * * inform
 */
@Slf4j
@RestController
@RequestMapping("statistic/SelectBigEChart")
@Tag(name = "考试试卷数据接口", description = "关于考试试卷数据的相关操作")
public class SelectBigEChartController {
    @Autowired
    private SelectBigEChartService selectBigEChartService;

    /**
     * 获取考试所有答题数据
     * 根据考试ID获取该考试的所有答题相关数据
     * @return 包含答题数据的结果对象
     */
    @Operation(summary = "获取考试所有答题数据", description = "根据考试ID获取该考试的所有答题相关数据")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "获取成功",
                    content = @Content(schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    @Parameters({
            @Parameter(name = "examId", description = "考试ID", required = true)
    })
    @GetMapping("/selectBasicNum")
    public Result selectBasicNum() {
        Map<String, Integer> result = selectBigEChartService.selectBasicNum();;
        return Result.success(result);
    }
    /**
     * 获取考试所有答题数据
     * 根据考试ID获取该考试的所有答题相关数据
     * @return 包含答题数据的结果对象
     */
    @Operation(summary = "获取考试所有答题数据", description = "根据考试ID获取该考试的所有答题相关数据")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "获取成功",
                    content = @Content(schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    @Parameters({
            @Parameter(name = "examId", description = "考试ID", required = true)
    })
    @GetMapping("/selectQuestionNum")
    public Result selectQuestionNum() {
        Map<String, Map<String, Integer>> result = selectBigEChartService.selectQuestionNum();;
        return Result.success(result);
    }
    /**
     * 获取考试所有答题数据
     * 根据考试ID获取该考试的所有答题相关数据
     * @return 包含答题数据的结果对象
     */
    @Operation(summary = "获取考试所有答题数据", description = "根据考试ID获取该考试的所有答题相关数据")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "获取成功",
                    content = @Content(schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    @Parameters({
            @Parameter(name = "examId", description = "考试ID", required = true)
    })
    @GetMapping("/selectExamAll")
    public Result selectExamAll() {
        List<ExamManage> result = selectBigEChartService.selectExamAll();;
        return Result.success(result);
    }

    /**
     * 获取考试所有答题数据
     * 根据考试ID获取该考试的所有答题相关数据
     * @return 包含答题数据的结果对象
     */
    @Operation(summary = "获取考试所有答题数据", description = "根据考试ID获取该考试的所有答题相关数据")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "获取成功",
                    content = @Content(schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    @Parameters({
            @Parameter(name = "examId", description = "考试ID", required = true)
    })
    @GetMapping("/selectStrongAll")
    public Result selectStrongAll() {
        Map<String, Integer> result = selectBigEChartService.selectStrongAll();;
        return Result.success(result);
    }
}
