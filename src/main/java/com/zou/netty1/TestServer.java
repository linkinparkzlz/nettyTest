package com.zou.netty1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TestServer {


    public static void main(String[] args) throws Exception {


        EventLoopGroup bossGroup = new NioEventLoopGroup(); //从客户端不断的接受连接，但是不对连接做任何的处理
        EventLoopGroup workerGroup = new NioEventLoopGroup(); //真正的完成对连接的处理,将结果返回给客户端


        try {


            ServerBootstrap serverBootstrap = new ServerBootstrap(); //用于启动服务端的一个类
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new TestServerInitializer());
            //childHandler()中就是我们自己编写的处理器，请求可以真正的由这个处理器处理


            //绑定端口
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();//关闭的监听


        } finally {

            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();


        }


    }


}

































