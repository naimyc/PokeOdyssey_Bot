package com.core;

import io.github.cdimascio.dotenv.Dotenv;

public class Credentials {
	private final Dotenv dotenv = Dotenv.load();
    private final String local_link = dotenv.get("LOCAL_MYSQL_LINK");
    private final String local_db = dotenv.get("LOCAL_MYSQL_DB");
    private final String local_user = dotenv.get("LOCAL_MYSQL_USER");
    private final String local_pass = dotenv.get("LOCAL_MYSQL_PASS");
    
    private final String mysql_link = dotenv.get("MYSQL_LINK");
    private final String mysql_db = dotenv.get("MYSQL_DB");
    private final String mysql_user = dotenv.get("MYSQL_USER");
    private final String mysql_pass = dotenv.get("MYSQL_PASS");

    private final String token = dotenv.get("TOKEN");

    public String getLocalLink() {
		return local_link;
	}
    
    public String getLocalDB() {
		return local_db;
	}
    
	public String getLocalUser() {
		return local_user;
	}

	public String getLocalPass() {
		return local_pass;
	}

	public String getMysqlLink() {
		return mysql_link;
	}

	public String getMysqlDB() {
		return mysql_db;
	}

	public String getMysqlUser() {
		return mysql_user;
	}
	
	public String getMysqlPass() {
		return mysql_pass;
	}

	public String getToken() {
		return token;
	}
    
    
}
