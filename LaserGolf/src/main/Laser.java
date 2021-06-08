package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * A single laser particle.
 * 
 * @author John D'Alessandro
 */
public class Laser extends Ellipse2D.Double
{
	/**
	 * The mirror the laser has most recently reflected off of.
	 */
	public Mirror mostRecentMirrorReflectedOff; 
	
	/**
	 * @see java.io.Serializable
	 */
	private static final long serialVersionUID = -1111498657098238489L;

	/**
	 * The speed at which the laser propagates.
	 */
	public static final double SPEED = 3.0;
	
	/**
	 * The diameter of the laser.
	 */
	public static final double DIAMETER = 10;
	
	/**
	 * The angle of the laser's travel.
	 */
	public double theta;
	
	/**
	 * The color of the laser.
	 */
	private Color color;

	/**
	 * Constructs a laser with a given x and y-coordinate, theta, and color. Its most recent m irror it has reflected off of is set to null.
	 * @param x The initial x-position.
	 * @param y The initial y-position.
	 * @param angle The initial angle of travel, in radians.
	 * @param color The color of the laser.
	 */
	public Laser(double x, double y, double angle, Color color)
	{
		super(x, y, DIAMETER, DIAMETER);
		this.theta = angle;
		this.color = color;
		mostRecentMirrorReflectedOff=null;
	}
	
	/**
	 * Returns the color of the laser.
	 * @return
	 */
	public Color getColor()
	{
		return color;
	}
	
	/**
	 * Sets the color of the laser.
	 * @param color
	 */
	public void setColor(Color color)
	{
		this.color = color;
	}
	
	/**
	 * Returns the theta (angle) of the laser.
	 * @return
	 */
	public double getTheta()
	{
		return theta;
	}
	
	/**
	 * Updates the position of the laser according to its speed and its theta (angle).
	 */
	public void tick() {
		this.x += Laser.SPEED * Math.cos(theta); 
		this.y += Laser.SPEED * Math.sin(theta); 
	}
	
	/**
	 * Attempt to reflect the laser off of a mirror with the specified normal vector.
	 * Shall only be successful if the laser is sufficiently close to the mirror. 
	 * NOTE: Each tick this method shall be called on all lasers with all mirrors. 
	 * 
	 * @param m The mirror to reflect off of. 
	 */
	public void reflect(Mirror m)
	{
		Mirror mRotated=new Mirror(m.currRotatedX1,m.currRotatedY1,m.currRotatedX2,m.currRotatedY2); //rotated version of the mirror. The rotated verison of the mirror is displayed on the screen.
		if((mRotated.ptSegDist(new Point2D.Double(this.getCenterX(), this.getCenterY())) < Surface.COLLIDE_DIST) && m!=mostRecentMirrorReflectedOff) { //if the laser has collided with the mirror (rotated version), the laser is reflected off of the mirror. The laser's most recent mirror it has reflected off of is set to the mirror.
			Vector2D incident = new Vector2D(theta);
			Vector2D normal = mRotated.getNormal();
			Vector2D reflect = incident.subtract(normal.scalarMultiply(2 * incident.dotProduct(normal)));
	        theta = reflect.angleOf(); //Yield.
	        mostRecentMirrorReflectedOff=m;
		}
	}
	
	/**
	 * Draws the laser
	 * @param g - Graphics instance used to draw the laser
	 */
	public void draw(Graphics2D g) {
		Graphics2D copy = (Graphics2D) g.create(); //Copy of graphics instance created to avoid rotating all graphics objects when the laser is drawn
//		copy.setColor(Color.BLACK);
//		copy.draw(this);
		copy.setColor(color);
		copy.rotate(Math.toRadians(theta), getCenterX(), getCenterY());
		copy.fill(this);
		copy.dispose();
	}
}
