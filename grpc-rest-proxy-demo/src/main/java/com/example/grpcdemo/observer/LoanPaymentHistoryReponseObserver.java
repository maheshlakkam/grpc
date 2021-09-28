package com.example.grpcdemo.observer;

import com.example.grpc.proto.LoanPaymentHistoryReponse;

import io.grpc.stub.StreamObserver;

public class LoanPaymentHistoryReponseObserver implements StreamObserver<LoanPaymentHistoryReponse> {

	@Override
	public void onNext(LoanPaymentHistoryReponse response) {
		System.out.println(response.getLoanAmount());
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
