/**
 * Account object for use in transactionMachine.
 * 
 * Matt Warman
 * 1.1
 */

import java.text.DecimalFormat;

public class Account {
	
	private int accountID; // hold the account identification number
	private double balance; // hold the account balance
	private double annualInterestRate; // hold the account's annual interest rate
	
	Account() // Create default account
	{
		accountID = 0;
		balance = 0.0;
	}
	
	Account(int setAccountID, double setBalance) // Create account with custom ID and balance
	{
		accountID = setAccountID;
		balance = setBalance;
	}
	
	void changeAccountID(int newAccountID) // change the account identification number
	{
		accountID = newAccountID;
	}
	
	int getAccountID() // access and return the account's identification number
	{
		return accountID;
	}
	
	void changeAccountBalance(double newBalance) // change the account's current balance
	{
		balance = newBalance;
	}
	
	double getAccountBalance() // access and return the account's current balance
	{
		return balance;
	}
	
	void changeAccountYearlyInterest(double newYearlyInterest) // change the account's annual interest rate
	{
		annualInterestRate = newYearlyInterest;
	}
	
	double getAccountYearlyInterest() // access and return the account's annual interest rate (as a decimal)
	{
		return annualInterestRate/100; // convert the annual interest rate to a decimal before returning it
	}
	
	double getMonthlyInterest() // calculate the amount of interest accrued per month based on the account balance
	{
		/*
		 * Divide the account's annual interest rate by 12 to convert it to the monthly interest rate.
		 * Then, divide it by 100 to convert it to a decimal for easy calculation
		 */
		return ((annualInterestRate / 1200) * balance); // return the calculated interest
	}
	
	void withdraw(double withdrawAmount) // withdraw a specified amount from the account's balance
	{
		balance = (balance - withdrawAmount); // change the balance to its self minus the withdrawal amount
	}
	
	void deposit(double depositAmount) // deposit a specified amount into the account's balance
	{
		balance = (balance + depositAmount); // change the balance to its self plus the deposit amount
	}
	
	public String toString() // return a string containing account information (accountID, balance, and annualInterestRate)
	{
		return ("\nThe account ID is: " + accountID + "\nThe balance is: " + new DecimalFormat("$###,###,###,##0.00").format(balance) + "\nThe annual interest rate is: " + new DecimalFormat("0.0#").format(annualInterestRate));
	}
}
