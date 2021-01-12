package com.yc.swagger.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 功能描述:
 *
 * @Author: xieyc
 * @Date: 2021-01-09
 */
@Data
@ApiModel
public class ResponseTest6 {

    @ApiModelProperty(value = "姓名", required = true, dataType = "query")
    private String name;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "到期时间")
    private Date expirationDate;

    @ApiModelProperty(value = "金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "list")
    private List<ResponseTest7> requestTest7List;

}
