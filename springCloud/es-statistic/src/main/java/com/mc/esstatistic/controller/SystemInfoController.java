package com.mc.esstatistic.controller;

import com.mc.escommon.result.Result;
import com.mc.esstatistic.utils.SystemInfoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistic/system")
public class SystemInfoController {

    @Autowired
    private SystemInfoUtils systemInfoUtils;

    @GetMapping("/info")
    public Result getSystemInfo() {
        return Result.success(systemInfoUtils.getSystemInfo());
    }
}