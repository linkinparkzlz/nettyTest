package com.zou.netty3;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {


    private ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE); //获取


    //服务器端收到任何一个客户端发过来的消息，就会得到调用
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        Channel channel = ctx.channel();

        channelGroup.forEach(ch -> {
            if (channel != channel) { //不是发消息的客户端
                ch.writeAndFlush(channel.remoteAddress() + "发送的消息 ：" + msg + "\n");
            } else {
                ch.writeAndFlush("自己 ：" + msg + "\n");
            }

        });


    }


    //表示服务器端与客户端已经建立好连接
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel(); //获取连接

        channelGroup.writeAndFlush("服务器 : - " + channel.remoteAddress() + " 加入\n"); //将消息写入到ChannelGroup的所有Channel中

        channelGroup.add(channel);  //将每个连接(channel)都放进group中


    }


    //连接断掉之后的回调处理
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        channelGroup.writeAndFlush("服务器 - " + channel.remoteAddress() + " 断开连接");


    }

    //连接处于活动状态.当一个连接(channel)建立的时候，执行的回调
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + " -上线了");


    }


    //连接断开后，执行的回调
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + " - 下线");


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.close();

    }
}


















































