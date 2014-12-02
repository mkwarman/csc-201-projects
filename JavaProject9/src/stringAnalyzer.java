/**
 * Analyze user input string for vowels, consonants, and other characters.
 * 
 * Matt Warman
 * Version 1.3
 */

import java.util.Scanner;
import javax.swing.JOptionPane;

public class stringAnalyzer {
	
	private static Scanner input; // Initialize a generic scanner to be used for user input
	
	public static void main (String args[]) // main method
	{
		// Declare variables
		int endProgram; // holds the value which indicates whether the user wants to end the program or not
		int charNumber; // an incrementing value which aids in parsing through the user input string character by character.
		int numberOfAs; // holds the number of times the character "a" or "A" appears in the user input string
		int numberOfEs; // holds the number of times the character "e" or "E" appears in the user input string
		int numberOfIs; // holds the number of times the character "i" or "I" appears in the user input string
		int numberOfOs; // holds the number of times the character "o" or "O" appears in the user input string
		int numberOfUs; // holds the number of times the character "u" or "U" appears in the user input string
		int numberOfConsonants; // holds the number of times that a consonant appears in the user input string
		int numberOfOthers; // holds the number of times that a character other than a vowel or a consonant appears in the user input string
		
		do
		{
			// set all counting variables to an initial value of 0
			charNumber = numberOfAs = numberOfEs = numberOfIs = numberOfOs = numberOfUs = numberOfConsonants = numberOfOthers = 0;
			
			String userInput; // holds input from the user
			System.out.print("Please enter a sentence to analyze: "); // print prompt for the user
			input = new Scanner (System.in); // receive input from the user
			userInput = input.nextLine(); // store the input line in the userInput variable for parsing
			
			while (charNumber < userInput.length()) // run the following code for the entire length of the string
			{
				switch(userInput.charAt(charNumber)) // evaluate the character at the current location in the string
				{
				case 'a': // detect "a"s
				case 'A':
					numberOfAs++; // Increment when an "a" or "A" is detected
					break;
				case 'e': // detect "e"s
				case 'E':
					numberOfEs++; // Increment when an "e" or "E" is detected
					break;
				case 'i': // detect "i"s
				case 'I':
					numberOfIs++; // Increment when an "i" or "I" is detected
					break;
				case 'o': // detect "o"s
				case 'O':
					numberOfOs++; // Increment when an "o" or "O" is detected
					break;
				case 'u': // detect "u"s
				case 'U':
					numberOfUs++; // Increment when an "u" or "U" is detected
					break;
				case 'b': // d
				case 'B': // e
				case 'c': // t
				case 'C': // e
				case 'd': // c
				case 'D': // t
				case 'f': // 
				case 'F': // a
				case 'g': // l
				case 'G': // l
				case 'h': // 
				case 'H': // t
				case 'j': // h
				case 'J': // e
				case 'k': // 
				case 'K': // c
				case 'l': // o
				case 'L': // n
				case 'm': // s
				case 'M': // o
				case 'n': // n
				case 'N': // a
				case 'p': // n
				case 'P': // t
				case 'q': // s
				case 'Q':
				case 'r':
				case 'R':
				case 's':
				case 'S':
				case 't':
				case 'T':
				case 'v':
				case 'V':
				case 'w':
				case 'W':
				case 'x':
				case 'X':
				case 'y':
				case 'Y':
				case 'z':
				case 'Z':
					numberOfConsonants++; // Increment when a consonant is detected
					break;
				default: // all other characters
					numberOfOthers++; // Increment when none of the other types of values are detected
				}
				charNumber++; // move forward to the next character location
			}
			
			// print computed information
			System.out.print("Number of a's: " + numberOfAs + "\nNumber of e's: " + numberOfEs + "\nNumber of i's: "
					+ numberOfIs + "\nNumber of o's: " + numberOfOs + "\nNumber of u's: " + numberOfUs + "\nNumber of consonants: "
					+ numberOfConsonants + "\nNumber of other characters: " + numberOfOthers + "\nThe length of the string is: "
					+ userInput.length());
	    	
			// prompt the user as to whether or not they would like to continue
			endProgram = JOptionPane.showConfirmDialog(null,"Would you like to analyze another sentence?", "Contine?", JOptionPane.YES_NO_OPTION);
			System.out.print("\n\n"); // Separate different instances of the program
			
		} while(endProgram == 0); // loop the program until the user elects to end it
	}
}
