package com.yc.tiktok.core.entity.order;

import lombok.Data;

@Data
public class AllianceInfo {

    // 作者账号(抖音/火山作者)
    private String author_account;
    // 实际值的10000倍,譬如佣金率是10%, 该值为0.1*10000=1000
    private Integer commission_rate;
    // 火山/抖音号id
    private Long short_id;
    // 实际佣金,单位是分
    private Integer real_commission;
}
