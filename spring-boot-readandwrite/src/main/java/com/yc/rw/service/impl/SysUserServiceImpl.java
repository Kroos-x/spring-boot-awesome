package com.yc.rw.service.impl;

import com.yc.rw.entity.SysUser;
import com.yc.rw.mapper.SysUserMapper;
import com.yc.rw.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 功能描述:
 *
 * @Author: xieyc
 * @Date: 2019-09-19
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getUser() {
        return sysUserMapper.getUser();
    }

    @Override
    public List<SysUser> listUser() {
        return null;
    }

    @Override
    public void save() {

    }
}
