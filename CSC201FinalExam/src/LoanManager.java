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
 * 1.2
 */

public class LoanManager {
	
	static Scanner input = new Scanner(System.in);
	static File savedInfo = new File("SavedInfo.txt");
	static List<Loan> loans = new ArrayList<Loan>();
	
	public static void main(String args[])
	{
		if(!loadFile())
		{
			System.out.print("\nPlease input a loan to access the main menu.\n");
			createNewAccount();
		}
		mainMenu();
		save();
	}
	
	public static void mainMenu()
	{
		while(true)
		{
			if (loans.size() == 0)
			{
				System.out.print("\nPlease input a loan to access the main menu.\n");
				createNewAccount();
			}
			System.out.print("Personal Loan Management System\nMain Menu\n\n          Please select an Option:\n");
			for(int i = 0; i < loans.size(); i++)
			{
				System.out.print("\n          " + (i + 1) + ". " + loans.get(i).getName());
			}
			System.out.print("\n\n          A. Add a Loan\n          E. Exit\n\nInput selection here: ");
			
			if(input.hasNextInt())
			{
				int selection = (input.nextInt() - 1);
				if (selection < loans.size())
				{
					accessLoan(selection);
				}
				else
				{
					System.out.print("\n\nSorry, you have entered an invalid selection.\nPlease try again.\n\n\n");
				}
			}
			else
			{
				switch(input.next())
				{
				case "a":
				case "A":
					createNewAccount();
					break;
				case "e":
				case "E":
					save();
				default:
					System.out.print("\n\nSorry, you have entered an invalid selection.\nPlease try again.\n\n\n");
				}
			}
		}
	}
	
	public static void accessLoan(int loanNumber)
	{
		Boolean returnToMain = false;
		do
		{
			System.out.print("\n\nPersonal Loan Management System\n" + loans.get(loanNumber).getName()
					+ " Menu\n\n          Please select an Option:\n\n          1. See Loan information"
					+ "\n          2. Make a Payment\n          3. Change Loan Name\n          4. Change Interest Rate"
					+ "\n          5. Delete Loan: " + loans.get(loanNumber).getName() + "\n\n          R. Return to Main Menu\n\nInput selection here: ");
			
			if(input.hasNextInt())
			{
				switch(input.nextInt())
				{
				case 1: 
					System.out.print("\n\n" + loans.get(loanNumber).toString());
					break;
				case 2:
					makePayment(loanNumber);
					break;
				case 3:
					loans.get(loanNumber).setName(getString("\n\nPlease input the new name for loan " + loans.get(loanNumber).getName() + ": "));
					break;
				case 4:
					loans.get(loanNumber).setInterest(getDoubleInput("\nPlease input the new annual percent interest for loan " + loans.get(loanNumber).getName() + ": "));
					break;
				case 5:
					deleteLoan(loanNumber);
					System.out.print("\n\n");
					returnToMain = true;
					break;
				default:
					System.out.print("\n\nSorry, you have entered an invalid selection.\nPlease try again.\n\n\n");
					break;
				}
			}
			else
			{
				switch(input.next())
				{
				case "r":
				case "R":
					returnToMain = true;
					break;
				default:
					System.out.print("\n\nSorry, you have entered an invalid selection.\nPlease try again.\n\n\n");
					break;
				}
			}
		} while(returnToMain == false);
	}
	
	public static void deleteLoan(int loanNumber)
	{
		String name = loans.get(loanNumber).getName();
		String confirm = getString("\n\nAre you sure you want to delete " + name + "?"
				+ "\nThis operation cannot be reversed.\n\nIf you still would like to delete "
				+ name + " please type \"delete\".\nTo cancel, press enter: ");
		
		if(confirm.equalsIgnoreCase("delete"))
		{
			loans.remove(loanNumber);
			System.out.print(name + " deleted.");
		}
		else
		{
			System.out.print("Deletion canceled, no changes made.");
		}
		
	}
	
	public static void makePayment(int loanNumber)
	{
		Boolean paymentSuccessful = true;
		do
		{
			System.out.print("\n\nThe current monthly payment is: " + new DecimalFormat("$###,###,###,##0.00").format(loans.get(loanNumber).monthlyPayment()) + "\n");
			double amount = getDoubleInput("Please enter the amount you wish to pay today: ");
			if ((amount + 0.009) < loans.get(loanNumber).monthlyPayment())
			{
				System.out.print("\n\nPlease enter a payment equal to or greater than the current monthly payment.\n");
				paymentSuccessful = false;
			}
			if (amount >= loans.get(loanNumber).getAmount())
			{
				System.out.print("\n\nThis amount is enough to pay off the remaining balance of the loan.\nThe loan will be reconciled with a final payment of "
						+ new DecimalFormat("$###,###,###,##0.00").format(loans.get(loanNumber).getAmount()) + "\n");
				pause();
				loans.get(loanNumber).setAmount(0);
				loans.get(loanNumber).setTerm(0);
			}
			else
			{
				loans.get(loanNumber).makePayment(amount);
				paymentSuccessful = true;
			}
		} while (paymentSuccessful == false);

	}
	
	public static void createNewAccount()
	{
		System.out.print("\n\n");
		Loan newLoan = new Loan(
				getString("Please input the name of the " + appendSuffix(loans.size()+1) + " loan: "),
				getDoubleInput("Please input the amount of the " + appendSuffix(loans.size()+1) + " loan: "),
				getDoubleInput("Please input the term in years of the " + appendSuffix(loans.size()+1) + " loan: "),
				getDoubleInput("Please input the annual percent interest of the " + appendSuffix(loans.size()+1) + " loan: "));
		loans.add(newLoan);
		System.out.print("\n\n");
	}
	
	public static String getString(String prompt)
	{
		input = new Scanner(System.in);
		System.out.print(prompt);
		return input.nextLine();
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
	
	public static boolean loadFile()
	{
		if (savedInfo.exists() && !savedInfo.isDirectory())
		{
			String loadName;
			double loadAmount;
			double loadTerm;
			double loadInterest;
			Scanner scanner = null;
			try {
				scanner = new Scanner(Paths.get("SavedInfo.txt"), "UTF-8");
			} catch (IOException e) {
				System.out.print("Cannot Read Save File.");
				e.printStackTrace();
			}
			scanner.useDelimiter("\n");
			while (scanner.hasNext())
			{
				try{
					loadName = scanner.next();
					loadAmount = scanner.nextDouble();
					loadTerm = scanner.nextDouble();
					loadInterest = scanner.nextDouble();
					Loan loadLoad = new Loan(loadName, loadAmount, loadTerm, loadInterest);
					loans.add(loadLoad);
				}
				catch (Exception e)
				{
					System.out.print("Save data is corrupt.\n\n");
					break;
				}

			}
			scanner.close();
			return true;
		}
		else
		{
			return false;
		}
	}
	
    public static void pause() // this is used to pause the program so the user can choose to continue when they are ready
    {
        System.out.print("\nPress enter to continue");
        input = new Scanner(System.in); // initialize a scanner
        if (input.hasNextLine()){}; // wait for the user to press enter before continuing
    }
	
	public static void save()
	{
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("SavedInfo.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			System.out.print("\n\nCould not create save file.\nPlease check folder write permissions and try again.\n\n");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.out.print("\n\nUTF-8 is unsupported in this environment.\nPlease try again where UTF-8 is supported.\n\n");
			e.printStackTrace();
		}
		
		for(int i = 0; i < loans.size(); i++)
		{
			writer.print(loans.get(i).getName() + "\n");
			writer.print(loans.get(i).getAmount() + "\n");
			writer.print(loans.get(i).getTerm() + "\n");
			writer.print(loans.get(i).getInterest());
			if (i < (loans.size() - 1))
			{
				writer.print("\n");
			}
		}
		System.out.print("\n\nAll changes saved successfully. Thank you for using this loan management program.\nHave a great christmas!");
		writer.close();
		input.close();
		System.exit(0);
	}
}

