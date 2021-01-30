package com.yc.tiktok.config;

import com.yc.tiktok.properties.TikTokProperties;
import com.yc.tiktok.utils.ErrNoHandleConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述: 配置抖音开放平台信息
 *
 * @Author: xieyc
 * @Date: 2021-01-27
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class TikTokOpenConfig {

    private final TikTokProperties tikTokProperties;

    @Bean
    public TikTokOpenClient tikTokOpen() {
        TikTokOpenClient client = new TikTokOpenClient(tikTokProperties);

        ErrNoHandleConfig errNoHandle = new ErrNoHandleConfig();
        errNoHandle.setAuthorize30006Handle((msg) -> {
            log.error("err回调 根据抖音错误码 可以进行统一处理:" + msg);
        });
        client.setErrNoHandleConfig(errNoHandle);
        return client;
    }

}
