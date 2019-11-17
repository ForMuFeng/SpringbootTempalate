package com.yqy.springbootTemplate.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Auth:yqy
 * @Date 2019/8/27 23:31
 */
@Configuration
public class WebStaticResourceConfigure extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        /*
         * * 根据系统标识来判断合适的文件路径格式
         * * 下面将路径中的/img/XX.jpg映射到文件系统中的D:/static/img/XX.jpg或者Unix下的/var/static/img/XX.jpg
         * * 如果需要映射的文件夹是在项目中的，比如resource/static,
         *   可以使用.addResourceLocations("classpath:/static/")
         */
        String os = System.getProperty("os.name");
        final String windowsFlag = "win";
        // windows
        if (os.toLowerCase().startsWith(windowsFlag)){
            registry.addResourceHandler("/upload/**")
                    .addResourceLocations("classpath:/static/");
            //linux 和mac
        } else {
            registry.addResourceHandler("/upload/**")
                    .addResourceLocations("classpath:/static/");
        }
    }
}
