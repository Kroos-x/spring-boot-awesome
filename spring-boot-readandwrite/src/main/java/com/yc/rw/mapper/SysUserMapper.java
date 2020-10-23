package com.yc.rw.mapper;

import com.yc.rw.entity.SysUser;
import org.apache.ibatis.annotations.Select;

/**
 * 功能描述:
 *
 * @Author: xieyc
 * @Date: 2019-09-19
 */
public interface SysUserMapper{

    @Select("select * from sys_user where id = '1' ")
    SysUser getUser();
}
