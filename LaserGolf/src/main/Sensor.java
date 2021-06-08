package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.List;

import javax.swing.Timer;

/**
 * A sensor that can detect when it has been hit by a laser.
 * 
 * @author John D'Alessandro
 */
public class Sensor extends Rectangle2D.Double
{
	/**
	 * @see java.io.Serializable
	 */
	private static final long serialVersionUID = 4790702913371182001L;
	
	/**
	 * The amount of time (in milliseconds) a sensor's hit flag stays true following a hit.
	 */
	public static final int TIMEOUT = 1000;

	/**
	 * The width and height of the sensor.
	 */
	public static final double SIZE = 20;
	
	/**
	 * Controls the resetting of the hit flag after the timeout elapses.
	 */
	private Timer timeoutController;
	
	/**
	 * The color of laser this sensor responds to.
	 */
	private final Color color;

	/**
	 * Whether this sensor has been hit by a laser of the appropriate color.
	 */
	public boolean hit = false;

	/**
	 * Constructs a sensor with a given x and y-coordinate and color.
	 * @param x The sensor's x position
	 * @param y The sensor's y position
	 * @param color The color of laser this sensor responds to
	 */
	public Sensor(double x, double y, Color color)
	{
		super(x, y, SIZE, SIZE);
		this.color = color;
		timeoutController = new Timer(TIMEOUT, e -> {hit = false;});
		timeoutController.setRepeats(false);
	}
	
	/**
	 * Returns the color of the sensor.
	 * @return
	 */
	public Color getColor()
	{
		return color;
	}

	/**
	 * Returns whether the sensor has been hit by a laser of its color
	 * @return
	 */
	public boolean isHit()
	{
		return hit;
	}
	
	/**
	 * Checks whether any of the specified lasers have hit this sensor.
	 * 
	 * @param lasers the lasers to be checked for a hit
	 * @return whether this sensor has been hit
	 */
	public boolean checkForHit(List<Laser> lasers)
	{	
		Iterator<Laser> laserIter = lasers.iterator();
		while (laserIter.hasNext()) {
			Laser laser = laserIter.next(); 
			if (laser.getColor().equals(this.color) && laser.intersects(this))
			{
				laserIter.remove();
				hit = true;
				timeoutController.restart();
//				System.out.println("HIT");
			}
		}
		return hit; 
	}

	/**
	 * Draws the sensor on the screen.
	 * @param g - graphics instance used to draw sensor
	 */
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(3));
		g.draw(this);
		g.setColor(color);
		g.fill(this);
	}
}
