package com.project3.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.project3.database.draw.DrawMap;
import com.project3.database.draw.Graph;


public class Database {

	public static ArrayList<Integer> crime_data = new ArrayList<>();
	public static ArrayList<String> crime_types = new ArrayList<>();
	public static ArrayList<Integer> income_data = new ArrayList<>();
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
				DrawMap.main(args);

			} catch (Exception e) {
				e.printStackTrace();
			}			
		}		

	}
	
	public static void DrawGraph() throws SQLException {
		if (con != null) {
			if (queries.isEmpty() == false) {
				crime_data.clear();
				income_data.clear();
				graphs.clear();
				frames.clear();
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

				}
				Graph graph = new Graph(700, 800, 100, 100, crime_data, 20, DrawMap.current_region, "Percentage", "Crime type");
				Graph graph2 = new Graph(200, 700, 800, 100, income_data, 20, DrawMap.current_region, "Amount", "Data");		
				graph.drawScreen();	
				
				
				//DrawGraph.main(null);
				rs.close();
				rs1.close();
				st.close(); 
			} 
			
		}
		
	}

}
