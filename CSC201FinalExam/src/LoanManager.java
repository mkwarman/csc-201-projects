import java.io.*;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Loan Management Program.
 * 
 * Matt Warman 
 * 1.7
 */

public class LoanManager {
	
	static Scanner input = new Scanner(System.in); // initialize a scanner to be reused
	static File savedInfo = new File("SavedInfo.txt"); // initialize save file location
	static List<Loan> loans = new ArrayList<Loan>(); // initialize an array list of Loan objects
	
	public static void main(String args[])
	{
		if(!loadFile()) // if loadfFile() indicates that there is not a save file present to load from
		{
			System.out.print("\nPlease input a loan to access the main menu.\n");
			createNewAccount(); // create a new account since none area available yet
		}
		mainMenu(); // initialize the main menu
		save(); // after the user is finished making changes, save all information
		exit(); // exit the program
	}
	
	public static void mainMenu() // generate the main menu, wait for instruction, and continue as appropriate
	{
		while(true) // the main menu should always be displayed until the user elects to end the program
		{
			if (loans.size() == 0) // if there are no loans, prompt the user to create one
			{
				System.out.print("\nPlease input a loan to access the main menu.\n");
				createNewAccount(); // create a new loan
			}
			System.out.print("Personal Loan Management System\nMain Menu\n\n          Please select an Option:\n"); 
			for(int i = 0; i < loans.size(); i++) // list all current loans
			{
				System.out.print("\n          " + (i + 1) + ". " + loans.get(i).getName()); // list the index number and name of the loan
			}
			System.out.print("\n\n          A. Add a Loan\n          S. Save changes\n          E. Save and Exit\n\nInput selection here: ");
			
			if(input.hasNextInt()) // if the input selection is a number
			{
				int selection = (input.nextInt() - 1); // store the input number in variable selection as an index identifier starting at 0 
				if (selection < loans.size()) // if the index identifier has a match within the loans arraylist
				{
					accessLoan(selection); // access the selected loan
				}
				else // if the input selection refers to a loan that does not exist
				{
					System.out.print("\n\nSorry, you have entered an invalid selection.\nPlease try again.\n\n\n");
				}
			}
			else // if the input selection is a string, double, or anything other than an integer
			{
				switch(input.next()) // read the input selection
				{
				case "a": // if "A. Add a Loan" was selected
				case "A":
					createNewAccount(); // create a new loan
					break;
				case "s":
				case "S":
					save(); // save all data
					break;
				case "e": // if "E. Exit" was selected
				case "E":
					save(); // save all data
					exit(); // exit the program
				default:
					System.out.print("\n\nSorry, you have entered an invalid selection.\nPlease try again.\n\n\n");
				}
			}
		}
	}
	
	public static void accessLoan(int loanNumber) // display options relating to a particular selected loan
	{
		Boolean returnToMain = false; // helps identify when the user wants to return to the main menu
		do
		{
			System.out.print("\n\nPersonal Loan Management System\n" + loans.get(loanNumber).getName()
					+ " Menu\n\n          Please select an Option:\n\n          1. See Loan information"
					+ "\n          2. Make a Payment\n          3. Change Loan Name\n          4. Change Interest Rate"
					+ "\n          5. Delete Loan: " + loans.get(loanNumber).getName() + "\n\n          R. Return to Main Menu\n\nInput selection here: ");
			
			if(input.hasNextInt()) // if the input selection is an integer
			{
				switch(input.nextInt()) // read the input integer
				{
				case 1: // if "1. See Loan information" was selected
					System.out.print("\n\n" + loans.get(loanNumber).toString()); // print loan information using Loan.toString()
					pause(); // wait for user acknowledgement before continuing
					break; // return to the loan menu
				case 2: // if "2. Make a Payment" was selected
					makePayment(loanNumber); // initialize the payment function
					System.out.print("Payment completed successfully.\n");
					pause();
					break; // return to the loan menu
				case 3: // if "3. Change Loan Name" was selected
					// prompt the user for a new name for the loan and set that as the loan's name
					loans.get(loanNumber).setName(getString("\n\nPlease input the new name for loan " + loans.get(loanNumber).getName() + ": "));
					System.out.print("Name changed successfully.\n");
					pause();
					break; // return to the loan menu
				case 4: // if "4. Change Interest Rate" was selected
					// prompt the user for a new annual percent interest for the loan and set that as the loan's annual percent interest
					loans.get(loanNumber).setInterest(getDoubleInput("\nPlease input the new annual percent interest for loan " + loans.get(loanNumber).getName() + ": "));
					loans.get(loanNumber).calculateMonthlyPayment(); // recalculate the monthly payment based on the new annual interest rate
					System.out.print("Annual percent interest changed successfully.\n");
					pause();
					break; // return to the loan menu
				case 5: // if "5. Delete Loan: [Loan_Name]" was selected
					deleteLoan(loanNumber); // initialize the loan deletion function
					System.out.print("\n\n");
					returnToMain = true; // end the loop to return to the main menu since the loan menu no longer exists after deleting the loan
					break; // return to the main menu
				default: // if a number selection other than those available was entered
					System.out.print("\n\nSorry, you have entered an invalid selection.\nPlease try again.\n\n\n");
					break; // return to the loan menu so the user can try to enter a valid selection
				}
			}
			else // if the input selection is a string, double, or anything other than an integer
			{
				switch(input.next()) // read the input
				{
				case "r": // if "R. Return to Main Menu" was selected
				case "R":
					System.out.print("\n\n");
					returnToMain = true; // end the loop to the main menu
					break; // return to the main menu
				default: // if any input other than an integer or "r"/"R" was entered
					System.out.print("\n\nSorry, you have entered an invalid selection.\nPlease try again.\n\n\n");
					break; // return to the loan menu so the user can try to enter a valid selection
				}
			}
		} while(returnToMain == false); // continue to loop the loan menu until the user elects to return to the main menu or the loan is deleted
	}
	
	public static void deleteLoan(int loanNumber) // function used to confirm and perform deletion of a loan instance
	{
		String name = loans.get(loanNumber).getName(); // save the name of the loan in a string since after deletion it cannot be read from the arraylist
		String confirm = getString("\n\nAre you sure you want to delete " + name + "?"
				+ "\nThis operation cannot be reversed.\n\nIf you still would like to delete "
				+ name + " please type \"delete\".\nTo cancel, press enter: "); // prompt the user to enter "delete" if they really want to delete the loan
		
		if(confirm.equalsIgnoreCase("delete")) // if the user entered "delete" exactly - regardless of case
		{
			loans.remove(loanNumber); // delete the loan entry from the arraylist
			System.out.print(name + " deleted."); // alert the user that the loan was deleted
		}
		else // if the user elected to cancel the deletion
		{
			System.out.print("Deletion canceled, no changes made."); // alert the user that deletion was cancelled
		}
		
	}
	
	public static void makePayment(int loanNumber) // allow the user to make a payment on a loan
	{
		Boolean paymentSuccessful = false; // helps identify whether the payment was successful or should be re-attempted
		do
		{
			System.out.print("\n\nThe current monthly payment is: " + new DecimalFormat("$###,###,###,##0.00").format(loans.get(loanNumber).getMonthlyPayment()) + "\n"); // print the current monthly payment
			double amount = getDoubleInput("Please enter the amount you wish to pay today: "); // prompt the user for the amount they wish to pay
			if ((amount + 0.005) < loans.get(loanNumber).getMonthlyPayment()) // if the amount the user wishes to pay is less than the required monthly payment. Amount adjusted to accommodate for monthlyPayment() rounding.
			{
				System.out.print("\n\nPlease enter a payment equal to or greater than the current monthly payment.\n");
				paymentSuccessful = false; // loop the payment since the user did not enter an acceptable payment amount
			}
			else if ((amount + 0.005) >= (loans.get(loanNumber).getAmount() + loans.get(loanNumber).monthlyInterest())) // if the user wishes to pay the loan off. Amount adjusted to accommodate for rounding.
			{
				System.out.print("\n\nThis amount is enough to pay off the remaining balance of the loan.\nThe loan will be reconciled with a final payment of "
						+ new DecimalFormat("$###,###,###,##0.00").format(loans.get(loanNumber).getAmount() + loans.get(loanNumber).monthlyInterest()) + "\n"); // alert the user of the final balance to be charged
				pause(); // prompt the user to press enter before continuing
				loans.get(loanNumber).setAmount(0); // zero out the loan balance
				loans.get(loanNumber).setTerm(0); // zero out the loan term
				loans.get(loanNumber).calculateMonthlyPayment(); // recalculate monthly payment now that the balance is zero. Should be 0
				paymentSuccessful = true; // report the payment as successful so the loop will exit
			}
			else // if the payment amount is an acceptable amount and less then the remaining balance of the loan
			{
				loans.get(loanNumber).makePayment(amount); // process the payment
				paymentSuccessful = true; // report the payment as successful so the loop will exit
			}
		} while (paymentSuccessful == false);

	}
	
	public static void createNewAccount() // allow the user to create a new loan account
	{
		System.out.print("\n\n");
		// Prompt the user for the loan name, loan amount, loan term (in years), and loan annual percent interest, then use it to create a new loan object called newLoan
		Loan newLoan = new Loan(
				getString("Please input the name of the " + appendSuffix(loans.size()+1) + " loan: "),
				getDoubleInput("Please input the amount of the " + appendSuffix(loans.size()+1) + " loan: "),
				getDoubleInput("Please input the term in years of the " + appendSuffix(loans.size()+1) + " loan: "),
				getDoubleInput("Please input the annual percent interest of the " + appendSuffix(loans.size()+1) + " loan: "));
		loans.add(newLoan); // store the newly created newLoan information in the loans arraylist 
		System.out.print("\n\n");
	}
	
	public static String getString(String prompt) // Facilitates an easy and reusable way to take in user string input
	{
		input = new Scanner(System.in); // prepare the input scanner
		System.out.print(prompt); // display the prompt for the user
		return input.nextLine(); // return the user input as a string
	}
	
    public static double getDoubleInput(String prompt) // facilitates an easy and reusable way to take in sanitized user input double values.
    {
        double value = 0; // initialize the placeholder value
        boolean correctData = true; // this aids in looping in the event that an incorrect data type is entered
        do // since it needs to execute at least once
        {
            System.out.print("\n" + prompt); // ask the user to input whatever it was that we wanted them to input. See above.
            input = new Scanner(System.in); // initialize the placeholder input scanner
            if (input.hasNextDouble()) // was the input data actually a double?
            {
                value = input.nextDouble(); // if so, store it in the placeholder variable as such
                if (value >= 0) // if the input value is zero or positive
                {
                	correctData = true; // the correct data type was entered
                }
                else // if the input value is negative
                {
                	System.out.print("\nYou cannot input a negitive value.\nPlease try again.\n");
                	correctData = false; // incorrect data was input
                }
            }
            else
            {
                System.out.print("\nYou have input an invalid value. Please try again.\n");
                correctData = false; // the wrong data type was entered
            }
        } while (correctData == false); // if the wrong data type was entered, give the user another chance to enter correct data
        return value; // return the value stored in the placeholder variable
    }
	
	public static String appendSuffix(int numberForSuffix) // This function computes the ordinal suffix for an input value
	{		   
		String[] suffixes = {"th","st","nd","rd","th","th","th","th","th","th"};
		switch(numberForSuffix % 100) // helps determine if the number for suffix is 11, 12, or 13, which don't follow suffix rules very nicely
		{
		case 11:
		case 12:
		case 13:
			return "th"; // for those weird numbers
		default:
			return (numberForSuffix + suffixes[numberForSuffix % 10]); // return input number with correct suffix
		}	
	}
	
	public static boolean loadFile() // check for saved data and load it when available
	{
		if (savedInfo.exists() && !savedInfo.isDirectory()) // if the save file exists (and is not a directory)
		{
			String loadName; // initialize temporary loan name variable to store information to be loaded into the loans arraylist
			double loadAmount; // initialize temporary loan amount variable to store information to be loaded into the loans arraylist
			double loadTerm; // initialize temporary loan term variable to store information to be loaded into the loans arraylist
			double loadInterest; // initialize temporary loan annual percent interest variable to store information to be loaded into the loans arraylist
			
			Scanner scanner = null; // initialize the scanner
			
			try {
				scanner = new Scanner(Paths.get("SavedInfo.txt"), "UTF-8"); // attempt to open the save file
			}
			catch (IOException e) // if the file cannot be opened
			{
				System.out.print("Cannot Read Save File.");
				pause();
			}
			
			scanner.useDelimiter("\n"); // set new lines as the delimiter for the scanner so that a new piece of information will be read for each line

			while (scanner.hasNext()) 
			// loop while the next line has information. This will increment by four lines each loop as information is systematically loaded line by line,
			//   thereby loading every available loan correctly until there are none left.
			{
				try{
					loadName = scanner.next(); // load the first line as the loan name
					loadAmount = scanner.nextDouble(); // load the second line as the amount
					loadTerm = scanner.nextDouble(); // load the third line as the loan term
					loadInterest = scanner.nextDouble(); // load the fourth line as the loan annual percent interest
					Loan loadLoan = new Loan(loadName, loadAmount, loadTerm, loadInterest); // create a new loan based on the information read from the save file
					loans.add(loadLoan); // store the newly loaded loan in the loans arraylist
				}
				catch (Exception e) // if any of the loads are of an incorrect format, report that the save data has been corrupted
				{
					System.out.print("Save data is corrupt.\n\n");
					break;
				}
			}
			scanner.close(); // close the scanner used to load from the file
			return true; // return true indicating that a file was loaded
		}
		else
		{
			return false; // return false indicating that a file was not loaded
		}
	}
	
    public static void pause() // this is used to pause the program so the user can choose to continue when they are ready
    {
        System.out.print("\nPress enter to continue");
        input = new Scanner(System.in); // initialize a scanner
        if (input.hasNextLine()){}; // wait for the user to press enter before continuing
    }
	
	public static void save() // save data and exit the program
	{
		PrintWriter writer = null; // Initialize the writer
		try
		{
			writer = new PrintWriter("SavedInfo.txt", "UTF-8"); // attempt to initialize writing to the SavedInfo.txt file
			for(int i = 0; i < loans.size(); i++) // for every entry in the loans arraylist
			{
				writer.print(loans.get(i).getName() + "\n"); // write the current loan's name then start a new line
				writer.print(loans.get(i).getAmount() + "\n"); // write the current loan's amount then start a new line
				writer.print(loans.get(i).getTerm() + "\n"); // write the current loan's term then start a new line
				writer.print(loans.get(i).getInterest()); // write the current loan's annual percent interest
				if (i < (loans.size() - 1)) { writer.print("\n"); } // if there are still more loan objects to write to the save file, then start a new line
			}
			System.out.print("\n\nAll changes saved successfully.\n\n\n");
			writer.close(); // close the SavedInfo.txt writer
		}
		catch (FileNotFoundException e) // if the file was not found
		{
			System.out.print("\n\nCould not create save file.\nPlease check folder write permissions and try again.\n\n");
			pause();
		}
		catch (UnsupportedEncodingException e) // if the encoding is not supported
		{
			System.out.print("\n\nUTF-8 is unsupported in this environment.\nPlease try again where UTF-8 is supported.\n\n");
			pause();
		}
		catch (Exception e)
		{
			System.out.print("\n\nAn error has occurred\n\n");
			pause();
		}
	}
	
	public static void exit()
	{
		System.out.print("Thank you for using this loan management program.\nHave a great Christmas!"); // print exiting message
		input.close(); // close the input scanner
		System.exit(0); // end the program
	}
}

