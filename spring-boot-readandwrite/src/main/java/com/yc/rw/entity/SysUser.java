package com.yc.rw.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 功能描述: 用户信息
 *
 * @Author: xieyc
 * @Date: 2019-09-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * @TableId: value:指定数据库表中主键的列名，如果实体类属性和表中字段一致，可省略
     */
    private String id;
    /**
     * 真实姓名
     */
    private String name;
    /**
     * age
     */
    private Integer age;

}
