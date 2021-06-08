package main;

import java.awt.Color;

public class Level12 extends Level {
	public Level12(GameManager gameManager)
	{
		super(gameManager, 2);
		
		emitters.add(new Emitter(50, 50, 0, Color.GREEN));
		emitters.add(new Emitter(50, 400, 0, Color.RED));
		sensors.add(new Sensor(800, 50, Color.RED));
		sensors.add(new Sensor(800, 400, Color.GREEN));
		surfaces.add(new Blackbody(400, 100, 400, 350));
		
		startingNumSurfaces = surfaces.size();
	}
}
