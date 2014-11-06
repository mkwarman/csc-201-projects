/**
 * Calculate things based on single line user input
 * 
 * Matt Warman 
 * Version: 1.7
 */

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class scoreCalculations
{    
	private static Scanner courseNameInput; // Define temporary location for user input to be read and evaluated before inserting it into courseName
	private static Scanner courseScoresInput; // Define temporary location for user input to be read and evaluated before inserting it into courseScores
	private static String courseName; // variable to store name of class

	
    public static void main(String args[])
    {
		int endProgram = 0; // determines if the program should end or continue running based on user input.
		int[] array = new int[40]; // initialize an array with 40 integer spaces
		
		do // Get number of courses
		{
			endProgram = 0; // This value must be reset each time the program is run.
			courseNameInput = new Scanner(System.in); // initialize courseName input
			System.out.print("Please enter the name of the course: ");
			courseName = courseNameInput.nextLine(); //store the user input name of course
			
			int length = loadArray(array); // we store this in a variable so that the method only needs to be executed once
			double average = findAverage(array, length); // we store this in a variable so that the method only needs to be executed once
			// To display values in nice formatting. "new DecimalFormat("0.00").format(VARIABLE)" is used to ensure two decimal points are shown
			System.out.println("\nThe number of scores in " + courseName + " is: " + length); // display the number of scores input
			System.out.println("The average score for " + courseName + " is: " + new DecimalFormat("0.00").format(average)); // display the calculated average of the scores input
			System.out.println("The minimum score for " + courseName + " is: " + findMinimum(array, length)); // display the calculated minimum of scores input
			System.out.println("The maximum score for " + courseName + " is: " + findMaximum(array, length)); // display the calculated maximum of scores input
			System.out.println("The standard deviations of the scores for " + courseName + " is: " + new DecimalFormat("0.00").format(findSTD(array, length, average))); // display the calculated standard deviation for scores input
			System.out.println("The sorted scores array for " + courseName + " is: "); // display a sorted list of the scores input
			printArray(array, length); // execute the print array method to print the sorted array
			System.out.println("The median score for " + courseName + " is: " + new DecimalFormat("0.00").format(findMedian(array, length)) + "\n\n"); // display the calculated median of scores input
			endProgram = JOptionPane.showConfirmDialog(null, "Would you like to continue?", "Continue", JOptionPane.YES_NO_OPTION);
			/*
			 * The above line displays a dialog box asking the user if they would like to continue or not.
			 * If the user clicks "Yes", endProgram is assigned the value 0, saying that they do not want to end the program.
			 * If the user clicks "No", endProgram is assigned the value 1, saying that they do want to end the program.
			 */
		} while (endProgram == 0); // if the user does not want to end the program, run again
    }
    
    public static int loadArray(int[] array) // Load the scores into an array and return the size of the array. Size is the number of elements that the array contains.
    {
		int correctData = 1; // variable used to flag if incorrect data has been entered, necessitating re-entry
		int courseScore; // temporarily holds the most recently scanned value
		int scoreNumber = 0; // Serve as a counter
		
		do // we want this to be run at least once, so lets use a do-while loop
		{
			scoreNumber = 0; // reset the counter
			for (int i = 0; i < 40; i++) // reset the array if there happens to be any values left in it from previous calculations
			{
				array[i] = -1;
				/*
				 * we choose '-1' here because a '0' could get mistaken as another value if the user forgets to type '-1'.
				 * This way, even if the user forgets to include '-1' at the end of their line of scores, the program will still see where their inputed values end.
				 */
			}
			correctData = 1; // innocent until proven guilty
			courseScoresInput = new Scanner(System.in); // initialize courseScore input
			System.out.print("\nPlease enter the scores (integer numbers seperated by spaces)\nfor " + courseName + " on a single line and type a \"-1\" at the end: ");
			while (true) // we're basically going run this loop until we catch a 'break;'.
			{
				if (courseScoresInput.hasNextInt()) // detect if the next value is actually a correct data type
				{
					courseScore = courseScoresInput.nextInt(); // let courseScore hold the value of the next number in the user input sequence
					if (courseScore != -1) // in order to detect when we're supposed to end the loop
					{
						array[scoreNumber] = courseScore; // store the input score in the current location in the array
						scoreNumber++; // increment the location counter
					}
					else // called when "-1" is detected
					{
						break; // if "-1" is detected, break the loop
					}
				}
				else // the next "number" was something other than an integer
				{
					System.out.print("\nOne or more of the values entered was of an incorrect data type.\nPlease try again.\n\n");
					correctData = 0; // incorrect data? guilty!
					break; // exit the loop
					/* 
					 * Since correctData = 0 and we broke the loop, the do-while loop will catch correctData's value and see that it needs to
					 * run again, allowing the user another chance to input correct data
					*/
				}
			}
		} while (correctData == 0); // was data entered incorrectly? If so, run the loop again.
    	return scoreNumber; // return the length of the array
    }
    
    public static double findAverage(int[] array, int length) // Find the average score and return it

    {
    	double cumulativeTotal = 0; // initialize a compounding variable to aid in computing the average
    	double average = 0; // initialize a variable to contain the computed average
    	for (int i = 0 ; i < length ; i++) // start at the beginning and go till the end
    	{
    		cumulativeTotal = cumulativeTotal + array[i]; // compound scores as the loop moves through the array
    	}
    	average = (cumulativeTotal / length); // compute the average value
    	return average; // return the computed average
    }
    
    public static int findMinimum(int[] array, int length) // Find the minimum score and return it
    {
    	int minimum = 999999; // we choose an initial value that should be well outside the realm of possible input
    	for (int i = 0 ; i < length ; i++) // start at the beginning and go till the end
    	{
    		if (array[i] < minimum) {minimum = array[i];} // if the current location in the array has a value lower than the current minimum, redefine the minimum as that value
    	}
    	return minimum; // return the computed minimum
    }
    
    public static int findMaximum(int[] array, int length)
    {
    	int maximum = -999999; // we choose an initial value that should be well outside the realm of possible input
    	for (int i = 0 ; i < length ; i++) // start at the beginning and go till the end
    	{
    		if (array[i] > maximum) {maximum = array[i];} // if the current location in the array has a value higher than the current maximum, redefine the maximum as that value
    	}
    	return maximum; // return the computed maximum
    }
    
    public static double findSTD(int[] array, int length, double mean) // Find the standard deviation and return it
    {
    	double cumulativeTotal = 0; // initialize a compounding variable to aid in computing the standard deviation
    	for (int i = 0 ; i < length ; i++) // start at the beginning and go till the end
    	{
    		cumulativeTotal = (cumulativeTotal + Math.pow((array[i] - mean), 2)); // compound calculations for standard deviation: (value - mean)^2
    	}
    	return Math.sqrt(cumulativeTotal / (length-1)); // return the calculated standard deviation sqrt( (sum( (value - mean)^2 ) / (numOfScores - 1)) )
    }
    
    public static void printArray(int[] array, int length) // Print the sorted array
    {
    	int count = 0; // a simple iteration counter to hold the array starting point
    	Arrays.sort(array); // sort the array from least to greatest
    	for (int i = 0 ; i < 40 ; i++) // start at the beginning and go till the end
    	{
    		if (array[i] != -1)
    		{
    			count = i;
    			break;
    		} // once the beginning of the input values is found, store that location in the variable 'count'
    	}
    	while (count < 40) // start at the beginning and go till the end
    	{
        	if (array[count] != -1) {System.out.println(array[count]);} // print the value at the current location in the array
        	count++; // increment the iteration count
    	}
    }
    
    public static double findMedian(int[] array, int length) // Find the median score and return it.
    {
    	switch(length % 2) // this determines of the number of scores input is odd or even
    	{
    	case 0: // for even array length values
    		// The nasty equation below basically calculates the value between the two median values when given an even array length
    		return ((( array[40 - (length/2)] - array[40 - (length/2 + 1)] ) / 2.0) + array[40 - (length/2 + 1)]);
    		
    	case 1: // for odd array values
    		return array[ (40 - ((length + 1) / 2)) ]; // this calculation finds and returns the median value
    		
    	default: return 0; // should never be called, but makes the compiler happy
    	}
    }
}
