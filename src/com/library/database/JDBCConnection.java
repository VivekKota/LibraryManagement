package com.library.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class JDBCConnection {

	private static Connection con = null;

	// connecting to the database
	public static Connection connect() {

		try {
			Connection con = null;
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "7075725533");
			con.setAutoCommit(false);
			return con;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// close the connection
	public static void close() {

		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
