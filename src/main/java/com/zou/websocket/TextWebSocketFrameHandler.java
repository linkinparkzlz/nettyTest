package com.zou.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;

public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {


        System.out.println("收到消息 : " + msg.text());

        ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器时间 ：" + LocalDateTime.now()));


    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

        System.out.println("handlerAdded  :" + ctx.channel().id().asLongText());
    }


    //连接关闭时被回调的方法
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {


        System.out.println("handlerRemoved  :" + ctx.channel().id().asLongText());
    }


    //发生异常时执行的回调方法

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        System.out.println("异常发生，关闭连接");
        ctx.close();

    }
}









































