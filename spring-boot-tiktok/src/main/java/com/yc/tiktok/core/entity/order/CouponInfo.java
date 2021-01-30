package com.yc.tiktok.core.entity.order;

import lombok.Data;

/**
 * 优惠券详情
 */
@Data
public class CouponInfo {

    private Long id;
    private String name;
    private String description;
    private Integer credit;
    private Integer type;
    private Integer discount;
    private Integer pay_type;
}
