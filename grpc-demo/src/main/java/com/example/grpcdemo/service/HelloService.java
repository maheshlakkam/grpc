package com.example.grpcdemo.service;

import com.example.grpc.proto.HelloRequest;
import com.example.grpc.proto.HelloResponse;
import com.example.grpc.proto.HelloServiceGrpc.HelloServiceImplBase;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class HelloService extends HelloServiceImplBase {

	@Override
	public void hello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
		HelloResponse helloResponse = HelloResponse.newBuilder()
				.setGreeting("Hello " + request.getFirstName() + " " + request.getLastName()).build();
		responseObserver.onNext(helloResponse);
		responseObserver.onCompleted();
	}

}
