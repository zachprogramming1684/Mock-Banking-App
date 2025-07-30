package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logic.BankService;

public class MakeAccountPanel extends JPanel
{
	private BankService bankService;
	private PanelSwitcher panelSwitcher;
	
	private JLabel customerName;
	private JTextField customerNameField;
	private JLabel checkingOrSavingsLabel;
	private JComboBox<String> checkingOrSavings;
	private JButton createAccount;
	private JButton returnButton;
	
	private String generalError = "An error occured, please try again.";
	private String acctNameError = "Please provide an account name.";
	private String successMessage = "Account created successfully.";
	private String errorTitle = "An error occured.";
	private JPanel popupPanel = new JPanel();
	
	public MakeAccountPanel(PanelSwitcher panelSwitcher, BankService bankService)
	{
		this.bankService = bankService;
		this.panelSwitcher = panelSwitcher;
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10,10,10,10);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		customerName = new JLabel("Please enter account name:");
		add(customerName, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		customerNameField = new JTextField(10);
		add(customerNameField, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		checkingOrSavingsLabel = new JLabel("Checking or Savings account?");
		add(checkingOrSavingsLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		String[] comboItems = {"Checking Account", "Savings Account"};
		checkingOrSavings = new JComboBox<String>(comboItems);
		checkingOrSavings.setSelectedIndex(-1);
		add(checkingOrSavings, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets = new Insets(25, 10, 10, 10);
		createAccount = new JButton("Create Account");
		createAccount.addActionListener(new createAccountActionListener());
		add(createAccount, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.insets = new Insets(75, 10, 10, 10);
		returnButton = new JButton("Go to Accounts Page");
		returnButton.addActionListener(new goBackActionListener());
		add(returnButton, gbc);
		
		
	}
	
	public class createAccountActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{

			successMessage = "Account created successfully";
			
			if(checkingOrSavings.getSelectedItem().equals("Checking Account"))
			{
				try
				{
					if(!customerNameField.getText().isEmpty())
					{
						bankService.openNewAccount(0, 0, customerNameField.getText());
						checkingOrSavings.setSelectedIndex(-1);
						customerNameField.setText("");
						JOptionPane.showMessageDialog(popupPanel, successMessage);
					}
					else
					{
						JOptionPane.showMessageDialog(popupPanel, acctNameError, errorTitle, JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(popupPanel, generalError, errorTitle, JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(checkingOrSavings.getSelectedItem().equals("Savings Account"))
			{
				try
				{
					if(!customerNameField.getText().isEmpty())
					{
						bankService.openNewAccount(1, 0, customerNameField.getText());
						checkingOrSavings.setSelectedIndex(-1);
						customerNameField.setText("");
						JOptionPane.showMessageDialog(popupPanel, successMessage);
					}
					else
					{
						JOptionPane.showMessageDialog(popupPanel, acctNameError, errorTitle, JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(Exception ex)
				{

					JOptionPane.showMessageDialog(popupPanel, generalError, errorTitle, JOptionPane.ERROR_MESSAGE);
				}
			}
			
		}
		
	}
	
	public class goBackActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			panelSwitcher.showPanel("Account Panel");
			
		}
		
	}
	
	
	
	
}
