package com.macro.vsearch.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * es索引处理
 *
 * @author yuans
 * @create 2019-09-24-14:52
 */
@RestController
public class ElasticSearchController {

    @Autowired
    private CreateIndexService createIndexService;

    @GetMapping(value = "/index/create")
    public void createIndex() throws IOException {
        createIndexService.createIndex();
    }
}
