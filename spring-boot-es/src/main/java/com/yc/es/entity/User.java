package com.yc.es.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能描述: 用户信息
 *
 * @Author: xieyc
 * @Date: 2020-12-27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    /**
     * 名称
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;


}
