package com.project3.database.screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.project3.database.Database;
import com.project3.database.draw.Button;
import com.project3.database.draw.Map;
import com.project3.database.other.Lists;

@SuppressWarnings("serial")
public class Menumain extends JFrame {
	private ImageIcon image1;
	private JLabel label1;
	private ArrayList<JButton> buttons = new ArrayList<>();
	public static JFrame frame = null;
	

	public Menumain() {
		//calls the GUI
		prepareGui();
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		if (frame == null) {
			Menumain menuMain = new Menumain();
		} else {
			frame.setVisible(true);		
		}	
	}
	
	public void prepareGui() {
		//sets up the main menu frame.
		frame = new JFrame("Project 3");
		image1 = new ImageIcon(getClass().getResource("cover.jpg"));		
		label1 = new JLabel(image1);
				
		// creating the buttons
		Button.createButton("Highest crime percentages", 400, 75, 150, 300, buttons, 20);	
		Button.createButton("Average income", 350, 75, 150, 400, buttons, 20);	
		Button.createButton("Safety levels", 300, 75, 150, 500, buttons, 20);	
		Button.createButton("Households", 250, 75, 150, 600, buttons, 20);					
		Button.createButton("Regions", 200, 75, 150, 700, buttons, 20);
		Button.createButton("Quit", 150, 75, 150, 800, buttons, 20);
		
		
		for (JButton button : buttons) {				
			frame.add(button); //Add buttons to the frame
		} 
		//Add actions to the buttons:
		buttons.get(0).addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {				
				Screens maxcrime = new Screens("Max Crime Percentage", "Percentage", "Regions", true);
				maxcrime.drawScreen();
			}
		});
		
		buttons.get(1).addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				Screens avgincome = new Screens("AvgIncome", "Amount", "Regions", false);
				avgincome.drawScreen();
			}
		});
		
		buttons.get(2).addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				Screens avgincome = new Screens("Safety Levels", "Amount", "Regions", false);
				avgincome.drawScreen();
			}
		});
		
		buttons.get(3).addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				Screens avgincome = new Screens("HouseHolds", "Amount", "Regions", false);
				avgincome.drawScreen();
			}
		});
				
		buttons.get(4).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Database.dataConnect();; //Call database to get a connection with the database
				Map.main(null); //Draw the map 
			}
		});
		
		buttons.get(5).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);				
			}
		});
		
		frame.add(label1);		
		frame.pack();		
		frame.setLayout(null);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		Lists.frames.add(frame); 
	}
}
