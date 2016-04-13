package com.project3.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.project3.database.draw.DrawGraph;
import com.project3.database.draw.DrawMap;

//http://www.rotterdam.nl/Clusters/RSO/Document2015/OBI/Publicaties/5005%20Feitenkaart%20Wijk%20en%20buurt%20RIO%202013.pdf

public class Database {

	public static ArrayList<Integer> crime_data = new ArrayList<>();
	public static ArrayList<String> crime_types = new ArrayList<>();
	public static ArrayList<Integer> income_data = new ArrayList<>();
	public static ArrayList<String> queries = new ArrayList<>();

	public static void main(String[] args) {
		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ProjectDatabase", "postgres",
					"project3");
			if (con != null) {
				System.out.println("Connected");
			}

			if (queries.isEmpty() == false) {
				crime_data.clear();
				income_data.clear();
				Statement st = con.createStatement();

				ResultSet rs = st.executeQuery(queries.get(0));
				while (rs.next()) {
					System.out.println(rs.getString(1) + "|" + rs.getString(2));

					crime_types.add(rs.getString(1));
					crime_data.add(Integer.parseInt(rs.getString(2)));

				}

				ResultSet rs1 = st.executeQuery(queries.get(1));
				while (rs1.next()) {
					System.out.println(rs1.getString(1) + "|" + rs1.getString(2));
					income_data.add(Integer.parseInt(rs1.getString(1)));
					income_data.add(Integer.parseInt(rs1.getString(2)));

				}
				DrawMap.main(args);
				DrawGraph.main(null);
				rs.close();
				rs1.close();
				st.close();
			} else {
				DrawMap.main(args);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
