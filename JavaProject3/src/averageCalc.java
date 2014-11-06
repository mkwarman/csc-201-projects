import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * User inputs number of classes to be calculated for, class names, and scores.
 * 
 * Matt Warman
 * Version 1.5
 */

public class averageCalc {
	
	private static Scanner numberOfCoursesInput; // Define temporary location for user input to be read and evaluated before inserting it into numberOfCourses
	private static Scanner courseNameInput; // Define temporary location for user input to be read and evaluated before inserting it into courseName
	private static Scanner courseGradeInput; // Define temporary location for user input to be read and evaluated before inserting it into courseGrade

	public static void main(String args[]) // main function
	{
		int numberOfCourses = 0; // this will be used to help count the number of times the course loop needs to run, and to help calculate the global average
		int courseNumber = 1; // this will be used to help determine suffix and keep track of the times the course loop has run
		int correctData = 1; // this will be used to determine whether a loop need to run again based on if data was incorrectly entered or not
		int numberOfGrades; // this will be used to help calculate course averages
		int cumulusNumberOfGrades = 0; // this will be used to display the total number of grades enteres (extra credit! whoo!)
		double courseGrade = 0; // this will hold the numerical course grade after the data has been declared 'sanitary' 
		double courseAverage; // this will be calculated and used to display course averages
		double courseMinimumGrade; // this will be compared to course grade to determine whether there is a new minimum or not. It will also be used to display the minimum course grade
		double courseMaximumGrade; // this will be compared to course grade to determine whether there is a new maximum or not. It will also be used to display the maximum course grade
		double cumulusCourseGrade; // this will be used to help compute a course average
		double cumulusCourseAverage = 0; // this will be used to calculate a global course average (extra credit! whoo!)
		double globalMinimumGrade = 999999; // this will be used to evaluate and house a global minimum grade (extra credit! whoo!)
		double globalMaximumGrade = -999999; // this will be used to evaluate and house a global maximum grade (extra credit! whoo!)
		String courseName; // this will be used to house course names
		
		do // Get number of courses
		{
			numberOfCoursesInput = new Scanner(System.in); // initialize numberOfCourses input
			System.out.print("Please enter the number of courses you would like to have the maximum, minimum, and average scores calculated for: ");
			if (numberOfCoursesInput.hasNextInt()) // Detect if user actually input a valid data type
			{
				numberOfCourses = numberOfCoursesInput.nextInt(); // Store the input from the user in the filingStatus variable as an integer
				correctData = 1; // the data entered is of the correct type
			}
			else
			{
				System.out.print("\nYou have input an invalid selection, please try again\n"); // Alert the user that the data type is incorrect
				correctData = 0; // Data type is incorrect, lets continue to run through the loop to try again
			}
				
		} while (correctData == 0); // if the data type was incorrect, run through the loop again
		
		while (courseNumber <= numberOfCourses ) // Start the course grade calculation loop
		{
			cumulusCourseGrade = 0; // reset this value for each course 
			numberOfGrades = 0; // reset this value for each course 
			courseAverage = 0; // reset this value for each course 
			courseMinimumGrade = 9999; // reset this value for each course 
			courseMaximumGrade = -9999; // reset this value for each course 
			numberOfGrades = 0; // initialize the number of grades counter
			courseNameInput = new Scanner(System.in); // initialize scanner for courseName 
			System.out.print("\n\nPlease input the name of the " + courseNumber + determineSuffix(courseNumber) + " course: ");
			courseName = courseNameInput.nextLine(); // store user input in courseName
			do 
			{
				courseGradeInput = new Scanner(System.in); // initialize scanner for courseGrade
				System.out.print("\nPlease enter a score for the course \"" + courseName + "\" (enter \"-1\" to signify no more grades to be input): ");
				if (courseGradeInput.hasNextDouble()) // determine if the input was actually a double, verses, say; a string
				{
					courseGrade = courseGradeInput.nextDouble(); // store the double value in courseGrade
					if (courseGrade != -1)  // make sure this isn't the 'I'm done' value
					{
						cumulusCourseGrade = cumulusCourseGrade + courseGrade; // compound on cumulusCourseGrade to help calculate course average
						numberOfGrades++; // keep track of how many grades have been entered to help with average grade calculation
					}
					if (courseGrade < courseMinimumGrade && courseGrade != -1) // is the recently input value a new minimum?
					{
						courseMinimumGrade = courseGrade; // if so, overwrite the value in courseMinimumGrade
					}
					if (courseGrade > courseMaximumGrade && courseGrade != -1) // is the recently input value a new maximum?
					{
						courseMaximumGrade = courseGrade; // if so, overwrite the value in courseMaxiumuGrade
					}
					correctData = 1; // the data was of the correct type, let us move on
				}
				else // if the data type is incorrect
				{
					System.out.print("\nYou have input an incorrect data type. Please try again"); // alert the user
					correctData = 0; // data was incorrect, lets try that again
				}
			} while (courseGrade != -1 || correctData == 0); // are we done, was the data type correct or should we try again?
			courseAverage = (cumulusCourseGrade/numberOfGrades); // calculate course average
			if (numberOfGrades == 0) // in case the user doesn't actually enter any grades
			{
				courseMinimumGrade = 0; // this value looks nicer than something like "NaN"
				courseMaximumGrade = 0; // this value looks nicer than something like "NaN"
				courseAverage = 0; // this value looks nicer than something like "NaN"
			}
			// Display course name, number of scores entered, calculated course average, maximum, and minimum in nice formatting. "new DecimalFormat("0.00").format(VARIABLE)" will ensure that the output looks like a grade format
			System.out.print("\nThe course name is: \"" + courseName + "\"\nThe number of scores entered is: " + numberOfGrades + "\nThe average score for \"" + courseName + "\" was: " + new DecimalFormat("0.00").format(courseAverage) + "\nThe maximum score for \"" + courseName + "\" was: " + new DecimalFormat("0.00").format(courseMaximumGrade) + "\nThe minimum score for \"" + courseName + "\" was: " + new DecimalFormat("0.00").format(courseMinimumGrade));
			cumulusCourseAverage = cumulusCourseAverage + courseAverage; // these accumulated values make calculating a global average easy
			if (courseMaximumGrade > globalMaximumGrade) {globalMaximumGrade = courseMaximumGrade;} // if the course's maximum grade is larger than the global maximum grade up to this point, overwrite globalMaximumGrade with the new maximum
			if (courseMinimumGrade < globalMinimumGrade) {globalMinimumGrade = courseMinimumGrade;} // if the course's minimum grade is smaller than the global minimum grade up to this point, overwrite globalMinimumGrade with the new minimum
			cumulusNumberOfGrades = cumulusNumberOfGrades + numberOfGrades; // keep track of how many total grades were entered
			courseNumber++; // keep track of how many courses have been input
		}
		if (numberOfCourses == 0) // in case the user enters zero classes
		{
			cumulusCourseAverage = 0; // this value looks nicer than something like "NaN"
			numberOfCourses = 1; // so we don't have divide by zero issues
			globalMaximumGrade = 0; // this value looks nicer than something like "NaN"
			globalMinimumGrade = 0; // this value looks nicer than something like "NaN"
		}
		// Display "All Classes", the total number of scored entered, calculated global average, maximum, and minimum in nice formatting. "new DecimalFormat("0.00").format(VARIABLE)" will ensure that the output looks like a grade format
		System.out.print("\n\nAll Classes\nThe total number of scores entered is: " + cumulusNumberOfGrades + "\nThe global average for all course grades is: " + new DecimalFormat("0.00").format(cumulusCourseAverage/numberOfCourses) + "\nThe global maximum for all courses is: " + new DecimalFormat("0.00").format(globalMaximumGrade) + "\nThe global minimum for all courses is: " + new DecimalFormat("0.00").format(globalMinimumGrade));
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
}
