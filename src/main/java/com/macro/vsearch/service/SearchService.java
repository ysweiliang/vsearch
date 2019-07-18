package com.macro.vsearch.service;

import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
;import java.io.IOException;

/**
 * @author yuans
 * @create 2019-07-18-23:07
 */
@Service
public class SearchService {

    private static final Logger logger = LoggerFactory.getLogger(SearchService.class);

    @Autowired
    private RestHighLevelClient client;

    public JSONObject searchByKeyWord(JSONObject keyWordJson) {
        JSONObject resultJson = new JSONObject();
        String keyWord = keyWordJson.getString("keyWord");
        //Search请求
        SearchRequest searchRequest = new SearchRequest("movies");

        //查询过滤条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termQuery("title", keyWord));
        searchRequest.source(searchSourceBuilder);

        try {
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

            SearchHits searchHits = searchResponse.getHits();

            for (SearchHit hit : searchHits) {
                System.out.println("index:" + hit.getIndex());
                System.out.println("type:" + hit.getType());
                System.out.println("id:" + hit.getId());
                System.out.println("sourceString:" + hit.getSourceAsString());
            }
            resultJson.put("searchHits", searchHits);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultJson;
    }

}
