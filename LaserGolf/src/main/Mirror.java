package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class Mirror extends Surface{
	
	/**
	 * @see java.io.Serializable
	 */
	private static final long serialVersionUID = 852791966707323054L;

	static final Color MIRROR_COLOR = new Color(0xb0fffe); //mirror's color
	
	//previous and current thetas (angles) of the mirror. Used, for instance, when the mirror is being rotated.
	public double prevTheta;
	public double currTheta;
	
	//The coordinates of the mirror before a rotation.
	public double prevRotatedX1;
	public double prevRotatedY1;
	public double prevRotatedX2;
	public double prevRotatedY2;
	
	//The coordinates of the mirror after a rotation. The coordinates of the mirror's rotated version.
	public double currRotatedX1;
	public double currRotatedY1;
	public double currRotatedX2;
	public double currRotatedY2;
	
    /**
     * Constructs a mirror with given starting and ending points. The mirror's previous theta (angle), current theta (angle), and additional rotation are initialized to 0, and the previous and current points of the rotated version of the mirror are initialized to the mirror's given starting and ending points when first created.
     * @param x1 - x-coordinate of mirror's starting point
     * @param y1 - y-coordinate of mirror's starting point
     * @param x2 - x-coordinate of mirror's ending point
     * @param y2 - y-coordinate of mirror's ending point
     */
	public Mirror(double x1, double y1, double x2, double y2) {
		super(x1, y1, x2, y2, MIRROR_COLOR);
		additionalRotation=0;
		prevTheta=0;
		currTheta=0;
		prevRotatedX1=x1;
		prevRotatedY1=y1;
		prevRotatedX2=x2;
		prevRotatedY2=y2;
		currRotatedX1=x1;
		currRotatedY1=y1;
		currRotatedX2=x2;
		currRotatedY2=y2;
	}
	
	/**
	 * Draws the mirror 
	 * @param g - Graphics instance used to draw the mirror.
	 */
	public void draw(Graphics2D g) {
		Graphics2D copy = (Graphics2D) g.create(); //Copy of graphics instance created to avoid rotating all graphics objects when the mirror is drawn
		copy.setColor(col);
		copy.setStroke(new BasicStroke(5));
		copy.rotate(Math.toRadians(currTheta), (x1+x2)/2, (y1+y2)/2);
		copy.draw(this);
		copy.dispose();
	}
	
	/**
	 * updates the coordinates of the reflected version of the mirror. The reflected version of the mirror is displayed on the screen.
	 */
	public void updateRotatedCoordinates() {
		double[]startingPointCorrdinates= {x1,y1}; //array of mirror's inital starting point. The x and y-coordinate of the mirror's initial starting point are stored in the array.
		double[]endingPointCoordinates= {x2,y2}; //array of mirror's inital ending point. The x and y-coordinate of the mirror's initial ending point are stored in the array.
		double angle_radians = currTheta*Math.PI/180; //angle of the mirror in radians
		Point2D.Double[]rotatedPoints=rotate(startingPointCorrdinates,endingPointCoordinates,angle_radians); //array that stores the points of the reflected version of the mirror based on its initial starting point coordinates, initial ending point coordinates, and current theta (angle).
		//The coordinates of the start and end points of the current reflected version of the mirror are updated
		currRotatedX1=rotatedPoints[0].x;
		currRotatedY1=rotatedPoints[0].y;
		currRotatedX2=rotatedPoints[1].x;
		currRotatedY2=rotatedPoints[1].y;
	}
	
	/**
	 * Used to find the points of the reflected version of the mirror based on its initial starting point coordinates, initial ending point coordinates, and current theta (angle).
	 * @param startingPointCoordinates - the x and y-coordinates of the initial starting point of the mirror
	 * @param endingPointCoordinates - the x and y-coordinates of the initial ending point of the mirror
	 * @param theta - the current theta (angle) of the mirror
	 * @return
	 */
	public Point2D.Double[] rotate(double[]startingPointCoordinates, double[]endingPointCoordinates, double theta) {
	    // startingPointCoordinates and endingPointCoordinates are arrays of length 2 with the x, y coordinates of
	    // the inital starting and endings points of the mirror with the form [x, y]

	    double[]midpoint = { //array to store the x and y-coordinates of the mirror's midpoint
	        (startingPointCoordinates[0] + endingPointCoordinates[0])/2,
	        (startingPointCoordinates[1] + endingPointCoordinates[1])/2
	    };

	    //The midpoint is made the origin
	    double[] startingPointCoordinates_mid = {
	        startingPointCoordinates[0] - midpoint[0],
	        startingPointCoordinates[1] - midpoint[1]
	    };
	    double[]endingPointCoordinates_mid = {
	        endingPointCoordinates[0] - midpoint[0],
	        endingPointCoordinates[1] - midpoint[1]
	    };
	    
	    double[]startingPointCoordinates_rotated = { //array to store the x and y-coordinates of the mirror's rotated initial starting point based on its current theta (angle).
	        Math.cos(theta)*startingPointCoordinates_mid[0] - Math.sin(theta)*startingPointCoordinates_mid[1],
	        Math.sin(theta)*startingPointCoordinates_mid[0] + Math.cos(theta)*startingPointCoordinates_mid[1]
	    };
	    
	    double[]endingPointCoordinates_rotated = { //array to store the x and y-coordinates of the mirror's rotated initial ending point based on its current theta (angle).
	        Math.cos(theta)*endingPointCoordinates_mid[0] - Math.sin(theta)*endingPointCoordinates_mid[1],
	        Math.sin(theta)*endingPointCoordinates_mid[0] + Math.cos(theta)*endingPointCoordinates_mid[1]
		};

	    // The midpoint coordinates are returned to the previous origin
	    startingPointCoordinates_rotated[0] = startingPointCoordinates_rotated[0] + midpoint[0];
	    startingPointCoordinates_rotated[1] = startingPointCoordinates_rotated[1] + midpoint[1];
	    endingPointCoordinates_rotated[0] = endingPointCoordinates_rotated[0] + midpoint[0];
	    endingPointCoordinates_rotated[1] = endingPointCoordinates_rotated[1] + midpoint[1];
	    
	    //array to stores the rotated starting and ending points of the mirror
	    Point2D.Double[]rotatedPoints= {new Point2D.Double(startingPointCoordinates_rotated[0],startingPointCoordinates_rotated[1]),new Point2D.Double(endingPointCoordinates_rotated[0],endingPointCoordinates_rotated[1])};

	    return rotatedPoints;
	}
}

/*package main;

import java.awt.Color;

public class Mirror extends Surface {
	
	static final Color MIRROR_COLOR = new Color(0xb0fffe); 

	public Mirror(double x1, double y1, double x2, double y2) {
		super(x1, y1, x2, y2, MIRROR_COLOR);
	}
}
*/
