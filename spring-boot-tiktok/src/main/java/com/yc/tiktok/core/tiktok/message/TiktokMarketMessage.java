package com.yc.tiktok.core.tiktok.message;

import lombok.Data;

/**
 * @author xieyc
 */
@Data
public class TiktokMarketMessage {

    private int msgType;

    private String message;

    private MsgAuthInfo msgAuthInfo;

    private MsgOrderInfo msgOrderInfo;

}
