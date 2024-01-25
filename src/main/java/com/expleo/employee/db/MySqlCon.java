package com.expleo.employee.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class MySqlCon {

	private Connection con;
	private static MySqlCon mySqlCon;
	
	private final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/";
	private final String DB_NAME = "expleo";
	private final String USERNAME = "root";
	private final String PASSWORD = "Sanskar@123";
	
	private MySqlCon() {
		
		//Load the driver..
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public static MySqlCon getInstance() {
		if(mySqlCon == null) {
			mySqlCon = new MySqlCon();
		}
		return mySqlCon;
	}
	
	//Get connection - if closed create again
	public Connection getConnection() {
		if(con != null) {
			try {
				if(con.isClosed()) {
					try {
						con = DriverManager.getConnection(DB_CONNECTION_URL+DB_NAME , USERNAME , PASSWORD);
						System.out.println("Connection Created....");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} else
			initDBConnection();
		
		return con;
	}

	
	//Open Connection : Return true when connection is created else false.
	private void initDBConnection() {
		try {
			con = DriverManager.getConnection(DB_CONNECTION_URL+DB_NAME , USERNAME , PASSWORD);
			System.out.println("Connection Created....");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	//Close Connection
	public void closeConnection() {
		try {
			con.close();
			if(con.isClosed())
			    System.out.println("Connection Closed..... ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
