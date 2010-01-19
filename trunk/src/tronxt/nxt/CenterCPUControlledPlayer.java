package tronxt.nxt;

import lejos.nxt.*;
import tronxt.core.*;

public class CenterCPUControlledPlayer extends AbstractPlayer {

	/**
	 * Board size:
	 * 
	 *   Length: 9 rotations ~ 3300 degrees
	 *   Width:  5 rotations ~ 1800 degrees
	 * 
	 */
	
	private WallDetector detector;
	
	public CenterCPUControlledPlayer(String name) {
		super(name);
		bike = new SuperTronBike(this);
		
		CrashHandler handler = new CrashHandler() {

			private int length = -1000;
			private int width = 0;
			private int dirLength = 1;
			private int dirWidth = 0;
			private int oldTacho = 0;

			@Override
			public void tronBikeCrashed() {
				int tacho = bike.getTachoCount() - oldTacho;
				oldTacho += tacho;
				
				length += dirLength * tacho;
				width += dirWidth * tacho;
				
				if (dirLength != 0) {
					if (dirLength * width > 0) {
						bike.turnLeft();
						dirWidth = -dirLength;
					} else {
						bike.turnRight();
						dirWidth = dirLength;
					}
					dirLength = 0;
				} else {
					if (dirWidth * length > 0) {
						bike.turnRight();
						dirLength = -dirWidth;
					} else {
						bike.turnLeft();
						dirLength = dirWidth;
					}
					dirWidth = 0;
				}
			}
		};
		
		detector = new WallDetector(SensorPort.S4, handler);
		detector.start();
	}
	
	@Override
	public void die() {
		detector.stop();
		super.die();
	}
	
	@Override
	public void win() {
		detector.stop();
		super.win();
	}
	
	public void start() {
		super.start();
		LCD.clearDisplay();
		detector.begin();
		bike.start();
	}
}
