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
		String query = "SELECT EXISTS (SELECT 1 FROM Phong WHERE MaLoaiPhong = ? LIMIT 1) AS 'TonTai'";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, maLoaiPhong);
		ResultSet rs = statement.executeQuery();
		rs.next();
		boolean isExist = rs.getBoolean("TonTai");
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
	
	public static boolean addLoaiPhong(String tenLoaiPhong, Integer soKhachToiDa, Integer donGia) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "INSERT INTO loaiphong (TenLoaiPhong,SoKhachToiDa,DonGia) VALUES (?, ?, ?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, tenLoaiPhong);
		statement.setInt(2, soKhachToiDa);
		statement.setInt(3, donGia);
		statement.execute();
		conn.close();
		return true;
	}
	
	public static boolean checkLoaiPhongTonTai(String tenLoaiPhong) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT * FROM loaiphong WHERE TenLoaiPhong = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, tenLoaiPhong);
		ResultSet rs = statement.executeQuery();
		boolean isExist = false;
		if(rs.next()) {
			isExist = true;
		}
		conn.close();
		return isExist;
	}
	
	public static boolean updateLoaiPhong(String tenLoaiPhongCu, String tenLoaiPhongMoi, Integer soKhachToiDa, Integer donGia) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "UPDATE loaiphong SET TenLoaiPhong = ?, SoKhachToiDa = ?, DonGia = ? WHERE TenLoaiPhong = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, tenLoaiPhongMoi);
		statement.setInt(2, soKhachToiDa);
		statement.setInt(3, donGia);
		statement.setString(4, tenLoaiPhongCu);
		statement.execute();
		conn.close();
		return true;
	}
}