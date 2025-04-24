package com.mc.esstatistic.controller;

import com.mc.escommon.result.Result;
import com.mc.esstatistic.service.SelectUserCountService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 该控制器主要用于处理与用户统计相关的请求，提供了查询考试答题统计信息的接口
 *
 * @author machao
 * @datetime 2025/3/16 16:30
 */
@Slf4j
@RestController
@RequestMapping("statistic/selectUserCount")
@Tag(name = "用户统计信息接口", description = "提供与用户统计相关的接口，例如查询考试答题统计信息")
public class SelectUserCountController {

    @Autowired
    private SelectUserCountService selectUserCountService;

    /**
     * 查询考试答题统计信息
     * @return 返回包含统计结果的通用结果对象
     */
    @Operation(summary = "查询考试答题统计信息", description = "根据考试 ID 和学生 ID 查询对应的考试答题统计信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "获取成功",
                    content = @Content(schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "500", description = "服务器内部错误，查询失败")
    })
    @Parameters({

    })
    @GetMapping("/selectUserCount")
    public Result selectUserCount() {
        Map<String, Integer> result = selectUserCountService.selectUserCount();
        // 返回成功结果
        return Result.success(result);
    }

    /**
     * 查询登录次数
     * @return 返回包含统计结果的通用结果对象
     */
    @Operation(summary = "查询考试答题统计信息", description = "根据考试 ID 和学生 ID 查询对应的考试答题统计信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "获取成功",
                    content = @Content(schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "500", description = "服务器内部错误，查询失败")
    })
    @Parameters({

    })
    @GetMapping("/selectUserLogin")
    public Result selectUserLogin() {
        Map<String, Map<String, Integer>> result = selectUserCountService.selectUserLogin();
        // 返回成功结果
        return Result.success(result);
    }
}