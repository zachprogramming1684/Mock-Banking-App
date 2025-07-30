package gui;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	
	private JLabel loginLabel;
	private JComboBox<Account> accountSelector;
	private DefaultComboBoxModel<Account> accountSelectorModel;
	private JLabel orDoThis;
	private JButton openAccount;
	private JLabel depositLabel;
	private JTextField depositField;
	private JButton depositSubmit;
	private JLabel withdrawLabel;
	private JTextField withdrawField;
	private JButton withdrawSubmit;
	
	public AccountPanel(PanelSwitcher panelSwitcher, BankService bankService)
	{
		this.panelSwitcher = panelSwitcher;
		this.bankService = bankService;
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,5,5,5);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		loginLabel = new JLabel("Please Select an Account");
		add(loginLabel, gbc);
		
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
		orDoThis = new JLabel("or");
		add(orDoThis, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.insets = new Insets(10, 5, 5, 5);
		openAccount = new JButton("Open new account");
		openAccount.addActionListener(new MakeAccountActionListener());
		add(openAccount, gbc);
		// NEEDS ACTION LISTENER
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(5,150,5,5);
		depositLabel = new JLabel("Deposit");
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
		// NEEDS ACTION LISTENER
		
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.insets = new Insets(5,150,5,5);
		withdrawLabel = new JLabel("Withdraw");
		add(withdrawLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 7;
		withdrawField = new JTextField(10);
		add(withdrawField, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 8;
		withdrawSubmit = new JButton("Submit");
		add(withdrawSubmit, gbc);
		// NEEDS ACTION LISTENER
		
	}
	
	public class MakeAccountActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			panelSwitcher.showPanel("MakeAccountPanel");
			
		}
		
	}
}
