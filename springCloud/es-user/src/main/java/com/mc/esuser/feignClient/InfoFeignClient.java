package com.mc.esuser.feignClient;

import com.mc.escommon.entity.info.Log;
import com.mc.escommon.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author machao
 * * @datetime 2025/1/9 19:57
 * * inform
 */

@FeignClient(name = "es-info", url = "${services.es-info.url:http://localhost:9082}")
public interface InfoFeignClient {
    @PostMapping("/info/log/insert")
    Result insert(@RequestBody Log log);
}
