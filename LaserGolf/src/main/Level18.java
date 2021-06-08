package main;

import java.awt.Color;

public class Level18 extends Level {
	public Level18(GameManager gameManager) {
		super(gameManager, 7);
		
		emitters.add(new Emitter(25, 25, 0, Color.GREEN));
		
		beamsplitters.add(new BeamSplitter(new Sensor(35, 300, Color.RED),
				new Emitter(25, 320, Math.PI / 2, Color.BLUE),
				new Emitter(25, 300, -Math.PI / 5, Color.GREEN),
				new Emitter(25, 280, -Math.PI / 2, Color.MAGENTA)));
		
		beamsplitters.add(new BeamSplitter(new Sensor(500, 415, Color.BLUE),
				new Emitter(520, 415, 0, Color.BLUE),
				new Emitter(480, 415, Math.PI, Color.BLUE)));
		
		sensors.add(new Sensor(950, 25, Color.GREEN));
		sensors.add(new Sensor(25, 125, Color.MAGENTA));
		sensors.add(new Sensor(25, 415, Color.BLUE));
		sensors.add(new Sensor(950, 415, Color.BLUE));

		colorfilters.add(new ColorFilter(920, 290, 15, 40, Color.RED));
		
		surfaces.add(new Blackbody(0, 75, 400, 200));
		surfaces.add(new Blackbody(410, 200, 635, 100));
		surfaces.add(new Blackbody(440, 275, 665, 175));
		surfaces.add(new Blackbody(200, 280, 1000, 280));
		surfaces.add(new Mirror(12, 90, 405, 212));
		surfaces.add(new Mirror(408, 213, 645, 107));
		surfaces.add(new Mirror(430, 270, 668, 162));
		surfaces.add(new Mirror(193, 270, 430, 270));
		surfaces.add(new Mirror(193, 290, 987, 290));
		surfaces.add(new Mirror(193, 328, 987, 328));
		surfaces.add(new Mirror(987, 290, 987, 328));
		surfaces.add(new Blackbody(200, 340, 1000, 340));
		
		startingNumSurfaces = surfaces.size();
	}
}
