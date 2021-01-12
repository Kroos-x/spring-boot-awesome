package com.yc.swagger.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetAddress;

/**
 * 功能描述:
 *
 * @Author: xieyc
 * @Date: 2020-12-13
 */
@Slf4j
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class Swagger2Config {

    @Value("${server.port}")
    private String port;

    /**
     * 配置swagger-bootstrap-ui基础信息
     *
     * @return apiInfo
     */
    private ApiInfo apiInfo() {
        InetAddress ia = null;
        try {
            ia = InetAddress.getLocalHost();
        } catch (Exception e) {
            log.error("本机信息未读取到");
        }
        return new ApiInfoBuilder()
                .title("Swagger For RESTful API")
                .description("Swagger2 测试用例")
                .termsOfServiceUrl("http://" + (ia != null ? ia.getHostAddress() : "") + ":" + port + "/doc.html")
                .version("Version 1.0.0")
                .contact(new Contact("developer", "www.xxx.com", "~@mail.com"))
                .license("CN Red Guest")
                .licenseUrl("www.cnhonkerarmy.com")
                .build();
    }


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yc.swagger"))
                .paths(PathSelectors.any())
                .build();
    }
}
