package com.yc.swagger.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 功能描述:
 *
 * @Author: xieyc
 * @Date: 2021-01-11
 */
@Data
@ApiModel
public class ResponseTest7 {

    @ApiModelProperty(value = "姓名",required = true)
    private String name;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "到期时间")
    private Date expirationDate;

    @ApiModelProperty(value = "金额")
    private BigDecimal amount;
}
