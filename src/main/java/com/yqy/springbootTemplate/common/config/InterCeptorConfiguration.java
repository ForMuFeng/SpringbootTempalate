package com.yqy.springbootTemplate.common.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @Auth:yqy
 * @Date 2019/9/6 14:13
 */
@SpringBootConfiguration
public class InterCeptorConfiguration implements WebMvcConfigurer {

    //在这里@Resource 拦截器并且配置一下~
    /**
     * addInterceptor()的顺序需要严格按照程序的执行的顺序
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }
}