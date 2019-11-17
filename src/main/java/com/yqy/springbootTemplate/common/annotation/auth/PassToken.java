package com.yqy.springbootTemplate.common.annotation.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: smartpage-springboot
 * @description: 不需要token的方法
 * @author: Mr.Yqy
 * @create: 2019-05-13 13:29
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PassToken {
    boolean required() default true;
}
