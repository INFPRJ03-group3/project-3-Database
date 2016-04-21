package com.project3.database.screens;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.project3.database.Database;
import com.project3.database.draw.Button;
import com.project3.database.draw.Graph;
import com.project3.database.draw.PieChart;
import com.project3.database.other.Lists;

@SuppressWarnings("serial")
public class Screens extends JFrame {
	private static ArrayList<Integer> data_list = new ArrayList<>();
	private static ArrayList<String> region_names = new ArrayList<>();
	private static ArrayList<String> crime_types = new ArrayList<>();
	static String title;
	String text_vertical;
	String text_horizontal;
	boolean bar_text;

	public Screens(String title, String text_vertical, String text_horizontal, boolean bar_text) {
		this.title = title;
		this.text_vertical = text_vertical;
		this.text_horizontal = text_horizontal;
		this.bar_text = bar_text;
	}

	public void drawScreen() {
		for (JFrame frame : Lists.frames) {
			frame.dispose(); // Close all screens
		}
		Lists.frames.clear();
		data_list.clear();
		region_names.clear();
		crime_types.clear();

		JFrame frame = new JFrame();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(screenSize);
		frame.setVisible(true);
		frame.setTitle(title);

		JButton backbutton = Button.backButton(1600, 900);
		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Menumain.main(null); //Return back to the mainmenu when the backbutton is clicked
			}
		});
		frame.add(backbutton);
		PieChart chart = null;
		if (bar_text) {
			chart = new PieChart(title, data_list, crime_types); //Create a piechart
		} else {
			chart = new PieChart(title, data_list, region_names);
		}

		JButton chartbutton = Button.piechartButton(1600, 800, chart);
		frame.add(chartbutton);

		Lists.frames.add(frame);
		fill__queries(title);
		try {
			run_queries();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void run_queries() throws SQLException {		
		Database.dataConnect(); //Create database connection
		if (Database.con != null) {
			Statement st = Database.con.createStatement();
			
			if (bar_text) {				
				for (String query : Lists.queries) {
					ResultSet rs = st.executeQuery(query);
					ResultSetMetaData rsMetaData = rs.getMetaData();
					int numberOfColumns = rsMetaData.getColumnCount();	
					while (rs.next()) {						
						region_names.add(rs.getString(1)); //Add the names for the regions to a list
						data_list.add(Integer.parseInt(rs.getString(2))); //Add data(values) to a list
						crime_types.add(rsMetaData.getTableName(numberOfColumns)); //Get the table names and add it to the crime_types list
					}

				}
			} else {
				for (String query : Lists.queries) {
					ResultSet rs = st.executeQuery(query);					
					while (rs.next()) {
						ResultSetMetaData rsMetaData = rs.getMetaData();
						int numberOfColumns = rsMetaData.getColumnCount();	
						if (numberOfColumns == 1) { //the query for Safety Levels only returns 1 column
							data_list.add(Integer.parseInt(rs.getString(1)));	
						} else {
							region_names.add(rs.getString(1));
							data_list.add(Integer.parseInt(rs.getString(2)));
						}
					}
				}
			}

			Lists.graphs.clear();
			Graph graph = new Graph(700, 800, 100, 100, data_list, 20, title, text_vertical, text_horizontal,
					region_names, crime_types);

			graph.drawScreen();
			Lists.frames.get(0).revalidate(); //Update the screen
		} else {
			System.out.println("No database connection");

		}

	}

	private static void fill__queries(String title) { //Add queries depending on the screen title
		Lists.queries.clear();
		if (title == "Max Crime Percentage") {
			Lists.queries.add(
					"SELECT * FROM Bicycle_theft WHERE Crime_percentage IN (SELECT MAX(Crime_percentage) FROM Bicycle_theft)");
			Lists.queries.add(
					"SELECT * FROM Theft_from_car WHERE Crime_percentage IN (SELECT MAX(Crime_percentage) FROM Theft_from_car)");
			Lists.queries.add(
					"SELECT * FROM Damage_theft_car WHERE Crime_percentage IN (SELECT MAX(Crime_percentage) FROM Damage_theft_car)");
			Lists.queries.add(
					"SELECT * FROM Reported_stolen_vehicles WHERE Report_percentage IN (SELECT MAX(Report_percentage) FROM Reported_stolen_vehicles)");
			Lists.queries.add(
					"SELECT * FROM Reported_stolen_mopeds WHERE Report_percentage IN (SELECT MAX(Report_percentage) FROM Reported_stolen_mopeds)");
			Lists.queries.add(
					"SELECT * FROM Reported_pickpocketing WHERE Report_percentage IN (SELECT MAX(Report_percentage) FROM Reported_pickpocketing)");
			Lists.queries.add(
					"SELECT * FROM Reported_theft_from_other_vehicles WHERE Report_percentage IN (SELECT MAX(Report_percentage) FROM Reported_theft_from_other_vehicles)");
		} else if (title == "AvgIncome") {
			Lists.queries.add("SELECT * FROM avgincome");
		} else if (title == "HouseHolds") {
			Lists.queries.add("SELECT * FROM Households");
		} else if (title == "Safety Levels") {
			region_names.clear();
			region_names.add("Charlois");
			region_names.add("Delfshaven");
			region_names.add("Feijenoord");
			region_names.add("Hillegersberg_Schiebroek");
			region_names.add("Hoek_van_holland");
			region_names.add("Hoogvliet");
			region_names.add("Ijsselmonde");
			region_names.add("Kralingen_Crooswijk");
			region_names.add("Noord");
			region_names.add("Overschie");
			region_names.add("Pernis");
			region_names.add("Prins_Alexander");
			region_names.add("Rozenburg");
			region_names.add("Stadscentrum");
			for (String reg : region_names) {
				Lists.queries.add("SELECT CAST(AVG(crime_avg) AS DECIMAL(10,0)) FROM " + reg);
			}
			
		
		}

	}

}
