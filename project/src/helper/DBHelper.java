package helper;

import java.sql.*;

public class DBHelper {
	public static Connection getConnection() throws SQLException {
		String connString = "jdbc:mysql://localhost:3306/qlks?autoReconnect=true&useSSL=false";
		String username = "root";
		String password = "1587397561";
		return DriverManager.getConnection(connString, username, password);
	}
}