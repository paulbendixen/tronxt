package tronxt.pc;

public class Program {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ControllerGUI gui = new ControllerGUI(new HumanControlledPlayer());

		gui.show();
	}

}
