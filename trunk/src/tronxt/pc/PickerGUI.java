package tronxt.pc;

import java.awt.event.*;
import javax.swing.*;

import lejos.pc.comm.*;

public class PickerGUI {
	
	private JFrame frame;
	private JList list;
	private PickerGUI gui = this;

	public void show() {
		
		frame = new JFrame("Pick A Brick");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Box box = new Box(BoxLayout.Y_AXIS);
		frame.add(box);
        
		list = new JList();
		box.add(list);
		
		JPanel connectionPanel = new JPanel();
		box.add(connectionPanel);
		
		JButton refresh = new JButton("Refresh");
		connectionPanel.add(refresh);
		refresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gui.populateList();
			}
		});
		
		JButton connect = new JButton("Connect");
		connectionPanel.add(connect);
		connect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedValue() != null) {
					gui.connectToNXT((NXTInfo) list.getSelectedValue());
				}
			}
		});
		
		frame.validate();
		frame.pack();
		frame.setVisible(true);
	}
	
	public void populateList() {
		NXTConnectionManager connectionManager = new NXTConnectionManager();
		NXTInfo[] infos = connectionManager.search();
		
		DefaultListModel model = new DefaultListModel();
		list.setModel(model);
		
		for (NXTInfo info : infos) {
			model.addElement(new NXTInfoPresenter(info));
			list.invalidate();
		}
		
		frame.validate();
		frame.pack();
		frame.setVisible(true);
	}
	
	public void connectToNXT(NXTInfo info) {
		HumanControlledPlayer player = new HumanControlledPlayer();
		player.connect(info);
	}
	
	public class NXTInfoPresenter extends NXTInfo {
		public NXTInfoPresenter(NXTInfo info) {
			super(info);
		}
		
		@Override
		public String toString() {
			return name +", "+ deviceAddress +" ("+ connectionState +")";
		}
	}
}

