package com.mc.esexam.controller;

import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.ExamPaper;
import com.mc.escommon.entity.exam.Params;
import com.mc.escommon.result.Result;
import com.mc.esexam.service.ExamPaperService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author machao
 * @datetime 2025/3/1 0:52
 * @inform 考试试卷相关操作的控制器
 */
@Slf4j
@RestController
@RequestMapping("exam/examPaper")
@Tag(name = "考试试卷接口", description = "关于考试试卷的操作")
public class ExamPaperController {

    @Autowired
    private ExamPaperService examPaperService;

    @Operation(summary = "新增考试试卷", description = "向系统中添加一份新的考试试卷")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "添加成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "examPaper", description = "考试试卷信息", required = true)
    })
    @PostMapping("/add")
    public Result add(@RequestBody ExamPaper examPaper) {
        examPaperService.add(examPaper);
        return Result.success();
    }

    @Operation(summary = "删除单个考试试卷", description = "根据试卷 ID 删除指定的考试试卷")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "id", description = "要删除的考试试卷的 ID", required = true)
    })
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        examPaperService.deleteById(id);
        return Result.success();
    }

    @Operation(summary = "批量删除考试试卷", description = "根据试卷 ID 列表批量删除考试试卷")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "ids", description = "要删除的考试试卷的 ID 列表", required = true)
    })
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        examPaperService.deleteBatch(ids);
        return Result.success();
    }

    @Operation(summary = "修改考试试卷信息", description = "根据试卷 ID 更新考试试卷的相关信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "修改成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "examPaper", description = "包含要更新信息的考试试卷对象", required = true)
    })
    @PutMapping("/update")
    public Result updateById(@RequestBody ExamPaper examPaper) {
        examPaperService.updateById(examPaper);
        return Result.success();
    }

    @Operation(summary = "根据 ID 查询考试试卷", description = "根据试卷 ID 查询指定的考试试卷信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "id", description = "要查询的考试试卷的 ID", required = true)
    })
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        ExamPaper examPaper = examPaperService.selectById(id);
        return Result.success(examPaper);
    }

    @Operation(summary = "根据条件查询考试试卷", description = "根据指定条件查询符合要求的考试试卷列表")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "examPaper", description = "查询条件对象", required = true)
    })
    @GetMapping("/selectAll")
    public Result selectAll(ExamPaper examPaper) {
        List<ExamPaper> list = examPaperService.selectAll(examPaper);
        return Result.success(list);
    }

    @Operation(summary = "分页查询考试试卷", description = "根据分页参数和查询条件分页查询考试试卷列表")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "params", description = "分页和查询条件对象", required = true)
    })
    @GetMapping("/selectPage")
    public Result selectPage(Params params) {
        PageInfo<ExamPaper> page = examPaperService.selectPage(params);
        return Result.success(page);
    }

    @Operation(summary = "分页查询考试试卷", description = "根据分页参数和查询条件分页查询考试试卷列表")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "params", description = "分页和查询条件对象", required = true)
    })
    @GetMapping("/addAll")
    public Result addAll(@Param("multi") Integer multi,
                         @Param("judge") Integer judge,
                         @Param("fill") Integer fill,
                         @Param("shortAns") Integer shortAns,
                         @Param("code") Integer code,
                         @Param("courseId") Integer courseId,
                         @Param("examId") Integer examId) {
        System.out.println("multi:"+multi+"judge:"+judge+"fill:"+fill+"shortAns:"+shortAns+"code:"+code+"courseId:"+courseId+"examId:"+examId);

        examPaperService.addAll(multi,judge,fill,shortAns,code,courseId,examId);
        return Result.success();
    }
}
