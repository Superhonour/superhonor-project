package com.superhonor.rpc.server;

import com.superhonor.rpc.common.RpcDecoder;
import com.superhonor.rpc.common.RpcEncoder;
import com.superhonor.rpc.util.RpcException;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 启动Netty监听服务
 *
 * @author liuweidong
 */
@Slf4j
public class ServerListener extends Thread {

    /**
     * 服务地址
     */
    private String serviceIp;
    /**
     * 服务端口
     */
    private int servicePort;
    /**
     * rpc服务
     */
    private Map <String, Object> handlers = new HashMap <String, Object>();

    private ServerListener(String serviceIp, int servicePort, Map<String, Object> handlers) {
        this.serviceIp = serviceIp;
        this.servicePort = servicePort;
        this.handlers = handlers;
    }

    public static ServerListener create(String serviceIp, int servicePort, Map<String, Object> handlers) {
        return new ServerListener(serviceIp, servicePort, handlers);
    }

    @Override
    public void run() {
        // 启动Netty服务
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(workerGroup, bossGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel channel) throws Exception {
                            // 添加编码、解码、业务处理的handler
                            channel.pipeline().addLast(new RpcDecoder()).addLast(new RpcEncoder())
                                    .addLast(new RpcServerHandler(handlers));
                        }
                    }).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);

            String host = serviceIp;
            int port = servicePort;
            ChannelFuture future = bootstrap.bind(host, port).sync();
            log.info("server started on {}", host + ":" + port);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RpcException(e);
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

}
