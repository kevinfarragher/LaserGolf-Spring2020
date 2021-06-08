package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

/**
 * A color filter that changes the color of the lasers passing through it.
 */
public class ColorFilter extends Rectangle2D.Double
{
	/**
	 * @see java.io.Serializable
	 */
	private static final long serialVersionUID = 975375230749800566L;
	
	/**
	 * The color to which this filter changes the laser passing through it.
	 */
	private Color color;

	/**
	 * COntructs a color filter
	 * @param x the x coordinate of the color filter's location
	 * @param y the y coordinate of the color filter's location
	 * @param width the color filter's width
	 * @param height the color filter's height
	 */
	public ColorFilter(double x, double y, double width, double height, Color color)
	{
		super(x, y, width, height);
		this.color = color;
	}
	
	/**
	 * Changes the color of lasers passing through this filter to the color of the filter.
	 * 
	 * @param lasers the lasers to be checked for passing through this filter
	 */
	public void changeLaserColors(List<Laser> lasers)
	{
		for (Laser laser : lasers)
			if (laser.intersects(this))
				laser.setColor(color);
	}
	
	/**
	 * Draws the color filter
	 * @param g - graphics instance used to draw the color filter
	 */
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fill(this);
	}
}
