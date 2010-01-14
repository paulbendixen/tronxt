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
		
		byte[] buffer = new byte[1];
		while (true) {
			conn.read(buffer, 1);
			LCD.clearDisplay();
			
			switch(buffer[0]) {
			case 'l': //Turn left
				LCD.drawString("Turning left", 0, 0);
				bike.turnLeft();
				break;
			case 'r': //Turn right
				LCD.drawString("Turning right", 0, 0);
				bike.turnRight();
				break;
			case 'q': //Quit game
				LCD.drawString("Exiting", 0, 0);
				conn.close();
				System.exit(0);
			}
			
			conn.write(new byte[] {'o'}, 1);
		}
	}
}
