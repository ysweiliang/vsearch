package com.macro.vsearch.service;

import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author yuans
 * @create 2019-07-18-23:07
 */
@Service
public class SearchService {

    private static final Logger logger = LoggerFactory.getLogger(SearchService.class);

    @Autowired
    private RestHighLevelClient client;

    /**
     * 查询接口
     *
     * @param keywordJson:{"keyword":"beautiful","pageNum":0,"pageSize":5}
     * @return 返回查询结果及分页
     */
    public JSONObject searchByKeyWord(JSONObject keywordJson) {
        JSONObject searchJson = new JSONObject();
        List list = new ArrayList();
        String keyword = keywordJson.getString("keyword");
        Integer pageNum = keywordJson.getInteger("pageNum");
        Integer pageSize = keywordJson.getInteger("pageSize");
        //Search请求
        SearchRequest searchRequest = new SearchRequest();

        //查询过滤条件，fuzziness：模糊查询，maxExpansions：模糊度
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder matchQueryBuilder = QueryBuilders.multiMatchQuery(keyword, "title", "content")
                .fuzziness(Fuzziness.AUTO)
                .prefixLength(3)
                .maxExpansions(10);
        searchSourceBuilder.query(matchQueryBuilder);
        //设置搜索分页
        searchSourceBuilder.from(pageNum);
        searchSourceBuilder.size(pageSize);
        //设置搜索超时时间
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        //按字段score排序
        searchSourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.ASC));
        //按字段id排序
        searchSourceBuilder.sort(new FieldSortBuilder("_id").order(SortOrder.ASC));
        //搜索字段高亮显示
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        HighlightBuilder.Field highlightTitle =
                new HighlightBuilder.Field("title");
//        highlightTitle.highlighterType("unified");
        highlightBuilder.preTags("<span style=\"color:red\">");//设置前缀
        highlightBuilder.postTags("</span>");//设置后缀
        highlightBuilder.field(highlightTitle);//设置高亮字
        searchSourceBuilder.highlighter(highlightBuilder);
        //添加searchSourceBuilder到SearchRequest
        searchRequest.indices("movies");
        searchRequest.source(searchSourceBuilder);

        try {
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits searchHits = searchResponse.getHits();
            long totalHits = searchHits.getTotalHits().value;
            for (SearchHit hit : searchHits) {
                String sourceAsString = hit.getSourceAsString();
                JSONObject jsonObject = JSONObject.parseObject(sourceAsString);
                Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                HighlightField title = highlightFields.get("title");
                if (title != null) {
                    Text[] fragments = title.fragments();
                    String str = "";
                    for (Text text : fragments) {
                        str += text;
                    }
                    jsonObject.put("title", str);
                    list.add(jsonObject);
                }
            }
            searchJson.put("pageNum", pageNum);
            searchJson.put("pageSize", pageSize);
            searchJson.put("total", totalHits);
            searchJson.put("rows", list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchJson;
    }

}
