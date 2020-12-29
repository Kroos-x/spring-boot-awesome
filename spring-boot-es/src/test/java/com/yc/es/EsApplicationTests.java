package com.yc.es;

import com.alibaba.fastjson.JSON;
import com.yc.es.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsApplicationTests {

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    public static final String index = "kuang_index";
    // ========================== About Index OPERATION ====================

    /**
     * 创建索引 PUT kuang_index
     */
    @Test
    public void testCreateIndex() throws IOException {
        // 1.创建索引请求
        CreateIndexRequest request = new CreateIndexRequest(index);
        // 2.客户端执行请求,并返回结果
        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println("==============================");
        System.out.println(createIndexResponse);
        System.out.println("==============================");
    }

    /**
     * 判断索引是否存在
     *
     * @throws IOException
     */
    @Test
    public void testExistIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest(index);
        boolean exist = client.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println("==============================");
        System.out.println(exist);
        System.out.println("==============================");
    }


    /**
     * 删除索引
     */
    @Test
    public void testDelIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest(index);
        AcknowledgedResponse delete = client.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println("==============================");
        System.out.println(delete.isAcknowledged());
        System.out.println("==============================");
    }


    // =========================== About Doc Operation ======================

    /**
     * 创建文档信息
     */
    @Test
    public void testAddDoc() {
        // 创建请求
        IndexRequest request = new IndexRequest(index);
        // 配置规则
        request.id("1");
        // 响应时间
        request.timeout(TimeValue.timeValueSeconds(1));
        // 创建资源
        User user = new User("李红", 1);
        // 将数据放入请求Json
        request.source(JSON.toJSONString(user), XContentType.JSON);

        // 客户端发送请求
        try {
            IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
            indexResponse.toString();
            System.out.println("===================");
            System.out.println(indexResponse.toString());
            System.out.println(indexResponse.status());
            System.out.println("===================");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断文档是否存在
     */
    @Test
    public void testIsExist() throws IOException {
        GetRequest getRequest = new GetRequest(index, "1");
        boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println("=================================");
        System.out.println(exists);
        System.out.println("=================================");
    }

    /**
     * 获取文档信息
     *
     * @throws IOException
     */
    @Test
    public void testGetDoc() throws IOException {
        GetRequest getRequest = new GetRequest(index, "1");
        if (client.exists(getRequest, RequestOptions.DEFAULT)) {
            GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
            System.out.println("===============================");
            System.out.println(getResponse.getSourceAsString());
            System.out.println(getResponse);
            System.out.println("===============================");
        } else {
            System.out.println("文档不存在");
        }
    }

    /**
     * 更新文档
     */
    @Test
    public void testUpdateDoc() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest(index, "1");
        updateRequest.timeout("1s");

        User user = new User("王明", 23);
        updateRequest.doc(JSON.toJSONString(user), XContentType.JSON);
        UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println("====================");
        System.out.println(updateResponse.status());
        System.out.println(updateResponse);
        System.out.println("====================");
    }


    /**
     * 删除文档信息
     *
     * @throws IOException
     */
    @Test
    public void testDelDoc() throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest(index, "1");
        deleteRequest.timeout("1s");

        DeleteResponse deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println("===========================");
        System.out.println(deleteResponse.status());
        System.out.println("===========================");

    }


    /**
     * 批量插入
     *
     * @throws IOException
     */
    @Test
    public void testBulkReq() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");

        ArrayList<User> users = new ArrayList<>();
        users.add(new User("name1", 1));
        users.add(new User("name2", 2));
        users.add(new User("name3", 3));
        users.add(new User("name4", 4));
        users.add(new User("name5", 5));
        users.add(new User("name6", 6));

        for (int i = 0; i < users.size(); i++) {
            // 批量修改 new UpdateRequest
            // 批量删除 new DeleteRequest
            bulkRequest.add(new IndexRequest("kuang_index")
                    .source(JSON.toJSONString(users.get(i)), XContentType.JSON)
            );
        }
        BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println("=============================");
        System.out.println(bulkResponse.hasFailures()); // false 代表成功
        System.out.println("=============================");
    }


    /**
     * 查询
     */
    @Test
    public void testSearch() throws IOException {
        // 搜索请求
        SearchRequest searchRequest = new SearchRequest(index);
        // 构建搜索条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 高亮
        sourceBuilder.highlighter();
        // 查询条件,可以使用QueryBuilders 工具实现
        // QueryBuilders.termQuery 精确
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "lihong");
        // QueryBuilders.matchAllQuery() 匹配所有
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();

        sourceBuilder.query(matchAllQueryBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        searchRequest.source(sourceBuilder);

        SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("=================");
        System.out.println(JSON.toJSONString(search.getHits()));
        System.out.println("=================");
        for (SearchHit hit : search.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }


    }


}
