package helper;

import java.sql.*;

public class DBHelper {
	public static Connection getConnection() throws SQLException {
		String connString = "jdbc:mysql://localhost:3306/QuanLiKhachSan";
		String username = "root";
		String password = "root";
		return DriverManager.getConnection(connString, username, password);
	}
}