package main;

import java.awt.Color;

public class Level13 extends Level {
	public Level13(GameManager gameManager)
	{
		super(gameManager, 2);
		
		colorfilters.add(new ColorFilter(400, 100, 20, 50, Color.BLUE));
		colorfilters.add(new ColorFilter(400, 200, 20, 50, Color.GREEN));
		colorfilters.add(new ColorFilter(400, 300, 20, 50, Color.RED));
		emitters.add(new Emitter(50, 200, 0, Color.magenta));
		sensors.add(new Sensor(915, 200, Color.red));
		explosives.add(new Explosive(600, 200, this));
		
		startingNumSurfaces = surfaces.size();
	}
}
