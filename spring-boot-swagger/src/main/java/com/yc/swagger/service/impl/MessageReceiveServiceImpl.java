package com.yc.swagger.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.swagger.core.entity.MessageReceive;
import com.yc.swagger.core.mapper.MessageReceiveMapper;
import com.yc.swagger.service.MessageReceiveService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 功能描述:
 *
 * @Author: xieyc
 * @Date: 2021-01-12
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MessageReceiveServiceImpl extends ServiceImpl<MessageReceiveMapper, MessageReceive> implements MessageReceiveService {

    @Override
    public Page<MessageReceive> page(Page<MessageReceive> page, String name) {
        return baseMapper.page(page,name);
    }
}
