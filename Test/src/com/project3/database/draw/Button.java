package com.project3.database.draw;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Button extends JFrame { 
	
	public static void createButton(String text, Integer size_x, Integer size_y, Integer pos_x, Integer pos_y, ArrayList<JButton> buttons, Integer text_size) {
		JButton button = new JButton(text);
		button.setEnabled(true);
		button.setSize(size_x, size_y);
		button.setLocation(pos_x, pos_y);
		if (text_size != null) {
			button.setFont(new Font(null, Font.BOLD, text_size));
		}		
		buttons.add(button);
	}
	
	public static void showMapButtons() {
		Button.createButton("Charlois", 190, 50, 50, 500, Map.buttons, null);
		Button.createButton("Delfshaven", 190, 50, 50, 560, Map.buttons, null);
		Button.createButton("Feijenoord", 190, 50, 50, 620, Map.buttons, null);
		Button.createButton("Hillegersberg_Schiebroek", 190, 50, 50, 680, Map.buttons, null);
		Button.createButton("Hoek_van_holland", 190, 50, 50, 740, Map.buttons, null);
		Button.createButton("Hoogvliet", 190, 50, 50, 800, Map.buttons, null);
		Button.createButton("Ijsselmonde", 190, 50, 50, 860, Map.buttons, null);
		Button.createButton("Kralingen_Crooswijk", 190, 50, 250, 500, Map.buttons, null);
		Button.createButton("Noord", 190, 50, 250, 560, Map.buttons, null);
		Button.createButton("Overschie", 190, 50, 250, 620, Map.buttons, null);
		Button.createButton("Pernis", 190, 50, 250, 680, Map.buttons, null);
		Button.createButton("Prins_Alexander", 190, 50, 250, 740, Map.buttons, null);
		Button.createButton("Rozenburg", 190, 50, 250, 800, Map.buttons, null);
		Button.createButton("Stadscentrum", 190, 50, 250, 860, Map.buttons, null);	
	}
	
	public static JButton backButton(Integer pos_x, Integer pos_y) {
		JButton button = new JButton("Back"); 
		button.setEnabled(true);
		button.setSize(190, 50);
		button.setLocation(pos_x, pos_y);		
		return button;
		
	}
	
	

}
