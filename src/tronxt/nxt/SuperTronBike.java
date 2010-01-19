package tronxt.nxt;

import tronxt.core.*;
import lejos.nxt.*;

public class SuperTronBike implements TronBike {

	private static final int ROTATE_ANGLE = 158;
	private static final int POWER = 100;
	
	private Motor motorRight = Motor.A;
	private Motor motorLeft = Motor.B;
	private Bumper bumper;
	private WallDetector wallDetector;
	private boolean stopped = true;
	
	public SuperTronBike(final Player player) {
		CrashHandler handler = new CrashHandler() {
			
			@Override
			public void tronBikeCrashed() {
				player.die();
				player.register();
				player.start();
			}
		};
		bumper = new Bumper(SensorPort.S1, SensorPort.S2, handler);
		bumper.start();
		wallDetector = new WallDetector(SensorPort.S3, handler);
		wallDetector.start();
		
		motorRight.setPower(POWER);
		motorLeft.setPower(POWER);
	}

	public void start() {
		stopped = false;
		bumper.begin();
		wallDetector.begin();
		
		forward();
	}
	
	private void forward() {
		if (!stopped) {
			motorRight.forward();
			motorLeft.forward();
		}
	}

	public void stop() {
		stopped = true;
		bumper.stop();
		wallDetector.stop();
		
		motorRight.stop();
		motorLeft.stop();
	}
	
	public int getTachoCount() {
		return (motorLeft.getTachoCount() + motorRight.getTachoCount()) / 2;
	}

	public synchronized void turnLeft() {
		if (!stopped) {
			motorRight.rotate(ROTATE_ANGLE, true);
			motorLeft.rotate(-ROTATE_ANGLE);
			forward();
		}
	}

	public synchronized void turnRight() {
		if (!stopped) {
			motorLeft.rotate(ROTATE_ANGLE, true);
			motorRight.rotate(-ROTATE_ANGLE);
			forward();
		}
	}
}
