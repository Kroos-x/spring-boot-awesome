package com.yc.es.service;

import com.alibaba.fastjson.JSON;
import com.yc.es.entity.HtmlContent;
import com.yc.es.util.HtmlParseUtil;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.awt.image.TileObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述:
 *
 * @Author: xieyc
 * @Date: 2020-12-28
 */
@Service
public class ContentService {

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    public Boolean parseContent(String keyWord) throws Exception {
        List<HtmlContent> result = new HtmlParseUtil().parseJD(keyWord);
        // 放入es
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("2m");
        for (int i = 0; i < result.size(); i++) {
            bulkRequest.add(new IndexRequest("jd_goods")
                    .source(JSON.toJSONString(result.get(i)), XContentType.JSON)
            );
        }
        BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println("=============================");
        System.out.println(bulkResponse.hasFailures()); // false 代表成功
        System.out.println("=============================");
        return !bulkResponse.hasFailures();
    }

    public List<Map<String, Object>> search(String keyWord, Integer pageNo, Integer pageSize) throws IOException {
        if (pageNo <= 1) {
            pageNo = 1;
        }
        SearchRequest searchRequest = new SearchRequest("jd_goods");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        // 分页
        searchSourceBuilder.from(pageNo);
        if(pageNo == 1 ){
            searchSourceBuilder.size( pageSize);
        } else {
            searchSourceBuilder.size((pageNo - 1) * pageSize);
        }
        // 精准匹配
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", keyWord);
        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        // 执行搜索
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        // 解析结果
        ArrayList<Map<String, Object>> result = new ArrayList<>();
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            result.add(hit.getSourceAsMap());
        }
        return result;
    }


    public List<Map<String, Object>> searchHighLight(String keyWord, Integer pageNo, Integer pageSize) throws IOException {
        if (pageNo <= 1) {
            pageNo = 1;
        }
        SearchRequest searchRequest = new SearchRequest("jd_goods");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        // 分页
        searchSourceBuilder.from(pageNo);
        if(pageNo == 1 ){
            searchSourceBuilder.size( pageSize);
        } else {
            searchSourceBuilder.size((pageNo - 1) * pageSize);
        }
        // 精准匹配
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", keyWord);
        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        // 高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title");
        // 同一条信息多个高亮显示
        highlightBuilder.requireFieldMatch(false);
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        searchSourceBuilder.highlighter(highlightBuilder);

        // 执行搜索
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        // 解析结果
        ArrayList<Map<String, Object>> result = new ArrayList<>();
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            Map<String, HighlightField> highlightFieldMap = hit.getHighlightFields();
            HighlightField title = highlightFieldMap.get("title");
            Map<String, Object> souraceMap = hit.getSourceAsMap();
            // 解析高亮的字段,将原来的字段换为我们高亮的字段即可
            if (title != null) {
                Text[] fragments = title.fragments();
                String n_title = "";
                for (Text fragment : fragments) {
                    n_title += fragment;
                }
                souraceMap.put("title", n_title);
            }
            result.add(souraceMap);
        }
        return result;
    }

}
