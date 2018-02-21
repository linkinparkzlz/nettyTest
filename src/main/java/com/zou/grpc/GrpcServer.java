package com.zou.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GrpcServer {

    private Server server;

    private void start() throws Exception {
        this.server = ServerBuilder.forPort(8890).addService(new StudentServiceImpl()).build().start();

        System.out.println("服务器启动");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("关闭jvm");
            GrpcServer.this.stop();
        }));

        System.out.println("执行到这里");
    }

    //关闭
    private void stop() {
        if (null != this.server) {
            this.server.shutdown();
        }
    }

    private void awaitTermination() throws Exception {
        if (null != this.server) {
            this.server.awaitTermination();
        }
    }


    public static void main(String[] args) throws Exception {

        GrpcServer server = new GrpcServer();

        server.start();
        server.awaitTermination();
    }

}
