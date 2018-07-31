package com.cg.walletapplication.WalletAppp_JDBC;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.sql.SQLException;
import org.junit.Test;
import com.cg.walletapp.beans.Customer;
import com.cg.walletapp.beans.Wallet;
import com.cg.walletapp.exception.WalletException;
import com.cg.walletapp.service.WalletService;
import com.cg.walletapp.service.WalletServiceImpl;

public class WalletServiceImplTest {
	public static WalletService walletService=new WalletServiceImpl();
	
    @Test
	public void addCustomerTestTrue() throws WalletException
	{
		Customer customer1 = new Customer("9848403838","Rama","Rama@123","rama@gmail.com",new Wallet());
		assertEquals("9848403838",walletService.addCustomer(customer1));
			
	}
    @Test
  	public void addCustomerTestFalse() throws WalletException
  	{

  		Customer customer2 = new Customer("9848403834","Rani","Rani@123","rani@gmail.com",new Wallet());
  		assertNotEquals("56968621",walletService.addCustomer(customer2));
  		
  	}
	

	@Test
	public void initBalanceTest() throws WalletException
	{
		Customer customer1 = new Customer("9848403835","Rani","Rani@123","rani@gmail.com",new Wallet());
		walletService.addCustomer(customer1);
		assertEquals(BigDecimal.valueOf(0.0),customer1.getWallet().getBalance());
		
	}
	
	
	@Test
	public void withdrawMoneyTestTrue() throws WalletException, ClassNotFoundException, SQLException
	{
		Customer customer2 = new Customer("9848403895","Rani","Rani@123","rani@gmail.com",new Wallet());
		walletService.addCustomer(customer2);
		walletService.deposit(customer2, BigDecimal.valueOf(8500.00));
		assertTrue(walletService.withDraw(customer2, BigDecimal.valueOf(3000.00)));
	}

	
	@Test(expected = WalletException.class)
	public void withdrawMoneyTestFalse() throws WalletException, ClassNotFoundException, SQLException
	{
		Customer customer2 = new Customer("9848403885","Rani","Rani@123","rani@gmail.com",new Wallet());
		walletService.addCustomer(customer2);
		walletService.deposit(customer2, BigDecimal.valueOf(8500.00));
		assertFalse(walletService.withDraw(customer2, BigDecimal.valueOf(9000.00)));
	}

}
