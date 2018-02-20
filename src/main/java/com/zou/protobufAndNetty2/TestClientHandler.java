package com.zou.protobufAndNetty2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {


    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        int randomInt = new Random().nextInt(3);
        MyDataInfo.MyMessage message = null;

        if (0 == randomInt) {

            message = MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DataType.PeopleType)
                    .setPeople(MyDataInfo.People.newBuilder().setName("王五").setAge(33).setAddress("杭州").build()).build();

        } else if (1 == randomInt) {
            message = MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DataType.DogType)
                    .setDog(MyDataInfo.Dog.newBuilder().setName("王六").setAge(66).build()).build();

        } else {
            message = MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DataType.CatType)
                    .setCat(MyDataInfo.Cat.newBuilder().setName("王六").setCity("城市").build()).build();
        }

        ctx.writeAndFlush(message);

    }
}
