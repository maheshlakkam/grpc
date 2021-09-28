package com.example.grpcdemo.service;

import com.example.grpc.proto.LoanBalanceRequest;
import com.example.grpc.proto.LoanBalanceResponse;
import com.example.grpc.proto.LoanPaymentHistoryReponse;
import com.example.grpc.proto.LoanPaymentHistoryRequest;
import com.example.grpc.proto.LoanPaymentRequest;
import com.example.grpc.proto.LoanPaymentResponse;
import com.example.grpc.proto.LoanServiceGrpc.LoanServiceImplBase;
import com.example.grpcdemo.observer.LoanPaymentResquestObserver;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class LoanService extends LoanServiceImplBase {

	/**
	 * Unary Service
	 */
	@Override
	public void getLoanBalance(LoanBalanceRequest request, StreamObserver<LoanBalanceResponse> responseObserver) {
		int accountNumber = request.getLoanAccountNumber(); // use this accountNumber and call DB layer to get balance..
		LoanBalanceResponse balance = LoanBalanceResponse.newBuilder().setLoanBalanceAmount(123.33).build();
		responseObserver.onNext(balance);
		responseObserver.onCompleted();
	}

	/**
	 * Server Streaming
	 */
	@Override
	public void getLoanPaymentHistory(LoanPaymentHistoryRequest request,
			StreamObserver<LoanPaymentHistoryReponse> reponseObserver) {
		int loanAccountNumber = request.getLoanAccountNumer(); // use this accountNumber and call DB layer..
		double amount = 0;
		for (int i = 0; i < 10; i++) {
			amount = amount + 100;
			reponseObserver.onNext(LoanPaymentHistoryReponse.newBuilder().setLoanAmount(amount).build());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		reponseObserver.onCompleted();
	}

	@Override
	public StreamObserver<LoanPaymentRequest> payments(StreamObserver<LoanPaymentResponse> responseObserver) {
		return new LoanPaymentResquestObserver(responseObserver);
	}

}
