package com.yc.tiktok.core.tiktok.request;

import com.yc.tiktok.core.tiktok.response.TikTokOpenResponse;

/**
 * 抖音小店接口 请求接口 抽象类
 *
 * @param <P> 参数类型
 * @param <T> 响应类型
 */
public abstract class TiktokOpenRequest<P ,T extends TikTokOpenResponse<?>> {

    public TiktokOpenRequest(P parameter) {
        setData(parameter);
    }

    // 代表参数
    private P data;

    public P getData() {
        return this.data;
    }

    public void setData(P data) {
        this.data = data;
    }

    /**
     * 提供响应类型，方便反序列化
     *
     * @return class type
     */
    public abstract Class<T> getResponseType();

    /**
     * 提供接口方法名称，不是请求方法
     *
     * @return method
     */
    public String getMethod() {
        String path = getContentPath();
        return path.substring(1).replace("/", ".");
    }

    /**
     * 请求地址
     *
     * @return path
     */
    public abstract String getContentPath();

    /**
     * 默认v=2
     *
     * @return v
     */
    public String getV() {
        return "2";
    }

    // 是否输出 log.info
    public boolean infoEnabled() {
        return true;
    }

    // 是否输出 log.debug
    public boolean debugEnabled() {
        return true;
    }
}
