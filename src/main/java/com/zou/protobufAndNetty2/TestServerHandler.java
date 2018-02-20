package com.zou.protobufAndNetty2;

import com.zou.protobufAndNetty2.MyDataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TestServerHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {

        //根据客户端传进来的不同的类型进行不同的处理

        MyDataInfo.MyMessage.DataType dataType = msg.getDataType();

        if (dataType == MyDataInfo.MyMessage.DataType.PeopleType) {

            MyDataInfo.People people = msg.getPeople();
            System.out.println(people.getName());
            System.out.println(people.getAge());
            System.out.println(people.getAddress());

        } else if (dataType == MyDataInfo.MyMessage.DataType.DogType) {

            MyDataInfo.Dog dog = msg.getDog();
            System.out.println(dog.getAge());
            System.out.println(dog.getName());

        } else {

            MyDataInfo.Cat cat = msg.getCat();
            System.out.println(cat.getCity());
            System.out.println(cat.getName());

        }


    }
}
