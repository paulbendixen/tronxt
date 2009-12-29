package tronxt.nxt;

import lejos.nxt.LCD;
import lejos.nxt.comm.*;
import tronxt.core.*;

public class BTControlledPlayer extends AbstractPlayer {

	public BTControlledPlayer(String name) {
		super(name, new SuperTronBike());
	}
	
	@Override
	public void register() {
		LCD.clearDisplay();
		LCD.drawString("Wait for connetion",0,0);
		BTConnection btc = Bluetooth.waitForConnection();
		btc.setIOMode(NXTConnection.RAW);
		
		
		int len = 1;
		byte[] buffer = {'a'};
		while (buffer[0] != 'q') {
			btc.read(buffer, len);
			LCD.clearDisplay();
			LCD.drawString(buffer.toString(), 0, 0);
		}
		
		
		btc.close();
	}
		
}
