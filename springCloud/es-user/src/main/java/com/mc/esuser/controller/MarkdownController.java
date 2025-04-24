package com.mc.esuser.controller;

import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.markdown.Markdown;
import com.mc.escommon.entity.markdown.Params;
import com.mc.escommon.result.Result;
import com.mc.esuser.service.MarkdownService;
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
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

/**
 * @author machao
 * * @datetime 2025/1/15 0:06
 * * inform
 */
@Slf4j
@RestController
@RefreshScope
@RequestMapping("md")
@Tag(name = "markdown接口",description = "通过邮箱辅助验证")
public class MarkdownController {
    @Autowired
    private MarkdownService markdownService;

    /**
     * 添加
     */
    @Operation(summary = "md文档的添加", description = "对md文档进行添加")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "添加成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "params", description = "md的相关信息", required = true),
    })
    @PostMapping("/add")
    public Result addFile(@RequestBody Params params){
        markdownService.add(params);
        return Result.success();
    }
    /**
     * 进行修改
     */
    @Operation(summary = "md文档的修改", description = "对md文档进行修改")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "修改成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "params", description = "md的相关信息", required = true),
    })
    @PutMapping("/update")
    public Result update(@RequestBody Params params){
        markdownService.updateById(params);
        return Result.success();
    }

    /**
     * md单个删除
     */
    @Operation(summary = "md文档的删除", description = "对md文档进行删除")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "id", description = "md文档的id", required = true),
    })
    @DeleteMapping("/delete/{id}")
    public Result delete(@RequestBody String role,@PathVariable Integer id){
        markdownService.deleteById(id,role);
        return Result.success();
    }


    @Operation(summary = "md文档的分页查询", description = "对md文档进行分页查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "params", description = "params的分页查询", required = true),
    })
    //进行分页查询
    @GetMapping("/selectPage")
    public Result selectPage(Params params) {
        PageInfo<Markdown> page = markdownService.selectPage(params);
        return Result.success(page);
    }

    @Operation(summary = "md文档的条件查询", description = "对md文档进行条件查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "params", description = "params的条件查询", required = true),
    })
    @PostMapping("/content")
    public Result selectContent(@RequestBody Params params){
        String content = markdownService.selectContent(params.getAddress());
        return Result.success(content);
    }
}
