package com.yc.rw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.yc"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringBootReadandwriteApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootReadandwriteApplication.class, args);
    }

}
