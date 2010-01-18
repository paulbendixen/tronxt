package tronxt.core;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Sound;
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
		conn = Bluetooth.waitForConnection(0, NXTConnection.RAW);
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
			default:
				LCD.drawChar((char)buffer[0],0,0,false);
			}
		}
	}
	
	@Override
	public void die()
	{
		if (conn != null) {
			conn.write(new byte[] {'d'}, 1);
			conn.close();
		}

		bike.stop();

		LCD.clearDisplay();
		LCD.drawString("DD  EEE AAAA DD ", 0, 0);
		LCD.drawString("D D E   A  A D D", 0, 1);
		LCD.drawString("D D EE  AAAA D D", 0, 2);
		LCD.drawString("D D EE  AAAA D D", 0, 3);
		LCD.drawString("D D E   A  A D D", 0, 4);
		LCD.drawString("DD  EEE A  A DD ", 0, 5);
		LCD.drawString("                ", 0, 6);
		LCD.drawString("ENTER to restart", 0, 7);
		

		// Play music, non blocking
		Sound.playNote(Sound.FLUTE, 440, 100);
		Sound.playNote(Sound.FLUTE, 420, 100);
		Sound.playNote(Sound.FLUTE, 400, 100);
		Sound.playNote(Sound.FLUTE, 380, 100);
		Sound.playNote(Sound.FLUTE, 360, 100);
		Sound.playNote(Sound.FLUTE, 340, 100);
		Sound.playNote(Sound.FLUTE, 320, 100);
		Sound.playNote(Sound.FLUTE, 300, 500);
		
		Button.ENTER.waitForPressAndRelease();
	}
	
	@Override
	public void win()
	{
		if (conn != null) {
			conn.write(new byte[] {'d'}, 1);
			conn.close();
		}

		bike.stop();

		LCD.clearDisplay();
		LCD.drawString("W  W  W III N  N", 0, 0);
		LCD.drawString("W  W  W  I  N  N", 0, 1);
		LCD.drawString("W  W  W  I  NN N", 0, 2);
		LCD.drawString("W  W  W  I  N NN", 0, 3);
		LCD.drawString(" W W W   I  N  N", 0, 4);
		LCD.drawString("  W W   III N  N", 0, 5);
		LCD.drawString("                ", 0, 6);
		LCD.drawString("ENTER to restart", 0, 7);
		
		// Play victory music
		Sound.playNote(Sound.FLUTE, 320, 100);
		Sound.playNote(Sound.FLUTE, 340, 100);
		Sound.playNote(Sound.FLUTE, 360, 100);
		Sound.playNote(Sound.FLUTE, 380, 100);
		Sound.playNote(Sound.FLUTE, 400, 100);
		Sound.playNote(Sound.FLUTE, 420, 100);
		Sound.playNote(Sound.FLUTE, 440, 100);
		
		Button.ENTER.waitForPressAndRelease();
	}
}
