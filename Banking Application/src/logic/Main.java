package logic;

import java.io.FileNotFoundException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
		try
		{
			BankService bankService = new BankService();
			bankService.loadAccounts();
			bankService.loadLogin();
			MainFrame frame = new MainFrame(bankService);
			frame.setVisible(true);
		}
		catch(FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(new JPanel(), "There was an error loading the program. Please make sure all data-storing files are closed.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
