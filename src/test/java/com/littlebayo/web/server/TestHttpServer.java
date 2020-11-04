package com.littlebayo.web.server;

/**
 * 测试http服务连接
 *
 * @author cuiqiongyu
 * @date 2020/11/4 15:39
 */
public class TestHttpServer {

    public static void main(String[] args) throws Exception {
        HttpServer server = new HttpServer();
        server.start();
    }

}
