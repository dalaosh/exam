package com.mc.esinfo.controller;

import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.info.Params;
import com.mc.escommon.entity.info.Log;
import com.mc.escommon.result.Result;
import com.mc.esinfo.service.LogService;
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
 * * @datetime 2025/1/3 23:05
 * * inform
 */

@Slf4j
@RestController
@RequestMapping("info/log")
@Tag(name = "日志接口",description = "进行相关日志的操作")
public class LogController {
    //登录，忘记密码，忘记密码通过qq邮箱找回，
    @Autowired
   private LogService logService;

    @Operation(summary = "日志分页查询，条件查询", description = "对日志进行分页查询，在表格中进行条件查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "params", description = "日志信息", required = true)
    })
    @GetMapping("/selectPage")
    public Result pageSelect(Params params){
        PageInfo<Log> message = logService.selectPage(params);
        return Result.success(message);
    }

    @Operation(summary = "日志添加", description = "自动添加日志，当登录时进行记录")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "添加成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "Log", description = "日志信息", required = true)
    })
    @PostMapping("/insert")
    public Result insert(@RequestBody Log log){
        logService.add(log);
        return Result.success();
    }

    @Operation(summary = "日志单体删除", description = "通过id进行日志的删除")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "id", description = "日志id", required = true)
    })
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        logService.deleteById(id);
        return Result.success();
    }

    @Operation(summary = "日志批量删除", description = "通过id列表进行日志的删除")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "id", description = "多个日志id", required = true)
    })
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        logService.deleteBatch(ids);
        return Result.success();
    }
}
