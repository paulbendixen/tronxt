package tronxt.pc;

import tronxt.core.*;
import java.awt.event.*;
import javax.swing.*;

public class ControllerGUI {

	private GameController controller;
	
	public ControllerGUI(GameController controller) {
		this.controller = controller;
	}
	
	public void show() {
		JFrame frame = new JFrame("TroNXT");
		
		JPanel panel = new JPanel();
		
		JButton left = new JButton("Left");
		left.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				
			}});
		panel.add(left);
		
		JButton right = new JButton("Right");
		right.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				
			}});
		panel.add(right);

		frame.add(panel);
		
		frame.validate();
		frame.pack();
		frame.setVisible(true);
	}
}
