package com.yc.tiktok;

import com.alibaba.fastjson.JSONObject;
import com.yc.tiktok.controller.Student;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 功能描述:
 *
 * @Author: xieyc
 * @Date: 2021-01-27
 */
public class Test {

    @GetMapping("/test")
    public Student test() {
        String jsonString = "{\"post_code\":\"c-add-img\",\"post_receiver\":\"图片广告\"}";
        return JSONObject.parseObject(jsonString, Student.class);
    }

}
