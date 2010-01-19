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
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Box box = new Box(BoxLayout.Y_AXIS);
		frame.add(box);
		
		JPanel steeringPanel = new JPanel();
		box.add(steeringPanel);

		JButton left = new JButton("Left");
		steeringPanel.add(left);
		left.addActionListener(new ActionListener() { 
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gui.leftPressed();
			}
		});
		
		JButton right = new JButton("Right");
		steeringPanel.add(right);
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
				switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					gui.leftPressed();
					break;
				case KeyEvent.VK_RIGHT:
					gui.rightPressed();
					break;
				case KeyEvent.VK_ESCAPE:
					controller.exit();
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
		displayText("Left pressed");
		controller.turnLeft();
	}

	public void rightPressed() {
		displayText("Right pressed");
		controller.turnRight();
	}
	
	public void displayText(String text) {
		label.setText(text);
	}
}
