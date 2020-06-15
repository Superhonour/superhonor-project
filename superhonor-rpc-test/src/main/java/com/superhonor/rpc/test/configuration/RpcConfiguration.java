package com.superhonor.rpc.test.configuration;

import com.superhonor.rpc.annotation.EnableRpcConfiguration;
import com.superhonor.rpc.client.RpcInjectHandler;
import com.superhonor.rpc.conf.Consts;
import com.superhonor.rpc.server.RpcServer;
import com.superhonor.rpc.zk.ZkClient;
import com.superhonor.rpc.zk.ZkServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * ZRPC配置类
 *
 * @author liuweidong
 */
@Configuration
@ConditionalOnBean(annotation = EnableRpcConfiguration.class)
public class RpcConfiguration {

    @Autowired
    private Environment env;

    /**
     * 注册中心（ZooKeeper）地址
     */
    @Value("${zrpc.register.zkServeres}")
    private String zkServeres;

    /**
     * 注册中心（ZooKeeper）连接超时时间
     */
    @Value("${zrpc.register.timeout:30000}")
    private int timeout;

    /**
     * 服务提供者的zk客户端
     * @return
     */
    @Bean
    @ConditionalOnProperty(prefix="zrpc", name="service", havingValue="true")
    public ZkServer zkServer() {
        return new ZkServer(zkServeres, timeout);
    }

    /**
     * RPC实例注册服务
     * @return
     */
    @Bean
    @ConditionalOnBean(ZkServer.class)
    public RpcServer rpcServer() {
        String serviceIp = Consts.LOCAL_IP;
        int servicePort = env.getProperty("zrpc.servicePort", int.class, 31226);
        return new RpcServer(serviceIp, servicePort, zkServer());
    }

    /**
     * 服务消费者的zk客户端
     * @return
     */
    @Bean
    public ZkClient zkClient() {
        return new ZkClient(zkServeres, timeout);
    }

    /**
     * RPC实例注入处理器
     * @return
     */
    @Bean
    @ConditionalOnBean(ZkClient.class)
    public RpcInjectHandler rpcInjectHandler() {
        return new RpcInjectHandler(zkClient());
    }
}
