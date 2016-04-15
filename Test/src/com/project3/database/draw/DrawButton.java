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
	
	public static void showMapButtons() {
		DrawButton.createButton("Charlois", 190, 50, 50, 500, DrawMap.buttons);
		DrawButton.createButton("Delfshaven", 190, 50, 50, 560, DrawMap.buttons);
		DrawButton.createButton("Feijenoord", 190, 50, 50, 620, DrawMap.buttons);
		DrawButton.createButton("Hillegersberg_Schiebroek", 190, 50, 50, 680, DrawMap.buttons);
		DrawButton.createButton("Hoek_van_holland", 190, 50, 50, 740, DrawMap.buttons);
		DrawButton.createButton("Hoogvliet", 190, 50, 50, 800, DrawMap.buttons);
		DrawButton.createButton("Ijsselmonde", 190, 50, 50, 860, DrawMap.buttons);
		DrawButton.createButton("Kralingen_Crooswijk", 190, 50, 250, 500, DrawMap.buttons);
		DrawButton.createButton("Noord", 190, 50, 250, 560, DrawMap.buttons);
		DrawButton.createButton("Overschie", 190, 50, 250, 620, DrawMap.buttons);
		DrawButton.createButton("Pernis", 190, 50, 250, 680, DrawMap.buttons);
		DrawButton.createButton("Prins_Alexander", 190, 50, 250, 740, DrawMap.buttons);
		DrawButton.createButton("Rozenburg", 190, 50, 250, 800, DrawMap.buttons);
		DrawButton.createButton("Stadscentrum", 190, 50, 250, 860, DrawMap.buttons);
	}
	
	

}
