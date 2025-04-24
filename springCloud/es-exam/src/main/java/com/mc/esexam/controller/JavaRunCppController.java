package com.mc.esexam.controller;


import com.mc.escommon.entity.exam.Code;
import com.mc.escommon.result.Result;
import com.mc.esexam.service.JavaRunCppService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("exam/code")
@Tag(name = "代码编码接口", description = "代码的运行的操作")
public class JavaRunCppController {

    @Autowired
    private JavaRunCppService javaRunCppService;


    @PostMapping("/cpp")
    public Result register(@RequestBody Code code) {
        String s=javaRunCppService.runCpp(code.getM(),code.getT());
        return Result.success(s);
    }
}
