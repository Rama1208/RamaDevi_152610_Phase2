package com.cg.walletapp.service;

import java.math.BigDecimal;
import java.sql.SQLException;

import com.cg.walletapp.beans.Customer;
import com.cg.walletapp.exception.WalletException;

public interface WalletService {

	void checkName(String name) throws WalletException;

	void checkMobileNumber(String mobileNumber) throws WalletException;

	void checkPassword(String password) throws WalletException;

	void checkEmail(String emailId) throws WalletException;

	String addCustomer(Customer customer) throws WalletException;

	Customer showBalance(String mobileNum, String password) throws WalletException, SQLException;

	Customer check(String mobileNum, String password) throws WalletException, SQLException;

	void deposit(Customer customer, BigDecimal amount) throws ClassNotFoundException, SQLException, WalletException;

	boolean withDraw(Customer customer, BigDecimal amount) throws WalletException, ClassNotFoundException, SQLException;

	boolean isFound(String receiverMobile) throws WalletException, SQLException;

	Customer transfer(String senderMobile, String receiverMobile, BigDecimal amount) throws InterruptedException, WalletException, ClassNotFoundException, SQLException;

	String printTransactions(Customer customer) throws ClassNotFoundException, SQLException;
}
