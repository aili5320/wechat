package com.aili.wechatdome.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${userPicPath}")
    private String userPicPath;
    @Value("${voicePath}")
    private String voicePath;

    @Value("${sendPicPath}")
    private String sendPicPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * 配置资源映射
         * 意思是：如果请求地址是以“/upload/”开头的，
         * 就给映射到本机的“D:/upload/”这个文件夹内，去找你要的资源
         * 注意：D:/upload/ 后面的 “/”一定要带上
         */
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:"+userPicPath)
                .addResourceLocations("file:"+voicePath)
                .addResourceLocations("file:"+sendPicPath);
    }
}
