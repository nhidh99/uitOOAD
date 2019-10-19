package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import DTO.*;
import helper.DBHelper;

public class PhongDAO {
	public static List<PhongDTO> getDSPhong() throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "SELECT * FROM view_DSPhong";
		ResultSet rs = statement.executeQuery(query);

		List<PhongDTO> output = new ArrayList<PhongDTO>();
		while (rs.next()) {
			TinhTrangDTO tinhTrang = new TinhTrangDTO(rs.getInt("MaTinhTrang"), rs.getString("TenTinhTrang"));
			LoaiPhongDTO loaiPhong = new LoaiPhongDTO(rs.getInt("MaLoaiPhong"), rs.getString("TenLoaiPhong"),
					rs.getInt("SoKhachToiDa"), rs.getInt("DonGia"));
			output.add(new PhongDTO(rs.getString("MaPhong"), loaiPhong, tinhTrang, rs.getString("GhiChu")));
		}
		conn.close();
		return output;
	}

	public static List<PhongDTO> getDSPhongCoTheThue(Timestamp ngayNhan, Timestamp ngayTra, Integer maLoaiPhong)
			throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "CALL search_DSPhong(?,?,?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setTimestamp(1, ngayNhan);
		statement.setTimestamp(2, ngayTra);
		statement.setInt(3, maLoaiPhong);
		ResultSet rs = statement.executeQuery();

		List<PhongDTO> output = new ArrayList<PhongDTO>();
		while (rs.next()) {
			TinhTrangDTO tinhTrang = new TinhTrangDTO(rs.getInt("MaTinhTrang"), rs.getString("TenTinhTrang"));
			LoaiPhongDTO loaiPhong = new LoaiPhongDTO(rs.getInt("MaLoaiPhong"), rs.getString("TenLoaiPhong"),
					rs.getInt("SoKhachToiDa"), rs.getInt("DonGia"));
			output.add(new PhongDTO(rs.getString("MaPhong"), loaiPhong, tinhTrang, rs.getString("GhiChu")));
		}
		conn.close();
		return output;

	}

	public static PhongDTO getPhongById(String maPhong) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT * FROM view_DSPhong WHERE MaPhong = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, maPhong);
		ResultSet rs = statement.executeQuery();
		rs.next();
		
		TinhTrangDTO tinhTrang = new TinhTrangDTO(rs.getInt("MaTinhTrang"), rs.getString("TenTinhTrang"));
		LoaiPhongDTO loaiPhong = new LoaiPhongDTO(rs.getInt("MaLoaiPhong"), rs.getString("TenLoaiPhong"), 
				rs.getInt("SoKhachToiDa"), rs.getInt("DonGia"));		
		PhongDTO output = new PhongDTO(rs.getString("MaPhong"), loaiPhong, tinhTrang, rs.getString("GhiChu"));
		conn.close();
		return output;
	}

	public static Integer getMaPTP(String maPhong) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT MaPTPHienTai FROM Phong WHERE MaPhong = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, maPhong);
		ResultSet rs = statement.executeQuery();
		rs.next();		
		Integer output = rs.getInt(1);
		conn.close();
		return output;
	}
}