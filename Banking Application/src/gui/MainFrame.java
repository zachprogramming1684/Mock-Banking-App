package gui;

import javax.swing.JFrame;

import logic.BankService;

public class MainFrame extends JFrame
{
	public MainFrame(BankService bankService)
	{
		this.setTitle("Banking Application");
		this.setSize(600,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
}
