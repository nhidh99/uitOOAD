package DAO;

import java.sql.*;
import java.util.*;
import DTO.NhanVienDTO;
import helper.DBHelper;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class NhanVienDAO {
	public static List<NhanVienDTO> getDSNhanVien() throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "SELECT * from NhanVien";
		ResultSet rs = statement.executeQuery(query);

		List<NhanVienDTO> output = new ArrayList<NhanVienDTO>();
		while (rs.next()) {
			output.add(new NhanVienDTO(
					rs.getInt("MaNhanVien"),
					rs.getString("TenNhanVien"),
					rs.getString("CMND"),
					rs.getString("DiaChi"),
					rs.getString("Email"),
					rs.getString("SoDienThoai"),
					rs.getString("ChucVu")));
		}
		conn.close();
		return output;
	}
	
	public static boolean deleteNhanVien(NhanVienDTO nhanVien) throws SQLException {
		if(nhanVien.getChucVu().equals("Quản lí")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể xóa người cùng chức vụ!");
			alert.showAndWait();	
			return false;
		}
		else
		{
			Connection conn = DBHelper.getConnection();
			Statement statement = conn.createStatement();
			String query = "Delete from NhanVien where MaNhanVien = '" + nhanVien.getMaNhanVien().toString() + "'";
			int result = statement.executeUpdate(query);
			conn.close();
			return result > 0;
		}
	}
	
	public static NhanVienDTO DangNhap(String taikhoan, String matkhau) throws SQLException {
		Connection conn = DBHelper.getConnection();
		Statement statement = conn.createStatement();
		String query = "SELECT * from NhanVien where TenTaiKhoan= '" + taikhoan + "' and MatKhau = '" + matkhau + "'";
		ResultSet rs = statement.executeQuery(query);
		NhanVienDTO result;
		if(rs.next()) {
			result = new NhanVienDTO(
					rs.getInt("MaNhanVien"),
					rs.getString("TenNhanVien"),
					rs.getString("CMND"),
					rs.getString("DiaChi"),
					rs.getString("Email"),
					rs.getString("SoDienThoai"),
					rs.getString("ChucVu"));
		}
		else {
			result = null;
		}
		conn.close();
		
		return result;
	}
}