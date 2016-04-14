package com.project3.database.draw;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class DrawButton extends JFrame { 	
	
	public static void createButton(String text, Integer size_x, Integer size_y, Integer pos_x, Integer pos_y, ArrayList<JButton> buttons) {
		JButton button = new JButton(text);
		button.setEnabled(true);
		button.setSize(size_x, size_y);
		button.setLocation(pos_x, pos_y);
		buttons.add(button);
	}
	

}
