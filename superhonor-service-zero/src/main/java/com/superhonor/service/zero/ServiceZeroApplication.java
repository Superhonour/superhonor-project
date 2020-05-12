package com.superhonor.service.zero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author liuweidong
 */
@SpringBootApplication
public class ServiceZeroApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceZeroApplication.class, args);
        System.out.println("=================================");
        System.out.println("[service-zero] 启动完成!!!");
        System.out.println("=================================");
    }
}
