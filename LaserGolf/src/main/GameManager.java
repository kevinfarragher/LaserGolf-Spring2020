package main;

import java.util.ArrayList;

public class GameManager {
	
		//program's driver
		public Driver driver;
		
		//Game's main menu, levels, and current level
		public MainMenu mainMenu;
		public HowToPlayMenu howToPlayMenu;
		public SelectLevelMenu selectLevelMenu;
		public ArrayList<Level> levels; 
		
		public int level; //number of current level
		
		//game's  state settings
	    public boolean placingMirror;
	    public boolean deletingMirror;
	    public boolean rotatingMirror;
	    public boolean restartingLevel;
	    public boolean mirrorPositionDetermined;
	    public boolean mirrorDeletingDetermined;
	    
	    //mouse coordinates
	    public int startMouseX;
	    public int startMouseY;
	    public int prevMouseX;
	    public int prevMouseY; //used when rotating mirrors
	    public int currMouseX;
	    public int currMouseY; //used when rotating mirrors

	    /**
	     * Constructs a gameManager with a given driver. The game's main menu, levels, current level, tracking coordinates, and state settings are initialized.
	     * @param driver - Driver of the program
	     */
	    public GameManager(Driver driver)
	    {
	    	this.driver = driver;
	    	mainMenu=new MainMenu(this);
	    	howToPlayMenu=new HowToPlayMenu(this);
	    	selectLevelMenu=new SelectLevelMenu(this);
	    	levels = new ArrayList<Level>(); 
	    	
	    	levels.add(new Level1(this));
	    	levels.add(new Level2(this));
	    	levels.add(new Level3(this));
	    	levels.add(new Level4(this));
	    	levels.add(new Level5(this));
	    	levels.add(new Level6(this));
	    	levels.add(new Level7(this));
	    	levels.add(new Level8(this));
	    	levels.add(new Level9(this));
	    	levels.add(new Level10(this));
	    	levels.add(new Level11(this));
	    	levels.add(new Level12(this));
	    	levels.add(new Level13(this));
	    	levels.add(new Level14(this));
	    	levels.add(new Level15(this));
	    	levels.add(new Level16(this));
	    	levels.add(new Level17(this));
	    	levels.add(new Level18(this));
	    	level = -1;
	    	
	        
	        placingMirror = false;
	        mirrorPositionDetermined = false;
	        deletingMirror = false;
	        mirrorDeletingDetermined=false;
	        rotatingMirror = false;
	        restartingLevel=false;
	        
	        startMouseX=0;
	        startMouseY=0;
	        prevMouseX=0;
		    prevMouseY=0;
		    currMouseX=0;
		    currMouseY=0;
	    }
	    
	    /**
	     * Loads the next level in the game
	     */
	    public void loadNextLevel()
	    {
	        level++;
	        if(level == levels.size()) {
	        	level = -1; 
	        }
	        
	        driver.frame.setContentPane((level == -1)? mainMenu : levels.get(level).contentPane);
	        //driver.frame.setContentPane((level == -2)? howToPlayMenu : levels.get(level).contentPane);
	        //driver.frame.setContentPane((level == -3)? selectLevelMenu : levels.get(level).contentPane);
	        driver.frame.pack();	        
	    }

	    /**
	     * Changes game's state to allow a mirror to be added to the current level.
	     */
	    public void addMirror()
	    {
	        placingMirror = true;
	        mirrorPositionDetermined = false;
	        deletingMirror = false;
	        rotatingMirror = false;
	        mirrorDeletingDetermined=false;
	        restartingLevel=false;
//	        System.out.println("Enter add mirror mode");
	    }

	    /**
	     * Changes game's state to allow a mirror to be deleted in the current level.
	     */
	    public void deleteMirror()
	    {
	        deletingMirror = true;
	        placingMirror = false;
	        mirrorPositionDetermined = false;
	        rotatingMirror = false;
	        mirrorDeletingDetermined=false;
	        restartingLevel=false;
//	        System.out.println("Enter delete mirror mode");
	    }

	    /**
	     * Changes game's state to allow a mirror to be rotated in the current level.
	     */
	    public void rotateMirror()
	    {
	        rotatingMirror = true;
	        deletingMirror = false;
	        placingMirror = false;
	        mirrorPositionDetermined = false;
	        mirrorDeletingDetermined=false;
	        restartingLevel=false;
//	        System.out.println("Enter rotate mirror mode");
	    }

	    /**
	     * Sets level to -1 to direct user to the main menu
	     */
	    public void loadMainMenu()
	    {
	    	level=-1;
	    	driver.frame.setContentPane(mainMenu);
	    	driver.frame.pack();
	    }
	
	    /**
	     * Sets level to -2 to direct user to the how to play menu
	     */
	    public void loadHowToPlayMenu()
	    {
	    	level=-2;
	    	driver.frame.setContentPane(howToPlayMenu);
	    	driver.frame.pack();
	    }
	    
	    /**
	     * Sets level to -3 to direct user to the select level menu
	     */
	    public void loadSelectLevelMenu()
	    {
	    	level=-3;
	    	driver.frame.setContentPane(selectLevelMenu);
	    	driver.frame.pack();
	    }
	    
	    public void loadLevel(int level) {
	    	this.level=level-1;
	    	driver.frame.setContentPane(levels.get(level-1).contentPane);
	    	driver.frame.pack();
	    }

	    /**
	     * Restores the game's default state for the current level.
	     */
	    public void restoreDefaultState()
	    {
	    	rotatingMirror = false;
	        deletingMirror = false;
	        placingMirror = false;
	        mirrorPositionDetermined = false;
	        mirrorDeletingDetermined=false;
	        restartingLevel=true;
	    }
}
