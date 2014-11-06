/**
 * User inputs number of classes to be calculated for, class names, and scores.
 * 
 * Matt Warman
 * Version 1.2
 */

import java.text.DecimalFormat;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class classDataCalc
{
	private static Scanner nameOfCourseInput; // Define temporary location for user input to be read and evaluated before inserting it into nameOfCourse
	private static Scanner courseScoresInput; // Define temporary location for user input to be read and evaluated before inserting it into nameOfCourse

	
	public static void main(String args[]) // main function
	{
		String nameOfCourse; // variable to store name of class
		int correctData = 1; // variable used to flag if incorrect data has been entered, necessitating re-entry
		int numberOfScores; // houses the detected number of scores input. This facilitates easy average calculation and allows us to display the number at the end
		int endProgram = 0; // determines if the program should end or continue running based on user input.
		double minimum; // houses detected minimum score
		double maximum; // houses detected maximum score
		double courseScore; // temporarily holds the most recently scanned value
		double cumulativeScore; // accumulates scores allowing for easy calculation of course average
		
		do // Get number of courses
		{
			numberOfScores = 0; // Initialize this value each time a new course to be graded is started.
			minimum = 999999; // Initialize this value each time a new course to be graded is started.
			maximum = -999999; // Initialize this value each time a new course to be graded is started.
			cumulativeScore = 0; // Initialize this value each time a new course to be graded is started.
			nameOfCourseInput = new Scanner(System.in); // initialize nameOfCourse input
			System.out.print("Please enter the name of the course you would like to have the maximum, minimum, and average scores calculated for: ");
			nameOfCourse = nameOfCourseInput.nextLine(); //store the user input name of course
			do // we want this to be run at least once, so lets use a do-while loop
			{
				correctData = 1; // innocent until proven guilty
				courseScoresInput = new Scanner(System.in); // initialize courseScore input
				System.out.print("Please enter the scores for " + nameOfCourse + " on a single line and type a \"-1\" at the end: ");
				while (true) // we're basically going run this loop until we catch a 'break;'.
				{
					if (courseScoresInput.hasNextDouble()) // detect if the next value is actually a correct data type
					{
						courseScore = courseScoresInput.nextDouble(); // let courseScore hold the value of the next number in the user input sequence
						if (courseScore != -1) // in order to detect when we're supposed to end the loop
						{
							cumulativeScore = cumulativeScore + courseScore; // accumulate scores in cumulativeScore in order to calculate average
							if (courseScore < minimum) {minimum = courseScore;} // test for new minimum, store it in minimum if it is
							if (courseScore > maximum) {maximum = courseScore;} // test for new maximum, store it in maximum if it is
							numberOfScores++; // this helps us keep track of how many scored were input so we can calculate the average easily and also display the number at the end
						}
						else // called when "-1" is detected
						{
							break; // if "-1" is detected, break the loop
						}
					}
					else // the next "number" was something other than an double/float/integer
					{
						System.out.print("\nUnfortunately, it seems that one or more of the values entered was of an incorrect data type.\nPlease try again.\n");
						correctData = 0; // incorrect data? guilty!
						break; // exit the loop
						/* 
						 * Since correctData = 0 and we broke the loop, the do-while loop will catch correctData's value and see that it needs to
						 * run again, allowing the user another chance to input correct data
						*/
					}
				}
			} while (correctData == 0); // was data entered incorrectly? If so, run the loop again.
			// Display course name, the number of scored entered, course average, maximum, and minimum in nice formatting. "new DecimalFormat("0.00").format(VARIABLE)" will ensure that the output looks like a grade format
			System.out.print("\n\nCourse name: \"" + nameOfCourse + "\"\nNumber of scores: " + numberOfScores + "\nThe average score: " + new DecimalFormat("0.00").format(cumulativeScore/numberOfScores) + "\nThe maximum score: " + new DecimalFormat("0.00").format(maximum) + "\nThe minimum score: " + new DecimalFormat("0.00").format(minimum) + "\n\n");
			endProgram = JOptionPane.showConfirmDialog(null, "Would you like to continue?", "Continue", JOptionPane.YES_NO_OPTION);
			/*
			 * The above line displays a dialog box asking the user if they would like to continue or not.
			 * If the user clicks "Yes", endProgram is assigned the value 0, saying that they do not want to end the program.
			 * If the user clicks "No", endProgram is assigned the value 1, saying that they do want to end the program.
			 */
		} while (endProgram == 0); // if the user does not want to end the program, run again
	}
}