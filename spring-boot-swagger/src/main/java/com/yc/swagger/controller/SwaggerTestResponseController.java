package com.yc.swagger.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.swagger.core.entity.MessageReceive;
import com.yc.swagger.model.ResponseTest6;
import com.yc.swagger.response.CommonPage;
import com.yc.swagger.response.CommonResult;
import com.yc.swagger.service.MessageReceiveService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 功能描述: 测试用例
 *
 * @Author: xieyc
 * @Date: 2021-01-09
 */
@Api(tags = "返回值示例")
@RestController
@RequestMapping("/response")
@RequiredArgsConstructor
public class SwaggerTestResponseController {


    @GetMapping("/test1")
    @ApiOperation(value = "单一返回值", notes = "对于单字段或少数字段,可以用此方式说明返回值")
    @ApiResponses({
            @ApiResponse(code = 200, message = "data：姓名")
    })
    public CommonResult<String> test1() {
        return CommonResult.success("hello swagger");
    }

    @GetMapping("/test2")
    @ApiOperation("返回单一对象")
    public CommonResult<ResponseTest6> test2() {
        ResponseTest6 test6 = new ResponseTest6();
        test6.setName("23");
        test6.setAge(12);
        test6.setSex(2);
        test6.setExpirationDate(new Date());
        test6.setAmount(BigDecimal.valueOf(3));
        return CommonResult.success(test6);
    }


    private final MessageReceiveService messageReceiveService;

    @GetMapping("/test7")
    @ApiOperation("分页入参及返回-散列入参")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "name", value = "姓名", paramType = "query")})
    public Page<MessageReceive> test7(@RequestParam Integer pageSize, @RequestParam Integer pageNum, String name) {
        Page<MessageReceive> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        return messageReceiveService.page(page, name);
    }

    @GetMapping("/test8")
    @ApiOperation("分页入参及返回-ComomonPage封装分页入参和返回分页信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "name", value = "姓名", paramType = "query")})
    public CommonResult<CommonPage<MessageReceive>> test8(@RequestParam long pageSize,
                                                          @RequestParam long pageNum,
                                                          String name) {
        return CommonResult.success(CommonPage.restPage(messageReceiveService.page(new Page<>(pageNum, pageSize), name)));
    }

}
