package com.yc.swagger.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.swagger.model.ResponseTest6;
import com.yc.swagger.response.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    // TODO: 2021/1/11 分页返回

    // TODO: 2021/1/12 requestParam 不传值还能用吗

    // @GetMapping("/test7")
    // @ApiOperation("分页入参及返回")
    // public void test7(Integer pageSize, Integer pageNum,String name){
    //     Page<>
    // }

    // TODO: 2021/1/12 正常分页返回和现在分页返回
}
