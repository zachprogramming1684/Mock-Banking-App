package logic;

import java.io.FileNotFoundException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gui.MainFrame;

public class Main 
{
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
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(new JPanel(), "An unknown error occured.", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}	
	}
}
