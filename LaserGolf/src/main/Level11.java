package main;

import java.awt.Color;

public class Level11 extends Level {
	public Level11(GameManager gameManager)
	{
		super(gameManager, 5);
		
		emitters.add(new Emitter(25, 25, Math.PI / 4, Color.RED));
		surfaces.add(new Blackbody(70, 100, 100, 70));
		surfaces.add(new Blackbody(900, 0, 900, 100));
		beamsplitters.add(new BeamSplitter(new Sensor(300, 200, Color.RED),
				new Emitter(305, 195, -Math.PI / 4, Color.GREEN),
				new Emitter(310, 200, 0, Color.BLUE)));
		sensors.add(new Sensor(950, 25, Color.GREEN));
		sensors.add(new Sensor(950, 410, Color.BLUE));
		
		startingNumSurfaces = surfaces.size();
	}
}
