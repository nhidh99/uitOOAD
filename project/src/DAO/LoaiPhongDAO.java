package DAO;

import java.sql.*;
import java.util.*;

import DTO.LoaiPhongDTO;
import helper.DBHelper;

public class LoaiPhongDAO {

	public static List<LoaiPhongDTO> getDSLoaiPhong() throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "SELECT * from LoaiPhong";
		ResultSet rs = statement.executeQuery(query);

		List<LoaiPhongDTO> output = new ArrayList<LoaiPhongDTO>();
		while (rs.next()) {
			output.add(new LoaiPhongDTO(
					rs.getInt("MaLoaiPhong"),
					rs.getString("TenLoaiPhong"),
					rs.getInt("SoKhachToiDa"),
					rs.getInt("DonGia")));
		}
		conn.close();
		return output;
	}

	public static boolean checkLoaiPhong(Integer maLoaiPhong) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT EXISTS (SELECT 1 FROM Phong WHERE MaLoaiPhong = ? LIMIT 1)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, maLoaiPhong);
		ResultSet rs = statement.executeQuery();
		rs.next();
		boolean isExist = rs.getBoolean(1);
		conn.close();
		return isExist;
	}

	public static boolean deleteLoaiPhong(Integer maLoaiPhong) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "DELETE FROM LoaiPhong WHERE MaLoaiPhong = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, maLoaiPhong);
		statement.execute();
		conn.close();
		return true;
	}
}