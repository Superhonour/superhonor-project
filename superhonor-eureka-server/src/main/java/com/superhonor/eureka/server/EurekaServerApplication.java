package com.superhonor.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author liuweidong
 */
@SpringBootApplication
@EnableEurekaServer
@EnableFeignClients
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
        System.out.println("=================================");
        System.out.println("[Eureka-Server] 启动完成!!!");
        System.out.println("=================================");
    }

}
