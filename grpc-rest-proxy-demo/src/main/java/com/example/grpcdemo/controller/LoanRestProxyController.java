package com.example.grpcdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.grpcdemo.model.LoanPaymentHistory;
import com.example.grpcdemo.service.RestProxyLoanService;

@RestController
public class LoanRestProxyController {

	@Autowired
	RestProxyLoanService RestProxyLoanService;

	@GetMapping("/loans/{account}/")
	public Double getBalance(@PathVariable final String account) {
		return RestProxyLoanService.getLoanBalance(account);
	}

	@GetMapping("/loans/{account}/payment/history")
	public List<LoanPaymentHistory> getPaymentHistory(@PathVariable final String account) {
		return RestProxyLoanService.getLoanPaymentHistory(account);
	}
}
