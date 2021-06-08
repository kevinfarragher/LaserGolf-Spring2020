package main;

import java.awt.Color;

public class Level9 extends Level {
	public Level9(GameManager gameManager)
	{
		super(gameManager, 2);

		emitters.add(new Emitter(60,60,Math.PI/2,Color.red));
		
		surfaces.add(new Mirror(50, 310, 100, 360));
		surfaces.add(new Mirror(460, 360, 510, 310));
		
		surfaces.add(new Blackbody(300, 0, 300, 250));
		surfaces.add(new Blackbody(600, 500, 600, 300));
		
		explosives.add(new Explosive(465, 60, this)); 
		
		sensors.add(new Sensor(900, 400, Color.red));
		
		startingNumSurfaces = surfaces.size();
	}
}
