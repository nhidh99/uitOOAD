package DAO;

import java.sql.*;
import java.util.*;

import DTO.NhanVienDTO;
import helper.DBHelper;

public class NhanVienDAO {
	public static List<NhanVienDTO> getDSNhanVien() throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "SELECT * FROM NhanVien WHERE ChucVu IS NOT NULL ORDER BY ChucVu DESC";
		ResultSet rs = statement.executeQuery(query);

		List<NhanVienDTO> output = new ArrayList<NhanVienDTO>();
		while (rs.next()) {
			output.add(new NhanVienDTO(rs.getInt("MaNhanVien"), rs.getString("TenNhanVien"), rs.getString("CMND"),
					rs.getString("DiaChi"), rs.getString("Email"), rs.getString("SoDienThoai"), rs.getString("ChucVu"),
					rs.getString("TenTaiKhoan"), rs.getString("MatKhau")));
		}
		conn.close();
		return output;
	}

	public static boolean checkNhanVien(NhanVienDTO nhanVien) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT (EXISTS (SELECT 1 FROM NhanVien WHERE CMND = ? AND MaNhanVien != ? AND ChucVu IS NOT NULL LIMIT 1))"
				+ "OR (EXISTS (SELECT 1 FROM NhanVien WHERE TenTaiKhoan = ? AND MaNhanVien != ? AND ChucVu IS NOT NULL LIMIT 1))";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, nhanVien.getCMND());
		statement.setInt(2, nhanVien.getMaNhanVien());
		statement.setString(3, nhanVien.getTaiKhoan());
		statement.setInt(4, nhanVien.getMaNhanVien());
		ResultSet rs = statement.executeQuery();
		rs.next();
		boolean isExist = rs.getBoolean(1);
		conn.close();
		return isExist;
	}

	public static boolean insertNhanVien(NhanVienDTO nhanVien) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "INSERT INTO NhanVien (TenTaiKhoan, MatKhau, TenNhanVien,"
				+ "CMND, DiaChi, Email, SoDienThoai, ChucVu) VALUES (?,?,?,?,?,?,?,?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, nhanVien.getTaiKhoan());
		statement.setString(2, nhanVien.getMatKhau());
		statement.setString(3, nhanVien.getTenNhanVien());
		statement.setString(4, nhanVien.getCMND());
		statement.setString(5, nhanVien.getDiaChi());
		statement.setString(6, nhanVien.getEmail());
		statement.setString(7, nhanVien.getSoDienThoai());
		statement.setString(8, nhanVien.getChucVu());
		statement.execute();
		conn.close();
		return true;
	}

	public static Integer getMaxMaNhanVien() throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "SELECT MAX(MaNhanVien) FROM NhanVien";
		ResultSet rs = statement.executeQuery(query);
		rs.next();
		Integer output = rs.getInt(1);
		conn.close();
		return output;
	}

	public static boolean updateNhanVien(NhanVienDTO nhanVien) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "UPDATE NhanVien "
				+ "SET TenNhanVien = ?, CMND = ?, DiaChi = ?, Email = ?, SoDienThoai = ?, ChucVu = ? "
				+ "WHERE MaNhanVien = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, nhanVien.getTenNhanVien());
		statement.setString(2, nhanVien.getCMND());
		statement.setString(3, nhanVien.getDiaChi());
		statement.setString(4, nhanVien.getEmail());
		statement.setString(5, nhanVien.getSoDienThoai());
		statement.setString(6, nhanVien.getChucVu());
		statement.setInt(7, nhanVien.getMaNhanVien());
		statement.execute();
		conn.close();
		return true;
	}

	public static NhanVienDTO getNhanVienById(Integer id) throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "SELECT * FROM NhanVien WHERE MaNhanVien = " + id;
		ResultSet rs = statement.executeQuery(query);
		rs.next();
		NhanVienDTO output = new NhanVienDTO(rs.getInt("MaNhanVien"), rs.getString("TenNhanVien"), rs.getString("CMND"),
				rs.getString("DiaChi"), rs.getString("Email"), rs.getString("SoDienThoai"), rs.getString("ChucVu"),
				rs.getString("TenTaiKhoan"), rs.getString("MatKhau"));
		conn.close();
		return output;
	}

	public static NhanVienDTO getNhanVienByUsername(String username) throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = String.format("SELECT * FROM NhanVien WHERE TenTaiKhoan = '%s'", username);
		ResultSet rs = statement.executeQuery(query);
		rs.next();
		NhanVienDTO output = new NhanVienDTO(rs.getInt("MaNhanVien"), rs.getString("TenNhanVien"), rs.getString("CMND"),
				rs.getString("DiaChi"), rs.getString("Email"), rs.getString("SoDienThoai"), rs.getString("ChucVu"),
				rs.getString("TenTaiKhoan"), rs.getString("MatKhau"));
		conn.close();
		return output;
	}

	public static boolean deleteNhanVien(Integer id) throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = String.format("CALL del_NhanVien(%d)", id);
		statement.executeQuery(query);
		conn.close();
		return true;
	}

	public static boolean checkLoginNhanVien(String username, String password) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "call login_NhanVien(?,?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, username);
		statement.setString(2, password);
		statement.execute();
		ResultSet rs = statement.executeQuery();
		rs.next();
		boolean output = rs.getBoolean(1);
		conn.close();
		return output;
	}
}