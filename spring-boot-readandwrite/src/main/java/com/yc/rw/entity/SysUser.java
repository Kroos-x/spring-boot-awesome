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
 * <p>
 * mybatisPlus会默认使用实体类的类名到数据库中找对应的表
 * 也可以通过@TableName(value = "sys_user")来指定在数据库中的名字
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

}
