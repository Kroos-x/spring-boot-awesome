package com.yc.springbootquartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.yc"})
@SpringBootApplication
public class SpringBootQuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootQuartzApplication.class, args);
    }


    // TODO: 2021/1/4 每隔一段时间执行,在服务重启后时间是否准确 
}
