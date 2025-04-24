package com.mc.esuser.controller;

import cn.hutool.core.util.ObjectUtil;
import com.mc.escommon.config.CaptureConfig;
import com.mc.escommon.entity.user.*;
import com.mc.escommon.result.Result;
import com.mc.escommon.result.ResultCodeEnum;
import com.mc.escommon.result.RoleEnum;

import com.mc.esuser.config.AutoLog;
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
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author machao
 * * @datetime 2025/1/3 23:05
 * * inform
 */

@Slf4j
@RestController
@RequestMapping("user")
@Tag(name = "基础前端接口",description = "用户登录，忘记密码的相关操作")
public class WebController {
    //登录，忘记密码，忘记密码通过qq邮箱找回，
    @Autowired
    private CaInstitutionService caInstitutionService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    @Parameters({
            @Parameter(name = "params", description = "用户信息", required = true)
    })
    @Operation(summary = "用户登录", description = "用户输入用户名和密码进行登录")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "登录成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "4001", description = "参数缺失",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "5001", description = "验证码不正确",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/login")
    @AutoLog("用户登录")
    public Result login(@RequestBody Params params, @RequestParam String key, HttpServletRequest request){
        //1.判断有没有缺失参数
        if (ObjectUtil.isEmpty(params.getAccount())
                || ObjectUtil.isEmpty(params.getPassword())
                || ObjectUtil.isEmpty(params.getRole())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        //2.进行验证码验证
        if(!params.getVerCode().toLowerCase().equals(CaptureConfig.CAPTURE_MAP.get(key))){
            //不相等，验证不通过
            clear(request);
            return Result.error(ResultCodeEnum.USER_RES_ERROR);
        }

        //3.根据角色的不同选择不同的数据库登录
        if (RoleEnum.caInstitution.name().equals(params.getRole())) {
            CaInstitution caInstitution =new CaInstitution();
            caInstitution.setAccount(params.getAccount());
            caInstitution.setPassword(params.getPassword());
            return Result.success(caInstitutionService.login(caInstitution));
        }
        if (RoleEnum.admin.name().equals(params.getRole())) {
            Admin admin =new Admin();
            admin.setAccount(params.getAccount());
            admin.setPassword(params.getPassword());
            return Result.success(adminService.login(admin));
        }
        if (RoleEnum.teacher.name().equals(params.getRole())) {
            Teacher teacher=new Teacher();
            teacher.setAccount(params.getAccount());
            teacher.setPassword(params.getPassword());
            return Result.success(teacherService.login(teacher));
        }
        if (RoleEnum.student.name().equals(params.getRole())) {
            Student student=new Student();
            student.setAccount(params.getAccount());
            student.setPassword(params.getPassword());
            return Result.success(studentService.login(student));
        }
        //4.删除验证码
        CaptureConfig.CAPTURE_MAP.remove(key);
        return Result.success();
    }
    public static void clear(HttpServletRequest request) {
        request.getSession().removeAttribute("captcha");
    }


    @Parameters({
            @Parameter(name = "params", description = "用户信息", required = true)
    })
    @Operation(summary = "用户修改密码", description = "用户进行密码的修改")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "修改成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @PostMapping("/forget")
    public Result login(@RequestBody Params params){
        if (ObjectUtil.isEmpty(params.getId())
                || ObjectUtil.isEmpty(params.getPassword())
                || ObjectUtil.isEmpty(params.getAccount())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        if (RoleEnum.caInstitution.name().equals(params.getRole())) {
            CaInstitution caInstitution =new CaInstitution();
            caInstitution.setPassword(params.getPassword());
            caInstitution.setAccount(params.getAccount());
            caInstitution.setId(params.getId());
            caInstitutionService.updatePassword(caInstitution);
            return Result.success();
        }
        if (RoleEnum.admin.name().equals(params.getRole())) {
            Admin admin=new Admin();
            admin.setPassword(params.getPassword());
            admin.setAccount(params.getAccount());
            admin.setId(params.getId());
            adminService.updatePassword(admin);
            return Result.success();
        }
        if (RoleEnum.teacher.name().equals(params.getRole())) {
            Teacher teacher=new Teacher();
            teacher.setPassword(params.getPassword());
            teacher.setAccount(params.getAccount());
            teacher.setId(params.getId());
            teacherService.updatePassword(teacher);
            return Result.success();
        }
        if (RoleEnum.student.name().equals(params.getRole())) {
            Student student=new Student();
            student.setPassword(params.getPassword());
            student.setAccount(params.getAccount());
            student.setId(params.getId());
            studentService.updatePassword(student);
            return Result.success();
        }
        return Result.success();
    }

    @Parameters({
            @Parameter(name = "params", description = "用户信息", required = true)
    })
    @Operation(summary = "用户查询信息", description = "用户进行信息的查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "修改成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @PostMapping("/findMessage")
    public Result findAdmin(@RequestBody Params params){
        if (ObjectUtil.isEmpty(params.getAccount())
                || ObjectUtil.isEmpty(params.getPhone())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        if (RoleEnum.caInstitution.name().equals(params.getRole())) {
            CaInstitution caInstitution = caInstitutionService.selectByAccountAndPhone(params.getAccount(), params.getPhone());
            return Result.success(caInstitution);
        }
        if (RoleEnum.admin.name().equals(params.getRole())) {
            Admin admin=adminService.selectByAccountAndPhone(params.getAccount(), params.getPhone());
            return Result.success(admin);
        }
        if (RoleEnum.teacher.name().equals(params.getRole())) {
            Teacher teacher=teacherService.selectByAccountAndPhone(params.getAccount(), params.getPhone());
            return Result.success(teacher);
        }
        if (RoleEnum.student.name().equals(params.getRole())) {
            Student student=studentService.selectByAccountAndPhone(params.getAccount(), params.getPhone());
            return Result.success(student);
        }
        return Result.success();
    }
}
