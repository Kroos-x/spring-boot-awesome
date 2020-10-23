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
     * 写库进行写入
     */
    @Test
    public void testWrite() {
        SysUser aaa = new SysUser();
        aaa.setId("2");
        aaa.setName("2");
        service.save(aaa);
    }

    /**
     * 读库（otadb1和otadb2随机）进行读取
     */
    @Test
    public void testRead() {
        SysUser aaa = service.selectByPrimaryKey();
        System.out.println("==========");
        System.out.println("==========");
        System.out.println("==========");
        System.out.println("==========");
        System.out.println(aaa.getId());
    }

}
