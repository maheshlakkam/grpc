package com.example.grpcdemo.observer;

import com.example.grpc.proto.LoanPaymentRequest;
import com.example.grpc.proto.LoanPaymentResponse;

import io.grpc.stub.StreamObserver;

public class LoanPaymentResquestObserver implements StreamObserver<LoanPaymentRequest> {

	private StreamObserver<LoanPaymentResponse> loanPaymentResponseObserver;

	public LoanPaymentResquestObserver(StreamObserver<LoanPaymentResponse> loanPaymentResponseObserver) {
		this.loanPaymentResponseObserver = loanPaymentResponseObserver;
	}

	@Override
	public void onNext(LoanPaymentRequest loanPaymentRequest) {
		int account = loanPaymentRequest.getLoanAccount(); // use this call DB
		System.out.println("Received payment amount:" + loanPaymentRequest.getLoanAmount());
	}

	@Override
	public void onError(Throwable t) {
		System.out.println("Error...");

	}

	@Override
	public void onCompleted() {
		this.loanPaymentResponseObserver
				.onNext(LoanPaymentResponse.newBuilder().setMessage("Received Payment...").build());
		this.loanPaymentResponseObserver.onCompleted();
	}

}
