package com.project3.database;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;

import com.project3.database.draw.Graph;


public class Database {
	public static List<Color> colors = Arrays.asList(Color.BLUE, Color.RED, Color.CYAN, Color.ORANGE, Color.GREEN, Color.MAGENTA, Color.DARK_GRAY);

	public static ArrayList<Integer> crime_data = new ArrayList<>();
	public static ArrayList<String> crime_types = new ArrayList<>();
	public static ArrayList<Integer> income_data = new ArrayList<>();
	public static ArrayList<String> income_types = new ArrayList<>();
	public static ArrayList<String> queries = new ArrayList<>();
	public static ArrayList<JFrame> frames = new ArrayList<>();
	public static ArrayList<Graph> graphs = new ArrayList<>();
	
	
	private static Connection con;

	public static void main(String[] args) {
		if (con == null) {
			try {
				Class.forName("org.postgresql.Driver");
				con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ProjectDatabase", "postgres",
						"project3");
				if (con != null) {
					System.out.println("Connected");
				}				

			} catch (Exception e) {
				e.printStackTrace();
			}			
		}		

	}
	
	public static void DrawGraph() throws SQLException {
		if (con != null) {
			if (queries.isEmpty() == false) {				
				if (income_types.isEmpty()) {
					income_types.add("HouseHolds");
					income_types.add("Income");				
				}				
				graphs.clear();
				crime_data.clear();
				crime_types.clear();
				income_data.clear();				
				Statement st = con.createStatement();

				ResultSet rs = st.executeQuery(queries.get(0));
				while (rs.next()) {	
					crime_types.add(rs.getString(1));
					crime_data.add(Integer.parseInt(rs.getString(2)));
				}

				ResultSet rs1 = st.executeQuery(queries.get(1));
				while (rs1.next()) {					
					income_data.add(Integer.parseInt(rs1.getString(1)));
					income_data.add(Integer.parseInt(rs1.getString(2)));

				} for (JFrame frame : frames) { //Close old screens
					frame.dispose();					
				}
				frames.clear();
				  				
				Graph graph = new Graph(700, 800, 100, 100, crime_data, 20, "CrimeTypes", "Percentage", "Crime type", crime_types);
				Graph graph2 = new Graph(200, 800, 1300, 100, income_data, 20, "Households and Income", "Amount", "Data", income_types);
				
				graph.drawScreen();	
				graph2.drawScreen();			
								
				rs.close();
				rs1.close();
				st.close(); 
			} 
			
		}
		
	}

}
