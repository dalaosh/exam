package com.mc.esuser.controller;

/**
 * @author machao
 * * @datetime 2025/1/7 15:06
 * * inform
 */


import com.mc.esuser.config.AutoLog;
import com.mc.escommon.config.CaptureConfig;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;



@Slf4j
@RestController
@RequestMapping("user/login")
@Tag(name = "验证码接口",description = "用户通过请求获取验证码")
public class CaptureController {
    @Parameters({
            @Parameter(name = "key", description = "随机数", required = true)
    })
    @Operation(summary = "验证码图形", description = "验证码生成后返回到前端")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "登录成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @RequestMapping("/captcha")
    @AutoLog("验证码")
    public void captcha(@RequestParam String key, HttpServletRequest request, HttpServletResponse response) throws Exception{
        //png类型
        SpecCaptcha captcha=new SpecCaptcha(135,33,4);//数字设置图片大小
        captcha.setCharType(Captcha.TYPE_NUM_AND_UPPER);//验证码格式
        CaptureConfig.CAPTURE_MAP.put(key,captcha.text().toLowerCase());
        out(captcha, request, response);
        //CaptchaUtil.out(captcha, request, response)
    }


    //CaptchaUtil.out(captcha, request, response)
    //不成功，进入out进行反编译
    public static void out(Captcha captcha, HttpServletRequest request, HttpServletResponse response) throws IOException {
        setHeader(response);
        request.getSession().setAttribute("captcha", captcha.text().toLowerCase());
        captcha.out(response.getOutputStream());
    }
    public static void setHeader(HttpServletResponse response) {
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
    }
}

