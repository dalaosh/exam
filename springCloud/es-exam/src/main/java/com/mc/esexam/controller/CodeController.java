package com.mc.esexam.controller;

import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.Code;
import com.mc.escommon.entity.exam.Params;
import com.mc.escommon.result.Result;
import com.mc.esexam.service.CodeService;
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
 * @datetime 2025/2/24 15:54
 * @inform 代码相关接口
 */
@Slf4j
@RestController
@RequestMapping("exam/code")
@Tag(name = "代码接口", description = "关于代码的操作")
public class CodeController {

    @Autowired
    private CodeService codeService;

    @Operation(summary = "代码的添加", description = "对代码进行添加")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "添加成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "code", description = "代码信息", required = true)
    })
    @PostMapping("/add")
    public Result add(@RequestBody Code code) {
        codeService.add(code);
        return Result.success();
    }

    @Operation(summary = "代码的单体删除", description = "通过 id 对代码进行删除")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "id", description = "要删除代码的 id", required = true)
    })
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        codeService.deleteById(id);
        return Result.success();
    }

    @Operation(summary = "代码的批量删除", description = "通过 id 列表对代码进行批量删除")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "ids", description = "要删除代码的 id 列表", required = true)
    })
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        codeService.deleteBatch(ids);
        return Result.success();
    }

    @Operation(summary = "代码的修改", description = "对代码进行修改")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "修改成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "code", description = "需要修改的代码信息", required = true)
    })
    @PutMapping("/update")
    public Result updateById(@RequestBody Code code) {
        codeService.updateById(code);
        return Result.success();
    }

    @Operation(summary = "代码的 id 查询", description = "对代码进行 id 查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "id", description = "要查询代码的 id", required = true)
    })
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Code code = codeService.selectById(id);
        return Result.success(code);
    }

    @Operation(summary = "代码的条件查询", description = "对代码进行条件查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "code", description = "要查询代码的条件", required = true)
    })
    @GetMapping("/selectAll")
    public Result selectAll(Code code) {
        List<Code> list = codeService.selectAll(code);
        return Result.success(list);
    }

    @Operation(summary = "代码的分页查询", description = "对代码进行分页查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "params", description = "要查询代码的分页条件", required = true)
    })
    @GetMapping("/selectPage")
    public Result selectPage(Params params) {
        PageInfo<Code> page = codeService.selectPage(params);
        return Result.success(page);
    }
}