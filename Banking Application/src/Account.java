
public class Account 
{
	private double balance;
	private int accountNumber;
	private String customerName;
	
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
	
}
