package tronxt.nxt;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import tronxt.core.*;

public class Program {

	public static void main(String[] args) {
		
		LCD.clearDisplay();
		LCD.drawString("Choose type:", 0, 0);
		LCD.drawString("< : CPU Player", 0, 1);
		LCD.drawString("> : Human Player", 0, 2);
		
		Player player = null;
		String name = "Player1";
		while (player == null) {
			switch (Button.waitForPress()) {
			case Button.ID_LEFT:
				player = new CPUControlledPlayer(name);
				break;
			case Button.ID_RIGHT:
				player = new BTControlledPlayer(name);
				break;
			case Button.ID_ESCAPE:
				return;
			case Button.ID_ENTER:
				LCD.drawString("Press < or >", 0, 4);
				break;
			}
		}
		
		player.register();
		
		LCD.clearDisplay();
		LCD.drawString("Playing...", 0, 0);
		LCD.drawString("ESCAPE to exit", 0, 8);

		while (!Button.ESCAPE.isPressed()) {}
	}
}
