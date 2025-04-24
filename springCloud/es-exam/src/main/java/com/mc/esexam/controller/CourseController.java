package com.mc.esexam.controller;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.Course;
import com.mc.escommon.entity.exam.Params;
import com.mc.escommon.result.Result;
import com.mc.escommon.result.ResultCodeEnum;
import com.mc.esexam.service.CourseService;
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
 * * inform 课程
 */

@Slf4j
@RestController
@RequestMapping("exam/course")
@Tag(name = "课程接口",description = "关于课程的操作")
public class CourseController {
    @Autowired
    private CourseService courseService;


    @Operation(summary = "课程的添加", description = "对课程进行添加")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "添加成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "course", description = "课程信息", required = true),
    })
    @PostMapping("/add")
    public Result add(@RequestBody Course course) {
        if (ObjectUtil.isEmpty(course.getPhoto())
                || ObjectUtil.isEmpty(course.getName())
                || ObjectUtil.isEmpty(course.getBeginTime())
                || ObjectUtil.isEmpty(course.getEndTime())
                || ObjectUtil.isEmpty(course.getNumber())
                || ObjectUtil.isEmpty(course.getMessage())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        courseService.add(course);
        return Result.success();
    }


    @Operation(summary = "课程的单体删除", description = "通过id对课程进行删除")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "id", description = "要删除课程的id", required = true),
    })
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        courseService.deleteById(id);
        return Result.success();
    }


    @Operation(summary = "课程的批量删除", description = "通过id列表对课程进行批量删除")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "ids", description = "要删除课程的id列表", required = true),
    })
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        courseService.deleteBatch(ids);
        return Result.success();
    }


    @Operation(summary = "课程的修改", description = "对课程进行修改")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "修改成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "course", description = "需要修改的课程信息", required = true),
    })
    @PutMapping("/update")
    public Result updateById(@RequestBody Course course) {
        courseService.updateById(course);
        return Result.success();
    }


    @Operation(summary = "课程的id查询", description = "对课程进行id查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "id", description = "要查询课程的id", required = true),
    })
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Course course = courseService.selectById(id);
        return Result.success(course);
    }

    @Operation(summary = "课程的编号查询", description = "对课程进行编号查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "number", description = "要查询课程的编号", required = true),
    })
    @GetMapping("/selectByNumber/{number}")
    public Result selectById(@PathVariable String number) {
        Course course = courseService.selectByNumber(number);
        return Result.success(course);
    }


    @Operation(summary = "课程的条件查询", description = "对课程进行条件查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "course", description = "要查询课程的条件", required = true),
    })
    @GetMapping("/selectAll")
    public Result selectAll(Course course ) {
        List<Course> list = courseService.selectAll(course);
        return Result.success(list);
    }


    @Operation(summary = "课程的分页查询", description = "对课程进行分页查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "params", description = "要查询课程的分页条件", required = true),
    })
    @GetMapping("/selectPage")
    public Result selectPage(Params params) {
        PageInfo<Course> page = courseService.selectPage(params);
        return Result.success(page);
    }

    @Operation(summary = "生成课程编码", description = "对课程编码进行生成")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "生成成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "params", description = "条件", required = true),
    })
    @GetMapping("/random")
    public Result random(Params params){
        String random=courseService.Random();
        while (true){
            Course course=new Course();
            course.setNumber(random);
            List<Course> courseList=courseService.selectAll(course);
            if(courseList.isEmpty()){
                break;
            }
            else {
                random=courseService.Random();
            }
        }
        return Result.success(random);
    }

    @Operation(summary = "课程的查询", description = "对课程进行查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "params", description = "要查询课程的分页条件", required = true),
    })
    @GetMapping("/selectAllById")
    public Result selectAllById(Params params) {
        List<Course> page = courseService.selectAllById(params);
        return Result.success(page);
    }
}
