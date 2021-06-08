package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Blackbody extends Surface {

	/**
	 * @see java.io.Serializable
	 */
	private static final long serialVersionUID = -8978782950371915963L;
	
	//blackbody's color
	static final Color BLACKBODY_COLOR = new Color(0x543a3a);
	
	/**
	 * Constructs a black body with given starting and ending points.
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public Blackbody(double x1, double y1, double x2, double y2) {
		super(x1, y1, x2, y2, BLACKBODY_COLOR);
	}
	
	/**
	 * Draws the black body
	 * @param g
	 */
	public void draw(Graphics2D g) {
		Graphics2D copy = (Graphics2D) g.create(); //Copy of graphics instance created to avoid rotating all graphics objects when the black body is drawn
		copy.setColor(col);
		copy.setStroke(new BasicStroke(20));
		copy.rotate(Math.toRadians(additionalRotation), (x1+x2)/2, (y1+y2)/2);
		copy.draw(this);
		copy.dispose();
	}
}
