package com.yc.swagger.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.swagger.core.entity.MessageReceive;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 功能描述:
 *
 * @Author: xieyc
 * @Date: 2019-10-08
 * @Version: 1.0.0
 */
@Repository
public interface MessageReceiveMapper extends BaseMapper<MessageReceive> {

    Page<MessageReceive> page(@Param("page") Page<MessageReceive> page, @Param("name") String name);
}
