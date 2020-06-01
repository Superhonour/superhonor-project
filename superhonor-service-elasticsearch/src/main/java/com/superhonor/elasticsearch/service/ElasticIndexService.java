package com.superhonor.elasticsearch.service;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author liuweidong
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
@Service
public class ElasticIndexService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ElasticIndexService.class);

    private final RestHighLevelClient restHighLevelClient;

    public ElasticIndexService(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    public void createIndex(String index, String type, String alias, String source) {
        //创建索引
        CreateIndexRequest request = new CreateIndexRequest(index);
        //创建的每个索引都可以有与之关联的特定设置。
        request.settings(Settings.builder()
                .put("index.number_of_shards", 3)
                .put("index.number_of_replicas", 2)
        );
        //创建索引时创建文档类型映射
        //类型定义
        //类型映射，需要的是一个JSON字符串
        request.mapping(type, source, XContentType.JSON);

        //为索引设置一个别名
        request.alias(new Alias(alias));

        //异步方法不会阻塞并立即返回。
        ActionListener <CreateIndexResponse> listener = new ActionListener <CreateIndexResponse>() {
            @Override
            public void onResponse(CreateIndexResponse createIndexResponse) {
                //如果执行成功，则调用onResponse方法;
                LOGGER.info("index [{}] create success", index);
            }
            @Override
            public void onFailure(Exception e) {
                //如果失败，则调用onFailure方法。
                LOGGER.error("index [{}] create fail", index);
            }
        };
        //要执行的CreateIndexRequest和执行完成时要使用的ActionListener
        restHighLevelClient.indices().createAsync(request, listener);
    }

    public boolean deleteIndex(String index) throws IOException {
        //指定要删除的索引名称
        DeleteIndexRequest request = new DeleteIndexRequest(index);

        //设置IndicesOptions控制如何解决不可用的索引以及如何扩展通配符表达式
        request.indicesOptions(IndicesOptions.lenientExpandOpen());

        //同步执行
        DeleteIndexResponse deleteIndexResponse = restHighLevelClient.indices().delete(request);

        //是否所有节点都已确认请求
        return deleteIndexResponse.isAcknowledged();
    }
}
