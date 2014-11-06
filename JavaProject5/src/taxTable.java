/**
 * User inputs taxable income.
 * 
 * Matt Warman
 * Version 1.1
 */

import java.text.DecimalFormat;
import java.util.Scanner;

public class taxTable {
	
	public static Scanner startValueInput; // Temporarily store user input for starting value.
	public static Scanner endValueInput; // Temporarily store user input for ending value.
	public static Scanner incrementInput; // Temporarily store user input for income.

	// Define percentage variables
	public static final double tenPercent = .1; // 10 percent
	public static final double fifteenPercent = .05; // 5 percent plus previous values comes to 15 percent
	public static final double twentyFivePercent = .1; // 10 percent plus previous values comes to 25 percent
	public static final double twentyEightPercent = .03; // 3 percent plus previous values comes to 28 percent
	public static final double thirtyThreePercent = .05; // 5 percent plus previous values comes to 33 percent
	public static final double thirtyFivePercent = .02; // 2 percent plus previous values comes to 35 percent
	public static final double thirtyNineSixPercent = .046; // 4.6 percent plus previous values comes to 39.6 percent
	
	public static void main(String args[])
	{
		double startValue = 0; // this will hold the starting value
		double endValue = 0; // the will hold the ending value
		double increment = 0; // this will hold the value to increment by
		double income = 0; // this will hold the taxable income input by the user
		int correctData = 1; // Initialize a variable for use with data type error detection
		
		do
		{
			startValueInput = new Scanner(System.in); // Initialize scanner for starting value
			System.out.print("\nPlease input the starting value: ");
			if (startValueInput.hasNextDouble()) // Detect if user actually input a number or not
			{
				startValue = startValueInput.nextDouble(); // Store the input form the user in the taxableIncome variable as an integer
				correctData = 1; // Data type is correct, let us exit the loop
			}
			else // If the user input an incorrect data type, report the error and ask them to try again
			{
				System.out.print("\nYou have input an incorrect value, please try again\n"); // Alert the user that the data type is incorrect
				correctData = 0; // Data type is incorrect, lets continue to run through the loop to try again
			} 
		} while (correctData == 0); // if the data type was incorrect, run through the loop again
		do
		{
			endValueInput = new Scanner(System.in); // Initialize scanner for ending value
			System.out.print("\nPlease input the ending value: ");
			if (endValueInput.hasNextDouble()) // Detect if user actually input a number or not
			{
				endValue = endValueInput.nextDouble(); // Store the input form the user in the taxableIncome variable as an integer
				correctData = 1; // Data type is correct, let us exit the loop
			}
			else // If the user input an incorrect data type, report the error and ask them to try again
			{
				System.out.print("\nYou have input an incorrect value, please try again\n"); // Alert the user that the data type is incorrect
				correctData = 0; // Data type is incorrect, lets continue to run through the loop to try again
			} 
		} while (correctData == 0); // if the data type was incorrect, run through the loop again
		do
		{
			if (startValue == endValue) // if the starting value is equal to the ending value
			{
				break; // break the loop, theres no need to ask for iteration if there is no difference between the values
			}
			incrementInput = new Scanner(System.in); // Initialize scanner for increment value
			System.out.print("\nPlease input the amount to increment by: ");
			if (incrementInput.hasNextDouble()) // Detect if user actually input a number or not
			{
				increment = incrementInput.nextDouble(); // Store the input form the user in the taxableIncome variable as an integer
				correctData = 1; // Data type is correct, let us exit the loop
			}
			else // If the user input an incorrect data type, report the error and ask them to try again
			{
				System.out.print("\nYou have input an incorrect value, please try again\n"); // Alert the user that the data type is incorrect
				correctData = 0; // Data type is incorrect, lets continue to run through the loop to try again
			} 
		} while (correctData == 0);// if the data type was incorrect, run through the loop again
		if (((endValue <= startValue) && (increment > 0)) || ((endValue >= startValue) && (increment < 0))) // if someone enters a number for the ending value that is lower than or equal to the starting value, with an increment value greater than zero
		{
			String catchError; // this is how we're going to pause the program in order to prompt the user to press Enter
			System.out.print("\nThe input increment amount will cause an infinite loop.\nIt has been negated to rectify the situation.\nPress enter to continue:");
			Scanner catchErrorInput = new Scanner(System.in); // wait until user presses Enter (part 1/2)
			catchError = catchErrorInput.nextLine();// wait until user presses Enter (part 2/2)
			increment = -(increment); // set the incrementer to be negative so that our calculations will work and our results will display logically
		}
		
		income = startValue; // our starting point for the tax calculations
		System.out.print("\n\n                                    Tax Table for the Year 2013");
		System.out.print("\n\nTaxable Income:                Single:                        Married Jointly:               Head of Household:");
		do
		{
			// Display the taxable income and the amount of tax based on it in nice formatting. "new DecimalFormat("$###,###,###.00").format(VARIABLE)" will ensure that the output looks like a familiar, correctly formatted, dollar amount.
			System.out.print("\n" + new DecimalFormat("$###,###,###.00").format(income) + "                     " + new DecimalFormat("$###,###,###.00").format(singleFilerTax(income)) + "                      " + new DecimalFormat("$###,###,###.00").format(marriedJointFilerTax(income)) + "                      " + new DecimalFormat("$###,###,###.00").format(headOfHouseholdFilerTax(income)));
			if (endValue == income) {break;} // if there is no more to increment, break the loop and exit the program
			
			/*
			 * For the following statement:
			 * 
			 * If the remaining amount is less than an entire iteration's worth; run the body.
			 * Math.abs(VARIABLE) is used here to enable this if statement to work regardless of whether
			 * we are going from a lower starting value to a higher ending value or a higher starting
			 * value to a lower ending value.
			 */
			
			if (Math.abs(endValue - income) < Math.abs(increment)) // see above explanation
			{
				income = endValue; // set income to be equal to the final value to be calculated
				// Display the taxable income and the amount of tax based on it in nice formatting. "new DecimalFormat("$###,###,###.00").format(VARIABLE)" will ensure that the output looks like a familiar, correctly formatted, dollar amount.
				System.out.print("\n" + new DecimalFormat("$###,###,###.00").format(income) + "                     " + new DecimalFormat("$###,###,###.00").format(singleFilerTax(income)) + "                      " + new DecimalFormat("$###,###,###.00").format(marriedJointFilerTax(income)) + "                      " + new DecimalFormat("$###,###,###.00").format(headOfHouseholdFilerTax(income)));
				break; // end the loop since this is the last iteration
			}
			income = income + increment; // increment the income variable to move on to the next value
		} while (true);
	}
	
	public static double singleFilerTax (double income)
	{
		
		// Define single filing status tax brackets
		int taxTierOne = 8925;
		int taxTierTwo = 27325;
		int taxTierThree = 51600;
		int taxTierFour = 95400;
		int taxTierFive = 215100;
		int taxTierSix = 1650;
		double calculatedTax = taxCalculation (income, taxTierOne, taxTierTwo, taxTierThree, taxTierFour, taxTierFive, taxTierSix); // compute tax based on input income and given tax brackets
		return calculatedTax; // return the calculated tax
	}
	
	public static double marriedJointFilerTax (double income)
	{
		
		// Define married-joint filing status tax brackets
		int taxTierOne = 17850;
		int taxTierTwo = 54650;
		int taxTierThree = 73900;
		int taxTierFour = 76650;
		int taxTierFive = 175300;
		int taxTierSix = 51650;
		double calculatedTax = taxCalculation (income, taxTierOne, taxTierTwo, taxTierThree, taxTierFour, taxTierFive, taxTierSix); // compute tax based on input income and given tax brackets
		return calculatedTax; // return the calculated tax
	}
	
	public static double headOfHouseholdFilerTax (double income)
	{
		
		// Define head of household filing status tax brackets
		int taxTierOne = 12750;
		int taxTierTwo = 35850;
		int taxTierThree = 76850;
		int taxTierFour = 77700;
		int taxTierFive = 195200;
		int taxTierSix = 26650;
		double calculatedTax = taxCalculation (income, taxTierOne, taxTierTwo, taxTierThree, taxTierFour, taxTierFive, taxTierSix); // compute tax based on input income and given tax brackets
		return calculatedTax; // return the calculated tax
	}
	
	public static double taxCalculation (double income, int taxTierOne, int taxTierTwo, int taxTierThree, int taxTierFour, int taxTierFive, int taxTierSix)
	{
		int count = 0; // for use with adding loop
		double calculatedTax = 0; // for dollar amounts to compile
		do // Here is where we actually calculate the amount of tax to be paid
		{
			switch(count)
			{
			case 0: // All cases follow similar structure
				if ((income - taxTierOne) < 0) // is the current value in income less than the max amount for this range?
				{ // if so, we just want to add the rest of it (multiplied to its respective rate) to calulatedTax
					calculatedTax = calculatedTax + (income * tenPercent); // take calculatedTax and add the remainder of income to it, after multiplying that by the current rate for this loop cycle
					income = 0; // Zero out income to end the loop
				}
				else
				{ // if not, add income to calculatedTax after multiplying it by the compounded tax rate, then subtract the max amount for this rate from income
					calculatedTax = calculatedTax + (income * tenPercent); // add income to calculatedTax after multiplying it by the compounded tax rate for this cycle
					income = (income - taxTierOne); // subtract the max amount for this rate cycle from income
				}
				break; // break the case, allow the loop to check whether there is still money in the income variable
			case 1: if ((income - taxTierTwo) < 0) { calculatedTax = calculatedTax + (income * fifteenPercent); income = 0; } // 27325 because $36,250 - $8,925 = 27,325, and so on.
				else { calculatedTax = calculatedTax + (income * fifteenPercent); income = (income - taxTierTwo);}
				break;
			case 2: if ((income - taxTierThree) < 0) { calculatedTax = calculatedTax + (income * twentyFivePercent); income = 0; }
				else { calculatedTax = calculatedTax + (income * twentyFivePercent); income = (income - taxTierThree);}
				break;	
			case 3: if ((income - taxTierFour) < 0) { calculatedTax = calculatedTax + (income * twentyEightPercent); income = 0; }
				else { calculatedTax = calculatedTax + (income * twentyEightPercent); income = (income - taxTierFour);}
				break;
			case 4: if ((income - taxTierFive) < 0) { calculatedTax = calculatedTax + (income * thirtyThreePercent); income = 0; }
				else { calculatedTax = calculatedTax + (income * thirtyThreePercent); income = (income - taxTierFive); }
				break;
			case 5: if ((income - taxTierSix) < 0) { calculatedTax = calculatedTax + (income * thirtyFivePercent); income = 0; }
				else { calculatedTax = calculatedTax + (income * thirtyFivePercent); income = (income - taxTierSix);}
				break;
			case 6: calculatedTax = calculatedTax + (income * thirtyNineSixPercent); income = 0;
				break;
			}
			count++; // increment the rate iteration counter
		} while (income > 0); // check to see if there is still taxable income to be processed
		return calculatedTax;
	}
	
}