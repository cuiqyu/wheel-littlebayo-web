package com.littlebayo.web.server;

import com.littlebayo.web.server.handle.HttpRequestHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;


/**
 * Http服务初始化器
 *
 * @author cuiqiongyu
 * @date 2020/11/4 15:10
 */
public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        // http 编解码
        pipeline.addLast(new HttpServerCodec());
        // http 消息聚合器
        pipeline.addLast("httpAggregator", new HttpObjectAggregator(512 * 1024));
        // 请求处理器
        pipeline.addLast(new HttpRequestHandler());
    }

}
