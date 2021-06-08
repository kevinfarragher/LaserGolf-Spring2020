package main;

import java.awt.Color;

public class Level2 extends Level
{
	
	public Level2(GameManager gameManager)
	{
		super(gameManager, 1);
		
		emitters.add(new Emitter(50, 50, 0, Color.RED));
		sensors.add(new Sensor(950, 400, Color.RED));
		surfaces.add(new Blackbody(300, 350, 700, 100));
		
		startingNumSurfaces = surfaces.size();
	}
}
