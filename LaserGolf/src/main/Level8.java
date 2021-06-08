package main;

import java.awt.Color;

public class Level8 extends Level {
	public Level8(GameManager gameManager)
	{
		super(gameManager, 1);

		emitters.add(new Emitter(500, 400, 3 * Math.PI / 2, Color.GREEN));
		beamsplitters.add(new BeamSplitter(new Sensor(500, 200, Color.GREEN),
				new Emitter(490, 200, Math.PI, Color.RED),
				new Emitter(510, 200, 0, Color.RED)));
		explosives.add(new Explosive(700, 200, this));
		sensors.add(new Sensor(300, 200, Color.RED));
		
		startingNumSurfaces = surfaces.size();
	}
}
