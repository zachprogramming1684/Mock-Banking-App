package gui;

import javax.swing.JFrame;

public class MainFrame extends JFrame
{
	public MainFrame()
	{
		this.setTitle("Banking Application");
		this.setSize(600,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
}
