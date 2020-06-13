package com.yc.db.controller;

import com.yc.db.entity.Test;
import com.yc.db.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2020-06-13
 * @Version: 1.0.0
 *
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Autowired
    private TestService service;

    @GetMapping("/one")
    public List<Test> list_one(){
        return service.listAll_one();
    }

    @GetMapping("/two")
    public List<Test> list_two(){
        return service.listAll_two();
    }


}
