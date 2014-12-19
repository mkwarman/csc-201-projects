import java.text.DecimalFormat;

/**
 * Loan class for the Loan Management Program.
 * 
 * Matt Warman 
 * 1.2
 */

public class Loan {
	
	String name;
	double amount;
	double term;
	double annualInterestRate;
	
	Loan()
	{
		name = "Example Loan";
		amount = 1000.0;
		term = 1.0;
		annualInterestRate = 0.1;
	}
	
	Loan(String setName, double setAmount, double setTerm, double setInterest)
	{
		name = setName;
		amount = setAmount;
		term = setTerm;
		annualInterestRate = (setInterest/100.0);
	}
	
	String getName()
	{
		return name;
	}
	
	void setName(String setName)
	{
		name = setName;
	}
	
	double getAmount()
	{
		return amount;
	}
	
	void setAmount(double setAmount)
	{
		amount = setAmount;
	}
	
	double getTerm()
	{
		return term;
	}
	
	void setTerm(double setTerm)
	{
		term = setTerm;
	}
	
	double getInterest()
	{
		try
		{
			return (annualInterestRate * 100.0);
		}
		catch (Exception e)
		{
			System.out.print("The system encountered an error while trying to retrieve the annual interest rate.");
			return 0;
		}
	}
	
	void setInterest(double setInterest)
	{
		try
		{
			annualInterestRate = (setInterest / 100.0);
		}
		catch (Exception e)
		{
			System.out.print("The system encountered an error while trying to set the annual interest rate.");
		}
	}
	
	void makePayment(double paymentAmount)
	{
		try
		{
			amount = (amount - paymentAmount);
			amount = (amount + monthlyInterest());
			term = (term - (1.0 / 12.0));
		}
		catch (Exception e)
		{
			System.out.print("The system encountered an error while processing your payment.");
		}
	}
	
	double monthlyInterestRate()
	{
		try
		{
			return (annualInterestRate / 12.0);
		}
		catch (Exception e)
		{
			System.out.print("The system encountered an error while calculating the monthly interest rate.");
			return 0;
		}
	}
	
	double monthlyInterest()
	{
		try
		{
			return (amount * monthlyInterestRate());
		}
		catch (Exception e)
		{
			System.out.print("The system encountered an error while calculating the monthly interest.");
			return 0;
		}
	}
	
	double remainingPayments()
	{
		try
		{
			return (term * 12.0);
		}
		catch (Exception e)
		{
			System.out.print("The system encountered an error while calculating the remaining payments.");
			return 0;
		}
	}
	
	double monthlyPayment()
	{
		if (amount == 0)
		{
			return 0;
		}
		else
		{
			try
			{
				return ((monthlyInterestRate() * amount)/(1.0 - (Math.pow((1.0 + monthlyInterestRate()), (-1.0 * remainingPayments())))));
			}
			catch (Exception e)
			{
				System.out.print("The system encountered an error while calculating the monthly payment.");
				return 0;
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
	
	public String toString()
	{
		try
		{
			return ("Loan name: " + name + "\nCurrent balence: " + new DecimalFormat("$###,###,###,##0.00").format(amount) + "\nAnnual interest rate: "
				+ new DecimalFormat("##0.0#").format(annualInterestRate * 100.0) + "%\nPayments Left: "
				+ new DecimalFormat("0").format(remainingPayments())+ "\nMonthly payment amount: "
				+ new DecimalFormat("$###,###,###,##0.00").format(monthlyPayment()) + "\n");
		}
		catch (Exception e)
		{
			return "The system encountered an error upon attempting to retrieve account information.";
		}
	}
	
}
