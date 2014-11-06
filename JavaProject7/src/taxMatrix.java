/**
 * User inputs taxable income and filing status.
 * 
 * Matt Warman
 * Version 1.3
 */

import java.text.DecimalFormat;
import java.util.Scanner;

public class taxMatrix
{
	
	private static Scanner input; // Initialize a generic scanner to be used for user input
	private static String filingStatusName; // Initialize a variable to hold the name of the user input filing status
	
	public static void main(String args[])
	{
		double[][] taxArray = new double[7][4]; // initialize the array used to hold rates and brackets
		double taxableIncome = 0; // hold user input taxable income
		int filingStatus = 0; // hold user input filing status
		
		populateTaxTables(taxArray); // load tax brackets into the tax array
		getRates(taxArray); // get user input tax rates
		// printArray(taxArray); // -- Used for testing only -- print all values stored in array
		while (true)  // just keep running till we hit a "break;" when the user wants to end the program
		{
			filingStatus = getFilingStatus(); // store the user input filing status in filingStatus for later use
			if (filingStatus == -1) // check whether the user elected to quit the program
			{
				System.out.print("\n\nThank you for using the this program.\nHave a nice day."); // print a farewell message
				break; // quit the program
			}
			taxableIncome = getTaxableIncome(); // store the use input taxable income for later use
			// Display the taxable income and the amount of tax based on it in nice formatting. "new DecimalFormat("$###,###,###.00").format(VARIABLE)" will ensure that the output looks like a familiar, correctly formatted, dollar amount.
			System.out.print("\nThe tax on " + new DecimalFormat("$###,###,###.00").format(taxableIncome) + " for a " + filingStatusName + " filer is " + new DecimalFormat("$###,###,###.00").format(taxCalculation(taxableIncome, filingStatus, taxArray)));
			pause(); // wait for the user to press enter before continuing. It helps with the flow of the program.
		}
		
	}
	
	public static void getRates(double array[][]) // get current tax rates from the user
	{
		double rateCompounder = 0; // this helps with converting rates input by the user into a format that can be used with the tax iteration loop.
		for (int i = 0; i < 7; i++) // start at 0 and go till you hit 7
		{
			while(true) // go until we hit a "break;"
			{
				System.out.print("Please input the tax percent for the " + (i+1) + determineSuffix(i+1) + " tier: "); // (i+1) so that it displays as the 1st tier as opposed to the 0th
				input = new Scanner(System.in); // initialize scanner
				if (input.hasNextDouble()) // make sure that the input value is of an acceptable data type
				{
					array[i][0] = ((input.nextDouble() * .01) - rateCompounder); // store the rate as a decimal percent
					rateCompounder = (rateCompounder + array[i][0]); // this makes the rate stored equivalent to the difference between the current and previous rate, which helps with iteration based calculation.
					break; // exit the loop with good data
				}
				else // if the data type was incorrect
				{
					System.out.print("\n\nSorry, you have input an invalid value. Please try again.\n\n");
				}
			}
		}
	}
	
	public static int getFilingStatus() // get the user's filing status
	{
		int status = 0; // initialize a variable to hold user filing status
		while(true) // go until we hit a "break;"
		{
			System.out.print("\nPlease choose from the following:\nEnter 1 to file as Single\nEnter 2 to file as Married filing Jointly\nEnter 3 to file as Head of Household\nEnter -1 to end the program\nPlease input your selection: ");
			input = new Scanner(System.in); // initialize scanner
			if (input.hasNextInt()) // make sure that the input value is of an acceptable data type
			{
				status = input.nextInt(); // store the input integer in status
				if (status == -1 || status == 1 || status == 2 || status == 3) // make sure that the input value is an acceptable option
				{
					break; //  if all is well, break the loop and continue the program
				}
				else // if the user selected an invalid option
				{
					System.out.print("\n\nSorry, you have input an invalid selection. Please try again.\n\n");
				}
			}
			else // if the user input an invalid data type
			{
				System.out.print("\n\nSorry, you have input an invalid value. Please try again.\n\n");
			}
		}
		switch(status) // assign the name of the filing status based on what the user input
		{
		case 1: // if user selected option 1 (file as "Single")
			filingStatusName = "Single"; // set filingStatusName to "Single"
			break;
		case 2: // if user selected option 1 (file as "Married")
			filingStatusName = "Married"; // set filingStatusName to "Married"
			break;
		case 3: // if user selected option 1 (file as "Head of Household")
			filingStatusName = "Head of Household"; // set filingStatusName to "Head of Household"
			break;
		default:
			filingStatusName = ""; // Should never be called, but makes the compiler happy.
			break;
		}
		return status; // return the input filing status
	}
	
	public static double getTaxableIncome() // get the user's taxable income
	{
		double income = 0; // initialize a variable to hold the user's input taxable income
		while(true) // go until we hit a "break;"
		{
			System.out.print("\nPlease input your taxable income: ");
			input = new Scanner(System.in); // initialize scanner
			if (input.hasNextDouble()) // make sure that the input value is of an acceptable data type
			{
				income = input.nextDouble(); // income equals the input double value
				break; // exit the loop and continue the program
			}
			else // if the input value was of an incorrect data type
			{
				System.out.print("\n\nSorry, you have input an invalid value. Please try again.\n\n");
			}
		}
		return income; // return the input taxable income
	}
	
	public static void printArray(double array[][]) // -- Used for testing only -- display all values in the array
	{
		for (int i = 0 ; i < 7 ; i++) // print rows 0 through 6
		{
			for (int n = 0 ; n < 4 ; n++) // print columns 0 through 3 of the current row
			{
				System.out.print(array[i][n]); // display the array value at the current location
				if (n != 3) {System.out.print(" --- ");} // separate middle values
			}
			System.out.print("\n"); // start the next row on a differeant line
		}
	}
	
	public static void populateTaxTables(double array[][]) // inject tax brackets into an array
	{
		// Define single filing status tax brackets
		array[0][1] = 8925;   // $8,925 - $0
		array[1][1] = 27325;  // $36,250 - $8,925
		array[2][1] = 51600;  // $87,850 - $36,250
		array[3][1] = 95400;  // $183,250 - $87,850
		array[4][1] = 215100; // $398,350 - $183,250
		array[5][1] = 1650;   // $400,000 - $398,350
		
		// Define married joint filing status tax brackets
		array[0][2] = 17850;  // $17,850 - $0
		array[1][2] = 54650;  // $72,500 - $17,850
		array[2][2] = 73900;  // $146,400 - $72,500
		array[3][2] = 76650;  // $223,050 - $146,400
		array[4][2] = 175300; // $398,350 - $223,050
		array[5][2] = 51650;  // $450,000 - $398,350
		
		// Define head of household filing status tax brackets
		array[0][3] = 12750;  // $12,750 - $0
		array[1][3] = 35850;  // $48,600 - $12,750
		array[2][3] = 76850;  // $125,450 - $48,600
		array[3][3] = 77700;  // $203,150 - $125,450
		array[4][3] = 195200; // $398,350 - $203,150
		array[5][3] = 26650;  // $425,000 - $398,350		
	}
	
	public static double taxCalculation (double income, int filingStatus, double array[][]) // calculate tax based on taxable income, filing status, tax rates, and tax brackets
	{
		int count = 0; // for use with adding loop
		double calculatedTax = 0; // for dollar amounts to compile
		do // Here is where we actually calculate the amount of tax to be paid
		{
			switch(count) // cases vary based on how many iterations have been run
			{
			case 0: // All cases follow similar structure
				if ((income - array[count][filingStatus]) < 0) // is the current value in income less than the max amount for this range?
				{ // if so, we just want to add the rest of it (multiplied to its respective rate) to calulatedTax
					calculatedTax = calculatedTax + (income * array[count][0]); // take calculatedTax and add the remainder of income to it, after multiplying that by the current rate for this loop cycle
					income = 0; // Zero out income to end the loop
				}
				else
				{ // if not, add income to calculatedTax after multiplying it by the compounded tax rate, then subtract the max amount for this rate from income
					calculatedTax = calculatedTax + (income * array[count][0]); // add income to calculatedTax after multiplying it by the compounded tax rate for this cycle
					income = (income - array[count][filingStatus]); // subtract the max amount for this rate cycle from income
				}
				break; // break the case and allow the loop to check whether there is still money in the income variable
			case 1: if ((income - array[count][filingStatus]) < 0) { calculatedTax = calculatedTax + (income * array[count][0]); income = 0; } // 27325 because $36,250 - $8,925 = 27,325, and so on.
				else { calculatedTax = calculatedTax + (income * array[count][0]); income = (income - array[count][filingStatus]);}
				break;
			case 2: if ((income - array[count][filingStatus]) < 0) { calculatedTax = calculatedTax + (income * array[count][0]); income = 0; }
				else { calculatedTax = calculatedTax + (income * array[count][0]); income = (income - array[count][filingStatus]);}
				break;	
			case 3: if ((income - array[count][filingStatus]) < 0) { calculatedTax = calculatedTax + (income * array[count][0]); income = 0; }
				else { calculatedTax = calculatedTax + (income * array[count][0]); income = (income - array[count][filingStatus]);}
				break;
			case 4: if ((income - array[count][filingStatus]) < 0) { calculatedTax = calculatedTax + (income * array[count][0]); income = 0; }
				else { calculatedTax = calculatedTax + (income * array[count][0]); income = (income - array[count][filingStatus]); }
				break;
			case 5: if ((income - array[count][filingStatus]) < 0) { calculatedTax = calculatedTax + (income * array[count][0]); income = 0; }
				else { calculatedTax = calculatedTax + (income * array[count][0]); income = (income - array[count][filingStatus]);}
				break;
			case 6: calculatedTax = calculatedTax + (income * array[count][0]); income = 0;
				break;
			}
			count++; // increment the rate iteration counter
		} while (income > 0); // check to see if there is still taxable income to be processed
		return calculatedTax; // return the calculated tax
	}
	
	public static String determineSuffix(int numberForSuffix) // This function computes the ordinal suffix for an input value
	{		   
		String[] suffixes = {"th","st","nd","rd","th","th","th","th","th","th"};
		switch(numberForSuffix % 100) // helps determine if the number for suffix is 11, 12, or 13, which don't follow suffix rules very nicely
		{
		case 11:
		case 12:
		case 13:
			return "th"; // for those weird numbers
		default:
			return suffixes[numberForSuffix % 10]; // return with correct suffix
		}	
	}
	
    public static void pause() // this is used to pause the program so the user can choose to continue when they are ready
    {
        System.out.print("\nPress enter to continue\n");
        input = new Scanner(System.in); // initialize a scanner
        if (input.hasNextLine()){}; // wait for the user to press enter before continuing
    }

}