package application;
//
import java.util.Scanner;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

public class SavingsAccount extends BankAccounts{
	//
	String accountType;
	int savingsAccountNo;
	double savingsBalance;
	//
	public SavingsAccount(String userName, String userSname, String userPhone,String accountType, int savingsAccountNo, double savingsBalance) {
		//
		super(userName, userSname, userPhone);// super class is BankAccounts
		//Attributes below were added to cheque account class 
		this.accountType = accountType;
		this.savingsAccountNo = savingsAccountNo;
		this.setSavingsBalance(savingsBalance);
	}
	// Getters for user account type
	@Override
	public String getAccountType()
	{
		return accountType;
	}
	
	// getters for cheque account number
	public int getSavingsAccountNo()
	{
		return savingsAccountNo;
	}
	
	// getters for cheque account balance
	public double getSavingsBalance()
	{
		return savingsBalance;
	}
	//
	public String getUserPhone() {
		//
		return userPhone;
	}
	public void setSavingsBalance(double savingsBalance) {
		//
		this.savingsBalance = savingsBalance;
	}
	//
	
	
}
