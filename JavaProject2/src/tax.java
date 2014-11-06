/**
 * User inputs filing status and taxable income
 * 
 * Matt Warman
 * Version 1.3
 */

import java.text.DecimalFormat;
import java.util.Scanner;

public class tax
{
	/*
	 * Declaring rates as constants as per assignment instructions.
	 * This program uses a compounding loop where percent is iteration based.
	 * Percentages are defined in such a way that they stack upon one another in order to achieve the desired amount. 
	*/
	public static final double tenPercent = .1; // 10 percent
	public static final double fifteenPercent = .05; // 5 percent plus previous values comes to 15 percent
	public static final double twentyFivePercent = .1; // 10 percent plus previous values comes to 25 percent
	public static final double twentyEightPercent = .03; // 3 percent plus previous values comes to 28 percent
	public static final double thirtyThreePercent = .05; // 5 percent plus previous values comes to 33 percent
	public static final double thirtyFivePercent = .02; // 2 percent plus previous values comes to 35 percent
	public static final double thirtyNineSixPercent = .046; // 4.6 percent plus previous values comes to 39.6 percent
	
	private static Scanner filingInput; // Define temporary location for user input to be read and evaluated before inserting it into filingStatus
	private static Scanner incomeInput; // Define temporary location for user input to be read and evaluated before inserting it into taxableIncome
	
	public static void main(String args[]) // main function
	{
		int filingStatus = 0; // Initialize the filingStatus variable as an integer with default value 0
		double taxableIncome = 0; // Initialize the taxbleIncome variable as an integer with default value 0
		int correctData = 1; // Initialize a variable for use with data type error detection
		
	
		// Print instructions for the user and make sure they enter the correct data type
		do
		{
			filingInput = new Scanner(System.in);
			System.out.print("Please input your filing status:\nInput \"0\" for \"Single\"\nInput \"1\" for \"Married Filing Jointly\"\nInput \"2\" for \"Head of Household\"\n\nEnter your selection here: ");
			if (filingInput.hasNextInt()) // Detect if user actually input a valid data type
			{
				filingStatus = filingInput.nextInt(); // Store the input from the user in the filingStatus variable as an integer
				if (filingStatus == 0 || filingStatus == 1 || filingStatus == 2)
				{
					correctData = 1; // Data type is correct, let us exit the loop
				}
				else 
				{
					System.out.print("\n\"" + filingStatus + "\" is not a valid choice. Please try again\n"); // Alert user that they have chosen an invalid choice
					correctData = 0; // Data type is incorrect, lets continue to run through the loop to try again
				}
			}
			else
			{
				System.out.print("\nYou have input an invalid selection, please try again\n"); // Alert the user that the data type is incorrect
				correctData = 0; // Data type is incorrect, lets continue to run through the loop to try again
			}

		} while (correctData == 0); // if the data type was incorrect, run through the loop again
		
		do
		{
			incomeInput = new Scanner(System.in);
			System.out.print("\nPlease input your taxable income: ");
			if (incomeInput.hasNextDouble()) // Detect if user actually input a number or not
			{
				taxableIncome = incomeInput.nextDouble(); // Store the input form the user in the taxableIncome variable as an integer
				correctData = 1; // Data type is correct, let us exit the loop
			}
			else // If the user input an incorrect data type, report the error and ask them to try again
			{
				System.out.print("\nYou have input an incorrect value, please try again\n"); // Alert the user that the data type is incorrect
				correctData = 0; // Data type is incorrect, lets continue to run through the loop to try again
			} 
			
		} while (correctData == 0); // if the data type was incorrect, run through the loop again
		
		
		
		switch(filingStatus) // Detect input filing status
		{
		case 0: fileSingle(taxableIncome); // Case filing Single
			break;
		case 1: fileJoint(taxableIncome); // Case filing Married Joint
			break;
		case 2: fileHead(taxableIncome); // Case filing Head of Household
			break;
		default: System.out.print("System Error");
			break;
		}
		
	}
	
	public static void fileSingle(double taxableIncome)
	{
		int count = 0; // for use with adding loop
		double calculatedTax = 0; // for dollar amounts to compile
		do // Here is where we actually calculate the amount of tax to be paid
		{
			switch(count)
			{
			case 0: // All cases follow similar structure
				if ((taxableIncome - 8925) < 0) // is the current value in taxableIncome less than the max amount for this range?
				{ // if so, we just want to add the rest of it (multiplied to its respective rate) to calulatedTax
					calculatedTax = calculatedTax + (taxableIncome * tenPercent); // take calculatedTax and add the remainder of taxableIncome to it, after multiplying that by the current rate for this loop cycle
					taxableIncome = 0; // Zero out taxableIncome to end the loop
				}
				else
				{ // if not, add taxableIncome to calculatedTax after multiplying it by the compounded tax rate, then subtract the max amount for this rate from taxableIncome
					calculatedTax = calculatedTax + (taxableIncome * tenPercent); // add taxableIncome to calculatedTax after multiplying it by the compounded tax rate for this cycle
					taxableIncome = (taxableIncome - 8925); // subtract the max amount for this rate cycle from taxableIncome
				}
				break; // break the case, allow the loop to check whether there is still money in the taxableIncome variable
			case 1: if ((taxableIncome - 27325) < 0) { calculatedTax = calculatedTax + (taxableIncome * fifteenPercent); taxableIncome = 0; } // 27325 because $36,250 - $8,925 = 27,325, and so on.
				else { calculatedTax = calculatedTax + (taxableIncome * fifteenPercent); taxableIncome = (taxableIncome - 27325);}
				break;
			case 2: if ((taxableIncome - 51600) < 0) { calculatedTax = calculatedTax + (taxableIncome * twentyFivePercent); taxableIncome = 0; }
				else { calculatedTax = calculatedTax + (taxableIncome * twentyFivePercent); taxableIncome = (taxableIncome - 51600);}
				break;	
			case 3: if ((taxableIncome - 95400) < 0) { calculatedTax = calculatedTax + (taxableIncome * twentyEightPercent); taxableIncome = 0; }
				else { calculatedTax = calculatedTax + (taxableIncome * twentyEightPercent); taxableIncome = (taxableIncome - 95400);}
				break;
			case 4: if ((taxableIncome - 215100) < 0) { calculatedTax = calculatedTax + (taxableIncome * thirtyThreePercent); taxableIncome = 0; }
				else { calculatedTax = calculatedTax + (taxableIncome * thirtyThreePercent); taxableIncome = (taxableIncome - 215100); }
				break;
			case 5: if ((taxableIncome - 1650) < 0) { calculatedTax = calculatedTax + (taxableIncome * thirtyFivePercent); taxableIncome = 0; }
				else { calculatedTax = calculatedTax + (taxableIncome * thirtyFivePercent); taxableIncome = (taxableIncome - 1650);}
				break;
			case 6: calculatedTax = calculatedTax + (taxableIncome * thirtyNineSixPercent); taxableIncome = 0;
				break;
			}
			count++; // increment the rate iteration counter
		} while (taxableIncome > 0); // check to see if there is still taxable income to be processed
		displayTax(calculatedTax); // once all the taxable income has been processed, pass the final calculated tax value to the displayTax function
	}
	
	public static void fileJoint(double taxableIncome)
	{
		int count = 0; // for use with adding loop
		double calculatedTax = 0; // for dollar amounts to compile
		do // Here is where we actually calculate the amount of tax to be paid
		{
			switch(count)
			{
			case 0: // All cases follow similar structure
				if ((taxableIncome - 17850) < 0) // is the current value in taxableIncome less than the max amount for this range?
				{ // if so, we just want to add the rest of it (multiplied to its respective rate) to calulatedTax
					calculatedTax = calculatedTax + (taxableIncome * tenPercent); // take calculatedTax and add the remainder of taxableIncome to it, after multiplying that by the current rate for this loop cycle
					taxableIncome = 0; // Zero out taxableIncome to end the loop
				}
				else
				{ // if not, add taxableIncome to calculatedTax after multiplying it by the compounded tax rate, then subtract the max amount for this rate from taxableIncome
					calculatedTax = calculatedTax + (taxableIncome * tenPercent); // add taxableIncome to calculatedTax after multiplying it by the compounded tax rate for this cycle
					taxableIncome = (taxableIncome - 17850); // subtract the max amount for this rate cycle from taxableIncome
				}
				break; // break the case, allow the loop to check whether there is still money in the taxableIncome variable
			case 1: if ((taxableIncome - 54650) < 0) { calculatedTax = calculatedTax + (taxableIncome * fifteenPercent); taxableIncome = 0; } // 54650 because $72,500 - $17,850 = 54,650, and so on.
				else { calculatedTax = calculatedTax + (taxableIncome * fifteenPercent); taxableIncome = (taxableIncome - 54650);}
				break;
			case 2: if ((taxableIncome - 73900) < 0) { calculatedTax = calculatedTax + (taxableIncome * twentyFivePercent); taxableIncome = 0; }
				else { calculatedTax = calculatedTax + (taxableIncome * twentyFivePercent); taxableIncome = (taxableIncome - 73900);}
				break;	
			case 3: if ((taxableIncome - 76650) < 0) { calculatedTax = calculatedTax + (taxableIncome * twentyEightPercent); taxableIncome = 0; }
				else { calculatedTax = calculatedTax + (taxableIncome * twentyEightPercent); taxableIncome = (taxableIncome - 76650);}
				break;
			case 4: if ((taxableIncome - 175300) < 0) { calculatedTax = calculatedTax + (taxableIncome * thirtyThreePercent); taxableIncome = 0; }
				else { calculatedTax = calculatedTax + (taxableIncome * thirtyThreePercent); taxableIncome = (taxableIncome - 175300); }
				break;
			case 5: if ((taxableIncome - 51650) < 0) { calculatedTax = calculatedTax + (taxableIncome * thirtyFivePercent); taxableIncome = 0; }
				else { calculatedTax = calculatedTax + (taxableIncome * thirtyFivePercent); taxableIncome = (taxableIncome - 51650);}
				break;
			case 6: calculatedTax = calculatedTax + (taxableIncome * thirtyNineSixPercent); taxableIncome = 0;
				break;
			}
			count++; // increment the rate iteration counter
		} while (taxableIncome > 0); // check to see if there is still taxable income to be processed
		displayTax(calculatedTax); // once all the taxable income has been processed, pass the final calculated tax value to the displayTax function
	}
	
	public static void fileHead(double taxableIncome)
	{
		int count = 0; // for use with adding loop
		double calculatedTax = 0; // for dollar amounts to compile
		do // Here is where we actually calculate the amount of tax to be paid
		{
			switch(count)
			{
			case 0: // All cases follow similar structure
				if ((taxableIncome - 12750) < 0) // is the current value in taxableIncome less than the max amount for this range?
				{ // if so, we just want to add the rest of it (multiplied to its respective rate) to calulatedTax
					calculatedTax = calculatedTax + (taxableIncome * tenPercent); // take calculatedTax and add the remainder of taxableIncome to it, after multiplying that by the current rate for this loop cycle
					taxableIncome = 0; // Zero out taxableIncome to end the loop
				}
				else
				{ // if not, add taxableIncome to calculatedTax after multiplying it by the compounded tax rate, then subtract the max amount for this rate from taxableIncome
					calculatedTax = calculatedTax + (taxableIncome * tenPercent); // add taxableIncome to calculatedTax after multiplying it by the compounded tax rate for this cycle
					taxableIncome = (taxableIncome - 12750); // subtract the max amount for this rate cycle from taxableIncome
				}
				break; // break the case, allow the loop to check whether there is still money in the taxableIncome variable
			case 1: if ((taxableIncome - 35850) < 0) { calculatedTax = calculatedTax + (taxableIncome * fifteenPercent); taxableIncome = 0; } // 35850 because $48,600 - $12,750 = 35,850, and so on.
				else { calculatedTax = calculatedTax + (taxableIncome * fifteenPercent); taxableIncome = (taxableIncome - 35850);}
				break;
			case 2: if ((taxableIncome - 76850) < 0) { calculatedTax = calculatedTax + (taxableIncome * twentyFivePercent); taxableIncome = 0; }
				else { calculatedTax = calculatedTax + (taxableIncome * twentyFivePercent); taxableIncome = (taxableIncome - 76850);}
				break;	
			case 3: if ((taxableIncome - 77700) < 0) { calculatedTax = calculatedTax + (taxableIncome * twentyEightPercent); taxableIncome = 0; }
				else { calculatedTax = calculatedTax + (taxableIncome * twentyEightPercent); taxableIncome = (taxableIncome - 77700);}
				break;
			case 4: if ((taxableIncome - 195200) < 0) { calculatedTax = calculatedTax + (taxableIncome * thirtyThreePercent); taxableIncome = 0; }
				else { calculatedTax = calculatedTax + (taxableIncome * thirtyThreePercent); taxableIncome = (taxableIncome - 195200); }
				break;
			case 5: if ((taxableIncome - 26650) < 0) { calculatedTax = calculatedTax + (taxableIncome * thirtyFivePercent); taxableIncome = 0; }
				else { calculatedTax = calculatedTax + (taxableIncome * thirtyFivePercent); taxableIncome = (taxableIncome - 26650);}
				break;
			case 6: calculatedTax = calculatedTax + (taxableIncome * thirtyNineSixPercent); taxableIncome = 0;
				break;
			}
			count++; // increment the rate iteration counter
		} while (taxableIncome > 0); // check to see if there is still taxable income to be processed
		displayTax(calculatedTax); // once all the taxable income has been processed, pass the final calculated tax value to the displayTax function
	}
	
	public static void displayTax(double calculatedTax)
	{
		// Here is where we send the final calculatedTax value to be displayed in a particular user friendly format
		System.out.print("\nYour calculated tax is: $" + new DecimalFormat("#.00").format(calculatedTax));
	}
}