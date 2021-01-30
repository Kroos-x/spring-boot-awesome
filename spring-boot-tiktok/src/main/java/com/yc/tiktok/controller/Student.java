package com.yc.tiktok.controller;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 功能描述:
 *
 * @Author: xieyc
 * @Date: 2021-01-27
 */
@Data
public class Student {

    @JSONField(name = "post_code")
    private String postCode;

    @JSONField(name = "post_receiver")
    private String postReceiver;

    @JSONField(name = "post_tel")
    private String postTel;

    @JSONField(name = "buyer_words")
    private String buyerWords;

    @JSONField(name = "seller_words")
    private String sellerWords;

    @JSONField(name = "logistics_id")
    private Integer logisticsId;

}
