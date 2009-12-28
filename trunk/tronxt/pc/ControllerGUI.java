package tronxt.pc;

import java.awt.event.*;
import javax.swing.*;

public class ControllerGUI {

	private HumanControlledPlayer controller;
	private final ControllerGUI gui;
	private JLabel label;
	
	public ControllerGUI(HumanControlledPlayer controller) {
		this.controller = controller;
		gui = this;
	}
	
	public void show() {
		JFrame frame = new JFrame("TroNXT");
		
		Box box = new Box(BoxLayout.Y_AXIS);
		frame.add(box);
		
		JPanel buttonPanel = new JPanel();
		box.add(buttonPanel);

		JButton left = new JButton("Left");
		buttonPanel.add(left);
		left.addActionListener(new ActionListener() { 
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gui.leftPressed();
			}
		});
		
		JButton right = new JButton("Right");
		buttonPanel.add(right);
		right.addActionListener(new ActionListener() { 
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gui.rightPressed();
			}
		});

		JPanel textPanel = new JPanel();
		box.add(textPanel);
		
		label = new JLabel("Use the buttons to control your bike.");
		textPanel.add(label);
		
		KeyListener arrowKeys = new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				label.setText("key event...");
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					gui.leftPressed();
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					gui.rightPressed();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {}

			@Override
			public void keyTyped(KeyEvent e) {}
		};
		left.addKeyListener(arrowKeys);
		right.addKeyListener(arrowKeys);
		
		
		frame.validate();
		frame.pack();
		frame.setVisible(true);
	}
	
	public void leftPressed() {
		label.setText("Left pressed");
		controller.turnLeft();
	}

	public void rightPressed() {
		label.setText("Right pressed");
		controller.turnRight();
	}
}
