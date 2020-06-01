package com.superhonor.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author liuweidong
 */
@SpringBootApplication
public class ElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchApplication.class, args);
        System.out.println("=================================");
        System.out.println("[service-elasticsearch] 启动完成!!!");
        System.out.println("=================================");
    }

}
