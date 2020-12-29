package com.yc.es.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 功能描述: 网页爬取内容
 *
 * @Author: xieyc
 * @Date: 2020-12-27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HtmlContent {

    /**
     * img
     */
    private String img;

    /**
     * price
     */
    private String price;

    /**
     * title
     */
    private String title;



}
