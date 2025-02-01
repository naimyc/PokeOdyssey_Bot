package com.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.core.Credentials;

public class SQL {
	static private Credentials credentials = new Credentials();
	static private String DB_URL = credentials.getLocalLink() + credentials.getLocalDB(),
			USER = credentials.getLocalUser(), PASSWORD = credentials.getLocalPass();

	// Private utility method to get connection
	private static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL, USER, PASSWORD);
	}

	// Method to handle SELECT queries and return results as a JSONObject
	public JSONObject queryDatabase(String query, Object[] values) throws JSONException, SQLException {
		JSONObject resultJson = new JSONObject(); // Create the JSON object to return results
		JSONArray resultArray = new JSONArray(); // This will hold the rows of results

		Connection conn = getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);

		// Set values dynamically
		if (values != null)
			for (int i = 0; i < values.length; i++) {
				stmt.setString(i + 1, (String) values[i]);
			}

		// Execute SELECT query
		try (ResultSet rs = stmt.executeQuery()) {
			// Process the result set
			while (rs.next()) {
				// Assuming you want to fetch all columns from the query
				JSONObject rowJson = new JSONObject();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					String columnName = rs.getMetaData().getColumnLabel(i);
					Object value = (rs.getString(i) == null) ? value = JSONObject.NULL: rs.getString(i);
					rowJson.put(columnName, value);
				}
				resultArray.put(rowJson); // Add each row as a JSONObject
			}
		}

		resultJson.put("data", resultArray); // Add data array to the result JSON

		return resultJson; // Return the result as a JSONObject
	}

	// Method to handle INSERT/UPDATE/DELETE queries and perform the operation
	public void insertQuery(String query, Object[] values) throws SQLException {
		Connection conn = getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);

		// Set values dynamically
		for (int i = 0; i < values.length; i++) {
			stmt.setObject(i + 1, values[i]);
		}

		// Execute the query (INSERT, UPDATE, DELETE)
		int rowsAffected = stmt.executeUpdate();
		System.out.println("Rows affected: " + rowsAffected);

	}

//	public static void main(String[] args) throws JSONException {
//		// Example for a SELECT query
//		String selectQuery = "SELECT * FROM mc_users";
//		String[] selectParams = { "18" };
//		JSONObject result = new SQL().queryDatabase(selectQuery, null); // Returns JSON with the query result
//		
//		System.out.println("Query Result: " + result.getJSONArray("data").getJSONObject(0).getInt("isRegistered"));// Pretty-print the JSON result
//
//		// Example for an INSERT query
////        String insertQuery = "INSERT INTO users (name, age) VALUES (?, ?)";
////        String[] insertParams = {"John Doe", "30"};
////        SQL.insertQuery(insertQuery, insertParams);  // Performs the insert operation
//	}
}
