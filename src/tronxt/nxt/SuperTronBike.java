package tronxt.nxt;

import tronxt.core.*;
import lejos.nxt.*;

public class SuperTronBike implements TronBike {

	private static final int ROTATE_ANGLE = 200; 
	private static final int SPEED = 50;
	
	private Motor motorRight = Motor.A;
	private Motor motorLeft = Motor.B;
	private Bumper bumper;
	private WallDetector wallDetector;
	private final TronBike bike = this;
	
	public SuperTronBike() {
		CrashHandler handler = new CrashHandler() {
			
			@Override
			public void tronBikeCrashed() {
				bike.stop();
			}
		};
		bumper = new Bumper(SensorPort.S1, SensorPort.S2, handler);
		wallDetector = new WallDetector(SensorPort.S3, handler);
		
		motorRight.setSpeed(SPEED);
		motorLeft.setSpeed(SPEED);
	}

	public void start() {
		bumper.start();
		wallDetector.start();
		
		motorRight.forward();
		motorLeft.forward();
	}

	public void stop() {
		bumper.interrupt();
		wallDetector.interrupt();
		
		motorRight.stop();
		motorLeft.stop();
	}

	public void turnLeft() {
		stop();
		motorRight.rotate(ROTATE_ANGLE);
		motorLeft.rotate(-ROTATE_ANGLE);
		start();
	}

	public void turnRight() {
		stop();
		motorLeft.rotate(ROTATE_ANGLE);
		motorRight.rotate(-ROTATE_ANGLE);
		start();
	}
}
