package tronxt.core;

import lejos.nxt.LCD;
import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;

public abstract class AbstractPlayer implements Player {

	protected BTConnection conn;
	protected TronBike bike;
	private String name;
	
	protected AbstractPlayer(String name) {
		this.name = name;
	}
	
	@Override
	public TronBike getBike() {
		return bike;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void register() {
		LCD.clearDisplay();
		LCD.drawString("Waiting for ", 0, 0);
		LCD.drawString("connection  ", 0, 1);
		conn = Bluetooth.waitForConnection();
		conn.setIOMode(NXTConnection.RAW);
		
		LCD.clearDisplay();
		LCD.drawString("Connected",0,0);
		
		byte[] buffer = new byte[1];
		while (true) {
			conn.read(buffer, 1);
			
			switch(buffer[0]) {
			case 's': //Start game
				conn.write(new byte[] {'o'}, 1);
				return;
			case 'q': //Quit game
				conn.close();
				System.exit(0);
			}
		}
	}
	
	@Override
	public void die()
	{
		LCD.clearDisplay();
		LCD.drawString("DD  EEE AAAA DD ", 0, 0);
		LCD.drawString("D D E   A  A D D", 0, 1);
		LCD.drawString("D D E   A  A D D", 0, 2);
		LCD.drawString("D D EE  AAAA D D", 0, 3);
		LCD.drawString("D D EE  AAAA D D", 0, 4);
		LCD.drawString("D D E   A  A D D", 0, 5);
		LCD.drawString("D D E   A  A D D", 0, 6);
		LCD.drawString("DD  EEE A  A DD ", 0, 7);
		
		conn.write(new byte[] {'d'}, 1);
		
		// Play music, non blocking
		for (int i = 0; i < 4*3;i++)
		{
			bike.turnRight();
		}
		bike.stop();
	}
	
	@Override
	public void win()
	{
		bike.stop();
		// Play victory music
	}
}
