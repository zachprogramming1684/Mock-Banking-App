package gui;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import logic.BankService;

public class AccountPanel extends JPanel
{
	private PanelSwitcher panelSwitcher; //gives this class a panelSwitcher object to be filled by the constructor
	private BankService bankService;
	
	public AccountPanel(PanelSwitcher panelSwitcher, BankService bankService)
	{
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,5,10,10);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		JLabel loginLabal = new JLabel("this is a test");
		add(loginLabal, gbc);
	}
}
