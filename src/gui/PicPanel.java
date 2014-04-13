package gui;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.*;

import javax.swing.*;

public class PicPanel extends JPanel implements ActionListener {

	public PicPanel(FlowLayout flowLayout) {
		super(flowLayout);
	}

	public void actionPerformed(ActionEvent e) {
		JComboBox cb = (JComboBox)e.getSource();
        String petName = (String)cb.getSelectedItem();
	}

	
}
