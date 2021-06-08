package main;

import java.awt.Color;

public class Level3 extends Level {

	public Level3(GameManager gameManager)
	{
		super(gameManager, 3);
		
		emitters.add(new Emitter(50, 50, 0, Color.RED));
		sensors.add(new Sensor(950, 400, Color.RED));
		surfaces.add(new Blackbody(400, 50, 400, 600));
		
		startingNumSurfaces = surfaces.size();
	}
}
