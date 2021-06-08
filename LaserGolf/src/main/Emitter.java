package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Emitter extends Ellipse2D.Double {
	/**
	 * @see java.io.Serializable
	 */
	private static final long serialVersionUID = 2482234304300706390L;

	public static final double DIAMETER = 20; //Diameter of this emitter's base ellipse.  
	
	public double theta; //Angle at which this emitter shall emit lasers.  
	
	private Color emitcolor; //Color of the emitter and the laser it emits. 
	
	/**
	 * Constructs an emitter with a given x-coordinate and y-coordinate, theta, and color with a width of 20 and height of 20. The emitter's laser is initialized; 
	 * @param x - x-coordinate of the emitter
	 * @param y - y-coordinate of the emitter
	 * @param itheta - theta of the emitter. (Angle it's rotated at)
	 * @param iemitcolor - color of the emitter
	 */
	public Emitter(double x, double y, double itheta, Color iemitcolor) {
		super(x, y, DIAMETER, DIAMETER); 
		theta = itheta; 
		emitcolor = iemitcolor; 
		//laser=new Laser((x+this.getCenterX())/2, (y+this.getCenterY())/2, itheta, emitcolor);
	}
	
	/**
	 * Returns the color of the emitter
	 * @return
	 */
	public Color getColor() {
		return emitcolor; 
	}
	
	//Emit a new laser into space. 
	public void fire(ArrayList<Laser> lasers) {
		lasers.add(new Laser(this.getCenterX(), this.getCenterY(), theta, emitcolor)); //original
//		System.out.println("Firing laser");
	}
	
	/**
	 * Draws the emitter. 
	 * @param g - Graphics instance used to draw the emitter
	 */
	public void draw(Graphics2D g) {
		Graphics2D copy = (Graphics2D) g.create(); //Copy of graphics instance created to avoid rotating all graphics objects when the emitter is drawn
		copy.setColor(emitcolor);
//		copy.rotate(Math.toRadians(theta), this.getCenterX(), this.getCenterY());
		copy.fill(this);
		final double linelen = 40; 
		copy.setColor(Color.BLACK);
		Line2D.Double l = new Line2D.Double(this.getCenterX(), this.getCenterY(), this.getCenterX() + linelen*Math.cos(theta), this.getCenterY() + linelen*Math.sin(theta));
		copy.draw(l); //line drawn to indicate to the user the direction the laser will be shot
		copy.dispose();
		//laser.draw(g);
	}
	
	/**
	 * Used to reset the emitter. The emitter's theta (angle) is reset to 0 and the emitter's laser is reset.
	 */
	public void reset() {
		theta=0;
		//laser=new Laser((x+this.getCenterX())/2, (y+this.getCenterY())/2, theta, emitcolor);
	}
}
