package com.superhonor.elasticsearch.service;

import com.alibaba.fastjson.JSONObject;
import com.superhonor.common.util.DozerHelper;
import org.apache.commons.collections.CollectionUtils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author liuweidong
 */
@SuppressWarnings({"unused", "rawtypes", "UnusedReturnValue", "TypeParameterExplicitlyExtendsObject"})
@Service
public class ElasticSearchService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ElasticSearchService.class);

    private final RestHighLevelClient restHighLevelClient;

    private DozerHelper dozerHelper;

    @Autowired
    public void setDozerHelper(DozerHelper dozerHelper) {
        this.dozerHelper = dozerHelper;
    }

    public ElasticSearchService(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    public SearchResponse searchDoc(String index, QueryBuilder queryBuilder) {
        try {
            SearchRequest searchRequest = new SearchRequest(index);

            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.size(100);
            sourceBuilder.query(queryBuilder);
            LOGGER.info("SourceBuilder As:{}", sourceBuilder.toString());
            searchRequest.source(sourceBuilder);
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
            searchResponse.getHits().forEach(message -> {
                try {
                    String sourceAsString = message.getSourceAsString();
                    LOGGER.info("SourceAsString AS:{}", sourceAsString);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            return searchResponse;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Couldn't get Detail");
        }
    }

    public IndexResponse insertDoc(String index, String type, String id, Object obj) throws IOException {
        IndexRequest indexRequest = new IndexRequest(index, type, id);
        indexRequest.source(JSONObject.toJSONString(obj), XContentType.JSON);
        return restHighLevelClient.index(indexRequest);
    }

    public BulkResponse batchInsertDoc(String index, String type, List<? extends Object> list) throws IOException {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        BulkRequest request = new BulkRequest();
        list.forEach(e -> {
            Map map = dozerHelper.map(e, Map.class);
            request.add(new IndexRequest(index, type, String.valueOf(map.remove("id"))).source(map, XContentType.JSON));
        });
        return restHighLevelClient.bulk(request);
    }

    public DeleteResponse deleteDoc(String index, String type, String id) throws IOException {
        DeleteRequest request = new DeleteRequest(index, type, id);
        //同步执行
        return restHighLevelClient.delete(request);
    }

    public UpdateResponse updateDoc(String index, String type, String id, Object obj) throws IOException {
        UpdateRequest request = new UpdateRequest(index, type, id);
        //方式1：使用字符串形式
        request.doc(JSONObject.toJSONString(obj), XContentType.JSON);
        //同步执行
        return restHighLevelClient.update(request);
    }

    /**
     * 单条件检索
     * @param fieldKey 列名
     * @param fieldValue 值
     * @return MatchPhraseQueryBuilder
     */
    public MatchPhraseQueryBuilder uniqueMatchQuery(String fieldKey, Object fieldValue){
        return QueryBuilders.matchPhraseQuery(fieldKey,fieldValue);
    }

    /**
     * 多条件检索并集，适用于搜索比如包含腾讯大王卡，滴滴大王卡的用户
     * @param fieldKey 列名
     * @param queryList 值
     * @return BoolQueryBuilder
     */
    public BoolQueryBuilder orMatchUnionWithList(String fieldKey, List<Object> queryList){
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        for (Object fieldValue : queryList){
            boolQueryBuilder.should(QueryBuilders.matchPhraseQuery(fieldKey,fieldValue));
        }
        return boolQueryBuilder;
    }

    /**
     * 范围查询，左右都是闭集
     * @param fieldKey 列名
     * @param start  开始
     * @param end 结束
     * @return RangeQueryBuilder
     */
    public RangeQueryBuilder rangeMathQuery(String fieldKey, Object start, Object end){
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(fieldKey);
        rangeQueryBuilder.gte(start);
        rangeQueryBuilder.lte(end);
        return rangeQueryBuilder;
    }

    /**
     * 根据中文分词进行查询
     * @param fieldKey 列名
     * @param fieldValue 值
     * @return MatchQueryBuilder
     */
    public MatchQueryBuilder matchQueryBuilder(String fieldKey, Object fieldValue){
        return QueryBuilders.matchQuery(fieldKey,fieldValue).analyzer("ik_smart");
    }
}
