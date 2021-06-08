package main;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public abstract class Level extends JPanel implements MouseInputListener {
	
	/**
	 * @see java.io.Serializable
	 */
	private static final long serialVersionUID = -4647364251995266738L;

	//program's gameManager and driver
	protected GameManager gameManager;
	
	int clickCount = 0;
	
	protected int startingNumSurfaces;
	protected int parScore;
	protected int playerScore;
	
	protected boolean isStarted; //level's running status

	//a level's surfaces (mirrors, black bodies), sensors, and emitters
	protected ArrayList<Laser> lasers;
	protected ArrayList<Surface> surfaces;
	protected ArrayList<Sensor> sensors; 
	protected ArrayList<Emitter> emitters;
	protected ArrayList<Explosive> explosives;
	protected ArrayList<BeamSplitter> beamsplitters;
	protected ArrayList<ColorFilter> colorfilters;
	
	//a level's current selected mirror
	protected Mirror selectedMirror;
	
	//a level's buttons
	protected JButton startButton;
	protected JButton restartButton;
	protected JButton quitButton;
	protected JButton addMirrorButton;
	protected JButton removeMirrorButton;
	protected JButton adjustMirrorAngleButton;
	
	protected JLabel parLabel;
	protected JLabel playerScoreLabel;
	
	protected JPanel contentPane;

	/**
	 * Constructs a level by initializing its surfaces (mirrors, black bodies), emitters, sensors, and buttons. The level's start state is initialized to false and selected mirror is initialized to null.
	 * @param driver - the driver of the program
	 * @param gameManager - the gameManager of the program
	 */
	public Level(GameManager gameManager, int parScore) {
		super(null);
		setPreferredSize(new Dimension(1000, 450));
		setBackground(Color.YELLOW);
		
		this.gameManager=gameManager;
		addMouseListener(this);
		addMouseMotionListener(this);
		
		this.parScore = parScore;
		this.playerScore = 0;
		
		isStarted=false;
		
		lasers = new ArrayList<Laser>(); 
		surfaces = new ArrayList<Surface>();
		sensors = new ArrayList<Sensor>();  
		emitters = new ArrayList<Emitter>();
		explosives = new ArrayList<Explosive>(); 
		beamsplitters = new ArrayList<BeamSplitter>(); 
		colorfilters = new ArrayList<ColorFilter>();
		
		//outer walls of the level
		surfaces.add(new Blackbody(0,0,1000,0));
		surfaces.add(new Blackbody(0,0,0,450));
		surfaces.add(new Blackbody(0,450,1000,450));
		surfaces.add(new Blackbody(1000,0,1000,450));
		
		selectedMirror=null;
		
		contentPane = new JPanel(new BorderLayout());
		contentPane.add(this, BorderLayout.CENTER);
		
		JPanel buttonTray = new JPanel();
		
		startButton=new JButton("Start"); ////Button for starting and halting a level
//		startButton.setBounds(0,0,300,50);
		startButton.setFont(new Font("Dialog", Font.PLAIN, 9));
		buttonTray.add(startButton);
		startButton.addActionListener(e -> { //When clicked, isStarted is set to !isStarted and the level's default state is restored. If isStarted was true before clicked, the level's current run is halted and the level's emitters reset. If isStarted was false before clicked, the level is started and the laser will start moving. 
					isStarted=!isStarted;
					gameManager.restoreDefaultState();
					if(isStarted==false) {
						resetLevel();
					}
			});
		
		restartButton=new JButton("Restart"); //Button for restarting a level
//		restartButton.setBounds(450,0,300,50);
		restartButton.setFont(new Font("Dialog", Font.PLAIN, 9)); 
		buttonTray.add(restartButton);
		restartButton.addActionListener(e -> {  //When clicked, the level is reset and the level is restored to its default state.
				resetLevel();
				playerScore=0;
				playerScoreLabel.setText(String.format("Your score: %d", playerScore));
				gameManager.restoreDefaultState();
				for(int i=0;i<surfaces.size();i++) {
					if(surfaces.get(i) instanceof Mirror) {
						surfaces.remove(i);
						i--;
					}
				}
			});
		
		quitButton=new JButton("Quit"); //Button for quitting a level
//		quitButton.setBounds(900,0,300,50);
		quitButton.setFont(new Font("Dialog", Font.PLAIN, 9));
		buttonTray.add(quitButton);
		quitButton.addActionListener(e -> { //When clicked, the level is reset, restored to its default settings, and the user is returned to the main menu (level 0) 
				resetLevel();
				playerScore=0;
				playerScoreLabel.setText(String.format("Your score: %d", playerScore));
				for(int i=0;i<surfaces.size();i++) {
					if(surfaces.get(i) instanceof Mirror) {
						surfaces.remove(i);
						i--;
					}
				}
				gameManager.loadMainMenu();
				gameManager.restoreDefaultState();
			});
		
		addMirrorButton=new JButton("Add Mirror"); //Button for adding mirror
//		addMirrorButton.setBounds(0,550,300,50);
		addMirrorButton.setFont(new Font("Dialog", Font.PLAIN, 9));
		buttonTray.add(addMirrorButton);
		addMirrorButton.addActionListener(e -> {  //When clicked, if adding mirror mode is disabled, adding mirror mode is enabled and the current run of the level is halted. If adding mirror mode is enabled, adding mirror mode is disabled and the level is restored to its default state.
				if(isStarted) {
					resetLevel(); 
				}
				if(gameManager.placingMirror==true) { //If adding mirror mode is enabled, adding mirror mode is disabled and the level is restored to its default state.  
			    	gameManager.restoreDefaultState();
			    	return;
			    }
			    gameManager.addMirror(); //if adding mirror mode is disabled, adding mirror mode is enabled and the current run of the level is halted.
			    isStarted=false;
			});
		
		removeMirrorButton=new JButton("Delete Mirror"); //Button for deleting mirror
//		removeMirrorButton.setBounds(450,550,300,50);
		removeMirrorButton.setFont(new Font("Dialog", Font.PLAIN, 9));
		buttonTray.add(removeMirrorButton);
		removeMirrorButton.addActionListener(e -> { //When clicked, if deleting mirror mode is disabled, deleting mirror mode is enabled and the current run of the level is halted. If deleting mirror mode is enabled, adding mirror mode is disabled and the level is restored to its default state
				if(isStarted) {
					resetLevel(); 
				}
				if(gameManager.deletingMirror==true) {
			    	gameManager.restoreDefaultState();
			    	return;
			    }
			    gameManager.deleteMirror();
			    isStarted=false;
			});
		
		adjustMirrorAngleButton=new JButton("Adjust Mirror Angle"); //button for adjusting mirror angle 
//		adjustMirrorAngleButton.setBounds(900,550,300,50);
		adjustMirrorAngleButton.setFont(new Font("Dialog", Font.PLAIN, 9));
		buttonTray.add(adjustMirrorAngleButton);
		adjustMirrorAngleButton.addActionListener(e -> { //When clicked, if rotating mirror mode is disabled, rotating mirror mode is enabled and the current run of the level is halted. If rotating mirror mode is enabled, rotating mirror mode is disabled and the level is restored to its default state.  
				if(isStarted) {
					resetLevel(); 
				}
				if(gameManager.rotatingMirror==true) {
			    	gameManager.restoreDefaultState();
			    	return;
			    }
				gameManager.rotateMirror();
				isStarted=false;
			});
		
		parLabel = new JLabel(String.format("Par %d", parScore));
		buttonTray.add(parLabel);
		playerScoreLabel = new JLabel(String.format("Your score: %d", playerScore));
		buttonTray.add(playerScoreLabel);
		
		contentPane.add(buttonTray, BorderLayout.PAGE_END);
		
		startButton.setVisible(true);
		restartButton.setVisible(true);
		quitButton.setVisible(true);
		addMirrorButton.setVisible(true);
		removeMirrorButton.setVisible(true);
		adjustMirrorAngleButton.setVisible(true);
	}
	
	/**
	 * Adds a mirror to the surfaces of the level based on the mouse's start and current points.
	 */
	public void addMirror() {
		surfaces.add(new Mirror(gameManager.startMouseX,gameManager.startMouseY, gameManager.currMouseX,gameManager.currMouseY));
		gameManager.mirrorPositionDetermined = false;
		playerScore++;
		playerScoreLabel.setText(String.format("Your score: %d", playerScore));
	}
	
	/**
	 * Used to update a level's status by checking for laser intersections.
	 */
	public void updateLevelStatus() {
		if(isStarted==true) { //if a level is currently running
			/*
			for(int i = 0; i < sensors.size(); i++) { //if the laser of the level's emitter hits (intersects) the level's sensor and has the same color as the emitter, the level is completed, the level is reset, and the next level is loaded. 
				for(int j = 0; j < emitters.size(); j++)
				if(sensors.get(i).checkForHit(emitters.get(j).laser)==true) { 
					isStarted=false;
					resetLevel();
					gameManager.loadNextLevel();
					return;
				}
			}
			*/
			
			for(int i = 0; i < emitters.size(); i++) {
				emitters.get(i).fire(lasers);
			}
			
			for(int i = 0; i < colorfilters.size(); i++) {
				colorfilters.get(i).changeLaserColors(lasers);
			}
			
			for(int i = 0; i < beamsplitters.size(); i++) {
				beamsplitters.get(i).checkForHit(this);
			}
			
			boolean allhit = true; 
			for(int i = 0; i < sensors.size(); i++) {
				if(!sensors.get(i).checkForHit(lasers)) {
					allhit = false;
				}
			}
			if(allhit) { //If all sensors are being satisfied, end/advance the level. 
				isStarted = false; 
				resetLevel();
				for(int i=0;i<surfaces.size();i++) {
					if(surfaces.get(i) instanceof Mirror) {
						surfaces.remove(i);
						i--;
					}
				}
				gameManager.loadNextLevel();
//				JOptionPane.showMessageDialog(gameManager.driver.frame, String.format("Your score: %d%nYour score relative to par: %d", playerScore, playerScore - parScore));
				return; 
			}
			
			for(int i = 0; i < explosives.size(); i++) {
				explosives.get(i).checkForHit(); 
			}
			
			Iterator<Laser> laserIter = lasers.iterator();
			while (laserIter.hasNext()) { //checking if laser's of the level have hit (intersect) a surface (mirror, black body), sensor, or emitter of the level.
				Laser current = laserIter.next();
				current.tick();
				for(int j = 0; j < surfaces.size(); j++) { //Checking if lasers have hit a surface (mirror, black body)
					if(surfaces.get(j) instanceof Mirror) { //if the surface is a mirror
						current.reflect((Mirror) surfaces.get(j)); //laser's are checked for if they intersect the mirror. If so, they are reflected off of the mirror.
					}
					if(surfaces.get(j) instanceof Blackbody) { //if the surface is a black body
						if(surfaces.get(j).ptSegDist(new Point2D.Double(current.getCenterX(), current.getCenterY()))<10) { //if a laser intersects a black body, the laser is reset.
							laserIter.remove(); 
							isStarted=false;
							resetLevel();
							return;
						}
					}
					/*
					if((emitters.get(i).contains(new Point2D.Double(emitters.get(i).laser.getCenterX(), emitters.get(i).laser.getCenterY()))) && emitters.get(i).laser.mostRecentMirrorReflectedOff!=null) { //if a laser intersects a black body and has already reflected off of a mirror, the laser is reset.
						for(int k=0;k<emitters.size();k++) {
							emitters.get(k).reset();
						}
						isStarted=false;
						return;
					}
					*/
				}
			}
		}
	}
	
	/**
	 * Used to draw a level. Draws a level's display, surfaces (mirrors, black bodies), emitters, sensors, and buttons.
	 * @param g Graphics object to draw the level's objects
	 */
	public void draw(Graphics2D g) {
		for(int i = 0; i < emitters.size(); i++) { //level's emitters are drawn;
			emitters.get(i).draw(g);
		}
		
		for(int i = 0; i < beamsplitters.size(); i++) {
			beamsplitters.get(i).draw(g); 
		}
		
		for(int i = 0; i < colorfilters.size(); i++) {
			colorfilters.get(i).draw(g);
		}
		
		for(int i = 0; i < explosives.size(); i++) {
			explosives.get(i).draw(g); 
		}
		
		for(int i = 0; i < lasers.size(); i++) {
			lasers.get(i).draw(g);
		}
		
		for(int i = 0; i < surfaces.size(); i++) { //level's surfaces are drawn
			surfaces.get(i).draw(g);
		}
		
		for(int i=0;i<sensors.size();i++) { //level's sensors are drawn
			sensors.get(i).draw(g);
		}
	}
	
	/**
	 * Used to reset level by clearing the level's placed mirrors and resetting the emitters' lasers.
	 */
	public void resetLevel() {
		/*
		for(int j = 0; j < surfaces.size(); j++) { //Checking if laser has hit a surface
			if(surfaces.get(j) instanceof Mirror) {
				surfaces.remove(j);
				j--;
			} 
		}
		*/
//		for(int i=0;i<emitters.size();i++) {
//			emitters.get(i).reset();
//		}
		lasers.clear();
		for(int i=0;i<beamsplitters.size();i++) {
			beamsplitters.get(i).sensor.hit=false;
		}
		isStarted=false;
	}
	
	@Override
	protected void paintComponent(Graphics graphicHelper) {
		super.paintComponent(graphicHelper);
		Graphics2D g = (Graphics2D)graphicHelper;
		
		this.updateLevelStatus();
		this.draw(g);
		if(gameManager.placingMirror==true && gameManager.mirrorPositionDetermined==true) { //if the user is actively placing a mirror on the current level display, a temporary mirror is shown as it is being dragged and resized.
			Mirror tempMirror=new Mirror(gameManager.startMouseX,gameManager.startMouseY,gameManager.currMouseX,gameManager.currMouseY);
			tempMirror.draw(g);
		}
		if(gameManager.rotatingMirror==true || gameManager.deletingMirror==true) { //if the user is attempting to delete or rotate a mirror, a point is drawn on each mirror to represent each mirror's center point. The center point of each mirror is clicked by the user to delete a mirror or start rotating a mirror.
			for(int i=0;i<this.surfaces.size();i++) {
				if(this.surfaces.get(i) instanceof Mirror) {
					g.setStroke(new BasicStroke(5));
					g.setColor(Color.BLACK);
					g.drawLine((int)(this.surfaces.get(i).getP1().getX()+this.surfaces.get(i).getP2().getX())/2, (int)(this.surfaces.get(i).getP1().getY()+this.surfaces.get(i).getP2().getY())/2, (int)(this.surfaces.get(i).getP1().getX()+this.surfaces.get(i).getP2().getX())/2, (int)(this.surfaces.get(i).getP1().getY()+this.surfaces.get(i).getP2().getY())/2);
				}
			}
		}
		
		//automatic repainting functionality
		long start = System.currentTimeMillis();
		long delta = 0;
		long frameTime = 1000/60;
		while(delta<frameTime) {
			delta = System.currentTimeMillis() - start;
		}
		
		repaint();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Handles mouse pressed events
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
//		gameManager.startMouseX=e.getX();
//		gameManager.startMouseY=e.getY();
//		gameManager.prevMouseX=e.getX();
//		gameManager.prevMouseY=e.getY();
//		gameManager.currMouseX=e.getX();
//		gameManager.currMouseY=e.getY();
		
		
		
		for(int i=0;i<this.emitters.size();i++) { //if a level is currently being displayed, the level isn't being ran, and the user clicks on one of the level's emitters, the emitter and its laser are rotated and the level is reset to its default state.
			if(this.emitters.get(i).contains(gameManager.currMouseX, gameManager.currMouseY) && this.isStarted==false) {
				this.emitters.get(i).theta+=Math.PI/4;
				//this.emitters.get(i).laser.theta+=1;
				gameManager.restoreDefaultState();
				//System.out.println("Emitter Angle:" + this.emitters.get(i).theta + " Laser Angle:" + this.emitters.get(i).laser.theta);
				return;
			}
		}
		if(gameManager.placingMirror==true) { //if a level is currently being displayed and the user is attempting to add a mirror, the status of whether the mirror's starting position has been determined is set to true.
			gameManager.mirrorPositionDetermined=true;
			clickCount++;
			
			
			if(clickCount%2!=0) {
				gameManager.startMouseX = e.getX();
				gameManager.startMouseY = e.getY();
				
			}else {
				gameManager.currMouseX = e.getX();
				gameManager.currMouseY = e.getY();
				//surfaceCount++;
				
				if(gameManager.placingMirror==true) { //if a level is currently being displayed, the user is attempting to add a mirror, and the mirror, if placed using the starting and current points, doesn't intersect with any of the level's surfaces (mirrors, black bodies), sensors, and emitters, the mirror is added to the level and displayed.
					if((gameManager.startMouseX!=gameManager.currMouseX) && (gameManager.startMouseY!=gameManager.currMouseY)) { //if the start and current points aren't the same
						for(int j = 0; j < this.surfaces.size(); j++) { //Checking if laser has hit a surface (mirror)
							if(this.surfaces.get(j) instanceof Blackbody) {  //if the mirror, placed using the starting and current points, intersects one of the level's black bodies, the mirror isn't placed.
								if(new Mirror(gameManager.startMouseX,gameManager.startMouseY, gameManager.currMouseX,gameManager.currMouseY).intersectsLine(this.surfaces.get(j))) {
									System.out.println("Mirror can't be placed here");
									gameManager.mirrorPositionDetermined=false;
									return;
								}
							}
							if(this.surfaces.get(j) instanceof Mirror) { //if the mirror, placed using the starting and current points, intersects one of the level's mirrors (it's rotated version), the mirror isn't placed.
								Mirror currIteratedMirror=(Mirror)this.surfaces.get(j); //the current iterated mirror
								Mirror mRotated=new Mirror(currIteratedMirror.currRotatedX1,currIteratedMirror.currRotatedY1,currIteratedMirror.currRotatedX2,currIteratedMirror.currRotatedY2); //the rotated version of current iterated mirror. The rotated version of each mirror is displayed on the screen.
								if(new Mirror(gameManager.startMouseX,gameManager.startMouseY, gameManager.currMouseX,gameManager.currMouseY).intersectsLine(mRotated)) {
									System.out.println("Mirror can't be placed here");
									gameManager.mirrorPositionDetermined=false;
									return;
								}
							}
						}
						
						for(int i=0;i<this.sensors.size();i++) { //if the mirror, placed using the starting and current points, intersects one of the level's sensors, the mirror isn't placed.
							//if(new Mirror(gameManager.startMouseX,gameManager.startMouseY, gameManager.currMouseX,gameManager.currMouseY).ptSegDist(new Point2D.Double(this.sensors.get(i).getCenterX(), this.sensors.get(i).getCenterY()))<10) { 
							if(new Mirror(gameManager.startMouseX,gameManager.startMouseY, gameManager.currMouseX,gameManager.currMouseY).intersects(this.sensors.get(i))) { 
								System.out.println("Mirror can't be placed here");
								gameManager.mirrorPositionDetermined=false;
								return;
							}
						}
						
						/*for(int i=0;i<this.colorfilters.size();i++) { //if the mirror, placed using the starting and current points, intersects one of the level's color filters, the mirror isn't placed.
							if(new Mirror(gameManager.startMouseX,gameManager.startMouseY, gameManager.currMouseX,gameManager.currMouseY).intersects(this.colorfilters.get(i))){
								System.out.println("Mirror can't be placed here");
								gameManager.mirrorPositionDetermined=false;
								return;
							}
						}*/
						
						for(int i=0;i<this.explosives.size();i++) { //if the mirror, placed using the starting and current points, intersects one of the level's explosives, the mirror isn't placed.
							if(new Mirror(gameManager.startMouseX,gameManager.startMouseY, gameManager.currMouseX,gameManager.currMouseY).intersects(this.explosives.get(i))){
								System.out.println("Mirror can't be placed here");
								gameManager.mirrorPositionDetermined=false;
								return;
							}
						}
						
						for(int i=0;i<this.beamsplitters.size();i++) { //if the mirror, placed using the starting and current points, intersects one of the level's beam splitters, the mirror isn't placed.
							for(int j=0;j<this.beamsplitters.get(i).emitters.size();j++) {
								if(new Mirror(gameManager.startMouseX,gameManager.startMouseY, gameManager.currMouseX,gameManager.currMouseY).ptSegDist(new Point2D.Double(this.beamsplitters.get(i).emitters.get(j).getCenterX(), this.beamsplitters.get(i).emitters.get(j).getCenterY()))<10){
									System.out.println("Mirror can't be placed here");
									gameManager.mirrorPositionDetermined=false;
									return;
								}
							}
							if(new Mirror(gameManager.startMouseX,gameManager.startMouseY, gameManager.currMouseX,gameManager.currMouseY).intersects(this.beamsplitters.get(i).sensor)) { 
								System.out.println("Mirror can't be placed here");
								gameManager.mirrorPositionDetermined=false;
								return;
							}
						}
						
						for(int i=0;i<this.emitters.size();i++) { //if the mirror, placed using the starting and current points, intersects one of the level's emitters, the mirror isn't placed.
							if(new Mirror(gameManager.startMouseX,gameManager.startMouseY, gameManager.currMouseX,gameManager.currMouseY).ptSegDist(new Point2D.Double(this.emitters.get(i).getCenterX(), this.emitters.get(i).getCenterY()))<10) { 
								System.out.println("Mirror can't be placed here");
								gameManager.mirrorPositionDetermined=false;
								return;
							}
						}
						
						
						this.addMirror(); //The mirror is placed and added to the level if the mirror, when placed using the starting and current points, doesn't contain/intersect any of the surfaces (black bodies, mirrors) sensors, and emitters of the level.
					}
				}
			}
		}
		if(gameManager.deletingMirror==true) { //if a level is currently being displayed, the user is attempting to delete a mirror, and the mouse point contains one of the center points of the level's mirrors, the mirror is removed from the level's list of surfaces and deleted.
			for(int i=0;i<this.surfaces.size();i++) {
				if(this.surfaces.get(i) instanceof Mirror) { //if the surface is a mirror
					Point2D.Double mirrorCenter=new Point2D.Double((this.surfaces.get(i).getP1().getX()+this.surfaces.get(i).getP2().getX())/2,(this.surfaces.get(i).getP1().getY()+this.surfaces.get(i).getP2().getY())/2); //current iterated mirror's center point
					if(mirrorCenter.distance(new Point2D.Double(gameManager.currMouseX, gameManager.currMouseY))<=2) {
						this.surfaces.remove(i);
						playerScore--;
						playerScoreLabel.setText(String.format("Your score: %d", playerScore));
						//gameManager.deletingMirror = false;
						return;
					}
				}
			}
		}
		if (gameManager.rotatingMirror) //if a level is currently being displayed, the user is attempting to rotate a mirror, and the mouse point contains one of the center points of the level's mirrors, the mirror is marked as the selected mirror for rotation.
        {
			for(int i=0;i<this.surfaces.size();i++) {
				if(this.surfaces.get(i) instanceof Mirror) { //if the surface is a mirror
					Point2D.Double mirrorCenter=new Point2D.Double((this.surfaces.get(i).getP1().getX()+this.surfaces.get(i).getP2().getX())/2,(this.surfaces.get(i).getP1().getY()+this.surfaces.get(i).getP2().getY())/2); //current iterated mirror's center point
					if(mirrorCenter.distance(new Point2D.Double(gameManager.currMouseX, gameManager.currMouseY))<=4) {
						this.selectedMirror=(Mirror)this.surfaces.get(i);
						return;
					}
				}
			}
        }
		repaint();
	}
	
	/**
	 * Handles mouse dragged events.
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		gameManager.currMouseX=e.getX();
		gameManager.currMouseY=e.getY();
		if(this!=null) { //if a level is currently being displayed
			if (gameManager.rotatingMirror) //if a mirror is being rotated and the mouse is being dragged, the mirror will be rotated based on the difference between the mouse's current y-coordinate and the mouse's previous y-coordinate.
	        {
	            gameManager.currMouseY = e.getY();   
	            if(this.selectedMirror!=null) {
		            if (gameManager.currMouseY - gameManager.prevMouseY > 0) //if the difference between the mouse's current y-coordinate and previous y-coordinate is greater than 0, the mirror will be rotated clockwise by 1 degree.
		            {
		            	this.selectedMirror.currTheta++;
		            }
		            else if (gameManager.currMouseY - gameManager.prevMouseY < 0) //if the difference between the mouse's current y-coordinate and previous y-coordinate is less than 0, the mirror will be rotated counter-clockwise by 1 degree.
		            {
		            	this.selectedMirror.currTheta--;
		            }
		            else
		            {
		            	this.selectedMirror.additionalRotation += 0; //if the difference between the mouse's current y-coordinate and previous y-coordinate is 0, the mirror will not be rotated.
		            	this.selectedMirror.currTheta+=0;
		            }
		          this.selectedMirror.updateRotatedCoordinates(); //after the mirror has been rotated, the mirror's rotated coordinates are updated
	            }
	            gameManager.prevMouseY = e.getY();
	        }
		}
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		gameManager.currMouseX=e.getX();
		gameManager.currMouseY=e.getY();
			if(this!=null) { //if a level is being displayed

			if (gameManager.rotatingMirror) //if a mirror is rotated and the mouse is released, the rotated version of the mirror is checked for if it contains/intersects one of the level's surfaces (black bodies, mirrors), emitters, and sensors. If so, the mirror is reset to its previous (initial) rotation before this current rotation. If not, the mirror rotation is complete. At the end, the selected mirror for rotation is set to null, with there no longer being a selected mirror for rotation in the current level.
	        {
				if(this.selectedMirror!=null) { //if the current rotated mirror is not null
					Mirror mRotated=new Mirror(this.selectedMirror.currRotatedX1,this.selectedMirror.currRotatedY1,this.selectedMirror.currRotatedX2,this.selectedMirror.currRotatedY2); //the rotated version of the mirror
					for(int j = 0; j < this.surfaces.size(); j++) { //Checking if rotated version of the mirror contains/intersects one of the level's surfaces (black body, mirror)
						if(this.surfaces.get(j) instanceof Blackbody) { //if the rotated version of the mirror intersects one of the level's black bodies, the mirror is reset to its previous (initial) rotation before this current rotation. The mirror's currents points are reset to their previous values before this current rotation started and the selected mirror for rotation is set to null.
							if(mRotated.intersectsLine(this.surfaces.get(j))) {
								System.out.println("Mirror can't be rotated here");
								this.selectedMirror.currRotatedX1=this.selectedMirror.prevRotatedX1;
								this.selectedMirror.currRotatedY1=this.selectedMirror.prevRotatedY1;
								this.selectedMirror.currRotatedX2=this.selectedMirror.prevRotatedX2;
								this.selectedMirror.currRotatedY2=this.selectedMirror.prevRotatedY2;
								this.selectedMirror.currTheta=this.selectedMirror.prevTheta;
								this.selectedMirror=null;
								return;
							}
						}
						if(this.surfaces.get(j) instanceof Mirror) { //if the rotated version of the mirror intersects one of the level's mirrors (it's rotated version), the mirror is reset to its previous (initial) rotation before this current rotation. The mirror's currents points are reset to their previous values before this current rotation started and the selected mirror for rotation is set to null.
							Mirror currIteratedMirror=(Mirror)this.surfaces.get(j); //the current mirror of the iteration
							Mirror cimRotated=new Mirror(currIteratedMirror.currRotatedX1,currIteratedMirror.currRotatedY1,currIteratedMirror.currRotatedX2,currIteratedMirror.currRotatedY2); //the rotated version of the current mirror of the iteration
							if(mRotated.intersectsLine(cimRotated) && currIteratedMirror!=this.selectedMirror) { //if the rotated version of the mirror intersects one of the rotated versions of the already placed mirrors and the rotated version of the already placed mirror is not equal to the mirror that has been rotated, the mirror is reset to its previous (initial) rotation before this current rotation. The mirror's currents points are reset to their previous values before this current rotation started and the selected mirror for rotation is set to null.
								System.out.println("Mirror can't be rotated here");
								this.selectedMirror.currRotatedX1=this.selectedMirror.prevRotatedX1;
								this.selectedMirror.currRotatedY1=this.selectedMirror.prevRotatedY1;
								this.selectedMirror.currRotatedX2=this.selectedMirror.prevRotatedX2;
								this.selectedMirror.currRotatedY2=this.selectedMirror.prevRotatedY2;
								this.selectedMirror.currTheta=this.selectedMirror.prevTheta;
								this.selectedMirror=null;
								return;
							}
						}
					}
					
					for(int i=0;i<this.sensors.size();i++) {  //Checking if rotated version of the mirror contains/intersects one of the level's sensors
						if(mRotated.ptSegDist(new Point2D.Double(this.sensors.get(i).getCenterX(), this.sensors.get(i).getCenterY()))<Sensor.SIZE/2) { //if rotated version of the mirror contains/intersects one of the level's sensors, the mirror is reset to its previous (initial) rotation before this current rotation. The mirror's currents points are reset to their previous values before this current rotation started and the selected mirror for rotation is set to null. 
							System.out.println("Mirror can't be rotated here");
							this.selectedMirror.currRotatedX1=this.selectedMirror.prevRotatedX1;
							this.selectedMirror.currRotatedY1=this.selectedMirror.prevRotatedY1;
							this.selectedMirror.currRotatedX2=this.selectedMirror.prevRotatedX2;
							this.selectedMirror.currRotatedY2=this.selectedMirror.prevRotatedY2;
							this.selectedMirror.currTheta=this.selectedMirror.prevTheta;
							this.selectedMirror=null;
							return;
						}
					}
					
					for(int i=0;i<this.explosives.size();i++) {  //Checking if rotated version of the mirror contains/intersects one of the level's explosives
						if(mRotated.ptSegDist(new Point2D.Double(this.explosives.get(i).getCenterX(), this.explosives.get(i).getCenterY()))<Explosive.SIZE/2) { //if rotated version of the mirror contains/intersects one of the level's explosives, the mirror is reset to its previous (initial) rotation before this current rotation. The mirror's currents points are reset to their previous values before this current rotation started and the selected mirror for rotation is set to null. 
							System.out.println("Mirror can't be rotated here");
							this.selectedMirror.currRotatedX1=this.selectedMirror.prevRotatedX1;
							this.selectedMirror.currRotatedY1=this.selectedMirror.prevRotatedY1;
							this.selectedMirror.currRotatedX2=this.selectedMirror.prevRotatedX2;
							this.selectedMirror.currRotatedY2=this.selectedMirror.prevRotatedY2;
							this.selectedMirror.currTheta=this.selectedMirror.prevTheta;
							this.selectedMirror=null;
							return;
						}
					}
					
					/*for(int i=0;i<this.colorfilters.size();i++) {  //Checking if rotated version of the mirror contains/intersects one of the level's color filters
						//if(mRotated.ptSegDist(new Point2D.Double(this.colorfilters.get(i).getCenterX(), this.colorfilters.get(i).getCenterY()))<Explosive.SIZE/2) { //if rotated version of the mirror contains/intersects one of the level's color filters, the mirror is reset to its previous (initial) rotation before this current rotation. The mirror's currents points are reset to their previous values before this current rotation started and the selected mirror for rotation is set to null. 
						if(mRotated.intersects(colorfilters.get(i))){ //if rotated version of the mirror contains/intersects one of the level's color filters, the mirror is reset to its previous (initial) rotation before this current rotation. The mirror's currents points are reset to their previous values before this current rotation started and the selected mirror for rotation is set to null. 
							System.out.println("Mirror can't be rotated here");
							this.selectedMirror.currRotatedX1=this.selectedMirror.prevRotatedX1;
							this.selectedMirror.currRotatedY1=this.selectedMirror.prevRotatedY1;
							this.selectedMirror.currRotatedX2=this.selectedMirror.prevRotatedX2;
							this.selectedMirror.currRotatedY2=this.selectedMirror.prevRotatedY2;
							this.selectedMirror.currTheta=this.selectedMirror.prevTheta;
							this.selectedMirror=null;
							return;
						}
					}*/
					
					for(int i=0;i<this.beamsplitters.size();i++) { //if the mirror, placed using the starting and current points, intersects one of the level's beam splitters, the mirror isn't placed.
						for(int j=0;j<this.beamsplitters.get(i).emitters.size();j++) {
							if(mRotated.ptSegDist(new Point2D.Double(this.beamsplitters.get(i).emitters.get(j).getCenterX(), this.beamsplitters.get(i).emitters.get(j).getCenterY()))<Emitter.DIAMETER/2) { //if rotated version of the mirror contains/intersects one of the level's emitters, the mirror is reset to its previous (initial) rotation before this current rotation. The mirror's currents points are reset to their previous values before this current rotation started and the selected mirror for rotation is set to null. 
								System.out.println("Mirror can't be rotated here");
								this.selectedMirror.currRotatedX1=this.selectedMirror.prevRotatedX1;
								this.selectedMirror.currRotatedY1=this.selectedMirror.prevRotatedY1;
								this.selectedMirror.currRotatedX2=this.selectedMirror.prevRotatedX2;
								this.selectedMirror.currRotatedY2=this.selectedMirror.prevRotatedY2;
								this.selectedMirror.currTheta=this.selectedMirror.prevTheta;
								this.selectedMirror=null;
								return;
							}
						}
						if(mRotated.ptSegDist(new Point2D.Double(this.beamsplitters.get(i).sensor.getCenterX(), this.beamsplitters.get(i).sensor.getCenterY()))<Sensor.SIZE/2) { //if rotated version of the mirror contains/intersects one of the level's sensors, the mirror is reset to its previous (initial) rotation before this current rotation. The mirror's currents points are reset to their previous values before this current rotation started and the selected mirror for rotation is set to null. 
							System.out.println("Mirror can't be rotated here");
							this.selectedMirror.currRotatedX1=this.selectedMirror.prevRotatedX1;
							this.selectedMirror.currRotatedY1=this.selectedMirror.prevRotatedY1;
							this.selectedMirror.currRotatedX2=this.selectedMirror.prevRotatedX2;
							this.selectedMirror.currRotatedY2=this.selectedMirror.prevRotatedY2;
							this.selectedMirror.currTheta=this.selectedMirror.prevTheta;
							this.selectedMirror=null;
							return;
						}
					}
					
					for(int i=0;i<this.emitters.size();i++) {//Checking if rotated version of the mirror contains/intersects one of the level's emitters
						if(mRotated.ptSegDist(new Point2D.Double(this.emitters.get(i).getCenterX(), this.emitters.get(i).getCenterY()))<Emitter.DIAMETER/2) { //if rotated version of the mirror contains/intersects one of the level's emitters, the mirror is reset to its previous (initial) rotation before this current rotation. The mirror's currents points are reset to their previous values before this current rotation started and the selected mirror for rotation is set to null. 
							System.out.println("Mirror can't be rotated here");
							this.selectedMirror.currRotatedX1=this.selectedMirror.prevRotatedX1;
							this.selectedMirror.currRotatedY1=this.selectedMirror.prevRotatedY1;
							this.selectedMirror.currRotatedX2=this.selectedMirror.prevRotatedX2;
							this.selectedMirror.currRotatedY2=this.selectedMirror.prevRotatedY2;
							this.selectedMirror.currTheta=this.selectedMirror.prevTheta;
							this.selectedMirror=null;
							return;
						}
					}
					////if the rotated version of the mirror doesn't contain/intersect any of the level's surfaces (black bodies, mirrors), emitters, and sensors, the mirror rotation is complete. The mirror's previous points are set to the current points after this current rotation. The The selected mirror for rotation is set to null, with there no longer being a selected mirror for rotation in the current level.
					this.selectedMirror.prevRotatedX1=this.selectedMirror.currRotatedX1;
					this.selectedMirror.prevRotatedY1=this.selectedMirror.currRotatedY1;
					this.selectedMirror.prevRotatedX2=this.selectedMirror.currRotatedX2;
					this.selectedMirror.prevRotatedY2=this.selectedMirror.currRotatedY2;
					this.selectedMirror.prevTheta=this.selectedMirror.currTheta;
					this.selectedMirror=null;
				}
			}
		}
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Handles mouse moved events. When the mouse is moved, the mouse's x and y-coordinates are stored.
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		gameManager.currMouseX=e.getX();
		gameManager.currMouseY=e.getY();
	}
}
