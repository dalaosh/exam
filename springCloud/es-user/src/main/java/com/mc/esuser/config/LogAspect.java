package com.mc.esuser.config;


import cn.hutool.core.date.DateUtil;
import com.mc.escommon.JwtUtils.JwtAPI;
import com.mc.escommon.entity.info.Log;
import com.mc.escommon.entity.user.*;
import com.mc.escommon.result.Result;
import com.mc.esuser.feignClient.InfoFeignClient;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * *Data 2025.1.13
 * 登录
 * 日志的切面编程
 * 切面直接对方法进行拦截
 * 通过切面环绕的方式进行编程
 */
@Component
@Aspect
public class LogAspect {
    @Autowired
    private InfoFeignClient infoFeignClient;
    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    @Around("@annotation(autoLog)")
    public Object doAround(ProceedingJoinPoint joinPoint, AutoLog autoLog) throws Throwable{
        System.out.println("进入切面es-user");
        //在注解里面定义了value
        String name= autoLog.value();
        //操作时间
        String time= DateUtil.now();
        //操作人
        String username = JwtAPI.getCurrentUser();
        //用户角色
        String role = null;
        //用户id
        Integer userId = null;
        //ip地址
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getRemoteAddr();
        //执行接口
        Result result=(Result) joinPoint.proceed();
        if(result==null){
            return null;
        }
        //如果是admin则为登录操作
        Object data = result.getData();
        if(data instanceof Admin
                || data instanceof CaInstitution
                || data instanceof Teacher
                || data instanceof Student
        ){
            if(data instanceof Admin user){
                username=user.getName();
                role=user.getRole();
                userId=user.getId();
            }
            if(data instanceof CaInstitution user){
                username=user.getName();
                role=user.getRole();
                userId=user.getId();
            }
            if(data instanceof Teacher user){
                username=user.getName();
                role=user.getRole();
                userId=user.getId();
            }
            if(data instanceof Student user){
                username=user.getName();
                role=user.getRole();
                userId=user.getId();
            }
        }

        //封装实体，执行完后添加数据
        Log log=new Log(null,null,userId,username,role,name,time,ip);
        infoFeignClient.insert(log);
        return result;
    }
}
