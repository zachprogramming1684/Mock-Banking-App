package logic;


/*
 * Implementation of the Account class.
 */
public class CheckingAccount extends Account
{
	private static final double interestRate = 0.01;
	private static final int accountType = 0;
	
	public CheckingAccount(double balance, int accountNumber, String customerName) 
	{
		super(balance, accountNumber, customerName);
	}
	
	@Override
	public double getInterestRate()
	{
		return interestRate;
	}

	// Comma separated format for saving to a CSV file 
	public String toCSV()
	{
		return accountType + "," + this.getBalance() + "," + this.getAccountNumber() + "," + this.getCustomerName() + "\n";
	}
	
	//String representation of a Checking Account for viewing in the GUI.
	public String toString()
	{
		return "Checking Account || ID: " + this.getAccountNumber();
	}
}
