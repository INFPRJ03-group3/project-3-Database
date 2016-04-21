package com.project3.database.screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.project3.database.draw.Button;
import com.project3.database.other.Lists;

@SuppressWarnings("serial")
public class Explanations extends JFrame {
	private  ImageIcon image1;
	private JLabel label1;
	
	public static JFrame frame = null;
	
	public static void drawScreen() {
		if (frame == null) {
			Explanations exp = new Explanations();
			exp.prepareFrame();
		} else {
			Menumain.frame.setVisible(false);
			frame.setVisible(true);		
		}	
	}
	
	public void prepareFrame() {
		for (JFrame frame : Lists.frames) {
			frame.dispose();
		}
		frame = new JFrame("Explanations");
		image1 = new ImageIcon(getClass().getResource("exp.png"));		
		label1 = new JLabel(image1);
		
		JButton backbutton = Button.backButton(1600, 800);
		backbutton.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Menumain.main(null);
				
			}
		});
		frame.add(label1);		
		frame.pack();		
		frame.setLayout(null);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.add(backbutton);
		frame.setVisible(true);	
	}

}
