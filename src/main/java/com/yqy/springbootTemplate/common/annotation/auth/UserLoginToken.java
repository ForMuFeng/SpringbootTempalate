package com.yqy.springbootTemplate.common.annotation.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: smartpage-springboot
 * @description: 用户登陆后需要token的注解
 * @author: Mr.Yqy
 * @create: 2019-05-13 13:28
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserLoginToken {
    boolean required() default true;
}
