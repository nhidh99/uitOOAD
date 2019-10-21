package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DTO.*;
import helper.DBHelper;

public class PhongDAO {
	public static List<PhongDTO> getDSPhong() throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "CALL get_DSPhong";
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

	public static boolean checkPhong(PhongDTO phong) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT * FROM phong WHERE MaPhong = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, phong.getMaPhong());
		ResultSet rs = statement.executeQuery();
		boolean isExist = false;
		if (rs.next()) {
			isExist = true;
		}
		conn.close();
		return isExist;
	}

	public static boolean insertPhong(PhongDTO phong) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "INSERT INTO phong(MaPhong, MaLoaiPhong, MaTinhTrang, GhiChu) VALUES(?,?,?,?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, phong.getMaPhong());
		statement.setInt(2, phong.getLoaiPhong().getMaLoaiPhong());
		statement.setInt(3, phong.getTinhTrang().getMaTinhTrang());
		statement.setString(4, phong.getGhiChu());
		statement.execute();
		conn.close();
		return true;
	}

	public static boolean deletePhong(PhongDTO phong) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "DELETE FROM phong WHERE MaPhong = ? ";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, phong.getMaPhong());
		statement.execute();
		conn.close();
		return true;
	}
	
	public static boolean updatePhong(PhongDTO phongCu, PhongDTO phongMoi) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "UPDATE phong SET MaPhong = ? , MaLoaiPhong = ?, MaTinhTrang = ?, GhiChu = ?"
				+ "WHERE MaPhong = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, phongMoi.getMaPhong());
		statement.setInt(2, phongMoi.getLoaiPhong().getMaLoaiPhong());
		statement.setInt(3, phongMoi.getTinhTrang().getMaTinhTrang());
		statement.setString(4, phongMoi.getGhiChu());
		statement.setString(5, phongCu.getMaPhong());
		statement.execute();
		conn.close();
		return true;
	}
	
	public static PhongDTO getPhong(String maPhong) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT * FROM phong WHERE MaPhong = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, maPhong);
		
		ResultSet rs = statement.executeQuery();
		if(rs.next())
		{
			return new PhongDTO(rs.getString(1), LoaiPhongDAO.getLoaiPhong(rs.getInt(2)), TinhTrangDAO.getTinhTrang(rs.getInt(3)), rs.getString(4));
		}
		else
			return null;
	}
}