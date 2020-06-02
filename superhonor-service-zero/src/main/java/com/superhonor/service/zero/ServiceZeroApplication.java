package com.superhonor.service.zero;

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
public class ServiceZeroApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceZeroApplication.class, args);
        System.out.println("=================================");
        System.out.println("[service-zero] 启动完成!!!");
        System.out.println("=================================");
    }
}
