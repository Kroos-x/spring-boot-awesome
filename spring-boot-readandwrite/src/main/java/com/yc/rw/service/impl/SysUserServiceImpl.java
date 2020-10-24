package com.yc.rw.service.impl;

import com.yc.rw.entity.SysUser;
import com.yc.rw.mapper.SysUserMapper;
import com.yc.rw.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 功能描述: 用户 impl
 *
 * @Author: xieyc
 * @Date: 2019-09-19
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser selectUserById(String id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysUser> listUser() {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(SysUser sysUser) {
        sysUserMapper.insert(sysUser);
    }
}
