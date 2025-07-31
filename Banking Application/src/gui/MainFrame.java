package gui;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import logic.BankService;

public class MainFrame extends JFrame implements PanelSwitcher
{
	private JPanel cardPanel; // Panel that holds all the cards
	private CardLayout cardLayout; // Card layout manager
	
	
	
	public MainFrame(BankService bankService)
	{
		setTitle("Banking Application");
		setSize(600,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // Centers the window
		setResizable(false);
		
		cardPanel = new JPanel();
		cardLayout = new CardLayout();
		cardPanel.setLayout(cardLayout);
		
		cardPanel.add(new LoginPanel(this, bankService), "Login Panel");
		cardPanel.add(new AccountPanel(this, bankService), "Account Panel");
		cardPanel.add(new MakeAccountPanel(this, bankService), "MakeAccountPanel");
		
		add(cardPanel);
		setVisible(true);
	}

	@Override
	public void showPanel(String panelName) 
	{
		cardLayout.show(cardPanel, panelName);
		
	}
}
