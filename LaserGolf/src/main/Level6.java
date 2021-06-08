package main;

import java.awt.Color;

public class Level6 extends Level {
	public Level6(GameManager gameManager)
	{
		super(gameManager, 5);
		emitters.add(new Emitter(50, 50, 0, Color.RED));
		sensors.add(new Sensor(950, 400, Color.RED));
		surfaces.add(new Blackbody(200, 0, 200, 250));
		surfaces.add(new Blackbody(400, 450, 400, 200));
		surfaces.add(new Blackbody(600, 0, 600, 250));
		surfaces.add(new Blackbody(800, 450, 800, 200));
		
		startingNumSurfaces = surfaces.size();
	}
}
