package main;

import java.awt.Color;

public class Level5 extends Level {
	public Level5(GameManager gameManager)
	{
		super(gameManager, 3);
		
		emitters.add(new Emitter(100, 200, 0, Color.GREEN));
		sensors.add(new Sensor(850, 200, Color.GREEN));
		explosives.add(new Explosive(400, 200, this));
		
		startingNumSurfaces = surfaces.size();
	}
}
