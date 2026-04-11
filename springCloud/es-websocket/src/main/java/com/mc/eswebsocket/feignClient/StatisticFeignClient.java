package com.mc.eswebsocket.feignClient;

import com.mc.escommon.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author machao
 * * @datetime 2025/3/29 14:42
 * * inform
 */

@FeignClient(name = "es-statistic", url = "${services.es-statistic.url:http://localhost:9085}")
public interface StatisticFeignClient {
    // 系统信息
    @GetMapping("/statistic/system/info")
    Result getSystemInfo();

    @GetMapping("/statistic/SelectBigEChart/selectBasicNum")
    Result selectBasicNum();

    @GetMapping("/statistic/SelectBigEChart/selectQuestionNum")
    Result selectQuestionNum();

    @GetMapping("/statistic/SelectBigEChart/selectExamAll")
    Result selectExamAll();

    @GetMapping("/statistic/SelectBigEChart/selectStrongAll")
    Result selectStrongAll();

    @GetMapping("/statistic/selectUserCount/selectUserCount")
    Result selectUserCount();

    @GetMapping("/statistic/selectUserCount/selectUserLogin")
    Result selectUserLogin();
}
