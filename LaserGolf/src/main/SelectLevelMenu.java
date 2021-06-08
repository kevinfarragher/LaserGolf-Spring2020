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

public class SelectLevelMenu extends JPanel{
	
	//Select level menu's labels and buttons
	public JLabel selectLevelTitle; 
	public JButton level1;
	public JButton level2;
	public JButton level3;
	public JButton level4;
	public JButton level5;
	public JButton level6;
	public JButton level7;
	public JButton level8;
	public JButton level9;
	public JButton level10;
	public JButton level11;
	public JButton level12;
	public JButton level13;
	public JButton level14;
	public JButton level15;
	public JButton level16;
	public JButton level17;
	public JButton level18;
	public JButton exitToMainMenu;
	
	//Select level menu's driver and gameManager
	//public Driver driver;
	public GameManager gameManager;
	
	public SelectLevelMenu(GameManager gameManager) {
		//this.driver=driver;
		BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layout);
		setBackground(Color.GRAY);
		this.gameManager=gameManager;
		
		this.setPreferredSize(new Dimension(1200,640));
		add(Box.createVerticalStrut(5));
		
		selectLevelTitle=new JLabel("Select Level", SwingConstants.CENTER); //Game title label
		selectLevelTitle.setFont(new Font("Dialog", Font.PLAIN, 32));
		selectLevelTitle.setPreferredSize(new Dimension(1200,25));
		selectLevelTitle.setMinimumSize(new Dimension(1200,25));
		selectLevelTitle.setMaximumSize(new Dimension(1200,25));
		add(selectLevelTitle);
		add(Box.createVerticalStrut(10));
		
		level1=new JButton("Level 1"); 
		level1.setFont(new Font("Dialog", Font.PLAIN, 9));
		level1.setPreferredSize(new Dimension(1200,25));
		level1.setMinimumSize(new Dimension(1200,25));
		level1.setMaximumSize(new Dimension(1200,25));
		add(level1);
		add(Box.createVerticalStrut(5));
		level1.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				gameManager.loadLevel(1);
			} });
		
		level2=new JButton("Level 2"); 
		level2.setFont(new Font("Dialog", Font.PLAIN, 9));
		level2.setPreferredSize(new Dimension(1200,25));
		level2.setMinimumSize(new Dimension(1200,25));
		level2.setMaximumSize(new Dimension(1200,25));
		add(level2);
		add(Box.createVerticalStrut(5));
		level2.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				gameManager.loadLevel(2);
			} });
		
		level3=new JButton("Level 3"); 
		level3.setFont(new Font("Dialog", Font.PLAIN, 9));
		level3.setPreferredSize(new Dimension(1200,25));
		level3.setMinimumSize(new Dimension(1200,25));
		level3.setMaximumSize(new Dimension(1200,25));
		add(level3);
		add(Box.createVerticalStrut(5));
		level3.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				gameManager.loadLevel(3);
			} });
		
		level4=new JButton("Level 4"); 
		level4.setFont(new Font("Dialog", Font.PLAIN, 9));
		level4.setPreferredSize(new Dimension(1200,25));
		level4.setMinimumSize(new Dimension(1200,25));
		level4.setMaximumSize(new Dimension(1200,25));
		add(level4);
		add(Box.createVerticalStrut(5));
		level4.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				gameManager.loadLevel(4);
			} });
		
		level5=new JButton("Level 5"); 
		level5.setFont(new Font("Dialog", Font.PLAIN, 9));
		level5.setPreferredSize(new Dimension(1200,25));
		level5.setMinimumSize(new Dimension(1200,25));
		level5.setMaximumSize(new Dimension(1200,25));
		add(level5);
		add(Box.createVerticalStrut(5));
		level5.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				gameManager.loadLevel(5);
			} });
		
		level6=new JButton("Level 6"); 
		level6.setFont(new Font("Dialog", Font.PLAIN, 9));
		level6.setPreferredSize(new Dimension(1200,25));
		level6.setMinimumSize(new Dimension(1200,25));
		level6.setMaximumSize(new Dimension(1200,25));;
		add(level6);
		add(Box.createVerticalStrut(5));
		level6.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				gameManager.loadLevel(6);
			} });
		
		level7=new JButton("Level 7"); 
		level7.setFont(new Font("Dialog", Font.PLAIN, 9));
		level7.setPreferredSize(new Dimension(1200,25));
		level7.setMinimumSize(new Dimension(1200,25));
		level7.setMaximumSize(new Dimension(1200,25));
		add(level7);
		add(Box.createVerticalStrut(5));
		level7.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				gameManager.loadLevel(7);
			} });
		
		level8=new JButton("Level 8"); 
		level8.setFont(new Font("Dialog", Font.PLAIN, 9));
		level8.setPreferredSize(new Dimension(1200,25));
		level8.setMinimumSize(new Dimension(1200,25));
		level8.setMaximumSize(new Dimension(1200,25));
		add(level8);
		add(Box.createVerticalStrut(5));
		level8.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				gameManager.loadLevel(8);
			} });
		
		level9=new JButton("Level 9"); 
		level9.setFont(new Font("Dialog", Font.PLAIN, 9));
		level9.setPreferredSize(new Dimension(1200,25));
		level9.setMinimumSize(new Dimension(1200,25));
		level9.setMaximumSize(new Dimension(1200,25));
		add(level9);
		add(Box.createVerticalStrut(5));
		level9.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				gameManager.loadLevel(9);
			} });
		
		level10=new JButton("Level 10");
		level10.setFont(new Font("Dialog", Font.PLAIN, 9));
		level10.setPreferredSize(new Dimension(1200,25));
		level10.setMinimumSize(new Dimension(1200,25));
		level10.setMaximumSize(new Dimension(1200,25));
		add(level10);
		add(Box.createVerticalStrut(5));
		level10.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				gameManager.loadLevel(10);
			} });
		
		level11=new JButton("Level 11"); 
		level11.setFont(new Font("Dialog", Font.PLAIN, 9));
		level11.setPreferredSize(new Dimension(1200,25));
		level11.setMinimumSize(new Dimension(1200,25));
		level11.setMaximumSize(new Dimension(1200,25));
		add(level11);
		add(Box.createVerticalStrut(5));
		level11.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				gameManager.loadLevel(11);
			} });
		
		level12=new JButton("Level 12"); 
		level12.setFont(new Font("Dialog", Font.PLAIN, 9));
		level12.setPreferredSize(new Dimension(1200,25));
		level12.setMinimumSize(new Dimension(1200,25));
		level12.setMaximumSize(new Dimension(1200,25));
		add(level12);
		add(Box.createVerticalStrut(5));
		level12.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				gameManager.loadLevel(12);
			} });
		
		level13=new JButton("Level 13"); 
		level13.setFont(new Font("Dialog", Font.PLAIN, 9));
		level13.setPreferredSize(new Dimension(1200,25));
		level13.setMinimumSize(new Dimension(1200,25));
		level13.setMaximumSize(new Dimension(1200,25));
		add(level13);
		add(Box.createVerticalStrut(5));
		level13.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				gameManager.loadLevel(13);
			} });
		
		level14=new JButton("Level 14"); 
		level14.setFont(new Font("Dialog", Font.PLAIN, 9));
		level14.setPreferredSize(new Dimension(1200,25));
		level14.setMinimumSize(new Dimension(1200,25));
		level14.setMaximumSize(new Dimension(1200,25));
		add(level14);
		add(Box.createVerticalStrut(5));
		level9.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				gameManager.loadLevel(14);
			} });
		
		level15=new JButton("Level 15"); 
		level15.setFont(new Font("Dialog", Font.PLAIN, 9));
		level15.setPreferredSize(new Dimension(1200,25));
		level15.setMinimumSize(new Dimension(1200,25));
		level15.setMaximumSize(new Dimension(1200,25));
		add(level15);
		add(Box.createVerticalStrut(5));
		level15.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				gameManager.loadLevel(15);
			} });
		
		level16=new JButton("Level 16"); 
		level16.setFont(new Font("Dialog", Font.PLAIN, 9));
		level16.setPreferredSize(new Dimension(1200,25));
		level16.setMinimumSize(new Dimension(1200,25));
		level16.setMaximumSize(new Dimension(1200,25));
		add(level16);
		add(Box.createVerticalStrut(5));
		level16.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				gameManager.loadLevel(16);
			} });
		
		level17=new JButton("Level 17"); 
		level17.setFont(new Font("Dialog", Font.PLAIN, 9));
		level17.setPreferredSize(new Dimension(1200,25));
		level17.setMinimumSize(new Dimension(1200,25));
		level17.setMaximumSize(new Dimension(1200,25));
		add(level17);
		add(Box.createVerticalStrut(5));
		level17.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				gameManager.loadLevel(17);
			} });
		
		level18=new JButton("Level 18"); 
		level18.setFont(new Font("Dialog", Font.PLAIN, 9));
		level18.setPreferredSize(new Dimension(1200,25));
		level18.setMinimumSize(new Dimension(1200,25));
		level18.setMaximumSize(new Dimension(1200,25));
		add(level18);
		add(Box.createVerticalStrut(5));
		level18.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				gameManager.loadLevel(18);
			} });
		
		exitToMainMenu=new JButton("Return to Main Menu"); 
		exitToMainMenu.setFont(new Font("Dialog", Font.PLAIN, 9));
		exitToMainMenu.setPreferredSize(new Dimension(1200,50));
		exitToMainMenu.setMinimumSize(new Dimension(1200,50));
		exitToMainMenu.setMaximumSize(new Dimension(1200,50));
		add(exitToMainMenu);
		exitToMainMenu.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				gameManager.loadMainMenu();
			} });
	}
}
