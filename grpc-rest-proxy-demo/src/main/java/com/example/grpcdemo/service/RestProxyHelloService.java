package com.example.grpcdemo.service;

import org.springframework.stereotype.Service;

import com.example.grpc.proto.HelloRequest;
import com.example.grpc.proto.HelloResponse;
import com.example.grpc.proto.HelloServiceGrpc;
import com.example.grpc.proto.LoanBalanceRequest;
import com.example.grpc.proto.LoanBalanceResponse;
import com.example.grpc.proto.LoanServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Service
public class RestProxyHelloService {

	public String sayHello(String firstName, String lastName) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8888).usePlaintext().build();
		HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);
		HelloResponse response = stub
				.hello(HelloRequest.newBuilder().setFirstName(firstName).setLastName(lastName).build());
		channel.shutdown();
		return response.getGreeting();
	}


}
