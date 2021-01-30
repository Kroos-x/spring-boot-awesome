package com.yc.tiktok.core.entity.order;

import lombok.Data;

@Data
public class Address {

    private Addr city;

    private String detail;

    private Addr province;

    private Addr town;

    @Data
    public static class Addr {
        private Long id;
        private String name;

    }

}
