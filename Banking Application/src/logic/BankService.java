package logic;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BankService 
{
	private ArrayList<Account> accounts; // Declare accounts ArrayList.
	private int idCount = 0; // ID count starts at zero and is incremented when accounts are loaded in or created.
	private String username;
	private String password;
	
	public BankService() // Initialize accounts variable in the constructor.
	{
		this.accounts = new ArrayList<Account>();
	}
	
	/**
	 * Loads accounts from a CSV file. If no file is found, this method will create one so that new accounts can be opened
	 * and saved to it.
	 * @throws FileNotFoundException
	 */
	public void loadAccounts() throws FileNotFoundException
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
		
		/*
		 * Quick for loop to keep track of ID numbers for accounts. It works quite well.
		 */
		for(Account a : accounts)
		{
			if(a.getAccountNumber() - idCount > 0)
			{
				idCount++;
			}
		}
		in.close();
	}
	
	/**
	 * Method used to check the login information given by the user.
	 * This is a simple check to allow entry only if the user given information matches what is saved in the bankService object.
	 * @param username
	 * @param password
	 * @return
	 */
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
	
	/**
	 * This method is used to store a custom login from the user. This creates a new CSV file if one does not already exist and stores the username and password there.
	 * @param username
	 * @param password
	 * @throws IOException
	 */
	public void storeLogin(String username, String password) throws IOException
	{
		File f = new File("login_info.csv");
		FileWriter fw = new FileWriter(f);
		PrintWriter out = new PrintWriter(fw);
		out.print(username + "," + password);
		out.flush();
		out.close();
	}
	
	/**
	 * This method loads login information from the previously created CSV file. If there is not information to be loaded, the login is set to default values.
	 * @throws FileNotFoundException
	 */
	public void loadLogin() throws FileNotFoundException
	{
		File f = new File("login_info.csv");
		FileOutputStream fo = new FileOutputStream(f, true);
		Scanner in = new Scanner(f);
		if(!in.hasNextLine())
		{
			setUsername("guest");
			setPassword("1234");
		}
		else
		{
			String line = in.nextLine();
			String[] tokens = line.split(",");
			setUsername(tokens[0]);
			setPassword(tokens[1]);
		}
	}
	
	/**
	 * This method opens a new account and saves it to a file directly after creation. This is to avoid data loss when manipulating accounts.
	 * @param choice (checking or savings account)
	 * @param balance (starting balance, always zero in the context of this program)
	 * @param customerName (customer or account name for the account to be opened)
	 * @throws FileNotFoundException
	 */
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
		}
		else if(choice == 1) // 1 is for savings account
		{
			idCount++;
			Account a = new SavingsAccount(balance, idCount, customerName);
			accounts.add(a);
			out.print(a.toCSV());
			out.flush();
		}
		out.close();
	}
	
	/**
	 * Simply removes an account from the ArrayList and furthermore CSV file.
	 * @param acctNum
	 */
	public void removeAccount(int acctNum)
	{
		for(Account a : accounts)
		{
			if(a.getAccountNumber() == acctNum)
			{
				accounts.remove(a);
				break;
			}
		}
		saveToFile();
	}
	
	/**
	 * Deposits money into a given account and saves changes to the CSV file.
	 * @param acctNum
	 * @param amount
	 * @return
	 */
	public boolean depositMoney(int acctNum, double amount)
	{
		for(Account a : accounts)
		{
			if(acctNum == a.getAccountNumber())
			{
				if(amount > 0)
				{
					a.deposit(amount);
					saveToFile();
					return true;
				}
				else
				{
					return false;
				}
			}
		}
		return false;
	}
	
	/**
	 * Withdraws money from a given account and saves changes to the CSV file.
	 * @param acctNum
	 * @param amount
	 * @return
	 */
	public boolean withdrawMoney(int acctNum, double amount)
	{
		for(Account a : accounts)
		{
			if(acctNum == a.getAccountNumber())
			{
				if(a.getBalance() - amount >= 0 && amount <= a.getBalance())
				{
					a.withdraw(amount);
					saveToFile();
					return true;
				}
				else
				{
					return false;
				}
			}
		}
		return false;
	}
	
	/**
	 * Calculates interest that will be earned from a given account over a period of time.
	 * @param acctId
	 * @param timePeriod
	 * @return
	 */
	public double calculateInterest(int acctId, double timePeriod) // TODO: Finish this
	{
		Account account = null;
		double interestRate;
		double interestAmount;
		
		for(Account a : accounts)
		{
			if(a.getAccountNumber() == acctId)
			{
				account = a;
				break;
			}
		}
		
		interestRate = account.getInterestRate();
		
		return 0;
	}
	
	/**
	 * Helpful method for saving changes made to accounts to the CSV file. This method prevents data loss by providing a universal way to save changes to the file directly after they are made.
	 */
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
			JOptionPane.showMessageDialog(new JPanel(), "There was an error saving data, please make sure accounts.csv is closed.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	public ArrayList<Account> getAccounts()
	{
		return accounts;
	}
}
