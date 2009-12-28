package tronxt.nxt;

import tronxt.core.*;
import lejos.nxt.*;

public class WallDetector extends Thread {

	private LightSensor lightSensor;
	private CrashHandler handler;
	private BTControlledPlayer player;
	private int threshold;
	
	public WallDetector(SensorPort lightSensorPort, BTControlledPlayer player) {
		this.player = player;
		lightSensor = new LightSensor(lightSensorPort);
		configure();
	}
	
	private void configure() {
		LCD.drawString("Configure light detector:", 0, 0);
		LCD.drawString("Place light sensor on a white surface and press ENTER", 0, 2);
		Button.ENTER.waitForPressAndRelease();
		int whiteValue = lightSensor.getLightValue();
		
		LCD.clearDisplay();
		LCD.drawString("Configure light detector:", 0, 0);
		LCD.drawString("White:", 0, 1);
		LCD.drawInt(whiteValue, 8, 1);
		LCD.drawString("Place light sensor on a line and press ENTER", 0, 2);
		Button.ENTER.waitForPressAndRelease();
		int lineValue = lightSensor.getLightValue();
		
		threshold = ((whiteValue - lineValue) / 2) + lineValue;
		
		LCD.clearDisplay();
		LCD.drawString("Configure light detector:", 0, 0);
		LCD.drawString("White:", 0, 1);
		LCD.drawInt(whiteValue, 13, 1);
		LCD.drawString("Line:", 0, 2);
		LCD.drawInt(lineValue, 13, 2);
		LCD.drawString("Threshold:", 0, 3);
		LCD.drawInt(threshold, 13, 3);
		LCD.drawString("Press ENTER to continue", 0, 5);
		Button.ENTER.waitForPressAndRelease();
	}
	
	public void addCrashHandler(CrashHandler handler) {
		this.handler = handler;
	}

	public void run() {
		while (lightSensor.getLightValue() < threshold);
		handler.tronBikeCrashed(player);
	}
}