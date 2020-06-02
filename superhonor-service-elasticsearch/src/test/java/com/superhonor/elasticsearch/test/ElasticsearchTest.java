package com.superhonor.elasticsearch.test;

import com.alibaba.fastjson.JSON;
import com.superhonor.elasticsearch.ElasticsearchApplication;
import com.superhonor.elasticsearch.service.ElasticIndexService;
import com.superhonor.elasticsearch.service.ElasticSearchService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ElasticsearchApplication.class})
@Slf4j
public class ElasticsearchTest {

    @Autowired
    private ElasticSearchService elasticSearchService;

    @Autowired
    private ElasticIndexService elasticIndexService;

    @Test
    public void searchDocTest() {
        log.info("abc");
        log.warn("abc");
        log.error("abc");
//        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
//        boolQueryBuilder.must(elasticSearchService.uniqueMatchQuery("plateNo", "鲁A123456"));
//        MatchQueryBuilder matchQueryBuilder = elasticSearchService.matchQueryBuilder("plateNo", "abcd");
        RangeQueryBuilder queryBuilder = elasticSearchService.rangeMathQuery("fee", "105", "107");
        SearchResponse response = elasticSearchService.searchDoc("etc_pass_record", queryBuilder);
        SearchHit[] results = response.getHits().getHits();
        for (SearchHit hit : results) {
            String source = hit.getSourceAsString();
            PassRecordVo passRecordVo = JSON.parseObject(source, PassRecordVo.class);
            System.out.println(passRecordVo);
        }
    }

    @Test
    public void insertDocTest() throws IOException {
        elasticSearchService.insertDoc("etc_pass_record",
                "pass_record",
                "1", PassRecordVo.init());
    }

    @Test
    public void batchInsertDocTest() throws IOException {
        List<PassRecordVo> list = new ArrayList <>();
        list.add(PassRecordVo.init());
        elasticSearchService.batchInsertDoc("etc_pass_record", "pass_record", list);
    }

    @Test
    public void updateDocTest() throws IOException {
        elasticSearchService.updateDoc("etc_pass_record",
                "pass_record",
                "1", PassRecordVo.init());
    }

    @Test
    public void deleteDocTest() throws IOException {
        for (int i = 0; i < 10; i++) {
            elasticSearchService.deleteDoc("etc_pass_record",
                    "pass_record",
                    String.valueOf(i));
        }
    }

    @Test
    public void createIndexTest() {
        elasticIndexService.createIndex("etc_pass_record", "pass_record", "alias", "{\n" +
                "\t\"pass_record\": {\n" +
                "\t\t\"properties\": {\n" +
                "\t\t\t\"id\": {\n" +
                "\t\t\t\t\"type\": \"long\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"cardNo\": {\n" +
                "\t\t\t\t\"type\": \"keyword\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"plateNo\": {\n" +
                "\t\t\t\t\"type\": \"text\",\n" +
                "\t\t\t\t\"analyzer\": \"ik_max_word\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"passRecordOrigId\": {\n" +
                "\t\t\t\t\"type\": \"long\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"uniqueIdentifier\": {\n" +
                "\t\t\t\t\"type\": \"keyword\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"productId\": {\n" +
                "\t\t\t\t\"type\": \"long\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"customerId\": {\n" +
                "\t\t\t\t\"type\": \"long\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"customerName\": {\n" +
                "\t\t\t\t\"type\": \"text\",\n" +
                "\t\t\t\t\"analyzer\": \"ik_max_word\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"passTime\": {\n" +
                "\t\t\t\t\"type\": \"date\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"chargeTime\": {\n" +
                "\t\t\t\t\"type\": \"date\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"fee\": {\n" +
                "\t\t\t\t\"type\": \"float\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"entrance\": {\n" +
                "\t\t\t\t\"type\": \"text\",\n" +
                "\t\t\t\t\"analyzer\": \"ik_max_word\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"exit\": {\n" +
                "\t\t\t\t\"type\": \"text\",\n" +
                "\t\t\t\t\"analyzer\": \"ik_max_word\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"delFlag\": {\n" +
                "\t\t\t\t\"type\": \"boolean\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"createTime\": {\n" +
                "\t\t\t\t\"type\": \"date\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"modifiedTime\": {\n" +
                "\t\t\t\t\"type\": \"date\"\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}");
    }

    @Test
    public void deleteIndex() throws IOException {
        elasticIndexService.deleteIndex("etc_pass_record");
    }
}
