package tronxt.nxt;

import lejos.nxt.LCD;
import tronxt.core.*;

public class BTControlledPlayer extends AbstractPlayer {

	public BTControlledPlayer(String name) {
		super(name);
		bike = new SuperTronBike(this);
	}
	
	public void start() {
		bike.start();
		
		int len = 1;
		byte[] buffer = new byte[1];
		while (true) {
			conn.read(buffer, len);
			
			switch(buffer[0]) {
			case 'l': //Turn left
				bike.turnLeft();
				break;
			case 'r': //Turn right
				bike.turnRight();
				break;
			case 'q': //Quit game
				conn.close();
				System.exit(0);
			}

			LCD.clearDisplay();
			LCD.drawChar((char)buffer[0], 0, 0, false);
		}
	}
}
