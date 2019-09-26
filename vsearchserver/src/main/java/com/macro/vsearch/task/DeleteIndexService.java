package com.macro.vsearch.task;

import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 删除ES中索引数据
 */
public class DeleteIndexService {

    @Autowired
    private RestHighLevelClient client;

    public void deleteIndex(String indexName) {
        //判断索引是否存在
        if (!isIndexExists(indexName)) {
            System.out.println(indexName + " not exists");
        }

//        if (dResponse.isAcknowledged()) {
//            System.out.println("delete index " + indexName + "  successfully!");
//        } else {
//            System.out.println("Fail to delete index " + indexName);
//        }
    }

    // 判断索引是否存在 传入参数为索引库名称
    private boolean isIndexExists(String indexName) {
        return false;
    }

}
