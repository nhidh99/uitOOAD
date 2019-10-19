package DAO;

import java.sql.*;
import java.util.*;

import DTO.KhachDTO;
import DTO.NhanVienDTO;
import helper.DBHelper;

public class KhachDAO{
	public static List<KhachDTO> getDSKhach(Integer maPhieuThue) throws SQLException{
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "SELECT * FROM khachhang WHERE MaPTPhong = " + maPhieuThue + "";
		ResultSet rs = statement.executeQuery(query);
		List<KhachDTO> output = new ArrayList<KhachDTO>();
		while(rs.next()) {
			output.add(new KhachDTO(
					rs.getInt("MaKhachHang"),
					rs.getInt("MaPTPhong"), 
					rs.getString("HoTen"), 
					rs.getString("CMND"),
					rs.getString("SoDienThoai"), 
					rs.getString("GioiTinh"), 
					rs.getString("QuocTich"),
					rs.getString("GhiChu")));
		}
		conn.close();
		return output;
	}
	
	public static boolean insertKhach(KhachDTO khach) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "INSERT INTO KhachHang (Hoten,SoDienThoai,GioiTinh,QuocTich,GhiChu)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, khach.getHoten());
		statement.setString(2, khach.getSDT());
		statement.setString(3, khach.getGioiTinh());
		statement.setString(4, khach.getQuocTich());
		statement.setString(5, khach.getGhiChu());
		int output = statement.executeUpdate();
		conn.close();
		return output > 0;
	}
	
	
	//Cach truyen tham so vao storeproceduced
	public static boolean deleteKhach(Integer makhachhang) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "CALL del_KhachByMaKhach(?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, makhachhang);
		int output = statement.executeUpdate();
		conn.close();
		return output > 0;
	}
	
	public static List<KhachDTO> searchKhach(String tenKhachHang, String CMND) throws SQLException{
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = String.format("SELECT HoTen, CMND, MaPhong FROM khachhang JOIN pt_phong ON khachhang.MaPTPhong = pt_phong.MaPTPhong\r\n" + 
				"	WHERE lower(HoTen) = lower('%s') OR CMND = '%s';", tenKhachHang, CMND);
		
		ResultSet rs = statement.executeQuery(query);
		List<KhachDTO> output = new ArrayList<KhachDTO>();
		while(rs.next()) {
			output.add(new KhachDTO(
					rs.getString("HoTen"), 
					rs.getString("CMND"),
					rs.getInt("MaPhong"))); 
		}
		conn.close();
		return output;
	}
	
	public static boolean updateKhach(KhachDTO khach) throws SQLException
	{
		Connection conn = DBHelper.getConnection();
		String query = "CALL update_Khach(?,?,?,?,?,?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, khach.getmaKhachHang());
		statement.setString(2, khach.getHoten());
		statement.setString(3, khach.getCMND());
		statement.setString(4, khach.getSDT());
		statement.setString(5, khach.getGioiTinh());
		statement.setString(6, khach.getGhiChu());
		int output = statement.executeUpdate();
		conn.close();
		return output > 0;
	}
	
}