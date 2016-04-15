package com.project3.database.screens;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Menumain extends JFrame {
	private ImageIcon image1;
	private JLabel label1;

	
	public Menumain() {
		//calls the GUI
		prepareGui();
	}

	public static void main(String[] args) {
		Menumain menuMain = new Menumain();

	}
	
	public void prepareGui() {
		//sets up the main menu frame.
		JFrame frame = new JFrame("Project 3");
		image1 = new ImageIcon(getClass().getResource("main_background.png"));
		label1 = new JLabel(image1);	
		
		
		
		// creating the buttons
		JButton mapButton = new JButton("Map");
		mapButton.setSize(125, 50);
		mapButton.setLocation(100, 100);			
		
		JButton quitButton = new JButton("Quit");
		quitButton.setSize(125, 50);
		quitButton.setLocation(200, 200);	
		
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
				//NextPage frame = new NextPage();
				frame.setVisible(true); 
			}
		});
		frame.add(label1);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		frame.pack();
		frame.setSize(screenSize);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
}
