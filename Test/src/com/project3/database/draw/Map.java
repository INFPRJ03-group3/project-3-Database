package com.project3.database.draw;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.project3.database.Database;
import com.project3.database.other.Lists;
import com.project3.database.screens.Menumain;

@SuppressWarnings("serial")
public class Map extends JFrame {
	private ImageIcon image1;
	private JLabel label1;
	public static ArrayList<JButton> buttons = new ArrayList<>();
	private ArrayList<String> queries = new ArrayList<>();
	private HashMap<JButton, String> crime_queries = new HashMap<>();
	private HashMap<JButton, String> income_queries = new HashMap<>();
	public static String current_region = null;
	private static String texture = "Rotterdam.png";
	public static JFrame frame = null;

	public Map() {
		image1 = new ImageIcon(getClass().getResource(texture));
		label1 = new JLabel(image1);

		Button.showMapButtons();

		if (crime_queries.isEmpty()) {
			fill_crime_queries();
		}
		if (income_queries.isEmpty()) {
			fill_income_queries();
		}

		if (buttons.isEmpty() == false) {
			for (JButton button : buttons) {
				add(button);

			}
			JButton backbutton = Button.backButton(1600, 900);

			backbutton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose(); // Close the current screen					
					for (JFrame frame : Lists.frames) {
						frame.dispose();
					}					
					Lists.graphs.clear();					

					Menumain.main(null); // Call the mainscreen
				}
			});
			add(backbutton);
		}

		addActions(buttons, queries);
		updateMap();
	}

	public void updateMap() {
		remove(label1); // remove the old map texture
		image1 = new ImageIcon(getClass().getResource(texture));
		label1 = new JLabel(image1);
		add(label1);
		validate(); // update the screen
	}

	private void addActions(ArrayList<JButton> buttons, ArrayList<String> queries) {
		for (JButton button : buttons) {
			button.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					for (Entry<JButton, String> entry : crime_queries.entrySet()) {
						JButton key = entry.getKey();
						String value = entry.getValue();
						if (key == button) {
							Lists.queries.clear();
							Lists.queries.add(value); // Change the queries used
														// (depends on what
														// button is clicked)
							current_region = button.getText(); // Is used to set
																// the graph and
																// screen title
						}
					}

					for (Entry<JButton, String> entry : income_queries.entrySet()) {
						JButton key = entry.getKey();
						String value = entry.getValue();
						if (key == button) {
							Lists.queries.add(value);

						}
					}
					try {
						Lists.frames.clear();
						Lists.graphs.clear();
						Database.DrawGraph(); // Draw graphs for the region
												// clicked
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});

			button.addMouseMotionListener(new MouseMotionListener() {

				public void mouseMoved(MouseEvent e) { // Change map texture
														// when the mouse is on
														// a button (red areas)
					texture = button.getText() + ".png";
					updateMap();
				}

				public void mouseDragged(MouseEvent e) {
					texture = button.getText() + ".png";
					updateMap();
				}
			});
		}

	}

	private void fill_crime_queries() {
		crime_queries.put(buttons.get(0), "SELECT * FROM Charlois");
		crime_queries.put(buttons.get(1), "SELECT * FROM Delfshaven");
		crime_queries.put(buttons.get(2), "SELECT * FROM Feijenoord");
		crime_queries.put(buttons.get(3), "SELECT * FROM Hillegersberg_Schiebroek");
		crime_queries.put(buttons.get(4), "SELECT * FROM Hoek_van_holland");
		crime_queries.put(buttons.get(5), "SELECT * FROM Hoogvliet");
		crime_queries.put(buttons.get(6), "SELECT * FROM Ijsselmonde");
		crime_queries.put(buttons.get(7), "SELECT * FROM Kralingen_Crooswijk");
		crime_queries.put(buttons.get(8), "SELECT * FROM Noord");
		crime_queries.put(buttons.get(9), "SELECT * FROM Overschie");
		crime_queries.put(buttons.get(10), "SELECT * FROM Pernis");
		crime_queries.put(buttons.get(11), "SELECT * FROM Prins_Alexander");
		crime_queries.put(buttons.get(12), "SELECT * FROM Rozenburg");
		crime_queries.put(buttons.get(13), "SELECT * FROM Stadscentrum");

	}

	private void fill_income_queries() {
		income_queries.put(buttons.get(0),
				"SELECT amount_households, average_income from Regions WHERE name = 'Charlois'");
		income_queries.put(buttons.get(1),
				"SELECT amount_households, average_income from Regions WHERE name = 'Delfshaven'");
		income_queries.put(buttons.get(2),
				"SELECT amount_households, average_income from Regions WHERE name = 'Feijenoord'");
		income_queries.put(buttons.get(3),
				"SELECT amount_households, average_income from Regions WHERE name = 'Hillegersberg_schiebroek'");
		income_queries.put(buttons.get(4),
				"SELECT amount_households, average_income from Regions WHERE name = 'Hoek_van_holland'");
		income_queries.put(buttons.get(5),
				"SELECT amount_households, average_income from Regions WHERE name = 'Hoogvliet'");
		income_queries.put(buttons.get(6),
				"SELECT amount_households, average_income from Regions WHERE name = 'Ijsselmonde'");
		income_queries.put(buttons.get(7),
				"SELECT amount_households, average_income from Regions WHERE name = 'Kralingen_crooswijk'");
		income_queries.put(buttons.get(8),
				"SELECT amount_households, average_income from Regions WHERE name = 'Noord'");
		income_queries.put(buttons.get(9),
				"SELECT amount_households, average_income from Regions WHERE name = 'Overschie'");
		income_queries.put(buttons.get(10),
				"SELECT amount_households, average_income from Regions WHERE name = 'Pernis'");
		income_queries.put(buttons.get(11),
				"SELECT amount_households, average_income from Regions WHERE name = 'Prins_Alexander'");
		income_queries.put(buttons.get(12),
				"SELECT amount_households, average_income from Regions WHERE name = 'Rozenburg'");
		income_queries.put(buttons.get(13),
				"SELECT amount_households, average_income from Regions WHERE name = 'Rotterdam_centrum'");
	}

	public static void main(String args[]) {
		if (frame == null) {
			frame = new Map();
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

			Button.showMapButtons();
			frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			frame.setVisible(true);
			frame.setSize(screenSize);
			frame.setTitle("Map");
			
		} else {
			frame.setVisible(true);			
		}

		
	}

}
