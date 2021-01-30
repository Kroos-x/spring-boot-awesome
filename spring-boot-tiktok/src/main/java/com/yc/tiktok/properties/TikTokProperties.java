package com.yc.tiktok.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 功能描述: 抖音开放平台参数
 *
 * @Author: xieyc
 * @Date: 2021-01-27
 */
@Data
@Component
@ConfigurationProperties(prefix = "tiktok.open")
public class TikTokProperties {

    /**
     * appKey
     */
    private String appKey;
    /**
     * appSecret
     */
    private String appSecret;
    /**
     * 接口访问前缀
     */
    private String host;
    /**
     * 建立连接超时时间
     */
    private int connectTimeout;
    /**
     * 数据交互超时时间
     */
    private int socketTimeout;

}
