package com.cy.dubbo.provider;

import com.cy.dubbo.netty.server.NettyServer;

//启动bootstrap会启动一个服务提供者，就是nettyServer
public class ServerBootStrap {
    public static void main(String[] args) {

        //代码代填....
        NettyServer.startServer("127.0.0.1", 7000);
    }
}
