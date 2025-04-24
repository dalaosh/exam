package com.mc.esexam.controller;

import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.CourseStudent;
import com.mc.escommon.entity.exam.ExamScore;
import com.mc.escommon.entity.exam.Params;
import com.mc.escommon.result.Result;
import com.mc.esexam.service.ExamScoreService;
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

import java.util.List;

/**
 * @author machao
 * @datetime 2025/3/1 0:53
 * @inform 考试成绩相关操作的控制器
 */
@Slf4j
@RestController
@RequestMapping("exam/examScore")
@Tag(name = "考试成绩接口", description = "关于考试成绩的操作")
public class ExamScoreController {

    @Autowired
    private ExamScoreService examScoreService;

    @Operation(summary = "新增考试成绩", description = "向系统中添加一条新的考试成绩记录")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "添加成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "examScore", description = "考试成绩信息", required = true)
    })
    @PostMapping("/add")
    public Result add(@RequestBody ExamScore examScore) {
        examScoreService.add(examScore);
        return Result.success();
    }

    @Operation(summary = "删除单个考试成绩记录", description = "根据成绩记录的 ID 删除指定的考试成绩")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "id", description = "要删除的考试成绩记录的 ID", required = true)
    })
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        examScoreService.deleteById(id);
        return Result.success();
    }

    @Operation(summary = "批量删除考试成绩记录", description = "根据成绩记录的 ID 列表批量删除考试成绩")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "ids", description = "要删除的考试成绩记录的 ID 列表", required = true)
    })
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        examScoreService.deleteBatch(ids);
        return Result.success();
    }

    @Operation(summary = "修改考试成绩记录", description = "根据成绩记录的 ID 更新考试成绩的相关信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "修改成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "examScore", description = "包含要更新信息的考试成绩对象", required = true)
    })
    @PutMapping("/update")
    public Result updateById(@RequestBody ExamScore examScore) {
        examScoreService.updateById(examScore);
        return Result.success();
    }

    @Operation(summary = "根据 ID 查询考试成绩记录", description = "根据成绩记录的 ID 查询指定的考试成绩信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "id", description = "要查询的考试成绩记录的 ID", required = true)
    })
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        ExamScore examScore = examScoreService.selectById(id);
        return Result.success(examScore);
    }

    @Operation(summary = "根据条件查询考试成绩记录", description = "根据指定条件查询符合要求的考试成绩列表")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "examScore", description = "查询条件对象", required = true)
    })
    @GetMapping("/selectAll")
    public Result selectAll(ExamScore examScore) {
        List<ExamScore> list = examScoreService.selectAll(examScore);
        return Result.success(list);
    }

    @Operation(summary = "分页查询考试成绩记录", description = "根据分页参数和查询条件分页查询考试成绩列表")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "params", description = "分页和查询条件对象", required = true)
    })
    @GetMapping("/selectPage")
    public Result selectPage(Params params) {
        PageInfo<ExamScore> page = examScoreService.selectPage(params);
        return Result.success(page);
    }

    @Operation(summary = "分页查询考试成绩记录", description = "根据分页参数和查询条件分页查询考试成绩列表")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "params", description = "分页和查询条件对象", required = true)
    })
    @PostMapping("/batchInsert/{examId}")
    public Result batchInsert(@RequestBody List<CourseStudent> courseStudentList, @PathVariable Integer examId) {
        examScoreService.batchInsert(courseStudentList,examId);
        return Result.success();
    }

    @Operation(summary = "分页查询考试成绩记录", description = "根据分页参数和查询条件分页查询考试成绩列表")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "params", description = "分页和查询条件对象", required = true)
    })
    @PostMapping("/setScore/{examId}")
    public Result setScore(@PathVariable Integer examId) {
        examScoreService.setScore(examId);
        return Result.success();
    }
}