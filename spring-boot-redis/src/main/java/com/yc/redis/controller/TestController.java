package com.yc.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 功能描述:
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2020-08-20
 */
@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping
    public void test() {
        System.out.println("*****************");
        System.out.println("*****************");
        System.out.println("*****************");
        redisTemplate.opsForValue().set("aaa", "ksdjifo", 5, TimeUnit.SECONDS);
        System.out.println("*****************");
        System.out.println("*****************");
    }

}
