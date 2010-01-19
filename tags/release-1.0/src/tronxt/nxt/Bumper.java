package tronxt.nxt;

import lejos.nxt.*;

public class Bumper extends Thread {

	private TouchSensor touchLeft;
	private TouchSensor touchRight;
	private CrashHandler handler;
	private boolean runnable = false;
	
	public Bumper(SensorPort leftSensorPort, SensorPort rightSensorPort, CrashHandler handler) {
		touchLeft = new TouchSensor(leftSensorPort);
		touchRight = new TouchSensor(rightSensorPort);
		this.handler = handler;
	}
	
	public void stop() {
		runnable = false;
	}
	
	public void begin() {
		runnable = true;
	}
	
	public void run()
	{
		while (true) {
			while (!touchLeft.isPressed() && !touchRight.isPressed());
			if (runnable) handler.tronBikeCrashed();
		}
	}
}
