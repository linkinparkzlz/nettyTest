package com.zou.protobufAndNetty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Person msg) throws Exception {


    }

    //客户端向服务器端数据的传递,此时传递的就是对象。


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        //首先，我们需要将对象构建出来
        MyDataInfo.Person  person = MyDataInfo.Person.newBuilder().setName("李四").setAge(33).setAddress("上海").build();

        //向服务器端发送对象（消息）
        ctx.channel().writeAndFlush(person);


    }
}
