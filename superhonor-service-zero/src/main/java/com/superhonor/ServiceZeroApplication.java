package com.superhonor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author liuweidong
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCaching
public class ServiceZeroApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceZeroApplication.class, args);
        System.out.println("=================================");
        System.out.println("[service-zero] 启动完成!!!");
        System.out.println("=================================");
    }
}
