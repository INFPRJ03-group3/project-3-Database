package com.project3.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.project3.database.draw.Graph;
import com.project3.database.other.Lists;


public class Database {
		
	public static Connection con;
	private static ArrayList<Integer> crime_data = new ArrayList<>();
	private static ArrayList<Integer> income_data = new ArrayList<>();

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
			Statement st = con.createStatement();			
			if (Lists.queries.isEmpty() == false) {				
				if (Lists.income_types.isEmpty()) {
					Lists.income_types.add("HouseHolds");
					Lists.income_types.add("Income");				
				}				
				Lists.graphs.clear();
				crime_data.clear();
				Lists.crime_types.clear();
				income_data.clear();					

				ResultSet rs = st.executeQuery(Lists.queries.get(0));
				while (rs.next()) {	
					Lists.crime_types.add(rs.getString(1));
					crime_data.add(Integer.parseInt(rs.getString(2)));
				}

				ResultSet rs1 = st.executeQuery(Lists.queries.get(1));
				while (rs1.next()) {					
					income_data.add(Integer.parseInt(rs1.getString(1)));
					income_data.add(Integer.parseInt(rs1.getString(2)));

				} for (JFrame frame : Lists.frames) { //Close old screens
					frame.dispose();					
				}
				Lists.frames.clear();				
				  				
				Graph graph = new Graph(700, 800, 100, 100, crime_data, 20, "CrimeTypes", "Percentage", "Crime type", Lists.crime_types, null);
				Graph graph2 = new Graph(200, 800, 1300, 100, income_data, 20, "Households and Income", "Amount", "Data", Lists.income_types, null);
				
				graph.drawScreen();	
				graph2.drawScreen();			
								
				rs.close();
				rs1.close();
				st.close(); 
			} 							
			
		}
		
	}

}
