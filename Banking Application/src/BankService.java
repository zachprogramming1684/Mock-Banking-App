import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class BankService 
{
	 /*
	 * --- Features ---
	 * 
	 * - Open a new account (DONE)
	 * - Deposit money
	 * - Withdraw money
	 * - Check balance for account
	 * - Transfer funds between accounts
	 * - Calculate interest for a month
	 */
	
	private ArrayList<Account> accounts; //declare accounts instance variable
	
	public BankService() //initialize accounts variable in the constructor
	{
		this.accounts = new ArrayList<Account>();
	}
	
	public void loadAccounts()
	{
		try
		{
			File f = new File("accounts.csv");
			FileOutputStream fo = new FileOutputStream(f, true);
			Scanner in = new Scanner(f);
			while (in.hasNextLine())
			{
				String line = in.nextLine();
				String[] tokens = line.split(",");
				if(Integer.parseInt(tokens[0]) == 0)
				{
					Account a = new CheckingAccount(Double.parseDouble(tokens[1]), Integer.parseInt(tokens[2]), tokens[3]);
					accounts.add(a);
				}
				else if(Integer.parseInt(tokens[0]) == 0)
				{
					Account a = new SavingsAccount(Double.parseDouble(tokens[1]), Integer.parseInt(tokens[2]), tokens[3]);
					accounts.add(a);
				}
			}
			
		}
		catch(FileNotFoundException e)
		{
			System.out.println("There was an error loading in account data, please try again.");
		}
	}
	
	public void openNewAccount(int choice, double balance, int accountNumber, String customerName) throws FileNotFoundException
	{
		File f = new File("accounts.csv");
		FileOutputStream fo = new FileOutputStream(f, true);
		PrintWriter out = new PrintWriter(fo);
		
		if(choice == 0)
		{
			Account a = new CheckingAccount(balance, accountNumber, customerName);
			accounts.add(a);
			out.print(a.toCSV());
			out.flush();
			System.out.println("Account opened successfully");
		}
		else if(choice == 1)
		{
			Account a = new SavingsAccount(balance, accountNumber, customerName);
			accounts.add(a);
			out.print(a.toCSV());
			out.flush();
			System.out.println("Account opened sucessfully");
		}
		out.close();
	}
}
