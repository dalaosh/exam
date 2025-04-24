package com.mc.esexam.controller;

import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.ExamManage;
import com.mc.escommon.entity.exam.ExamTeacherGrading;
import com.mc.escommon.entity.exam.Params;
import com.mc.escommon.result.Result;
import com.mc.esexam.service.ExamTeacherGradingService;
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

import java.util.Arrays;
import java.util.List;

/**
 * @author machao
 * @datetime 2025/3/1 0:54
 * @inform 教师评卷相关操作的控制器
 */
@Slf4j
@RestController
@RequestMapping("exam/examTeacherGrading")
@Tag(name = "教师评卷接口", description = "关于教师评卷的操作")
public class ExamTeacherGradingController {

    @Autowired
    private ExamTeacherGradingService examTeacherGradingService;

    @Operation(summary = "新增教师评卷信息", description = "向系统中添加一条新的教师评卷记录")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "添加成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "examTeacherGrading", description = "教师评卷信息", required = true)
    })
    @PostMapping("/add")
    public Result add(@RequestBody ExamTeacherGrading examTeacherGrading) {
        examTeacherGradingService.add(examTeacherGrading);
        return Result.success();
    }

    @Operation(summary = "删除单个教师评卷记录", description = "根据评卷记录的 ID 删除指定的教师评卷信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "id", description = "要删除的教师评卷记录的 ID", required = true)
    })
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        examTeacherGradingService.deleteById(id);
        return Result.success();
    }

    @Operation(summary = "批量删除教师评卷记录", description = "根据评卷记录的 ID 列表批量删除教师评卷信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "ids", description = "要删除的教师评卷记录的 ID 列表", required = true)
    })
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        examTeacherGradingService.deleteBatch(ids);
        return Result.success();
    }

    @Operation(summary = "修改教师评卷记录", description = "根据评卷记录的 ID 更新教师评卷的相关信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "修改成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "examTeacherGrading", description = "包含要更新信息的教师评卷对象", required = true)
    })
    @PutMapping("/update")
    public Result updateById(@RequestBody ExamTeacherGrading examTeacherGrading) {
        examTeacherGradingService.updateById(examTeacherGrading);
        return Result.success();
    }

    @Operation(summary = "根据 ID 查询教师评卷记录", description = "根据评卷记录的 ID 查询指定的教师评卷信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "id", description = "要查询的教师评卷记录的 ID", required = true)
    })
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        ExamTeacherGrading examTeacherGrading = examTeacherGradingService.selectById(id);
        return Result.success(examTeacherGrading);
    }

    @Operation(summary = "根据条件查询教师评卷记录", description = "根据指定条件查询符合要求的教师评卷列表")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "examTeacherGrading", description = "查询条件对象", required = true)
    })
    @GetMapping("/selectAll")
    public Result selectAll(ExamTeacherGrading examTeacherGrading) {
        List<ExamTeacherGrading> list = examTeacherGradingService.selectAll(examTeacherGrading);
        return Result.success(list);
    }

    @Operation(summary = "分页查询教师评卷记录", description = "根据分页参数和查询条件分页查询教师评卷列表")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "params", description = "分页和查询条件对象", required = true)
    })
    @GetMapping("/selectPage")
    public Result selectPage(Params params) {
        PageInfo<ExamTeacherGrading> page = examTeacherGradingService.selectPage(params);
        return Result.success(page);
    }

    @Operation(summary = "新增教师评卷信息自动", description = "向系统中添加一条新的教师评卷记录")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "添加成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "examTeacherGrading", description = "教师评卷信息", required = true)
    })
    @PostMapping("/addPlay/{examId}")
    public Result addPlay(@PathVariable Integer examId,@RequestBody Params params) {
        List<Integer> ids=params.getIds();
        String beginTime= params.getBeginTime();
        String endTime= params.getEndTime();
        examTeacherGradingService.addPlay(examId,ids,beginTime,endTime);
        return Result.success();
    }

    @Operation(summary = "通过试卷删除教师评卷记录", description = "根据评卷记录的 ID 删除指定的教师评卷信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "id", description = "要删除的试卷ID", required = true)
    })
    @DeleteMapping("/deleteByExamId/{id}")
    public Result deleteByExamId(@PathVariable Integer id) {
        examTeacherGradingService.deleteByExamId(id);
        return Result.success();
    }

    @Operation(summary = "查询课程", description = "根据指定条件查询课程列表")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "examTeacherGrading", description = "查询条件对象", required = true)
    })
    @GetMapping("/selectExam")
    public Result selectExam(ExamTeacherGrading examTeacherGrading) {
        List<ExamManage> list = examTeacherGradingService.selectExam(examTeacherGrading);

        return Result.success(list);
    }
}