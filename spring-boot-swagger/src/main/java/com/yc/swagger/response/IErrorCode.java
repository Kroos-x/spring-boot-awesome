package com.yc.swagger.response;

/**
 * 功能描述: 封装API的错误码
 *
 * @Author: xieyc
 * @Date: 2021-01-10
 */
public interface IErrorCode {

    /**
     * 响应码
     *
     * @return code
     */
    long getCode();

    /**
     * 消息体
     *
     * @return msg
     */
    String getMsg();
}
