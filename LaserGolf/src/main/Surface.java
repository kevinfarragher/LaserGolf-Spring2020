package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class Surface extends Line2D.Double {
	
	/**
	 * @see java.io.Serializable
	 */
	private static final long serialVersionUID = 6807190087427098946L;
	
	//surface's theta (angle) that it is initially drawn at and additional rotation
	protected double additionalRotation;
	protected double theta;
	
	//Distance below which a laser shall have 'collided' with this surface. 
	protected static final double COLLIDE_DIST = 5; 
	
	//Color of this surface. 
	protected Color col;  
	
	/**
	 * Constructs a surface with given starting and ending points and color. Its theta (angle) it was drawn at is determined and its additional rotation is set to 0.
	 * @param x1 - x-coordinate of surface's starting point
     * @param y1 - y-coordinate of surface's starting point
     * @param x2 - x-coordinate of surface's ending point
     * @param y2 - y-coordinate of surface's ending point
	 * @param icol - color of the surface
	 */
	public Surface(double x1, double y1, double x2, double y2, Color icol) {
		super(x1, y1, x2, y2); 
		col = icol; 
		additionalRotation=0;
		theta=getNormal().angleOf();
	}
	
	/**
	 * returns the color of the surface
	 * @return
	 */
	protected Color getColor() {
		return col; 
	}
	
	/**
	 * Computes the normal vector to the surface
	 * @return
	 */
	protected Vector2D getNormal() {
		final double orthogonal_slope = -1.0 / (((y2) - (y1))/((x2) - (x1)));
		return new Vector2D(Math.atan2(orthogonal_slope, 1));
	}
 
	/**
	 * Draws the surface
	 * @param g - grpahics instance used to draw the surface
	 */
	protected void draw(Graphics2D g) {
		Graphics2D copy = (Graphics2D) g.create(); //Copy of graphics instance created to avoid rotating all graphics objects when the mirror is drawn
		copy.setColor(col);
		copy.setStroke(new BasicStroke(5));
		copy.rotate(Math.toRadians(additionalRotation), (x1+x2)/2, (y1+y2)/2);
		copy.draw(this);
		copy.dispose();
	}
}
