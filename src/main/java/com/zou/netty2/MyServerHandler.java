package com.zou.netty2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

public class MyServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        System.out.println(ctx.channel().remoteAddress() + "," + msg); //得到客户端的远程地址和发送过来的消息


        ctx.channel().writeAndFlush("from   server :" + UUID.randomUUID());  //服务端返回给客户端的消息

    }


    //出现异常时调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel(); //关闭连接
    }
}

















































