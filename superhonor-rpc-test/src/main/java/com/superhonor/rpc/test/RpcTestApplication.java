package com.superhonor.rpc.test;

import com.superhonor.rpc.annotation.EnableRpcConfiguration;;
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
@EnableRpcConfiguration
public class RpcTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpcTestApplication.class, args);
        System.out.println("=================================");
        System.out.println("[rpc-test] 启动完成!!!");
        System.out.println("=================================");
    }

}
