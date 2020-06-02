package com.superhonor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author liuweidong
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceMqApplication.class, args);
        System.out.println("=================================");
        System.out.println("[service-mq] 启动完成!!!");
        System.out.println("=================================");
    }

}
