package tronxt.nxt;

import lejos.nxt.*;

public class Bumper extends Thread {

	private TouchSensor touchLeft;
	private TouchSensor touchRight;
	private CrashHandler handler;
	
	public Bumper(SensorPort leftSensorPort, SensorPort rightSensorPort, CrashHandler handler) {
		touchLeft = new TouchSensor(leftSensorPort);
		touchRight = new TouchSensor(rightSensorPort);
		this.handler = handler;
	}
	
	public void run()
	{
		while (true) {
			while (!touchLeft.isPressed() && !touchRight.isPressed()) ;
			handler.tronBikeCrashed();
		}
	}
}
