package com.aili.wechatdome;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.aili.wechatdome.mapper")
public class WechatDomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatDomeApplication.class, args);

//        关闭启动logo
        /*SpringApplication springApplication = new  SpringApplication(WechatDomeApplication.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);*/
    }

}
