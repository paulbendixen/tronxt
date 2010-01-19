package tronxt.pc;

import java.io.IOException;

import lejos.pc.comm.*;

public class HumanControlledPlayer {
	
	private ControllerGUI gui;
	private NXTConnector conn;
	
	public HumanControlledPlayer() {
		gui = new ControllerGUI(this);
		gui.show();
	}
	
	private void sendCommand(char command) {
		try {
			conn.getNXTComm().write(new byte[] {(byte) command});
			switch(conn.getNXTComm().read()[0]) {
			case 'o': //OK
				break;
			case 'd': //Dead
				gui.displayText("You're dead!");
				break;
			case 'w': //Winner
				gui.displayText("You are the champion");
				break;
			}
		} catch (IOException e) {
			gui.displayText(e.getMessage());
		}
	}

	public void connect(NXTInfo info) {
		gui.displayText("Connecting to NXT.");
		conn = new NXTConnector();

		// Connect to any NXT over Bluetooth
		boolean connected = conn.connectTo("btspp://");
		//boolean connected = conn.connectTo(info, NXTComm.RAW);
		
		if (!connected) {
			gui.displayText("Failed to connect to any NXT");
			return;
		}
		
		gui.displayText("Connected to "+ conn.getNXTInfo().name);
		
		sendCommand('s');
	}
	
	public void turnLeft() {
		sendCommand('l');
	}

	public void turnRight() {
		sendCommand('r');
	}
	
	public void exit() {
		//Send close signal to NXT
		try {
			conn.getNXTComm().write(new byte[] {'q'});
			conn.close();
		} catch (Exception e) {}
		
		System.exit(0);
	}
}
