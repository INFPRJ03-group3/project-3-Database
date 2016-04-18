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
import com.project3.database.other.Lists;

@SuppressWarnings("serial")
public class MaxCrime extends JFrame {
	private static ArrayList<Integer> data_list = new ArrayList<>();
	private static ArrayList<String> region_name = new ArrayList<>();
	private static ArrayList<String> crime_types = new ArrayList<>();	
	
	public static void drawScreen() {	
		for (JFrame frame : Lists.frames) {
			frame.dispose(); //Close all screens
		}	
		Lists.frames.clear();
		data_list.clear();
		region_name.clear();
		crime_types.clear();
			
		JFrame frame = new JFrame();			
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
		frame.setSize(screenSize);	
		frame.setVisible(true);		
		frame.setTitle("Highest Crime Percentages");	
		
		JButton backbutton = Button.backButton(1600, 900); 
		backbutton.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Menumain.main(null);				
			}
		});
		frame.add(backbutton);
		
		Lists.frames.add(frame);	
		fill__queries();	
		try {
			run_queries();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void run_queries() throws SQLException {
		Database.main(null);
		if (Database.con != null) {
			Statement st = Database.con.createStatement();		
			
			for (String query : Lists.queries) {
				ResultSet rs = st.executeQuery(query);
				
				ResultSetMetaData rsMetaData = rs.getMetaData();
			    int numberOfColumns = rsMetaData.getColumnCount(); 
			    crime_types.add(rsMetaData.getTableName(numberOfColumns)); 
			    
				while (rs.next()) {						
					region_name.add(rs.getString(1));						
					data_list.add(Integer.parseInt(rs.getString(2)));
				}
			}		
			
			Lists.graphs.clear();		
			
			Graph graph = new Graph(700, 800, 100, 100, data_list, 20, "Max Crime Percentages", "Percentage", "Crime type", region_name,crime_types);
			
			graph.drawScreen();
			Lists.frames.get(0).revalidate();
		} else {
			System.out.println("No database connection");
			
		}	
		
	}
	
	private static void fill__queries() {
		Lists.queries.clear();
		Lists.queries.add("SELECT * FROM Bicycle_theft WHERE Crime_percentage IN (SELECT MAX(Crime_percentage) FROM Bicycle_theft)");
		Lists.queries.add("SELECT * FROM Theft_from_car WHERE Crime_percentage IN (SELECT MAX(Crime_percentage) FROM Theft_from_car)");
		Lists.queries.add("SELECT * FROM Damage_theft_car WHERE Crime_percentage IN (SELECT MAX(Crime_percentage) FROM Damage_theft_car)");
		Lists.queries.add("SELECT * FROM Reported_stolen_vehicles WHERE Report_percentage IN (SELECT MAX(Report_percentage) FROM Reported_stolen_vehicles)");
		Lists.queries.add("SELECT * FROM Reported_stolen_mopeds WHERE Report_percentage IN (SELECT MAX(Report_percentage) FROM Reported_stolen_mopeds)");
		Lists.queries.add("SELECT * FROM Reported_pickpocketing WHERE Report_percentage IN (SELECT MAX(Report_percentage) FROM Reported_pickpocketing)");
		Lists.queries.add("SELECT * FROM Reported_theft_from_other_vehicles WHERE Report_percentage IN (SELECT MAX(Report_percentage) FROM Reported_theft_from_other_vehicles)");		

	}

}
