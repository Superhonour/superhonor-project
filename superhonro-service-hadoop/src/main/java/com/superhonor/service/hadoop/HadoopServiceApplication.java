package com.superhonor.service.hadoop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author liuweidong
 */
@SpringBootApplication
public class HadoopServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HadoopServiceApplication.class, args);
        System.out.println("=================================");
        System.out.println("[service-hadoop] 启动完成!!!");
        System.out.println("=================================");
    }
}
