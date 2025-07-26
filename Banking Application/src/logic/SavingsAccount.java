package logic;



public class SavingsAccount extends Account
{
	private static final double interestRate = 0.03;
	private static final int accountType = 1;
	
	public SavingsAccount(double balance, int accountNumber, String customerName) 
	{
		super(balance, accountNumber, customerName);
	}
	
	public static double getInterestRate()
	{
		return interestRate;
	}
	
	public String toCSV()
	{
		return accountType + "," + this.getBalance() + "," + this.getAccountNumber() + "," + this.getCustomerName() + "\n";
	}
	
	public String toString()
	{
		return "Savings Account || ID: " + this.getAccountNumber() + "\n";	
	}
}
