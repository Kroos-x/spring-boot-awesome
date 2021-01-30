package com.yc.tiktok.core.tiktok.message;

import lombok.Data;

@Data
public class MsgOrderInfo {

    private long app_id;
    private PushOrderInfo order_info;

    @Data
    public static class PushOrderInfo {
        private long order_id;
        private long shop_id;
        // 服务开始时间
        private long service_start_time;
        // 服务结束时间
        private long service_end_time;
        // 1：订单待付款，4：订单取消，5：已完成
        private int status;
        // 	店铺所有者的手机号
        private String phone;
        // 	用户实际支付的金额，以分为单位
        private long pay_amount;
        //
        private long pay_time;
        private long order_create_time;
        // 1：微信，2：支付宝，4：该订单为试用订单，0元单
        private int pay_type;
        private PushSkuInfo push_sku_info;

    }

    @Data
    public static class PushSkuInfo {
        // 规格类型--0: 版本，其他待定
        private int spec_type;
        // 规格名称
        private String spec_value;
        // sku价格
        private long price;
        // 使用时长
        private int duration;
        // 使用时长单位
        private int duration_unit;
        // sku名称
        private String title;

    }
}
