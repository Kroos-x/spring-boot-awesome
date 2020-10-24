package com.yc.rw;

import com.yc.rw.entity.SysUser;
import com.yc.rw.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootReadandwriteApplicationTests {

    @Autowired
    private SysUserService service;

    /**
     * 写库
     */
    @Test
    public void testWrite() {
        SysUser aaa = new SysUser();
        aaa.setId("1");
        aaa.setName("1");
        aaa.setAge(12);
        service.insert(aaa);
        aaa = new SysUser();
        aaa.setId("2");
        aaa.setName("2");
        service.insert(aaa);
    }

    /**
     * 读库
     */
    @Test
    public void testRead() {
        SysUser aaa = service.selectUserById("1");
        System.out.println("==========");
        System.out.println("==========");
        System.out.println(aaa);
        System.out.println("==========");
        System.out.println("==========");
    }

}
