/**
 * This program will take in user input and output calculated values
 * 
 * Matt Warman
 * Version: 1.5
 */

import java.util.Scanner;
import java.text.DecimalFormat;

public class areaCalc
{
    // Scanner variables
    public static Scanner calcSelectionInput; // Initialize scanner to hold user selection input    
    public static Scanner valueInput; // Initialize scanner to hold user value input
    public static Scanner waitScanner; // Initialize scanner used to pause the program

    
    public static void main(String args[])
    {
        int exit = 0; // This is used to indicate to the program whether the user would like to exit or not
        do // since it needs to execute at least once
        {   
            int calcSelection = 0; // This is used to house the initial selection made by the user
            double base = 0; // This houses the input base value for rectangles and triangles
            double height = 0; // This houses the input height value for rectangles and triangles
            double radius = 0; // This houses the input radius value for circles
            System.out.print("Area Calculation Program\nPlease select one of the following options:\n\n1) Calculate the area of a rectangle\n2) Calculate the area of a triangle\n3) Calculate the area of a circle\n4) Exit the program\n\nPlease input your selection: ");
            calcSelectionInput = new Scanner(System.in); // initialize the scanner for the calcSelection
            if (calcSelectionInput.hasNextInt()) // is the input value of the correct data type?
            {
                // Notice that there is no checking to see whether the input value is within the acceptable bound of 1 to 4,
                // because the switch statement lower down will do this for us. A check here would be inefficient
                calcSelection = calcSelectionInput.nextInt(); // store the input value in calcSelection
            }
            
            System.out.println(""); // just makes things look a little nicer
            
            switch (calcSelection)
            {
            // new DecimalFormat("0.##").format(variable)  <-- This facilitates a numeral output that will show no more than 2 decimal places, and only when necessary.
            // It also makes sure that if the answer is, say ".5", it will show up as "0.5" when output
            case 1: // if the user wants the program to evaluate the area of a rectangle
                base = getDoubleInput("rectangle", "base"); // get the value of the rectangle base
                height = getDoubleInput("rectangle", "height"); // get the value of the rectangle height
                System.out.print("\n\nThe area of a rectangle with base " + new DecimalFormat("0.##").format(base) + " and height " + new DecimalFormat("0.##").format(height) + " is: " + new DecimalFormat("0.##").format(calcRectangleArea(base, height)));
                break;
            case 2: // if the user wants the program to evaluate the area of a triangle
                base = getDoubleInput("triangle", "base"); // get the value of the triangle base
                height = getDoubleInput("triangle", "height"); // get the value of the triangle height
                System.out.print("\n\nThe area of a triangle with base " + new DecimalFormat("0.##").format(base) + " and height " + new DecimalFormat("0.##").format(height) + " is: " + new DecimalFormat("0.##").format(calcTriangleArea(base, height)));
                break;
            case 3: // if the user wants the program to evaluate the area of a circle
                radius = getDoubleInput("circle", "radius"); // get the value of the circle radius
                System.out.print("\n\nThe area of a circle with radius " + new DecimalFormat("0.##").format(radius) + " is: " + new DecimalFormat("0.##").format(calcCircleArea(radius)));
                break;
            case 4: // if the user wants to exit
                System.out.print("\nThank you for using the Area Calculation Program.\nHave a nice day.");
                exit = 1; // Exit? yes.
                break;
            default: // if the user chose a number outside the given bounds
                System.out.print("\nSorry, you have input an invalid value. Please try again\n\n");
            }
            if (calcSelection == 1 || calcSelection == 2 || calcSelection == 3){ pause(); } // this calls the pause method except for when the user wants to exit
        } while (exit == 0); // did the user want to exit? if so, end the loop. If not, [Energizer Bunny Here].
    }
    
    public static double getDoubleInput(String shapeName, String valueName)
    {
        /*
         * This method is passed two strings. One string identifies the shape being evaluated, and the other
         * identifies which attribute of the shape being evaluated is to be input. Both of these arguments
         * are used for nothing more than display to the user what information they need to input, but they
         * are vital pieces of information nonetheless.
         */
        double value = 0; // initialize the placeholder value
        int correctData = 1; // this aids in looping in the event that an incorrect data type is entered
        do // since it needs to execute at least once
        {
            System.out.print("Please insert the " + shapeName + "\'s " + valueName + ": "); // ask the user to input whatever it was that we wanted them to input. See above.
            valueInput = new Scanner(System.in); // initialize the placeholder input scanner
            if (valueInput.hasNextDouble()) // was the input data actually a double?
            {
                value = valueInput.nextDouble(); // if so, store it in the placeholder variable as such
                correctData = 1; // the correct data type was entered
            }
            else
            {
                System.out.print("\nYou have input an invalid value. Please try again.\n");
                correctData = 0; // the wrong data type was entered
            }
        } while (correctData == 0); // if the wrong data type was entered, give the user another chance to enter correct data
        return value; // return the value stored in the placeholder variable
    }
    
    public static double calcRectangleArea(double base, double height) // base and height arguments are passed in order to facilitate area calculation
    {
        return (base * height); // return the computed area for a rectangle (base times height)
    }

    public static double calcTriangleArea(double base, double height) // base and height arguments are passed in order to facilitate area calculation
    {
        return (0.5 * (base * height)); // return the computed area for a triangle (one half base times height)
    }

    public static double calcCircleArea(double radius) // the radius argument is passed in order to facilitate area calculation
    {
        return (Math.PI * (radius * radius)); // return the computed area for a circle (pi times radius squared)
    }
    
    public static void pause() // this is used to pause the program so the user can choose to continue when they are ready
    {
        String wait; 
        // we must define a variable, even though its not really going to store much of anything
        System.out.print("\nPress enter to continue\n\n");
        waitScanner = new Scanner(System.in); // initialize a scanner
        wait = waitScanner.nextLine(); // wait for the user to press enter before continuing
    }
}