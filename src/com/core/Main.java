package com.core;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
	private static final HikariDataSource dataSource;
	static Dotenv dotenv = Dotenv.load();
    static String user = dotenv.get("MYSQL_USER");
    static String pass = dotenv.get("MYSQL_PASS");
    
	static {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:mysql://localhost:3306/test_db");
		config.setUsername(user);
		config.setPassword(pass);
		config.setMaximumPoolSize(10); // Adjust as needed
		config.setMinimumIdle(2);

		dataSource = new HikariDataSource(config);
	}

	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public static void main(String[] args) {
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("select * from Persons;");
				ResultSet resultSet = preparedStatement.executeQuery()) {
				
			 while (resultSet.next()) {
	                int id = resultSet.getInt("PersonID");
	                String name = resultSet.getString("LastName");
	                String d_id = resultSet.getString("Address");

	                System.out.println(id + "\t" + name + "\t\t" + d_id);
	            }

		} catch (SQLException e) {
			System.err.println("An error occurred while fetching users.");
			e.printStackTrace();
		}

	}
}
