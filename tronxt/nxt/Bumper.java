package tronxt.nxt;

import tronxt.core.*;
import lejos.nxt.*;

public class Bumper extends Thread {

	private TouchSensor touchLeft;
	private TouchSensor touchRight;
	private CrashHandler handler;
	private BTControlledPlayer player;
	
	public Bumper(SensorPort leftSensorPort, SensorPort rightSensorPort, BTControlledPlayer player) {
		this.player = player;
		touchLeft = new TouchSensor(leftSensorPort);
		touchRight = new TouchSensor(rightSensorPort);
	}
	
	public void addCrashHandler(CrashHandler handler) {
		this.handler = handler;
	}

	public void run()
	{
		while (!touchLeft.isPressed() && !touchRight.isPressed()) ;
		handler.tronBikeCrashed(player);
	}
}
