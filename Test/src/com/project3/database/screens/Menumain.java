package com.project3.database.screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.project3.database.Database;
import com.project3.database.draw.DrawMap;

@SuppressWarnings("serial")
public class Menumain extends JFrame {
	private ImageIcon image1;
	private JLabel label1;

	
	public Menumain() {
		//calls the GUI
		prepareGui();
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Menumain menuMain = new Menumain();

	}
	
	public void prepareGui() {
		//sets up the main menu frame.
		JFrame frame = new JFrame("Project 3");
		image1 = new ImageIcon(getClass().getResource("cover.jpg"));		
		label1 = new JLabel(image1);
				
		// creating the buttons
		JButton mapButton = new JButton("Map");
		mapButton.setSize(200, 100);
		mapButton.setLocation(600, 850);			
		
		JButton quitButton = new JButton("Quit");
		quitButton.setSize(200, 100);
		quitButton.setLocation(1200, 850);	
		
		frame.add(mapButton);
		frame.add(quitButton);
				
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);				
			}
		});
		
		mapButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Database.main(null); //Call database to get a connection with the database
				DrawMap.main(null); //Draw the map
			}
		});
		frame.add(label1);
		

		frame.pack();
		
		frame.setLayout(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
}
