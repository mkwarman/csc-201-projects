
/**
 * User inputs first name, last name, student ID, and class name.
 * 
 * Matt Warman
 * Version 1.1
 */

import java.util.Scanner; //for use with scanner input
import javax.swing.JOptionPane; //for dialog box output

public class MyInformation
{
    public static void main(String args[])
    {
        String firstName, lastName, studentID, className; //create variables for information to be input
        
        Scanner input = new Scanner(System.in); //tell compiler that we're going to take in input
        
        System.out.print("Enter your First Name: ");
        firstName = input.nextLine(); //store input information in variable firstName
        
        System.out.print("Enter your Last Name: ");
        lastName = input.nextLine(); //store input information in variable lastName
        
        System.out.print("Enter your Student ID: ");
        studentID = input.nextLine(); //store input information in variable studentID
        
        System.out.print("Enter your Class Name: ");
        className = input.nextLine(); //store input information in variable className
        
        //Print out input information in sentance form to console
        System.out.print("Hello, " + firstName + " " + lastName + "!\nYour student ID is: " + studentID + "\nYour class " + className + " is an excellent choice!");
        
        //Print out input information in sentance form to a dialog box
        JOptionPane.showMessageDialog (null, "Hello, " + firstName + " " + lastName + "!\nYour student ID is: " + studentID + "\nYour class " + className + " is an excellent choice!" ); 
    }        
}
