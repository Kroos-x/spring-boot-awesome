package com.yc.rw.mapper;

import com.yc.rw.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 功能描述: sysUser mapper
 *
 * @Author: xieyc
 * @Date: 2019-09-19
 */
@Mapper
public interface SysUserMapper {

    int deleteByPrimaryKey(String id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}
