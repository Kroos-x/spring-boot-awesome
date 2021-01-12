package com.yc.swagger.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 功能描述:
 *
 * @Author: xieyc
 * @Date: 2021-01-09
 */
@Data
public class RequestTest5 {

    @ApiModelProperty(value = "姓名", required = true, dataType = "query")
    private String name;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "性别")
    private Integer sex;


}
