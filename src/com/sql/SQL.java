package com.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.core.Credentials;

public class SQL {
	static private Credentials credentials = new Credentials();
	static private String DB_URL = credentials.getLocalDB(), USER = credentials.getLocalUser(), PASSWORD = credentials.getLocalPass();

	public void queryDatabase(String query, String[] values) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set values dynamically
            for (int i = 0; i < values.length; i++) {
                stmt.setString(i + 1, values[i]);
            }

            // Execute query
            if (query.trim().toUpperCase().startsWith("SELECT")) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    // Process result set as needed
                    System.out.println("Result: " + rs.getString(1));
                }
            } else {
                int rowsAffected = stmt.executeUpdate();
                System.out.println("Rows affected: " + rowsAffected);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	public String[] queryDatabase(String query, Object[] values) {
		String[] s = {""};
		
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set values dynamically
            for (int i = 0; i < values.length; i++) {
                stmt.setString(i + 1, values[i]);
            }

            // Execute query
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    // Process result set as needed
                    System.out.println("Result: " + rs.getString(1));

                } 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }
	

}
