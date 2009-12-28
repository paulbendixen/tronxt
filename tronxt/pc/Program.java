package tronxt.pc;

import tronxt.core.*;

public class Program {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ControllerGUI gui = new ControllerGUI(new GameController());

		gui.show();
	}

}
