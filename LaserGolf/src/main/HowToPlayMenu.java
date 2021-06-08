package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HowToPlayMenu extends JPanel {
	
	//Main menu's labels
	public JLabel howToPlayTitle; 
	public JLabel overview;
	public JLabel buttons;
	public JLabel startButton;
	public JLabel restartButton;
	public JLabel quitButton;
	public JLabel addMirrorButton;
	public JLabel deleteMirrorButton;
	public JLabel rotateMirrorButton;
	public JLabel objects;
	public JLabel emitter;
	public JLabel sensor;
	public JLabel mirror;
	public JLabel blackBody;
	public JLabel beamSplitter;
	public JLabel laser;
	public JLabel colorFilter;
	public JLabel explosive;
	public JButton exitToMainMenu;
	
	//How to play menu's driver and gameManager
	//public Driver driver;
	public GameManager gameManager;
	
	public HowToPlayMenu(GameManager gameManager) {
		//this.driver=driver;
		BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layout);
		setBackground(Color.GRAY);
		this.gameManager=gameManager;
		
		this.setPreferredSize(new Dimension(1200,560));
		add(Box.createVerticalStrut(5));
		
		howToPlayTitle=new JLabel("How To Play", SwingConstants.CENTER); //Game title label
		howToPlayTitle.setFont(new Font("Dialog", Font.PLAIN, 32));
		howToPlayTitle.setPreferredSize(new Dimension(1200,40));
		howToPlayTitle.setMinimumSize(new Dimension(1200,40));
		howToPlayTitle.setMaximumSize(new Dimension(1200,40));
		add(howToPlayTitle);
		add(Box.createVerticalStrut(10));
		
		overview=new JLabel(); 
		overview.setText(
				"<html>Overview: Laser Golf is a puzzle/arcade game where the"
				+ " player’s primary objective is to guide coloured lasers through a variety of complex levels from emitters "
				+ "to correspondingly coloured receivers such that all receivers are being struck by the correct colour "
				+ "simultaneously. The player can draw as many mirrors as they like to achieve this, but "
				+ "the catch is that in order to attain a higher score, the player must use fewer mirrors. Each level has a set "
				+ "“par” of mirrors which represents the primary solution, but in many cases the level can be completed with "
				+ "fewer than the par of mirrors, resulting in large score-rewards. </html>"
				);
		overview.setFont(new Font("Dialog", Font.PLAIN, 16));
		add(overview);
		add(Box.createVerticalStrut(20));
		
		buttons=new JLabel(); 
		buttons.setText("<html><b><u>Buttons</u></b></html>");
		buttons.setFont(new Font("Dialog", Font.PLAIN, 16));
		add(buttons);
		
		startButton=new JLabel();
		startButton.setText("<html>Start Button - Runs the level. If clicked during a level's run, the level is stopped.</html>");
		startButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(startButton);
		
		restartButton=new JLabel(); 
		restartButton.setText("<html>Restart Button - Restarts the level. The mirrors placed on the current level are removed. If clicked during a level's run, the level is stopped.</html>");
		restartButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(restartButton);
		
		quitButton=new JLabel(); 
		quitButton.setText("<html>Quit Button - Returns to the main menu from the current level.</html>");
		quitButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(quitButton);
		
		addMirrorButton=new JLabel(); 
		addMirrorButton.setText("<html>Add Mirror Button - Enables the ability to add a mirror to the current level. A mirror can be placed by clicking one"
				+ "once, where the first click indicates the first end point of the mirror, and clicking again, where the second click indicates the second"
				+ "end point of the mirror.</html>");
		addMirrorButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(addMirrorButton);
		
		deleteMirrorButton=new JLabel(); 
		deleteMirrorButton.setText("<html>Delete Mirror Button - Enables the ability to delete a mirror of the current level. When a mirror's center (marked by "
				+ "a black point) is clicked, the mirror is deleted.</html>");
		deleteMirrorButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(deleteMirrorButton);
		
		rotateMirrorButton=new JLabel(); 
		rotateMirrorButton.setText("<html>Rotate Mirror Button - Enables the ability to rotate a mirror of the current level. When a mirror's center (marked by "
				+ "a black point) is clicked and dragged upwards and downwards, the mirror is rotated 1 degree (counterclockwise and clockwise, respectively).</html>");
		rotateMirrorButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(rotateMirrorButton);
		add(Box.createVerticalStrut(20));
		
		objects=new JLabel(); 
		objects.setText("<html><b><u>Objects</u></b></html>");
		objects.setFont(new Font("Dialog", Font.PLAIN, 16));
		add(objects);
		
		emitter=new JLabel();
		emitter.setText("<html>Emitter - Displayed as a non-black, colored circle with an outlined smaller circle (laser) in its center. Emits a laser at its current angle."
				+ "which is indicated by the line coming out of it.</html>");
		emitter.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(emitter);
		
		sensor=new JLabel();
		sensor.setText("<html>Sensor - Displayed as a non-black, colored rectangle with a black outline. Once a laser of the same color reaches it, the next level is loaded.");
		sensor.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(sensor);
		
		mirror=new JLabel();
		mirror.setText("<html>Mirror - Displayed as a cyan rectangle. Lasers reflect off of them.");
		mirror.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(mirror);
		
		blackBody=new JLabel();
		blackBody.setText("<html>Blackbody - Displayed as a black line. When a laser collides with them, the current level run fails.");
		blackBody.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(blackBody);
		
		beamSplitter=new JLabel();
		beamSplitter.setText("<html>Beam Splitter - Displayed as a black rectangle with three different colored lasers pointing from it"
				+ "When a laser collides with them, its lasers are fired.</html>");
		beamSplitter.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(beamSplitter);
		
		laser=new JLabel();
		laser.setText("<html>Laser - Displayed as an oulinted, non-black circle. Fired from emitters and reflects off mirrors"
				+ "When a laser collides with them, its lasers are fired.</html>");
		laser.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(laser);
		
		colorFilter=new JLabel();
		colorFilter.setText("<html>Color Filter - Displayed as a non-black rectangle. When a laser moves through them, the laser's color is changed to the filter's color."
				+ "When a laser collides with them, its lasers are fired.</html>");
		colorFilter.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(colorFilter);
		
		explosive=new JLabel();
		explosive.setText("<html>Explosive - Displayed as a black rectangle with a yellow triangle as a warning symbol. When a laser collides with them, the current level run fails.");
		explosive.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(explosive);
		add(Box.createVerticalStrut(20));
		
		exitToMainMenu=new JButton("Return to Main Menu"); 
		exitToMainMenu.setFont(new Font("Dialog", Font.PLAIN, 9));
		exitToMainMenu.setPreferredSize(new Dimension(1200,40));
		exitToMainMenu.setMinimumSize(new Dimension(1200,40));
		exitToMainMenu.setMaximumSize(new Dimension(1200,40));
		add(exitToMainMenu);
		exitToMainMenu.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				gameManager.loadMainMenu();
			} });
	}
}
