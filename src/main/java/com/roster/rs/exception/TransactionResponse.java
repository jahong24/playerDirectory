package com.roster.rs.exception;

import org.springframework.stereotype.Component;

@Component
public class TransactionResponse {
	
	private String transactionCode; 
	private String transactionMessage;
	
	public String getTransactionCode() {
		return transactionCode;
	}
	
	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}
	
	public String getTransactionMessage() {
		return transactionMessage;
	}
	
	public void setTransactionMessage(String transactionMessage) {
		this.transactionMessage = transactionMessage;
	}
	
}
