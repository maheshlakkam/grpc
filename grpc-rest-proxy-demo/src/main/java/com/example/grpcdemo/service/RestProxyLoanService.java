package com.example.grpcdemo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.stereotype.Service;

import com.example.grpc.proto.LoanBalanceRequest;
import com.example.grpc.proto.LoanBalanceResponse;
import com.example.grpc.proto.LoanPaymentHistoryReponse;
import com.example.grpc.proto.LoanPaymentHistoryRequest;
import com.example.grpc.proto.LoanServiceGrpc;
import com.example.grpcdemo.model.LoanPaymentHistory;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Service
public class RestProxyLoanService {

	/**
	 * Unary server with CLINET - Blocking Stub
	 * 
	 */
	public Double getLoanBalance(String account) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8888).usePlaintext().build();
		LoanServiceGrpc.LoanServiceBlockingStub stub = LoanServiceGrpc.newBlockingStub(channel);
		LoanBalanceResponse response = stub.getLoanBalance(
				LoanBalanceRequest.newBuilder().setLoanAccountNumber(Integer.parseInt(account)).build());
		channel.shutdown();
		return response.getLoanBalanceAmount();
	}

	/**
	 * SEVER - Streaming with CLINET - Blocking Stub
	 * 
	 */
	public List<LoanPaymentHistory> getLoanPaymentHistory(String account) {
		List<LoanPaymentHistory> loanPaymentHistoryList = new ArrayList<>();
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8888).usePlaintext().build();
		LoanServiceGrpc.LoanServiceBlockingStub stub = LoanServiceGrpc.newBlockingStub(channel);
		Iterator<LoanPaymentHistoryReponse> response = stub.getLoanPaymentHistory(
				LoanPaymentHistoryRequest.newBuilder().setLoanAccountNumer(Integer.parseInt(account)).build());
		channel.shutdown();
		response.forEachRemaining(consumeLoanPaymentHistory(loanPaymentHistoryList));
		return loanPaymentHistoryList;
	}

	private Consumer<LoanPaymentHistoryReponse> consumeLoanPaymentHistory(
			List<LoanPaymentHistory> loanPaymentHistoryList) {
		return e -> {
			LoanPaymentHistory loanPaymentHistory = new LoanPaymentHistory(e.getLoanAmount());
			loanPaymentHistoryList.add(loanPaymentHistory);
		};
	}

}
