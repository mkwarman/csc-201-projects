/**
 * Transaction machine emulator.
 * 
 * Matt Warman 
 * 1.1
 */

import java.util.Scanner;

public class transactionMachine {
	
	private static Scanner input; // This initializes a reusable scanner input variable
	
	public static void main(String args[]) // main method
	{
		boolean correctData = true; // holds a value that represents whether the user entered a valid selection or not
		boolean continueProgram = true; // holds a value that represents whether the user elected to continue the program or not
		
		Account account1 = new Account(getIntInput("Please input account number: "), getDoubleInput("Please input starting balance: ")); // Initialize account and fill it with user input account ID and starting balance
		account1.changeAccountYearlyInterest(getDoubleInput("Please input account interest: ")); // Change annual account interest to a user input amount
		System.out.print(account1.toString() + "\n"); // print out account information
		
		do
		{
			correctData = true; // this should only be changed when the user inputs an invalid selection
			switch(getIntInput("Do you have a transaction?\nType 1 for yes; or 0 for No: ")) // take in and examine user input for whether they want to conduct a transaction or not
			{
			case 0: // for "no"
				continueProgram = false; // do not continue the program
				break;
			case 1: // for "yes"
				runTransaction(account1); // run a deposit or withdrawal transaction
				break;
			default: // for invalid selections
				System.out.print("\nYou have entered an invalid selection. Please try again\n");
				correctData = false; // incorrect data was entered
				break;
			}	
			
			// the line below prints current account information if the user has elected to continue the program
			if (continueProgram == true) {System.out.print(account1.toString() + "\n");} // print out account information if the user elects to continue
		
		} while (correctData == false || continueProgram == true); // repeat the loop if the user input incorrect data or wanted to restart the program
		
		System.out.print("\n\nThank you for using transactionMachine");
	}
	
	public static void runTransaction(Account account) // initiates withdrawals and deposits for the current account
	{
		boolean correctData = true; // holds a value that represents whether the user entered a valid selection or not
		double amount = 0; // this will hold the amount of money to be deposited or withdrawn from the current account
		do
		{
			switch(getIntInput("What type of transaction?\nType 0 for withdrawal and 1 for deposit: ")) // take in and examine user input for whether they want to deposit or withdraw
			{
			case 0: // for withdraw
				correctData = true;
				amount = getDoubleInput("How much would you like to withdraw: "); // get user input double for amount to withdraw
				calculateInterest(account); // pass the account to the calculateInterest method so that it can pre-calculate how much should currently be in the account
				withdraw(account, amount); // pass the account and the amount onto the withdraw method to make sure that the account has enough for the transaction
				break;
			case 1: // for deposit
				correctData = true;
				amount = getDoubleInput("How much would you like to deposit: "); // get user input double for amount to deposit
				calculateInterest(account); // pass the account to the calculateInterest method so that it can pre-calculate how much should currently be in the account
				account.deposit(amount); // deposit the input amount into the account
				break;
			default: // for invalid selections
				System.out.print("\nYou have entered an invalid selection. Please try again\n");
				correctData = false; // incorrect data was entered
				break;	
			}
		} while (correctData == false); // repeat the loop if the user input incorrect data
	}
	
	public static void calculateInterest(Account account) 
	{
		/*
		 * Calculates interest for the current account based on the annual interest rate, current balance, and the amount of days that have passed.
		 * Then, add that interest to the current balance.
		 */
		
		// user inputs number of days since the last transaction
		int daysPassed = getIntInput("How many days has it been since the last transaction: ");
		
		// calculate the amount of interest accumulated per day and multiply that by the number of days passed
		double calculatedInterest = (daysPassed * (account.getMonthlyInterest() / 30));
		
		// change the account balance to what it was plus the calculated interest
		account.changeAccountBalance(account.getAccountBalance() + calculatedInterest);
	}
	
	public static void withdraw(Account account, double amount)
	{
		if (amount > account.getAccountBalance()) // if there is NOT enough money in the account for the withdrawal
		{
			System.out.print("\n\nYou can't withdraw that amount. Transaction cancelled.\n\n");
		}
		else // if there IS enough money for the withdrawal
		{
			account.withdraw(amount); // withdraw the input amount from the current account
		}
	}
	
    public static double getDoubleInput(String prompt) // facilitates an easy and reusable way to take in sanitized user input double values.
    {
        double value = 0; // initialize the placeholder value
        boolean correctData = true; // this aids in looping in the event that an incorrect data type is entered
        do // since it needs to execute at least once
        {
            System.out.print("\n" + prompt); // ask the user to input whatever it was that we wanted them to input. See above.
            input = new Scanner(System.in); // initialize the placeholder input scanner
            if (input.hasNextDouble()) // was the input data actually a double?
            {
                value = input.nextDouble(); // if so, store it in the placeholder variable as such
                if (value >= 0) // if the input value is zero or positive
                {
                	correctData = true; // the correct data type was entered
                }
                else // if the input value is negative
                {
                	System.out.print("\nYou cannot input a negitive value.\nPlease try again.");
                	correctData = false; // incorrect data was input
                }
            }
            else
            {
                System.out.print("\nYou have input an invalid value. Please try again.\n");
                correctData = false; // the wrong data type was entered
            }
        } while (correctData == false); // if the wrong data type was entered, give the user another chance to enter correct data
        return value; // return the value stored in the placeholder variable
    }
    
    public static int getIntInput(String prompt) // facilitates an easy and reusable way to take in sanitized user input integer values.
    {
        int value = 0; // initialize the placeholder value
        boolean correctData = true; // this aids in looping in the event that an incorrect data type is entered
        do // since it needs to execute at least once
        {
            System.out.print("\n" + prompt); // ask the user to input whatever it was that we wanted them to input. See above.
            input = new Scanner(System.in); // initialize the placeholder input scanner
            if (input.hasNextInt()) // was the input data actually an int?
            {
                value = input.nextInt(); // if so, store it in the placeholder variable as such
                if (value >= 0) // if the input value is zero or positive
                {
                    correctData = true; // the correct data type was entered
                }
                else // if the input value is negative
                {
                	System.out.print("\nYou cannot input a negitive value.\nPlease try again.\n");
                	correctData = false; // incorrect data was input
                }
            }
            else
            {
                System.out.print("\nYou have input an invalid value. Please try again.\n");
                correctData = false; // the wrong data type was entered
            }
        } while (correctData == false); // if the wrong data type was entered, give the user another chance to enter correct data
        return value; // return the value stored in the placeholder variable
    }
}
