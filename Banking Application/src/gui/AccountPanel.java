package gui;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.InputMismatchException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logic.Account;
import logic.BankService;

public class AccountPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private PanelSwitcher panelSwitcher; // Gives this class a panelSwitcher object to be filled by the constructor.
	private BankService bankService;
	
	private JLabel loginLabel;
	private JComboBox<Account> accountSelector;
	private DefaultComboBoxModel<Account> accountSelectorModel;
	private JLabel orDoThis;
	private JButton openAccount;
	private JButton viewSelectedAccount;
	private JButton deleteSelectedAccount;
	private JButton refresh;
	private JButton setLogin;
	private JLabel depositLabel;
	private JTextField depositField;
	private JButton depositSubmit;
	private JLabel withdrawLabel;
	private JTextField withdrawField;
	private JButton withdrawSubmit;
	
	private JPanel popupPanel;
	private String acctDetailsMessage;
	private String withdrawDepositMessage;
	
	/**
	 * Main panel for completing various account-related actions. Utilizes many action listeners for moving to different panels and completing actions.
	 * @param panelSwitcher
	 * @param bankService
	 */
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
		accountSelectorModel.addAll(bankService.getAccounts());
		accountSelector = new JComboBox<>(accountSelectorModel);
		accountSelector.setSelectedIndex(-1);
		add(accountSelector, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		viewSelectedAccount = new JButton("View Account Details");
		viewSelectedAccount.addActionListener(new ViewAcctDetailsActionListener());
		add(viewSelectedAccount, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		deleteSelectedAccount = new JButton("Delete Selected Account");
		deleteSelectedAccount.addActionListener(new DeleteSelectedAccountActionListener());
		add(deleteSelectedAccount, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		orDoThis = new JLabel("or");
		add(orDoThis, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.insets = new Insets(10, 5, 5, 5);
		openAccount = new JButton("Open New Account");
		openAccount.addActionListener(new MakeAccountActionListener());
		add(openAccount, gbc);

		gbc.gridx = 0;
		gbc.gridy = 6;
		refresh = new JButton("Refresh Accounts");
		refresh.addActionListener(new RefreshButtonActionListener());
		add(refresh, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 7;
		setLogin = new JButton("Set Login");
		setLogin.addActionListener(new SetLoginPageActionListener());
		add(setLogin, gbc);
		
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
		depositSubmit.addActionListener(new DepositActionListener());
		add(depositSubmit, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.insets = new Insets(5,150,5,5);
		withdrawLabel = new JLabel("Withdraw");
		add(withdrawLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 6;
		withdrawField = new JTextField(10);
		add(withdrawField, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 7;
		withdrawSubmit = new JButton("Submit");
		withdrawSubmit.addActionListener(new WithdrawActionListener());
		add(withdrawSubmit, gbc);
	}
	
	public class MakeAccountActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			panelSwitcher.showPanel("MakeAccountPanel");
			
		}
	}
	
	public class ViewAcctDetailsActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Account chosenAcct = (Account) accountSelector.getSelectedItem();
			String acctName = chosenAcct.getCustomerName();
			double balance = chosenAcct.getBalance();
			acctDetailsMessage = "Account Name: " + acctName + "\n Balance: $" + balance;
			JOptionPane.showMessageDialog(popupPanel, acctDetailsMessage);
		}
	}
	
	public class DeleteSelectedAccountActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Account acctToRemove = (Account) accountSelector.getSelectedItem();
			int indexToRemove = acctToRemove.getAccountNumber();
			bankService.removeAccount(indexToRemove);
			accountSelectorModel.removeAllElements();
			accountSelectorModel.addAll(bankService.getAccounts());
		}
	}
	
	public class RefreshButtonActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			accountSelectorModel.removeAllElements();
			accountSelectorModel.addAll(bankService.getAccounts());	
		}
	}
	
	private class SetLoginPageActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			panelSwitcher.showPanel("Set Login Panel");
			
		}
	}
	
	public class WithdrawActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Account acctToWithdraw = (Account) accountSelector.getSelectedItem();
			int indexToWithdraw = acctToWithdraw.getAccountNumber();
			try
			{
				boolean withdrawChecker = bankService.withdrawMoney(indexToWithdraw, Double.parseDouble(withdrawField.getText()));
				if(withdrawChecker)
				{
					withdrawDepositMessage = "Withdraw successful.";
					withdrawField.setText("");
					JOptionPane.showMessageDialog(popupPanel, withdrawDepositMessage);
				}
				else
				{
					withdrawDepositMessage = "Please input a valid amount to withdraw.";
					withdrawField.setText("");
					JOptionPane.showMessageDialog(popupPanel, withdrawDepositMessage, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			catch(NumberFormatException ex)
			{
				withdrawDepositMessage = "Please input a valid amount to withdraw.";
				withdrawField.setText("");
				JOptionPane.showMessageDialog(popupPanel, withdrawDepositMessage, "Error", JOptionPane.ERROR_MESSAGE);
			}
			accountSelectorModel.removeAllElements();
			accountSelectorModel.addAll(bankService.getAccounts());
		}
	}
	
	public class DepositActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Account acctToDeposit = (Account) accountSelector.getSelectedItem();
			int indexToDeposit = acctToDeposit.getAccountNumber();
			try
			{
				boolean depositChecker = bankService.depositMoney(indexToDeposit, Double.parseDouble(depositField.getText()));
				if(depositChecker)
				{
					withdrawDepositMessage = "Deposit successful.";
					depositField.setText("");
					JOptionPane.showMessageDialog(popupPanel, withdrawDepositMessage);
				}
				else
				{
					withdrawDepositMessage = "Please input a valid amount to deposit.";
					depositField.setText("");
					JOptionPane.showMessageDialog(popupPanel, withdrawDepositMessage, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			catch(NumberFormatException ex)
			{
				withdrawDepositMessage = "Please input a valid amount to deposit.";
				depositField.setText("");
				JOptionPane.showMessageDialog(popupPanel, withdrawDepositMessage, "Error", JOptionPane.ERROR_MESSAGE);
			}
			accountSelectorModel.removeAllElements();
			accountSelectorModel.addAll(bankService.getAccounts());
		}
	}
}
