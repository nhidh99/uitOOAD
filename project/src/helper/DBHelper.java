package helper;

import java.sql.*;

public class DBHelper {
	public static Connection getConnection() throws SQLException {
		String connString = "jdbc:mysql://localhost:3306/quanlikhachsan";
		String username = "root";
		String password = "admin";
		return DriverManager.getConnection(connString, username, password);
	}
}