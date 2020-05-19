package com.superhonor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author liuweidong
 */
@SpringBootApplication
public class ServiceMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceMqApplication.class, args);
        System.out.println("=================================");
        System.out.println("[service-mq] 启动完成!!!");
        System.out.println("=================================");
    }

}
