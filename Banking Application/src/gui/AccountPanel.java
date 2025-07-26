package gui;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logic.Account;
import logic.BankService;

public class AccountPanel extends JPanel
{
	private PanelSwitcher panelSwitcher; //gives this class a panelSwitcher object to be filled by the constructor
	private BankService bankService;
	
	private JComboBox<Account> accountSelector;
	private DefaultComboBoxModel<Account> accountSelectorModel;
	private JButton openAccount;
	private JTextField depositField;
	private JButton depositSubmit;
	
	public AccountPanel(PanelSwitcher panelSwitcher, BankService bankService)
	{
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,5,5,5);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel loginLabal = new JLabel("Please Select an Account");
		add(loginLabal, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		accountSelectorModel = new DefaultComboBoxModel<Account>();
		for(Account a : bankService.getAccounts())
		{
			accountSelectorModel.addElement(a);
		}
		accountSelector = new JComboBox<>(accountSelectorModel);
		accountSelector.setSelectedIndex(-1);
		add(accountSelector, gbc);
		
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		JLabel orDoThis = new JLabel("or");
		add(orDoThis, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.insets = new Insets(10, 5, 5, 5);
		openAccount = new JButton("Open new account");
		add(openAccount, gbc);
		// NEEDS ACTION LISTENER
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(5,150,5,5);
		JLabel depositLabel = new JLabel("Deposit");
		add(depositLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		depositField = new JTextField(10);
		add(depositField, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.insets = new Insets(5,150,5,5);
		depositSubmit = new JButton("Submit");
		add(depositSubmit, gbc);
		
	}
}
