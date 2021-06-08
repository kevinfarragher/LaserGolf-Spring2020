package main;

import java.awt.Color;

public class Level10 extends Level {
	public Level10(GameManager gameManager)
	{
		super(gameManager, 4);
		
		emitters.add(new Emitter(50, 50, 0, Color.magenta));
		sensors.add(new Sensor(915, 400, Color.magenta));
		surfaces.add(new Blackbody(500, 0, 500, 300));
		surfaces.add(new Blackbody(250, 125, 750, 125));
		surfaces.add(new Mirror(900, 100,900,430));
		surfaces.add(new Mirror(950, 100,950,430));
		surfaces.add(new Mirror(900, 430,950,430));
		explosives.add(new Explosive(485, 350, this));
		
		startingNumSurfaces = surfaces.size();
	}
}
