package DAO;

import java.sql.*;
import java.util.*;

import DTO.KhachHangDTO;
import helper.DBHelper;

public class KhachHangDAO{
	public static List<KhachHangDTO> getDSKhachHang() throws SQLException{
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "SELECT * FROM KhachHang "
					+ "JOIN pt_phong"
					+ "	ON KhachHang.MaPTPhong = pt_phong.MaPTPhong";
		ResultSet rs = statement.executeQuery(query);
		
		List<KhachHangDTO> output = new ArrayList<KhachHangDTO>();
		while(rs.next()) {
			output.add(new KhachHangDTO(
					rs.getInt("MaKhachHang"),
					rs.getInt("MaPTPhong"), 
					rs.getString("HoTen"), 
					rs.getString("CMND"),
					rs.getString("SDT"), 
					rs.getString("GioiTinh"), 
					rs.getString("QuocTich"), 
					rs.getString("GhiChu")));
		}
		conn.close();
		return output;
	}
	
	public static boolean insertKhachHang(KhachHangDTO khach) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "INSERT INTO KhachHang (Hoten,SDT,GioiTinh,QuocTich,GhiChu)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, khach.getTenKhachHang());
		statement.setString(2, khach.getSDT());
		statement.setString(3, khach.getGioiTinh());
		statement.setString(4, khach.getQuocTich());
		statement.setString(5, khach.getGhiChu());
		int output = statement.executeUpdate();
		conn.close();
		return output > 0;
	}
	
	public static boolean updateKhachHang(KhachHangDTO khach) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "UPDATE KhachHang "
				+ "SET HoTen = ?,"
				+ "CMND = ?,"
				+ "SDT = ?,"
				+ "GioiTinh = ?,"
				+ "QuocTich = ?,"
				+ "GhiChu = ? ";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, khach.getTenKhachHang());
		statement.setString(2, khach.getCMND());
		statement.setString(3, khach.getSDT());
		statement.setString(4, khach.getGioiTinh());
		statement.setString(5, khach.getQuocTich());
		statement.setString(6, khach.getGhiChu());
		int output = statement.executeUpdate();
		conn.close();
		return output > 0;
	}
	
	public static boolean deleteKhachHang(Integer maKhachHang) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "CALL del_Khach(?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, maKhachHang);
		int output = statement.executeUpdate();
		conn.close();
		return output > 0;
	}
}