
import java.io.FileNotFoundException;

public class Main 
{
	/*
	 * --- Features ---
	 * 
	 * - Open a new account
	 * - Deposit money
	 * - Withdraw money
	 * - Check balance for account
	 * - Transfer funds between accounts
	 * - Calculate interest for a month
	 * 
	 * --- Logic flow ---
	 * 
	 * - User is prompted to either select an account or make a new one
	 * - All open accounts are displayed
	 * - Once user selects an account, they can do one of the features listed above on it
	 * - Once they are done completing an action, the user will be prompted to pick an account again 
	 * - The user can continue the program until they decide to quit
	 */
	
	public static void main(String[] args) throws FileNotFoundException 
	{
		BankService bankService = new BankService();
		bankService.loadAccounts();
		
		bankService.openNewAccount(0, 200,"zachy123");
		
		
		
		
		//bankService.depositMoney(1, 100000);
		//bankService.withdrawMoney(2, 1);
		//bankService.checkBalance(0);
		
		 
		
		
		
	}

}
