package DAO;

import java.sql.*;
import java.util.*;

import DTO.NhaCungCapDTO;
import helper.DBHelper;

public class NhaCungCapDAO {

	public static List<NhaCungCapDTO> getDSNhaCungCap() throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "SELECT * from NhaCungCap";
		ResultSet rs = statement.executeQuery(query);

		List<NhaCungCapDTO> output = new ArrayList<NhaCungCapDTO>();
		while (rs.next()) {
			output.add(new NhaCungCapDTO(
					rs.getInt("MaNhaCungCap"),
					rs.getString("TenNhaCungCap"),
					rs.getString("SoDienThoai")));
		}
		conn.close();
		return output;
	}
	public static Integer getMaNhaCungCap(String tenNhaCungCap) throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "SELECT * from NhaCungCap WHERE TenNhaCungCap = '" + tenNhaCungCap + "'";
		ResultSet rs = statement.executeQuery(query);
		Integer result = -1;
		if(rs.next()) {
			result = rs.getInt("MaNhaCungCap");
		}
		conn.close();
		return result;
	}
}