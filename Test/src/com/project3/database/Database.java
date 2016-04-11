package com.project3.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.project3.database.draw.DrawGraph;

//http://www.rotterdam.nl/Clusters/RSO/Document2015/OBI/Publicaties/5005%20Feitenkaart%20Wijk%20en%20buurt%20RIO%202013.pdf

public class Database {

	public static ArrayList<Integer> crime_data = new ArrayList<>();
	public static ArrayList<String> crime_types = new ArrayList<>();
	public static ArrayList<Integer> income_data = new ArrayList<>();

	public static void main(String[] args) {
		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Crime2", "postgres",
					"project3");
			if (con != null) {
				System.out.println("Connected");
			}
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery("SELECT * FROM Charlois");
			while (rs.next()) {
				System.out.println(rs.getString(1) + "|" + rs.getString(2));
								
				crime_types.add(rs.getString(1));
				crime_data.add(Integer.parseInt(rs.getString(2)));
				
				
				if (income_data.isEmpty()) {					
					income_data.add(43160); 
					income_data.add(4610); 
				}

			}
			rs.close();
			st.close();

			// st.execute(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}

		DrawGraph.main(args);		

	}

}
