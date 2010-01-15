package tronxt.nxt;

import tronxt.core.*;
import lejos.nxt.*;

public class SuperTronBike implements TronBike {

	//180 == 8/7
	//180/8 = 22,5
	//22,5*7 = 90+45+22,5 = 135+22,5 = 157,5
	
	private static final int ROTATE_ANGLE = 158; //180
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
