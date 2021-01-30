package com.yc.tiktok.core.tiktok.response;

import lombok.Data;

import java.util.List;

@Data
public class PageList<T> {

    private Integer count;

    private Integer total;

    private List<T> list;

}
