package helper;

import java.sql.*;

public class DBHelper {
	public static Connection getConnection() throws SQLException {
		String connString = "jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false";
		String username = "root";
		String password = "truongcoi123";
		return DriverManager.getConnection(connString, username, password);
	}
}