package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.print.attribute.standard.JobOriginatingUserName;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logic.BankService;

public class LoginPanel extends JPanel
{
	private PanelSwitcher panelSwitcher; //gives this class a panelSwitcher object to be filled by the constructor
	private BankService bankService;
	
	private JTextField usernameBox;
	private JTextField passwordBox;
	
	public LoginPanel(PanelSwitcher panelSwitcher, BankService bankService)
	{
		this.panelSwitcher = panelSwitcher; //panelSwitcher object is passed into the private field from the constructor so that this class itself can switch panels
		this.bankService = bankService;
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,5,10,10);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		JLabel loginLabal = new JLabel("Please login");
		add(loginLabal, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		JLabel username = new JLabel("Username:");
		add(username, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		usernameBox = new JTextField(10);
		add(usernameBox, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		JLabel password = new JLabel("Password:");
		add(password, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		passwordBox = new JTextField(10);
		add(passwordBox, gbc);
		
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 3;
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new MyActionListener());
		add(submitButton, gbc);
		
		
	}
	
	public class MyActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			boolean goodLogin = bankService.checkLogin(usernameBox.getText(), passwordBox.getText());
			
			if(goodLogin == true)
			{
				panelSwitcher.showPanel("Account Panel");
				System.out.println("It works");
			}
			else
			{
				System.out.println("it works 2");

			}
			
		}
		
	}
}


