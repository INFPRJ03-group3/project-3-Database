package com.project3.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database{

	public static void main(String[] args) {		
		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dvdstore", "postgres", "project3");
			if (con != null) {
				System.out.println("Connected");
			}
			Statement st = con.createStatement(); 
			//String sql = "Drop table person";
			
			ResultSet rs = st.executeQuery("SELECT * FROM actor");
			while (rs.next())
			{						   			
			   System.out.println(rs.getString(1) + "|" + rs.getString(2) + "|" + rs.getString(3) + "|" + rs.getString(4));			
			  
			} rs.close();
			st.close();
			
			//st.execute(sql);		
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

