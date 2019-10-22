package com.macro.vsearch.task;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.ResultSet;

/**
 * 数据迁移：将MYSQL中的数据迁移到ES中
 *
 * @author yuans
 * @create 2019-09-27-15:32
 */
@Slf4j
@Service
public class DataMigrationService {

    @Resource
    private DataMigrationMapper dataMigrationMapper;

    public void DataMigrationService() {

    }

    private void addIndexData(ResultSet result) {
        try {
            XContentBuilder jsonBuild = XContentFactory.jsonBuilder();
        } catch (IOException e) {
            log.info("e:{}", e.toString());
        }
    }
}
