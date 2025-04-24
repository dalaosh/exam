package com.mc.esexam.controller;

import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.CourseStudent;
import com.mc.escommon.entity.exam.Params;
import com.mc.escommon.result.Result;
import com.mc.esexam.service.CourseStudentService;
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
 * * @datetime 2025/1/13 10:59
 * * inform 课程学生
 */

@Slf4j
@RestController
@RequestMapping("exam/courseStudent")
@Tag(name = "学生课程接口",description = "关于学生课程的操作")
public class CourseStudentController {
    @Autowired
    private CourseStudentService courseStudentService;


    @Operation(summary = "学生课程的添加", description = "对学生课程进行添加")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "添加成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "courseStudent", description = "学生课程信息", required = true),
    })
    @PostMapping("/add")
    public Result add(@RequestBody CourseStudent courseStudent) {
        courseStudentService.add(courseStudent);
        return Result.success();
    }


    @Operation(summary = "学生课程的单体删除", description = "通过id对学生课程进行删除")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "id", description = "要删除学生课程的id", required = true),
    })
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        courseStudentService.deleteById(id);
        return Result.success();
    }


    @Operation(summary = "学生课程的批量删除", description = "通过id列表对学生课程进行批量删除")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "ids", description = "要删除学生课程的id列表", required = true),
    })
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        courseStudentService.deleteBatch(ids);
        return Result.success();
    }


    @Operation(summary = "学生课程的修改", description = "对学生课程进行修改")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "修改成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "courseStudent", description = "需要修改的学生课程信息", required = true),
    })
    @PutMapping("/update")
    public Result updateById(@RequestBody CourseStudent courseStudent) {
        courseStudentService.updateById(courseStudent);
        return Result.success();
    }


    @Operation(summary = "学生课程的id查询", description = "对学生课程进行id查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "id", description = "要查询学生课程的id", required = true),
    })
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        CourseStudent courseStudent = courseStudentService.selectById(id);
        return Result.success(courseStudent);
    }


    @Operation(summary = "学生课程的条件查询", description = "对学生课程进行条件查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "courseStudent", description = "要查询学生课程的条件", required = true),
    })
    @GetMapping("/selectAll")
    public Result selectAll(CourseStudent courseStudent ) {
        List<CourseStudent> list = courseStudentService.selectAll(courseStudent);
        return Result.success(list);
    }


    @Operation(summary = "学生课程的分页查询", description = "对学生课程进行分页查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "params", description = "要查询学生课程的分页条件", required = true),
    })
    @GetMapping("/selectPage")
    public Result selectPage(Params params) {
        PageInfo<CourseStudent> page = courseStudentService.selectPage(params);
        return Result.success(page);
    }
}
