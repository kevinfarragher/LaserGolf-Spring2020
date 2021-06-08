package main;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A beam splitter is composed of a sensor and two or more emitters. When the sensor is hit by a laser, each emitter fires.
 */
public class BeamSplitter
{
	/**
	 * The level on which the beam splitter is a component.
	 */
	private Level level;
	
	/**
	 * The sensor that controls activation of the beam splitter's emitter components.
	 */
	public Sensor sensor;
	
	/**
	 * The emitters to be activated by the sensor.
	 */
	public List<Emitter> emitters;
	
	/**
	 * Constructs a beam splitter
	 * @param sensor
	 * @param emitters
	 */
	public BeamSplitter(Sensor sensor, List<Emitter> emitters)
	{
		this.sensor = sensor;
		this.emitters = emitters;
	}
	
	/**
	 * Constructs a beam splitter
	 * @param sensor
	 * @param emitters
	 */
	public BeamSplitter(Sensor sensor, Emitter... emitters)
	{
		this(sensor, new ArrayList<>(Arrays.asList(emitters)));
	}

	/**
	 * Checks whether the sensor has been hit and, if so, fires each emitter
	 * @param level - the level the veam splitter is associated with
	 */
	public void checkForHit(Level level) {
		if(sensor.checkForHit(level.lasers)) { 
			for(int i = 0; i < emitters.size(); i++) {
				emitters.get(i).fire(level.lasers);
			}
		}
	}
	
	public void draw(Graphics2D g) {
		sensor.draw(g);
		for(int i = 0; i < emitters.size(); i++) {
			emitters.get(i).draw(g); 
		}
	}	
}
