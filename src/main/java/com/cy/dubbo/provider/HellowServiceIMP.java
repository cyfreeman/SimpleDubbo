package com.cy.dubbo.provider;

import com.cy.dubbo.publicinterface.HellowService;

public class HellowServiceIMP implements HellowService {

    private static int count = 0;
    //当有消费方调用该方法时，就返回一个结果字符串
    @Override
    public String hellow(String msg) {
        System.out.println("receive msg from client : " + msg);
        if(msg != null) {
            return "hi client, i have receive your msg[" + msg + "] 第" + (++count) + "";
        }else {
            return "hi client, i have receive your msg";

        }
    }
}
