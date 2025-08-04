package gui;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import logic.BankService;

public class MainFrame extends JFrame implements PanelSwitcher
{
	private static final long serialVersionUID = 1L;
	private JPanel cardPanel; // Panel that holds all the cards
	private CardLayout cardLayout; // Card layout manager
	
	/**
	 * Initializes a Swing frame to hold all JPanels. Uses a card layout to easily switch between panels.
	 * @param bankService (bankService object to manipulate accounts in the GUI)
	 */
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
		cardPanel.add(new SetLoginPanel(this, bankService), "Set Login Panel");
		
		add(cardPanel);
	}

	/**
	 * Implementation of PanelSwitcher interface. This method is used in all panels added to the Main Frame. This method provides a quick way to switch between panels using a String representation of that panel.
	 */
	@Override
	public void showPanel(String panelName) 
	{
		cardLayout.show(cardPanel, panelName);
		
	}
}
