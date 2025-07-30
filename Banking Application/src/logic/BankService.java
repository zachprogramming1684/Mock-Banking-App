package logic;


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
	private String username;
	private String password;
	
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
				else if(Integer.parseInt(tokens[0]) == 1)
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
	
	public boolean checkLogin(String username, String password)
	{
		if(getUsername().equals(username) && getPassword().equals(password))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void openNewAccount(int choice, double balance, String customerName) throws FileNotFoundException
	{
		File f = new File("accounts.csv");
		FileOutputStream fo = new FileOutputStream(f, true);
		PrintWriter out = new PrintWriter(fo);
		
		if(choice == 0) // 0 is for checking account
		{
			idCount++;
			Account a = new CheckingAccount(balance, idCount, customerName);
			accounts.add(a);
			out.print(a.toCSV());
			out.flush();
			System.out.println("Checking Account opened successfully");
		}
		else if(choice == 1) // 1 is for savings account
		{
			idCount++;
			Account a = new SavingsAccount(balance, idCount, customerName);
			accounts.add(a);
			out.print(a.toCSV());
			out.flush();
			System.out.println("Savings Account opened successfully");
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
				if(a.getBalance() - amount >= 0 && amount <= a.getBalance())
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
	
	public void transferFunds(int acct1Num, int acct2Num, double amount)
	{
		Account acct1 = null;
		Account acct2 = null;
		
		for(Account a : accounts)
		{
			if(acct1Num == a.getAccountNumber())
			{
				acct1 = a;
			}
			else if (acct2Num == a.getAccountNumber())
			{
				acct2 = a;
			}	
		}
		
		if(acct1 != null && acct2 != null)
		{
			 if(acct1.getBalance() - amount >= 0 && amount <= acct1.getBalance())
			 {
				 acct1.setBalance(acct1.getBalance() - amount);
				 acct2.setBalance(acct2.getBalance() + amount);
				 saveToFile();
				 System.out.println("Transfer successfull.");
			 }
		}
		else
		{
			System.out.println("Please provide valid account numbers.");
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public ArrayList<Account> getAccounts()
	{
		return accounts;
	}


}
