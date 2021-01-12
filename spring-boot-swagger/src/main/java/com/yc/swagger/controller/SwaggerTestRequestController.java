package com.yc.swagger.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.swagger.model.RequestTest3;
import com.yc.swagger.model.RequestTest3_;
import com.yc.swagger.model.RequestTest4;
import com.yc.swagger.model.RequestTest5;
import com.yc.swagger.response.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * 功能描述: 测试用例
 *
 * @Author: xieyc
 * @Date: 2021-01-09
 */
@Api(tags = "请求参数示例")
@RestController
@RequestMapping("/request")
public class SwaggerTestRequestController {

    @GetMapping("/test1")
    @ApiOperation(value = "无请求参数", notes = "我是接口描述")
    public CommonResult test1() {
        return CommonResult.success();
    }

    @GetMapping("/test2/{name}")
    @ApiOperation("散列参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "姓名", required = true, paramType = "path"),
            @ApiImplicitParam(name = "email", value = "邮箱", paramType = "query")})
    public CommonResult<String> test2(@RequestParam String name, String email) {
        return CommonResult.success("Swagger Hello" + name + email);
    }

    @PostMapping("/test3")
    @ApiOperation("请求实体-添加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "query", value = "查询实体类1", dataType = "RequestTest3"),
            @ApiImplicitParam(name = "requestTest3_", value = "查询实体类2", dataType = "RequestTest3_")
    })
    public CommonResult<String> test3(@RequestBody RequestTest3 query, @RequestBody RequestTest3_ requestTest3_) {
        try {
            return CommonResult.success("Swagger Hello" + query.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("Swagger Hello" + query.toString());
        }
    }

    @GetMapping("/test4")
    @ApiOperation("请求实体-查询")
    public CommonResult<String> test4(Page<RequestTest4> page, RequestTest4 query) {
        // CommonPage.restPage();
        return CommonResult.success("Swagger Hello" + query.toString());
    }


    @PutMapping("/test5")
    @ApiOperation("单请求实体")
    @ApiImplicitParams(@ApiImplicitParam(name = "query", value = "用户信息入参", dataType = "RequestTest5"))
    public CommonResult<String> test5(@RequestBody RequestTest5 query) {
        return CommonResult.success("Swagger Hello" + query.toString());
    }


    @DeleteMapping("/test6/{id}")
    @ApiOperation("单请求参数")
    @ApiImplicitParam(name = "id", value = "用户信息入参", required = true, dataType = "String")
    public CommonResult<String> test6(@PathVariable String id) {
        return CommonResult.success("Swagger Hello" + id);
    }


}
