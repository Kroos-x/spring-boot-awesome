package com.yc.tiktok.core.entity.order;

import com.sun.org.glassfish.gmbal.NameValue;
import lombok.Data;

import java.util.List;

@Data
public class Order {

    private String order_id;

    private Long shop_id;

    // 在抖音小程序下单时，买家的抖音小程序ID
    private String open_id;

    private String pid;

    private List<Order> child;

    private Long product_id;
    private Long out_product_id;

    private String product_name;

    private String product_pic;

    // 该子订单购买的商品 sku_id
    private Long combo_id;
    private Long out_sku_id;

    private String code;

    private List<NameValue> spec_desc;

    private Address post_addr;

    private String post_code;

    private String post_receiver;

    private String post_tel;

    private String buyer_words;

    private String seller_words;

    private Integer logistics_id;

    private String logistics_code;

    private Long logistics_time;

    private Long receipt_time;

    private Integer order_status;

    private Integer final_status;

    // 订单类型 订单类型 (0:普通订单，2:虚拟订单，4:电子券)
    // (0实物，2普通虚拟，4poi核销，5三方核销，6服务市场)
    private Integer order_type;

    // 订单预售类型 (1:全款预售订单)
    private Integer pre_sale_type;

    private Long create_time;

    private Long update_time;

    private Long exp_ship_time;

    private String cancel_reason;
    // 支付类型 (0：货到付款，1：微信，2：支付宝）
    private Integer pay_type;

    private Long pay_time;

    private Integer combo_amount;

    private Integer combo_num;

    private Integer post_amount;

    // 达人ID
    private Long author_id;

    private AllianceInfo alliance_info;

    private Integer coupon_amount;

    private Integer shop_coupon_amount;

    private Long coupon_meta_id;

    private List<CouponInfo> coupon_info;

    private Long campaign_id;

    private List<CampaignInfo> campaign_info;

    // 店铺满减优惠信息(shop_campaign_id：店铺满减活动ID，shop_full_amount：分摊到该子订单上的满减金额，单位：分
//    private Integer shop_full_campaign;
    /**
     * 父订单总金额 (单位: 分) 即用户实际支付金额, 不包含运费
     */
    private Integer order_total_amount;

    /**
     * 该子订单总金额 (单位: 分)
     */
    private Integer total_amount;

    // 是否评价 (1:已评价)
    private Integer is_comment;

    // 催单次数
    private Integer urge_cnt;
    // 订单APP渠道
    private Integer b_type;

    // 订单来源类型
    //0:未知
    //1:app
    //2:小程序
    //3:h5
    private Integer sub_b_type;

    // 1	鲁班广告
    //2	联盟
    //4	商城
    //8	自主经营
    //10	线索通支付表单
    //12	抖音门店
    //14	抖+
    //15	穿山甲
    private Integer c_biz;

    /**
     * child_num 总会与child.size()一致，所以没有什么意义
     */
    private Integer child_num;

    // 仓库ID
    private Integer warehouse_id;
    // 仓库外部ID
    private Integer out_warehouse_id;
    // 供应商ID
    private Integer warehouse_supplier;

}
