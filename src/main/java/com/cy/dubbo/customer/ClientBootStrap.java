package com.cy.dubbo.customer;

import com.cy.dubbo.netty.client.NettyClient;
import com.cy.dubbo.publicinterface.HellowService;

public class ClientBootStrap {

    //定义协议头
    public static final String providerName = "Hellow&Service&Hellow#";

    public static void main(String[] args) throws Exception {
        //创建一个消费者
        NettyClient customer = new NettyClient();

        //创建代理对象
        HellowService service = (HellowService) customer.getBean(HellowService.class, providerName);

        for(;;) {
            //通过代理对象调用服务提供者的方法（服务）
            String res = service.hellow("dobbo hi!").toString();
            System.out.println("service result = " + res);
            Thread.sleep(2 * 1000);
        }
    }
}
