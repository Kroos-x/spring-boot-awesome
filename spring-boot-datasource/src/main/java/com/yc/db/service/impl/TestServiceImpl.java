package com.yc.db.service.impl;

import com.yc.db.entity.Test;
import com.yc.db.mapper.db1.Test1Mapper;
import com.yc.db.mapper.db2.Test2Mapper;
import com.yc.db.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-06-13
 * @Version: 1.0.0
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    public Test1Mapper test1Mapper;

    @Autowired
    public Test2Mapper test2Mapper;

    @Override
    public List<Test> listAll_one() {
        return test1Mapper.listAll();
    }

    @Override
    public List<Test> listAll_two() {
        return test2Mapper.listAll();
    }
}
