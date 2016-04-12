package com.project3.database.draw;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class DrawMap extends JFrame {
	private ImageIcon image1;
	private JLabel label1;	
	private ArrayList<JButton> buttons = new ArrayList<>(); 

	public DrawMap() {
		image1 = new ImageIcon(getClass().getResource("Rotterdam.png"));	
		label1 = new JLabel(image1);
		
		showButtons();
		
		if (buttons.isEmpty() == false) {
			for (JButton button : buttons) {
				add(button);
			}
		}
	
		
		add(label1); 		 

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY(); 			
				
				System.out.println(x + ", " + y);
				DrawGraph.main(null);				
			}
		});
	}
	
	public void showButtons() {		
		createButton("Charlois", 190, 50, 50, 500);
		createButton("Delfshaven", 190, 50, 50, 560);
		createButton("Feijenoord", 190, 50, 50, 620);
		createButton("Hillegersberg_Schiebroek", 190, 50, 50, 680);
		createButton("Hoek_van_holland", 190, 50, 50, 740);	
		createButton("Hoogvliet", 190, 50, 50, 800);	
		createButton("Ijsselmonde", 190, 50, 50, 860);	
		createButton("Kralingen_Crooswijk", 190, 50, 250, 500);	
		createButton("Noord", 190, 50, 250, 560);	
		createButton("Overschie", 190, 50, 250, 620);	
		createButton("Pernis", 190, 50, 250, 680);	
		createButton("Prins_Alexander", 190, 50, 250, 740);	
		createButton("Rozenburg", 190, 50, 250, 800);	
		createButton("Stadscentrum", 190, 50, 250, 860);	

	}
	
	private void createButton(String text, Integer size_x, Integer size_y, Integer pos_x, Integer pos_y) {
		JButton button = new JButton(text); 
		button.setEnabled(true);	
		button.setSize(size_x, size_y);		
		button.setLocation(pos_x, pos_y);	
		buttons.add(button); 
	}
		

	public static void main(String args[]) {
		DrawMap draw = new DrawMap();	
		draw.showButtons();
		draw.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		draw.setVisible(true);
		draw.pack();
		draw.setTitle("Map");
	}

}
