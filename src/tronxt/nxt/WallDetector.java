package tronxt.nxt;

import lejos.nxt.*;

public class WallDetector extends Thread {

	private LightSensor lightSensor;
	private CrashHandler handler;
	private int threshold;
	
	public WallDetector(SensorPort lightSensorPort, CrashHandler handler) {
		lightSensor = new LightSensor(lightSensorPort);
		this.handler = handler;
		configure();
	}
	
	private void configure() {
		LCD.clearDisplay();
		LCD.drawString("Configure light:", 0, 0);
		LCD.drawString("Place sensor on ", 0, 2);
		LCD.drawString("a white surface ", 0, 3);
		LCD.drawString("and press ENTER ", 0, 4);
		Button.ENTER.waitForPressAndRelease();
		int whiteValue = lightSensor.getLightValue();
		
		LCD.clearDisplay();
		LCD.drawString("Configure light:", 0, 0);
		LCD.drawString("White:", 0, 1);
		LCD.drawInt(whiteValue, 13, 1);
		LCD.drawString("Place sensor on ", 0, 2);
		LCD.drawString("a line and press", 0, 3);
		LCD.drawString("ENTER           ", 0, 4);
		Button.ENTER.waitForPressAndRelease();
		int lineValue = lightSensor.getLightValue();
		
		threshold = ((whiteValue - lineValue) / 2) + lineValue;
		
		LCD.clearDisplay();
		LCD.drawString("Configure light:", 0, 0);
		LCD.drawString("White:", 0, 1);
		LCD.drawInt(whiteValue, 13, 1);
		LCD.drawString("Line:", 0, 2);
		LCD.drawInt(lineValue, 13, 2);
		LCD.drawString("Threshold:", 0, 3);
		LCD.drawInt(threshold, 13, 3);
		LCD.drawString("Press ENTER     ", 0, 5);
		Button.ENTER.waitForPressAndRelease();
	}
	
	public void run() {
		while (lightSensor.getLightValue() > threshold);
		handler.tronBikeCrashed();
	}
}