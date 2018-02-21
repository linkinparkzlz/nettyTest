package com.zou.grpc;

import com.zou.proto.MyRequest;
import com.zou.proto.MyResponse;
import com.zou.proto.StudentServiceGrpc;
import io.grpc.stub.StreamObserver;

public class StudentServiceImpl   extends StudentServiceGrpc.StudentServiceImplBase{

    @Override
    public void getRealNameByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {


        System.out.println("接收到客户端信息 ：" + request.getUsername());

        //服务端构造好对象，返回给客户端
        responseObserver.onNext(MyResponse.newBuilder().setRealname("张三").build());

        responseObserver.onCompleted();//标识方法调用结束

    }
}
