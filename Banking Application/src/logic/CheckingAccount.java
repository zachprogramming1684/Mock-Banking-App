package logic;



public class CheckingAccount extends Account
{

	private static final double interestRate = 0.01;
	private static final int accountType = 0;
	
	public CheckingAccount(double balance, int accountNumber, String customerName) 
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
		return "Checking Account || ID: " + this.getAccountNumber() + "\n";
	}
}
