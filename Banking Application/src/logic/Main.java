package logic;

import java.io.FileNotFoundException;
import gui.MainFrame;

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
		bankService.setUsername("test");
		bankService.setPassword("test");
		MainFrame frame = new MainFrame(bankService);
		
		
		
		/*
		BankService bankService = new BankService();
		bankService.loadAccounts();
		
		bankService.openNewAccount(0, 200,"zachy123");
		bankService.openNewAccount(0, 400,"zachy5345");
		bankService.openNewAccount(1, 600,"zachy565");
		bankService.openNewAccount(1, 800,"zachy878");
		bankService.openNewAccount(0, 1000,"zachy67867"); 
		bankService.depositMoney(1, 34);
		bankService.depositMoney(3, 589);
		bankService.withdrawMoney(5, 999);
		bankService.transferFunds(2, 4, 400);
		bankService.depositMoney(1, 100000);
		bankService.withdrawMoney(2, 1);
		bankService.checkBalance(0);
		*/
		
	}

}
