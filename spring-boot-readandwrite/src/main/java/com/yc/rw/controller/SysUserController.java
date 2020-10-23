package com.yc.rw.controller;

import com.yc.rw.entity.SysUser;
import com.yc.rw.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 功能描述:
 *
 * @Author: xieyc
 * @Date: 2020-10-23
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService service;
    // @Autowired
    // private IUserService iUserService;

    @GetMapping("/get")
    public SysUser get() {
        return service.getUser();
    }

    // @GetMapping("/findUser")
    // public List<User> findUser() {
    //     return iUserService.findUser();
    // }

    @GetMapping("/list")
    public List<SysUser> list() {
        // return service.list();
        return null;
    }

    @PostMapping("/b")
    public void save() {
        SysUser sysUser = new SysUser();
        sysUser.setName("2");
        sysUser.setAge(2);
        // service.save(sysUser);
    }


}
