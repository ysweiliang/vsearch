package com.macro.vsearch.util;

import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * elasticsearch相关工具
 *
 * @author yuans
 * @create 2019-10-22-16:10
 */
public class ESUtils {

    @Autowired
    private static RestHighLevelClient client;

    /**
     * 精确校验某个字段值是否存在
     * @param jsonObject
     * @return
     * @throws IOException
     */
    public static JSONObject verifyAddress(JSONObject jsonObject) throws IOException {
        JSONObject resultObj = new JSONObject();
        //字段精确查询必须加keyword，否则会查询不到
        QueryBuilder addrFullTermQuery = QueryBuilders.termQuery("ADDR_FULL.keyword", jsonObject.getString("addrFull"));
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(addrFullTermQuery);
        searchRequest.source(searchSourceBuilder);
        SearchResponse addrFullResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        if (addrFullResponse.getHits().getHits().length > 0) {
            resultObj.put("resultcode", 0);
            resultObj.put("msg", "存在相同地址");
            return resultObj;
        } else {
            resultObj.put("resultcode", 1);
            resultObj.put("msg", "无相同地址");
            return resultObj;
        }
    }
}
