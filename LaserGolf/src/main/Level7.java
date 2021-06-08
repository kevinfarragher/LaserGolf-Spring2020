package main;

import java.awt.Color;

public class Level7 extends Level {
	public Level7(GameManager gameManager) {
		super(gameManager, 2);
		
		emitters.add(new Emitter(100,200,0,Color.magenta));
		sensors.add(new Sensor(850,100, Color.red));
		sensors.add(new Sensor(900,300, Color.blue));
		
		double bspx = 300, bspy = 200, bsp_fov = 20, bsp_dist = 5; 
		beamsplitters.add(new BeamSplitter(new Sensor(bspx, bspy, Color.magenta), new Emitter(bspx + bsp_dist, bspy - bsp_fov, 0.0, Color.red), new Emitter(bspx + bsp_dist, bspy + bsp_fov, 0.0, Color.blue)));
	
		startingNumSurfaces = surfaces.size();
	}
}
