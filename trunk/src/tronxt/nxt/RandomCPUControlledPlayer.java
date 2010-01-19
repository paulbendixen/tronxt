package tronxt.nxt;

import lejos.nxt.*;
import tronxt.core.*;

public class RandomCPUControlledPlayer extends AbstractPlayer {

	private WallDetector detector;
	private boolean stopped = true;
	public double probability = 0.5;
	public final RandomCPUControlledPlayer player;
	
	public RandomCPUControlledPlayer(String name) {
		super(name);
		bike = new SuperTronBike(this);
		player = this;
		
		CrashHandler handler = new CrashHandler() {

			@Override
			public void tronBikeCrashed() {
				if (Math.random() > probability) {
					bike.turnLeft();
					player.probability += .25;
				} else {
					bike.turnRight();
					player.probability -= .25;
				}
			}
		};
		
		detector = new WallDetector(SensorPort.S4, handler);
		detector.start();
	}
	
	@Override
	public void die() {
		stopped = true;
		detector.stop();
		super.die();
	}
	
	@Override
	public void win() {
		stopped = true;
		detector.stop();
		super.win();
	}
	
	public void start() {
		super.start();
		LCD.clearDisplay();
		detector.begin();
		bike.start();
		
		stopped = false;
		Sound.pause((int)Math.random()*20000);
		while (!stopped) {
			if (Math.random() > probability) {
				bike.turnLeft();
			} else {
				bike.turnRight();
			}
			Sound.pause((int)(Math.random()*20000)+2000);
		}
	}
}
