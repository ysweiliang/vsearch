package com.macro.vsearch.client;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuans
 * @create 2019-07-18-23:51
 */
@Configuration
public class ElasticsearchConfiguration{
    private static final Logger logger = LoggerFactory.getLogger(ElasticsearchConfiguration.class);

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.192.145", 9201, "http"),
                        new HttpHost("192.168.192.145", 9202, "http"),
                        new HttpHost("192.168.192.145", 9203, "http")));
        return client;
    }

}
