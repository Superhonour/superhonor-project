package com.superhonor.service.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author liuweidong
 */
@EnableFeignClients
@SpringBootApplication
@EnableEurekaClient
public class WebApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApiApplication.class, args);
        System.out.println("=================================");
        System.out.println("[Web-Api] 启动完成!!!");
        System.out.println("=================================");
    }
}
