package com.zou.grpc;

import com.zou.proto.MyRequest;
import com.zou.proto.MyResponse;
import com.zou.proto.StudentServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {

    public static void main(String[] args) {

        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8890).
                usePlaintext(true).build();
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc.
                newBlockingStub(managedChannel);
        StudentServiceGrpc.StudentServiceStub stub = StudentServiceGrpc.newStub(managedChannel);

        MyResponse myResponse = blockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("zhangsan").build());

        System.out.println(myResponse.getRealname());


    }
}
