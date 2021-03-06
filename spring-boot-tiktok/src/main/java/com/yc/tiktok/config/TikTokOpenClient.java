package com.yc.tiktok.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yc.tiktok.core.tiktok.auth.AccessToken;
import com.yc.tiktok.core.tiktok.message.MsgAuthInfo;
import com.yc.tiktok.core.tiktok.message.MsgOrderInfo;
import com.yc.tiktok.core.tiktok.message.TiktokMarketMessage;
import com.yc.tiktok.core.tiktok.request.TiktokOpenRequest;
import com.yc.tiktok.core.tiktok.response.TikTokOpenResponse;
import com.yc.tiktok.exceptions.MsgRequestException;
import com.yc.tiktok.exceptions.TiktokRequestException;
import com.yc.tiktok.properties.TikTokProperties;
import com.yc.tiktok.utils.AesDecryptUtils;
import com.yc.tiktok.utils.AssertUtils;
import com.yc.tiktok.utils.ErrNoHandleConfig;
import com.yc.tiktok.utils.HttpUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 功能描述: 抖音开放平台能力
 *
 * @Author: xieyc
 * @Date: 2021-01-27
 */
public class TikTokOpenClient {

    private HttpUtils httpUtils;
    private TikTokProperties tikTokProperties;
    private DateTimeFormatter dateTimeFormatter;
    private ErrNoHandleConfig errNoHandleConfig;
    private Logger logger = LoggerFactory.getLogger(TikTokOpenClient.class);

    public TikTokOpenClient(TikTokProperties tikTokProperties) {
        this.tikTokProperties = tikTokProperties;
        this.httpUtils = new HttpUtils(tikTokProperties);
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    public <S, T extends TikTokOpenResponse<?>> T getTiktokResponse(TiktokOpenRequest<S, T> request, String accessToken) {
        String jsonStr = JSON.toJSONString(request.getData(), SerializerFeature.MapSortField, SerializerFeature.WriteEnumUsingToString);
        // 不可包含特殊字符 & = ｜ ^ +
        AssertUtils.isTrue(!jsonStr.contains("&"), "不可包含特殊字符 & ");

        SortedMap<String, String> treeMap = new TreeMap<>();

        treeMap.put("param_json", jsonStr);

        treeMap.put("method", request.getMethod());
        treeMap.put("app_key", tikTokProperties.getAppKey());
        treeMap.put("timestamp", dateTimeFormatter.format(LocalDateTime.now()));
        treeMap.put("v", request.getV());

        String sign = signStr(treeMap);

        if (logger.isInfoEnabled()) {
            logger.info("抖音开发平台请求 {} {} {} sign:{}", request.getMethod(), jsonStr, accessToken, sign);
        }

        treeMap.put("sign", sign);
        // access_token 不参与加密
        treeMap.put("access_token", accessToken);
        String url = tikTokProperties.getHost() + request.getContentPath();
        // doGet 不处理 urlEncoder 所以要准备避免特殊字符
        // 修改为 doPost 方法，应该可以避免特殊字符影响
        String response = httpUtils.doPost(url, treeMap);

        // 请求异常则 response 会抛出异常 而不会是null
        if (response != null) {
            if (logger.isInfoEnabled() && request.infoEnabled()) {
                logger.info("抖音开发平台响应 {}", response);
            } else if (logger.isDebugEnabled() && request.debugEnabled()) {
                logger.debug("抖音开发平台响应 {}", response);
            }

            try {
                T res = JSON.parseObject(response, request.getResponseType());
                if (res.getErr_no() == 30006 || res.getErr_no() == 11
                        || res.getErr_no() == 30003) {
                    // 用户取消授权 授权过期等
                    if (errNoHandleConfig.getAuthorize30006Handle() != null) {
                        errNoHandleConfig.getAuthorize30006Handle().accept(accessToken);
                    }
                }
                return res;
            } catch (Exception e) {
                logger.error("抖音响应解析失败", e);
                logger.warn("错误响应消息 msg: {}", response);
                throw new TiktokRequestException(-1, "抖音响应解析失败 str:" + response);
            }
        }
        return null;
    }

    /**
     * 抖音签名方法
     *
     * @param sortedMap
     * @return
     */
    private String signStr(SortedMap<String, String> sortedMap) {
        StringBuilder sb = new StringBuilder(tikTokProperties.getAppSecret());
        sortedMap.forEach((key, value) -> {
            sb.append(key).append(value);
        });
        sb.append(tikTokProperties.getAppSecret());
        return DigestUtils.md5Hex(sb.toString()).toLowerCase();
    }

    /**
     * 服务市场消息回调解密方法
     *
     * @param sSrc 秘文
     * @return
     */
    public TiktokMarketMessage decrypt(String sSrc) {
        String decrypt = AesDecryptUtils.decrypt(sSrc, tikTokProperties.getAppSecret().replace("-", ""));
        if (decrypt == null) {
            throw new MsgRequestException("aes 解密失败:" + sSrc);
        }
        logger.info("服务市场消息推送：" + decrypt);
        JSONObject json = JSONObject.parseObject(decrypt);
        int msgType = json.getInteger("msg_type");
        String msg = json.getString("msg");
        TiktokMarketMessage message = new TiktokMarketMessage();
        message.setMsgType(msgType);
        message.setMessage(decrypt);
        if (msgType == 1) {
            // msg_type=1，msg中存储的是支付成功相关信息
            MsgOrderInfo info = JSON.parseObject(msg, MsgOrderInfo.class);
            message.setMsgOrderInfo(info);
        } else if (msgType == 2) {
            // msg_type=2，msg中存储的是授权相关信息
            MsgAuthInfo info = JSON.parseObject(msg, MsgAuthInfo.class);
            message.setMsgAuthInfo(info);
        } else {
            throw new MsgRequestException("未支持的订购消息类型:" + msgType);
        }
        return message;
    }

    public AccessToken getAccessToken(String code) {
        String url = tikTokProperties.getHost();
        String uri = "/oauth2/access_token";

        Map<String, String> params = new HashMap<>();
        params.put("app_id", tikTokProperties.getAppKey());
        params.put("app_secret", tikTokProperties.getAppSecret());
        params.put("code", code);
        params.put("grant_type", "authorization_code");

        String response = httpUtils.doGet(url + uri, params);
        return JSON.parseObject(response, AccessToken.class);
    }

    public AccessToken refreshToken(String refreshToken) {
        String url = tikTokProperties.getHost();
        String uri = "/oauth2/refresh_token";

        Map<String, String> params = new HashMap<>();
        params.put("app_id", tikTokProperties.getAppKey());
        params.put("app_secret", tikTokProperties.getAppSecret());
        params.put("refresh_token", refreshToken);
        params.put("grant_type", "refresh_token");

        String response = httpUtils.doGet(url + uri, params);
        return JSON.parseObject(response, AccessToken.class);
    }

    /**
     * 检查是否有特殊字符
     *
     * @return
     */
    public static boolean hasSpecialChar(String content) {
        // 不可包含特殊字符 & = ｜ ^ +
        int len = content.length();
        char[] chars = new char[]{'&', '|', '^'};
        for (int i = 0; i < len; i++) {
            char s = content.charAt(i);
            for (char aChar : chars) {
                if (s == aChar) {
                    return true;
                }
            }
        }
        return false;
    }

    public ErrNoHandleConfig getErrNoHandleConfig() {
        return errNoHandleConfig;
    }

    public void setErrNoHandleConfig(ErrNoHandleConfig errNoHandleConfig) {
        this.errNoHandleConfig = errNoHandleConfig;
    }

}
