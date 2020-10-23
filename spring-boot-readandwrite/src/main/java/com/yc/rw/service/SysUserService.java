package com.yc.rw.service;

import com.yc.rw.entity.SysUser;

import java.util.List;

/**
 * 功能描述:
 *
 * @Author: xieyc
 * @Date: 2019-09-19
 */
public interface SysUserService {

    SysUser getUser();

    List<SysUser> listUser();

    void save();

}
