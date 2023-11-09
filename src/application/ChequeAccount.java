package application;
//
import java.util.Scanner;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
/*
 * 
 * */
public class ChequeAccount extends BankAccounts{
	//
	String accountType;
	int chequeAccountNo;
	double chequeBalance;
	//
	public ChequeAccount(String userName, String userSname, String userPhone,String accountType, int chequeAccountNo, double chequeBalance) {
		//
		super(userName, userSname, userPhone);// super class is BankAccounts
		//Attributes below were added to cheque account class 
		this.accountType = "Cheque Account";
		this.chequeAccountNo = chequeAccountNo;
		this.setChequeBalance(chequeBalance);
	}
	// Getters for user account type
	@Override
	public String getAccountType()
	{
		return accountType;
	}
	
	// getters for cheque account number
	public int getChequeAccountNo()
	{
		return chequeAccountNo;
	}
	
	// getters for cheque account balance
	public double getChequeBalance()
	{
		return chequeBalance;
	}
	//
	public String getUserPhone() {
		//
		return userPhone;
	}
	
	public String getUserName() {
		//
		return userName;
	}
	//
	public void setChequeBalance(double chequeBalance) {
		//
		this.chequeBalance = chequeBalance;
	}
	//
	
	
}
