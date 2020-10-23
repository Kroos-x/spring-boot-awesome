package com.yc.rw.service;

import com.yc.rw.annotation.Master;
import com.yc.rw.entity.TestPO;
import com.yc.rw.mapper.TestPOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private TestPOMapper testPOMapper;

    public int insert(TestPO aaa) {
        return testPOMapper.insert(aaa);
    }

    @Master
    public int save(TestPO aaa) {
        return testPOMapper.insert(aaa);
    }

    public TestPO selectByPrimaryKey(String id) {
        return testPOMapper.selectByPrimaryKey(id);
    }

    @Master
    public TestPO getById(String id) {
        //  有些读操作必须读主数据库
        //  比如，获取微信access_token，因为高峰时期主从同步可能延迟
        //  这种情况下就必须强制从主数据读
        return testPOMapper.selectByPrimaryKey(id);
    }
}
