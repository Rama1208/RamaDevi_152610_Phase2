package com.cg.walletapp.pl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Scanner;

import com.cg.walletapp.beans.Customer;
import com.cg.walletapp.exception.WalletException;
import com.cg.walletapp.service.WalletService;
import com.cg.walletapp.service.WalletServiceImpl;

public class Client {
	static WalletService walletService = new WalletServiceImpl();
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		int choice;
		try {
			do {
				selectChoice();
				choice = scanner.nextInt();
				switch (choice) {
				case 1:
					createAccount();

					break;
				case 2:
					showBalance();

					break;
				case 3:
					depositAmonut();

					break;
				case 4:
					withdrawAmount();

					break;
				case 5:
					transferFund();

					break;
				case 6:
					printDetails();
					break;
				case 7:
					scanner.close();
					System.exit(0);
					break;
				default:
					System.out.println("Enter your choice correctly");
					break;

				}
			} while (choice != 7);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
	}

	private static void selectChoice() {
		System.out.println(
				"1) Create Account \n 2) Show Balance \n 3) Deposit\n 4) Withdraw \n 5)Fund Transfer\n 6) Print Transactions\n 7) exit");
		System.out.println("Enter Your Choice");

	}

	private static void printDetails() throws SQLException, ClassNotFoundException {
		try {
			System.out.println("Enter your mobile number");
			String mobileNum = scanner.next();
			walletService.checkMobileNumber(mobileNum);
			System.out.println("Enter the password");
			String password = scanner.next();
			walletService.checkPassword(password);
			Customer customer = walletService.check(mobileNum, password);
			if (customer != null) {
				String mytransactions = walletService.printTransactions(customer);
				@SuppressWarnings("resource")
				Scanner sc = new Scanner(mytransactions).useDelimiter(",");
				System.out.println("   Date       Time        Cr/Db    Amount");
				while (sc.hasNext()) {
					System.out.println(sc.next());
					System.out.println("-----------------------------------------");
				}
			}

		} catch (WalletException walletException) {
			System.out.println(walletException.getMessage());
		}

	}

	private static void transferFund() throws SQLException, ClassNotFoundException {
		try {
			System.out.println("Enter Receivers mobile number ");
			String receiverMobile = scanner.next();
			walletService.checkMobileNumber(receiverMobile);
			boolean result = walletService.isFound(receiverMobile);
			if (result) {
				System.out.println("Enter sender mobile number");
				String senderMobile = scanner.next();
				walletService.checkMobileNumber(senderMobile);
				System.out.println("Enter senders password");
				String password = scanner.next();
				walletService.checkPassword(password);
				Customer customer = walletService.check(senderMobile, password);
				if (customer != null) {
					System.out.println("Enter the amount to transfer");
					BigDecimal amount = scanner.nextBigDecimal();
					Customer output = walletService.transfer(senderMobile, receiverMobile, amount);
					if (output != null) {
						System.out.println("Amount is succesfully transferred to " + receiverMobile
								+ " and current balance is " + output.getWallet().getBalance());
					}

				}
			}

		} catch (WalletException walletException) {
			System.out.println(walletException.getMessage());
		} catch (InterruptedException interruptedException) {
			System.out.println(interruptedException.getMessage());
		}
	}

	private static void withdrawAmount() throws SQLException, ClassNotFoundException {
		try {
			System.out.println("Enter your mobile number");
			String mobileNum = scanner.next();
			walletService.checkMobileNumber(mobileNum);
			System.out.println("Enter the password");
			String password = scanner.next();
			walletService.checkPassword(password);
			Customer customer = walletService.check(mobileNum, password);
			if (customer != null) {
				System.out.println("Enter the amount to withdraw");
				BigDecimal amount = scanner.nextBigDecimal();
				boolean result = walletService.withDraw(customer, amount);
				if (result == true) {
					System.out.println("Amount is succesfully withdrawn and current balance is "
							+ customer.getWallet().getBalance());
				}
			}
		} catch (WalletException walletException) {
			System.out.println(walletException.getMessage());
		}

	}

	private static void depositAmonut() throws SQLException, ClassNotFoundException {
		try {
			System.out.println("Enter your mobile number");
			String mobileNum = scanner.next();
			walletService.checkMobileNumber(mobileNum);
			System.out.println("Enter the password");
			String password = scanner.next();
			walletService.checkPassword(password);
			Customer customer = walletService.check(mobileNum, password);
			if (customer != null) {
				System.out.println("Enter amount to deposit ");
				BigDecimal amount = scanner.nextBigDecimal();
				walletService.deposit(customer, amount);
				System.out.println("Deposited");
			}
		} catch (WalletException walletException) {

			System.out.println(walletException.getMessage());
		}

	}

	private static void showBalance() throws SQLException {
		try {
			System.out.println("Enter your mobile number");
			String mobileNum = scanner.next();
			walletService.checkMobileNumber(mobileNum);
			System.out.println("Enter the password");
			String password = scanner.next();
			walletService.checkPassword(password);
			Customer customer = walletService.showBalance(mobileNum, password);
			if (customer != null) {
				System.out.println("Available balance is " + customer.getWallet().getBalance());
			}
		} catch (WalletException walletException) {

			System.out.println(walletException.getMessage());
		}

	}

	public static void createAccount() {
		try {
			Customer customer = new Customer();
			System.out.println("Enter Customer  Name");
			String name = scanner.next();
			walletService.checkName(name);
			customer.setName(name);
			System.out.println("Enter Mobile Number");
			String mobileNumber = scanner.next();
			walletService.checkMobileNumber(mobileNumber);
			customer.setMobileNumber(mobileNumber);
			System.out.println("Enter Password");
			String password = scanner.next();
			walletService.checkPassword(password);
			customer.setPassword(password);
			System.out.println("Enter Email Id");
			String emailId = scanner.next();
			walletService.checkEmail(emailId);
			customer.setEmailId(emailId);
			String result = walletService.addCustomer(customer);
			if (result != null)
				System.out.println("Account created with Account Number " + result);
		} catch (WalletException walletException) {
			System.out.println(walletException.getMessage());
		}

	}
}
