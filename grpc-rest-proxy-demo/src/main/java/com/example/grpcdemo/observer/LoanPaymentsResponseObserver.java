package com.example.grpcdemo.observer;

import com.example.grpc.proto.LoanPaymentRequest;
import com.example.grpc.proto.LoanPaymentResponse;

import io.grpc.stub.StreamObserver;

public class LoanPaymentsResponseObserver implements StreamObserver<LoanPaymentResponse> {

	@Override
	public void onNext(LoanPaymentResponse reponse) {
		System.out.println(reponse.getMessage());
	}

	@Override
	public void onError(Throwable t) {
		System.out.println("Error...");
	}

	@Override
	public void onCompleted() {
		System.out.println("Completed...");
	}

}
