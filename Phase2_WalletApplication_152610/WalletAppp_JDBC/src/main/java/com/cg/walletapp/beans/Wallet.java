package com.cg.walletapp.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

public class Wallet implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private BigDecimal balance;
	
	public Wallet() {
		this.balance=BigDecimal.valueOf(0.0);
	}

	
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

}
