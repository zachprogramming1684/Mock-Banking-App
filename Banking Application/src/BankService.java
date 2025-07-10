import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class BankService 
{
	 /*
	 * --- Features ---
	 * 
	 * - Save to file (DONE)
	 * - Keep track of ID numbers (DONE I THINK)
	 * 
	 * - Open a new account (DONE)
	 * - Deposit money (DONE)
	 * - Withdraw money (DONE)
	 * - Check balance for account or all accounts (DONE)
	 * - Transfer funds between accounts
	 * - Calculate interest for a month
	 */
	
	private ArrayList<Account> accounts; //declare accounts instance variable
	private int idCount = 0;
	
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
					idCount++;
				}
				else if(Integer.parseInt(tokens[0]) == 0)
				{
					Account a = new SavingsAccount(Double.parseDouble(tokens[1]), Integer.parseInt(tokens[2]), tokens[3]);
					accounts.add(a);
					idCount++;
				}
			}
			
			for(Account a : accounts)
			{
				if(a.getAccountNumber() - idCount > 0)
				{
					idCount++;
				}
			}
			
		}
		catch(FileNotFoundException e)
		{
			System.out.println("There was an error loading in account data, please try again.");
		}
	}
	
	public void openNewAccount(int choice, double balance, String customerName) throws FileNotFoundException
	{
		File f = new File("accounts.csv");
		FileOutputStream fo = new FileOutputStream(f, true);
		PrintWriter out = new PrintWriter(fo);
		
		if(choice == 0)
		{
			idCount++;
			Account a = new CheckingAccount(balance, idCount, customerName);
			accounts.add(a);
			out.print(a.toCSV());
			out.flush();
			System.out.println("Account opened successfully");
		}
		else if(choice == 1)
		{
			idCount++;
			Account a = new SavingsAccount(balance, idCount, customerName);
			accounts.add(a);
			out.print(a.toCSV());
			out.flush();
			System.out.println("Account opened successfully");
		}
		out.close();
	}
	
	public void depositMoney(int acctNum, double amount)
	{
		for(Account a : accounts)
		{
			if(acctNum == a.getAccountNumber())
			{
				if(amount > 0)
				{
					a.setBalance(a.getBalance() + amount);
					saveToFile();
					System.out.println("Deposit successful");
					break;
				}
				else
				{
					System.out.println("Please provide an amount greater than 0 to deposit.");
					break;
				}
			}
		}
	}
	
	public void withdrawMoney(int acctNum, double amount)
	{
		for(Account a : accounts)
		{
			if(acctNum == a.getAccountNumber())
			{
				if(a.getBalance() - amount >= 0)
				{
					a.setBalance(a.getBalance() - amount);
					saveToFile();
					System.out.println("Withdraw successful");
					break;
				}
				else
				{
					System.out.println("Please provide a valid amount");
					break;
				}
			}
		}
	}
	
	public void checkBalance(int acctNum)
	{
		if(acctNum != 0)
		{
			for(Account a : accounts)
			{
				if(acctNum == a.getAccountNumber())
				{
					System.out.println("Balance: $" + a.getBalance());
					break;
				}
			}
		}
		else
		{
			for(Account a : accounts)
			{
				System.out.print(a);
			}
		}
	}
	
	public void saveToFile()
	{
		try
		{
			File f = new File("accounts.csv");
			FileWriter fw = new FileWriter(f);
			PrintWriter out = new PrintWriter(fw);
			
			for(Account a : accounts)
			{
				out.print(a.toCSV());
			}
			out.flush();
			out.close();
		}
		catch(Exception e)
		{
			System.out.println("Error writing to file, please make sure the file is closed.");
		}
	}
}
