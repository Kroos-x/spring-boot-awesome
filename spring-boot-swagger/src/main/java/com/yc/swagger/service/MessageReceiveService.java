package com.yc.swagger.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.swagger.core.entity.MessageReceive;

/**
 * 功能描述:
 *
 * @Author: xieyc
 * @Date: 2021-01-12
 */
public interface MessageReceiveService {

    /**
     * @param page
     * @param name
     * @return
     */
    Page<MessageReceive> page(Page<MessageReceive> page, String name);
}
