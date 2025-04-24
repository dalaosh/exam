package com.mc.esstatistic.controller;

import com.mc.escommon.result.Result;
import com.mc.esstatistic.service.SelectTeacherUseCountService;
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
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author machao
 * * @datetime 2025/3/17 11:30
 * * inform
 */

@Slf4j
@RestController
@RequestMapping("statistic/selectTeacherUseCount")
@Tag(name = "教师端数据统计接口", description = "关于考试试卷数据的相关操作")
public class SelectTeacherUseCountController {
    @Autowired
    private SelectTeacherUseCountService selectTeacherUseCountService;
    /**
     * 获取考试所有答题数据
     * 根据考试ID获取该考试的所有答题相关数据
     *
     * @param teacherId 考试ID
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
    @GetMapping("/selectQuestionType/{teacherId}")
    public Result selectQuestionType(@PathVariable Integer teacherId) {
        Map<String, Integer> result = selectTeacherUseCountService.selectQuestionType(teacherId);;
        return Result.success(result);
    }

    /**
     * 获取考试所有答题数据
     * 根据考试ID获取该考试的所有答题相关数据
     *
     * @param teacherId 考试ID
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
    @GetMapping("/selectExamAnswerLevel/{teacherId}")
    public Result selectExamAnswerLevel(@PathVariable Integer teacherId) {
        Map<String, Map<String, Integer>> result = selectTeacherUseCountService.selectExamAnswerLevel(teacherId);;
        return Result.success(result);
    }

    /**
     * 获取考试所有答题数据
     * 根据考试ID获取该考试的所有答题相关数据
     *
     * @param teacherId 考试ID
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
    @GetMapping("/selectBasicNum/{teacherId}")
    public Result selectBasicNum(@PathVariable Integer teacherId) {
        Map<String, Integer> result = selectTeacherUseCountService.selectBasicNum(teacherId);;
        return Result.success(result);
    }
}
