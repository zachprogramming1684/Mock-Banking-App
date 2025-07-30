package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.print.attribute.standard.JobOriginatingUserName;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logic.BankService;

public class LoginPanel extends JPanel
{
	private PanelSwitcher panelSwitcher; //gives this class a panelSwitcher object to be filled by the constructor
	private BankService bankService;
	
	private JLabel loginLabel;
	private JLabel usernameLabel;
	private JTextField usernameBox;
	private JLabel passwordLabel;
	private JTextField passwordBox;
	private JButton submitButton;
	
	private String errorMessage;
	private String dialogTitle;
	private JPanel loginError;
	
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
		loginLabel = new JLabel("Please login");
		add(loginLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		usernameLabel = new JLabel("Username:");
		add(usernameLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		usernameBox = new JTextField(10);
		add(usernameBox, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		passwordLabel = new JLabel("Password:");
		add(passwordLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		passwordBox = new JTextField(10);
		add(passwordBox, gbc);
		
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 3;
		submitButton = new JButton("Submit");
		submitButton.addActionListener(new MyActionListener());
		add(submitButton, gbc);
		
		
	}
	
	public class MyActionListener implements ActionListener
	{
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
	        errorMessage = "Incorrect Password or Username";
	        dialogTitle = "Error";
	        loginError = new JPanel();
	        
			boolean goodLogin = bankService.checkLogin(usernameBox.getText(), passwordBox.getText());
			
			if(goodLogin == true)
			{
				panelSwitcher.showPanel("Account Panel");
				System.out.println("It works");
			}
			else
			{
		        JOptionPane.showMessageDialog(loginError, errorMessage, dialogTitle, JOptionPane.ERROR_MESSAGE);
				System.out.println("it works 2");

			}
			
		}
		
	}
}


