package com.zou.netty1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;


/*
自己提供的处理器

 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    //这个方法作用是读取客户端发送过来的请求并返回响应
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {


        if (msg instanceof HttpRequest) {


            ByteBuf content = Unpooled.copiedBuffer("Hello  world", CharsetUtil.UTF_8); //构造响应的内容
            //响应  协议版本号，响应码，内容
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);

            //设置一些头信息
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());


            //返回给客户端
            ctx.writeAndFlush(response);


        }

    }


    //channel变为活动的时候，调用的方法
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel  active");
        super.channelActive(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel registered");
        super.channelRegistered(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("hander  added");
        super.handlerAdded(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel inactive");
        super.channelInactive(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {

        System.out.println("channel unregisted");
        super.channelUnregistered(ctx);
    }
}













































































