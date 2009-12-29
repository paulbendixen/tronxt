package tronxt.pc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import tronxt.core.*;
import lejos.pc.comm.*;

public class HumanControlledPlayer {
	
	private ControllerGUI gui;
	
	public HumanControlledPlayer() {
		gui = new ControllerGUI(this);
		gui.show();
	}

	private void makeConnection() {
		gui.displayText("Making connection.");

		//Start BT connection
		NXTConnector conn = new NXTConnector();

		// Connect to any NXT over Bluetooth
		boolean connected = conn.connectTo("btspp://");
		if (!connected) {
			gui.displayText("Failed to connect to any NXT");
			return;
		}
		
		gui.displayText("connected.");

		
		byte[][] data = {{'a'}, {'b'}, {'c'}, {'q'}};
		
		for (byte[] dat : data) {
			try {
				conn.getNXTComm().sendRequest(dat, 0);
				Thread.sleep(1000);
			} catch (Exception e) {}
		}
				
		try {
			conn.close();
		} catch (IOException e) {}
	}
	
	public void turnLeft() {
		makeConnection();
	}

	public void turnRight() {
		
	}
}
