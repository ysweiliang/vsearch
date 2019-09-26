package com.macro.vsearch.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * 搜索实现
 *
 * @author yuans
 * @create 2019-07-18-23:04
 */
@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping(value = "/search")
    public JSONObject search(@RequestBody JSONObject keyWordJson) {
        return searchService.searchByKeyWord(keyWordJson);
    }

    /**
     * 搜索下拉框
     * @param suggestJson
     * @return
     */
    @PostMapping(value = "/search/dropDown")
    public List<String> dropDown(@RequestBody JSONObject suggestJson) {
        return searchService.getSuggestDropDown(suggestJson);
    }

    /**
     * 聚合查询示例
     * @param suggestJson
     * @return
     */
    @PostMapping(value = "/search/suggest")
    public List<String> getSuggest(@RequestBody JSONObject suggestJson) {
        return searchService.getSuggestCompletion(suggestJson);
    }
}
