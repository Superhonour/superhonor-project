package com.superhonor.rpc.client;

import com.superhonor.rpc.common.RpcDecoder;
import com.superhonor.rpc.common.RpcEncoder;
import com.superhonor.rpc.common.RpcRequest;
import com.superhonor.rpc.common.RpcResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * 客户端请求服务器类
 * 主要用于RPC服务
 *
 * @author liuweidong
 */
@Slf4j
public class RpcClientHandler extends SimpleChannelInboundHandler<RpcResponse> {

    /**
     * 服务对象地址
     */
    private String host;
    /**
     * 服务对象端口
     */
    private int port;
    /**
     * 响应结果
     */
    private RpcResponse response;
    /**
     * 用于阻塞连接关闭，等待结果返回
     */
    private CountDownLatch latch = new CountDownLatch(1);


    public RpcClientHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * 链接服务端，发送消息
     *
     * @param request
     * @return
     * @throws Exception
     */
    public RpcResponse send(RpcRequest request) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    // 添加编码、解码、业务处理的handler
                    ch.pipeline().addLast(new RpcEncoder())
                            .addLast(new RpcDecoder())
                            .addLast(RpcClientHandler.this);
                }

            }).option(ChannelOption.SO_KEEPALIVE, true);

            // 链接服务器
            ChannelFuture future = bootstrap.connect(host, port).sync();
            // 将request对象写入outbundle处理后发出（即RpcEncoder编码器）
            future.channel().writeAndFlush(request).sync();

            // 先在此阻塞，等待获取到服务端的返回后，被唤醒，从而关闭网络连接
            latch.await();
            if (response != null) {
                future.channel().closeFuture().sync();
            }
            return response;
        } finally {
            group.shutdownGracefully();
        }
    }

    /**
     * 读取服务端的返回结果
     */
    @Override
    public void channelRead0(ChannelHandlerContext ctx, RpcResponse response) throws Exception {
        this.response = response;
        latch.countDown();
    }

    /**
     * 异常处理
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("client caught exception", cause);
        ctx.close();
    }

}
