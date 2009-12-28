package tronxt.pc;

public class Program {

	public static void main(String[] args) {
		ControllerGUI gui = new ControllerGUI(new HumanControlledPlayer());
		gui.show();
	}

}
