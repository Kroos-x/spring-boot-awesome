package com.yc.tiktok.core.tiktok.response.order;

import com.alibaba.fastjson.annotation.JSONField;
import com.yc.tiktok.core.entity.order.Order;
import com.yc.tiktok.core.tiktok.response.PageList;
import com.yc.tiktok.core.tiktok.response.TikTokOpenResponse;

import java.util.ArrayList;
import java.util.List;

public class OrderListResponse extends TikTokOpenResponse<PageList<Order>> {

    @JSONField(serialize = false, deserialize = false)
    public List<Order> getContents() {
        if (getData() != null && getData().getList() != null) {
            return getData().getList();
        }
        return new ArrayList<>();
    }

}
