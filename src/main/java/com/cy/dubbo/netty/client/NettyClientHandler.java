package com.cy.dubbo.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

public class NettyClientHandler extends ChannelInboundHandlerAdapter implements
    Callable {

    private ChannelHandlerContext context;
    private String result;//調用後返回的結果
    private String para;//傳入的參數

    //與服務器創建連接後，就會被調用
    //第一个被调用
    //1111111111111111111111
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive is called");
        context = ctx;//因為我在其他方法會使用到ctx
    }

    //收到服務器數據後，就會調用
    //notify——喚醒等待的線程
    //444444444444444444444444
    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("channelRead is called");
        result = msg.toString();
        notify();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    //被代理對象調用，發送數據給服務器-->wait-->等待被唤醒-->返回结果
    //333333333333333333333333333
    @Override
    public synchronized Object call() throws Exception {
        System.out.println("call is called");
        context.writeAndFlush(para);
        wait();//等待channelRead方法获取到服务器结果后唤醒
        System.out.println("call is called 2th");
        return result;//服务方返回的结果

    }

    //2222222222222222222222222222
    public void setPara(String para) {
        System.out.println("setPara is called");
        this.para = para;
    }
}
