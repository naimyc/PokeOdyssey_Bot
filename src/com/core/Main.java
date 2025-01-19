package com.core;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
	private static final HikariDataSource dataSource;

	static {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:mysql://na02-db.cus.mc-panel.net/db_847364");
		config.setUsername("db_847364");
		config.setPassword("f045ff5165");
		config.setMaximumPoolSize(10); // Adjust as needed
		config.setMinimumIdle(2);

		dataSource = new HikariDataSource(config);
	}

	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public static void main(String[] args) {
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("select * from users;");
				ResultSet resultSet = preparedStatement.executeQuery()) {
				
			 while (resultSet.next()) {
	                int id = resultSet.getInt("mc_id");
	                String name = resultSet.getString("discord_name");
	                String d_id = resultSet.getString("discord_id");

	                System.out.println(id + "\t" + name + "\t\t" + d_id);
	            }

		} catch (SQLException e) {
			System.err.println("An error occurred while fetching users.");
			e.printStackTrace();
		}

	}
}
