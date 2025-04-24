package com.mc.esinfo.controller;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.info.Notice;
import com.mc.escommon.entity.info.Params;
import com.mc.escommon.result.Result;
import com.mc.escommon.result.ResultCodeEnum;
import com.mc.esinfo.service.NoticeService;
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
 * * inform
 */

@Slf4j
@RestController
@RequestMapping("info/notice")
@Tag(name = "公告接口",description = "关于公告的操作")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;


    @Operation(summary = "公告的添加", description = "对公告进行添加")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "添加成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "notice", description = "公告信息", required = true),
    })
    @PostMapping("/add")
    public Result add(@RequestBody Notice notice) {
        if (ObjectUtil.isEmpty(notice.getContent())
                || ObjectUtil.isEmpty(notice.getName())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        noticeService.add(notice);
        return Result.success();
    }


    @Operation(summary = "公告的单个删除", description = "对公告进行单个删除")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "id", description = "要删除公告的id", required = true),
    })
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        noticeService.deleteById(id);
        return Result.success();
    }


    @Operation(summary = "公告的批量删除", description = "对公告进行批量删除")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "ids", description = "要删除公告的id列表", required = true),
    })
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        noticeService.deleteBatch(ids);
        return Result.success();
    }


    @Operation(summary = "公告的修改", description = "对公告进行修改")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "修改成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "notice", description = "需要修改的公告信息", required = true),
    })
    @PutMapping("/update")
    public Result updateById(@RequestBody Notice notice) {
        noticeService.updateById(notice);
        return Result.success();
    }


    @Operation(summary = "公告的id查询", description = "对公告进行id查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "id", description = "要查询公告的id", required = true),
    })
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Notice notice = noticeService.selectById(id);
        return Result.success(notice);
    }


    @Operation(summary = "公告的条件查询", description = "对公告进行条件查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "notice", description = "要查询公告的条件", required = true),
    })
    @GetMapping("/selectAll")
    public Result selectAll(Notice notice ) {
        List<Notice> list = noticeService.selectAll(notice);
        return Result.success(list);
    }



    @Operation(summary = "公告的分页查询", description = "对公告进行分页查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "params", description = "要查询公告的分页条件", required = true),
    })
    @GetMapping("/selectPage")
    public Result selectPage(Params params) {
        PageInfo<Notice> page = noticeService.selectPage(params);
        return Result.success(page);
    }
}
