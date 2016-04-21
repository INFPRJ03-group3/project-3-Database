package com.project3.database.screens;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.project3.database.Database;
import com.project3.database.draw.Button;
import com.project3.database.draw.Graph;
import com.project3.database.other.Lists;

@SuppressWarnings("serial")
public class IncomeCrime extends JFrame {
	private static ArrayList<Integer> Income = new ArrayList<>();
	private static ArrayList<Integer> crime_percentages = new ArrayList<>();
	private static ArrayList<String> region_names = new ArrayList<>();
	private static ArrayList<String> income_queries = new ArrayList<>();

	public static void drawScreen() throws SQLException {
		for (JFrame frame : Lists.frames) {
			frame.dispose(); // Close all screens
		}
		Lists.frames.clear();
		Lists.graphs.clear();
		Income.clear();
		crime_percentages.clear();
		region_names.clear();
		region_names.clear();
		income_queries.clear();
		JFrame frame = new JFrame();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(screenSize);
		frame.setVisible(true);
		frame.setTitle("Income and crime connection");

		JButton backbutton = Button.backButton(1600, 900);
		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Menumain.main(null); // Return back to the mainmenu when the
										// backbutton is clicked
			}
		});
		frame.add(backbutton);
		Lists.frames.add(frame); 
		fill__queries();
		run_queries();
	}

	private static void run_queries() throws SQLException {
		if (Database.con == null) {
			Database.dataConnect(); // Create database connection
		}
		for (String query : income_queries) {
			Statement st = Database.con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				region_names.add(rs.getString(1)); 
				Income.add(Integer.parseInt(rs.getString(2)));
			}
		}		
		Graph graph = new Graph(200, 750, 100, 150, Income, 10, "Max and min income", "Income", "Region", region_names, null); 
		graph.drawScreen();
		run_queries2();
	}

	private static void run_queries2() throws SQLException {
		for (String region : region_names) {
			String query = "SELECT * FROM " + region + " WHERE crime_avg IN(SELECT MAX(crime_avg) FROM " + region + ")";		
			Statement st = Database.con.createStatement();
			ResultSet rs = st.executeQuery(query);		
			while (rs.next()) {				
				crime_percentages.add(Integer.parseInt(rs.getString(2))); 
			}
		}		
		Graph graph = new Graph(200, 750, 800, 150, crime_percentages, 10, "Highest crime percentage", "Percentage", "Region", region_names, null);
		graph.drawScreen();
	}
	
	private static void fill__queries() {
		income_queries.add("SELECT name, average_income FROM Regions WHERE average_income IN (SELECT MAX(average_income) FROM Regions)"); 
		income_queries.add("SELECT name, average_income FROM Regions WHERE average_income IN (SELECT MIN(average_income) FROM Regions)"); 
		
	}
}


