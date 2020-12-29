package com.yc.es.util;

import com.yc.es.entity.HtmlContent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述: 网页爬取工具类
 *
 * @Author: xieyc
 * @Date: 2020-12-27
 */
@Component
public class HtmlParseUtil {

    private static final String JD_URL_PRE = "https://search.jd.com/Search?keyword=";

    /**
     * 爬取京东数据
     *
     * @param keyWord 关键字
     * @return list
     */
    public List<HtmlContent> parseJD(String keyWord) throws Exception {
        String url = JD_URL_PRE + keyWord;
        Document document = Jsoup.parse(new URL(url), 990000);
        Element element = document.getElementById("J_goodsList");
        Elements elements = element.getElementsByTag("li");
        ArrayList<HtmlContent> htmlContents = new ArrayList<>();
        for (Element item : elements) {
            if (item.attr("class").equalsIgnoreCase("gl-item")) {
                String img = item.getElementsByTag("img").eq(0).attr("data-lazy-img");
                String price = item.getElementsByClass("p-price").eq(0).text();
                String title = item.getElementsByClass("p-name").eq(0).text();

                htmlContents.add(new HtmlContent(img, price, title));
            }

        }
        return htmlContents;
    }


    public static void main(String[] args) {
        try {
            new HtmlParseUtil().parseJD("java").forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
