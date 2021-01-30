package com.yc.tiktok.core.tiktok.auth;

import lombok.Data;

@Data
public class AccessToken {
    private Integer err_no;
    private String message;
    private Token data;

    @Data
    public static class Token {
        private String access_token;
        private String refresh_token;
        // 单位（秒）
        private Long expires_in;
        // 授权作用域，使用逗号,分隔。预留字段
        private String scope;
        private String shop_id;
        private String shop_name;
    }

}
