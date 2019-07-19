package com.macro.vsearch.controller;

import com.macro.vsearch.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;

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
}
