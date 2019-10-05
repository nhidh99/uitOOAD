package DAO;

import helper.DBHelper;
import java.sql.*;
import java.util.*;

public class ListDAO {
	public static List<String> getListName() throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "SELECT * from DanhMuc";
		ResultSet rs = statement.executeQuery(query);
		
		List<String> output = new ArrayList<String>();
		while (rs.next()) {
			output.add(rs.getString(2));
		}
		conn.close();
		return output;
	}
	
	public static boolean deleteListByName(String name) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "DELETE FROM DanhMuc WHERE TenDM = ?"; 
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, name);
		statement.execute();
		conn.close();
		return true;
	}
	
	public static String getIdByName(String name) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT MaDM from DanhMuc WHERE TenDM = ?"; 
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, name);
		ResultSet rs = statement.executeQuery();
		rs.next();
		String output = rs.getString("MaDM");
		conn.close();
		return output;
	}
}
