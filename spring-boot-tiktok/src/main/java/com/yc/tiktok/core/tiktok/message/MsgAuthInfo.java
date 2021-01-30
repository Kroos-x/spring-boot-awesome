package com.yc.tiktok.core.tiktok.message;

import lombok.Data;

/**
 * @author xieyc
 * @date 2020-01-27
 */
@Data
public class MsgAuthInfo {

    private int action_type;
    private long app_id;
    private long shop_id;

}
