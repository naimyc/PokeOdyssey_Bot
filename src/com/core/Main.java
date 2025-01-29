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
	
	static Credentials c = new Credentials();
	
	static String user = c.getLocalUser();
	static String pass = c.getLocalPass();
	static String link = c.getLocalLink();
	static String db = c.getLocalDB();
    
	static {
		System.out.println(link + db);
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:mysql://"+ link + db);

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
				PreparedStatement preparedStatement = connection.prepareStatement("select * from mc_users order by user_id ASC;");
				ResultSet resultSet = preparedStatement.executeQuery()) {
				
			 while (resultSet.next()) {
	                int id = resultSet.getInt("user_id");
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
