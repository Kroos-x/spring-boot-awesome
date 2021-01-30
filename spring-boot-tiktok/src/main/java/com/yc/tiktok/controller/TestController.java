package com.yc.tiktok.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述:
 *
 * @Author: xieyc
 * @Date: 2021-01-27
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public Student test() {
        String jsonString = "{\"post_code\":\"c-add-img\",\"post_receiver\":\"图片广告\"}";
        return JSONObject.parseObject(jsonString, Student.class);
    }

}
