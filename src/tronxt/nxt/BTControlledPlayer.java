package tronxt.nxt;

import lejos.nxt.LCD;
import tronxt.core.*;

public class BTControlledPlayer extends AbstractPlayer {

	public BTControlledPlayer(String name) {
		super(name);
		bike = new SuperTronBike(this);
	}
	
	public void start() {
		super.start();
		bike.start();
		
		LCD.clearDisplay();
		
		byte[] buffer = new byte[1];
		while (true) {
			conn.read(buffer, 1);
			
			switch(buffer[0]) {
			case 'l':
				bike.turnLeft();
				break;
			case 'r':
				bike.turnRight();
				break;
			case 'q': //Quit game
				conn.close();
				System.exit(0);
			}
		}
	}
}
