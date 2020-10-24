package com.yc.rw.service;

import com.yc.rw.entity.SysUser;

import java.util.List;

/**
 * 功能描述: 用户 service
 *
 * @Author: xieyc
 * @Date: 2020-10-24
 */
public interface SysUserService {

    SysUser selectUserById(String id);

    List<SysUser> listUser();

    void insert(SysUser sysUser);
}
