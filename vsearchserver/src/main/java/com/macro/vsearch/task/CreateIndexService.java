package com.macro.vsearch.task;


import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 新建索引
 *
 * @author yuans
 * @create 2019-08-15-11:55
 */
@Service
public class CreateIndexService {

    @Autowired
    private RestHighLevelClient client;

    public void createIndex() throws IOException {
        //1、创建索引，设置索引名
        CreateIndexRequest request = new CreateIndexRequest("article");
        //2、设置索引的settings
        Settings settings = Settings.builder()
                .put("index.number_of_shards", 3)//分片数
                .put("index.number_of_replicas", 1)//副本数
//                .put("analysis.analyzer.default.tokenizer", "ik_smart")//默认分词器
                .build();
        request.settings(settings);
        //3、设置索引的mappings
        XContentBuilder mapping = XContentFactory.jsonBuilder()
                .startObject()
                .startObject("properties") //下面是设置文档列属性。
                .startObject("ARTICLE_ID").field("type", "text").endObject()
                .startObject("CONTENT").field("type", "text").endObject()
                .startObject("TITLE").field("type", "text").endObject()
                .startObject("AUTHOR").field("type", "text").endObject()
                .startObject("COLLECT").field("type", "integer").endObject()
                .startObject("PAGE_VIEW").field("type", "integer").endObject()
                .endObject()
                .endObject();
        request.mapping(mapping);
        //5、发送方式:采用同步方式发送请求
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        //处理响应
        boolean acknowledged = response.isAcknowledged();
        boolean shardsAcknowledged = response.isShardsAcknowledged();
        System.out.println("acknowledged = " + acknowledged);
        System.out.println("shardsAcknowledged = " + shardsAcknowledged);
        client.close();
    }
}
