package com.mc.esstatistic.controller;

import com.mc.escommon.result.Result;
import com.mc.esstatistic.service.SelectExamCountService;
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

import java.util.Map;

/**
 * 考试试卷数据接口控制器
 * 提供关于考试试卷数据的相关操作接口
 *
 * @author machao
 * @datetime 2025/3/15 14:17
 */
@Slf4j
@RestController
@RequestMapping("statistic/selectExamCount")
@Tag(name = "考试试卷数据接口", description = "关于考试试卷数据的相关操作")
public class SelectExamCountController {

    @Autowired
    private SelectExamCountService selectExamCountService;

    /**
     * 获取考试所有答题数据
     * 根据考试ID获取该考试的所有答题相关数据
     *
     * @param examId 考试ID
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
    @GetMapping("/selectExamAnswer")
    public Result selectExamAnswer(@RequestParam Integer examId) {
            Map<String, Map<String, Integer>> result = selectExamCountService.selectExamAnswer(examId);;
            return Result.success(result);
    }

    /**
     * 获取指定学生的考试答题数据
     * 根据考试ID和学生ID获取该学生在该考试中的答题相关数据
     *
     * @param examId    考试ID
     * @param studentId 学生ID
     * @return 包含答题数据的结果对象
     */
    @Operation(summary = "获取指定学生的考试答题数据", description = "根据考试ID和学生ID获取该学生在该考试中的答题相关数据")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "获取成功",
                    content = @Content(schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    @Parameters({
            @Parameter(name = "examId", description = "考试ID", required = true),
            @Parameter(name = "studentId", description = "学生ID", required = true)
    })
    @GetMapping("/selectExamAnswerStudent")
    public Result selectExamAnswerStudent(@RequestParam Integer examId, @RequestParam Integer studentId) {
            Map<String, Map<String, Integer>> result = selectExamCountService.selectExamAnswerStudent(examId, studentId);
            return Result.success(result);
    }

    /**
     * 获取指定课程的考试答题统计数据
     * 根据考试ID和课程ID获取该课程在该考试中的答题统计相关数据
     *
     * @param examId  考试ID
     * @param courseId 课程ID
     * @return 包含答题统计数据的结果对象
     */
    @Operation(summary = "获取指定课程的考试答题统计数据", description = "根据考试ID和课程ID获取该课程在该考试中的答题统计相关数据")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "获取成功",
                    content = @Content(schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    @Parameters({
            @Parameter(name = "examId", description = "考试ID", required = true),
            @Parameter(name = "courseId", description = "课程ID", required = true)
    })
    @GetMapping("/selectExamAnswerStatistic")
    public Result selectExamAnswerStatistic(@RequestParam Integer examId, @RequestParam Integer courseId) {
            Map<String, Integer> result = selectExamCountService.selectExamAnswerStatistic(examId, courseId);
            return Result.success(result);
    }
}