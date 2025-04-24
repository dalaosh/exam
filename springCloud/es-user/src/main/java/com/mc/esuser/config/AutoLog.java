package com.mc.esuser.config;


import java.lang.annotation.*;

@Target(ElementType.METHOD)//目标   作用在方法
@Retention(RetentionPolicy.RUNTIME)//
@Documented
public @interface AutoLog {
    String value() default "";
}
