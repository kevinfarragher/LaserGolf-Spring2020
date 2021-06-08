package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Rectangle2D;

/**
 * A crate of explosives that causes the player to fail the current level if a laser touches it.
 */
public class Explosive extends Rectangle2D.Double
{
	/**
	 * @see java.io.Serializable
	 */
	private static final long serialVersionUID = 3035730487287102751L;

	/**
	 * The width and height of the crate of explosives.
	 */
	public static final int SIZE = 30;
	
	/**
	 * The level on which this crate of explosives exists.
	 */
	private Level level;
	
	/**
	 * The "danger" icon displayed on the front of the crate of explosives.
	 */
	private Polygon triangle;
	
	/**
	 * @param x the x-coordinate at which the crate of explosives is to be placed
	 * @param y the y-coordinate at which the crate of explosives is to be placed
	 * @param level the level on which the crate of explosives is to be placed
	 */
	public Explosive(int x, int y, Level level)
	{
		super(x, y, SIZE, SIZE);
		this.level = level;
		triangle = new Polygon();
		triangle.addPoint(x + 5, y + SIZE - 5);
		triangle.addPoint(x + (SIZE / 2), y + 5);
		triangle.addPoint(x + SIZE - 5, y + SIZE - 5);
	}
	
	/**
	 * Checks whether this crate of explosives has been hit and restarts the level if so.
	 */
	public void checkForHit()
	{
		for (Laser laser : level.lasers)
			if (laser.intersects(this))
			{
				level.resetLevel();
				break;
			}
	}
	
	public void draw(Graphics2D g)
	{
		g.setColor(Color.DARK_GRAY);
		g.fill(this);
		g.setColor(Color.YELLOW);
		g.fill(triangle);
		g.setColor(Color.BLACK);
		g.setFont(g.getFont().deriveFont(Font.BOLD));
		g.drawString("!", (float) (getX() + (SIZE * .45)), (float) (getY() + (SIZE * .75)));
	}
}
