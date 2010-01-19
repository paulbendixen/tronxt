package tronxt.nxt;

import lejos.nxt.*;
import tronxt.core.*;

public class Program {

	public static void main(String[] args) {
		
		Button.ESCAPE.addButtonListener(new ButtonListener() {

			@Override
			public void buttonPressed(Button button) {
				System.exit(0);
			}
			
			@Override
			public void buttonReleased(Button button) {}
		});
		
		LCD.clearDisplay();
		LCD.drawString("Choose type:", 0, 0);
		LCD.drawString("< : CPU Player", 0, 1);
		LCD.drawString("> : Human Player", 0, 2);
		
		Player player = null;
		String name = "Kevin Flynn";
		
		while (player == null) {
			switch (Button.waitForPress()) {
			case Button.ID_LEFT:
				LCD.clearDisplay();
				LCD.drawString("Choose type:", 0, 0);
				LCD.drawString("< : Middleman", 0, 1);
				LCD.drawString("> : Randy Turner", 0, 2);
				while (player == null) {
					switch (Button.waitForPress()) {
					case Button.ID_LEFT:
						player = new CenterCPUControlledPlayer(name);
						name = "Middleman";
						break;
					case Button.ID_RIGHT:
						player = new RandomCPUControlledPlayer(name);
						name = "Randy Turner";
						break;
					case Button.ID_ENTER:
						LCD.drawString("Press < or >", 0, 4);
						break;
					}
				}
				break;
			case Button.ID_RIGHT:
				player = new BTControlledPlayer(name);
				break;
			case Button.ID_ENTER:
				LCD.drawString("Press < or >", 0, 4);
				break;
			}
		}
		
		player.register();
		
		player.start();
	}
}
