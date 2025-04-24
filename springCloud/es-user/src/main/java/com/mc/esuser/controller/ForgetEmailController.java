package com.mc.esuser.controller;


import cn.hutool.core.util.ObjectUtil;
import com.mc.escommon.config.CaptureConfig;
import com.mc.escommon.config.EmailConfig;
import com.mc.escommon.entity.user.CaInstitution;
import com.mc.escommon.entity.user.Params;
import com.mc.escommon.result.Result;
import com.mc.escommon.result.ResultCodeEnum;
import com.mc.escommon.result.RoleEnum;
import com.mc.esuser.feignClient.EmailFeignClient;
import com.mc.esuser.service.CaInstitutionService;
import com.mc.esuser.service.AdminService;
import com.mc.esuser.service.StudentService;
import com.mc.esuser.service.TeacherService;
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

import java.util.Objects;
import java.util.Random;

/**
 * @author machao
 * * @datetime 2025/1/9 20:21
 * * inform
 */
@Slf4j
@RestController
@RefreshScope
@RequestMapping("user/forget")
@Tag(name = "邮箱验证接口",description = "通过邮箱辅助验证")
public class ForgetEmailController {
    @Autowired
    private EmailFeignClient emailFeignClient;
    @Autowired
    private CaInstitutionService caInstitutionService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Parameters({
            @Parameter(name = "key", description = "随机数", required = true),
            @Parameter(name = "params", description = "用户数据", required = true)
    })
    @Operation(summary = "发送忘记密码验证码", description = "验证码发送后返回到前端")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "发送成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "5007", description = "用户未找到",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/email")
    public Result email(@RequestParam String key,@RequestBody Params params){
        if (ObjectUtil.isEmpty(params.getKey1())
                || ObjectUtil.isEmpty(params.getAccount())
                || ObjectUtil.isEmpty(params.getPhone())
                || ObjectUtil.isEmpty(params.getRole())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        //验证图形验证码
        String sk=params.getKey1();
        if(params.getVerCode()==null|| params.getVerCode().equals("")){
            return Result.error(ResultCodeEnum.EMAIL_MA_ERROR);
        }
        try {
            if(!params.getVerCode().toLowerCase().equals(CaptureConfig.CAPTURE_MAP.get(sk))){
                //不相等，验证不通过
                return Result.error(ResultCodeEnum.USER_RES_ERROR);
            }
        }catch (Exception e){
            return Result.error(ResultCodeEnum.USER_RES_ERROR);
        }
        // 1.查询邮箱地址(分角色) 通过账号和电话查询邮箱
        String address="";
        if (RoleEnum.caInstitution.name().equals(params.getRole())) {
            CaInstitution caInstitution = caInstitutionService.selectByAccountAndPhone(params.getAccount(),params.getPhone());
            address= caInstitution.getEmail();
        }
        if (RoleEnum.admin.name().equals(params.getRole())) {
            address=adminService.selectByAccountAndPhone(params.getAccount(),params.getPhone()).getEmail();
        }
        if (RoleEnum.teacher.name().equals(params.getRole())) {
            address=teacherService.selectByAccountAndPhone(params.getAccount(),params.getPhone()).getEmail();
        }
        if (RoleEnum.student.name().equals(params.getRole())) {
            address=studentService.selectByAccountAndPhone(params.getAccount(),params.getPhone()).getEmail();
        }
        if("".equals(address)||address==null){
            Result.error(ResultCodeEnum.USER_NO_ERROR);
        }
        // 2.设置主题
        String subject=generateSubject();
        // 3.正文
        String number=generateVerificationCode();
        String body=generateBody(number);
        // 4.保存验证码
        EmailConfig.EMAIL_MAP.put(key,number);
        long nowTime = System.currentTimeMillis();
        EmailConfig.TIME_MAP.put(key,nowTime);
        // 5.发送验证码
        emailFeignClient.sendSimpleMail(address,subject,body);
        // 6.返回结果
        return Result.success();
    }

    // 生成随机验证码
    public static String generateVerificationCode() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }

    // 生成邮件标题
    public static String generateSubject() {
        return "QQ邮箱密码找回验证";
    }

    // 生成邮件正文
    public static String generateBody(String verificationCode) {
        return "您正在进行密码找回操作，您的验证码是：" + verificationCode + "。请在10分钟内使用此验证码完成验证。";
    }

    @Parameters({
            @Parameter(name = "key", description = "随机数", required = true),
            @Parameter(name = "params", description = "用户数据", required = true)
    })
    @Operation(summary = "验证邮箱验证码", description = "验证码发送后返回到前端")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "验证成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "6001", description = "验证码超时",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "6002", description = "验证码错误",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/verify")
    public Result verify(@RequestParam String key,@RequestBody Params params){
        //获取新旧数据
        if (ObjectUtil.isEmpty(params.getMailCode())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        long oldTime=EmailConfig.TIME_MAP.get(key);
        String oldNumber=EmailConfig.EMAIL_MAP.get(key);
        long newTime=System.currentTimeMillis();
        String newNumber=params.getMailCode();
        // 计算时间差（毫秒）
        long differenceInMillis = newTime - oldTime;
        // 将时间差转换为其他单位
        long differenceInSeconds = differenceInMillis / 1000;
        long differenceInMinutes = differenceInSeconds / 60;
        EmailConfig.EMAIL_MAP.remove(key);
        EmailConfig.TIME_MAP.remove(key);
        if(differenceInMinutes>10){
            return Result.error(ResultCodeEnum.EMAIL_TIMEOUT_ERROR);
        }
        if(!Objects.equals(oldNumber, newNumber)){
            return Result.error(ResultCodeEnum.EMAIL_CODE_ERROR);
        }
        return Result.success();
    }
}
