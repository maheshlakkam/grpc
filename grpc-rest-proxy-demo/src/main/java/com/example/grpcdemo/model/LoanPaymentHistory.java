package com.example.grpcdemo.model;

public class LoanPaymentHistory {
	private double amout;

	public LoanPaymentHistory(double amout) {
		super();
		this.amout = amout;
	}

	public double getAmout() {
		return amout;
	}

	public void setAmout(double amout) {
		this.amout = amout;
	}

}
