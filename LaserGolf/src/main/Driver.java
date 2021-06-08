package main;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Driver {
	
	//Driver's frame, display, and background
	public JFrame frame;
	
	/**
	 * Used to create a driver instance
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(Driver::new);
	}
	
	/**
	 * Constructs a driver that initializes the program's frame, display and background. 
	 */
	public Driver() {
		frame=new JFrame(); //program's frame
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		GameManager mgr = new GameManager(this);
		frame.setContentPane(mgr.mainMenu);
		
		frame.pack();
		frame.setVisible(true);
		frame.setTitle("Laser Golf");	
	}
}
