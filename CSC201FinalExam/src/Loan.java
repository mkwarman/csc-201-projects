import java.text.DecimalFormat;

/**
 * Loan class for the Loan Management Program.
 * 
 * Matt Warman 
 * 1.5
 */

public class Loan {
	
	private String name; // hold the name of the loan
	private double amount; // hold the amount of the loan (balance)
	private double term; // hold the year term of the loan
	private double annualInterestRate; // hold the annual interest rate of the loan (as a decimal)
	private double monthlyPayment; // hold the monthly payment amount between calculations
	
	Loan() // default no-arg constructor
	{
		name = "Example Loan";
		amount = 1000.0;
		term = 1.0;
		annualInterestRate = 0.1;
		calculateMonthlyPayment();
	}
	
	Loan(String setName, double setAmount, double setTerm, double setInterest) // create new loan with specified name, amount, term, and interest
	{
		name = setName;
		amount = setAmount;
		term = setTerm;
		annualInterestRate = (setInterest/100.0); // set annual interest rate as a decimal
		calculateMonthlyPayment(); // after setting all values for a new account, calculate the monthly payment associated with it
	}
	
	String getName() // return name of loan
	{
		return name;
	}
	
	void setName(String setName) // set name of loan
	{
		name = setName;
	}
	
	double getAmount() // return amount of loan (balance)
	{
		return amount;
	}
	
	void setAmount(double setAmount)// set amount of loan (balance)
	{
		amount = setAmount;
	}
	
	double getTerm() // return term of loan
	{
		return term;
	}
	
	void setTerm(double setTerm)// set term of loan
	{
		term = setTerm;
	}
	
	double getInterest() // return annual percent interest of loan as percentage
	{
		try
		{
			return (annualInterestRate * 100.0);
		}
		catch (Exception e) // in the event of an error
		{
			System.out.print("The system encountered an error while trying to retrieve the annual interest rate.");
			return 0;
		}
	}
	
	void setInterest(double setInterest) // set annual percent interest of loan to input percentage
	{
		try
		{
			annualInterestRate = (setInterest / 100.0); // save percentage as a decimal
		}
		catch (Exception e) // in the event of an error
		{
			System.out.print("The system encountered an error while trying to set the annual interest rate.");
		}
	}
	
	void makePayment(double paymentAmount) // make a payment
	{
		try
		{
			amount = (amount + monthlyInterest()); // add the interest to the remaining amount
			amount = (amount - paymentAmount); // the new balance is the old balance minus the amount of the payment
			term = (term - (1.0 / 12.0)); // decrease the remaining payments by 1 (one year = twelve months, so the equation is: term(in years) - 1/12(one month)
		}
		catch (Exception e) // in the event of an error
		{
			System.out.print("The system encountered an error while processing your payment.");
		}
	}
	
	double monthlyInterestRate() // return the interest rate per month
	{
		try
		{
			return (annualInterestRate / 12.0); // the monthly interest rate is annual interest rate divided by 12
		}
		catch (Exception e) // in the event of an error
		{
			System.out.print("The system encountered an error while calculating the monthly interest rate.");
			return 0;
		}
	}
	
	double monthlyInterest() // return the monthly interest calculated from the remaining balance and the monthly interest rate
	{
		try
		{
			return (amount * monthlyInterestRate()); // monthly interest is the remaining balance times the monthly interest rate
		}
		catch (Exception e) // in the event of an error
		{
			System.out.print("The system encountered an error while calculating the monthly interest.");
			return 0;
		}
	}
	
	double remainingPayments() // return the number of remaining payments
	{
		try
		{
			return (term * 12.0); // the number of remaining payments is the term times 12 since there are 12 months in a year
		}
		catch (Exception e) // in the event of an error
		{
			System.out.print("The system encountered an error while calculating the remaining payments.");
			return 0;
		}
	}
	
	void calculateMonthlyPayment() // calculate the monthly payment based on the amount, monthly interest rate, and remaining payments at the time of invocation
	{
		if (amount == 0) // if the amount has been zero'd out
		{
			monthlyPayment = 0;
		}
		else
		{
			try
			{
				monthlyPayment = ((monthlyInterestRate() * amount)/(1.0 - (Math.pow((1.0 + monthlyInterestRate()), (-1.0 * remainingPayments()))))); // see formula below
			}
			catch (Exception e) // in the event of an error
			{
				System.out.print("The system encountered an error while calculating the monthly payment.");
				monthlyPayment = 0;
			}
		}

		/*
		 * P = (R * A)/(1 – (1 + R)^(-N))
		 *
		 *	Where: 	P = Loan Payment
		 *			R = Rate per Period
		 *			A = Loan Amount
		 *			N = Number of Months 
		 *
		 */
	}
	
	double getMonthlyPayment() // return the current monthly payment
	{
		if (monthlyPayment > (amount + monthlyInterest())) // if the remaining balance is less than the minimum payment
		{
			return (amount + monthlyInterest()); // return what the final payment should be
		}
		return monthlyPayment;
	}
	
	public String toString() // gather current loan information and return it as a string
	{
		try
		{
			return ("Loan name: " + name + "\nCurrent balence: " + new DecimalFormat("$###,###,###,##0.00").format(amount)
				+ "\nThis month's interest: " + new DecimalFormat("$###,###,###,##0.00").format(monthlyInterest())
				+ "\nAnnual interest rate: " + new DecimalFormat("##0.0#").format(annualInterestRate * 100.0) + "%\nPayments Left: "
				+ new DecimalFormat("0").format(remainingPayments())+ "\nMonthly payment amount: "
				+ new DecimalFormat("$###,###,###,##0.00").format(getMonthlyPayment()) + "\n");
		}
		catch (Exception e) // in the event of an error
		{
			return "The system encountered an error upon attempting to retrieve account information.";
		}
	}
	
}
