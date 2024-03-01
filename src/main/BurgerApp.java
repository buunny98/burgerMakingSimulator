package main;

import javax.swing.JFrame;

//This is the main JFrame app which consists the name and the frame size of the window. 

public class BurgerApp extends JFrame{

	private static final long serialVersionUID = 1L;

	public BurgerApp(String title) {
		super(title);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.setLocation(0, 0);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BurgerPanel bpnl = new BurgerPanel(this);
		this.add(bpnl); 
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

	public static void main (String[] args){
		new BurgerApp("Tasty Burgerzzz");

	}
}