package tronxt.nxt;

import lejos.nxt.SensorPort;
import tronxt.core.*;

public class CPUControlledPlayer extends AbstractPlayer {

	private WallDetector detector;
	
	public CPUControlledPlayer(String name) {
		super(name);
		bike = new SuperTronBike(this);
		
		CrashHandler handler = new CrashHandler() {
			
			@Override
			public void tronBikeCrashed() {
				if (Math.random() > 0.5) {
					bike.turnLeft();
				} else {
					bike.turnRight();
				}
			}
		};
		
		detector = new WallDetector(SensorPort.S4, handler);
	}
	
	public void start() {
		detector.start();
		bike.start();
	}
}
