package com.yc.swagger.service.impl;

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
public class MessageReceiveServiceImpl implements MessageReceiveService {
}
