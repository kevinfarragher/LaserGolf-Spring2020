package main;

import java.awt.Color;

public class Level1fjkegsbk extends Level {

	/**
	 * @see java.io.Serializable
	 */
	private static final long serialVersionUID = -6440896181752090831L;

	/**
	 * Constructs level 1 of the game with a given driver and gameManager. Objects (emitters, sensors) are added to the level.
	 * @param driver - driver of the program
	 * @param gameManager - gameManager of the program
	 */
	public Level1fjkegsbk(GameManager gameManager) {
		super(gameManager, 0);
		super.emitters.add(new Emitter(100,200,0,Color.GREEN));
		super.sensors.add(new Sensor(850,200, Color.GREEN));
		
		startingNumSurfaces = surfaces.size();
	}

}
