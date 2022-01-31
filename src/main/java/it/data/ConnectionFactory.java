package it.data;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	public static final String URL = "jdbc:postgresql://localhost:5432/bancadb";
	public static final String USER = "postgres";
	public static final String PASS = "Zaniolo99!";
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(URL,USER,PASS);
			System.out.println("Connessione avvenuta");
				} catch (Exception e) {
			System.out.println("Connessione non eseguita!");
			e.printStackTrace();
		}
		
		return conn;
	}
}