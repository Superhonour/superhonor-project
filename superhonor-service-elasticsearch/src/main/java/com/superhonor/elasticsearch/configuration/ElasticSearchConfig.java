package com.superhonor.elasticsearch.configuration;

import com.superhonor.elasticsearch.utils.DozerHelper;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author liuweidong
 */
@Configuration
@ComponentScan(basePackageClasses= ElasticClientSpringFactory.class)
public class ElasticSearchConfig {

    @Value("${elasticSearch.host}")
    private String host;

    @Value("${elasticSearch.port}")
    private int port;

    @Value("${elasticSearch.client.connectNum}")
    private Integer connectNum;

    @Value("${elasticSearch.client.connectPerRoute}")
    private Integer connectPerRoute;

    @Bean
    public HttpHost httpHost(){
        return new HttpHost(host,port,"http");
    }

    @Bean(initMethod="init",destroyMethod="close")
    public ElasticClientSpringFactory getFactory(){
        return ElasticClientSpringFactory.
                build(httpHost(), connectNum, connectPerRoute);
    }

    @Bean
    @Scope("singleton")
    public RestClient getRestClient(){
        return getFactory().getClient();
    }

    @Bean
    @Scope("singleton")
    public RestHighLevelClient getRestHighLevelClient(){
        return getFactory().getRhlClient();
    }

    @Bean
    @Scope
    public DozerHelper dozerHelper() {
        return new DozerHelper();
    }
}

