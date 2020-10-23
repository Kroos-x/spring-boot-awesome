package com.yc.rw.service;

import com.yc.rw.entity.SysUser;
import com.yc.rw.mapper.SysUserMapper;
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
@Slf4j
public class SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    public SysUser selectByPrimaryKey() {
        return sysUserMapper.selectByPrimaryKey("1");
    }

    public List<SysUser> listUser() {
        return null;
    }

    public void save(SysUser sysUser) {
        sysUserMapper.insert(sysUser);
    }
}
