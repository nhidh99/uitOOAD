package DAO;

import java.sql.*;
import java.util.*;

import DTO.LoaiPhongDTO;
import helper.DBHelper;

public class LoaiPhongDAO {

	public static List<LoaiPhongDTO> getDSLoaiPhong() throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			Statement statement = conn.createStatement();
			String query = "SELECT * FROM LoaiPhong WHERE KhaDung = true";
			ResultSet rs = statement.executeQuery(query);

			List<LoaiPhongDTO> output = new ArrayList<LoaiPhongDTO>();
			while (rs.next()) {
				output.add(new LoaiPhongDTO(rs.getInt("MaLoaiPhong"), rs.getString("TenLoaiPhong"),
						rs.getInt("SoKhachToiDa"), rs.getInt("DonGia")));
			}
			return output;
		} finally {
			conn.close();
		}
	}

	public static boolean checkLoaiPhong(String tenLoaiPhong) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "SELECT EXISTS (SELECT 1 FROM LoaiPhong WHERE TenLoaiPhong = ? LIMIT 1)";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, tenLoaiPhong);
			ResultSet rs = statement.executeQuery();
			rs.next();
			boolean isExist = rs.getBoolean(1);
			return isExist;
		} finally {
			conn.close();
		}
	}

	public static boolean deleteLoaiPhong(Integer maLoaiPhong) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "CALL del_LoaiPhong(?)";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, maLoaiPhong);
			int records = statement.executeUpdate();
			return records > 0;
		} finally {
			conn.close();
		}
	}

	public static boolean insertLoaiPhong(LoaiPhongDTO loaiPhong) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "INSERT INTO loaiphong (TenLoaiPhong, SoKhachToiDa, DonGia) VALUES (?, ?, ?)";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, loaiPhong.getTenLoaiPhong());
			statement.setInt(2, loaiPhong.getSoKhachToiDa());
			statement.setInt(3, loaiPhong.getDonGiaValue());
			int records = statement.executeUpdate();
			return records > 0;
		} finally {
			conn.close();
		}
	}

	public static boolean updateLoaiPhong(LoaiPhongDTO loaiPhong) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "UPDATE loaiphong SET TenLoaiPhong = ?, SoKhachToiDa = ?, DonGia = ? WHERE MaLoaiPhong = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, loaiPhong.getTenLoaiPhong());
			statement.setInt(2, loaiPhong.getSoKhachToiDa());
			statement.setInt(3, loaiPhong.getDonGiaValue());
			statement.setInt(4, loaiPhong.getMaLoaiPhong());
			statement.execute();
			return true;
		} finally {
			conn.close();
		}
	}

	public static LoaiPhongDTO getLoaiPhongById(int maLoaiPhong) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "SELECT * FROM LoaiPhong WHERE MaLoaiPhong = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, maLoaiPhong);
			ResultSet rs = statement.executeQuery();
			rs.next();
			LoaiPhongDTO output = new LoaiPhongDTO(maLoaiPhong, rs.getString("TenLoaiPhong"), rs.getInt("SoKhachToiDa"),
					rs.getInt("DonGia"));
			return output;
		} finally {
			conn.close();
		}
	}
}