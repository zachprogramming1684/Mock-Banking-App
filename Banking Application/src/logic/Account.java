package logic;



public class Account 
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

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String toCSV()
	{
		return balance + "," + accountNumber + "," + customerName;
	}
	
	public String toString()
	{
		return "Customer Name: " + customerName + " || Account Number: " + accountNumber + " || Balance: " + balance;
	}
	
}
