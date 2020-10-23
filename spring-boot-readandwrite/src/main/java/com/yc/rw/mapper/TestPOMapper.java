package com.yc.rw.mapper;

import com.yc.rw.entity.TestPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestPOMapper {

    int deleteByPrimaryKey(String id);

    int insert(TestPO record);

    int insertSelective(TestPO record);

    TestPO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TestPO record);

    int updateByPrimaryKey(TestPO record);
}
