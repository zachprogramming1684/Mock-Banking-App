package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logic.BankService;


public class SetLoginPanel extends JPanel
{
	private BankService bankService;
	private PanelSwitcher panelSwitcher;
	
	private JLabel usernameLabel;
	private JTextField usernameField;
	private JLabel passwordLabel;
	private JTextField passwordField;
	private JButton submitButton;
	private JButton goBackButton;
	
	private JPanel popupPanel;
	
	public SetLoginPanel(PanelSwitcher panelSwitcher, BankService bankService)
	{
		this.panelSwitcher = panelSwitcher;
		this.bankService = bankService;
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,5,5,5);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		usernameLabel = new JLabel("New Username:");
		add(usernameLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		usernameField = new JTextField(10);
		add(usernameField, gbc);
	
		gbc.gridx = 2;
		gbc.gridy = 0;
		passwordLabel = new JLabel("New Password:");
		add(passwordLabel, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 1;
		passwordField = new JTextField(10);
		add(passwordField, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.insets = new Insets(50,5,5,5);
		submitButton = new JButton("Submit");
		submitButton.addActionListener(new SubmitLoginActionListener());
		add(submitButton, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.insets = new Insets(5, 5, 5, 5);
		goBackButton = new JButton("Go Back");
		goBackButton.addActionListener(new GoBackButtonActionListener());
		add(goBackButton, gbc);
	}
	
	public class SubmitLoginActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			try
			{
				bankService.storeLogin(usernameField.getText(), passwordField.getText());
				JOptionPane.showMessageDialog(popupPanel, "Username and Password set successfully.");
				panelSwitcher.showPanel("Account Panel");
			}
			catch(IOException ex)
			{
				JOptionPane.showMessageDialog(popupPanel, "Error", "There was an error storing login information. Please try again.", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public class GoBackButtonActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			panelSwitcher.showPanel("Account Panel");
		}
	}
}
