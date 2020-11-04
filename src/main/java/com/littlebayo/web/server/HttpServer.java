package com.littlebayo.web.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.net.InetSocketAddress;

/**
 * 提供http通信连接服务
 *
 * @author cuiqiongyu
 * @date 2020/11/4 15:03
 */
@AllArgsConstructor
@NoArgsConstructor
public class HttpServer {

    /**
     * Http请求连接端口，默认8080
     */
    private int port = 8080;

    /**
     * 启动方法
     */
    public void start() throws Exception {
        // 添加启动引导类
        ServerBootstrap bootstrap = new ServerBootstrap();
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup work = new NioEventLoopGroup();

        bootstrap.group(boss, work)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .channel(NioServerSocketChannel.class)
                .childHandler(new HttpServerInitializer());

        ChannelFuture cf = bootstrap.bind(new InetSocketAddress(port)).sync();
        System.out.println(" server start up on port : " + port);
        cf.channel().closeFuture().sync();
    }

}
