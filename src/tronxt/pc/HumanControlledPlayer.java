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

	public void connect() {
		gui.displayText("Connecting to NXT.");
		conn = new NXTConnector();

		// Connect to any NXT over Bluetooth
		boolean connected = conn.connectTo("btspp://");
		if (!connected) {
			gui.displayText("Failed to connect to any NXT");
			return;
		}
		
		gui.displayText("Connected to "+ conn.getNXTInfo().name);
		
		try {
			conn.getNXTComm().write(new byte[] {'s'});
		} catch (IOException e) {}
	}
	
	public void turnLeft() {
		try {
			conn.getNXTComm().write(new byte[] {'l'});
		} catch (IOException e) {}
	}

	public void turnRight() {
		try {
			conn.getNXTComm().write(new byte[] {'r'});
		} catch (IOException e) {}
	}
	
	public void exit() {
		//Send close signal to NXT
		try {
			conn.getNXTComm().write(new byte[] {'q'});
			conn.close();
		} catch (IOException e) {}
		
		System.exit(0);
	}
}
