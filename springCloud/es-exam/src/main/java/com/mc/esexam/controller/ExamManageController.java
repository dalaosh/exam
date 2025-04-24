package com.mc.esexam.controller;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.ExamManage;
import com.mc.escommon.entity.exam.Params;
import com.mc.escommon.entity.user.Teacher;
import com.mc.escommon.result.Result;
import com.mc.escommon.result.ResultCodeEnum;
import com.mc.esexam.service.ExamManageService;
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
 * @datetime 2025/3/1 0:51
 * @inform 考试管理相关操作的控制器
 */
@Slf4j
@RestController
@RequestMapping("exam/examManage")
@Tag(name = "考试管理接口", description = "关于考试管理的操作")
public class ExamManageController {

    @Autowired
    private ExamManageService examManageService;

    @Operation(summary = "考试管理信息的添加", description = "对考试管理信息进行添加")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "添加成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "examManage", description = "考试管理信息", required = true)
    })
    @PostMapping("/add")
    public Result add(@RequestBody ExamManage examManage) {
        if (ObjectUtil.isEmpty(examManage.getCourseId())
                || ObjectUtil.isEmpty(examManage.getTotalTime())
                || ObjectUtil.isEmpty(examManage.getExamDate())
                || ObjectUtil.isEmpty(examManage.getType())
                || ObjectUtil.isEmpty(examManage.getTips())
                || ObjectUtil.isEmpty(examManage.getDescription())
                || ObjectUtil.isEmpty(examManage.getTotalScore())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        examManageService.add(examManage);
        return Result.success();
    }

    @Operation(summary = "考试试卷的公钥查看", description = "对考试试卷的公钥进行查看")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "id", description = "考试管理信息id", required = true)
    })
    @PostMapping("/getPublicKeys/{id}")
    public Result getPublicKeys(@PathVariable Integer id) {
        List<Teacher> examManageList=examManageService.getPublicKeys(id);
        return Result.success(examManageList);
    }

    @Operation(summary = "考试管理信息的单体删除", description = "通过id对考试管理信息进行删除")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "id", description = "要删除考试管理信息的id", required = true)
    })
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        examManageService.deleteById(id);
        return Result.success();
    }

    @Operation(summary = "考试管理信息的批量删除", description = "通过id列表对考试管理信息进行批量删除")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "ids", description = "要删除考试管理信息的id列表", required = true)
    })
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        examManageService.deleteBatch(ids);
        return Result.success();
    }

    @Operation(summary = "考试管理信息的修改", description = "对考试管理信息进行修改")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "修改成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "examManage", description = "需要修改的考试管理信息", required = true)
    })
    @PutMapping("/update")
    public Result updateById(@RequestBody ExamManage examManage) {
        examManageService.updateById(examManage);
        return Result.success();
    }

    @Operation(summary = "考试管理信息的id查询", description = "对考试管理信息进行id查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "id", description = "要查询考试管理信息的id", required = true)
    })
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        ExamManage examManage = examManageService.selectById(id);
        return Result.success(examManage);
    }

    @Operation(summary = "考试管理信息的条件查询", description = "对考试管理信息进行条件查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "examManage", description = "要查询考试管理信息的条件", required = true)
    })
    @GetMapping("/selectAll")
    public Result selectAll(ExamManage examManage) {
        List<ExamManage> list = examManageService.selectAll(examManage);
        return Result.success(list);
    }

    @Operation(summary = "考试管理信息的分页查询", description = "对考试管理信息进行分页查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "params", description = "要查询考试管理信息的分页条件", required = true)
    })
    @GetMapping("/selectPage")
    public Result selectPage(Params params) {
        PageInfo<ExamManage> page = examManageService.selectPage(params);
        return Result.success(page);
    }
}