package com.mc.esinfo.controller;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.info.News;
import com.mc.escommon.entity.info.Params;
import com.mc.escommon.result.Result;
import com.mc.escommon.result.ResultCodeEnum;
import com.mc.esinfo.service.NewsService;
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
@RequestMapping("info/news")
@Tag(name = "新闻接口",description = "关于新闻的操作")
public class NewsController {
    @Autowired
    private NewsService newsService;


    @Operation(summary = "新闻的添加", description = "对新闻进行添加")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "添加成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "news", description = "新闻信息", required = true),
    })
    @PostMapping("/add")
    public Result add(@RequestBody News news) {
        if (ObjectUtil.isEmpty(news.getContent())
                || ObjectUtil.isEmpty(news.getName())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        newsService.add(news);
        return Result.success();
    }


    @Operation(summary = "新闻的单体删除", description = "通过id对新闻进行删除")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "id", description = "要删除新闻的id", required = true),
    })
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        newsService.deleteById(id);
        return Result.success();
    }


    @Operation(summary = "新闻的批量删除", description = "通过id列表对新闻进行批量删除")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "ids", description = "要删除新闻的id列表", required = true),
    })
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        newsService.deleteBatch(ids);
        return Result.success();
    }


    @Operation(summary = "新闻的修改", description = "对新闻进行修改")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "修改成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "news", description = "需要修改的新闻信息", required = true),
    })
    @PutMapping("/update")
    public Result updateById(@RequestBody News news) {
        newsService.updateById(news);
        return Result.success();
    }


    @Operation(summary = "新闻的id查询", description = "对新闻进行id查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "id", description = "要查询新闻的id", required = true),
    })
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        News news = newsService.selectById(id);
        return Result.success(news);
    }


    @Operation(summary = "新闻的条件查询", description = "对新闻进行条件查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "news", description = "要查询新闻的条件", required = true),
    })
    @GetMapping("/selectAll")
    public Result selectAll(News news ) {
        List<News> list = newsService.selectAll(news);
        return Result.success(list);
    }


    @Operation(summary = "新闻的分页查询", description = "对新闻进行分页查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "params", description = "要查询新闻的分页条件", required = true),
    })
    @GetMapping("/selectPage")
    public Result selectPage(Params params) {
        PageInfo<News> page = newsService.selectPage(params);
        return Result.success(page);
    }
}
