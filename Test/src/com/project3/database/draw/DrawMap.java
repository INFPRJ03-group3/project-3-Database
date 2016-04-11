package com.project3.database.draw;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DrawMap extends JPanel {

	public static void main(String[] args) {
		DrawMap panel = new DrawMap();
		JFrame application = new JFrame();

		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.add(panel);
		application.setSize(2000, 960);
		application.setVisible(true);
		application.setTitle("Map");
	}

}
