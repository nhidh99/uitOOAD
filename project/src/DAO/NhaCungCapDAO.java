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
	
	public static boolean checkNhaCungCap(Integer maNhaCungCap) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT EXISTS (SELECT 1 FROM NhaCungCap WHERE MaNhaCungCap = ? LIMIT 1) AS 'TonTai'";
		PreparedStatement statement = conn.prepareStatement(query);
		ResultSet rs = statement.executeQuery();
		rs.next();
		boolean isExist = rs.getBoolean("TonTai");
		System.out.print(maNhaCungCap);
		conn.close();
		return isExist;
	}
	public static boolean deleteNhaCungCap(Integer maNhaCungCap) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "DELETE FROM NhaCungCap WHERE MaNhaCungCap = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, maNhaCungCap);
		statement.execute();
		conn.close();
		return true;
	}
}