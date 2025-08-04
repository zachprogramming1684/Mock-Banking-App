package logic;


/*
 * This is an abstract class that outlines the blueprint for an Account object.
 */
public abstract class Account 
{
	private double balance;
	private int accountNumber;
	private String customerName;
	
	public Account(double balance, int accountNumber, String customerName)
	{
		this.balance = balance;
		this.accountNumber = accountNumber;
		this.customerName = customerName;
	}
	
	public void deposit(double amount)
	{
		setBalance(getBalance() + amount);
	}
	
	public void withdraw(double amount)
	{
		setBalance(getBalance() - amount);
	}

	public double getBalance() 
	{
		return balance;
	}

	public void setBalance(double balance) 
	{
		this.balance = balance;
	}

	public int getAccountNumber() 
	{
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) 
	{
		this.accountNumber = accountNumber;
	}

	public String getCustomerName() 
	{
		return customerName;
	}

	public void setCustomerName(String customerName) 
	{
		this.customerName = customerName;
	}
	
	// Comma separated format for saving to a CSV file.
	public String toCSV()
	{
		return balance + "," + accountNumber + "," + customerName;
	}
	
	//String representation of an Account for viewing in the GUI.
	public String toString()
	{
		return "Customer Name: " + customerName + " || Account Number: " + accountNumber + " || Balance: " + balance;
	}

	public double getInterestRate() 
	{
		return 0;
	}
	
}
