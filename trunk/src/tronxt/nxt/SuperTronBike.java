package tronxt.nxt;

import tronxt.core.*;
import lejos.nxt.*;

public class SuperTronBike implements TronBike {

	private static final int ROTATE_ANGLE = 180; 
	private static final int POWER = 100;
	
	private Motor motorRight = Motor.A;
	private Motor motorLeft = Motor.B;
	private Bumper bumper;
	private WallDetector wallDetector;
	
	public SuperTronBike(final Player player) {
		CrashHandler handler = new CrashHandler() {
			
			@Override
			public void tronBikeCrashed() {
				player.die();
			}
		};
		bumper = new Bumper(SensorPort.S1, SensorPort.S2, handler);
		wallDetector = new WallDetector(SensorPort.S3, handler);
		
		motorRight.setPower(POWER);
		motorLeft.setPower(POWER);
	}

	public void start() {
		bumper.start();
		wallDetector.start();
		forward();
	}
	
	private void forward() {
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
		motorRight.rotate(ROTATE_ANGLE, true);
		motorLeft.rotate(-ROTATE_ANGLE);
		forward();
	}

	public void turnRight() {
		motorLeft.rotate(ROTATE_ANGLE, true);
		motorRight.rotate(-ROTATE_ANGLE);
		forward();
	}
}
