package com.example.grpcdemo.client;

import com.example.grpc.proto.LoanPaymentHistoryRequest;
import com.example.grpc.proto.LoanPaymentRequest;
import com.example.grpc.proto.LoanServiceGrpc;
import com.example.grpcdemo.observer.LoanPaymentHistoryReponseObserver;
import com.example.grpcdemo.observer.LoanPaymentsResponseObserver;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class GrpcClient {

	/**
	 * SEVER - Streaming with CLINET - Non Blocking Stub
	 * 
	 */
	public void getLoanPaymentHistoryAsync(String account) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8888).usePlaintext().build();
		LoanServiceGrpc.LoanServiceStub stub = LoanServiceGrpc.newStub(channel);
		stub.getLoanPaymentHistory(
				LoanPaymentHistoryRequest.newBuilder().setLoanAccountNumer(Integer.parseInt(account)).build(),
				new LoanPaymentHistoryReponseObserver());
		channel.shutdown();
	}

	/**
	 * SEVER - Unary with CLINET - Streaming
	 * 
	 */
	public void loanPayments() {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8888).usePlaintext().build();
		LoanServiceGrpc.LoanServiceStub stub = LoanServiceGrpc.newStub(channel);
		StreamObserver<LoanPaymentRequest> stream = stub.payments(new LoanPaymentsResponseObserver());
		for (int i = 0; i < 10; i++) {
			stream.onNext(LoanPaymentRequest.newBuilder().setLoanAccount(1).setLoanAmount(1000).build());
		}
		stream.onCompleted();
	}
}
