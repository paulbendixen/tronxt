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
	
	public SuperTronBike(BTControlledPlayer player) {
		bumper = new Bumper(SensorPort.S1, SensorPort.S2, player);
		wallDetector = new WallDetector(SensorPort.S3, player);
		
		motorRight.setSpeed(SPEED);
		motorLeft.setSpeed(SPEED);
	}

	public void addCrashHandler(CrashHandler handler) {
		bumper.addCrashHandler(handler);
		wallDetector.addCrashHandler(handler);
	}

	public void start() {
		motorRight.forward();
		motorLeft.forward();
	}

	public void stop() {
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
